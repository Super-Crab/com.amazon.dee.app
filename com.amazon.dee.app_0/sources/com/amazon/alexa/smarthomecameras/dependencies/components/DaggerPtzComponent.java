package com.amazon.alexa.smarthomecameras.dependencies.components;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.View;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraMetricsModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraMetricsModule_ProvideMobilyticsFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraMetricsModule_ProvideMobilyticsServiceFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule_ProvidesContextFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.FeatureServiceV2Module_ProvideFeatureServiceV2Factory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.GsonModule_ProvidesGsonFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvideDisplayMetricsFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvideGestureRouterFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesDevicePayloadFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesDigitalPtzGestureHandlerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesDigitalZoomStateFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesDirectiveSenderFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesGestureDetectorFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesLiveViewPtzGestureListenerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesPhysicalPtzDirectiveFactoryFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesPhysicalPtzGestureHandlerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesPtzListenerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesPtzMetricsListenerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesPtzMetricsRecorderFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesPtzViewFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesScaleGestureDetectorFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesScaleGestureListenerFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule_ProvidesSimplePtzGestureListenerFactory;
import com.amazon.alexa.smarthomecameras.directives.DirectiveSender;
import com.amazon.alexa.smarthomecameras.handlers.LiveViewPtzListener;
import com.amazon.alexa.smarthomecameras.metrics.PtzMetricsListener;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.ptz.PhysicalPtzGestureHandler;
import com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener;
import com.amazon.alexa.smarthomecameras.ptz.directives.PhysicalPtzDirectiveFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.gestures.listeners.ScalePtzGestureListener;
import com.amazon.ptz.metrics.MetricRecorder;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class DaggerPtzComponent implements PtzComponent {
    private Provider<DisplayMetrics> provideDisplayMetricsProvider;
    private Provider<FeatureServiceV2> provideFeatureServiceV2Provider;
    private Provider<GestureHandler> provideGestureRouterProvider;
    private Provider<CamerasMobilyticsService> provideMobilyticsServiceProvider;
    private Provider<Context> providesContextProvider;
    private Provider<DevicePayload> providesDevicePayloadProvider;
    private Provider<DigitalPtzGestureHandler> providesDigitalPtzGestureHandlerProvider;
    private Provider<DigitalZoomState> providesDigitalZoomStateProvider;
    private Provider<DirectiveSender> providesDirectiveSenderProvider;
    private Provider<GestureDetector> providesGestureDetectorProvider;
    private Provider<Gson> providesGsonProvider;
    private Provider<PtzGestureListener> providesLiveViewPtzGestureListenerProvider;
    private Provider<PhysicalPtzDirectiveFactory> providesPhysicalPtzDirectiveFactoryProvider;
    private Provider<PhysicalPtzGestureHandler> providesPhysicalPtzGestureHandlerProvider;
    private Provider<LiveViewPtzListener> providesPtzListenerProvider;
    private Provider<PtzMetricsListener> providesPtzMetricsListenerProvider;
    private Provider<MetricRecorder> providesPtzMetricsRecorderProvider;
    private Provider<View> providesPtzViewProvider;
    private Provider<OldScaleGestureDetector> providesScaleGestureDetectorProvider;
    private Provider<ScalePtzGestureListener> providesScaleGestureListenerProvider;
    private Provider<GestureDetector.SimpleOnGestureListener> providesSimplePtzGestureListenerProvider;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private ContextModule contextModule;
        private PtzModule ptzModule;

        public PtzComponent build() {
            Preconditions.checkBuilderRequirement(this.ptzModule, PtzModule.class);
            Preconditions.checkBuilderRequirement(this.contextModule, ContextModule.class);
            return new DaggerPtzComponent(this);
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

        public Builder ptzModule(PtzModule ptzModule) {
            this.ptzModule = (PtzModule) Preconditions.checkNotNull(ptzModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesPtzViewProvider = DoubleCheck.provider(PtzModule_ProvidesPtzViewFactory.create(builder.ptzModule));
        this.providesDigitalZoomStateProvider = DoubleCheck.provider(PtzModule_ProvidesDigitalZoomStateFactory.create(builder.ptzModule, this.providesPtzViewProvider));
        this.provideMobilyticsServiceProvider = DoubleCheck.provider(CameraMetricsModule_ProvideMobilyticsServiceFactory.create(CameraMetricsModule_ProvideMobilyticsFactory.create()));
        this.providesPtzMetricsRecorderProvider = DoubleCheck.provider(PtzModule_ProvidesPtzMetricsRecorderFactory.create(builder.ptzModule, this.provideMobilyticsServiceProvider));
        this.providesDigitalPtzGestureHandlerProvider = DoubleCheck.provider(PtzModule_ProvidesDigitalPtzGestureHandlerFactory.create(builder.ptzModule, this.providesPtzViewProvider, this.providesDigitalZoomStateProvider, this.providesPtzMetricsRecorderProvider));
        this.providesContextProvider = DoubleCheck.provider(ContextModule_ProvidesContextFactory.create(builder.contextModule));
        this.provideDisplayMetricsProvider = DoubleCheck.provider(PtzModule_ProvideDisplayMetricsFactory.create(builder.ptzModule, this.providesContextProvider));
        this.providesPhysicalPtzDirectiveFactoryProvider = DoubleCheck.provider(PtzModule_ProvidesPhysicalPtzDirectiveFactoryFactory.create(builder.ptzModule));
        this.providesGsonProvider = DoubleCheck.provider(GsonModule_ProvidesGsonFactory.create());
        this.providesDirectiveSenderProvider = DoubleCheck.provider(PtzModule_ProvidesDirectiveSenderFactory.create(builder.ptzModule, this.providesGsonProvider));
        this.provideFeatureServiceV2Provider = DoubleCheck.provider(FeatureServiceV2Module_ProvideFeatureServiceV2Factory.create());
        this.providesDevicePayloadProvider = DoubleCheck.provider(PtzModule_ProvidesDevicePayloadFactory.create(builder.ptzModule));
        this.providesPhysicalPtzGestureHandlerProvider = DoubleCheck.provider(PtzModule_ProvidesPhysicalPtzGestureHandlerFactory.create(builder.ptzModule, this.providesDigitalZoomStateProvider, this.provideDisplayMetricsProvider, this.providesPhysicalPtzDirectiveFactoryProvider, this.providesDirectiveSenderProvider, this.provideFeatureServiceV2Provider, this.provideMobilyticsServiceProvider, this.providesDevicePayloadProvider));
        this.providesPtzMetricsListenerProvider = DoubleCheck.provider(PtzModule_ProvidesPtzMetricsListenerFactory.create(builder.ptzModule, this.provideMobilyticsServiceProvider));
        this.providesLiveViewPtzGestureListenerProvider = DoubleCheck.provider(PtzModule_ProvidesLiveViewPtzGestureListenerFactory.create(builder.ptzModule, this.providesPtzMetricsListenerProvider, this.providesDevicePayloadProvider));
        this.provideGestureRouterProvider = DoubleCheck.provider(PtzModule_ProvideGestureRouterFactory.create(builder.ptzModule, this.providesDigitalPtzGestureHandlerProvider, this.providesPhysicalPtzGestureHandlerProvider, this.providesLiveViewPtzGestureListenerProvider));
        this.providesSimplePtzGestureListenerProvider = DoubleCheck.provider(PtzModule_ProvidesSimplePtzGestureListenerFactory.create(builder.ptzModule, this.provideGestureRouterProvider, this.providesPtzMetricsRecorderProvider));
        this.providesGestureDetectorProvider = DoubleCheck.provider(PtzModule_ProvidesGestureDetectorFactory.create(builder.ptzModule, this.providesContextProvider, this.providesSimplePtzGestureListenerProvider));
        this.providesScaleGestureListenerProvider = DoubleCheck.provider(PtzModule_ProvidesScaleGestureListenerFactory.create(builder.ptzModule, this.provideGestureRouterProvider, this.providesPtzMetricsRecorderProvider));
        this.providesScaleGestureDetectorProvider = DoubleCheck.provider(PtzModule_ProvidesScaleGestureDetectorFactory.create(builder.ptzModule, this.providesContextProvider, this.providesScaleGestureListenerProvider));
        this.providesPtzListenerProvider = DoubleCheck.provider(PtzModule_ProvidesPtzListenerFactory.create(builder.ptzModule, this.provideGestureRouterProvider, this.providesGestureDetectorProvider, this.providesScaleGestureDetectorProvider, this.providesContextProvider, this.providesLiveViewPtzGestureListenerProvider));
    }

    @Override // com.amazon.alexa.smarthomecameras.dependencies.components.PtzComponent
    public LiveViewPtzListener getPtzListener() {
        return this.providesPtzListenerProvider.mo10268get();
    }

    private DaggerPtzComponent(Builder builder) {
        initialize(builder);
    }
}
