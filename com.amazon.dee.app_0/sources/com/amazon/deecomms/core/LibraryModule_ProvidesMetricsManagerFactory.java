package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.comms.metrics.MetricsManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesMetricsManagerFactory implements Factory<MetricsManager> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesMetricsManagerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesMetricsManagerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesMetricsManagerFactory(libraryModule, provider);
    }

    public static MetricsManager provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (MetricsManager) Preconditions.checkNotNull(libraryModule.providesMetricsManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static MetricsManager proxyProvidesMetricsManager(LibraryModule libraryModule, Context context) {
        return (MetricsManager) Preconditions.checkNotNull(libraryModule.providesMetricsManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
