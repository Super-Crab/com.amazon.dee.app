package com.amazon.deecomms.core;

import android.content.Context;
import android.telecom.TelecomManager;
import com.amazon.deecomms.calling.phonecallcontroller.EndNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.NoCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesEndNativeCallHandlerFactory implements Factory<EndNativeCallHandler> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;
    private final Provider<NoCallPermissionHandler> noCallPermissionHandlerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<TelecomManager> telecomManagerProvider;

    public LibraryModule_ProvidesEndNativeCallHandlerFactory(LibraryModule libraryModule, Provider<NoCallPermissionHandler> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<Context> provider4) {
        this.module = libraryModule;
        this.noCallPermissionHandlerProvider = provider;
        this.pccContextProvider = provider2;
        this.telecomManagerProvider = provider3;
        this.contextProvider = provider4;
    }

    public static LibraryModule_ProvidesEndNativeCallHandlerFactory create(LibraryModule libraryModule, Provider<NoCallPermissionHandler> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<Context> provider4) {
        return new LibraryModule_ProvidesEndNativeCallHandlerFactory(libraryModule, provider, provider2, provider3, provider4);
    }

    public static EndNativeCallHandler provideInstance(LibraryModule libraryModule, Provider<NoCallPermissionHandler> provider, Provider<PCCContextProvider> provider2, Provider<TelecomManager> provider3, Provider<Context> provider4) {
        return (EndNativeCallHandler) Preconditions.checkNotNull(libraryModule.providesEndNativeCallHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static EndNativeCallHandler proxyProvidesEndNativeCallHandler(LibraryModule libraryModule, NoCallPermissionHandler noCallPermissionHandler, PCCContextProvider pCCContextProvider, TelecomManager telecomManager, Context context) {
        return (EndNativeCallHandler) Preconditions.checkNotNull(libraryModule.providesEndNativeCallHandler(noCallPermissionHandler, pCCContextProvider, telecomManager, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EndNativeCallHandler mo10268get() {
        return provideInstance(this.module, this.noCallPermissionHandlerProvider, this.pccContextProvider, this.telecomManagerProvider, this.contextProvider);
    }
}
