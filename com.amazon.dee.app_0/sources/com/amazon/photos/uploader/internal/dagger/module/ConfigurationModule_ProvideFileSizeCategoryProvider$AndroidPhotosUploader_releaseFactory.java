package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.FileSizeCategoryProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideFileSizeCategoryProvider$AndroidPhotosUploader_releaseFactory implements Factory<FileSizeCategoryProvider> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideFileSizeCategoryProvider$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideFileSizeCategoryProvider$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideFileSizeCategoryProvider$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static FileSizeCategoryProvider provideFileSizeCategoryProvider$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (FileSizeCategoryProvider) Preconditions.checkNotNull(configurationModule.provideFileSizeCategoryProvider$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FileSizeCategoryProvider mo10268get() {
        return provideFileSizeCategoryProvider$AndroidPhotosUploader_release(this.module);
    }
}
