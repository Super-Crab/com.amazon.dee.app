package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.logs.ILog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsServiceV2;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsServiceV2Wrapper.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e0\rH\u0002J4\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e0\rH\u0016J4\u0010\u0013\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00122\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\f\u0010\u0015\u001a\u00020\u0016*\u00020\nH\u0002J\f\u0010\u0017\u001a\u00020\u0018*\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/MetricsServiceV2Wrapper;", "Lcom/amazon/alexa/fitness/metrics/MetricService;", "metricsServiceV2", "Lcom/dee/app/metrics/MetricsServiceV2;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/dee/app/metrics/MetricsServiceV2;Lcom/amazon/alexa/fitness/logs/ILog;)V", "buildMetricDescriptor", "Lcom/dee/app/metrics/MetricDescriptor;", "methodName", "", "metric", "customEntries", "", "", "recordCount", "", "value", "", "recordTimer", "valueMs", "asMetricComponentName", "Lcom/dee/app/metrics/MetricComponentName;", "asMetricName", "Lcom/dee/app/metrics/MetricName;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricsServiceV2Wrapper implements MetricService {
    private final ILog log;
    private final MetricsServiceV2 metricsServiceV2;

    @Inject
    public MetricsServiceV2Wrapper(@NotNull MetricsServiceV2 metricsServiceV2, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(metricsServiceV2, "metricsServiceV2");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.metricsServiceV2 = metricsServiceV2;
        this.log = log;
    }

    private final MetricComponentName asMetricComponentName(@NotNull String str) {
        MetricComponentName build = new MetricComponentName.Builder().withMethod(str).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "MetricComponentName.Buil….withMethod(this).build()");
        return build;
    }

    private final MetricName asMetricName(@NotNull String str) {
        MetricName build = new MetricName.Builder(str).withModule(MetricsConstantsKt.METRIC_MODULE).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "MetricName.Builder(this)…le(METRIC_MODULE).build()");
        return build;
    }

    private final MetricDescriptor buildMetricDescriptor(String str, String str2, Map<String, ? extends Object> map) {
        MetricDescriptor build = new MetricDescriptor.Builder(asMetricName(str2), asMetricComponentName(str)).withCustomEntries(map).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "MetricDescriptor.Builder…es(customEntries).build()");
        return build;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricService
    public void recordCount(@NotNull String methodName, @NotNull String metric, long j, @NotNull Map<String, ? extends Object> customEntries) {
        boolean isBlank;
        boolean isBlank2;
        Map<String, ? extends Object> mutableMap;
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        Intrinsics.checkParameterIsNotNull(customEntries, "customEntries");
        isBlank = StringsKt__StringsJVMKt.isBlank(methodName);
        if (!isBlank) {
            isBlank2 = StringsKt__StringsJVMKt.isBlank(metric);
            if (!isBlank2) {
                mutableMap = MapsKt__MapsKt.toMutableMap(customEntries);
                MetricDescriptor buildMetricDescriptor = buildMetricDescriptor(methodName, metric, mutableMap);
                ILog iLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Recording count with value ");
                sb.append(j);
                sb.append(" with metric name ");
                sb.append(metric);
                ILog.DefaultImpls.debug$default(iLog, "MetricsServiceV2Wrapper", GeneratedOutlineSupport1.outline91(sb, " and method name ", methodName), null, 4, null);
                this.metricsServiceV2.recordCount(buildMetricDescriptor, j);
                return;
            }
            throw new IllegalArgumentException("metric cannot be blank".toString());
        }
        throw new IllegalArgumentException("methodName cannot be blank".toString());
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricService
    public void recordTimer(@NotNull String methodName, @NotNull String metric, long j, @NotNull Map<String, ? extends Object> customEntries) {
        boolean isBlank;
        boolean isBlank2;
        Map<String, ? extends Object> mutableMap;
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        Intrinsics.checkParameterIsNotNull(customEntries, "customEntries");
        isBlank = StringsKt__StringsJVMKt.isBlank(methodName);
        boolean z = true;
        if (!isBlank) {
            isBlank2 = StringsKt__StringsJVMKt.isBlank(metric);
            if (!(!isBlank2)) {
                throw new IllegalArgumentException("metric cannot be blank".toString());
            }
            if (j < 0) {
                z = false;
            }
            if (z) {
                mutableMap = MapsKt__MapsKt.toMutableMap(customEntries);
                MetricDescriptor buildMetricDescriptor = buildMetricDescriptor(methodName, metric, mutableMap);
                ILog iLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Recording timer with value ");
                sb.append(j);
                sb.append(" with metric name ");
                sb.append(metric);
                ILog.DefaultImpls.debug$default(iLog, "MetricsServiceV2Wrapper", GeneratedOutlineSupport1.outline91(sb, " and method name ", methodName), null, 4, null);
                this.metricsServiceV2.recordTime(buildMetricDescriptor, j);
                return;
            }
            throw new IllegalArgumentException("valueMs cannot be negative".toString());
        }
        throw new IllegalArgumentException("methodName cannot be blank".toString());
    }
}
