package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallPayloadValidator;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.sip.SipClientState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCallManagerFactory implements Factory<CallManager> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CallPayloadValidator> callPayloadValidatorProvider;
    private final Provider<Context> contextProvider;
    private final Provider<SipClientState> currentSipClientStateProvider;
    private final Provider<ModeSwitchHelper> modeSwitchHelperProvider;
    private final LibraryModule module;
    private final Provider<SipClientState> previousSipClientStateProvider;

    public LibraryModule_ProvidesCallManagerFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<SipClientState> provider2, Provider<ApplicationManager> provider3, Provider<ModeSwitchHelper> provider4, Provider<CallPayloadValidator> provider5, Provider<SipClientState> provider6) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.currentSipClientStateProvider = provider2;
        this.applicationManagerProvider = provider3;
        this.modeSwitchHelperProvider = provider4;
        this.callPayloadValidatorProvider = provider5;
        this.previousSipClientStateProvider = provider6;
    }

    public static LibraryModule_ProvidesCallManagerFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<SipClientState> provider2, Provider<ApplicationManager> provider3, Provider<ModeSwitchHelper> provider4, Provider<CallPayloadValidator> provider5, Provider<SipClientState> provider6) {
        return new LibraryModule_ProvidesCallManagerFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static CallManager provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<SipClientState> provider2, Provider<ApplicationManager> provider3, Provider<ModeSwitchHelper> provider4, Provider<CallPayloadValidator> provider5, Provider<SipClientState> provider6) {
        return (CallManager) Preconditions.checkNotNull(libraryModule.providesCallManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallManager proxyProvidesCallManager(LibraryModule libraryModule, Context context, SipClientState sipClientState, ApplicationManager applicationManager, ModeSwitchHelper modeSwitchHelper, CallPayloadValidator callPayloadValidator, SipClientState sipClientState2) {
        return (CallManager) Preconditions.checkNotNull(libraryModule.providesCallManager(context, sipClientState, applicationManager, modeSwitchHelper, callPayloadValidator, sipClientState2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.currentSipClientStateProvider, this.applicationManagerProvider, this.modeSwitchHelperProvider, this.callPayloadValidatorProvider, this.previousSipClientStateProvider);
    }
}
