package com.amazon.alexa.location.utils;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import dagger.Lazy;
@Deprecated
/* loaded from: classes9.dex */
public final class MetricsUtil {
    private static final String TAG = "MetricsUtil";

    /* loaded from: classes9.dex */
    public final class CategoryId {
        public static final String LOCATION = "location";

        private CategoryId() {
        }
    }

    /* loaded from: classes9.dex */
    public final class ComponentName {
        public static final String LOCATION = "location";

        private ComponentName() {
        }
    }

    /* loaded from: classes9.dex */
    public final class LegacyEventType {
        public static final String GEOFENCE_NO_ACCESS_TO_FEATURE = "GEOFENCE_NO_ACCESS_TO_FEATURE";
        public static final String GEOFENCE_PROCESS_SYNC_MESSAGE = "GEOFENCE_PROCESS_SYNC_MESSAGE";
        public static final String GEOFENCE_SYNC = "GEOFENCE_SYNC";
        public static final String GEOFENCE_SYNC_ERROR_RATE = "GEOFENCE_SYNC_ERROR_RATE";

        private LegacyEventType() {
        }
    }

    @VisibleForTesting
    /* loaded from: classes9.dex */
    static final class LegacyMetricTypes {
        public static final String AVAILABILITY = "availability";
        public static final String COUNT = "count";
        public static final String DATA = "";
        public static final String FAULT = "fault";
        public static final String LIMIT = "limit";
        public static final String TIME = "time";

        private LegacyMetricTypes() {
        }
    }

    /* loaded from: classes9.dex */
    public final class LegacySubComponentName {
        public static final String LOCATION_SERVICE = "LocationService";

