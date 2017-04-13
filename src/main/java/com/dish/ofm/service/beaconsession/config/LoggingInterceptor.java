package com.dish.ofm.service.beaconsession.config;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.lang.String.format;

@Component
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Logger logger = LoggerFactory.getLogger("LoggingInterceptor");
        Request request = chain.request();
        long t1 = System.nanoTime();
        String requestBodyAsString = maskDetails(getRequestBodyAsString(request));
        logger.info(format("Sending request %s %s with data %s", request.method(), request.url(), requestBodyAsString));
        logger.info("headers - "+  request.headers());

        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();
        String responseBodyString = response.body().string();
        Response copiedResponse = response.newBuilder()
            .body(ResponseBody.create(responseBody.contentType(), responseBodyString.getBytes()))
            .build();
        responseBodyString = maskDetails(responseBodyString);

        long t2 = System.nanoTime();
        logger.info(format("Received response %s for %s in %.1fms%n%s",
            responseBodyString, response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return copiedResponse;
    }

    private String getRequestBodyAsString(Request request) {
        final Request requestCopy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        try {
            if (requestCopy.body() == null) {
                return "...There was no data!";
            } else {
                requestCopy.body().writeTo(buffer);
                return buffer.readUtf8();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String maskDetails(String body) {
        return body
            .replaceAll("\"accountNumber\":(\\s?)\"\\d+(\\d{4})\"", "\"accountNumber\":$1\"****$2\"")
            .replaceAll("\"cardNumber\":(\\s?)\"\\d+(\\d{4})\"", "\"cardNumber\":$1\"****$2\"")
            .replaceAll("\"expirationMonth\":(\\s?)\"\\d+\"", "\"expirationMonth\":$1\"**\"")
            .replaceAll("\"expirationYear\":(\\s?)\"\\d+\"", "\"expirationYear\":$1\"****\"")
            .replaceAll("\"accountId\":(\\s?)\"\\d+(\\d{4})\"", "\"accountId\":$1\"****$2\"")
            .replaceAll("\"securityCode\":(\\s?)\"\\d+\"", "\"securityCode\":$1\"****\"")
            ;
    }
}
