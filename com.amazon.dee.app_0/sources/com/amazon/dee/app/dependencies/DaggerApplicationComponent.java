package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.webkit.CookieManager;
import com.amazon.alexa.assetManagementService.api.AssetManagementService;
import com.amazon.alexa.component.api.ServiceLifecycle;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashObserverRegistrar;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.delegatedidentity.TokenAccessor;
import com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement;
import com.amazon.alexa.delegatedidentity.storage.LocalAndroidKeyValueStore;
import com.amazon.alexa.delegatedidentity.storage.TokenEncryptor;
import com.amazon.alexa.delegatedidentity.storage.TokenStorage;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.dialog.api.DialogBuilderProvider;
import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.enrollment.module.app.EnrollmentModule;
import com.amazon.alexa.enrollment.module.app.EnrollmentModule_ProvideEnrollmentRoutingAdapterFactory;
import com.amazon.alexa.enrollment.module.app.EnrollmentModule_ProvideEnrollmentServiceFactory;
import com.amazon.alexa.enrollment.module.app.EnrollmentModule_ProvideKidsEnrollmentRoutingAdapterFactory;
import com.amazon.alexa.enrollment.route.EnrollmentRoutingAdapter;
import com.amazon.alexa.enrollment.route.KidsEnrollmentRoutingAdapter;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.feature.provider.api.FeatureStore;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.dependencies.DaggerInitializer;
import com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl;
import com.amazon.alexa.featureservice.service.DefaultFeatureServiceV2;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import com.amazon.alexa.identity.ApesCallerInterface;
import com.amazon.alexa.identity.MAPAccountRegistrationService;
import com.amazon.alexa.identity.MAPAccountUpgradeService;
import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.UserIdentityMapper;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.AccountUpgradeAuthority;
import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentityRepository;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.identity.api.UserProfileManager;
import com.amazon.alexa.location.LocationProvider;
import com.amazon.alexa.location.LocationService;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.MobilyticsReporter;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.permissions.DefaultPermissionsService;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.photos.PhotosAppInfoProvider;
import com.amazon.alexa.photos.PhotosFeatureGuardian;
import com.amazon.alexa.photos.UploadBundleManager;
import com.amazon.alexa.photos.api.PhotosChooser;
import com.amazon.alexa.photos.api.PhotosUploader;
import com.amazon.alexa.photos.hva.HVAManager;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.util.SystemUtility;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.presence.battery.BatteryOptimizationRoutingAdapter;
import com.amazon.alexa.protocols.datastore.DataStoreService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureConstraints;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.HomeViewDelegate;
import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.RouteWatcher;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.alexa.sendtoapp.notification.SendToAppMessagingReceiverModule;
import com.amazon.alexa.sendtoapp.notification.SendToAppMessagingReceiverModule_ProvidesSendToAppMessagingReceiverFactory;
import com.amazon.alexa.tarazed.api.AlexaTarazedService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.ttcf.TTCFService;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import com.amazon.alexa.ttcf.api.TTCFRoutingDelegate;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.amazon.alexa.viewmanagement.impl.ReactNativeViewManager;
import com.amazon.alexa.viewmanagement.impl.ViewControllerFactoryProducer;
import com.amazon.alexa.viewmanagement.impl.ViewControllerFragment;
import com.amazon.alexa.viewmanagement.impl.ViewControllerFragment_MembersInjector;
import com.amazon.alexa.viewmanagement.impl.ViewManagerDelegate;
import com.amazon.alexa.viewmanagement.impl.ViewManagerRoutingAdapter;
import com.amazon.alexa.viewmanagement.impl.ViewPresenter;
import com.amazon.alexa.voice.app.LatencyReportingDelegate;
import com.amazon.alexa.voice.handsfree.HandsFreeRoutingAdapter;
import com.amazon.alexa.voice.handsfree.HandsFreeSettingsMetricRecorder;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.alexa.voice.handsfree.settings.providers.SettingsSetupFlowProvider;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter;
import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.dee.app.elements.AlexaNativeModuleCallExceptionHandler;
import com.amazon.dee.app.elements.ElementsRoutingAdapter;
import com.amazon.dee.app.elements.ReactBridgeMetrics;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.elements.ReactFeatureControllerTransition;
import com.amazon.dee.app.elements.ReactFeatureControllerTransition_MembersInjector;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.elements.ReactFeatureViewController;
import com.amazon.dee.app.elements.ReactRouteRegistry;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import com.amazon.dee.app.framework.MainApplicationImplementation;
import com.amazon.dee.app.framework.MainApplicationImplementation_MembersInjector;
import com.amazon.dee.app.services.accessibility.AccessibilityService;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.amazon.dee.app.services.appreviewrequest.AppReviewRequestService;
import com.amazon.dee.app.services.bluetooth.BluetoothService;
import com.amazon.dee.app.services.clouddrive.CloudDriveService;
import com.amazon.dee.app.services.clouddrive.MAPAuthenticatedURLConnectionFactory;
import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.datastore.DataStoreHelper;
import com.amazon.dee.app.services.environment.DataRegionEnvironmentService;
import com.amazon.dee.app.services.environment.PersistentEndpointsStorage;
import com.amazon.dee.app.services.environment.PfmEnvironmentService;
import com.amazon.dee.app.services.export.ComponentBinder;
import com.amazon.dee.app.services.header.HeaderCacheService;
import com.amazon.dee.app.services.messaging.CryptoFactory;
import com.amazon.dee.app.services.messaging.MessageCrypto;
import com.amazon.dee.app.services.messaging.MessagingHandler;
import com.amazon.dee.app.services.messaging.MessagingSettings;
import com.amazon.dee.app.services.messaging.MessagingSettingsMetricsHandler;
import com.amazon.dee.app.services.metrics.DCMMetricsConnector;
import com.amazon.dee.app.services.metrics.KinesisMetricsConnector;
import com.amazon.dee.app.services.metrics.kinesis.KinesisManager;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundService;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundServiceUrlResolver;
import com.amazon.dee.app.services.photos.PhotoService;
import com.amazon.dee.app.services.photos.PhotoServiceFactory;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.amazon.dee.app.services.tcomm.TCommServiceManager;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.services.testing.TestConfigurationService;
import com.amazon.dee.app.services.toolbar.ToolbarService;
import com.amazon.dee.app.services.toolbar.ToolbarWatcher;
import com.amazon.dee.app.services.useragent.UserAgentService;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.dee.app.storage.JsonConverter;
import com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity;
import com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity_MembersInjector;
import com.amazon.dee.app.ui.clouddrive.ViewBoxFragment;
import com.amazon.dee.app.ui.clouddrive.ViewBoxFragment_MembersInjector;
import com.amazon.dee.app.ui.comms.CommsViewModel;
import com.amazon.dee.app.ui.external.ExternalUIViewModel;
import com.amazon.dee.app.ui.external.ExternalUIViewModel_MembersInjector;
import com.amazon.dee.app.ui.fullscreentakeover.FullScreenTakeoverViewModel;
import com.amazon.dee.app.ui.main.AuthenticationExceptionHandler;
import com.amazon.dee.app.ui.main.IntentFactory;
import com.amazon.dee.app.ui.main.MainActivity;
import com.amazon.dee.app.ui.main.MainActivity_MembersInjector;
import com.amazon.dee.app.ui.main.MainBindingThemeSetter;
import com.amazon.dee.app.ui.main.MainHandler;
import com.amazon.dee.app.ui.main.MainViewModel;
import com.amazon.dee.app.ui.main.MainViewModel_MembersInjector;
import com.amazon.dee.app.ui.main.RNLogPrinter;
import com.amazon.dee.app.ui.main.TabSelectionAnimator;
import com.amazon.dee.app.ui.main.ThemeRecorder;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel;
import com.amazon.dee.app.ui.preload.PreloadAttributionUIManager;
import com.amazon.dee.app.ui.util.CacheClearOperations;
import com.amazon.dee.app.ui.util.LocationPermissionMetricHelper;
import com.amazon.dee.app.ui.util.SonarUrlHandler;
import com.amazon.dee.app.ui.web.AccessibilityInfoBridge;
import com.amazon.dee.app.ui.web.AccountManagementBridge;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.dee.app.ui.web.AlexaWebView;
import com.amazon.dee.app.ui.web.AlexaWebView_MembersInjector;
import com.amazon.dee.app.ui.web.AppCacheBridge;
import com.amazon.dee.app.ui.web.AppInfoBridge;
import com.amazon.dee.app.ui.web.AppLauncherBridge;
import com.amazon.dee.app.ui.web.AppLayoutBridge;
import com.amazon.dee.app.ui.web.AppReloadBridge;
import com.amazon.dee.app.ui.web.AudioBridge;
import com.amazon.dee.app.ui.web.EventBusWebViewBridge;
import com.amazon.dee.app.ui.web.ExternalUILauncherBridge;
import com.amazon.dee.app.ui.web.FeedbackBridge;
import com.amazon.dee.app.ui.web.HeaderInfoBridge;
import com.amazon.dee.app.ui.web.JavaScriptBridgeOrchestrator;
import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptPlayer;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.MenuSettingsBridge;
import com.amazon.dee.app.ui.web.MetricsServiceBridge;
import com.amazon.dee.app.ui.web.NativeHostBridge;
import com.amazon.dee.app.ui.web.NativeLocalStorageBridge;
import com.amazon.dee.app.ui.web.NavBarToggleBridge;
import com.amazon.dee.app.ui.web.NavigationBridge;
import com.amazon.dee.app.ui.web.NetworkEventBridge;
import com.amazon.dee.app.ui.web.NotificationServiceBridge;
import com.amazon.dee.app.ui.web.OOBEBridge;
import com.amazon.dee.app.ui.web.OrientationBridge;
import com.amazon.dee.app.ui.web.PermissionsBridge;
import com.amazon.dee.app.ui.web.TachyonIdentityBridge;
import com.amazon.dee.app.ui.web.TachyonSettingsBridge;
import com.amazon.dee.app.ui.web.VideoPlaybackBridge;
import com.amazon.dee.app.ui.web.WebApp;
import com.amazon.dee.app.ui.web.WebAppMessagingReceiver;
import com.amazon.dee.app.ui.web.WebApp_MembersInjector;
import com.amazon.dee.app.ui.web.WebNavigator;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import com.amazon.dee.app.ui.web.WebViewJavaScriptLoader;
import com.amazon.dee.app.ui.web.WifiBridge;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.api.CommsUIDelegateBase;
import com.amazon.deecomms.common.ui.main.ConversationRoutingAdapter;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.ConversationRouting;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.conversation.FireOSDirectiveHandlerService;
import com.amazon.deecomms.conversation.FireOSDirectiveHandlerService_MembersInjector;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.amazon.deecomms.core.decoupling.AlexaCommsServiceWrapper;
import com.amazon.deecomms.services.ConversationUIService;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import com.amazon.deecomms.ui.util.CommsRoutingHelper;
import com.amazon.device.messaging.ADM;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.matter.service.MatterService;
import com.amazon.regulator.Router;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.HttpResponseWrapper;
import com.dee.app.data.DefaultElementLocalStorage;
import com.dee.app.data.reactnative.ElementsDataService;
import com.dee.app.http.CoralService;
import com.dee.app.http.UrlResolver;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.google.android.play.core.review.ReviewManager;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.MemoizedSentinel;
import dagger.internal.Preconditions;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import rx.Scheduler;
/* loaded from: classes12.dex */
public final class DaggerApplicationComponent implements ApplicationComponent {
    private volatile Object aDM;
    private volatile Object accessibilityService;
    private volatile Object accountConfiguration;
    private volatile Object accountService;
    private volatile Object accountUpgradeAuthority;
    private volatile Object accountUpgradeService;
    private volatile Object alexaCommsService;
    private volatile Object alexaCommsServiceWrapper;
    private AlexaDeviceBackgroundImageModule alexaDeviceBackgroundImageModule;
    private volatile Object alexaMobileAndroidFeatureServiceImpl;
    private volatile Object alexaPhotosBackgroundService;
    private volatile Object alexaPhotosBackgroundServiceUrlResolver;
    private volatile Object alexaTarazedService;
    private volatile Object amazonCloudDriveExtendedClient;
    private AmazonMessagingModule amazonMessagingModule;
    private volatile Object apesCallerInterface;
    private volatile Object appSessionClient;
    private volatile Object application;
    private ApplicationModule applicationModule;
    private volatile Object assetManagementService;
    private volatile Object backgroundImageService;
    private volatile Object bluetoothService;
    private volatile Object cacheClearOperations;
    private volatile Object certificateReaderService;
    private volatile Object clientConfiguration;
    private volatile Object cloudDriveMetrics;
    private CloudDriveModule cloudDriveModule;
    private volatile Object cloudDriveService;
    private volatile Object commsDeviceSupport;
    private volatile Object commsManager;
    private CommsModule commsModule;
    private volatile Object commsServiceV2;
    private volatile Object componentBinder;
    private volatile Object context;
    private volatile Object conversationService;
    private volatile Object cookieManager;
    private volatile Object coralService;
    private volatile Object crashMetadata;
    private volatile Object crashObserverRegistrar;
    private volatile Object crashReporter;
    private volatile Object crashReportingService;
    private volatile Object cryptoFactory;
    private volatile Object daggerInitializer;
    private volatile Object dataRegionEnvironmentService;
    private volatile Object dataStoreHelper;
    private DataStoreModule dataStoreModule;
    private volatile Object dataStoreService;
    private volatile Object dataStoreService2;
    private volatile Object defaultApplicationLifecycleService;
    private volatile Object defaultFeatureServiceV2;
    private volatile Object delegatedTokenManagement;
    private volatile Object deviceInformation;
    private volatile Object dialogBuilderProvider;
    private DriveModeApplicationModule driveModeApplicationModule;
    private volatile Object driveModeService;
    private volatile Object endpointsCache;
    private EnrollmentModule enrollmentModule;
    private EntertainmentModule entertainmentModule;
    private volatile Object environmentService;
    private volatile Object eventBus;
    private volatile Object eventBusMessagingReceiver;
    private EventBusModule eventBusModule;
    private volatile Object factory;
    private volatile Object featureConstraints;
    private volatile Object featureFilter;
    private volatile Object featureFilter2;
    private volatile Object featureQuery;
    private volatile Object featureServiceConfiguration;
    private volatile Object featureServiceV2;
    private volatile Object featureStore;
    private FeaturesModule featuresModule;
    private volatile Object firebaseInstanceId;
    private GoogleApiModule googleApiModule;
    private volatile Object gson;
    private volatile Object hVAManager;
    private volatile Object headerCacheService;
    private IdentityModule identityModule;
    private volatile Object identityPreferencesProvider;
    private volatile Object identityService;
    private volatile Object intentFactory;
    private volatile Object jsonConverter;
    private volatile Object kinesisEventClient;
    private volatile Object kinesisManager;
    private KinesisMetricsModule kinesisMetricsModule;
    private volatile Object latencyInfra;
    private volatile Object latencyReportingDelegate;
    private volatile Object localAndroidKeyValueStore;
    private LocationModule locationModule;
    private volatile Object locationProvider;
    private volatile Object locationService;
    private volatile Object mAPAccountManager;
    private volatile Object mAPAccountRegistrationService;
    private volatile Object mAPAccountUpgradeService;
    private volatile Object mAPAuthenticatedURLConnectionFactory;
    private volatile Object mAPIdentityService;
    private volatile Object mainActivityLifecycleService;
    private volatile Object marketplaceService;
    private volatile Object marketplaceService2;
    private volatile Object matterService;
    private volatile Object messageCrypto;
    private volatile Object messagingHandler;
    private MessagingModule messagingModule;
    private volatile Object messagingReceiver;
    private volatile Object messagingReceiver2;
    private volatile Object messagingReceiver3;
    private volatile Object messagingReceiver4;
    private volatile Object messagingService;
    private volatile Object messagingSettings;
    private volatile Object messagingSettingsMetricsHandler;
    private MetricsModule metricsModule;
    private volatile Object metricsService;
    private volatile Object metricsServiceV2;
    private volatile Object mobilytics;
    private volatile Object mobilyticsConfiguration;
    private volatile Object mobilyticsEventFactory;
    private MobilyticsModule mobilyticsModule;
    private volatile Object mobilyticsReporter;
    private ModeModule modeModule;
    private volatile Object modeService;
    private volatile Object namedAWSCredentialsProvider;
    private volatile Object namedCacheOfAppDataCacheEntry;
    private volatile Object namedCacheOfHttpResponseWrapper;
    private volatile Object namedDefaultElementLocalStorage;
    private volatile Object namedDefaultElementLocalStorage2;
    private volatile Object namedExecutorService;
    private volatile Object namedScheduler;
    private volatile Object namedString;
    private NetworkModule networkModule;
    private volatile Object networkService;
    private volatile Object okHttpClient;
    private volatile Object persistentEndpointsStorage;
    private volatile Object personIdProvider;
    private volatile Object pfmEnvironmentService;
    private volatile Object photoService;
    private volatile Object photoServiceFactory;
    private PhotoServiceModule photoServiceModule;
    private volatile Object photosAppInfoProvider;
    private volatile Object photosChooser;
    private volatile Object photosFeatureGuardian;
    private volatile Object photosUploader;
    private volatile Object platformFeatureServiceV2;
    private volatile Object playerCardUpdater;
    private volatile Object preloadAttributionManager;
    private PreloadAttributionModule preloadAttributionModule;
    private volatile Object preloadAttributionUIManager;
    private volatile Provider<ADM> provideADMProvider;
    private volatile Provider<MAPAccountRegistrationService> provideAccountRegistrationServiceProvider;
    private volatile Provider<AccountService> provideAccountServiceProvider;
    private volatile Provider<AccountUpgradeService> provideAccountUpgradeServiceProvider;
    private volatile Provider<AlexaTarazedService> provideAlexaTarazedServiceProvider;
    private volatile Provider<AmazonCloudDriveExtendedClient> provideAmazonCloudDriveExtendedClientProvider;
    private volatile Provider<DefaultApplicationLifecycleService> provideApplicationLifecycleServiceProvider;
    private volatile Provider<AssetManagementService> provideAssetManagementServiceProvider;
    private volatile Provider<BackgroundImageService> provideBackgroundImageServiceProvider;
    private volatile Provider<CertificateReaderService> provideCertificateReaderServiceProvider;
    private volatile Provider<CloudDriveMetrics> provideCloudDriveMetricsProvider;
    private volatile Provider<CommsManager> provideCommsManagerProvider;
    private volatile Provider<CommsServiceV2> provideCommsServiceV2Provider;
    private volatile Provider<Context> provideContextProvider;
    private volatile Provider<ConversationService> provideConversationServiceProvider;
    private volatile Provider<CookieManager> provideCookieManagerProvider;
    private volatile Provider<CoralService> provideCoralServiceProvider;
    private volatile Provider<CrashMetadata> provideCrashMetadataProvider;
    private volatile Provider<CrashObserverRegistrar> provideCrashObserverRegistrarProvider;
    private volatile Provider<CrashReporter> provideCrashReporterProvider;
    private volatile Provider<CrashReportingService> provideCrashReportingServiceProvider;
    private volatile Provider<DCMMetricsConnector> provideDCMMetricsConnectorProvider;
    private volatile Provider<DefaultElementLocalStorage> provideDataStoreProvider;
    private volatile Provider<DeviceInformation> provideDeviceInformationProvider;
    private volatile Provider<DialogBuilderProvider> provideDialogBuilderProvider;
    private volatile Provider<DriveModeService> provideDriveModeServiceProvider;
    private volatile Provider<MetricsServiceV2> provideElementsMetricsServiceProvider;
    private volatile Provider<EnrollmentGateway> provideEnrollmentServiceProvider;
    private volatile Provider<EnvironmentService> provideEnvironmentServiceProvider;
    private volatile Provider<EventBus> provideEventBusProvider;
    private volatile Provider<FeatureQuery> provideFeatureQueryProvider;
    private volatile Provider<FeatureServiceV2> provideFeatureServiceV2Provider;
    private volatile Provider<FeatureStore> provideFeatureStoreProvider;
    private volatile Provider<OkHttpClient> provideHttpClientProvider;
    private volatile Provider<IdentityService> provideIdentityServiceProvider;
    private volatile Provider<FirebaseInstanceId> provideInstanceIDProvider;
    private volatile Provider<JsonConverter> provideJsonConverterProvider;
    private volatile Provider<KinesisEventClient> provideKinesisEventClientProvider;
    private volatile Provider<KinesisMetricsConnector> provideKinesisMetricsConnectorProvider;
    private volatile Provider<AppSessionClient> provideKinesisSessionClientProvider;
    private volatile Provider<LatencyReportingDelegate> provideLatencyReportingDelegateProvider;
    private volatile Provider<LocationProvider> provideLocationProvider;
    private volatile Provider<LocationService> provideLocationServiceProvider;
    private volatile Provider<MAPAccountManager> provideMAPAccountManagerProvider;
    private volatile Provider<MAPAuthenticatedURLConnectionFactory> provideMAPAuthenticatedURLConnectionFactoryProvider;
    private volatile Provider<MainActivityLifecycleService> provideMainActivityLifecycleServiceProvider;
    private volatile Provider<MarketplaceService> provideMarketplaceServiceProvider;
    private volatile Provider<MatterService> provideMatterServiceProvider;
    private volatile Provider<MessagingHandler> provideMessagingHandlerProvider;
    private volatile Provider<MetricsFactory> provideMetricsFactoryProvider;
    private volatile Provider<MetricsService> provideMetricsServiceProvider;
    private volatile Provider<MobilyticsEventFactory> provideMobilyticsEventFactoryProvider;
    private volatile Provider<Mobilytics> provideMobilyticsProvider;
    private volatile Provider<ModeService> provideModeServiceProvider;
    private volatile Provider<NetworkService> provideNetworkServiceProvider;
    private volatile Provider<PersistentStorage.Factory> providePersistentStorageFactoryProvider;
    private volatile Provider<PersonIdProvider> providePersonIdProvider;
    private volatile Provider<PhotoService> providePhotoServiceProvider;
    private volatile Provider<PhotosFeatureGuardian> providePhotosFeatureGuardianProvider;
    private volatile Provider<UploadBundleManager> providePhotosUploadBundleManagerProvider;
    private volatile Provider<PhotosUploader> providePhotosUploaderProvider;
    private volatile Provider<PreloadAttributionManager> providePreloadAttributionManagerProvider;
    private volatile Provider<PreloadAttributionUIManager> providePreloadAttributionUIManagerProvider;
    private volatile Provider<com.amazon.alexa.protocols.marketplace.MarketplaceService> provideProtocolsMarketplaceServiceProvider;
    private volatile Provider<RoutingService> provideRoutingServiceProvider;
    private volatile Provider<SystemUtility> provideSystemUtilityProvider;
    private volatile Provider<TTCFCheckpoint> provideTTCFCheckpointProvider;
    private volatile Provider<TTCFRoutingDelegate> provideTTCFRoutingDelegateProvider;
    private volatile Provider<TTCFService> provideTTCFServiceProvider;
    private volatile Provider<TaskManager> provideTaskManagerProvider;
    private volatile Provider<UrlResolver> provideUrlResolverProvider;
    private volatile Provider<VoiceService> provideVoiceServiceProvider;
    private volatile Provider<AlexaMobileAndroidFeatureServiceImpl> providesAlexaMobileAndroidFeatureServiceImplProvider;
    private volatile Provider<FeatureServiceConfiguration> providesFsv2TestConfigurationProvider;
    private volatile Object rNLogPrinter;
    private volatile Object requestAuthenticator;
    private volatile Object routeFeatureGroupRegistry;
    private volatile Object routeWatcher;
    private RoutingModule routingModule;
    private volatile Object routingRegistry;
    private volatile Object routingRegistryAdapter;
    private volatile Object routingService;
    private volatile Object routingViewService;
    private SendToAppMessagingReceiverModule sendToAppMessagingReceiverModule;
    private volatile Object serviceLifecycle;
    private ServiceModule serviceModule;
    private volatile Object systemUtility;
    private volatile Object tCommServiceManager;
    private TCommServiceModule tCommServiceModule;
    private volatile Object tTCFCheckpoint;
    private TTCFModule tTCFModule;
    private volatile Object tTCFRoutingDelegate;
    private volatile Object tTCFService;
    private volatile Object taskManager;
    private volatile Object tokenAccessor;
    private volatile Object tokenEncryptor;
    private volatile Object tokenStorage;
    private volatile Object uploadBundleManager;
    private volatile Object urlResolver;
    private volatile Object userAgentService;
    private volatile Object userIdentityMapper;
    private volatile Object userIdentityRepository;
    private volatile Object userIdentityStorage;
    private volatile Object userProfileManager;
    private VoiceModule voiceModule;
    private volatile Object voiceService;
    private volatile Object wifiService;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private AlexaDeviceBackgroundImageModule alexaDeviceBackgroundImageModule;
        private AmazonMessagingModule amazonMessagingModule;
        private ApplicationModule applicationModule;
        private CloudDriveModule cloudDriveModule;
        private CommsModule commsModule;
        private DataStoreModule dataStoreModule;
        private DriveModeApplicationModule driveModeApplicationModule;
        private EnrollmentModule enrollmentModule;
        private EntertainmentModule entertainmentModule;
        private EventBusModule eventBusModule;
        private FeaturesModule featuresModule;
        private GoogleApiModule googleApiModule;
        private IdentityModule identityModule;
        private KinesisMetricsModule kinesisMetricsModule;
        private LocationModule locationModule;
        private MessagingModule messagingModule;
        private MetricsModule metricsModule;
        private MobilyticsModule mobilyticsModule;
        private ModeModule modeModule;
        private NetworkModule networkModule;
        private PhotoServiceModule photoServiceModule;
        private PreloadAttributionModule preloadAttributionModule;
        private RoutingModule routingModule;
        private SendToAppMessagingReceiverModule sendToAppMessagingReceiverModule;
        private ServiceModule serviceModule;
        private TCommServiceModule tCommServiceModule;
        private TTCFModule tTCFModule;
        private VoiceModule voiceModule;

        public Builder alexaDeviceBackgroundImageModule(AlexaDeviceBackgroundImageModule alexaDeviceBackgroundImageModule) {
            this.alexaDeviceBackgroundImageModule = (AlexaDeviceBackgroundImageModule) Preconditions.checkNotNull(alexaDeviceBackgroundImageModule);
            return this;
        }

        public Builder amazonMessagingModule(AmazonMessagingModule amazonMessagingModule) {
            this.amazonMessagingModule = (AmazonMessagingModule) Preconditions.checkNotNull(amazonMessagingModule);
            return this;
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public ApplicationComponent build() {
            if (this.identityModule == null) {
                this.identityModule = new IdentityModule();
            }
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            if (this.networkModule == null) {
                this.networkModule = new NetworkModule();
            }
            if (this.commsModule == null) {
                this.commsModule = new CommsModule();
            }
            if (this.serviceModule == null) {
                this.serviceModule = new ServiceModule();
            }
            if (this.dataStoreModule == null) {
                this.dataStoreModule = new DataStoreModule();
            }
            if (this.eventBusModule == null) {
                this.eventBusModule = new EventBusModule();
            }
            if (this.mobilyticsModule == null) {
                this.mobilyticsModule = new MobilyticsModule();
            }
            if (this.routingModule == null) {
                this.routingModule = new RoutingModule();
            }
            if (this.preloadAttributionModule == null) {
                this.preloadAttributionModule = new PreloadAttributionModule();
            }
            if (this.featuresModule == null) {
                this.featuresModule = new FeaturesModule();
            }
            if (this.metricsModule == null) {
                this.metricsModule = new MetricsModule();
            }
            if (this.kinesisMetricsModule == null) {
                this.kinesisMetricsModule = new KinesisMetricsModule();
            }
            if (this.voiceModule == null) {
                this.voiceModule = new VoiceModule();
            }
            if (this.modeModule == null) {
                this.modeModule = new ModeModule();
            }
            if (this.amazonMessagingModule == null) {
                this.amazonMessagingModule = new AmazonMessagingModule();
            }
            if (this.googleApiModule == null) {
                this.googleApiModule = new GoogleApiModule();
            }
            if (this.messagingModule == null) {
                this.messagingModule = new MessagingModule();
            }
            if (this.sendToAppMessagingReceiverModule == null) {
                this.sendToAppMessagingReceiverModule = new SendToAppMessagingReceiverModule();
            }
            if (this.tCommServiceModule == null) {
                this.tCommServiceModule = new TCommServiceModule();
            }
            if (this.locationModule == null) {
                this.locationModule = new LocationModule();
            }
            if (this.tTCFModule == null) {
                this.tTCFModule = new TTCFModule();
            }
            if (this.photoServiceModule == null) {
                this.photoServiceModule = new PhotoServiceModule();
            }
            if (this.cloudDriveModule == null) {
                this.cloudDriveModule = new CloudDriveModule();
            }
            if (this.alexaDeviceBackgroundImageModule == null) {
                this.alexaDeviceBackgroundImageModule = new AlexaDeviceBackgroundImageModule();
            }
            if (this.enrollmentModule == null) {
                this.enrollmentModule = new EnrollmentModule();
            }
            if (this.entertainmentModule == null) {
                this.entertainmentModule = new EntertainmentModule();
            }
            if (this.driveModeApplicationModule == null) {
                this.driveModeApplicationModule = new DriveModeApplicationModule();
            }
            return new DaggerApplicationComponent(this);
        }

        public Builder cloudDriveModule(CloudDriveModule cloudDriveModule) {
            this.cloudDriveModule = (CloudDriveModule) Preconditions.checkNotNull(cloudDriveModule);
            return this;
        }

        public Builder commsModule(CommsModule commsModule) {
            this.commsModule = (CommsModule) Preconditions.checkNotNull(commsModule);
            return this;
        }

        public Builder dataStoreModule(DataStoreModule dataStoreModule) {
            this.dataStoreModule = (DataStoreModule) Preconditions.checkNotNull(dataStoreModule);
            return this;
        }

        public Builder driveModeApplicationModule(DriveModeApplicationModule driveModeApplicationModule) {
            this.driveModeApplicationModule = (DriveModeApplicationModule) Preconditions.checkNotNull(driveModeApplicationModule);
            return this;
        }

        public Builder enrollmentModule(EnrollmentModule enrollmentModule) {
            this.enrollmentModule = (EnrollmentModule) Preconditions.checkNotNull(enrollmentModule);
            return this;
        }

        public Builder entertainmentModule(EntertainmentModule entertainmentModule) {
            this.entertainmentModule = (EntertainmentModule) Preconditions.checkNotNull(entertainmentModule);
            return this;
        }

        public Builder eventBusModule(EventBusModule eventBusModule) {
            this.eventBusModule = (EventBusModule) Preconditions.checkNotNull(eventBusModule);
            return this;
        }

        public Builder featuresModule(FeaturesModule featuresModule) {
            this.featuresModule = (FeaturesModule) Preconditions.checkNotNull(featuresModule);
            return this;
        }

        public Builder googleApiModule(GoogleApiModule googleApiModule) {
            this.googleApiModule = (GoogleApiModule) Preconditions.checkNotNull(googleApiModule);
            return this;
        }

        public Builder identityModule(IdentityModule identityModule) {
            this.identityModule = (IdentityModule) Preconditions.checkNotNull(identityModule);
            return this;
        }

        public Builder kinesisMetricsModule(KinesisMetricsModule kinesisMetricsModule) {
            this.kinesisMetricsModule = (KinesisMetricsModule) Preconditions.checkNotNull(kinesisMetricsModule);
            return this;
        }

        public Builder locationModule(LocationModule locationModule) {
            this.locationModule = (LocationModule) Preconditions.checkNotNull(locationModule);
            return this;
        }

        public Builder messagingModule(MessagingModule messagingModule) {
            this.messagingModule = (MessagingModule) Preconditions.checkNotNull(messagingModule);
            return this;
        }

        public Builder metricsModule(MetricsModule metricsModule) {
            this.metricsModule = (MetricsModule) Preconditions.checkNotNull(metricsModule);
            return this;
        }

        public Builder mobilyticsModule(MobilyticsModule mobilyticsModule) {
            this.mobilyticsModule = (MobilyticsModule) Preconditions.checkNotNull(mobilyticsModule);
            return this;
        }

        public Builder modeModule(ModeModule modeModule) {
            this.modeModule = (ModeModule) Preconditions.checkNotNull(modeModule);
            return this;
        }

        public Builder networkModule(NetworkModule networkModule) {
            this.networkModule = (NetworkModule) Preconditions.checkNotNull(networkModule);
            return this;
        }

        public Builder photoServiceModule(PhotoServiceModule photoServiceModule) {
            this.photoServiceModule = (PhotoServiceModule) Preconditions.checkNotNull(photoServiceModule);
            return this;
        }

        public Builder preloadAttributionModule(PreloadAttributionModule preloadAttributionModule) {
            this.preloadAttributionModule = (PreloadAttributionModule) Preconditions.checkNotNull(preloadAttributionModule);
            return this;
        }

        public Builder routingModule(RoutingModule routingModule) {
            this.routingModule = (RoutingModule) Preconditions.checkNotNull(routingModule);
            return this;
        }

        public Builder sendToAppMessagingReceiverModule(SendToAppMessagingReceiverModule sendToAppMessagingReceiverModule) {
            this.sendToAppMessagingReceiverModule = (SendToAppMessagingReceiverModule) Preconditions.checkNotNull(sendToAppMessagingReceiverModule);
            return this;
        }

        public Builder serviceModule(ServiceModule serviceModule) {
            this.serviceModule = (ServiceModule) Preconditions.checkNotNull(serviceModule);
            return this;
        }

        public Builder tCommServiceModule(TCommServiceModule tCommServiceModule) {
            this.tCommServiceModule = (TCommServiceModule) Preconditions.checkNotNull(tCommServiceModule);
            return this;
        }

        public Builder tTCFModule(TTCFModule tTCFModule) {
            this.tTCFModule = (TTCFModule) Preconditions.checkNotNull(tTCFModule);
            return this;
        }

        public Builder voiceModule(VoiceModule voiceModule) {
            this.voiceModule = (VoiceModule) Preconditions.checkNotNull(voiceModule);
            return this;
        }

        private Builder() {
        }
    }

