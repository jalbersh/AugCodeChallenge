package support;

import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FakeHttpClient extends OkHttpClient {
    private final Map<String, Response> responseMap;
    private final Map<String, Request> madeRequests;

    public FakeHttpClient() {
        responseMap = new HashMap<>();
        madeRequests = new HashMap<>();
    }

    public void setResponse(String method, String url, Response response) {
        responseMap.put(getMapKey(method, url), response);
    }

    public Map<String, Request> getMadeRequests() {
        return madeRequests;
    }

    public Request getMadeRequest(String method, String url) {
        return madeRequests.get(getMapKey(method, url));
    }

    private String getMapKey(String method, String url) {
        return method + " " + url;
    }

    private String getMapKey(Request request) {
        return getMapKey(request.method(), request.urlString());
    }

    @Override
    public Call newCall(Request request) {
        return new FakeCall(request);
    }

    public void reset() {
        responseMap.clear();
        madeRequests.clear();
    }

    public class FakeCall extends Call {
        private final Request request;

        public FakeCall(Request request) {
            super(FakeHttpClient.this, request);
            this.request = request;
        }

        @Override
        public Response execute() throws IOException {
            updateMadeRequests();
            Response response = getResponse();

            if (response != null) {
                return response;
            }
            String message = String.format(
                "Could not execute request %s.\nMapped responses: %s",
                request.toString(),
                responseMap.keySet().toString());
            throw new IOException(message);
        }

        @Override
        public void enqueue(Callback responseCallback) {
            updateMadeRequests();
            try {
                responseCallback.onResponse(getResponse());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private Request updateMadeRequests() {
            return madeRequests.put(getMapKey(request), request);
        }

        private Response getResponse() {
            return responseMap.remove(getMapKey(request));
        }
    }
}
