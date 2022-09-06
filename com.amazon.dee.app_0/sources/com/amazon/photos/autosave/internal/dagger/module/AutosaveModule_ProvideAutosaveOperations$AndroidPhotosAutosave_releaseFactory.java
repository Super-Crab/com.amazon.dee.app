package com.amazon.photos.autosave.internal.dagger.module;

import androidx.work.WorkManager;
import com.amazon.photos.autosave.AutosaveFrameworkContext;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.discovery.Discovery;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideAutosaveOperations$AndroidPhotosAutosave_releaseFactory implements Factory<AutosaveOperations> {
    private final Provider<AutosaveFrameworkContext> autosaveFrameworkContextProvider;
    private final Provider<Discovery> discoveryProvider;
    private final AutosaveModule module;
    private final Provider<WorkManager> workManagerProvider;

    public AutosaveModule_ProvideAutosaveOperations$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule, Provider<WorkManager> provider, Provider<AutosaveFrameworkContext> provider2, Provider<Discovery> provider3) {
        this.module = autosaveModule;
        this.workManagerProvider = provider;
        this.autosaveFrameworkContextProvider = provider2;
        this.discoveryProvider = provider3;
    }

    public static AutosaveModule_ProvideAutosaveOperations$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule, Provider<WorkManager> provider, Provider<AutosaveFrameworkContext> provider2, Provider<Discovery> provider3) {
        return new AutosaveModule_ProvideAutosaveOperations$AndroidPhotosAutosave_releaseFactory(autosaveModule, provider, provider2, provider3);
    }

    public static AutosaveOperations provideAutosaveOperations$AndroidPhotosAutosave_release(AutosaveModule autosaveModule, WorkManager workManager, AutosaveFrameworkContext autosaveFrameworkContext, Discovery discovery) {
        return (AutosaveOperations) Preconditions.checkNotNull(autosaveModule.provideAutosaveOperations$AndroidPhotosAutosave_release(workManager, autosaveFrameworkContext, discovery), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosaveOperations mo10268get() {
        return provideAutosaveOperations$AndroidPhotosAutosave_release(this.module, this.workManagerProvider.mo10268get(), this.autosaveFrameworkContextProvider.mo10268get(), this.discoveryProvider.mo10268get());
    }
}
