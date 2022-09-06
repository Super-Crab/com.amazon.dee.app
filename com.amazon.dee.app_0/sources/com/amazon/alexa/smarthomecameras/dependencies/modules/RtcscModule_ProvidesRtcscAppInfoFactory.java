package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class RtcscModule_ProvidesRtcscAppInfoFactory implements Factory<RtcscAppInfo> {
    private static final RtcscModule_ProvidesRtcscAppInfoFactory INSTANCE = new RtcscModule_ProvidesRtcscAppInfoFactory();

    public static RtcscModule_ProvidesRtcscAppInfoFactory create() {
        return INSTANCE;
    }

    public static RtcscAppInfo provideInstance() {
        return proxyProvidesRtcscAppInfo();
    }

    public static RtcscAppInfo proxyProvidesRtcscAppInfo() {
        return (RtcscAppInfo) Preconditions.checkNotNull(RtcscModule.providesRtcscAppInfo(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RtcscAppInfo mo10268get() {
        return provideInstance();
    }
}
