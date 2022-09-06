package com.amazon.alexa.fitness.session;

import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import io.reactivex.rxjava3.observers.DisposableObserver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAccessorySessionUpdateObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BB\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012#\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\b\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\b\u0010\u0015\u001a\u00020\rH\u0016J\u0012\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/session/FitnessAccessorySessionUpdateObserver;", "Lio/reactivex/rxjava3/observers/DisposableObserver;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSessionUpdate;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "sensorCommandReceiver", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "Lkotlin/ParameterName;", "name", "sensorCommand", "", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lkotlin/jvm/functions/Function1;Lcom/amazon/alexa/fitness/logs/ILog;)V", "handleAutoStop", "fitnessSessionUpdate", "handlePause", "handleResume", "onComplete", "onError", "throwable", "", "onNext", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessAccessorySessionUpdateObserver extends DisposableObserver<FitnessSessionUpdate> {
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final Function1<Command, Unit> sensorCommandReceiver;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessSession.WorkoutState.values().length];

        static {
            $EnumSwitchMapping$0[FitnessSession.WorkoutState.IDLE.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessSession.WorkoutState.PAUSED.ordinal()] = 2;
            $EnumSwitchMapping$0[FitnessSession.WorkoutState.ACTIVE.ordinal()] = 3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FitnessAccessorySessionUpdateObserver(@NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @Nullable Function1<? super Command, Unit> function1, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.sensorCommandReceiver = function1;
        this.log = log;
    }

    private final void handleAutoStop(FitnessSessionUpdate fitnessSessionUpdate) {
        fitnessSessionUpdate.markSuccessful();
        Function1<Command, Unit> function1 = this.sensorCommandReceiver;
        if (function1 == null || function1.mo12165invoke(new Command.Stop(SessionCommandSource.SENSOR)) == null) {
            ILog.DefaultImpls.error$default(this.log, "FitnessAccessorySessionUpdateObserver", "no sensor command receiver set", null, 4, null);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void handlePause(FitnessSessionUpdate fitnessSessionUpdate) {
        fitnessSessionUpdate.markSuccessful();
        Function1<Command, Unit> function1 = this.sensorCommandReceiver;
        if (function1 == null || function1.mo12165invoke(new Command.Pause(SessionCommandSource.SENSOR)) == null) {
            ILog.DefaultImpls.error$default(this.log, "FitnessAccessorySessionUpdateObserver", "no sensor command receiver set", null, 4, null);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void handleResume(FitnessSessionUpdate fitnessSessionUpdate) {
        fitnessSessionUpdate.markSuccessful();
        Function1<Command, Unit> function1 = this.sensorCommandReceiver;
        if (function1 == null || function1.mo12165invoke(new Command.Resume(SessionCommandSource.SENSOR)) == null) {
            ILog.DefaultImpls.error$default(this.log, "FitnessAccessorySessionUpdateObserver", "no sensor command receiver set", null, 4, null);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        ILog.DefaultImpls.debug$default(this.log, "FitnessAccessorySessionUpdateObserver", "onComplete() invoked...", null, 4, null);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(@Nullable Throwable th) {
        this.log.error("FitnessAccessorySessionUpdateObserver", "Error with FitnessSessionUpdate.", th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(@NotNull FitnessSessionUpdate fitnessSessionUpdate) {
        Intrinsics.checkParameterIsNotNull(fitnessSessionUpdate, "fitnessSessionUpdate");
        ILog.DefaultImpls.debug$default(this.log, "FitnessAccessorySessionUpdateObserver", "Received fitness session update...", null, 4, null);
        MetricEvent createMetricEvent = this.metricEventFactory.createMetricEvent(MetricsClass.FITNESS_SESSION_UPDATE_OBSERVER);
        if (FitnessSessionUpdate.Origin.ACCESSORY == fitnessSessionUpdate.getOrigin()) {
            FitnessSession fitnessSession = fitnessSessionUpdate.getFitnessSession();
            Intrinsics.checkExpressionValueIsNotNull(fitnessSession, "fitnessSessionUpdate.fitnessSession");
            FitnessSession.WorkoutState workoutState = fitnessSession.getWorkoutState();
            ILog iLog = this.log;
            ILog.DefaultImpls.info$default(iLog, "FitnessAccessorySessionUpdateObserver", "Processing fitness session update from accessory with workoutState " + workoutState, null, 4, null);
            if (workoutState != null) {
                try {
                    int i = WhenMappings.$EnumSwitchMapping$0[workoutState.ordinal()];
                    if (i == 1) {
                        handleAutoStop(fitnessSessionUpdate);
                    } else if (i == 2) {
                        handlePause(fitnessSessionUpdate);
                    } else if (i == 3) {
                        handleResume(fitnessSessionUpdate);
                    }
                    return;
                } catch (Exception e) {
                    this.log.error("FitnessAccessorySessionUpdateObserver", "error handling session update", e);
                    return;
                }
            }
            MetricEvent.DefaultImpls.incrementCounter$default(createMetricEvent, MetricsConstantsKt.buildMetricErrorName(MetricsOperation.NOTIFY_DATA_AVAILABLE, MetricsCategory.WORKOUT_STATE, MetricsName.INVALID), 0L, 2, null);
            this.metricEventRecorder.record(createMetricEvent);
            fitnessSessionUpdate.markFailed(new IllegalArgumentException("Cannot update fitness session for workout state: " + workoutState));
            return;
        }
        MetricEvent.DefaultImpls.incrementCounter$default(createMetricEvent, MetricsConstantsKt.buildMetricErrorName(MetricsOperation.NOTIFY_DATA_AVAILABLE, MetricsCategory.EVENT_ORIGIN, MetricsName.INVALID), 0L, 2, null);
        this.metricEventRecorder.record(createMetricEvent);
    }
}