    /* loaded from: classes12.dex */
    private final class ExternalUIComponentImpl implements ExternalUIComponent {
        @CanIgnoreReturnValue
        private ExternalUIViewModel injectExternalUIViewModel(ExternalUIViewModel externalUIViewModel) {
            ExternalUIViewModel_MembersInjector.injectEnvironmentService(externalUIViewModel, DaggerApplicationComponent.this.getEnvironmentService());
            ExternalUIViewModel_MembersInjector.injectUserAgentService(externalUIViewModel, DaggerApplicationComponent.this.getUserAgentService());
            return externalUIViewModel;
        }

        @Override // com.amazon.dee.app.dependencies.ExternalUIComponent
        public ExternalUIViewModel inject(ExternalUIViewModel externalUIViewModel) {
            return injectExternalUIViewModel(externalUIViewModel);
        }

        private ExternalUIComponentImpl(ExternalUIModule externalUIModule) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class MainComponentImpl implements MainComponent {
        private volatile Object aMPDInformationProvider;
        private volatile Object activity;
        private volatile Object alexaMenu;
        private volatile Object alexaNativeModuleCallExceptionHandler;
        private volatile Object appReviewRequestService;
        private volatile Object authenticationExceptionHandler;
        private volatile Object batteryOptimizationRoutingAdapter;
        private BatteryOptimizationUiModule batteryOptimizationUiModule;
        private volatile Object bridgeStatusService;
        private volatile Object commsRoutingHelper;
        private volatile Object commsViewModel;
        private ConversationModule conversationModule;
        private volatile Object conversationRouting;
        private volatile Object conversationRoutingAdapter;
        private volatile Object conversationUIService;
        private volatile Object defaultPermissionsService;
        private volatile Object driveModeMainActivityCompanion;
        private DriveModeMainModule driveModeMainModule;
        private volatile Object elementsDataService;
        private ElementsModule elementsModule;
        private volatile Object elementsRoutingAdapter;
        private volatile Object fullScreenTakeoverViewModel;
        private volatile Object handsFreeRoutingAdapter;
        private volatile Object handsFreeSettingsMetricRecorder;
        private volatile Object handsFreeSetup;
        private HomeModule homeModule;
        private volatile Object homeViewDelegate;
        private volatile Object locationPermissionMetricHelper;
        private volatile Object mainActivity;
        private volatile Object mainBindingThemeSetter;
        private volatile Object mainHandler;
        private MainModule mainModule;
        private volatile Object namedRoutingAdapter;
        private volatile Object namedRoutingAdapter2;
        private volatile Object nowPlayingViewModel;
        private volatile Object permissionsService;
        private volatile Provider<AMPDInformationProvider> provideAMPDInformationProvider;
        private volatile Provider<AppReviewRequestService> provideAppReviewRequestServiceProvider;
        private volatile Provider<BridgeStatusService> provideBridgeStatusServiceProvider;
        private volatile Provider<ConversationUIService> provideConversationUIServiceProvider;
        private volatile Provider<DefaultPermissionsService> provideDefaultPermissionsServiceProvider;
        private volatile Provider<HomeViewDelegate> provideHomeViewDelegateProvider;
        private volatile Provider<MainBindingThemeSetter> provideMainBindingThemeSetterProvider;
        private volatile Provider<MainHandler> provideMainHandlerProvider;
        private volatile Provider<UserMessageService> provideMessageUserServiceProvider;
        private volatile Provider<PermissionsService> providePermissionsServiceProvider;
        private volatile Provider<ReactBridgeService> provideReactBridgeServiceProvider;
        private volatile Provider<ReactFeatureManager> provideReactInstanceManagerProvider;
        private volatile Provider<ReactNativeViewManager> provideReactNativeViewManagerProvider;
        private volatile Provider<SonarUrlHandler> provideSonarUrlHandlerProvider;
        private volatile Provider<TabSelectionAnimator> provideTabSelectionAnimatorProvider;
        private volatile Provider<TestConfigurationService> provideTestConfigurationServiceProvider;
        private volatile Provider<ThemeRecorder> provideThemeRecorderProvider;
        private volatile Provider<ViewControllerFactoryProducer> provideViewControllerFactoryProducerProvider;
        private volatile Provider<ViewManagerDelegate> provideViewManagerDelegateProvider;
        private volatile Provider<ViewManagerLoadingDelegate> provideViewManagerLoadingDelegateProvider;
        private volatile Provider<DriveModeMainActivityCompanion.ViewModel> providesDriveModeViewModelProvider;
        private volatile Object reactBridgeMetrics;
        private volatile Object reactBridgeService;
        private volatile Object reactFeatureManager;
        private volatile Object reactInstanceManager;
        private volatile Object reactNativeViewManager;
        private volatile Object reactPackage;
        private volatile Object reactPackage2;
        private volatile Object reactPackage3;
        private volatile Object reactPackage4;
        private volatile Object reviewManager;
        private volatile Object settingsSetupFlowProvider;
        private volatile Object sonarUrlHandler;
        private volatile Object tabLayoutStatusService;
        private volatile Object tabSelectionAnimator;
        private volatile Object testArgumentsService;
        private volatile Object testConfigurationService;
        private volatile Object themeRecorder;
        private volatile Object toolbarService;
        private volatile Object toolbarWatcher;
        private volatile Object userMessageService;
        private volatile Object vendorAPIWrapperProvider;
        private volatile Object viewControllerFactoryProducer;
        private volatile Object viewManagerDelegate;
        private volatile Object viewManagerEventNotifier;
        private volatile Object viewManagerLoadingDelegate;
        private ViewManagerModule viewManagerModule;
        private volatile Object viewManagerRoutingAdapter;
        private volatile Object viewModel;
        private volatile Object viewPresenter;
        private volatile Object voiceRoutingAdapter;
        private VoiceUiModule voiceUiModule;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public final class SwitchingProvider<T> implements Provider<T> {
            private final int id;

            SwitchingProvider(int i) {
                this.id = i;
            }

            @Override // javax.inject.Provider
            /* renamed from: get */
            public T mo10268get() {
                int i = this.id;
                switch (i) {
                    case 0:
                        return (T) MainComponentImpl.this.reactFeatureManager();
                    case 1:
                        return (T) MainComponentImpl.this.getAMPDInformationProvider();
                    case 2:
                        return (T) MainComponentImpl.this.getReactBridgeService();
                    case 3:
                        return (T) MainComponentImpl.this.getMainHandler();
                    case 4:
                        return (T) MainComponentImpl.this.getHomeViewDelegate();
                    case 5:
                        return (T) MainComponentImpl.this.getBridgeStatusService();
                    case 6:
                        return (T) MainComponentImpl.this.getUserMessageService();
                    case 7:
                        return (T) MainComponentImpl.this.getConversationUIService();
                    case 8:
                        return (T) MainComponentImpl.this.getViewModel();
                    case 9:
                        return (T) MainComponentImpl.this.getThemeRecorder();
                    case 10:
                        return (T) MainComponentImpl.this.getSonarUrlHandler();
                    case 11:
                        return (T) MainComponentImpl.this.getMainBindingThemeSetter();
                    case 12:
                        return (T) MainComponentImpl.this.getTabSelectionAnimator();
                    case 13:
                        return (T) MainComponentImpl.this.getDefaultPermissionsService();
                    case 14:
                        return (T) MainComponentImpl.this.getTestConfigurationService();
                    case 15:
                        return (T) MainComponentImpl.this.getAppReviewRequestService();
                    case 16:
                        return (T) MainComponentImpl.this.getViewControllerFactoryProducer();
                    case 17:
                        return (T) MainComponentImpl.this.getPermissionsService();
                    case 18:
                        return (T) MainComponentImpl.this.getReactNativeViewManager();
                    case 19:
                        return (T) MainComponentImpl.this.getViewManagerDelegate();
                    case 20:
                        return (T) MainComponentImpl.this.getViewManagerLoadingDelegate();
                    default:
                        throw new AssertionError(i);
                }
            }
        }

        /* loaded from: classes12.dex */
        private final class WebComponentImpl implements WebComponent {
            private BridgeModule bridgeModule;
            private volatile Object javaScriptBridgeOrchestrator;
            private volatile Object javaScriptDelegate;
            private volatile Object javaScriptInjector;
            private volatile Object javaScriptPlayer;
            private volatile Object javaScriptResponseQueue;
            private volatile Object webAppMessagingReceiver;
            private WebModule webModule;
            private volatile Object webNavigator;
            private volatile Object webViewDelegate;
            private volatile Object webViewJavaScriptLoader;

            private AccessibilityInfoBridge getAccessibilityInfoBridge() {
                return BridgeModule_ProvideAccessibilityInfoBridgeFactory.proxyProvideAccessibilityInfoBridge(this.bridgeModule, DaggerApplicationComponent.this.getContext(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private AccountManagementBridge getAccountManagementBridge() {
                return BridgeModule_ProvideAccountManagementBridgeFactory.proxyProvideAccountManagementBridge(this.bridgeModule, DaggerApplicationComponent.this.getMAPAccountManager(), DaggerApplicationComponent.this.accountService(), getJavaScriptInjector(), getJavaScriptResponseQueue(), getWebViewDelegate(), DaggerApplicationComponent.this.getRoutingService(), DaggerApplicationComponent.this.identityService(), getJavaScriptDelegate(), DaggerApplicationComponent.this.metricsService());
            }

            private AlexaDeviceBackgroundImageBridge getAlexaDeviceBackgroundImageBridge() {
                return BridgeModule_ProvideBackgroundImageBridgeFactory.proxyProvideBackgroundImageBridge(this.bridgeModule, MainComponentImpl.this.getActivity(), getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.getGson());
            }

            private AppCacheBridge getAppCacheBridge() {
                return BridgeModule_ProvideAppCacheBridgeFactory.proxyProvideAppCacheBridge(this.bridgeModule, getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private AppInfoBridge getAppInfoBridge() {
                return BridgeModule_ProvideAppInfoBridgeFactory.proxyProvideAppInfoBridge(this.bridgeModule, DaggerApplicationComponent.this.getContext(), MainComponentImpl.this.getActivity(), DaggerApplicationComponent.this.getDeviceInformation(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private AppLauncherBridge getAppLauncherBridge() {
                return BridgeModule_ProvideAppLauncherBridgeFactory.proxyProvideAppLauncherBridge(this.bridgeModule, DaggerApplicationComponent.this.getContext(), MainComponentImpl.this.getActivity(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private AppLayoutBridge getAppLayoutBridge() {
                return BridgeModule_ProvideAppLayoutBridgeFactory.proxyProvideAppLayoutBridge(this.bridgeModule, getJavaScriptDelegate(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private AppReloadBridge getAppReloadBridge() {
                return BridgeModule_ProvideAppReloadBridgeFactory.proxyProvideAppReloadBridge(this.bridgeModule, getWebViewDelegate(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private AudioBridge getAudioBridge() {
                return BridgeModule_ProvideAudioBridgeFactory.proxyProvideAudioBridge(this.bridgeModule, DaggerApplicationComponent.this.getContext(), getJavaScriptPlayer(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private EventBusWebViewBridge getEventBusWebViewBridge() {
                return BridgeModule_ProvideEventBusWebViewBridgeFactory.proxyProvideEventBusWebViewBridge(this.bridgeModule, getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.eventBus());
            }

            private ExternalUILauncherBridge getExternalUILauncherBridge() {
                return BridgeModule_ProvideExternalUILauncherBridgeFactory.proxyProvideExternalUILauncherBridge(this.bridgeModule, MainComponentImpl.this.getActivity(), getWebViewDelegate(), DaggerApplicationComponent.this.getEnvironmentService(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private FeedbackBridge getFeedbackBridge() {
                return BridgeModule_ProvideFeedbackBridgeFactory.proxyProvideFeedbackBridge(this.bridgeModule, MainComponentImpl.this.getActivity(), getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.getCoralService(), BridgeModule_ProvideLogRetrieverFactory.proxyProvideLogRetriever(this.bridgeModule));
            }

            private HeaderInfoBridge getHeaderInfoBridge() {
                return BridgeModule_ProvideHeaderInfoBridgeFactory.proxyProvideHeaderInfoBridge(this.bridgeModule, getJavaScriptDelegate(), getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.getHeaderCacheService());
            }

            private JavaScriptBridgeOrchestrator getJavaScriptBridgeOrchestrator() {
                Object obj;
                Object obj2 = this.javaScriptBridgeOrchestrator;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.javaScriptBridgeOrchestrator;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideJavaScriptBridgeOrchestratorFactory.proxyProvideJavaScriptBridgeOrchestrator(this.webModule, getJavaScriptResponseQueue());
                            this.javaScriptBridgeOrchestrator = DoubleCheck.reentrantCheck(this.javaScriptBridgeOrchestrator, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (JavaScriptBridgeOrchestrator) obj2;
            }

            private JavaScriptDelegate getJavaScriptDelegate() {
                Object obj;
                Object obj2 = this.javaScriptDelegate;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.javaScriptDelegate;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideJavaScriptDelegateFactory.proxyProvideJavaScriptDelegate(this.webModule);
                            this.javaScriptDelegate = DoubleCheck.reentrantCheck(this.javaScriptDelegate, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (JavaScriptDelegate) obj2;
            }

            private JavaScriptInjector getJavaScriptInjector() {
                Object obj;
                Object obj2 = this.javaScriptInjector;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.javaScriptInjector;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideJavaScriptInjectorFactory.proxyProvideJavaScriptInjector(this.webModule, DaggerApplicationComponent.this.getContext(), getWebViewJavaScriptLoader());
                            this.javaScriptInjector = DoubleCheck.reentrantCheck(this.javaScriptInjector, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (JavaScriptInjector) obj2;
            }

            private JavaScriptPlayer getJavaScriptPlayer() {
                Object obj;
                Object obj2 = this.javaScriptPlayer;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.javaScriptPlayer;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideJavaScriptPlayerFactory.proxyProvideJavaScriptPlayer(this.webModule, DaggerApplicationComponent.this.getContext());
                            this.javaScriptPlayer = DoubleCheck.reentrantCheck(this.javaScriptPlayer, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (JavaScriptPlayer) obj2;
            }

            private JavaScriptResponseQueue getJavaScriptResponseQueue() {
                Object obj;
                Object obj2 = this.javaScriptResponseQueue;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.javaScriptResponseQueue;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideJavaScriptResponseQueueFactory.proxyProvideJavaScriptResponseQueue(this.webModule);
                            this.javaScriptResponseQueue = DoubleCheck.reentrantCheck(this.javaScriptResponseQueue, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (JavaScriptResponseQueue) obj2;
            }

            private MenuSettingsBridge getMenuSettingsBridge() {
                return BridgeModule_ProvideMenuSettingsBridgeFactory.proxyProvideMenuSettingsBridge(this.bridgeModule, getJavaScriptInjector(), getJavaScriptResponseQueue(), MainComponentImpl.this.getAlexaMenu());
            }

            private MetricsServiceBridge getMetricsServiceBridge() {
                return BridgeModule_ProvideMetricsServiceBridgeFactory.proxyProvideMetricsServiceBridge(this.bridgeModule, getJavaScriptDelegate(), getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.metricsService(), DaggerApplicationComponent.this.getGson());
            }

            private NativeHostBridge getNativeHostBridge() {
                return BridgeModule_ProvideNativeHostBridgeFactory.proxyProvideNativeHostBridge(this.bridgeModule, getJavaScriptDelegate(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private NativeLocalStorageBridge getNativeLocalStorageBridge() {
                return BridgeModule_ProvideNativeLocalStorageBridgeFactory.proxyProvideNativeLocalStorageBridge(this.bridgeModule, DaggerApplicationComponent.this.getDataStoreService2(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private NavBarToggleBridge getNavBarToggleBridge() {
                return BridgeModule_ProvideNavBarToggleBridgeFactory.proxyProvideNavBarToggleBridge(this.bridgeModule, getJavaScriptDelegate(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private NavigationBridge getNavigationBridge() {
                return BridgeModule_ProvideNavigationBridgeFactory.proxyProvideNavigationBridge(this.bridgeModule, getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.getRoutingService(), getWebViewDelegate(), DaggerApplicationComponent.this.identityService(), DoubleCheck.lazy(DaggerApplicationComponent.this.getCommsServiceV2Provider()));
            }

            private NetworkEventBridge getNetworkEventBridge() {
                return BridgeModule_ProvideNetworkEventBridgeFactory.proxyProvideNetworkEventBridge(this.bridgeModule, DaggerApplicationComponent.this.getContext(), DaggerApplicationComponent.this.getNetworkService(), DaggerApplicationComponent.this.getWifiService(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private NotificationServiceBridge getNotificationServiceBridge() {
                return BridgeModule_ProvideNotificationServiceBridgeFactory.proxyProvideNotificationServiceBridge(this.bridgeModule, DaggerApplicationComponent.this.getContext(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private OOBEBridge getOOBEBridge() {
                return BridgeModule_ProvideOOBEBridgeFactory.proxyProvideOOBEBridge(this.bridgeModule, MainComponentImpl.this.getActivity(), DoubleCheck.lazy(DaggerApplicationComponent.this.getConversationServiceProvider()), DaggerApplicationComponent.this.identityService(), DaggerApplicationComponent.this.getEnvironmentService(), getJavaScriptInjector(), getJavaScriptResponseQueue(), getWebViewDelegate(), DaggerApplicationComponent.this.accountService(), DaggerApplicationComponent.this.metricsService(), DaggerApplicationComponent.this.getRoutingService(), DaggerApplicationComponent.this.eventBus(), MainComponentImpl.this.getHandsFreeSetup(), DoubleCheck.lazy(DaggerApplicationComponent.this.getCommsServiceV2Provider()));
            }

            private OrientationBridge getOrientationBridge() {
                return BridgeModule_ProvideOrientationBridgeFactory.proxyProvideOrientationBridge(this.bridgeModule, MainComponentImpl.this.getActivity(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private PermissionsBridge getPermissionsBridge() {
                return BridgeModule_ProvidePermissionsBridgeFactory.proxyProvidePermissionsBridge(this.bridgeModule, MainComponentImpl.this.getActivity(), getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.getGson());
            }

            private TachyonIdentityBridge getTachyonIdentityBridge() {
                return BridgeModule_ProvideTachyonIdentityBridgeFactory.proxyProvideTachyonIdentityBridge(this.bridgeModule, getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.commsManager(), DoubleCheck.lazy(DaggerApplicationComponent.this.getCommsServiceV2Provider()));
            }

            private TachyonSettingsBridge getTachyonSettingsBridge() {
                return BridgeModule_ProvideTachyonSettingsBridgeFactory.proxyProvideTachyonSettingsBridge(this.bridgeModule, getJavaScriptInjector(), getJavaScriptResponseQueue(), DaggerApplicationComponent.this.commsManager(), DaggerApplicationComponent.this.getIdentityPreferencesProvider());
            }

            private VideoPlaybackBridge getVideoPlaybackBridge() {
                return BridgeModule_ProvideVideoPlaybackBridgeFactory.proxyProvideVideoPlaybackBridge(this.bridgeModule, MainComponentImpl.this.getActivity(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            private WebAppMessagingReceiver getWebAppMessagingReceiver() {
                Object obj;
                Object obj2 = this.webAppMessagingReceiver;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.webAppMessagingReceiver;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideWebAppMessagingReceiverFactory.proxyProvideWebAppMessagingReceiver(this.webModule, getWebNavigator(), getJavaScriptInjector(), DaggerApplicationComponent.this.getGson());
                            this.webAppMessagingReceiver = DoubleCheck.reentrantCheck(this.webAppMessagingReceiver, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (WebAppMessagingReceiver) obj2;
            }

            private WebNavigator getWebNavigator() {
                Object obj;
                Object obj2 = this.webNavigator;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.webNavigator;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideWebNavigatorFactory.proxyProvideWebNavigator(this.webModule, getWebViewDelegate(), getJavaScriptInjector(), DaggerApplicationComponent.this.getEnvironmentService(), DaggerApplicationComponent.this.identityService(), DaggerApplicationComponent.this.eventBus());
                            this.webNavigator = DoubleCheck.reentrantCheck(this.webNavigator, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (WebNavigator) obj2;
            }

            private WebViewDelegate getWebViewDelegate() {
                Object obj;
                Object obj2 = this.webViewDelegate;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.webViewDelegate;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideWebViewDelegateFactory.proxyProvideWebViewDelegate(this.webModule);
                            this.webViewDelegate = DoubleCheck.reentrantCheck(this.webViewDelegate, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (WebViewDelegate) obj2;
            }

            private WebViewJavaScriptLoader getWebViewJavaScriptLoader() {
                Object obj;
                Object obj2 = this.webViewJavaScriptLoader;
                if (obj2 instanceof MemoizedSentinel) {
                    synchronized (obj2) {
                        obj = this.webViewJavaScriptLoader;
                        if (obj instanceof MemoizedSentinel) {
                            obj = WebModule_ProvideJavaScriptLoaderFactory.proxyProvideJavaScriptLoader(this.webModule);
                            this.webViewJavaScriptLoader = DoubleCheck.reentrantCheck(this.webViewJavaScriptLoader, obj);
                        }
                    }
                    obj2 = obj;
                }
                return (WebViewJavaScriptLoader) obj2;
            }

            private WifiBridge getWifiBridge() {
                return BridgeModule_ProvideWifiBridgeFactory.proxyProvideWifiBridge(this.bridgeModule, MainComponentImpl.this.getActivity(), DaggerApplicationComponent.this.getWifiService(), getJavaScriptInjector(), getJavaScriptResponseQueue());
            }

            @CanIgnoreReturnValue
            private WebApp injectWebApp(WebApp webApp) {
                WebApp_MembersInjector.injectJavaScriptBridgeOrchestrator(webApp, getJavaScriptBridgeOrchestrator());
                WebApp_MembersInjector.injectJavaScriptPlayer(webApp, getJavaScriptPlayer());
                WebApp_MembersInjector.injectWebViewDelegate(webApp, getWebViewDelegate());
                WebApp_MembersInjector.injectJavaScriptInjector(webApp, getJavaScriptInjector());
                WebApp_MembersInjector.injectPermissionsBridge(webApp, getPermissionsBridge());
                WebApp_MembersInjector.injectNavigationBridge(webApp, getNavigationBridge());
                WebApp_MembersInjector.injectNetworkEventBridge(webApp, getNetworkEventBridge());
                WebApp_MembersInjector.injectAccountManagementBridge(webApp, getAccountManagementBridge());
                WebApp_MembersInjector.injectAppInfoBridge(webApp, getAppInfoBridge());
                WebApp_MembersInjector.injectAccessibilityInfoBridge(webApp, getAccessibilityInfoBridge());
                WebApp_MembersInjector.injectAppCacheBridge(webApp, getAppCacheBridge());
                WebApp_MembersInjector.injectAudioBridge(webApp, getAudioBridge());
                WebApp_MembersInjector.injectAppReloadBridge(webApp, getAppReloadBridge());
                WebApp_MembersInjector.injectAppLauncherBridge(webApp, getAppLauncherBridge());
                WebApp_MembersInjector.injectExternalUILauncherBridge(webApp, getExternalUILauncherBridge());
                WebApp_MembersInjector.injectHeaderInfoBridge(webApp, getHeaderInfoBridge());
                WebApp_MembersInjector.injectNativeLocalStorageBridge(webApp, getNativeLocalStorageBridge());
                WebApp_MembersInjector.injectNavBarToggleBridge(webApp, getNavBarToggleBridge());
                WebApp_MembersInjector.injectOrientationBridge(webApp, getOrientationBridge());
                WebApp_MembersInjector.injectNotificationServiceBridge(webApp, getNotificationServiceBridge());
                WebApp_MembersInjector.injectWifiBridge(webApp, getWifiBridge());
                WebApp_MembersInjector.injectVideoPlaybackBridge(webApp, getVideoPlaybackBridge());
                WebApp_MembersInjector.injectNativeHostBridge(webApp, getNativeHostBridge());
                WebApp_MembersInjector.injectMetricsServiceBridge(webApp, getMetricsServiceBridge());
                WebApp_MembersInjector.injectAppLayoutBridge(webApp, getAppLayoutBridge());
                WebApp_MembersInjector.injectEnvironmentService(webApp, DaggerApplicationComponent.this.getEnvironmentService());
                WebApp_MembersInjector.injectNetworkService(webApp, DaggerApplicationComponent.this.getNetworkService());
                WebApp_MembersInjector.injectWifiService(webApp, DaggerApplicationComponent.this.getWifiService());
                WebApp_MembersInjector.injectMessagingService(webApp, DaggerApplicationComponent.this.messagingService());
                WebApp_MembersInjector.injectWebAppMessagingReceiver(webApp, getWebAppMessagingReceiver());
                WebApp_MembersInjector.injectIdentityService(webApp, DaggerApplicationComponent.this.identityService());
                WebApp_MembersInjector.injectAlexaDeviceBackgroundImageBridge(webApp, getAlexaDeviceBackgroundImageBridge());
                WebApp_MembersInjector.injectRoutingService(webApp, DaggerApplicationComponent.this.getRoutingService());
                WebApp_MembersInjector.injectEnvironmentWebNavigator(webApp, getWebNavigator());
                WebApp_MembersInjector.injectUserMessageService(webApp, MainComponentImpl.this.getUserMessageService());
                WebApp_MembersInjector.injectOobeBridge(webApp, getOOBEBridge());
                WebApp_MembersInjector.injectCommsDeviceSupport(webApp, DaggerApplicationComponent.this.getCommsDeviceSupport());
                WebApp_MembersInjector.injectTachyonSettingsBridge(webApp, getTachyonSettingsBridge());
                WebApp_MembersInjector.injectTachyonIdentityBridge(webApp, getTachyonIdentityBridge());
                WebApp_MembersInjector.injectFeedbackBridge(webApp, getFeedbackBridge());
                WebApp_MembersInjector.injectContext(webApp, DaggerApplicationComponent.this.getContext());
                WebApp_MembersInjector.injectMetricsService(webApp, DaggerApplicationComponent.this.metricsService());
                WebApp_MembersInjector.injectEventBusWebViewBridge(webApp, getEventBusWebViewBridge());
                WebApp_MembersInjector.injectMenuSettingsBridge(webApp, getMenuSettingsBridge());
                return webApp;
            }

            @Override // com.amazon.dee.app.dependencies.WebComponent
            public void inject(WebApp webApp) {
                injectWebApp(webApp);
            }

            private WebComponentImpl(WebModule webModule) {
                this.javaScriptResponseQueue = new MemoizedSentinel();
                this.javaScriptBridgeOrchestrator = new MemoizedSentinel();
                this.javaScriptPlayer = new MemoizedSentinel();
                this.webViewDelegate = new MemoizedSentinel();
                this.webViewJavaScriptLoader = new MemoizedSentinel();
                this.javaScriptInjector = new MemoizedSentinel();
                this.javaScriptDelegate = new MemoizedSentinel();
                this.webNavigator = new MemoizedSentinel();
                this.webAppMessagingReceiver = new MemoizedSentinel();
                this.webModule = (WebModule) Preconditions.checkNotNull(webModule);
                this.bridgeModule = new BridgeModule();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public AMPDInformationProvider getAMPDInformationProvider() {
            Object obj;
            Object obj2 = this.aMPDInformationProvider;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.aMPDInformationProvider;
                    if (obj instanceof MemoizedSentinel) {
                        obj = VoiceUiModule_ProvideAMPDInformationProviderFactory.proxyProvideAMPDInformationProvider(this.voiceUiModule, DaggerApplicationComponent.this.getContext());
                        this.aMPDInformationProvider = DoubleCheck.reentrantCheck(this.aMPDInformationProvider, obj);
                    }
                }
                obj2 = obj;
            }
            return (AMPDInformationProvider) obj2;
        }

        private Provider<AMPDInformationProvider> getAMPDInformationProviderProvider() {
            Provider<AMPDInformationProvider> provider = this.provideAMPDInformationProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(1);
                this.provideAMPDInformationProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Activity getActivity() {
            Object obj;
            Object obj2 = this.activity;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.activity;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideActivityFactory.proxyProvideActivity(this.mainModule);
                        this.activity = DoubleCheck.reentrantCheck(this.activity, obj);
                    }
                }
                obj2 = obj;
            }
            return (Activity) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public AlexaMenu getAlexaMenu() {
            Object obj;
            Object obj2 = this.alexaMenu;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.alexaMenu;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideMenuFactory.proxyProvideMenu(this.mainModule, getActivity());
                        this.alexaMenu = DoubleCheck.reentrantCheck(this.alexaMenu, obj);
                    }
                }
                obj2 = obj;
            }
            return (AlexaMenu) obj2;
        }

        private AlexaNativeModuleCallExceptionHandler getAlexaNativeModuleCallExceptionHandler() {
            Object obj;
            Object obj2 = this.alexaNativeModuleCallExceptionHandler;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.alexaNativeModuleCallExceptionHandler;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideAlexaNativeModuleCallExceptionHandlerFactory.proxyProvideAlexaNativeModuleCallExceptionHandler(this.elementsModule, DaggerApplicationComponent.this.rnLogPrinter());
                        this.alexaNativeModuleCallExceptionHandler = DoubleCheck.reentrantCheck(this.alexaNativeModuleCallExceptionHandler, obj);
                    }
                }
                obj2 = obj;
            }
            return (AlexaNativeModuleCallExceptionHandler) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public AppReviewRequestService getAppReviewRequestService() {
            Object obj;
            Object obj2 = this.appReviewRequestService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.appReviewRequestService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideAppReviewRequestServiceFactory.proxyProvideAppReviewRequestService(this.mainModule, DaggerApplicationComponent.this.eventBus(), DaggerApplicationComponent.this.getRoutingService(), DaggerApplicationComponent.this.identityService(), DaggerApplicationComponent.this.getWifiService(), DaggerApplicationComponent.this.getEnvironmentService(), DoubleCheck.lazy(DaggerApplicationComponent.this.getModeServiceProvider()), DaggerApplicationComponent.this.getContext(), getReviewManager(), DoubleCheck.lazy(DaggerApplicationComponent.this.getFeatureServiceV2Provider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getMobilyticsProvider()));
                        this.appReviewRequestService = DoubleCheck.reentrantCheck(this.appReviewRequestService, obj);
                    }
                }
                obj2 = obj;
            }
            return (AppReviewRequestService) obj2;
        }

        private Provider<AppReviewRequestService> getAppReviewRequestServiceProvider() {
            Provider<AppReviewRequestService> provider = this.provideAppReviewRequestServiceProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(15);
                this.provideAppReviewRequestServiceProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private AuthenticationExceptionHandler getAuthenticationExceptionHandler() {
            Object obj;
            Object obj2 = this.authenticationExceptionHandler;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.authenticationExceptionHandler;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideAuthenticationExceptionHandlerFactory.proxyProvideAuthenticationExceptionHandler(this.mainModule, DaggerApplicationComponent.this.getMobilytics(), getUserMessageService(), DaggerApplicationComponent.this.getRoutingService());
                        this.authenticationExceptionHandler = DoubleCheck.reentrantCheck(this.authenticationExceptionHandler, obj);
                    }
                }
                obj2 = obj;
            }
            return (AuthenticationExceptionHandler) obj2;
        }

        private BatteryOptimizationRoutingAdapter getBatteryOptimizationRoutingAdapter() {
            Object obj;
            Object obj2 = this.batteryOptimizationRoutingAdapter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.batteryOptimizationRoutingAdapter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = BatteryOptimizationUiModule_ProvideBatteryOptimizationRoutingAdapterFactory.proxyProvideBatteryOptimizationRoutingAdapter(this.batteryOptimizationUiModule, getActivity());
                        this.batteryOptimizationRoutingAdapter = DoubleCheck.reentrantCheck(this.batteryOptimizationRoutingAdapter, obj);
                    }
                }
                obj2 = obj;
            }
            return (BatteryOptimizationRoutingAdapter) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BridgeStatusService getBridgeStatusService() {
            Object obj;
            Object obj2 = this.bridgeStatusService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.bridgeStatusService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideBridgeStatusServiceFactory.proxyProvideBridgeStatusService(this.elementsModule, DaggerApplicationComponent.this.metricsService(), DaggerApplicationComponent.this.eventBus());
                        this.bridgeStatusService = DoubleCheck.reentrantCheck(this.bridgeStatusService, obj);
                    }
                }
                obj2 = obj;
            }
            return (BridgeStatusService) obj2;
        }

        private Provider<BridgeStatusService> getBridgeStatusServiceProvider() {
            Provider<BridgeStatusService> provider = this.provideBridgeStatusServiceProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(5);
                this.provideBridgeStatusServiceProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private CommsRoutingHelper getCommsRoutingHelper() {
            Object obj;
            Object obj2 = this.commsRoutingHelper;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.commsRoutingHelper;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ConversationModule_ProvideCommsRoutingHelperFactory.proxyProvideCommsRoutingHelper(this.conversationModule, getConversationUIService(), DaggerApplicationComponent.this.getRoutingService(), DaggerApplicationComponent.this.commsServiceV2(), DaggerApplicationComponent.this.commsManager(), DaggerApplicationComponent.this.identityService());
                        this.commsRoutingHelper = DoubleCheck.reentrantCheck(this.commsRoutingHelper, obj);
                    }
                }
                obj2 = obj;
            }
            return (CommsRoutingHelper) obj2;
        }

        private CommsUIDelegateBase getCommsUIDelegateBase() {
            return ConversationModule_ProvideCommsUIDelegateBaseFactory.proxyProvideCommsUIDelegateBase(this.conversationModule, getActivity(), DaggerApplicationComponent.this.getRoutingService(), getConversationRouting(), DaggerApplicationComponent.this.accountService(), DaggerApplicationComponent.this.getRoutingRegistryAdapter());
        }

        private CommsViewModel getCommsViewModel() {
            Object obj;
            Object obj2 = this.commsViewModel;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.commsViewModel;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideCommsViewModelFactory.proxyProvideCommsViewModel(this.mainModule, DaggerApplicationComponent.this.getContext(), DaggerApplicationComponent.this.mainActivityLifecycleService());
                        this.commsViewModel = DoubleCheck.reentrantCheck(this.commsViewModel, obj);
                    }
                }
                obj2 = obj;
            }
            return (CommsViewModel) obj2;
        }

        private ConversationRouting getConversationRouting() {
            Object obj;
            Object obj2 = this.conversationRouting;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.conversationRouting;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ConversationModule_ProvideConversationRoutingFactory.proxyProvideConversationRouting(this.conversationModule);
                        this.conversationRouting = DoubleCheck.reentrantCheck(this.conversationRouting, obj);
                    }
                }
                obj2 = obj;
            }
            return (ConversationRouting) obj2;
        }

        private ConversationRoutingAdapter getConversationRoutingAdapter() {
            Object obj;
            Object obj2 = this.conversationRoutingAdapter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.conversationRoutingAdapter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ConversationModule_ProvideConversationRoutingAdapterFactory.proxyProvideConversationRoutingAdapter(this.conversationModule, getActivity(), DaggerApplicationComponent.this.commsManager(), getConversationRouting(), DaggerApplicationComponent.this.metricsService(), DoubleCheck.lazy(DaggerApplicationComponent.this.getCommsServiceV2Provider()));
                        this.conversationRoutingAdapter = DoubleCheck.reentrantCheck(this.conversationRoutingAdapter, obj);
                    }
                }
                obj2 = obj;
            }
            return (ConversationRoutingAdapter) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConversationUIService getConversationUIService() {
            Object obj;
            Object obj2 = this.conversationUIService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.conversationUIService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ConversationModule_ProvideConversationUIServiceFactory.proxyProvideConversationUIService(this.conversationModule, getActivity(), DaggerApplicationComponent.this.identityService(), DaggerApplicationComponent.this.commsManager(), getCommsUIDelegateBase(), DaggerApplicationComponent.this.getCommsDeviceSupport());
                        this.conversationUIService = DoubleCheck.reentrantCheck(this.conversationUIService, obj);
                    }
                }
                obj2 = obj;
            }
            return (ConversationUIService) obj2;
        }

        private Provider<ConversationUIService> getConversationUIServiceProvider() {
            Provider<ConversationUIService> provider = this.provideConversationUIServiceProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(7);
                this.provideConversationUIServiceProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DefaultPermissionsService getDefaultPermissionsService() {
            Object obj;
            Object obj2 = this.defaultPermissionsService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.defaultPermissionsService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideDefaultPermissionsServiceFactory.proxyProvideDefaultPermissionsService(this.mainModule, getActivity());
                        this.defaultPermissionsService = DoubleCheck.reentrantCheck(this.defaultPermissionsService, obj);
                    }
                }
                obj2 = obj;
            }
            return (DefaultPermissionsService) obj2;
        }

        private Provider<DefaultPermissionsService> getDefaultPermissionsServiceProvider() {
            Provider<DefaultPermissionsService> provider = this.provideDefaultPermissionsServiceProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(13);
                this.provideDefaultPermissionsServiceProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private DriveModeMainActivityCompanion getDriveModeMainActivityCompanion() {
            Object obj;
            Object obj2 = this.driveModeMainActivityCompanion;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.driveModeMainActivityCompanion;
                    if (obj instanceof MemoizedSentinel) {
                        obj = DriveModeMainModule_ProvideDriveModeMainActivityCompanionFactory.proxyProvideDriveModeMainActivityCompanion(this.driveModeMainModule, DaggerApplicationComponent.this.getDriveModeService());
                        this.driveModeMainActivityCompanion = DoubleCheck.reentrantCheck(this.driveModeMainActivityCompanion, obj);
                    }
                }
                obj2 = obj;
            }
            return (DriveModeMainActivityCompanion) obj2;
        }

        private ElementsDataService getElementsDataService() {
            Object obj;
            Object obj2 = this.elementsDataService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.elementsDataService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideElementsDataServiceFactory.proxyProvideElementsDataService(this.elementsModule, DoubleCheck.lazy(DaggerApplicationComponent.this.getCookieManagerProvider()), DaggerApplicationComponent.this.httpClient(), DaggerApplicationComponent.this.getGson(), DaggerApplicationComponent.this.getMetricsServiceV2(), DaggerApplicationComponent.this.getUrlResolver(), DoubleCheck.lazy(DaggerApplicationComponent.this.getIdentityServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getAccountServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getRoutingServiceProvider()), DaggerApplicationComponent.this.getNamedCacheOfHttpResponseWrapper(), DoubleCheck.lazy(DaggerApplicationComponent.this.getMetricsServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getFeatureServiceV2Provider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getEnvironmentServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getMobilyticsProvider()));
                        this.elementsDataService = DoubleCheck.reentrantCheck(this.elementsDataService, obj);
                    }
                }
            } else {
                obj = obj2;
            }
            return (ElementsDataService) obj;
        }

        private ElementsRoutingAdapter getElementsRoutingAdapter() {
            Object obj;
            Object obj2 = this.elementsRoutingAdapter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.elementsRoutingAdapter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideElementsRoutingAdapterFactory.proxyProvideElementsRoutingAdapter(this.elementsModule, getRouter(), reactFeatureManager(), DaggerApplicationComponent.this.identityService(), getReactBridgeService());
                        this.elementsRoutingAdapter = DoubleCheck.reentrantCheck(this.elementsRoutingAdapter, obj);
                    }
                }
                obj2 = obj;
            }
            return (ElementsRoutingAdapter) obj2;
        }

        private FullScreenTakeoverViewModel getFullScreenTakeoverViewModel() {
            Object obj;
            Object obj2 = this.fullScreenTakeoverViewModel;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.fullScreenTakeoverViewModel;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideFullScreenTakeoverViewModelFactory.proxyProvideFullScreenTakeoverViewModel(this.mainModule, getReactInstanceManager());
                        this.fullScreenTakeoverViewModel = DoubleCheck.reentrantCheck(this.fullScreenTakeoverViewModel, obj);
                    }
                }
                obj2 = obj;
            }
            return (FullScreenTakeoverViewModel) obj2;
        }

        private HandsFreeRoutingAdapter getHandsFreeRoutingAdapter() {
            Object obj;
            Object obj2 = this.handsFreeRoutingAdapter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.handsFreeRoutingAdapter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = VoiceUiModule_ProvideHandsFreeRoutingAdapterFactory.proxyProvideHandsFreeRoutingAdapter(this.voiceUiModule, getActivity(), getAMPDInformationProvider(), getVendorAPIWrapperProvider(), getSettingsSetupFlowProvider(), getHandsFreeSettingsMetricRecorder());
                        this.handsFreeRoutingAdapter = DoubleCheck.reentrantCheck(this.handsFreeRoutingAdapter, obj);
                    }
                }
                obj2 = obj;
            }
            return (HandsFreeRoutingAdapter) obj2;
        }

        private HandsFreeSettingsMetricRecorder getHandsFreeSettingsMetricRecorder() {
            Object obj;
            Object obj2 = this.handsFreeSettingsMetricRecorder;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.handsFreeSettingsMetricRecorder;
                    if (obj instanceof MemoizedSentinel) {
                        obj = VoiceUiModule_ProvideHandsFreeSettingsMetricRecorderFactory.proxyProvideHandsFreeSettingsMetricRecorder(this.voiceUiModule, DaggerApplicationComponent.this.getContext());
                        this.handsFreeSettingsMetricRecorder = DoubleCheck.reentrantCheck(this.handsFreeSettingsMetricRecorder, obj);
                    }
                }
                obj2 = obj;
            }
            return (HandsFreeSettingsMetricRecorder) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HandsFreeSetup getHandsFreeSetup() {
            Object obj;
            Object obj2 = this.handsFreeSetup;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.handsFreeSetup;
                    if (obj instanceof MemoizedSentinel) {
                        obj = VoiceUiModule_ProvideHandsFreeSetupFactory.proxyProvideHandsFreeSetup(this.voiceUiModule, DaggerApplicationComponent.this.getContext());
                        this.handsFreeSetup = DoubleCheck.reentrantCheck(this.handsFreeSetup, obj);
                    }
                }
                obj2 = obj;
            }
            return (HandsFreeSetup) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HomeViewDelegate getHomeViewDelegate() {
            Object obj;
            Object obj2 = this.homeViewDelegate;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.homeViewDelegate;
                    if (obj instanceof MemoizedSentinel) {
                        obj = HomeModule_ProvideHomeViewDelegateFactory.proxyProvideHomeViewDelegate(this.homeModule, getActivity());
                        this.homeViewDelegate = DoubleCheck.reentrantCheck(this.homeViewDelegate, obj);
                    }
                }
                obj2 = obj;
            }
            return (HomeViewDelegate) obj2;
        }

        private Provider<HomeViewDelegate> getHomeViewDelegateProvider() {
            Provider<HomeViewDelegate> provider = this.provideHomeViewDelegateProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(4);
                this.provideHomeViewDelegateProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private LocationPermissionMetricHelper getLocationPermissionMetricHelper() {
            Object obj;
            Object obj2 = this.locationPermissionMetricHelper;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.locationPermissionMetricHelper;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideLocationPermissionMetricHelperFactory.proxyProvideLocationPermissionMetricHelper(this.mainModule, getActivity(), DaggerApplicationComponent.this.metricsService());
                        this.locationPermissionMetricHelper = DoubleCheck.reentrantCheck(this.locationPermissionMetricHelper, obj);
                    }
                }
                obj2 = obj;
            }
            return (LocationPermissionMetricHelper) obj2;
        }

        private MainActivity getMainActivity() {
            Object obj;
            Object obj2 = this.mainActivity;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.mainActivity;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideMainActivityFactory.proxyProvideMainActivity(this.mainModule);
                        this.mainActivity = DoubleCheck.reentrantCheck(this.mainActivity, obj);
                    }
                }
                obj2 = obj;
            }
            return (MainActivity) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MainBindingThemeSetter getMainBindingThemeSetter() {
            Object obj;
            Object obj2 = this.mainBindingThemeSetter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.mainBindingThemeSetter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideMainBindingThemeSetterFactory.proxyProvideMainBindingThemeSetter(this.mainModule, getActivity());
                        this.mainBindingThemeSetter = DoubleCheck.reentrantCheck(this.mainBindingThemeSetter, obj);
                    }
                }
                obj2 = obj;
            }
            return (MainBindingThemeSetter) obj2;
        }

        private Provider<MainBindingThemeSetter> getMainBindingThemeSetterProvider() {
            Provider<MainBindingThemeSetter> provider = this.provideMainBindingThemeSetterProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(11);
                this.provideMainBindingThemeSetterProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MainHandler getMainHandler() {
            Object obj;
            Object obj2 = this.mainHandler;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.mainHandler;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideMainHandlerFactory.proxyProvideMainHandler(this.mainModule);
                        this.mainHandler = DoubleCheck.reentrantCheck(this.mainHandler, obj);
                    }
                }
                obj2 = obj;
            }
            return (MainHandler) obj2;
        }

        private Provider<MainHandler> getMainHandlerProvider() {
            Provider<MainHandler> provider = this.provideMainHandlerProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(3);
                this.provideMainHandlerProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private RoutingAdapter getNamedRoutingAdapter() {
            Object obj;
            Object obj2 = this.namedRoutingAdapter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.namedRoutingAdapter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = HomeModule_ProvideHomeRoutingAdapterFactory.proxyProvideHomeRoutingAdapter(this.homeModule, DoubleCheck.lazy(DaggerApplicationComponent.this.getContextProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getLocationProviderProvider()), DoubleCheck.lazy(getReactBridgeServiceProvider()), DoubleCheck.lazy(getMainHandlerProvider()), DoubleCheck.lazy(getHomeViewDelegateProvider()), DoubleCheck.lazy(getBridgeStatusServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getIdentityServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getRoutingServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getVoiceServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getTTCFCheckpointProvider()), DoubleCheck.lazy(getUserMessageServiceProvider()), getActivity());
                        this.namedRoutingAdapter = DoubleCheck.reentrantCheck(this.namedRoutingAdapter, obj);
                    }
                }
            } else {
                obj = obj2;
            }
            return (RoutingAdapter) obj;
        }

        private RoutingAdapter getNamedRoutingAdapter2() {
            Object obj;
            Object obj2 = this.namedRoutingAdapter2;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.namedRoutingAdapter2;
                    if (obj instanceof MemoizedSentinel) {
                        obj = DriveModeMainModule_ProvideDriveModeRoutingAdapterFactory.proxyProvideDriveModeRoutingAdapter(this.driveModeMainModule, getDriveModeMainActivityCompanion());
                        this.namedRoutingAdapter2 = DoubleCheck.reentrantCheck(this.namedRoutingAdapter2, obj);
                    }
                }
                obj2 = obj;
            }
            return (RoutingAdapter) obj2;
        }

        private NowPlayingViewModel getNowPlayingViewModel() {
            Object obj;
            Object obj2 = this.nowPlayingViewModel;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.nowPlayingViewModel;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideNowPlayingViewModelFactory.proxyProvideNowPlayingViewModel(this.mainModule, getActivity(), DaggerApplicationComponent.this.mainActivityLifecycleService(), DaggerApplicationComponent.this.getPlayerCardUpdater(), DaggerApplicationComponent.this.metricsService(), DaggerApplicationComponent.this.voiceService(), DaggerApplicationComponent.this.identityService());
                        this.nowPlayingViewModel = DoubleCheck.reentrantCheck(this.nowPlayingViewModel, obj);
                    }
                }
                obj2 = obj;
            }
            return (NowPlayingViewModel) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public PermissionsService getPermissionsService() {
            Object obj;
            Object obj2 = this.permissionsService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.permissionsService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvidePermissionsServiceFactory.proxyProvidePermissionsService(this.mainModule, getDefaultPermissionsService());
                        this.permissionsService = DoubleCheck.reentrantCheck(this.permissionsService, obj);
                    }
                }
                obj2 = obj;
            }
            return (PermissionsService) obj2;
        }

        private Provider<PermissionsService> getPermissionsServiceProvider() {
            Provider<PermissionsService> provider = this.providePermissionsServiceProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(17);
                this.providePermissionsServiceProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private ReactBridgeMetrics getReactBridgeMetrics() {
            Object obj;
            Object obj2 = this.reactBridgeMetrics;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactBridgeMetrics;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideReactBridgeMetricsFactory.proxyProvideReactBridgeMetrics(this.elementsModule, DaggerApplicationComponent.this.getMetricsServiceV2(), getBridgeStatusService());
                        this.reactBridgeMetrics = DoubleCheck.reentrantCheck(this.reactBridgeMetrics, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactBridgeMetrics) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ReactBridgeService getReactBridgeService() {
            Object obj;
            Object obj2 = this.reactBridgeService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactBridgeService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideReactBridgeServiceFactory.proxyProvideReactBridgeService(this.elementsModule, DaggerApplicationComponent.this.getApplication(), getSetOfReactPackage(), this.elementsModule.provideReactDeveloperSupportEnabled(), getAlexaNativeModuleCallExceptionHandler(), DaggerApplicationComponent.this.getMetricsServiceV2(), DoubleCheck.lazy(DaggerApplicationComponent.this.getRoutingServiceProvider()), DaggerApplicationComponent.this.mainActivityLifecycleService());
                        this.reactBridgeService = DoubleCheck.reentrantCheck(this.reactBridgeService, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactBridgeService) obj2;
        }

        private Provider<ReactBridgeService> getReactBridgeServiceProvider() {
            Provider<ReactBridgeService> provider = this.provideReactBridgeServiceProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(2);
                this.provideReactBridgeServiceProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private Provider<ReactFeatureManager> getReactFeatureManagerProvider() {
            Provider<ReactFeatureManager> provider = this.provideReactInstanceManagerProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(0);
                this.provideReactInstanceManagerProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private ReactInstanceManager getReactInstanceManager() {
            Object obj;
            Object obj2 = this.reactInstanceManager;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactInstanceManager;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideReactInstanceManagerFactory.proxyProvideReactInstanceManager(this.elementsModule, getReactBridgeService(), getReactBridgeMetrics(), getBridgeStatusService());
                        this.reactInstanceManager = DoubleCheck.reentrantCheck(this.reactInstanceManager, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactInstanceManager) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ReactNativeViewManager getReactNativeViewManager() {
            Object obj;
            Object obj2 = this.reactNativeViewManager;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactNativeViewManager;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ViewManagerModule_ProvideReactNativeViewManagerFactory.proxyProvideReactNativeViewManager(this.viewManagerModule, DoubleCheck.lazy(getReactBridgeServiceProvider()));
                        this.reactNativeViewManager = DoubleCheck.reentrantCheck(this.reactNativeViewManager, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactNativeViewManager) obj2;
        }

        private Provider<ReactNativeViewManager> getReactNativeViewManagerProvider() {
            Provider<ReactNativeViewManager> provider = this.provideReactNativeViewManagerProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(18);
                this.provideReactNativeViewManagerProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private ReactPackage getReactPackage() {
            ElementsModule elementsModule = this.elementsModule;
            return ElementsModule_ProvideAlexaMobileAndroidPackageFactory.proxyProvideAlexaMobileAndroidPackage(elementsModule, ElementsModule_ProvideCollectionsFactoryFactory.proxyProvideCollectionsFactory(elementsModule), DaggerApplicationComponent.this.getEnvironmentService(), DaggerApplicationComponent.this.getDeviceInformation(), DaggerApplicationComponent.this.getRoutingService(), DaggerApplicationComponent.this.identityService(), DaggerApplicationComponent.this.accountService(), DaggerApplicationComponent.this.getUserAgentService(), DaggerApplicationComponent.this.metricsService(), DaggerApplicationComponent.this.getMetricsServiceV2(), DoubleCheck.lazy(DaggerApplicationComponent.this.getMobilyticsProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getMobilyticsEventFactoryProvider()), DaggerApplicationComponent.this.locationService(), DaggerApplicationComponent.this.eventBus(), DoubleCheck.lazy(getReactFeatureManagerProvider()), getBridgeStatusService(), DaggerApplicationComponent.this.voiceService(), getAlexaMenu(), DaggerApplicationComponent.this.routingRegistry(), DaggerApplicationComponent.this.getRouteFeatureGroupRegistry(), getReactRouteRegistry(), DaggerApplicationComponent.this.getLocationProvider(), DaggerApplicationComponent.this.persistentStorageFactory(), DaggerApplicationComponent.this.getLatencyInfra(), DaggerApplicationComponent.this.getBluetoothService(), DaggerApplicationComponent.this.getPhotosChooser(), DoubleCheck.lazy(DaggerApplicationComponent.this.getPhotosUploaderProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getUploadBundleManagerProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getEventBusProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getTaskManagerProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getIdentityServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getCertificateReaderServiceProvider()), getTestArgumentsService(), DoubleCheck.lazy(DaggerApplicationComponent.this.getPhotosFeatureGuardianProvider()), DoubleCheck.lazy(getAMPDInformationProviderProvider()), DaggerApplicationComponent.this.getPlatformFeatureServiceV2(), DoubleCheck.lazy(DaggerApplicationComponent.this.getCloudDriveMetricsProvider()));
        }

        private ReactPackage getReactPackage2() {
            return ElementsModule_ProvideBugsnagPackageFactory.proxyProvideBugsnagPackage(this.elementsModule, DaggerApplicationComponent.this.getCrashReportingService());
        }

        private ReactPackage getReactPackage3() {
            Object obj;
            Object obj2 = this.reactPackage;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactPackage;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideAlexaDataAPIPackageFactory.proxyProvideAlexaDataAPIPackage(this.elementsModule, getElementsDataService(), DaggerApplicationComponent.this.getNamedDefaultElementLocalStorage2(), DaggerApplicationComponent.this.getNamedDefaultElementLocalStorage());
                        this.reactPackage = DoubleCheck.reentrantCheck(this.reactPackage, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactPackage) obj2;
        }

        private ReactPackage getReactPackage4() {
            Object obj;
            Object obj2 = this.reactPackage2;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactPackage2;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideDeviceSetupFFSReactPackageFactory.proxyProvideDeviceSetupFFSReactPackage(this.elementsModule);
                        this.reactPackage2 = DoubleCheck.reentrantCheck(this.reactPackage2, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactPackage) obj2;
        }

        private ReactPackage getReactPackage5() {
            Object obj;
            Object obj2 = this.reactPackage3;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactPackage3;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideAlexaIoTSoftAPPackageFactory.proxyProvideAlexaIoTSoftAPPackage(this.elementsModule);
                        this.reactPackage3 = DoubleCheck.reentrantCheck(this.reactPackage3, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactPackage) obj2;
        }

        private ReactPackage getReactPackage6() {
            return ElementsModule_ProvideMainReactPackageFactory.proxyProvideMainReactPackage(this.elementsModule, DoubleCheck.lazy(DaggerApplicationComponent.this.getFeatureServiceV2Provider()));
        }

        private ReactPackage getReactPackage7() {
            Object obj;
            Object obj2 = this.reactPackage4;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactPackage4;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideBleManagerPackageFactory.proxyProvideBleManagerPackage(this.elementsModule);
                        this.reactPackage4 = DoubleCheck.reentrantCheck(this.reactPackage4, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactPackage) obj2;
        }

        private ReactRouteRegistry getReactRouteRegistry() {
            return ElementsModule_ProvideReactRouteRegistryFactory.proxyProvideReactRouteRegistry(this.elementsModule, DaggerApplicationComponent.this.getRouteFeatureGroupRegistry(), DaggerApplicationComponent.this.getRouteWatcher());
        }

        private ReviewManager getReviewManager() {
            Object obj;
            Object obj2 = this.reviewManager;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reviewManager;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideReviewManagerFactory.proxyProvideReviewManager(this.mainModule, getActivity());
                        this.reviewManager = DoubleCheck.reentrantCheck(this.reviewManager, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReviewManager) obj2;
        }

        private Router getRouter() {
            return ElementsModule_ProvideRouterFactory.proxyProvideRouter(this.elementsModule, reactFeatureManager());
        }

        private Set<ReactPackage> getSetOfReactPackage() {
            return ImmutableSet.of(getReactPackage(), ElementsModule_ProvideSMSMessagingPackageFactory.proxyProvideSMSMessagingPackage(this.elementsModule), ElementsModule_ProvideReactNativeHereMapsExplorePackageFactory.proxyProvideReactNativeHereMapsExplorePackage(this.elementsModule), ElementsModule_ProvideReactNativeCoachMarkPackageFactory.proxyProvideReactNativeCoachMarkPackage(this.elementsModule), ElementsModule_ProvideSvgPackageFactory.proxyProvideSvgPackage(this.elementsModule), getReactPackage2(), ElementsModule_ProvideCameraPackageFactory.proxyProvideCameraPackage(this.elementsModule), ElementsModule_ProvideLottiePackageFactory.proxyProvideLottiePackage(this.elementsModule), ElementsModule_ProvidesRNCNetInfoFactory.proxyProvidesRNCNetInfo(this.elementsModule), ElementsModule_ProvidesRNCCameraRollFactory.proxyProvidesRNCCameraRoll(this.elementsModule), ElementsModule_ProvideSliderPackageFactory.proxyProvideSliderPackage(this.elementsModule), ElementsModule_ProvidesRNCWebviewFactory.proxyProvidesRNCWebview(this.elementsModule), ElementsModule_ProvideDateTimePickerPackageFactory.proxyProvideDateTimePickerPackage(this.elementsModule), ElementsModule_ProvideAppearancePackageFactory.proxyProvideAppearancePackage(this.elementsModule), ElementsModule_ProvideViewPagerPackageFactory.proxyProvideViewPagerPackage(this.elementsModule), ElementsModule_ProvideImagePickerPackageFactory.proxyProvideImagePickerPackage(this.elementsModule), ElementsModule_ProvideRNCPickerPackageFactory.proxyProvideRNCPickerPackage(this.elementsModule), getReactPackage3(), getReactPackage4(), getReactPackage5(), getReactPackage6(), getReactPackage7());
        }

        private SettingsSetupFlowProvider getSettingsSetupFlowProvider() {
            Object obj;
            Object obj2 = this.settingsSetupFlowProvider;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.settingsSetupFlowProvider;
                    if (obj instanceof MemoizedSentinel) {
                        obj = VoiceUiModule_ProvideSettingsSetupFlowProviderFactory.proxyProvideSettingsSetupFlowProvider(this.voiceUiModule);
                        this.settingsSetupFlowProvider = DoubleCheck.reentrantCheck(this.settingsSetupFlowProvider, obj);
                    }
                }
                obj2 = obj;
            }
            return (SettingsSetupFlowProvider) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public SonarUrlHandler getSonarUrlHandler() {
            Object obj;
            Object obj2 = this.sonarUrlHandler;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.sonarUrlHandler;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideSonarUrlHandlerFactory.proxyProvideSonarUrlHandler(this.mainModule, DoubleCheck.lazy(DaggerApplicationComponent.this.getOkHttpClientProvider()));
                        this.sonarUrlHandler = DoubleCheck.reentrantCheck(this.sonarUrlHandler, obj);
                    }
                }
                obj2 = obj;
            }
            return (SonarUrlHandler) obj2;
        }

        private Provider<SonarUrlHandler> getSonarUrlHandlerProvider() {
            Provider<SonarUrlHandler> provider = this.provideSonarUrlHandlerProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(10);
                this.provideSonarUrlHandlerProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private TabLayoutStatusService getTabLayoutStatusService() {
            Object obj;
            Object obj2 = this.tabLayoutStatusService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.tabLayoutStatusService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideTabLayoutStatusServiceFactory.proxyProvideTabLayoutStatusService(this.mainModule);
                        this.tabLayoutStatusService = DoubleCheck.reentrantCheck(this.tabLayoutStatusService, obj);
                    }
                }
                obj2 = obj;
            }
            return (TabLayoutStatusService) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public TabSelectionAnimator getTabSelectionAnimator() {
            Object obj;
            Object obj2 = this.tabSelectionAnimator;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.tabSelectionAnimator;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideTabSelectionAnimatorFactory.proxyProvideTabSelectionAnimator(this.mainModule, DaggerApplicationComponent.this.getContext(), DoubleCheck.lazy(DaggerApplicationComponent.this.getEventBusProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getIdentityServiceProvider()));
                        this.tabSelectionAnimator = DoubleCheck.reentrantCheck(this.tabSelectionAnimator, obj);
                    }
                }
                obj2 = obj;
            }
            return (TabSelectionAnimator) obj2;
        }

        private Provider<TabSelectionAnimator> getTabSelectionAnimatorProvider() {
            Provider<TabSelectionAnimator> provider = this.provideTabSelectionAnimatorProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(12);
                this.provideTabSelectionAnimatorProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private TestArgumentsService getTestArgumentsService() {
            Object obj;
            Object obj2 = this.testArgumentsService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.testArgumentsService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideTestArgumentsServiceFactory.proxyProvideTestArgumentsService(this.mainModule, getActivity(), DoubleCheck.lazy(DaggerApplicationComponent.this.getCertificateReaderServiceProvider()));
                        this.testArgumentsService = DoubleCheck.reentrantCheck(this.testArgumentsService, obj);
                    }
                }
                obj2 = obj;
            }
            return (TestArgumentsService) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public TestConfigurationService getTestConfigurationService() {
            Object obj;
            Object obj2 = this.testConfigurationService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.testConfigurationService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideTestConfigurationServiceFactory.proxyProvideTestConfigurationService(this.mainModule, getTestArgumentsService(), DoubleCheck.lazy(DaggerApplicationComponent.this.getCrashReportingServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getCertificateReaderServiceProvider()), DoubleCheck.lazy(getAppReviewRequestServiceProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getFeatureServiceConfigurationProvider()));
                        this.testConfigurationService = DoubleCheck.reentrantCheck(this.testConfigurationService, obj);
                    }
                }
                obj2 = obj;
            }
            return (TestConfigurationService) obj2;
        }

        private Provider<TestConfigurationService> getTestConfigurationServiceProvider() {
            Provider<TestConfigurationService> provider = this.provideTestConfigurationServiceProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(14);
                this.provideTestConfigurationServiceProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ThemeRecorder getThemeRecorder() {
            Object obj;
            Object obj2 = this.themeRecorder;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.themeRecorder;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideThemeRecorderFactory.proxyProvideThemeRecorder(this.mainModule, DaggerApplicationComponent.this.getContext(), DoubleCheck.lazy(DaggerApplicationComponent.this.getMobilyticsProvider()), DoubleCheck.lazy(DaggerApplicationComponent.this.getIdentityServiceProvider()));
                        this.themeRecorder = DoubleCheck.reentrantCheck(this.themeRecorder, obj);
                    }
                }
                obj2 = obj;
            }
            return (ThemeRecorder) obj2;
        }

        private Provider<ThemeRecorder> getThemeRecorderProvider() {
            Provider<ThemeRecorder> provider = this.provideThemeRecorderProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(9);
                this.provideThemeRecorderProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private ToolbarService getToolbarService() {
            Object obj;
            Object obj2 = this.toolbarService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.toolbarService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideToolbarServiceFactory.proxyProvideToolbarService(this.elementsModule, DaggerApplicationComponent.this.eventBus(), DaggerApplicationComponent.this.getGson(), getActivity(), getReactInstanceManager());
                        this.toolbarService = DoubleCheck.reentrantCheck(this.toolbarService, obj);
                    }
                }
                obj2 = obj;
            }
            return (ToolbarService) obj2;
        }

        private ToolbarWatcher getToolbarWatcher() {
            Object obj;
            Object obj2 = this.toolbarWatcher;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.toolbarWatcher;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideToolbarWatcherFactory.proxyProvideToolbarWatcher(this.elementsModule, DaggerApplicationComponent.this.getRoutingService(), getToolbarService(), DaggerApplicationComponent.this.getRoutingViewService(), DaggerApplicationComponent.this.crashMetadata());
                        this.toolbarWatcher = DoubleCheck.reentrantCheck(this.toolbarWatcher, obj);
                    }
                }
                obj2 = obj;
            }
            return (ToolbarWatcher) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public UserMessageService getUserMessageService() {
            Object obj;
            Object obj2 = this.userMessageService;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.userMessageService;
                    if (obj instanceof MemoizedSentinel) {
                        obj = MainModule_ProvideMessageUserServiceFactory.proxyProvideMessageUserService(this.mainModule);
                        this.userMessageService = DoubleCheck.reentrantCheck(this.userMessageService, obj);
                    }
                }
                obj2 = obj;
            }
            return (UserMessageService) obj2;
        }

        private Provider<UserMessageService> getUserMessageServiceProvider() {
            Provider<UserMessageService> provider = this.provideMessageUserServiceProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(6);
                this.provideMessageUserServiceProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private VendorAPIWrapperProvider getVendorAPIWrapperProvider() {
            Object obj;
            Object obj2 = this.vendorAPIWrapperProvider;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.vendorAPIWrapperProvider;
                    if (obj instanceof MemoizedSentinel) {
                        obj = VoiceUiModule_ProvideVendorAPIWrapperProviderFactory.proxyProvideVendorAPIWrapperProvider(this.voiceUiModule, DaggerApplicationComponent.this.getContext());
                        this.vendorAPIWrapperProvider = DoubleCheck.reentrantCheck(this.vendorAPIWrapperProvider, obj);
                    }
                }
                obj2 = obj;
            }
            return (VendorAPIWrapperProvider) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ViewControllerFactoryProducer getViewControllerFactoryProducer() {
            Object obj;
            Object obj2 = this.viewControllerFactoryProducer;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.viewControllerFactoryProducer;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ViewManagerModule_ProvideViewControllerFactoryProducerFactory.proxyProvideViewControllerFactoryProducer(this.viewManagerModule);
                        this.viewControllerFactoryProducer = DoubleCheck.reentrantCheck(this.viewControllerFactoryProducer, obj);
                    }
                }
                obj2 = obj;
            }
            return (ViewControllerFactoryProducer) obj2;
        }

        private Provider<ViewControllerFactoryProducer> getViewControllerFactoryProducerProvider() {
            Provider<ViewControllerFactoryProducer> provider = this.provideViewControllerFactoryProducerProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(16);
                this.provideViewControllerFactoryProducerProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ViewManagerDelegate getViewManagerDelegate() {
            Object obj;
            Object obj2 = this.viewManagerDelegate;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.viewManagerDelegate;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ViewManagerModule_ProvideViewManagerDelegateFactory.proxyProvideViewManagerDelegate(this.viewManagerModule, getMainActivity());
                        this.viewManagerDelegate = DoubleCheck.reentrantCheck(this.viewManagerDelegate, obj);
                    }
                }
                obj2 = obj;
            }
            return (ViewManagerDelegate) obj2;
        }

        private Provider<ViewManagerDelegate> getViewManagerDelegateProvider() {
            Provider<ViewManagerDelegate> provider = this.provideViewManagerDelegateProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(19);
                this.provideViewManagerDelegateProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private ViewManagerEventNotifier getViewManagerEventNotifier() {
            Object obj;
            Object obj2 = this.viewManagerEventNotifier;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.viewManagerEventNotifier;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ViewManagerModule_ProvideViewManagerEventNotifierFactory.proxyProvideViewManagerEventNotifier(this.viewManagerModule, getViewManagerDelegate());
                        this.viewManagerEventNotifier = DoubleCheck.reentrantCheck(this.viewManagerEventNotifier, obj);
                    }
                }
                obj2 = obj;
            }
            return (ViewManagerEventNotifier) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ViewManagerLoadingDelegate getViewManagerLoadingDelegate() {
            Object obj;
            Object obj2 = this.viewManagerLoadingDelegate;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.viewManagerLoadingDelegate;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ViewManagerModule_ProvideViewManagerLoadingDelegateFactory.proxyProvideViewManagerLoadingDelegate(this.viewManagerModule, getViewManagerEventNotifier());
                        this.viewManagerLoadingDelegate = DoubleCheck.reentrantCheck(this.viewManagerLoadingDelegate, obj);
                    }
                }
                obj2 = obj;
            }
            return (ViewManagerLoadingDelegate) obj2;
        }

        private Provider<ViewManagerLoadingDelegate> getViewManagerLoadingDelegateProvider() {
            Provider<ViewManagerLoadingDelegate> provider = this.provideViewManagerLoadingDelegateProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(20);
                this.provideViewManagerLoadingDelegateProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private ViewManagerRoutingAdapter getViewManagerRoutingAdapter() {
            Object obj;
            Object obj2 = this.viewManagerRoutingAdapter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.viewManagerRoutingAdapter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ViewManagerModule_ProvideViewManagerRoutingAdapterFactory.proxyProvideViewManagerRoutingAdapter(this.viewManagerModule, getViewPresenter());
                        this.viewManagerRoutingAdapter = DoubleCheck.reentrantCheck(this.viewManagerRoutingAdapter, obj);
                    }
                }
                obj2 = obj;
            }
            return (ViewManagerRoutingAdapter) obj2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DriveModeMainActivityCompanion.ViewModel getViewModel() {
            Object obj;
            Object obj2 = this.viewModel;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.viewModel;
                    if (obj instanceof MemoizedSentinel) {
                        obj = DriveModeMainModule_ProvidesDriveModeViewModelFactory.proxyProvidesDriveModeViewModel(this.driveModeMainModule, getDriveModeMainActivityCompanion());
                        this.viewModel = DoubleCheck.reentrantCheck(this.viewModel, obj);
                    }
                }
                obj2 = obj;
            }
            return (DriveModeMainActivityCompanion.ViewModel) obj2;
        }

        private Provider<DriveModeMainActivityCompanion.ViewModel> getViewModelProvider() {
            Provider<DriveModeMainActivityCompanion.ViewModel> provider = this.providesDriveModeViewModelProvider;
            if (provider == null) {
                SwitchingProvider switchingProvider = new SwitchingProvider(8);
                this.providesDriveModeViewModelProvider = switchingProvider;
                return switchingProvider;
            }
            return provider;
        }

        private ViewPresenter getViewPresenter() {
            Object obj;
            Object obj2 = this.viewPresenter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.viewPresenter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ViewManagerModule_ProvideViewPresenterFactory.proxyProvideViewPresenter(this.viewManagerModule, getMainActivity());
                        this.viewPresenter = DoubleCheck.reentrantCheck(this.viewPresenter, obj);
                    }
                }
                obj2 = obj;
            }
            return (ViewPresenter) obj2;
        }

        private VoiceRoutingAdapter getVoiceRoutingAdapter() {
            Object obj;
            Object obj2 = this.voiceRoutingAdapter;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.voiceRoutingAdapter;
                    if (obj instanceof MemoizedSentinel) {
                        obj = VoiceUiModule_ProvideVoiceRoutingAdapterFactory.proxyProvideVoiceRoutingAdapter(this.voiceUiModule, getActivity());
                        this.voiceRoutingAdapter = DoubleCheck.reentrantCheck(this.voiceRoutingAdapter, obj);
                    }
                }
                obj2 = obj;
            }
            return (VoiceRoutingAdapter) obj2;
        }

        @CanIgnoreReturnValue
        private MainActivity injectMainActivity(MainActivity mainActivity) {
            MainActivity_MembersInjector.injectNowPlayingViewModel(mainActivity, getNowPlayingViewModel());
            MainActivity_MembersInjector.injectCommsViewModel(mainActivity, getCommsViewModel());
            MainActivity_MembersInjector.injectFullScreenTakeoverViewModel(mainActivity, getFullScreenTakeoverViewModel());
            MainActivity_MembersInjector.injectDriveModeViewModel(mainActivity, getViewModel());
            MainActivity_MembersInjector.injectAlexaMenu(mainActivity, getAlexaMenu());
            MainActivity_MembersInjector.injectTabLayoutStatus(mainActivity, getTabLayoutStatusService());
            MainActivity_MembersInjector.injectLatencyInfra(mainActivity, DaggerApplicationComponent.this.getLatencyInfra());
            MainActivity_MembersInjector.injectCrashMetadata(mainActivity, DaggerApplicationComponent.this.crashMetadata());
            MainActivity_MembersInjector.injectFeatureStore(mainActivity, DoubleCheck.lazy(DaggerApplicationComponent.this.getFeatureStoreProvider()));
            MainActivity_MembersInjector.injectMainBindingThemeSetter(mainActivity, DoubleCheck.lazy(getMainBindingThemeSetterProvider()));
            MainActivity_MembersInjector.injectThemeRecorder(mainActivity, DoubleCheck.lazy(getThemeRecorderProvider()));
            MainActivity_MembersInjector.injectTabSelectionAnimator(mainActivity, DoubleCheck.lazy(getTabSelectionAnimatorProvider()));
            MainActivity_MembersInjector.injectPermissionsService(mainActivity, DoubleCheck.lazy(getDefaultPermissionsServiceProvider()));
            MainActivity_MembersInjector.injectTestConfigurations(mainActivity, DoubleCheck.lazy(getTestConfigurationServiceProvider()));
            MainActivity_MembersInjector.injectCertificateReaderService(mainActivity, DoubleCheck.lazy(DaggerApplicationComponent.this.getCertificateReaderServiceProvider()));
            MainActivity_MembersInjector.injectViewControllerFactoryProducer(mainActivity, DoubleCheck.lazy(getViewControllerFactoryProducerProvider()));
            MainActivity_MembersInjector.injectModeService(mainActivity, DoubleCheck.lazy(DaggerApplicationComponent.this.getModeServiceProvider()));
            MainActivity_MembersInjector.injectFeatureQuery(mainActivity, DaggerApplicationComponent.this.getFeatureQuery());
            MainActivity_MembersInjector.injectFeatureServiceV2Lazy(mainActivity, DoubleCheck.lazy(DaggerApplicationComponent.this.getFeatureServiceV2Provider()));
            return mainActivity;
        }

        @CanIgnoreReturnValue
        private MainViewModel injectMainViewModel(MainViewModel mainViewModel) {
            MainViewModel_MembersInjector.injectActivity(mainViewModel, getActivity());
            MainViewModel_MembersInjector.injectMainActivity(mainViewModel, getMainActivity());
            MainViewModel_MembersInjector.injectTaskManager(mainViewModel, DaggerApplicationComponent.this.getTaskManager());
            MainViewModel_MembersInjector.injectIdentityService(mainViewModel, DaggerApplicationComponent.this.identityService());
            MainViewModel_MembersInjector.injectFeatureQuery(mainViewModel, DaggerApplicationComponent.this.getFeatureQuery());
            MainViewModel_MembersInjector.injectAccountService(mainViewModel, DaggerApplicationComponent.this.accountService());
            MainViewModel_MembersInjector.injectMetricsService(mainViewModel, DaggerApplicationComponent.this.metricsService());
            MainViewModel_MembersInjector.injectMetricsServiceV2(mainViewModel, DaggerApplicationComponent.this.getMetricsServiceV2());
            MainViewModel_MembersInjector.injectCrashMetadata(mainViewModel, DaggerApplicationComponent.this.crashMetadata());
            MainViewModel_MembersInjector.injectMobilytics(mainViewModel, DaggerApplicationComponent.this.getMobilytics());
            MainViewModel_MembersInjector.injectRoutingService(mainViewModel, DaggerApplicationComponent.this.getRoutingService());
            MainViewModel_MembersInjector.injectViewService(mainViewModel, DaggerApplicationComponent.this.getRoutingViewService());
            MainViewModel_MembersInjector.injectAdapters(mainViewModel, DaggerApplicationComponent.this.getRoutingRegistryAdapter());
            MainViewModel_MembersInjector.injectMainRoutingAdapter(mainViewModel, MainModule_ProvideMainRoutingAdapterFactory.proxyProvideMainRoutingAdapter(this.mainModule));
            MainViewModel_MembersInjector.injectConversationRoutingAdapter(mainViewModel, getConversationRoutingAdapter());
            MainViewModel_MembersInjector.injectElementsRoutingAdapter(mainViewModel, getElementsRoutingAdapter());
            MainViewModel_MembersInjector.injectVoiceRoutingAdapter(mainViewModel, getVoiceRoutingAdapter());
            MainViewModel_MembersInjector.injectBatteryOptimizationRoutingAdapter(mainViewModel, getBatteryOptimizationRoutingAdapter());
            MainViewModel_MembersInjector.injectEnrollmentRoutingAdapter(mainViewModel, DaggerApplicationComponent.this.getEnrollmentRoutingAdapter());
            MainViewModel_MembersInjector.injectKidsEnrollmentRoutingAdapter(mainViewModel, DaggerApplicationComponent.this.getKidsEnrollmentRoutingAdapter());
            MainViewModel_MembersInjector.injectHomeRoutingAdapter(mainViewModel, getNamedRoutingAdapter());
            MainViewModel_MembersInjector.injectRoutes(mainViewModel, DaggerApplicationComponent.this.routingRegistry());
            MainViewModel_MembersInjector.injectRouteFeatureGroupRegistry(mainViewModel, DaggerApplicationComponent.this.getRouteFeatureGroupRegistry());
            MainViewModel_MembersInjector.injectRouteWatcher(mainViewModel, DaggerApplicationComponent.this.getRouteWatcher());
            MainViewModel_MembersInjector.injectEnvironmentService(mainViewModel, DaggerApplicationComponent.this.getEnvironmentService());
            MainViewModel_MembersInjector.injectCommsServiceV2(mainViewModel, DoubleCheck.lazy(DaggerApplicationComponent.this.getCommsServiceV2Provider()));
            MainViewModel_MembersInjector.injectConversationService(mainViewModel, DoubleCheck.lazy(DaggerApplicationComponent.this.getConversationServiceProvider()));
            MainViewModel_MembersInjector.injectConversationUIService(mainViewModel, DoubleCheck.lazy(getConversationUIServiceProvider()));
            MainViewModel_MembersInjector.injectHeaderCacheService(mainViewModel, DaggerApplicationComponent.this.getHeaderCacheService());
            MainViewModel_MembersInjector.injectExceptionHandler(mainViewModel, getAuthenticationExceptionHandler());
            MainViewModel_MembersInjector.injectVoiceService(mainViewModel, DaggerApplicationComponent.this.voiceService());
            MainViewModel_MembersInjector.injectNowPlayingViewModel(mainViewModel, getNowPlayingViewModel());
            MainViewModel_MembersInjector.injectCacheClearOperations(mainViewModel, DaggerApplicationComponent.this.getCacheClearOperations());
            MainViewModel_MembersInjector.injectReactFeatureManager(mainViewModel, reactFeatureManager());
            MainViewModel_MembersInjector.injectBridgeStatusService(mainViewModel, getBridgeStatusService());
            MainViewModel_MembersInjector.injectPersistentStorageFactory(mainViewModel, DaggerApplicationComponent.this.persistentStorageFactory());
            MainViewModel_MembersInjector.injectEventBusMessagingReceiver(mainViewModel, DaggerApplicationComponent.this.getEventBusMessagingReceiver());
            MainViewModel_MembersInjector.injectHandsFreeSetup(mainViewModel, getHandsFreeSetup());
            MainViewModel_MembersInjector.injectToolbarService(mainViewModel, getToolbarService());
            MainViewModel_MembersInjector.injectCommsManager(mainViewModel, DoubleCheck.lazy(DaggerApplicationComponent.this.getCommsManagerProvider()));
            MainViewModel_MembersInjector.injectAppReviewRequestService(mainViewModel, getAppReviewRequestService());
            MainViewModel_MembersInjector.injectEventBus(mainViewModel, DaggerApplicationComponent.this.eventBus());
            MainViewModel_MembersInjector.injectToolbarWatcher(mainViewModel, getToolbarWatcher());
            MainViewModel_MembersInjector.injectTabLayoutService(mainViewModel, getTabLayoutStatusService());
            MainViewModel_MembersInjector.injectPreloadAttributionUIManager(mainViewModel, DoubleCheck.lazy(DaggerApplicationComponent.this.getPreloadAttributionUIManagerProvider()));
            MainViewModel_MembersInjector.injectReactBridgeService(mainViewModel, getReactBridgeService());
            MainViewModel_MembersInjector.injectLatencyInfra(mainViewModel, DaggerApplicationComponent.this.getLatencyInfra());
            MainViewModel_MembersInjector.injectIntentFactory(mainViewModel, DaggerApplicationComponent.this.getIntentFactory());
            MainViewModel_MembersInjector.injectCommsRoutingHelper(mainViewModel, getCommsRoutingHelper());
            MainViewModel_MembersInjector.injectHandsFreeRoutingAdapter(mainViewModel, getHandsFreeRoutingAdapter());
            MainViewModel_MembersInjector.injectLocationPermissionMetricHelper(mainViewModel, getLocationPermissionMetricHelper());
            MainViewModel_MembersInjector.injectViewManagerRoutingAdapter(mainViewModel, getViewManagerRoutingAdapter());
            MainViewModel_MembersInjector.injectWebRoutingAdapter(mainViewModel, MainModule_ProvideWebRoutingAdapterFactory.proxyProvideWebRoutingAdapter(this.mainModule));
            MainViewModel_MembersInjector.injectDriveModeRoutingAdapter(mainViewModel, getNamedRoutingAdapter2());
            MainViewModel_MembersInjector.injectDriveModeViewModel(mainViewModel, DoubleCheck.lazy(getViewModelProvider()));
            MainViewModel_MembersInjector.injectModeService(mainViewModel, DoubleCheck.lazy(DaggerApplicationComponent.this.getModeServiceProvider()));
            MainViewModel_MembersInjector.injectDriveModeService(mainViewModel, DoubleCheck.lazy(DaggerApplicationComponent.this.getDriveModeServiceProvider()));
            MainViewModel_MembersInjector.injectThemeRecorder(mainViewModel, DoubleCheck.lazy(getThemeRecorderProvider()));
            MainViewModel_MembersInjector.injectSonarUrlHandler(mainViewModel, DoubleCheck.lazy(getSonarUrlHandlerProvider()));
            MainViewModel_MembersInjector.injectFeatureServiceV2Lazy(mainViewModel, DoubleCheck.lazy(DaggerApplicationComponent.this.getFeatureServiceV2Provider()));
            MainViewModel_MembersInjector.injectTestArguments(mainViewModel, getTestArgumentsService());
            return mainViewModel;
        }

        @CanIgnoreReturnValue
        private ReactFeatureControllerTransition injectReactFeatureControllerTransition(ReactFeatureControllerTransition reactFeatureControllerTransition) {
            ReactFeatureControllerTransition_MembersInjector.injectReactFeatureManager(reactFeatureControllerTransition, reactFeatureManager());
            return reactFeatureControllerTransition;
        }

        @CanIgnoreReturnValue
        private ViewControllerFragment injectViewControllerFragment(ViewControllerFragment viewControllerFragment) {
            ViewControllerFragment_MembersInjector.injectPermissionsService(viewControllerFragment, DoubleCheck.lazy(getPermissionsServiceProvider()));
            ViewControllerFragment_MembersInjector.injectRnViewManager(viewControllerFragment, DoubleCheck.lazy(getReactNativeViewManagerProvider()));
            ViewControllerFragment_MembersInjector.injectFactoryProducer(viewControllerFragment, DoubleCheck.lazy(getViewControllerFactoryProducerProvider()));
            ViewControllerFragment_MembersInjector.injectDelegate(viewControllerFragment, DoubleCheck.lazy(getViewManagerDelegateProvider()));
            ViewControllerFragment_MembersInjector.injectLoadingDelegate(viewControllerFragment, DoubleCheck.lazy(getViewManagerLoadingDelegateProvider()));
            return viewControllerFragment;
        }

        @Override // com.amazon.dee.app.dependencies.MainComponent
        public ReactFeatureViewController inject(ReactFeatureViewController reactFeatureViewController) {
            return reactFeatureViewController;
        }

        @Override // com.amazon.dee.app.dependencies.MainComponent
        public MainViewModel inject(MainViewModel mainViewModel) {
            return injectMainViewModel(mainViewModel);
        }

        @Override // com.amazon.dee.app.dependencies.MainComponent
        public WebComponent plus(WebModule webModule) {
            return new WebComponentImpl(webModule);
        }

        @Override // com.amazon.dee.app.dependencies.MainComponent
        public ReactFeatureManager reactFeatureManager() {
            Object obj;
            Object obj2 = this.reactFeatureManager;
            if (obj2 instanceof MemoizedSentinel) {
                synchronized (obj2) {
                    obj = this.reactFeatureManager;
                    if (obj instanceof MemoizedSentinel) {
                        obj = ElementsModule_ProvideReactInstanceManagerProviderFactory.proxyProvideReactInstanceManagerProvider(this.elementsModule, ElementsModule_ProvideCollectionsFactoryFactory.proxyProvideCollectionsFactory(this.elementsModule), getReactInstanceManager(), getBridgeStatusService(), DaggerApplicationComponent.this.getMetricsServiceV2(), this.elementsModule.provideReactDeveloperSupportEnabled(), getTabLayoutStatusService(), getReactBridgeService());
                        this.reactFeatureManager = DoubleCheck.reentrantCheck(this.reactFeatureManager, obj);
                    }
                }
                obj2 = obj;
            }
            return (ReactFeatureManager) obj2;
        }

        private MainComponentImpl(MainModule mainModule, ConversationModule conversationModule, ElementsModule elementsModule) {
            this.activity = new MemoizedSentinel();
            this.mainActivity = new MemoizedSentinel();
            this.conversationRouting = new MemoizedSentinel();
            this.conversationRoutingAdapter = new MemoizedSentinel();
            this.bridgeStatusService = new MemoizedSentinel();
            this.alexaMenu = new MemoizedSentinel();
            this.testArgumentsService = new MemoizedSentinel();
            this.aMPDInformationProvider = new MemoizedSentinel();
            this.elementsDataService = new MemoizedSentinel();
            this.reactPackage = new MemoizedSentinel();
            this.reactPackage2 = new MemoizedSentinel();
            this.reactPackage3 = new MemoizedSentinel();
            this.reactPackage4 = new MemoizedSentinel();
            this.alexaNativeModuleCallExceptionHandler = new MemoizedSentinel();
            this.reactBridgeService = new MemoizedSentinel();
            this.elementsRoutingAdapter = new MemoizedSentinel();
            this.voiceRoutingAdapter = new MemoizedSentinel();
            this.batteryOptimizationRoutingAdapter = new MemoizedSentinel();
            this.mainHandler = new MemoizedSentinel();
            this.homeViewDelegate = new MemoizedSentinel();
            this.userMessageService = new MemoizedSentinel();
            this.namedRoutingAdapter = new MemoizedSentinel();
            this.conversationUIService = new MemoizedSentinel();
            this.authenticationExceptionHandler = new MemoizedSentinel();
            this.nowPlayingViewModel = new MemoizedSentinel();
            this.handsFreeSetup = new MemoizedSentinel();
            this.reactBridgeMetrics = new MemoizedSentinel();
            this.reactInstanceManager = new MemoizedSentinel();
            this.toolbarService = new MemoizedSentinel();
            this.reviewManager = new MemoizedSentinel();
            this.appReviewRequestService = new MemoizedSentinel();
            this.toolbarWatcher = new MemoizedSentinel();
            this.tabLayoutStatusService = new MemoizedSentinel();
            this.commsRoutingHelper = new MemoizedSentinel();
            this.vendorAPIWrapperProvider = new MemoizedSentinel();
            this.settingsSetupFlowProvider = new MemoizedSentinel();
            this.handsFreeSettingsMetricRecorder = new MemoizedSentinel();
            this.handsFreeRoutingAdapter = new MemoizedSentinel();
            this.locationPermissionMetricHelper = new MemoizedSentinel();
            this.viewPresenter = new MemoizedSentinel();
            this.viewManagerRoutingAdapter = new MemoizedSentinel();
            this.driveModeMainActivityCompanion = new MemoizedSentinel();
            this.namedRoutingAdapter2 = new MemoizedSentinel();
            this.viewModel = new MemoizedSentinel();
            this.themeRecorder = new MemoizedSentinel();
            this.sonarUrlHandler = new MemoizedSentinel();
            this.commsViewModel = new MemoizedSentinel();
            this.fullScreenTakeoverViewModel = new MemoizedSentinel();
            this.mainBindingThemeSetter = new MemoizedSentinel();
            this.tabSelectionAnimator = new MemoizedSentinel();
            this.defaultPermissionsService = new MemoizedSentinel();
            this.testConfigurationService = new MemoizedSentinel();
            this.viewControllerFactoryProducer = new MemoizedSentinel();
            this.reactFeatureManager = new MemoizedSentinel();
            this.permissionsService = new MemoizedSentinel();
            this.reactNativeViewManager = new MemoizedSentinel();
            this.viewManagerDelegate = new MemoizedSentinel();
            this.viewManagerEventNotifier = new MemoizedSentinel();
            this.viewManagerLoadingDelegate = new MemoizedSentinel();
            this.mainModule = (MainModule) Preconditions.checkNotNull(mainModule);
            this.conversationModule = (ConversationModule) Preconditions.checkNotNull(conversationModule);
            this.elementsModule = (ElementsModule) Preconditions.checkNotNull(elementsModule);
            this.voiceUiModule = new VoiceUiModule();
            this.batteryOptimizationUiModule = new BatteryOptimizationUiModule();
            this.homeModule = new HomeModule();
            this.viewManagerModule = new ViewManagerModule();
            this.driveModeMainModule = new DriveModeMainModule();
        }

        @Override // com.amazon.dee.app.dependencies.MainComponent
        public MainActivity inject(MainActivity mainActivity) {
            return injectMainActivity(mainActivity);
        }

        @Override // com.amazon.dee.app.dependencies.MainComponent
        public ReactFeatureControllerTransition inject(ReactFeatureControllerTransition reactFeatureControllerTransition) {
            return injectReactFeatureControllerTransition(reactFeatureControllerTransition);
        }

        @Override // com.amazon.dee.app.dependencies.MainComponent
        public ViewControllerFragment inject(ViewControllerFragment viewControllerFragment) {
            return injectViewControllerFragment(viewControllerFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class SwitchingProvider<T> implements Provider<T> {
        private final int id;

        SwitchingProvider(int i) {
            this.id = i;
        }

        @Override // javax.inject.Provider
        /* renamed from: get */
        public T mo10268get() {
            int i = this.id;
            switch (i) {
                case 0:
                    return (T) DaggerApplicationComponent.this.accountService();
                case 1:
                    return (T) DaggerApplicationComponent.this.crashMetadata();
                case 2:
                    return (T) DaggerApplicationComponent.this.getCrashReporter();
                case 3:
                    return (T) DaggerApplicationComponent.this.getCrashReportingService();
                case 4:
                    return (T) DaggerApplicationComponent.this.httpClient();
                case 5:
                    return (T) DaggerApplicationComponent.this.getRoutingService();
                case 6:
                    return (T) DaggerApplicationComponent.this.getFeatureQuery();
                case 7:
                    return (T) DaggerApplicationComponent.this.identityService();
                case 8:
                    return (T) DaggerApplicationComponent.this.getAlexaMobileAndroidFeatureServiceImpl();
                case 9:
                    return (T) DaggerApplicationComponent.this.getEnvironmentService();
                case 10:
                    return (T) DaggerApplicationComponent.this.getCoralService();
                case 11:
                    return (T) DaggerApplicationComponent.this.getCookieManager();
                case 12:
                    return (T) DaggerApplicationComponent.this.metricsService();
                case 13:
                    return (T) DaggerApplicationComponent.this.getMobilytics();
                case 14:
                    return (T) DaggerApplicationComponent.this.getFeatureServiceV2();
                case 15:
                    return (T) DaggerApplicationComponent.this.eventBus();
                case 16:
                    return (T) DaggerApplicationComponent.this.getTaskManager();
                case 17:
                    return (T) DaggerApplicationComponent.this.getNamedDefaultElementLocalStorage();
                case 18:
                    return (T) DaggerApplicationComponent.this.getAlexaTarazedService();
                case 19:
                    return (T) DaggerApplicationComponent.this.certificateReaderService();
                case 20:
                    return (T) DaggerApplicationComponent.this.getMatterService();
                case 21:
                    return (T) DaggerApplicationComponent.this.getADM();
                case 22:
                    return (T) DaggerApplicationComponent.this.getFirebaseInstanceId();
                case 23:
                    return (T) DaggerApplicationComponent.this.getMessagingHandler();
                case 24:
                    return (T) DaggerApplicationComponent.this.getPreloadAttributionManager();
                case 25:
                    return (T) DaggerApplicationComponent.this.getDCMMetricsConnector();
                case 26:
                    return (T) DaggerApplicationComponent.this.getMetricsFactory();
                case 27:
                    return (T) DaggerApplicationComponent.this.getKinesisMetricsConnector();
                case 28:
                    return (T) DaggerApplicationComponent.this.getAppSessionClient();
                case 29:
                    return (T) DaggerApplicationComponent.this.getKinesisEventClient();
                case 30:
                    return (T) DaggerApplicationComponent.this.getMobilyticsEventFactory();
                case 31:
                    return (T) DaggerApplicationComponent.this.voiceService();
                case 32:
                    return (T) DaggerApplicationComponent.this.commsServiceV2();
                case 33:
                    return (T) DaggerApplicationComponent.this.getMAPAccountRegistrationService();
                case 34:
                    return (T) DaggerApplicationComponent.this.getContext();
                case 35:
                    return (T) DaggerApplicationComponent.this.getDeviceInformation();
                case 36:
                    return (T) DaggerApplicationComponent.this.getAccountUpgradeService();
                case 37:
                    return (T) DaggerApplicationComponent.this.getNetworkService();
                case 38:
                    return (T) DaggerApplicationComponent.this.persistentStorageFactory();
                case 39:
                    return (T) DaggerApplicationComponent.this.getLatencyReportingDelegate();
                case 40:
                    return (T) DaggerApplicationComponent.this.applicationLifecycleService();
                case 41:
                    return (T) DaggerApplicationComponent.this.getMAPAccountManager();
                case 42:
                    return (T) DaggerApplicationComponent.this.getPhotoService();
                case 43:
                    return (T) DaggerApplicationComponent.this.getBackgroundImageService();
                case 44:
                    return (T) DaggerApplicationComponent.this.getPersonIdProvider();
                case 45:
                    return (T) DaggerApplicationComponent.this.mainActivityLifecycleService();
                case 46:
                    return (T) DaggerApplicationComponent.this.getMarketplaceService();
                case 47:
                    return (T) DaggerApplicationComponent.this.getMarketplaceService2();
                case 48:
                    return (T) DaggerApplicationComponent.this.getCrashObserverRegistrar();
                case 49:
                    return (T) DaggerApplicationComponent.this.getMetricsServiceV2();
                case 50:
                    return (T) DaggerApplicationComponent.this.modeService();
                case 51:
                    return (T) DaggerApplicationComponent.this.locationService();
                case 52:
                    return (T) DaggerApplicationComponent.this.getJsonConverter();
                case 53:
                    return (T) DaggerApplicationComponent.this.ttcfService();
                case 54:
                    return (T) DaggerApplicationComponent.this.getAssetManagementService();
                case 55:
                    return (T) DaggerApplicationComponent.this.getDialogBuilderProvider();
                case 56:
                    return (T) DaggerApplicationComponent.this.getTTCFRoutingDelegate();
                case 57:
                    return (T) DaggerApplicationComponent.this.getPhotosUploader();
                case 58:
                    return (T) DaggerApplicationComponent.this.getAmazonCloudDriveExtendedClient();
                case 59:
                    return (T) DaggerApplicationComponent.this.getUploadBundleManager();
                case 60:
                    return (T) DaggerApplicationComponent.this.getCloudDriveMetrics();
                case 61:
                    return (T) DaggerApplicationComponent.this.getMAPAuthenticatedURLConnectionFactory();
                case 62:
                    return (T) DaggerApplicationComponent.this.getPhotosFeatureGuardian();
                case 63:
                    return (T) DaggerApplicationComponent.this.getSystemUtility();
                case 64:
                    return (T) DaggerApplicationComponent.this.getEnrollmentGateway();
                case 65:
                    return (T) DaggerApplicationComponent.this.getUrlResolver();
                case 66:
                    return (T) DaggerApplicationComponent.this.getLocationProvider();
                case 67:
                    return (T) DaggerApplicationComponent.this.getTTCFCheckpoint();
                case 68:
                    return (T) DaggerApplicationComponent.this.conversationService();
                case 69:
                    return (T) DaggerApplicationComponent.this.commsManager();
                case 70:
                    return (T) DaggerApplicationComponent.this.getPreloadAttributionUIManager();
                case 71:
                    return (T) DaggerApplicationComponent.this.getDriveModeService();
                case 72:
                    return (T) DaggerApplicationComponent.this.getFeatureStore();
                case 73:
                    return (T) DaggerApplicationComponent.this.getFeatureServiceConfiguration();
                default:
                    throw new AssertionError(i);
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ADM getADM() {
        Object obj;
        Object obj2 = this.aDM;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.aDM;
                if (obj instanceof MemoizedSentinel) {
                    obj = AmazonMessagingModule_ProvideADMFactory.proxyProvideADM(this.amazonMessagingModule, getContext());
                    this.aDM = DoubleCheck.reentrantCheck(this.aDM, obj);
                }
            }
            obj2 = obj;
        }
        return (ADM) obj2;
    }

    private Provider<ADM> getADMProvider() {
        Provider<ADM> provider = this.provideADMProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(21);
            this.provideADMProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private AccountConfiguration getAccountConfiguration() {
        Object obj;
        Object obj2 = this.accountConfiguration;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.accountConfiguration;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideAccountConfigurationFactory.proxyProvideAccountConfiguration(this.cloudDriveModule, getMAPAuthenticatedURLConnectionFactory(), getEndpointsCache(), getRequestAuthenticator());
                    this.accountConfiguration = DoubleCheck.reentrantCheck(this.accountConfiguration, obj);
                }
            }
            obj2 = obj;
        }
        return (AccountConfiguration) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<AccountService> getAccountServiceProvider() {
        Provider<AccountService> provider = this.provideAccountServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(0);
            this.provideAccountServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private AccountUpgradeAuthority getAccountUpgradeAuthority() {
        Object obj;
        Object obj2 = this.accountUpgradeAuthority;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.accountUpgradeAuthority;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideAccountUpgradeAuthorityFactory.proxyProvideAccountUpgradeAuthority(this.identityModule, getMAPAccountUpgradeService());
                    this.accountUpgradeAuthority = DoubleCheck.reentrantCheck(this.accountUpgradeAuthority, obj);
                }
            }
            obj2 = obj;
        }
        return (AccountUpgradeAuthority) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AccountUpgradeService getAccountUpgradeService() {
        Object obj;
        Object obj2 = this.accountUpgradeService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.accountUpgradeService;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideAccountUpgradeServiceFactory.proxyProvideAccountUpgradeService(this.identityModule, getMAPAccountUpgradeService());
                    this.accountUpgradeService = DoubleCheck.reentrantCheck(this.accountUpgradeService, obj);
                }
            }
            obj2 = obj;
        }
        return (AccountUpgradeService) obj2;
    }

    private Provider<AccountUpgradeService> getAccountUpgradeServiceProvider() {
        Provider<AccountUpgradeService> provider = this.provideAccountUpgradeServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(36);
            this.provideAccountUpgradeServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private AlexaCommsServiceWrapper getAlexaCommsServiceWrapper() {
        Object obj;
        Object obj2 = this.alexaCommsServiceWrapper;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.alexaCommsServiceWrapper;
                if (obj instanceof MemoizedSentinel) {
                    obj = CommsModule_ProvidesAlexaCommsServiceFactory.proxyProvidesAlexaCommsService(this.commsModule, DoubleCheck.lazy(getContextProvider()), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getEventBusProvider()), DoubleCheck.lazy(getMetricsServiceProvider()), DoubleCheck.lazy(getMAPAccountManagerProvider()), getNamedString(), DoubleCheck.lazy(getDeviceInformationProvider()));
                    this.alexaCommsServiceWrapper = DoubleCheck.reentrantCheck(this.alexaCommsServiceWrapper, obj);
                }
            }
            obj2 = obj;
        }
        return (AlexaCommsServiceWrapper) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlexaMobileAndroidFeatureServiceImpl getAlexaMobileAndroidFeatureServiceImpl() {
        Object obj;
        Object obj2 = this.alexaMobileAndroidFeatureServiceImpl;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.alexaMobileAndroidFeatureServiceImpl;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvidesAlexaMobileAndroidFeatureServiceImplFactory.proxyProvidesAlexaMobileAndroidFeatureServiceImpl(this.serviceModule, getDaggerInitializer());
                    this.alexaMobileAndroidFeatureServiceImpl = DoubleCheck.reentrantCheck(this.alexaMobileAndroidFeatureServiceImpl, obj);
                }
            }
            obj2 = obj;
        }
        return (AlexaMobileAndroidFeatureServiceImpl) obj2;
    }

    private Provider<AlexaMobileAndroidFeatureServiceImpl> getAlexaMobileAndroidFeatureServiceImplProvider() {
        Provider<AlexaMobileAndroidFeatureServiceImpl> provider = this.providesAlexaMobileAndroidFeatureServiceImplProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(8);
            this.providesAlexaMobileAndroidFeatureServiceImplProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private AlexaPhotosBackgroundService getAlexaPhotosBackgroundService() {
        Object obj;
        Object obj2 = this.alexaPhotosBackgroundService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.alexaPhotosBackgroundService;
                if (obj instanceof MemoizedSentinel) {
                    obj = PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceFactory.proxyProvideAlexaPhotosBackgroundService(this.photoServiceModule, getContext(), DoubleCheck.lazy(getCookieManagerProvider()), httpClient(), getGson(), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getAccountServiceProvider()), DoubleCheck.lazy(getRoutingServiceProvider()), getAlexaPhotosBackgroundServiceUrlResolver(), DoubleCheck.lazy(getMetricsServiceProvider()), getMetricsServiceV2(), DoubleCheck.lazy(getMobilyticsProvider()), DoubleCheck.lazy(getFeatureServiceV2Provider()), DoubleCheck.lazy(getEnvironmentServiceProvider()));
                    this.alexaPhotosBackgroundService = DoubleCheck.reentrantCheck(this.alexaPhotosBackgroundService, obj);
                }
            }
        } else {
            obj = obj2;
        }
        return (AlexaPhotosBackgroundService) obj;
    }

    private AlexaPhotosBackgroundServiceUrlResolver getAlexaPhotosBackgroundServiceUrlResolver() {
        Object obj;
        Object obj2 = this.alexaPhotosBackgroundServiceUrlResolver;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.alexaPhotosBackgroundServiceUrlResolver;
                if (obj instanceof MemoizedSentinel) {
                    obj = PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceUrlResolverFactory.proxyProvideAlexaPhotosBackgroundServiceUrlResolver(this.photoServiceModule, identityService());
                    this.alexaPhotosBackgroundServiceUrlResolver = DoubleCheck.reentrantCheck(this.alexaPhotosBackgroundServiceUrlResolver, obj);
                }
            }
            obj2 = obj;
        }
        return (AlexaPhotosBackgroundServiceUrlResolver) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlexaTarazedService getAlexaTarazedService() {
        Object obj;
        Object obj2 = this.alexaTarazedService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.alexaTarazedService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideAlexaTarazedServiceFactory.proxyProvideAlexaTarazedService(this.serviceModule);
                    this.alexaTarazedService = DoubleCheck.reentrantCheck(this.alexaTarazedService, obj);
                }
            }
            obj2 = obj;
        }
        return (AlexaTarazedService) obj2;
    }

    private Provider<AlexaTarazedService> getAlexaTarazedServiceProvider() {
        Provider<AlexaTarazedService> provider = this.provideAlexaTarazedServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(18);
            this.provideAlexaTarazedServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AmazonCloudDriveExtendedClient getAmazonCloudDriveExtendedClient() {
        Object obj;
        Object obj2 = this.amazonCloudDriveExtendedClient;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.amazonCloudDriveExtendedClient;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideAmazonCloudDriveExtendedClientFactory.proxyProvideAmazonCloudDriveExtendedClient(this.cloudDriveModule, getAccountConfiguration(), getClientConfiguration());
                    this.amazonCloudDriveExtendedClient = DoubleCheck.reentrantCheck(this.amazonCloudDriveExtendedClient, obj);
                }
            }
            obj2 = obj;
        }
        return (AmazonCloudDriveExtendedClient) obj2;
    }

    private Provider<AmazonCloudDriveExtendedClient> getAmazonCloudDriveExtendedClientProvider() {
        Provider<AmazonCloudDriveExtendedClient> provider = this.provideAmazonCloudDriveExtendedClientProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(58);
            this.provideAmazonCloudDriveExtendedClientProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private ApesCallerInterface getApesCallerInterface() {
        Object obj;
        Object obj2 = this.apesCallerInterface;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.apesCallerInterface;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideApesCallerFactory.proxyProvideApesCaller(this.identityModule, getCoralService(), metricsService());
                    this.apesCallerInterface = DoubleCheck.reentrantCheck(this.apesCallerInterface, obj);
                }
            }
            obj2 = obj;
        }
        return (ApesCallerInterface) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AppSessionClient getAppSessionClient() {
        Object obj;
        Object obj2 = this.appSessionClient;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.appSessionClient;
                if (obj instanceof MemoizedSentinel) {
                    obj = KinesisMetricsModule_ProvideKinesisSessionClientFactory.proxyProvideKinesisSessionClient(this.kinesisMetricsModule, getKinesisManager());
                    this.appSessionClient = DoubleCheck.reentrantCheck(this.appSessionClient, obj);
                }
            }
            obj2 = obj;
        }
        return (AppSessionClient) obj2;
    }

    private Provider<AppSessionClient> getAppSessionClientProvider() {
        Provider<AppSessionClient> provider = this.provideKinesisSessionClientProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(28);
            this.provideKinesisSessionClientProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Application getApplication() {
        Object obj;
        Object obj2 = this.application;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.application;
                if (obj instanceof MemoizedSentinel) {
                    obj = ApplicationModule_ProvideApplicationFactory.proxyProvideApplication(this.applicationModule);
                    this.application = DoubleCheck.reentrantCheck(this.application, obj);
                }
            }
            obj2 = obj;
        }
        return (Application) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AssetManagementService getAssetManagementService() {
        Object obj;
        Object obj2 = this.assetManagementService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.assetManagementService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideAssetManagementServiceFactory.proxyProvideAssetManagementService(this.serviceModule, getContext(), DoubleCheck.lazy(getEventBusProvider()), DoubleCheck.lazy(getMobilyticsProvider()));
                    this.assetManagementService = DoubleCheck.reentrantCheck(this.assetManagementService, obj);
                }
            }
            obj2 = obj;
        }
        return (AssetManagementService) obj2;
    }

    private Provider<AssetManagementService> getAssetManagementServiceProvider() {
        Provider<AssetManagementService> provider = this.provideAssetManagementServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(54);
            this.provideAssetManagementServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BackgroundImageService getBackgroundImageService() {
        Object obj;
        Object obj2 = this.backgroundImageService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.backgroundImageService;
                if (obj instanceof MemoizedSentinel) {
                    obj = AlexaDeviceBackgroundImageModule_ProvideBackgroundImageServiceFactory.proxyProvideBackgroundImageService(this.alexaDeviceBackgroundImageModule, getCoralService());
                    this.backgroundImageService = DoubleCheck.reentrantCheck(this.backgroundImageService, obj);
                }
            }
            obj2 = obj;
        }
        return (BackgroundImageService) obj2;
    }

    private Provider<BackgroundImageService> getBackgroundImageServiceProvider() {
        Provider<BackgroundImageService> provider = this.provideBackgroundImageServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(43);
            this.provideBackgroundImageServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothService getBluetoothService() {
        Object obj;
        Object obj2 = this.bluetoothService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.bluetoothService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideBluetoothServiceFactory.proxyProvideBluetoothService(this.serviceModule, getContext(), mainActivityLifecycleService(), DoubleCheck.lazy(getMobilyticsProvider()));
                    this.bluetoothService = DoubleCheck.reentrantCheck(this.bluetoothService, obj);
                }
            }
            obj2 = obj;
        }
        return (BluetoothService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CacheClearOperations getCacheClearOperations() {
        Object obj;
        Object obj2 = this.cacheClearOperations;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.cacheClearOperations;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCacheClearOperationsFactory.proxyProvideCacheClearOperations(this.serviceModule, getTaskManager(), identityService(), eventBus(), getNamedCacheOfHttpResponseWrapper(), getNamedDefaultElementLocalStorage2(), getNamedDefaultElementLocalStorage());
                    this.cacheClearOperations = DoubleCheck.reentrantCheck(this.cacheClearOperations, obj);
                }
            }
            obj2 = obj;
        }
        return (CacheClearOperations) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<CertificateReaderService> getCertificateReaderServiceProvider() {
        Provider<CertificateReaderService> provider = this.provideCertificateReaderServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(19);
            this.provideCertificateReaderServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private ClientConfiguration getClientConfiguration() {
        Object obj;
        Object obj2 = this.clientConfiguration;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.clientConfiguration;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideClientConfigurationFactory.proxyProvideClientConfiguration(this.cloudDriveModule, httpClient(), getContext());
                    this.clientConfiguration = DoubleCheck.reentrantCheck(this.clientConfiguration, obj);
                }
            }
            obj2 = obj;
        }
        return (ClientConfiguration) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CloudDriveMetrics getCloudDriveMetrics() {
        Object obj;
        Object obj2 = this.cloudDriveMetrics;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.cloudDriveMetrics;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideCloudDriveMetricsFactory.proxyProvideCloudDriveMetrics(this.cloudDriveModule, DoubleCheck.lazy(getMobilyticsProvider()));
                    this.cloudDriveMetrics = DoubleCheck.reentrantCheck(this.cloudDriveMetrics, obj);
                }
            }
            obj2 = obj;
        }
        return (CloudDriveMetrics) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<CloudDriveMetrics> getCloudDriveMetricsProvider() {
        Provider<CloudDriveMetrics> provider = this.provideCloudDriveMetricsProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(60);
            this.provideCloudDriveMetricsProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private CloudDriveService getCloudDriveService() {
        Object obj;
        Object obj2 = this.cloudDriveService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.cloudDriveService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCloudDriveServiceFactory.proxyProvideCloudDriveService(this.serviceModule, getContext(), identityService(), getAmazonCloudDriveExtendedClient(), persistentStorageFactory(), getMobilytics(), metricsService(), getTaskManager(), eventBus());
                    this.cloudDriveService = DoubleCheck.reentrantCheck(this.cloudDriveService, obj);
                }
            }
            obj2 = obj;
        }
        return (CloudDriveService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommsDeviceSupport getCommsDeviceSupport() {
        Object obj;
        Object obj2 = this.commsDeviceSupport;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.commsDeviceSupport;
                if (obj instanceof MemoizedSentinel) {
                    obj = CommsModule_ProvideCommsDeviceSupportFactory.proxyProvideCommsDeviceSupport(this.commsModule, commsService());
                    this.commsDeviceSupport = DoubleCheck.reentrantCheck(this.commsDeviceSupport, obj);
                }
            }
            obj2 = obj;
        }
        return (CommsDeviceSupport) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<CommsManager> getCommsManagerProvider() {
        Provider<CommsManager> provider = this.provideCommsManagerProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(69);
            this.provideCommsManagerProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<CommsServiceV2> getCommsServiceV2Provider() {
        Provider<CommsServiceV2> provider = this.provideCommsServiceV2Provider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(32);
            this.provideCommsServiceV2Provider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getContext() {
        Object obj;
        Object obj2 = this.context;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.context;
                if (obj instanceof MemoizedSentinel) {
                    obj = ApplicationModule_ProvideContextFactory.proxyProvideContext(this.applicationModule);
                    this.context = DoubleCheck.reentrantCheck(this.context, obj);
                }
            }
            obj2 = obj;
        }
        return (Context) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<Context> getContextProvider() {
        Provider<Context> provider = this.provideContextProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(34);
            this.provideContextProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<ConversationService> getConversationServiceProvider() {
        Provider<ConversationService> provider = this.provideConversationServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(68);
            this.provideConversationServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CookieManager getCookieManager() {
        Object obj;
        Object obj2 = this.cookieManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.cookieManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = NetworkModule_ProvideCookieManagerFactory.proxyProvideCookieManager(this.networkModule);
                    this.cookieManager = DoubleCheck.reentrantCheck(this.cookieManager, obj);
                }
            }
            obj2 = obj;
        }
        return (CookieManager) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<CookieManager> getCookieManagerProvider() {
        Provider<CookieManager> provider = this.provideCookieManagerProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(11);
            this.provideCookieManagerProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CoralService getCoralService() {
        Object obj;
        Object obj2 = this.coralService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.coralService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCoralServiceFactory.proxyProvideCoralService(this.serviceModule, DoubleCheck.lazy(getCookieManagerProvider()), httpClient(), getGson(), getUrlResolver(), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getAccountServiceProvider()), DoubleCheck.lazy(getRoutingServiceProvider()), getMetricsServiceV2(), DoubleCheck.lazy(getMetricsServiceProvider()), DoubleCheck.lazy(getMobilyticsProvider()), DoubleCheck.lazy(getFeatureServiceV2Provider()), DoubleCheck.lazy(getEnvironmentServiceProvider()));
                    this.coralService = DoubleCheck.reentrantCheck(this.coralService, obj);
                }
            }
        } else {
            obj = obj2;
        }
        return (CoralService) obj;
    }

    private Provider<CoralService> getCoralServiceProvider() {
        Provider<CoralService> provider = this.provideCoralServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(10);
            this.provideCoralServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private Provider<CrashMetadata> getCrashMetadataProvider() {
        Provider<CrashMetadata> provider = this.provideCrashMetadataProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(1);
            this.provideCrashMetadataProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CrashObserverRegistrar getCrashObserverRegistrar() {
        Object obj;
        Object obj2 = this.crashObserverRegistrar;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.crashObserverRegistrar;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCrashObserverRegistrarFactory.proxyProvideCrashObserverRegistrar(this.serviceModule, DoubleCheck.lazy(getCrashReportingServiceProvider()));
                    this.crashObserverRegistrar = DoubleCheck.reentrantCheck(this.crashObserverRegistrar, obj);
                }
            }
            obj2 = obj;
        }
        return (CrashObserverRegistrar) obj2;
    }

    private Provider<CrashObserverRegistrar> getCrashObserverRegistrarProvider() {
        Provider<CrashObserverRegistrar> provider = this.provideCrashObserverRegistrarProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(48);
            this.provideCrashObserverRegistrarProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CrashReporter getCrashReporter() {
        Object obj;
        Object obj2 = this.crashReporter;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.crashReporter;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCrashReporterFactory.proxyProvideCrashReporter(this.serviceModule, DoubleCheck.lazy(getCrashReportingServiceProvider()));
                    this.crashReporter = DoubleCheck.reentrantCheck(this.crashReporter, obj);
                }
            }
            obj2 = obj;
        }
        return (CrashReporter) obj2;
    }

    private Provider<CrashReporter> getCrashReporterProvider() {
        Provider<CrashReporter> provider = this.provideCrashReporterProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(2);
            this.provideCrashReporterProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CrashReportingService getCrashReportingService() {
        Object obj;
        Object obj2 = this.crashReportingService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.crashReportingService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCrashReportingServiceFactory.proxyProvideCrashReportingService(this.serviceModule, getContext());
                    this.crashReportingService = DoubleCheck.reentrantCheck(this.crashReportingService, obj);
                }
            }
            obj2 = obj;
        }
        return (CrashReportingService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<CrashReportingService> getCrashReportingServiceProvider() {
        Provider<CrashReportingService> provider = this.provideCrashReportingServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(3);
            this.provideCrashReportingServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private CryptoFactory getCryptoFactory() {
        Object obj;
        Object obj2 = this.cryptoFactory;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.cryptoFactory;
                if (obj instanceof MemoizedSentinel) {
                    obj = MessagingModule_ProvideCryptoFactoryFactory.proxyProvideCryptoFactory(this.messagingModule);
                    this.cryptoFactory = DoubleCheck.reentrantCheck(this.cryptoFactory, obj);
                }
            }
            obj2 = obj;
        }
        return (CryptoFactory) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DCMMetricsConnector getDCMMetricsConnector() {
        return MetricsModule_ProvideDCMMetricsConnectorFactory.proxyProvideDCMMetricsConnector(this.metricsModule, getContext(), getEnvironmentService(), DoubleCheck.lazy(getMetricsFactoryProvider()));
    }

    private Provider<DCMMetricsConnector> getDCMMetricsConnectorProvider() {
        Provider<DCMMetricsConnector> provider = this.provideDCMMetricsConnectorProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(25);
            this.provideDCMMetricsConnectorProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private DaggerInitializer getDaggerInitializer() {
        Object obj;
        Object obj2 = this.daggerInitializer;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.daggerInitializer;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvidesFsv2DaggerInitializerFactory.proxyProvidesFsv2DaggerInitializer(this.serviceModule, getContext(), DoubleCheck.lazy(getOkHttpClientProvider()), DoubleCheck.lazy(getEnvironmentServiceProvider()), DoubleCheck.lazy(getEventBusProvider()), DoubleCheck.lazy(getCoralServiceProvider()), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getCookieManagerProvider()), DoubleCheck.lazy(getAccountServiceProvider()), DoubleCheck.lazy(getRoutingServiceProvider()), DoubleCheck.lazy(getMobilyticsProvider()), DoubleCheck.lazy(getFeatureQueryProvider()), DoubleCheck.lazy(getFeatureServiceV2Provider()));
                    this.daggerInitializer = DoubleCheck.reentrantCheck(this.daggerInitializer, obj);
                }
            }
        } else {
            obj = obj2;
        }
        return (DaggerInitializer) obj;
    }

    private DataRegionEnvironmentService getDataRegionEnvironmentService() {
        Object obj;
        Object obj2 = this.dataRegionEnvironmentService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.dataRegionEnvironmentService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideDataRegionEnvironmentServiceFactory.proxyProvideDataRegionEnvironmentService(this.serviceModule, getContext(), eventBus(), getUserIdentityStorage(), getPersistentEndpointsStorage(), DoubleCheck.lazy(getCoralServiceProvider()), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getMobilyticsProvider()));
                    this.dataRegionEnvironmentService = DoubleCheck.reentrantCheck(this.dataRegionEnvironmentService, obj);
                }
            }
            obj2 = obj;
        }
        return (DataRegionEnvironmentService) obj2;
    }

    private DataStoreHelper getDataStoreHelper() {
        Object obj;
        Object obj2 = this.dataStoreHelper;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.dataStoreHelper;
                if (obj instanceof MemoizedSentinel) {
                    obj = DataStoreModule_ProvideDataStoreHelperFactory.proxyProvideDataStoreHelper(this.dataStoreModule, getContext());
                    this.dataStoreHelper = DoubleCheck.reentrantCheck(this.dataStoreHelper, obj);
                }
            }
            obj2 = obj;
        }
        return (DataStoreHelper) obj2;
    }

    private DataStoreService getDataStoreService() {
        Object obj;
        Object obj2 = this.dataStoreService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.dataStoreService;
                if (obj instanceof MemoizedSentinel) {
                    obj = DataStoreModule_ProvideProtocolsDataStoreServiceFactory.proxyProvideProtocolsDataStoreService(this.dataStoreModule, getDataStoreHelper());
                    this.dataStoreService = DoubleCheck.reentrantCheck(this.dataStoreService, obj);
                }
            }
            obj2 = obj;
        }
        return (DataStoreService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.amazon.dee.app.services.datastore.DataStoreService getDataStoreService2() {
        Object obj;
        Object obj2 = this.dataStoreService2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.dataStoreService2;
                if (obj instanceof MemoizedSentinel) {
                    obj = DataStoreModule_ProvideDataStoreServiceFactory.proxyProvideDataStoreService(this.dataStoreModule, getDataStoreHelper());
                    this.dataStoreService2 = DoubleCheck.reentrantCheck(this.dataStoreService2, obj);
                }
            }
            obj2 = obj;
        }
        return (com.amazon.dee.app.services.datastore.DataStoreService) obj2;
    }

    private Provider<DefaultApplicationLifecycleService> getDefaultApplicationLifecycleServiceProvider() {
        Provider<DefaultApplicationLifecycleService> provider = this.provideApplicationLifecycleServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(40);
            this.provideApplicationLifecycleServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private DefaultFeatureServiceV2 getDefaultFeatureServiceV2() {
        Object obj;
        Object obj2 = this.defaultFeatureServiceV2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.defaultFeatureServiceV2;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideDefaultFeatureServiceV2Factory.proxyProvideDefaultFeatureServiceV2(this.serviceModule, getContext(), DoubleCheck.lazy(getEventBusProvider()), DoubleCheck.lazy(getCoralServiceProvider()), DoubleCheck.lazy(getMobilyticsProvider()));
                    this.defaultFeatureServiceV2 = DoubleCheck.reentrantCheck(this.defaultFeatureServiceV2, obj);
                }
            }
            obj2 = obj;
        }
        return (DefaultFeatureServiceV2) obj2;
    }

    private DelegatedTokenManagement getDelegatedTokenManagement() {
        Object obj;
        Object obj2 = this.delegatedTokenManagement;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.delegatedTokenManagement;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideDelegatedTokenManagementFactory.proxyProvideDelegatedTokenManagement(this.identityModule, getTokenAccessor(), getTokenStorage());
                    this.delegatedTokenManagement = DoubleCheck.reentrantCheck(this.delegatedTokenManagement, obj);
                }
            }
            obj2 = obj;
        }
        return (DelegatedTokenManagement) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceInformation getDeviceInformation() {
        Object obj;
        Object obj2 = this.deviceInformation;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.deviceInformation;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideDeviceInformationFactory.proxyProvideDeviceInformation(this.serviceModule);
                    this.deviceInformation = DoubleCheck.reentrantCheck(this.deviceInformation, obj);
                }
            }
            obj2 = obj;
        }
        return (DeviceInformation) obj2;
    }

    private Provider<DeviceInformation> getDeviceInformationProvider() {
        Provider<DeviceInformation> provider = this.provideDeviceInformationProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(35);
            this.provideDeviceInformationProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DialogBuilderProvider getDialogBuilderProvider() {
        Object obj;
        Object obj2 = this.dialogBuilderProvider;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.dialogBuilderProvider;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideDialogBuilderProviderFactory.proxyProvideDialogBuilderProvider(this.serviceModule);
                    this.dialogBuilderProvider = DoubleCheck.reentrantCheck(this.dialogBuilderProvider, obj);
                }
            }
            obj2 = obj;
        }
        return (DialogBuilderProvider) obj2;
    }

    private Provider<DialogBuilderProvider> getDialogBuilderProviderProvider() {
        Provider<DialogBuilderProvider> provider = this.provideDialogBuilderProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(55);
            this.provideDialogBuilderProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DriveModeService getDriveModeService() {
        Object obj;
        Object obj2 = this.driveModeService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.driveModeService;
                if (obj instanceof MemoizedSentinel) {
                    obj = DriveModeApplicationModule_ProvideDriveModeServiceFactory.proxyProvideDriveModeService(this.driveModeApplicationModule);
                    this.driveModeService = DoubleCheck.reentrantCheck(this.driveModeService, obj);
                }
            }
            obj2 = obj;
        }
        return (DriveModeService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<DriveModeService> getDriveModeServiceProvider() {
        Provider<DriveModeService> provider = this.provideDriveModeServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(71);
            this.provideDriveModeServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private EndpointsCache getEndpointsCache() {
        Object obj;
        Object obj2 = this.endpointsCache;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.endpointsCache;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideEndpointsCacheFactory.proxyProvideEndpointsCache(this.cloudDriveModule, getContext(), identityService(), eventBus(), persistentStorageFactory());
                    this.endpointsCache = DoubleCheck.reentrantCheck(this.endpointsCache, obj);
                }
            }
            obj2 = obj;
        }
        return (EndpointsCache) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EnrollmentGateway getEnrollmentGateway() {
        return EnrollmentModule_ProvideEnrollmentServiceFactory.proxyProvideEnrollmentService(this.enrollmentModule, DoubleCheck.lazy(getContextProvider()), DoubleCheck.lazy(getCoralServiceProvider()), DoubleCheck.lazy(getPersonIdProviderProvider()), DoubleCheck.lazy(getMobilyticsProvider()), DoubleCheck.lazy(getDeviceInformationProvider()), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getEventBusProvider()), DoubleCheck.lazy(getRoutingServiceProvider()), DoubleCheck.lazy(getUrlResolverProvider()), DoubleCheck.lazy(getEnvironmentServiceProvider()));
    }

    private Provider<EnrollmentGateway> getEnrollmentGatewayProvider() {
        Provider<EnrollmentGateway> provider = this.provideEnrollmentServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(64);
            this.provideEnrollmentServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EnrollmentRoutingAdapter getEnrollmentRoutingAdapter() {
        return EnrollmentModule_ProvideEnrollmentRoutingAdapterFactory.proxyProvideEnrollmentRoutingAdapter(this.enrollmentModule, DoubleCheck.lazy(getContextProvider()), DoubleCheck.lazy(getEnrollmentGatewayProvider()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EnvironmentService getEnvironmentService() {
        Object obj;
        Object obj2 = this.environmentService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.environmentService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideEnvironmentServiceFactory.proxyProvideEnvironmentService(this.serviceModule, getPfmEnvironmentService(), getDataRegionEnvironmentService(), DoubleCheck.lazy(getIdentityServiceProvider()));
                    this.environmentService = DoubleCheck.reentrantCheck(this.environmentService, obj);
                }
            }
            obj2 = obj;
        }
        return (EnvironmentService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<EnvironmentService> getEnvironmentServiceProvider() {
        Provider<EnvironmentService> provider = this.provideEnvironmentServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(9);
            this.provideEnvironmentServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EventBusMessagingReceiver getEventBusMessagingReceiver() {
        Object obj;
        Object obj2 = this.eventBusMessagingReceiver;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.eventBusMessagingReceiver;
                if (obj instanceof MemoizedSentinel) {
                    obj = ApplicationModule_ProvideConcreteEventBusMessagingReceiverFactory.proxyProvideConcreteEventBusMessagingReceiver(this.applicationModule, eventBus());
                    this.eventBusMessagingReceiver = DoubleCheck.reentrantCheck(this.eventBusMessagingReceiver, obj);
                }
            }
            obj2 = obj;
        }
        return (EventBusMessagingReceiver) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<EventBus> getEventBusProvider() {
        Provider<EventBus> provider = this.provideEventBusProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(15);
            this.provideEventBusProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private Provider<PersistentStorage.Factory> getFactoryProvider() {
        Provider<PersistentStorage.Factory> provider = this.providePersistentStorageFactoryProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(38);
            this.providePersistentStorageFactoryProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private FeatureConstraints getFeatureConstraints() {
        Object obj;
        Object obj2 = this.featureConstraints;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.featureConstraints;
                if (obj instanceof MemoizedSentinel) {
                    obj = FeaturesModule_ProvideFeatureConstraintsFactory.proxyProvideFeatureConstraints(this.featuresModule, getSetOfFeatureFilter(), getEnvironmentService());
                    this.featureConstraints = DoubleCheck.reentrantCheck(this.featureConstraints, obj);
                }
            }
            obj2 = obj;
        }
        return (FeatureConstraints) obj2;
    }

    private FeatureFilter getFeatureFilter() {
        Object obj;
        Object obj2 = this.featureFilter;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.featureFilter;
                if (obj instanceof MemoizedSentinel) {
                    obj = FeaturesModule_ProvideCommsFeatureFilterFactory.proxyProvideCommsFeatureFilter(this.featuresModule, commsService());
                    this.featureFilter = DoubleCheck.reentrantCheck(this.featureFilter, obj);
                }
            }
            obj2 = obj;
        }
        return (FeatureFilter) obj2;
    }

    private FeatureFilter getFeatureFilter2() {
        Object obj;
        Object obj2 = this.featureFilter2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.featureFilter2;
                if (obj instanceof MemoizedSentinel) {
                    obj = FeaturesModule_ProvideVoiceIngressFeatureFilterFactory.proxyProvideVoiceIngressFeatureFilter(this.featuresModule, DoubleCheck.lazy(getVoiceServiceProvider()));
                    this.featureFilter2 = DoubleCheck.reentrantCheck(this.featureFilter2, obj);
                }
            }
            obj2 = obj;
        }
        return (FeatureFilter) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeatureQuery getFeatureQuery() {
        Object obj;
        Object obj2 = this.featureQuery;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.featureQuery;
                if (obj instanceof MemoizedSentinel) {
                    obj = FeaturesModule_ProvideFeatureQueryFactory.proxyProvideFeatureQuery(this.featuresModule, DoubleCheck.lazy(getIdentityServiceProvider()));
                    this.featureQuery = DoubleCheck.reentrantCheck(this.featureQuery, obj);
                }
            }
            obj2 = obj;
        }
        return (FeatureQuery) obj2;
    }

    private Provider<FeatureQuery> getFeatureQueryProvider() {
        Provider<FeatureQuery> provider = this.provideFeatureQueryProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(6);
            this.provideFeatureQueryProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeatureServiceConfiguration getFeatureServiceConfiguration() {
        Object obj;
        Object obj2 = this.featureServiceConfiguration;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.featureServiceConfiguration;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvidesFsv2TestConfigurationFactory.proxyProvidesFsv2TestConfiguration(this.serviceModule, getDaggerInitializer());
                    this.featureServiceConfiguration = DoubleCheck.reentrantCheck(this.featureServiceConfiguration, obj);
                }
            }
            obj2 = obj;
        }
        return (FeatureServiceConfiguration) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<FeatureServiceConfiguration> getFeatureServiceConfigurationProvider() {
        Provider<FeatureServiceConfiguration> provider = this.providesFsv2TestConfigurationProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(73);
            this.providesFsv2TestConfigurationProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeatureServiceV2 getFeatureServiceV2() {
        Object obj;
        Object obj2 = this.featureServiceV2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.featureServiceV2;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideFeatureServiceV2Factory.proxyProvideFeatureServiceV2(this.serviceModule, getPlatformFeatureServiceV2());
                    this.featureServiceV2 = DoubleCheck.reentrantCheck(this.featureServiceV2, obj);
                }
            }
            obj2 = obj;
        }
        return (FeatureServiceV2) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<FeatureServiceV2> getFeatureServiceV2Provider() {
        Provider<FeatureServiceV2> provider = this.provideFeatureServiceV2Provider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(14);
            this.provideFeatureServiceV2Provider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeatureStore getFeatureStore() {
        Object obj;
        Object obj2 = this.featureStore;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.featureStore;
                if (obj instanceof MemoizedSentinel) {
                    obj = FeaturesModule_ProvideFeatureStoreFactory.proxyProvideFeatureStore(this.featuresModule);
                    this.featureStore = DoubleCheck.reentrantCheck(this.featureStore, obj);
                }
            }
            obj2 = obj;
        }
        return (FeatureStore) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<FeatureStore> getFeatureStoreProvider() {
        Provider<FeatureStore> provider = this.provideFeatureStoreProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(72);
            this.provideFeatureStoreProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FirebaseInstanceId getFirebaseInstanceId() {
        Object obj;
        Object obj2 = this.firebaseInstanceId;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.firebaseInstanceId;
                if (obj instanceof MemoizedSentinel) {
                    obj = GoogleApiModule_ProvideInstanceIDFactory.proxyProvideInstanceID(this.googleApiModule);
                    this.firebaseInstanceId = DoubleCheck.reentrantCheck(this.firebaseInstanceId, obj);
                }
            }
            obj2 = obj;
        }
        return (FirebaseInstanceId) obj2;
    }

    private Provider<FirebaseInstanceId> getFirebaseInstanceIdProvider() {
        Provider<FirebaseInstanceId> provider = this.provideInstanceIDProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(22);
            this.provideInstanceIDProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Gson getGson() {
        Object obj;
        Object obj2 = this.gson;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.gson;
                if (obj instanceof MemoizedSentinel) {
                    obj = NetworkModule_ProvideGsonFactory.proxyProvideGson(this.networkModule);
                    this.gson = DoubleCheck.reentrantCheck(this.gson, obj);
                }
            }
            obj2 = obj;
        }
        return (Gson) obj2;
    }

    private HVAManager getHVAManager() {
        Object obj;
        Object obj2 = this.hVAManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.hVAManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideHVAManagerFactory.proxyProvideHVAManager(this.cloudDriveModule, DoubleCheck.lazy(getCloudDriveMetricsProvider()));
                    this.hVAManager = DoubleCheck.reentrantCheck(this.hVAManager, obj);
                }
            }
            obj2 = obj;
        }
        return (HVAManager) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HeaderCacheService getHeaderCacheService() {
        Object obj;
        Object obj2 = this.headerCacheService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.headerCacheService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideHeaderCacheServiceFactory.proxyProvideHeaderCacheService(this.serviceModule);
                    this.headerCacheService = DoubleCheck.reentrantCheck(this.headerCacheService, obj);
                }
            }
            obj2 = obj;
        }
        return (HeaderCacheService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IdentityPreferencesProvider getIdentityPreferencesProvider() {
        Object obj;
        Object obj2 = this.identityPreferencesProvider;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.identityPreferencesProvider;
                if (obj instanceof MemoizedSentinel) {
                    obj = CommsModule_ProvideIdentityPreferencesProviderFactory.proxyProvideIdentityPreferencesProvider(this.commsModule, getContext());
                    this.identityPreferencesProvider = DoubleCheck.reentrantCheck(this.identityPreferencesProvider, obj);
                }
            }
            obj2 = obj;
        }
        return (IdentityPreferencesProvider) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<IdentityService> getIdentityServiceProvider() {
        Provider<IdentityService> provider = this.provideIdentityServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(7);
            this.provideIdentityServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IntentFactory getIntentFactory() {
        Object obj;
        Object obj2 = this.intentFactory;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.intentFactory;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideIntentFactoryFactory.proxyProvideIntentFactory(this.serviceModule, getContext());
                    this.intentFactory = DoubleCheck.reentrantCheck(this.intentFactory, obj);
                }
            }
            obj2 = obj;
        }
        return (IntentFactory) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JsonConverter getJsonConverter() {
        Object obj;
        Object obj2 = this.jsonConverter;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.jsonConverter;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideJsonConverterFactory.proxyProvideJsonConverter(this.serviceModule, getGson());
                    this.jsonConverter = DoubleCheck.reentrantCheck(this.jsonConverter, obj);
                }
            }
            obj2 = obj;
        }
        return (JsonConverter) obj2;
    }

    private Provider<JsonConverter> getJsonConverterProvider() {
        Provider<JsonConverter> provider = this.provideJsonConverterProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(52);
            this.provideJsonConverterProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public KidsEnrollmentRoutingAdapter getKidsEnrollmentRoutingAdapter() {
        return EnrollmentModule_ProvideKidsEnrollmentRoutingAdapterFactory.proxyProvideKidsEnrollmentRoutingAdapter(this.enrollmentModule, DoubleCheck.lazy(getContextProvider()), DoubleCheck.lazy(getEnrollmentGatewayProvider()), DoubleCheck.lazy(getIdentityServiceProvider()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public KinesisEventClient getKinesisEventClient() {
        Object obj;
        Object obj2 = this.kinesisEventClient;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.kinesisEventClient;
                if (obj instanceof MemoizedSentinel) {
                    obj = KinesisMetricsModule_ProvideKinesisEventClientFactory.proxyProvideKinesisEventClient(this.kinesisMetricsModule, getKinesisManager());
                    this.kinesisEventClient = DoubleCheck.reentrantCheck(this.kinesisEventClient, obj);
                }
            }
            obj2 = obj;
        }
        return (KinesisEventClient) obj2;
    }

    private Provider<KinesisEventClient> getKinesisEventClientProvider() {
        Provider<KinesisEventClient> provider = this.provideKinesisEventClientProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(29);
            this.provideKinesisEventClientProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private KinesisManager getKinesisManager() {
        Object obj;
        Object obj2 = this.kinesisManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.kinesisManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = KinesisMetricsModule_ProvideKinesisManagerFactory.proxyProvideKinesisManager(this.kinesisMetricsModule, getContext(), KinesisMetricsModule_ProvideKinesisEnvironmentFactory.proxyProvideKinesisEnvironment(this.kinesisMetricsModule), persistentStorageFactory(), getNamedAWSCredentialsProvider(), identityService());
                    this.kinesisManager = DoubleCheck.reentrantCheck(this.kinesisManager, obj);
                }
            }
            obj2 = obj;
        }
        return (KinesisManager) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public KinesisMetricsConnector getKinesisMetricsConnector() {
        return KinesisMetricsModule_ProvideKinesisMetricsConnectorFactory.proxyProvideKinesisMetricsConnector(this.kinesisMetricsModule, getContext(), DoubleCheck.lazy(getIdentityServiceProvider()), getEnvironmentService(), getDeviceInformation(), DoubleCheck.lazy(getAppSessionClientProvider()), DoubleCheck.lazy(getKinesisEventClientProvider()));
    }

    private Provider<KinesisMetricsConnector> getKinesisMetricsConnectorProvider() {
        Provider<KinesisMetricsConnector> provider = this.provideKinesisMetricsConnectorProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(27);
            this.provideKinesisMetricsConnectorProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LatencyInfra getLatencyInfra() {
        Object obj;
        Object obj2 = this.latencyInfra;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.latencyInfra;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideLatencyInfraFactory.proxyProvideLatencyInfra(this.serviceModule);
                    this.latencyInfra = DoubleCheck.reentrantCheck(this.latencyInfra, obj);
                }
            }
            obj2 = obj;
        }
        return (LatencyInfra) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LatencyReportingDelegate getLatencyReportingDelegate() {
        Object obj;
        Object obj2 = this.latencyReportingDelegate;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.latencyReportingDelegate;
                if (obj instanceof MemoizedSentinel) {
                    obj = VoiceModule_ProvideLatencyReportingDelegateFactory.proxyProvideLatencyReportingDelegate(this.voiceModule);
                    this.latencyReportingDelegate = DoubleCheck.reentrantCheck(this.latencyReportingDelegate, obj);
                }
            }
            obj2 = obj;
        }
        return (LatencyReportingDelegate) obj2;
    }

    private Provider<LatencyReportingDelegate> getLatencyReportingDelegateProvider() {
        Provider<LatencyReportingDelegate> provider = this.provideLatencyReportingDelegateProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(39);
            this.provideLatencyReportingDelegateProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private LocalAndroidKeyValueStore getLocalAndroidKeyValueStore() {
        Object obj;
        Object obj2 = this.localAndroidKeyValueStore;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.localAndroidKeyValueStore;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideLocalAndroidKeyValueStoreFactory.proxyProvideLocalAndroidKeyValueStore(this.identityModule, getContext());
                    this.localAndroidKeyValueStore = DoubleCheck.reentrantCheck(this.localAndroidKeyValueStore, obj);
                }
            }
            obj2 = obj;
        }
        return (LocalAndroidKeyValueStore) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocationProvider getLocationProvider() {
        Object obj;
        Object obj2 = this.locationProvider;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.locationProvider;
                if (obj instanceof MemoizedSentinel) {
                    obj = LocationModule_ProvideLocationProviderFactory.proxyProvideLocationProvider(this.locationModule, getContext());
                    this.locationProvider = DoubleCheck.reentrantCheck(this.locationProvider, obj);
                }
            }
            obj2 = obj;
        }
        return (LocationProvider) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<LocationProvider> getLocationProviderProvider() {
        Provider<LocationProvider> provider = this.provideLocationProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(66);
            this.provideLocationProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private Provider<LocationService> getLocationServiceProvider() {
        Provider<LocationService> provider = this.provideLocationServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(51);
            this.provideLocationServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MAPAccountManager getMAPAccountManager() {
        Object obj;
        Object obj2 = this.mAPAccountManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mAPAccountManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideMAPAccountManagerFactory.proxyProvideMAPAccountManager(this.identityModule, getContext());
                    this.mAPAccountManager = DoubleCheck.reentrantCheck(this.mAPAccountManager, obj);
                }
            }
            obj2 = obj;
        }
        return (MAPAccountManager) obj2;
    }

    private Provider<MAPAccountManager> getMAPAccountManagerProvider() {
        Provider<MAPAccountManager> provider = this.provideMAPAccountManagerProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(41);
            this.provideMAPAccountManagerProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MAPAccountRegistrationService getMAPAccountRegistrationService() {
        Object obj;
        Object obj2 = this.mAPAccountRegistrationService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mAPAccountRegistrationService;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideAccountRegistrationServiceFactory.proxyProvideAccountRegistrationService(this.identityModule, getMAPAccountManager(), getNamedString());
                    this.mAPAccountRegistrationService = DoubleCheck.reentrantCheck(this.mAPAccountRegistrationService, obj);
                }
            }
            obj2 = obj;
        }
        return (MAPAccountRegistrationService) obj2;
    }

    private Provider<MAPAccountRegistrationService> getMAPAccountRegistrationServiceProvider() {
        Provider<MAPAccountRegistrationService> provider = this.provideAccountRegistrationServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(33);
            this.provideAccountRegistrationServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private MAPAccountUpgradeService getMAPAccountUpgradeService() {
        Object obj;
        Object obj2 = this.mAPAccountUpgradeService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mAPAccountUpgradeService;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideImplementationAccountUpgradeServiceFactory.proxyProvideImplementationAccountUpgradeService(this.identityModule, getMAPAccountManager(), getNamedString());
                    this.mAPAccountUpgradeService = DoubleCheck.reentrantCheck(this.mAPAccountUpgradeService, obj);
                }
            }
            obj2 = obj;
        }
        return (MAPAccountUpgradeService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MAPAuthenticatedURLConnectionFactory getMAPAuthenticatedURLConnectionFactory() {
        Object obj;
        Object obj2 = this.mAPAuthenticatedURLConnectionFactory;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mAPAuthenticatedURLConnectionFactory;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory.proxyProvideMAPAuthenticatedURLConnectionFactory(this.cloudDriveModule, getContext(), identityService());
                    this.mAPAuthenticatedURLConnectionFactory = DoubleCheck.reentrantCheck(this.mAPAuthenticatedURLConnectionFactory, obj);
                }
            }
            obj2 = obj;
        }
        return (MAPAuthenticatedURLConnectionFactory) obj2;
    }

    private Provider<MAPAuthenticatedURLConnectionFactory> getMAPAuthenticatedURLConnectionFactoryProvider() {
        Provider<MAPAuthenticatedURLConnectionFactory> provider = this.provideMAPAuthenticatedURLConnectionFactoryProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(61);
            this.provideMAPAuthenticatedURLConnectionFactoryProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private MAPIdentityService getMAPIdentityService() {
        Object obj;
        Object obj2 = this.mAPIdentityService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mAPIdentityService;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideImplementationMAPIdentityServiceFactory.proxyProvideImplementationMAPIdentityService(this.identityModule, getContext(), getMAPAccountManager(), DoubleCheck.lazy(getCookieManagerProvider()), getTokenManagement(), getDataStoreService(), getUserIdentityRepository(), getUserProfileManager(), DoubleCheck.lazy(getMAPAccountRegistrationServiceProvider()), getDelegatedTokenManagement(), getApesCallerInterface());
                    this.mAPIdentityService = DoubleCheck.reentrantCheck(this.mAPIdentityService, obj);
                }
            }
            obj2 = obj;
        }
        return (MAPIdentityService) obj2;
    }

    private Provider<MainActivityLifecycleService> getMainActivityLifecycleServiceProvider() {
        Provider<MainActivityLifecycleService> provider = this.provideMainActivityLifecycleServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(45);
            this.provideMainActivityLifecycleServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MarketplaceService getMarketplaceService() {
        Object obj;
        Object obj2 = this.marketplaceService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.marketplaceService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideMarketplaceServiceFactory.proxyProvideMarketplaceService(this.serviceModule, persistentStorageFactory(), DoubleCheck.lazy(getCoralServiceProvider()));
                    this.marketplaceService = DoubleCheck.reentrantCheck(this.marketplaceService, obj);
                }
            }
            obj2 = obj;
        }
        return (MarketplaceService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.amazon.alexa.protocols.marketplace.MarketplaceService getMarketplaceService2() {
        Object obj;
        Object obj2 = this.marketplaceService2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.marketplaceService2;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideProtocolsMarketplaceServiceFactory.proxyProvideProtocolsMarketplaceService(this.serviceModule, persistentStorageFactory(), DoubleCheck.lazy(getCoralServiceProvider()));
                    this.marketplaceService2 = DoubleCheck.reentrantCheck(this.marketplaceService2, obj);
                }
            }
            obj2 = obj;
        }
        return (com.amazon.alexa.protocols.marketplace.MarketplaceService) obj2;
    }

    private Provider<MarketplaceService> getMarketplaceServiceProvider() {
        Provider<MarketplaceService> provider = this.provideMarketplaceServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(46);
            this.provideMarketplaceServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private Provider<com.amazon.alexa.protocols.marketplace.MarketplaceService> getMarketplaceServiceProvider2() {
        Provider<com.amazon.alexa.protocols.marketplace.MarketplaceService> provider = this.provideProtocolsMarketplaceServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(47);
            this.provideProtocolsMarketplaceServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MatterService getMatterService() {
        Object obj;
        Object obj2 = this.matterService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.matterService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideMatterServiceFactory.proxyProvideMatterService(this.serviceModule, eventBus(), getContext());
                    this.matterService = DoubleCheck.reentrantCheck(this.matterService, obj);
                }
            }
            obj2 = obj;
        }
        return (MatterService) obj2;
    }

    private Provider<MatterService> getMatterServiceProvider() {
        Provider<MatterService> provider = this.provideMatterServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(20);
            this.provideMatterServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private MessageCrypto getMessageCrypto() {
        Object obj;
        Object obj2 = this.messageCrypto;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messageCrypto;
                if (obj instanceof MemoizedSentinel) {
                    obj = MessagingModule_ProvideMessageCryptoFactory.proxyProvideMessageCrypto(this.messagingModule, DoubleCheck.lazy(getMobilyticsProvider()), persistentStorageFactory(), getContext(), getCryptoFactory());
                    this.messageCrypto = DoubleCheck.reentrantCheck(this.messageCrypto, obj);
                }
            }
            obj2 = obj;
        }
        return (MessageCrypto) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MessagingHandler getMessagingHandler() {
        Object obj;
        Object obj2 = this.messagingHandler;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messagingHandler;
                if (obj instanceof MemoizedSentinel) {
                    obj = MessagingModule_ProvideMessagingHandlerFactory.proxyProvideMessagingHandler(this.messagingModule, DoubleCheck.lazy(getMobilyticsProvider()), identityService(), getMessageCrypto(), getSetOfMessagingReceiver(), DoubleCheck.lazy(getFeatureServiceV2Provider()));
                    this.messagingHandler = DoubleCheck.reentrantCheck(this.messagingHandler, obj);
                }
            }
            obj2 = obj;
        }
        return (MessagingHandler) obj2;
    }

    private Provider<MessagingHandler> getMessagingHandlerProvider() {
        Provider<MessagingHandler> provider = this.provideMessagingHandlerProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(23);
            this.provideMessagingHandlerProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private MessagingReceiver getMessagingReceiver() {
        Object obj;
        Object obj2 = this.messagingReceiver;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messagingReceiver;
                if (obj instanceof MemoizedSentinel) {
                    obj = ApplicationModule_ProvideMainMessagingReceiverFactory.proxyProvideMainMessagingReceiver(this.applicationModule, getContext(), DoubleCheck.lazy(getMobilyticsProvider()), DoubleCheck.lazy(getEnvironmentServiceProvider()), DoubleCheck.lazy(getFeatureServiceV2Provider()));
                    this.messagingReceiver = DoubleCheck.reentrantCheck(this.messagingReceiver, obj);
                }
            }
            obj2 = obj;
        }
        return (MessagingReceiver) obj2;
    }

    private MessagingReceiver getMessagingReceiver2() {
        Object obj;
        Object obj2 = this.messagingReceiver2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messagingReceiver2;
                if (obj instanceof MemoizedSentinel) {
                    obj = ApplicationModule_ProvideEventBusMessagingReceiverFactory.proxyProvideEventBusMessagingReceiver(this.applicationModule, getEventBusMessagingReceiver());
                    this.messagingReceiver2 = DoubleCheck.reentrantCheck(this.messagingReceiver2, obj);
                }
            }
            obj2 = obj;
        }
        return (MessagingReceiver) obj2;
    }

    private MessagingReceiver getMessagingReceiver3() {
        Object obj;
        Object obj2 = this.messagingReceiver3;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messagingReceiver3;
                if (obj instanceof MemoizedSentinel) {
                    obj = CommsModule_ProvideConversationMessagingReceiverFactory.proxyProvideConversationMessagingReceiver(this.commsModule, commsServiceV2());
                    this.messagingReceiver3 = DoubleCheck.reentrantCheck(this.messagingReceiver3, obj);
                }
            }
            obj2 = obj;
        }
        return (MessagingReceiver) obj2;
    }

    private MessagingReceiver getMessagingReceiver4() {
        Object obj;
        Object obj2 = this.messagingReceiver4;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messagingReceiver4;
                if (obj instanceof MemoizedSentinel) {
                    obj = VoiceModule_ProvideVoiceMessagingReceiverFactory.proxyProvideVoiceMessagingReceiver(this.voiceModule, voiceService());
                    this.messagingReceiver4 = DoubleCheck.reentrantCheck(this.messagingReceiver4, obj);
                }
            }
            obj2 = obj;
        }
        return (MessagingReceiver) obj2;
    }

    private MessagingReceiver getMessagingReceiver5() {
        return SendToAppMessagingReceiverModule_ProvidesSendToAppMessagingReceiverFactory.proxyProvidesSendToAppMessagingReceiver(this.sendToAppMessagingReceiverModule, getContext());
    }

    private MessagingSettings getMessagingSettings() {
        Object obj;
        Object obj2 = this.messagingSettings;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messagingSettings;
                if (obj instanceof MemoizedSentinel) {
                    obj = MessagingModule_ProvideMessagingSettingsFactory.proxyProvideMessagingSettings(this.messagingModule, persistentStorageFactory(), getMessagingSettingsMetricsHandler(), getDeviceInformation(), getCoralService(), eventBus(), identityService(), getNetworkService(), getContext());
                    this.messagingSettings = DoubleCheck.reentrantCheck(this.messagingSettings, obj);
                }
            }
            obj2 = obj;
        }
        return (MessagingSettings) obj2;
    }

    private MessagingSettingsMetricsHandler getMessagingSettingsMetricsHandler() {
        Object obj;
        Object obj2 = this.messagingSettingsMetricsHandler;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messagingSettingsMetricsHandler;
                if (obj instanceof MemoizedSentinel) {
                    obj = MessagingModule_ProvideMessagingSettingsMetricsHandlerFactory.proxyProvideMessagingSettingsMetricsHandler(this.messagingModule, DoubleCheck.lazy(getMobilyticsProvider()));
                    this.messagingSettingsMetricsHandler = DoubleCheck.reentrantCheck(this.messagingSettingsMetricsHandler, obj);
                }
            }
            obj2 = obj;
        }
        return (MessagingSettingsMetricsHandler) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MetricsFactory getMetricsFactory() {
        return MetricsModule_ProvideMetricsFactoryFactory.proxyProvideMetricsFactory(this.metricsModule, getContext());
    }

    private Provider<MetricsFactory> getMetricsFactoryProvider() {
        Provider<MetricsFactory> provider = this.provideMetricsFactoryProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(26);
            this.provideMetricsFactoryProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<MetricsService> getMetricsServiceProvider() {
        Provider<MetricsService> provider = this.provideMetricsServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(12);
            this.provideMetricsServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MetricsServiceV2 getMetricsServiceV2() {
        Object obj;
        Object obj2 = this.metricsServiceV2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.metricsServiceV2;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideElementsMetricsServiceFactory.proxyProvideElementsMetricsService(this.serviceModule, metricsService());
                    this.metricsServiceV2 = DoubleCheck.reentrantCheck(this.metricsServiceV2, obj);
                }
            }
            obj2 = obj;
        }
        return (MetricsServiceV2) obj2;
    }

    private Provider<MetricsServiceV2> getMetricsServiceV2Provider() {
        Provider<MetricsServiceV2> provider = this.provideElementsMetricsServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(49);
            this.provideElementsMetricsServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Mobilytics getMobilytics() {
        Object obj;
        Object obj2 = this.mobilytics;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mobilytics;
                if (obj instanceof MemoizedSentinel) {
                    obj = MobilyticsModule_ProvideMobilyticsFactory.proxyProvideMobilytics(this.mobilyticsModule, getMobilyticsConfiguration());
                    this.mobilytics = DoubleCheck.reentrantCheck(this.mobilytics, obj);
                }
            }
            obj2 = obj;
        }
        return (Mobilytics) obj2;
    }

    private MobilyticsConfiguration getMobilyticsConfiguration() {
        Object obj;
        Object obj2 = this.mobilyticsConfiguration;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mobilyticsConfiguration;
                if (obj instanceof MemoizedSentinel) {
                    obj = MobilyticsModule_ProvideMobilyticsConfigurationFactory.proxyProvideMobilyticsConfiguration(this.mobilyticsModule, getContext(), eventBus());
                    this.mobilyticsConfiguration = DoubleCheck.reentrantCheck(this.mobilyticsConfiguration, obj);
                }
            }
            obj2 = obj;
        }
        return (MobilyticsConfiguration) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MobilyticsEventFactory getMobilyticsEventFactory() {
        Object obj;
        Object obj2 = this.mobilyticsEventFactory;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mobilyticsEventFactory;
                if (obj instanceof MemoizedSentinel) {
                    obj = MobilyticsModule_ProvideMobilyticsEventFactoryFactory.proxyProvideMobilyticsEventFactory(this.mobilyticsModule);
                    this.mobilyticsEventFactory = DoubleCheck.reentrantCheck(this.mobilyticsEventFactory, obj);
                }
            }
            obj2 = obj;
        }
        return (MobilyticsEventFactory) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<MobilyticsEventFactory> getMobilyticsEventFactoryProvider() {
        Provider<MobilyticsEventFactory> provider = this.provideMobilyticsEventFactoryProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(30);
            this.provideMobilyticsEventFactoryProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<Mobilytics> getMobilyticsProvider() {
        Provider<Mobilytics> provider = this.provideMobilyticsProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(13);
            this.provideMobilyticsProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<ModeService> getModeServiceProvider() {
        Provider<ModeService> provider = this.provideModeServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(50);
            this.provideModeServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private AWSCredentialsProvider getNamedAWSCredentialsProvider() {
        Object obj;
        Object obj2 = this.namedAWSCredentialsProvider;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedAWSCredentialsProvider;
                if (obj instanceof MemoizedSentinel) {
                    obj = KinesisMetricsModule_ProvideKinesisIdentityCredentialsProviderFactory.proxyProvideKinesisIdentityCredentialsProvider(this.kinesisMetricsModule, getContext(), KinesisMetricsModule_ProvideKinesisEnvironmentFactory.proxyProvideKinesisEnvironment(this.kinesisMetricsModule));
                    this.namedAWSCredentialsProvider = DoubleCheck.reentrantCheck(this.namedAWSCredentialsProvider, obj);
                }
            }
            obj2 = obj;
        }
        return (AWSCredentialsProvider) obj2;
    }

    private Cache<AppDataCacheEntry> getNamedCacheOfAppDataCacheEntry() {
        Object obj;
        Object obj2 = this.namedCacheOfAppDataCacheEntry;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedCacheOfAppDataCacheEntry;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideLocationCacheFactory.proxyProvideLocationCache(this.serviceModule, getContext(), getMetricsServiceV2(), getNamedExecutorService(), getNamedScheduler());
                    this.namedCacheOfAppDataCacheEntry = DoubleCheck.reentrantCheck(this.namedCacheOfAppDataCacheEntry, obj);
                }
            }
            obj2 = obj;
        }
        return (Cache) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cache<HttpResponseWrapper> getNamedCacheOfHttpResponseWrapper() {
        Object obj;
        Object obj2 = this.namedCacheOfHttpResponseWrapper;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedCacheOfHttpResponseWrapper;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideHttpCacheFactory.proxyProvideHttpCache(this.serviceModule, getContext(), getMetricsServiceV2(), getNamedExecutorService(), getNamedScheduler());
                    this.namedCacheOfHttpResponseWrapper = DoubleCheck.reentrantCheck(this.namedCacheOfHttpResponseWrapper, obj);
                }
            }
            obj2 = obj;
        }
        return (Cache) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DefaultElementLocalStorage getNamedDefaultElementLocalStorage() {
        Object obj;
        Object obj2 = this.namedDefaultElementLocalStorage;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedDefaultElementLocalStorage;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideDataStoreFactory.proxyProvideDataStore(this.serviceModule, getContext(), getMetricsServiceV2(), getNamedExecutorService(), getNamedScheduler());
                    this.namedDefaultElementLocalStorage = DoubleCheck.reentrantCheck(this.namedDefaultElementLocalStorage, obj);
                }
            }
            obj2 = obj;
        }
        return (DefaultElementLocalStorage) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DefaultElementLocalStorage getNamedDefaultElementLocalStorage2() {
        Object obj;
        Object obj2 = this.namedDefaultElementLocalStorage2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedDefaultElementLocalStorage2;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideDataCacheFactory.proxyProvideDataCache(this.serviceModule, getContext(), getMetricsServiceV2(), getNamedExecutorService(), getNamedScheduler());
                    this.namedDefaultElementLocalStorage2 = DoubleCheck.reentrantCheck(this.namedDefaultElementLocalStorage2, obj);
                }
            }
            obj2 = obj;
        }
        return (DefaultElementLocalStorage) obj2;
    }

    private Provider<DefaultElementLocalStorage> getNamedDefaultElementLocalStorageProvider() {
        Provider<DefaultElementLocalStorage> provider = this.provideDataStoreProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(17);
            this.provideDataStoreProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private ExecutorService getNamedExecutorService() {
        Object obj;
        Object obj2 = this.namedExecutorService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedExecutorService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideShortLivedTaskExecutorFactory.proxyProvideShortLivedTaskExecutor(this.serviceModule, getTaskManager());
                    this.namedExecutorService = DoubleCheck.reentrantCheck(this.namedExecutorService, obj);
                }
            }
            obj2 = obj;
        }
        return (ExecutorService) obj2;
    }

    private Scheduler getNamedScheduler() {
        Object obj;
        Object obj2 = this.namedScheduler;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedScheduler;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideDiskSchedulerFactory.proxyProvideDiskScheduler(this.serviceModule);
                    this.namedScheduler = DoubleCheck.reentrantCheck(this.namedScheduler, obj);
                }
            }
            obj2 = obj;
        }
        return (Scheduler) obj2;
    }

    private String getNamedString() {
        Object obj;
        Object obj2 = this.namedString;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedString;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideDeviceNameTemplateFactory.proxyProvideDeviceNameTemplate(this.identityModule, getContext());
                    this.namedString = DoubleCheck.reentrantCheck(this.namedString, obj);
                }
            }
            obj2 = obj;
        }
        return (String) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NetworkService getNetworkService() {
        Object obj;
        Object obj2 = this.networkService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.networkService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideNetworkServiceFactory.proxyProvideNetworkService(this.serviceModule, getContext());
                    this.networkService = DoubleCheck.reentrantCheck(this.networkService, obj);
                }
            }
            obj2 = obj;
        }
        return (NetworkService) obj2;
    }

    private Provider<NetworkService> getNetworkServiceProvider() {
        Provider<NetworkService> provider = this.provideNetworkServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(37);
            this.provideNetworkServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<OkHttpClient> getOkHttpClientProvider() {
        Provider<OkHttpClient> provider = this.provideHttpClientProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(4);
            this.provideHttpClientProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private PersistentEndpointsStorage getPersistentEndpointsStorage() {
        Object obj;
        Object obj2 = this.persistentEndpointsStorage;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.persistentEndpointsStorage;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvidePersistentEndpointsStorageFactory.proxyProvidePersistentEndpointsStorage(this.serviceModule, persistentStorageFactory());
                    this.persistentEndpointsStorage = DoubleCheck.reentrantCheck(this.persistentEndpointsStorage, obj);
                }
            }
            obj2 = obj;
        }
        return (PersistentEndpointsStorage) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PersonIdProvider getPersonIdProvider() {
        Object obj;
        Object obj2 = this.personIdProvider;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.personIdProvider;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvidePersonIdProviderFactory.proxyProvidePersonIdProvider(this.identityModule, identityService(), metricsService());
                    this.personIdProvider = DoubleCheck.reentrantCheck(this.personIdProvider, obj);
                }
            }
            obj2 = obj;
        }
        return (PersonIdProvider) obj2;
    }

    private Provider<PersonIdProvider> getPersonIdProviderProvider() {
        Provider<PersonIdProvider> provider = this.providePersonIdProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(44);
            this.providePersonIdProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private PfmEnvironmentService getPfmEnvironmentService() {
        Object obj;
        Object obj2 = this.pfmEnvironmentService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.pfmEnvironmentService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvidePfmEnvironmentServiceFactory.proxyProvidePfmEnvironmentService(this.serviceModule, getContext(), getDeviceInformation(), getUserIdentityStorage());
                    this.pfmEnvironmentService = DoubleCheck.reentrantCheck(this.pfmEnvironmentService, obj);
                }
            }
            obj2 = obj;
        }
        return (PfmEnvironmentService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PhotoService getPhotoService() {
        Object obj;
        Object obj2 = this.photoService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.photoService;
                if (obj instanceof MemoizedSentinel) {
                    obj = PhotoServiceModule_ProvidePhotoServiceFactory.proxyProvidePhotoService(this.photoServiceModule, getPhotoServiceFactory());
                    this.photoService = DoubleCheck.reentrantCheck(this.photoService, obj);
                }
            }
            obj2 = obj;
        }
        return (PhotoService) obj2;
    }

    private PhotoServiceFactory getPhotoServiceFactory() {
        Object obj;
        Object obj2 = this.photoServiceFactory;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.photoServiceFactory;
                if (obj instanceof MemoizedSentinel) {
                    obj = PhotoServiceModule_ProvidePhotoServiceFactoryFactory.proxyProvidePhotoServiceFactory(this.photoServiceModule, getAlexaPhotosBackgroundService(), getCloudDriveService(), identityService(), eventBus());
                    this.photoServiceFactory = DoubleCheck.reentrantCheck(this.photoServiceFactory, obj);
                }
            }
            obj2 = obj;
        }
        return (PhotoServiceFactory) obj2;
    }

    private Provider<PhotoService> getPhotoServiceProvider() {
        Provider<PhotoService> provider = this.providePhotoServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(42);
            this.providePhotoServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private PhotosAppInfoProvider getPhotosAppInfoProvider() {
        Object obj;
        Object obj2 = this.photosAppInfoProvider;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.photosAppInfoProvider;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvidePhotosApplicationIdProviderFactory.proxyProvidePhotosApplicationIdProvider(this.cloudDriveModule, getContext());
                    this.photosAppInfoProvider = DoubleCheck.reentrantCheck(this.photosAppInfoProvider, obj);
                }
            }
            obj2 = obj;
        }
        return (PhotosAppInfoProvider) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PhotosChooser getPhotosChooser() {
        Object obj;
        Object obj2 = this.photosChooser;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.photosChooser;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvidePhotosChooserFactory.proxyProvidePhotosChooser(this.cloudDriveModule);
                    this.photosChooser = DoubleCheck.reentrantCheck(this.photosChooser, obj);
                }
            }
            obj2 = obj;
        }
        return (PhotosChooser) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PhotosFeatureGuardian getPhotosFeatureGuardian() {
        Object obj;
        Object obj2 = this.photosFeatureGuardian;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.photosFeatureGuardian;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvidePhotosFeatureGuardianFactory.proxyProvidePhotosFeatureGuardian(this.cloudDriveModule);
                    this.photosFeatureGuardian = DoubleCheck.reentrantCheck(this.photosFeatureGuardian, obj);
                }
            }
            obj2 = obj;
        }
        return (PhotosFeatureGuardian) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<PhotosFeatureGuardian> getPhotosFeatureGuardianProvider() {
        Provider<PhotosFeatureGuardian> provider = this.providePhotosFeatureGuardianProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(62);
            this.providePhotosFeatureGuardianProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PhotosUploader getPhotosUploader() {
        Object obj;
        Object obj2 = this.photosUploader;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.photosUploader;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvidePhotosUploaderFactory.proxyProvidePhotosUploader(this.cloudDriveModule, getContext(), DoubleCheck.lazy(getAmazonCloudDriveExtendedClientProvider()), DoubleCheck.lazy(getUploadBundleManagerProvider()), DoubleCheck.lazy(getPhotosFeatureGuardianProvider()), DoubleCheck.lazy(getCloudDriveMetricsProvider()), DoubleCheck.lazy(getSystemUtilityProvider()));
                    this.photosUploader = DoubleCheck.reentrantCheck(this.photosUploader, obj);
                }
            }
            obj2 = obj;
        }
        return (PhotosUploader) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<PhotosUploader> getPhotosUploaderProvider() {
        Provider<PhotosUploader> provider = this.providePhotosUploaderProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(57);
            this.providePhotosUploaderProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlatformFeatureServiceV2 getPlatformFeatureServiceV2() {
        Object obj;
        Object obj2 = this.platformFeatureServiceV2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.platformFeatureServiceV2;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvidePlatformFeatureServiceV2Factory.proxyProvidePlatformFeatureServiceV2(this.serviceModule, DoubleCheck.lazy(getFeatureQueryProvider()), DoubleCheck.lazy(getAlexaMobileAndroidFeatureServiceImplProvider()), getDefaultFeatureServiceV2());
                    this.platformFeatureServiceV2 = DoubleCheck.reentrantCheck(this.platformFeatureServiceV2, obj);
                }
            }
            obj2 = obj;
        }
        return (PlatformFeatureServiceV2) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlayerCardUpdater getPlayerCardUpdater() {
        Object obj;
        Object obj2 = this.playerCardUpdater;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.playerCardUpdater;
                if (obj instanceof MemoizedSentinel) {
                    obj = EntertainmentModule_ProvidePlayerCardUpdaterFactory.proxyProvidePlayerCardUpdater(this.entertainmentModule);
                    this.playerCardUpdater = DoubleCheck.reentrantCheck(this.playerCardUpdater, obj);
                }
            }
            obj2 = obj;
        }
        return (PlayerCardUpdater) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PreloadAttributionManager getPreloadAttributionManager() {
        Object obj;
        Object obj2 = this.preloadAttributionManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.preloadAttributionManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = PreloadAttributionModule_ProvidePreloadAttributionManagerFactory.proxyProvidePreloadAttributionManager(this.preloadAttributionModule, getContext(), getFeatureServiceV2());
                    this.preloadAttributionManager = DoubleCheck.reentrantCheck(this.preloadAttributionManager, obj);
                }
            }
            obj2 = obj;
        }
        return (PreloadAttributionManager) obj2;
    }

    private Provider<PreloadAttributionManager> getPreloadAttributionManagerProvider() {
        Provider<PreloadAttributionManager> provider = this.providePreloadAttributionManagerProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(24);
            this.providePreloadAttributionManagerProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PreloadAttributionUIManager getPreloadAttributionUIManager() {
        Object obj;
        Object obj2 = this.preloadAttributionUIManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.preloadAttributionUIManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = PreloadAttributionModule_ProvidePreloadAttributionUIManagerFactory.proxyProvidePreloadAttributionUIManager(this.preloadAttributionModule, getContext(), getRoutingService(), getPreloadAttributionManager());
                    this.preloadAttributionUIManager = DoubleCheck.reentrantCheck(this.preloadAttributionUIManager, obj);
                }
            }
            obj2 = obj;
        }
        return (PreloadAttributionUIManager) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<PreloadAttributionUIManager> getPreloadAttributionUIManagerProvider() {
        Provider<PreloadAttributionUIManager> provider = this.providePreloadAttributionUIManagerProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(70);
            this.providePreloadAttributionUIManagerProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private RequestAuthenticator getRequestAuthenticator() {
        Object obj;
        Object obj2 = this.requestAuthenticator;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.requestAuthenticator;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideRequestAuthenticatorFactory.proxyProvideRequestAuthenticator(this.cloudDriveModule, identityService());
                    this.requestAuthenticator = DoubleCheck.reentrantCheck(this.requestAuthenticator, obj);
                }
            }
            obj2 = obj;
        }
        return (RequestAuthenticator) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RouteFeatureGroupRegistry getRouteFeatureGroupRegistry() {
        Object obj;
        Object obj2 = this.routeFeatureGroupRegistry;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.routeFeatureGroupRegistry;
                if (obj instanceof MemoizedSentinel) {
                    obj = RoutingModule_ProvideGroupRoutingRegistryFactory.proxyProvideGroupRoutingRegistry(this.routingModule);
                    this.routeFeatureGroupRegistry = DoubleCheck.reentrantCheck(this.routeFeatureGroupRegistry, obj);
                }
            }
            obj2 = obj;
        }
        return (RouteFeatureGroupRegistry) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RouteWatcher getRouteWatcher() {
        Object obj;
        Object obj2 = this.routeWatcher;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.routeWatcher;
                if (obj instanceof MemoizedSentinel) {
                    obj = RoutingModule_ProvideRouteWatcherFactory.proxyProvideRouteWatcher(this.routingModule, identityService(), routingRegistry(), getRouteFeatureGroupRegistry(), eventBus());
                    this.routeWatcher = DoubleCheck.reentrantCheck(this.routeWatcher, obj);
                }
            }
            obj2 = obj;
        }
        return (RouteWatcher) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RoutingRegistryAdapter getRoutingRegistryAdapter() {
        Object obj;
        Object obj2 = this.routingRegistryAdapter;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.routingRegistryAdapter;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideAdapterRegistryFactory.proxyProvideAdapterRegistry(this.serviceModule);
                    this.routingRegistryAdapter = DoubleCheck.reentrantCheck(this.routingRegistryAdapter, obj);
                }
            }
            obj2 = obj;
        }
        return (RoutingRegistryAdapter) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RoutingService getRoutingService() {
        Object obj;
        Object obj2 = this.routingService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.routingService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideRoutingServiceFactory.proxyProvideRoutingService(this.serviceModule, getContext(), routingRegistry(), metricsService(), getRoutingRegistryAdapter(), crashMetadata(), getFeatureServiceV2(), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getEventBusProvider()));
                    this.routingService = DoubleCheck.reentrantCheck(this.routingService, obj);
                }
            }
            obj2 = obj;
        }
        return (RoutingService) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<RoutingService> getRoutingServiceProvider() {
        Provider<RoutingService> provider = this.provideRoutingServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(5);
            this.provideRoutingServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RoutingViewService getRoutingViewService() {
        Object obj;
        Object obj2 = this.routingViewService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.routingViewService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideViewServiceFactory.proxyProvideViewService(this.serviceModule, getRoutingService(), metricsService(), getRoutingRegistryAdapter(), routingRegistry(), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getTTCFRoutingDelegateProvider()), crashMetadata(), getFeatureServiceV2());
                    this.routingViewService = DoubleCheck.reentrantCheck(this.routingViewService, obj);
                }
            }
            obj2 = obj;
        }
        return (RoutingViewService) obj2;
    }

    private Set<FeatureFilter> getSetOfFeatureFilter() {
        return ImmutableSet.of(getFeatureFilter(), getFeatureFilter2());
    }

    private Set<MessagingReceiver> getSetOfMessagingReceiver() {
        return ImmutableSet.of(getMessagingReceiver(), getMessagingReceiver2(), getMessagingReceiver3(), getMessagingReceiver4(), getMessagingReceiver5());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SystemUtility getSystemUtility() {
        Object obj;
        Object obj2 = this.systemUtility;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.systemUtility;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvideSystemUtilityFactory.proxyProvideSystemUtility(this.cloudDriveModule);
                    this.systemUtility = DoubleCheck.reentrantCheck(this.systemUtility, obj);
                }
            }
            obj2 = obj;
        }
        return (SystemUtility) obj2;
    }

    private Provider<SystemUtility> getSystemUtilityProvider() {
        Provider<SystemUtility> provider = this.provideSystemUtilityProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(63);
            this.provideSystemUtilityProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TTCFCheckpoint getTTCFCheckpoint() {
        Object obj;
        Object obj2 = this.tTCFCheckpoint;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.tTCFCheckpoint;
                if (obj instanceof MemoizedSentinel) {
                    obj = TTCFModule_ProvideTTCFCheckpointFactory.proxyProvideTTCFCheckpoint(this.tTCFModule, ttcfService());
                    this.tTCFCheckpoint = DoubleCheck.reentrantCheck(this.tTCFCheckpoint, obj);
                }
            }
            obj2 = obj;
        }
        return (TTCFCheckpoint) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<TTCFCheckpoint> getTTCFCheckpointProvider() {
        Provider<TTCFCheckpoint> provider = this.provideTTCFCheckpointProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(67);
            this.provideTTCFCheckpointProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TTCFRoutingDelegate getTTCFRoutingDelegate() {
        Object obj;
        Object obj2 = this.tTCFRoutingDelegate;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.tTCFRoutingDelegate;
                if (obj instanceof MemoizedSentinel) {
                    obj = TTCFModule_ProvideTTCFRoutingDelegateFactory.proxyProvideTTCFRoutingDelegate(this.tTCFModule, ttcfService());
                    this.tTCFRoutingDelegate = DoubleCheck.reentrantCheck(this.tTCFRoutingDelegate, obj);
                }
            }
            obj2 = obj;
        }
        return (TTCFRoutingDelegate) obj2;
    }

    private Provider<TTCFRoutingDelegate> getTTCFRoutingDelegateProvider() {
        Provider<TTCFRoutingDelegate> provider = this.provideTTCFRoutingDelegateProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(56);
            this.provideTTCFRoutingDelegateProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private Provider<TTCFService> getTTCFServiceProvider() {
        Provider<TTCFService> provider = this.provideTTCFServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(53);
            this.provideTTCFServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TaskManager getTaskManager() {
        Object obj;
        Object obj2 = this.taskManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.taskManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideTaskManagerFactory.proxyProvideTaskManager(this.serviceModule);
                    this.taskManager = DoubleCheck.reentrantCheck(this.taskManager, obj);
                }
            }
            obj2 = obj;
        }
        return (TaskManager) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<TaskManager> getTaskManagerProvider() {
        Provider<TaskManager> provider = this.provideTaskManagerProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(16);
            this.provideTaskManagerProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    private TokenAccessor getTokenAccessor() {
        Object obj;
        Object obj2 = this.tokenAccessor;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.tokenAccessor;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideTokenAccessorFactory.proxyProvideTokenAccessor(this.identityModule, getCoralService());
                    this.tokenAccessor = DoubleCheck.reentrantCheck(this.tokenAccessor, obj);
                }
            }
            obj2 = obj;
        }
        return (TokenAccessor) obj2;
    }

    private TokenEncryptor getTokenEncryptor() {
        Object obj;
        Object obj2 = this.tokenEncryptor;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.tokenEncryptor;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideTokenEncryptorFactory.proxyProvideTokenEncryptor(this.identityModule, getLocalAndroidKeyValueStore());
                    this.tokenEncryptor = DoubleCheck.reentrantCheck(this.tokenEncryptor, obj);
                }
            }
            obj2 = obj;
        }
        return (TokenEncryptor) obj2;
    }

    private TokenManagement getTokenManagement() {
        return IdentityModule_ProvideTokenManagementFactory.proxyProvideTokenManagement(this.identityModule, getContext());
    }

    private TokenStorage getTokenStorage() {
        Object obj;
        Object obj2 = this.tokenStorage;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.tokenStorage;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideTokenStorageFactory.proxyProvideTokenStorage(this.identityModule, getDataStoreService(), getTokenEncryptor());
                    this.tokenStorage = DoubleCheck.reentrantCheck(this.tokenStorage, obj);
                }
            }
            obj2 = obj;
        }
        return (TokenStorage) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UploadBundleManager getUploadBundleManager() {
        Object obj;
        Object obj2 = this.uploadBundleManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.uploadBundleManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = CloudDriveModule_ProvidePhotosUploadBundleManagerFactory.proxyProvidePhotosUploadBundleManager(this.cloudDriveModule, getContext(), getHVAManager(), getPhotosAppInfoProvider(), DoubleCheck.lazy(getCloudDriveMetricsProvider()), DoubleCheck.lazy(getMAPAuthenticatedURLConnectionFactoryProvider()), DoubleCheck.lazy(getPhotosFeatureGuardianProvider()));
                    this.uploadBundleManager = DoubleCheck.reentrantCheck(this.uploadBundleManager, obj);
                }
            }
            obj2 = obj;
        }
        return (UploadBundleManager) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<UploadBundleManager> getUploadBundleManagerProvider() {
        Provider<UploadBundleManager> provider = this.providePhotosUploadBundleManagerProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(59);
            this.providePhotosUploadBundleManagerProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UrlResolver getUrlResolver() {
        Object obj;
        Object obj2 = this.urlResolver;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.urlResolver;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideUrlResolverFactory.proxyProvideUrlResolver(this.serviceModule, getEnvironmentService());
                    this.urlResolver = DoubleCheck.reentrantCheck(this.urlResolver, obj);
                }
            }
            obj2 = obj;
        }
        return (UrlResolver) obj2;
    }

    private Provider<UrlResolver> getUrlResolverProvider() {
        Provider<UrlResolver> provider = this.provideUrlResolverProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(65);
            this.provideUrlResolverProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UserAgentService getUserAgentService() {
        Object obj;
        Object obj2 = this.userAgentService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.userAgentService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideUserAgentServiceFactory.proxyProvideUserAgentService(this.serviceModule);
                    this.userAgentService = DoubleCheck.reentrantCheck(this.userAgentService, obj);
                }
            }
            obj2 = obj;
        }
        return (UserAgentService) obj2;
    }

    private UserIdentityMapper getUserIdentityMapper() {
        Object obj;
        Object obj2 = this.userIdentityMapper;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.userIdentityMapper;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideUserIdentityMapperFactory.proxyProvideUserIdentityMapper(this.identityModule, getFeatureConstraints());
                    this.userIdentityMapper = DoubleCheck.reentrantCheck(this.userIdentityMapper, obj);
                }
            }
            obj2 = obj;
        }
        return (UserIdentityMapper) obj2;
    }

    private UserIdentityRepository getUserIdentityRepository() {
        Object obj;
        Object obj2 = this.userIdentityRepository;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.userIdentityRepository;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideUserIdentityRepositoryFactory.proxyProvideUserIdentityRepository(this.identityModule, getContext(), getCoralService(), getMarketplaceService(), getUserIdentityMapper(), getUserIdentityStorage(), getMAPAccountManager(), getTokenManagement(), getUserProfileManager());
                    this.userIdentityRepository = DoubleCheck.reentrantCheck(this.userIdentityRepository, obj);
                }
            }
            obj2 = obj;
        }
        return (UserIdentityRepository) obj2;
    }

    private UserIdentityStorage getUserIdentityStorage() {
        Object obj;
        Object obj2 = this.userIdentityStorage;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.userIdentityStorage;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideUserIdentityStorageFactory.proxyProvideUserIdentityStorage(this.identityModule, persistentStorageFactory());
                    this.userIdentityStorage = DoubleCheck.reentrantCheck(this.userIdentityStorage, obj);
                }
            }
            obj2 = obj;
        }
        return (UserIdentityStorage) obj2;
    }

    private UserProfileManager getUserProfileManager() {
        Object obj;
        Object obj2 = this.userProfileManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.userProfileManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideUserProfileManagerFactory.proxyProvideUserProfileManager(this.identityModule, commsManager(), DoubleCheck.lazy(getCommsServiceV2Provider()));
                    this.userProfileManager = DoubleCheck.reentrantCheck(this.userProfileManager, obj);
                }
            }
            obj2 = obj;
        }
        return (UserProfileManager) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Provider<VoiceService> getVoiceServiceProvider() {
        Provider<VoiceService> provider = this.provideVoiceServiceProvider;
        if (provider == null) {
            SwitchingProvider switchingProvider = new SwitchingProvider(31);
            this.provideVoiceServiceProvider = switchingProvider;
            return switchingProvider;
        }
        return provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WifiService getWifiService() {
        Object obj;
        Object obj2 = this.wifiService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.wifiService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideWifiServiceFactory.proxyProvideWifiService(this.serviceModule, getContext(), getEnvironmentService(), getMobilytics());
                    this.wifiService = DoubleCheck.reentrantCheck(this.wifiService, obj);
                }
            }
            obj2 = obj;
        }
        return (WifiService) obj2;
    }

    @CanIgnoreReturnValue
    private AlexaDeviceBackgroundImageActivity injectAlexaDeviceBackgroundImageActivity(AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity) {
        AlexaDeviceBackgroundImageActivity_MembersInjector.injectIdentityService(alexaDeviceBackgroundImageActivity, DoubleCheck.lazy(getIdentityServiceProvider()));
        AlexaDeviceBackgroundImageActivity_MembersInjector.injectPhotoService(alexaDeviceBackgroundImageActivity, DoubleCheck.lazy(getPhotoServiceProvider()));
        AlexaDeviceBackgroundImageActivity_MembersInjector.injectDeviceBackgroundImageService(alexaDeviceBackgroundImageActivity, DoubleCheck.lazy(getBackgroundImageServiceProvider()));
        AlexaDeviceBackgroundImageActivity_MembersInjector.injectMetricsService(alexaDeviceBackgroundImageActivity, DoubleCheck.lazy(getMetricsServiceProvider()));
        return alexaDeviceBackgroundImageActivity;
    }

    @CanIgnoreReturnValue
    private AlexaWebView injectAlexaWebView(AlexaWebView alexaWebView) {
        AlexaWebView_MembersInjector.injectMetricsService(alexaWebView, metricsService());
        AlexaWebView_MembersInjector.injectCertificateReaderService(alexaWebView, DoubleCheck.lazy(getCertificateReaderServiceProvider()));
        return alexaWebView;
    }

    @CanIgnoreReturnValue
    private FireOSDirectiveHandlerService injectFireOSDirectiveHandlerService(FireOSDirectiveHandlerService fireOSDirectiveHandlerService) {
        FireOSDirectiveHandlerService_MembersInjector.injectCommsConversationService(fireOSDirectiveHandlerService, conversationService());
        return fireOSDirectiveHandlerService;
    }

    @CanIgnoreReturnValue
    private MainApplicationImplementation injectMainApplicationImplementation(MainApplicationImplementation mainApplicationImplementation) {
        MainApplicationImplementation_MembersInjector.injectAccountService(mainApplicationImplementation, DoubleCheck.lazy(getAccountServiceProvider()));
        MainApplicationImplementation_MembersInjector.injectCrashMetadata(mainApplicationImplementation, DoubleCheck.lazy(getCrashMetadataProvider()));
        MainApplicationImplementation_MembersInjector.injectCrashReporter(mainApplicationImplementation, DoubleCheck.lazy(getCrashReporterProvider()));
        MainApplicationImplementation_MembersInjector.injectCrashReportingService(mainApplicationImplementation, DoubleCheck.lazy(getCrashReportingServiceProvider()));
        MainApplicationImplementation_MembersInjector.injectLatencyInfra(mainApplicationImplementation, getLatencyInfra());
        MainApplicationImplementation_MembersInjector.injectOkHttpClient(mainApplicationImplementation, DoubleCheck.lazy(getOkHttpClientProvider()));
        MainApplicationImplementation_MembersInjector.injectRoutingService(mainApplicationImplementation, DoubleCheck.lazy(getRoutingServiceProvider()));
        MainApplicationImplementation_MembersInjector.injectTaskManager(mainApplicationImplementation, DoubleCheck.lazy(getTaskManagerProvider()));
        MainApplicationImplementation_MembersInjector.injectMobilytics(mainApplicationImplementation, DoubleCheck.lazy(getMobilyticsProvider()));
        MainApplicationImplementation_MembersInjector.injectDataStore(mainApplicationImplementation, DoubleCheck.lazy(getNamedDefaultElementLocalStorageProvider()));
        MainApplicationImplementation_MembersInjector.injectModeService(mainApplicationImplementation, modeService());
        MainApplicationImplementation_MembersInjector.injectAlexaTarazedService(mainApplicationImplementation, DoubleCheck.lazy(getAlexaTarazedServiceProvider()));
        MainApplicationImplementation_MembersInjector.injectCertificateReaderService(mainApplicationImplementation, DoubleCheck.lazy(getCertificateReaderServiceProvider()));
        MainApplicationImplementation_MembersInjector.injectDefaultFeatureServiceV2(mainApplicationImplementation, getDefaultFeatureServiceV2());
        MainApplicationImplementation_MembersInjector.injectAlexaMobileAndroidFeatureService(mainApplicationImplementation, DoubleCheck.lazy(getAlexaMobileAndroidFeatureServiceImplProvider()));
        MainApplicationImplementation_MembersInjector.injectAssetManagementService(mainApplicationImplementation, getAssetManagementService());
        MainApplicationImplementation_MembersInjector.injectMatterService(mainApplicationImplementation, DoubleCheck.lazy(getMatterServiceProvider()));
        return mainApplicationImplementation;
    }

    @CanIgnoreReturnValue
    private ViewBoxFragment injectViewBoxFragment(ViewBoxFragment viewBoxFragment) {
        ViewBoxFragment_MembersInjector.injectIdentityService(viewBoxFragment, DoubleCheck.lazy(getIdentityServiceProvider()));
        ViewBoxFragment_MembersInjector.injectPhotoService(viewBoxFragment, DoubleCheck.lazy(getPhotoServiceProvider()));
        ViewBoxFragment_MembersInjector.injectEnvironmentService(viewBoxFragment, DoubleCheck.lazy(getEnvironmentServiceProvider()));
        ViewBoxFragment_MembersInjector.injectDeviceBackgroundImageService(viewBoxFragment, DoubleCheck.lazy(getBackgroundImageServiceProvider()));
        ViewBoxFragment_MembersInjector.injectMetricsService(viewBoxFragment, DoubleCheck.lazy(getMetricsServiceProvider()));
        return viewBoxFragment;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public AccessibilityService accessibilityService() {
        Object obj;
        Object obj2 = this.accessibilityService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.accessibilityService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideAccessibilityServiceFactory.proxyProvideAccessibilityService(this.serviceModule, getContext(), getMobilytics());
                    this.accessibilityService = DoubleCheck.reentrantCheck(this.accessibilityService, obj);
                }
            }
            obj2 = obj;
        }
        return (AccessibilityService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public AccountService accountService() {
        Object obj;
        Object obj2 = this.accountService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.accountService;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideAccountServiceFactory.proxyProvideAccountService(this.identityModule, getMAPAccountManager(), getAccountUpgradeAuthority(), DoubleCheck.lazy(getCookieManagerProvider()), commsManager(), getNamedString());
                    this.accountService = DoubleCheck.reentrantCheck(this.accountService, obj);
                }
            }
            obj2 = obj;
        }
        return (AccountService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public DefaultApplicationLifecycleService applicationLifecycleService() {
        Object obj;
        Object obj2 = this.defaultApplicationLifecycleService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.defaultApplicationLifecycleService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideApplicationLifecycleServiceFactory.proxyProvideApplicationLifecycleService(this.serviceModule, getNamedExecutorService());
                    this.defaultApplicationLifecycleService = DoubleCheck.reentrantCheck(this.defaultApplicationLifecycleService, obj);
                }
            }
            obj2 = obj;
        }
        return (DefaultApplicationLifecycleService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public CertificateReaderService certificateReaderService() {
        Object obj;
        Object obj2 = this.certificateReaderService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.certificateReaderService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCertificateReaderServiceFactory.proxyProvideCertificateReaderService(this.serviceModule, getContext());
                    this.certificateReaderService = DoubleCheck.reentrantCheck(this.certificateReaderService, obj);
                }
            }
            obj2 = obj;
        }
        return (CertificateReaderService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public CommsManager commsManager() {
        Object obj;
        Object obj2 = this.commsManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.commsManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = CommsModule_ProvideCommsManagerFactory.proxyProvideCommsManager(this.commsModule, commsService());
                    this.commsManager = DoubleCheck.reentrantCheck(this.commsManager, obj);
                }
            }
            obj2 = obj;
        }
        return (CommsManager) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public AlexaCommsService commsService() {
        Object obj;
        Object obj2 = this.alexaCommsService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.alexaCommsService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCommsServiceFactory.proxyProvideCommsService(this.serviceModule, getAlexaCommsServiceWrapper());
                    this.alexaCommsService = DoubleCheck.reentrantCheck(this.alexaCommsService, obj);
                }
            }
            obj2 = obj;
        }
        return (AlexaCommsService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public CommsServiceV2 commsServiceV2() {
        Object obj;
        Object obj2 = this.commsServiceV2;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.commsServiceV2;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCommsServiceV2Factory.proxyProvideCommsServiceV2(this.serviceModule, getAlexaCommsServiceWrapper());
                    this.commsServiceV2 = DoubleCheck.reentrantCheck(this.commsServiceV2, obj);
                }
            }
            obj2 = obj;
        }
        return (CommsServiceV2) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public ComponentBinder componentBinder() {
        Object obj;
        Object obj2 = this.componentBinder;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.componentBinder;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideComponentFactoriesFactory.proxyProvideComponentFactories(this.serviceModule, DoubleCheck.lazy(getEnvironmentServiceProvider()), DoubleCheck.lazy(getFeatureQueryProvider()), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getPersonIdProviderProvider()), DoubleCheck.lazy(getMainActivityLifecycleServiceProvider()), DoubleCheck.lazy(getDefaultApplicationLifecycleServiceProvider()), DoubleCheck.lazy(getMarketplaceServiceProvider()), DoubleCheck.lazy(getMarketplaceServiceProvider2()), DoubleCheck.lazy(getCrashMetadataProvider()), DoubleCheck.lazy(getCrashObserverRegistrarProvider()), DoubleCheck.lazy(getCrashReporterProvider()), DoubleCheck.lazy(getMetricsServiceProvider()), DoubleCheck.lazy(getMetricsServiceV2Provider()), DoubleCheck.lazy(getMobilyticsProvider()), DoubleCheck.lazy(getModeServiceProvider()), DoubleCheck.lazy(getLocationServiceProvider()), DoubleCheck.lazy(getCoralServiceProvider()), DoubleCheck.lazy(getJsonConverterProvider()), DoubleCheck.lazy(getFactoryProvider()), DoubleCheck.lazy(getRoutingServiceProvider()), DoubleCheck.lazy(getTTCFServiceProvider()), DoubleCheck.lazy(getCertificateReaderServiceProvider()), DoubleCheck.lazy(getCommsServiceV2Provider()), DoubleCheck.lazy(getFeatureServiceV2Provider()), DoubleCheck.lazy(getAssetManagementServiceProvider()), DoubleCheck.lazy(getNamedDefaultElementLocalStorageProvider()), DoubleCheck.lazy(getDialogBuilderProviderProvider()));
                    this.componentBinder = DoubleCheck.reentrantCheck(this.componentBinder, obj);
                }
            }
        } else {
            obj = obj2;
        }
        return (ComponentBinder) obj;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public ConversationService conversationService() {
        Object obj;
        Object obj2 = this.conversationService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.conversationService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideConversationServiceFactory.proxyProvideConversationService(this.serviceModule, commsService());
                    this.conversationService = DoubleCheck.reentrantCheck(this.conversationService, obj);
                }
            }
            obj2 = obj;
        }
        return (ConversationService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public CrashMetadata crashMetadata() {
        Object obj;
        Object obj2 = this.crashMetadata;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.crashMetadata;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideCrashMetadataFactory.proxyProvideCrashMetadata(this.serviceModule, DoubleCheck.lazy(getCrashReportingServiceProvider()));
                    this.crashMetadata = DoubleCheck.reentrantCheck(this.crashMetadata, obj);
                }
            }
            obj2 = obj;
        }
        return (CrashMetadata) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public EventBus eventBus() {
        Object obj;
        Object obj2 = this.eventBus;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.eventBus;
                if (obj instanceof MemoizedSentinel) {
                    obj = EventBusModule_ProvideEventBusFactory.proxyProvideEventBus(this.eventBusModule);
                    this.eventBus = DoubleCheck.reentrantCheck(this.eventBus, obj);
                }
            }
            obj2 = obj;
        }
        return (EventBus) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public ServiceLifecycle eventBusService() {
        Object obj;
        Object obj2 = this.serviceLifecycle;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.serviceLifecycle;
                if (obj instanceof MemoizedSentinel) {
                    obj = EventBusModule_ProvideEventBusServiceFactory.proxyProvideEventBusService(this.eventBusModule, eventBus());
                    this.serviceLifecycle = DoubleCheck.reentrantCheck(this.serviceLifecycle, obj);
                }
            }
            obj2 = obj;
        }
        return (ServiceLifecycle) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public OkHttpClient httpClient() {
        Object obj;
        Object obj2 = this.okHttpClient;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.okHttpClient;
                if (obj instanceof MemoizedSentinel) {
                    obj = NetworkModule_ProvideHttpClientFactory.proxyProvideHttpClient(this.networkModule);
                    this.okHttpClient = DoubleCheck.reentrantCheck(this.okHttpClient, obj);
                }
            }
            obj2 = obj;
        }
        return (OkHttpClient) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public IdentityService identityService() {
        Object obj;
        Object obj2 = this.identityService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.identityService;
                if (obj instanceof MemoizedSentinel) {
                    obj = IdentityModule_ProvideIdentityServiceFactory.proxyProvideIdentityService(this.identityModule, getMAPIdentityService());
                    this.identityService = DoubleCheck.reentrantCheck(this.identityService, obj);
                }
            }
            obj2 = obj;
        }
        return (IdentityService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public MainApplicationImplementation inject(MainApplicationImplementation mainApplicationImplementation) {
        return injectMainApplicationImplementation(mainApplicationImplementation);
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public LocationService locationService() {
        Object obj;
        Object obj2 = this.locationService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.locationService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideLocationServiceFactory.proxyProvideLocationService(this.serviceModule, getContext(), getLocationProvider(), httpClient(), getNamedCacheOfAppDataCacheEntry());
                    this.locationService = DoubleCheck.reentrantCheck(this.locationService, obj);
                }
            }
            obj2 = obj;
        }
        return (LocationService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public MainActivityLifecycleService mainActivityLifecycleService() {
        Object obj;
        Object obj2 = this.mainActivityLifecycleService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mainActivityLifecycleService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideMainActivityLifecycleServiceFactory.proxyProvideMainActivityLifecycleService(this.serviceModule, getNamedExecutorService());
                    this.mainActivityLifecycleService = DoubleCheck.reentrantCheck(this.mainActivityLifecycleService, obj);
                }
            }
            obj2 = obj;
        }
        return (MainActivityLifecycleService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public MessagingService messagingService() {
        Object obj;
        Object obj2 = this.messagingService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.messagingService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideMessagingServiceFactory.proxyProvideMessagingService(this.serviceModule, getContext(), getCoralService(), identityService(), getADMProvider(), getFirebaseInstanceIdProvider(), DoubleCheck.lazy(getMessagingHandlerProvider()), getMessagingSettings(), commsManager(), getCommsDeviceSupport(), metricsService(), getNetworkService(), getDeviceInformation(), getMessageCrypto(), getPersonIdProvider(), eventBus());
                    this.messagingService = DoubleCheck.reentrantCheck(this.messagingService, obj);
                }
            }
        } else {
            obj = obj2;
        }
        return (MessagingService) obj;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public MetricsService metricsService() {
        Object obj;
        Object obj2 = this.metricsService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.metricsService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvideMetricsServiceFactory.proxyProvideMetricsService(this.serviceModule, getEnvironmentService(), persistentStorageFactory(), crashMetadata(), DoubleCheck.lazy(getPreloadAttributionManagerProvider()), DoubleCheck.lazy(getDCMMetricsConnectorProvider()), DoubleCheck.lazy(getKinesisMetricsConnectorProvider()), DoubleCheck.lazy(getMobilyticsProvider()), DoubleCheck.lazy(getMobilyticsEventFactoryProvider()), DoubleCheck.lazy(getFeatureQueryProvider()));
                    this.metricsService = DoubleCheck.reentrantCheck(this.metricsService, obj);
                }
            }
            obj2 = obj;
        }
        return (MetricsService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public MobilyticsReporter mobilyticsReporter() {
        Object obj;
        Object obj2 = this.mobilyticsReporter;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.mobilyticsReporter;
                if (obj instanceof MemoizedSentinel) {
                    obj = MobilyticsModule_ProvideMobilyticsReporterFactory.proxyProvideMobilyticsReporter(this.mobilyticsModule, getMobilytics());
                    this.mobilyticsReporter = DoubleCheck.reentrantCheck(this.mobilyticsReporter, obj);
                }
            }
            obj2 = obj;
        }
        return (MobilyticsReporter) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public ModeService modeService() {
        Object obj;
        Object obj2 = this.modeService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.modeService;
                if (obj instanceof MemoizedSentinel) {
                    obj = ModeModule_ProvideModeServiceFactory.proxyProvideModeService(this.modeModule, getContext());
                    this.modeService = DoubleCheck.reentrantCheck(this.modeService, obj);
                }
            }
            obj2 = obj;
        }
        return (ModeService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public PersistentStorage.Factory persistentStorageFactory() {
        Object obj;
        Object obj2 = this.factory;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.factory;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvidePersistentStorageFactoryFactory.proxyProvidePersistentStorageFactory(this.serviceModule, getContext(), getJsonConverter());
                    this.factory = DoubleCheck.reentrantCheck(this.factory, obj);
                }
            }
            obj2 = obj;
        }
        return (PersistentStorage.Factory) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public MainComponent plus(MainModule mainModule, ConversationModule conversationModule, ElementsModule elementsModule) {
        return new MainComponentImpl(mainModule, conversationModule, elementsModule);
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public RNLogPrinter rnLogPrinter() {
        Object obj;
        Object obj2 = this.rNLogPrinter;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.rNLogPrinter;
                if (obj instanceof MemoizedSentinel) {
                    obj = ServiceModule_ProvidesRNLogPrinterFactory.proxyProvidesRNLogPrinter(this.serviceModule, DoubleCheck.lazy(getRoutingServiceProvider()));
                    this.rNLogPrinter = DoubleCheck.reentrantCheck(this.rNLogPrinter, obj);
                }
            }
            obj2 = obj;
        }
        return (RNLogPrinter) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public RoutingRegistry routingRegistry() {
        Object obj;
        Object obj2 = this.routingRegistry;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.routingRegistry;
                if (obj instanceof MemoizedSentinel) {
                    obj = RoutingModule_ProvideRoutingRegistryFactory.proxyProvideRoutingRegistry(this.routingModule);
                    this.routingRegistry = DoubleCheck.reentrantCheck(this.routingRegistry, obj);
                }
            }
            obj2 = obj;
        }
        return (RoutingRegistry) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public TCommServiceManager tCommServiceManager() {
        Object obj;
        Object obj2 = this.tCommServiceManager;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.tCommServiceManager;
                if (obj instanceof MemoizedSentinel) {
                    obj = TCommServiceModule_ProvideTCommServiceManagerFactory.proxyProvideTCommServiceManager(this.tCommServiceModule, getContext(), identityService(), getNetworkService(), eventBus(), mainActivityLifecycleService(), getMobilytics(), getDeviceInformation(), DoubleCheck.lazy(getFeatureServiceV2Provider()));
                    this.tCommServiceManager = DoubleCheck.reentrantCheck(this.tCommServiceManager, obj);
                }
            }
            obj2 = obj;
        }
        return (TCommServiceManager) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public TTCFService ttcfService() {
        Object obj;
        Object obj2 = this.tTCFService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.tTCFService;
                if (obj instanceof MemoizedSentinel) {
                    obj = TTCFModule_ProvideTTCFServiceFactory.proxyProvideTTCFService(this.tTCFModule);
                    this.tTCFService = DoubleCheck.reentrantCheck(this.tTCFService, obj);
                }
            }
            obj2 = obj;
        }
        return (TTCFService) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public VoiceService voiceService() {
        Object obj;
        Object obj2 = this.voiceService;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.voiceService;
                if (obj instanceof MemoizedSentinel) {
                    obj = VoiceModule_ProvideVoiceServiceFactory.proxyProvideVoiceService(this.voiceModule, DoubleCheck.lazy(getContextProvider()), DoubleCheck.lazy(getDeviceInformationProvider()), DoubleCheck.lazy(getIdentityServiceProvider()), DoubleCheck.lazy(getAccountUpgradeServiceProvider()), DoubleCheck.lazy(getNetworkServiceProvider()), DoubleCheck.lazy(getFactoryProvider()), DoubleCheck.lazy(getLatencyReportingDelegateProvider()), DoubleCheck.lazy(getMetricsServiceProvider()), DoubleCheck.lazy(getRoutingServiceProvider()), DoubleCheck.lazy(getDefaultApplicationLifecycleServiceProvider()));
                    this.voiceService = DoubleCheck.reentrantCheck(this.voiceService, obj);
                }
            }
            obj2 = obj;
        }
        return (VoiceService) obj2;
    }

    private DaggerApplicationComponent(Builder builder) {
        this.context = new MemoizedSentinel();
        this.crashReportingService = new MemoizedSentinel();
        this.crashReporter = new MemoizedSentinel();
        this.latencyInfra = new MemoizedSentinel();
        this.routingRegistryAdapter = new MemoizedSentinel();
        this.featureQuery = new MemoizedSentinel();
        this.deviceInformation = new MemoizedSentinel();
        this.userIdentityStorage = new MemoizedSentinel();
        this.pfmEnvironmentService = new MemoizedSentinel();
        this.persistentEndpointsStorage = new MemoizedSentinel();
        this.cookieManager = new MemoizedSentinel();
        this.gson = new MemoizedSentinel();
        this.urlResolver = new MemoizedSentinel();
        this.metricsServiceV2 = new MemoizedSentinel();
        this.mobilyticsConfiguration = new MemoizedSentinel();
        this.mobilytics = new MemoizedSentinel();
        this.coralService = new MemoizedSentinel();
        this.dataRegionEnvironmentService = new MemoizedSentinel();
        this.environmentService = new MemoizedSentinel();
        this.daggerInitializer = new MemoizedSentinel();
        this.alexaMobileAndroidFeatureServiceImpl = new MemoizedSentinel();
        this.defaultFeatureServiceV2 = new MemoizedSentinel();
        this.platformFeatureServiceV2 = new MemoizedSentinel();
        this.featureServiceV2 = new MemoizedSentinel();
        this.routingService = new MemoizedSentinel();
        this.taskManager = new MemoizedSentinel();
        this.namedExecutorService = new MemoizedSentinel();
        this.namedScheduler = new MemoizedSentinel();
        this.namedDefaultElementLocalStorage = new MemoizedSentinel();
        this.alexaTarazedService = new MemoizedSentinel();
        this.assetManagementService = new MemoizedSentinel();
        this.matterService = new MemoizedSentinel();
        this.aDM = new MemoizedSentinel();
        this.firebaseInstanceId = new MemoizedSentinel();
        this.cryptoFactory = new MemoizedSentinel();
        this.messageCrypto = new MemoizedSentinel();
        this.messagingReceiver = new MemoizedSentinel();
        this.eventBusMessagingReceiver = new MemoizedSentinel();
        this.messagingReceiver2 = new MemoizedSentinel();
        this.messagingReceiver3 = new MemoizedSentinel();
        this.messagingReceiver4 = new MemoizedSentinel();
        this.messagingHandler = new MemoizedSentinel();
        this.messagingSettingsMetricsHandler = new MemoizedSentinel();
        this.networkService = new MemoizedSentinel();
        this.messagingSettings = new MemoizedSentinel();
        this.commsDeviceSupport = new MemoizedSentinel();
        this.personIdProvider = new MemoizedSentinel();
        this.messagingService = new MemoizedSentinel();
        this.accessibilityService = new MemoizedSentinel();
        this.preloadAttributionManager = new MemoizedSentinel();
        this.namedAWSCredentialsProvider = new MemoizedSentinel();
        this.kinesisManager = new MemoizedSentinel();
        this.appSessionClient = new MemoizedSentinel();
        this.kinesisEventClient = new MemoizedSentinel();
        this.mobilyticsEventFactory = new MemoizedSentinel();
        this.metricsService = new MemoizedSentinel();
        this.crashMetadata = new MemoizedSentinel();
        this.eventBus = new MemoizedSentinel();
        this.serviceLifecycle = new MemoizedSentinel();
        this.conversationService = new MemoizedSentinel();
        this.mAPAccountManager = new MemoizedSentinel();
        this.namedString = new MemoizedSentinel();
        this.mAPAccountUpgradeService = new MemoizedSentinel();
        this.accountUpgradeAuthority = new MemoizedSentinel();
        this.accountService = new MemoizedSentinel();
        this.dataStoreHelper = new MemoizedSentinel();
        this.dataStoreService = new MemoizedSentinel();
        this.marketplaceService = new MemoizedSentinel();
        this.featureFilter = new MemoizedSentinel();
        this.featureFilter2 = new MemoizedSentinel();
        this.featureConstraints = new MemoizedSentinel();
        this.userIdentityMapper = new MemoizedSentinel();
        this.userProfileManager = new MemoizedSentinel();
        this.userIdentityRepository = new MemoizedSentinel();
        this.mAPAccountRegistrationService = new MemoizedSentinel();
        this.tokenAccessor = new MemoizedSentinel();
        this.localAndroidKeyValueStore = new MemoizedSentinel();
        this.tokenEncryptor = new MemoizedSentinel();
        this.tokenStorage = new MemoizedSentinel();
        this.delegatedTokenManagement = new MemoizedSentinel();
        this.apesCallerInterface = new MemoizedSentinel();
        this.mAPIdentityService = new MemoizedSentinel();
        this.identityService = new MemoizedSentinel();
        this.accountUpgradeService = new MemoizedSentinel();
        this.latencyReportingDelegate = new MemoizedSentinel();
        this.voiceService = new MemoizedSentinel();
        this.routingRegistry = new MemoizedSentinel();
        this.jsonConverter = new MemoizedSentinel();
        this.factory = new MemoizedSentinel();
        this.certificateReaderService = new MemoizedSentinel();
        this.rNLogPrinter = new MemoizedSentinel();
        this.mainActivityLifecycleService = new MemoizedSentinel();
        this.modeService = new MemoizedSentinel();
        this.tCommServiceManager = new MemoizedSentinel();
        this.defaultApplicationLifecycleService = new MemoizedSentinel();
        this.locationProvider = new MemoizedSentinel();
        this.namedCacheOfAppDataCacheEntry = new MemoizedSentinel();
        this.locationService = new MemoizedSentinel();
        this.commsManager = new MemoizedSentinel();
        this.alexaCommsServiceWrapper = new MemoizedSentinel();
        this.alexaCommsService = new MemoizedSentinel();
        this.commsServiceV2 = new MemoizedSentinel();
        this.mobilyticsReporter = new MemoizedSentinel();
        this.tTCFService = new MemoizedSentinel();
        this.alexaPhotosBackgroundServiceUrlResolver = new MemoizedSentinel();
        this.alexaPhotosBackgroundService = new MemoizedSentinel();
        this.mAPAuthenticatedURLConnectionFactory = new MemoizedSentinel();
        this.endpointsCache = new MemoizedSentinel();
        this.requestAuthenticator = new MemoizedSentinel();
        this.accountConfiguration = new MemoizedSentinel();
        this.clientConfiguration = new MemoizedSentinel();
        this.amazonCloudDriveExtendedClient = new MemoizedSentinel();
        this.cloudDriveService = new MemoizedSentinel();
        this.photoServiceFactory = new MemoizedSentinel();
        this.photoService = new MemoizedSentinel();
        this.backgroundImageService = new MemoizedSentinel();
        this.marketplaceService2 = new MemoizedSentinel();
        this.crashObserverRegistrar = new MemoizedSentinel();
        this.dialogBuilderProvider = new MemoizedSentinel();
        this.componentBinder = new MemoizedSentinel();
        this.okHttpClient = new MemoizedSentinel();
        this.tTCFRoutingDelegate = new MemoizedSentinel();
        this.routingViewService = new MemoizedSentinel();
        this.application = new MemoizedSentinel();
        this.userAgentService = new MemoizedSentinel();
        this.routeFeatureGroupRegistry = new MemoizedSentinel();
        this.routeWatcher = new MemoizedSentinel();
        this.bluetoothService = new MemoizedSentinel();
        this.photosChooser = new MemoizedSentinel();
        this.cloudDriveMetrics = new MemoizedSentinel();
        this.hVAManager = new MemoizedSentinel();
        this.photosAppInfoProvider = new MemoizedSentinel();
        this.photosFeatureGuardian = new MemoizedSentinel();
        this.uploadBundleManager = new MemoizedSentinel();
        this.systemUtility = new MemoizedSentinel();
        this.photosUploader = new MemoizedSentinel();
        this.namedCacheOfHttpResponseWrapper = new MemoizedSentinel();
        this.namedDefaultElementLocalStorage2 = new MemoizedSentinel();
        this.tTCFCheckpoint = new MemoizedSentinel();
        this.headerCacheService = new MemoizedSentinel();
        this.playerCardUpdater = new MemoizedSentinel();
        this.cacheClearOperations = new MemoizedSentinel();
        this.wifiService = new MemoizedSentinel();
        this.preloadAttributionUIManager = new MemoizedSentinel();
        this.intentFactory = new MemoizedSentinel();
        this.driveModeService = new MemoizedSentinel();
        this.featureStore = new MemoizedSentinel();
        this.featureServiceConfiguration = new MemoizedSentinel();
        this.dataStoreService2 = new MemoizedSentinel();
        this.identityPreferencesProvider = new MemoizedSentinel();
        this.applicationModule = builder.applicationModule;
        this.serviceModule = builder.serviceModule;
        this.featuresModule = builder.featuresModule;
        this.identityModule = builder.identityModule;
        this.networkModule = builder.networkModule;
        this.mobilyticsModule = builder.mobilyticsModule;
        this.amazonMessagingModule = builder.amazonMessagingModule;
        this.googleApiModule = builder.googleApiModule;
        this.messagingModule = builder.messagingModule;
        this.commsModule = builder.commsModule;
        this.voiceModule = builder.voiceModule;
        this.sendToAppMessagingReceiverModule = builder.sendToAppMessagingReceiverModule;
        this.preloadAttributionModule = builder.preloadAttributionModule;
        this.metricsModule = builder.metricsModule;
        this.kinesisMetricsModule = builder.kinesisMetricsModule;
        this.eventBusModule = builder.eventBusModule;
        this.dataStoreModule = builder.dataStoreModule;
        this.routingModule = builder.routingModule;
        this.modeModule = builder.modeModule;
        this.tCommServiceModule = builder.tCommServiceModule;
        this.locationModule = builder.locationModule;
        this.tTCFModule = builder.tTCFModule;
        this.photoServiceModule = builder.photoServiceModule;
        this.cloudDriveModule = builder.cloudDriveModule;
        this.alexaDeviceBackgroundImageModule = builder.alexaDeviceBackgroundImageModule;
        this.enrollmentModule = builder.enrollmentModule;
        this.entertainmentModule = builder.entertainmentModule;
        this.driveModeApplicationModule = builder.driveModeApplicationModule;
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public FireOSDirectiveHandlerService inject(FireOSDirectiveHandlerService fireOSDirectiveHandlerService) {
        return injectFireOSDirectiveHandlerService(fireOSDirectiveHandlerService);
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public ExternalUIComponent plus(ExternalUIModule externalUIModule) {
        return new ExternalUIComponentImpl(externalUIModule);
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public ViewBoxFragment inject(ViewBoxFragment viewBoxFragment) {
        return injectViewBoxFragment(viewBoxFragment);
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public AlexaDeviceBackgroundImageActivity inject(AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity) {
        return injectAlexaDeviceBackgroundImageActivity(alexaDeviceBackgroundImageActivity);
    }

    @Override // com.amazon.dee.app.dependencies.ApplicationComponent
    public AlexaWebView inject(AlexaWebView alexaWebView) {
        return injectAlexaWebView(alexaWebView);
    }
}
