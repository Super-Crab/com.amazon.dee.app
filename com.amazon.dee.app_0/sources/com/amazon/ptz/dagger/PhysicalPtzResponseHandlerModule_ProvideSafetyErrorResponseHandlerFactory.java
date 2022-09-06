package com.amazon.ptz.dagger;

import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.communication.responses.handlers.SafetyErrorResponseHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes13.dex */
public final class PhysicalPtzResponseHandlerModule_ProvideSafetyErrorResponseHandlerFactory implements Factory<SafetyErrorResponseHandler> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final PhysicalPtzResponseHandlerModule module;
    private final Provider<RcSerializer> rcSerializerProvider;

    public PhysicalPtzResponseHandlerModule_ProvideSafetyErrorResponseHandlerFactory(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<EventBus> provider, Provider<RcSerializer> provider2, Provider<MetricRecorder> provider3) {
        this.module = physicalPtzResponseHandlerModule;
        this.eventBusProvider = provider;
        this.rcSerializerProvider = provider2;
        this.metricRecorderProvider = provider3;
    }

    public static PhysicalPtzResponseHandlerModule_ProvideSafetyErrorResponseHandlerFactory create(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<EventBus> provider, Provider<RcSerializer> provider2, Provider<MetricRecorder> provider3) {
        return new PhysicalPtzResponseHandlerModule_ProvideSafetyErrorResponseHandlerFactory(physicalPtzResponseHandlerModule, provider, provider2, provider3);
    }

    public static SafetyErrorResponseHandler provideInstance(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<EventBus> provider, Provider<RcSerializer> provider2, Provider<MetricRecorder> provider3) {
        return proxyProvideSafetyErrorResponseHandler(physicalPtzResponseHandlerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static SafetyErrorResponseHandler proxyProvideSafetyErrorResponseHandler(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, EventBus eventBus, RcSerializer rcSerializer, MetricRecorder metricRecorder) {
        return (SafetyErrorResponseHandler) Preconditions.checkNotNull(physicalPtzResponseHandlerModule.provideSafetyErrorResponseHandler(eventBus, rcSerializer, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SafetyErrorResponseHandler mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.rcSerializerProvider, this.metricRecorderProvider);
    }
}
