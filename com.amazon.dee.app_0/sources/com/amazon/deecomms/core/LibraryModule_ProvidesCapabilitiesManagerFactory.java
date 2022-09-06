package com.amazon.deecomms.core;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCapabilitiesManagerFactory implements Factory<CapabilitiesManager> {
    private final Provider<Context> contextProvider;
    private final Provider<FeatureFlagManager> ffmProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCapabilitiesManagerFactory(LibraryModule libraryModule, Provider<FeatureFlagManager> provider, Provider<Context> provider2) {
        this.module = libraryModule;
        this.ffmProvider = provider;
        this.contextProvider = provider2;
    }

    public static LibraryModule_ProvidesCapabilitiesManagerFactory create(LibraryModule libraryModule, Provider<FeatureFlagManager> provider, Provider<Context> provider2) {
        return new LibraryModule_ProvidesCapabilitiesManagerFactory(libraryModule, provider, provider2);
    }

    public static CapabilitiesManager provideInstance(LibraryModule libraryModule, Provider<FeatureFlagManager> provider, Provider<Context> provider2) {
        return (CapabilitiesManager) Preconditions.checkNotNull(libraryModule.providesCapabilitiesManager(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CapabilitiesManager proxyProvidesCapabilitiesManager(LibraryModule libraryModule, FeatureFlagManager featureFlagManager, Context context) {
        return (CapabilitiesManager) Preconditions.checkNotNull(libraryModule.providesCapabilitiesManager(featureFlagManager, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CapabilitiesManager mo10268get() {
        return provideInstance(this.module, this.ffmProvider, this.contextProvider);
    }
}
