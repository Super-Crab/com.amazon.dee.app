package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.livedata.RunningRequestProvider;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideRunningRequestProvider$AndroidPhotosUploader_releaseFactory implements Factory<RunningRequestProvider> {
    private final ConfigurationModule module;
    private final Provider<UploadRequestObservable> uploadRequestObservableProvider;

    public ConfigurationModule_ProvideRunningRequestProvider$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadRequestObservable> provider) {
        this.module = configurationModule;
        this.uploadRequestObservableProvider = provider;
    }

    public static ConfigurationModule_ProvideRunningRequestProvider$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadRequestObservable> provider) {
        return new ConfigurationModule_ProvideRunningRequestProvider$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static RunningRequestProvider provideRunningRequestProvider$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadRequestObservable uploadRequestObservable) {
        return (RunningRequestProvider) Preconditions.checkNotNull(configurationModule.provideRunningRequestProvider$AndroidPhotosUploader_release(uploadRequestObservable), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RunningRequestProvider mo10268get() {
        return provideRunningRequestProvider$AndroidPhotosUploader_release(this.module, this.uploadRequestObservableProvider.mo10268get());
    }
}
