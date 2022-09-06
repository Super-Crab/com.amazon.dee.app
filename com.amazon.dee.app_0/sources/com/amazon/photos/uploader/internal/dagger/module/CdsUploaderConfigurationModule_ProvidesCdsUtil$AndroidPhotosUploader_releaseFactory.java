package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.CdsUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesCdsUtil$AndroidPhotosUploader_releaseFactory implements Factory<CdsUtil> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvidesCdsUtil$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvidesCdsUtil$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvidesCdsUtil$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static CdsUtil providesCdsUtil$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (CdsUtil) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesCdsUtil$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsUtil mo10268get() {
        return providesCdsUtil$AndroidPhotosUploader_release(this.module);
    }
}
