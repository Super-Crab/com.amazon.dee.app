package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.SpanningMetricOperations;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.lang.Throwable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Callback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ!\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00018\u0001H\u0016¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0015R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/util/MetricsTrackingCallback;", ExifInterface.GPS_DIRECTION_TRUE, "U", "", "Lcom/amazon/alexa/fitness/util/Callback;", HttpClientModule.ElementsRequestKey.OPERATION_NAME, "", "metricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "(Ljava/lang/String;Lcom/amazon/alexa/fitness/metrics/MetricEvent;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;)V", "spanningMetricOperations", "Lcom/amazon/alexa/fitness/metrics/SpanningMetricOperations;", "onError", "", "errorName", "error", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "onSuccess", "result", "(Ljava/lang/Object;)V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricsTrackingCallback<T, U extends Throwable> implements Callback<T, U> {
    private final SpanningMetricOperations spanningMetricOperations;

    public MetricsTrackingCallback(@NotNull String operationName, @NotNull MetricEvent metricEvent, @NotNull MetricEventRecorder metricEventRecorder) {
        Intrinsics.checkParameterIsNotNull(operationName, "operationName");
        Intrinsics.checkParameterIsNotNull(metricEvent, "metricEvent");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        this.spanningMetricOperations = new SpanningMetricOperations(metricEvent, metricEventRecorder, operationName, 0, 8, null);
    }

    @Override // com.amazon.alexa.fitness.util.Callback
    public void onError(@Nullable String str, @Nullable U u) {
        this.spanningMetricOperations.completeAllOperationsOnError(str, u);
    }

    @Override // com.amazon.alexa.fitness.util.Callback
    public void onSuccess(@Nullable T t) {
        this.spanningMetricOperations.completeAllOperationsOnSuccess();
    }
}
