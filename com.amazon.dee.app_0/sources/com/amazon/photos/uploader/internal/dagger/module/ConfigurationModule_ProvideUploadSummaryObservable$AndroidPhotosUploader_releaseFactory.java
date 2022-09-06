package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.UploadSummaryNotifier;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideUploadSummaryObservable$AndroidPhotosUploader_releaseFactory implements Factory<UploadSummaryObservable> {
    private final ConfigurationModule module;
    private final Provider<UploadSummaryNotifier> uploadSummaryNotifierProvider;

    public ConfigurationModule_ProvideUploadSummaryObservable$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadSummaryNotifier> provider) {
        this.module = configurationModule;
        this.uploadSummaryNotifierProvider = provider;
    }

    public static ConfigurationModule_ProvideUploadSummaryObservable$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadSummaryNotifier> provider) {
        return new ConfigurationModule_ProvideUploadSummaryObservable$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static UploadSummaryObservable provideUploadSummaryObservable$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadSummaryNotifier uploadSummaryNotifier) {
        return (UploadSummaryObservable) Preconditions.checkNotNull(configurationModule.provideUploadSummaryObservable$AndroidPhotosUploader_release(uploadSummaryNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadSummaryObservable mo10268get() {
        return provideUploadSummaryObservable$AndroidPhotosUploader_release(this.module, this.uploadSummaryNotifierProvider.mo10268get());
    }
}
