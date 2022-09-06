package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.AutosaveFrameworkContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseFactory implements Factory<AutosaveFrameworkContext> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideAutosaveFrameworkContext$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static AutosaveFrameworkContext provideAutosaveFrameworkContext$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (AutosaveFrameworkContext) Preconditions.checkNotNull(autosaveModule.provideAutosaveFrameworkContext$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosaveFrameworkContext mo10268get() {
        return provideAutosaveFrameworkContext$AndroidPhotosAutosave_release(this.module);
    }
}
