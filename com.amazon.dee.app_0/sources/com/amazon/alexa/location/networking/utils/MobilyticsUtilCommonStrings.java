package com.amazon.alexa.location.networking.utils;

import com.amazon.alexa.location.networking.LocationErrorCode;
import com.amazon.comms.ringservice.MetricsSession;
import com.google.common.collect.ImmutableMap;
import com.here.sdk.search.PlaceCategory;
/* loaded from: classes9.dex */
public final class MobilyticsUtilCommonStrings {
    public static final String COMPONENT_PREFIX = "location.networking";
    public static final ImmutableMap<LocationErrorCode, String> METRICS_NAME_ERROR_PART = new ImmutableMap.Builder().mo7828put(LocationErrorCode.GENERIC_ERROR, "generic_error").mo7828put(LocationErrorCode.PERMISSION_ERROR, "permission_error").mo7828put(LocationErrorCode.BEYOND_20_LIMIT, "beyond_20_limit").mo7828put(LocationErrorCode.FEATURE_NOT_AVAILABLE, "feature_not_available").mo7828put(LocationErrorCode.ALS_TIMEOUT, "timeout").mo7828put(LocationErrorCode.ALS_NOT_RECOGNIZE, "not_recognize").mo7828put(LocationErrorCode.ALS_PAYLOAD_ERROR, "payload_error").mo7828put(LocationErrorCode.ALS_AUTH_TOKEN_ERROR, "auth_token_error").mo7828put(LocationErrorCode.ALS_400, PlaceCategory.TRANSPORT).mo7828put(LocationErrorCode.ALS_401, "401").mo7828put(LocationErrorCode.ALS_403, "403").mo7828put(LocationErrorCode.ALS_404, "404").mo7828put(LocationErrorCode.ALS_500, PlaceCategory.ACCOMODATION).mo7828put(LocationErrorCode.ALS_503, MetricsSession.SERVICE_UNAVAILABLE).mo7828put(LocationErrorCode.ALPS_TIMEOUT, "timeout").mo7828put(LocationErrorCode.ALPS_NOT_RECOGNIZE, "not_recognize").mo7828put(LocationErrorCode.ALPS_PAYLOAD_ERROR, "payload_error").mo7828put(LocationErrorCode.ALPS_AUTH_TOKEN_ERROR, "auth_token_error").mo7828put(LocationErrorCode.ALPS_400, PlaceCategory.TRANSPORT).mo7828put(LocationErrorCode.ALPS_401, "401").mo7828put(LocationErrorCode.ALPS_403, "403").mo7828put(LocationErrorCode.ALPS_404, "404").mo7828put(LocationErrorCode.ALPS_500, PlaceCategory.ACCOMODATION).mo7828put(LocationErrorCode.ALPS_503, MetricsSession.SERVICE_UNAVAILABLE).mo7826build();

    /* loaded from: classes9.dex */
    public static final class ComponentSuffix {
        public static final String ALPS_NETWORK_GATEWAY = "alps_network_gateway";
        public static final String ALS_REPORT_GEOFENCES_STATUS = "als_report_geofences_status";
        public static final String PERMISSION = "permission";
        public static final String REGISTER_GEOFENCE = "register_geofence";
        public static final String SYNC_GEOFENCE = "sync_geofence";
        public static final String TRIGGER_GEOFENCE = "trigger_geofence";

        private ComponentSuffix() {
        }
    }

    /* loaded from: classes9.dex */
    public final class Method {
        public static final String ALS_CREATE_GEOFENCE = "als_create_geofence";
        public static final String ALS_FETCH_GEOFENCES = "als_fetch_geofences";
        public static final String ALS_REPORT_GEOFENCES_STATUS = "als_report_geofences_status";
        public static final String ALS_TRIGGER_GEOFENCE = "als_trigger_geofence";
        public static final String ALS_UPDATE_GEOFENCE = "als_update_geofence";
        public static final String CREATE_GEOFENCE = "create_geofence";
        public static final String FEATURE = "feature";
        public static final String PERMISSION = "permission";
        public static final String SETTING = "setting";
        public static final String TRIGGER_GEOFENCE = "trigger_geofence";
        public static final String UPDATE_GEOFENCE = "update_geofence";

        private Method() {
        }
    }

    /* loaded from: classes9.dex */
    public final class MetricsID {
        public static final String FETCH = "fetch_als";
        public static final String FETCH_400_ERROR = "fetch_error_400";
        public static final String FETCH_401_ERROR = "fetch_error_401";
        public static final String FETCH_403_ERROR = "fetch_error_403";
        public static final String FETCH_404_ERROR = "fetch_error_404";
        public static final String FETCH_500_ERROR = "fetch_error_500";
        public static final String FETCH_503_ERROR = "fetch_error_503";
        public static final String FETCH_NOT_RECOGNIZE = "fetch_error_not_recognized";
        public static final String FETCH_PAYLOAD_ERROR = "fetch_error_payload";
        public static final String FETCH_SUCCESSFUL = "fetch_successful";
        public static final String FETCH_TIMEOUT_ERROR = "fetch_error_timeout";
        public static final String GEOFENCE_TRIGGER_IO_EXCEPTION = "geofence_trigger_io_exception";
        public static final String GEOFENCE_TRIGGER_IO_EXCEPTION_DELAY = "geofence_trigger_io_exception_delay";
        public static final String GEOFENCE_TRIGGER_NETWORK_AVAILABLE = "geofence_trigger_network_available";
        public static final String IO_EXCEPTION_TEMPLATE = "trigger_io_exception_%s";
        public static final String MISSING_ACCESS_TOKEN = "missing_access_token";
        public static final String MISSING_BUILD_STAGE = "missing_build_stage";
        public static final String MISSING_MARKETPLACE_CODE = "missing_marketplace_code";
        public static final String MISSING_PERSON_ID = "missing_person_id";
        public static final String PERMISSION_ALWAYS = "permission_always";
        public static final String PERMISSION_DENIED = "permission_denied";
        public static final String REPORT_LOCATION_COMPLETE = "report_location_complete";
        public static final String REPORT_LOCATION_FAILURE_PREFIX = "report_location_failure_";
        public static final String TRIGGER_GEOFENCE_LOCATION_EXCEPTION_DELAY = "trigger_geofence_location_exception_delay";

        private MetricsID() {
        }
    }

    private MobilyticsUtilCommonStrings() {
    }
}
