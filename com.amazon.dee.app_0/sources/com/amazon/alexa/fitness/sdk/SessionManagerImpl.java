package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager;
import com.amazon.alexa.fitness.api.LocationCoordinate;
import com.amazon.alexa.fitness.api.UserPreferenceKey;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionTransition;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorProviderType;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionEvent;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.AggregatedMetricsConstants;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.SessionMetrics;
import com.amazon.alexa.fitness.model.device.DeviceTypeKt;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SessionRecoveryResult;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.sdk.stateMachine.FitnessPlanKt;
import com.amazon.alexa.fitness.sdk.stateMachine.StateMachine;
import com.amazon.alexa.fitness.sdk.stateMachine.StateMachinePlan;
import com.amazon.alexa.fitness.util.ExhaustiveKt;
import com.amazon.alexa.fitness.utils.MetricComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionManager.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 t2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001tB_\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018¢\u0006\u0002\u0010\u0019J(\u0010L\u001a\u0002002\u0006\u0010M\u001a\u00020J2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010/2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010PH\u0002J\n\u0010Q\u001a\u0004\u0018\u00010JH\u0002J\b\u0010R\u001a\u000200H\u0002J\b\u0010S\u001a\u000200H\u0002J\u0018\u0010T\u001a\u0002002\u0006\u0010U\u001a\u00020<2\u0006\u0010V\u001a\u00020WH\u0002J\b\u0010X\u001a\u000200H\u0002J\u0010\u0010Y\u001a\u0002002\u0006\u0010U\u001a\u00020<H\u0002J\b\u0010Z\u001a\u000200H\u0002J\u0018\u0010[\u001a\u0002002\u0006\u0010U\u001a\u00020<2\u0006\u0010V\u001a\u00020WH\u0002J\b\u0010\\\u001a\u00020JH\u0002J\u0018\u0010]\u001a\u0002002\u0006\u0010M\u001a\u00020J2\u0006\u0010V\u001a\u00020WH\u0002J\n\u0010^\u001a\u0004\u0018\u00010_H\u0002J\u0018\u0010`\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010;2\u0006\u0010M\u001a\u00020JH\u0002J\u0018\u0010a\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010;2\u0006\u0010M\u001a\u00020JH\u0002J4\u0010b\u001a\u0002002\u0006\u0010c\u001a\u00020E2\u0006\u0010M\u001a\u00020J2\u0006\u0010d\u001a\u00020E2\b\u0010O\u001a\u0004\u0018\u00010P2\b\u0010N\u001a\u0004\u0018\u00010/H\u0002J\u0010\u0010e\u001a\u0002002\u0006\u0010f\u001a\u00020gH\u0002J\u0016\u0010h\u001a\u0002002\f\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;H\u0016J*\u0010i\u001a\u0002002\u0006\u0010j\u001a\u0002032\u0006\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u0002042\b\u0010O\u001a\u0004\u0018\u00010nH\u0016J\u0010\u0010o\u001a\u0002002\u0006\u0010p\u001a\u00020qH\u0016J\u0010\u0010r\u001a\u0002002\u0006\u0010N\u001a\u00020/H\u0016J\u0012\u0010s\u001a\u0002002\b\u0010O\u001a\u0004\u0018\u00010PH\u0002R\u001c\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0016\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002000\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R:\u00101\u001a\"\u0012\u0004\u0012\u000203\u0012\u0006\u0012\u0004\u0018\u00010402j\u0010\u0012\u0004\u0012\u000203\u0012\u0006\u0012\u0004\u0018\u000104`5X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0016\u0010:\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010=\u001a\u0004\u0018\u00010\u001dX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u000e\u0010B\u001a\u00020CX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010D\u001a\u00020E8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR,\u0010H\u001a \u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020J\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020J0K0IX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006u"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionManagerImpl;", "Lcom/amazon/alexa/fitness/sdk/SessionManager;", "Lcom/amazon/alexa/fitness/sdk/SampleObserver;", "Lcom/amazon/alexa/fitness/sdk/SensorStateObserver;", "afxMessageProcessor", "Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "metricsAggregatorRecovery", "Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;", "fitnessAlgorithmsManager", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmsManager;", "recoveryManager", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryManager;", "delegate", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/fitness/sdk/SessionManagerDelegate;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "timeoutHandler", "Lcom/amazon/alexa/fitness/sdk/TimeoutHandler;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "userPreferenceStore", "Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "(Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmsManager;Lcom/amazon/alexa/fitness/sdk/SessionRecoveryManager;Ljava/lang/ref/WeakReference;Lcom/amazon/alexa/fitness/logs/ILog;Lcom/amazon/alexa/fitness/sdk/TimeoutHandler;Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;Lcom/amazon/alexa/fitness/api/UserPreferenceStore;)V", "decodeSession", "Lkotlin/Function1;", "", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "getDelegate", "()Ljava/lang/ref/WeakReference;", "setDelegate", "(Ljava/lang/ref/WeakReference;)V", "encodeSession", "Lkotlin/Function0;", "getFitnessAlgorithmsManager", "()Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmsManager;", "processingCommand", "", "repeatingTimer", "Lcom/amazon/alexa/fitness/sdk/RepeatingTimer;", "getSampleStore", "()Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "setSampleStore", "(Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;)V", "sensorCommandReceiver", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "", "sensorIdToLastKnownUnavailableTime", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getSensorIdToLastKnownUnavailableTime", "()Ljava/util/HashMap;", "setSensorIdToLastKnownUnavailableTime", "(Ljava/util/HashMap;)V", "sensorProviders", "", "Lcom/amazon/alexa/fitness/sdk/SensorProvider;", "session", "getSession", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "setSession", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;)V", "sessionMetrics", "Lcom/amazon/alexa/fitness/metrics/SessionMetrics;", "state", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "getState", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "stateMachine", "Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachine;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;", "Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;", "attempt", "transition", "command", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "attemptRecovery", "attemptToPause", "attemptToRecover", "attemptToRecoverOptionalSensor", "sensorProvider", "config", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "attemptToResume", "attemptToResumeOptionalSensor", "attemptToStart", "attemptToStartOptionalSensor", "attemptToStop", "attemptTransition", "getAccessorySensorProviderInUse", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider;", "getOptionalSensors", "getRequiredSensors", "handle", "fromState", "endState", "handleTimeout", "timeout", "Lcom/amazon/alexa/fitness/sdk/Timeout;", "initSensorProviders", "onAvailabilityChanged", "sensorId", "isAvailable", "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "timestamp", "", "onNextSample", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "receive", "sensorCallback", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionManagerImpl implements SessionManager, SampleObserver, SensorStateObserver {
    public static final Companion Companion = new Companion(null);
    private static SessionManager thisRef;
    private final Function1<byte[], Session> decodeSession;
    @Nullable
    private WeakReference<SessionManagerDelegate> delegate;
    private final Function0<byte[]> encodeSession;
    @NotNull
    private final FitnessAlgorithmsManager fitnessAlgorithmsManager;
    private final ILog log;
    private final MetricsAggregator metricsAggregator;
    private final MetricsAggregatorRecovery metricsAggregatorRecovery;
    private boolean processingCommand;
    private final SessionRecoveryManager recoveryManager;
    private final RepeatingTimer repeatingTimer;
    @NotNull
    private SampleStore sampleStore;
    private final Function1<Command, Unit> sensorCommandReceiver;
    @NotNull
    private HashMap<String, Long> sensorIdToLastKnownUnavailableTime;
    private List<? extends SensorProvider> sensorProviders;
    @Nullable
    private Session session;
    private final SessionMetrics sessionMetrics;
    private final StateMachine<FitnessSessionState, FitnessSessionTransition, StateMachinePlan<FitnessSessionState, FitnessSessionTransition>> stateMachine;
    private final TimeoutHandler timeoutHandler;
    private final UserPreferenceStore userPreferenceStore;

    /* compiled from: SessionManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionManagerImpl$Companion;", "", "()V", "thisRef", "Lcom/amazon/alexa/fitness/sdk/SessionManager;", "getInstance", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final SessionManager getInstance() {
            return SessionManagerImpl.thisRef;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessSessionState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;

        static {
            $EnumSwitchMapping$0[FitnessSessionState.RECOVERING.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessSessionState.IDLE.ordinal()] = 2;
            $EnumSwitchMapping$0[FitnessSessionState.STARTING.ordinal()] = 3;
            $EnumSwitchMapping$0[FitnessSessionState.FAILED_TO_START.ordinal()] = 4;
            $EnumSwitchMapping$0[FitnessSessionState.ACTIVE.ordinal()] = 5;
            $EnumSwitchMapping$0[FitnessSessionState.PAUSED.ordinal()] = 6;
            $EnumSwitchMapping$0[FitnessSessionState.RESUMING.ordinal()] = 7;
            $EnumSwitchMapping$0[FitnessSessionState.FAILED_TO_RESUME.ordinal()] = 8;
            $EnumSwitchMapping$0[FitnessSessionState.STOPPING.ordinal()] = 9;
            $EnumSwitchMapping$1 = new int[FitnessSessionTransition.values().length];
            $EnumSwitchMapping$1[FitnessSessionTransition.START_COMMAND_RECEIVED.ordinal()] = 1;
            $EnumSwitchMapping$1[FitnessSessionTransition.RESUME_COMMAND_RECEIVED.ordinal()] = 2;
            $EnumSwitchMapping$1[FitnessSessionTransition.RECOVERY_COMMAND_RECEIVED.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[FitnessSessionTransition.values().length];
            $EnumSwitchMapping$2[FitnessSessionTransition.START_COMMAND_RECEIVED.ordinal()] = 1;
            $EnumSwitchMapping$2[FitnessSessionTransition.RESUME_COMMAND_RECEIVED.ordinal()] = 2;
            $EnumSwitchMapping$2[FitnessSessionTransition.RECOVERY_COMMAND_RECEIVED.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[FitnessSessionTransition.values().length];
            $EnumSwitchMapping$3[FitnessSessionTransition.START_COMMAND_RECEIVED.ordinal()] = 1;
            $EnumSwitchMapping$3[FitnessSessionTransition.RESUME_COMMAND_RECEIVED.ordinal()] = 2;
            $EnumSwitchMapping$3[FitnessSessionTransition.RECOVERY_COMMAND_RECEIVED.ordinal()] = 3;
            $EnumSwitchMapping$4 = new int[FitnessSessionTransition.values().length];
            $EnumSwitchMapping$4[FitnessSessionTransition.START_COMMAND_RECEIVED.ordinal()] = 1;
            $EnumSwitchMapping$5 = new int[FitnessSessionState.values().length];
            $EnumSwitchMapping$5[FitnessSessionState.RECOVERING.ordinal()] = 1;
            $EnumSwitchMapping$5[FitnessSessionState.STARTING.ordinal()] = 2;
            $EnumSwitchMapping$5[FitnessSessionState.RESUMING.ordinal()] = 3;
            $EnumSwitchMapping$6 = new int[Timeout.values().length];
            $EnumSwitchMapping$6[Timeout.AccessoryDisconnected.ordinal()] = 1;
            $EnumSwitchMapping$6[Timeout.NoSample.ordinal()] = 2;
        }
    }

    public SessionManagerImpl(@NotNull AfxMessageProcessor afxMessageProcessor, @NotNull MetricsAggregator metricsAggregator, @NotNull MetricsAggregatorRecovery metricsAggregatorRecovery, @NotNull FitnessAlgorithmsManager fitnessAlgorithmsManager, @NotNull SessionRecoveryManager recoveryManager, @Nullable WeakReference<SessionManagerDelegate> weakReference, @NotNull ILog log, @NotNull TimeoutHandler timeoutHandler, @NotNull SampleStore sampleStore, @NotNull UserPreferenceStore userPreferenceStore) {
        Intrinsics.checkParameterIsNotNull(afxMessageProcessor, "afxMessageProcessor");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(metricsAggregatorRecovery, "metricsAggregatorRecovery");
        Intrinsics.checkParameterIsNotNull(fitnessAlgorithmsManager, "fitnessAlgorithmsManager");
        Intrinsics.checkParameterIsNotNull(recoveryManager, "recoveryManager");
        Intrinsics.checkParameterIsNotNull(log, "log");
        Intrinsics.checkParameterIsNotNull(timeoutHandler, "timeoutHandler");
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(userPreferenceStore, "userPreferenceStore");
        this.metricsAggregator = metricsAggregator;
        this.metricsAggregatorRecovery = metricsAggregatorRecovery;
        this.fitnessAlgorithmsManager = fitnessAlgorithmsManager;
        this.recoveryManager = recoveryManager;
        this.delegate = weakReference;
        this.log = log;
        this.timeoutHandler = timeoutHandler;
        this.sampleStore = sampleStore;
        this.userPreferenceStore = userPreferenceStore;
        this.stateMachine = new StateMachine<>(FitnessPlanKt.fitnessPlan());
        this.sensorCommandReceiver = new SessionManagerImpl$sensorCommandReceiver$1(this);
        this.encodeSession = new SessionManagerImpl$encodeSession$1(this);
        this.decodeSession = SessionManagerImpl$decodeSession$1.INSTANCE;
        RepeatingTimer repeatingTimer = new RepeatingTimer(afxMessageProcessor, this.log, 0L, 4, null);
        repeatingTimer.setRunnable(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.SessionManagerImpl$$special$$inlined$apply$lambda$1
            @Override // java.lang.Runnable
            public final void run() {
                SessionRecoveryManager sessionRecoveryManager;
                List<String> sensorIds;
                String str;
                TimeoutHandler timeoutHandler2;
                sessionRecoveryManager = SessionManagerImpl.this.recoveryManager;
                sessionRecoveryManager.save();
                Session session = SessionManagerImpl.this.getSession();
                if (session == null || (sensorIds = session.getSensorIds()) == null || (str = (String) CollectionsKt.firstOrNull((List<? extends Object>) sensorIds)) == null) {
                    return;
                }
                timeoutHandler2 = SessionManagerImpl.this.timeoutHandler;
                Timeout reevaluate = timeoutHandler2.reevaluate(str);
                if (reevaluate == null) {
                    return;
                }
                SessionManagerImpl.this.handleTimeout(reevaluate);
            }
        });
        this.repeatingTimer = repeatingTimer;
        this.sessionMetrics = new SessionMetrics(this.metricsAggregator, this.metricsAggregatorRecovery, this.log);
        ILog.DefaultImpls.info$default(this.log, "SessionManager", "session manager created", null, 4, null);
        thisRef = this;
        this.recoveryManager.registerSessionRecovery(this.encodeSession, this.decodeSession);
        this.sensorIdToLastKnownUnavailableTime = new HashMap<>();
    }

    private final void attempt(FitnessSessionTransition fitnessSessionTransition, Command command, SensorError sensorError) {
        this.processingCommand = true;
        try {
            try {
                handle(this.stateMachine.getState(), fitnessSessionTransition, this.stateMachine.process(fitnessSessionTransition), sensorError, command);
            } catch (Exception e) {
                this.log.error("SessionManager", "error transitioning ", e);
                String message = e.getMessage();
                if (message == null) {
                    message = "Invalid Transition";
                }
                throw new SessionManagerException(message);
            }
        } finally {
            this.processingCommand = false;
        }
    }

    static /* synthetic */ void attempt$default(SessionManagerImpl sessionManagerImpl, FitnessSessionTransition fitnessSessionTransition, Command command, SensorError sensorError, int i, Object obj) {
        if ((i & 2) != 0) {
            command = null;
        }
        if ((i & 4) != 0) {
            sensorError = null;
        }
        sessionManagerImpl.attempt(fitnessSessionTransition, command, sensorError);
    }

    private final FitnessSessionTransition attemptRecovery() {
        ILog.DefaultImpls.info$default(this.log, "SessionManager", "Attempting to recover any previous sessions.", null, 4, null);
        SessionRecoveryResult recoverFitnessSession = this.recoveryManager.recoverFitnessSession();
        if (recoverFitnessSession instanceof SessionRecoveryResult.Recovered) {
            setSession(((SessionRecoveryResult.Recovered) recoverFitnessSession).getSession());
            attemptToRecover();
            return null;
        } else if (recoverFitnessSession instanceof SessionRecoveryResult.NothingToRecover) {
            ILog.DefaultImpls.info$default(this.log, "SessionManager", "Nothing to recover.", null, 4, null);
            return FitnessSessionTransition.RECOVERED_NOTHING_TO_RECOVER;
        } else if (recoverFitnessSession instanceof SessionRecoveryResult.UnrecoverableNoData) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getRECOVER_FAILED_NO_DATA());
            return FitnessSessionTransition.RECOVERING_FAILED_NO_DATA;
        } else if (recoverFitnessSession instanceof SessionRecoveryResult.UnrecoverableStaleData) {
            setSession(((SessionRecoveryResult.UnrecoverableStaleData) recoverFitnessSession).getSession());
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getRECOVERY_FAILED_STALE());
            return FitnessSessionTransition.RECOVERING_FAILED_STALE_DATA;
        } else if (!(recoverFitnessSession instanceof SessionRecoveryResult.FailedToRecover)) {
            throw new NoWhenBranchMatchedException();
        } else {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getRECOVER_FAILED_INTERNAL());
            return FitnessSessionTransition.RECOVERING_FAILED;
        }
    }

    private final void attemptToPause() {
        List<? extends SensorProvider> list = this.sensorProviders;
        if (list != null) {
            for (SensorProvider sensorProvider : list) {
                sensorProvider.pause(new SessionManagerImpl$attemptToPause$$inlined$forEach$lambda$1(this));
            }
        }
    }

    private final void attemptToRecover() {
        SessionConfiguration configuration;
        Session session = getSession();
        if (session != null && (configuration = session.getConfiguration()) != null) {
            attemptTransition(FitnessSessionTransition.RECOVERY_COMMAND_RECEIVED, configuration);
        } else {
            ILog.DefaultImpls.error$default(this.log, "SessionManager", "No session created before attempting to recover", null, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void attemptToRecoverOptionalSensor(SensorProvider sensorProvider, SessionConfiguration sessionConfiguration) {
        List<String> sensorIds;
        Session session = getSession();
        if (session != null && (sensorIds = session.getSensorIds()) != null) {
            sensorProvider.recover(sessionConfiguration.getSessionId(), sensorIds, new SessionManagerImpl$attemptToRecoverOptionalSensor$$inlined$let$lambda$1(this, sensorProvider, sessionConfiguration));
        } else {
            ILog.DefaultImpls.error$default(this.log, "SessionManager", "no sensors available in session to recover", null, 4, null);
        }
    }

    private final void attemptToResume() {
        SessionConfiguration configuration;
        Session session = getSession();
        if (session != null && (configuration = session.getConfiguration()) != null) {
            attemptTransition(FitnessSessionTransition.RESUME_COMMAND_RECEIVED, configuration);
        } else {
            ILog.DefaultImpls.error$default(this.log, "SessionManager", "No session created before attempting to resume", null, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void attemptToResumeOptionalSensor(SensorProvider sensorProvider) {
        sensorProvider.resume(new SessionManagerImpl$attemptToResumeOptionalSensor$1(this, sensorProvider));
    }

    private final void attemptToStart() {
        SessionConfiguration configuration;
        Session session = getSession();
        if (session != null && (configuration = session.getConfiguration()) != null) {
            attemptTransition(FitnessSessionTransition.START_COMMAND_RECEIVED, configuration);
        } else {
            ILog.DefaultImpls.error$default(this.log, "SessionManager", "No session created before attempting to start", null, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void attemptToStartOptionalSensor(SensorProvider sensorProvider, SessionConfiguration sessionConfiguration) {
        sensorProvider.start(sessionConfiguration.getSessionId(), new SessionManagerImpl$attemptToStartOptionalSensor$1(this, sensorProvider));
    }

    private final FitnessSessionTransition attemptToStop() {
        List<? extends SensorProvider> list = this.sensorProviders;
        if (list != null) {
            for (SensorProvider sensorProvider : list) {
                sensorProvider.stop(new SessionManagerImpl$attemptToStop$$inlined$forEach$lambda$1(this));
            }
        }
        this.metricsAggregator.recordCounter(AggregatedMetricsConstants.Companion.getSESSION_COMPLETE(), 1L);
        if (this.userPreferenceStore.get(UserPreferenceKey.IsRouteTrackingEnabled)) {
            this.metricsAggregator.recordCounter(AggregatedMetricsConstants.Companion.getSESSION_COMPLETE_WITH_ROUTE(), 1L);
        }
        return FitnessSessionTransition.STOPPED;
    }

    private final void attemptTransition(FitnessSessionTransition fitnessSessionTransition, SessionConfiguration sessionConfiguration) {
        List<String> sensorIds;
        List<SensorProvider> requiredSensors = getRequiredSensors(fitnessSessionTransition);
        if (requiredSensors != null) {
            ILog iLog = this.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("# of required sensors = ");
            outline107.append(requiredSensors.size());
            ILog.DefaultImpls.debug$default(iLog, "SessionManager", outline107.toString(), null, 4, null);
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 0;
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = false;
            SessionManagerImpl$attemptTransition$$inlined$let$lambda$1 sessionManagerImpl$attemptTransition$$inlined$let$lambda$1 = new SessionManagerImpl$attemptTransition$$inlined$let$lambda$1(booleanRef, intRef, requiredSensors, this, fitnessSessionTransition, sessionConfiguration);
            for (SensorProvider sensorProvider : requiredSensors) {
                ILog iLog2 = this.log;
                ILog.DefaultImpls.info$default(iLog2, "SessionManager", "attempting to handle " + fitnessSessionTransition + " on " + sensorProvider, null, 4, null);
                int i = WhenMappings.$EnumSwitchMapping$2[fitnessSessionTransition.ordinal()];
                if (i == 1) {
                    sensorProvider.start(sessionConfiguration.getSessionId(), new SessionManagerImpl$attemptTransition$$inlined$let$lambda$2(sessionManagerImpl$attemptTransition$$inlined$let$lambda$1, this, fitnessSessionTransition, sessionConfiguration));
                } else if (i == 2) {
                    sensorProvider.resume(new SessionManagerImpl$attemptTransition$$inlined$let$lambda$3(sessionManagerImpl$attemptTransition$$inlined$let$lambda$1, this, fitnessSessionTransition, sessionConfiguration));
                } else if (i == 3) {
                    Session session = getSession();
                    if (session != null && (sensorIds = session.getSensorIds()) != null) {
                        sensorProvider.recover(sessionConfiguration.getSessionId(), sensorIds, new SessionManagerImpl$attemptTransition$$inlined$let$lambda$4(sensorProvider, sessionManagerImpl$attemptTransition$$inlined$let$lambda$1, this, fitnessSessionTransition, sessionConfiguration));
                    } else {
                        ILog.DefaultImpls.error$default(this.log, "SessionManager", "no sensors to recover in session", null, 4, null);
                    }
                }
            }
        }
    }

    private final FitnessAccessorySensorProvider getAccessorySensorProviderInUse() {
        List<? extends SensorProvider> list = this.sensorProviders;
        if (list != null) {
            for (SensorProvider sensorProvider : list) {
                if (sensorProvider instanceof FitnessAccessorySensorProvider) {
                    return (FitnessAccessorySensorProvider) sensorProvider;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<SensorProvider> getOptionalSensors(FitnessSessionTransition fitnessSessionTransition) {
        List<? extends SensorProvider> list;
        List<SensorProvider> mutableList;
        if (WhenMappings.$EnumSwitchMapping$4[fitnessSessionTransition.ordinal()] == 1 && (list = this.sensorProviders) != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (!((SensorProvider) obj).getRequired()) {
                    arrayList.add(obj);
                }
            }
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
            return mutableList;
        }
        return null;
    }

    private final List<SensorProvider> getRequiredSensors(FitnessSessionTransition fitnessSessionTransition) {
        List<SensorProvider> mutableList;
        List<? extends SensorProvider> list;
        List<SensorProvider> mutableList2;
        List<SensorProviderType> sensorProviderTypes;
        int i = WhenMappings.$EnumSwitchMapping$3[fitnessSessionTransition.ordinal()];
        if (i == 1) {
            List<? extends SensorProvider> list2 = this.sensorProviders;
            if (list2 == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : list2) {
                if (((SensorProvider) obj).getRequired()) {
                    arrayList.add(obj);
                }
            }
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
            return mutableList;
        } else if (!(i == 2 || i == 3) || (list = this.sensorProviders) == null) {
            return null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : list) {
                SensorProvider sensorProvider = (SensorProvider) obj2;
                Session session = getSession();
                if ((session == null || (sensorProviderTypes = session.getSensorProviderTypes()) == null || !sensorProviderTypes.contains(sensorProvider.getSensorProviderType())) ? false : true) {
                    arrayList2.add(obj2);
                }
            }
            mutableList2 = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList2);
            return mutableList2;
        }
    }

    private final void handle(FitnessSessionState fitnessSessionState, FitnessSessionTransition fitnessSessionTransition, FitnessSessionState fitnessSessionState2, SensorError sensorError, Command command) {
        FitnessSessionTransition attemptRecovery;
        Unit unit;
        MetricComponent start_with_full_fitness_profile;
        ArrayList<SensorProvider> arrayList;
        List<String> emptyList;
        List<? extends SensorProviderType> emptyList2;
        FitnessAccessorySensorProvider firstEligibleFitnessAccessorySensorProvider$default;
        int collectionSizeOrDefault;
        boolean contains;
        SessionManagerDelegate sessionManagerDelegate;
        int collectionSizeOrDefault2;
        List<SessionEvent> events;
        ILog.DefaultImpls.info$default(this.log, "SessionManager", "Transitioned from: " + fitnessSessionState + ", with: " + fitnessSessionTransition + ", to: " + fitnessSessionState2, null, 4, null);
        switch (WhenMappings.$EnumSwitchMapping$0[fitnessSessionState2.ordinal()]) {
            case 1:
                attemptRecovery = attemptRecovery();
                unit = Unit.INSTANCE;
                break;
            case 2:
                if (fitnessSessionState == FitnessSessionState.STOPPING) {
                    getFitnessAlgorithmsManager().sessionEnded();
                }
                unit = Unit.INSTANCE;
                attemptRecovery = null;
                break;
            case 3:
                if (command != null) {
                    if (command instanceof Command.Start) {
                        Command.Start start = (Command.Start) command;
                        Session session = new Session(DateTime.Companion.now(), start.getSessionConfiguration(), new ArrayList(), start.getSessionConfiguration().getUserProfile(), null, null, null, null, null, null, 1008, null);
                        getFitnessAlgorithmsManager().start(session);
                        setSession(session);
                        Unit unit2 = Unit.INSTANCE;
                        UserProfile userProfile = start.getSessionConfiguration().getUserProfile();
                        if (userProfile != null) {
                            if (userProfile.getWeightInKilograms() == null) {
                                start_with_full_fitness_profile = AggregatedMetricsConstants.Companion.getSTART_WITH_PARTIAL_FITNESS_PROFILE();
                            } else {
                                start_with_full_fitness_profile = AggregatedMetricsConstants.Companion.getSTART_WITH_FULL_FITNESS_PROFILE();
                            }
                            this.metricsAggregator.recordCounter(start_with_full_fitness_profile, 1L);
                            Unit unit3 = Unit.INSTANCE;
                        }
                    }
                    Unit unit4 = Unit.INSTANCE;
                }
                attemptToStart();
                unit = Unit.INSTANCE;
                attemptRecovery = null;
                break;
            case 4:
                unit = Unit.INSTANCE;
                attemptRecovery = null;
                break;
            case 5:
                List<? extends SensorProvider> list = this.sensorProviders;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (Object obj : list) {
                        if (Intrinsics.areEqual(((SensorProvider) obj).getAvailability(), SensorAvailability.Available.INSTANCE)) {
                            arrayList.add(obj);
                        }
                    }
                } else {
                    arrayList = null;
                }
                Session session2 = getSession();
                if (session2 != null) {
                    if (arrayList != null) {
                        emptyList = new ArrayList<>();
                        for (SensorProvider sensorProvider : arrayList) {
                            String sensorInUse = sensorProvider.getSensorInUse();
                            if (sensorInUse != null) {
                                emptyList.add(sensorInUse);
                            }
                        }
                    } else {
                        emptyList = CollectionsKt__CollectionsKt.emptyList();
                    }
                    session2.setSensorIds(emptyList);
                    if (arrayList != null) {
                        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
                        emptyList2 = new ArrayList<>(collectionSizeOrDefault);
                        for (SensorProvider sensorProvider2 : arrayList) {
                            emptyList2.add(sensorProvider2.getSensorProviderType());
                        }
                    } else {
                        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
                    }
                    session2.setSensorProviderTypes(emptyList2);
                    FitnessAccessorySensorProvider accessorySensorProviderInUse = getAccessorySensorProviderInUse();
                    session2.setDeviceTypeInUse(DeviceTypeKt.convertIdentifierToDeviceType(accessorySensorProviderInUse != null ? accessorySensorProviderInUse.getDeviceTypeInUse() : null));
                    List<? extends SensorProvider> list2 = this.sensorProviders;
                    if (list2 != null && (firstEligibleFitnessAccessorySensorProvider$default = SessionManagerKt.firstEligibleFitnessAccessorySensorProvider$default(list2, null, 1, null)) != null) {
                        session2.setDataSource(firstEligibleFitnessAccessorySensorProvider$default.getDataSource());
                        Unit unit5 = Unit.INSTANCE;
                    }
                    Unit unit6 = Unit.INSTANCE;
                }
                this.repeatingTimer.start();
                unit = Unit.INSTANCE;
                attemptRecovery = null;
                break;
            case 6:
                attemptToPause();
                this.timeoutHandler.reset();
                unit = Unit.INSTANCE;
                attemptRecovery = null;
                break;
            case 7:
                attemptToResume();
                unit = Unit.INSTANCE;
                attemptRecovery = null;
                break;
            case 8:
                unit = Unit.INSTANCE;
                attemptRecovery = null;
                break;
            case 9:
                attemptRecovery = attemptToStop();
                this.timeoutHandler.reset();
                this.repeatingTimer.stop();
                Session session3 = getSession();
                if (session3 != null) {
                    session3.setEndTime(DateTime.Companion.now());
                }
                this.recoveryManager.save();
                this.recoveryManager.reset();
                unit = Unit.INSTANCE;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        ExhaustiveKt.exhaustive(unit);
        Session session4 = getSession();
        if (session4 != null && (events = session4.getEvents()) != null) {
            Boolean.valueOf(events.add(new SessionEvent(new SessionEventType.StateChangeEvent(fitnessSessionState2, fitnessSessionTransition, command, sensorError), DateTime.Companion.now())));
        }
        contains = ArraysKt___ArraysKt.contains(new FitnessSessionState[]{FitnessSessionState.PAUSED, FitnessSessionState.STOPPING}, fitnessSessionState2);
        if (contains) {
            Session session5 = getSession();
            if (session5 != null) {
                List<Sample.LocationSample> queryAllLocationSamplesSync = getSampleStore().queryAllLocationSamplesSync(session5.getConfiguration().getSessionId());
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(queryAllLocationSamplesSync, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
                for (Sample.LocationSample locationSample : queryAllLocationSamplesSync) {
                    arrayList2.add(new LocationCoordinate(locationSample.getSampleData().getLatitude(), locationSample.getSampleData().getLongitude()));
                }
                session5.setCoordinates(arrayList2);
                Unit unit7 = Unit.INSTANCE;
            }
        } else {
            Session session6 = getSession();
            if (session6 != null) {
                session6.setCoordinates(null);
            }
        }
        WeakReference<SessionManagerDelegate> delegate = getDelegate();
        if (delegate != null && (sessionManagerDelegate = delegate.get()) != null) {
            sessionManagerDelegate.stateDidChange(fitnessSessionState, fitnessSessionTransition, fitnessSessionState2, sensorError);
            Unit unit8 = Unit.INSTANCE;
        }
        getFitnessAlgorithmsManager().stateDidChange(getSession());
        if (attemptRecovery != null) {
            attempt$default(this, attemptRecovery, null, null, 6, null);
            Unit unit9 = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleTimeout(Timeout timeout) {
        FitnessSessionTransition fitnessSessionTransition;
        ILog iLog = this.log;
        ILog.DefaultImpls.info$default(iLog, "SessionManager", timeout + " occurred", null, 4, null);
        int i = WhenMappings.$EnumSwitchMapping$6[timeout.ordinal()];
        if (i == 1) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getSTOP_ACCESSORY());
            fitnessSessionTransition = FitnessSessionTransition.AUTOSTOP_SENSOR_DISCONNECTED;
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getSTOP_NO_SAMPLE());
            fitnessSessionTransition = FitnessSessionTransition.AUTOSTOP_NO_DATA_TIMEOUT;
        }
        attempt$default(this, fitnessSessionTransition, null, null, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sensorCallback(SensorError sensorError) {
        int i = WhenMappings.$EnumSwitchMapping$5[getState().ordinal()];
        FitnessSessionState fitnessSessionState = null;
        if (i == 1) {
            if (sensorError == null) {
                Session session = getSession();
                if (session != null) {
                    fitnessSessionState = SessionDataModelsKt.previousState(session);
                }
                if (fitnessSessionState == FitnessSessionState.PAUSED) {
                    attempt$default(this, FitnessSessionTransition.RECOVERED_WAS_PAUSED, null, null, 6, null);
                    return;
                } else {
                    attempt$default(this, FitnessSessionTransition.RECOVERED_WAS_ACTIVE_SUCCEEDED, null, null, 6, null);
                    return;
                }
            }
            ILog iLog = this.log;
            ILog.DefaultImpls.error$default(iLog, "SessionManager", "error recovering sensor " + sensorError, null, 4, null);
            attempt$default(this, FitnessSessionTransition.RECOVERED_WAS_ACTIVE_FAILED, null, null, 6, null);
        } else if (i == 2) {
            if (sensorError == null) {
                ILog.DefaultImpls.info$default(this.log, "SessionManager", "started successfully", null, 4, null);
                attempt$default(this, FitnessSessionTransition.STARTED, null, null, 6, null);
                return;
            }
            ILog iLog2 = this.log;
            ILog.DefaultImpls.error$default(iLog2, "SessionManager", "error while starting - " + sensorError, null, 4, null);
            attempt(FitnessSessionTransition.STARTING_FAILED, null, sensorError);
        } else if (i != 3) {
            ILog iLog3 = this.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unexpected call for ");
            outline107.append(getState());
            ILog.DefaultImpls.error$default(iLog3, "SessionManager", outline107.toString(), null, 4, null);
        } else if (sensorError == null) {
            ILog.DefaultImpls.info$default(this.log, "SessionManager", "resumed successfully", null, 4, null);
            attempt$default(this, FitnessSessionTransition.RESUMED, null, null, 6, null);
        } else {
            ILog iLog4 = this.log;
            ILog.DefaultImpls.error$default(iLog4, "SessionManager", "error while resuming - " + sensorError, null, 4, null);
            attempt(FitnessSessionTransition.RESUMING_FAILED, null, sensorError);
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    @Nullable
    public WeakReference<SessionManagerDelegate> getDelegate() {
        return this.delegate;
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    @NotNull
    public FitnessAlgorithmsManager getFitnessAlgorithmsManager() {
        return this.fitnessAlgorithmsManager;
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    @NotNull
    public SampleStore getSampleStore() {
        return this.sampleStore;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorStateObserver
    @NotNull
    public HashMap<String, Long> getSensorIdToLastKnownUnavailableTime() {
        return this.sensorIdToLastKnownUnavailableTime;
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    @Nullable
    public Session getSession() {
        return this.session;
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    @NotNull
    public FitnessSessionState getState() {
        return this.stateMachine.getState();
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    public void initSensorProviders(@NotNull List<? extends SensorProvider> sensorProviders) {
        Intrinsics.checkParameterIsNotNull(sensorProviders, "sensorProviders");
        this.sensorProviders = sensorProviders;
        for (SensorProvider sensorProvider : sensorProviders) {
            sensorProvider.prepare();
            sensorProvider.setSampleObserver(this);
            sensorProvider.setSensorCommandReceiver(this.sensorCommandReceiver);
            List<WeakReference<SensorStateObserver>> stateObservers = sensorProvider.getStateObservers();
            if (stateObservers != null) {
                stateObservers.add(new WeakReference<>(this.timeoutHandler));
            }
            List<WeakReference<SensorStateObserver>> stateObservers2 = sensorProvider.getStateObservers();
            if (stateObservers2 != null) {
                stateObservers2.add(new WeakReference<>(this));
            }
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorStateObserver
    public void onAvailabilityChanged(@NotNull String sensorId, @NotNull SensorAvailability isAvailable, long j, @Nullable Throwable th) {
        SessionManagerDelegate sessionManagerDelegate;
        List<? extends SensorProvider> list;
        FitnessAccessorySensorProvider firstEligibleFitnessAccessorySensorProvider;
        Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
        Intrinsics.checkParameterIsNotNull(isAvailable, "isAvailable");
        Session session = getSession();
        if (session != null && (list = this.sensorProviders) != null && (firstEligibleFitnessAccessorySensorProvider = SessionManagerKt.firstEligibleFitnessAccessorySensorProvider(list, sensorId)) != null) {
            session.setDataSource(firstEligibleFitnessAccessorySensorProvider.getDataSource());
        }
        WeakReference<SessionManagerDelegate> delegate = getDelegate();
        if (delegate == null || (sessionManagerDelegate = delegate.get()) == null) {
            return;
        }
        sessionManagerDelegate.sensorAvailabilityChanged(isAvailable, sensorId);
    }

    @Override // com.amazon.alexa.fitness.sdk.SampleObserver
    public void onNextSample(@NotNull Sample sample) {
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        this.timeoutHandler.handleSample(sample);
        getSampleStore().store(sample);
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    public void receive(@NotNull Command command) throws SessionManagerException {
        Intrinsics.checkParameterIsNotNull(command, "command");
        ILog iLog = this.log;
        ILog.DefaultImpls.info$default(iLog, "SessionManager", "Received " + command, null, 4, null);
        this.sessionMetrics.parseCommandRecordMetric(command);
        if (!this.processingCommand) {
            try {
                attempt$default(this, SessionManagerKt.toTransition(command), command, null, 4, null);
                return;
            } catch (Exception e) {
                ILog iLog2 = this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid Command for current state: ");
                outline107.append(getState());
                iLog2.error("SessionManager", outline107.toString(), e);
                String message = e.getMessage();
                if (message == null) {
                    message = "Invalid Transition";
                }
                throw new SessionManagerException(message);
            }
        }
        ILog.DefaultImpls.error$default(this.log, "SessionManager", "received a command while processing a previous command", null, 4, null);
        throw new SessionManagerException("Received a command while processing a previous command");
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    public void setDelegate(@Nullable WeakReference<SessionManagerDelegate> weakReference) {
        this.delegate = weakReference;
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManager
    public void setSampleStore(@NotNull SampleStore sampleStore) {
        Intrinsics.checkParameterIsNotNull(sampleStore, "<set-?>");
        this.sampleStore = sampleStore;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorStateObserver
    public void setSensorIdToLastKnownUnavailableTime(@NotNull HashMap<String, Long> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.sensorIdToLastKnownUnavailableTime = hashMap;
    }

    public void setSession(@Nullable Session session) {
        this.session = session;
    }

    public /* synthetic */ SessionManagerImpl(AfxMessageProcessor afxMessageProcessor, MetricsAggregator metricsAggregator, MetricsAggregatorRecovery metricsAggregatorRecovery, FitnessAlgorithmsManager fitnessAlgorithmsManager, SessionRecoveryManager sessionRecoveryManager, WeakReference weakReference, ILog iLog, TimeoutHandler timeoutHandler, SampleStore sampleStore, UserPreferenceStore userPreferenceStore, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(afxMessageProcessor, metricsAggregator, metricsAggregatorRecovery, fitnessAlgorithmsManager, sessionRecoveryManager, (i & 32) != 0 ? null : weakReference, iLog, timeoutHandler, sampleStore, userPreferenceStore);
    }
}
