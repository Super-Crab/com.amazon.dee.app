package com.amazon.photos.autosave.internal.metrics;

import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ-\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J%\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/autosave/internal/metrics/MetricsHelper;", "", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "logMetricWithCount", "", "tag", "", "metric", "count", "", "logMetricWithCount$AndroidPhotosAutosave_release", "logMetricWithCountAndTimer", OperationalEventType.TIMER, "", "logMetricWithCountAndTimer$AndroidPhotosAutosave_release", "logMetricWithTimer", "logMetricWithTimer$AndroidPhotosAutosave_release", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MetricsHelper {
    private final Metrics metrics;

    public MetricsHelper(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.metrics = metrics;
    }

    public final void logMetricWithCount$AndroidPhotosAutosave_release(@NotNull String tag, @NotNull final String metric, int i) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        this.metrics.recordCustomMetric(tag, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.autosave.internal.metrics.MetricsHelper$logMetricWithCount$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return metric;
            }
        }, i).withTagName(tag), new MetricRecordingType[0]);
    }

    public final void logMetricWithCountAndTimer$AndroidPhotosAutosave_release(@NotNull String tag, @NotNull final String metric, int i, double d) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        this.metrics.recordCustomMetric(tag, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.autosave.internal.metrics.MetricsHelper$logMetricWithCountAndTimer$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return metric;
            }
        }, i).addTimer(new MetricName() { // from class: com.amazon.photos.autosave.internal.metrics.MetricsHelper$logMetricWithCountAndTimer$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return metric;
            }
        }, d).withTagName(tag), new MetricRecordingType[0]);
    }

    public final void logMetricWithTimer$AndroidPhotosAutosave_release(@NotNull String tag, @NotNull final String metric, double d) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        this.metrics.recordCustomMetric(tag, new ClientMetric().addTimer(new MetricName() { // from class: com.amazon.photos.autosave.internal.metrics.MetricsHelper$logMetricWithTimer$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return metric;
            }
        }, d).withTagName(tag), new MetricRecordingType[0]);
    }
}
