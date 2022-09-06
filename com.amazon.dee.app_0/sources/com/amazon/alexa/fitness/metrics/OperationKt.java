package com.amazon.alexa.fitness.metrics;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Operation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0002\u001a*\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u001a*\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u001a\u0012\u0010\u0012\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u0012\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0001\u001a\u0012\u0010\u0013\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u0013\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000*\n\u0010\u0014\"\u00020\u000e2\u00020\u000e¨\u0006\u0015"}, d2 = {"METRIC_KEY_ERROR_REASON", "", "METRIC_SUFFIX_ERROR", "METRIC_SUFFIX_SUCCESS", "METRIC_SUFFIX_TIME", "METRIC_SUFFIX_TIME_ERROR", "METRIC_SUFFIX_TIME_SUCCESS", "formatStackTraceAsString", "throwable", "", "decorateOnOperationError", "", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "operation", "Lcom/amazon/alexa/fitness/metrics/Operation;", "error", "errorCounterName", HttpClientModule.ElementsRequestKey.OPERATION_NAME, "decorateOnOperationStart", "decorateOnOperationSuccess", "MeasuredOperation", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class OperationKt {
    @NotNull
    public static final String METRIC_KEY_ERROR_REASON = ":ErrorReason";
    @NotNull
    public static final String METRIC_SUFFIX_ERROR = ":Error";
    @NotNull
    public static final String METRIC_SUFFIX_SUCCESS = ":Success";
    @NotNull
    public static final String METRIC_SUFFIX_TIME = ":Time";
    @NotNull
    public static final String METRIC_SUFFIX_TIME_ERROR = ":Error:Time";
    @NotNull
    public static final String METRIC_SUFFIX_TIME_SUCCESS = ":Success:Time";

    public static final void decorateOnOperationError(@NotNull MetricEvent decorateOnOperationError, @NotNull Operation operation, @Nullable Throwable th, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(decorateOnOperationError, "$this$decorateOnOperationError");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        decorateOnOperationError(decorateOnOperationError, operation.getName(), th, str);
    }

    public static /* synthetic */ void decorateOnOperationError$default(MetricEvent metricEvent, Operation operation, Throwable th, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            str = null;
        }
        decorateOnOperationError(metricEvent, operation, th, str);
    }

    public static final void decorateOnOperationStart(@NotNull MetricEvent decorateOnOperationStart, @NotNull Operation operation) {
        Intrinsics.checkParameterIsNotNull(decorateOnOperationStart, "$this$decorateOnOperationStart");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        decorateOnOperationStart(decorateOnOperationStart, operation.getName());
    }

    public static final void decorateOnOperationSuccess(@NotNull MetricEvent decorateOnOperationSuccess, @NotNull Operation operation) {
        Intrinsics.checkParameterIsNotNull(decorateOnOperationSuccess, "$this$decorateOnOperationSuccess");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        decorateOnOperationSuccess(decorateOnOperationSuccess, operation.getName());
    }

    private static final String formatStackTraceAsString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        try {
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            String stringWriter2 = stringWriter.toString();
            Intrinsics.checkExpressionValueIsNotNull(stringWriter2, "stringWriter.toString()");
            CloseableKt.closeFinally(printWriter, null);
            CloseableKt.closeFinally(stringWriter, null);
            return stringWriter2;
        } finally {
        }
    }

    public static final void decorateOnOperationError(@NotNull MetricEvent decorateOnOperationError, @NotNull String operationName, @Nullable Throwable th, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(decorateOnOperationError, "$this$decorateOnOperationError");
        Intrinsics.checkParameterIsNotNull(operationName, "operationName");
        decorateOnOperationError.stopTimer(operationName + METRIC_SUFFIX_TIME);
        decorateOnOperationError.removeTimer(operationName + METRIC_SUFFIX_TIME_SUCCESS);
        decorateOnOperationError.stopTimer(operationName + METRIC_SUFFIX_TIME_ERROR);
        decorateOnOperationError.incrementCounter(operationName + METRIC_SUFFIX_ERROR, 1L);
        if (str != null) {
            decorateOnOperationError.incrementCounter(GeneratedOutlineSupport1.outline75(operationName, ":Error:", str), 1L);
        }
        if (th != null) {
            decorateOnOperationError.attachMetadata(GeneratedOutlineSupport1.outline72(operationName, METRIC_KEY_ERROR_REASON), formatStackTraceAsString(th));
        }
    }

    public static /* synthetic */ void decorateOnOperationError$default(MetricEvent metricEvent, String str, Throwable th, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        decorateOnOperationError(metricEvent, str, th, str2);
    }

    public static final void decorateOnOperationStart(@NotNull MetricEvent decorateOnOperationStart, @NotNull String operationName) {
        Intrinsics.checkParameterIsNotNull(decorateOnOperationStart, "$this$decorateOnOperationStart");
        Intrinsics.checkParameterIsNotNull(operationName, "operationName");
        decorateOnOperationStart.startTimer(operationName + METRIC_SUFFIX_TIME);
        decorateOnOperationStart.startTimer(operationName + METRIC_SUFFIX_TIME_SUCCESS);
        decorateOnOperationStart.startTimer(operationName + METRIC_SUFFIX_TIME_ERROR);
        decorateOnOperationStart.setCounter(operationName + METRIC_SUFFIX_SUCCESS, 0L);
        decorateOnOperationStart.setCounter(operationName + METRIC_SUFFIX_ERROR, 0L);
    }

    public static final void decorateOnOperationSuccess(@NotNull MetricEvent decorateOnOperationSuccess, @NotNull String operationName) {
        Intrinsics.checkParameterIsNotNull(decorateOnOperationSuccess, "$this$decorateOnOperationSuccess");
        Intrinsics.checkParameterIsNotNull(operationName, "operationName");
        decorateOnOperationSuccess.stopTimer(operationName + METRIC_SUFFIX_TIME);
        decorateOnOperationSuccess.stopTimer(operationName + METRIC_SUFFIX_TIME_SUCCESS);
        decorateOnOperationSuccess.removeTimer(operationName + METRIC_SUFFIX_TIME_ERROR);
        decorateOnOperationSuccess.incrementCounter(operationName + METRIC_SUFFIX_SUCCESS, 1L);
    }
}
