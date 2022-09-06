package com.amazon.alexa.growth.metrics;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.growth.coachmark.Utils;
import com.amazon.alexa.mobilytics.Mobilytics;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public class BIMetricsRecorder {
    @VisibleForTesting
    static final String APP_COMPONENT = "AndroidCoachMarks";
    private final Provider<Mobilytics> mobilyticsProvider;

    public BIMetricsRecorder(Provider<Mobilytics> provider) {
        this.mobilyticsProvider = provider;
    }

    public void recordEventOccurrence(String[] strArr, String str) {
        this.mobilyticsProvider.mo10268get().recordOccurrence(Utils.generateMetricEventName(strArr), true, APP_COMPONENT, str);
    }
}
