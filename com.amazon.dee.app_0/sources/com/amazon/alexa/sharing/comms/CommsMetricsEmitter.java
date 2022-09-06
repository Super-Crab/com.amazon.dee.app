package com.amazon.alexa.sharing.comms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.metrics.TimerMetric;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class CommsMetricsEmitter {
    private static final String FAIL_SUFFIX = ".fail";
    @Nullable
    AlexaCommsCoreMetricsService metricsService;
    @Nullable
    @Inject
    Lazy<AlexaCommsCoreMetricsService> metricsServiceLazy;
    private final String source;
    private static final String TAG = "sharing-metrics-emitter";
    private static final CommsLogger LOG = CommsLogger.getLogger(TAG, AlexaSharingClient.class);

    public CommsMetricsEmitter(@NonNull String str) {
        this.source = str;
    }

    @Nullable
    private String recordMetricByOccurrence(@NonNull String str, @NonNull Map<String, Object> map, boolean z) {
        if (getMetricService() == null) {
            return null;
        }
        this.metricsService.recordOccurrence(str, this.source, z, map);
        return str;
    }

    @Nullable
    public TimerMetric createTimerMetric(@NonNull String str, @NonNull Map<String, Object> map) {
        if (getMetricService() == null) {
            return null;
        }
        return this.metricsService.createTimer(str, this.source, map);
    }

    @Nullable
    @VisibleForTesting
    AlexaCommsCoreMetricsService getMetricService() {
        AlexaCommsCoreMetricsService alexaCommsCoreMetricsService = this.metricsService;
        if (alexaCommsCoreMetricsService != null) {
            return alexaCommsCoreMetricsService;
        }
        Lazy<AlexaCommsCoreMetricsService> lazy = this.metricsServiceLazy;
        if (lazy == null) {
            LOG.e("AlexaCommsCoreMetricsServiceLazy is null");
            return null;
        }
        this.metricsService = lazy.mo358get();
        AlexaCommsCoreMetricsService alexaCommsCoreMetricsService2 = this.metricsService;
        if (alexaCommsCoreMetricsService2 != null) {
            return alexaCommsCoreMetricsService2;
        }
        LOG.e("AlexaCommsCoreMetricsService is null");
        return null;
    }

    @Nullable
    public String recordAPIClientFailMetric(@NonNull String str, @NonNull String str2) {
        if (getMetricService() == null) {
            return null;
        }
        String outline72 = GeneratedOutlineSupport1.outline72(str, ".fail");
        this.metricsService.recordOccurrence(outline72, this.source, true, GeneratedOutlineSupport1.outline134("errorSource", str2, "contentProvider", str2));
        return outline72;
    }

    @Nullable
    public String recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        if (getMetricService() == null) {
            return null;
        }
        this.metricsService.recordCriticalEvent(str, str2, str3, th);
        return str;
    }

    @Nullable
    public String recordNonOccurrenceMetric(@NonNull String str, @NonNull Map<String, Object> map) {
        return recordMetricByOccurrence(str, map, false);
    }

    @Nullable
    public String recordOccurrenceMetric(@NonNull String str, @NonNull Map<String, Object> map) {
        return recordMetricByOccurrence(str, map, true);
    }

    @Nullable
    public TimerMetric recordTimerMetric(@NonNull TimerMetric timerMetric) {
        if (getMetricService() == null) {
            return null;
        }
        this.metricsService.recordTimer(timerMetric);
        return timerMetric;
    }

    public CommsMetricsEmitter(@Nullable Lazy<AlexaCommsCoreMetricsService> lazy, @NonNull String str) {
        this.metricsServiceLazy = lazy;
        this.source = str;
    }
}
