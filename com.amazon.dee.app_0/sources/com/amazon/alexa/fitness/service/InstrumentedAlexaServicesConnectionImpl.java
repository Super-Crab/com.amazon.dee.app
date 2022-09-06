package com.amazon.alexa.fitness.service;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.AggregatedMetricsConstants;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.mode.debug.EmulateConnection;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InstrumentedAlexaServicesConnectionImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000_\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u000e\b\u0007\u0018\u00002\u00020\u0001:\u0001&B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0019H\u0016J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0019H\u0016J\b\u0010%\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000f¨\u0006'"}, d2 = {"Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnectionImpl;", "Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/api/AlexaServicesConnection;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/logs/ILog;)V", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "metricsConnectionListener", "com/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1", "Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1;", EmulateConnection.EXTRA_CONNECT, "", "metricEventInput", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "deregisterContextsProvider", "alexaContextsProvider", "Lcom/amazon/alexa/api/AlexaContextsProvider;", Metrics.DISCONNECT, "isConnected", "", "isDisconnected", "registerConnectionListener", "connectionListener", "Lcom/amazon/alexa/api/AlexaServicesConnection$ConnectionListener;", "registerContextsProvider", "send", "alexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "requiresContext", "setContextCachingEnabled", "enableContextCaching", "start", "AfxAlexaApiCallbacks", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class InstrumentedAlexaServicesConnectionImpl implements InstrumentedAlexaServicesConnection {
    private final AlexaServicesConnection alexaServicesConnection;
    private final ReentrantLock lock;
    private final ILog log;
    private final MetricEventRecorder metricEventRecorder;
    private final MetricsAggregator metricsAggregator;
    private final InstrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1 metricsConnectionListener;

    /* compiled from: InstrumentedAlexaServicesConnectionImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnectionImpl$AfxAlexaApiCallbacks;", "Lcom/amazon/alexa/api/AlexaApiCallbacks;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "startTime", "", "(Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;J)V", "onFailure", "", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/alexa/api/ApiCallFailure;", "onSuccess", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    private static final class AfxAlexaApiCallbacks extends AlexaApiCallbacks {
        private final MetricsAggregator metricsAggregator;
        private final long startTime;

        public /* synthetic */ AfxAlexaApiCallbacks(MetricsAggregator metricsAggregator, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(metricsAggregator, (i & 2) != 0 ? DateTime.Companion.now().getEpochMilli() : j);
        }

        @Override // com.amazon.alexa.api.AlexaApiCallbacks
        public void onFailure(@Nullable ApiCallFailure apiCallFailure) {
            super.onFailure(apiCallFailure);
            this.metricsAggregator.recordTimer(AggregatedMetricsConstants.Companion.getAVS_EVENT_DURATION(), DateTime.Companion.now().getEpochMilli() - this.startTime);
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getAVS_FAILURE_EVENT_COUNT());
        }

        @Override // com.amazon.alexa.api.AlexaApiCallbacks
        public void onSuccess() {
            super.onSuccess();
            this.metricsAggregator.recordTimer(AggregatedMetricsConstants.Companion.getAVS_EVENT_DURATION(), DateTime.Companion.now().getEpochMilli() - this.startTime);
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getAVS_SUCCESS_EVENT_COUNT());
        }

        public AfxAlexaApiCallbacks(@NotNull MetricsAggregator metricsAggregator, long j) {
            Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
            this.metricsAggregator = metricsAggregator;
            this.startTime = j;
        }
    }

    @Inject
    public InstrumentedAlexaServicesConnectionImpl(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull MetricsAggregator metricsAggregator, @NotNull MetricEventRecorder metricEventRecorder, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.alexaServicesConnection = alexaServicesConnection;
        this.metricsAggregator = metricsAggregator;
        this.metricEventRecorder = metricEventRecorder;
        this.log = log;
        this.lock = new ReentrantLock();
        this.metricsConnectionListener = new InstrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1(this);
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public void connect(@NotNull MetricEvent metricEventInput) {
        Intrinsics.checkParameterIsNotNull(metricEventInput, "metricEventInput");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.alexaServicesConnection.isConnected() && !this.alexaServicesConnection.isConnecting()) {
                InstrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1 instrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1 = this.metricsConnectionListener;
                metricEventInput.startTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "SuccessLatency"));
                metricEventInput.startTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.CONNECT, MetricsCategory.ALEXA_SERVICES, "FailureLatency"));
                instrumentedAlexaServicesConnectionImpl$metricsConnectionListener$1.setMetricEvent(metricEventInput);
                this.alexaServicesConnection.connect();
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public void deregisterContextsProvider(@NotNull AlexaContextsProvider alexaContextsProvider) {
        Intrinsics.checkParameterIsNotNull(alexaContextsProvider, "alexaContextsProvider");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            AlexaServicesApis.ContextProviders.deregister(this.alexaServicesConnection, alexaContextsProvider);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public void disconnect() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            this.alexaServicesConnection.disconnect();
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public boolean isConnected() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return this.alexaServicesConnection.isConnected();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public boolean isDisconnected() {
        boolean z;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.alexaServicesConnection.isConnected()) {
                if (!this.alexaServicesConnection.isConnecting()) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public void registerConnectionListener(@NotNull AlexaServicesConnection.ConnectionListener connectionListener) {
        Intrinsics.checkParameterIsNotNull(connectionListener, "connectionListener");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            this.alexaServicesConnection.registerListener(connectionListener);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public void registerContextsProvider(@NotNull AlexaContextsProvider alexaContextsProvider) {
        Intrinsics.checkParameterIsNotNull(alexaContextsProvider, "alexaContextsProvider");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.alexaServicesConnection.isConnected()) {
                AlexaServicesApis.ContextProviders.register(this.alexaServicesConnection, alexaContextsProvider);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public void send(@NotNull AlexaEvent alexaEvent, boolean z) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.alexaServicesConnection.isConnected()) {
                return;
            }
            AlexaServices.EventSender.send(this.alexaServicesConnection, alexaEvent, z, new AfxAlexaApiCallbacks(this.metricsAggregator, 0L, 2, null));
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection
    public void setContextCachingEnabled(boolean z) {
        Set mutableSetOf;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (isConnected()) {
                AlexaServicesConnection alexaServicesConnection = this.alexaServicesConnection;
                mutableSetOf = SetsKt__SetsKt.mutableSetOf("Alexa.Health.Fitness");
                AlexaServicesApis.ContextProviders.setContextCachingEnabled(alexaServicesConnection, mutableSetOf, z);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.service.Startable
    public void start() {
        this.alexaServicesConnection.registerListener(this.metricsConnectionListener);
    }
}
