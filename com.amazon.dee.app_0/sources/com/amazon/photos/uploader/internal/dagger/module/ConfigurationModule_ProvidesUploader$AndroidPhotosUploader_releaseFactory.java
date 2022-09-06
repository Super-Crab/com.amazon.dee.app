package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.Uploader;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidesUploader$AndroidPhotosUploader_releaseFactory implements Factory<Uploader> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvidesUploader$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvidesUploader$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvidesUploader$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static Uploader providesUploader$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (Uploader) Preconditions.checkNotNull(configurationModule.providesUploader$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Uploader mo10268get() {
        return providesUploader$AndroidPhotosUploader_release(this.module);
    }
}
