package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.CdsCallClientWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideCdsCallClientWrapper$AndroidPhotosUploader_releaseFactory implements Factory<CdsCallClientWrapper> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideCdsCallClientWrapper$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideCdsCallClientWrapper$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideCdsCallClientWrapper$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static CdsCallClientWrapper provideCdsCallClientWrapper$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (CdsCallClientWrapper) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideCdsCallClientWrapper$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsCallClientWrapper mo10268get() {
        return provideCdsCallClientWrapper$AndroidPhotosUploader_release(this.module);
    }
}
