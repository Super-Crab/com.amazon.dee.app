package com.amazon.whisperjoin.softap;

import com.amazon.whisperjoin.softap.callbacks.DeviceConnectionEstablishedCallback;
import com.amazon.whisperjoin.softap.callbacks.DeviceConnectionStateChangedListener;
import com.amazon.whisperjoin.softap.callbacks.OnDeviceDetailsFetchedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnProvisioningDataSavedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnRegistrationTokenSavedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnWifiConfigurationSavedCallback;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.amazon.whisperjoin.softap.pojos.RegistrationToken;
import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
/* loaded from: classes13.dex */
public interface SoftApActionsController {
    void disconnect();

    void establishNetworkConnection(String str, DeviceConnectionEstablishedCallback deviceConnectionEstablishedCallback, DeviceConnectionStateChangedListener deviceConnectionStateChangedListener);

    void establishNetworkConnection(String str, String str2, DeviceConnectionEstablishedCallback deviceConnectionEstablishedCallback, DeviceConnectionStateChangedListener deviceConnectionStateChangedListener);

    void getDeviceDetails(OnDeviceDetailsFetchedCallback onDeviceDetailsFetchedCallback);

    void provisionDevice(WifiConfiguration wifiConfiguration, OnWifiConfigurationSavedCallback onWifiConfigurationSavedCallback);

    void saveProvisioningData(ProvisioningData provisioningData, OnProvisioningDataSavedCallback onProvisioningDataSavedCallback);

    void saveRegistrationToken(RegistrationToken registrationToken, OnRegistrationTokenSavedCallback onRegistrationTokenSavedCallback);
}
