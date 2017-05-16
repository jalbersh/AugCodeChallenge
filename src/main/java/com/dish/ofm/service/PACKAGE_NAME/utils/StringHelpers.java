package com.dish.ofm.service.PACKAGE_NAME.utils;

import com.google.common.base.CaseFormat;

import java.util.Map;

public class StringHelpers {
    public static String interpolateString(String string, Map<String, String> map) {
        return map.keySet().stream()
            .filter(string::contains)
            .map(key -> string.replaceAll("\\{\\{" + key + "\\}\\}", map.get(key)))
            .findFirst()
            .orElse(string);
    }

    public static String lowerSnakeToLowerCamel(String snakeCase) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, snakeCase);
    }
}
