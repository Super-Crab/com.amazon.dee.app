package com.amazon.alexa.fitness.message;

import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionTransition;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionEvent;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType;
import com.amazon.alexa.fitness.configuration.SpeechletEventEmitterConfiguration;
import com.amazon.alexa.fitness.configuration.SpeechletEventEmitterConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.message.FitnessSessionEventReceiver;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.model.event.EndedReason;
import com.amazon.alexa.fitness.model.event.Error;
import com.amazon.alexa.fitness.model.event.FitnessSessionEventName;
import com.amazon.alexa.fitness.model.event.FitnessSessionEventPayload;
import com.amazon.alexa.fitness.model.event.Operation;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection;
import com.amazon.alexa.fitness.session.FitnessSessionStateService;
import com.amazon.alexa.fitness.util.Callback;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.alexa.fitness.util.StubCallback;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SpeechletEventEmitterImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ß\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0017\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B?\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J \u00100\u001a\u00020\"2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020&2\u0006\u00104\u001a\u000205H\u0002J$\u00106\u001a\u00020\"2\u0006\u00107\u001a\u00020\u001c2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020:09H\u0016J \u0010;\u001a\u00020\"2\u0006\u00107\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020)2\u0006\u0010<\u001a\u00020=H\u0016J$\u0010>\u001a\u00020\"2\u0006\u00107\u001a\u00020\u001c2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020:09H\u0016J$\u0010?\u001a\u00020\"2\u0006\u00107\u001a\u00020\u001c2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020:09H\u0016J$\u0010@\u001a\u00020\"2\u0006\u00107\u001a\u00020\u001c2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020:09H\u0016J\u0012\u0010A\u001a\u00020\"2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u001c\u0010D\u001a\u00020\"2\b\u00101\u001a\u0004\u0018\u0001022\b\u0010*\u001a\u0004\u0018\u00010EH\u0016J\u0018\u0010F\u001a\u00020\"2\u0006\u0010G\u001a\u0002022\u0006\u0010H\u001a\u00020CH\u0016J\u0010\u0010I\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0018\u0010J\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010K\u001a\u00020/H\u0002J\u0018\u0010L\u001a\u00020$2\u0006\u0010K\u001a\u00020/2\u0006\u0010%\u001a\u00020&H\u0002J&\u0010M\u001a\u00020\"2\u0006\u00107\u001a\u00020\u001c2\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020:09H\u0002J\u0010\u0010N\u001a\u00020\"2\u0006\u0010O\u001a\u00020PH\u0016J\b\u0010Q\u001a\u00020\"H\u0016J\u0018\u0010R\u001a\u00020$2\u0006\u0010K\u001a\u00020/2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010S\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\f\u0010T\u001a\u00020$*\u00020UH\u0002J\u0014\u0010V\u001a\u0004\u0018\u00010W*\b\u0012\u0004\u0012\u00020Y0XH\u0002J\f\u0010Z\u001a\u00020\"*\u00020\u001cH\u0002J\u000e\u0010[\u001a\u0004\u0018\u00010U*\u00020WH\u0002R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\\"}, d2 = {"Lcom/amazon/alexa/fitness/message/SpeechletEventEmitterImpl;", "Lcom/amazon/alexa/fitness/message/SpeechletEventEmitter;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "configurationProvider", "Lcom/amazon/alexa/fitness/configuration/SpeechletEventEmitterConfigurationProvider;", "instrumentedAlexaServicesConnection", "Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;", "fitnessSessionOrchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "fitnessSessionStateService", "Lcom/amazon/alexa/fitness/session/FitnessSessionStateService;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/configuration/SpeechletEventEmitterConfigurationProvider;Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;Lcom/amazon/alexa/fitness/session/FitnessSessionStateService;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/logs/ILog;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/configuration/SpeechletEventEmitterConfiguration;", "getConfiguration", "()Lcom/amazon/alexa/fitness/configuration/SpeechletEventEmitterConfiguration;", "connectionListener", "com/amazon/alexa/fitness/message/SpeechletEventEmitterImpl$connectionListener$1", "Lcom/amazon/alexa/fitness/message/SpeechletEventEmitterImpl$connectionListener$1;", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "createAlexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "fitnessSessionEventName", "Lcom/amazon/alexa/fitness/model/event/FitnessSessionEventName;", "fitnessSessionEventPayload", "Lcom/amazon/alexa/fitness/model/event/FitnessSessionEventPayload;", "ensureAlexaServicesConnection", "", "failedToResume", "", "newState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "failedToStart", "getAfxError", "Lcom/amazon/alexa/fitness/model/event/Error;", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "getEndedReason", "Lcom/amazon/alexa/fitness/model/event/EndedReason;", "fitnessSessionTransition", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;", "handleSessionChanged", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "currentState", "lastStateChangeEvent", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType$StateChangeEvent;", "onFitnessSessionEnded", "alexaEvent", "callback", "Lcom/amazon/alexa/fitness/util/Callback;", "", "onFitnessSessionError", "operation", "Lcom/amazon/alexa/fitness/model/event/Operation;", "onFitnessSessionPaused", "onFitnessSessionResumed", "onFitnessSessionStarted", "onMetricsUpdated", "metrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "onSessionChanged", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "onSessionEnded", "endedSession", "finalMetrics", "paused", "recovered", "transition", Constants.TIMELINE_RESUMED_STR, "sendAlexaEventToSpeechlet", "sensorAvailabilityChanged", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "start", "started", "stopped", "isGUI", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "lastCommand", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEvent;", "send", "source", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SpeechletEventEmitterImpl implements SpeechletEventEmitter, FitnessSessionOrchestratorDelegate {
    private final SpeechletEventEmitterConfigurationProvider configurationProvider;
    private final SpeechletEventEmitterImpl$connectionListener$1 connectionListener;
    private final FitnessSessionOrchestrator fitnessSessionOrchestrator;
    private final FitnessSessionStateService fitnessSessionStateService;
    private final InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection;
    private final ReentrantLock lock;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessSessionTransition.values().length];

        static {
            $EnumSwitchMapping$0[FitnessSessionTransition.RECOVERING_FAILED.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessSessionTransition.RECOVERING_FAILED_STALE_DATA.ordinal()] = 2;
            $EnumSwitchMapping$0[FitnessSessionTransition.RECOVERED_WAS_ACTIVE_FAILED.ordinal()] = 3;
            $EnumSwitchMapping$0[FitnessSessionTransition.STOP_COMMAND_RECEIVED.ordinal()] = 4;
            $EnumSwitchMapping$0[FitnessSessionTransition.AUTOSTOP_NO_DATA_TIMEOUT.ordinal()] = 5;
            $EnumSwitchMapping$0[FitnessSessionTransition.AUTOSTOP_SENSOR_DISCONNECTED.ordinal()] = 6;
        }
    }

    @Inject
    public SpeechletEventEmitterImpl(@NotNull SpeechletEventEmitterConfigurationProvider configurationProvider, @NotNull InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection, @NotNull FitnessSessionOrchestrator fitnessSessionOrchestrator, @NotNull FitnessSessionStateService fitnessSessionStateService, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(configurationProvider, "configurationProvider");
        Intrinsics.checkParameterIsNotNull(instrumentedAlexaServicesConnection, "instrumentedAlexaServicesConnection");
        Intrinsics.checkParameterIsNotNull(fitnessSessionOrchestrator, "fitnessSessionOrchestrator");
        Intrinsics.checkParameterIsNotNull(fitnessSessionStateService, "fitnessSessionStateService");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.configurationProvider = configurationProvider;
        this.instrumentedAlexaServicesConnection = instrumentedAlexaServicesConnection;
        this.fitnessSessionOrchestrator = fitnessSessionOrchestrator;
        this.fitnessSessionStateService = fitnessSessionStateService;
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.log = log;
        this.lock = new ReentrantLock();
        this.connectionListener = new SpeechletEventEmitterImpl$connectionListener$1(this);
        this.fitnessSessionOrchestrator.registerDelegate(new WeakReference<>(this));
    }

    private final AlexaEvent createAlexaEvent(FitnessSessionEventName fitnessSessionEventName, FitnessSessionEventPayload fitnessSessionEventPayload) {
        AlexaHeader.Builder builder = AlexaHeader.builder();
        builder.setNamespace("Alexa.Health.Fitness");
        builder.setName(fitnessSessionEventName.getValue());
        return new AlexaEvent(builder.build(), new AlexaPayload(GsonUtilsKt.toJson(fitnessSessionEventPayload)));
    }

    private final void ensureAlexaServicesConnection() {
        if (this.instrumentedAlexaServicesConnection.isDisconnected()) {
            this.instrumentedAlexaServicesConnection.connect(this.metricEventFactory.createMetricEvent(MetricsClass.EVENT_EMITTER));
        }
    }

    private final boolean failedToResume(FitnessSessionState fitnessSessionState) {
        return fitnessSessionState == FitnessSessionState.FAILED_TO_RESUME;
    }

    private final boolean failedToStart(FitnessSessionState fitnessSessionState) {
        return fitnessSessionState == FitnessSessionState.FAILED_TO_START;
    }

    private final Error getAfxError(SensorError sensorError) {
        if (!(sensorError instanceof SensorError.SetStateFailed) && !(sensorError instanceof SensorError.NullFitnessRepository) && !(sensorError instanceof SensorError.InvalidCommand)) {
            if (!(sensorError instanceof SensorError.Unavailable)) {
                throw new NoWhenBranchMatchedException();
            }
            return Error.UNSUPPORTED;
        }
        return Error.FAILED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SpeechletEventEmitterConfiguration getConfiguration() {
        return this.configurationProvider.provideSpeechletEventEmitterConfiguration();
    }

    private final EndedReason getEndedReason(FitnessSessionTransition fitnessSessionTransition) {
        switch (WhenMappings.$EnumSwitchMapping$0[fitnessSessionTransition.ordinal()]) {
            case 1:
                return EndedReason.DEVICE_COMPLETED;
            case 2:
                return EndedReason.DEVICE_COMPLETED;
            case 3:
                return EndedReason.DEVICE_COMPLETED;
            case 4:
                return EndedReason.USER_REQUESTED;
            case 5:
                return EndedReason.DEVICE_COMPLETED;
            case 6:
                return EndedReason.DEVICE_UNREACHABLE;
            default:
                return EndedReason.UNKNOWN;
        }
    }

    private final void handleSessionChanged(Session session, FitnessSessionState fitnessSessionState, SessionEventType.StateChangeEvent stateChangeEvent) {
        SessionCommandSource source;
        SessionCommandSource source2;
        SessionCommandSource source3;
        UUID sessionId = session.getConfiguration().getSessionId();
        FitnessSessionTransition transition = stateChangeEvent.getTransition();
        SensorError error = stateChangeEvent.getError();
        if (started(transition, fitnessSessionState)) {
            Command lastCommand = lastCommand(session.getEvents());
            if (lastCommand != null && (source3 = source(lastCommand)) != null && isGUI(source3)) {
                return;
            }
            FitnessSessionEventReceiver.DefaultImpls.onFitnessSessionStarted$default(this, createAlexaEvent(FitnessSessionEventName.STARTED, FitnessSessionEventPayload.Companion.fitnessSessionStarted(sessionId)), null, 2, null);
            ILog.DefaultImpls.info$default(this.log, "SpeechletEventEmitter", "Sending fitness session started event", null, 4, null);
        } else if (stopped(fitnessSessionState)) {
            EndedReason endedReason = getEndedReason(transition);
            FitnessSessionEventReceiver.DefaultImpls.onFitnessSessionEnded$default(this, createAlexaEvent(FitnessSessionEventName.ENDED, FitnessSessionEventPayload.Companion.fitnessSessionEnded(sessionId, endedReason)), null, 2, null);
            ILog iLog = this.log;
            ILog.DefaultImpls.info$default(iLog, "SpeechletEventEmitter", "sending fitness session stopped event with " + endedReason, null, 4, null);
        } else if (paused(fitnessSessionState)) {
            Command lastCommand2 = lastCommand(session.getEvents());
            if (lastCommand2 != null && (source2 = source(lastCommand2)) != null && isGUI(source2)) {
                return;
            }
            ILog.DefaultImpls.info$default(this.log, "SpeechletEventEmitter", "sending fitness session paused event", null, 4, null);
            FitnessSessionEventReceiver.DefaultImpls.onFitnessSessionPaused$default(this, createAlexaEvent(FitnessSessionEventName.PAUSED, FitnessSessionEventPayload.Companion.fitnessSessionPaused(sessionId)), null, 2, null);
        } else if (resumed(transition, fitnessSessionState)) {
            Command lastCommand3 = lastCommand(session.getEvents());
            if (lastCommand3 != null && (source = source(lastCommand3)) != null && isGUI(source)) {
                return;
            }
            ILog.DefaultImpls.info$default(this.log, "SpeechletEventEmitter", "sending fitness session resumed event", null, 4, null);
            FitnessSessionEventReceiver.DefaultImpls.onFitnessSessionResumed$default(this, createAlexaEvent(FitnessSessionEventName.RESUMED, FitnessSessionEventPayload.Companion.fitnessSessionResumed(sessionId)), null, 2, null);
        } else if (failedToStart(fitnessSessionState)) {
            if (error != null) {
                onFitnessSessionError(createAlexaEvent(FitnessSessionEventName.ERROR, FitnessSessionEventPayload.Companion.fitnessSessionError(sessionId, getAfxError(error), Operation.START)), getAfxError(error), Operation.START);
                ILog iLog2 = this.log;
                ILog.DefaultImpls.error$default(iLog2, "SpeechletEventEmitter", "sending error " + error + " starting session", null, 4, null);
                return;
            }
            ILog.DefaultImpls.error$default(this.log, "SpeechletEventEmitter", "Got failed to start update, but no error found", null, 4, null);
        } else if (failedToResume(fitnessSessionState)) {
            if (error != null) {
                onFitnessSessionError(createAlexaEvent(FitnessSessionEventName.ERROR, FitnessSessionEventPayload.Companion.fitnessSessionError(sessionId, getAfxError(error), Operation.RESUME)), getAfxError(error), Operation.RESUME);
                ILog iLog3 = this.log;
                ILog.DefaultImpls.error$default(iLog3, "SpeechletEventEmitter", "sending error " + error + " resuming session", null, 4, null);
                return;
            }
            ILog.DefaultImpls.error$default(this.log, "SpeechletEventEmitter", "Got failed to resume update, but no error found", null, 4, null);
        } else if (recovered(fitnessSessionState, transition)) {
            ILog.DefaultImpls.info$default(this.log, "SpeechletEventEmitter", "session recovered successfully", null, 4, null);
            if (!this.instrumentedAlexaServicesConnection.isDisconnected()) {
                return;
            }
            this.instrumentedAlexaServicesConnection.connect(this.metricEventFactory.createMetricEvent(MetricsClass.EVENT_EMITTER));
        } else {
            ILog iLog4 = this.log;
            ILog.DefaultImpls.warn$default(iLog4, "SpeechletEventEmitter", "transitioned to " + fitnessSessionState + " because of " + transition, null, 4, null);
        }
    }

    private final boolean isGUI(@NotNull SessionCommandSource sessionCommandSource) {
        return sessionCommandSource == SessionCommandSource.GUI;
    }

    private final Command lastCommand(@NotNull List<SessionEvent> list) {
        List<SessionEvent> reversed;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((SessionEvent) obj).getEventType() instanceof SessionEventType.StateChangeEvent) {
                arrayList.add(obj);
            }
        }
        reversed = CollectionsKt___CollectionsKt.reversed(arrayList);
        for (SessionEvent sessionEvent : reversed) {
            SessionEventType eventType = sessionEvent.getEventType();
            if (eventType == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType.StateChangeEvent");
            }
            Command command = ((SessionEventType.StateChangeEvent) eventType).getCommand();
            if (command != null) {
                return command;
            }
        }
        return null;
    }

    private final boolean paused(FitnessSessionState fitnessSessionState) {
        return fitnessSessionState == FitnessSessionState.PAUSED;
    }

    private final boolean recovered(FitnessSessionState fitnessSessionState, FitnessSessionTransition fitnessSessionTransition) {
        return (fitnessSessionState == FitnessSessionState.ACTIVE && fitnessSessionTransition == FitnessSessionTransition.RECOVERED_WAS_ACTIVE_SUCCEEDED) || (fitnessSessionState == FitnessSessionState.PAUSED && fitnessSessionTransition == FitnessSessionTransition.RECOVERED_WAS_PAUSED);
    }

    private final boolean resumed(FitnessSessionTransition fitnessSessionTransition, FitnessSessionState fitnessSessionState) {
        return fitnessSessionTransition == FitnessSessionTransition.RESUMED && fitnessSessionState == FitnessSessionState.ACTIVE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void send(@NotNull AlexaEvent alexaEvent) {
        this.instrumentedAlexaServicesConnection.send(alexaEvent, true);
        Unit unit = Unit.INSTANCE;
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sending Alexa Event, header: ");
        outline107.append(alexaEvent.getAlexaHeader());
        outline107.append(", payload: ");
        AlexaPayload alexaPayload = alexaEvent.getAlexaPayload();
        Intrinsics.checkExpressionValueIsNotNull(alexaPayload, "alexaPayload");
        outline107.append(alexaPayload.getPayload());
        ILog.DefaultImpls.debug$default(iLog, "SpeechletEventEmitter", outline107.toString(), null, 4, null);
    }

    private final void sendAlexaEventToSpeechlet(AlexaEvent alexaEvent, Callback<Unit, Throwable> callback) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.instrumentedAlexaServicesConnection.isConnected()) {
                send(alexaEvent);
                Callback.DefaultImpls.onSuccess$default(callback, null, 1, null);
                Unit unit = Unit.INSTANCE;
            } else {
                Boolean.valueOf(this.connectionListener.getAlexaEventQueue().add(new AlexaEventMessageQueueItem(alexaEvent, callback)));
            }
            reentrantLock.unlock();
            ensureAlexaServicesConnection();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void sendAlexaEventToSpeechlet$default(SpeechletEventEmitterImpl speechletEventEmitterImpl, AlexaEvent alexaEvent, Callback callback, int i, Object obj) {
        if ((i & 2) != 0) {
            callback = new StubCallback();
        }
        speechletEventEmitterImpl.sendAlexaEventToSpeechlet(alexaEvent, callback);
    }

    private final SessionCommandSource source(@NotNull Command command) {
        if (command instanceof Command.Start) {
            return ((Command.Start) command).getSource();
        }
        if (command instanceof Command.Pause) {
            return ((Command.Pause) command).getSource();
        }
        if (command instanceof Command.Resume) {
            return ((Command.Resume) command).getSource();
        }
        if (command instanceof Command.Stop) {
            return ((Command.Stop) command).getSource();
        }
        if (!(command instanceof Command.AttemptRecovery)) {
            throw new NoWhenBranchMatchedException();
        }
        return null;
    }

    private final boolean started(FitnessSessionTransition fitnessSessionTransition, FitnessSessionState fitnessSessionState) {
        return fitnessSessionTransition == FitnessSessionTransition.STARTED && fitnessSessionState == FitnessSessionState.ACTIVE;
    }

    private final boolean stopped(FitnessSessionState fitnessSessionState) {
        return fitnessSessionState == FitnessSessionState.STOPPING;
    }

    @Override // com.amazon.alexa.fitness.message.FitnessSessionEventReceiver
    public void onFitnessSessionEnded(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendAlexaEventToSpeechlet(alexaEvent, callback);
    }

    @Override // com.amazon.alexa.fitness.message.FitnessSessionEventReceiver
    public void onFitnessSessionError(@NotNull AlexaEvent alexaEvent, @NotNull Error error, @NotNull Operation operation) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        sendAlexaEventToSpeechlet$default(this, alexaEvent, null, 2, null);
    }

    @Override // com.amazon.alexa.fitness.message.FitnessSessionEventReceiver
    public void onFitnessSessionPaused(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendAlexaEventToSpeechlet(alexaEvent, callback);
    }

    @Override // com.amazon.alexa.fitness.message.FitnessSessionEventReceiver
    public void onFitnessSessionResumed(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendAlexaEventToSpeechlet(alexaEvent, callback);
    }

    @Override // com.amazon.alexa.fitness.message.FitnessSessionEventReceiver
    public void onFitnessSessionStarted(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sendAlexaEventToSpeechlet(alexaEvent, callback);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics) {
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionChanged(@Nullable Session session, @Nullable FitnessSessionOrchestrator.CommandProcessingError commandProcessingError) {
        SessionEvent sessionEvent;
        SessionConfiguration configuration;
        if (commandProcessingError == FitnessSessionOrchestrator.CommandProcessingError.NoFitnessProfile) {
            FitnessSessionEventName fitnessSessionEventName = FitnessSessionEventName.ERROR;
            FitnessSessionEventPayload.Companion companion = FitnessSessionEventPayload.Companion;
            UUID randomUUID = UUID.randomUUID();
            Intrinsics.checkExpressionValueIsNotNull(randomUUID, "UUID.randomUUID()");
            onFitnessSessionError(createAlexaEvent(fitnessSessionEventName, companion.fitnessSessionError(randomUUID, Error.BIOMETRICS_MISSING, Operation.START)), Error.BIOMETRICS_MISSING, Operation.START);
            return;
        }
        SessionEventType sessionEventType = null;
        FitnessSessionState currentState = session != null ? SessionDataModelsKt.currentState(session) : null;
        if (((session == null || (configuration = session.getConfiguration()) == null) ? null : configuration.getSessionId()) == null) {
            ILog.DefaultImpls.error$default(this.log, "SpeechletEventEmitter", "State change received to " + currentState + " when Session ID not set", null, 4, null);
        } else if (currentState == null) {
            ILog.DefaultImpls.error$default(this.log, "SpeechletEventEmitter", "current state not defined", null, 4, null);
        } else {
            List<SessionEvent> events = session.getEvents();
            ListIterator<SessionEvent> listIterator = events.listIterator(events.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    sessionEvent = null;
                    break;
                }
                sessionEvent = listIterator.previous();
                if (sessionEvent.getEventType() instanceof SessionEventType.StateChangeEvent) {
                    break;
                }
            }
            SessionEvent sessionEvent2 = sessionEvent;
            if (sessionEvent2 != null) {
                sessionEventType = sessionEvent2.getEventType();
            }
            if (sessionEventType != null) {
                SessionEventType.StateChangeEvent stateChangeEvent = (SessionEventType.StateChangeEvent) sessionEventType;
                if (stateChangeEvent.getTransition() == null) {
                    ILog.DefaultImpls.warn$default(this.log, "SpeechletEventEmitter", "No state change event that can be used to determine state change", null, 4, null);
                    return;
                }
                ensureAlexaServicesConnection();
                handleSessionChanged(session, currentState, stateChangeEvent);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType.StateChangeEvent");
        }
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionEnded(@NotNull Session endedSession, @NotNull FitnessMetrics finalMetrics) {
        Intrinsics.checkParameterIsNotNull(endedSession, "endedSession");
        Intrinsics.checkParameterIsNotNull(finalMetrics, "finalMetrics");
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void sensorAvailabilityChanged(@NotNull SensorAvailability availability) {
        Intrinsics.checkParameterIsNotNull(availability, "availability");
    }

    @Override // com.amazon.alexa.fitness.service.Startable
    public void start() {
        ILog.DefaultImpls.debug$default(this.log, "SpeechletEventEmitter", "Registering connection listener...", null, 4, null);
        this.instrumentedAlexaServicesConnection.registerConnectionListener(this.connectionListener);
    }
}
