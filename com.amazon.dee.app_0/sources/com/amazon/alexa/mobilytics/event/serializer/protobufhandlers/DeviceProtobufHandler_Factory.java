package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DeviceProtobufHandler_Factory implements Factory<DeviceProtobufHandler> {
    private final Provider<DeviceConfiguration> deviceConfigurationProvider;

    public DeviceProtobufHandler_Factory(Provider<DeviceConfiguration> provider) {
        this.deviceConfigurationProvider = provider;
    }

    public static DeviceProtobufHandler_Factory create(Provider<DeviceConfiguration> provider) {
        return new DeviceProtobufHandler_Factory(provider);
    }

    public static DeviceProtobufHandler newDeviceProtobufHandler(DeviceConfiguration deviceConfiguration) {
        return new DeviceProtobufHandler(deviceConfiguration);
    }

    public static DeviceProtobufHandler provideInstance(Provider<DeviceConfiguration> provider) {
        return new DeviceProtobufHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceProtobufHandler mo10268get() {
        return provideInstance(this.deviceConfigurationProvider);
    }
}
