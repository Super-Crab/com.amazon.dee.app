package com.amazon.alexa.sharing.media.transmitter;

import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class MediaUploadManager_Factory implements Factory<MediaUploadManager> {
    private final Provider<FileTransmitter> fileTransmitterProvider;
    private final Provider<CommsMetricsEmitter> metricsEmitterProvider;

    public MediaUploadManager_Factory(Provider<FileTransmitter> provider, Provider<CommsMetricsEmitter> provider2) {
        this.fileTransmitterProvider = provider;
        this.metricsEmitterProvider = provider2;
    }

    public static MediaUploadManager_Factory create(Provider<FileTransmitter> provider, Provider<CommsMetricsEmitter> provider2) {
        return new MediaUploadManager_Factory(provider, provider2);
    }

    public static MediaUploadManager newMediaUploadManager(FileTransmitter fileTransmitter, CommsMetricsEmitter commsMetricsEmitter) {
        return new MediaUploadManager(fileTransmitter, commsMetricsEmitter);
    }

    public static MediaUploadManager provideInstance(Provider<FileTransmitter> provider, Provider<CommsMetricsEmitter> provider2) {
        return new MediaUploadManager(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MediaUploadManager mo10268get() {
        return provideInstance(this.fileTransmitterProvider, this.metricsEmitterProvider);
    }
}
