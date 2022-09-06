package com.bugsnag.android;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.List;
/* loaded from: classes.dex */
class ForegroundDetector {
    private final ActivityManager activityManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ForegroundDetector(Context context) {
        this.activityManager = (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
    }

    private ActivityManager.RunningAppProcessInfo getProcessInfo() {
        int i = Build.VERSION.SDK_INT;
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        return runningAppProcessInfo;
    }

    @Nullable
    private ActivityManager.RunningAppProcessInfo getProcessInfoPreApi16() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = this.activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (myPid == runningAppProcessInfo.pid) {
                    return runningAppProcessInfo;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Boolean isInForeground() {
        try {
            ActivityManager.RunningAppProcessInfo processInfo = getProcessInfo();
            if (processInfo == null) {
                return null;
            }
            return Boolean.valueOf(processInfo.importance <= 100);
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
