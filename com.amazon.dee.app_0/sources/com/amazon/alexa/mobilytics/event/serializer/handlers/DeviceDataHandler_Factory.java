package com.amazon.alexa.mobilytics.event.serializer.handlers;

import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DeviceDataHandler_Factory implements Factory<DeviceDataHandler> {
    private final Provider<DeviceConfiguration> deviceConfigurationProvider;

    public DeviceDataHandler_Factory(Provider<DeviceConfiguration> provider) {
        this.deviceConfigurationProvider = provider;
    }

    public static DeviceDataHandler_Factory create(Provider<DeviceConfiguration> provider) {
        return new DeviceDataHandler_Factory(provider);
    }

    public static DeviceDataHandler newDeviceDataHandler(DeviceConfiguration deviceConfiguration) {
        return new DeviceDataHandler(deviceConfiguration);
    }

    public static DeviceDataHandler provideInstance(Provider<DeviceConfiguration> provider) {
        return new DeviceDataHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceDataHandler mo10268get() {
        return provideInstance(this.deviceConfigurationProvider);
    }
}
