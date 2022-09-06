package com.amazon.alexa.devicesetup.sdk.whisperjoin;

import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ClientProvisioningDataModel;
/* loaded from: classes7.dex */
public interface DeviceSetupPresenter {
    public static final String CONFIG_BARCODE_STRING = "CONFIG_BARCODE_STRING";
    public static final String CONFIG_BUILD_STAGE = "CONFIG_BUILD_STAGE";
    public static final String CONFIG_COUNTRY_CODE = "CONFIG_COUNTRY_CODE";
    public static final String CONFIG_DEBUG = "CONFIG_DEBUG";
    public static final String CONFIG_DEVICE_TYPE = "CONFIG_DEVICE_TYPE";
    public static final String CONFIG_LANGUAGE_LOCALE = "CONFIG_LANGUAGE_LOCALE";
    public static final String CONFIG_MARKET_PLACE_ID = "CONFIG_MARKET_PLACE_ID";
    public static final String CONFIG_PROVISIONER_VERSION_NUMBER = "PROVISIONER_VERSION_NUMBER";
    public static final String DEVICE_ADVERTISED_NAME = "advertisedName";
    public static final String DEVICE_IDENTITY = "deviceIdentity";
    public static final String DEVICE_PRODUCT_INDEX = "productIndex";
    public static final String DISCOVERED_DEVICES = "discoveredDevices";
    public static final String ERROR = "error";
    public static final String ERROR_TYPE = "errorType";
    public static final String EVENT_DISCOVERED_DEVICES = "ffs::DiscoveredDevices";
    public static final String EVENT_ERROR_UNEXPECTED = "error::";
    public static final String EVENT_PROVISIONING_DETAILS = "ffs::ProvisioningDetails";
    public static final String EVENT_PROVISIONING_EVENT_CALLBACK_COMPLETE = "ffs::zerotouchsetup::OnComplete";
    public static final String EVENT_PROVISIONING_EVENT_CALLBACK_ERROR = "ffs::zerotouchsetup::OnError";
    public static final String EVENT_PROVISIONING_EVENT_CALLBACK_NEXT = "ffs::zerotouchsetup::OnNext";
    public static final String EVENT_SETUP_COMPLETE = "ffs::SetupComplete";
    public static final String EVENT_SETUP_FAILURE = "ffs::SetupFailure";
    public static final String EVENT_SETUP_IDLE = "ffs::SetupIdle";
    public static final String EVENT_SETUP_IN_PROGRESS = "ffs::SetupInProgress";
    public static final String EVENT_WIFI_CONNECTION_ERROR = "ffs::WifiConnectionError";
    public static final String PROGRESS_STATE = "progressState";
    public static final String PROVISIONABLE_DEVICE_DETAILS = "deviceDetails";
    public static final String PROVISIONABLE_DEVICE_DETAILS_DEVICE_FIRMWARE_REVISION = "deviceFirmwareRevision";
    public static final String PROVISIONABLE_DEVICE_DETAILS_DEVICE_HARDWARE_REVISION = "deviceHardwareRevision";
    public static final String PROVISIONABLE_DEVICE_DETAILS_DEVICE_MODEL_NUMBER = "deviceModelNumber";
    public static final String PROVISIONABLE_DEVICE_DETAILS_DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
    public static final String PROVISIONABLE_DEVICE_DETAILS_MANUFACTURER = "manufacturer";
    public static final String PROVISIONER_APP_NAME = "AlexaMobileAndroid";
    public static final String PROVISIONER_VERSION_NUMBER = "1.0";
    public static final String PROVISIONING_CONFIG = "PROVISIONING_CONFIG";
    public static final String WIFI_CONFIGURED_NETWORKS = "configuredNetworks";
    public static final String WIFI_FREQUENCY_BAND = "frequencyBand";
    public static final String WIFI_HAS_CONFIGURATION = "hasConfiguration";
    public static final String WIFI_IS_HIDDEN = "isHidden";
    public static final String WIFI_KEY_MANAGEMENT = "keyManagement";
    public static final String WIFI_PRIORITY = "priority";
    public static final String WIFI_PSK = "pskKey";
    public static final String WIFI_SIGNAL_STRENGTH = "signalStrength";
    public static final String WIFI_SSID = "ssid";
    public static final String WIFI_UNRECOGNIZED_NETWORKS = "unrecognizedNetworks";
    public static final String WIFI_WEP_KEY = "wepKey";
    public static final String WJ_ERROR_DETAILS = "wjErrorDetails";
    public static final String WJ_ERROR_DOMAIN = "wjErrorDomain";
    public static final String WJ_ERROR_RESOLUTION = "wjErrorResolution";
    public static final String WJ_ERROR_TYPE = "wjErrorType";

    void attachView();

    void chooseDevice(DiscoveredDevice discoveredDevice);

    void chooseDevice(String str, String str2);

    void detachView();

    void discoverDevices();

    String getLanguageLocale();

    void provisionDevice(ClientProvisioningDataModel clientProvisioningDataModel);

    void refreshAvailableNetworks();

    void terminateSetup();
}
