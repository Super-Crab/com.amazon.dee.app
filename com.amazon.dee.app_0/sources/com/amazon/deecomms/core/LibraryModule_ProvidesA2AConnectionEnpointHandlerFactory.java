package com.amazon.deecomms.core;

import com.amazon.deecomms.alexa.connection.enpoint.a2a.A2AConnectionEndpointHandler;
import com.amazon.deecomms.calling.controller.CallManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesA2AConnectionEnpointHandlerFactory implements Factory<A2AConnectionEndpointHandler> {
    private final Provider<CallManager> callManagerProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesA2AConnectionEnpointHandlerFactory(LibraryModule libraryModule, Provider<CallManager> provider) {
        this.module = libraryModule;
        this.callManagerProvider = provider;
    }

    public static LibraryModule_ProvidesA2AConnectionEnpointHandlerFactory create(LibraryModule libraryModule, Provider<CallManager> provider) {
        return new LibraryModule_ProvidesA2AConnectionEnpointHandlerFactory(libraryModule, provider);
    }

    public static A2AConnectionEndpointHandler provideInstance(LibraryModule libraryModule, Provider<CallManager> provider) {
        return (A2AConnectionEndpointHandler) Preconditions.checkNotNull(libraryModule.providesA2AConnectionEnpointHandler(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static A2AConnectionEndpointHandler proxyProvidesA2AConnectionEnpointHandler(LibraryModule libraryModule, CallManager callManager) {
        return (A2AConnectionEndpointHandler) Preconditions.checkNotNull(libraryModule.providesA2AConnectionEnpointHandler(callManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public A2AConnectionEndpointHandler mo10268get() {
        return provideInstance(this.module, this.callManagerProvider);
    }
}
