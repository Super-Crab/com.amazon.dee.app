package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.cds.PartProvider;
import com.amazon.photos.uploader.cds.error.CdsUploadPartErrorResolver;
import com.amazon.photos.uploader.cds.multipart.PartInfoDao;
import com.amazon.photos.uploader.cds.multipart.PartUploader;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidePartUploader$AndroidPhotosUploader_releaseFactory implements Factory<PartUploader> {
    private final Provider<CDClient> cdClientProvider;
    private final Provider<ContentSignatureProvider> contentSignatureProvider;
    private final Provider<Metrics> metricsProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<PartInfoDao> partInfoDaoProvider;
    private final Provider<PartProvider> partProvider;
    private final Provider<CdsUploadPartErrorResolver> partUploadErrorResolverProvider;

    public CdsUploaderConfigurationModule_ProvidePartUploader$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<PartInfoDao> provider3, Provider<ContentSignatureProvider> provider4, Provider<PartProvider> provider5, Provider<CdsUploadPartErrorResolver> provider6) {
        this.module = cdsUploaderConfigurationModule;
        this.cdClientProvider = provider;
        this.metricsProvider = provider2;
        this.partInfoDaoProvider = provider3;
        this.contentSignatureProvider = provider4;
        this.partProvider = provider5;
        this.partUploadErrorResolverProvider = provider6;
    }

    public static CdsUploaderConfigurationModule_ProvidePartUploader$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<PartInfoDao> provider3, Provider<ContentSignatureProvider> provider4, Provider<PartProvider> provider5, Provider<CdsUploadPartErrorResolver> provider6) {
        return new CdsUploaderConfigurationModule_ProvidePartUploader$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static PartUploader providePartUploader$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, CDClient cDClient, Metrics metrics, PartInfoDao partInfoDao, ContentSignatureProvider contentSignatureProvider, PartProvider partProvider, CdsUploadPartErrorResolver cdsUploadPartErrorResolver) {
        return (PartUploader) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providePartUploader$AndroidPhotosUploader_release(cDClient, metrics, partInfoDao, contentSignatureProvider, partProvider, cdsUploadPartErrorResolver), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PartUploader mo10268get() {
        return providePartUploader$AndroidPhotosUploader_release(this.module, this.cdClientProvider.mo10268get(), this.metricsProvider.mo10268get(), this.partInfoDaoProvider.mo10268get(), this.contentSignatureProvider.mo10268get(), this.partProvider.mo10268get(), this.partUploadErrorResolverProvider.mo10268get());
    }
}
