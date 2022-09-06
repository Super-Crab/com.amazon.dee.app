package com.amazon.photos.uploader.cds.multipart;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class MultipartDatabaseModule_ProvideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseFactory implements Factory<MultipartUploadRequestMetadataDao> {
    private final MultipartDatabaseModule module;

    public MultipartDatabaseModule_ProvideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseFactory(MultipartDatabaseModule multipartDatabaseModule) {
        this.module = multipartDatabaseModule;
    }

    public static MultipartDatabaseModule_ProvideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseFactory create(MultipartDatabaseModule multipartDatabaseModule) {
        return new MultipartDatabaseModule_ProvideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_releaseFactory(multipartDatabaseModule);
    }

    public static MultipartUploadRequestMetadataDao provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_release(MultipartDatabaseModule multipartDatabaseModule) {
        return (MultipartUploadRequestMetadataDao) Preconditions.checkNotNull(multipartDatabaseModule.provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MultipartUploadRequestMetadataDao mo10268get() {
        return provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_release(this.module);
    }
}
