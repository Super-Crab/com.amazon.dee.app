package com.amazon.communication.heartbeat;

import com.amazon.communication.ConnectivityManagerWrapper;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.dp.logger.DPLogger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class HeartbeatSettings {
    public static final String CHECK_HEARTBEAT_RESPONSE_DELAY_MILLIS_KEY = "Heartbeat.CheckHeartbeatResponseDelayMillis";
    public static final String CONSERVATIVE_INTERVAL_MILLIS_KEY = "Heartbeat.ConservativeIntervalMillis";
    public static final String HEARTBEAT_INTERVAL_HIKE_MILLIS_KEY = "Heartbeat.IntervalHikeMillis";
    public static final String HEARTBEAT_INTERVAL_RANGE_MILLIS_KEY = "AdaptiveHeartbeat.IntervalRangeMillis";
    public static final String INTERVAL_VALIDITY_PERIOD_MILLIS_KEY = "AdaptiveHeartbeat.IntervalValidityPeriodMillis";
    public static final String IS_PROBING_CONNECTION_ENABLED_KEY = "Heartbeat.IsProbingConnectionEnabled";
    private static final char KEY_SEPARATOR = '.';
    public static final String KNOWN_CARRIER_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY = "AdaptiveHeartbeat.KnownCarrierMaxIntervalMillis.WAN";
    public static final String MAX_ALLOWED_CONSECUTIVE_FAILURE_COUNT_KEY = "AdaptiveHeartbeat.MaxAllowedConsecutiveFailureCount";
    public static final String MAX_ALLOWED_CONSECUTIVE_FAILURE_NEAR_LOWER_BOUND_KEY = "Heartbeat.MaxAllowedConsecutiveFailureNearLowerBound";
    public static final String MAX_HEARTBEAT_TRIES_KEY = "Heartbeat.MaxHeartbeatTries";
    public static final String MAX_LEARNING_ATTEMPT_COUNT_KEY = "AdaptiveHeartbeat.MaxLearningAttemptCount";
    public static final String POOR_CONNECTIVITY_INTERVAL_VALIDITY_PERIOD_MILLIS_KEY = "Heartbeat.PoorConnectivityIntervalValidityPeriodMillis";
    public static final String PROBING_CONNECTION_MAX_INITIAL_DELAY_MILLIS_KEY = "Heartbeat.ProbingConnectionMaxInitialDelayMillis";
    public static final String SOCKET_STALENESS_BUFFER_MILLIS_KEY = "Tcomm.AdaptiveHeartbeat.StalenessBufferMillis";
    public static final String STABILIZED_INTERVAL_BOUNDS_DIFF_MILLIS_KEY = "Heartbeat.StabilizedIntervalBoundsDiffMillis";
    public static final String TRIVIAL_INTERVAL_DIFF_MILLIS_KEY = "Heartbeat.TrivialIntervalDiffMillis";
    private static final DPLogger log = new DPLogger("TComm.HeartbeatSettings");
    private static ConnectivityManagerWrapper sConnectivityManager = null;
    public static final String MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY = "AdaptiveHeartbeat.MinIntervalMillis.WiFi";
    public static final long DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI = HeartbeatSettingsDefaultValues.getDefaultLong(MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY).longValue();
    public static final String MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY = "AdaptiveHeartbeat.MaxIntervalMillis.WiFi";
    public static final long DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI = HeartbeatSettingsDefaultValues.getDefaultLong(MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY).longValue();
    public static final long DEFAULT_SOCKET_STALENESS_BUFFER_MILLIS = TimeUnit.MINUTES.toMillis(2);
    public static final String MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY = "AdaptiveHeartbeat.MinIntervalMillis.WAN";
    public static final long DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN = HeartbeatSettingsDefaultValues.getDefaultLong(MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue();
    public static final String MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY = "AdaptiveHeartbeat.MaxIntervalMillis.WAN";
    public static final long DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN = HeartbeatSettingsDefaultValues.getDefaultLong(MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue();
    public static final String KNOWN_CARRIER_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY = "AdaptiveHeartbeat.KnownCarrierMinIntervalMillis.WAN";
    public static final long KNOWN_CARRIER_DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN = HeartbeatSettingsDefaultValues.getDefaultLong(KNOWN_CARRIER_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue();
    public static final long KNOWN_CARRIER_DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN = DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN;
    private static final Map<String, Long> DEFAULT_OVERRIDE_MAP = new HashMap<String, Long>() { // from class: com.amazon.communication.heartbeat.HeartbeatSettings.1
        {
            String[] strArr;
            for (String str : new String[]{"310070", "310311", "310380", "310410", "310490", "310530", "310560", "310680", "310890", "310990", "311070", "310012", "311480", "311481", "311482", "311483", "311484", "311485", "311486", "311487", "311488", "311489"}) {
                put(HeartbeatSettings.suffixKey(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, str, str), Long.valueOf(HeartbeatSettings.KNOWN_CARRIER_DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN));
                put(HeartbeatSettings.suffixKey(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, str, str), Long.valueOf(HeartbeatSettings.KNOWN_CARRIER_DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN));
            }
            put(HeartbeatSettings.suffixKey(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, "311480", "310012"), Long.valueOf(HeartbeatSettings.KNOWN_CARRIER_DEFAULT_MIN_HEARTBEAT_INTERVAL_MILLIS_WAN));
            put(HeartbeatSettings.suffixKey(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, "311480", "310012"), Long.valueOf(HeartbeatSettings.KNOWN_CARRIER_DEFAULT_MAX_HEARTBEAT_INTERVAL_MILLIS_WAN));
        }
    };

    public static Boolean getBooleanValue(String str) {
        return RemoteSettingManager.getOptBooleanValue(str, HeartbeatSettingsDefaultValues.getDefaultBoolean(str));
    }

    private static long getDefaultLongValue(String str, long j) {
        return DEFAULT_OVERRIDE_MAP.containsKey(str) ? DEFAULT_OVERRIDE_MAP.get(str).longValue() : j;
    }

    public static Integer getIntValue(String str) {
        return RemoteSettingManager.getOptIntValue(str, HeartbeatSettingsDefaultValues.getDefaultInt(str));
    }

    public static Long getLongValue(String str) {
        return RemoteSettingManager.getOptLongValue(str, HeartbeatSettingsDefaultValues.getDefaultLong(str));
    }

    public static long getMaxHeartbeatIntervalMillisOverWan(String str, String str2) {
        return getSpecificIntervalLongValue(MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, getLongValue(MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue(), str, str2);
    }

    public static long getMinHeartbeatIntervalMillisOverWan(String str, String str2) {
        return getSpecificIntervalLongValue(MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, getLongValue(MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue(), str, str2);
    }

    private static long getSpecificIntervalLongValue(String str, long j, String... strArr) {
        String suffixKey = suffixKey(str, strArr);
        return RemoteSettingManager.getOptLongValue(suffixKey, Long.valueOf(getDefaultLongValue(suffixKey, j))).longValue();
    }

    public static void setConnectivityManager(ConnectivityManagerWrapper connectivityManagerWrapper) {
        sConnectivityManager = connectivityManagerWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String suffixKey(String str, String... strArr) {
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : strArr) {
            sb.append('.');
            sb.append(str2);
        }
        return sb.toString();
    }

    public static long getMaxHeartbeatIntervalMillisOverWan() {
        ConnectivityManagerWrapper connectivityManagerWrapper = sConnectivityManager;
        if (connectivityManagerWrapper != null) {
            return getMaxHeartbeatIntervalMillisOverWan(connectivityManagerWrapper.getSimCountryAndNetworkCodes(), sConnectivityManager.getTowerCountryAndNetworkCodes());
        }
        return getLongValue(MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue();
    }

    public static long getMinHeartbeatIntervalMillisOverWan() {
        ConnectivityManagerWrapper connectivityManagerWrapper = sConnectivityManager;
        if (connectivityManagerWrapper != null) {
            return getMinHeartbeatIntervalMillisOverWan(connectivityManagerWrapper.getSimCountryAndNetworkCodes(), sConnectivityManager.getTowerCountryAndNetworkCodes());
        }
        return getLongValue(MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue();
    }
}
