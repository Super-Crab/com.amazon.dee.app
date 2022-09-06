package com.amazon.alexa.client.metrics.core;

import java.util.Map;
/* loaded from: classes6.dex */
public class AlexaMetricsUtils {
    private AlexaMetricsUtils() {
    }

    public static String concatValues(String... strArr) {
        int i = 0;
        if (strArr.length == 1) {
            return strArr[0];
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        while (i < strArr.length - 1) {
            sb.append(":");
            i++;
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public static Double getAsDouble(Map map, Object obj) {
        Object obj2 = map.get(obj);
        if (obj2 == null || !(obj2 instanceof Number)) {
            return null;
        }
        return Double.valueOf(((Number) obj2).doubleValue());
    }

    public static String getAsString(Map map, Object obj) {
        Object obj2 = map.get(obj);
        if (obj2 == null || !(obj2 instanceof String)) {
            return null;
        }
        return (String) obj2;
    }
}
