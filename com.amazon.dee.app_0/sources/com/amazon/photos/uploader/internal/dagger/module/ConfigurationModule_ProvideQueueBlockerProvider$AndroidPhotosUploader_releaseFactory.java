package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideQueueBlockerProvider$AndroidPhotosUploader_releaseFactory implements Factory<QueueBlockerLiveDataProvider> {
    private final ConfigurationModule module;
    private final Provider<UploadSummaryObservable> uploadSummaryObservableProvider;

    public ConfigurationModule_ProvideQueueBlockerProvider$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadSummaryObservable> provider) {
        this.module = configurationModule;
        this.uploadSummaryObservableProvider = provider;
    }

    public static ConfigurationModule_ProvideQueueBlockerProvider$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadSummaryObservable> provider) {
        return new ConfigurationModule_ProvideQueueBlockerProvider$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static QueueBlockerLiveDataProvider provideQueueBlockerProvider$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadSummaryObservable uploadSummaryObservable) {
        return (QueueBlockerLiveDataProvider) Preconditions.checkNotNull(configurationModule.provideQueueBlockerProvider$AndroidPhotosUploader_release(uploadSummaryObservable), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public QueueBlockerLiveDataProvider mo10268get() {
        return provideQueueBlockerProvider$AndroidPhotosUploader_release(this.module, this.uploadSummaryObservableProvider.mo10268get());
    }
}
