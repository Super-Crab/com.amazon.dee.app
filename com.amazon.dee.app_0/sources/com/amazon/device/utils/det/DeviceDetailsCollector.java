package com.amazon.device.utils.det;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import java.io.File;
import java.util.Map;
/* loaded from: classes12.dex */
public class DeviceDetailsCollector {
    private DeviceDetailsCollector() {
    }

    public static void collect(Context context, Map<String, String> map) {
        ActivityManager activityManager;
        map.put("androidVersion", Build.VERSION.RELEASE);
        map.put("androidBuildVersion", Build.VERSION.INCREMENTAL);
        map.put("deviceBoard", Build.BOARD);
        map.put("deviceBrand", Build.BRAND);
        map.put("deviceDisplay", Build.DISPLAY);
        map.put("deviceFingerPrint", Build.FINGERPRINT);
        map.put("deviceId", Build.ID);
        map.put("deviceManufacturer", Build.MANUFACTURER);
        map.put(ArcusConstants.InputAttribute.DEVICE_MODEL, Build.MODEL);
        map.put("deviceProduct", Build.PRODUCT);
        map.put("deviceTags", Build.TAGS);
        map.put("deviceTime", Long.toString(Build.TIME));
        map.put("deviceType", Build.TYPE);
        map.put("deviceUser", Build.USER);
        map.put("totalInternalMemorySize", Long.toString(getTotalInternalMemorySizeInBytes()));
        map.put("availableInternalMemorySize", Long.toString(getAvailableInternalMemorySizeInBytes()));
        if (context != null && (activityManager = (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)) != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            map.put("memAvail", Long.toString(memoryInfo.availMem));
            map.put("memLowFlag", Boolean.toString(memoryInfo.lowMemory));
            map.put("memLowThreshold", Long.toString(memoryInfo.threshold));
        }
        map.put("nativeHeapSize", Long.toString(Debug.getNativeHeapSize()));
        map.put("nativeHeapFreeSize", Long.toString(Debug.getNativeHeapAllocatedSize()));
        map.put("threadAllocCount", Long.toString(Debug.getThreadAllocCount()));
        map.put("threadAllocSize", Long.toString(Debug.getThreadAllocSize()));
        Debug.MemoryInfo memoryInfo2 = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo2);
        map.put("memoryDalvikPrivateDirty", Integer.toString(memoryInfo2.dalvikPrivateDirty));
        map.put("memoryDalvikPss", Integer.toString(memoryInfo2.dalvikPss));
        map.put("memoryDalvikSharedDirty", Integer.toString(memoryInfo2.dalvikSharedDirty));
        map.put("memoryNativePrivateDirty", Integer.toString(memoryInfo2.nativePrivateDirty));
        map.put("memoryNativePss", Integer.toString(memoryInfo2.nativePss));
        map.put("memoryNativeSharedDirty", Integer.toString(memoryInfo2.nativeSharedDirty));
        map.put("memoryOtherPrivateDirty", Integer.toString(memoryInfo2.otherPrivateDirty));
        map.put("memoryOtherPss", Integer.toString(memoryInfo2.otherPss));
        map.put("memoryOtherSharedDirty", Integer.toString(memoryInfo2.otherSharedDirty));
    }

    public static long getAvailableCacheMemorySizeInBytes() {
        return getAvailableMemorySizeInBytes(Environment.getDownloadCacheDirectory());
    }

    public static long getAvailableInternalMemorySizeInBytes() {
        return getAvailableMemorySizeInBytes(Environment.getDataDirectory());
    }

    private static long getAvailableMemorySizeInBytes(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public static long getTotalCacheMemorySizeInBytes() {
        return getTotalMemorySizeInBytes(Environment.getDownloadCacheDirectory());
    }

    public static long getTotalInternalMemorySizeInBytes() {
        return getTotalMemorySizeInBytes(Environment.getDataDirectory());
    }

    private static long getTotalMemorySizeInBytes(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getBlockCount() * statFs.getBlockSize();
    }
}
