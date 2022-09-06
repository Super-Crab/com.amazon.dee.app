package com.amazon.device.nos;

import android.content.ComponentName;
/* loaded from: classes12.dex */
public interface NetworkOptimizationManager {
    public static final String ACTION_NOS_DATA_TRANSFER = "com.amazon.intent.action.NOS_DATA_TRANSFER";
    public static final String EXTRA_DETAIL_CODE = "extra_detail_code";
    public static final String EXTRA_REGISTRATION_ID = "extra_registration_id";
    public static final String EXTRA_RESULT_CODE = "extra_result_code";
    public static final String SYSTEM_SERVICE_KEY = "com.amazon.device.nos.NetworkOptimizationManager";
    public static final int TRANSFER_BATTERY_NOT_CHARGING = 256;
    public static final int TRANSFER_DEVICE_NOT_IDLE = 512;
    public static final int TRANSFER_IDEAL_CONDITIONS = 1;
    public static final int TRANSFER_IS_ROAMING = 32;
    public static final int TRANSFER_LOW_BANDWIDTH = 8;
    public static final int TRANSFER_LOW_BATTERY = 2;
    public static final int TRANSFER_LOW_MOBILE_SIGNAL = 4;
    public static final int TRANSFER_NO_ACTIVE_TX = 16;
    public static final int TRANSFER_NO_MOBILE_NETWORK = 128;
    public static final int TRANSFER_NO_WIFI_NETWORK = 64;
    public static final int TRANSFER_RESULT_CRITERIA_MET = 1;
    public static final int TRANSFER_RESULT_CRITERIA_NOT_MET = 2;

    void deregister(ComponentName componentName, int i);

    void deregisterAll(ComponentName componentName);

    int register(TransferCriteria transferCriteria);
}
