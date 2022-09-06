package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideLogger$AndroidPhotosAutosave_releaseFactory implements Factory<Logger> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideLogger$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideLogger$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideLogger$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static Logger provideLogger$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (Logger) Preconditions.checkNotNull(autosaveModule.provideLogger$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Logger mo10268get() {
        return provideLogger$AndroidPhotosAutosave_release(this.module);
    }
}
