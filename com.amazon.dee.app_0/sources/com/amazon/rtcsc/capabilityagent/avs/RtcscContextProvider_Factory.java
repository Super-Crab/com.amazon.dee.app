package com.amazon.rtcsc.capabilityagent.avs;

import dagger.internal.Factory;
/* loaded from: classes13.dex */
public final class RtcscContextProvider_Factory implements Factory<RtcscContextProvider> {
    private static final RtcscContextProvider_Factory INSTANCE = new RtcscContextProvider_Factory();

    public static RtcscContextProvider_Factory create() {
        return INSTANCE;
    }

    public static RtcscContextProvider newRtcscContextProvider() {
        return new RtcscContextProvider();
    }

    public static RtcscContextProvider provideInstance() {
        return new RtcscContextProvider();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RtcscContextProvider mo10268get() {
        return provideInstance();
    }
}
