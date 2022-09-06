package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.afx.SensorUnavailabilityReason;
import com.amazon.alexa.fitness.configuration.FitnessAccessorySessionMonitorConfigurationProvider;
import com.amazon.alexa.fitness.configuration.FitnessDataHandlerConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.util.PreconditionsKt;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TimeoutHandler.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B/\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J*\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u000f2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010,\u001a\u00020\u0017H\u0016J\b\u0010-\u001a\u00020\u001eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R:\u0010\u0015\u001a\"\u0012\u0004\u0012\u00020\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0016j\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u000f`\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006."}, d2 = {"Lcom/amazon/alexa/fitness/sdk/TimeoutHandler;", "Lcom/amazon/alexa/fitness/sdk/SensorStateObserver;", "Lcom/amazon/alexa/fitness/sdk/SampleMonitor;", "configurationProvider", "Lcom/amazon/alexa/fitness/configuration/FitnessAccessorySessionMonitorConfigurationProvider;", "dataConfigurationProvider", "Lcom/amazon/alexa/fitness/configuration/FitnessDataHandlerConfigurationProvider;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/configuration/FitnessAccessorySessionMonitorConfigurationProvider;Lcom/amazon/alexa/fitness/configuration/FitnessDataHandlerConfigurationProvider;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/logs/ILog;)V", "lastKnownSampleTime", "", "getLastKnownSampleTime", "()Ljava/lang/Long;", "setLastKnownSampleTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "sensorIdToLastKnownUnavailableTime", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getSensorIdToLastKnownUnavailableTime", "()Ljava/util/HashMap;", "setSensorIdToLastKnownUnavailableTime", "(Ljava/util/HashMap;)V", "handleSample", "", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "onAvailabilityChanged", "sensorId", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "timestamp", "error", "", "recordMetric", "timeoutReason", "Lcom/amazon/alexa/fitness/sdk/Timeout;", "reevaluate", "forSensorId", "reset", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class TimeoutHandler implements SensorStateObserver, SampleMonitor {
    private final FitnessAccessorySessionMonitorConfigurationProvider configurationProvider;
    private final FitnessDataHandlerConfigurationProvider dataConfigurationProvider;
    @Nullable
    private Long lastKnownSampleTime;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    @NotNull
    private HashMap<String, Long> sensorIdToLastKnownUnavailableTime;

    @Inject
    public TimeoutHandler(@NotNull FitnessAccessorySessionMonitorConfigurationProvider configurationProvider, @NotNull FitnessDataHandlerConfigurationProvider dataConfigurationProvider, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(configurationProvider, "configurationProvider");
        Intrinsics.checkParameterIsNotNull(dataConfigurationProvider, "dataConfigurationProvider");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.configurationProvider = configurationProvider;
        this.dataConfigurationProvider = dataConfigurationProvider;
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.log = log;
        this.sensorIdToLastKnownUnavailableTime = new HashMap<>();
    }

    private final void recordMetric(Timeout timeout) {
        this.metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(this.metricEventFactory.createMetricEvent(MetricsClass.TIMEOUT_HANDLER), MetricsConstantsKt.buildMetricName(timeout.name(), MetricsName.AUTOSTOP), 0L, 2, null));
    }

    @Override // com.amazon.alexa.fitness.sdk.SampleMonitor
    @Nullable
    public Long getLastKnownSampleTime() {
        return this.lastKnownSampleTime;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorStateObserver
    @NotNull
    public HashMap<String, Long> getSensorIdToLastKnownUnavailableTime() {
        return this.sensorIdToLastKnownUnavailableTime;
    }

    @Override // com.amazon.alexa.fitness.sdk.SampleMonitor
    public void handleSample(@NotNull Sample sample) {
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        if (sample instanceof Sample.EchoBudSample) {
            setLastKnownSampleTime(Long.valueOf(sample.getReceivedAtTimestamp()));
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorStateObserver
    public void onAvailabilityChanged(@NotNull String sensorId, @NotNull SensorAvailability availability, long j, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
        Intrinsics.checkParameterIsNotNull(availability, "availability");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        PreconditionsKt.checkAfxThread(currentThread);
        if (!(availability instanceof SensorAvailability.Available) && (!(availability instanceof SensorAvailability.Unavailable) || ((SensorAvailability.Unavailable) availability).getReason() != SensorUnavailabilityReason.EarBudsOutOfEar)) {
            if (getSensorIdToLastKnownUnavailableTime().get(sensorId) != null) {
                return;
            }
            ILog.DefaultImpls.info$default(this.log, MetricsClass.TIMEOUT_HANDLER, GeneratedOutlineSupport1.outline56("last known unavailable time updated to ", j), null, 4, null);
            getSensorIdToLastKnownUnavailableTime().put(sensorId, Long.valueOf(j));
            return;
        }
        getSensorIdToLastKnownUnavailableTime().put(sensorId, null);
    }

    @Nullable
    public Timeout reevaluate(@NotNull String forSensorId) {
        Intrinsics.checkParameterIsNotNull(forSensorId, "forSensorId");
        long currentTimeMillis = System.currentTimeMillis();
        Long it2 = getSensorIdToLastKnownUnavailableTime().get(forSensorId);
        if (it2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            if (currentTimeMillis - it2.longValue() > this.configurationProvider.provideFitnessAccessorySessionMonitorConfiguration().getDisconnectTimeoutInMillis()) {
                ILog.DefaultImpls.info$default(this.log, MetricsClass.TIMEOUT_HANDLER, "stopping due to accessory unavailable", null, 4, null);
                recordMetric(Timeout.AccessoryDisconnected);
                return Timeout.AccessoryDisconnected;
            }
        }
        Long lastKnownSampleTime = getLastKnownSampleTime();
        if (lastKnownSampleTime == null || currentTimeMillis - lastKnownSampleTime.longValue() <= this.dataConfigurationProvider.provideFitnessDataHandlerConfiguration().getNoFitnessDataAutoStopInMillis()) {
            return null;
        }
        ILog.DefaultImpls.info$default(this.log, MetricsClass.TIMEOUT_HANDLER, "stopping due to no activity", null, 4, null);
        recordMetric(Timeout.NoSample);
        return Timeout.NoSample;
    }

    @Override // com.amazon.alexa.fitness.sdk.SampleMonitor
    public void reset() {
        setLastKnownSampleTime(null);
    }

    @Override // com.amazon.alexa.fitness.sdk.SampleMonitor
    public void setLastKnownSampleTime(@Nullable Long l) {
        this.lastKnownSampleTime = l;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorStateObserver
    public void setSensorIdToLastKnownUnavailableTime(@NotNull HashMap<String, Long> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.sensorIdToLastKnownUnavailableTime = hashMap;
    }
}
