package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.cds.error.CdsConflictResolver;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.cds.error.MultiPartUploadErrorResolver;
import com.amazon.photos.uploader.cds.observer.CdsUploadNotifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesCdsUploadErrorResolver$AndroidPhotosUploader_releaseFactory implements Factory<CdsUploadErrorResolver> {
    private final Provider<CdsConflictResolver> cdsConflictResolverProvider;
    private final Provider<CdsUploadNotifier> cdsUploadNotifierProvider;
    private final Provider<Metrics> metricsProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<MultiPartUploadErrorResolver> multiPartUploadErrorResolverProvider;

    public CdsUploaderConfigurationModule_ProvidesCdsUploadErrorResolver$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<Metrics> provider, Provider<CdsConflictResolver> provider2, Provider<MultiPartUploadErrorResolver> provider3, Provider<CdsUploadNotifier> provider4) {
        this.module = cdsUploaderConfigurationModule;
        this.metricsProvider = provider;
        this.cdsConflictResolverProvider = provider2;
        this.multiPartUploadErrorResolverProvider = provider3;
        this.cdsUploadNotifierProvider = provider4;
    }

    public static CdsUploaderConfigurationModule_ProvidesCdsUploadErrorResolver$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<Metrics> provider, Provider<CdsConflictResolver> provider2, Provider<MultiPartUploadErrorResolver> provider3, Provider<CdsUploadNotifier> provider4) {
        return new CdsUploaderConfigurationModule_ProvidesCdsUploadErrorResolver$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3, provider4);
    }

    public static CdsUploadErrorResolver providesCdsUploadErrorResolver$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Metrics metrics, CdsConflictResolver cdsConflictResolver, MultiPartUploadErrorResolver multiPartUploadErrorResolver, CdsUploadNotifier cdsUploadNotifier) {
        return (CdsUploadErrorResolver) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesCdsUploadErrorResolver$AndroidPhotosUploader_release(metrics, cdsConflictResolver, multiPartUploadErrorResolver, cdsUploadNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsUploadErrorResolver mo10268get() {
        return providesCdsUploadErrorResolver$AndroidPhotosUploader_release(this.module, this.metricsProvider.mo10268get(), this.cdsConflictResolverProvider.mo10268get(), this.multiPartUploadErrorResolverProvider.mo10268get(), this.cdsUploadNotifierProvider.mo10268get());
    }
}
