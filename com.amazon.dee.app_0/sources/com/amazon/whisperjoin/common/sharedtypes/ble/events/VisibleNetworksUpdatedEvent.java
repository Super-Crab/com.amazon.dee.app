package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResultCollection;
/* loaded from: classes13.dex */
public class VisibleNetworksUpdatedEvent extends ProvisionableEvent<WifiScanResultCollection> {
    public VisibleNetworksUpdatedEvent(WifiScanResultCollection wifiScanResultCollection) {
        super(ProvisionableEvents.WIFI_VISIBLE_NETWORKS_UPDATED_EVENT_UUID, wifiScanResultCollection);
    }
}
