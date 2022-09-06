package com.amazon.deecomms.calling.core;

import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallInitiationAuthority_Factory implements Factory<CallInitiationAuthority> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public CallInitiationAuthority_Factory(Provider<SipClientState> provider, Provider<CapabilitiesManager> provider2) {
        this.sipClientStateProvider = provider;
        this.capabilitiesManagerProvider = provider2;
    }

    public static CallInitiationAuthority_Factory create(Provider<SipClientState> provider, Provider<CapabilitiesManager> provider2) {
        return new CallInitiationAuthority_Factory(provider, provider2);
    }

    public static CallInitiationAuthority newCallInitiationAuthority(SipClientState sipClientState, CapabilitiesManager capabilitiesManager) {
        return new CallInitiationAuthority(sipClientState, capabilitiesManager);
    }

    public static CallInitiationAuthority provideInstance(Provider<SipClientState> provider, Provider<CapabilitiesManager> provider2) {
        return new CallInitiationAuthority(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallInitiationAuthority mo10268get() {
        return provideInstance(this.sipClientStateProvider, this.capabilitiesManagerProvider);
    }
}
