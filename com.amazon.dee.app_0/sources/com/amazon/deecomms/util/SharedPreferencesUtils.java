package com.amazon.deecomms.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class SharedPreferencesUtils {
    private static Map<String, Object> cacheValuesMap = new HashMap();

    private SharedPreferencesUtils() {
    }

    public static void cache(@NonNull Context context, @NonNull String str, boolean z) {
        cacheValuesMap.put(str, Boolean.valueOf(context.getSharedPreferences("SHARED_PREFS", 0).getBoolean(str, z)));
    }

    public static boolean getBooleanValue(@NonNull Context context, @NonNull String str, boolean z) {
        return context.getSharedPreferences("SHARED_PREFS", 0).getBoolean(str, z);
    }

    public static Object getCacheValue(@NonNull Context context, @NonNull String str, boolean z) {
        if (!cacheValuesMap.containsKey(str)) {
            cache(context, str, z);
        }
        return cacheValuesMap.get(str);
    }

    public static float getFloatValue(@NonNull Context context, @NonNull String str, float f) {
        return context.getSharedPreferences("SHARED_PREFS", 0).getFloat(str, f);
    }

    public static void persistCacheValues(@NonNull Context context, @NonNull String str, Object obj) {
        SharedPreferences.Editor edit = context.getSharedPreferences("SHARED_PREFS", 0).edit();
        if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof String) {
            edit.putString(str, (String) obj);
        }
        edit.apply();
        cacheValuesMap.put(str, obj);
    }
}
