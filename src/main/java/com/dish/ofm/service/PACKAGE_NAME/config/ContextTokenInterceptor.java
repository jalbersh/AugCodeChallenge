package com.dish.ofm.service.PACKAGE_NAME.config;

import com.dish.cloud.tracing.context.TraceContext;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class ContextTokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = new Request.Builder()
            .url(request.url())
            .method(request.method(), request.body())
            .tag(request.tag())
            .headers(request.headers());
        String token = TraceContext.getTrace(TraceContext.Keys.TOKEN);
        String traceId = TraceContext.getTrace(TraceContext.Keys.TRACE_ID);
        String sessionId = TraceContext.getTrace(TraceContext.Keys.SESSION_ID);

        addHeader(requestBuilder, token, "X-Context-Token");
        addHeader(requestBuilder, traceId, "X-Context-Trace-Id");
        addHeader(requestBuilder, sessionId, "X-Context-Session-Id");

        requestBuilder.addHeader("X-Context-Request-Id", UUID.randomUUID().toString());

        return chain.proceed(requestBuilder.build());
    }

    private void addHeader(Request.Builder requestBuilder, String token, String name) {
        if (token != null && !token.equals("null")) {
            requestBuilder.addHeader(name, token);
        }
    }
}
