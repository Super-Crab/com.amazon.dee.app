package com.amazon.alexa.voice.permissions;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
/* loaded from: classes11.dex */
public final class PermissionsUtils {
    private PermissionsUtils() {
    }

    public static boolean hasPermissions(@NonNull Context context, @NonNull String[] strArr) {
        int i = Build.VERSION.SDK_INT;
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }
}
