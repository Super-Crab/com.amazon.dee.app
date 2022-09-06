package com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit;
/* loaded from: classes13.dex */
class DSSConstants {

    /* loaded from: classes13.dex */
    static class ApiPaths {
        static final String COMPUTE_CONFIGURATION_DATA = "/v1/computeConfigurationData";
        static final String CREATE_AP_FOR_WIFI_PROVISIONEE = "/v1/createAPForWifiProvisionee";
        static final String DISCOVERED_PROVISIONABLE_DEVICE = "/v1/discoveredProvisionableDevice";
        static final String DISCOVERED_PROVISIONEE_DEVICE = "/v1/discoveredProvisionee";
        static final String FINALIZE_ECDHE_AUTH_SESSION = "/v1/finalizeEcdheAuthenticationSession";
        static final String GET_CUSTOMER_DEVICES_CREDENTIALS = "/v1/getCustomerDevicesCredentials";
        static final String GET_CUSTOMER_DEVICES_CREDENTIALS_V2 = "/v2/getCustomerDevicesCredentials";
        static final String GET_CUSTOMER_PROVISIONEES_SETUP_STATUS = "/v1/getCustomerProvisioneesSetupStatus";
        static final String GET_DEVICE_REGISTRATION_STATUS = "/v1/getDeviceRegistrationStatus";
        static final String GET_FFS_WHITELIST_POLICY = "fetchConfig";
        static final String GET_WIFI_NETWORKS = "/v1/GetWiFiNetworks";
        static final String GET_WIFI_SYNC_AUTH_TOKEN = "/v1/getWifiSyncAuthToken";
        static final String RECORD_DEVICE_POSSESSION_INTENT_INNER_BARCODE = "/v1/recordDevicePossessionIntentInnerBarcode";
        static final String SAVE_WIFI_NETWORK = "/v1/SaveWifiNetwork";
        static final String START_ECDHE_AUTH_SESSION = "/v1/startEcdheAuthenticationSession";
        static final String VALIDATE_WIFI_SYNC_AUTH_TOKEN = "/v1/validateWifiSyncAuthToken";

        ApiPaths() {
        }
    }

    /* loaded from: classes13.dex */
    static class Endpoints {
        static final String API_GATEWAY_GAMMA = "https://re8sfystqa.execute-api.us-east-1.amazonaws.com/gamma/";
        static final String API_GATEWAY_PROD = "https://wl.amazon-dss.com/";
        static final String DSS_NA_GAMMA = "https://dss-na-gamma.amazon.com";
        static final String DSS_NA_PROD = "https://dss-na.amazon.com";

        Endpoints() {
        }
    }

    /* loaded from: classes13.dex */
    static class Headers {
        static final String ACCEPT_JSON = "Accept: application/json";
        static final String ACCEPT_LANUAGE_EN_US = "Accept-Language: en-US";
        static final String ACCESS_TOKEN = "x-amz-access-token";
        static final String CONTENT_TYPE_JSON = "Content-Type: application/json";
        static final String RESPONSE_REDIRECT_ENDPOINT = "x-amzn-endpoint";
        static final String RESPONSE_REQUEST_ID = "x-amzn-RequestId";
        static final String RESPONSE_RETRY_AFTER = "Retry-After";

        Headers() {
        }
    }

    private DSSConstants() {
    }
}
