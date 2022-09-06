package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory implements Factory<DestroyableDatabaseWrapper> {
    private final Provider<UploadFrameworkContext> frameworkContextProvider;
    private final DatabaseModule module;

    public DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory(DatabaseModule databaseModule, Provider<UploadFrameworkContext> provider) {
        this.module = databaseModule;
        this.frameworkContextProvider = provider;
    }

    public static DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory create(DatabaseModule databaseModule, Provider<UploadFrameworkContext> provider) {
        return new DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory(databaseModule, provider);
    }

    public static DestroyableDatabaseWrapper provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(DatabaseModule databaseModule, UploadFrameworkContext uploadFrameworkContext) {
        return (DestroyableDatabaseWrapper) Preconditions.checkNotNull(databaseModule.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(uploadFrameworkContext), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DestroyableDatabaseWrapper mo10268get() {
        return provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(this.module, this.frameworkContextProvider.mo10268get());
    }
}
