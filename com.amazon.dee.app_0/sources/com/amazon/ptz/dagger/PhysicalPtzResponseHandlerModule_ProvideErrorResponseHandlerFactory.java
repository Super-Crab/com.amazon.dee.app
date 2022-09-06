package com.amazon.ptz.dagger;

import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.communication.responses.handlers.ErrorResponseHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes13.dex */
public final class PhysicalPtzResponseHandlerModule_ProvideErrorResponseHandlerFactory implements Factory<ErrorResponseHandler> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final PhysicalPtzResponseHandlerModule module;
    private final Provider<RcSerializer> rcSerializerProvider;

    public PhysicalPtzResponseHandlerModule_ProvideErrorResponseHandlerFactory(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<EventBus> provider, Provider<RcSerializer> provider2, Provider<MetricRecorder> provider3) {
        this.module = physicalPtzResponseHandlerModule;
        this.eventBusProvider = provider;
        this.rcSerializerProvider = provider2;
        this.metricRecorderProvider = provider3;
    }

    public static PhysicalPtzResponseHandlerModule_ProvideErrorResponseHandlerFactory create(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<EventBus> provider, Provider<RcSerializer> provider2, Provider<MetricRecorder> provider3) {
        return new PhysicalPtzResponseHandlerModule_ProvideErrorResponseHandlerFactory(physicalPtzResponseHandlerModule, provider, provider2, provider3);
    }

    public static ErrorResponseHandler provideInstance(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<EventBus> provider, Provider<RcSerializer> provider2, Provider<MetricRecorder> provider3) {
        return proxyProvideErrorResponseHandler(physicalPtzResponseHandlerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static ErrorResponseHandler proxyProvideErrorResponseHandler(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, EventBus eventBus, RcSerializer rcSerializer, MetricRecorder metricRecorder) {
        return (ErrorResponseHandler) Preconditions.checkNotNull(physicalPtzResponseHandlerModule.provideErrorResponseHandler(eventBus, rcSerializer, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ErrorResponseHandler mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.rcSerializerProvider, this.metricRecorderProvider);
    }
}
