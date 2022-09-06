package com.amazon.photos.autosave.internal.dagger.module;

import android.content.SharedPreferences;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.coroutines.DispatcherProvider;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import com.amazon.photos.autosave.internal.preferences.AutosavePreferenceChangeListener;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.autosave.internal.preferences.PreferenceUploadQueueHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideAutosavePreferences$AndroidPhotosAutosave_releaseFactory implements Factory<InternalPreferences> {
    private final Provider<AutosaveBucketDao> autosaveBucketDaoProvider;
    private final Provider<AutosaveEventNotifier> autosaveEventNotifierProvider;
    private final Provider<AutosaveOperations> autosaveOperationsProvider;
    private final Provider<DispatcherProvider> dispatcherProvider;
    private final AutosaveModule module;
    private final Provider<AutosavePreferenceChangeListener> preferenceChangeListenerProvider;
    private final Provider<PreferenceUploadQueueHelper> queueHelperProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public AutosaveModule_ProvideAutosavePreferences$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule, Provider<AutosaveBucketDao> provider, Provider<AutosavePreferenceChangeListener> provider2, Provider<AutosaveOperations> provider3, Provider<DispatcherProvider> provider4, Provider<PreferenceUploadQueueHelper> provider5, Provider<SharedPreferences> provider6, Provider<AutosaveEventNotifier> provider7) {
        this.module = autosaveModule;
        this.autosaveBucketDaoProvider = provider;
        this.preferenceChangeListenerProvider = provider2;
        this.autosaveOperationsProvider = provider3;
        this.dispatcherProvider = provider4;
        this.queueHelperProvider = provider5;
        this.sharedPreferencesProvider = provider6;
        this.autosaveEventNotifierProvider = provider7;
    }

    public static AutosaveModule_ProvideAutosavePreferences$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule, Provider<AutosaveBucketDao> provider, Provider<AutosavePreferenceChangeListener> provider2, Provider<AutosaveOperations> provider3, Provider<DispatcherProvider> provider4, Provider<PreferenceUploadQueueHelper> provider5, Provider<SharedPreferences> provider6, Provider<AutosaveEventNotifier> provider7) {
        return new AutosaveModule_ProvideAutosavePreferences$AndroidPhotosAutosave_releaseFactory(autosaveModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static InternalPreferences provideAutosavePreferences$AndroidPhotosAutosave_release(AutosaveModule autosaveModule, AutosaveBucketDao autosaveBucketDao, AutosavePreferenceChangeListener autosavePreferenceChangeListener, AutosaveOperations autosaveOperations, DispatcherProvider dispatcherProvider, PreferenceUploadQueueHelper preferenceUploadQueueHelper, SharedPreferences sharedPreferences, AutosaveEventNotifier autosaveEventNotifier) {
        return (InternalPreferences) Preconditions.checkNotNull(autosaveModule.provideAutosavePreferences$AndroidPhotosAutosave_release(autosaveBucketDao, autosavePreferenceChangeListener, autosaveOperations, dispatcherProvider, preferenceUploadQueueHelper, sharedPreferences, autosaveEventNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public InternalPreferences mo10268get() {
        return provideAutosavePreferences$AndroidPhotosAutosave_release(this.module, this.autosaveBucketDaoProvider.mo10268get(), this.preferenceChangeListenerProvider.mo10268get(), this.autosaveOperationsProvider.mo10268get(), this.dispatcherProvider.mo10268get(), this.queueHelperProvider.mo10268get(), this.sharedPreferencesProvider.mo10268get(), this.autosaveEventNotifierProvider.mo10268get());
    }
}
