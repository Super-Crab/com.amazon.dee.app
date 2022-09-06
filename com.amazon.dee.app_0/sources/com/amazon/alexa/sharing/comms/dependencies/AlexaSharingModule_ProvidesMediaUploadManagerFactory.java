package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.sharing.media.transmitter.FileTransmitter;
import com.amazon.alexa.sharing.media.transmitter.MediaUploadManager;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesMediaUploadManagerFactory implements Factory<MediaUploadManager> {
    private final Provider<FileTransmitter> fileTransmitterProvider;
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesMediaUploadManagerFactory(AlexaSharingModule alexaSharingModule, Provider<FileTransmitter> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        this.module = alexaSharingModule;
        this.fileTransmitterProvider = provider;
        this.metricsServiceLazyProvider = provider2;
    }

    public static AlexaSharingModule_ProvidesMediaUploadManagerFactory create(AlexaSharingModule alexaSharingModule, Provider<FileTransmitter> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        return new AlexaSharingModule_ProvidesMediaUploadManagerFactory(alexaSharingModule, provider, provider2);
    }

    public static MediaUploadManager provideInstance(AlexaSharingModule alexaSharingModule, Provider<FileTransmitter> provider, Provider<AlexaCommsCoreMetricsService> provider2) {
        return proxyProvidesMediaUploadManager(alexaSharingModule, provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    public static MediaUploadManager proxyProvidesMediaUploadManager(AlexaSharingModule alexaSharingModule, FileTransmitter fileTransmitter, Lazy<AlexaCommsCoreMetricsService> lazy) {
        return (MediaUploadManager) Preconditions.checkNotNull(alexaSharingModule.providesMediaUploadManager(fileTransmitter, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MediaUploadManager mo10268get() {
        return provideInstance(this.module, this.fileTransmitterProvider, this.metricsServiceLazyProvider);
    }
}
