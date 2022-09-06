package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideSystemUtil$AndroidPhotosUploader_releaseFactory implements Factory<SystemUtil> {
    private final ConfigurationModule module;
    private final Provider<UploadFrameworkContext> uploadFrameworkContextProvider;

    public ConfigurationModule_ProvideSystemUtil$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<UploadFrameworkContext> provider) {
        this.module = configurationModule;
        this.uploadFrameworkContextProvider = provider;
    }

    public static ConfigurationModule_ProvideSystemUtil$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<UploadFrameworkContext> provider) {
        return new ConfigurationModule_ProvideSystemUtil$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static SystemUtil provideSystemUtil$AndroidPhotosUploader_release(ConfigurationModule configurationModule, UploadFrameworkContext uploadFrameworkContext) {
        return (SystemUtil) Preconditions.checkNotNull(configurationModule.provideSystemUtil$AndroidPhotosUploader_release(uploadFrameworkContext), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SystemUtil mo10268get() {
        return provideSystemUtil$AndroidPhotosUploader_release(this.module, this.uploadFrameworkContextProvider.mo10268get());
    }
}
