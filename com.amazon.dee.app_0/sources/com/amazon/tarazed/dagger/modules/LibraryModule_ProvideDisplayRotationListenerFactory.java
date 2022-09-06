package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.rotation.android.DisplayRotationListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideDisplayRotationListenerFactory implements Factory<DisplayRotationListener> {
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final LibraryModule module;

    public LibraryModule_ProvideDisplayRotationListenerFactory(LibraryModule libraryModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        this.module = libraryModule;
        this.loggerProvider = provider;
        this.metricsHelperProvider = provider2;
    }

    public static LibraryModule_ProvideDisplayRotationListenerFactory create(LibraryModule libraryModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        return new LibraryModule_ProvideDisplayRotationListenerFactory(libraryModule, provider, provider2);
    }

    public static DisplayRotationListener provideInstance(LibraryModule libraryModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        return proxyProvideDisplayRotationListener(libraryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static DisplayRotationListener proxyProvideDisplayRotationListener(LibraryModule libraryModule, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return (DisplayRotationListener) Preconditions.checkNotNull(libraryModule.provideDisplayRotationListener(tarazedSessionLogger, tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DisplayRotationListener mo10268get() {
        return provideInstance(this.module, this.loggerProvider, this.metricsHelperProvider);
    }
}
