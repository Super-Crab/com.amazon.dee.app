package com.amazon.deecomms.commscore;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.metrics.CounterMetric;
import com.amazon.commscore.api.metrics.InteractionMetric;
import com.amazon.commscore.api.metrics.InteractionMetricType;
import com.amazon.commscore.api.metrics.OperationalMetric;
import com.amazon.commscore.api.metrics.OperationalMetricType;
import com.amazon.commscore.api.metrics.TimerMetric;
import com.amazon.commscore.metrics.dependencies.AlexaCommsCoreMetricsServiceWrapper;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.core.FeatureFlagManager;
import java.util.Map;
/* loaded from: classes12.dex */
public class AlexaCommsCoreMetricsServiceImpl implements AlexaCommsCoreMetricsService {
    private AlexaCommsCoreMetricsService alexaCommsCoreMetricsService;
    private AlexaCommsCoreMetricsServiceWrapper alexaCommsCoreMetricsServiceWrapper;
    private FeatureFlagManager featureFlagManager;
    private MetricsService metricsService;

    public AlexaCommsCoreMetricsServiceImpl(ComponentGetter componentGetter, Context context) {
        if (CommsDaggerWrapper.getComponent() != null) {
            this.featureFlagManager = CommsDaggerWrapper.getComponent().getFeatureFlagManager();
            this.metricsService = CommsDaggerWrapper.getComponent().getMetricsService();
            this.alexaCommsCoreMetricsServiceWrapper = new AlexaCommsCoreMetricsServiceWrapper(componentGetter, context);
            initialize();
        }
    }

    private void initialize() {
        this.alexaCommsCoreMetricsService = this.alexaCommsCoreMetricsServiceWrapper;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public CounterMetric createCounter(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createCounter(str, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public InteractionMetric createInteractionMetric(@NonNull String str, @NonNull InteractionMetricType interactionMetricType, @NonNull String str2, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createInteractionMetric(str, interactionMetricType, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public OperationalMetric createOperationalMetric(@NonNull String str, @NonNull OperationalMetricType operationalMetricType, @Nullable String str2, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createOperationalMetric(str, operationalMetricType, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createTimer(str, str2, map);
    }

    @VisibleForTesting
    AlexaCommsCoreMetricsService getCommsCoreMetricsServiceImplementation() {
        return this.alexaCommsCoreMetricsService;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCounter(@NonNull CounterMetric counterMetric) {
        this.alexaCommsCoreMetricsService.recordCounter(counterMetric);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        this.alexaCommsCoreMetricsService.recordCriticalEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        this.alexaCommsCoreMetricsService.recordInfoEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInteractionMetric(InteractionMetric interactionMetric) {
        this.alexaCommsCoreMetricsService.recordInteractionMetric(interactionMetric);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(@NonNull String str, @Nullable String str2, boolean z, @Nullable Map<String, Object> map) {
        this.alexaCommsCoreMetricsService.recordOccurrence(str, str2, z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOperationalMetric(OperationalMetric operationalMetric) {
        this.alexaCommsCoreMetricsService.recordOperationalMetric(operationalMetric);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(@NonNull String str) {
        this.alexaCommsCoreMetricsService.recordTimer(str);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        this.alexaCommsCoreMetricsService.recordWarningEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public CounterMetric createCounter(@NonNull String str, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createCounter(str, str2, str3, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public InteractionMetric createInteractionMetric(@NonNull String str, @NonNull InteractionMetricType interactionMetricType, @NonNull String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createInteractionMetric(str, interactionMetricType, str2, str3, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public OperationalMetric createOperationalMetric(@NonNull String str, @NonNull OperationalMetricType operationalMetricType, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createOperationalMetric(str, operationalMetricType, str2, str3, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createTimer(str, str2, str3, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        this.alexaCommsCoreMetricsService.recordCriticalEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        this.alexaCommsCoreMetricsService.recordInfoEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(@NonNull String str, @Nullable String str2, @NonNull String str3, boolean z, @Nullable Map<String, Object> map) {
        this.alexaCommsCoreMetricsService.recordOccurrence(str, str2, str3, z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(@NonNull TimerMetric timerMetric) {
        this.alexaCommsCoreMetricsService.recordTimer(timerMetric);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        this.alexaCommsCoreMetricsService.recordWarningEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, long j, boolean z, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createTimer(str, str2, j, z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @NonNull String str3, long j, boolean z, @Nullable Map<String, Object> map) {
        return this.alexaCommsCoreMetricsService.createTimer(str, str2, str3, j, z, map);
    }

    @VisibleForTesting
    AlexaCommsCoreMetricsServiceImpl(FeatureFlagManager featureFlagManager, MetricsService metricsService, AlexaCommsCoreMetricsServiceWrapper alexaCommsCoreMetricsServiceWrapper) {
        this.featureFlagManager = featureFlagManager;
        this.metricsService = metricsService;
        this.alexaCommsCoreMetricsServiceWrapper = alexaCommsCoreMetricsServiceWrapper;
        initialize();
    }
}
