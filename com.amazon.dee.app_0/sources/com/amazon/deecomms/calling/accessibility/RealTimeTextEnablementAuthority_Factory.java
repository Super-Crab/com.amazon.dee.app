package com.amazon.deecomms.calling.accessibility;

import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class RealTimeTextEnablementAuthority_Factory implements Factory<RealTimeTextEnablementAuthority> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public RealTimeTextEnablementAuthority_Factory(Provider<CapabilitiesManager> provider, Provider<SipClientState> provider2) {
        this.capabilitiesManagerProvider = provider;
        this.sipClientStateProvider = provider2;
    }

    public static RealTimeTextEnablementAuthority_Factory create(Provider<CapabilitiesManager> provider, Provider<SipClientState> provider2) {
        return new RealTimeTextEnablementAuthority_Factory(provider, provider2);
    }

    public static RealTimeTextEnablementAuthority newRealTimeTextEnablementAuthority(CapabilitiesManager capabilitiesManager, SipClientState sipClientState) {
        return new RealTimeTextEnablementAuthority(capabilitiesManager, sipClientState);
    }

    public static RealTimeTextEnablementAuthority provideInstance(Provider<CapabilitiesManager> provider, Provider<SipClientState> provider2) {
        return new RealTimeTextEnablementAuthority(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RealTimeTextEnablementAuthority mo10268get() {
        return provideInstance(this.capabilitiesManagerProvider, this.sipClientStateProvider);
    }
}
