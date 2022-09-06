package com.amazon.photos.uploader.internal.dagger.module;

import androidx.work.WorkManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideWorkManager$AndroidPhotosUploader_releaseFactory implements Factory<WorkManager> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideWorkManager$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideWorkManager$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideWorkManager$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static WorkManager provideWorkManager$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (WorkManager) Preconditions.checkNotNull(configurationModule.provideWorkManager$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkManager mo10268get() {
        return provideWorkManager$AndroidPhotosUploader_release(this.module);
    }
}
