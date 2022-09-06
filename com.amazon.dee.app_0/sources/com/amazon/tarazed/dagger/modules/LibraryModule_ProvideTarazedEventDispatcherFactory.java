package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideTarazedEventDispatcherFactory implements Factory<TarazedEventDispatcher> {
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final LibraryModule module;

    public LibraryModule_ProvideTarazedEventDispatcherFactory(LibraryModule libraryModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        this.module = libraryModule;
        this.loggerProvider = provider;
        this.metricsHelperProvider = provider2;
    }

    public static LibraryModule_ProvideTarazedEventDispatcherFactory create(LibraryModule libraryModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        return new LibraryModule_ProvideTarazedEventDispatcherFactory(libraryModule, provider, provider2);
    }

    public static TarazedEventDispatcher provideInstance(LibraryModule libraryModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        return proxyProvideTarazedEventDispatcher(libraryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static TarazedEventDispatcher proxyProvideTarazedEventDispatcher(LibraryModule libraryModule, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return (TarazedEventDispatcher) Preconditions.checkNotNull(libraryModule.provideTarazedEventDispatcher(tarazedSessionLogger, tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedEventDispatcher mo10268get() {
        return provideInstance(this.module, this.loggerProvider, this.metricsHelperProvider);
    }
}
