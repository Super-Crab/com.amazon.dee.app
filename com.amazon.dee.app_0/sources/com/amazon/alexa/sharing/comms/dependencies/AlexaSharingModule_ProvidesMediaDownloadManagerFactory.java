package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.sharing.media.transmitter.FileTransmitter;
import com.amazon.alexa.sharing.media.transmitter.MediaDownloadManager;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesMediaDownloadManagerFactory implements Factory<MediaDownloadManager> {
    private final Provider<FileTransmitter> fileTransmitterProvider;
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesMediaDownloadManagerFactory(AlexaSharingModule alexaSharingModule, Provider<FileTransmitter> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        this.module = alexaSharingModule;
        this.fileTransmitterProvider = provider;
        this.metricsServiceLazyProvider = provider2;
    }

    public static AlexaSharingModule_ProvidesMediaDownloadManagerFactory create(AlexaSharingModule alexaSharingModule, Provider<FileTransmitter> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        return new AlexaSharingModule_ProvidesMediaDownloadManagerFactory(alexaSharingModule, provider, provider2);
    }

    public static MediaDownloadManager provideInstance(AlexaSharingModule alexaSharingModule, Provider<FileTransmitter> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        return proxyProvidesMediaDownloadManager(alexaSharingModule, provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    public static MediaDownloadManager proxyProvidesMediaDownloadManager(AlexaSharingModule alexaSharingModule, FileTransmitter fileTransmitter, Lazy<AlexaCommsCoreMetricsService> lazy) {
        return (MediaDownloadManager) Preconditions.checkNotNull(alexaSharingModule.providesMediaDownloadManager(fileTransmitter, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MediaDownloadManager mo10268get() {
        return provideInstance(this.module, this.fileTransmitterProvider, this.metricsServiceLazyProvider);
    }
}
