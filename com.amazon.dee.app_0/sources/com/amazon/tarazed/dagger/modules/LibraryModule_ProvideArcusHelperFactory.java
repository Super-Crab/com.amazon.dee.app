package com.amazon.tarazed.dagger.modules;

import androidx.work.WorkManager;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideArcusHelperFactory implements Factory<ArcusHelper> {
    private final Provider<TarazedLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsProvider;
    private final LibraryModule module;
    private final Provider<RemoteConfigurationManager> remoteConfigurationManagerProvider;
    private final Provider<WorkManager> workManagerProvider;

    public LibraryModule_ProvideArcusHelperFactory(LibraryModule libraryModule, Provider<WorkManager> provider, Provider<RemoteConfigurationManager> provider2, Provider<TarazedLogger> provider3, Provider<TarazedMetricsHelper> provider4) {
        this.module = libraryModule;
        this.workManagerProvider = provider;
        this.remoteConfigurationManagerProvider = provider2;
        this.loggerProvider = provider3;
        this.metricsProvider = provider4;
    }

    public static LibraryModule_ProvideArcusHelperFactory create(LibraryModule libraryModule, Provider<WorkManager> provider, Provider<RemoteConfigurationManager> provider2, Provider<TarazedLogger> provider3, Provider<TarazedMetricsHelper> provider4) {
        return new LibraryModule_ProvideArcusHelperFactory(libraryModule, provider, provider2, provider3, provider4);
    }

    public static ArcusHelper provideInstance(LibraryModule libraryModule, Provider<WorkManager> provider, Provider<RemoteConfigurationManager> provider2, Provider<TarazedLogger> provider3, Provider<TarazedMetricsHelper> provider4) {
        return proxyProvideArcusHelper(libraryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static ArcusHelper proxyProvideArcusHelper(LibraryModule libraryModule, WorkManager workManager, RemoteConfigurationManager remoteConfigurationManager, TarazedLogger tarazedLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return (ArcusHelper) Preconditions.checkNotNull(libraryModule.provideArcusHelper(workManager, remoteConfigurationManager, tarazedLogger, tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ArcusHelper mo10268get() {
        return provideInstance(this.module, this.workManagerProvider, this.remoteConfigurationManagerProvider, this.loggerProvider, this.metricsProvider);
    }
}