        private LegacySubComponentName() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class LocalLazyComponent<T> implements LazyComponent<T> {
        final Lazy<T> lazy;

        public LocalLazyComponent(Lazy<T> lazy) {
            this.lazy = lazy;
        }

        @Override // com.amazon.alexa.protocols.service.api.LazyComponent, javax.inject.Provider
        /* renamed from: get */
        public T mo10268get() {
            Lazy<T> lazy = this.lazy;
            if (lazy != null) {
                return lazy.mo358get();
            }
            return null;
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
    public final class MetricsId {
        public static final String EXCEEDS_MAX_GEOFENCES = "exceeds_max_geofences";
        public static final String FETCH = "fetch_als";
        public static final String FETCH_400_ERROR = "fetch_error_400";
        public static final String FETCH_401_ERROR = "fetch_error_401";
        public static final String FETCH_403_ERROR = "fetch_error_403";
        public static final String FETCH_404_ERROR = "fetch_error_404";
        public static final String FETCH_500_ERROR = "fetch_error_500";
        public static final String FETCH_503_ERROR = "fetch_error_503";
        public static final String FETCH_NOT_RECOGNIZE = "fetch_error_not_recognized";
        public static final String FETCH_PAYLOAD_ERROR = "fetch_error_payload";
        public static final String FETCH_TIMEOUT_ERROR = "fetch_error_timeout";
        public static final String GEOFENCES_REGISTERED_TO_OS = "geofences_registered_to_os";
        public static final String GEOFENCE_ENTER_TRIGGERED = "geofence_enter_triggered";
        public static final String GEOFENCE_EXIT_TRIGGERED = "geofence_exit_triggered";
        public static final String GEOFENCE_TRIGGER_FAILURE = "geofence_trigger_failure";
        public static final String GEOFENCE_TRIGGER_IO_EXCEPTION = "geofence_trigger_io_exception";
        public static final String GEOFENCE_TRIGGER_IO_EXCEPTION_DELAY = "geofence_trigger_io_exception_delay";
        public static final String GEOFENCE_TRIGGER_NETWORK_AVAILABLE = "geofence_trigger_network_available";
        public static final String GEOFENCE_TRIGGER_PROCESSING_DELAY = "geofence_trigger_processing_delay";
        public static final String GEOFENCE_TRIGGER_RECEIVED = "geofence_trigger_received";
        public static final String GEOFENCE_TRIGGER_SUCCESS = "geofence_trigger_success";
        public static final String GEOFENCE_USER_CHANGE_SYNC_TRIGGER = "GEOFENCE_USER_CHANGE_SYNC_TRIGGER";
        public static final String GPS_PRESENT = "gps_present";
        public static final String GPS_USABLE = "gps_usable";
        public static final String LOCATION_SETTING_API = "location_setting_api";
        public static final String NETWORK_LOCATION_PRESENT = "network_location_present";
        public static final String NETWORK_LOCATION_USABLE = "network_location_usable";
        public static final String PERMISSION_ALWAYS = "permission_always";
        public static final String PERMISSION_DENIED = "permission_denied";
        public static final String PERMISSION_FORGROUND_ONLY = "permission_foreground_only";
        public static final String WORKFLOW_ALS_401_FAILURE = "workflow_incompleted_duo_to_als_401_failure";
        public static final String WORKFLOW_ALS_OTHER_FAILURE = "workflow_incompleted_duo_to_als_other_failure";
        public static final String WORKFLOW_ALS_TIMEOUT_FAILURE = "workflow_incompleted_duo_to_als_timeout_failure";
        public static final String WORKFLOW_AUTH_FAILURE = "workflow_incompleted_due_to_auth";
        public static final String WORKFLOW_COMPLETED = "workflow_completed";

        private MetricsId() {
        }
    }

    /* loaded from: classes9.dex */
    public final class Module {
        public static final String LOCATION = "location";

        private Module() {
        }
    }

    /* loaded from: classes9.dex */
    public final class Source {
        public static final String NATIVE = "native";

        private Source() {
        }
    }

    private MetricsUtil() {
    }

    public static String buildComponentName(@NonNull String str, @NonNull String str2) {
        return String.format("%s.%s", str, str2);
    }

    @VisibleForTesting
    static String buildEventName(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4) {
        return String.format("%s.%s.%s.%s", str3, str2, str, str4);
    }

    @Deprecated
    public static String buildSubComponentName(@NonNull String str, @NonNull String str2) {
        return String.format("%s.%s", str, str2);
    }

    @Nullable
    public static MobilyticsMetricsCounter createCounter(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2) {
        if (isMobilyticsMissing(lazyComponent)) {
            return null;
        }
        return lazyComponent.mo10268get().createCounter(buildEventName(str, "location", "native", "count"), buildComponentName("location", str2), buildSubComponentName("location", str2), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    @Nullable
    public static MobilyticsMetricsTimer createTimer(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2) {
        if (isMobilyticsMissing(lazyComponent)) {
            return null;
        }
        return lazyComponent.mo10268get().createTimer(buildEventName(str, "location", "native", "time"), buildComponentName("location", str2), buildSubComponentName("location", str2), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static boolean isMobilyticsMissing(LazyComponent<Mobilytics> lazyComponent) {
        boolean z = lazyComponent == null || lazyComponent.mo10268get() == null;
        if (z) {
            Log.w(TAG, "Cannot get Mobilytics for metric recording. Metrics will be lost.");
        }
        return z;
    }

    public static void recordFailure(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2, String str3) {
        if (isMobilyticsMissing(lazyComponent)) {
            return;
        }
        String buildEventName = buildEventName(str, "location", "native", LegacyMetricTypes.FAULT);
        String buildComponentName = buildComponentName("location", str2);
        String buildSubComponentName = buildSubComponentName("location", str2);
        if (TextUtils.isEmpty(str3)) {
            str3 = "Error message not available";
        }
        lazyComponent.mo10268get().recordErrorEvent(buildEventName, str3, buildComponentName, buildSubComponentName);
    }

    public static void recordOccurrence(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2, boolean z) {
        if (isMobilyticsMissing(lazyComponent)) {
            return;
        }
        lazyComponent.mo10268get().recordOccurrence(buildEventName(str, "location", "native", "count"), z, buildComponentName("location", str2), buildSubComponentName("location", str2), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static void recordSuccess(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2) {
        if (isMobilyticsMissing(lazyComponent)) {
            return;
        }
        lazyComponent.mo10268get().recordOccurrence(buildEventName(str, "location", "native", LegacyMetricTypes.FAULT), false, buildComponentName("location", str2), buildSubComponentName("location", str2), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static void recordTime(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2, long j) {
        if (isMobilyticsMissing(lazyComponent)) {
            return;
        }
        lazyComponent.mo10268get().recordTimer(lazyComponent.mo10268get().createTimer(buildEventName(str, "location", "native", "time"), buildComponentName("location", str2), buildSubComponentName("location", str2), j, false, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID));
    }
}
