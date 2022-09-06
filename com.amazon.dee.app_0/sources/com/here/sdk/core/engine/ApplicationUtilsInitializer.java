package com.here.sdk.core.engine;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import com.here.sdk.core.engine.ApplicationUtils;
/* loaded from: classes3.dex */
public final class ApplicationUtilsInitializer {
    private static final String LOG_TAG = "ApplicationUtilsInitializer";

    private ApplicationUtilsInitializer() {
    }

    public static void initialize(@NonNull Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            str = null;
        }
        if (str == null) {
            str = "Unknown";
        }
        ApplicationUtils.setApplicationInformation(new ApplicationUtils.ApplicationInformation(str));
    }
}
