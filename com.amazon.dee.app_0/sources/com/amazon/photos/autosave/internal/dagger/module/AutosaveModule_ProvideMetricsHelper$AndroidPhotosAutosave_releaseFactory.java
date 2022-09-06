package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.metrics.MetricsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideMetricsHelper$AndroidPhotosAutosave_releaseFactory implements Factory<MetricsHelper> {
    private final Provider<Metrics> metricsProvider;
    private final AutosaveModule module;

    public AutosaveModule_ProvideMetricsHelper$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule, Provider<Metrics> provider) {
        this.module = autosaveModule;
        this.metricsProvider = provider;
    }

    public static AutosaveModule_ProvideMetricsHelper$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule, Provider<Metrics> provider) {
        return new AutosaveModule_ProvideMetricsHelper$AndroidPhotosAutosave_releaseFactory(autosaveModule, provider);
    }

    public static MetricsHelper provideMetricsHelper$AndroidPhotosAutosave_release(AutosaveModule autosaveModule, Metrics metrics) {
        return (MetricsHelper) Preconditions.checkNotNull(autosaveModule.provideMetricsHelper$AndroidPhotosAutosave_release(metrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsHelper mo10268get() {
        return provideMetricsHelper$AndroidPhotosAutosave_release(this.module, this.metricsProvider.mo10268get());
    }
}
