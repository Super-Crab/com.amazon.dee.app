package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.util.AutoModeCommonUtils;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideAutoModeCommonUtilsFactory implements Factory<AutoModeCommonUtils> {
    private final Provider<DriveModeMetricsHelper> driveModeMetricsHelperProvider;
    private final Provider<DriveModeMetrics> driveModeMetricsProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideAutoModeCommonUtilsFactory(RepositoryModule repositoryModule, Provider<DriveModeMetricsHelper> provider, Provider<DriveModeMetrics> provider2) {
        this.module = repositoryModule;
        this.driveModeMetricsHelperProvider = provider;
        this.driveModeMetricsProvider = provider2;
    }

    public static RepositoryModule_ProvideAutoModeCommonUtilsFactory create(RepositoryModule repositoryModule, Provider<DriveModeMetricsHelper> provider, Provider<DriveModeMetrics> provider2) {
        return new RepositoryModule_ProvideAutoModeCommonUtilsFactory(repositoryModule, provider, provider2);
    }

    public static AutoModeCommonUtils provideInstance(RepositoryModule repositoryModule, Provider<DriveModeMetricsHelper> provider, Provider<DriveModeMetrics> provider2) {
        return proxyProvideAutoModeCommonUtils(repositoryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AutoModeCommonUtils proxyProvideAutoModeCommonUtils(RepositoryModule repositoryModule, DriveModeMetricsHelper driveModeMetricsHelper, DriveModeMetrics driveModeMetrics) {
        return (AutoModeCommonUtils) Preconditions.checkNotNull(repositoryModule.provideAutoModeCommonUtils(driveModeMetricsHelper, driveModeMetrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutoModeCommonUtils mo10268get() {
        return provideInstance(this.module, this.driveModeMetricsHelperProvider, this.driveModeMetricsProvider);
    }
}
