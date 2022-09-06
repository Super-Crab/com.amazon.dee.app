package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.utils.FileUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory implements Factory<FileUtils> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static FileUtils provideFileUtils$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (FileUtils) Preconditions.checkNotNull(configurationModule.provideFileUtils$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FileUtils mo10268get() {
        return provideFileUtils$AndroidPhotosUploader_release(this.module);
    }
}
