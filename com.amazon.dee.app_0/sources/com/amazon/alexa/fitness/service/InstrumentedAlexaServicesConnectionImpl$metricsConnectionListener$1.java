package com.amazon.alexa.fitness.service;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.Nullable;
/* compiled from: InstrumentedAlexaServicesConnectionImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"com/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1", "Lcom/amazon/alexa/api/AlexaServicesConnection$ConnectionListener;", "metricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "getMetricEvent", "()Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "setMetricEvent", "(Lcom/amazon/alexa/fitness/metrics/MetricEvent;)V", "onConnected", "", "onConnectingFailed", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/alexa/api/AlexaConnectingFailedReason;", "message", "", "onDisconnected", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class InstrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1 implements AlexaServicesConnection.ConnectionListener {
    @Nullable
    private MetricEvent metricEvent;
    final /* synthetic */ InstrumentedAlexaServicesConnectionImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InstrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1(InstrumentedAlexaServicesConnectionImpl instrumentedAlexaServicesConnectionImpl) {
        this.this$0 = instrumentedAlexaServicesConnectionImpl;
    }

    @Nullable
    public final MetricEvent getMetricEvent() {
        return this.metricEvent;
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        ReentrantLock reentrantLock;
        MetricEventRecorder metricEventRecorder;
        reentrantLock = this.this$0.lock;
        reentrantLock.lock();
        try {
            MetricEvent metricEvent = this.metricEvent;
            if (metricEvent != null) {
                metricEvent.stopTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "SuccessLatency"));
                metricEvent.removeTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "FailureLatency"));
                metricEvent.setCounter(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "Success"), 1L);
                metricEventRecorder = this.this$0.metricEventRecorder;
                metricEventRecorder.record(metricEvent);
            }
            this.metricEvent = null;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(@Nullable AlexaConnectingFailedReason alexaConnectingFailedReason, @Nullable String str) {
        ReentrantLock reentrantLock;
        MetricEventRecorder metricEventRecorder;
        reentrantLock = this.this$0.lock;
        reentrantLock.lock();
        try {
            MetricEvent metricEvent = this.metricEvent;
            if (metricEvent != null) {
                metricEvent.removeTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "SuccessLatency"));
                metricEvent.stopTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "FailureLatency"));
                metricEvent.setCounter(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "Success"), 0L);
                metricEventRecorder = this.this$0.metricEventRecorder;
                metricEventRecorder.record(metricEvent);
            }
            this.metricEvent = null;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        ReentrantLock reentrantLock;
        reentrantLock = this.this$0.lock;
        reentrantLock.lock();
        try {
            MetricEvent metricEvent = this.metricEvent;
            if (metricEvent != null) {
                metricEvent.removeTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "SuccessLatency"));
                metricEvent.removeTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "FailureLatency"));
            }
            this.metricEvent = null;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void setMetricEvent(@Nullable MetricEvent metricEvent) {
        this.metricEvent = metricEvent;
    }
}
