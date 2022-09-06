package com.amazon.alexa.photos.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.google.common.base.Strings;
import dagger.Lazy;
import java.util.Map;
/* loaded from: classes9.dex */
public class CloudDriveMetrics implements Metrics {
    private static final String ERROR_METRIC_NAME_PREFIX = "Error";
    private static final String MODULE = "PhotosUploaderV2";
    private static final String NATIVE_METRIC_NAME_PREFIX = "native";
    private static final String TIMER_METRIC_NAME_SUFFIX = "time";
    private static final String UNKNOWN_EXCEPTION_CLASS = "Unknown";
    private final Lazy<Mobilytics> mobilytics;

    public CloudDriveMetrics(@NonNull Lazy<Mobilytics> lazy) {
        this.mobilytics = lazy;
    }

    private String buildErrorMetricName(@NonNull String str) {
        return String.format("%s.%s", "Error", str);
    }

    private String buildTimerMetricName(@NonNull String str, @NonNull String str2) {
        return String.format("%s.%s.%s.%s", "native", str2, str, "time");
    }

    @NonNull
    private String getSuffixFromException(@NonNull Exception exc) {
        return !Strings.isNullOrEmpty(exc.getClass().getSimpleName()) ? exc.getClass().getSimpleName() : "Unknown";
    }

    static /* synthetic */ String lambda$recordEvent$0(String str) {
        return str;
    }

    @VisibleForTesting
    public MetricDescriptor buildMetricDescriptor(@NonNull String str, @NonNull String str2) {
        return new MetricDescriptor.Builder(new MetricName.Builder(str).withModule(MODULE).build(), new MetricComponentName.Builder().withCategoryId(str2).build()).build();
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Metrics
    public void recordCustomMetric(@NonNull String str, @NonNull ClientMetric clientMetric, @NonNull MetricRecordingType... metricRecordingTypeArr) {
        for (Map.Entry<com.amazon.clouddrive.android.core.interfaces.MetricName, Integer> entry : clientMetric.getCounters().entrySet()) {
            recordSimpleCounter(str, entry.getKey(), entry.getValue().intValue());
        }
        for (Map.Entry<com.amazon.clouddrive.android.core.interfaces.MetricName, Exception> entry2 : clientMetric.getErrors().entrySet()) {
            recordSimpleErrorEvent(str, entry2.getKey(), entry2.getValue());
        }
        for (Map.Entry<com.amazon.clouddrive.android.core.interfaces.MetricName, Double> entry3 : clientMetric.getTimers().entrySet()) {
            recordSimpleDuration(str, entry3.getKey(), entry3.getValue().doubleValue());
        }
    }

    public void recordEvent(@NonNull String str, @NonNull final String str2) {
        recordSimpleEvent(str, new com.amazon.clouddrive.android.core.interfaces.MetricName() { // from class: com.amazon.alexa.photos.metrics.-$$Lambda$CloudDriveMetrics$GO8f8XkX0wXw-HkhoMOcquvIAvo
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                return str2;
            }
        }, new MetricRecordingType[0]);
    }

    public void recordSimpleCounter(@NonNull String str, @NonNull com.amazon.clouddrive.android.core.interfaces.MetricName metricName, int i) {
        MobilyticsMetricsCounter createCounter = this.mobilytics.mo358get().createCounter(metricName.getEventName(), str, null, OwnerIdentifier.ANDROID_PHOTOS_UPLOADER);
        createCounter.incrementCounterByValue(i);
        this.mobilytics.mo358get().recordCounter(createCounter);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Metrics
    public void recordSimpleDuration(@NonNull String str, @NonNull com.amazon.clouddrive.android.core.interfaces.MetricName metricName, double d) {
        this.mobilytics.mo358get().recordTimer(this.mobilytics.mo358get().createTimer(buildTimerMetricName(metricName.getEventName(), MODULE), MODULE, str, (long) d, true, OwnerIdentifier.ANDROID_PHOTOS_UPLOADER));
    }

    public void recordSimpleErrorEvent(@NonNull String str, @NonNull com.amazon.clouddrive.android.core.interfaces.MetricName metricName, @NonNull String str2, @NonNull Throwable th) {
        this.mobilytics.mo358get().recordCriticalEvent(buildErrorMetricName(metricName.getEventName()), str2, str, str, th, OwnerIdentifier.ANDROID_PHOTOS_UPLOADER);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Metrics
    public void recordSimpleEvent(@NonNull String str, @NonNull com.amazon.clouddrive.android.core.interfaces.MetricName metricName, @NonNull MetricRecordingType... metricRecordingTypeArr) {
        recordSimpleCounter(str, metricName, 1);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Metrics
    public void recordSimpleErrorEvent(@NonNull String str, @NonNull com.amazon.clouddrive.android.core.interfaces.MetricName metricName, @NonNull Exception exc) {
        recordSimpleErrorEvent(str, metricName, getSuffixFromException(exc), exc);
    }
}
