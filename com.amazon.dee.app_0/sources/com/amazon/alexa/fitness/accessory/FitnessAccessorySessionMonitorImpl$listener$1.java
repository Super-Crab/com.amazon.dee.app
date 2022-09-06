package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.afx.SensorUnavailabilityReason;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.AggregatedMetricsConstants;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.fitness.metrics.Operation;
import com.amazon.alexa.fitness.metrics.OperationWithMetricEvent;
import com.amazon.alexa.fitness.metrics.RxJavaMetricsExtensionsKt;
import com.amazon.alexa.fitness.metrics.SpanningMetricOperations;
import com.amazon.alexa.fitness.util.Callback;
import com.amazon.alexa.fitness.util.DisposableManager;
import com.amazon.alexa.fitness.util.DisposableManagerFactory;
import com.amazon.alexa.fitness.util.RxJavaUtilsKt;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAccessorySessionMonitorImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J,\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0010"}, d2 = {"com/amazon/alexa/fitness/accessory/FitnessAccessorySessionMonitorImpl$listener$1", "Lcom/amazon/alexa/accessory/AccessorySessionListener;", "onAccessorySessionConnected", "", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "onAccessorySessionConnectedWithMetrics", "metricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "callback", "Lcom/amazon/alexa/fitness/util/Callback;", "", "onAccessorySessionDisconnected", "onAccessorySessionFailed", "e", "onAccessorySessionReleased", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAccessorySessionMonitorImpl$listener$1 extends AccessorySessionListener {
    final /* synthetic */ FitnessAccessorySessionMonitorImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FitnessAccessorySessionMonitorImpl$listener$1(FitnessAccessorySessionMonitorImpl fitnessAccessorySessionMonitorImpl) {
        this.this$0 = fitnessAccessorySessionMonitorImpl;
    }

    private final void onAccessorySessionConnectedWithMetrics(final Accessory accessory, final MetricEvent metricEvent, final Callback<Unit, Throwable> callback) {
        ILog iLog;
        Accessories accessories;
        DisposableManagerFactory disposableManagerFactory;
        ILog iLog2;
        iLog = this.this$0.log;
        ILog.DefaultImpls.info$default(iLog, MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Processing onAccessorySessionConnected for accessory: " + accessory, null, 4, null);
        accessories = this.this$0.accessories;
        String address = accessory.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address, "accessory.address");
        final AccessorySession accessorySession = accessories.getAccessorySession(address);
        metricEvent.startTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.ON_RESPONSE, "DeviceInformation", "Success"));
        disposableManagerFactory = this.this$0.disposableManagerFactory;
        final DisposableManager createDisposableManager = disposableManagerFactory.createDisposableManager();
        OperationWithMetricEvent with = new Operation(MetricsOperation.ON_RESPONSE).with(metricEvent);
        Single<Set<Device.DeviceInformation>> firstOrError = accessorySession.getDeviceRepository().queryDeviceInformationSet().firstOrError();
        Intrinsics.checkExpressionValueIsNotNull(firstOrError, "accessorySession.getDevi…ationSet().firstOrError()");
        Disposable subscribe = RxJavaMetricsExtensionsKt.instrumentAsync(with, firstOrError).subscribe(new Consumer<Set<Device.DeviceInformation>>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$listener$1$onAccessorySessionConnectedWithMetrics$$inlined$also$lambda$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Set<Device.DeviceInformation> deviceInformationSet) {
                Intrinsics.checkExpressionValueIsNotNull(deviceInformationSet, "deviceInformationSet");
                Device.DeviceInformation deviceInformation = (Device.DeviceInformation) CollectionsKt.firstOrNull(deviceInformationSet);
                if (deviceInformation != null) {
                    this.this$0.onDeviceSubscribeSuccess(deviceInformation, accessory, DisposableManager.this, metricEvent, callback);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$listener$1$onAccessorySessionConnectedWithMetrics$$inlined$also$lambda$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable th) {
                FitnessAccessorySessionMonitorImpl$listener$1.this.this$0.onDeviceSubscribeFailure(metricEvent, callback);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Operation(MetricsOperati…metricEvent, callback) })");
        RxJavaUtilsKt.addTo(subscribe, createDisposableManager);
        iLog2 = this.this$0.log;
        ILog.DefaultImpls.debug$default(iLog2, MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Successfully completed onAccessorySessionConnected for accessory: " + accessory, null, 4, null);
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionConnected(@NotNull Accessory accessory) {
        MetricEventFactory metricEventFactory;
        MetricsAggregator metricsAggregator;
        MetricsAggregator metricsAggregator2;
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        metricEventFactory = this.this$0.metricEventFactory;
        final MetricEvent createMetricEvent = metricEventFactory.createMetricEvent(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR);
        metricsAggregator = this.this$0.metricsAggregator;
        metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getRECONNECT());
        metricsAggregator2 = this.this$0.metricsAggregator;
        metricsAggregator2.pauseTimer(AggregatedMetricsConstants.Companion.getDISCONNECTED_DURATION());
        onAccessorySessionConnectedWithMetrics(accessory, createMetricEvent, new Callback<Unit, Throwable>(createMetricEvent) { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$listener$1$onAccessorySessionConnected$callback$1
            final /* synthetic */ MetricEvent $metricEvent;
            @NotNull
            private final SpanningMetricOperations spanningMetricOperation;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                MetricEventRecorder metricEventRecorder;
                this.$metricEvent = createMetricEvent;
                metricEventRecorder = FitnessAccessorySessionMonitorImpl$listener$1.this.this$0.metricEventRecorder;
                this.spanningMetricOperation = new SpanningMetricOperations(createMetricEvent, metricEventRecorder, MetricsOperation.ON_CONNECTION, 0, 8, null);
            }

            @NotNull
            public final SpanningMetricOperations getSpanningMetricOperation() {
                return this.spanningMetricOperation;
            }

            @Override // com.amazon.alexa.fitness.util.Callback
            public void onError(@Nullable String str, @Nullable Throwable th) {
                this.spanningMetricOperation.completeAllOperationsOnError(str, th);
            }

            @Override // com.amazon.alexa.fitness.util.Callback
            public void onSuccess(@Nullable Unit unit) {
                this.spanningMetricOperation.completeAllOperationsOnSuccess();
            }
        });
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionDisconnected(@NotNull Accessory accessory) {
        ILog iLog;
        MetricsAggregator metricsAggregator;
        MetricsAggregator metricsAggregator2;
        MetricEventRecorder metricEventRecorder;
        MetricEventFactory metricEventFactory;
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        iLog = this.this$0.log;
        ILog.DefaultImpls.info$default(iLog, MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Accessory session disconnected for accessory: " + accessory, null, 4, null);
        metricsAggregator = this.this$0.metricsAggregator;
        metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getDISCONNECT());
        metricsAggregator2 = this.this$0.metricsAggregator;
        metricsAggregator2.startOrResumeTimer(AggregatedMetricsConstants.Companion.getDISCONNECTED_DURATION());
        metricEventRecorder = this.this$0.metricEventRecorder;
        metricEventFactory = this.this$0.metricEventFactory;
        metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR), MetricsConstantsKt.buildMetricName(MetricsCategory.ACCESSORY_SESSION, MetricsName.DISCONNECT), 0L, 2, null));
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionFailed(@NotNull Accessory accessory, @NotNull Throwable e) {
        ILog iLog;
        MetricEventRecorder metricEventRecorder;
        MetricEventFactory metricEventFactory;
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(e, "e");
        iLog = this.this$0.log;
        iLog.info(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Accessory session failed for accessory: " + accessory, e);
        metricEventRecorder = this.this$0.metricEventRecorder;
        metricEventFactory = this.this$0.metricEventFactory;
        metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR), MetricsConstantsKt.buildMetricName(MetricsCategory.ACCESSORY_SESSION, "Failure"), 0L, 2, null));
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionReleased(@NotNull Accessory accessory) {
        ILog iLog;
        MetricEventRecorder metricEventRecorder;
        MetricEventFactory metricEventFactory;
        DisposableManager disposableManager;
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        iLog = this.this$0.log;
        ILog.DefaultImpls.info$default(iLog, MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Accessory session released for accessory: " + accessory, null, 4, null);
        this.this$0.removeAccessoryFromMap(accessory);
        FitnessAccessorySessionMonitorImpl.notifyStateChange$default(this.this$0, accessory, new SensorAvailability.Unavailable(SensorUnavailabilityReason.Disconnected), System.currentTimeMillis(), null, 8, null);
        metricEventRecorder = this.this$0.metricEventRecorder;
        metricEventFactory = this.this$0.metricEventFactory;
        metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR), MetricsConstantsKt.buildMetricName(MetricsCategory.ACCESSORY_SESSION, MetricsName.RELEASED), 0L, 2, null));
        disposableManager = this.this$0.localDisposableManager;
        disposableManager.clear();
    }
}
