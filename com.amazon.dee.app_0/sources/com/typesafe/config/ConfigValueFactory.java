package com.typesafe.config;

import com.typesafe.config.impl.ConfigImpl;
import java.util.Map;
/* loaded from: classes3.dex */
public final class ConfigValueFactory {
    private ConfigValueFactory() {
    }

    public static ConfigValue fromAnyRef(Object obj, String str) {
        return ConfigImpl.fromAnyRef(obj, str);
    }

    public static ConfigList fromIterable(Iterable<? extends Object> iterable, String str) {
        return (ConfigList) fromAnyRef(iterable, str);
    }

    public static ConfigObject fromMap(Map<String, ? extends Object> map, String str) {
        return (ConfigObject) fromAnyRef(map, str);
    }

    public static ConfigValue fromAnyRef(Object obj) {
        return fromAnyRef(obj, null);
    }

    public static ConfigList fromIterable(Iterable<? extends Object> iterable) {
        return fromIterable(iterable, null);
    }

    public static ConfigObject fromMap(Map<String, ? extends Object> map) {
        return fromMap(map, null);
    }
}
