package com.amazon.photos.autosave.internal.dagger.module;

import androidx.work.WorkManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideWorkManager$AndroidPhotosAutosave_releaseFactory implements Factory<WorkManager> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideWorkManager$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideWorkManager$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideWorkManager$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static WorkManager provideWorkManager$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (WorkManager) Preconditions.checkNotNull(autosaveModule.provideWorkManager$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkManager mo10268get() {
        return provideWorkManager$AndroidPhotosAutosave_release(this.module);
    }
}
