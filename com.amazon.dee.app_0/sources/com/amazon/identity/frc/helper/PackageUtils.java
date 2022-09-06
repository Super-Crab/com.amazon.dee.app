package com.amazon.identity.frc.helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
/* loaded from: classes12.dex */
public final class PackageUtils {
    private static final String TAG = "com.amazon.identity.frc.helper.PackageUtils";

    private PackageUtils() {
    }

    public static Integer getPackageVersion(Context context, String str) {
        try {
            return Integer.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, String.format("Could not find package %s", str), e);
            return null;
        }
    }
}
