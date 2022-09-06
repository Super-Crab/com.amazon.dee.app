package com.amazon.ptz.dagger;

import android.util.DisplayMetrics;
import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.PhysicalPtzGestureHandler;
import com.amazon.ptz.physical.communication.PhysicalPtzCommandCache;
import com.amazon.ptz.physical.communication.PhysicalPtzDirectiveSender;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureHandlerModule_ProvidePhysicalPtzGestureHandlerFactory implements Factory<PhysicalPtzGestureHandler> {
    private final Provider<List<CameraPtzInstance>> cameraPtzInstancesProvider;
    private final Provider<DisplayMetrics> displayMetricsProvider;
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final GestureHandlerModule module;
    private final Provider<PhysicalPtzCommandCache> physicalPtzCommandCacheProvider;
    private final Provider<PhysicalPtzDirectiveSender> physicalPtzDirectiveSenderProvider;
    private final Provider<RcSerializer> rcSerializerProvider;
    private final Provider<DigitalZoomState> zoomStateProvider;

    public GestureHandlerModule_ProvidePhysicalPtzGestureHandlerFactory(GestureHandlerModule gestureHandlerModule, Provider<PhysicalPtzDirectiveSender> provider, Provider<List<CameraPtzInstance>> provider2, Provider<DigitalZoomState> provider3, Provider<PhysicalPtzCommandCache> provider4, Provider<DisplayMetrics> provider5, Provider<RcSerializer> provider6, Provider<MetricRecorder> provider7) {
        this.module = gestureHandlerModule;
        this.physicalPtzDirectiveSenderProvider = provider;
        this.cameraPtzInstancesProvider = provider2;
        this.zoomStateProvider = provider3;
        this.physicalPtzCommandCacheProvider = provider4;
        this.displayMetricsProvider = provider5;
        this.rcSerializerProvider = provider6;
        this.metricRecorderProvider = provider7;
    }

    public static GestureHandlerModule_ProvidePhysicalPtzGestureHandlerFactory create(GestureHandlerModule gestureHandlerModule, Provider<PhysicalPtzDirectiveSender> provider, Provider<List<CameraPtzInstance>> provider2, Provider<DigitalZoomState> provider3, Provider<PhysicalPtzCommandCache> provider4, Provider<DisplayMetrics> provider5, Provider<RcSerializer> provider6, Provider<MetricRecorder> provider7) {
        return new GestureHandlerModule_ProvidePhysicalPtzGestureHandlerFactory(gestureHandlerModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static PhysicalPtzGestureHandler provideInstance(GestureHandlerModule gestureHandlerModule, Provider<PhysicalPtzDirectiveSender> provider, Provider<List<CameraPtzInstance>> provider2, Provider<DigitalZoomState> provider3, Provider<PhysicalPtzCommandCache> provider4, Provider<DisplayMetrics> provider5, Provider<RcSerializer> provider6, Provider<MetricRecorder> provider7) {
        return proxyProvidePhysicalPtzGestureHandler(gestureHandlerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static PhysicalPtzGestureHandler proxyProvidePhysicalPtzGestureHandler(GestureHandlerModule gestureHandlerModule, PhysicalPtzDirectiveSender physicalPtzDirectiveSender, List<CameraPtzInstance> list, DigitalZoomState digitalZoomState, PhysicalPtzCommandCache physicalPtzCommandCache, DisplayMetrics displayMetrics, RcSerializer rcSerializer, MetricRecorder metricRecorder) {
        return (PhysicalPtzGestureHandler) Preconditions.checkNotNull(gestureHandlerModule.providePhysicalPtzGestureHandler(physicalPtzDirectiveSender, list, digitalZoomState, physicalPtzCommandCache, displayMetrics, rcSerializer, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhysicalPtzGestureHandler mo10268get() {
        return provideInstance(this.module, this.physicalPtzDirectiveSenderProvider, this.cameraPtzInstancesProvider, this.zoomStateProvider, this.physicalPtzCommandCacheProvider, this.displayMetricsProvider, this.rcSerializerProvider, this.metricRecorderProvider);
    }
}
