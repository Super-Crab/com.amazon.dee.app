package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi;

import com.amazon.devicesetup.common.v1.WifiCredentials;
import com.amazon.devicesetup.common.v1.WifiScanData;
import com.amazon.devicesetupservice.v1.GetWiFiNetworksInput;
import com.amazon.devicesetupservice.v1.GetWiFiNetworksOutput;
import com.amazon.devicesetupservice.v1.WifiNetworkDetail;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResult;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResultCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.util.dss.DSSTypesHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.AvailableWifiNetworksBuilder;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetwork;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetworks;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/* loaded from: classes13.dex */
public class GetAvailableWifiNetworksFromDSS implements GetAvailableWifiNetworksProvider {
    private static final String TAG = "GetAvailableWifiNetworksFromDSS";
    private CurrentWifiNetworkProvider mCurrentWifiNetworkProvider;
    private DSSClient mDSSClient;
    private ProvisioningMethod mProvisioningMethod;
    private TrustProvider.TrustState mTrustState;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.GetAvailableWifiNetworksFromDSS$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.OTHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public GetAvailableWifiNetworksFromDSS(DSSClient dSSClient, CurrentWifiNetworkProvider currentWifiNetworkProvider, ProvisioningMethod provisioningMethod, TrustProvider.TrustState trustState) {
        this.mDSSClient = dSSClient;
        this.mCurrentWifiNetworkProvider = currentWifiNetworkProvider;
        this.mProvisioningMethod = provisioningMethod;
        this.mTrustState = trustState;
    }

