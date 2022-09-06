package com.amazon.alexa.location.networking.utils;

import android.app.ActivityManager;
import android.content.Context;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes9.dex */
public final class AppLifecycleUtil {
    private AppLifecycleUtil() {
    }

    public static String getRunningStateMetricName(String str, boolean z) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(z ? "_foreground" : "_background");
        return outline107.toString();
    }

    public static boolean isAppRunningForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningAppProcesses();
        String packageName = context.getPackageName();
        if (runningAppProcesses != null && runningAppProcesses.size() != 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (100 == runningAppProcessInfo.importance && runningAppProcessInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
