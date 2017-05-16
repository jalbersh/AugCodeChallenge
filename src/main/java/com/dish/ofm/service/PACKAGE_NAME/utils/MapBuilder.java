package com.dish.ofm.service.PACKAGE_NAME.utils;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

public class MapBuilder<K, V> {
    private final Map<K, V> map = new HashMap<>();

    public MapBuilder<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }

    public MapBuilder<K, V> remove(K key) {
        map.remove(key);
        return this;
    }

    public Map<K, V> build() {
        return unmodifiableMap(map);
    }

    public static MapBuilder<String, Object> stringObjectMapBuilder() {
        return new MapBuilder<>();
    }

    public static MapBuilder<String, String> mapBuilder() {
        return new MapBuilder<>();
    }
}
