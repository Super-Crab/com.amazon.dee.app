package com.amazon.rtcsc.capabilityagent.common.core;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class RtcscCapabilityAgent_Factory implements Factory<RtcscCapabilityAgent> {
    private final Provider<Context> contextProvider;
    private final Provider<RtcscCapabilityAgentEventListener> eventListenerProvider;

    public RtcscCapabilityAgent_Factory(Provider<Context> provider, Provider<RtcscCapabilityAgentEventListener> provider2) {
        this.contextProvider = provider;
        this.eventListenerProvider = provider2;
    }

    public static RtcscCapabilityAgent_Factory create(Provider<Context> provider, Provider<RtcscCapabilityAgentEventListener> provider2) {
        return new RtcscCapabilityAgent_Factory(provider, provider2);
    }

    public static RtcscCapabilityAgent newRtcscCapabilityAgent(Context context, RtcscCapabilityAgentEventListener rtcscCapabilityAgentEventListener) {
        return new RtcscCapabilityAgent(context, rtcscCapabilityAgentEventListener);
    }

    public static RtcscCapabilityAgent provideInstance(Provider<Context> provider, Provider<RtcscCapabilityAgentEventListener> provider2) {
        return new RtcscCapabilityAgent(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RtcscCapabilityAgent mo10268get() {
        return provideInstance(this.contextProvider, this.eventListenerProvider);
    }
}
