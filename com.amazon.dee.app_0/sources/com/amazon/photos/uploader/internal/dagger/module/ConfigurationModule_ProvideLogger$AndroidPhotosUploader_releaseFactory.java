package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.log.UploadLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideLogger$AndroidPhotosUploader_releaseFactory implements Factory<UploadLogger> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideLogger$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideLogger$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideLogger$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static UploadLogger provideLogger$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (UploadLogger) Preconditions.checkNotNull(configurationModule.provideLogger$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadLogger mo10268get() {
        return provideLogger$AndroidPhotosUploader_release(this.module);
    }
}
