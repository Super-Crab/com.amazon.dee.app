package com.amazon.device.crashmanager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import java.util.Map;
/* loaded from: classes12.dex */
public class AppDetailsCollector {
    private static final String TAG = "AppDetailsCollector";

    private AppDetailsCollector() {
    }

    private static long calculateTotalMemory(Runtime runtime) {
        if (runtime.maxMemory() != Long.MAX_VALUE) {
            return runtime.maxMemory();
        }
        return runtime.totalMemory();
    }

    public static void collect(Context context, Map<String, String> map) {
        collectPackageDetails(context, map);
        collectMemoryDetails(map);
    }

    private static void collectMemoryDetails(Map<String, String> map) {
        map.put("nativeHeapSize", Long.toString(Debug.getNativeHeapSize()));
        map.put("nativeHeapFreeSize", Long.toString(Debug.getNativeHeapAllocatedSize()));
        map.put("threadAllocCount", Long.toString(Debug.getThreadAllocCount()));
        map.put("threadAllocSize", Long.toString(Debug.getThreadAllocSize()));
        Runtime runtime = Runtime.getRuntime();
        long calculateTotalMemory = calculateTotalMemory(runtime);
        long freeMemory = runtime.totalMemory() - runtime.freeMemory();
        map.put("jvmMaxMemory", String.valueOf(calculateTotalMemory));
        map.put("jvmMemoryUsage", String.valueOf(freeMemory));
        map.put("jvmFreeMemory", String.valueOf(runtime.maxMemory() - freeMemory));
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        map.put("memoryDalvikPrivateDirtyKB", Integer.toString(memoryInfo.dalvikPrivateDirty));
        map.put("memoryDalvikPssKB", Integer.toString(memoryInfo.dalvikPss));
        map.put("memoryDalvikSharedDirtyKB", Integer.toString(memoryInfo.dalvikSharedDirty));
        map.put("memoryNativePrivateDirtyKB", Integer.toString(memoryInfo.nativePrivateDirty));
        map.put("memoryNativePssKB", Integer.toString(memoryInfo.nativePss));
        map.put("memoryNativeSharedDirtyKB", Integer.toString(memoryInfo.nativeSharedDirty));
        map.put("memoryOtherPrivateDirtyKB", Integer.toString(memoryInfo.otherPrivateDirty));
        map.put("memoryOtherPssKB", Integer.toString(memoryInfo.otherPss));
        map.put("memoryOtherSharedDirtyKB", Integer.toString(memoryInfo.otherSharedDirty));
    }

    private static void collectPackageDetails(Context context, Map<String, String> map) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                Log.w(TAG, "Package manager was null.");
                return;
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo == null) {
                String str = TAG;
                Log.w(str, "Package info was null for package: " + context.getPackageName());
                return;
            }
            map.put("packageFilePath", context.getFilesDir().getAbsolutePath());
            map.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, packageInfo.packageName);
            map.put("packageVersionName", packageInfo.versionName);
            map.put("packageVersionCode", Integer.toString(packageInfo.versionCode));
        } catch (Exception e) {
            Log.e(TAG, "Error while capturing package details", e);
        }
    }
}
