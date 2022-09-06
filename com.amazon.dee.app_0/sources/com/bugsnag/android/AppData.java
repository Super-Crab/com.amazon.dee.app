package com.bugsnag.android;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
class AppData {
    static final String RELEASE_STAGE_DEVELOPMENT = "development";
    static final String RELEASE_STAGE_PRODUCTION = "production";
    private static final long startTimeMs = SystemClock.elapsedRealtime();
    private final Context appContext;
    @Nullable
    final String appName;
    @Nullable
    private ApplicationInfo applicationInfo;
    private String binaryArch = null;
    private final Configuration config;
    @Nullable
    private PackageInfo packageInfo;
    private PackageManager packageManager;
    private final String packageName;
    private final SessionTracker sessionTracker;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppData(Context context, PackageManager packageManager, Configuration configuration, SessionTracker sessionTracker) {
        this.appContext = context;
        this.packageManager = packageManager;
        this.config = configuration;
        this.sessionTracker = sessionTracker;
        this.packageName = context.getPackageName();
        try {
            this.packageManager = packageManager;
            this.packageInfo = this.packageManager.getPackageInfo(this.packageName, 0);
            this.applicationInfo = this.packageManager.getApplicationInfo(this.packageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not retrieve package/application information for ");
            outline107.append(this.packageName);
            Logger.warn(outline107.toString());
        }
        this.appName = getAppName();
    }

    @NonNull
    private String calculateNotifierType() {
        String notifierType = this.config.getNotifierType();
        return notifierType != null ? notifierType : "android";
    }

    @Nullable
    private Integer calculateVersionCode() {
        Integer versionCode = this.config.getVersionCode();
        if (versionCode != null) {
            return versionCode;
        }
        PackageInfo packageInfo = this.packageInfo;
        if (packageInfo == null) {
            return null;
        }
        return Integer.valueOf(packageInfo.versionCode);
    }

    @Nullable
    private String calculateVersionName() {
        String appVersion = this.config.getAppVersion();
        if (appVersion != null) {
            return appVersion;
        }
        PackageInfo packageInfo = this.packageInfo;
        if (packageInfo == null) {
            return null;
        }
        return packageInfo.versionName;
    }

    @Nullable
    private String getAppName() {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = this.packageManager;
        if (packageManager == null || (applicationInfo = this.applicationInfo) == null) {
            return null;
        }
        return packageManager.getApplicationLabel(applicationInfo).toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getDurationMs() {
        return SystemClock.elapsedRealtime() - startTimeMs;
    }

    private long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    @Nullable
    private Boolean isLowMemory() {
        try {
            ActivityManager activityManager = (ActivityManager) this.appContext.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
            if (activityManager == null) {
                return null;
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return Boolean.valueOf(memoryInfo.lowMemory);
        } catch (Exception unused) {
            Logger.warn("Could not check lowMemory status");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Long calculateDurationInForeground() {
        return this.sessionTracker.getDurationInForegroundMs(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getActiveScreenClass() {
        return this.sessionTracker.getContextActivity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> getAppData() {
        Map<String, Object> appDataSummary = getAppDataSummary();
        appDataSummary.put("id", this.packageName);
        appDataSummary.put("buildUUID", this.config.getBuildUUID());
        appDataSummary.put("duration", Long.valueOf(getDurationMs()));
        appDataSummary.put("durationInForeground", calculateDurationInForeground());
        appDataSummary.put("inForeground", this.sessionTracker.isInForeground());
        appDataSummary.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, this.packageName);
        appDataSummary.put("binaryArch", this.binaryArch);
        return appDataSummary;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> getAppDataMetaData() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.appName);
        hashMap.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, this.packageName);
        hashMap.put(ModelTransformer.KEY_FIRMWARE_VERSION_NAME, calculateVersionName());
        hashMap.put("activeScreen", getActiveScreenClass());
        hashMap.put("memoryUsage", Long.valueOf(getMemoryUsage()));
        hashMap.put("lowMemory", isLowMemory());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> getAppDataSummary() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", calculateNotifierType());
        hashMap.put("releaseStage", guessReleaseStage());
        hashMap.put("version", calculateVersionName());
        hashMap.put("versionCode", calculateVersionCode());
        hashMap.put("codeBundleId", this.config.getCodeBundleId());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String guessReleaseStage() {
        String releaseStage = this.config.getReleaseStage();
        if (releaseStage != null) {
            return releaseStage;
        }
        ApplicationInfo applicationInfo = this.applicationInfo;
        return (applicationInfo == null || (applicationInfo.flags & 2) == 0) ? RELEASE_STAGE_PRODUCTION : RELEASE_STAGE_DEVELOPMENT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBinaryArch(String str) {
        this.binaryArch = str;
    }
}
