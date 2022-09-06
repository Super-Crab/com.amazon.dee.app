package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadVerifier;
import com.amazon.photos.uploader.cds.multipart.PartInfoDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideMultipartUploadVerifier$AndroidPhotosUploader_releaseFactory implements Factory<MultipartUploadVerifier> {
    private final Provider<CDClient> cdClientProvider;
    private final Provider<Metrics> metricsProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<PartInfoDao> partInfoDaoProvider;

    public CdsUploaderConfigurationModule_ProvideMultipartUploadVerifier$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<PartInfoDao> provider3) {
        this.module = cdsUploaderConfigurationModule;
        this.cdClientProvider = provider;
        this.metricsProvider = provider2;
        this.partInfoDaoProvider = provider3;
    }

    public static CdsUploaderConfigurationModule_ProvideMultipartUploadVerifier$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<PartInfoDao> provider3) {
        return new CdsUploaderConfigurationModule_ProvideMultipartUploadVerifier$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3);
    }

    public static MultipartUploadVerifier provideMultipartUploadVerifier$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, CDClient cDClient, Metrics metrics, PartInfoDao partInfoDao) {
        return (MultipartUploadVerifier) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideMultipartUploadVerifier$AndroidPhotosUploader_release(cDClient, metrics, partInfoDao), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MultipartUploadVerifier mo10268get() {
        return provideMultipartUploadVerifier$AndroidPhotosUploader_release(this.module, this.cdClientProvider.mo10268get(), this.metricsProvider.mo10268get(), this.partInfoDaoProvider.mo10268get());
    }
}
