package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
/* loaded from: classes13.dex */
public class WifiConnectionUpdatedEvent extends ProvisionableEvent<WifiConnectionDetails> {
    public WifiConnectionUpdatedEvent(WifiConnectionDetails wifiConnectionDetails) {
        super(ProvisionableEvents.WIFI_CONNECTION_DETAILS_UPDATED_EVENT_UUID, wifiConnectionDetails);
    }
}
