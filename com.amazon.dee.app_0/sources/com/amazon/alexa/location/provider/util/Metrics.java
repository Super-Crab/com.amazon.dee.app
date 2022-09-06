package com.amazon.alexa.location.provider.util;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
/* loaded from: classes9.dex */
public final class Metrics {
    public static final String COMPONENT_NAME = "LocationSkillsProvider";

    private Metrics() {
    }

    public static void recordOccurrence(@NonNull String str, @NonNull String str2) {
        ((Mobilytics) ComponentRegistry.getInstance().getLazy(Mobilytics.class).mo10268get()).recordOccurrence(str, true, COMPONENT_NAME, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static void recordSuccess(@NonNull String str, boolean z, @NonNull String str2) {
        ((Mobilytics) ComponentRegistry.getInstance().getLazy(Mobilytics.class).mo10268get()).recordOccurrence(str, z, COMPONENT_NAME, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public static void recordValue(@NonNull String str, long j, @NonNull String str2) {
        Mobilytics mobilytics = (Mobilytics) ComponentRegistry.getInstance().getLazy(Mobilytics.class).mo10268get();
        MobilyticsMetricsCounter createCounter = mobilytics.createCounter(str, COMPONENT_NAME, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        createCounter.incrementCounterByValue(j);
        mobilytics.recordCounter(createCounter);
    }
}
