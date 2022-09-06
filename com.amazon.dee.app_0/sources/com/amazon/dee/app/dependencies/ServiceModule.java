package com.amazon.dee.app.dependencies;

import android.content.Context;
import android.webkit.CookieManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.assetManagementService.api.AssetManagementService;
import com.amazon.alexa.assetManagementService.service.GetAssetURLService;
import com.amazon.alexa.assetManagementService.storage.GetAssetURLStorageService;
import com.amazon.alexa.crashreporting.CrashReportingMetadata;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.DefaultCrashReportingServiceFactory;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashObserver;
import com.amazon.alexa.crashreporting.api.CrashObserverRegistrar;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.dialog.api.DialogBuilderProvider;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.dependencies.DaggerInitializer;
import com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl;
import com.amazon.alexa.featureservice.service.DefaultFeatureServiceV2;
import com.amazon.alexa.featureservice.storage.FeatureServiceStorage;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.location.LocationProvider;
import com.amazon.alexa.location.LocationService;
import com.amazon.alexa.location.LocationServiceFactory;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.DefaultRoutingService;
import com.amazon.alexa.routing.DefaultRoutingViewService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.alexa.tarazed.api.AlexaTarazedService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.ttcf.TTCFService;
import com.amazon.alexa.ttcf.api.TTCFRoute;
import com.amazon.alexa.ttcf.api.TTCFRoutingDelegate;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.dee.app.framework.ApplicationRoutingAdapter;
import com.amazon.dee.app.services.accessibility.AccessibilityService;
import com.amazon.dee.app.services.accessibility.DefaultAccessibilityService;
import com.amazon.dee.app.services.bluetooth.BluetoothService;
import com.amazon.dee.app.services.bluetooth.DefaultBluetoothService;
import com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService;
import com.amazon.dee.app.services.clouddrive.CloudDriveService;
import com.amazon.dee.app.services.coral.AcceptHeaderRequestInterceptor;
import com.amazon.dee.app.services.coral.AcceptLanguageRequestInterceptor;
import com.amazon.dee.app.services.coral.CookieAuthenticationRequestInterceptor;
import com.amazon.dee.app.services.coral.EnvironmentUrlResolver;
import com.amazon.dee.app.services.coral.HttpCoralAuthenticationResponseInterceptor;
import com.amazon.dee.app.services.coral.HttpCoralDefaultInterceptor;
import com.amazon.dee.app.services.coral.TimestampHeaderRequestInterceptor;
import com.amazon.dee.app.services.coral.UserAgentRequestInterceptor;
import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.environment.DataRegionEnvironmentService;
import com.amazon.dee.app.services.environment.DefaultEnvironmentService;
import com.amazon.dee.app.services.environment.PersistentEndpointsStorage;
import com.amazon.dee.app.services.environment.PfmEnvironmentService;
import com.amazon.dee.app.services.export.ComponentBinder;
import com.amazon.dee.app.services.export.ComponentBinderImpl;
import com.amazon.dee.app.services.features.Features;
import com.amazon.dee.app.services.header.DefaultHeaderCacheService;
import com.amazon.dee.app.services.header.HeaderCacheService;
import com.amazon.dee.app.services.marketplace.PreferredMarketplaceService;
import com.amazon.dee.app.services.marketplace.ProtocolsPreferredMarketplaceService;
import com.amazon.dee.app.services.messaging.AmazonDeviceMessagingService;
import com.amazon.dee.app.services.messaging.FirebaseCloudMessagingService;
import com.amazon.dee.app.services.messaging.MessageCrypto;
import com.amazon.dee.app.services.messaging.MessagingHandler;
import com.amazon.dee.app.services.messaging.MessagingSettings;
import com.amazon.dee.app.services.messaging.UnsupportedMessagingService;
import com.amazon.dee.app.services.metrics.DCMMetricsConnector;
import com.amazon.dee.app.services.metrics.ElementsMetricsService;
import com.amazon.dee.app.services.metrics.FeatureCheckedMetricsService;
import com.amazon.dee.app.services.metrics.KinesisMetricsConnector;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.dee.app.services.network.interceptor.AcceptContentTypeInterceptor;
import com.amazon.dee.app.services.network.interceptor.AcceptLanguageInterceptor;
import com.amazon.dee.app.services.network.interceptor.ClientTimestampInterceptor;
import com.amazon.dee.app.services.network.interceptor.CookieAuthenticationInterceptor;
import com.amazon.dee.app.services.network.interceptor.CookieRefreshInterceptor;
import com.amazon.dee.app.services.network.interceptor.NetworkLatencyInterceptor;
import com.amazon.dee.app.services.network.interceptor.UserAgentInterceptor;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.security.DefaultCertificateReaderService;
import com.amazon.dee.app.services.useragent.UserAgentService;
import com.amazon.dee.app.services.wifi.DefaultWifiService;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.dee.app.storage.GsonJsonConverter;
import com.amazon.dee.app.storage.JsonConverter;
import com.amazon.dee.app.storage.PreferencesStorage;
import com.amazon.dee.app.ui.main.IntentFactory;
import com.amazon.dee.app.ui.main.RNLogPrinter;
import com.amazon.dee.app.ui.util.CacheClearOperations;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.amazon.deecomms.core.decoupling.AlexaCommsServiceWrapper;
import com.amazon.device.messaging.ADM;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.matter.service.MatterService;
import com.amazon.matter.service.MatterServiceImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.DefaultObjectCache;
import com.dee.app.cachemanager.DiskLruByteCache;
import com.dee.app.cachemanager.Encryptor;
import com.dee.app.cachemanager.HttpResponseWrapper;
import com.dee.app.cachemanager.MarshmallowPlusAESEncryptor;
import com.dee.app.cachemanager.TwoTierLruMemoryCacheImpl;
import com.dee.app.data.DefaultElementLocalStorage;
import com.dee.app.http.CoralService;
import com.dee.app.http.HttpCoralService;
import com.dee.app.http.UrlResolver;
import com.dee.app.metrics.CacheMetricsObserver;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Named;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import rx.Scheduler;
import rx.schedulers.Schedulers;
@Module
/* loaded from: classes12.dex */
public class ServiceModule {
    public static final String DATA_CACHE = "dataCache";
    public static final String DATA_STORE = "dataStore";
    public static final String DEVICE_NAME_TEMPLATE = "deviceNameTemplate";
    public static final String ELEMENTS_DISK_IO = "ElementsDiskIO";
    public static final String ELEMENTS_DISK_SCHEDULER = "diskScheduler";
    public static final String HTTP_CACHE = "httpCache";
    public static final String LOCATION_CACHE = "LocationCache";
    private static final String SHORT_LIVED_TASK_EXECUTOR = "shortLivedTaskExecutor";

