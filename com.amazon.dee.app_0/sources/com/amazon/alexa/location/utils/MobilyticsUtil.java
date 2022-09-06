package com.amazon.alexa.location.utils;

import androidx.annotation.NonNull;
import com.amazon.alexa.location.LocationErrorCode;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.comms.ringservice.MetricsSession;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableMap;
import com.here.sdk.search.PlaceCategory;
/* loaded from: classes9.dex */
public final class MobilyticsUtil {
    public static final String COMPONENT_PREFIX = "location.";
    public static final ImmutableMap<LocationErrorCode, String> METRICS_NAME_ERROR_PART = new ImmutableMap.Builder().mo7828put(LocationErrorCode.GENERIC_ERROR, "generic_error").mo7828put(LocationErrorCode.PERMISSION_ERROR, "permission_error").mo7828put(LocationErrorCode.BEYOND_20_LIMIT, "beyond_20_limit").mo7828put(LocationErrorCode.FEATURE_NOT_AVAILABLE, "feature_not_available").mo7828put(LocationErrorCode.ALS_TIMEOUT, "timeout").mo7828put(LocationErrorCode.ALS_NOT_RECOGNIZE, "not_recognize").mo7828put(LocationErrorCode.ALS_PAYLOAD_ERROR, "payload_error").mo7828put(LocationErrorCode.ALS_AUTH_TOKEN_ERROR, "auth_token_error").mo7828put(LocationErrorCode.ALS_400, PlaceCategory.TRANSPORT).mo7828put(LocationErrorCode.ALS_401, "401").mo7828put(LocationErrorCode.ALS_403, "403").mo7828put(LocationErrorCode.ALS_404, "404").mo7828put(LocationErrorCode.ALS_500, PlaceCategory.ACCOMODATION).mo7828put(LocationErrorCode.ALS_503, MetricsSession.SERVICE_UNAVAILABLE).mo7826build();

    /* loaded from: classes9.dex */
    public static final class ComponentSuffix {
        public static final String FEATURE = "feature";
        public static final String PERMISSION = "permission";
        public static final String REGISTER_GEOFENCE = "register_geofence";
        public static final String SETTING = "setting";
        public static final String SYNC_GEOFENCE = "sync_geofence";
        public static final String TRIGGER_GEOFENCE = "trigger_geofence";

