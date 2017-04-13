
package com.dish.ofm.service.PACKAGE_NAME.model;

import static com.dish.ofm.service.PACKAGE_NAME.model.StatusMessageBuilder.statusMessageBuilder;

public class RestResponse<T> {
    private final T data;
    private final StatusMessage messages;

    public RestResponse(T data, StatusMessage messages) {
        this.data = data;
        this.messages = messages;
    }

    public RestResponse(T data) {
        this(data, statusMessageBuilder().build());
    }

    public T getData() {
        return data;
    }

    public StatusMessage getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestResponse<?> restResponse = (RestResponse<?>) o;

        if (data != null ? !data.equals(restResponse.data) : restResponse.data != null) return false;
        return messages != null ? messages.equals(restResponse.messages) : restResponse.messages == null;

    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "data=" + data +
                ", messages=" + messages +
                '}';
    }
}
