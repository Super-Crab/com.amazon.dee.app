package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.UploaderTransactionRunner;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideTransactionRunner$AndroidPhotosUploader_releaseFactory implements Factory<UploaderTransactionRunner> {
    private final DatabaseModule module;

    public DatabaseModule_ProvideTransactionRunner$AndroidPhotosUploader_releaseFactory(DatabaseModule databaseModule) {
        this.module = databaseModule;
    }

    public static DatabaseModule_ProvideTransactionRunner$AndroidPhotosUploader_releaseFactory create(DatabaseModule databaseModule) {
        return new DatabaseModule_ProvideTransactionRunner$AndroidPhotosUploader_releaseFactory(databaseModule);
    }

    public static UploaderTransactionRunner provideTransactionRunner$AndroidPhotosUploader_release(DatabaseModule databaseModule) {
        return (UploaderTransactionRunner) Preconditions.checkNotNull(databaseModule.provideTransactionRunner$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploaderTransactionRunner mo10268get() {
        return provideTransactionRunner$AndroidPhotosUploader_release(this.module);
    }
}
