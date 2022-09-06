package com.amazon.deecomms.core;

import android.content.Context;
import android.telecom.TelecomManager;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.calling.phonecallcontroller.AcceptNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.EndNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.NoCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCDirectiveHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesPCCDirectiveHandlerFactory implements Factory<PCCDirectiveHandler> {
    private final Provider<AcceptNativeCallHandler> acceptNativeCallHandlerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsEventSender> commsEventSenderProvider;
    private final Provider<AlexaServicesConnection> connectionProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EndNativeCallHandler> endNativeCallHandlerProvider;
    private final Provider<CommsAlexaServicesConnectionListener> listenerProvider;
    private final Provider<MakeNativeCallHandler> makeNativeCallHandlerProvider;
    private final LibraryModule module;
    private final Provider<NoCallPermissionHandler> noCallPermissionHandlerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<TelecomManager> telecomManagerProvider;

    public LibraryModule_ProvidesPCCDirectiveHandlerFactory(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<AlexaServicesConnection> provider4, Provider<CommsEventSender> provider5, Provider<CommsAlexaServicesConnectionListener> provider6, Provider<MakeNativeCallHandler> provider7, Provider<AcceptNativeCallHandler> provider8, Provider<EndNativeCallHandler> provider9, Provider<NoCallPermissionHandler> provider10, Provider<Context> provider11) {
        this.module = libraryModule;
        this.capabilitiesManagerProvider = provider;
        this.pccContextProvider = provider2;
        this.telecomManagerProvider = provider3;
        this.connectionProvider = provider4;
        this.commsEventSenderProvider = provider5;
        this.listenerProvider = provider6;
        this.makeNativeCallHandlerProvider = provider7;
        this.acceptNativeCallHandlerProvider = provider8;
        this.endNativeCallHandlerProvider = provider9;
        this.noCallPermissionHandlerProvider = provider10;
        this.contextProvider = provider11;
    }

    public static LibraryModule_ProvidesPCCDirectiveHandlerFactory create(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<AlexaServicesConnection> provider4, Provider<CommsEventSender> provider5, Provider<CommsAlexaServicesConnectionListener> provider6, Provider<MakeNativeCallHandler> provider7, Provider<AcceptNativeCallHandler> provider8, Provider<EndNativeCallHandler> provider9, Provider<NoCallPermissionHandler> provider10, Provider<Context> provider11) {
        return new LibraryModule_ProvidesPCCDirectiveHandlerFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static PCCDirectiveHandler provideInstance(LibraryModule libraryModule, Provider<CapabilitiesManager> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<AlexaServicesConnection> provider4, Provider<CommsEventSender> provider5, Provider<CommsAlexaServicesConnectionListener> provider6, Provider<MakeNativeCallHandler> provider7, Provider<AcceptNativeCallHandler> provider8, Provider<EndNativeCallHandler> provider9, Provider<NoCallPermissionHandler> provider10, Provider<Context> provider11) {
        return (PCCDirectiveHandler) Preconditions.checkNotNull(libraryModule.providesPCCDirectiveHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PCCDirectiveHandler proxyProvidesPCCDirectiveHandler(LibraryModule libraryModule, CapabilitiesManager capabilitiesManager, PCCContextProvider pCCContextProvider, TelecomManager telecomManager, AlexaServicesConnection alexaServicesConnection, CommsEventSender commsEventSender, CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, MakeNativeCallHandler makeNativeCallHandler, AcceptNativeCallHandler acceptNativeCallHandler, EndNativeCallHandler endNativeCallHandler, NoCallPermissionHandler noCallPermissionHandler, Context context) {
        return (PCCDirectiveHandler) Preconditions.checkNotNull(libraryModule.providesPCCDirectiveHandler(capabilitiesManager, pCCContextProvider, telecomManager, alexaServicesConnection, commsEventSender, commsAlexaServicesConnectionListener, makeNativeCallHandler, acceptNativeCallHandler, endNativeCallHandler, noCallPermissionHandler, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PCCDirectiveHandler mo10268get() {
        return provideInstance(this.module, this.capabilitiesManagerProvider, this.pccContextProvider, this.telecomManagerProvider, this.connectionProvider, this.commsEventSenderProvider, this.listenerProvider, this.makeNativeCallHandlerProvider, this.acceptNativeCallHandlerProvider, this.endNativeCallHandlerProvider, this.noCallPermissionHandlerProvider, this.contextProvider);
    }
}
