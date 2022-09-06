package com.amazon.alexa.fitness.metrics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u001a\u001e\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u0016\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u001e\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"METRIC_MODULE", "", "buildMetricErrorName", "metricsOperationConstant", "metricsCategoryConstant", "metricsNameConstant", "buildMetricName", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricsConstantsKt {
    @NotNull
    public static final String METRIC_MODULE = "AFX";

    @NotNull
    public static final String buildMetricErrorName(@NotNull String metricsOperationConstant, @NotNull String metricsCategoryConstant, @NotNull String metricsNameConstant) {
        Intrinsics.checkParameterIsNotNull(metricsOperationConstant, "metricsOperationConstant");
        Intrinsics.checkParameterIsNotNull(metricsCategoryConstant, "metricsCategoryConstant");
        Intrinsics.checkParameterIsNotNull(metricsNameConstant, "metricsNameConstant");
        return metricsOperationConstant + ":Error:" + metricsCategoryConstant + JsonReaderKt.COLON + metricsNameConstant;
    }

    @NotNull
    public static final String buildMetricName(@NotNull String metricsOperationConstant, @NotNull String metricsCategoryConstant, @NotNull String metricsNameConstant) {
        Intrinsics.checkParameterIsNotNull(metricsOperationConstant, "metricsOperationConstant");
        Intrinsics.checkParameterIsNotNull(metricsCategoryConstant, "metricsCategoryConstant");
        Intrinsics.checkParameterIsNotNull(metricsNameConstant, "metricsNameConstant");
        return metricsOperationConstant + JsonReaderKt.COLON + metricsCategoryConstant + JsonReaderKt.COLON + metricsNameConstant;
    }

    @NotNull
    public static final String buildMetricName(@NotNull String metricsCategoryConstant, @NotNull String metricsNameConstant) {
        Intrinsics.checkParameterIsNotNull(metricsCategoryConstant, "metricsCategoryConstant");
        Intrinsics.checkParameterIsNotNull(metricsNameConstant, "metricsNameConstant");
        return metricsCategoryConstant + JsonReaderKt.COLON + metricsNameConstant;
    }
}
