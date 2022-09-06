package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.blockers.unauthorizedaccess.UnauthorizedAccessPersistence;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseFactory implements Factory<UnauthorizedAccessPersistence> {
    private final ConfigurationModule module;
    private final Provider<UploadFrameworkContext> uploadFrameworkContextProvider;

    public ConfigurationModule_ProvideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadFrameworkContext> provider) {
        this.module = configurationModule;
        this.uploadFrameworkContextProvider = provider;
    }

    public static ConfigurationModule_ProvideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadFrameworkContext> provider) {
        return new ConfigurationModule_ProvideUnauthorizedAccessPersistence$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static UnauthorizedAccessPersistence provideUnauthorizedAccessPersistence$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadFrameworkContext uploadFrameworkContext) {
        return (UnauthorizedAccessPersistence) Preconditions.checkNotNull(configurationModule.provideUnauthorizedAccessPersistence$AndroidPhotosUploader_release(uploadFrameworkContext), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UnauthorizedAccessPersistence mo10268get() {
        return provideUnauthorizedAccessPersistence$AndroidPhotosUploader_release(this.module, this.uploadFrameworkContextProvider.mo10268get());
    }
}
