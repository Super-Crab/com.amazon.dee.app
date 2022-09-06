package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi;

import com.amazon.devicesetupservice.v1.SaveWifiNetworkInput;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.util.dss.DSSTypesHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import io.reactivex.rxjava3.core.Completable;
/* loaded from: classes13.dex */
public class SaveWifiNetworkThroughDSS {
    private static final String TAG = "SaveWifiNetworkThroughDSS";
    private DSSClient mDSSClient;
    private ProvisioningMethod mProvisioningMethod;
    private TrustProvider.TrustState mTrustState;

    public SaveWifiNetworkThroughDSS(DSSClient dSSClient, ProvisioningMethod provisioningMethod, TrustProvider.TrustState trustState) {
        this.mDSSClient = dSSClient;
        this.mProvisioningMethod = provisioningMethod;
        this.mTrustState = trustState;
    }

    private SaveWifiNetworkInput createSaveWifiNetworkInput(String str, String str2, WifiConfiguration wifiConfiguration, String str3) {
        SaveWifiNetworkInput saveWifiNetworkInput = new SaveWifiNetworkInput();
        saveWifiNetworkInput.setAuthMaterialIndex(str);
        saveWifiNetworkInput.setProductIndex(str2);
        saveWifiNetworkInput.setTrustMethod(DSSTypesHelper.getTrustMethodFromTrustState(this.mTrustState));
        saveWifiNetworkInput.setKeyExchangeMethod(DSSTypesHelper.getKeyExchangeMethodFromTrustState(this.mTrustState));
        saveWifiNetworkInput.setProvisioningMethod(this.mProvisioningMethod.toString());
        saveWifiNetworkInput.setWifiConfig(DSSTypesHelper.convertToDssWifiConfiguration(wifiConfiguration));
        saveWifiNetworkInput.setSessionToken(str3);
        return saveWifiNetworkInput;
    }

    public Completable saveWifiNetwork(WJProvisionee wJProvisionee, WifiConfiguration wifiConfiguration, String str) {
        WhisperJoinPeripheralDeviceDetails peripheralDeviceDetails = wJProvisionee.getPeripheralDeviceDetails();
        SaveWifiNetworkInput createSaveWifiNetworkInput = createSaveWifiNetworkInput(peripheralDeviceDetails.getDeviceIdentity(), peripheralDeviceDetails.getProductIndex(), wifiConfiguration, str);
        WJLog.d(TAG, "Calling DSS to save wifi network");
        return Completable.fromSingle(this.mDSSClient.saveWifiNetwork(createSaveWifiNetworkInput));
    }
}
