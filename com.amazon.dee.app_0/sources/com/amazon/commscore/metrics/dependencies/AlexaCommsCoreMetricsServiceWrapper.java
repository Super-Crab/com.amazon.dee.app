package com.amazon.commscore.metrics.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.metrics.CounterMetric;
import com.amazon.commscore.api.metrics.InteractionMetric;
import com.amazon.commscore.api.metrics.InteractionMetricType;
import com.amazon.commscore.api.metrics.OperationalMetric;
import com.amazon.commscore.api.metrics.OperationalMetricType;
import com.amazon.commscore.api.metrics.TimerMetric;
import com.amazon.commscore.dependencies.BaseComponentWrapper;
import java.util.Map;
/* loaded from: classes12.dex */
public class AlexaCommsCoreMetricsServiceWrapper extends BaseComponentWrapper<AlexaCommsCoreMetricsService> implements AlexaCommsCoreMetricsService {
    public AlexaCommsCoreMetricsServiceWrapper(ComponentGetter componentGetter, Context context) {
        super(componentGetter, context);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public CounterMetric createCounter(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createCounter(str, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public InteractionMetric createInteractionMetric(@NonNull String str, @NonNull InteractionMetricType interactionMetricType, @NonNull String str2, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createInteractionMetric(str, interactionMetricType, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public OperationalMetric createOperationalMetric(@NonNull String str, @NonNull OperationalMetricType operationalMetricType, @Nullable String str2, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createOperationalMetric(str, operationalMetricType, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createTimer(str, str2, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCounter(@NonNull CounterMetric counterMetric) {
        mo3276getImplementation().recordCounter(counterMetric);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        mo3276getImplementation().recordCriticalEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        mo3276getImplementation().recordInfoEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInteractionMetric(@NonNull InteractionMetric interactionMetric) {
        mo3276getImplementation().recordInteractionMetric(interactionMetric);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(@NonNull String str, @Nullable String str2, boolean z, @Nullable Map<String, Object> map) {
        mo3276getImplementation().recordOccurrence(str, str2, z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOperationalMetric(@NonNull OperationalMetric operationalMetric) {
        mo3276getImplementation().recordOperationalMetric(operationalMetric);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(@NonNull String str) {
        mo3276getImplementation().recordTimer(str);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        mo3276getImplementation().recordWarningEvent(str, str2, str3, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public CounterMetric createCounter(@NonNull String str, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createCounter(str, str2, str3, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public InteractionMetric createInteractionMetric(@NonNull String str, @NonNull InteractionMetricType interactionMetricType, @NonNull String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createInteractionMetric(str, interactionMetricType, str2, str3, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public OperationalMetric createOperationalMetric(@NonNull String str, @NonNull OperationalMetricType operationalMetricType, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createOperationalMetric(str, operationalMetricType, str2, str3, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createTimer(str, str2, str3, map);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.commscore.dependencies.BaseComponentWrapper
    /* renamed from: getImplementation */
    public AlexaCommsCoreMetricsService mo3276getImplementation() {
        return this.commsCoreComponent.getMetricsService();
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        mo3276getImplementation().recordCriticalEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        mo3276getImplementation().recordInfoEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(@NonNull String str, @Nullable String str2, @NonNull String str3, boolean z, @Nullable Map<String, Object> map) {
        mo3276getImplementation().recordOccurrence(str, str2, str3, z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(@NonNull TimerMetric timerMetric) {
        mo3276getImplementation().recordTimer(timerMetric);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        mo3276getImplementation().recordWarningEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, long j, boolean z, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createTimer(str, str2, j, z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @NonNull String str3, long j, boolean z, @Nullable Map<String, Object> map) {
        return mo3276getImplementation().createTimer(str, str2, str3, j, z, map);
    }
}
