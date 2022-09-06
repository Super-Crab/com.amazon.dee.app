package com.amazon.deecomms.commscore.legacy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.metrics.InteractionMetric;
import com.amazon.commscore.api.metrics.InteractionMetricType;
import com.amazon.commscore.api.metrics.OperationalMetric;
import com.amazon.commscore.api.metrics.OperationalMetricType;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class AlexaCommsCoreMetricsServiceLegacy implements AlexaCommsCoreMetricsService {
    private static final String COMMS_OWNER_IDENTIFIER = "ec2b8c3f-5736-4878-bf6f-70ee753a92b2";
    private static final String DEFAULT_COMPONENT = "Comms";
    private static final String DEFAULT_SUBCOMPONENT = "deecomms.AlexaCommsCoreMetricsServiceLegacy";
    private MetricsService metricsService;

    public AlexaCommsCoreMetricsServiceLegacy() {
        this.metricsService = CommsDaggerWrapper.getComponent().getMetricsService();
    }

    private CounterMetric fromCoreCounterMetric(com.amazon.commscore.api.metrics.CounterMetric counterMetric) {
        CounterMetric generateOperationalWithSource = CounterMetric.generateOperationalWithSource(counterMetric.getName(), counterMetric.getComponent(), counterMetric.getSubComponent());
        generateOperationalWithSource.setCounter(Double.valueOf(Long.valueOf(counterMetric.getCount()).doubleValue()));
        return generateOperationalWithSource;
    }

    private TimerMetric fromCoreTimerMetric(com.amazon.commscore.api.metrics.TimerMetric timerMetric) {
        TimerMetric generateOperational = TimerMetric.generateOperational(timerMetric.getName());
        generateOperational.setMetadata(timerMetric.getCustomEntries());
        return generateOperational;
    }

    private com.amazon.commscore.api.metrics.CounterMetric toCoreCounterMetric(final CounterMetric counterMetric) {
        return new com.amazon.commscore.api.metrics.CounterMetric(counterMetric.getMetricName(), "Comms", counterMetric.getMetadata().get("source") != null ? String.valueOf(counterMetric.getMetadata().get("source")) : DEFAULT_SUBCOMPONENT) { // from class: com.amazon.deecomms.commscore.legacy.AlexaCommsCoreMetricsServiceLegacy.2
            @Override // com.amazon.commscore.api.metrics.CounterMetric
            public long getCount() {
                return Double.valueOf(counterMetric.getCount().doubleValue()).longValue();
            }

            @Override // com.amazon.commscore.api.metrics.CounterMetric
            public void incrementCounter() {
                CounterMetric counterMetric2 = counterMetric;
                counterMetric2.setCounter(Double.valueOf(counterMetric2.getCount().doubleValue() + 1.0d));
            }

            @Override // com.amazon.commscore.api.metrics.CounterMetric
            public void incrementCounterBy(long j) {
                CounterMetric counterMetric2 = counterMetric;
                counterMetric2.setCounter(Double.valueOf(counterMetric2.getCount().doubleValue() + j));
            }

            @Override // com.amazon.commscore.api.metrics.CounterMetric
            public void resetCounter() {
                counterMetric.setCounter(Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR));
            }
        };
    }

    private com.amazon.commscore.api.metrics.TimerMetric toCoreTimerMetric(final TimerMetric timerMetric) {
        com.amazon.commscore.api.metrics.TimerMetric timerMetric2 = new com.amazon.commscore.api.metrics.TimerMetric(timerMetric.getMetricName(), "Comms", timerMetric.getMetadata().get("source") != null ? String.valueOf(timerMetric.getMetadata().get("source")) : DEFAULT_SUBCOMPONENT) { // from class: com.amazon.deecomms.commscore.legacy.AlexaCommsCoreMetricsServiceLegacy.1
            private long elapsedTimeBeforePause = 0;

            @Override // com.amazon.commscore.api.metrics.TimerMetric
            public void finishTimer() {
                timerMetric.stopTimer();
                if (this.elapsedTimeBeforePause > 0) {
                    TimerMetric timerMetric3 = timerMetric;
                    timerMetric3.setTimeDelta(timerMetric3.getTimeDelta() + this.elapsedTimeBeforePause);
                    this.elapsedTimeBeforePause = 0L;
                }
            }

            @Override // com.amazon.commscore.api.metrics.TimerMetric
            public long getElapsedTime() {
                return timerMetric.getTimeDelta();
            }

            @Override // com.amazon.commscore.api.metrics.TimerMetric
            public void pauseTimer() {
                timerMetric.stopTimer();
                this.elapsedTimeBeforePause = timerMetric.getTimeDelta();
            }

            @Override // com.amazon.commscore.api.metrics.TimerMetric
            public void resumeTimer() {
                timerMetric.startTimer();
            }

            @Override // com.amazon.commscore.api.metrics.TimerMetric
            public void finishTimer(long j) {
                finishTimer();
            }
        };
        timerMetric2.setCustomEntries(timerMetric.getMetadata());
        return timerMetric2;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public com.amazon.commscore.api.metrics.CounterMetric createCounter(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        return toCoreCounterMetric(CounterMetric.generateOperationalWithSource(str, "Comms", str2));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public InteractionMetric createInteractionMetric(@NonNull String str, @NonNull InteractionMetricType interactionMetricType, @NonNull String str2, @Nullable Map<String, Object> map) {
        return createInteractionMetric(str, interactionMetricType, str2, "ec2b8c3f-5736-4878-bf6f-70ee753a92b2", map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public OperationalMetric createOperationalMetric(@NonNull String str, @NonNull OperationalMetricType operationalMetricType, @Nullable String str2, @Nullable Map<String, Object> map) {
        return createOperationalMetric(str, operationalMetricType, str2, "ec2b8c3f-5736-4878-bf6f-70ee753a92b2", map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public com.amazon.commscore.api.metrics.TimerMetric createTimer(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        TimerMetric generateOperational = TimerMetric.generateOperational(str);
        if (map == null) {
            map = new HashMap<>();
        }
        generateOperational.setMetadata(map);
        return toCoreTimerMetric(generateOperational);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCounter(@NonNull com.amazon.commscore.api.metrics.CounterMetric counterMetric) {
        this.metricsService.recordCounterMetric(fromCoreCounterMetric(counterMetric));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        this.metricsService.recordCounterMetric(CounterMetric.generateOperationalException(th, str, str2, 0));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        this.metricsService.recordCounterMetric(CounterMetric.generateOperationalException(th, str, str2, 0));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInteractionMetric(InteractionMetric interactionMetric) {
        this.metricsService.recordCounterMetric(CounterMetric.generateClickstream(interactionMetric.getName()));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(@NonNull String str, @Nullable String str2, boolean z, @Nullable Map<String, Object> map) {
        CounterMetric generateOperationalWithSource = CounterMetric.generateOperationalWithSource(str, "Comms", str2);
        generateOperationalWithSource.setMetadata(map);
        this.metricsService.recordCounterMetric(generateOperationalWithSource);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOperationalMetric(OperationalMetric operationalMetric) {
        this.metricsService.recordCounterMetric(CounterMetric.generateOperationalWithSource(operationalMetric.getName(), operationalMetric.getComponent(), operationalMetric.getSubComponent()));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(@NonNull String str) {
        this.metricsService.recordTimerMetric(TimerMetric.generateOperational(str));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        this.metricsService.recordCounterMetric(CounterMetric.generateOperationalException(th, str, str2, 0));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public InteractionMetric createInteractionMetric(@NonNull String str, @NonNull InteractionMetricType interactionMetricType, @NonNull String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return new InteractionMetric(str, "Comms", str2, str3).setCustomEntries(map).ofType(interactionMetricType);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public OperationalMetric createOperationalMetric(@NonNull String str, @NonNull OperationalMetricType operationalMetricType, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        if (OperationalMetricType.TIMER == operationalMetricType) {
            return createTimer(str, str2, str3, map);
        }
        if (OperationalMetricType.COUNTER == operationalMetricType) {
            return createCounter(str, str2, str3, map);
        }
        return new OperationalMetric(str, "Comms", str2, str3).setCustomEntries(map).ofType(operationalMetricType);
    }

    public AlexaCommsCoreMetricsServiceLegacy(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public com.amazon.commscore.api.metrics.CounterMetric createCounter(@NonNull String str, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return createCounter(str, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        recordCriticalEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        recordInfoEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        recordWarningEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(@NonNull String str, @Nullable String str2, @NonNull String str3, boolean z, @Nullable Map<String, Object> map) {
        recordOccurrence(str, str2, z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(@NonNull com.amazon.commscore.api.metrics.TimerMetric timerMetric) {
        this.metricsService.recordTimerMetric(fromCoreTimerMetric(timerMetric));
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public com.amazon.commscore.api.metrics.TimerMetric createTimer(@NonNull String str, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return createTimer(str, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public com.amazon.commscore.api.metrics.TimerMetric createTimer(@NonNull String str, @Nullable String str2, long j, boolean z, @Nullable Map<String, Object> map) {
        return createTimer(str, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public com.amazon.commscore.api.metrics.TimerMetric createTimer(@NonNull String str, @Nullable String str2, @NonNull String str3, long j, boolean z, @Nullable Map<String, Object> map) {
        return createTimer(str, str2, map);
    }
}
