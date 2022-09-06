package com.amazon.alexa.sensor.location;

import android.text.TextUtils;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public abstract class Metrics {
    public static final String COMPONENT = "LocationSensorAccessor";

    /* loaded from: classes10.dex */
    public static abstract class Events {
        public static final String AUTHORIZED_CALL = "authorized_call";
        public static final String INCOMPATIBLE_ACCESSOR = "incompatible_accessor";
        public static final String LOCATIONS_RECEIVED = "locations_received";
        public static final String LOCATION_AVAILABILITIES_RECEIVED = "location_availabilities_received";
        public static final String LOCATION_DELIVERED = "location_delivered";
        public static final String LOCATION_DELIVERY_ATTEMPTED = "location_delivery_attempted";
        public static final String LOCATION_RESULTS_RECEIVED = "location_results_received";
        public static final String LOCATION_TRACKING_ALL_CHANGES = "location_tracking_all_changes";
        public static final String LOCATION_TRACKING_STARTED = "location_tracking_started";
        public static final String LOCATION_TRACKING_STOPPED = "location_tracking_stopped";
        public static final String MISSING_BACKGROUND_LOCATION_PERMISSION = "missing_background_location_permission";
        public static final String MISSING_LOCATION_PERMISSION = "missing_location_permission";
        public static final String MISSING_PRECISE_LOCATION_PERMISSION = "missing_precise_location_permission";
        public static final String NO_LOCATION_RETURNED = "no_location_returned";
        public static final String NO_SUBSCRIPTION_TO_CANCEL = "no_subscription_to_cancel";
        public static final String PLATFORM_API_CALL_FAILED = "platform_api_call_failed";
        public static final String SUBSCRIPTION_UPDATE_REQUESTED = "subscription_update_requested";
    }

    /* loaded from: classes10.dex */
    public static abstract class SubComponents {
        public static final String CANCEL_SUBSCRIPTION = "CancelSubscription";
        public static final String CHECK_AUTHORIZATION = "CheckAuthorization";
        public static final String CONFIGURATION_CHANGES = "ConfigurationChanges";
        public static final String LOCATION_SUBSCRIPTION = "LocationSubscription";
        public static final String LOCATION_UPDATES = "LocationUpdates";
        public static final String REPORT_CONFIGURATION = "ReportConfiguration";
        public static final String SINGLE_LOCATION = "SingleLocation";
    }

    public static String fullComponentName(@Nullable String str) {
        return TextUtils.isEmpty(str) ? "LocationSensorAccessor" : String.format("%s_%s", "LocationSensorAccessor", str);
    }
}
