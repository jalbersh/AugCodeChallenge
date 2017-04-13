package com.dish.ofm.service.PACKAGE_NAME.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class StatusMessage {
    private List<String> success;
    private List<String> info;
    private List<String> warning;
    private List<String> error;

    public StatusMessage(
        @JsonProperty(value = "success") List<String> success,
        @JsonProperty(value = "info") List<String> info,
        @JsonProperty(value = "warning") List<String> warning,
        @JsonProperty(value = "error") List<String> error) {

        if (success == null) {
            this.success = new ArrayList<>();
        } else {
            this.success = success;
        }

        if (info == null) {
            this.info = new ArrayList<>();
        } else {
            this.info = info;
        }

        if (warning == null) {
            this.warning = new ArrayList<>();
        } else {
            this.warning = warning;
        }

        if (error == null) {
            this.error = new ArrayList<>();
        } else {
            this.error = error;
        }
    }

    public List<String> getSuccess() {
        return success;
    }

    public List<String> getInfo() {
        return info;
    }

    public List<String> getWarning() {
        return warning;
    }

    public List<String> getError() {
        return error;
    }

    public StatusMessage addErrors(List<String> messages) {
        if (this.error != null && messages != null) {
            this.error.addAll(messages);
        } else if (this.error == null) {
            this.error = messages;
        }
        return this;
    }

    public StatusMessage addInfos(List<String> messages) {
        if (this.info != null && messages != null) {
            this.info.addAll(messages);
        } else if (this.info == null) {
            this.info = messages;
        }
        return this;
    }

    public StatusMessage addWarnings(List<String> messages) {
        if (this.warning != null && messages != null) {
            this.warning.addAll(messages);
        } else if (this.warning == null) {
            this.warning = messages;
        }
        return this;
    }

    @Override
    public String toString() {
        return "StatusMessage{" +
            "success=" + success +
            ", info=" + info +
            ", warning=" + warning +
            ", error=" + error +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusMessage that = (StatusMessage) o;

        if (success != null ? !success.equals(that.success) : that.success != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (warning != null ? !warning.equals(that.warning) : that.warning != null) return false;
        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        int result = success != null ? success.hashCode() : 0;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (warning != null ? warning.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        return result;
    }
}
