package com.amazon.whisperjoin.deviceprovisioningservice.util;

import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import java.util.Locale;
/* loaded from: classes13.dex */
public class ProvisioneeInfoMessage {
    public static String create(WJProvisionee wJProvisionee) {
        return create(wJProvisionee == null ? null : wJProvisionee.getPeripheralDeviceDetails());
    }

    public static String create(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        if (whisperJoinPeripheralDeviceDetails == null) {
            return String.format(Locale.ENGLISH, "Provisionee: [NONE]", new Object[0]);
        }
        return String.format(Locale.ENGLISH, "Provisionee: [%s, %s, %s]", whisperJoinPeripheralDeviceDetails.getFriendlyName(), whisperJoinPeripheralDeviceDetails.getProductIndex(), whisperJoinPeripheralDeviceDetails.getDeviceIdentity());
    }
}
