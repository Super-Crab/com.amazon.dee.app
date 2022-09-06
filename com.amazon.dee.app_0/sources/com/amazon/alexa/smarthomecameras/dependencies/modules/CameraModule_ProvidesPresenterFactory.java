package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class CameraModule_ProvidesPresenterFactory implements Factory<CameraViewContract.Presenter> {
    private final Provider<AppLifecycleOwner> appLifecycleOwnerProvider;
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<CameraSessionManager> cameraSessionManagerProvider;
    private final Provider<CameraViewContract.Model> cameraViewModelProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<DevicePayload> devicePayloadProvider;
    private final Provider<EntityId> entityIdProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<CamerasMobilyticsService> mobilyticsServiceProvider;
    private final CameraModule module;
    private final Provider<RoutingService> routingServiceProvider;

    public CameraModule_ProvidesPresenterFactory(CameraModule cameraModule, Provider<EntityId> provider, Provider<CameraViewContract.Model> provider2, Provider<CameraSessionManager> provider3, Provider<AudioManager> provider4, Provider<AppLifecycleOwner> provider5, Provider<CamerasMobilyticsService> provider6, Provider<RoutingService> provider7, Provider<FeatureServiceV2> provider8, Provider<DevicePayload> provider9, Provider<CoralService> provider10) {
        this.module = cameraModule;
        this.entityIdProvider = provider;
        this.cameraViewModelProvider = provider2;
        this.cameraSessionManagerProvider = provider3;
        this.audioManagerProvider = provider4;
        this.appLifecycleOwnerProvider = provider5;
        this.mobilyticsServiceProvider = provider6;
        this.routingServiceProvider = provider7;
        this.featureServiceV2Provider = provider8;
        this.devicePayloadProvider = provider9;
        this.coralServiceProvider = provider10;
    }

    public static CameraModule_ProvidesPresenterFactory create(CameraModule cameraModule, Provider<EntityId> provider, Provider<CameraViewContract.Model> provider2, Provider<CameraSessionManager> provider3, Provider<AudioManager> provider4, Provider<AppLifecycleOwner> provider5, Provider<CamerasMobilyticsService> provider6, Provider<RoutingService> provider7, Provider<FeatureServiceV2> provider8, Provider<DevicePayload> provider9, Provider<CoralService> provider10) {
        return new CameraModule_ProvidesPresenterFactory(cameraModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static CameraViewContract.Presenter provideInstance(CameraModule cameraModule, Provider<EntityId> provider, Provider<CameraViewContract.Model> provider2, Provider<CameraSessionManager> provider3, Provider<AudioManager> provider4, Provider<AppLifecycleOwner> provider5, Provider<CamerasMobilyticsService> provider6, Provider<RoutingService> provider7, Provider<FeatureServiceV2> provider8, Provider<DevicePayload> provider9, Provider<CoralService> provider10) {
        return proxyProvidesPresenter(cameraModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get());
    }

    public static CameraViewContract.Presenter proxyProvidesPresenter(CameraModule cameraModule, EntityId entityId, CameraViewContract.Model model, CameraSessionManager cameraSessionManager, AudioManager audioManager, AppLifecycleOwner appLifecycleOwner, CamerasMobilyticsService camerasMobilyticsService, RoutingService routingService, FeatureServiceV2 featureServiceV2, DevicePayload devicePayload, CoralService coralService) {
        return (CameraViewContract.Presenter) Preconditions.checkNotNull(cameraModule.providesPresenter(entityId, model, cameraSessionManager, audioManager, appLifecycleOwner, camerasMobilyticsService, routingService, featureServiceV2, devicePayload, coralService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CameraViewContract.Presenter mo10268get() {
        return provideInstance(this.module, this.entityIdProvider, this.cameraViewModelProvider, this.cameraSessionManagerProvider, this.audioManagerProvider, this.appLifecycleOwnerProvider, this.mobilyticsServiceProvider, this.routingServiceProvider, this.featureServiceV2Provider, this.devicePayloadProvider, this.coralServiceProvider);
    }
}
