package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.AutosaveFrameworkContext;
import com.amazon.photos.autosave.internal.db.DestroyableDatabaseWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseFactory implements Factory<DestroyableDatabaseWrapper> {
    private final Provider<AutosaveFrameworkContext> frameworkContextProvider;
    private final DatabaseModule module;

    public DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseFactory(DatabaseModule databaseModule, Provider<AutosaveFrameworkContext> provider) {
        this.module = databaseModule;
        this.frameworkContextProvider = provider;
    }

    public static DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseFactory create(DatabaseModule databaseModule, Provider<AutosaveFrameworkContext> provider) {
        return new DatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosAutosave_releaseFactory(databaseModule, provider);
    }

    public static DestroyableDatabaseWrapper provideDestroyableDatabaseWrapper$AndroidPhotosAutosave_release(DatabaseModule databaseModule, AutosaveFrameworkContext autosaveFrameworkContext) {
        return (DestroyableDatabaseWrapper) Preconditions.checkNotNull(databaseModule.provideDestroyableDatabaseWrapper$AndroidPhotosAutosave_release(autosaveFrameworkContext), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DestroyableDatabaseWrapper mo10268get() {
        return provideDestroyableDatabaseWrapper$AndroidPhotosAutosave_release(this.module, this.frameworkContextProvider.mo10268get());
    }
}
