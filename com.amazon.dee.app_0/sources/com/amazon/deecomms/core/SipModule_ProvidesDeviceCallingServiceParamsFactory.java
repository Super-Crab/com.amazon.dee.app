package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.sipclient.SipHeaders;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesDeviceCallingServiceParamsFactory implements Factory<DeviceCallingServiceParams> {
    private final Provider<Context> applicationContextProvider;
    private final SipModule module;
    private final Provider<SipHeaders> registrationHeadersProvider;

    public SipModule_ProvidesDeviceCallingServiceParamsFactory(SipModule sipModule, Provider<Context> provider, Provider<SipHeaders> provider2) {
        this.module = sipModule;
        this.applicationContextProvider = provider;
        this.registrationHeadersProvider = provider2;
    }

    public static SipModule_ProvidesDeviceCallingServiceParamsFactory create(SipModule sipModule, Provider<Context> provider, Provider<SipHeaders> provider2) {
        return new SipModule_ProvidesDeviceCallingServiceParamsFactory(sipModule, provider, provider2);
    }

    public static DeviceCallingServiceParams provideInstance(SipModule sipModule, Provider<Context> provider, Provider<SipHeaders> provider2) {
        return (DeviceCallingServiceParams) Preconditions.checkNotNull(sipModule.providesDeviceCallingServiceParams(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DeviceCallingServiceParams proxyProvidesDeviceCallingServiceParams(SipModule sipModule, Context context, SipHeaders sipHeaders) {
        return (DeviceCallingServiceParams) Preconditions.checkNotNull(sipModule.providesDeviceCallingServiceParams(context, sipHeaders), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceCallingServiceParams mo10268get() {
        return provideInstance(this.module, this.applicationContextProvider, this.registrationHeadersProvider);
    }
}
