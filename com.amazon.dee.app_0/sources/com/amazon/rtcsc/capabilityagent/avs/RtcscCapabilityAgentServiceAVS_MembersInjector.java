package com.amazon.rtcsc.capabilityagent.avs;

import com.amazon.rtcsc.capabilityagent.common.core.RtcscCapabilityAgent;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class RtcscCapabilityAgentServiceAVS_MembersInjector implements MembersInjector<RtcscCapabilityAgentServiceAVS> {
    private final Provider<RtcscCapabilityAgent> mRtcscCapabilityAgentProvider;
    private final Provider<RtcscContextProvider> mRtcscContextProvider;
    private final Provider<WrappedAlexaConnection> mWrappedAlexaConnectionProvider;

    public RtcscCapabilityAgentServiceAVS_MembersInjector(Provider<WrappedAlexaConnection> provider, Provider<RtcscCapabilityAgent> provider2, Provider<RtcscContextProvider> provider3) {
        this.mWrappedAlexaConnectionProvider = provider;
        this.mRtcscCapabilityAgentProvider = provider2;
        this.mRtcscContextProvider = provider3;
    }

    public static MembersInjector<RtcscCapabilityAgentServiceAVS> create(Provider<WrappedAlexaConnection> provider, Provider<RtcscCapabilityAgent> provider2, Provider<RtcscContextProvider> provider3) {
        return new RtcscCapabilityAgentServiceAVS_MembersInjector(provider, provider2, provider3);
    }

    public static void injectMRtcscCapabilityAgent(RtcscCapabilityAgentServiceAVS rtcscCapabilityAgentServiceAVS, RtcscCapabilityAgent rtcscCapabilityAgent) {
        rtcscCapabilityAgentServiceAVS.mRtcscCapabilityAgent = rtcscCapabilityAgent;
    }

    public static void injectMRtcscContextProvider(RtcscCapabilityAgentServiceAVS rtcscCapabilityAgentServiceAVS, RtcscContextProvider rtcscContextProvider) {
        rtcscCapabilityAgentServiceAVS.mRtcscContextProvider = rtcscContextProvider;
    }

    public static void injectMWrappedAlexaConnection(RtcscCapabilityAgentServiceAVS rtcscCapabilityAgentServiceAVS, WrappedAlexaConnection wrappedAlexaConnection) {
        rtcscCapabilityAgentServiceAVS.mWrappedAlexaConnection = wrappedAlexaConnection;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RtcscCapabilityAgentServiceAVS rtcscCapabilityAgentServiceAVS) {
        injectMWrappedAlexaConnection(rtcscCapabilityAgentServiceAVS, this.mWrappedAlexaConnectionProvider.mo10268get());
        injectMRtcscCapabilityAgent(rtcscCapabilityAgentServiceAVS, this.mRtcscCapabilityAgentProvider.mo10268get());
        injectMRtcscContextProvider(rtcscCapabilityAgentServiceAVS, this.mRtcscContextProvider.mo10268get());
    }
}
