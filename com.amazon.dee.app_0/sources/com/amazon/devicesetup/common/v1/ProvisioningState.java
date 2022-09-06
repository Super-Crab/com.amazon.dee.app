package com.amazon.devicesetup.common.v1;
/* loaded from: classes12.dex */
public class ProvisioningState {
    public static final String DONE = "DONE";
    public static final String TOKEN_REDEEMED = "TOKEN_REDEEMED";
    public static final String NOT_PROVISIONED = "NOT_PROVISIONED";
    public static final String START_PROVISIONING = "START_PROVISIONING";
    public static final String START_PIN_BASED_SETUP = "START_PIN_BASED_SETUP";
    public static final String POST_WIFI_SCAN_DATA = "POST_WIFI_SCAN_DATA";
    public static final String GET_WIFI_LIST = "GET_WIFI_LIST";
    public static final String COMPUTE_CONFIGURATION = "COMPUTE_CONFIGURATION";
    public static final String CONNECTING_TO_USER_NETWORK = "CONNECTING_TO_USER_NETWORK";
    public static final String CONNECTED_TO_USER_NETWORK = "CONNECTED_TO_USER_NETWORK";
    private static final String[] values = {NOT_PROVISIONED, START_PROVISIONING, START_PIN_BASED_SETUP, POST_WIFI_SCAN_DATA, GET_WIFI_LIST, COMPUTE_CONFIGURATION, CONNECTING_TO_USER_NETWORK, CONNECTED_TO_USER_NETWORK, "TOKEN_REDEEMED", "DONE"};

    private ProvisioningState() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
