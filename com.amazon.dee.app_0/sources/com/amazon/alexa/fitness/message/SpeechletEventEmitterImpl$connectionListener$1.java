package com.amazon.alexa.fitness.message;

import amazon.speech.simclient.settings.Settings;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.fitness.configuration.SpeechletEventEmitterConfiguration;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection;
import com.amazon.alexa.fitness.session.FitnessSessionStateService;
import com.amazon.alexa.fitness.util.Callback;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SpeechletEventEmitterImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000A\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\u000e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"com/amazon/alexa/fitness/message/SpeechletEventEmitterImpl$connectionListener$1", "Lcom/amazon/alexa/api/AlexaServicesConnection$ConnectionListener;", "alexaEventQueue", "Ljava/util/Queue;", "Lcom/amazon/alexa/fitness/message/AlexaEventMessageQueueItem;", "getAlexaEventQueue", "()Ljava/util/Queue;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "retryCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "onConnected", "", "onConnectingFailed", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/alexa/api/AlexaConnectingFailedReason;", "message", "", "onDisconnected", "retryConnectToAlexaServicesConnection", "retryNumber", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SpeechletEventEmitterImpl$connectionListener$1 implements AlexaServicesConnection.ConnectionListener {
    final /* synthetic */ SpeechletEventEmitterImpl this$0;
    private final AtomicInteger retryCount = new AtomicInteger(0);
    @NotNull
    private final Queue<AlexaEventMessageQueueItem> alexaEventQueue = new LinkedList();
    @NotNull
    private final Handler handler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpeechletEventEmitterImpl$connectionListener$1(SpeechletEventEmitterImpl speechletEventEmitterImpl) {
        this.this$0 = speechletEventEmitterImpl;
    }

    @NotNull
    public final Queue<AlexaEventMessageQueueItem> getAlexaEventQueue() {
        return this.alexaEventQueue;
    }

    @NotNull
    public final Handler getHandler() {
        return this.handler;
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        ReentrantLock reentrantLock;
        ILog iLog;
        MetricEventRecorder metricEventRecorder;
        MetricEventFactory metricEventFactory;
        FitnessSessionStateService fitnessSessionStateService;
        ILog iLog2;
        InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection;
        reentrantLock = this.this$0.lock;
        reentrantLock.lock();
        try {
            iLog = this.this$0.log;
            ILog.DefaultImpls.debug$default(iLog, "SpeechletEventEmitter", "AlexaServicesConnection connected with " + this.alexaEventQueue.size() + " messages in the alexaEventQueue.", null, 4, null);
            while (!this.alexaEventQueue.isEmpty()) {
                AlexaEventMessageQueueItem remove = this.alexaEventQueue.remove();
                this.this$0.send(remove.getAlexaEvent());
                Callback.DefaultImpls.onSuccess$default(remove.getCallback(), null, 1, null);
            }
            metricEventRecorder = this.this$0.metricEventRecorder;
            metricEventFactory = this.this$0.metricEventFactory;
            MetricEvent createMetricEvent = metricEventFactory.createMetricEvent(MetricsClass.EVENT_EMITTER);
            createMetricEvent.setCounter(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, "Retry", MetricsName.ATTEMPT), this.retryCount.longValue());
            metricEventRecorder.record(createMetricEvent);
            this.retryCount.set(0);
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            fitnessSessionStateService = this.this$0.fitnessSessionStateService;
            if (!fitnessSessionStateService.isFitnessSessionInactive()) {
                return;
            }
            iLog2 = this.this$0.log;
            ILog.DefaultImpls.info$default(iLog2, "SpeechletEventEmitter", "disconnecting as session is not active", null, 4, null);
            instrumentedAlexaServicesConnection = this.this$0.instrumentedAlexaServicesConnection;
            instrumentedAlexaServicesConnection.disconnect();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(@Nullable final AlexaConnectingFailedReason alexaConnectingFailedReason, @Nullable String str) {
        ILog iLog;
        ReentrantLock reentrantLock;
        SpeechletEventEmitterConfiguration configuration;
        ILog iLog2;
        SpeechletEventEmitterConfiguration configuration2;
        MetricEventRecorder metricEventRecorder;
        MetricEventFactory metricEventFactory;
        SpeechletEventEmitterConfiguration configuration3;
        iLog = this.this$0.log;
        ILog.DefaultImpls.error$default(iLog, "SpeechletEventEmitter", "AlexaServicesConnection failed to connect, reason: " + alexaConnectingFailedReason + ", message: " + str, null, 4, null);
        reentrantLock = this.this$0.lock;
        reentrantLock.lock();
        try {
            final int incrementAndGet = this.retryCount.incrementAndGet();
            configuration = this.this$0.getConfiguration();
            if (incrementAndGet > configuration.getMaximumAttempts()) {
                iLog2 = this.this$0.log;
                StringBuilder sb = new StringBuilder();
                sb.append("AlexaServicesConnection failed to connect after ");
                configuration2 = this.this$0.getConfiguration();
                sb.append(configuration2.getMaximumAttempts());
                sb.append(" retry attempts.");
                ILog.DefaultImpls.error$default(iLog2, "SpeechletEventEmitter", sb.toString(), null, 4, null);
                while (!this.alexaEventQueue.isEmpty()) {
                    Callback.DefaultImpls.onError$default(this.alexaEventQueue.remove().getCallback(), MetricsConstantsKt.buildMetricName(MetricsCategory.ALEXA_SERVICES_CONNECTION, String.valueOf(alexaConnectingFailedReason)), null, 2, null);
                }
                metricEventRecorder = this.this$0.metricEventRecorder;
                metricEventFactory = this.this$0.metricEventFactory;
                MetricEvent createMetricEvent = metricEventFactory.createMetricEvent(MetricsClass.EVENT_EMITTER);
                createMetricEvent.setCounter(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, "Retry", MetricsName.ATTEMPT), this.retryCount.longValue());
                metricEventRecorder.record(createMetricEvent);
                this.retryCount.set(0);
                Unit unit = Unit.INSTANCE;
            } else {
                Handler handler = this.handler;
                Runnable runnable = new Runnable() { // from class: com.amazon.alexa.fitness.message.SpeechletEventEmitterImpl$connectionListener$1$onConnectingFailed$$inlined$withLock$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.retryConnectToAlexaServicesConnection(incrementAndGet);
                    }
                };
                configuration3 = this.this$0.getConfiguration();
                Boolean.valueOf(handler.postDelayed(runnable, SpeechletEventEmitterImplKt.calculateDelay(incrementAndGet, configuration3.getBaseDelayInMillis())));
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        ILog iLog;
        iLog = this.this$0.log;
        ILog.DefaultImpls.info$default(iLog, "SpeechletEventEmitter", "connectionListener::onDisconnected() invoked...", null, 4, null);
    }

    public final void retryConnectToAlexaServicesConnection(int i) {
        ILog iLog;
        InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection;
        MetricEventFactory metricEventFactory;
        iLog = this.this$0.log;
        ILog.DefaultImpls.info$default(iLog, "SpeechletEventEmitter", GeneratedOutlineSupport1.outline52("Retry ", i, " connecting to AlexaServicesConnection."), null, 4, null);
        instrumentedAlexaServicesConnection = this.this$0.instrumentedAlexaServicesConnection;
        metricEventFactory = this.this$0.metricEventFactory;
        instrumentedAlexaServicesConnection.connect(metricEventFactory.createMetricEvent(MetricsClass.EVENT_EMITTER));
    }
}
