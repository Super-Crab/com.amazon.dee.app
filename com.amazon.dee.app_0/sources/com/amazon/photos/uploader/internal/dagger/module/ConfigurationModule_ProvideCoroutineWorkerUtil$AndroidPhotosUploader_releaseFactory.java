package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.workers.CoroutineWorkerUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory implements Factory<CoroutineWorkerUtil> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvideCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvideCoroutineWorkerUtil$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    public static CoroutineWorkerUtil provideCoroutineWorkerUtil$AndroidPhotosUploader_release(ConfigurationModule configurationModule) {
        return (CoroutineWorkerUtil) Preconditions.checkNotNull(configurationModule.provideCoroutineWorkerUtil$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoroutineWorkerUtil mo10268get() {
        return provideCoroutineWorkerUtil$AndroidPhotosUploader_release(this.module);
    }
}
