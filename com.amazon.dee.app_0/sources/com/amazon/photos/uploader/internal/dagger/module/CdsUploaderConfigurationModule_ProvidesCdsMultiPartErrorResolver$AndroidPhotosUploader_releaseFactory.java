package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.CdsUtil;
import com.amazon.photos.uploader.cds.error.MultiPartUploadErrorResolver;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseFactory implements Factory<MultiPartUploadErrorResolver> {
    private final Provider<CdsUtil> cdsUtilProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<MultipartUploadRequestMetadataDao> multipartUploadRequestMetadataDaoProvider;

    public CdsUploaderConfigurationModule_ProvidesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<MultipartUploadRequestMetadataDao> provider, Provider<CdsUtil> provider2) {
        this.module = cdsUploaderConfigurationModule;
        this.multipartUploadRequestMetadataDaoProvider = provider;
        this.cdsUtilProvider = provider2;
    }

    public static CdsUploaderConfigurationModule_ProvidesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<MultipartUploadRequestMetadataDao> provider, Provider<CdsUtil> provider2) {
        return new CdsUploaderConfigurationModule_ProvidesCdsMultiPartErrorResolver$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2);
    }

    public static MultiPartUploadErrorResolver providesCdsMultiPartErrorResolver$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, CdsUtil cdsUtil) {
        return (MultiPartUploadErrorResolver) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesCdsMultiPartErrorResolver$AndroidPhotosUploader_release(multipartUploadRequestMetadataDao, cdsUtil), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MultiPartUploadErrorResolver mo10268get() {
        return providesCdsMultiPartErrorResolver$AndroidPhotosUploader_release(this.module, this.multipartUploadRequestMetadataDaoProvider.mo10268get(), this.cdsUtilProvider.mo10268get());
    }
}
