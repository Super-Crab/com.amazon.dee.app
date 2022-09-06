package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
@KeepForSdk
/* loaded from: classes2.dex */
public class InstantApps {
    private static Context zzhv;
    private static Boolean zzhw;

    @KeepForSdk
    public static synchronized boolean isInstantApp(Context context) {
        synchronized (InstantApps.class) {
            Context applicationContext = context.getApplicationContext();
            if (zzhv != null && zzhw != null && zzhv == applicationContext) {
                return zzhw.booleanValue();
            }
            zzhw = null;
            if (PlatformVersion.isAtLeastO()) {
                zzhw = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
            } else {
                try {
                    context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                    zzhw = true;
                } catch (ClassNotFoundException unused) {
                    zzhw = false;
                }
            }
            zzhv = applicationContext;
            return zzhw.booleanValue();
        }
    }
}
