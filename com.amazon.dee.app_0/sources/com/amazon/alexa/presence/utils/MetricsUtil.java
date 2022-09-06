package com.amazon.alexa.presence.utils;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsServiceV2;
/* loaded from: classes9.dex */
public final class MetricsUtil {

    /* loaded from: classes9.dex */
    public static final class CategoryId {
        public static final String PRESENCE = "presence";

        private CategoryId() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class Method {
        public static final String BATTERY_OPTIMIZATION_SUBSCRIBER_MESSAGE_RECEIVED = "battery_optimization_subscriber_message_received";
        public static final String BLE_SCAN_FAILED = "ble_scan_failed";
        public static final String BLUETOOTH_STATE = "bluetooth_state";
        public static final String CHECK_UNIQUE_BEACON = "unique_beacon";
        public static final String NOTIFICATION_SUBSCRIBER_MESSAGE_RECEIVED = "notification_subscriber_message_received";
        public static final String ON_SCAN_RESULT = "on_scan_result";
        public static final String PERSISTENT_LOCAL_STORAGE = "persistent_local_storage";
        public static final String PHONE_ID_START = "phoneid_start";
        public static final String PHONE_ID_STOP = "phoneid_stop";
        public static final String PRESENCE_STATE_RESTORED = "presence_state_restored";
        public static final String PRESENCE_SUBSCRIBER_MESSAGE_RECEIVED = "presence_subscriber_message_received";
        public static final String PRESENCE_V1_START = "presence_v1_start";
        public static final String PRESENCE_V1_STOP = "presence_v1_stop";
        public static final String PRESENCE_V2_START = "presence_v2_start";
        public static final String PRESENCE_V2_STOP = "presence_v2_stop";
        public static final String RESOLVE_BEACONS = "resolve_beacons";
        public static final String RESTART_SCANNING_WORKFLOW = "restart_scanning_workflow";
        public static final String START_SCANNING_IMPL = "start_scanning_impl";
        public static final String START_SCANNING_WORKFLOW = "start_scanning_workflow";
        public static final String STOP_SCANNING_IMPL = "stop_scanning_impl";
        public static final String STOP_SCANNING_WORKFLOW = "stop_scanning_workflow";

        private Method() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class MetricsId {
        public static final String ADD_PRESENCE_DOMAIN = "add_presence_domain";
        public static final String AUTH_USER_WITH_PRESENCE_REQUEST = "auth_user_with_presence_request";
        public static final String BLUETOOTH_DISABLED = "bluetooth_disabled";
        public static final String BLUETOOTH_TURNED_ON = "bluetooth_turned_on";
        public static final String CHECK_BATTERY_OPTIMIZATION = "check_battery_optimization";
        public static final String CHECK_BATTERY_OPTIMIZATION_MESSAGE = "check_battery_optimization_message";
        public static final String DUPLICATE_BEACON = "duplicate_beacon";
        public static final String INVALID_MESSAGE = "invalid_message";
        public static final String INVALID_MESSAGE_FORMAT = "invalid_message_format";
        public static final String INVALID_SCAN_RECORD = "invalid_scan_record";
        public static final String NOTIFICATION_PRESENCE_DISABLE_MESSAGE = "notification_presence_disable_message";
        public static final String NOTIFICATION_PRESENCE_ENABLE_MESSAGE = "notification_presence_enable_message";
        public static final String NO_BLUETOOTH_ADAPTER = "no_bluetooth_adapter";
        public static final String NO_BLUETOOTH_SCANNER = "no_bluetooth_scanner";
        public static final String NO_DOMAIN_REQUESTING_PRESENCE = "no_domain_requesting_presence";
        public static final String NO_PERMISSIONS = "no_permissions";
        public static final String NULL_BEACON_PAYLOAD = "null_beacon_payload";
        public static final String PRESENCE_DISABLE_MESSAGE = "presence_disable_message";
        public static final String PRESENCE_ENABLE_MESSAGE = "presence_enable_message";
        public static final String PRESENCE_METRIC = "presence";
        public static final String REMOVE_PRESENCE_DOMAIN = "remove_presence_domain";
        public static final String REQUEST_BATTERY_OPTIMIZATION_DISABLE_MESSAGE = "request_battery_optimization_disable_message";
        public static final String REQUEST_DISABLE = "request_disable";
        public static final String RESOLVE = "resolve";
        public static final String RESOLVE_FAIL_AFTER_RETRIES = "resolve_fail_after_retries";
        public static final String RESOLVE_IO_EXCEPTION = "resolve_io_exception";
        public static final String RESTART_SCANNING_PERIODIC_ALARM = "restart_scanning_periodic_alarm";
        public static final String START_SCANNING = "start_scanning";
        public static final String START_SCANNING_APP_FOREGROUND = "start_scanning_app_foreground";
        public static final String START_SCANNING_BLUETOOTH_ON = "start_scanning_bluetooth_on";
        public static final String START_SCANNING_EVENT_BUS = "start_scanning_event_bus";
        public static final String START_SCANNING_INITIALIZE_PRESENCE = "start_scanning_initialize_presence";
        public static final String START_SCANNING_PUSH_NOTIFICATION = "start_scanning_push_notification";
        public static final String START_SCAN_FAILURE = "start_scan_failure";
        public static final String START_SCAN_SUCCESSFUL = "start_scan_successful";
        public static final String STOP_SCANNING = "stop_scanning";
        public static final String STOP_SCANNING_EVENT_BUS = "stop_scanning_event_bus";
        public static final String STOP_SCANNING_LOGOUT = "stop_scanning_logout";
        public static final String STOP_SCANNING_PUSH_NOTIFICATION = "stop_scanning_push_notification";
        public static final String STOP_SCAN_FAILURE = "stop_scan_failure";
        public static final String STOP_SCAN_SUCCESSFUL = "stop_scan_successful";
        public static final String UNIQUE_BEACON = "unique_beacon";
        public static final String USER_UNAUTHENTICATED = "user_unauthenticated";
        public static final String VALID_SCAN_RECORD = "valid_scan_record";

        private MetricsId() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class Module {
        public static final String PRESENCE = "presence";

        private Module() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class Route {
        private Route() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class Source {
        public static final String NATIVE = "native";

        private Source() {
        }
    }

    private MetricsUtil() {
    }

    static MetricDescriptor getMetricDescriptor(String str, String str2, String str3, String str4, String str5) {
        return new MetricDescriptor.Builder(new MetricName.Builder(str).withModule(str2).withSource(str3).build(), new MetricComponentName.Builder().withCategoryId(str4).withMethod(str5).build()).build();
    }

    public static void recordCount(MetricsServiceV2 metricsServiceV2, String str, String str2) {
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordCount(getMetricDescriptor(str, "presence", "native", "presence", str2), 1.0d);
    }

    public static void recordFailure(MetricsServiceV2 metricsServiceV2, String str, String str2, String str3) {
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordFailure(getMetricDescriptor(str, "presence", "native", "presence", str2), str3);
    }

    public static void recordSuccess(MetricsServiceV2 metricsServiceV2, String str, String str2) {
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordSuccess(getMetricDescriptor(str, "presence", "native", "presence", str2));
    }

    public static void recordTime(MetricsServiceV2 metricsServiceV2, String str, String str2, long j) {
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordTime(getMetricDescriptor(str, "presence", "native", "presence", str2), j);
    }

    public static void recordZeroCount(MetricsServiceV2 metricsServiceV2, String str, String str2) {
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordCount(getMetricDescriptor(str, "presence", "native", "presence", str2), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }
}
