package com.amazon.alexa.location.networking.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.location.networking.LocationErrorCode;
import com.amazon.alexa.location.networking.LocationException;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public final class MobilyticsUtil {
    private MobilyticsUtil() {
    }

    public static MobilyticsMetricsCounter createCounterWithNewName(LazyComponent<Mobilytics> lazyComponent, MobilyticsMetricsCounter mobilyticsMetricsCounter, String str, String str2) {
        MobilyticsMetricsCounter createCounter = lazyComponent.mo10268get().createCounter(str, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter.incrementCounterByValue(mobilyticsMetricsCounter.getCount());
        return createCounter;
    }

    @Nullable
    public static MobilyticsMetricsTimer createTimer(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2) {
        return lazyComponent.mo10268get().createTimer(getEventName(str), getComponentName(str2), getSubComponentName(str2), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static MobilyticsMetricsTimer createTimerWithNewName(LazyComponent<Mobilytics> lazyComponent, MobilyticsMetricsTimer mobilyticsMetricsTimer, String str, String str2) {
        return createTimerWithValue(lazyComponent, str, str2, str2, mobilyticsMetricsTimer.getElapsedTime());
    }

    public static MobilyticsMetricsTimer createTimerWithValue(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2, @NonNull String str3, long j) {
        return lazyComponent.mo10268get().createTimer(str, str2, str3, j, false, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static String getComponentName(String str) {
        return String.format("%s.%s", MobilyticsUtilCommonStrings.COMPONENT_PREFIX, str);
    }

    public static String getEventName(String str) {
        return String.format("%s.%s", MobilyticsUtilCommonStrings.COMPONENT_PREFIX, str);
    }

    public static String getLocationErrorMetricNameByReason(String str, LocationErrorCode locationErrorCode) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(MobilyticsUtilCommonStrings.METRICS_NAME_ERROR_PART.mo7740get(locationErrorCode));
        return outline107.toString();
    }

    public static String getSubComponentName(String str) {
        return String.format("%s.%s", MobilyticsUtilCommonStrings.COMPONENT_PREFIX, str);
    }

    public static void recordExceptionMetrics(LazyComponent<Mobilytics> lazyComponent, LocationException locationException, String str, String str2) {
        lazyComponent.mo10268get().recordOccurrence(getLocationErrorMetricNameByReason(str, locationException.getErrorCode()), true, str2, str2);
    }

    public static void recordFailure(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2, String str3) {
        String eventName = getEventName(str);
        String componentName = getComponentName(str2);
        String subComponentName = getSubComponentName(str2);
        if (TextUtils.isEmpty(str3)) {
            str3 = "Error message not available";
        }
        lazyComponent.mo10268get().recordErrorEvent(eventName, str3, componentName, subComponentName);
    }

    public static void recordSuccess(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2) {
        lazyComponent.mo10268get().recordOccurrence(getEventName(str), false, getComponentName(str2), getSubComponentName(str2), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static void recordTime(LazyComponent<Mobilytics> lazyComponent, @NonNull String str, @NonNull String str2, long j) {
        lazyComponent.mo10268get().recordTimer(lazyComponent.mo10268get().createTimer(getEventName(str), getComponentName(str2), getSubComponentName(str2), j, false, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID));
    }
}
