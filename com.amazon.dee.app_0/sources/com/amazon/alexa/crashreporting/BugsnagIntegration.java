package com.amazon.alexa.crashreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.CrashReportingMetrics;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.comms.device.KnightDeviceFacade;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bugsnag.android.BeforeSend;
import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.Configuration;
import com.bugsnag.android.Report;
import com.bugsnag.android.Severity;
import com.dee.app.metrics.MetricsService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import org.apache.logging.log4j.util.Chars;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class BugsnagIntegration extends CrashReportingComponent {
    private static final String TAG = "BugsnagIntegration";
    @SuppressLint({"StaticFieldLeak"})
    private static volatile BugsnagIntegration singleton;
    private final BugsnagLibraryApi bugsnagLibraryApi;
    private final Context context;
    private final Provider<DeviceInformation> deviceInformation;
    private final Provider<MetricsService> metricsService;
    @VisibleForTesting
    final Map<Tab, Map<String, String>> pendingMetadata = new HashMap();
    @VisibleForTesting
    final Set<Tab> pendingTabsToBeCleared;
    private final String serviceOrProcessName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public interface BugsnagLibraryApi {
        void clearTab(Tab tab);

        void init(Context context, Configuration configuration);

        void notify(Throwable th);

        void putMetadata(Tab tab, String str, Object obj);

        void setUser(String str);
    }

    /* loaded from: classes6.dex */
    static final class BugsnagNativeLibraryApi implements BugsnagLibraryApi {
        BugsnagNativeLibraryApi() {
        }

        @Override // com.amazon.alexa.crashreporting.BugsnagIntegration.BugsnagLibraryApi
        public void clearTab(Tab tab) {
            Bugsnag.clearTab(tab.tabName());
        }

        @Override // com.amazon.alexa.crashreporting.BugsnagIntegration.BugsnagLibraryApi
        public void init(Context context, Configuration configuration) {
            Bugsnag.init(context, configuration);
        }

        @Override // com.amazon.alexa.crashreporting.BugsnagIntegration.BugsnagLibraryApi
        public void notify(Throwable th) {
            Bugsnag.notify(th);
        }

        @Override // com.amazon.alexa.crashreporting.BugsnagIntegration.BugsnagLibraryApi
        public void putMetadata(Tab tab, String str, Object obj) {
            Bugsnag.getMetaData().addToTab(tab.tabName(), str, obj);
        }

        @Override // com.amazon.alexa.crashreporting.BugsnagIntegration.BugsnagLibraryApi
        public void setUser(String str) {
            Bugsnag.setUserEmail(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes6.dex */
    public enum Tab {
        DEFAULT(KnightDeviceFacade.WakeWordObserver.DEFAULT_WAKEWORD),
        FEATUREV2("ALEXA FEATURES V2");
        
        private String tabName;

        Tab(String str) {
            this.tabName = str;
        }

        public String tabName() {
            return this.tabName;
        }
    }

    @VisibleForTesting
    BugsnagIntegration(@NonNull Context context, @NonNull String str, BugsnagLibraryApi bugsnagLibraryApi, Provider<MetricsService> provider, Provider<DeviceInformation> provider2) {
        this.context = context;
        this.serviceOrProcessName = str;
        this.bugsnagLibraryApi = bugsnagLibraryApi;
        this.metricsService = provider;
        this.deviceInformation = provider2;
        for (Tab tab : Tab.values()) {
            this.pendingMetadata.put(tab, new HashMap());
        }
        this.pendingTabsToBeCleared = new HashSet();
    }

    private synchronized void flushPendingMetadata() {
        for (Map.Entry<Tab, Map<String, String>> entry : this.pendingMetadata.entrySet()) {
            Tab key = entry.getKey();
            for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
                this.bugsnagLibraryApi.putMetadata(key, entry2.getKey(), entry2.getValue());
            }
            entry.setValue(new HashMap());
        }
    }

    private synchronized void flushPendingOperations() {
        for (Tab tab : this.pendingTabsToBeCleared) {
            this.bugsnagLibraryApi.clearTab(tab);
        }
        this.pendingTabsToBeCleared.clear();
        flushPendingMetadata();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static synchronized BugsnagIntegration init(@NonNull Context context, @NonNull String str, Provider<MetricsService> provider, Provider<DeviceInformation> provider2) {
        BugsnagIntegration bugsnagIntegration;
        synchronized (BugsnagIntegration.class) {
            if (singleton == null) {
                singleton = new BugsnagIntegration(context, str, new BugsnagNativeLibraryApi(), provider, provider2);
                bugsnagIntegration = singleton;
            } else {
                throw new IllegalStateException("Already initialized.");
            }
        }
        return bugsnagIntegration;
    }

    @VisibleForTesting
    static synchronized BugsnagIntegration singleton() {
        BugsnagIntegration bugsnagIntegration;
        synchronized (BugsnagIntegration.class) {
            bugsnagIntegration = singleton;
        }
        return bugsnagIntegration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean beforeSend(Report report) {
        String format = String.format(Locale.US, "%s: %s", report.getError().getExceptionName(), report.getError().getExceptionMessage());
        if (Severity.ERROR.equals(report.getError().getSeverity())) {
            GeneratedOutlineSupport1.outline163("Bugsnag is processing a crash: ", format, TAG);
            if (this.metricsService.mo10268get() != null) {
                this.metricsService.mo10268get().recordEvent(CrashReportingMetrics.MetricKey.APP_CRASH_USING_BUGSNAG, "Application", null);
            }
        } else {
            GeneratedOutlineSupport1.outline163("Bugsnag is processing a non-fatal: ", format, TAG);
        }
        return true;
    }

    @VisibleForTesting
    synchronized void clearTab(Tab tab) {
        if (!isInitialized()) {
            this.pendingTabsToBeCleared.add(tab);
        } else {
            this.bugsnagLibraryApi.clearTab(tab);
        }
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    public String getFriendlyName() {
        return "Bugsnag";
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    void putMetadata(@NonNull String str, @NonNull String str2) {
        putMetadata(Tab.DEFAULT, str, str2);
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    synchronized void registerDefaultUncaughtExceptionHandler() {
        Configuration configuration;
        if (this.deviceInformation.mo10268get().isFireOS()) {
            configuration = new Configuration("39ffe376ceeb94dc4b913a83b334a5ef");
        } else {
            configuration = new Configuration(com.amazon.dee.app.BuildConfig.BUGSNAG_API_KEY);
        }
        configuration.beforeSend(new BeforeSend() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$Q7OVfgVGeQLZGZcVMFbp5TkUDo8
            @Override // com.bugsnag.android.BeforeSend
            public final boolean run(Report report) {
                return BugsnagIntegration.this.beforeSend(report);
            }
        });
        configuration.setDetectAnrs(true);
        configuration.setDetectNdkCrashes(true);
        configuration.setEndpoints("https://bugsnag-event.monitor.core.app.alexa.a2z.com", "https://bugsnag-session.monitor.core.app.alexa.a2z.com");
        configuration.setErrorStore("/error-store-" + this.serviceOrProcessName.replace(Chars.SPACE, '-') + '/');
        this.bugsnagLibraryApi.init(this.context, configuration);
        flushPendingOperations();
        Log.i(TAG, "Successfully initialized bugsnag.");
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    void reportNonFatal(Throwable th) {
        if (!isInitialized()) {
            Log.w(TAG, "Bugsnag isn't initialized, yet. Won't report non-fatal.", th);
        } else {
            this.bugsnagLibraryApi.notify(th);
        }
    }

    @Override // com.amazon.alexa.crashreporting.CrashReportingComponent
    void setAccount(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        Bugsnag.setUser(str, str2, str3);
    }

    @VisibleForTesting
    synchronized void setUser(String str) {
        if (isInitialized()) {
            this.bugsnagLibraryApi.setUser(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void syncFeaturesV2(Map<String, String> map) {
        if (map == null) {
            return;
        }
        Log.i(TAG, "Syncing FeatureServiceV2 features to Bugsnag");
        clearTab(Tab.FEATUREV2);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            putMetadata(Tab.FEATUREV2, entry.getKey(), entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void syncUser(String str) {
        setUser(str);
    }

    @VisibleForTesting
    synchronized void putMetadata(@NonNull Tab tab, @NonNull String str, @NonNull String str2) {
        if (!isInitialized()) {
            this.pendingMetadata.get(tab).put(str, str2);
        } else {
            this.bugsnagLibraryApi.putMetadata(tab, str, str2);
        }
    }
}
