package com.amazon.communication.heartbeat;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class HeartbeatSettingsDefaultValues {
    private static final Map<String, Boolean> mDefaultBooleanByKey = new Hashtable<String, Boolean>() { // from class: com.amazon.communication.heartbeat.HeartbeatSettingsDefaultValues.1
        {
            put(HeartbeatSettings.IS_PROBING_CONNECTION_ENABLED_KEY, true);
        }
    };
    private static long DEFAULT_CHECK_HEARTBEAT_RESPONSE_DELAY_MILLIS = TimeUnit.SECONDS.toMillis(5);
    private static final long DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI = TimeUnit.MINUTES.toMillis(5);
    private static final long DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI = TimeUnit.MINUTES.toMillis(29);
    private static final long DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN = TimeUnit.MINUTES.toMillis(8);
    private static final long DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN = TimeUnit.MINUTES.toMillis(29);
    private static final long DEFAULT_SOCKET_STALENESS_BUFFER_MILLIS = TimeUnit.MINUTES.toMillis(2);
    public static final long KNOWN_CARRIER_DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN = TimeUnit.MINUTES.toMillis(28);
    public static final long KNOWN_CARRIER_DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN = DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN;
    private static final Map<String, Long> mDefaultLongByKey = new Hashtable<String, Long>() { // from class: com.amazon.communication.heartbeat.HeartbeatSettingsDefaultValues.2
        {
            put(HeartbeatSettings.PROBING_CONNECTION_MAX_INITIAL_DELAY_MILLIS_KEY, Long.valueOf(TimeUnit.MINUTES.toMillis(5L)));
            put(HeartbeatSettings.HEARTBEAT_INTERVAL_RANGE_MILLIS_KEY, Long.valueOf(TimeUnit.SECONDS.toMillis(15L)));
            put(HeartbeatSettings.HEARTBEAT_INTERVAL_HIKE_MILLIS_KEY, Long.valueOf(TimeUnit.SECONDS.toMillis(30L)));
            put(HeartbeatSettings.TRIVIAL_INTERVAL_DIFF_MILLIS_KEY, Long.valueOf(TimeUnit.SECONDS.toMillis(30L)));
            put(HeartbeatSettings.STABILIZED_INTERVAL_BOUNDS_DIFF_MILLIS_KEY, Long.valueOf(TimeUnit.SECONDS.toMillis(30L)));
            put(HeartbeatSettings.CHECK_HEARTBEAT_RESPONSE_DELAY_MILLIS_KEY, Long.valueOf(TimeUnit.SECONDS.toMillis(5L)));
            put(HeartbeatSettings.CONSERVATIVE_INTERVAL_MILLIS_KEY, Long.valueOf(TimeUnit.MINUTES.toMillis(1L) + HeartbeatSettingsDefaultValues.DEFAULT_CHECK_HEARTBEAT_RESPONSE_DELAY_MILLIS));
            put(HeartbeatSettings.INTERVAL_VALIDITY_PERIOD_MILLIS_KEY, Long.valueOf(TimeUnit.DAYS.toMillis(21L)));
            put(HeartbeatSettings.POOR_CONNECTIVITY_INTERVAL_VALIDITY_PERIOD_MILLIS_KEY, Long.valueOf(TimeUnit.HOURS.toMillis(24L)));
            put(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY, Long.valueOf(HeartbeatSettingsDefaultValues.DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI));
            put(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY, Long.valueOf(HeartbeatSettingsDefaultValues.DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI));
            put("AdaptiveHeartbeat.StalenessBufferMillis", Long.valueOf(HeartbeatSettingsDefaultValues.DEFAULT_SOCKET_STALENESS_BUFFER_MILLIS));
            put(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, Long.valueOf(HeartbeatSettingsDefaultValues.DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN));
            put(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, Long.valueOf(HeartbeatSettingsDefaultValues.DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN));
            put(HeartbeatSettings.KNOWN_CARRIER_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, Long.valueOf(HeartbeatSettingsDefaultValues.KNOWN_CARRIER_DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN));
            put(HeartbeatSettings.KNOWN_CARRIER_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, Long.valueOf(HeartbeatSettingsDefaultValues.KNOWN_CARRIER_DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN));
        }
    };
    private static final Map<String, Integer> mDefaultIntegerByKey = new Hashtable<String, Integer>() { // from class: com.amazon.communication.heartbeat.HeartbeatSettingsDefaultValues.3
        {
            put(HeartbeatSettings.MAX_HEARTBEAT_TRIES_KEY, 2);
            put(HeartbeatSettings.MAX_LEARNING_ATTEMPT_COUNT_KEY, 5);
            put(HeartbeatSettings.MAX_ALLOWED_CONSECUTIVE_FAILURE_COUNT_KEY, 2);
            put(HeartbeatSettings.MAX_ALLOWED_CONSECUTIVE_FAILURE_NEAR_LOWER_BOUND_KEY, 2);
        }
    };

    public static Boolean getDefaultBoolean(String str) {
        return mDefaultBooleanByKey.get(str);
    }

    public static Integer getDefaultInt(String str) {
        return mDefaultIntegerByKey.get(str);
    }

    public static Long getDefaultLong(String str) {
        return mDefaultLongByKey.get(str);
    }
}
