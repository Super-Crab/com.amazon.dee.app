package com.amazon.device.crashmanager.ndk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
/* loaded from: classes12.dex */
public class NDKCrashDetector {
    private static final String REVISION_PLACEHOLDER = "X";
    private static final String TAG = "NDKCrashDetector";
    private boolean isEnabled = false;

    public NDKCrashDetector() {
        System.loadLibrary("crashmanager-ndk");
    }

    private SystemProps getSystemProperties(Context context) {
        PackageManager packageManager;
        SystemProps systemProps = new SystemProps();
        systemProps.setBuildFingerPrint(Build.FINGERPRINT);
        systemProps.setOsBuildNumber(Build.VERSION.INCREMENTAL);
        systemProps.setHardware(Build.BOARD);
        systemProps.setRevision(REVISION_PLACEHOLDER);
        systemProps.setBootLoader(Build.BOOTLOADER);
        try {
            packageManager = context.getPackageManager();
        } catch (Exception e) {
            Log.e(TAG, "Error while capturing package details", e);
        }
        if (packageManager == null) {
            Log.w(TAG, "Package manager was null.");
            return null;
        }
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        if (packageInfo == null) {
            String str = TAG;
            Log.w(str, "Package info was null for package: " + context.getPackageName());
            return null;
        }
        systemProps.setVersion(packageInfo.versionName);
        return systemProps;
    }

    public static native void setupNDKCrash(String str, SystemProps systemProps);

    public void enableNDKCrash(Context context) {
        if (!this.isEnabled) {
            SystemProps systemProperties = getSystemProperties(context);
            setupNDKCrash(context.getApplicationInfo().dataDir + "/files", systemProperties);
            this.isEnabled = true;
        }
    }
}
