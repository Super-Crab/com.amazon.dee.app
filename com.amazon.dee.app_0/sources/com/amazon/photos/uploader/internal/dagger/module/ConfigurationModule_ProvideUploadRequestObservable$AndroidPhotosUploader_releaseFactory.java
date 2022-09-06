package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideUploadRequestObservable$AndroidPhotosUploader_releaseFactory implements Factory<UploadRequestObservable> {
    private final ConfigurationModule module;
    private final Provider<UploadRequestUpdatesNotifier> uploadRequestUpdatesNotifierProvider;

    public ConfigurationModule_ProvideUploadRequestObservable$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadRequestUpdatesNotifier> provider) {
        this.module = configurationModule;
        this.uploadRequestUpdatesNotifierProvider = provider;
    }

    public static ConfigurationModule_ProvideUploadRequestObservable$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadRequestUpdatesNotifier> provider) {
        return new ConfigurationModule_ProvideUploadRequestObservable$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static UploadRequestObservable provideUploadRequestObservable$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier) {
        return (UploadRequestObservable) Preconditions.checkNotNull(configurationModule.provideUploadRequestObservable$AndroidPhotosUploader_release(uploadRequestUpdatesNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadRequestObservable mo10268get() {
        return provideUploadRequestObservable$AndroidPhotosUploader_release(this.module, this.uploadRequestUpdatesNotifierProvider.mo10268get());
    }
}
