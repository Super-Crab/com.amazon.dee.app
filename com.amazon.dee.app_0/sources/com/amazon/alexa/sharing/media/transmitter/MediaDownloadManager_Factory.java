package com.amazon.alexa.sharing.media.transmitter;

import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class MediaDownloadManager_Factory implements Factory<MediaDownloadManager> {
    private final Provider<CommsBridgeService> commsBridgeServiceProvider;
    private final Provider<FileTransmitter> fileTransmitterProvider;
    private final Provider<CommsMetricsEmitter> metricsEmitterProvider;

    public MediaDownloadManager_Factory(Provider<FileTransmitter> provider, Provider<CommsBridgeService> provider2, Provider<CommsMetricsEmitter> provider3) {
        this.fileTransmitterProvider = provider;
        this.commsBridgeServiceProvider = provider2;
        this.metricsEmitterProvider = provider3;
    }

    public static MediaDownloadManager_Factory create(Provider<FileTransmitter> provider, Provider<CommsBridgeService> provider2, Provider<CommsMetricsEmitter> provider3) {
        return new MediaDownloadManager_Factory(provider, provider2, provider3);
    }

    public static MediaDownloadManager newMediaDownloadManager(FileTransmitter fileTransmitter, CommsBridgeService commsBridgeService, CommsMetricsEmitter commsMetricsEmitter) {
        return new MediaDownloadManager(fileTransmitter, commsBridgeService, commsMetricsEmitter);
    }

    public static MediaDownloadManager provideInstance(Provider<FileTransmitter> provider, Provider<CommsBridgeService> provider2, Provider<CommsMetricsEmitter> provider3) {
        return new MediaDownloadManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MediaDownloadManager mo10268get() {
        return provideInstance(this.fileTransmitterProvider, this.commsBridgeServiceProvider, this.metricsEmitterProvider);
    }
}
