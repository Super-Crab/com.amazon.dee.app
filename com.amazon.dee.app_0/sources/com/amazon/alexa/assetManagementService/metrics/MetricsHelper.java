package com.amazon.alexa.assetManagementService.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceMetricsConstants;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes6.dex */
public class MetricsHelper {
    private static MetricsHelper instance;
    private Lazy<Mobilytics> mobilytics;

    public MetricsHelper(Lazy<Mobilytics> lazy) {
        this.mobilytics = lazy;
    }

    public static MetricsHelper getInstance(Lazy<Mobilytics> lazy) {
        if (instance == null) {
            synchronized (MetricsHelper.class) {
                instance = new MetricsHelper(lazy);
            }
        }
        return instance;
    }

    public MobilyticsMetricsTimer createTimerEvent(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return this.mobilytics.mo358get().createTimer(str, str2, str3);
    }

    public void recordErrorMetricsEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Exception exc) {
        this.mobilytics.mo358get().recordCriticalEvent(GeneratedOutlineSupport1.outline72(GetAssetURLServiceMetricsConstants.METRICS_NAME_PREFIX, str), GeneratedOutlineSupport1.outline72(GetAssetURLServiceMetricsConstants.METRICS_NAME_PREFIX, str2), str3, str4, exc);
    }

    public void recordOccurrenceMetricsEvent(@NonNull String str, @NonNull boolean z, @NonNull String str2, @NonNull String str3) {
        this.mobilytics.mo358get().recordOccurrence(str, z, str2, str3);
    }
}
