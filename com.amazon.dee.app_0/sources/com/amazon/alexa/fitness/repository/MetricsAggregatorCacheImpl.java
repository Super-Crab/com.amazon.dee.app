package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.util.ErrorReporting;
import com.dee.app.cachemanager.CacheMetadata;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MetricsAggregatorCacheImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/fitness/repository/MetricsAggregatorCacheImpl;", "Lcom/amazon/alexa/fitness/repository/MetricsAggregatorCache;", "cacheProvider", "Lcom/amazon/alexa/fitness/repository/CacheProvider;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/repository/CacheProvider;Lcom/amazon/alexa/fitness/logs/ILog;)V", "cacheMetaData", "Lcom/dee/app/cachemanager/CacheMetadata;", "deleteAggregatedMetrics", "", "getAggregatedCounterMetrics", "", "getAggregatedTimerMetrics", "saveAggregatedCounterMetrics", "aggregatedCounterMetrics", "saveAggregatedTimerMetrics", "aggregatedTimerMetrics", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricsAggregatorCacheImpl implements MetricsAggregatorCache {
    private final CacheMetadata cacheMetaData;
    private final CacheProvider cacheProvider;
    private final ILog log;

    @Inject
    public MetricsAggregatorCacheImpl(@NotNull CacheProvider cacheProvider, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(cacheProvider, "cacheProvider");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.cacheProvider = cacheProvider;
        this.log = log;
        this.cacheMetaData = new CacheMetadata(1);
    }

    @Override // com.amazon.alexa.fitness.repository.MetricsAggregatorCache
    public void deleteAggregatedMetrics() {
        try {
            this.cacheProvider.getRecoveryCache().remove("metricCounter", this.cacheMetaData).toBlocking().singleOrDefault(null);
            ILog.DefaultImpls.info$default(this.log, "MetricsAggregatorCache", "counter metrics deleted.", null, 4, null);
        } catch (Throwable th) {
            this.log.error("MetricsAggregatorCache", "error deleting aggregated counter metrics", th);
            ErrorReporting.Companion.reportThrowable(th);
        }
        try {
            this.cacheProvider.getRecoveryCache().remove("metricTimer", this.cacheMetaData).toBlocking().singleOrDefault(null);
            ILog.DefaultImpls.info$default(this.log, "MetricsAggregatorCache", "timer metrics deleted.", null, 4, null);
        } catch (Throwable th2) {
            this.log.error("MetricsAggregatorCache", "error deleting aggregated timer metrics", th2);
            ErrorReporting.Companion.reportThrowable(th2);
        }
    }

    @Override // com.amazon.alexa.fitness.repository.MetricsAggregatorCache
    @Nullable
    public byte[] getAggregatedCounterMetrics() {
        try {
            byte[] orNull = this.cacheProvider.getRecoveryCache().get("metricCounter", this.cacheMetaData).toBlocking().single().orNull();
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "MetricsAggregatorCache", orNull + " retrieved as Metrics", null, 4, null);
            return orNull;
        } catch (Throwable th) {
            this.log.error("MetricsAggregatorCache", "error getting aggregated counter metrics", th);
            ErrorReporting.Companion.reportThrowable(th);
            return null;
        }
    }

    @Override // com.amazon.alexa.fitness.repository.MetricsAggregatorCache
    @Nullable
    public byte[] getAggregatedTimerMetrics() {
        try {
            byte[] orNull = this.cacheProvider.getRecoveryCache().get("metricTimer", this.cacheMetaData).toBlocking().single().orNull();
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "MetricsAggregatorCache", orNull + " retrieved as Metrics", null, 4, null);
            return orNull;
        } catch (Throwable th) {
            this.log.error("MetricsAggregatorCache", "error getting aggregated timer metrics", th);
            ErrorReporting.Companion.reportThrowable(th);
            return null;
        }
    }

    @Override // com.amazon.alexa.fitness.repository.MetricsAggregatorCache
    public void saveAggregatedCounterMetrics(@NotNull byte[] aggregatedCounterMetrics) {
        Intrinsics.checkParameterIsNotNull(aggregatedCounterMetrics, "aggregatedCounterMetrics");
        try {
            this.cacheProvider.getRecoveryCache().put("metricCounter", aggregatedCounterMetrics, this.cacheMetaData).toBlocking().singleOrDefault(null);
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "MetricsAggregatorCache", "Session saved - " + aggregatedCounterMetrics, null, 4, null);
        } catch (Throwable th) {
            this.log.error("MetricsAggregatorCache", "error saving aggregated counter metrics", th);
            ErrorReporting.Companion.reportThrowable(th);
        }
    }

    @Override // com.amazon.alexa.fitness.repository.MetricsAggregatorCache
    public void saveAggregatedTimerMetrics(@NotNull byte[] aggregatedTimerMetrics) {
        Intrinsics.checkParameterIsNotNull(aggregatedTimerMetrics, "aggregatedTimerMetrics");
        try {
            this.cacheProvider.getRecoveryCache().put("metricTimer", aggregatedTimerMetrics, this.cacheMetaData).toBlocking().singleOrDefault(null);
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "MetricsAggregatorCache", "Session saved - " + aggregatedTimerMetrics, null, 4, null);
        } catch (Throwable th) {
            this.log.error("MetricsAggregatorCache", "error saving aggregated timer metrics", th);
            ErrorReporting.Companion.reportThrowable(th);
        }
    }
}
