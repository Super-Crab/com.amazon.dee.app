package com.amazon.deecomms.core;

import com.amazon.comms.calling.service.DeviceCallingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesDeviceCallingServiceFactory implements Factory<DeviceCallingService> {
    private final SipModule module;

    public SipModule_ProvidesDeviceCallingServiceFactory(SipModule sipModule) {
        this.module = sipModule;
    }

    public static SipModule_ProvidesDeviceCallingServiceFactory create(SipModule sipModule) {
        return new SipModule_ProvidesDeviceCallingServiceFactory(sipModule);
    }

    public static DeviceCallingService provideInstance(SipModule sipModule) {
        return (DeviceCallingService) Preconditions.checkNotNull(sipModule.providesDeviceCallingService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DeviceCallingService proxyProvidesDeviceCallingService(SipModule sipModule) {
        return (DeviceCallingService) Preconditions.checkNotNull(sipModule.providesDeviceCallingService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceCallingService mo10268get() {
        return provideInstance(this.module);
    }
}
