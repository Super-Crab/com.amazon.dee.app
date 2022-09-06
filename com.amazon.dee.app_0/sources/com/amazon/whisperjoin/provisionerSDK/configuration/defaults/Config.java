package com.amazon.whisperjoin.provisionerSDK.configuration.defaults;

import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class Config {

    /* loaded from: classes13.dex */
    public static class Bluetooth {
        public static final int BLE_SCAN_MODE = 1;
    }

    /* loaded from: classes13.dex */
    public static class Overactive {
        public static final long BUCKET_LENGTH_MS = TimeUnit.SECONDS.toMillis(10);
        public static final long MONITORING_WINDOW_MS = TimeUnit.MINUTES.toMillis(3);
        public static final double THRESHOLD_PERCENTAGE = 50.0d;
    }

    /* loaded from: classes13.dex */
    public static class Radio {
        public static final long BLE_MINIMUM_SIGNAL_STRENGTH = -100;
        public static final long BLE_RADIO_WAIT_TIME_MILLISECONDS = 10;
    }

    /* loaded from: classes13.dex */
    public static class Transport {
        public static final int MAX_MESSAGE_SIZE_BYTES = 8192;
        public static final int OPERATION_RETRIES = 3;
        public static final long OPERATION_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(20);
    }
}
