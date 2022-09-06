package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.sharing.comms.AlexaSharingClient;
import com.amazon.alexa.sharing.media.transmitter.FileTransmitter;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesAlexaSharingClientFactory implements Factory<AlexaSharingClient> {
    private final Provider<CommsBridgeService> commsBridgeServiceLazyProvider;
    private final Provider<AlexaCommsCoreRemoteConfigurationService> commsConfigLazyProvider;
    private final Provider<AlexaCommsCoreIdentityService> commsIdentityLazyProvider;
    private final Provider<AlexaCommsCoreMetricsService> commsMetricsLazyProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<FileTransmitter> fileTransmitterProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesAlexaSharingClientFactory(AlexaSharingModule alexaSharingModule, Provider<CommsBridgeService> provider, Provider<AlexaCommsCoreIdentityService> provider2, Provider<AlexaCommsCoreMetricsService> provider3, Provider<AlexaCommsCoreRemoteConfigurationService> provider4, Provider<FeatureServiceV2> provider5, Provider<FileTransmitter> provider6) {
        this.module = alexaSharingModule;
        this.commsBridgeServiceLazyProvider = provider;
        this.commsIdentityLazyProvider = provider2;
        this.commsMetricsLazyProvider = provider3;
        this.commsConfigLazyProvider = provider4;
        this.featureServiceV2LazyProvider = provider5;
        this.fileTransmitterProvider = provider6;
    }

    public static AlexaSharingModule_ProvidesAlexaSharingClientFactory create(AlexaSharingModule alexaSharingModule, Provider<CommsBridgeService> provider, Provider<AlexaCommsCoreIdentityService> provider2, Provider<AlexaCommsCoreMetricsService> provider3, Provider<AlexaCommsCoreRemoteConfigurationService> provider4, Provider<FeatureServiceV2> provider5, Provider<FileTransmitter> provider6) {
        return new AlexaSharingModule_ProvidesAlexaSharingClientFactory(alexaSharingModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static AlexaSharingClient provideInstance(AlexaSharingModule alexaSharingModule, Provider<CommsBridgeService> provider, Provider<AlexaCommsCoreIdentityService> provider2, Provider<AlexaCommsCoreMetricsService> provider3, Provider<AlexaCommsCoreRemoteConfigurationService> provider4, Provider<FeatureServiceV2> provider5, Provider<FileTransmitter> provider6) {
        return proxyProvidesAlexaSharingClient(alexaSharingModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), provider6.mo10268get());
    }

    public static AlexaSharingClient proxyProvidesAlexaSharingClient(AlexaSharingModule alexaSharingModule, Lazy<CommsBridgeService> lazy, Lazy<AlexaCommsCoreIdentityService> lazy2, Lazy<AlexaCommsCoreMetricsService> lazy3, Lazy<AlexaCommsCoreRemoteConfigurationService> lazy4, Lazy<FeatureServiceV2> lazy5, FileTransmitter fileTransmitter) {
        return (AlexaSharingClient) Preconditions.checkNotNull(alexaSharingModule.providesAlexaSharingClient(lazy, lazy2, lazy3, lazy4, lazy5, fileTransmitter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaSharingClient mo10268get() {
        return provideInstance(this.module, this.commsBridgeServiceLazyProvider, this.commsIdentityLazyProvider, this.commsMetricsLazyProvider, this.commsConfigLazyProvider, this.featureServiceV2LazyProvider, this.fileTransmitterProvider);
    }
}
