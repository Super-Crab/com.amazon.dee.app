package com.amazon.alexa.featureservice.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
/* loaded from: classes7.dex */
public final class JsonUtils {
    private static final String TAG = "JsonUtils";

    private JsonUtils() {
    }

    public static boolean getBoolean(@NonNull JsonObject jsonObject, @NonNull String str) {
        return getBoolean(jsonObject, str, false);
    }

    public static Long getLong(@NonNull JsonObject jsonObject, @NonNull String str) {
        Preconditions.checkNotNull(jsonObject);
        Preconditions.checkNotNull(str);
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        try {
            return Long.valueOf(jsonElement.getAsLong());
        } catch (Exception e) {
            Log.e(TAG, "Trying to get long but that's not the type", e);
            return null;
        }
    }

    public static String getString(@NonNull JsonObject jsonObject, @NonNull String str) {
        Preconditions.checkNotNull(jsonObject);
        Preconditions.checkNotNull(str);
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        try {
            return jsonElement.getAsString();
        } catch (Exception e) {
            Log.e(TAG, "Trying to get string but that's not the type", e);
            return null;
        }
    }

    public static JsonObject toJsonObject(@NonNull String str) throws JsonSyntaxException, IllegalStateException {
        return new JsonParser().parse(str).getAsJsonObject();
    }

    public static boolean getBoolean(@NonNull JsonObject jsonObject, @NonNull String str, boolean z) {
        JsonElement jsonElement;
        Preconditions.checkNotNull(jsonObject);
        Preconditions.checkNotNull(str);
        if (!jsonObject.isJsonNull() && (jsonElement = jsonObject.get(str)) != null && !jsonElement.isJsonNull()) {
            try {
                return jsonElement.getAsBoolean();
            } catch (Exception e) {
                Log.e(TAG, "Trying to get boolean but that's not the type", e);
            }
        }
        return z;
    }
}
