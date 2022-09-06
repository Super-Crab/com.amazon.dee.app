package com.amazon.dee.app.framework;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Process;
import android.util.AndroidRuntimeException;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.assetManagementService.api.AssetManagementService;
import com.amazon.alexa.audiopersonalization.api.AudioPersonalizationManager;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.devicesetup.service.DeviceProvisioningCoordinator;
import com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl;
import com.amazon.alexa.featureservice.service.DefaultFeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.alexa.lifecycle.api.LifecycleManager;
import com.amazon.alexa.location.provider.LocationSkillsService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.tarazed.api.AlexaTarazedService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.dee.app.BuildConfig;
import com.amazon.dee.app.R;
import com.amazon.dee.app.dependencies.ApplicationComponent;
import com.amazon.dee.app.dependencies.ApplicationModule;
import com.amazon.dee.app.dependencies.DaggerApplicationComponent;
import com.amazon.dee.app.dependencies.ServiceModule;
import com.amazon.dee.app.elements.bridges.TraceModule;
import com.amazon.dee.app.framework.MainApplicationImplementation;
import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.HistogramMetricsEvent;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.dee.app.services.metrics.UserPerceivedLatencyService;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.strictmode.AlexaStrictMode;
import com.amazon.dee.app.ui.main.LoginForTestUi;
import com.amazon.dee.app.ui.main.MainActivity;
import com.amazon.dee.app.util.Utils;
import com.amazon.deecomms.accessories.monitors.AlexaCallNotificationMonitor;
import com.amazon.deecomms.accessories.monitors.AlexaMessageNotificationMonitor;
import com.amazon.latencyinfra.DefaultLatencyReporter;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.LatencyMarker;
import com.amazon.latencyinfra.LatencyNamespace;
import com.amazon.latencyinfra.LatencyReporterArgument;
import com.amazon.latencyinfra.LatencyReporterType;
import com.amazon.matter.service.MatterService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.Cache;
import com.dee.app.data.DefaultElementLocalStorage;
import com.dee.app.metrics.MetricTimeout;
import com.dee.app.metrics.MetricsService;
import com.facebook.react.modules.network.OkHttpClientFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.react.modules.network.ReactCookieJarContainer;
import com.facebook.soloader.SoLoader;
import com.google.common.base.Optional;
import com.squareup.leakcanary.LeakCanary;
import dagger.Lazy;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import net.danlew.android.joda.JodaTimeAndroid;
import okhttp3.OkHttpClient;
import rx.plugins.RxJavaHooks;
/* loaded from: classes12.dex */
public class MainApplicationImplementation implements ApplicationImplementation {
    @Inject
    Lazy<AccountService> accountService;
    @Inject
    Lazy<AlexaMobileAndroidFeatureServiceImpl> alexaMobileAndroidFeatureService;
    private AlexaStrictMode alexaStrictMode;
    @Inject
    Lazy<AlexaTarazedService> alexaTarazedService;
    @VisibleForTesting
    protected Application application;
    private DefaultApplicationLifecycleService applicationLifecycleService;
    @Inject
    AssetManagementService assetManagementService;
    @Inject
    Lazy<CertificateReaderService> certificateReaderService;
    protected ApplicationComponent component;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    @Inject
    Lazy<CrashReportingService> crashReportingService;
    protected Locale currentLocale;
    @Inject
    @Named(ServiceModule.DATA_STORE)
    Lazy<DefaultElementLocalStorage> dataStore;
    @Inject
    DefaultFeatureServiceV2 defaultFeatureServiceV2;
    private DeviceInformation deviceInformation;
    private MetricTimeout landscapeMetricTimeout;
    @Inject
    LatencyInfra latencyInfra;
    protected LifecycleManager lifecycleManager;
    @Inject
    Lazy<MatterService> matterService;
    protected MetricsService metricsService;
    @Inject
    Lazy<Mobilytics> mobilytics;
    @Inject
    ModeService modeService;
    @Inject
    Lazy<OkHttpClient> okHttpClient;
    @Inject
    Lazy<RoutingService> routingService;
    @Inject
    Lazy<TaskManager> taskManager;
    private static final String TAG = Log.tag(MainApplicationImplementation.class);
    private static final String APP_INFO = Arrays.toString(new String[]{"com.amazon.dee.app", "prod", "release", BuildConfig.VERSION_NAME, String.valueOf(-1)});
    private static boolean coldStart = true;
    private static boolean isSingleSignOnBuild = false;
    private static boolean hasReportedAppForeground = false;
    private boolean isActivityStopEventSent = true;
    private boolean isLandscapeAllowed = false;
    private boolean isNoScreenVisibleAfterAppQuit = false;
    private int startedActivityCounter = 0;
    private int createdActivityCounter = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.dee.app.framework.MainApplicationImplementation$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass1 extends DefaultLatencyReporter {
        AnonymousClass1(LatencyReporterType latencyReporterType) {
            super(latencyReporterType);
        }

        public /* synthetic */ void lambda$report$0$MainApplicationImplementation$1(LatencyReporterArgument latencyReporterArgument) {
            MainApplicationImplementation.this.component.mobilyticsReporter().report(latencyReporterArgument);
        }

        @Override // com.amazon.latencyinfra.LatencyReporter
        public void report(final LatencyReporterArgument latencyReporterArgument) {
            MainApplicationImplementation.this.taskManager.mo358get().getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$1$S3H-mDcsxQZGyKM9u6nO7LCoPMg
                @Override // java.lang.Runnable
                public final void run() {
                    MainApplicationImplementation.AnonymousClass1.this.lambda$report$0$MainApplicationImplementation$1(latencyReporterArgument);
                }
            });
        }
    }

    /* renamed from: com.amazon.dee.app.framework.MainApplicationImplementation$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$framework$MainApplicationImplementation$BundleDataType = new int[BundleDataType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$dee$app$framework$MainApplicationImplementation$BundleDataType[BundleDataType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$framework$MainApplicationImplementation$BundleDataType[BundleDataType.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$framework$MainApplicationImplementation$BundleDataType[BundleDataType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$framework$MainApplicationImplementation$BundleDataType[BundleDataType.INTEGER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum BundleDataType {
        BOOLEAN,
        STRING,
        FLOAT,
        INTEGER
    }

    private void checkForInitialScreenVisible() {
        this.startedActivityCounter++;
        if (this.startedActivityCounter != 1 || !this.isActivityStopEventSent) {
            return;
        }
        DefaultApplicationLifecycleService defaultApplicationLifecycleService = this.applicationLifecycleService;
        if (defaultApplicationLifecycleService != null) {
            defaultApplicationLifecycleService.notifyOnStart();
        }
        this.isActivityStopEventSent = false;
        this.isNoScreenVisibleAfterAppQuit = false;
    }

    private void checkForNoScreenVisible() {
        this.startedActivityCounter--;
        if (this.startedActivityCounter == 0) {
            this.isNoScreenVisibleAfterAppQuit = true;
            notify(LifecycleEvent.APPLICATION_WILL_SHUTDOWN);
            DefaultApplicationLifecycleService defaultApplicationLifecycleService = this.applicationLifecycleService;
            if (defaultApplicationLifecycleService != null) {
                defaultApplicationLifecycleService.notifyOnStop();
            }
            this.isActivityStopEventSent = true;
        }
    }

    static void doRestart(Context context) {
        try {
            if (context != null) {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    if (AlexaApplication.isAppOnForeground(context)) {
                        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(context.getPackageName());
                        if (launchIntentForPackage != null) {
                            launchIntentForPackage.setFlags(268468224);
                            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + 100, PendingIntent.getActivity(context, 223344556, launchIntentForPackage, 268435456));
                        } else {
                            Log.e(TAG, "Was not able to restart application, pending intent is null");
                        }
                    }
                } else {
                    Log.e(TAG, "Was not able to restart application, package manager null");
                }
            } else {
                Log.e(TAG, "Was not able to restart application, Context null");
            }
        } catch (Exception unused) {
            Log.e(TAG, "Was not able to restart application");
        }
        System.exit(0);
    }

    private Locale getConfiguredLocale(Configuration configuration) {
        int i = Build.VERSION.SDK_INT;
        return configuration.getLocales().get(0);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0045 -> B:17:0x0055). Please submit an issue!!! */
    private Object getManifestMetadata(BundleDataType bundleDataType, String str, Object obj) {
        Object obj2;
        Bundle bundle;
        int ordinal;
        try {
            bundle = this.application.getPackageManager().getApplicationInfo(this.application.getPackageName(), 128).metaData;
            ordinal = bundleDataType.ordinal();
        } catch (Exception e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to load metadata: ");
            outline107.append(e.getMessage());
            outline107.toString();
        }
        if (ordinal == 0) {
            obj2 = Boolean.valueOf(bundle.getBoolean(str));
        } else if (ordinal == 1) {
            obj2 = bundle.getString(str);
        } else if (ordinal != 2) {
            obj2 = ordinal != 3 ? obj : Integer.valueOf(bundle.getInt(str));
        } else {
            obj2 = Float.valueOf(bundle.getFloat(str));
        }
        if (obj2 == null) {
            obj2 = obj;
        }
        Utils.safeFormat("%s: %s", str, obj2);
        return obj2;
    }

    private void initJodaTime() {
        JodaTimeAndroid.init(this.application);
    }

    private void initializeAccessories() {
        AccessoriesFactory.activateAccessories(this.application, this.component.identityService(), this.mobilytics, new AlexaCallNotificationMonitor(this.application), new AlexaMessageNotificationMonitor(this.application), new Lazy() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$_IYoNcXR1N9EbHgpddGyTG2_poc
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return MainApplicationImplementation.this.lambda$initializeAccessories$9$MainApplicationImplementation();
            }
        }, new Lazy() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$4VoCm3x7m8-Y7TW6ZcjtILqryE0
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return MainApplicationImplementation.this.lambda$initializeAccessories$10$MainApplicationImplementation();
            }
        });
        this.component.commsManager().initializeAccessoryComponents();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeConversationService() {
        try {
            this.component.conversationService().initialize();
        } catch (AndroidRuntimeException e) {
            Log.e(TAG, "Caught AndroidRuntimeException, webview is missing.");
            this.crashReportingService.mo358get().reportNonFatal(e);
            System.exit(2);
        }
        this.component.tCommServiceManager().start();
    }

    private void initializeLeakCanary() {
        "robolectric".equals(Build.FINGERPRINT);
    }

    private void initializeModeService() {
        if (this.modeService == null) {
            this.modeService = (ModeService) GeneratedOutlineSupport1.outline20(ModeService.class);
        }
        this.modeService.initialize();
    }

    private void initializeOrientationMetrics() {
        if (this.deviceInformation == null) {
            this.deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
        }
        this.isLandscapeAllowed = !this.deviceInformation.isPhoneFormFactor();
        if (this.isLandscapeAllowed) {
            this.landscapeMetricTimeout = new MetricTimeout(this.metricsService);
            this.landscapeMetricTimeout.setEventData(MetricTimeout.Timeout.FIRST, AlexaMetricsConstants.MetricEvents.APP_SESSION_LANDSCAPE_USE_10, 10);
            this.landscapeMetricTimeout.setEventData(MetricTimeout.Timeout.SECOND, AlexaMetricsConstants.MetricEvents.APP_SESSION_LANDSCAPE_USE_60, 60);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initialize$3() {
        Optional optional = ComponentRegistry.getInstance().get(LocationSkillsService.class);
        if (optional.isPresent()) {
            ((LocationSkillsService) optional.get()).start();
        }
    }

    private void reportFirstAppForeground() {
        if (hasReportedAppForeground) {
            return;
        }
        hasReportedAppForeground = true;
        recordDiagnosticMetrics(AlexaMetricsConstants.MetricEvents.APP_LAUNCHED_IN_FOREGROUND, "Application", TAG);
    }

    private void reportStringAsLong(String str, String str2) {
        try {
            this.metricsService.recordEvent(new HistogramMetricsEvent(str, "Application", Long.parseLong(str2)));
        } catch (NumberFormatException unused) {
            new Object[1][0] = str2;
        }
    }

    private void reportTotalMemory(Map<String, String> map) {
        try {
            this.metricsService.recordEvent(new HistogramMetricsEvent(AlexaMetricsConstants.MetricEvents.MEMORY_PROCESS_SIZE, "Application", Integer.parseInt(map.get("summary.total-pss")) - Integer.parseInt(map.get("summary.private-other"))));
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }

    private void trackWordSize() {
        int i = Build.VERSION.SDK_INT;
        boolean is64Bit = Process.is64Bit();
        String str = TAG;
        Log.i(str, "Word size is64Bit: " + is64Bit);
        this.crashMetadata.mo358get().put("is_64_bit", is64Bit);
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public ApplicationComponent getComponent() {
        if (this.component == null) {
            this.component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this.application)).build();
        }
        return this.component;
    }

    protected void initReactNative() {
        OkHttpClientProvider.setOkHttpClientFactory(new OkHttpClientFactory() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$KjC5f_lagdyi7yZMv9BZQbImGAs
            @Override // com.facebook.react.modules.network.OkHttpClientFactory
            public final OkHttpClient createNewNetworkModuleClient() {
                return MainApplicationImplementation.this.lambda$initReactNative$7$MainApplicationImplementation();
            }
        });
        this.taskManager.mo358get().getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$YaeK3OzZTyRSaegcD4zuQ_kHBLg
            @Override // java.lang.Runnable
            public final void run() {
                MainApplicationImplementation.this.lambda$initReactNative$8$MainApplicationImplementation();
            }
        });
    }

    protected void initialize() {
        long currentTimeMillis = System.currentTimeMillis();
        initJodaTime();
        RxJavaPlugins.setErrorHandler(new Consumer() { // from class: com.amazon.dee.app.framework.-$$Lambda$Ex5Sg0zNHFZQ95QtIwne8NS7-s4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MainApplicationImplementation.this.reportUnhandledRxException((Throwable) obj);
            }
        });
        RxJavaHooks.lockdown();
        RxJavaPlugins.lockdown();
        getComponent();
        this.component.componentBinder().registerComponents(this.application);
        this.component.inject(this);
        this.component.componentBinder().publishTransitionalObjectsOwnedByDagger();
        long currentTimeMillis2 = System.currentTimeMillis();
        this.component.eventBusService().start();
        this.defaultFeatureServiceV2.initialize();
        this.alexaMobileAndroidFeatureService.mo358get().initialize();
        this.assetManagementService.initialize();
        initializeLifecycleManager();
        initReactNative();
        long currentTimeMillis3 = System.currentTimeMillis();
        if (BuildConfig.IS_PROD_ENVIRONMENT) {
            Log.enforceReleaseLogging();
        }
        TraceModule.enableApplicationTracing(true);
        isSingleSignOnBuild = true ^ ((Boolean) getManifestMetadata(BundleDataType.BOOLEAN, "MAPIsolateApplication", false)).booleanValue();
        this.latencyInfra.setIsInternalBuild(Boolean.valueOf(this.certificateReaderService.mo358get().isPerformanceProfilingBuild()));
        this.latencyInfra.setDefaultReporter(new AnonymousClass1(LatencyReporterType.METRIC));
        this.component.commsService().initialize();
        this.latencyInfra.mark(LatencyMarker.COMMS_CSL, LatencyNamespace.PROFILE_COLDSTART);
        this.metricsService = this.component.metricsService();
        this.metricsService.beginSession();
        LatencyService.setMetricsService(this.metricsService);
        this.taskManager.mo358get().getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$tph3MW6ynq0BJLDZ3mbV-oD-ERg
            @Override // java.lang.Runnable
            public final void run() {
                MainApplicationImplementation.this.lambda$initialize$0$MainApplicationImplementation();
            }
        });
        this.taskManager.mo358get().getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$xjdD-fsKqtGhdKNMakqiAOd0A8Q
            @Override // java.lang.Runnable
            public final void run() {
                MainApplicationImplementation.this.lambda$initialize$1$MainApplicationImplementation();
            }
        });
        this.applicationLifecycleService = this.component.applicationLifecycleService();
        this.taskManager.mo358get().addAfterUiTask(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$cK1MvQNtg82vN8mMu7ez-ydriGk
            @Override // java.lang.Runnable
            public final void run() {
                MainApplicationImplementation.this.initializeConversationService();
            }
        });
        this.taskManager.mo358get().getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$h63XN2JBTJxEikvGLyoOHT4LKZc
            @Override // java.lang.Runnable
            public final void run() {
                MainApplicationImplementation.this.lambda$initialize$2$MainApplicationImplementation();
            }
        });
        this.taskManager.mo358get().getExecutor(0).submit($$Lambda$MainApplicationImplementation$wezUCvALQPiliRbwt_RTMH0k7rA.INSTANCE);
        initializeAccessories();
        this.latencyInfra.mark(LatencyMarker.ACCESSORIES_CSL, LatencyNamespace.PROFILE_COLDSTART);
        this.taskManager.mo358get().addAfterUiTask($$Lambda$MainApplicationImplementation$YLwiPV1p9kzYA8W3YC4U94SLqTc.INSTANCE);
        this.taskManager.mo358get().addAfterUiTask(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$7K7ho6YI1Y2AvdLslzM622no_C4
            @Override // java.lang.Runnable
            public final void run() {
                MainApplicationImplementation.this.lambda$initialize$5$MainApplicationImplementation();
            }
        });
        this.taskManager.mo358get().addAfterUiTask(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$CzSU-76i_GQdSVr4wpEstUMNlhA
            @Override // java.lang.Runnable
            public final void run() {
                MainApplicationImplementation.this.lambda$initialize$6$MainApplicationImplementation();
            }
        });
        if (!AlexaApplication.isAppOnForeground(this.application)) {
            LatencyService.pause();
        }
        initializeShortLivedTasks(this.taskManager.mo358get());
        initializeOrientationMetrics();
        notify(LifecycleEvent.APPLICATION_LOADED);
        trackWordSize();
        LatencyService.recordStartupTimeline(currentTimeMillis, currentTimeMillis2, currentTimeMillis3);
        initializeModeService();
        this.latencyInfra.mark(LatencyMarker.MOSAIC_THEME_INIT_CSL_START, LatencyNamespace.PROFILE_COLDSTART);
        ThemeUtil.initTheme(null);
        this.latencyInfra.mark(LatencyMarker.MOSAIC_THEME_INIT_CSL_END, LatencyNamespace.PROFILE_COLDSTART);
        this.latencyInfra.mark(LatencyMarker.MAIN_APPLICATION_CSL, LatencyNamespace.PROFILE_COLDSTART);
    }

    protected void initializeLifecycleManager() {
        this.lifecycleManager = (LifecycleManager) GeneratedOutlineSupport1.outline20(LifecycleManager.class);
        this.lifecycleManager.start();
    }

    @VisibleForTesting
    void initializeShortLivedTasks(TaskManager taskManager) {
        taskManager.getExecutor(0).submit($$Lambda$MainApplicationImplementation$N33yvH5ixEDTSih2MpBIJQbiftM.INSTANCE);
        taskManager.getExecutor(0).submit($$Lambda$MainApplicationImplementation$S67Vilqy4pPaWuw9XlJu2iL97E.INSTANCE);
        taskManager.getExecutor(0).submit($$Lambda$MainApplicationImplementation$xagaKA7Iv50pYrL873KZXpBrPg.INSTANCE);
        taskManager.getExecutor(0).submit($$Lambda$MainApplicationImplementation$gmsxjP2OR8AduIAXW8YEGyVsuY.INSTANCE);
        taskManager.getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$hWuNE8Rxt1wiy1VnS1VPpyTgMBg
            @Override // java.lang.Runnable
            public final void run() {
                MainApplicationImplementation.this.lambda$initializeShortLivedTasks$15$MainApplicationImplementation();
            }
        });
        taskManager.getExecutor(0).submit($$Lambda$MainApplicationImplementation$liZkQc4_gSlrQUKKL3_me_L0Y7w.INSTANCE);
        taskManager.getExecutor(0).submit($$Lambda$MainApplicationImplementation$LjWnX1rm85tByEhF7HQj8elyvpE.INSTANCE);
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public boolean isColdStart() {
        return coldStart;
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public boolean isNoActivityVisible() {
        return this.isNoScreenVisibleAfterAppQuit;
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public boolean isSingleSignOnBuild() {
        return isSingleSignOnBuild;
    }

    public /* synthetic */ OkHttpClient lambda$initReactNative$7$MainApplicationImplementation() {
        return this.okHttpClient.mo358get().newBuilder().connectTimeout(60L, TimeUnit.SECONDS).cookieJar(new ReactCookieJarContainer()).readTimeout(300L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build();
    }

    public /* synthetic */ void lambda$initReactNative$8$MainApplicationImplementation() {
        SoLoader.init((Context) this.application, false);
    }

    public /* synthetic */ void lambda$initialize$0$MainApplicationImplementation() {
        this.component.accessibilityService().recordAccessibilityMetrics();
    }

    public /* synthetic */ void lambda$initialize$1$MainApplicationImplementation() {
        this.crashReportingService.mo358get().start();
    }

    public /* synthetic */ void lambda$initialize$2$MainApplicationImplementation() {
        ((DeviceProvisioningCoordinator) GeneratedOutlineSupport1.outline20(DeviceProvisioningCoordinator.class)).initialize();
        this.component.voiceService();
        this.component.locationService().start();
    }

    public /* synthetic */ void lambda$initialize$5$MainApplicationImplementation() {
        ((AudioPersonalizationManager) GeneratedOutlineSupport1.outline20(AudioPersonalizationManager.class)).init(this.application.getApplicationContext());
    }

    public /* synthetic */ void lambda$initialize$6$MainApplicationImplementation() {
        this.matterService.mo358get().start();
    }

    public /* synthetic */ Cache lambda$initializeAccessories$10$MainApplicationImplementation() {
        return this.dataStore.mo358get().getNativeCache();
    }

    public /* synthetic */ OkHttpClient lambda$initializeAccessories$9$MainApplicationImplementation() {
        return this.component.httpClient();
    }

    public /* synthetic */ void lambda$initializeShortLivedTasks$15$MainApplicationImplementation() {
        this.alexaTarazedService.mo358get().initialize();
    }

    protected void notify(LifecycleEvent lifecycleEvent) {
        this.lifecycleManager.notify(lifecycleEvent);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        String.format("Activity created: %s", activity.getClass().getCanonicalName());
        this.createdActivityCounter++;
        recordDiagnosticMetrics(AlexaMetricsConstants.MetricEvents.ACTIVITY_CREATED, activity.getClass().getCanonicalName(), activity.getClass().getCanonicalName());
        if (!(activity instanceof MainActivity)) {
            UserPerceivedLatencyService.cancelColdStartTimer();
            this.latencyInfra.blockNamespace(LatencyNamespace.HOME_COLDSTART);
            this.latencyInfra.blockNamespace(LatencyNamespace.JASPERHOME_COLDSTART);
            this.latencyInfra.blockNamespace(LatencyNamespace.PROFILE_COLDSTART);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        recordDiagnosticMetrics(AlexaMetricsConstants.MetricEvents.ACTIVITY_DESTROYED, activity.getClass().getCanonicalName(), activity.getClass().getCanonicalName());
        String.format("Activity destroyed: %s", activity.getClass().getCanonicalName());
        this.createdActivityCounter--;
        if (!AlexaApplication.isAppOnForeground(this.application)) {
            this.metricsService.pauseSession();
            if (this.createdActivityCounter != 0) {
                return;
            }
            this.routingService.mo358get().clearCurrentRoute();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        recordDiagnosticMetrics(AlexaMetricsConstants.MetricEvents.ACTIVITY_PAUSED, activity.getClass().getCanonicalName(), activity.getClass().getCanonicalName());
        String.format("Activity paused: %s", activity.getClass().getCanonicalName());
        if (!AlexaApplication.isAppOnForeground(this.application)) {
            this.crashMetadata.mo358get().put("in_background", true);
            if (!LatencyService.isStartType(LatencyService.StartType.COLD)) {
                coldStart = false;
            }
            LatencyService.pause();
            this.metricsService.pauseSession();
        }
        if (this.isLandscapeAllowed) {
            this.landscapeMetricTimeout.stop();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        recordDiagnosticMetrics(AlexaMetricsConstants.MetricEvents.ACTIVITY_RESUMED, activity.getClass().getCanonicalName(), activity.getClass().getCanonicalName());
        String.format("Activity resumed: %s", activity.getClass().getCanonicalName());
        if (AlexaApplication.isAppOnForeground(this.application)) {
            this.crashMetadata.mo358get().put("in_background", false);
            this.metricsService.resumeSession();
            LatencyService.resume();
            reportFirstAppForeground();
        }
        if (!LatencyService.isStartActive() && AlexaApplication.isAppLaunchingFromBackground()) {
            LatencyService.start(LatencyService.StartType.WARM);
        }
        if (this.isLandscapeAllowed && this.application.getResources().getConfiguration().orientation == 2) {
            this.landscapeMetricTimeout.start();
        }
        if (this.mobilytics.mo358get() != null) {
            this.mobilytics.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.APP_SESSION_LANDSCAPE_ENABLED, this.isLandscapeAllowed, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        }
        if (LatencyService.isStartType(LatencyService.StartType.WARM) && AlexaApplication.isAppLaunchingFromBackground()) {
            LatencyService.complete(LatencyService.Component.NATIVE, LatencyService.Completion.UNKNOWN);
        }
        if (!"AuthPortalUIActivity".equals(activity.getClass().getSimpleName()) || activity.findViewById(R.id.test_login_id) != null) {
            return;
        }
        final Lazy<AccountService> lazy = this.accountService;
        lazy.getClass();
        new LoginForTestUi(new Provider() { // from class: com.amazon.dee.app.framework.-$$Lambda$DtGUkZaPpicRKnRM5mCHlKIDR_U
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return (AccountService) Lazy.this.mo358get();
            }
        }).addHiddenLoginWidgets(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        checkForInitialScreenVisible();
        recordDiagnosticMetrics(AlexaMetricsConstants.MetricEvents.ACTIVITY_STARTED, activity.getClass().getCanonicalName(), activity.getClass().getCanonicalName());
        String.format("Activity started: %s", activity.getClass().getCanonicalName());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        recordDiagnosticMetrics(AlexaMetricsConstants.MetricEvents.ACTIVITY_STOPPED, activity.getClass().getCanonicalName(), activity.getClass().getCanonicalName());
        String.format("Activity stopped: %s", activity.getClass().getCanonicalName());
        if (!AlexaApplication.isAppOnForeground(this.application)) {
            this.metricsService.pauseSession();
            UserPerceivedLatencyService.cancelColdStartTimer();
            this.latencyInfra.blockNamespace(LatencyNamespace.HOME_COLDSTART);
            this.latencyInfra.blockNamespace(LatencyNamespace.JASPERHOME_COLDSTART);
            this.latencyInfra.blockNamespace(LatencyNamespace.PROFILE_COLDSTART);
        }
        checkForNoScreenVisible();
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public void onConfigurationChanged(Configuration configuration) {
        if (LeakCanary.isInAnalyzerProcess(this.application)) {
            return;
        }
        boolean z = false;
        Locale configuredLocale = getConfiguredLocale(configuration);
        if (configuredLocale != null && !configuredLocale.equals(this.currentLocale)) {
            if (this.currentLocale != null) {
                z = true;
            }
            this.currentLocale = getConfiguredLocale(this.application.getResources().getConfiguration());
        }
        if (z) {
            doRestart(this.application.getApplicationContext());
            this.component.voiceService().updateLocale(this.currentLocale);
        }
        if (!AlexaApplication.isAppOnForeground(this.application) || !this.isLandscapeAllowed) {
            return;
        }
        if (configuration.orientation == 2) {
            this.landscapeMetricTimeout.start();
        } else {
            this.landscapeMetricTimeout.stop();
        }
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public void onCreate(Application application) {
        this.application = application;
        initializeLeakCanary();
        if (AlexaApplication.isAppOnForeground(application)) {
            UserPerceivedLatencyService.initColdStartTimer();
        }
        LatencyService.start(LatencyService.StartType.COLD);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Launching ");
        outline107.append(APP_INFO);
        Log.i(str, outline107.toString());
        initialize();
        this.currentLocale = getConfiguredLocale(application.getResources().getConfiguration());
        if (!AlexaApplication.isAppOnForeground(application)) {
            this.latencyInfra.disable();
        }
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public void onTrimMemory(int i) {
        String str = i != 15 ? i != 60 ? i != 80 ? null : AlexaMetricsConstants.MetricEvents.TRIM_MEMORY_COMPLETE : AlexaMetricsConstants.MetricEvents.TRIM_MEMORY_MODERATE : AlexaMetricsConstants.MetricEvents.TRIM_MEMORY_RUNNING_CRITICAL;
        if (str == null || this.component == null) {
            return;
        }
        for (String str2 : Arrays.asList(str, AlexaMetricsConstants.MetricEvents.APP_MEMORY_WARNING, AlexaMetricsConstants.MetricEvents.APP_SESSION_MEMORY_WARNING)) {
            recordDiagnosticMetrics(str2, "Application", TAG);
        }
    }

    @VisibleForTesting
    void recordDiagnosticMetrics(String str, String str2, String str3) {
        if (this.mobilytics.mo358get() != null) {
            this.mobilytics.mo358get().recordOperationalEvent(this.mobilytics.mo358get().createOperationalEvent(str, OperationalEventType.DIAGNOSTIC, str2, str3, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        }
    }

    @Override // com.amazon.dee.app.framework.ApplicationImplementation
    public void reportMemoryStats() {
        int i = Build.VERSION.SDK_INT;
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        Map<String, String> memoryStats = memoryInfo.getMemoryStats();
        reportStringAsLong(AlexaMetricsConstants.MetricEvents.MEMORY_JAVA_HEAP_SIZE, memoryStats.get("summary.java-heap"));
        reportStringAsLong(AlexaMetricsConstants.MetricEvents.MEMORY_NATIVE_HEAP_SIZE, memoryStats.get("summary.native-heap"));
        reportStringAsLong(AlexaMetricsConstants.MetricEvents.MEMORY_GRAPHICS_SIZE, memoryStats.get("summary.graphics"));
        reportTotalMemory(memoryStats);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void reportUnhandledRxException(Throwable th) throws Exception {
        boolean z = false;
        Log.e("RxJavaFailure", th, "Unhandled Exception in RxJava Thread", new Object[0]);
        if (this.component == null || this.certificateReaderService.mo358get().isPerformanceProfilingBuild() || this.crashReporter == null) {
            z = true;
        }
        if (z) {
            if (!(th instanceof Exception)) {
                throw new RuntimeException("Unhandled Exception in RxJava Thread", th);
            }
            throw ((Exception) th);
        }
        this.crashReporter.mo358get().reportNonFatal(th, 100);
    }
}
