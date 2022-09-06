package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.cds.CdsSinglePartUploader;
import com.amazon.photos.uploader.cds.ParentNodeFetcher;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesCdsSinglePartUploader$AndroidPhotosUploader_releaseFactory implements Factory<CdsSinglePartUploader> {
    private final Provider<CDClient> cdClientProvider;
    private final Provider<CdsUploadErrorResolver> cdsErrorResolverProvider;
    private final Provider<Metrics> metricsProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<ParentNodeFetcher> parentNodeFetcherProvider;

    public CdsUploaderConfigurationModule_ProvidesCdsSinglePartUploader$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<CdsUploadErrorResolver> provider2, Provider<Metrics> provider3, Provider<ParentNodeFetcher> provider4) {
        this.module = cdsUploaderConfigurationModule;
        this.cdClientProvider = provider;
        this.cdsErrorResolverProvider = provider2;
        this.metricsProvider = provider3;
        this.parentNodeFetcherProvider = provider4;
    }

    public static CdsUploaderConfigurationModule_ProvidesCdsSinglePartUploader$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CDClient> provider, Provider<CdsUploadErrorResolver> provider2, Provider<Metrics> provider3, Provider<ParentNodeFetcher> provider4) {
        return new CdsUploaderConfigurationModule_ProvidesCdsSinglePartUploader$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3, provider4);
    }

    public static CdsSinglePartUploader providesCdsSinglePartUploader$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, CDClient cDClient, CdsUploadErrorResolver cdsUploadErrorResolver, Metrics metrics, ParentNodeFetcher parentNodeFetcher) {
        return (CdsSinglePartUploader) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesCdsSinglePartUploader$AndroidPhotosUploader_release(cDClient, cdsUploadErrorResolver, metrics, parentNodeFetcher), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CdsSinglePartUploader mo10268get() {
        return providesCdsSinglePartUploader$AndroidPhotosUploader_release(this.module, this.cdClientProvider.mo10268get(), this.cdsErrorResolverProvider.mo10268get(), this.metricsProvider.mo10268get(), this.parentNodeFetcherProvider.mo10268get());
    }
}
