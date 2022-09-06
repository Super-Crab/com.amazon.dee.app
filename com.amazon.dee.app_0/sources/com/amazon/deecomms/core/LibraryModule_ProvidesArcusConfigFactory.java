package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.auth.Stage;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesArcusConfigFactory implements Factory<ArcusConfig> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;
    private final Provider<Stage> stageProvider;

    public LibraryModule_ProvidesArcusConfigFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<Stage> provider2) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.stageProvider = provider2;
    }

    public static LibraryModule_ProvidesArcusConfigFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<Stage> provider2) {
        return new LibraryModule_ProvidesArcusConfigFactory(libraryModule, provider, provider2);
    }

    public static ArcusConfig provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<Stage> provider2) {
        return (ArcusConfig) Preconditions.checkNotNull(libraryModule.providesArcusConfig(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ArcusConfig proxyProvidesArcusConfig(LibraryModule libraryModule, Context context, Stage stage) {
        return (ArcusConfig) Preconditions.checkNotNull(libraryModule.providesArcusConfig(context, stage), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ArcusConfig mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.stageProvider);
    }
}
