package com.amazon.photos.uploader.internal.dagger.component;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.cds.CdsCallClientWrapper;
import com.amazon.photos.uploader.cds.CdsSinglePartUploader;
import com.amazon.photos.uploader.cds.CdsUploader;
import com.amazon.photos.uploader.cds.CdsUtil;
import com.amazon.photos.uploader.cds.ParentIdCache;
import com.amazon.photos.uploader.cds.ParentNodeFetcher;
import com.amazon.photos.uploader.cds.PartProvider;
import com.amazon.photos.uploader.cds.error.CdsConflictResolver;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.cds.error.CdsUploadPartErrorResolver;
import com.amazon.photos.uploader.cds.error.MultiPartUploadErrorResolver;
import com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader;
import com.amazon.photos.uploader.cds.multipart.InitializeMultiPartRequestBuilder;
import com.amazon.photos.uploader.cds.multipart.MultipartDatabaseModule;
import com.amazon.photos.uploader.cds.multipart.MultipartDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.cds.multipart.MultipartDatabaseModule_ProvideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.cds.multipart.MultipartDatabaseModule_ProvideMultipartTransactionRunner$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.cds.multipart.MultipartDatabaseModule_ProvidePartInfoDao$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.cds.multipart.MultipartTransactionRunner;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadCompleter;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadInitiator;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadNodeFetcher;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadVerifier;
import com.amazon.photos.uploader.cds.multipart.PartInfoDao;
import com.amazon.photos.uploader.cds.multipart.PartUploader;
import com.amazon.photos.uploader.cds.nodecache.NodeCacheDatabaseModule;
import com.amazon.photos.uploader.cds.nodecache.NodeCacheDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.cds.nodecache.NodeCacheDatabaseModule_ProvideParentIdDao$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.cds.nodecache.ParentIdDao;
import com.amazon.photos.uploader.cds.observer.CdsUploadNotifier;
import com.amazon.photos.uploader.cds.observer.CdsUploadObservable;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideCdsCallClientWrapper$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideCdsConflictResolver$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideCdsUploadNotifier$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideCdsUploadObservable$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideMultipartUploadCompleter$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideMultipartUploadInitiator$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideMultipartUploadVerifier$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideParentIdCache$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidePartProvider$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidePartUploader$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvideUploadLogger$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidesCDCClient$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidesCdsMultiPartUploader$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidesCdsSinglePartUploader$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidesCdsUploadErrorResolver$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidesCdsUtil$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule_ProvidesParentNodeFetcher$AndroidPhotosUploader_releaseFactory;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.log.UploadLogger;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerCdsUploaderComponent implements CdsUploaderComponent {
    private Provider<CdsCallClientWrapper> provideCdsCallClientWrapper$AndroidPhotosUploader_releaseProvider;
    private Provider<CdsConflictResolver> provideCdsConflictResolver$AndroidPhotosUploader_releaseProvider;
    private Provider<CdsUploadNotifier> provideCdsUploadNotifier$AndroidPhotosUploader_releaseProvider;
    private Provider<CdsUploadObservable> provideCdsUploadObservable$AndroidPhotosUploader_releaseProvider;
    private Provider<ContentSignatureProvider> provideContentSignatureProvider$AndroidPhotosUploader_releaseProvider;
    private Provider<DestroyableDatabaseWrapper> provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider;
    private Provider<DestroyableDatabaseWrapper> provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider2;
    private Provider<FileUtils> provideFileUtils$AndroidPhotosUploader_releaseProvider;
    private Provider<InitializeMultiPartRequestBuilder> provideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseProvider;
    private Provider<Metrics> provideMetrics$AndroidPhotosUploader_releaseProvider;
    private Provider<MultipartUploadRequestMetadataDao> provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseProvider;
    private Provider<MultipartTransactionRunner> provideMultipartTransactionRunner$AndroidPhotosUploader_releaseProvider;
    private Provider<MultipartUploadCompleter> provideMultipartUploadCompleter$AndroidPhotosUploader_releaseProvider;
    private Provider<MultipartUploadInitiator> provideMultipartUploadInitiator$AndroidPhotosUploader_releaseProvider;
    private Provider<MultipartUploadNodeFetcher> provideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseProvider;
    private Provider<MultipartUploadVerifier> provideMultipartUploadVerifier$AndroidPhotosUploader_releaseProvider;
    private Provider<ParentIdCache> provideParentIdCache$AndroidPhotosUploader_releaseProvider;
    private Provider<ParentIdDao> provideParentIdDao$AndroidPhotosUploader_releaseProvider;
    private Provider<PartInfoDao> providePartInfoDao$AndroidPhotosUploader_releaseProvider;
    private Provider<PartProvider> providePartProvider$AndroidPhotosUploader_releaseProvider;
    private Provider<PartUploader> providePartUploader$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadFrameworkContext> provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider;
    private Provider<UploadLogger> provideUploadLogger$AndroidPhotosUploader_releaseProvider;
    private Provider<CDClient> providesCDCClient$AndroidPhotosUploader_releaseProvider;
    private Provider<MultiPartUploadErrorResolver> providesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseProvider;
    private Provider<CdsMultiPartUploader> providesCdsMultiPartUploader$AndroidPhotosUploader_releaseProvider;
    private Provider<CdsSinglePartUploader> providesCdsSinglePartUploader$AndroidPhotosUploader_releaseProvider;
    private Provider<CdsUploadErrorResolver> providesCdsUploadErrorResolver$AndroidPhotosUploader_releaseProvider;
    private Provider<CdsUploadPartErrorResolver> providesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseProvider;
    private Provider<CdsUtil> providesCdsUtil$AndroidPhotosUploader_releaseProvider;
    private Provider<ParentNodeFetcher> providesParentNodeFetcher$AndroidPhotosUploader_releaseProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private CdsUploaderConfigurationModule cdsUploaderConfigurationModule;
        private MultipartDatabaseModule multipartDatabaseModule;
        private NodeCacheDatabaseModule nodeCacheDatabaseModule;

        public CdsUploaderComponent build() {
            Preconditions.checkBuilderRequirement(this.cdsUploaderConfigurationModule, CdsUploaderConfigurationModule.class);
            Preconditions.checkBuilderRequirement(this.multipartDatabaseModule, MultipartDatabaseModule.class);
            Preconditions.checkBuilderRequirement(this.nodeCacheDatabaseModule, NodeCacheDatabaseModule.class);
            return new DaggerCdsUploaderComponent(this.cdsUploaderConfigurationModule, this.multipartDatabaseModule, this.nodeCacheDatabaseModule);
        }

        public Builder cdsUploaderConfigurationModule(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
            this.cdsUploaderConfigurationModule = (CdsUploaderConfigurationModule) Preconditions.checkNotNull(cdsUploaderConfigurationModule);
            return this;
        }

        public Builder multipartDatabaseModule(MultipartDatabaseModule multipartDatabaseModule) {
            this.multipartDatabaseModule = (MultipartDatabaseModule) Preconditions.checkNotNull(multipartDatabaseModule);
            return this;
        }

        public Builder nodeCacheDatabaseModule(NodeCacheDatabaseModule nodeCacheDatabaseModule) {
            this.nodeCacheDatabaseModule = (NodeCacheDatabaseModule) Preconditions.checkNotNull(nodeCacheDatabaseModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, MultipartDatabaseModule multipartDatabaseModule, NodeCacheDatabaseModule nodeCacheDatabaseModule) {
        this.providesCDCClient$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidesCDCClient$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideMetrics$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideMetrics$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideCdsConflictResolver$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideCdsConflictResolver$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(MultipartDatabaseModule_ProvideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseFactory.create(multipartDatabaseModule));
        this.providesCdsUtil$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidesCdsUtil$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.providesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseProvider, this.providesCdsUtil$AndroidPhotosUploader_releaseProvider));
        this.provideCdsUploadNotifier$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideCdsUploadNotifier$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.providesCdsUploadErrorResolver$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidesCdsUploadErrorResolver$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.provideCdsConflictResolver$AndroidPhotosUploader_releaseProvider, this.providesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseProvider, this.provideCdsUploadNotifier$AndroidPhotosUploader_releaseProvider));
        this.provideCdsCallClientWrapper$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideCdsCallClientWrapper$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideParentIdDao$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(NodeCacheDatabaseModule_ProvideParentIdDao$AndroidPhotosUploader_releaseFactory.create(nodeCacheDatabaseModule));
        this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideUploadFrameworkContext$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(NodeCacheDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory.create(nodeCacheDatabaseModule, this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider));
        this.provideCdsUploadObservable$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideCdsUploadObservable$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.provideCdsUploadNotifier$AndroidPhotosUploader_releaseProvider));
        this.provideParentIdCache$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideParentIdCache$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.provideParentIdDao$AndroidPhotosUploader_releaseProvider, this.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider, this.provideCdsUploadObservable$AndroidPhotosUploader_releaseProvider));
        this.providesParentNodeFetcher$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidesParentNodeFetcher$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.provideCdsCallClientWrapper$AndroidPhotosUploader_releaseProvider, this.provideParentIdCache$AndroidPhotosUploader_releaseProvider));
        this.providesCdsSinglePartUploader$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidesCdsSinglePartUploader$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.providesCDCClient$AndroidPhotosUploader_releaseProvider, this.providesCdsUploadErrorResolver$AndroidPhotosUploader_releaseProvider, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.providesParentNodeFetcher$AndroidPhotosUploader_releaseProvider));
        this.providePartInfoDao$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(MultipartDatabaseModule_ProvidePartInfoDao$AndroidPhotosUploader_releaseFactory.create(multipartDatabaseModule));
        this.provideContentSignatureProvider$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideContentSignatureProvider$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider2 = DoubleCheck.provider(MultipartDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory.create(multipartDatabaseModule, this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider));
        this.providePartProvider$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidePartProvider$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideMultipartUploadInitiator$AndroidPhotosUploader_releaseProvider = CdsUploaderConfigurationModule_ProvideMultipartUploadInitiator$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.providesCDCClient$AndroidPhotosUploader_releaseProvider, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.providesParentNodeFetcher$AndroidPhotosUploader_releaseProvider, this.providesCdsUploadErrorResolver$AndroidPhotosUploader_releaseProvider, this.provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseProvider, this.providePartInfoDao$AndroidPhotosUploader_releaseProvider, this.provideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_releaseProvider);
        this.providesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.providePartUploader$AndroidPhotosUploader_releaseProvider = CdsUploaderConfigurationModule_ProvidePartUploader$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.providesCDCClient$AndroidPhotosUploader_releaseProvider, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.providePartInfoDao$AndroidPhotosUploader_releaseProvider, this.provideContentSignatureProvider$AndroidPhotosUploader_releaseProvider, this.providePartProvider$AndroidPhotosUploader_releaseProvider, this.providesCdsUploadPartErrorResolver$AndroidPhotosUploader_releaseProvider);
        this.provideMultipartUploadCompleter$AndroidPhotosUploader_releaseProvider = CdsUploaderConfigurationModule_ProvideMultipartUploadCompleter$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.providesCDCClient$AndroidPhotosUploader_releaseProvider, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.providesCdsUploadErrorResolver$AndroidPhotosUploader_releaseProvider, this.providePartInfoDao$AndroidPhotosUploader_releaseProvider);
        this.provideMultipartUploadVerifier$AndroidPhotosUploader_releaseProvider = CdsUploaderConfigurationModule_ProvideMultipartUploadVerifier$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.providesCDCClient$AndroidPhotosUploader_releaseProvider, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.providePartInfoDao$AndroidPhotosUploader_releaseProvider);
        this.provideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseProvider = CdsUploaderConfigurationModule_ProvideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.providesCDCClient$AndroidPhotosUploader_releaseProvider, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.providesCdsUploadErrorResolver$AndroidPhotosUploader_releaseProvider, this.providesParentNodeFetcher$AndroidPhotosUploader_releaseProvider);
        this.provideMultipartTransactionRunner$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(MultipartDatabaseModule_ProvideMultipartTransactionRunner$AndroidPhotosUploader_releaseFactory.create(multipartDatabaseModule));
        this.providesCdsMultiPartUploader$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvidesCdsMultiPartUploader$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule, this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider, this.providesCDCClient$AndroidPhotosUploader_releaseProvider, this.providesCdsUploadErrorResolver$AndroidPhotosUploader_releaseProvider, this.provideMetrics$AndroidPhotosUploader_releaseProvider, this.providesParentNodeFetcher$AndroidPhotosUploader_releaseProvider, this.provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseProvider, this.providePartInfoDao$AndroidPhotosUploader_releaseProvider, this.provideContentSignatureProvider$AndroidPhotosUploader_releaseProvider, this.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseProvider2, this.providePartProvider$AndroidPhotosUploader_releaseProvider, this.provideMultipartUploadInitiator$AndroidPhotosUploader_releaseProvider, this.providePartUploader$AndroidPhotosUploader_releaseProvider, this.provideMultipartUploadCompleter$AndroidPhotosUploader_releaseProvider, this.provideMultipartUploadVerifier$AndroidPhotosUploader_releaseProvider, this.provideMultipartUploadNodeFetcher$AndroidPhotosUploader_releaseProvider, this.provideMultipartTransactionRunner$AndroidPhotosUploader_releaseProvider));
        this.provideFileUtils$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideFileUtils$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
        this.provideUploadLogger$AndroidPhotosUploader_releaseProvider = DoubleCheck.provider(CdsUploaderConfigurationModule_ProvideUploadLogger$AndroidPhotosUploader_releaseFactory.create(cdsUploaderConfigurationModule));
    }

    @CanIgnoreReturnValue
    private CdsUploader injectCdsUploader(CdsUploader cdsUploader) {
        cdsUploader.setSinglePartUploader$AndroidPhotosUploader_release(this.providesCdsSinglePartUploader$AndroidPhotosUploader_releaseProvider.mo10268get());
        cdsUploader.setMultiPartUploader$AndroidPhotosUploader_release(this.providesCdsMultiPartUploader$AndroidPhotosUploader_releaseProvider.mo10268get());
        cdsUploader.setUploadFrameworkContext$AndroidPhotosUploader_release(this.provideUploadFrameworkContext$AndroidPhotosUploader_releaseProvider.mo10268get());
        cdsUploader.setMetrics$AndroidPhotosUploader_release(this.provideMetrics$AndroidPhotosUploader_releaseProvider.mo10268get());
        cdsUploader.setFileUtils$AndroidPhotosUploader_release(this.provideFileUtils$AndroidPhotosUploader_releaseProvider.mo10268get());
        cdsUploader.setParentIdCache$AndroidPhotosUploader_release(this.provideParentIdCache$AndroidPhotosUploader_releaseProvider.mo10268get());
        cdsUploader.setLogger$AndroidPhotosUploader_release(this.provideUploadLogger$AndroidPhotosUploader_releaseProvider.mo10268get());
        return cdsUploader;
    }

    @Override // com.amazon.photos.uploader.internal.dagger.component.CdsUploaderComponent
    public void inject(CdsUploader cdsUploader) {
        injectCdsUploader(cdsUploader);
    }

    private DaggerCdsUploaderComponent(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, MultipartDatabaseModule multipartDatabaseModule, NodeCacheDatabaseModule nodeCacheDatabaseModule) {
        initialize(cdsUploaderConfigurationModule, multipartDatabaseModule, nodeCacheDatabaseModule);
    }
}
