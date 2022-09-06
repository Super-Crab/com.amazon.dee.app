package com.amazon.alexa.location.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
/* loaded from: classes9.dex */
public final class PendingIntentCompat {
    private PendingIntentCompat() {
    }

    public static PendingIntent getForegroundService(Context context, int i, Intent intent, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        return PendingIntent.getForegroundService(context, i, intent, i2);
    }
}
