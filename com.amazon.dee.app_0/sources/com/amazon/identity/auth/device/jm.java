package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.PackageManager;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jm {
    private static final String TAG = "com.amazon.identity.auth.device.jm";

    private jm() {
    }

    public static Integer D(Context context, String str) {
        try {
            return Integer.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            String str2 = TAG;
            String.format("%s cannot be found because it is not installed", str);
            io.dm(str2);
            return null;
        }
    }
}
