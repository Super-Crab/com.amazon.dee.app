package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class AccessoryInquiryRecord {
    private final Set<Device> devices;

    public AccessoryInquiryRecord(Set<Device.DeviceInformation> set) {
        Preconditions.notNull(set, "deviceInformationSet");
        this.devices = extractDevices(set);
    }

    private static com.amazon.alexa.accessory.repositories.device.v2.Device extractDevice(Device.DeviceInformation deviceInformation) {
        return com.amazon.alexa.accessory.repositories.device.v2.Device.newBuilder().name(deviceInformation.getName()).serialNumber(deviceInformation.getSerialNumber()).deviceId(Integer.valueOf(deviceInformation.getDeviceId())).color(Integer.valueOf(deviceInformation.getProductColor())).type(deviceInformation.getDeviceType()).build();
    }

    private static Set<com.amazon.alexa.accessory.repositories.device.v2.Device> extractDevices(Set<Device.DeviceInformation> set) {
        HashSet hashSet = new HashSet(set.size());
        for (Device.DeviceInformation deviceInformation : set) {
            hashSet.add(extractDevice(deviceInformation));
        }
        return hashSet;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AccessoryInquiryRecord.class == obj.getClass()) {
            return Objects.equals(this.devices, ((AccessoryInquiryRecord) obj).devices);
        }
        return false;
    }

    public Set<com.amazon.alexa.accessory.repositories.device.v2.Device> getDevices() {
        return Collections.unmodifiableSet(this.devices);
    }

    public int hashCode() {
        return Objects.hash(this.devices);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DefaultAccessoryInquiryRecord\n{\n\tdevices: ");
        outline107.append(Arrays.toString(this.devices.toArray()));
        outline107.append("\n}\n");
        return outline107.toString();
    }

    public AccessoryInquiryRecord(List<com.amazon.alexa.accessory.repositories.device.v2.Device> list) {
        Preconditions.notNull(list, "devices");
        this.devices = new HashSet(list);
    }

    private static Set<com.amazon.alexa.accessory.repositories.device.v2.Device> extractDevices(DeviceGroup deviceGroup) {
        HashSet hashSet = new HashSet(deviceGroup.getDevices().size());
        hashSet.addAll(deviceGroup.getDevices());
        return hashSet;
    }

    public AccessoryInquiryRecord(DeviceGroup deviceGroup) {
        Preconditions.notNull(deviceGroup, "deviceGroup");
        this.devices = extractDevices(deviceGroup);
    }
}
