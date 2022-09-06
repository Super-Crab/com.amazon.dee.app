package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.observer.CdsUploadNotifier;
import com.amazon.photos.uploader.cds.observer.CdsUploadObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideCdsUploadObservable$AndroidPhotosUploader_releaseFactory implements Factory<CdsUploadObservable> {
    private final Provider<CdsUploadNotifier> cdsUploadNotifierProvider;
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvideCdsUploadObservable$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CdsUploadNotifier> provider) {
        this.module = cdsUploaderConfigurationModule;
        this.cdsUploadNotifierProvider = provider;
    }

    public static CdsUploaderConfigurationModule_ProvideCdsUploadObservable$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CdsUploadNotifier> provider) {
        return new CdsUploaderConfigurationModule_ProvideCdsUploadObservable$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider);
    }

    public static CdsUploadObservable provideCdsUploadObservable$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, CdsUploadNotifier cdsUploadNotifier) {
        return (CdsUploadObservable) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideCdsUploadObservable$AndroidPhotosUploader_release(cdsUploadNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsUploadObservable mo10268get() {
        return provideCdsUploadObservable$AndroidPhotosUploader_release(this.module, this.cdsUploadNotifierProvider.mo10268get());
    }
}
