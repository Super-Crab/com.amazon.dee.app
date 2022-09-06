package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.cds.ParentNodeFetcher;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadNodeFetcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseFactory implements Factory<MultipartUploadNodeFetcher> {
    private final Provider<CDClient> cdClientProvider;
    private final Provider<CdsUploadErrorResolver> cdsErrorResolverProvider;
    private final Provider<Metrics> metricsProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<ParentNodeFetcher> parentNodeFetcherProvider;

    public CdsUploaderConfigurationModule_ProvideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<CdsUploadErrorResolver> provider3, Provider<ParentNodeFetcher> provider4) {
        this.module = cdsUploaderConfigurationModule;
        this.cdClientProvider = provider;
        this.metricsProvider = provider2;
        this.cdsErrorResolverProvider = provider3;
        this.parentNodeFetcherProvider = provider4;
    }

    public static CdsUploaderConfigurationModule_ProvideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<CdsUploadErrorResolver> provider3, Provider<ParentNodeFetcher> provider4) {
        return new CdsUploaderConfigurationModule_ProvideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3, provider4);
    }

    public static MultipartUploadNodeFetcher provideMultipartUploadNodeFetcher$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, CDClient cDClient, Metrics metrics, CdsUploadErrorResolver cdsUploadErrorResolver, ParentNodeFetcher parentNodeFetcher) {
        return (MultipartUploadNodeFetcher) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideMultipartUploadNodeFetcher$AndroidPhotosUploader_release(cDClient, metrics, cdsUploadErrorResolver, parentNodeFetcher), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MultipartUploadNodeFetcher mo10268get() {
        return provideMultipartUploadNodeFetcher$AndroidPhotosUploader_release(this.module, this.cdClientProvider.mo10268get(), this.metricsProvider.mo10268get(), this.cdsErrorResolverProvider.mo10268get(), this.parentNodeFetcherProvider.mo10268get());
    }
}
