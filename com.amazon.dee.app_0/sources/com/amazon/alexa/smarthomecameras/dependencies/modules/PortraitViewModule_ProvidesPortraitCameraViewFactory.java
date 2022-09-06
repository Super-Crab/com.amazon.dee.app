package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.view.PortraitCameraView;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PortraitViewModule_ProvidesPortraitCameraViewFactory implements Factory<PortraitCameraView> {
    private final Provider<CameraLabel> cameraLabelProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DevicePayload> devicePayloadProvider;
    private final Provider<EntityId> entityIdProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<CameraViewContract.Presenter> presenterProvider;
    private final Provider<PtzListenerFactory> ptzListenerFactoryProvider;
    private final Provider<RoutingRegistry> routingRegistryProvider;

    public PortraitViewModule_ProvidesPortraitCameraViewFactory(Provider<Context> provider, Provider<EntityId> provider2, Provider<CameraLabel> provider3, Provider<DevicePayload> provider4, Provider<CameraViewContract.Presenter> provider5, Provider<PtzListenerFactory> provider6, Provider<NetworkService> provider7, Provider<RoutingRegistry> provider8, Provider<FeatureServiceV2> provider9) {
        this.contextProvider = provider;
        this.entityIdProvider = provider2;
        this.cameraLabelProvider = provider3;
        this.devicePayloadProvider = provider4;
        this.presenterProvider = provider5;
        this.ptzListenerFactoryProvider = provider6;
        this.networkServiceProvider = provider7;
        this.routingRegistryProvider = provider8;
        this.featureServiceV2Provider = provider9;
    }

    public static PortraitViewModule_ProvidesPortraitCameraViewFactory create(Provider<Context> provider, Provider<EntityId> provider2, Provider<CameraLabel> provider3, Provider<DevicePayload> provider4, Provider<CameraViewContract.Presenter> provider5, Provider<PtzListenerFactory> provider6, Provider<NetworkService> provider7, Provider<RoutingRegistry> provider8, Provider<FeatureServiceV2> provider9) {
        return new PortraitViewModule_ProvidesPortraitCameraViewFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static PortraitCameraView provideInstance(Provider<Context> provider, Provider<EntityId> provider2, Provider<CameraLabel> provider3, Provider<DevicePayload> provider4, Provider<CameraViewContract.Presenter> provider5, Provider<PtzListenerFactory> provider6, Provider<NetworkService> provider7, Provider<RoutingRegistry> provider8, Provider<FeatureServiceV2> provider9) {
        return proxyProvidesPortraitCameraView(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get());
    }

    public static PortraitCameraView proxyProvidesPortraitCameraView(Context context, EntityId entityId, CameraLabel cameraLabel, DevicePayload devicePayload, CameraViewContract.Presenter presenter, PtzListenerFactory ptzListenerFactory, NetworkService networkService, RoutingRegistry routingRegistry, FeatureServiceV2 featureServiceV2) {
        return (PortraitCameraView) Preconditions.checkNotNull(PortraitViewModule.providesPortraitCameraView(context, entityId, cameraLabel, devicePayload, presenter, ptzListenerFactory, networkService, routingRegistry, featureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PortraitCameraView mo10268get() {
        return provideInstance(this.contextProvider, this.entityIdProvider, this.cameraLabelProvider, this.devicePayloadProvider, this.presenterProvider, this.ptzListenerFactoryProvider, this.networkServiceProvider, this.routingRegistryProvider, this.featureServiceV2Provider);
    }
}
