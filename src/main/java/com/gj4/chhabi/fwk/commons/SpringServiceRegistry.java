package com.gj4.chhabi.fwk.commons;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author Krunal Lukhi
 * @since 03/08/24
 */
public abstract class SpringServiceRegistry<Key, Value> {

    private final Map<Key, Value> serviceMap = new ConcurrentHashMap<>();

    public SpringServiceRegistry(List<Value> services, Function<Value, Key> keyFunction) {
        services.forEach(service -> {
            Key key = keyFunction.apply(service);
            if (key == null) {
                return;
            }
            serviceMap.put(key, service);
        });
    }

    public Value get(Key key) {
        if (key == null) {
            return defaultValue();
        }
        Value value = serviceMap.get(key);
        if (value == null) {
            return defaultValue();
        }
        return value;
    }

    public abstract Value defaultValue();
}
