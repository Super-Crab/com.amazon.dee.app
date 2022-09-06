package org.apache.commons.lang3.text;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
/* loaded from: classes4.dex */
public abstract class StrLookup<V> {
    private static final StrLookup<String> NONE_LOOKUP = new MapStrLookup(null);

    /* loaded from: classes4.dex */
    static class MapStrLookup<V> extends StrLookup<V> {
        private final Map<String, V> map;

        MapStrLookup(Map<String, V> map) {
            this.map = map;
        }

        @Override // org.apache.commons.lang3.text.StrLookup
        public String lookup(String str) {
            V v;
            Map<String, V> map = this.map;
            if (map == null || (v = map.get(str)) == null) {
                return null;
            }
            return v.toString();
        }
    }

    protected StrLookup() {
    }

    private static Properties copyProperties(Properties properties) {
        if (properties == null) {
            return null;
        }
        Properties properties2 = new Properties();
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            properties2.setProperty(str, properties.getProperty(str));
        }
        return properties2;
    }

    public static <V> StrLookup<V> mapLookup(Map<String, V> map) {
        return new MapStrLookup(map);
    }

    public static StrLookup<?> noneLookup() {
        return NONE_LOOKUP;
    }

    public static StrLookup<String> systemPropertiesLookup() {
        Properties properties;
        try {
            properties = System.getProperties();
        } catch (SecurityException unused) {
            properties = null;
        }
        return new MapStrLookup(copyProperties(properties));
    }

    public abstract String lookup(String str);
}
