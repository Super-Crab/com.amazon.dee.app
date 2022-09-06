package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.util.DisplayMetrics;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.smarthomecameras.directives.DirectiveSender;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.ptz.PhysicalPtzGestureHandler;
import com.amazon.alexa.smarthomecameras.ptz.directives.PhysicalPtzDirectiveFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.ptz.digital.DigitalZoomState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesPhysicalPtzGestureHandlerFactory implements Factory<PhysicalPtzGestureHandler> {
    private final Provider<DevicePayload> devicePayloadProvider;
    private final Provider<DirectiveSender> directiveSenderProvider;
    private final Provider<DisplayMetrics> displayMetricsProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<CamerasMobilyticsService> metricsServiceProvider;
    private final PtzModule module;
    private final Provider<PhysicalPtzDirectiveFactory> physicalPtzDirectiveFactoryProvider;
    private final Provider<DigitalZoomState> stateProvider;

    public PtzModule_ProvidesPhysicalPtzGestureHandlerFactory(PtzModule ptzModule, Provider<DigitalZoomState> provider, Provider<DisplayMetrics> provider2, Provider<PhysicalPtzDirectiveFactory> provider3, Provider<DirectiveSender> provider4, Provider<FeatureServiceV2> provider5, Provider<CamerasMobilyticsService> provider6, Provider<DevicePayload> provider7) {
        this.module = ptzModule;
        this.stateProvider = provider;
        this.displayMetricsProvider = provider2;
        this.physicalPtzDirectiveFactoryProvider = provider3;
        this.directiveSenderProvider = provider4;
        this.featureServiceV2Provider = provider5;
        this.metricsServiceProvider = provider6;
        this.devicePayloadProvider = provider7;
    }

    public static PtzModule_ProvidesPhysicalPtzGestureHandlerFactory create(PtzModule ptzModule, Provider<DigitalZoomState> provider, Provider<DisplayMetrics> provider2, Provider<PhysicalPtzDirectiveFactory> provider3, Provider<DirectiveSender> provider4, Provider<FeatureServiceV2> provider5, Provider<CamerasMobilyticsService> provider6, Provider<DevicePayload> provider7) {
        return new PtzModule_ProvidesPhysicalPtzGestureHandlerFactory(ptzModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static PhysicalPtzGestureHandler provideInstance(PtzModule ptzModule, Provider<DigitalZoomState> provider, Provider<DisplayMetrics> provider2, Provider<PhysicalPtzDirectiveFactory> provider3, Provider<DirectiveSender> provider4, Provider<FeatureServiceV2> provider5, Provider<CamerasMobilyticsService> provider6, Provider<DevicePayload> provider7) {
        return proxyProvidesPhysicalPtzGestureHandler(ptzModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static PhysicalPtzGestureHandler proxyProvidesPhysicalPtzGestureHandler(PtzModule ptzModule, DigitalZoomState digitalZoomState, DisplayMetrics displayMetrics, PhysicalPtzDirectiveFactory physicalPtzDirectiveFactory, DirectiveSender directiveSender, FeatureServiceV2 featureServiceV2, CamerasMobilyticsService camerasMobilyticsService, DevicePayload devicePayload) {
        return (PhysicalPtzGestureHandler) Preconditions.checkNotNull(ptzModule.providesPhysicalPtzGestureHandler(digitalZoomState, displayMetrics, physicalPtzDirectiveFactory, directiveSender, featureServiceV2, camerasMobilyticsService, devicePayload), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhysicalPtzGestureHandler mo10268get() {
        return provideInstance(this.module, this.stateProvider, this.displayMetricsProvider, this.physicalPtzDirectiveFactoryProvider, this.directiveSenderProvider, this.featureServiceV2Provider, this.metricsServiceProvider, this.devicePayloadProvider);
    }
}
