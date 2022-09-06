package com.bugsnag.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessorykit.ModelTransformer;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes.dex */
class DeviceData {
    private static final String INSTALL_ID_KEY = "install.iud";
    private static final String[] ROOT_INDICATORS = {"/system/xbin/su", "/system/bin/su", "/system/app/Superuser.apk", "/system/app/SuperSU.apk", "/system/app/Superuser", "/system/app/SuperSU", "/system/xbin/daemonsu", "/su/bin"};
    private final Context appContext;
    private final Connectivity connectivity;
    @NonNull
    final String[] cpuAbi;
    private final DisplayMetrics displayMetrics;
    @Nullable
    final Integer dpi;
    private final boolean emulator;
    private final String id;
    @NonNull
    final String locale;
    private final Resources resources;
    private final boolean rooted;
    @Nullable
    final Float screenDensity;
    @Nullable
    final String screenResolution;
    private final SharedPreferences sharedPrefs;

    /* loaded from: classes.dex */
    static class Abi2Wrapper {
        Abi2Wrapper() {
        }

        @NonNull
        static String[] getAbi1andAbi2() {
            return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SupportedAbiWrapper {
        SupportedAbiWrapper() {
        }

        @RequiresApi(21)
        static String[] getSupportedAbis() {
            return Build.SUPPORTED_ABIS;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceData(Connectivity connectivity, Context context, Resources resources, SharedPreferences sharedPreferences) {
        this.connectivity = connectivity;
        this.appContext = context;
        this.resources = resources;
        this.sharedPrefs = sharedPreferences;
        if (resources != null) {
            this.displayMetrics = resources.getDisplayMetrics();
        } else {
            this.displayMetrics = null;
        }
        this.screenDensity = getScreenDensity();
        this.dpi = getScreenDensityDpi();
        this.screenResolution = getScreenResolution();
        this.locale = getLocale();
        this.cpuAbi = getCpuAbi();
        this.emulator = isEmulator();
        this.id = retrieveUniqueInstallId();
        this.rooted = isRooted();
    }

    private long calculateFreeMemory() {
        Runtime runtime = Runtime.getRuntime();
        if (runtime.maxMemory() != Long.MAX_VALUE) {
            return runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory());
        }
        return runtime.freeMemory();
    }

    @Nullable
    private String calculateOrientation() {
        Resources resources = this.resources;
        if (resources != null) {
            int i = resources.getConfiguration().orientation;
            if (i == 1) {
                return "portrait";
            }
            if (i == 2) {
                return "landscape";
            }
        }
        return null;
    }

    static long calculateTotalMemory() {
        Runtime runtime = Runtime.getRuntime();
        if (runtime.maxMemory() != Long.MAX_VALUE) {
            return runtime.maxMemory();
        }
        return runtime.totalMemory();
    }

    @Nullable
    private Float getBatteryLevel() {
        try {
            Intent registerReceiver = this.appContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            return Float.valueOf(registerReceiver.getIntExtra(ModelTransformer.KEY_BATTERY_LEVEL, -1) / registerReceiver.getIntExtra(ModelTransformer.KEY_BATTERY_SCALE, -1));
        } catch (Exception unused) {
            Logger.warn("Could not get batteryLevel");
            return null;
        }
    }

    @NonNull
    private String[] getCpuAbi() {
        int i = Build.VERSION.SDK_INT;
        return SupportedAbiWrapper.getSupportedAbis();
    }

    @NonNull
    private String getLocale() {
        return Locale.getDefault().toString();
    }

    @Nullable
    private String getLocationStatus() {
        try {
            String string = Settings.Secure.getString(this.appContext.getContentResolver(), "location_providers_allowed");
            return string != null ? string.length() > 0 ? "allowed" : "disallowed" : "disallowed";
        } catch (Exception unused) {
            Logger.warn("Could not get locationStatus");
            return null;
        }
    }

    @Nullable
    private String getNetworkAccess() {
        return this.connectivity.retrieveNetworkAccessState();
    }

    @Nullable
    private Float getScreenDensity() {
        DisplayMetrics displayMetrics = this.displayMetrics;
        if (displayMetrics != null) {
            return Float.valueOf(displayMetrics.density);
        }
        return null;
    }

    @Nullable
    private Integer getScreenDensityDpi() {
        DisplayMetrics displayMetrics = this.displayMetrics;
        if (displayMetrics != null) {
            return Integer.valueOf(displayMetrics.densityDpi);
        }
        return null;
    }

    @Nullable
    private String getScreenResolution() {
        DisplayMetrics displayMetrics = this.displayMetrics;
        if (displayMetrics != null) {
            int max = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
            DisplayMetrics displayMetrics2 = this.displayMetrics;
            return String.format(Locale.US, "%dx%d", Integer.valueOf(max), Integer.valueOf(Math.min(displayMetrics2.widthPixels, displayMetrics2.heightPixels)));
        }
        return null;
    }

    @NonNull
    private String getTime() {
        return DateUtils.toIso8601(new Date());
    }

    @Nullable
    private Boolean isCharging() {
        boolean z;
        try {
            int intExtra = this.appContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
            if (intExtra != 2 && intExtra != 5) {
                z = false;
                return Boolean.valueOf(z);
            }
            z = true;
            return Boolean.valueOf(z);
        } catch (Exception unused) {
            Logger.warn("Could not get charging status");
            return null;
        }
    }

    private boolean isEmulator() {
        String str = Build.FINGERPRINT;
        return str.startsWith("unknown") || str.contains("generic") || str.contains("vbox");
    }

    private boolean isRooted() {
        String str = Build.TAGS;
        if (str == null || !str.contains("test-keys")) {
            try {
                for (String str2 : ROOT_INDICATORS) {
                    if (new File(str2).exists()) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
            return false;
        }
        return true;
    }

    @Nullable
    private String retrieveUniqueInstallId() {
        String string = this.sharedPrefs.getString(INSTALL_ID_KEY, null);
        if (string == null) {
            String uuid = UUID.randomUUID().toString();
            this.sharedPrefs.edit().putString(INSTALL_ID_KEY, uuid).apply();
            return uuid;
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"UsableSpace"})
    public long calculateFreeDisk() {
        return Environment.getDataDirectory().getUsableSpace();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> getDeviceData() {
        Map<String, Object> deviceDataSummary = getDeviceDataSummary();
        deviceDataSummary.put("id", this.id);
        deviceDataSummary.put("freeMemory", Long.valueOf(calculateFreeMemory()));
        deviceDataSummary.put("totalMemory", Long.valueOf(calculateTotalMemory()));
        deviceDataSummary.put("freeDisk", Long.valueOf(calculateFreeDisk()));
        deviceDataSummary.put("orientation", calculateOrientation());
        return deviceDataSummary;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> getDeviceDataSummary() {
        HashMap hashMap = new HashMap();
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put("model", Build.MODEL);
        hashMap.put("jailbroken", Boolean.valueOf(this.rooted));
        hashMap.put("osName", "android");
        hashMap.put("osVersion", Build.VERSION.RELEASE);
        hashMap.put("cpuAbi", this.cpuAbi);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("androidApiLevel", Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap2.put("osBuild", Build.DISPLAY);
        hashMap.put("runtimeVersions", hashMap2);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> getDeviceMetaData() {
        HashMap hashMap = new HashMap();
        hashMap.put("batteryLevel", getBatteryLevel());
        hashMap.put("charging", isCharging());
        hashMap.put("locationStatus", getLocationStatus());
        hashMap.put("networkAccess", getNetworkAccess());
        hashMap.put("time", getTime());
        hashMap.put("brand", Build.BRAND);
        hashMap.put("locale", this.locale);
        hashMap.put("screenDensity", this.screenDensity);
        hashMap.put("dpi", this.dpi);
        hashMap.put("emulator", Boolean.valueOf(this.emulator));
        hashMap.put("screenResolution", this.screenResolution);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getId() {
        return this.id;
    }
}
