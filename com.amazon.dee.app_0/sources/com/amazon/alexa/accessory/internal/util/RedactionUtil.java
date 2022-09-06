package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class RedactionUtil {
    public static final String REDACTED = "[REDACTED]";

    private RedactionUtil() {
    }

    public static Device.DeviceInformation redact(Device.DeviceInformation deviceInformation) {
        if (deviceInformation == null) {
            return null;
        }
        return Device.DeviceInformation.newBuilder(deviceInformation).setSerialNumber("[REDACTED]").mo10084build();
    }

    public static Accessory redact(Accessory accessory) {
        if (accessory == null) {
            return null;
        }
        return new Accessory("[REDACTED]", accessory.getTransport(), accessory.getName());
    }

    public static com.amazon.alexa.accessory.repositories.device.v2.Device redact(com.amazon.alexa.accessory.repositories.device.v2.Device device) {
        if (device == null) {
            return null;
        }
        return com.amazon.alexa.accessory.repositories.device.v2.Device.newBuilder(device).serialNumber("[REDACTED]").build();
    }

    public static PeripheralDevice redact(PeripheralDevice peripheralDevice) {
        if (peripheralDevice == null) {
            return null;
        }
        return new PeripheralDevice("[REDACTED]", peripheralDevice.getType(), peripheralDevice.getName());
    }

    public static DeviceGroup redact(DeviceGroup deviceGroup) {
        if (deviceGroup == null) {
            return null;
        }
        List<com.amazon.alexa.accessory.repositories.device.v2.Device> devices = deviceGroup.getDevices();
        ArrayList arrayList = new ArrayList(devices.size());
        for (com.amazon.alexa.accessory.repositories.device.v2.Device device : devices) {
            arrayList.add(redact(device));
        }
        return DeviceGroup.newBuilder(deviceGroup).devices(arrayList).build();
    }
}
