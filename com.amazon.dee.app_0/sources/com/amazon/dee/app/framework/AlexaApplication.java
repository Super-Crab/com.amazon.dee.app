package com.amazon.dee.app.framework;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Process;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.multidex.MultiDexApplication;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.alexa.lifecycle.api.LifecycleManager;
import com.amazon.dee.app.dependencies.ApplicationComponent;
import com.amazon.dee.app.framework.DefaultApplicationImplementation;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class AlexaApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {
    private static final String ACCESSORIES_PROCESS_NAME = "com.amazon.dee.app:alexa_accessories";
    private static final String ALEXA_PROCESS_NAME = "com.amazon.dee.app:alexa";
    private static final String MAIN_PROCESS_NAME = "com.amazon.dee.app";
    private static final String TAG = Log.tag(AlexaApplication.class);
    private static final Map<Integer, String> memoryTrimToString;
    @VisibleForTesting
    ApplicationImplementation applicationImplementation;
    private final Random random = new SecureRandom();

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(5, "TRIM_MEMORY_RUNNING_MODERATE: no risk of getting killed, we're foregrounded.");
        hashMap.put(10, "TRIM_MEMORY_RUNNING_LOW: no risk of getting killed, we're foregrounded.");
        hashMap.put(15, "TRIM_MEMORY_RUNNING_CRITICAL: We might not get killed, but we're using a lot of memory.");
        hashMap.put(20, "TRIM_MEMORY_UI_HIDDEN: There is another foreground process, no risk of getting killed.");
        hashMap.put(40, "TRIM_MEMORY_BACKGROUND: We're in the background, low risk of getting killed.");
        hashMap.put(60, "TRIM_MEMORY_MODERATE: Medium risk of getting killed.");
        hashMap.put(80, "TRIM_MEMORY_COMPLETE: High risk of getting killed, system is almost out of memory.");
        memoryTrimToString = Collections.unmodifiableMap(hashMap);
    }

    private void createMemoryMonitor() {
        int i = Build.VERSION.SDK_INT;
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$AlexaApplication$tJUpXLZql876sWxTkQprw4wI9kU
            @Override // java.lang.Runnable
            public final void run() {
                AlexaApplication.this.lambda$createMemoryMonitor$0$AlexaApplication();
            }
        }, 30L, 300L, TimeUnit.SECONDS);
    }

    private String getCurrentProcess() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    @Nullable
    private ActivityManager.MemoryInfo getMemoryInfo() {
        ActivityManager activityManager = (ActivityManager) getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        if (activityManager != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo;
        }
        return null;
    }

    private String getMemoryWarningString(int i) {
        String str = memoryTrimToString.get(Integer.valueOf(i));
        return str != null ? str : GeneratedOutlineSupport1.outline49("Unknown trim level: ", i);
    }

    public static boolean isAppLaunchingFromBackground() {
        LifecycleManager lifecycleManager = (LifecycleManager) GeneratedOutlineSupport1.outline20(LifecycleManager.class);
        if (lifecycleManager == null || lifecycleManager.getCurrentState() == null) {
            return true;
        }
        return !lifecycleManager.getCurrentState().get(LifecycleEvent.IS_FOREGROUND).booleanValue();
    }

    public static boolean isAppOnForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    private void logDebugMemoryInfo() {
        int i = Build.VERSION.SDK_INT;
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        Map<String, String> memoryStats = memoryInfo.getMemoryStats();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MemoryStats nativeHeap=");
        outline107.append(memoryStats.get("summary.native-heap"));
        outline107.append("kB javaHeap=");
        outline107.append(memoryStats.get("summary.java-heap"));
        outline107.append("kB code=");
        outline107.append(memoryStats.get("summary.code"));
        outline107.append("kB stack=");
        outline107.append(memoryStats.get("summary.stack"));
        outline107.append("kB graphics=");
        outline107.append(memoryStats.get("summary.graphics"));
        outline107.append("kB privateOther=");
        outline107.append(memoryStats.get("summary.private-other"));
        outline107.append("kB totalPss=");
        outline107.append(memoryStats.get("summary.total-pss"));
        outline107.append("kB");
        Log.i(str, outline107.toString());
    }

    protected ApplicationImplementation getApplicationImplementation(String str) {
        if (str != null && !str.isEmpty() && !str.equals("com.amazon.dee.app")) {
            if (ACCESSORIES_PROCESS_NAME.equals(str)) {
                return new DefaultApplicationImplementation.AccessoriesApplicationImplementation(str);
            }
            if (ALEXA_PROCESS_NAME.equals(str)) {
                return new DefaultApplicationImplementation.VoiceApplicationImplementation(str);
            }
            return new DefaultApplicationImplementation(str);
        }
        return new MainApplicationImplementation();
    }

    public ApplicationComponent getComponent() {
        return this.applicationImplementation.getComponent();
    }

    public boolean isColdStart() {
        return this.applicationImplementation.isColdStart();
    }

    public boolean isNoActivityVisible() {
        return this.applicationImplementation.isNoActivityVisible();
    }

    public boolean isSingleSignOnBuild() {
        return this.applicationImplementation.isSingleSignOnBuild();
    }

    public /* synthetic */ void lambda$createMemoryMonitor$0$AlexaApplication() {
        logDebugMemoryInfo();
        if (this.random.nextInt(144) == 0) {
            this.applicationImplementation.reportMemoryStats();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.applicationImplementation.onActivityCreated(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.applicationImplementation.onActivityDestroyed(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.applicationImplementation.onActivityPaused(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.applicationImplementation.onActivityResumed(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.applicationImplementation.onActivitySaveInstanceState(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.applicationImplementation.onActivityStarted(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        this.applicationImplementation.onActivityStopped(activity);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.applicationImplementation.onConfigurationChanged(configuration);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.applicationImplementation = getApplicationImplementation(getCurrentProcess());
        this.applicationImplementation.onCreate(this);
        registerActivityLifecycleCallbacks(this.applicationImplementation);
        createMemoryMonitor();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        this.applicationImplementation.onTrimMemory(i);
    }
}
