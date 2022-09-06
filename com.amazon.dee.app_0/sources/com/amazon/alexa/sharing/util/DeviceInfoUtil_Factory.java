package com.amazon.alexa.sharing.util;

import dagger.internal.Factory;
/* loaded from: classes10.dex */
public final class DeviceInfoUtil_Factory implements Factory<DeviceInfoUtil> {
    private static final DeviceInfoUtil_Factory INSTANCE = new DeviceInfoUtil_Factory();

    public static DeviceInfoUtil_Factory create() {
        return INSTANCE;
    }

    public static DeviceInfoUtil newDeviceInfoUtil() {
        return new DeviceInfoUtil();
    }

    public static DeviceInfoUtil provideInstance() {
        return new DeviceInfoUtil();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInfoUtil mo10268get() {
        return provideInstance();
    }
}
