package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideTransactionRunner$AndroidPhotosAutosave_releaseFactory implements Factory<AutosaveTransactionRunner> {
    private final DatabaseModule module;

    public DatabaseModule_ProvideTransactionRunner$AndroidPhotosAutosave_releaseFactory(DatabaseModule databaseModule) {
        this.module = databaseModule;
    }

    public static DatabaseModule_ProvideTransactionRunner$AndroidPhotosAutosave_releaseFactory create(DatabaseModule databaseModule) {
        return new DatabaseModule_ProvideTransactionRunner$AndroidPhotosAutosave_releaseFactory(databaseModule);
    }

    public static AutosaveTransactionRunner provideTransactionRunner$AndroidPhotosAutosave_release(DatabaseModule databaseModule) {
        return (AutosaveTransactionRunner) Preconditions.checkNotNull(databaseModule.provideTransactionRunner$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosaveTransactionRunner mo10268get() {
        return provideTransactionRunner$AndroidPhotosAutosave_release(this.module);
    }
}
