package com.amazon.rtcsc.capabilityagent.common.core;

import android.content.Context;
import com.amazon.rtcsc.capabilityagent.avs.RtcscContextProvider;
import com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class RtcscCapabilityAgentEventListener_Factory implements Factory<RtcscCapabilityAgentEventListener> {
    private final Provider<WrappedAlexaConnection> alexaConnectionProvider;
    private final Provider<Context> contextProvider;
    private final Provider<RtcscContextProvider> rtcscContextProvider;

    public RtcscCapabilityAgentEventListener_Factory(Provider<Context> provider, Provider<WrappedAlexaConnection> provider2, Provider<RtcscContextProvider> provider3) {
        this.contextProvider = provider;
        this.alexaConnectionProvider = provider2;
        this.rtcscContextProvider = provider3;
    }

    public static RtcscCapabilityAgentEventListener_Factory create(Provider<Context> provider, Provider<WrappedAlexaConnection> provider2, Provider<RtcscContextProvider> provider3) {
        return new RtcscCapabilityAgentEventListener_Factory(provider, provider2, provider3);
    }

    public static RtcscCapabilityAgentEventListener newRtcscCapabilityAgentEventListener(Context context, WrappedAlexaConnection wrappedAlexaConnection, RtcscContextProvider rtcscContextProvider) {
        return new RtcscCapabilityAgentEventListener(context, wrappedAlexaConnection, rtcscContextProvider);
    }

    public static RtcscCapabilityAgentEventListener provideInstance(Provider<Context> provider, Provider<WrappedAlexaConnection> provider2, Provider<RtcscContextProvider> provider3) {
        return new RtcscCapabilityAgentEventListener(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RtcscCapabilityAgentEventListener mo10268get() {
        return provideInstance(this.contextProvider, this.alexaConnectionProvider, this.rtcscContextProvider);
    }
}
