package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResultCollection;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetworks;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes13.dex */
public interface GetAvailableWifiNetworksProvider {
    Single<AvailableWifiNetworks> getAvailableWifiNetworks(WJProvisionee wJProvisionee, WifiScanResultCollection wifiScanResultCollection, DeviceDetails deviceDetails, String str);
}
