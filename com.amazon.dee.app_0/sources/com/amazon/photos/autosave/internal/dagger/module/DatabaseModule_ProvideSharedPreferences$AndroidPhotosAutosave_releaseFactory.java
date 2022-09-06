package com.amazon.photos.autosave.internal.dagger.module;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideSharedPreferences$AndroidPhotosAutosave_releaseFactory implements Factory<SharedPreferences> {
    private final DatabaseModule module;

    public DatabaseModule_ProvideSharedPreferences$AndroidPhotosAutosave_releaseFactory(DatabaseModule databaseModule) {
        this.module = databaseModule;
    }

    public static DatabaseModule_ProvideSharedPreferences$AndroidPhotosAutosave_releaseFactory create(DatabaseModule databaseModule) {
        return new DatabaseModule_ProvideSharedPreferences$AndroidPhotosAutosave_releaseFactory(databaseModule);
    }

    public static SharedPreferences provideSharedPreferences$AndroidPhotosAutosave_release(DatabaseModule databaseModule) {
        return (SharedPreferences) Preconditions.checkNotNull(databaseModule.provideSharedPreferences$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SharedPreferences mo10268get() {
        return provideSharedPreferences$AndroidPhotosAutosave_release(this.module);
    }
}
