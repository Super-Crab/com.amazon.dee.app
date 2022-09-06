package com.amazon.deecomms.core;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesFeatureFlagManagerFactory implements Factory<FeatureFlagManager> {
    private final LibraryModule module;

    public LibraryModule_ProvidesFeatureFlagManagerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesFeatureFlagManagerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesFeatureFlagManagerFactory(libraryModule);
    }

    public static FeatureFlagManager provideInstance(LibraryModule libraryModule) {
        return (FeatureFlagManager) Preconditions.checkNotNull(libraryModule.providesFeatureFlagManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static FeatureFlagManager proxyProvidesFeatureFlagManager(LibraryModule libraryModule) {
        return (FeatureFlagManager) Preconditions.checkNotNull(libraryModule.providesFeatureFlagManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureFlagManager mo10268get() {
        return provideInstance(this.module);
    }
}
