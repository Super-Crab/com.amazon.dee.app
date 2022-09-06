package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideAutosaveEventNotifier$AndroidPhotosAutosave_releaseFactory implements Factory<AutosaveEventNotifier> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideAutosaveEventNotifier$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideAutosaveEventNotifier$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideAutosaveEventNotifier$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static AutosaveEventNotifier provideAutosaveEventNotifier$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (AutosaveEventNotifier) Preconditions.checkNotNull(autosaveModule.provideAutosaveEventNotifier$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosaveEventNotifier mo10268get() {
        return provideAutosaveEventNotifier$AndroidPhotosAutosave_release(this.module);
    }
}
