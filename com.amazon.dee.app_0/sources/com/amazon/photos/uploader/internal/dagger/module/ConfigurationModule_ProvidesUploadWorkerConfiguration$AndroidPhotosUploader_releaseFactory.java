package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.UploadWorkerConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidesUploadWorkerConfiguration$AndroidPhotosUploader_releaseFactory implements Factory<UploadWorkerConfiguration> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvidesUploadWorkerConfiguration$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvidesUploadWorkerConfiguration$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvidesUploadWorkerConfiguration$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static UploadWorkerConfiguration providesUploadWorkerConfiguration$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (UploadWorkerConfiguration) Preconditions.checkNotNull(configurationModule.providesUploadWorkerConfiguration$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadWorkerConfiguration mo10268get() {
        return providesUploadWorkerConfiguration$AndroidPhotosUploader_release(this.module);
    }
}
