package com.amazon.deecomms.core;

import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.calling.controller.CallManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesPCCConnectionEnpointHandlerFactory implements Factory<PCCConnectionEndpointHandler> {
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesPCCConnectionEnpointHandlerFactory(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<CallManager> provider2) {
        this.module = libraryModule;
        this.capabilitiesManagerProvider = provider;
        this.callManagerProvider = provider2;
    }

    public static LibraryModule_ProvidesPCCConnectionEnpointHandlerFactory create(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<CallManager> provider2) {
        return new LibraryModule_ProvidesPCCConnectionEnpointHandlerFactory(libraryModule, provider, provider2);
    }

    public static PCCConnectionEndpointHandler provideInstance(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<CallManager> provider2) {
        return (PCCConnectionEndpointHandler) Preconditions.checkNotNull(libraryModule.providesPCCConnectionEnpointHandler(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PCCConnectionEndpointHandler proxyProvidesPCCConnectionEnpointHandler(LibraryModule libraryModule, CapabilitiesManager capabilitiesManager, CallManager callManager) {
        return (PCCConnectionEndpointHandler) Preconditions.checkNotNull(libraryModule.providesPCCConnectionEnpointHandler(capabilitiesManager, callManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PCCConnectionEndpointHandler mo10268get() {
        return provideInstance(this.module, this.capabilitiesManagerProvider, this.callManagerProvider);
    }
}
