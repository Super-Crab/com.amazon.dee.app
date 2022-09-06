package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.cds.ParentNodeFetcher;
import com.amazon.photos.uploader.cds.PartProvider;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader;
import com.amazon.photos.uploader.cds.multipart.MultipartTransactionRunner;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadCompleter;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadInitiator;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadNodeFetcher;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadVerifier;
import com.amazon.photos.uploader.cds.multipart.PartInfoDao;
import com.amazon.photos.uploader.cds.multipart.PartUploader;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesCdsMultiPartUploader$AndroidPhotosUploader_releaseFactory implements Factory<CdsMultiPartUploader> {
    private final Provider<CDClient> cdClientProvider;
    private final Provider<CdsUploadErrorResolver> cdsErrorResolverProvider;
    private final Provider<ContentSignatureProvider> contentSignatureProvider;
    private final Provider<DestroyableDatabaseWrapper> destroyableDatabaseWrapperProvider;
    private final Provider<Metrics> metricsProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<MultipartTransactionRunner> multipartTransactionRunnerProvider;
    private final Provider<MultipartUploadCompleter> multipartUploadCompleterProvider;
    private final Provider<MultipartUploadInitiator> multipartUploadInitiatorProvider;
    private final Provider<MultipartUploadNodeFetcher> multipartUploadNodeFetcherProvider;
    private final Provider<MultipartUploadRequestMetadataDao> multipartUploadRequestMetadataDaoProvider;
    private final Provider<MultipartUploadVerifier> multipartUploadVerifierProvider;
    private final Provider<ParentNodeFetcher> parentNodeFetcherProvider;
    private final Provider<PartInfoDao> partInfoDaoProvider;
    private final Provider<PartProvider> partProvider;
    private final Provider<PartUploader> partUploaderProvider;
    private final Provider<UploadFrameworkContext> uploadFrameworkContextProvider;

    public CdsUploaderConfigurationModule_ProvidesCdsMultiPartUploader$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<UploadFrameworkContext> provider, Provider<CDClient> provider2, Provider<CdsUploadErrorResolver> provider3, Provider<Metrics> provider4, Provider<ParentNodeFetcher> provider5, Provider<MultipartUploadRequestMetadataDao> provider6, Provider<PartInfoDao> provider7, Provider<ContentSignatureProvider> provider8, Provider<DestroyableDatabaseWrapper> provider9, Provider<PartProvider> provider10, Provider<MultipartUploadInitiator> provider11, Provider<PartUploader> provider12, Provider<MultipartUploadCompleter> provider13, Provider<MultipartUploadVerifier> provider14, Provider<MultipartUploadNodeFetcher> provider15, Provider<MultipartTransactionRunner> provider16) {
        this.module = cdsUploaderConfigurationModule;
        this.uploadFrameworkContextProvider = provider;
        this.cdClientProvider = provider2;
        this.cdsErrorResolverProvider = provider3;
        this.metricsProvider = provider4;
        this.parentNodeFetcherProvider = provider5;
        this.multipartUploadRequestMetadataDaoProvider = provider6;
        this.partInfoDaoProvider = provider7;
        this.contentSignatureProvider = provider8;
        this.destroyableDatabaseWrapperProvider = provider9;
        this.partProvider = provider10;
        this.multipartUploadInitiatorProvider = provider11;
        this.partUploaderProvider = provider12;
        this.multipartUploadCompleterProvider = provider13;
        this.multipartUploadVerifierProvider = provider14;
        this.multipartUploadNodeFetcherProvider = provider15;
        this.multipartTransactionRunnerProvider = provider16;
    }

    public static CdsUploaderConfigurationModule_ProvidesCdsMultiPartUploader$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<UploadFrameworkContext> provider, Provider<CDClient> provider2, Provider<CdsUploadErrorResolver> provider3, Provider<Metrics> provider4, Provider<ParentNodeFetcher> provider5, Provider<MultipartUploadRequestMetadataDao> provider6, Provider<PartInfoDao> provider7, Provider<ContentSignatureProvider> provider8, Provider<DestroyableDatabaseWrapper> provider9, Provider<PartProvider> provider10, Provider<MultipartUploadInitiator> provider11, Provider<PartUploader> provider12, Provider<MultipartUploadCompleter> provider13, Provider<MultipartUploadVerifier> provider14, Provider<MultipartUploadNodeFetcher> provider15, Provider<MultipartTransactionRunner> provider16) {
        return new CdsUploaderConfigurationModule_ProvidesCdsMultiPartUploader$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16);
    }

    public static CdsMultiPartUploader providesCdsMultiPartUploader$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, UploadFrameworkContext uploadFrameworkContext, CDClient cDClient, CdsUploadErrorResolver cdsUploadErrorResolver, Metrics metrics, ParentNodeFetcher parentNodeFetcher, MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, PartInfoDao partInfoDao, ContentSignatureProvider contentSignatureProvider, DestroyableDatabaseWrapper destroyableDatabaseWrapper, PartProvider partProvider, MultipartUploadInitiator multipartUploadInitiator, PartUploader partUploader, MultipartUploadCompleter multipartUploadCompleter, MultipartUploadVerifier multipartUploadVerifier, MultipartUploadNodeFetcher multipartUploadNodeFetcher, MultipartTransactionRunner multipartTransactionRunner) {
        return (CdsMultiPartUploader) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesCdsMultiPartUploader$AndroidPhotosUploader_release(uploadFrameworkContext, cDClient, cdsUploadErrorResolver, metrics, parentNodeFetcher, multipartUploadRequestMetadataDao, partInfoDao, contentSignatureProvider, destroyableDatabaseWrapper, partProvider, multipartUploadInitiator, partUploader, multipartUploadCompleter, multipartUploadVerifier, multipartUploadNodeFetcher, multipartTransactionRunner), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsMultiPartUploader mo10268get() {
        return providesCdsMultiPartUploader$AndroidPhotosUploader_release(this.module, this.uploadFrameworkContextProvider.mo10268get(), this.cdClientProvider.mo10268get(), this.cdsErrorResolverProvider.mo10268get(), this.metricsProvider.mo10268get(), this.parentNodeFetcherProvider.mo10268get(), this.multipartUploadRequestMetadataDaoProvider.mo10268get(), this.partInfoDaoProvider.mo10268get(), this.contentSignatureProvider.mo10268get(), this.destroyableDatabaseWrapperProvider.mo10268get(), this.partProvider.mo10268get(), this.multipartUploadInitiatorProvider.mo10268get(), this.partUploaderProvider.mo10268get(), this.multipartUploadCompleterProvider.mo10268get(), this.multipartUploadVerifierProvider.mo10268get(), this.multipartUploadNodeFetcherProvider.mo10268get(), this.multipartTransactionRunnerProvider.mo10268get());
    }
}
