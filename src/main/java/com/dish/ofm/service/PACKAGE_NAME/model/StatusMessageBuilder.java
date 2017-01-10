package com.dish.ofm.service.PACKAGE_NAME.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatusMessageBuilder {

    private List<String> success;
    private List<String> info;
    private List<String> warning;
    private List<String> error;

    public StatusMessageBuilder() {
        success = new ArrayList<>();
        info = new ArrayList<>();
        warning = new ArrayList<>();
        error = new ArrayList<>();
    }

    public static StatusMessageBuilder statusMessageBuilder() {
        return new StatusMessageBuilder();
    }

    public static StatusMessageBuilder statusMessageBuilder(StatusMessage statusMessage) {
        return statusMessageBuilder()
            .success(statusMessage.getSuccess())
            .info(statusMessage.getInfo())
            .warning(statusMessage.getWarning())
            .error(statusMessage.getError());
    }

    public StatusMessageBuilder success(String... success) {
        this.success.addAll(Arrays.asList(success));
        return this;
    }

    public StatusMessageBuilder info(String... info) {
        this.info.addAll(Arrays.asList(info));
        return this;
    }

    public StatusMessageBuilder warning(String... warning) {
        this.warning.addAll(Arrays.asList(warning));
        return this;
    }

    public StatusMessageBuilder error(String... error) {
        this.error.addAll(Arrays.asList(error));
        return this;
    }

    public StatusMessageBuilder error(List<String> errorList) {
        this.error.addAll(errorList);
        return this;
    }

    public StatusMessageBuilder info(List<String> infoList) {
        this.info.addAll(infoList);
        return this;
    }

    public StatusMessageBuilder warning(List<String> warningList) {
        this.warning.addAll(warningList);
        return this;
    }

    public StatusMessageBuilder success(List<String> successList) {
        this.success.addAll(successList);
        return this;
    }


    public StatusMessage build() {
        return new StatusMessage(success, info, warning, error);
    }

    public StatusMessageBuilder uniqueWarning(String warning) {
        if (!this.warning.contains(warning)) {
            this.warning.add(warning);
        }
        return this;
    }
}
