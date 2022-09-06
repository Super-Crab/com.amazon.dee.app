package com.amazon.ptz.dagger;

import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.communication.responses.handlers.ChangeReportHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class PhysicalPtzResponseHandlerModule_ProvideChangeReportHandlerFactory implements Factory<ChangeReportHandler> {
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final PhysicalPtzResponseHandlerModule module;
    private final Provider<RcSerializer> rcSerializerProvider;

    public PhysicalPtzResponseHandlerModule_ProvideChangeReportHandlerFactory(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<RcSerializer> provider, Provider<MetricRecorder> provider2) {
        this.module = physicalPtzResponseHandlerModule;
        this.rcSerializerProvider = provider;
        this.metricRecorderProvider = provider2;
    }

    public static PhysicalPtzResponseHandlerModule_ProvideChangeReportHandlerFactory create(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<RcSerializer> provider, Provider<MetricRecorder> provider2) {
        return new PhysicalPtzResponseHandlerModule_ProvideChangeReportHandlerFactory(physicalPtzResponseHandlerModule, provider, provider2);
    }

    public static ChangeReportHandler provideInstance(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<RcSerializer> provider, Provider<MetricRecorder> provider2) {
        return proxyProvideChangeReportHandler(physicalPtzResponseHandlerModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ChangeReportHandler proxyProvideChangeReportHandler(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, RcSerializer rcSerializer, MetricRecorder metricRecorder) {
        return (ChangeReportHandler) Preconditions.checkNotNull(physicalPtzResponseHandlerModule.provideChangeReportHandler(rcSerializer, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ChangeReportHandler mo10268get() {
        return provideInstance(this.module, this.rcSerializerProvider, this.metricRecorderProvider);
    }
}
