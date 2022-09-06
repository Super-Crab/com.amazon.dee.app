package com.amazon.whisperjoin.provisionerSDK.data.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.GenericDeviceStatus;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import java.util.Collection;
/* loaded from: classes13.dex */
public class AmazonWifiDeviceStatus extends GenericDeviceStatus<WifiConnectionDetails, CBLRegistrationDetails> {
    public AmazonWifiDeviceStatus(WifiConnectionDetails wifiConnectionDetails, Collection<CBLRegistrationDetails> collection) {
        super(wifiConnectionDetails, collection);
    }
}
