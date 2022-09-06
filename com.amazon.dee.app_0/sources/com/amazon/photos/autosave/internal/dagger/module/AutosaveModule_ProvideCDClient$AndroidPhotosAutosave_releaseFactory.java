package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.clouddrive.cdasdk.CDClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideCDClient$AndroidPhotosAutosave_releaseFactory implements Factory<CDClient> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideCDClient$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideCDClient$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideCDClient$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static CDClient provideCDClient$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (CDClient) Preconditions.checkNotNull(autosaveModule.provideCDClient$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CDClient mo10268get() {
        return provideCDClient$AndroidPhotosAutosave_release(this.module);
    }
}
