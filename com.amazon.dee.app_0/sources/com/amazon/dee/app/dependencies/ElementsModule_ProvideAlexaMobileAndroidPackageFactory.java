package com.amazon.dee.app.dependencies;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
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
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.elements.ReactRouteRegistry;
import com.amazon.dee.app.services.bluetooth.BluetoothService;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.services.useragent.UserAgentService;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.latencyinfra.LatencyInfra;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactPackage;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideAlexaMobileAndroidPackageFactory implements Factory<ReactPackage> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<AlexaMenu> alexaMenuProvider;
    private final Provider<AMPDInformationProvider> ampdInformationProvider;
    private final Provider<BluetoothService> bluetoothServiceProvider;
    private final Provider<BridgeStatusService> bridgeStatusServiceProvider;
    private final Provider<CertificateReaderService> certificateReaderServiceProvider;
    private final Provider<CloudDriveMetrics> cloudDriveMetricsProvider;
    private final Provider<CollectionsFactory> collectionsFactoryProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusAndEventBusLazyProvider;
    private final Provider<RouteFeatureGroupRegistry> groupRegistryProvider;
    private final Provider<IdentityService> identityServiceAndIdentityServiceLazyProvider;
    private final Provider<LatencyInfra> latencyInfraProvider;
    private final Provider<LocationProvider> locationProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final Provider<MobilyticsEventFactory> mobilyticsEventFactoryProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ElementsModule module;
    private final Provider<PhotosChooser> photosChooserProvider;
    private final Provider<PhotosFeatureGuardian> photosFeatureGuardianLazyProvider;
    private final Provider<PhotosUploader> photosUploaderProvider;
    private final Provider<PlatformFeatureServiceV2> platformFeatureServiceV2Provider;
    private final Provider<ReactFeatureManager> reactFeatureManagerRefProvider;
    private final Provider<ReactRouteRegistry> reactRouteRegistryProvider;
    private final Provider<RoutingRegistry> routingRegistryProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;
    private final Provider<TaskManager> taskManagerLazyProvider;
    private final Provider<TestArgumentsService> testArgumentsProvider;
    private final Provider<UploadBundleManager> uploadBundleManagerProvider;
    private final Provider<UserAgentService> userAgentServiceProvider;
    private final Provider<VoiceService> voiceServiceProvider;

    public ElementsModule_ProvideAlexaMobileAndroidPackageFactory(ElementsModule elementsModule, Provider<CollectionsFactory> provider, Provider<EnvironmentService> provider2, Provider<DeviceInformation> provider3, Provider<RoutingService> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<UserAgentService> provider7, Provider<MetricsService> provider8, Provider<MetricsServiceV2> provider9, Provider<Mobilytics> provider10, Provider<MobilyticsEventFactory> provider11, Provider<LocationService> provider12, Provider<EventBus> provider13, Provider<ReactFeatureManager> provider14, Provider<BridgeStatusService> provider15, Provider<VoiceService> provider16, Provider<AlexaMenu> provider17, Provider<RoutingRegistry> provider18, Provider<RouteFeatureGroupRegistry> provider19, Provider<ReactRouteRegistry> provider20, Provider<LocationProvider> provider21, Provider<PersistentStorage.Factory> provider22, Provider<LatencyInfra> provider23, Provider<BluetoothService> provider24, Provider<PhotosChooser> provider25, Provider<PhotosUploader> provider26, Provider<UploadBundleManager> provider27, Provider<TaskManager> provider28, Provider<CertificateReaderService> provider29, Provider<TestArgumentsService> provider30, Provider<PhotosFeatureGuardian> provider31, Provider<AMPDInformationProvider> provider32, Provider<PlatformFeatureServiceV2> provider33, Provider<CloudDriveMetrics> provider34) {
        this.module = elementsModule;
        this.collectionsFactoryProvider = provider;
        this.environmentServiceProvider = provider2;
        this.deviceInformationProvider = provider3;
        this.routingServiceProvider = provider4;
        this.identityServiceAndIdentityServiceLazyProvider = provider5;
        this.accountServiceProvider = provider6;
        this.userAgentServiceProvider = provider7;
        this.metricsServiceProvider = provider8;
        this.metricsServiceV2Provider = provider9;
        this.mobilyticsProvider = provider10;
        this.mobilyticsEventFactoryProvider = provider11;
        this.locationServiceProvider = provider12;
        this.eventBusAndEventBusLazyProvider = provider13;
        this.reactFeatureManagerRefProvider = provider14;
        this.bridgeStatusServiceProvider = provider15;
        this.voiceServiceProvider = provider16;
        this.alexaMenuProvider = provider17;
        this.routingRegistryProvider = provider18;
        this.groupRegistryProvider = provider19;
        this.reactRouteRegistryProvider = provider20;
        this.locationProvider = provider21;
        this.storageFactoryProvider = provider22;
        this.latencyInfraProvider = provider23;
        this.bluetoothServiceProvider = provider24;
        this.photosChooserProvider = provider25;
        this.photosUploaderProvider = provider26;
        this.uploadBundleManagerProvider = provider27;
        this.taskManagerLazyProvider = provider28;
        this.certificateReaderServiceProvider = provider29;
        this.testArgumentsProvider = provider30;
        this.photosFeatureGuardianLazyProvider = provider31;
        this.ampdInformationProvider = provider32;
        this.platformFeatureServiceV2Provider = provider33;
        this.cloudDriveMetricsProvider = provider34;
    }

    public static ElementsModule_ProvideAlexaMobileAndroidPackageFactory create(ElementsModule elementsModule, Provider<CollectionsFactory> provider, Provider<EnvironmentService> provider2, Provider<DeviceInformation> provider3, Provider<RoutingService> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<UserAgentService> provider7, Provider<MetricsService> provider8, Provider<MetricsServiceV2> provider9, Provider<Mobilytics> provider10, Provider<MobilyticsEventFactory> provider11, Provider<LocationService> provider12, Provider<EventBus> provider13, Provider<ReactFeatureManager> provider14, Provider<BridgeStatusService> provider15, Provider<VoiceService> provider16, Provider<AlexaMenu> provider17, Provider<RoutingRegistry> provider18, Provider<RouteFeatureGroupRegistry> provider19, Provider<ReactRouteRegistry> provider20, Provider<LocationProvider> provider21, Provider<PersistentStorage.Factory> provider22, Provider<LatencyInfra> provider23, Provider<BluetoothService> provider24, Provider<PhotosChooser> provider25, Provider<PhotosUploader> provider26, Provider<UploadBundleManager> provider27, Provider<TaskManager> provider28, Provider<CertificateReaderService> provider29, Provider<TestArgumentsService> provider30, Provider<PhotosFeatureGuardian> provider31, Provider<AMPDInformationProvider> provider32, Provider<PlatformFeatureServiceV2> provider33, Provider<CloudDriveMetrics> provider34) {
        return new ElementsModule_ProvideAlexaMobileAndroidPackageFactory(elementsModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule, Provider<CollectionsFactory> provider, Provider<EnvironmentService> provider2, Provider<DeviceInformation> provider3, Provider<RoutingService> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<UserAgentService> provider7, Provider<MetricsService> provider8, Provider<MetricsServiceV2> provider9, Provider<Mobilytics> provider10, Provider<MobilyticsEventFactory> provider11, Provider<LocationService> provider12, Provider<EventBus> provider13, Provider<ReactFeatureManager> provider14, Provider<BridgeStatusService> provider15, Provider<VoiceService> provider16, Provider<AlexaMenu> provider17, Provider<RoutingRegistry> provider18, Provider<RouteFeatureGroupRegistry> provider19, Provider<ReactRouteRegistry> provider20, Provider<LocationProvider> provider21, Provider<PersistentStorage.Factory> provider22, Provider<LatencyInfra> provider23, Provider<BluetoothService> provider24, Provider<PhotosChooser> provider25, Provider<PhotosUploader> provider26, Provider<UploadBundleManager> provider27, Provider<TaskManager> provider28, Provider<CertificateReaderService> provider29, Provider<TestArgumentsService> provider30, Provider<PhotosFeatureGuardian> provider31, Provider<AMPDInformationProvider> provider32, Provider<PlatformFeatureServiceV2> provider33, Provider<CloudDriveMetrics> provider34) {
        return proxyProvideAlexaMobileAndroidPackage(elementsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), provider12.mo10268get(), provider13.mo10268get(), DoubleCheck.lazy(provider14), provider15.mo10268get(), provider16.mo10268get(), provider17.mo10268get(), provider18.mo10268get(), provider19.mo10268get(), provider20.mo10268get(), provider21.mo10268get(), provider22.mo10268get(), provider23.mo10268get(), provider24.mo10268get(), provider25.mo10268get(), DoubleCheck.lazy(provider26), DoubleCheck.lazy(provider27), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider28), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider29), provider30.mo10268get(), DoubleCheck.lazy(provider31), DoubleCheck.lazy(provider32), provider33.mo10268get(), DoubleCheck.lazy(provider34));
    }

    public static ReactPackage proxyProvideAlexaMobileAndroidPackage(ElementsModule elementsModule, CollectionsFactory collectionsFactory, EnvironmentService environmentService, DeviceInformation deviceInformation, RoutingService routingService, IdentityService identityService, AccountService accountService, UserAgentService userAgentService, MetricsService metricsService, MetricsServiceV2 metricsServiceV2, Lazy<Mobilytics> lazy, Lazy<MobilyticsEventFactory> lazy2, LocationService locationService, EventBus eventBus, Lazy<ReactFeatureManager> lazy3, BridgeStatusService bridgeStatusService, VoiceService voiceService, AlexaMenu alexaMenu, RoutingRegistry routingRegistry, RouteFeatureGroupRegistry routeFeatureGroupRegistry, ReactRouteRegistry reactRouteRegistry, LocationProvider locationProvider, PersistentStorage.Factory factory, LatencyInfra latencyInfra, BluetoothService bluetoothService, PhotosChooser photosChooser, Lazy<PhotosUploader> lazy4, Lazy<UploadBundleManager> lazy5, Lazy<EventBus> lazy6, Lazy<TaskManager> lazy7, Lazy<IdentityService> lazy8, Lazy<CertificateReaderService> lazy9, TestArgumentsService testArgumentsService, Lazy<PhotosFeatureGuardian> lazy10, Lazy<AMPDInformationProvider> lazy11, PlatformFeatureServiceV2 platformFeatureServiceV2, Lazy<CloudDriveMetrics> lazy12) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideAlexaMobileAndroidPackage(collectionsFactory, environmentService, deviceInformation, routingService, identityService, accountService, userAgentService, metricsService, metricsServiceV2, lazy, lazy2, locationService, eventBus, lazy3, bridgeStatusService, voiceService, alexaMenu, routingRegistry, routeFeatureGroupRegistry, reactRouteRegistry, locationProvider, factory, latencyInfra, bluetoothService, photosChooser, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, testArgumentsService, lazy10, lazy11, platformFeatureServiceV2, lazy12), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module, this.collectionsFactoryProvider, this.environmentServiceProvider, this.deviceInformationProvider, this.routingServiceProvider, this.identityServiceAndIdentityServiceLazyProvider, this.accountServiceProvider, this.userAgentServiceProvider, this.metricsServiceProvider, this.metricsServiceV2Provider, this.mobilyticsProvider, this.mobilyticsEventFactoryProvider, this.locationServiceProvider, this.eventBusAndEventBusLazyProvider, this.reactFeatureManagerRefProvider, this.bridgeStatusServiceProvider, this.voiceServiceProvider, this.alexaMenuProvider, this.routingRegistryProvider, this.groupRegistryProvider, this.reactRouteRegistryProvider, this.locationProvider, this.storageFactoryProvider, this.latencyInfraProvider, this.bluetoothServiceProvider, this.photosChooserProvider, this.photosUploaderProvider, this.uploadBundleManagerProvider, this.taskManagerLazyProvider, this.certificateReaderServiceProvider, this.testArgumentsProvider, this.photosFeatureGuardianLazyProvider, this.ampdInformationProvider, this.platformFeatureServiceV2Provider, this.cloudDriveMetricsProvider);
    }
}
