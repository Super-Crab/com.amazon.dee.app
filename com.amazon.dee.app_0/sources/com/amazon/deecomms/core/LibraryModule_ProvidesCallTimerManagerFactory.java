package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCallTimerManagerFactory implements Factory<CallTimerManager> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCallTimerManagerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesCallTimerManagerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesCallTimerManagerFactory(libraryModule, provider);
    }

    public static CallTimerManager provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (CallTimerManager) Preconditions.checkNotNull(libraryModule.providesCallTimerManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallTimerManager proxyProvidesCallTimerManager(LibraryModule libraryModule, Context context) {
        return (CallTimerManager) Preconditions.checkNotNull(libraryModule.providesCallTimerManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallTimerManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
