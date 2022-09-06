package com.amazon.rtcsc.capabilityagent.common.dependencies;

import com.amazon.rtcsc.capabilityagent.avs.RtcscContextProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ApplicationModule_ProvidesRtcscContextProviderFactory implements Factory<RtcscContextProvider> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesRtcscContextProviderFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesRtcscContextProviderFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesRtcscContextProviderFactory(applicationModule);
    }

    public static RtcscContextProvider provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesRtcscContextProvider(applicationModule);
    }

    public static RtcscContextProvider proxyProvidesRtcscContextProvider(ApplicationModule applicationModule) {
        return (RtcscContextProvider) Preconditions.checkNotNull(applicationModule.providesRtcscContextProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RtcscContextProvider mo10268get() {
        return provideInstance(this.module);
    }
}
