package com.amazon.deecomms.calling.controller;

import android.content.Context;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallActionsDispatcher_Factory implements Factory<CallActionsDispatcher> {
    private final Provider<CallInitiationAuthority> callInitiationAuthorityProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public CallActionsDispatcher_Factory(Provider<Context> provider, Provider<SipClientState> provider2, Provider<CallManager> provider3, Provider<CallInitiationAuthority> provider4, Provider<CapabilitiesManager> provider5) {
        this.contextProvider = provider;
        this.sipClientStateProvider = provider2;
        this.callManagerProvider = provider3;
        this.callInitiationAuthorityProvider = provider4;
        this.capabilitiesManagerProvider = provider5;
    }

    public static CallActionsDispatcher_Factory create(Provider<Context> provider, Provider<SipClientState> provider2, Provider<CallManager> provider3, Provider<CallInitiationAuthority> provider4, Provider<CapabilitiesManager> provider5) {
        return new CallActionsDispatcher_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CallActionsDispatcher newCallActionsDispatcher(Context context, SipClientState sipClientState, CallManager callManager, CallInitiationAuthority callInitiationAuthority, CapabilitiesManager capabilitiesManager) {
        return new CallActionsDispatcher(context, sipClientState, callManager, callInitiationAuthority, capabilitiesManager);
    }

    public static CallActionsDispatcher provideInstance(Provider<Context> provider, Provider<SipClientState> provider2, Provider<CallManager> provider3, Provider<CallInitiationAuthority> provider4, Provider<CapabilitiesManager> provider5) {
        return new CallActionsDispatcher(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallActionsDispatcher mo10268get() {
        return provideInstance(this.contextProvider, this.sipClientStateProvider, this.callManagerProvider, this.callInitiationAuthorityProvider, this.capabilitiesManagerProvider);
    }
}
