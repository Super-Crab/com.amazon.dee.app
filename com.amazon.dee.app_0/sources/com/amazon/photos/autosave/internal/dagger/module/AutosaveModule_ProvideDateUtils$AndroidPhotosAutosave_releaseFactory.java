package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.utils.DateUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideDateUtils$AndroidPhotosAutosave_releaseFactory implements Factory<DateUtils> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideDateUtils$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideDateUtils$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideDateUtils$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static DateUtils provideDateUtils$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (DateUtils) Preconditions.checkNotNull(autosaveModule.provideDateUtils$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DateUtils mo10268get() {
        return provideDateUtils$AndroidPhotosAutosave_release(this.module);
    }
}
