package com.amazon.device.crashmanager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes12.dex */
public final class DeviceDetailsCollector {
    private static final String[] ROOT_INDICATORS = {"/system/xbin/su", "/system/bin/su", "/system/app/Superuser.apk", "/system/app/SuperSU.apk", "/system/app/Superuser", "/system/app/SuperSU", "/system/xbin/daemonsu", "/su/bin"};
    private static final String TAG = "DeviceDetailsCollector";

    private DeviceDetailsCollector() {
    }

    public static void collect(Context context, Map<String, String> map) {
        map.put("deviceManufacturer", Build.MANUFACTURER);
        map.put("hardwareModel", Build.MODEL);
        map.put("deviceBoard", Build.BOARD);
        map.put("deviceBrand", Build.BRAND);
        map.put("buildDisplayId", Build.DISPLAY);
        map.put("buildId", Build.ID);
        map.put("productName", Build.PRODUCT);
        map.put("buildUser", Build.USER);
        map.put("osVersion", Build.VERSION.RELEASE);
        map.put("androidBuildVersion", Build.VERSION.INCREMENTAL);
        map.put("androidApiLevel", String.valueOf(Build.VERSION.SDK_INT));
        map.put("freeDiskSpaceBytes", getFreeDiskSpace());
        map.put("totalDiskSpaceBytes", getTotalDiskSpace());
        map.put("connectionType", getNetworkAccess(context));
        map.put("carrierName", getCarrierName(context));
        map.put("deviceLocale", getLocale());
        map.put(MetricsConfiguration.DEVICE_LANGUAGE, getDeviceLanguage());
        map.put("isRooted", isRooted());
        map.put("isEmulator", String.valueOf(isEmulator()));
        collectBatteryDetails(context, map);
        collectScreenDetails(context, map);
        collectMemoryDetails(context, map);
    }

    private static void collectBatteryDetails(Context context, Map<String, String> map) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver == null) {
                return;
            }
            map.put("batteryLevel", getBatteryLevel(registerReceiver));
            map.put("isCharging", isCharging(registerReceiver));
        } catch (Exception unused) {
            Log.e(TAG, "Unable to collect Battery details");
        }
    }

    private static void collectMemoryDetails(Context context, Map<String, String> map) {
        if (context != null) {
            try {
                ActivityManager activityManager = (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
                if (activityManager == null) {
                    return;
                }
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                activityManager.getMemoryInfo(memoryInfo);
                map.put("freeMemoryBytes", Long.toString(memoryInfo.availMem));
                map.put("totalMemoryBytes", Long.toString(memoryInfo.totalMem));
                map.put("lowMemory", Boolean.toString(memoryInfo.lowMemory));
                map.put("memLowThreshold", Long.toString(memoryInfo.threshold));
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("collectMemoryDetails: "), TAG);
            }
        }
    }

    private static void collectScreenDetails(Context context, Map<String, String> map) {
        DisplayMetrics displayMetrics;
        Resources resources = context.getResources();
        if (resources != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            map.put("screenDensity", String.valueOf(displayMetrics.density));
            map.put("screenDensityDpi", String.valueOf(displayMetrics.densityDpi));
            map.put("screenResolution", getScreenResolution(displayMetrics));
        }
        map.put("orientation", getDeviceOrientation(context));
    }

    private static String getBatteryLevel(Intent intent) {
        return String.valueOf((int) ((intent.getIntExtra(ModelTransformer.KEY_BATTERY_LEVEL, -1) * 100) / intent.getIntExtra(ModelTransformer.KEY_BATTERY_SCALE, -1)));
    }

    private static String getCarrierName(Context context) {
        String str;
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            if (str == null) {
                return str;
            }
            try {
                return str.trim().length() > 0 ? str : "Unknown";
            } catch (Exception unused) {
                Log.w(TAG, "Unable to get carrier name");
                return str;
            }
        } catch (Exception unused2) {
            str = "Unknown";
        }
    }

    private static String getDeviceLanguage() {
        return Locale.getDefault().getLanguage();
    }

    private static String getDeviceOrientation(Context context) {
        Resources resources = context.getResources();
        return (resources == null || resources.getConfiguration() == null) ? "Unknown" : 2 == resources.getConfiguration().orientation ? "landscape" : "portrait";
    }

    private static String getFreeDiskSpace() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Exception e) {
            Log.e(TAG, "Unable to get available disk space", e);
            j = -1;
        }
        return Long.toString(j);
    }

    private static String getLocale() {
        return Locale.getDefault().toString();
    }

    private static String getNetworkAccess(Context context) {
        NetworkInfo activeNetworkInfo;
        String str;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return "Unknown";
            }
            if (activeNetworkInfo.getType() == 1) {
                str = "wifi";
            } else {
                str = activeNetworkInfo.getType() == 9 ? "ethernet" : "cellular";
            }
            return str;
        } catch (Exception unused) {
            Log.w(TAG, "Unable to get network access information check for 'android.permission.ACCESS_NETWORK_STATE' permission");
            return "Unknown";
        }
    }

    private static String getScreenResolution(DisplayMetrics displayMetrics) {
        if (displayMetrics != null) {
            return String.format(Locale.US, "%dx%d", Integer.valueOf(Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels)), Integer.valueOf(Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)));
        }
        return "Unknown";
    }

    private static String getTotalDiskSpace() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Exception e) {
            Log.e(TAG, "Unable to get total disk space", e);
            j = -1;
        }
        return Long.toString(j);
    }

    private static String isCharging(Intent intent) {
        int intExtra = intent.getIntExtra("status", -1);
        return String.valueOf(intExtra == 2 || intExtra == 5);
    }

    private static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }

    private static String isRooted() {
        String str = Build.TAGS;
        boolean z = false;
        boolean z2 = str != null && str.contains("test-keys");
        try {
            boolean z3 = z2;
            for (String str2 : ROOT_INDICATORS) {
                if (new File(str2).exists()) {
                    z3 = true;
                }
            }
            z = z3;
        } catch (Exception unused) {
        }
        return String.valueOf(z);
    }
}
