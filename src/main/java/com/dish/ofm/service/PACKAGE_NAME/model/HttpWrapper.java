package com.dish.ofm.service.PACKAGE_NAME.model;

import org.springframework.http.HttpStatus;

public class HttpWrapper<T> {
    private HttpStatus statusCode;
    private StatusMessage messages;
    private T data;

    public HttpWrapper(T data, HttpStatus statusCode, StatusMessage messages) {
        this.data = data;
        this.statusCode = statusCode;
        this.messages = messages;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
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

        HttpWrapper<?> that = (HttpWrapper<?>) o;

        if (statusCode != that.statusCode) return false;
        if (messages != null ? !messages.equals(that.messages) : that.messages != null) return false;
        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override
    public int hashCode() {
        int result = statusCode != null ? statusCode.hashCode() : 0;
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HttpWrapper{" +
            "statusCode=" + statusCode +
            ", messages=" + messages +
            ", data=" + data +
            '}';
    }
}
