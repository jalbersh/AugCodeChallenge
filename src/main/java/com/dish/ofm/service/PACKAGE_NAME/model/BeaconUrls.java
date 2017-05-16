package com.dish.ofm.service.PACKAGE_NAME.model;

import java.util.Map;

public class BeaconUrls {
    private Map<String, String> urls;

    public BeaconUrls(Map<String, String> urls) {
        this.urls = urls;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeaconUrls that = (BeaconUrls) o;

        return urls != null ? urls.equals(that.urls) : that.urls == null;
    }

    @Override
    public int hashCode() {
        return urls != null ? urls.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BeaconUrls{" +
            "urls=" + urls +
            '}';
    }
}