        private ComponentSuffix() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class MetricsID {
        public static final String ALS_CALL_FAILURE = "als_call_failure_";
        public static final String ALS_WORKFLOW_EMPTY_AUTHTOKEN = "als_workflow_empty_authtoken";
        public static final String ALS_WORKFLOW_EMPTY_PERSON_ID = "als_workflow_empty_person_id";
        public static final String ALS_WORKFLOW_FAILURE = "als_workflow_failure";
        public static final String ALS_WORKFLOW_FAILURE_GET_AUTH_RETRY_COUNT = "als_workflow_failure_get_auth_retry_count";
        public static final String ALS_WORKFLOW_FAILURE_INTEGRATE_RETRY_COUNT = "als_workflow_failure_integrate_retry_count";
        public static final String ALS_WORKFLOW_FAILURE_OTHERS = "als_workflow_failure_others";
        public static final String ALS_WORKFLOW_FAILURE_RETRIES_EXCEEDS = "als_workflow_failure_retries_exceeds_";
        public static final String ALS_WORKFLOW_FAILURE_TOKEN_ACCESS_FAIL = "als_workflow_failure_token_access_fail";
        public static final String ALS_WORKFLOW_INITIAL_CALL = "als_workflow_initial_call";
        public static final String ALS_WORKFLOW_START = "als_workflow_start";
        public static final String ALS_WORKFLOW_SUCCESS = "als_workflow_success";
        public static final String ALS_WORKFLOW_SUCCESS_GET_AUTH_RETRY_COUNT = "als_workflow_success_get_auth_retry_count";
        public static final String ALS_WORKFLOW_SUCCESS_INTEGRATE_RETRY_COUNT = "als_workflow_success_integrate_retry_count";
        public static final String ALS_WORKFLOW_SUCCESS_TRIGGER_MISSED = "als_workflow_success_trigger_missed";
        public static final String ALS_WORKFLOW_TIME_FAILURE = "als_workflow_time_failure";
        public static final String ALS_WORKFLOW_TIME_SUCCESS = "als_workflow_time_success";
        public static final String ALS_WORKFLOW_TRIGGER_MISSED = "als_workflow_trigger_missed";
        public static final String CANARY_CACHE_GET_ERROR = "canary_cache_get_error";
        public static final String CANARY_CACHE_PUT_ERROR = "canary_cache_put_error";
        public static final String CANARY_DECRYPT_ERROR = "canary_decrypt_error";
        public static final String CANARY_ENCRYPT_ERROR = "canary_encrypt_error";
        public static final String CONFIGURATION_SET = "configuration_%s_set";
        public static final String EVENT_EVALUATED = "event_%s_evaluated";
        public static final String FAILURES_PER_INTENT = "failures_per_intent";
        public static final String FETCH_CONFIG_EXCEPTION = "fetch_config_exception";
        public static final String FETCH_SUCCESSFUL = "fetch_successful";
        public static final String GEOFENCES_ADDED_FAILURE = "geofences_added_failure_";
        public static final String GEOFENCES_ADDED_SUCCESS = "geofences_added_success";
        public static final String GEOFENCES_REMOVED_FAILURE = "geofences_removed_failure_";
        public static final String GEOFENCES_REMOVED_SUCCESS = "geofences_removed_success";
        public static final String GEOFENCE_INFO_FAILURE = "geofence_info_failure_";
        public static final String GEOFENCE_SYNC_PROCESS_TIME_FAILURE = "process_time_failure";
        public static final String GEOFENCE_SYNC_PROCESS_TIME_SUCCESS = "process_time_success";
        public static final String GEOFENCE_SYNC_REQUESTED_FORGROUND = "sync_reason_event_bus_foreground";
        public static final String GEOFENCE_SYNC_REQUESTED_PERMISSION_CHANGED = "sync_reason_event_bus_permission_changed";
        public static final String GEOFENCE_SYNC_REQUESTED_THROUGH_EVENT_BUS = "sync_reason_event_bus_sync_geofences";
        public static final String GEOFENCE_SYNC_REQUESTED_USER_CHANGED = "sync_reason_event_bus_geofence_user_changed";
        public static final String GEOFENCE_SYNC_RETRY_COUNT_FAILURE = "geofence_sync_retry_count_failure";
        public static final String GEOFENCE_SYNC_RETRY_COUNT_SUCESS = "geofence_sync_retry_count_success";
        public static final String GEOFENCE_SYNC_START = "start";
        public static final String GEOFENCE_SYNC_SUCCESS = "success";
        public static final String GMSCLIENT_CONNECTION_FAILED = "gmsclient_connection_failed";
        public static final String GMSCLIENT_REQUEST_FAILED = "gmsclient_request_failed";
        public static final String INTENT_FAILURE = "intent_%s_failure";
        public static final String INTENT_HANDLED_TIME = "intent_%s_handled_time";
        public static final String INTENT_RECEIVED = "intent_received";
        public static final String INTENT_SERVICE_STARTED = "intent_service_started";
        public static final String INTENT_START = "intent_%s_start";
        public static final String INTENT_SUCCESS = "intent_%s_success";
        public static final String IO_EXCEPTION_TEMPLATE = "trigger_io_exception_%s";
        public static final String MISSING_ACCESS_TOKEN = "missing_access_token";
        public static final String MISSING_BUILD_STAGE = "missing_build_stage";
        public static final String MISSING_MARKETPLACE_CODE = "missing_marketplace_code";
        public static final String MISSING_PERSON_ID = "missing_person_id";
        public static final String NO_USER_IDENTITY = "no_user_identity";
        public static final String OS_GEOFENCE_TRIGGERED = "os_geofence_triggered";
        public static final String OS_TRIGGER_DISCARD = "os_trigger_discarded";
        public static final String OS_TRIGGER_DISCARDED_AUTHTOKEN = "os_trigger_discarded_authtoken";
        public static final String OS_TRIGGER_DISCARDED_BUILDSTAGE = "os_trigger_discarded_buildstage";
        public static final String OS_TRIGGER_DISCARDED_PFM = "os_trigger_discarded_pfm";
        public static final String OS_TRIGGER_DISCARDED_UNKNOWN = "os_trigger_discarded_unknown";
        public static final String OS_TRIGGER_MISSED = "os_trigger_missed";
        public static final String OS_TRIGGER_RECEIVED = "os_trigger_received";
        public static final String PHASE3_CONTROLLED_USER_WEBLAB_LOCATION_SERVICE_MISMATCH = "phase3_controlled_user_weblab_location_service_mismatch";
        public static final String PHASE3_SERVICE_NOT_AVAILABLE = "phase3_service_not_available";
        public static final String PHASE3_USER_WEBLAB_LOCATION_SERVICE_MISMATCH = "phase3_user_weblab_location_service_mismatch";
        public static final String PROCESS_INTENT_TIME = "process_intent_time";
        public static final String REGIONS_PER_INTENT = "regions_per_intent";
        public static final String REGISTERED_NEW_FENCES = "registered_new_fences";
        public static final String REMOVED_FENCES = "removed_fences";
        public static final String RE_REGISTER_FAILURE = "re_register_failure";
        public static final String RE_REGISTER_SUCCESS = "re_register_success";
        public static final String START_UPTIME = "start_uptime";
        public static final String TRIGGER_FAILURE = "trigger_failure";
        public static final String TRIGGER_GEOFENCE_LOCATION_EXCEPTION_DELAY = "trigger_geofence_location_exception_delay";
        public static final String GEOFENCE_TRIGGER_PROCESSING_DELAY = MobilyticsUtil.getLegacyName(MetricsUtil.MetricsId.GEOFENCE_TRIGGER_PROCESSING_DELAY, "time");
        public static final String PERMISSION_DENIED = MobilyticsUtil.getLegacyName("permission_denied", "count");
        public static final String PERMISSION_FOREGROUND_ONLY = MobilyticsUtil.getLegacyName(MetricsUtil.MetricsId.PERMISSION_FORGROUND_ONLY, "count");
        public static final String PERMISSION_ALWAYS = MobilyticsUtil.getLegacyName("permission_always", "count");
        public static final String GPS_PRESENT = MobilyticsUtil.getLegacyName(MetricsUtil.MetricsId.GPS_PRESENT, "count");
        public static final String GPS_USABLE = MobilyticsUtil.getLegacyName(MetricsUtil.MetricsId.GPS_USABLE, "count");
        public static final String NETWORK_LOCATION_PRESENT = MobilyticsUtil.getLegacyName(MetricsUtil.MetricsId.NETWORK_LOCATION_PRESENT, "count");
        public static final String NETWORK_LOCATION_USABLE = MobilyticsUtil.getLegacyName(MetricsUtil.MetricsId.NETWORK_LOCATION_USABLE, "count");

        private MetricsID() {
        }
    }

