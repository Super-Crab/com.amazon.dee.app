package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.observer.CdsUploadNotifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideCdsUploadNotifier$AndroidPhotosUploader_releaseFactory implements Factory<CdsUploadNotifier> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideCdsUploadNotifier$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvideCdsUploadNotifier$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvideCdsUploadNotifier$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static CdsUploadNotifier provideCdsUploadNotifier$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (CdsUploadNotifier) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideCdsUploadNotifier$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsUploadNotifier mo10268get() {
        return provideCdsUploadNotifier$AndroidPhotosUploader_release(this.module);
    }
}