    @NonNull
    private static Encryptor getEncryptor(Context context, String str) {
        return new MarshmallowPlusAESEncryptor(context, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$provideCrashObserverRegistrar$0(Lazy lazy, final CrashObserver crashObserver) {
        crashObserver.getClass();
        ((CrashReportingService) lazy.mo358get()).addObserver(new CrashReportingService.CrashObserver() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$2xEK_8-QlB5vcc30e5oVLSXOLcM
            @Override // com.amazon.alexa.crashreporting.CrashReportingService.CrashObserver
            public final void onCrash() {
                CrashObserver.this.onCrash();
            }
        });
    }

    private boolean useFeatureServiceV2Refactor(Lazy<FeatureQuery> lazy) {
        if (lazy.mo358get() != null) {
            return lazy.mo358get().isActive(Features.FEATURE_SERVICE_V2_REFACTOR);
        }
        return false;
    }

    @Provides
    @ApplicationScope
    public AccessibilityService provideAccessibilityService(Context context, Mobilytics mobilytics) {
        return new DefaultAccessibilityService(context, mobilytics);
    }

    @Provides
    @ApplicationScope
    public RoutingRegistryAdapter provideAdapterRegistry() {
        RoutingRegistryAdapter routingRegistryAdapter = (RoutingRegistryAdapter) GeneratedOutlineSupport1.outline20(RoutingRegistryAdapter.class);
        routingRegistryAdapter.register(new ApplicationRoutingAdapter());
        return routingRegistryAdapter;
    }

    @Provides
    @ApplicationScope
    public AlexaTarazedService provideAlexaTarazedService() {
        return (AlexaTarazedService) GeneratedOutlineSupport1.outline20(AlexaTarazedService.class);
    }

    @Provides
    @ApplicationScope
    public DefaultApplicationLifecycleService provideApplicationLifecycleService(@Named("shortLivedTaskExecutor") ExecutorService executorService) {
        return new DefaultApplicationLifecycleService(executorService);
    }

    @Provides
    @ApplicationScope
    public AssetManagementService provideAssetManagementService(Context context, Lazy<EventBus> lazy, Lazy<Mobilytics> lazy2) {
        return new GetAssetURLService(lazy, lazy2, new GetAssetURLStorageService(context, Executors.newSingleThreadExecutor()), context);
    }

    @Provides
    @ApplicationScope
    public BluetoothService provideBluetoothService(Context context, MainActivityLifecycleService mainActivityLifecycleService, Lazy<Mobilytics> lazy) {
        return new DefaultBluetoothService(context, mainActivityLifecycleService, lazy);
    }

    @Provides
    @ApplicationScope
    public CacheClearOperations provideCacheClearOperations(TaskManager taskManager, IdentityService identityService, EventBus eventBus, @Named("httpCache") Cache<HttpResponseWrapper> cache, @Named("dataCache") DefaultElementLocalStorage defaultElementLocalStorage, @Named("dataStore") DefaultElementLocalStorage defaultElementLocalStorage2) {
        return new CacheClearOperations(taskManager, identityService, eventBus, cache, defaultElementLocalStorage.getNativeCache(), defaultElementLocalStorage2.getNativeCache());
    }

    @Provides
    @ApplicationScope
    public CertificateReaderService provideCertificateReaderService(Context context) {
        return new DefaultCertificateReaderService(context);
    }

    @Provides
    @ApplicationScope
    public CloudDriveService provideCloudDriveService(Context context, IdentityService identityService, AmazonCloudDriveExtendedClient amazonCloudDriveExtendedClient, PersistentStorage.Factory factory, Mobilytics mobilytics, MetricsService metricsService, TaskManager taskManager, EventBus eventBus) {
        return new AlexaCloudDriveService(context, factory, identityService, amazonCloudDriveExtendedClient, mobilytics, metricsService, taskManager, eventBus);
    }

    @Provides
    @ApplicationScope
    public CoachMarkFactory provideCoachMarkFactory() {
        return (CoachMarkFactory) GeneratedOutlineSupport1.outline20(CoachMarkFactory.class);
    }

    @Provides
    @ApplicationScope
    public CommsDynamicFeatureService provideCommsFeatureService(AlexaCommsService alexaCommsService) {
        return alexaCommsService.getCommsDynamicFeatureService();
    }

    @Provides
    @ApplicationScope
    public AlexaCommsService provideCommsService(@NonNull AlexaCommsServiceWrapper alexaCommsServiceWrapper) {
        return alexaCommsServiceWrapper;
    }

    @Provides
    @ApplicationScope
    public CommsServiceV2 provideCommsServiceV2(@NonNull AlexaCommsServiceWrapper alexaCommsServiceWrapper) {
        return alexaCommsServiceWrapper;
    }

    @Provides
    @ApplicationScope
    public ComponentBinder provideComponentFactories(Lazy<EnvironmentService> lazy, Lazy<FeatureQuery> lazy2, Lazy<IdentityService> lazy3, Lazy<PersonIdProvider> lazy4, Lazy<MainActivityLifecycleService> lazy5, Lazy<DefaultApplicationLifecycleService> lazy6, Lazy<MarketplaceService> lazy7, Lazy<com.amazon.alexa.protocols.marketplace.MarketplaceService> lazy8, Lazy<CrashMetadata> lazy9, Lazy<CrashObserverRegistrar> lazy10, Lazy<CrashReporter> lazy11, Lazy<MetricsService> lazy12, Lazy<MetricsServiceV2> lazy13, Lazy<Mobilytics> lazy14, Lazy<ModeService> lazy15, Lazy<LocationService> lazy16, Lazy<CoralService> lazy17, Lazy<JsonConverter> lazy18, Lazy<PersistentStorage.Factory> lazy19, Lazy<RoutingService> lazy20, Lazy<TTCFService> lazy21, Lazy<CertificateReaderService> lazy22, Lazy<CommsServiceV2> lazy23, Lazy<FeatureServiceV2> lazy24, Lazy<AssetManagementService> lazy25, @Named("dataStore") Lazy<DefaultElementLocalStorage> lazy26, Lazy<DialogBuilderProvider> lazy27) {
        return new ComponentBinderImpl(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18, lazy19, lazy20, lazy21, lazy22, lazy23, lazy24, lazy25, lazy26, lazy27);
    }

    @Provides
    @ApplicationScope
    public ConversationService provideConversationService(AlexaCommsService alexaCommsService) {
        return alexaCommsService.getConversationService();
    }

    @Provides
    @ApplicationScope
    public CoralService provideCoralService(Lazy<CookieManager> lazy, OkHttpClient okHttpClient, Gson gson, UrlResolver urlResolver, Lazy<IdentityService> lazy2, Lazy<AccountService> lazy3, Lazy<RoutingService> lazy4, MetricsServiceV2 metricsServiceV2, Lazy<MetricsService> lazy5, Lazy<Mobilytics> lazy6, Lazy<FeatureServiceV2> lazy7, Lazy<EnvironmentService> lazy8) {
        HttpCoralService httpCoralService = new HttpCoralService(okHttpClient, gson, urlResolver, metricsServiceV2);
        HttpCoralDefaultInterceptor httpCoralDefaultInterceptor = new HttpCoralDefaultInterceptor();
        httpCoralService.addResponseInterceptor(new HttpCoralAuthenticationResponseInterceptor(okHttpClient, lazy2, lazy3, lazy4, lazy6));
        httpCoralService.addResponseInterceptor(httpCoralDefaultInterceptor);
        httpCoralService.addRequestInterceptor(new UserAgentRequestInterceptor());
        httpCoralService.addRequestInterceptor(new AcceptHeaderRequestInterceptor());
        httpCoralService.addRequestInterceptor(new TimestampHeaderRequestInterceptor());
        httpCoralService.addRequestInterceptor(new CookieAuthenticationRequestInterceptor(lazy, lazy2, lazy6, lazy7, lazy8));
        httpCoralService.addRequestInterceptor(new AcceptLanguageRequestInterceptor());
        httpCoralService.addRequestInterceptor(httpCoralDefaultInterceptor);
        return httpCoralService;
    }

    @Provides
    @ApplicationScope
    public CrashMetadata provideCrashMetadata(final Lazy<CrashReportingService> lazy) {
        lazy.getClass();
        return new CrashReportingMetadata(new Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$43jj9zLqAXlAH1PdLEJksY_G79M
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return (CrashReportingService) Lazy.this.mo358get();
            }
        });
    }

    @Provides
    @ApplicationScope
    public CrashObserverRegistrar provideCrashObserverRegistrar(final Lazy<CrashReportingService> lazy) {
        return new CrashObserverRegistrar() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$ServiceModule$G48-ZL1cV2I4HtxBhuRdcnWWvHA
            @Override // com.amazon.alexa.crashreporting.api.CrashObserverRegistrar
            public final void addObserver(CrashObserver crashObserver) {
                ServiceModule.lambda$provideCrashObserverRegistrar$0(Lazy.this, crashObserver);
            }
        };
    }

    @Provides
    @ApplicationScope
    public CrashReporter provideCrashReporter(final Lazy<CrashReportingService> lazy) {
        return new CrashReporter() { // from class: com.amazon.dee.app.dependencies.ServiceModule.1
            private final Random random = new Random();

            @Override // com.amazon.alexa.crashreporting.api.CrashReporter
            public void reportNonFatal(Throwable th) {
                reportNonFatal(th, 1);
            }

            @Override // com.amazon.alexa.crashreporting.api.CrashReporter
            public void reportNonFatal(Throwable th, int i) {
                if (this.random.nextInt(i) == 0) {
                    ((CrashReportingService) lazy.mo358get()).reportNonFatal(th);
                }
            }
        };
    }

    @Provides
    @ApplicationScope
    public CrashReportingService provideCrashReportingService(Context context) {
        return DefaultCrashReportingServiceFactory.newInstance(context);
    }

    @Provides
    @ApplicationScope
    @Named(DATA_CACHE)
    public DefaultElementLocalStorage provideDataCache(Context context, MetricsServiceV2 metricsServiceV2, @Named("shortLivedTaskExecutor") ExecutorService executorService, @Named("diskScheduler") Scheduler scheduler) {
        DefaultObjectCache defaultObjectCache = new DefaultObjectCache(AppDataCacheEntry.class, new TwoTierLruMemoryCacheImpl(10), new DiskLruByteCache(context, DATA_CACHE, 1, 10, executorService), getEncryptor(context, DATA_CACHE), executorService, scheduler);
        defaultObjectCache.getCacheEvents().observeOn(Schedulers.io()).subscribe(new CacheMetricsObserver(metricsServiceV2));
        return new DefaultElementLocalStorage(defaultObjectCache);
    }

    @Provides
    @ApplicationScope
    public DataRegionEnvironmentService provideDataRegionEnvironmentService(Context context, EventBus eventBus, UserIdentityStorage userIdentityStorage, PersistentEndpointsStorage persistentEndpointsStorage, Lazy<CoralService> lazy, Lazy<IdentityService> lazy2, Lazy<Mobilytics> lazy3) {
        return new DataRegionEnvironmentService(context, eventBus, userIdentityStorage, persistentEndpointsStorage, lazy, lazy2, lazy3);
    }

    @Provides
    @ApplicationScope
    @Named(DATA_STORE)
    public DefaultElementLocalStorage provideDataStore(Context context, MetricsServiceV2 metricsServiceV2, @Named("shortLivedTaskExecutor") ExecutorService executorService, @Named("diskScheduler") Scheduler scheduler) {
        DefaultObjectCache defaultObjectCache = new DefaultObjectCache(AppDataCacheEntry.class, new TwoTierLruMemoryCacheImpl(10), new DiskLruByteCache(context, DATA_STORE, 1, DefaultObjectCache.MAX_SIZE, executorService), getEncryptor(context, DATA_STORE), executorService, scheduler);
        defaultObjectCache.getCacheEvents().observeOn(Schedulers.io()).subscribe(new CacheMetricsObserver(metricsServiceV2));
        return new DefaultElementLocalStorage(defaultObjectCache);
    }

    @Provides
    @ApplicationScope
    public DefaultFeatureServiceV2 provideDefaultFeatureServiceV2(Context context, Lazy<EventBus> lazy, Lazy<CoralService> lazy2, Lazy<Mobilytics> lazy3) {
        return new DefaultFeatureServiceV2(lazy, new FeatureServiceStorage(context, Executors.newSingleThreadExecutor()), lazy2, lazy3);
    }

    @Provides
    @ApplicationScope
    public DeviceInformation provideDeviceInformation() {
        return (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
    }

    @Provides
    @ApplicationScope
    public DialogBuilderProvider provideDialogBuilderProvider() {
        return new com.amazon.alexa.dialog.impl.DialogBuilderProvider();
    }

    @Provides
    @ApplicationScope
    @Named(ELEMENTS_DISK_SCHEDULER)
    public Scheduler provideDiskScheduler() {
        return Schedulers.from(Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat(ELEMENTS_DISK_IO).build()));
    }

    @Provides
    @ApplicationScope
    public MetricsServiceV2 provideElementsMetricsService(MetricsService metricsService) {
        return new ElementsMetricsService(metricsService);
    }

    @Provides
    @ApplicationScope
    public EnvironmentService provideEnvironmentService(PfmEnvironmentService pfmEnvironmentService, DataRegionEnvironmentService dataRegionEnvironmentService, Lazy<IdentityService> lazy) {
        return new DefaultEnvironmentService(pfmEnvironmentService, dataRegionEnvironmentService, lazy);
    }

    @Provides
    @ApplicationScope
    public FeatureServiceV2 provideFeatureServiceV2(PlatformFeatureServiceV2 platformFeatureServiceV2) {
        return platformFeatureServiceV2;
    }

    @Provides
    @ApplicationScope
    public HeaderCacheService provideHeaderCacheService() {
        return new DefaultHeaderCacheService();
    }

    @Provides
    @ApplicationScope
    @Named(HTTP_CACHE)
    public Cache<HttpResponseWrapper> provideHttpCache(Context context, MetricsServiceV2 metricsServiceV2, @Named("shortLivedTaskExecutor") ExecutorService executorService, @Named("diskScheduler") Scheduler scheduler) {
        DefaultObjectCache defaultObjectCache = new DefaultObjectCache(HttpResponseWrapper.class, new TwoTierLruMemoryCacheImpl(10), new DiskLruByteCache(context, "httpData", 1, 10, executorService), getEncryptor(context, "httpData"), executorService, scheduler);
        defaultObjectCache.getCacheEvents().observeOn(Schedulers.io()).subscribe(new CacheMetricsObserver(metricsServiceV2));
        return defaultObjectCache;
    }

    @Provides
    @ApplicationScope
    public IntentFactory provideIntentFactory(Context context) {
        return new IntentFactory(context);
    }

    @Provides
    @ApplicationScope
    public JsonConverter provideJsonConverter(Gson gson) {
        return new GsonJsonConverter(gson);
    }

    @Provides
    @ApplicationScope
    public LatencyInfra provideLatencyInfra() {
        return (LatencyInfra) GeneratedOutlineSupport1.outline20(LatencyInfra.class);
    }

    @Provides
    @ApplicationScope
    @Named(LOCATION_CACHE)
    public Cache<AppDataCacheEntry> provideLocationCache(Context context, MetricsServiceV2 metricsServiceV2, @Named("shortLivedTaskExecutor") ExecutorService executorService, @Named("diskScheduler") Scheduler scheduler) {
        DefaultObjectCache defaultObjectCache = new DefaultObjectCache(AppDataCacheEntry.class, new TwoTierLruMemoryCacheImpl(1), new DiskLruByteCache(context, LOCATION_CACHE, -1, 1, executorService), getEncryptor(context, LOCATION_CACHE), executorService, scheduler);
        defaultObjectCache.getCacheEvents().observeOn(Schedulers.io()).subscribe(new CacheMetricsObserver(metricsServiceV2));
        return defaultObjectCache;
    }

    @Provides
    @ApplicationScope
    public LocationService provideLocationService(Context context, LocationProvider locationProvider, OkHttpClient okHttpClient, @Named("LocationCache") Cache<AppDataCacheEntry> cache) {
        return LocationServiceFactory.create(context, locationProvider, okHttpClient, cache);
    }

    @Provides
    @ApplicationScope
    public MainActivityLifecycleService provideMainActivityLifecycleService(@Named("shortLivedTaskExecutor") ExecutorService executorService) {
        return new MainActivityLifecycleService(executorService);
    }

    @Provides
    @ApplicationScope
    public MarketplaceService provideMarketplaceService(PersistentStorage.Factory factory, Lazy<CoralService> lazy) {
        return new PreferredMarketplaceService(factory, lazy);
    }

    @Provides
    @ApplicationScope
    public MatterService provideMatterService(EventBus eventBus, Context context) {
        return new MatterServiceImpl(eventBus, context);
    }

    @Provides
    @ApplicationScope
    public MessagingService provideMessagingService(Context context, CoralService coralService, IdentityService identityService, Provider<ADM> provider, Provider<FirebaseInstanceId> provider2, Lazy<MessagingHandler> lazy, MessagingSettings messagingSettings, CommsManager commsManager, CommsDeviceSupport commsDeviceSupport, MetricsService metricsService, NetworkService networkService, DeviceInformation deviceInformation, MessageCrypto messageCrypto, PersonIdProvider personIdProvider, EventBus eventBus) {
        if (AmazonDeviceMessagingService.isSupported(context)) {
            AmazonDeviceMessagingService amazonDeviceMessagingService = new AmazonDeviceMessagingService(context, provider.mo10268get(), deviceInformation, coralService, identityService, lazy, messagingSettings, commsManager, commsDeviceSupport, metricsService, networkService, personIdProvider, eventBus);
            amazonDeviceMessagingService.initialize();
            return amazonDeviceMessagingService;
        } else if (FirebaseCloudMessagingService.isSupported(context)) {
            FirebaseCloudMessagingService firebaseCloudMessagingService = new FirebaseCloudMessagingService(context, provider2.mo10268get(), coralService, identityService, lazy, messagingSettings, commsManager, commsDeviceSupport, metricsService, networkService, deviceInformation, messageCrypto, personIdProvider, eventBus);
            firebaseCloudMessagingService.initialize();
            return firebaseCloudMessagingService;
        } else {
            return new UnsupportedMessagingService();
        }
    }

    @Provides
    @ApplicationScope
    public MetricsService provideMetricsService(EnvironmentService environmentService, PersistentStorage.Factory factory, CrashMetadata crashMetadata, Lazy<PreloadAttributionManager> lazy, Lazy<DCMMetricsConnector> lazy2, Lazy<KinesisMetricsConnector> lazy3, Lazy<Mobilytics> lazy4, Lazy<MobilyticsEventFactory> lazy5, Lazy<FeatureQuery> lazy6) {
        return new FeatureCheckedMetricsService(environmentService, factory, crashMetadata, lazy, lazy2, lazy3, lazy4, lazy5, lazy6);
    }

    @Provides
    @ApplicationScope
    public NetworkService provideNetworkService(Context context) {
        return (NetworkService) GeneratedOutlineSupport1.outline20(NetworkService.class);
    }

    @Provides
    @ApplicationScope
    public PersistentEndpointsStorage providePersistentEndpointsStorage(PersistentStorage.Factory factory) {
        return new PersistentEndpointsStorage(factory);
    }

    @Provides
    @ApplicationScope
    public PersistentStorage.Factory providePersistentStorageFactory(Context context, JsonConverter jsonConverter) {
        return new PreferencesStorage.Factory(context, jsonConverter);
    }

    @Provides
    @ApplicationScope
    public PfmEnvironmentService providePfmEnvironmentService(Context context, DeviceInformation deviceInformation, UserIdentityStorage userIdentityStorage) {
        return new PfmEnvironmentService(context, deviceInformation, userIdentityStorage);
    }

    @Provides
    @ApplicationScope
    public PlatformFeatureServiceV2 providePlatformFeatureServiceV2(Lazy<FeatureQuery> lazy, Lazy<AlexaMobileAndroidFeatureServiceImpl> lazy2, DefaultFeatureServiceV2 defaultFeatureServiceV2) {
        return useFeatureServiceV2Refactor(lazy) ? lazy2.mo358get() : defaultFeatureServiceV2;
    }

    @Provides
    @ApplicationScope
    public com.amazon.alexa.protocols.marketplace.MarketplaceService provideProtocolsMarketplaceService(PersistentStorage.Factory factory, Lazy<CoralService> lazy) {
        return new ProtocolsPreferredMarketplaceService(factory, lazy);
    }

    @Provides
    @ApplicationScope
    public RoutingService provideRoutingService(Context context, RoutingRegistry routingRegistry, MetricsService metricsService, RoutingRegistryAdapter routingRegistryAdapter, CrashMetadata crashMetadata, FeatureServiceV2 featureServiceV2, Lazy<IdentityService> lazy, Lazy<EventBus> lazy2) {
        return new DefaultRoutingService(context, routingRegistry, metricsService, routingRegistryAdapter, crashMetadata, featureServiceV2, lazy, lazy2);
    }

    @Provides
    @ApplicationScope
    @Named(SHORT_LIVED_TASK_EXECUTOR)
    public ExecutorService provideShortLivedTaskExecutor(TaskManager taskManager) {
        return taskManager.getExecutor(0);
    }

    @Provides
    @ApplicationScope
    public TaskManager provideTaskManager() {
        return (TaskManager) GeneratedOutlineSupport1.outline20(TaskManager.class);
    }

    @Provides
    @ApplicationScope
    public UrlResolver provideUrlResolver(EnvironmentService environmentService) {
        return new EnvironmentUrlResolver(environmentService);
    }

    @Provides
    @ApplicationScope
    public UserAgentService provideUserAgentService() {
        return $$Lambda$hCvHCDm2zz03zLstUdXyimC7n4.INSTANCE;
    }

    @Provides
    @ApplicationScope
    public RoutingViewService provideViewService(RoutingService routingService, MetricsService metricsService, RoutingRegistryAdapter routingRegistryAdapter, RoutingRegistry routingRegistry, Lazy<IdentityService> lazy, final Lazy<TTCFRoutingDelegate> lazy2, CrashMetadata crashMetadata, FeatureServiceV2 featureServiceV2) {
        DefaultRoutingViewService defaultRoutingViewService = new DefaultRoutingViewService(routingService, metricsService, routingRegistryAdapter, routingRegistry, lazy, crashMetadata, new DefaultRoutingViewService.RoutingDelegate() { // from class: com.amazon.dee.app.dependencies.ServiceModule.2
            @Override // com.amazon.alexa.routing.DefaultRoutingViewService.RoutingDelegate
            public void routeDidFinish(Route route) {
                ((TTCFRoutingDelegate) lazy2.mo358get()).routeDidFinish();
            }

            @Override // com.amazon.alexa.routing.DefaultRoutingViewService.RoutingDelegate
            public void routeDidStart(Route route) {
                TTCFRoute routeDidStart = LatencyService.routeDidStart(route);
                if (routeDidStart == null) {
                    routeDidStart = new TTCFRoute() { // from class: com.amazon.dee.app.dependencies.ServiceModule.2.1
                    };
                }
                ((TTCFRoutingDelegate) lazy2.mo358get()).routeDidStart(routeDidStart);
            }
        }, featureServiceV2);
        defaultRoutingViewService.start();
        return defaultRoutingViewService;
    }

    @Provides
    @ApplicationScope
    public WifiService provideWifiService(Context context, EnvironmentService environmentService, Mobilytics mobilytics) {
        return new DefaultWifiService(context, environmentService, mobilytics);
    }

    @Provides
    @ApplicationScope
    public AlexaMobileAndroidFeatureServiceImpl providesAlexaMobileAndroidFeatureServiceImpl(DaggerInitializer daggerInitializer) {
        return (AlexaMobileAndroidFeatureServiceImpl) daggerInitializer.getFeatureServiceComponent().getFeatureService();
    }

    @Provides
    @ApplicationScope
    public DaggerInitializer providesFsv2DaggerInitializer(Context context, Lazy<OkHttpClient> lazy, Lazy<EnvironmentService> lazy2, Lazy<EventBus> lazy3, Lazy<CoralService> lazy4, Lazy<IdentityService> lazy5, Lazy<CookieManager> lazy6, Lazy<AccountService> lazy7, Lazy<RoutingService> lazy8, Lazy<Mobilytics> lazy9, Lazy<FeatureQuery> lazy10, Lazy<FeatureServiceV2> lazy11) {
        CookieAuthenticationInterceptor cookieAuthenticationInterceptor = new CookieAuthenticationInterceptor(lazy6, lazy5, lazy11);
        return new DaggerInitializer(context, lazy.mo358get().newBuilder().addInterceptor(cookieAuthenticationInterceptor).addInterceptor(new CookieRefreshInterceptor(lazy5, lazy7, lazy8, lazy9)).addInterceptor(new AcceptContentTypeInterceptor()).addInterceptor(new AcceptLanguageInterceptor()).addInterceptor(new ClientTimestampInterceptor()).addInterceptor(new NetworkLatencyInterceptor()).addInterceptor(new UserAgentInterceptor()).build(), lazy2, lazy3, lazy9, lazy4, lazy10);
    }

    @Provides
    @ApplicationScope
    public FeatureServiceConfiguration providesFsv2TestConfiguration(DaggerInitializer daggerInitializer) {
        return daggerInitializer.getFeatureServiceComponent().getTestConfiguration();
    }

    @Provides
    @ApplicationScope
    public RNLogPrinter providesRNLogPrinter(Lazy<RoutingService> lazy) {
        return new RNLogPrinter(lazy);
    }
}