    private MobilyticsUtil() {
    }

    public static MobilyticsMetricsCounter createCouterWithNewName(LazyComponent<Mobilytics> lazyComponent, MobilyticsMetricsCounter mobilyticsMetricsCounter, String str, String str2) {
        MobilyticsMetricsCounter createCounter = lazyComponent.mo10268get().createCounter(str, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter.incrementCounterByValue(mobilyticsMetricsCounter.getCount());
        return createCounter;
    }

    public static MobilyticsMetricsTimer createTimerWithNewName(LazyComponent<Mobilytics> lazyComponent, MobilyticsMetricsTimer mobilyticsMetricsTimer, String str, String str2) {
        return createTimerWithValue(lazyComponent, str, str2, str2, mobilyticsMetricsTimer.getElapsedTime());
    }

    public static MobilyticsMetricsTimer createTimerWithValue(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2, @NonNull String str3, long j) {
        return lazyComponent.mo10268get().createTimer(str, str2, str3, j, false, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static String getComponentName(String str) {
        return GeneratedOutlineSupport1.outline72(COMPONENT_PREFIX, str);
    }

    public static String getLegacyName(String str, String str2) {
        return GeneratedOutlineSupport1.outline76("native.location.", str, ".", str2);
    }

    public static String getLocationErrorMetricNameByReason(String str, LocationErrorCode locationErrorCode) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(METRICS_NAME_ERROR_PART.mo7740get(locationErrorCode));
        return outline107.toString();
    }
}
