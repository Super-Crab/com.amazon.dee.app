package com.amazon.alexa.accessorykit.interprocess.utils;

import android.app.ActivityManager;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import java.util.List;
/* loaded from: classes6.dex */
public final class IPCUtils {
    private static final String TAG = "IPCUtils:";

    private IPCUtils() {
    }

    public static boolean isMainProcessAlive(ActivityManager activityManager) {
        long currentTimeMillis = System.currentTimeMillis();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(AccessoriesFactory.getAppName())) {
                    Logger.d("%s Main process is alive. Took %d ms time to determine.", TAG, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    return true;
                }
            }
        }
        Logger.d("%s Main process is not alive. Took %d ms time to determine.", TAG, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return false;
    }

    public static boolean isMainProcessAliveAndForeground(ActivityManager activityManager) {
        long currentTimeMillis = System.currentTimeMillis();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(AccessoriesFactory.getAppName())) {
                    int i = runningAppProcessInfo.importance;
                    boolean z = i == 100 || i == 200;
                    if (z) {
                        Logger.d("%s Main process is alive and in foreground. Took %d ms time to determine.", TAG, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    } else {
                        Logger.d("%s Main process is alive but not in foreground. Took %d ms time to determine.", TAG, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    }
                    return z;
                }
            }
        }
        Logger.d("%s Main process is neither alive not in foreground. Took %d ms time to determine.", TAG, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return false;
    }
}
