package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideTarazedIoTManagerFactory implements Factory<TarazedIoTManager> {
    private final Provider<TarazedEventDispatcher> eventDispatcherProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final LibraryModule module;
    private final Provider<TarazedSessionNotifier> sessionNotifierProvider;

    public LibraryModule_ProvideTarazedIoTManagerFactory(LibraryModule libraryModule, Provider<TarazedEventDispatcher> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3, Provider<TarazedSessionNotifier> provider4) {
        this.module = libraryModule;
        this.eventDispatcherProvider = provider;
        this.loggerProvider = provider2;
        this.metricsHelperProvider = provider3;
        this.sessionNotifierProvider = provider4;
    }

    public static LibraryModule_ProvideTarazedIoTManagerFactory create(LibraryModule libraryModule, Provider<TarazedEventDispatcher> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3, Provider<TarazedSessionNotifier> provider4) {
        return new LibraryModule_ProvideTarazedIoTManagerFactory(libraryModule, provider, provider2, provider3, provider4);
    }

    public static TarazedIoTManager provideInstance(LibraryModule libraryModule, Provider<TarazedEventDispatcher> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3, Provider<TarazedSessionNotifier> provider4) {
        return proxyProvideTarazedIoTManager(libraryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static TarazedIoTManager proxyProvideTarazedIoTManager(LibraryModule libraryModule, TarazedEventDispatcher tarazedEventDispatcher, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, TarazedSessionNotifier tarazedSessionNotifier) {
        return (TarazedIoTManager) Preconditions.checkNotNull(libraryModule.provideTarazedIoTManager(tarazedEventDispatcher, tarazedSessionLogger, tarazedMetricsHelper, tarazedSessionNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedIoTManager mo10268get() {
        return provideInstance(this.module, this.eventDispatcherProvider, this.loggerProvider, this.metricsHelperProvider, this.sessionNotifierProvider);
    }
}
