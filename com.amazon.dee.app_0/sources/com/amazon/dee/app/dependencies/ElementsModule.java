package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import com.airbnb.lottie.react.LottiePackage;
import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.devicesetup.bridge.AlexaMobileAndroidIoTSoftAPPackage;
import com.amazon.alexa.devicesetup.bridge.DeviceSetupFFSReactPackage;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventing.EventArgs;
import com.amazon.alexa.eventing.EventHandler;
import com.amazon.alexa.eventing.Listen;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.growth.coachmark.rnbridge.CoachMarkRNPackage;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.location.LocationProvider;
import com.amazon.alexa.location.LocationService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.photos.PhotosFeatureGuardian;
import com.amazon.alexa.photos.UploadBundleManager;
import com.amazon.alexa.photos.api.PhotosChooser;
import com.amazon.alexa.photos.api.PhotosUploader;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.RouteWatcher;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.dee.app.R;
import com.amazon.dee.app.elements.AlexaMobileAndroidPackage;
import com.amazon.dee.app.elements.AlexaNativeModuleCallExceptionHandler;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.elements.ElementsRoutingAdapter;
import com.amazon.dee.app.elements.ReactBridgeMetrics;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.elements.ReactBridgeStatusService;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.elements.ReactRouteRegistry;
import com.amazon.dee.app.elements.SMSMessagingPackage;
import com.amazon.dee.app.elements.SharedInstanceReactFeatureManager;
import com.amazon.dee.app.metrics.FetchMetricsInterceptor;
import com.amazon.dee.app.services.bluetooth.BluetoothService;
import com.amazon.dee.app.services.coral.AcceptLanguageRequestInterceptor;
import com.amazon.dee.app.services.coral.ElementsCookieAuthenticationRequestInterceptor;
import com.amazon.dee.app.services.coral.HttpCoralAuthenticationResponseInterceptor;
import com.amazon.dee.app.services.coral.UserAgentRequestInterceptor;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.services.toolbar.ToolbarDelegate;
import com.amazon.dee.app.services.toolbar.ToolbarService;
import com.amazon.dee.app.services.toolbar.ToolbarWatcher;
import com.amazon.dee.app.services.useragent.UserAgentService;
import com.amazon.dee.app.ui.main.MainActivity;
import com.amazon.dee.app.ui.main.RNLogPrinter;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.TimelineInputArgument;
import com.amazon.mobile.heremapsexplore.ReactNativeHereMapsExplorePackage;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import com.bugsnag.BugsnagReactNative;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.HttpResponseWrapper;
import com.dee.app.data.DefaultElementLocalStorage;
import com.dee.app.data.reactnative.AlexaDataAPIReactNativePackage;
import com.dee.app.data.reactnative.DefaultElementsDataService;
import com.dee.app.data.reactnative.ElementsDataService;
import com.dee.app.http.HttpCoralService;
import com.dee.app.http.UrlResolver;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.google.gson.Gson;
import com.horcrux.svg.SvgPackage;
import com.imagepicker.ImagePickerPackage;
import com.reactcommunity.rndatetimepicker.RNDateTimePickerPackage;
import com.reactnativecommunity.cameraroll.CameraRollPackage;
import com.reactnativecommunity.netinfo.NetInfoPackage;
import com.reactnativecommunity.picker.RNCPickerPackage;
import com.reactnativecommunity.slider.ReactSliderPackage;
import com.reactnativecommunity.viewpager.RNCViewPagerPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.expo.appearance.RNCAppearancePackage;
import it.innove.BleManagerPackage;
import java.lang.ref.WeakReference;
import java.util.Set;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import org.reactnative.camera.RNCameraPackage;
@Module
/* loaded from: classes12.dex */
public class ElementsModule {
    public static final String REACT_DEVELOPER_SUPPORT = "ReactDeveloperSupport";
    private static final String SP_RN_BRIDGE_LOAD = "iRNBridgeLoad";
    private static final String TAG = Log.tag(ElementsModule.class);
    private static final long WAIT_TIME_IN_MILLIS = 10000;
    MainActivity activity;
    ReactInstanceManager.ReactInstanceEventListener reactInstanceEventListener = null;

