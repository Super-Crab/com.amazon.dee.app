package com.amazon.alexa.sharing.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public final class Utils {
    private Utils() {
    }

    public static boolean getBooleanPreferenceFromSharedPrefs(Context context, @NonNull String str, boolean z) {
        if (context == null) {
            Log.e(Constants.LOG_TAG, "Context is null. Cannot fetch Boolean preferences");
            return false;
        }
        return context.getSharedPreferences("SHARED_PREFS", 0).getBoolean(str, z);
    }

    @SuppressLint({"DefaultLocale"})
    public static String getMediaTypeFromMimeType(@Nullable String str) {
        if (str != null) {
            return str.split("/")[0].toLowerCase();
        }
        throw new IllegalArgumentException("mimeType is null");
    }

    public static void writeBooleanPreferenceToSharedPrefs(Context context, @NonNull String str, boolean z) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("SHARED_PREFS", 0).edit().putBoolean(str, z).apply();
    }
}
