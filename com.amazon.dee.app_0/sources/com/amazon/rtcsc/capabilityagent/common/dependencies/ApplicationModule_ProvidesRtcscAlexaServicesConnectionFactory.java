package com.amazon.rtcsc.capabilityagent.common.dependencies;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ApplicationModule_ProvidesRtcscAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesRtcscAlexaServicesConnectionFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public static ApplicationModule_ProvidesRtcscAlexaServicesConnectionFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesRtcscAlexaServicesConnectionFactory(applicationModule, provider);
    }

    public static AlexaServicesConnection provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesRtcscAlexaServicesConnection(applicationModule, provider.mo10268get());
    }

    public static AlexaServicesConnection proxyProvidesRtcscAlexaServicesConnection(ApplicationModule applicationModule, Context context) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(applicationModule.providesRtcscAlexaServicesConnection(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
