package com.amazon.alexa.fitness.context;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.context.SessionSummaryProviderImpl;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.Measurement;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleType;
import com.amazon.alexa.fitness.sdk.sample.ObserverToken;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.util.ConversionUtils;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionSummaryProvider.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0012\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\u0018\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J\u001a\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020*2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010+\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010,\u001a\u00020!H\u0002J\u0018\u0010-\u001a\u00020!2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010.\u001a\u00020!H\u0002R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001c0\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/amazon/alexa/fitness/context/SessionSummaryProviderImpl;", "Lcom/amazon/alexa/fitness/context/SessionSummaryProvider;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;Lcom/amazon/alexa/fitness/logs/ILog;)V", "delegate", "Lcom/amazon/alexa/fitness/context/SessionSummaryProviderDelegate;", "getDelegate", "()Lcom/amazon/alexa/fitness/context/SessionSummaryProviderDelegate;", "setDelegate", "(Lcom/amazon/alexa/fitness/context/SessionSummaryProviderDelegate;)V", "fitnessMetrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "mainHandler", "Landroid/os/Handler;", "repeatingRunnable", "com/amazon/alexa/fitness/context/SessionSummaryProviderImpl$repeatingRunnable$1", "Lcom/amazon/alexa/fitness/context/SessionSummaryProviderImpl$repeatingRunnable$1;", "sampleObserverTokens", "", "Lcom/amazon/alexa/fitness/sdk/sample/ObserverToken;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "totalCalories", "Lkotlin/Pair;", "", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", "totalDistance", "totalSteps", "", "cleanup", "", "getDefaultCaloriesValue", "handleMeasurementSample", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample$MeasurementSample;", "sampleType", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "onSessionStateChanged", "currentState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "recoverSamples", "reset", "setupSampleSubscription", "updateMetrics", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionSummaryProviderImpl implements SessionSummaryProvider {
    @Nullable
    private SessionSummaryProviderDelegate delegate;
    private FitnessMetrics fitnessMetrics;
    private final ILog log;
    private final Handler mainHandler;
    private final SessionSummaryProviderImpl$repeatingRunnable$1 repeatingRunnable;
    private List<ObserverToken> sampleObserverTokens;
    private final SampleStore sampleStore;
    private com.amazon.alexa.fitness.api.fitnessSdk.Session session;
    private Pair<Double, ? extends Units> totalCalories;
    private Pair<Double, ? extends Units> totalDistance;
    private Pair<Integer, ? extends Units> totalSteps;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessSessionState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[FitnessSessionState.STARTING.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessSessionState.RECOVERING.ordinal()] = 2;
            $EnumSwitchMapping$0[FitnessSessionState.ACTIVE.ordinal()] = 3;
            $EnumSwitchMapping$0[FitnessSessionState.PAUSED.ordinal()] = 4;
            $EnumSwitchMapping$0[FitnessSessionState.IDLE.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[SampleType.values().length];
            $EnumSwitchMapping$1[SampleType.Distance.ordinal()] = 1;
            $EnumSwitchMapping$1[SampleType.StepCount.ordinal()] = 2;
            $EnumSwitchMapping$1[SampleType.ActiveCaloriesBurned.ordinal()] = 3;
        }
    }

    /* JADX WARN: Type inference failed for: r3v5, types: [com.amazon.alexa.fitness.context.SessionSummaryProviderImpl$repeatingRunnable$1] */
    @Inject
    public SessionSummaryProviderImpl(@NotNull SampleStore sampleStore, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.sampleStore = sampleStore;
        this.log = log;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.totalSteps = new Pair<>(0, Units.Count);
        this.totalDistance = new Pair<>(Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR), Units.Feet);
        this.totalCalories = new Pair<>(Double.valueOf(getDefaultCaloriesValue()), Units.KiloCalories);
        this.repeatingRunnable = new Runnable() { // from class: com.amazon.alexa.fitness.context.SessionSummaryProviderImpl$repeatingRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                Handler handler;
                handler = SessionSummaryProviderImpl.this.mainHandler;
                handler.postDelayed(this, 1000L);
                SessionSummaryProviderImpl.this.updateMetrics();
            }
        };
        this.sampleObserverTokens = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cleanup() {
        this.mainHandler.removeCallbacks(this.repeatingRunnable);
        for (ObserverToken observerToken : this.sampleObserverTokens) {
            this.sampleStore.removeObserver(observerToken);
        }
    }

    private final int getDefaultCaloriesValue() {
        UserProfile userProfile;
        Double weightInKilograms;
        com.amazon.alexa.fitness.api.fitnessSdk.Session session = this.session;
        if (session != null && (userProfile = session.getUserProfile()) != null && (weightInKilograms = userProfile.getWeightInKilograms()) != null) {
            if (weightInKilograms.doubleValue() != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                return 0;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleMeasurementSample(Sample.MeasurementSample measurementSample, SampleType sampleType) {
        if (measurementSample.getValue() instanceof Measurement.Discrete) {
            Measurement.Discrete value = measurementSample.getValue();
            if (this.fitnessMetrics == null) {
                this.totalCalories = new Pair<>(Double.valueOf(getDefaultCaloriesValue()), Units.KiloCalories);
                this.fitnessMetrics = new FitnessMetrics.StepBased(this.totalCalories, null, null, null, 14, null);
            }
            int i = WhenMappings.$EnumSwitchMapping$1[sampleType.ordinal()];
            if (i == 1) {
                this.totalDistance = new Pair<>(Double.valueOf(value.getValue()), Units.Feet);
                ILog iLog = this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updating distance to - ");
                outline107.append(value.getValue());
                ILog.DefaultImpls.info$default(iLog, "SessionSummaryProvider", outline107.toString(), null, 4, null);
            } else if (i == 2) {
                this.totalSteps = new Pair<>(Integer.valueOf((int) value.getValue()), Units.Count);
                ILog iLog2 = this.log;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("updating total steps to - ");
                outline1072.append(value.getValue());
                ILog.DefaultImpls.info$default(iLog2, "SessionSummaryProvider", outline1072.toString(), null, 4, null);
            } else if (i != 3) {
            } else {
                this.totalCalories = new Pair<>(Double.valueOf(value.getValue()), Units.KiloCalories);
                ILog iLog3 = this.log;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("updating calories to - ");
                outline1073.append(value.getValue());
                ILog.DefaultImpls.info$default(iLog3, "SessionSummaryProvider", outline1073.toString(), null, 4, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recoverSamples(com.amazon.alexa.fitness.api.fitnessSdk.Session session) {
        this.session = session;
        ILog.DefaultImpls.info$default(this.log, "SessionSummaryProvider", "session is recovering, get samples and update summary", null, 4, null);
        this.sampleStore.queryAllSamples(session.getConfiguration().getSessionId(), new SessionSummaryProviderImpl$recoverSamples$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reset() {
        ILog.DefaultImpls.info$default(this.log, "SessionSummaryProvider", "resetting values", null, 4, null);
        this.session = null;
        Double valueOf = Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        this.totalCalories = new Pair<>(valueOf, Units.KiloCalories);
        this.totalSteps = new Pair<>(0, Units.Count);
        this.totalDistance = new Pair<>(valueOf, Units.Feet);
        this.fitnessMetrics = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupSampleSubscription(SampleType sampleType, com.amazon.alexa.fitness.api.fitnessSdk.Session session) {
        this.sampleObserverTokens.add(this.sampleStore.queryMeasurementSamplesSubscription(session.getConfiguration().getSessionId(), sampleType, new SessionSummaryProviderImpl$setupSampleSubscription$1(this, sampleType)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMetrics() {
        if (this.fitnessMetrics == null) {
            return;
        }
        com.amazon.alexa.fitness.api.fitnessSdk.Session session = this.session;
        int duration = session != null ? SessionDataModelsKt.duration(session, DateTime.Companion.now()) : 1;
        double convertFeetToMiles = ConversionUtils.Companion.convertFeetToMiles(this.totalDistance.getFirst().doubleValue());
        this.fitnessMetrics = new FitnessMetrics.StepBased(this.totalCalories, this.totalSteps, new Pair(Double.valueOf(convertFeetToMiles), Units.Miles), new Pair(Double.valueOf(convertFeetToMiles / ConversionUtils.Companion.convertTimeInSecondsToTimeInHours(duration)), Units.MilesPerHour));
        SessionSummaryProviderDelegate delegate = getDelegate();
        if (delegate == null) {
            return;
        }
        delegate.onFitnessMetricsUpdated(this.fitnessMetrics);
    }

    @Override // com.amazon.alexa.fitness.context.SessionSummaryProvider
    @Nullable
    public SessionSummaryProviderDelegate getDelegate() {
        return this.delegate;
    }

    @Override // com.amazon.alexa.fitness.context.SessionSummaryProvider
    public void onSessionStateChanged(@NotNull final FitnessSessionState currentState, @Nullable final com.amazon.alexa.fitness.api.fitnessSdk.Session session) {
        Intrinsics.checkParameterIsNotNull(currentState, "currentState");
        if (session == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.context.SessionSummaryProviderImpl$onSessionStateChanged$1
            @Override // java.lang.Runnable
            public final void run() {
                Handler handler;
                SessionSummaryProviderImpl$repeatingRunnable$1 sessionSummaryProviderImpl$repeatingRunnable$1;
                ILog iLog;
                ILog iLog2;
                ILog iLog3;
                int i = SessionSummaryProviderImpl.WhenMappings.$EnumSwitchMapping$0[currentState.ordinal()];
                if (i == 1 || i == 2) {
                    SessionSummaryProviderImpl.this.reset();
                } else if (i == 3) {
                    if (SessionDataModelsKt.previousState(session) != FitnessSessionState.RESUMING) {
                        SessionSummaryProviderImpl.this.reset();
                    }
                    SessionSummaryProviderImpl.this.cleanup();
                    SessionSummaryProviderImpl.this.session = session;
                    handler = SessionSummaryProviderImpl.this.mainHandler;
                    sessionSummaryProviderImpl$repeatingRunnable$1 = SessionSummaryProviderImpl.this.repeatingRunnable;
                    handler.post(sessionSummaryProviderImpl$repeatingRunnable$1);
                    iLog = SessionSummaryProviderImpl.this.log;
                    ILog.DefaultImpls.info$default(iLog, "SessionSummaryProvider", "session became active, starting sample subscriptions", null, 4, null);
                    SessionSummaryProviderImpl.this.setupSampleSubscription(SampleType.Distance, session);
                    SessionSummaryProviderImpl.this.setupSampleSubscription(SampleType.StepCount, session);
                    SessionSummaryProviderImpl.this.setupSampleSubscription(SampleType.ActiveCaloriesBurned, session);
                } else if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                    iLog3 = SessionSummaryProviderImpl.this.log;
                    ILog.DefaultImpls.info$default(iLog3, "SessionSummaryProvider", "session idle, remove sample subscriptions", null, 4, null);
                    SessionSummaryProviderImpl.this.cleanup();
                } else {
                    if (SessionDataModelsKt.previousState(session) != FitnessSessionState.RECOVERING) {
                        SessionSummaryProviderImpl.this.updateMetrics();
                    } else {
                        SessionSummaryProviderImpl.this.session = session;
                        SessionSummaryProviderImpl.this.recoverSamples(session);
                    }
                    iLog2 = SessionSummaryProviderImpl.this.log;
                    ILog.DefaultImpls.info$default(iLog2, "SessionSummaryProvider", "session paused, removing sample subscriptions", null, 4, null);
                    SessionSummaryProviderImpl.this.cleanup();
                }
            }
        });
    }

    @Override // com.amazon.alexa.fitness.context.SessionSummaryProvider
    public void setDelegate(@Nullable SessionSummaryProviderDelegate sessionSummaryProviderDelegate) {
        this.delegate = sessionSummaryProviderDelegate;
    }
}
