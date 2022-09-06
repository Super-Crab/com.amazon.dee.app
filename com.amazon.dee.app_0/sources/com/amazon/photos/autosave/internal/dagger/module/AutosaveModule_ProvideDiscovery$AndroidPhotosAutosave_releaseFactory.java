package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.discovery.Discovery;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideDiscovery$AndroidPhotosAutosave_releaseFactory implements Factory<Discovery> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideDiscovery$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideDiscovery$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideDiscovery$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static Discovery provideDiscovery$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (Discovery) Preconditions.checkNotNull(autosaveModule.provideDiscovery$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Discovery mo10268get() {
        return provideDiscovery$AndroidPhotosAutosave_release(this.module);
    }
}
