package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.livedata.GlobalBlockerLiveDataProvider;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideGlobalBlockerProvider$AndroidPhotosUploader_releaseFactory implements Factory<GlobalBlockerLiveDataProvider> {
    private final ConfigurationModule module;
    private final Provider<UploadSummaryObservable> uploadSummaryObservableProvider;

    public ConfigurationModule_ProvideGlobalBlockerProvider$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadSummaryObservable> provider) {
        this.module = configurationModule;
        this.uploadSummaryObservableProvider = provider;
    }

    public static ConfigurationModule_ProvideGlobalBlockerProvider$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadSummaryObservable> provider) {
        return new ConfigurationModule_ProvideGlobalBlockerProvider$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static GlobalBlockerLiveDataProvider provideGlobalBlockerProvider$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadSummaryObservable uploadSummaryObservable) {
        return (GlobalBlockerLiveDataProvider) Preconditions.checkNotNull(configurationModule.provideGlobalBlockerProvider$AndroidPhotosUploader_release(uploadSummaryObservable), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GlobalBlockerLiveDataProvider mo10268get() {
        return provideGlobalBlockerProvider$AndroidPhotosUploader_release(this.module, this.uploadSummaryObservableProvider.mo10268get());
    }
}
