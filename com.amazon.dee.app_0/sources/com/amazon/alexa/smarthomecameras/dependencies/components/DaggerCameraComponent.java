package com.amazon.alexa.smarthomecameras.dependencies.components;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AlexaServicesModule_ProvidesAmfApisFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AppLiveViewModule_ProvidesAppLifecycleOwnerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AppLiveViewModule_ProvidesCameraSessionManagerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AppLiveViewModule_ProvidesCamerasAppClientFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AppLiveViewModule_ProvidesExecutorServiceFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AppLiveViewModule_ProvidesLiveViewEventSenderFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AppLiveViewModule_ProvidesModelFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AppLiveViewModule_ProvidesPtzListenerFactoryFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AudioManagerModule_ProvidesAudioManagerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraMetricsModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraMetricsModule_ProvideMobilyticsFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraMetricsModule_ProvideMobilyticsServiceFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule_ProvidesContextFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CoralServiceModule_ProvideCoralServiceFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.EventBusModule_ProvideEventBusFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.FeatureServiceV2Module_ProvideFeatureServiceV2Factory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.NetworkServiceModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.NetworkServiceModule_ProvideNetworkServiceFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingRegistryModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingRegistryModule_ProvideRoutingRegistryFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingServiceModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingServiceModule_ProvidesRoutingServiceFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RtcscModule_ProvidesRtcscAppInfoFactory;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.rtcsc.CamerasAppClient;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import com.dee.app.http.CoralService;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class DaggerCameraComponent implements CameraComponent {
    private Provider<CoralService> provideCoralServiceProvider;
    private Provider<EventBus> provideEventBusProvider;
    private Provider<FeatureServiceV2> provideFeatureServiceV2Provider;
    private Provider<CamerasMobilyticsService> provideMobilyticsServiceProvider;
    private Provider<NetworkService> provideNetworkServiceProvider;
    private Provider<RoutingRegistry> provideRoutingRegistryProvider;
    private Provider<AlexaMobileFrameworkApis> providesAmfApisProvider;
    private Provider<AppLifecycleOwner> providesAppLifecycleOwnerProvider;
    private Provider<AudioManager> providesAudioManagerProvider;
    private Provider<CameraSessionManager> providesCameraSessionManagerProvider;
    private Provider<CamerasAppClient> providesCamerasAppClientProvider;
    private Provider<Context> providesContextProvider;
    private Provider<LiveViewEventSender> providesLiveViewEventSenderProvider;
    private Provider<CameraViewContract.Model> providesModelProvider;
    private Provider<PtzListenerFactory> providesPtzListenerFactoryProvider;
    private Provider<RoutingService> providesRoutingServiceProvider;
    private Provider<RtcscAppInfo> providesRtcscAppInfoProvider;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private ContextModule contextModule;
        private RoutingServiceModule routingServiceModule;

        public CameraComponent build() {
            Preconditions.checkBuilderRequirement(this.contextModule, ContextModule.class);
            Preconditions.checkBuilderRequirement(this.routingServiceModule, RoutingServiceModule.class);
            return new DaggerCameraComponent(this);
        }

        @Deprecated
        public Builder cameraMetricsModule(CameraMetricsModule cameraMetricsModule) {
            Preconditions.checkNotNull(cameraMetricsModule);
            return this;
        }

        public Builder contextModule(ContextModule contextModule) {
            this.contextModule = (ContextModule) Preconditions.checkNotNull(contextModule);
            return this;
        }

        @Deprecated
        public Builder networkServiceModule(NetworkServiceModule networkServiceModule) {
            Preconditions.checkNotNull(networkServiceModule);
            return this;
        }

        @Deprecated
        public Builder routingRegistryModule(RoutingRegistryModule routingRegistryModule) {
            Preconditions.checkNotNull(routingRegistryModule);
            return this;
        }

        public Builder routingServiceModule(RoutingServiceModule routingServiceModule) {
            this.routingServiceModule = (RoutingServiceModule) Preconditions.checkNotNull(routingServiceModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesContextProvider = DoubleCheck.provider(ContextModule_ProvidesContextFactory.create(builder.contextModule));
        this.providesCamerasAppClientProvider = DoubleCheck.provider(AppLiveViewModule_ProvidesCamerasAppClientFactory.create(this.providesContextProvider));
        this.providesAudioManagerProvider = DoubleCheck.provider(AudioManagerModule_ProvidesAudioManagerFactory.create(this.providesContextProvider, this.providesCamerasAppClientProvider));
        this.providesPtzListenerFactoryProvider = DoubleCheck.provider(AppLiveViewModule_ProvidesPtzListenerFactoryFactory.create());
        this.providesRtcscAppInfoProvider = DoubleCheck.provider(RtcscModule_ProvidesRtcscAppInfoFactory.create());
        this.providesAmfApisProvider = DoubleCheck.provider(AlexaServicesModule_ProvidesAmfApisFactory.create(this.providesContextProvider));
        this.provideMobilyticsServiceProvider = DoubleCheck.provider(CameraMetricsModule_ProvideMobilyticsServiceFactory.create(CameraMetricsModule_ProvideMobilyticsFactory.create()));
        this.providesLiveViewEventSenderProvider = DoubleCheck.provider(AppLiveViewModule_ProvidesLiveViewEventSenderFactory.create(this.providesAmfApisProvider, this.provideMobilyticsServiceProvider));
        this.providesCameraSessionManagerProvider = DoubleCheck.provider(AppLiveViewModule_ProvidesCameraSessionManagerFactory.create(this.providesContextProvider, this.providesRtcscAppInfoProvider, this.providesCamerasAppClientProvider, this.providesLiveViewEventSenderProvider, AppLiveViewModule_ProvidesExecutorServiceFactory.create(), this.provideMobilyticsServiceProvider));
        this.providesModelProvider = DoubleCheck.provider(AppLiveViewModule_ProvidesModelFactory.create());
        this.provideNetworkServiceProvider = DoubleCheck.provider(NetworkServiceModule_ProvideNetworkServiceFactory.create());
        this.providesRoutingServiceProvider = DoubleCheck.provider(RoutingServiceModule_ProvidesRoutingServiceFactory.create(builder.routingServiceModule));
        this.providesAppLifecycleOwnerProvider = DoubleCheck.provider(AppLiveViewModule_ProvidesAppLifecycleOwnerFactory.create());
        this.provideFeatureServiceV2Provider = DoubleCheck.provider(FeatureServiceV2Module_ProvideFeatureServiceV2Factory.create());
        this.provideRoutingRegistryProvider = DoubleCheck.provider(RoutingRegistryModule_ProvideRoutingRegistryFactory.create());
        this.provideEventBusProvider = DoubleCheck.provider(EventBusModule_ProvideEventBusFactory.create());
        this.provideCoralServiceProvider = DoubleCheck.provider(CoralServiceModule_ProvideCoralServiceFactory.create());
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public AppLifecycleOwner appLifeycleOwner() {
        return this.providesAppLifecycleOwnerProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public AudioManager audioManager() {
        return this.providesAudioManagerProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public CameraViewContract.Model cameraModel() {
        return this.providesModelProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public CameraSessionManager cameraSessionManager() {
        return this.providesCameraSessionManagerProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public CamerasMobilyticsService camerasMobilyticsService() {
        return this.provideMobilyticsServiceProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public Context context() {
        return this.providesContextProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public CoralService coralService() {
        return this.provideCoralServiceProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public EventBus eventBus() {
        return this.provideEventBusProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public FeatureServiceV2 featureServiceV2() {
        return this.provideFeatureServiceV2Provider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public NetworkService networkService() {
        return this.provideNetworkServiceProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public PtzListenerFactory ptzListenerFactory() {
        return this.providesPtzListenerFactoryProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public RoutingRegistry routingRegistry() {
        return this.provideRoutingRegistryProvider.mo10268get();
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent
    public RoutingService routingService() {
        return this.providesRoutingServiceProvider.mo10268get();
    }

    private DaggerCameraComponent(Builder builder) {
        initialize(builder);
    }
}
