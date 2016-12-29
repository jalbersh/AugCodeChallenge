package support;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class BrokenHttpClient extends FakeHttpClient {
    public class BrokenCall extends FakeCall {
        private final Request request;

        public BrokenCall(Request request) {
            super(request);
            this.request = request;
        }

        @Override
        public Response execute() throws IOException {
            throw new IOException("something went wrong");
        }
    }
}
