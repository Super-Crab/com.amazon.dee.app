package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.accessory.repositories.fitness.FitnessDataAvailableNotification;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
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
import com.amazon.alexa.fitness.sdk.EchoBudSampleData;
import com.amazon.alexa.fitness.session.FitnessDataHandler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAccessoryDataAvailableObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BI\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\b\u0010\u0017\u001a\u00020\u0006H\u0016J\u0012\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/fitness/accessory/FitnessAccessoryDataAvailableObserver;", "Lio/reactivex/rxjava3/observers/DisposableObserver;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessDataAvailableNotification;", "publishSampleCallback", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/sdk/EchoBudSampleData;", "", "accessorySession", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", "fitnessDataHandler", "Lcom/amazon/alexa/fitness/session/FitnessDataHandler;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lkotlin/jvm/functions/Function1;Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;Lcom/amazon/alexa/fitness/session/FitnessDataHandler;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/logs/ILog;)V", "lastDataAvailableTimestamp", "", "Ljava/lang/Long;", "onComplete", "onError", "e", "", "onNext", "t", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAccessoryDataAvailableObserver extends DisposableObserver<FitnessDataAvailableNotification> {
    private final AccessorySession accessorySession;
    private final FitnessDataHandler fitnessDataHandler;
    private Long lastDataAvailableTimestamp;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final MetricsAggregator metricsAggregator;
    private final Function1<EchoBudSampleData, Unit> publishSampleCallback;

    /* JADX WARN: Multi-variable type inference failed */
    public FitnessAccessoryDataAvailableObserver(@NotNull Function1<? super EchoBudSampleData, Unit> publishSampleCallback, @NotNull AccessorySession accessorySession, @NotNull FitnessDataHandler fitnessDataHandler, @NotNull MetricEventRecorder metricEventRecorder, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricsAggregator metricsAggregator, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(publishSampleCallback, "publishSampleCallback");
        Intrinsics.checkParameterIsNotNull(accessorySession, "accessorySession");
        Intrinsics.checkParameterIsNotNull(fitnessDataHandler, "fitnessDataHandler");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.publishSampleCallback = publishSampleCallback;
        this.accessorySession = accessorySession;
        this.fitnessDataHandler = fitnessDataHandler;
        this.metricEventRecorder = metricEventRecorder;
        this.metricEventFactory = metricEventFactory;
        this.metricsAggregator = metricsAggregator;
        this.log = log;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        ILog.DefaultImpls.info$default(this.log, "FitnessAccessoryDataAvailableObserver", "Subscription ended.", null, 4, null);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(@Nullable Throwable th) {
        this.log.error("FitnessAccessoryDataAvailableObserver", "error in receiving data notification from accessory.", th);
        this.metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(this.metricEventFactory.createMetricEvent(MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER), MetricsConstantsKt.buildMetricName(MetricsCategory.ACCESSORY_FITNESS_DATA, MetricsName.FAILED_TO_RECEIVE_DATA), 0L, 2, null));
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(@Nullable FitnessDataAvailableNotification fitnessDataAvailableNotification) {
        final long currentTimeMillis = System.currentTimeMillis();
        ILog iLog = this.log;
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("received FitnessDataAvailable notification", " (delta = ");
        Long l = this.lastDataAvailableTimestamp;
        outline113.append(currentTimeMillis - (l != null ? l.longValue() : currentTimeMillis));
        outline113.append("ms)");
        ILog.DefaultImpls.info$default(iLog, "FitnessAccessoryDataAvailableObserver", outline113.toString(), null, 4, null);
        this.lastDataAvailableTimestamp = Long.valueOf(currentTimeMillis);
        this.accessorySession.getFitnessRepository().getFitnessData().subscribe(new Consumer<FitnessDataSource>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessoryDataAvailableObserver$onNext$$inlined$let$lambda$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(final FitnessDataSource fitnessDataSource) {
                final Scheduler.Worker createWorker = Schedulers.io().createWorker();
                createWorker.schedule(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessoryDataAvailableObserver$onNext$$inlined$let$lambda$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ILog iLog2;
                        MetricsAggregator metricsAggregator;
                        FitnessDataHandler fitnessDataHandler;
                        ILog iLog3;
                        MetricEventRecorder metricEventRecorder;
                        MetricEventFactory metricEventFactory;
                        Function1 function1;
                        ILog iLog4;
                        MetricEventRecorder metricEventRecorder2;
                        MetricEventFactory metricEventFactory2;
                        try {
                            long currentTimeMillis2 = System.currentTimeMillis();
                            iLog2 = FitnessAccessoryDataAvailableObserver.this.log;
                            ILog.DefaultImpls.info$default(iLog2, "FitnessAccessoryDataAvailableObserver", "received FitnessData (notify/received delta = " + (currentTimeMillis2 - currentTimeMillis) + ')', null, 4, null);
                            metricsAggregator = FitnessAccessoryDataAvailableObserver.this.metricsAggregator;
                            metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getMESSAGE_COUNT());
                            fitnessDataHandler = FitnessAccessoryDataAvailableObserver.this.fitnessDataHandler;
                            FitnessDataSource it2 = fitnessDataSource;
                            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                            EchoBudSampleData process = fitnessDataHandler.process(currentTimeMillis2, it2);
                            if (process != null) {
                                function1 = FitnessAccessoryDataAvailableObserver.this.publishSampleCallback;
                                function1.mo12165invoke(process);
                                iLog4 = FitnessAccessoryDataAvailableObserver.this.log;
                                ILog.DefaultImpls.info$default(iLog4, "FitnessAccessoryDataAvailableObserver", "publishing sample for " + process.getActivity() + " with #steps = " + process.getSteps(), null, 4, null);
                                metricEventRecorder2 = FitnessAccessoryDataAvailableObserver.this.metricEventRecorder;
                                metricEventFactory2 = FitnessAccessoryDataAvailableObserver.this.metricEventFactory;
                                metricEventRecorder2.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory2.createMetricEvent(MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER), MetricsConstantsKt.buildMetricName(MetricsCategory.ACCESSORY_FITNESS_DATA, "Success"), 0L, 2, null));
                            } else {
                                iLog3 = FitnessAccessoryDataAvailableObserver.this.log;
                                ILog.DefaultImpls.error$default(iLog3, "FitnessAccessoryDataAvailableObserver", "error processing fitness data", null, 4, null);
                                metricEventRecorder = FitnessAccessoryDataAvailableObserver.this.metricEventRecorder;
                                metricEventFactory = FitnessAccessoryDataAvailableObserver.this.metricEventFactory;
                                metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER), MetricsConstantsKt.buildMetricName(MetricsCategory.ACCESSORY_FITNESS_DATA, MetricsName.FAILED_TO_PROCESS_DATA), 0L, 2, null));
                            }
                        } finally {
                            Scheduler.Worker.this.dispose();
                        }
                    }
                });
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessoryDataAvailableObserver$onNext$$inlined$let$lambda$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable th) {
                ILog iLog2;
                MetricEventRecorder metricEventRecorder;
                MetricEventFactory metricEventFactory;
                iLog2 = FitnessAccessoryDataAvailableObserver.this.log;
                iLog2.error("FitnessAccessoryDataAvailableObserver", "Error subscribing to fitness data source", th);
                metricEventRecorder = FitnessAccessoryDataAvailableObserver.this.metricEventRecorder;
                metricEventFactory = FitnessAccessoryDataAvailableObserver.this.metricEventFactory;
                metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER), MetricsConstantsKt.buildMetricName(MetricsCategory.ACCESSORY_FITNESS_DATA, MetricsName.FAILED_TO_SUBSCRIBE), 0L, 2, null));
            }
        });
    }
}
