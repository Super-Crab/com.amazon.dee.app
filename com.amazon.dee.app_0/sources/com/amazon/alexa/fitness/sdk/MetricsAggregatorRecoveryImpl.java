package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.repository.MetricsAggregatorCache;
import java.nio.charset.Charset;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsAggregatorRecoveryImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecoveryImpl;", "Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;", "metricsAggregatorCache", "Lcom/amazon/alexa/fitness/repository/MetricsAggregatorCache;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "(Lcom/amazon/alexa/fitness/repository/MetricsAggregatorCache;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;)V", "recoverAggregatedMetrics", "", "saveAggregatedMetrics", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricsAggregatorRecoveryImpl implements MetricsAggregatorRecovery {
    private final MetricsAggregator metricsAggregator;
    private final MetricsAggregatorCache metricsAggregatorCache;

    @Inject
    public MetricsAggregatorRecoveryImpl(@NotNull MetricsAggregatorCache metricsAggregatorCache, @NotNull MetricsAggregator metricsAggregator) {
        Intrinsics.checkParameterIsNotNull(metricsAggregatorCache, "metricsAggregatorCache");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        this.metricsAggregatorCache = metricsAggregatorCache;
        this.metricsAggregator = metricsAggregator;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r0 == null) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r2 == null) goto L6;
     */
    @Override // com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void recoverAggregatedMetrics() {
        /*
            r4 = this;
            com.amazon.alexa.fitness.repository.MetricsAggregatorCache r0 = r4.metricsAggregatorCache
            byte[] r0 = r0.getAggregatedCounterMetrics()
            com.amazon.alexa.fitness.metrics.MetricsAggregator r1 = r4.metricsAggregator
            r2 = 0
            if (r0 == 0) goto L22
            java.lang.Object r0 = org.apache.commons.lang3.SerializationUtils.deserialize(r0)     // Catch: java.lang.Exception -> L1e
            if (r0 == 0) goto L16
            java.util.Map r0 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r0)     // Catch: java.lang.Exception -> L1e
            goto L1f
        L16:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch: java.lang.Exception -> L1e
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.collections.MutableMap<com.amazon.alexa.fitness.utils.MetricComponent, kotlin.Long>"
            r0.<init>(r3)     // Catch: java.lang.Exception -> L1e
            throw r0     // Catch: java.lang.Exception -> L1e
        L1e:
            r0 = r2
        L1f:
            if (r0 == 0) goto L22
            goto L27
        L22:
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
        L27:
            r1.setAggregatedCounterMetrics(r0)
            com.amazon.alexa.fitness.repository.MetricsAggregatorCache r0 = r4.metricsAggregatorCache
            byte[] r0 = r0.getAggregatedTimerMetrics()
            com.amazon.alexa.fitness.metrics.MetricsAggregator r1 = r4.metricsAggregator
            if (r0 == 0) goto L4a
            java.lang.Object r0 = org.apache.commons.lang3.SerializationUtils.deserialize(r0)     // Catch: java.lang.Exception -> L47
            if (r0 == 0) goto L3f
            java.util.Map r2 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r0)     // Catch: java.lang.Exception -> L47
            goto L47
        L3f:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch: java.lang.Exception -> L47
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.collections.MutableMap<com.amazon.alexa.fitness.utils.MetricComponent, com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer>"
            r0.<init>(r3)     // Catch: java.lang.Exception -> L47
            throw r0     // Catch: java.lang.Exception -> L47
        L47:
            if (r2 == 0) goto L4a
            goto L4f
        L4a:
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
        L4f:
            r1.setAggregatedTimerMetrics(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.sdk.MetricsAggregatorRecoveryImpl.recoverAggregatedMetrics():void");
    }

    @Override // com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery
    public void saveAggregatedMetrics() {
        MetricsAggregatorCache metricsAggregatorCache = this.metricsAggregatorCache;
        String obj = this.metricsAggregator.getAggregatedCounterMetrics().toString();
        Charset charset = Charsets.UTF_8;
        if (obj != null) {
            byte[] bytes = obj.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            metricsAggregatorCache.saveAggregatedCounterMetrics(bytes);
            MetricsAggregatorCache metricsAggregatorCache2 = this.metricsAggregatorCache;
            String obj2 = this.metricsAggregator.getAggregatedTimerMetrics().toString();
            Charset charset2 = Charsets.UTF_8;
            if (obj2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes2 = obj2.getBytes(charset2);
            Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
            metricsAggregatorCache2.saveAggregatedTimerMetrics(bytes2);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
