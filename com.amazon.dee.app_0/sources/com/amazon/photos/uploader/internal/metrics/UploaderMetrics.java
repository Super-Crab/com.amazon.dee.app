package com.amazon.photos.uploader.internal.metrics;

import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.device.messaging.ADMRegistrationConstants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploaderMetrics.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J%\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00052\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005H\u0002¢\u0006\u0002\u0010\bJ1\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\u0016¢\u0006\u0002\u0010\u000fJ \u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J$\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0016J1\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\u0016¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/photos/uploader/internal/metrics/UploaderMetrics;", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "metrics", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "getMetricRecordingTypes", "", "Lcom/amazon/clouddrive/android/core/interfaces/MetricRecordingType;", "metricRecordingTypes", "([Lcom/amazon/clouddrive/android/core/interfaces/MetricRecordingType;)[Lcom/amazon/clouddrive/android/core/interfaces/MetricRecordingType;", "recordCustomMetric", "", "componentName", "", "clientMetric", "Lcom/amazon/clouddrive/android/core/interfaces/ClientMetric;", "(Ljava/lang/String;Lcom/amazon/clouddrive/android/core/interfaces/ClientMetric;[Lcom/amazon/clouddrive/android/core/interfaces/MetricRecordingType;)V", "recordSimpleDuration", "metricName", "Lcom/amazon/clouddrive/android/core/interfaces/MetricName;", "elapsedTime", "", "recordSimpleErrorEvent", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "recordSimpleEvent", "(Ljava/lang/String;Lcom/amazon/clouddrive/android/core/interfaces/MetricName;[Lcom/amazon/clouddrive/android/core/interfaces/MetricRecordingType;)V", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploaderMetrics implements Metrics {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final MetricRecordingType[] DEFAULT_RECORDING_TYPES = {MetricRecordingType.STANDARD};
    private final Metrics metrics;

    /* compiled from: UploaderMetrics.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/internal/metrics/UploaderMetrics$Companion;", "", "()V", "DEFAULT_RECORDING_TYPES", "", "Lcom/amazon/clouddrive/android/core/interfaces/MetricRecordingType;", "[Lcom/amazon/clouddrive/android/core/interfaces/MetricRecordingType;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public UploaderMetrics(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.metrics = metrics;
    }

    private final MetricRecordingType[] getMetricRecordingTypes(MetricRecordingType[] metricRecordingTypeArr) {
        return metricRecordingTypeArr.length == 0 ? DEFAULT_RECORDING_TYPES : metricRecordingTypeArr;
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Metrics
    public void recordCustomMetric(@NotNull String componentName, @NotNull ClientMetric clientMetric, @NotNull MetricRecordingType... metricRecordingTypes) {
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        Intrinsics.checkParameterIsNotNull(clientMetric, "clientMetric");
        Intrinsics.checkParameterIsNotNull(metricRecordingTypes, "metricRecordingTypes");
        Metrics metrics = this.metrics;
        MetricRecordingType[] metricRecordingTypes2 = getMetricRecordingTypes(metricRecordingTypes);
        metrics.recordCustomMetric(componentName, clientMetric, (MetricRecordingType[]) Arrays.copyOf(metricRecordingTypes2, metricRecordingTypes2.length));
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Metrics
    public void recordSimpleDuration(@NotNull String componentName, @NotNull MetricName metricName, double d) {
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        this.metrics.recordSimpleDuration(componentName, metricName, d);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Metrics
    public void recordSimpleErrorEvent(@NotNull String componentName, @NotNull MetricName metricName, @NotNull Exception exception) {
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        this.metrics.recordSimpleErrorEvent(componentName, metricName, exception);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Metrics
    public void recordSimpleEvent(@NotNull String componentName, @NotNull MetricName metricName, @NotNull MetricRecordingType... metricRecordingTypes) {
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        Intrinsics.checkParameterIsNotNull(metricRecordingTypes, "metricRecordingTypes");
        Metrics metrics = this.metrics;
        MetricRecordingType[] metricRecordingTypes2 = getMetricRecordingTypes(metricRecordingTypes);
        metrics.recordSimpleEvent(componentName, metricName, (MetricRecordingType[]) Arrays.copyOf(metricRecordingTypes2, metricRecordingTypes2.length));
    }
}
