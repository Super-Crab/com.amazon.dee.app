package com.amazon.alexa.applink.metrics;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public final class MobilyticsMetricsRecorder {
    private static final String OWNER_ID = "d4e28534-61e4-409c-90ef-c8eb6f3c5c25";
    private static final String TAG = "MobilyticsMetricsRecorder";

    private MobilyticsMetricsRecorder() {
    }

    public static void recordCounter(@NonNull String str, @NonNull String str2, @NonNull String str3, int i, @Nullable String str4) {
        Mobilytics mobilytics = (Mobilytics) GeneratedOutlineSupport1.outline21(Mobilytics.class);
        if (mobilytics == null) {
            Log.e(TAG, "Mobilytics is null");
            return;
        }
        MobilyticsMetricsCounter createCounter = mobilytics.createCounter(str3, str, str2, "d4e28534-61e4-409c-90ef-c8eb6f3c5c25");
        createCounter.setContentId(str4);
        createCounter.incrementCounterByValue(i);
        mobilytics.recordCounter(createCounter);
    }

    public static void recordTimer(@NonNull String str, @NonNull String str2, @NonNull String str3, long j, @Nullable String str4) {
        Mobilytics mobilytics = (Mobilytics) GeneratedOutlineSupport1.outline21(Mobilytics.class);
        if (mobilytics == null) {
            Log.e(TAG, "Mobilytics is null");
            return;
        }
        MobilyticsMetricsTimer createTimer = mobilytics.createTimer(str3, str, str2, j, false, "d4e28534-61e4-409c-90ef-c8eb6f3c5c25");
        createTimer.setContentId(str4);
        mobilytics.recordTimer(createTimer);
    }

    public static void recordUserInteractionEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5) {
        Mobilytics mobilytics = (Mobilytics) GeneratedOutlineSupport1.outline21(Mobilytics.class);
        if (mobilytics == null) {
            Log.e(TAG, "Mobilytics is null");
            return;
        }
        MobilyticsUserInteractionEvent createUserInteractionEvent = mobilytics.createUserInteractionEvent(str3, str4, str, str2, "d4e28534-61e4-409c-90ef-c8eb6f3c5c25");
        createUserInteractionEvent.setContentId(str5);
        mobilytics.recordUserInteractionEvent(createUserInteractionEvent);
    }
}
