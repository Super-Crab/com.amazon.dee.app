package com.amazon.photos.uploader.cds.multipart;

import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MultipartDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory implements Factory<DestroyableDatabaseWrapper> {
    private final Provider<UploadFrameworkContext> frameworkContextProvider;
    private final MultipartDatabaseModule module;

    public MultipartDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory(MultipartDatabaseModule multipartDatabaseModule, Provider<UploadFrameworkContext> provider) {
        this.module = multipartDatabaseModule;
        this.frameworkContextProvider = provider;
    }

    public static MultipartDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory create(MultipartDatabaseModule multipartDatabaseModule, Provider<UploadFrameworkContext> provider) {
        return new MultipartDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory(multipartDatabaseModule, provider);
    }

    public static DestroyableDatabaseWrapper provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(MultipartDatabaseModule multipartDatabaseModule, UploadFrameworkContext uploadFrameworkContext) {
        return (DestroyableDatabaseWrapper) Preconditions.checkNotNull(multipartDatabaseModule.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(uploadFrameworkContext), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DestroyableDatabaseWrapper mo10268get() {
        return provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(this.module, this.frameworkContextProvider.mo10268get());
    }
}
