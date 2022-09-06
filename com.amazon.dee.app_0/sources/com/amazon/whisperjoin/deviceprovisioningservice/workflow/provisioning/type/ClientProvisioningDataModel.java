package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.Map;
/* loaded from: classes13.dex */
public class ClientProvisioningDataModel {
    private final Map<String, String> mCustomConfiguration;
    private final boolean mSaveNetworkToLocker;
    private final WifiConfiguration mWifiConfiguration;

    public ClientProvisioningDataModel(WifiConfiguration wifiConfiguration, boolean z) {
        this(wifiConfiguration, z, Collections.EMPTY_MAP);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ClientProvisioningDataModel.class != obj.getClass()) {
            return false;
        }
        ClientProvisioningDataModel clientProvisioningDataModel = (ClientProvisioningDataModel) obj;
        return this.mSaveNetworkToLocker == clientProvisioningDataModel.mSaveNetworkToLocker && Objects.equal(this.mWifiConfiguration, clientProvisioningDataModel.mWifiConfiguration) && Objects.equal(this.mCustomConfiguration, clientProvisioningDataModel.mCustomConfiguration);
    }

    public Map<String, String> getCustomConfiguration() {
        return this.mCustomConfiguration;
    }

    public WifiConfiguration getWifiConfiguration() {
        return this.mWifiConfiguration;
    }

    public int hashCode() {
        return Objects.hashCode(this.mWifiConfiguration, Boolean.valueOf(this.mSaveNetworkToLocker), this.mCustomConfiguration);
    }

    public boolean saveNetworkToLocker() {
        return this.mSaveNetworkToLocker;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mWifiConfiguration", this.mWifiConfiguration).add("mSaveNetworkToLocker", this.mSaveNetworkToLocker).add("mCustomConfiguration", this.mCustomConfiguration).toString();
    }

    public ClientProvisioningDataModel(WifiConfiguration wifiConfiguration, boolean z, Map<String, String> map) {
        if (wifiConfiguration != null) {
            if (map != null) {
                this.mWifiConfiguration = wifiConfiguration;
                this.mSaveNetworkToLocker = z;
                this.mCustomConfiguration = map;
                return;
            }
            throw new IllegalArgumentException("customConfiguration can not be null");
        }
        throw new IllegalArgumentException("wifiConfiguration can not be null");
    }
}
