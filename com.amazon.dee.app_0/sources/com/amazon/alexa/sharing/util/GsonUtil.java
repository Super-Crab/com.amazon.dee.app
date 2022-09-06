package com.amazon.alexa.sharing.util;

import androidx.annotation.NonNull;
import com.google.gson.JsonObject;
/* loaded from: classes10.dex */
public final class GsonUtil {
    private GsonUtil() {
    }

    public static boolean getBooleanFromJson(@NonNull JsonObject jsonObject, @NonNull String str, boolean z) {
        return jsonObject.has(str) ? jsonObject.get(str).getAsBoolean() : z;
    }

    public static double getDoubleFromJson(@NonNull JsonObject jsonObject, @NonNull String str, double d) {
        return !jsonObject.has(str) ? d : jsonObject.get(str).getAsDouble();
    }

    public static int getIntFromJson(@NonNull JsonObject jsonObject, @NonNull String str, int i) {
        return !jsonObject.has(str) ? i : jsonObject.get(str).getAsInt();
    }

    public static String getStringFromJson(@NonNull JsonObject jsonObject, @NonNull String str, String str2) {
        return !jsonObject.has(str) ? str2 : jsonObject.get(str).getAsString();
    }
}
