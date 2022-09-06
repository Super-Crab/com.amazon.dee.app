package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes.dex */
public final class MultiDeviceUtils {
    private MultiDeviceUtils() {
    }

    public static String extractLocaleFromFirmwareInformation(Set<Firmware.FirmwareInformation> set) throws IOException {
        ArrayList<Firmware.FirmwareInformation> arrayList = new ArrayList(set);
        Collections.sort(arrayList, $$Lambda$MultiDeviceUtils$eRCy09H6wYZO8Vrqp2hTZ2HG8Js.INSTANCE);
        for (Firmware.FirmwareInformation firmwareInformation : arrayList) {
            String locale = firmwareInformation.getLocale();
            if (!locale.isEmpty()) {
                Logger.d("Retrieved locale (%s) from FirmwareInformation.", locale);
                return locale;
            }
        }
        throw new IOException("Failed to get locale from the available firmwareInformation list.");
    }

    public static Device.DeviceInformation getDeviceInformationWithHighestDeviceId(Set<Device.DeviceInformation> set) {
        ArrayList arrayList = new ArrayList(set);
        Collections.sort(arrayList, $$Lambda$MultiDeviceUtils$NxkKehyJHYCIWA9K_1RkjwQ0.INSTANCE);
        return !arrayList.isEmpty() ? (Device.DeviceInformation) arrayList.get(0) : Device.DeviceInformation.getDefaultInstance();
    }

    public static String getDeviceTypeOfHighestDeviceId(DeviceGroup deviceGroup) {
        ArrayList arrayList = new ArrayList(deviceGroup.getDevices());
        Collections.sort(arrayList, $$Lambda$MultiDeviceUtils$PA32Pww8xmdFvyeLajHun8RI5II.INSTANCE);
        if (!arrayList.isEmpty()) {
            return ((com.amazon.alexa.accessory.repositories.device.v2.Device) arrayList.get(0)).getType();
        }
        return null;
    }

    public static Firmware.FirmwareInformation getFirmwareInformationWithLowestVersion(Set<Firmware.FirmwareInformation> set) {
        ArrayList arrayList = new ArrayList(set);
        Collections.sort(arrayList, $$Lambda$MultiDeviceUtils$TmOBtkPcAkoDneqk4u8EVNWYbK8.INSTANCE);
        return !arrayList.isEmpty() ? (Firmware.FirmwareInformation) arrayList.get(0) : Firmware.FirmwareInformation.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$extractLocaleFromFirmwareInformation$0(Firmware.FirmwareInformation firmwareInformation, Firmware.FirmwareInformation firmwareInformation2) {
        return firmwareInformation2.getDeviceId() - firmwareInformation.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getDeviceInformationWithHighestDeviceId$2(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation2.getDeviceId() - deviceInformation.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getDeviceTypeOfHighestDeviceId$3(com.amazon.alexa.accessory.repositories.device.v2.Device device, com.amazon.alexa.accessory.repositories.device.v2.Device device2) {
        return device2.getDeviceId().intValue() - device.getDeviceId().intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getFirmwareInformationWithLowestVersion$1(Firmware.FirmwareInformation firmwareInformation, Firmware.FirmwareInformation firmwareInformation2) {
        return firmwareInformation.getVersion() - firmwareInformation2.getVersion();
    }
}
