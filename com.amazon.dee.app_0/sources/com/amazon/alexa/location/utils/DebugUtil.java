package com.amazon.alexa.location.utils;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public final class DebugUtil {
    private static final String FILENAME_DISABLE_OS_TRIGGER = "alexa_disable_os_geofence";

    private DebugUtil() {
    }

    public static boolean isOsTriggerDisabled(@NonNull Context context) {
        return false;
    }
}
