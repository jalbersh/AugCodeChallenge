package com.dish.ofm.service.beaconsession.config;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MisconfiguredEndpointInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if(response.code() == 405){
            throw new MisconfiguredEndpointException("Invalid endpoint: "+request.url().toString());
        }

       return response;
    }
 }
