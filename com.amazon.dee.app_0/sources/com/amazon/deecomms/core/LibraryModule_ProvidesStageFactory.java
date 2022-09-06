package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.auth.Stage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesStageFactory implements Factory<Stage> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesStageFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesStageFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesStageFactory(libraryModule, provider);
    }

    public static Stage provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (Stage) Preconditions.checkNotNull(libraryModule.providesStage(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Stage proxyProvidesStage(LibraryModule libraryModule, Context context) {
        return (Stage) Preconditions.checkNotNull(libraryModule.providesStage(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Stage mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
