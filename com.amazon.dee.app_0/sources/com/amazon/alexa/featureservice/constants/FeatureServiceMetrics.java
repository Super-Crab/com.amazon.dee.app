package com.amazon.alexa.featureservice.constants;
/* loaded from: classes7.dex */
public final class FeatureServiceMetrics {
    public static final String HTTP_ERROR_PREFIX = "http_error_";
    public static final String METRICS_NAME_PREFIX = "feature_service_v2.";

    /* loaded from: classes7.dex */
    public static final class EventName {
        public static final String COLD_START = "cold_start";
        public static final String EMPTY_RESPONSE = "empty_response";
        public static final String EXECUTION_ERROR = "execution_error";
        public static final String INTERRUPT_ERROR = "interrupt_error";
        public static final String MISSING_TREATMENT = "missing_treatment";
        public static final String MODEL_ERROR = "model_error";
        public static final String PARSE_FAILED = "parse_failed";
        public static final String RE_LOGIN = "re_login";
        public static final String WARM_START = "warm_start";
    }

    /* loaded from: classes7.dex */
    public static final class EventType {
        public static final String NEW_SESSION = "new_session";
        public static final String NON_ACTIONABLE_HTTP_ERROR = "non_actionable_http_error";
        public static final String REFRESH_HTTP_ERROR = "refresh_http_error";
        public static final String REFRESH_PAYLOAD_ERROR = "refresh_payload_error";
        public static final String REFRESH_SUCCESS = "refresh_success";
        public static final String ROOM_THREAD_ERROR = "room_thread_error";
        public static final String TRIGGER_HTTP_ERROR = "trigger_http_error";
        public static final String TRIGGER_PAYLOAD_ERROR = "trigger_payload_error";
        public static final String TRIGGER_SUCCESS = "trigger_success";
    }

    /* loaded from: classes7.dex */
    public static final class Subcomponent {
        public static final String RECORD_TRIGGER = "RecordTrigger";
        public static final String REFRESH_FEATURES = "RefreshFeatures";
        public static final String ROOM_DATABASE = "RoomDatabase";
        public static final String SESSION_MANAGEMENT = "SessionManagement";
    }

    private FeatureServiceMetrics() {
    }
}
