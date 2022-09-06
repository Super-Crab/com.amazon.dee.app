package com.amazon.whisperjoin.deviceprovisioningservice.arcus.data;
/* loaded from: classes13.dex */
public class ArcusConstants {
    public static final String APP_CONFIGURATION_ID = "arn:aws:remote-config:us-west-2:044929725723:appConfig:acx30c6w";
    public static final String ARCUS_UPDATE_INTENT_ACTION = "com.amazon.whisperjoin.deviceprovisioningservice.ArcusUpdate.Action";
    public static final String ARCUS_UPDATE_INTENT_PERMISSION = "com.amazon.whisperjoin.deviceprovisioningservice.ArcusUpdate.Permission";

    /* loaded from: classes13.dex */
    public static class InputAttribute {
        public static final String APPLICATION = "application";
        public static final String APPLICATION_VERSION = "applicationVersion";
        public static final String DEVICE_MODEL = "deviceModel";
        public static final String DIRECTED_ID = "directedId";
        public static final String DSN = "dsn";
        public static final String FIRMWARE_VERSION = "firmwareVersion";
        public static final String MANUFACTURER = "manufacturer";
        public static final String MARKETPLACE = "marketplace";
    }

    /* loaded from: classes13.dex */
    public static class Settings {
        static final String DEFAULT_ARCUS_SYNC_PERIOD = "P1D";
        static final boolean DEFAULT_ENABLE_PERIOD_SYNC = false;
        static final boolean DEFAULT_ENABLE_PHILIPS_BLE_WORKFLOW = false;
        static final boolean DEFAULT_ENABLE_ZIGBEE_SYNC_SERVICE = false;
        static final boolean DEFAULT_FFR_PROVISIONER_ENABLED = true;
        static final String DEFAULT_JOB_SCHEDULE_INITIAL_BACKOFF = "PT15M";
        static final String DEFAULT_JOB_SCHEDULE_MAX_LATENCY = "PT5M";
        static final String DEFAULT_JOB_SCHEDULE_MIN_LATENCY = "PT30S";
        static final String DEFAULT_OVERACTIVE_BUCKET_LENGTH_PERIOD = "PT10S";
        static final String DEFAULT_OVERACTIVE_MONITORING_WINDOW_PERIOD = "PT3M";
        static final double DEFAULT_OVERACTIVE_THRESHOLD_PERCENTAGE = 50.0d;
        static final int DEFAULT_RETRY_BACKOFF_ATTEMPT_COUNT = 5;
        static final String DEFAULT_RETRY_BACKOFF_BASE_DURATION = "PT5M";
        static final boolean DEFAULT_USE_V2_API = false;
        static final String DEFAULT_ZIGBEE_CACHE_EXPIRATION = "PT1H";
        static final String DEFAULT_ZIGBEE_SYNC_PERIOD = "P1D";
        static final String DEFAULT_ZTW_DSS_FAILED_MONITOR_WINDOW_PERIOD = "PT1M";
        static final long DEFAULT_ZTW_DSS_FAILED_THRESHOLD = 4;
        static final String DEFAULT_ZTW_FAILED_MONITOR_WINDOW_PERIOD = "PT1M";
        static final long DEFAULT_ZTW_FAILED_THRESHOLD = 3;
        static final String JSON_KEY_ARCUS_SETTINGS = "arcusSettings";
        static final String JSON_KEY_ARCUS_SETTINGS_SYNC_PERIOD = "arcusSyncPeriod";
        static final String JSON_KEY_ENABLE_PHILIPS_BLE_WORKFLOW = "enablePhilipsBleWorkflow";
        static final String JSON_KEY_ENABLE_SYNC_SERVICE = "enableZigbeeSyncService";
        static final String JSON_KEY_FFR_PROVISIONER = "provisioner";
        static final String JSON_KEY_FFR_PROVISIONER_ENABLED = "ffrEnabled";
        static final String JSON_KEY_FFR_SETTINGS = "frustrationFreeReconnectSettings";
        static final String JSON_KEY_JOB_SCHEDULE_INITIAL_BACKOFF_DURATION = "jobScheduleInitialBackOffDuration";
        static final String JSON_KEY_JOB_SCHEDULE_MAX_LATENCY_DURATION = "jobScheduleMaxLatencyDuration";
        static final String JSON_KEY_JOB_SCHEDULE_MIN_LATENCY_DURATION = "jobScheduleMinLatencyDuration";
        static final String JSON_KEY_OVERACTIVE_BUCKET_LENGTH_PERIOD = "bucketLengthPeriod";
        static final String JSON_KEY_OVERACTIVE_MONITORING_WINDOW_PERIOD = "monitoringWindowPeriod";
        static final String JSON_KEY_OVERACTIVE_THRESHOLD_PERCENTAGE = "thresholdPercentage";
        static final String JSON_KEY_RETRY_BACKOFF_ATTEMPT_COUNT = "backoffAttemptCount";
        static final String JSON_KEY_RETRY_BACKOFF_BASE_DURATION = "backoffBaseDuration";
        static final String JSON_KEY_THROTTLE_SETTINGS = "throttleSettings";
        static final String JSON_KEY_THROTTLE_SETTINGS_BACKOFF = "retryBackoff";
        static final String JSON_KEY_THROTTLE_SETTINGS_OVERACTIVE = "overactive";
        static final String JSON_KEY_THROTTLE_SETTINGS_ZTW = "zeroTouchWorkflow";
        static final String JSON_KEY_USE_ENABLE_PERIODIC_SYNC = "enablePeriodicSync";
        static final String JSON_KEY_USE_V2_API = "useV2API";
        static final String JSON_KEY_ZERO_TOUCH_PROVISIONING_SETTINGS = "zeroTouchProvisioningSettings";
        static final String JSON_KEY_ZIGBEE_CACHE_EXPIRATION_PERIOD = "zigbeeCacheExpirationPeriod";
        static final String JSON_KEY_ZIGBEE_SETTINGS = "zigbeeSettings";
        static final String JSON_KEY_ZIGBEE_SYNC_PERIOD = "zigbeeSyncPeriod";
        static final String JSON_KEY_ZTW_DSS_FAILED_MONITOR_WINDOW_PERIOD = "dssFailedMonitorWindowPeriod";
        static final String JSON_KEY_ZTW_DSS_FAILED_THRESHOLD = "dssFailedThreshold";
        static final String JSON_KEY_ZTW_FAILED_MONITOR_WINDOW_PERIOD = "failedMonitorWindowPeriod";
        static final String JSON_KEY_ZTW_FAILED_THRESHOLD = "failedThreshold";
    }
}
