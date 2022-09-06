package com.amazon.dee.app.framework;

import com.amazon.alexa.assetManagementService.api.AssetManagementService;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl;
import com.amazon.alexa.featureservice.service.DefaultFeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.tarazed.api.AlexaTarazedService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.matter.service.MatterService;
import com.dee.app.data.DefaultElementLocalStorage;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class MainApplicationImplementation_MembersInjector implements MembersInjector<MainApplicationImplementation> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<AlexaMobileAndroidFeatureServiceImpl> alexaMobileAndroidFeatureServiceProvider;
    private final Provider<AlexaTarazedService> alexaTarazedServiceProvider;
    private final Provider<AssetManagementService> assetManagementServiceProvider;
    private final Provider<CertificateReaderService> certificateReaderServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<CrashReportingService> crashReportingServiceProvider;
    private final Provider<DefaultElementLocalStorage> dataStoreProvider;
    private final Provider<DefaultFeatureServiceV2> defaultFeatureServiceV2Provider;
    private final Provider<LatencyInfra> latencyInfraProvider;
    private final Provider<MatterService> matterServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final Provider<OkHttpClient> okHttpClientProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<TaskManager> taskManagerProvider;

    public MainApplicationImplementation_MembersInjector(Provider<AccountService> provider, Provider<CrashMetadata> provider2, Provider<CrashReporter> provider3, Provider<CrashReportingService> provider4, Provider<LatencyInfra> provider5, Provider<OkHttpClient> provider6, Provider<RoutingService> provider7, Provider<TaskManager> provider8, Provider<Mobilytics> provider9, Provider<DefaultElementLocalStorage> provider10, Provider<ModeService> provider11, Provider<AlexaTarazedService> provider12, Provider<CertificateReaderService> provider13, Provider<DefaultFeatureServiceV2> provider14, Provider<AlexaMobileAndroidFeatureServiceImpl> provider15, Provider<AssetManagementService> provider16, Provider<MatterService> provider17) {
        this.accountServiceProvider = provider;
        this.crashMetadataProvider = provider2;
        this.crashReporterProvider = provider3;
        this.crashReportingServiceProvider = provider4;
        this.latencyInfraProvider = provider5;
        this.okHttpClientProvider = provider6;
        this.routingServiceProvider = provider7;
        this.taskManagerProvider = provider8;
        this.mobilyticsProvider = provider9;
        this.dataStoreProvider = provider10;
        this.modeServiceProvider = provider11;
        this.alexaTarazedServiceProvider = provider12;
        this.certificateReaderServiceProvider = provider13;
        this.defaultFeatureServiceV2Provider = provider14;
        this.alexaMobileAndroidFeatureServiceProvider = provider15;
        this.assetManagementServiceProvider = provider16;
        this.matterServiceProvider = provider17;
    }

    public static MembersInjector<MainApplicationImplementation> create(Provider<AccountService> provider, Provider<CrashMetadata> provider2, Provider<CrashReporter> provider3, Provider<CrashReportingService> provider4, Provider<LatencyInfra> provider5, Provider<OkHttpClient> provider6, Provider<RoutingService> provider7, Provider<TaskManager> provider8, Provider<Mobilytics> provider9, Provider<DefaultElementLocalStorage> provider10, Provider<ModeService> provider11, Provider<AlexaTarazedService> provider12, Provider<CertificateReaderService> provider13, Provider<DefaultFeatureServiceV2> provider14, Provider<AlexaMobileAndroidFeatureServiceImpl> provider15, Provider<AssetManagementService> provider16, Provider<MatterService> provider17) {
        return new MainApplicationImplementation_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17);
    }

    public static void injectAccountService(MainApplicationImplementation mainApplicationImplementation, Lazy<AccountService> lazy) {
        mainApplicationImplementation.accountService = lazy;
    }

    public static void injectAlexaMobileAndroidFeatureService(MainApplicationImplementation mainApplicationImplementation, Lazy<AlexaMobileAndroidFeatureServiceImpl> lazy) {
        mainApplicationImplementation.alexaMobileAndroidFeatureService = lazy;
    }

    public static void injectAlexaTarazedService(MainApplicationImplementation mainApplicationImplementation, Lazy<AlexaTarazedService> lazy) {
        mainApplicationImplementation.alexaTarazedService = lazy;
    }

    public static void injectAssetManagementService(MainApplicationImplementation mainApplicationImplementation, AssetManagementService assetManagementService) {
        mainApplicationImplementation.assetManagementService = assetManagementService;
    }

    public static void injectCertificateReaderService(MainApplicationImplementation mainApplicationImplementation, Lazy<CertificateReaderService> lazy) {
        mainApplicationImplementation.certificateReaderService = lazy;
    }

    public static void injectCrashMetadata(MainApplicationImplementation mainApplicationImplementation, Lazy<CrashMetadata> lazy) {
        mainApplicationImplementation.crashMetadata = lazy;
    }

    public static void injectCrashReporter(MainApplicationImplementation mainApplicationImplementation, Lazy<CrashReporter> lazy) {
        mainApplicationImplementation.crashReporter = lazy;
    }

    public static void injectCrashReportingService(MainApplicationImplementation mainApplicationImplementation, Lazy<CrashReportingService> lazy) {
        mainApplicationImplementation.crashReportingService = lazy;
    }

    public static void injectDataStore(MainApplicationImplementation mainApplicationImplementation, Lazy<DefaultElementLocalStorage> lazy) {
        mainApplicationImplementation.dataStore = lazy;
    }

    public static void injectDefaultFeatureServiceV2(MainApplicationImplementation mainApplicationImplementation, DefaultFeatureServiceV2 defaultFeatureServiceV2) {
        mainApplicationImplementation.defaultFeatureServiceV2 = defaultFeatureServiceV2;
    }

    public static void injectLatencyInfra(MainApplicationImplementation mainApplicationImplementation, LatencyInfra latencyInfra) {
        mainApplicationImplementation.latencyInfra = latencyInfra;
    }

    public static void injectMatterService(MainApplicationImplementation mainApplicationImplementation, Lazy<MatterService> lazy) {
        mainApplicationImplementation.matterService = lazy;
    }

    public static void injectMobilytics(MainApplicationImplementation mainApplicationImplementation, Lazy<Mobilytics> lazy) {
        mainApplicationImplementation.mobilytics = lazy;
    }

    public static void injectModeService(MainApplicationImplementation mainApplicationImplementation, ModeService modeService) {
        mainApplicationImplementation.modeService = modeService;
    }

    public static void injectOkHttpClient(MainApplicationImplementation mainApplicationImplementation, Lazy<OkHttpClient> lazy) {
        mainApplicationImplementation.okHttpClient = lazy;
    }

    public static void injectRoutingService(MainApplicationImplementation mainApplicationImplementation, Lazy<RoutingService> lazy) {
        mainApplicationImplementation.routingService = lazy;
    }

    public static void injectTaskManager(MainApplicationImplementation mainApplicationImplementation, Lazy<TaskManager> lazy) {
        mainApplicationImplementation.taskManager = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MainApplicationImplementation mainApplicationImplementation) {
        injectAccountService(mainApplicationImplementation, DoubleCheck.lazy(this.accountServiceProvider));
        injectCrashMetadata(mainApplicationImplementation, DoubleCheck.lazy(this.crashMetadataProvider));
        injectCrashReporter(mainApplicationImplementation, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashReportingService(mainApplicationImplementation, DoubleCheck.lazy(this.crashReportingServiceProvider));
        injectLatencyInfra(mainApplicationImplementation, this.latencyInfraProvider.mo10268get());
        injectOkHttpClient(mainApplicationImplementation, DoubleCheck.lazy(this.okHttpClientProvider));
        injectRoutingService(mainApplicationImplementation, DoubleCheck.lazy(this.routingServiceProvider));
        injectTaskManager(mainApplicationImplementation, DoubleCheck.lazy(this.taskManagerProvider));
        injectMobilytics(mainApplicationImplementation, DoubleCheck.lazy(this.mobilyticsProvider));
        injectDataStore(mainApplicationImplementation, DoubleCheck.lazy(this.dataStoreProvider));
        injectModeService(mainApplicationImplementation, this.modeServiceProvider.mo10268get());
        injectAlexaTarazedService(mainApplicationImplementation, DoubleCheck.lazy(this.alexaTarazedServiceProvider));
        injectCertificateReaderService(mainApplicationImplementation, DoubleCheck.lazy(this.certificateReaderServiceProvider));
        injectDefaultFeatureServiceV2(mainApplicationImplementation, this.defaultFeatureServiceV2Provider.mo10268get());
        injectAlexaMobileAndroidFeatureService(mainApplicationImplementation, DoubleCheck.lazy(this.alexaMobileAndroidFeatureServiceProvider));
        injectAssetManagementService(mainApplicationImplementation, this.assetManagementServiceProvider.mo10268get());
        injectMatterService(mainApplicationImplementation, DoubleCheck.lazy(this.matterServiceProvider));
    }
}
