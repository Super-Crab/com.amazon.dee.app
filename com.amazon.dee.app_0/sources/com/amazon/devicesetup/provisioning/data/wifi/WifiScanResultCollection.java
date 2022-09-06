package com.amazon.devicesetup.provisioning.data.wifi;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.GenericTreeSetCollection;
import java.util.Collection;
/* loaded from: classes12.dex */
public class WifiScanResultCollection extends GenericTreeSetCollection<WifiScanResult> {
    public WifiScanResultCollection() {
    }

    public boolean contains(String str, WifiKeyManagement wifiKeyManagement) {
        if (this.values == null) {
            return false;
        }
        return this.values.contains(new WifiScanResult(str, wifiKeyManagement));
    }

    public WifiScanResultCollection(Collection<WifiScanResult> collection) {
        super(collection);
    }
}
