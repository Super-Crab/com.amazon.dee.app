package com.amazon.dee.app.util;

import java.util.Map;
/* loaded from: classes12.dex */
public final class MapUtils {
    private MapUtils() {
    }

    public static Double getAsDouble(Map map, Object obj) {
        Object obj2 = map.get(obj);
        if (obj2 == null || !(obj2 instanceof Number)) {
            return null;
        }
        return Double.valueOf(((Number) obj2).doubleValue());
    }
}
