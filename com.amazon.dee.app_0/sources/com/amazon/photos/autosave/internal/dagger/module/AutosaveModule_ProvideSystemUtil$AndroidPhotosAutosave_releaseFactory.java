package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.utils.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideSystemUtil$AndroidPhotosAutosave_releaseFactory implements Factory<SystemUtil> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideSystemUtil$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideSystemUtil$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideSystemUtil$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static SystemUtil provideSystemUtil$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (SystemUtil) Preconditions.checkNotNull(autosaveModule.provideSystemUtil$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SystemUtil mo10268get() {
        return provideSystemUtil$AndroidPhotosAutosave_release(this.module);
    }
}
