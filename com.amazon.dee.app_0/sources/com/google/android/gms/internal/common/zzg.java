package com.google.android.gms.internal.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
/* loaded from: classes2.dex */
public final class zzg {
    private static volatile boolean zziy = !zzam();

    @RequiresApi(24)
    @TargetApi(24)
    public static Context getDeviceProtectedStorageContext(Context context) {
        return context.isDeviceProtectedStorage() ? context : context.createDeviceProtectedStorageContext();
    }

    public static boolean zzam() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }
}
