package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFailureUpdateHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowMetricsReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowControllerFactory implements Factory<ZeroTouchWorkflowController> {
    private final Provider<DeferredDiscoveryHandler> deferredDiscoveryHandlerProvider;
    private final Provider<DeviceDiscoveryStream> deviceDiscoveryStreamProvider;
    private final Provider<DiscoverySettings> discoverySettingsProvider;
    private final Provider<FFSArcusSettings> ffsArcusSettingsProvider;
    private final Provider<FFSProvisioningServiceMetricsRecorder> ffsProvisioningServiceMetricsRecorderProvider;
    private final ZeroTouchProvisioningModule module;
    private final Provider<ZeroTouchWorkflowFailureUpdateHandler> zeroTouchWorkflowFailureUpdateHandlerProvider;
    private final Provider<ZeroTouchWorkflowMetricsReporter> zeroTouchWorkflowMetricsReporterProvider;
    private final Provider<ZeroTouchWorkflowRouter> zeroTouchWorkflowRouterProvider;

    public ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowControllerFactory(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<ZeroTouchWorkflowRouter> provider, Provider<DeviceDiscoveryStream> provider2, Provider<ZeroTouchWorkflowMetricsReporter> provider3, Provider<FFSProvisioningServiceMetricsRecorder> provider4, Provider<DiscoverySettings> provider5, Provider<ZeroTouchWorkflowFailureUpdateHandler> provider6, Provider<DeferredDiscoveryHandler> provider7, Provider<FFSArcusSettings> provider8) {
        this.module = zeroTouchProvisioningModule;
        this.zeroTouchWorkflowRouterProvider = provider;
        this.deviceDiscoveryStreamProvider = provider2;
        this.zeroTouchWorkflowMetricsReporterProvider = provider3;
        this.ffsProvisioningServiceMetricsRecorderProvider = provider4;
        this.discoverySettingsProvider = provider5;
        this.zeroTouchWorkflowFailureUpdateHandlerProvider = provider6;
        this.deferredDiscoveryHandlerProvider = provider7;
        this.ffsArcusSettingsProvider = provider8;
    }

    public static ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowControllerFactory create(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<ZeroTouchWorkflowRouter> provider, Provider<DeviceDiscoveryStream> provider2, Provider<ZeroTouchWorkflowMetricsReporter> provider3, Provider<FFSProvisioningServiceMetricsRecorder> provider4, Provider<DiscoverySettings> provider5, Provider<ZeroTouchWorkflowFailureUpdateHandler> provider6, Provider<DeferredDiscoveryHandler> provider7, Provider<FFSArcusSettings> provider8) {
        return new ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowControllerFactory(zeroTouchProvisioningModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static ZeroTouchWorkflowController provideInstance(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<ZeroTouchWorkflowRouter> provider, Provider<DeviceDiscoveryStream> provider2, Provider<ZeroTouchWorkflowMetricsReporter> provider3, Provider<FFSProvisioningServiceMetricsRecorder> provider4, Provider<DiscoverySettings> provider5, Provider<ZeroTouchWorkflowFailureUpdateHandler> provider6, Provider<DeferredDiscoveryHandler> provider7, Provider<FFSArcusSettings> provider8) {
        return proxyProvidesZeroTouchWorkflowController(zeroTouchProvisioningModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    public static ZeroTouchWorkflowController proxyProvidesZeroTouchWorkflowController(ZeroTouchProvisioningModule zeroTouchProvisioningModule, ZeroTouchWorkflowRouter zeroTouchWorkflowRouter, DeviceDiscoveryStream deviceDiscoveryStream, ZeroTouchWorkflowMetricsReporter zeroTouchWorkflowMetricsReporter, FFSProvisioningServiceMetricsRecorder fFSProvisioningServiceMetricsRecorder, DiscoverySettings discoverySettings, ZeroTouchWorkflowFailureUpdateHandler zeroTouchWorkflowFailureUpdateHandler, DeferredDiscoveryHandler deferredDiscoveryHandler, FFSArcusSettings fFSArcusSettings) {
        return (ZeroTouchWorkflowController) Preconditions.checkNotNull(zeroTouchProvisioningModule.providesZeroTouchWorkflowController(zeroTouchWorkflowRouter, deviceDiscoveryStream, zeroTouchWorkflowMetricsReporter, fFSProvisioningServiceMetricsRecorder, discoverySettings, zeroTouchWorkflowFailureUpdateHandler, deferredDiscoveryHandler, fFSArcusSettings), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ZeroTouchWorkflowController mo10268get() {
        return provideInstance(this.module, this.zeroTouchWorkflowRouterProvider, this.deviceDiscoveryStreamProvider, this.zeroTouchWorkflowMetricsReporterProvider, this.ffsProvisioningServiceMetricsRecorderProvider, this.discoverySettingsProvider, this.zeroTouchWorkflowFailureUpdateHandlerProvider, this.deferredDiscoveryHandlerProvider, this.ffsArcusSettingsProvider);
    }
}
