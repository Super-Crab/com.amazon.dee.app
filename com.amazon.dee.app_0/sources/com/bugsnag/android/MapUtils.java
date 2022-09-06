package com.bugsnag.android;

import androidx.annotation.Nullable;
import java.util.Map;
/* loaded from: classes.dex */
final class MapUtils {
    MapUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String getStringFromMap(String str, Map<String, Object> map) {
        Object obj = map.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }
}
