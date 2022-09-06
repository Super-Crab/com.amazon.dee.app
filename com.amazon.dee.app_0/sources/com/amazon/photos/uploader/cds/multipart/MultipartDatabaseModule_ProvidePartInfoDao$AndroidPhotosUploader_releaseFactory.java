package com.amazon.photos.uploader.cds.multipart;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class MultipartDatabaseModule_ProvidePartInfoDao$AndroidPhotosUploader_releaseFactory implements Factory<PartInfoDao> {
    private final MultipartDatabaseModule module;

    public MultipartDatabaseModule_ProvidePartInfoDao$AndroidPhotosUploader_releaseFactory(MultipartDatabaseModule multipartDatabaseModule) {
        this.module = multipartDatabaseModule;
    }

    public static MultipartDatabaseModule_ProvidePartInfoDao$AndroidPhotosUploader_releaseFactory create(MultipartDatabaseModule multipartDatabaseModule) {
        return new MultipartDatabaseModule_ProvidePartInfoDao$AndroidPhotosUploader_releaseFactory(multipartDatabaseModule);
    }

    public static PartInfoDao providePartInfoDao$AndroidPhotosUploader_release(MultipartDatabaseModule multipartDatabaseModule) {
        return (PartInfoDao) Preconditions.checkNotNull(multipartDatabaseModule.providePartInfoDao$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PartInfoDao mo10268get() {
        return providePartInfoDao$AndroidPhotosUploader_release(this.module);
    }
}
