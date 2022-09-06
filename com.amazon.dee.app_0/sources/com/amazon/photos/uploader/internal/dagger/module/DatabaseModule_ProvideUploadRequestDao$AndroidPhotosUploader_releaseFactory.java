package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideUploadRequestDao$AndroidPhotosUploader_releaseFactory implements Factory<UploadRequestDao> {
    private final DatabaseModule module;

    public DatabaseModule_ProvideUploadRequestDao$AndroidPhotosUploader_releaseFactory(DatabaseModule databaseModule) {
        this.module = databaseModule;
    }

    public static DatabaseModule_ProvideUploadRequestDao$AndroidPhotosUploader_releaseFactory create(DatabaseModule databaseModule) {
        return new DatabaseModule_ProvideUploadRequestDao$AndroidPhotosUploader_releaseFactory(databaseModule);
    }

    public static UploadRequestDao provideUploadRequestDao$AndroidPhotosUploader_release(DatabaseModule databaseModule) {
        return (UploadRequestDao) Preconditions.checkNotNull(databaseModule.provideUploadRequestDao$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadRequestDao mo10268get() {
        return provideUploadRequestDao$AndroidPhotosUploader_release(this.module);
    }
}
