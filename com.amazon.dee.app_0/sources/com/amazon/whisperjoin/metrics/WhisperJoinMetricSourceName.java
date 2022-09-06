package com.amazon.whisperjoin.metrics;

import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedCredentialsSyncService;
/* loaded from: classes13.dex */
public enum WhisperJoinMetricSourceName {
    CONNECT_TO_DEVICE("ConnectingToDevice"),
    GET_DEVICE_DETAILS("GetDeviceDetails"),
    GET_VISIBLE_NETWORKS("GetVisibleNetworks"),
    INITIATE_WIFI_SCAN("InitiateWifiScan"),
    SAVE_WIFI_CONFIGURATION("SaveWifiConiguration"),
    SET_REGISTRATION_TOKEN("SetRegistrationToken"),
    START_PROVISIONING("StartProvisioning"),
    COMPLETE_PROVISIONING("CompleteProvisioning"),
    SAVE_CONFIGURATION_MAP("SaveConfigurationMap"),
    GET_WIFI_CONNECTION_DETAIL("GetWifiConnectionDetail"),
    GET_BLE_REGISTRATION_STATUS("GetBleRegistrationStatus"),
    DISCONNECT_FROM_DEVICE("DisconnectFromDevice"),
    GET_PROVISIONABLE_DEVICE_DETAILS("GetProvisionableDeviceDetails"),
    GET_PERIPHERAL_DEVICE_DETAILS("GetPeripheralDeviceDetails"),
    SAVE_LOCALE("SaveLocale"),
    GET_DISCOVERED_DEVICES_LIST_OPERATION("GetDiscoveredDevicesListOperation"),
    START_DISCOVERY_OPERATION("StartDiscoveryOperation"),
    STOP_DISCOVERY_OPERATION("StopDiscoveryOperation"),
    VERIFY_PROVISIONING_OPERATION("VerifyProvisioningOperation"),
    CONNECT_TO_DEVICE_OPERATION("ConnectToDeviceOperation"),
    PROVISION_DEVICE_OPERATION("ProvisionDeviceOperation"),
    DSS_DISCOVERED_PROVISIONABLE_DEVICE("DssDiscoveredProvisionableDevice"),
    DSS_START_ECDHE_AUTHENTICATION_SESSION("DssStartEcdheAuthenticationSession"),
    DSS_FINALIZE_ECDHE_AUTHENTICATION_SESSION("DssFinalizeEcdheAuthenticationSession"),
    DSS_VALIDATE_WIFI_SYNC_AUTH_TOKEN("ValidateWifiSyncAuthToken"),
    DSS_GENERATE_PROVISIONING_SESSION("DssGenerateProvisioningSession"),
    BLE_START_DISCVOERY("BleStartDiscovery"),
    BLE_STOP_DISCOVERY("BleStopDiscovery"),
    BLE_START_ATTEMPTS_RETRY_HANDLER("BleStartAttemptsRetryHandler"),
    FFS_PROVISIONING_SERVICE("FFSProvisioningService"),
    DEVICE_POWER_MONITOR("DevicePowerMonitor"),
    DSHS(AssociatedCredentialsSyncService.INTENT_EXTRA_SOURCE_VAL_DSHS),
    PROVISIONING_SESSION("ProvisioningSession"),
    BEACONING_TO_CONNECTED_TO_PROVISIONER("BeaconingToConnectedToProvisioner"),
    SET_CONFIGURATION_IN_DATA_MAP("SetConfigurationInDataMap"),
    CONNECT_TO_WIFI("ConnectToWifi"),
    REGISTER_DEVICE("RegisterDevice"),
    CBL_REGISTRATION_ACCOUNT_BASED("CblRegistrationAccountBased"),
    CBL_REGISTRATION_PROFILE_BASED("CblRegistrationProfileBased");
    
    private final String mMetricName;

    WhisperJoinMetricSourceName(String str) {
        this.mMetricName = str;
    }

    public String getMetricName() {
        return this.mMetricName;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mMetricName;
    }
}