    private AvailableWifiNetwork createAvailableWifiNetwork(WifiScanData wifiScanData, WifiCredentials wifiCredentials) {
        WifiKeyManagement valueOf;
        WifiConfiguration createOpenWifiConfiguration;
        String ssid = wifiScanData.getSsid();
        String securityProtocol = wifiScanData.getSecurityProtocol();
        if (securityProtocol.equals("OPEN")) {
            valueOf = WifiKeyManagement.NONE;
        } else {
            valueOf = WifiKeyManagement.valueOf(securityProtocol);
        }
        WifiScanResult wifiScanResult = new WifiScanResult(ssid, valueOf, wifiScanData.getFrequency(), wifiScanData.getRssi());
        if (wifiCredentials == null) {
            return new AvailableWifiNetwork(wifiScanResult);
        }
        int ordinal = valueOf.ordinal();
        if (ordinal == 0) {
            createOpenWifiConfiguration = WifiConfiguration.createOpenWifiConfiguration(ssid);
        } else if (ordinal != 1) {
            createOpenWifiConfiguration = ordinal != 2 ? null : WifiConfiguration.createWepWifiConfiguration(ssid, wifiCredentials.getKey());
        } else {
            createOpenWifiConfiguration = WifiConfiguration.createWpaWifiConfiguration(ssid, wifiCredentials.getKey());
        }
        if (createOpenWifiConfiguration == null) {
            String str = TAG;
            WJLog.d(str, "Failed to create wifi configuration for credentials for SSID " + ssid + " keyMgmt " + valueOf);
            return null;
        }
        return new AvailableWifiNetwork(wifiScanResult, createOpenWifiConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AvailableWifiNetworks createAvailableWifiNetworksFromDSSOutput(GetWiFiNetworksOutput getWiFiNetworksOutput) {
        AvailableWifiNetwork availableWifiNetwork;
        ArrayList arrayList = new ArrayList(getWiFiNetworksOutput.getWifiConfigList().size());
        for (WifiNetworkDetail wifiNetworkDetail : getWiFiNetworksOutput.getWifiConfigList()) {
            AvailableWifiNetwork createAvailableWifiNetwork = createAvailableWifiNetwork(wifiNetworkDetail.getScanData(), wifiNetworkDetail.getCredential());
            if (createAvailableWifiNetwork == null) {
                String str = TAG;
                WJLog.d(str, "Failed to create available wifi network for details: " + wifiNetworkDetail);
            } else {
                arrayList.add(createAvailableWifiNetwork);
            }
        }
        ArrayList arrayList2 = new ArrayList(getWiFiNetworksOutput.getUnknownWifiNetworkList().size());
        Iterator<WifiScanData> it2 = getWiFiNetworksOutput.getUnknownWifiNetworkList().iterator();
        while (true) {
            availableWifiNetwork = null;
            if (!it2.hasNext()) {
                break;
            }
            WifiScanData next = it2.next();
            AvailableWifiNetwork createAvailableWifiNetwork2 = createAvailableWifiNetwork(next, null);
            if (createAvailableWifiNetwork2 == null) {
                String str2 = TAG;
                WJLog.d(str2, "Failed to create available wifi network for scan data: " + next);
            } else {
                arrayList2.add(createAvailableWifiNetwork2);
            }
        }
        WifiNetworkDetail preferredWifiConfig = getWiFiNetworksOutput.getPreferredWifiConfig();
        if (preferredWifiConfig != null) {
            availableWifiNetwork = createAvailableWifiNetwork(preferredWifiConfig.getScanData(), preferredWifiConfig.getCredential());
        }
        return new AvailableWifiNetworks(arrayList, arrayList2, availableWifiNetwork);
    }

    private GetWiFiNetworksInput createGetWiFiNetworksInput(WifiScanResultCollection wifiScanResultCollection, String str, String str2, WifiConnectionDetails wifiConnectionDetails, String str3) {
        GetWiFiNetworksInput getWiFiNetworksInput = new GetWiFiNetworksInput();
        getWiFiNetworksInput.setAuthMaterialIndex(str);
        getWiFiNetworksInput.setProductIndex(str2);
        getWiFiNetworksInput.setTrustMethod(DSSTypesHelper.getTrustMethodFromTrustState(this.mTrustState));
        getWiFiNetworksInput.setKeyExchangeMethod(DSSTypesHelper.getKeyExchangeMethodFromTrustState(this.mTrustState));
        getWiFiNetworksInput.setProvisioningMethod(this.mProvisioningMethod.toString());
        getWiFiNetworksInput.setCurrentWifiNetwork(DSSTypesHelper.createWifiScanDataFromWifiNetwork(this.mCurrentWifiNetworkProvider.getCurrentWifiNetwork()));
        getWiFiNetworksInput.setWifiScanDataList(DSSTypesHelper.createWifiScanDataListFromWifiScanResults(wifiScanResultCollection));
        getWiFiNetworksInput.setProvisioneeLastConnectedNetwork(DSSTypesHelper.createWifiScanDataFromWifiConnectionDetails(wifiConnectionDetails));
        getWiFiNetworksInput.setSessionToken(str3);
        return getWiFiNetworksInput;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.GetAvailableWifiNetworksProvider
    public Single<AvailableWifiNetworks> getAvailableWifiNetworks(WJProvisionee wJProvisionee, WifiScanResultCollection wifiScanResultCollection, DeviceDetails deviceDetails, String str) {
        WhisperJoinPeripheralDeviceDetails peripheralDeviceDetails = wJProvisionee.getPeripheralDeviceDetails();
        GetWiFiNetworksInput createGetWiFiNetworksInput = createGetWiFiNetworksInput(wifiScanResultCollection, peripheralDeviceDetails.getDeviceIdentity(), peripheralDeviceDetails.getProductIndex(), deviceDetails.getLastConnectionWifiDetails(), str);
        WJLog.d(TAG, "Getting WifiNetworks from DSS");
        return this.mDSSClient.getWifiNetworks(createGetWiFiNetworksInput).map(new Function<GetWiFiNetworksOutput, AvailableWifiNetworks>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.GetAvailableWifiNetworksFromDSS.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public AvailableWifiNetworks mo10358apply(GetWiFiNetworksOutput getWiFiNetworksOutput) throws Exception {
                return GetAvailableWifiNetworksFromDSS.this.createAvailableWifiNetworksFromDSSOutput(getWiFiNetworksOutput);
            }
        }).onErrorReturnItem(new AvailableWifiNetworksBuilder().setWifiLocker(Collections.emptyList()).setVisibleNetworksCollection(wifiScanResultCollection.getSetCollection()).setCurrentNetwork(this.mCurrentWifiNetworkProvider.getCurrentWifiNetwork()).createAvailableWifiNetworks());
    }
}
