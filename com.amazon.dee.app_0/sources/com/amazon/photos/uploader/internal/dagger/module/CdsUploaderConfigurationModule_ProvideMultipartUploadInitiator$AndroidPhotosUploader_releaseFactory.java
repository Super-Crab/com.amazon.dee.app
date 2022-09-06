package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.cds.ParentNodeFetcher;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.cds.multipart.InitializeMultiPartRequestBuilder;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadInitiator;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao;
import com.amazon.photos.uploader.cds.multipart.PartInfoDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideMultipartUploadInitiator$AndroidPhotosUploader_releaseFactory implements Factory<MultipartUploadInitiator> {
    private final Provider<CDClient> cdClientProvider;
    private final Provider<CdsUploadErrorResolver> cdsErrorResolverProvider;
    private final Provider<InitializeMultiPartRequestBuilder> initializeMultiPartRequestBuilderProvider;
    private final Provider<Metrics> metricsProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<MultipartUploadRequestMetadataDao> multipartUploadRequestMetadataDaoProvider;
    private final Provider<ParentNodeFetcher> parentNodeFetcherProvider;
    private final Provider<PartInfoDao> partInfoDaoProvider;

    public CdsUploaderConfigurationModule_ProvideMultipartUploadInitiator$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<ParentNodeFetcher> provider3, Provider<CdsUploadErrorResolver> provider4, Provider<MultipartUploadRequestMetadataDao> provider5, Provider<PartInfoDao> provider6, Provider<InitializeMultiPartRequestBuilder> provider7) {
        this.module = cdsUploaderConfigurationModule;
        this.cdClientProvider = provider;
        this.metricsProvider = provider2;
        this.parentNodeFetcherProvider = provider3;
        this.cdsErrorResolverProvider = provider4;
        this.multipartUploadRequestMetadataDaoProvider = provider5;
        this.partInfoDaoProvider = provider6;
        this.initializeMultiPartRequestBuilderProvider = provider7;
    }

    public static CdsUploaderConfigurationModule_ProvideMultipartUploadInitiator$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<Metrics> provider2, Provider<ParentNodeFetcher> provider3, Provider<CdsUploadErrorResolver> provider4, Provider<MultipartUploadRequestMetadataDao> provider5, Provider<PartInfoDao> provider6, Provider<InitializeMultiPartRequestBuilder> provider7) {
        return new CdsUploaderConfigurationModule_ProvideMultipartUploadInitiator$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static MultipartUploadInitiator provideMultipartUploadInitiator$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, CDClient cDClient, Metrics metrics, ParentNodeFetcher parentNodeFetcher, CdsUploadErrorResolver cdsUploadErrorResolver, MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, PartInfoDao partInfoDao, InitializeMultiPartRequestBuilder initializeMultiPartRequestBuilder) {
        return (MultipartUploadInitiator) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideMultipartUploadInitiator$AndroidPhotosUploader_release(cDClient, metrics, parentNodeFetcher, cdsUploadErrorResolver, multipartUploadRequestMetadataDao, partInfoDao, initializeMultiPartRequestBuilder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MultipartUploadInitiator mo10268get() {
        return provideMultipartUploadInitiator$AndroidPhotosUploader_release(this.module, this.cdClientProvider.mo10268get(), this.metricsProvider.mo10268get(), this.parentNodeFetcherProvider.mo10268get(), this.cdsErrorResolverProvider.mo10268get(), this.multipartUploadRequestMetadataDaoProvider.mo10268get(), this.partInfoDaoProvider.mo10268get(), this.initializeMultiPartRequestBuilderProvider.mo10268get());
    }
}
