package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.metrics.AutosaveLatencyRecorder;
import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import com.amazon.photos.autosave.internal.utils.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseFactory implements Factory<AutosaveLatencyRecorder> {
    private final Provider<AutosaveEventNotifier> autosaveEventNotifierProvider;
    private final Provider<Metrics> metricsProvider;
    private final AutosaveModule module;
    private final Provider<SystemUtil> systemUtilProvider;

    public AutosaveModule_ProvideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule, Provider<Metrics> provider, Provider<SystemUtil> provider2, Provider<AutosaveEventNotifier> provider3) {
        this.module = autosaveModule;
        this.metricsProvider = provider;
        this.systemUtilProvider = provider2;
        this.autosaveEventNotifierProvider = provider3;
    }

    public static AutosaveModule_ProvideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule, Provider<Metrics> provider, Provider<SystemUtil> provider2, Provider<AutosaveEventNotifier> provider3) {
        return new AutosaveModule_ProvideAutosaveLatencyRecorder$AndroidPhotosAutosave_releaseFactory(autosaveModule, provider, provider2, provider3);
    }

    public static AutosaveLatencyRecorder provideAutosaveLatencyRecorder$AndroidPhotosAutosave_release(AutosaveModule autosaveModule, Metrics metrics, SystemUtil systemUtil, AutosaveEventNotifier autosaveEventNotifier) {
        return (AutosaveLatencyRecorder) Preconditions.checkNotNull(autosaveModule.provideAutosaveLatencyRecorder$AndroidPhotosAutosave_release(metrics, systemUtil, autosaveEventNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutosaveLatencyRecorder mo10268get() {
        return provideAutosaveLatencyRecorder$AndroidPhotosAutosave_release(this.module, this.metricsProvider.mo10268get(), this.systemUtilProvider.mo10268get(), this.autosaveEventNotifierProvider.mo10268get());
    }
}
