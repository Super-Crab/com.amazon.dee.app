package com.amazon.deecomms.core;

import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesAppUrlFactory implements Factory<AppUrl> {
    private final Provider<ArcusConfig> arcusConfigProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesAppUrlFactory(LibraryModule libraryModule, Provider<ArcusConfig> provider) {
        this.module = libraryModule;
        this.arcusConfigProvider = provider;
    }

    public static LibraryModule_ProvidesAppUrlFactory create(LibraryModule libraryModule, Provider<ArcusConfig> provider) {
        return new LibraryModule_ProvidesAppUrlFactory(libraryModule, provider);
    }

    public static AppUrl provideInstance(LibraryModule libraryModule, Provider<ArcusConfig> provider) {
        return (AppUrl) Preconditions.checkNotNull(libraryModule.providesAppUrl(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AppUrl proxyProvidesAppUrl(LibraryModule libraryModule, ArcusConfig arcusConfig) {
        return (AppUrl) Preconditions.checkNotNull(libraryModule.providesAppUrl(arcusConfig), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppUrl mo10268get() {
        return provideInstance(this.module, this.arcusConfigProvider);
    }
}
