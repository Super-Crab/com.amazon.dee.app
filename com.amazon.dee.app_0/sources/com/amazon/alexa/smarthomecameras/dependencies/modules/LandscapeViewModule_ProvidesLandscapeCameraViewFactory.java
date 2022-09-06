package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.view.LandscapeCameraView;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class LandscapeViewModule_ProvidesLandscapeCameraViewFactory implements Factory<LandscapeCameraView> {
    private final Provider<CameraLabel> cameraLabelProvider;
    private final Provider<DevicePayload> devicePayloadProvider;
    private final Provider<EntityId> entityIdProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<CamerasMobilyticsService> mobilyticsServiceProvider;
    private final LandscapeViewModule module;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<CameraViewContract.Presenter> presenterProvider;
    private final Provider<PtzListenerFactory> ptzListenerFactoryProvider;

    public LandscapeViewModule_ProvidesLandscapeCameraViewFactory(LandscapeViewModule landscapeViewModule, Provider<EntityId> provider, Provider<CameraLabel> provider2, Provider<DevicePayload> provider3, Provider<CameraViewContract.Presenter> provider4, Provider<PtzListenerFactory> provider5, Provider<CamerasMobilyticsService> provider6, Provider<NetworkService> provider7, Provider<FeatureServiceV2> provider8) {
        this.module = landscapeViewModule;
        this.entityIdProvider = provider;
        this.cameraLabelProvider = provider2;
        this.devicePayloadProvider = provider3;
        this.presenterProvider = provider4;
        this.ptzListenerFactoryProvider = provider5;
        this.mobilyticsServiceProvider = provider6;
        this.networkServiceProvider = provider7;
        this.featureServiceV2Provider = provider8;
    }

    public static LandscapeViewModule_ProvidesLandscapeCameraViewFactory create(LandscapeViewModule landscapeViewModule, Provider<EntityId> provider, Provider<CameraLabel> provider2, Provider<DevicePayload> provider3, Provider<CameraViewContract.Presenter> provider4, Provider<PtzListenerFactory> provider5, Provider<CamerasMobilyticsService> provider6, Provider<NetworkService> provider7, Provider<FeatureServiceV2> provider8) {
        return new LandscapeViewModule_ProvidesLandscapeCameraViewFactory(landscapeViewModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static LandscapeCameraView provideInstance(LandscapeViewModule landscapeViewModule, Provider<EntityId> provider, Provider<CameraLabel> provider2, Provider<DevicePayload> provider3, Provider<CameraViewContract.Presenter> provider4, Provider<PtzListenerFactory> provider5, Provider<CamerasMobilyticsService> provider6, Provider<NetworkService> provider7, Provider<FeatureServiceV2> provider8) {
        return proxyProvidesLandscapeCameraView(landscapeViewModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    public static LandscapeCameraView proxyProvidesLandscapeCameraView(LandscapeViewModule landscapeViewModule, EntityId entityId, CameraLabel cameraLabel, DevicePayload devicePayload, CameraViewContract.Presenter presenter, PtzListenerFactory ptzListenerFactory, CamerasMobilyticsService camerasMobilyticsService, NetworkService networkService, FeatureServiceV2 featureServiceV2) {
        return (LandscapeCameraView) Preconditions.checkNotNull(landscapeViewModule.providesLandscapeCameraView(entityId, cameraLabel, devicePayload, presenter, ptzListenerFactory, camerasMobilyticsService, networkService, featureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LandscapeCameraView mo10268get() {
        return provideInstance(this.module, this.entityIdProvider, this.cameraLabelProvider, this.devicePayloadProvider, this.presenterProvider, this.ptzListenerFactoryProvider, this.mobilyticsServiceProvider, this.networkServiceProvider, this.featureServiceV2Provider);
    }
}
