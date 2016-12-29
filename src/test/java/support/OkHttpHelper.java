package support;

import com.squareup.okhttp.*;
import okio.Buffer;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Map;

import static com.squareup.okhttp.MediaType.parse;

public class OkHttpHelper {
    public static String getRequestBodyAsString(Request request) {
        final Request requestCopy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        try {
            requestCopy.body().writeTo(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.readUtf8();
    }

    public static Response getJsonResponse(Request request, String response) {
        return getJsonResponse(request, response, 200);
    }

    public static Response getJsonResponse(Request request, String response, int statusCode) {
        return new Response.Builder()
            .addHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
            .code(statusCode)
            .body(ResponseBody.create(parse(MediaType.APPLICATION_JSON_VALUE), response))
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .build();
    }

    public static Request getJsonGetRequest(String url) {
        return new Request.Builder()
            .header("Accept", "application/json")
            .get()
            .url(url)
            .build();
    }

    public static Request getJsonPostRequest(String url, String requestBody) {
        return new Request.Builder()
            .header("Accept", "application/json")
            .post(RequestBody.create(parse(MediaType.APPLICATION_ATOM_XML_VALUE), requestBody))
            .url(url)
            .build();
    }

    public static Response buildMockOkHttpJsonResponse(String jsonResponse) {
        return new Response.Builder()
            .request(new Request.Builder().url("http://example.com").build())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .addHeader("Content-Type", "application/json")
            .body(ResponseBody.create(parse("application/json; charset=utf-8"), jsonResponse))
            .build();
    }

    public static Response buildMockOkHttpJsonResponse(String jsonResponse, int code) {
        return new Response.Builder()
            .request(new Request.Builder().url("http://example.com").build())
            .protocol(Protocol.HTTP_1_1)
            .code(code)
            .addHeader("Content-Type", "application/json")
            .body(ResponseBody.create(parse("application/json; charset=utf-8"), jsonResponse))
            .build();
    }

    public static Response buildMockOkHttpJsonResponse(String jsonResponse, int code, Map<String, String> headers) {
        return new Response.Builder()
            .request(new Request.Builder().url("http://example.com").build())
            .protocol(Protocol.HTTP_1_1)
            .code(code)
            .addHeader("Content-Type", "application/json")
            .headers(Headers.of(headers))
            .body(ResponseBody.create(parse("application/json; charset=utf-8"), jsonResponse))
            .build();
    }

    public static Response buildMockOkHttpStringResponse(String stringResponse) {
        return new Response.Builder()
            .request(new Request.Builder().url("http://example.com").build())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .body(ResponseBody.create(parse("text/plain; charset=utf-8"), stringResponse))
            .build();
    }

    public static Response buildMockOkHttpStringResponse(String stringResponse, int code) {
        return new Response.Builder()
            .request(new Request.Builder().url("http://example.com").build())
            .protocol(Protocol.HTTP_1_1)
            .code(code)
            .body(ResponseBody.create(parse("text/plain; charset=utf-8"), stringResponse))
            .build();
    }
}
