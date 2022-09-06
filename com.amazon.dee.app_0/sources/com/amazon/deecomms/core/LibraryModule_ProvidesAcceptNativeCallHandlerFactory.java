package com.amazon.deecomms.core;

import android.content.Context;
import android.telecom.TelecomManager;
import com.amazon.deecomms.calling.phonecallcontroller.AcceptNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.NoCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesAcceptNativeCallHandlerFactory implements Factory<AcceptNativeCallHandler> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;
    private final Provider<NoCallPermissionHandler> noCallPermissionHandlerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<TelecomManager> telecomManagerProvider;

    public LibraryModule_ProvidesAcceptNativeCallHandlerFactory(LibraryModule libraryModule, Provider<NoCallPermissionHandler> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<Context> provider4) {
        this.module = libraryModule;
        this.noCallPermissionHandlerProvider = provider;
        this.pccContextProvider = provider2;
        this.telecomManagerProvider = provider3;
        this.contextProvider = provider4;
    }

    public static LibraryModule_ProvidesAcceptNativeCallHandlerFactory create(LibraryModule libraryModule, Provider<NoCallPermissionHandler> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<Context> provider4) {
        return new LibraryModule_ProvidesAcceptNativeCallHandlerFactory(libraryModule, provider, provider2, provider3, provider4);
    }

    public static AcceptNativeCallHandler provideInstance(LibraryModule libraryModule, Provider<NoCallPermissionHandler> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<Context> provider4) {
        return (AcceptNativeCallHandler) Preconditions.checkNotNull(libraryModule.providesAcceptNativeCallHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AcceptNativeCallHandler proxyProvidesAcceptNativeCallHandler(LibraryModule libraryModule, NoCallPermissionHandler noCallPermissionHandler, PCCContextProvider pCCContextProvider, TelecomManager telecomManager, Context context) {
        return (AcceptNativeCallHandler) Preconditions.checkNotNull(libraryModule.providesAcceptNativeCallHandler(noCallPermissionHandler, pCCContextProvider, telecomManager, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AcceptNativeCallHandler mo10268get() {
        return provideInstance(this.module, this.noCallPermissionHandlerProvider, this.pccContextProvider, this.telecomManagerProvider, this.contextProvider);
    }
}
