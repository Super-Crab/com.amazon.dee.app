package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.autosave.internal.coroutines.DispatcherProvider;
import com.amazon.photos.autosave.internal.preferences.AutosavePreferenceChangeListener;
import com.amazon.photos.autosave.internal.preferences.PreferenceUploadQueueHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideAutosavePreferencesListener$AndroidPhotosAutosave_releaseFactory implements Factory<AutosavePreferenceChangeListener> {
    private final Provider<DispatcherProvider> dispatcherProvider;
    private final AutosaveModule module;
    private final Provider<PreferenceUploadQueueHelper> queueHelperProvider;

    public AutosaveModule_ProvideAutosavePreferencesListener$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule, Provider<DispatcherProvider> provider, Provider<PreferenceUploadQueueHelper> provider2) {
        this.module = autosaveModule;
        this.dispatcherProvider = provider;
        this.queueHelperProvider = provider2;
    }

    public static AutosaveModule_ProvideAutosavePreferencesListener$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule, Provider<DispatcherProvider> provider, Provider<PreferenceUploadQueueHelper> provider2) {
        return new AutosaveModule_ProvideAutosavePreferencesListener$AndroidPhotosAutosave_releaseFactory(autosaveModule, provider, provider2);
    }

    public static AutosavePreferenceChangeListener provideAutosavePreferencesListener$AndroidPhotosAutosave_release(AutosaveModule autosaveModule, DispatcherProvider dispatcherProvider, PreferenceUploadQueueHelper preferenceUploadQueueHelper) {
        return (AutosavePreferenceChangeListener) Preconditions.checkNotNull(autosaveModule.provideAutosavePreferencesListener$AndroidPhotosAutosave_release(dispatcherProvider, preferenceUploadQueueHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosavePreferenceChangeListener mo10268get() {
        return provideAutosavePreferencesListener$AndroidPhotosAutosave_release(this.module, this.dispatcherProvider.mo10268get(), this.queueHelperProvider.mo10268get());
    }
}
