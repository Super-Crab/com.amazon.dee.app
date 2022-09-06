package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowMetricsReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowMetricsReporterFactory implements Factory<ZeroTouchWorkflowMetricsReporter> {
    private final Provider<FFSProvisioningServiceMetricsRecorder> ffsProvisioningServiceMetricsRecorderProvider;
    private final ZeroTouchProvisioningModule module;

    public ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowMetricsReporterFactory(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<FFSProvisioningServiceMetricsRecorder> provider) {
        this.module = zeroTouchProvisioningModule;
        this.ffsProvisioningServiceMetricsRecorderProvider = provider;
    }

    public static ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowMetricsReporterFactory create(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<FFSProvisioningServiceMetricsRecorder> provider) {
        return new ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowMetricsReporterFactory(zeroTouchProvisioningModule, provider);
    }

    public static ZeroTouchWorkflowMetricsReporter provideInstance(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<FFSProvisioningServiceMetricsRecorder> provider) {
        return proxyProvidesZeroTouchWorkflowMetricsReporter(zeroTouchProvisioningModule, provider.mo10268get());
    }

    public static ZeroTouchWorkflowMetricsReporter proxyProvidesZeroTouchWorkflowMetricsReporter(ZeroTouchProvisioningModule zeroTouchProvisioningModule, FFSProvisioningServiceMetricsRecorder fFSProvisioningServiceMetricsRecorder) {
        return (ZeroTouchWorkflowMetricsReporter) Preconditions.checkNotNull(zeroTouchProvisioningModule.providesZeroTouchWorkflowMetricsReporter(fFSProvisioningServiceMetricsRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ZeroTouchWorkflowMetricsReporter mo10268get() {
        return provideInstance(this.module, this.ffsProvisioningServiceMetricsRecorderProvider);
    }
}
