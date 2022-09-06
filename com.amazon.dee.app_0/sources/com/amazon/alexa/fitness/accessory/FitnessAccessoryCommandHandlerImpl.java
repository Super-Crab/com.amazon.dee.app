package com.amazon.alexa.fitness.accessory;

import amazon.speech.model.DirectiveIntent;
import androidx.annotation.MainThread;
import com.amazon.alexa.accessory.repositories.fitness.FitnessRepository;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.repository.FitnessSessionRepository;
import com.amazon.alexa.fitness.sdk.AccessoryFitnessSession;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessAccessoryCommandHandlerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ=\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u0016H\u0016J9\u0010\u001b\u001a\u00020\u00102#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0017H\u0002J=\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u0016H\u0016JE\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001f2#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u0016H\u0003JE\u0010 \u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001f2#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u0016H\u0002J \u0010!\u001a\u00020\"2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$H\u0002J=\u0010%\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u0016H\u0016J=\u0010&\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u0016H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/alexa/fitness/accessory/FitnessAccessoryCommandHandlerImpl;", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessoryCommandHandler;", "fitnessSessionRepository", "Lcom/amazon/alexa/fitness/repository/FitnessSessionRepository;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "afxMessageProcessor", "Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/repository/FitnessSessionRepository;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;Lcom/amazon/alexa/fitness/logs/ILog;)V", DirectiveIntent.INTENT_KEY_SEQUENCE_ID, "Ljava/util/concurrent/atomic/AtomicInteger;", "pause", "", "accessorySession", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "callback", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "Lkotlin/ParameterName;", "name", "error", "postResultCallbackToAfx", "resume", "setAccessoryStateAndPostResult", "workoutState", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSession$WorkoutState;", "setAccessoryStateAndPostResultImmediately", "setWorkoutState", "Lio/reactivex/rxjava3/core/Completable;", "fitnessRepository", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessRepository;", "start", "stop", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAccessoryCommandHandlerImpl implements FitnessAccessoryCommandHandler {
    private final AfxMessageProcessor afxMessageProcessor;
    private final FitnessSessionRepository fitnessSessionRepository;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final AtomicInteger sequenceId;

    @Inject
    public FitnessAccessoryCommandHandlerImpl(@NotNull FitnessSessionRepository fitnessSessionRepository, @NotNull MetricEventRecorder metricEventRecorder, @NotNull MetricEventFactory metricEventFactory, @NotNull AfxMessageProcessor afxMessageProcessor, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(fitnessSessionRepository, "fitnessSessionRepository");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(afxMessageProcessor, "afxMessageProcessor");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.fitnessSessionRepository = fitnessSessionRepository;
        this.metricEventRecorder = metricEventRecorder;
        this.metricEventFactory = metricEventFactory;
        this.afxMessageProcessor = afxMessageProcessor;
        this.log = log;
        AccessoryFitnessSession accessoryFitnessSession = this.fitnessSessionRepository.getAccessoryFitnessSession();
        this.sequenceId = new AtomicInteger(accessoryFitnessSession != null ? accessoryFitnessSession.getSequenceId() : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postResultCallbackToAfx(final Function1<? super SensorError, Unit> function1, final SensorError sensorError) {
        this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandlerImpl$postResultCallbackToAfx$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1.this.mo12165invoke(sensorError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void postResultCallbackToAfx$default(FitnessAccessoryCommandHandlerImpl fitnessAccessoryCommandHandlerImpl, Function1 function1, SensorError sensorError, int i, Object obj) {
        if ((i & 2) != 0) {
            sensorError = null;
        }
        fitnessAccessoryCommandHandlerImpl.postResultCallbackToAfx(function1, sensorError);
    }

    @MainThread
    private final void setAccessoryStateAndPostResult(final UUID uuid, AccessorySession accessorySession, final FitnessSession.WorkoutState workoutState, final Function1<? super SensorError, Unit> function1) {
        setWorkoutState(uuid, workoutState, accessorySession.getFitnessRepository()).subscribe(new Action() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandlerImpl$setAccessoryStateAndPostResult$$inlined$let$lambda$1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                ILog iLog;
                iLog = FitnessAccessoryCommandHandlerImpl.this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("workout state set to ");
                outline107.append(workoutState);
                outline107.append(" successfully");
                ILog.DefaultImpls.info$default(iLog, "AccessoryCommandHandler", outline107.toString(), null, 4, null);
                FitnessAccessoryCommandHandlerImpl.postResultCallbackToAfx$default(FitnessAccessoryCommandHandlerImpl.this, function1, null, 2, null);
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandlerImpl$setAccessoryStateAndPostResult$$inlined$let$lambda$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                ILog iLog;
                iLog = FitnessAccessoryCommandHandlerImpl.this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error setting state to ");
                outline107.append(workoutState);
                iLog.error("AccessoryCommandHandler", outline107.toString(), throwable);
                FitnessAccessoryCommandHandlerImpl fitnessAccessoryCommandHandlerImpl = FitnessAccessoryCommandHandlerImpl.this;
                Function1 function12 = function1;
                Intrinsics.checkExpressionValueIsNotNull(throwable, "throwable");
                fitnessAccessoryCommandHandlerImpl.postResultCallbackToAfx(function12, new SensorError.SetStateFailed(throwable));
            }
        });
    }

    private final void setAccessoryStateAndPostResultImmediately(UUID uuid, AccessorySession accessorySession, FitnessSession.WorkoutState workoutState, Function1<? super SensorError, Unit> function1) {
        setWorkoutState(uuid, workoutState, accessorySession.getFitnessRepository());
        postResultCallbackToAfx$default(this, function1, null, 2, null);
    }

    private final Completable setWorkoutState(UUID uuid, FitnessSession.WorkoutState workoutState, FitnessRepository fitnessRepository) {
        FitnessSession fitnessSession = new FitnessSession(uuid, this.sequenceId.incrementAndGet(), workoutState);
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setting ");
        outline107.append(fitnessSession.getWorkoutState());
        outline107.append(" on ");
        outline107.append(fitnessRepository);
        ILog.DefaultImpls.info$default(iLog, "AccessoryCommandHandler", outline107.toString(), null, 4, null);
        FitnessSessionRepository fitnessSessionRepository = this.fitnessSessionRepository;
        UUID uuid2 = fitnessSession.getUuid();
        Intrinsics.checkExpressionValueIsNotNull(uuid2, "it.uuid");
        int sequenceId = fitnessSession.getSequenceId();
        FitnessSession.WorkoutState workoutState2 = fitnessSession.getWorkoutState();
        Intrinsics.checkExpressionValueIsNotNull(workoutState2, "it.workoutState");
        fitnessSessionRepository.saveAccessoryFitnessSession(new AccessoryFitnessSession(uuid2, sequenceId, workoutState2));
        Completable fitnessSession2 = fitnessRepository.setFitnessSession(fitnessSession);
        Intrinsics.checkExpressionValueIsNotNull(fitnessSession2, "fitnessRepository.setFitnessSession(it)");
        Intrinsics.checkExpressionValueIsNotNull(fitnessSession2, "FitnessSession(sessionId…nessSession(it)\n        }");
        return fitnessSession2;
    }

    @Override // com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandler
    public void pause(@NotNull AccessorySession accessorySession, @NotNull UUID sessionId, @NotNull Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(accessorySession, "accessorySession");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        setAccessoryStateAndPostResultImmediately(sessionId, accessorySession, FitnessSession.WorkoutState.PAUSED, callback);
    }

    @Override // com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandler
    public void resume(@NotNull AccessorySession accessorySession, @NotNull UUID sessionId, @NotNull Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(accessorySession, "accessorySession");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        setAccessoryStateAndPostResultImmediately(sessionId, accessorySession, FitnessSession.WorkoutState.ACTIVE, callback);
    }

    @Override // com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandler
    public void start(@NotNull AccessorySession accessorySession, @NotNull UUID sessionId, @NotNull Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(accessorySession, "accessorySession");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        setAccessoryStateAndPostResult(sessionId, accessorySession, FitnessSession.WorkoutState.ACTIVE, callback);
    }

    @Override // com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandler
    public void stop(@NotNull AccessorySession accessorySession, @NotNull UUID sessionId, @NotNull Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(accessorySession, "accessorySession");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        setAccessoryStateAndPostResult(sessionId, accessorySession, FitnessSession.WorkoutState.IDLE, callback);
    }
}
