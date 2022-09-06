package com.amazon.tarazed.dagger.modules;

import android.os.Handler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideMainLooperHandlerFactory implements Factory<Handler> {
    private final LibraryModule module;

    public LibraryModule_ProvideMainLooperHandlerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvideMainLooperHandlerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvideMainLooperHandlerFactory(libraryModule);
    }

    public static Handler provideInstance(LibraryModule libraryModule) {
        return proxyProvideMainLooperHandler(libraryModule);
    }

    public static Handler proxyProvideMainLooperHandler(LibraryModule libraryModule) {
        return (Handler) Preconditions.checkNotNull(libraryModule.provideMainLooperHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Handler mo10268get() {
        return provideInstance(this.module);
    }
}
