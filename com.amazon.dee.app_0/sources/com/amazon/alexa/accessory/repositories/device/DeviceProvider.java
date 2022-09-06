package com.amazon.alexa.accessory.repositories.device;

import com.amazon.alexa.accessory.protocol.Device;
import java.util.Set;
/* loaded from: classes6.dex */
public interface DeviceProvider {
    void provideDeviceConfiguration(Device.DeviceConfiguration deviceConfiguration);

    void provideDeviceFeatures(Device.DeviceFeatures deviceFeatures);

    void provideDeviceFeaturesError(Throwable th);

    void provideDeviceInformationSet(Set<Device.DeviceInformation> set);
}
