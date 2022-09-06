package com.amazon.whisperjoin.deviceprovisioningservice.wifi;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetwork;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetworks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes13.dex */
public class AvailableWifiNetworksBuilder {
    private static final String TAG = "AvailableWifiNetworksBuilder";
    private WifiNetwork currentNetwork = null;
    private List<AvailableWifiNetwork> mConfiguredNetworks;
    private List<AvailableWifiNetwork> mUnrecognizedNetworks;
    private Collection<WifiScanResult> visibleNetworksCollection;
    private List<WifiConfiguration> wifiLocker;

    private boolean areEqual(WifiNetwork wifiNetwork, WifiNetwork wifiNetwork2) {
        return wifiNetwork.getSsid().equals(wifiNetwork2.getSsid()) && wifiNetwork.getKeyManagement().equals(wifiNetwork2.getKeyManagement());
    }

    private WifiConfiguration findConfigurationMatchingScanResult(WifiScanResult wifiScanResult, List<WifiConfiguration> list) {
        for (WifiConfiguration wifiConfiguration : list) {
            if (areEqual(wifiConfiguration, wifiScanResult)) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    private AvailableWifiNetwork getCurrentNetworkConfigFromLocker(WifiNetwork wifiNetwork) {
        if (wifiNetwork == null) {
            return null;
        }
        for (AvailableWifiNetwork availableWifiNetwork : this.mConfiguredNetworks) {
            if (areEqual(availableWifiNetwork, wifiNetwork)) {
                return availableWifiNetwork;
            }
        }
        return null;
    }

    private AvailableWifiNetwork getPreferredNetwork(@Nullable WifiNetwork wifiNetwork) {
        AvailableWifiNetwork currentNetworkConfigFromLocker = getCurrentNetworkConfigFromLocker(wifiNetwork);
        return (currentNetworkConfigFromLocker != null || this.mConfiguredNetworks.size() <= 0) ? currentNetworkConfigFromLocker : this.mConfiguredNetworks.get(0);
    }

    private void processAvailableNetworks(List<WifiConfiguration> list, List<WifiScanResult> list2) {
        Collections.sort(list2, Collections.reverseOrder(new NetworkPriorityComparator()));
        this.mConfiguredNetworks = new ArrayList();
        this.mUnrecognizedNetworks = new ArrayList();
        for (WifiScanResult wifiScanResult : list2) {
            WifiConfiguration findConfigurationMatchingScanResult = findConfigurationMatchingScanResult(wifiScanResult, list);
            if (findConfigurationMatchingScanResult != null) {
                this.mConfiguredNetworks.add(new AvailableWifiNetwork(wifiScanResult, findConfigurationMatchingScanResult));
            } else {
                this.mUnrecognizedNetworks.add(new AvailableWifiNetwork(wifiScanResult));
            }
        }
    }

    public AvailableWifiNetworks createAvailableWifiNetworks() {
        if (this.wifiLocker != null) {
            Collection<WifiScanResult> collection = this.visibleNetworksCollection;
            if (collection != null) {
                processAvailableNetworks(this.wifiLocker, new ArrayList(collection));
                return new AvailableWifiNetworks(this.mConfiguredNetworks, this.mUnrecognizedNetworks, getPreferredNetwork(this.currentNetwork));
            }
            throw new IllegalArgumentException("VisibleNetworks can't be null");
        }
        throw new IllegalArgumentException("WifiLocker can't be null");
    }

    public AvailableWifiNetworksBuilder setCurrentNetwork(WifiNetwork wifiNetwork) {
        this.currentNetwork = wifiNetwork;
        return this;
    }

    public AvailableWifiNetworksBuilder setVisibleNetworksCollection(Collection<WifiScanResult> collection) {
        this.visibleNetworksCollection = collection;
        return this;
    }

    public AvailableWifiNetworksBuilder setWifiLocker(List<WifiConfiguration> list) {
        this.wifiLocker = list;
        return this;
    }
}
