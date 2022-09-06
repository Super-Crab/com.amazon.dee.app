package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IncomingCallFragment_MembersInjector implements MembersInjector<IncomingCallFragment> {
    private final Provider<CapabilitiesManager> mCapabilitiesManagerProvider;
    private final Provider<RealTimeTextEnablementAuthority> realTimeTextEnablementAuthorityProvider;

    public IncomingCallFragment_MembersInjector(Provider<CapabilitiesManager> provider, Provider<RealTimeTextEnablementAuthority> provider2) {
        this.mCapabilitiesManagerProvider = provider;
        this.realTimeTextEnablementAuthorityProvider = provider2;
    }

    public static MembersInjector<IncomingCallFragment> create(Provider<CapabilitiesManager> provider, Provider<RealTimeTextEnablementAuthority> provider2) {
        return new IncomingCallFragment_MembersInjector(provider, provider2);
    }

    public static void injectMCapabilitiesManager(IncomingCallFragment incomingCallFragment, CapabilitiesManager capabilitiesManager) {
        incomingCallFragment.mCapabilitiesManager = capabilitiesManager;
    }

    public static void injectRealTimeTextEnablementAuthority(IncomingCallFragment incomingCallFragment, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        incomingCallFragment.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(IncomingCallFragment incomingCallFragment) {
        incomingCallFragment.mCapabilitiesManager = this.mCapabilitiesManagerProvider.mo10268get();
        incomingCallFragment.realTimeTextEnablementAuthority = this.realTimeTextEnablementAuthorityProvider.mo10268get();
    }
}
