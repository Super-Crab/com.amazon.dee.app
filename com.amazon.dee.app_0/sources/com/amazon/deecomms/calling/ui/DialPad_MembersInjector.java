package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.common.sip.SipClientState;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DialPad_MembersInjector implements MembersInjector<DialPad> {
    private final Provider<SipClientState> sipClientStateProvider;

    public DialPad_MembersInjector(Provider<SipClientState> provider) {
        this.sipClientStateProvider = provider;
    }

    public static MembersInjector<DialPad> create(Provider<SipClientState> provider) {
        return new DialPad_MembersInjector(provider);
    }

    public static void injectSipClientState(DialPad dialPad, SipClientState sipClientState) {
        dialPad.sipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DialPad dialPad) {
        dialPad.sipClientState = this.sipClientStateProvider.mo10268get();
    }
}
