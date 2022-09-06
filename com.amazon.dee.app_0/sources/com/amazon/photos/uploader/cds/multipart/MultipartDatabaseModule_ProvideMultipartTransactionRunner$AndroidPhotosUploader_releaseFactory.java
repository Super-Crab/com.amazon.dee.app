package com.amazon.photos.uploader.cds.multipart;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class MultipartDatabaseModule_ProvideMultipartTransactionRunner$AndroidPhotosUploader_releaseFactory implements Factory<MultipartTransactionRunner> {
    private final MultipartDatabaseModule module;

    public MultipartDatabaseModule_ProvideMultipartTransactionRunner$AndroidPhotosUploader_releaseFactory(MultipartDatabaseModule multipartDatabaseModule) {
        this.module = multipartDatabaseModule;
    }

    public static MultipartDatabaseModule_ProvideMultipartTransactionRunner$AndroidPhotosUploader_releaseFactory create(MultipartDatabaseModule multipartDatabaseModule) {
        return new MultipartDatabaseModule_ProvideMultipartTransactionRunner$AndroidPhotosUploader_releaseFactory(multipartDatabaseModule);
    }

    public static MultipartTransactionRunner provideMultipartTransactionRunner$AndroidPhotosUploader_release(MultipartDatabaseModule multipartDatabaseModule) {
        return (MultipartTransactionRunner) Preconditions.checkNotNull(multipartDatabaseModule.provideMultipartTransactionRunner$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MultipartTransactionRunner mo10268get() {
        return provideMultipartTransactionRunner$AndroidPhotosUploader_release(this.module);
    }
}
