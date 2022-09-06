package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadCompleter;
import com.amazon.photos.uploader.cds.multipart.PartInfoDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideMultipartUploadCompleter$AndroidPhotosUploader_releaseFactory implements Factory<MultipartUploadCompleter> {
    private final Provider<CDClient> cdClientProvider;
    private final Provider<CdsUploadErrorResolver> cdsErrorResolverProvider;
    private final Provider<Metrics> metricsProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<PartInfoDao> partInfoDaoProvider;

    public CdsUploaderConfigurationModule_ProvideMultipartUploadCompleter$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<CdsUploadErrorResolver> provider3, Provider<PartInfoDao> provider4) {
        this.module = cdsUploaderConfigurationModule;
        this.cdClientProvider = provider;
        this.metricsProvider = provider2;
        this.cdsErrorResolverProvider = provider3;
        this.partInfoDaoProvider = provider4;
    }

    public static CdsUploaderConfigurationModule_ProvideMultipartUploadCompleter$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<CdsUploadErrorResolver> provider3, Provider<PartInfoDao> provider4) {
        return new CdsUploaderConfigurationModule_ProvideMultipartUploadCompleter$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3, provider4);
    }

    public static MultipartUploadCompleter provideMultipartUploadCompleter$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, CDClient cDClient, Metrics metrics, CdsUploadErrorResolver cdsUploadErrorResolver, PartInfoDao partInfoDao) {
        return (MultipartUploadCompleter) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideMultipartUploadCompleter$AndroidPhotosUploader_release(cDClient, metrics, cdsUploadErrorResolver, partInfoDao), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MultipartUploadCompleter mo10268get() {
        return provideMultipartUploadCompleter$AndroidPhotosUploader_release(this.module, this.cdClientProvider.mo10268get(), this.metricsProvider.mo10268get(), this.cdsErrorResolverProvider.mo10268get(), this.partInfoDaoProvider.mo10268get());
    }
}
