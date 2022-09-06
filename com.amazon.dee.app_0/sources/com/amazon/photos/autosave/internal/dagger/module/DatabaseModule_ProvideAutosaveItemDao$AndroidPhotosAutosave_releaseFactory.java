package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideAutosaveItemDao$AndroidPhotosAutosave_releaseFactory implements Factory<AutosaveItemDao> {
    private final DatabaseModule module;

    public DatabaseModule_ProvideAutosaveItemDao$AndroidPhotosAutosave_releaseFactory(DatabaseModule databaseModule) {
        this.module = databaseModule;
    }

    public static DatabaseModule_ProvideAutosaveItemDao$AndroidPhotosAutosave_releaseFactory create(DatabaseModule databaseModule) {
        return new DatabaseModule_ProvideAutosaveItemDao$AndroidPhotosAutosave_releaseFactory(databaseModule);
    }

    public static AutosaveItemDao provideAutosaveItemDao$AndroidPhotosAutosave_release(DatabaseModule databaseModule) {
        return (AutosaveItemDao) Preconditions.checkNotNull(databaseModule.provideAutosaveItemDao$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosaveItemDao mo10268get() {
        return provideAutosaveItemDao$AndroidPhotosAutosave_release(this.module);
    }
}