    public ElementsModule(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    @IntoSet
    public ReactPackage provideAlexaDataAPIPackage(ElementsDataService elementsDataService, @Named("dataCache") DefaultElementLocalStorage defaultElementLocalStorage, @Named("dataStore") DefaultElementLocalStorage defaultElementLocalStorage2) {
        return new AlexaDataAPIReactNativePackage(elementsDataService, defaultElementLocalStorage, defaultElementLocalStorage2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    @IntoSet
    public ReactPackage provideAlexaIoTSoftAPPackage() {
        return new AlexaMobileAndroidIoTSoftAPPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideAlexaMobileAndroidPackage(CollectionsFactory collectionsFactory, EnvironmentService environmentService, DeviceInformation deviceInformation, RoutingService routingService, IdentityService identityService, AccountService accountService, UserAgentService userAgentService, MetricsService metricsService, MetricsServiceV2 metricsServiceV2, Lazy<Mobilytics> lazy, Lazy<MobilyticsEventFactory> lazy2, LocationService locationService, EventBus eventBus, Lazy<ReactFeatureManager> lazy3, BridgeStatusService bridgeStatusService, VoiceService voiceService, AlexaMenu alexaMenu, RoutingRegistry routingRegistry, RouteFeatureGroupRegistry routeFeatureGroupRegistry, ReactRouteRegistry reactRouteRegistry, LocationProvider locationProvider, PersistentStorage.Factory factory, LatencyInfra latencyInfra, BluetoothService bluetoothService, PhotosChooser photosChooser, Lazy<PhotosUploader> lazy4, Lazy<UploadBundleManager> lazy5, Lazy<EventBus> lazy6, Lazy<TaskManager> lazy7, Lazy<IdentityService> lazy8, Lazy<CertificateReaderService> lazy9, TestArgumentsService testArgumentsService, Lazy<PhotosFeatureGuardian> lazy10, Lazy<AMPDInformationProvider> lazy11, PlatformFeatureServiceV2 platformFeatureServiceV2, Lazy<CloudDriveMetrics> lazy12) {
        return new AlexaMobileAndroidPackage(collectionsFactory, environmentService, deviceInformation, routingService, identityService, accountService, userAgentService, metricsService, metricsServiceV2, lazy, lazy2, locationService, eventBus, lazy3, bridgeStatusService, voiceService, alexaMenu, routingRegistry, routeFeatureGroupRegistry, reactRouteRegistry, locationProvider, factory, latencyInfra, bluetoothService, new WeakReference(this.activity), photosChooser, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, testArgumentsService, lazy10, lazy11, platformFeatureServiceV2, lazy12);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public AlexaNativeModuleCallExceptionHandler provideAlexaNativeModuleCallExceptionHandler(RNLogPrinter rNLogPrinter) {
        return new AlexaNativeModuleCallExceptionHandler(rNLogPrinter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideAppearancePackage() {
        return new RNCAppearancePackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    @IntoSet
    public ReactPackage provideBleManagerPackage() {
        return new BleManagerPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public BridgeStatusService provideBridgeStatusService(MetricsService metricsService, EventBus eventBus) {
        return new ReactBridgeStatusService(metricsService, eventBus);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideBugsnagPackage(CrashReportingService crashReportingService) {
        crashReportingService.start();
        return BugsnagReactNative.getPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideCameraPackage() {
        return new RNCameraPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public CollectionsFactory provideCollectionsFactory() {
        return new CollectionsFactory();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideDateTimePickerPackage() {
        return new RNDateTimePickerPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    @IntoSet
    public ReactPackage provideDeviceSetupFFSReactPackage() {
        return new DeviceSetupFFSReactPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public ElementsDataService provideElementsDataService(Lazy<CookieManager> lazy, OkHttpClient okHttpClient, Gson gson, MetricsServiceV2 metricsServiceV2, UrlResolver urlResolver, Lazy<IdentityService> lazy2, Lazy<AccountService> lazy3, Lazy<RoutingService> lazy4, @Named("httpCache") Cache<HttpResponseWrapper> cache, Lazy<MetricsService> lazy5, Lazy<FeatureServiceV2> lazy6, Lazy<EnvironmentService> lazy7, Lazy<Mobilytics> lazy8) {
        HttpCoralService httpCoralService = new HttpCoralService(okHttpClient, gson, urlResolver, metricsServiceV2, false);
        FetchMetricsInterceptor fetchMetricsInterceptor = new FetchMetricsInterceptor(metricsServiceV2);
        httpCoralService.addRequestInterceptor(new UserAgentRequestInterceptor());
        httpCoralService.addRequestInterceptor(new ElementsCookieAuthenticationRequestInterceptor(lazy, lazy2, lazy5, lazy8, lazy6, lazy7));
        httpCoralService.addRequestInterceptor(new AcceptLanguageRequestInterceptor());
        httpCoralService.addRequestInterceptor(fetchMetricsInterceptor);
        httpCoralService.addResponseInterceptor(new HttpCoralAuthenticationResponseInterceptor(okHttpClient, lazy2, lazy3, lazy4, lazy8));
        httpCoralService.addResponseInterceptor(fetchMetricsInterceptor);
        return new DefaultElementsDataService(httpCoralService, cache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public ElementsRoutingAdapter provideElementsRoutingAdapter(Router router, ReactFeatureManager reactFeatureManager, IdentityService identityService, ReactBridgeService reactBridgeService) {
        MainActivity mainActivity = this.activity;
        return new ElementsRoutingAdapter(mainActivity, mainActivity, router, reactFeatureManager, identityService, reactBridgeService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideImagePickerPackage() {
        return new ImagePickerPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideLottiePackage() {
        return new LottiePackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @SuppressFBWarnings({"DLS_DEAD_LOCAL_STORE"})
    @IntoSet
    public ReactPackage provideMainReactPackage(Lazy<FeatureServiceV2> lazy) {
        Context applicationContext = this.activity.getApplicationContext();
        return new MainReactPackage(new MainPackageConfig.Builder().setFrescoConfig(ImagePipelineConfig.newBuilder(applicationContext).setBitmapMemoryCacheParamsSupplier(new CustomBitmapMemoryCacheParamsSupplier(applicationContext)).build()).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideRNCPickerPackage() {
        return new RNCPickerPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public ReactBridgeMetrics provideReactBridgeMetrics(MetricsServiceV2 metricsServiceV2, BridgeStatusService bridgeStatusService) {
        return new ReactBridgeMetrics(metricsServiceV2, bridgeStatusService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public ReactBridgeService provideReactBridgeService(Application application, Set<ReactPackage> set, @Named("ReactDeveloperSupport") boolean z, AlexaNativeModuleCallExceptionHandler alexaNativeModuleCallExceptionHandler, MetricsServiceV2 metricsServiceV2, Lazy<RoutingService> lazy, MainActivityLifecycleService mainActivityLifecycleService) {
        return new ReactBridgeService(application, set, z, alexaNativeModuleCallExceptionHandler, metricsServiceV2, lazy, mainActivityLifecycleService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Named("ReactDeveloperSupport")
    public boolean provideReactDeveloperSupportEnabled() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public ReactInstanceManager provideReactInstanceManager(ReactBridgeService reactBridgeService, ReactBridgeMetrics reactBridgeMetrics, BridgeStatusService bridgeStatusService) {
        reactBridgeService.setBridgeMetrics(reactBridgeMetrics);
        final TimelineInputArgument.Builder startLoadingReactNative = LatencyService.startLoadingReactNative();
        Listen.once(bridgeStatusService.onReadyChange(), new EventHandler() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$ElementsModule$yjfMNfNmM7MAu0mcIOfPtdv4OaQ
            @Override // com.amazon.alexa.eventing.EventHandler
            public final void onEvent(EventArgs eventArgs) {
                LatencyService.reactNativeLoaded(TimelineInputArgument.Builder.this);
            }
        });
        reactBridgeService.start(true);
        return reactBridgeService.instance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public ReactFeatureManager provideReactInstanceManagerProvider(CollectionsFactory collectionsFactory, ReactInstanceManager reactInstanceManager, BridgeStatusService bridgeStatusService, MetricsServiceV2 metricsServiceV2, @Named("ReactDeveloperSupport") boolean z, TabLayoutStatusService tabLayoutStatusService, ReactBridgeService reactBridgeService) {
        MainActivity mainActivity = this.activity;
        return new SharedInstanceReactFeatureManager(mainActivity, mainActivity, collectionsFactory, reactInstanceManager, bridgeStatusService, metricsServiceV2, z, tabLayoutStatusService, reactBridgeService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideReactNativeCoachMarkPackage() {
        return new CoachMarkRNPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideReactNativeHereMapsExplorePackage() {
        return new ReactNativeHereMapsExplorePackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public ReactRouteRegistry provideReactRouteRegistry(RouteFeatureGroupRegistry routeFeatureGroupRegistry, RouteWatcher routeWatcher) {
        return new ReactRouteRegistry(routeFeatureGroupRegistry, routeWatcher);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public Router provideRouter(ReactFeatureManager reactFeatureManager) {
        Component component = new Component();
        component.provide((Class<? extends Class>) ReactFeatureManager.class, (Class) reactFeatureManager).register();
        Router router = new Router(this.activity, component);
        router.attach((ViewGroup) this.activity.findViewById(R.id.rn_container));
        return router;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideSMSMessagingPackage() {
        return new SMSMessagingPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideSliderPackage() {
        return new ReactSliderPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideSvgPackage() {
        return new SvgPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public ToolbarService provideToolbarService(EventBus eventBus, Gson gson, Activity activity, ReactInstanceManager reactInstanceManager) {
        return new ToolbarService(eventBus, gson, (ToolbarDelegate) activity, activity, reactInstanceManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @MainScope
    public ToolbarWatcher provideToolbarWatcher(RoutingService routingService, ToolbarService toolbarService, RoutingViewService routingViewService, CrashMetadata crashMetadata) {
        return new ToolbarWatcher(routingService, toolbarService, routingViewService, crashMetadata);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage provideViewPagerPackage() {
        return new RNCViewPagerPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage providesRNCCameraRoll() {
        return new CameraRollPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage providesRNCNetInfo() {
        return new NetInfoPackage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @IntoSet
    public ReactPackage providesRNCWebview() {
        return new RNCWebViewPackage();
    }
}
