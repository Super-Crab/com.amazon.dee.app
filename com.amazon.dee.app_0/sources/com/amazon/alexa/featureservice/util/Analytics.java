package com.amazon.alexa.featureservice.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.constants.FeatureServiceMetrics;
import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class Analytics {
    private static final String TAG = "Analytics";
    private Lazy<Mobilytics> mobilyticsLazy;

    @Inject
    public Analytics(Lazy<Mobilytics> lazy) {
        this.mobilyticsLazy = lazy;
    }

    public void recordCriticalMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        if (this.mobilyticsLazy.mo358get() != null) {
            try {
                this.mobilyticsLazy.mo358get().recordCriticalEvent(FeatureServiceMetrics.METRICS_NAME_PREFIX + str, FeatureServiceMetrics.METRICS_NAME_PREFIX + str, str2, str3, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
            } catch (Exception e) {
                Log.e(TAG, "Failed to log metrics due to exception", e);
            }
        }
    }
}
