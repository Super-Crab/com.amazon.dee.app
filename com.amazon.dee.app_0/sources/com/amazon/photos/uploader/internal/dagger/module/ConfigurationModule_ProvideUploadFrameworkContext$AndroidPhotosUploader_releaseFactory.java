package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.UploadFrameworkContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory implements Factory<UploadFrameworkContext> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static UploadFrameworkContext provideUploadFrameworkContext$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (UploadFrameworkContext) Preconditions.checkNotNull(configurationModule.provideUploadFrameworkContext$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadFrameworkContext mo10268get() {
        return provideUploadFrameworkContext$AndroidPhotosUploader_release(this.module);
    }
}
