package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.coroutines.DispatcherProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideDispatcherProvider$AndroidPhotosAutosave_releaseFactory implements Factory<DispatcherProvider> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideDispatcherProvider$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideDispatcherProvider$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideDispatcherProvider$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static DispatcherProvider provideDispatcherProvider$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (DispatcherProvider) Preconditions.checkNotNull(autosaveModule.provideDispatcherProvider$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DispatcherProvider mo10268get() {
        return provideDispatcherProvider$AndroidPhotosAutosave_release(this.module);
    }
}
