package com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data;

import com.amazon.whisperjoin.common.sharedtypes.devices.AbstractPeripheralDeviceDetails;
/* loaded from: classes13.dex */
public class PeripheralDiscoveredEventData {
    private final AbstractPeripheralDeviceDetails mDeviceDetails;

    public PeripheralDiscoveredEventData(AbstractPeripheralDeviceDetails abstractPeripheralDeviceDetails) {
        if (abstractPeripheralDeviceDetails != null) {
            this.mDeviceDetails = abstractPeripheralDeviceDetails;
            return;
        }
        throw new IllegalArgumentException("deviceDetails can not be null");
    }

    public AbstractPeripheralDeviceDetails getDeviceDetails() {
        return this.mDeviceDetails;
    }
}
