package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.log.UploadLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideUploadLogger$AndroidPhotosUploader_releaseFactory implements Factory<UploadLogger> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideUploadLogger$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideUploadLogger$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideUploadLogger$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static UploadLogger provideUploadLogger$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (UploadLogger) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideUploadLogger$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadLogger mo10268get() {
        return provideUploadLogger$AndroidPhotosUploader_release(this.module);
    }
}
