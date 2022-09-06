package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.error.CdsUploadPartErrorResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseFactory implements Factory<CdsUploadPartErrorResolver> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvidesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvidesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvidesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static CdsUploadPartErrorResolver providesCdsUploadPartErrorResolver$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (CdsUploadPartErrorResolver) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesCdsUploadPartErrorResolver$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsUploadPartErrorResolver mo10268get() {
        return providesCdsUploadPartErrorResolver$AndroidPhotosUploader_release(this.module);
    }
}
