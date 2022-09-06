package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleObserver;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideTarazedAppLifeCycleObserverFactory implements Factory<TarazedAppLifeCycleObserver> {
    private final Provider<ArcusHelper> arcusHelperProvider;
    private final Provider<BizMetricsHelper> bizMetricsHelperProvider;
    private final Provider<CoroutineScope> coroutineScopeProvider;
    private final Provider<DispatcherProvider> dispatcherProvider;
    private final Provider<TarazedLogger> loggerProvider;
    private final LibraryModule module;
    private final Provider<TarazedSessionNotifier> notifierProvider;

    public LibraryModule_ProvideTarazedAppLifeCycleObserverFactory(LibraryModule libraryModule, Provider<TarazedLogger> provider, Provider<CoroutineScope> provider2, Provider<DispatcherProvider> provider3, Provider<ArcusHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<BizMetricsHelper> provider6) {
        this.module = libraryModule;
        this.loggerProvider = provider;
        this.coroutineScopeProvider = provider2;
        this.dispatcherProvider = provider3;
        this.arcusHelperProvider = provider4;
        this.notifierProvider = provider5;
        this.bizMetricsHelperProvider = provider6;
    }

    public static LibraryModule_ProvideTarazedAppLifeCycleObserverFactory create(LibraryModule libraryModule, Provider<TarazedLogger> provider, Provider<CoroutineScope> provider2, Provider<DispatcherProvider> provider3, Provider<ArcusHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<BizMetricsHelper> provider6) {
        return new LibraryModule_ProvideTarazedAppLifeCycleObserverFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static TarazedAppLifeCycleObserver provideInstance(LibraryModule libraryModule, Provider<TarazedLogger> provider, Provider<CoroutineScope> provider2, Provider<DispatcherProvider> provider3, Provider<ArcusHelper> provider4, Provider<TarazedSessionNotifier> provider5, Provider<BizMetricsHelper> provider6) {
        return proxyProvideTarazedAppLifeCycleObserver(libraryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static TarazedAppLifeCycleObserver proxyProvideTarazedAppLifeCycleObserver(LibraryModule libraryModule, TarazedLogger tarazedLogger, CoroutineScope coroutineScope, DispatcherProvider dispatcherProvider, ArcusHelper arcusHelper, TarazedSessionNotifier tarazedSessionNotifier, BizMetricsHelper bizMetricsHelper) {
        return (TarazedAppLifeCycleObserver) Preconditions.checkNotNull(libraryModule.provideTarazedAppLifeCycleObserver(tarazedLogger, coroutineScope, dispatcherProvider, arcusHelper, tarazedSessionNotifier, bizMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedAppLifeCycleObserver mo10268get() {
        return provideInstance(this.module, this.loggerProvider, this.coroutineScopeProvider, this.dispatcherProvider, this.arcusHelperProvider, this.notifierProvider, this.bizMetricsHelperProvider);
    }
}
