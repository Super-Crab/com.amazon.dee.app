package com.amazon.comms.ringservice;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public enum DeviceCallingServiceImpl_Factory implements Factory<DeviceCallingServiceImpl> {
    INSTANCE;

    public static Factory<DeviceCallingServiceImpl> create() {
        return INSTANCE;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceCallingServiceImpl mo10268get() {
        return new DeviceCallingServiceImpl();
    }
}
