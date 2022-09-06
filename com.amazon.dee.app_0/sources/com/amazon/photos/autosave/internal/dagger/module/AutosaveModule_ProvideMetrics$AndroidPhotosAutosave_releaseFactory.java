package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideMetrics$AndroidPhotosAutosave_releaseFactory implements Factory<Metrics> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideMetrics$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideMetrics$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideMetrics$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static Metrics provideMetrics$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (Metrics) Preconditions.checkNotNull(autosaveModule.provideMetrics$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Metrics mo10268get() {
        return provideMetrics$AndroidPhotosAutosave_release(this.module);
    }
}
