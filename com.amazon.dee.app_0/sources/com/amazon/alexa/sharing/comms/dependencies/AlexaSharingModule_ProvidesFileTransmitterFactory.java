package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.sharing.media.transmitter.FileTransmitter;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesFileTransmitterFactory implements Factory<FileTransmitter> {
    private final Provider<AmazonCloudDriveExtendedClient> cdsClientProvider;
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesFileTransmitterFactory(AlexaSharingModule alexaSharingModule, Provider<AmazonCloudDriveExtendedClient> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        this.module = alexaSharingModule;
        this.cdsClientProvider = provider;
        this.metricsServiceLazyProvider = provider2;
    }

    public static AlexaSharingModule_ProvidesFileTransmitterFactory create(AlexaSharingModule alexaSharingModule, Provider<AmazonCloudDriveExtendedClient> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        return new AlexaSharingModule_ProvidesFileTransmitterFactory(alexaSharingModule, provider, provider2);
    }

    public static FileTransmitter provideInstance(AlexaSharingModule alexaSharingModule, Provider<AmazonCloudDriveExtendedClient> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        return proxyProvidesFileTransmitter(alexaSharingModule, provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    public static FileTransmitter proxyProvidesFileTransmitter(AlexaSharingModule alexaSharingModule, AmazonCloudDriveExtendedClient amazonCloudDriveExtendedClient, Lazy<AlexaCommsCoreMetricsService> lazy) {
        return (FileTransmitter) Preconditions.checkNotNull(alexaSharingModule.providesFileTransmitter(amazonCloudDriveExtendedClient, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FileTransmitter mo10268get() {
        return provideInstance(this.module, this.cdsClientProvider, this.metricsServiceLazyProvider);
    }
}
