package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideAutosaveBucketDao$AndroidPhotosAutosave_releaseFactory implements Factory<AutosaveBucketDao> {
    private final DatabaseModule module;

    public DatabaseModule_ProvideAutosaveBucketDao$AndroidPhotosAutosave_releaseFactory(DatabaseModule databaseModule) {
        this.module = databaseModule;
    }

    public static DatabaseModule_ProvideAutosaveBucketDao$AndroidPhotosAutosave_releaseFactory create(DatabaseModule databaseModule) {
        return new DatabaseModule_ProvideAutosaveBucketDao$AndroidPhotosAutosave_releaseFactory(databaseModule);
    }

    public static AutosaveBucketDao provideAutosaveBucketDao$AndroidPhotosAutosave_release(DatabaseModule databaseModule) {
        return (AutosaveBucketDao) Preconditions.checkNotNull(databaseModule.provideAutosaveBucketDao$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosaveBucketDao mo10268get() {
        return provideAutosaveBucketDao$AndroidPhotosAutosave_release(this.module);
    }
}
