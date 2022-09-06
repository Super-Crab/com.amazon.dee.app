package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.algorithms.ActivityEvent;
import com.amazon.alexa.fitness.algorithms.ActivityMetrics;
import com.amazon.alexa.fitness.algorithms.ActivityType;
import com.amazon.alexa.fitness.algorithms.BiometricProfile;
import com.amazon.alexa.fitness.algorithms.StepToDistanceAlgorithm;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.Aggregation;
import com.amazon.alexa.fitness.sdk.Measurement;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleType;
import com.amazon.alexa.fitness.sdk.sample.ObserverToken;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StepsToDistanceAlgorithmAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0016\u0018\u00002\u00020\u0001B%\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J1\u0010\u001e\u001a(\u0012\f\u0012\n !*\u0004\u0018\u00010 0  !*\u0014\u0012\u000e\b\u0001\u0012\n !*\u0004\u0018\u00010 0 \u0018\u00010\u001f0\u001f¢\u0006\u0002\u0010\"J.\u0010#\u001a\u0004\u0018\u00010\u001a2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020\u000fH\u0002J\u0010\u0010*\u001a\u00020\u00182\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010+\u001a\u00020\u0018H\u0016J\u0018\u0010,\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010-\u001a\u00020\u0018H\u0002J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u000200H\u0002J\f\u00101\u001a\u000202*\u000203H\u0002J\f\u00104\u001a\u000205*\u000205H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/StepsToDistanceAlgorithmAdapter;", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithm;", "stepsToDistanceAlgorithm", "Ldagger/Lazy;", "Lcom/amazon/alexa/fitness/algorithms/StepToDistanceAlgorithm;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Ldagger/Lazy;Lcom/amazon/alexa/fitness/api/afx/FeatureService;Lcom/amazon/alexa/fitness/logs/ILog;)V", "isHybridDistanceEnabled", "", "lastActivityType", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "lastKnownSampleTime", "", "Ljava/lang/Long;", "sampleObserverToken", "Lcom/amazon/alexa/fitness/sdk/sample/ObserverToken;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "sessionConfiguration", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "addEvent", "", "activityEvent", "Lcom/amazon/alexa/fitness/algorithms/ActivityEvent;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "generateDerivedSamples", "getMetrics", "", "Lcom/amazon/alexa/fitness/algorithms/ActivityMetrics;", "kotlin.jvm.PlatformType", "()[Lcom/amazon/alexa/fitness/algorithms/ActivityMetrics;", "handleStateChange", "previousState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "currentState", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "time", "sessionChangedState", "sessionEnded", "setup", "setupSampleSubscription", "startSTDAlgorithm", "userProfile", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "toAlgorithmSex", "Lcom/amazon/alexa/fitness/algorithms/BiometricProfile$Sex;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile$Gender;", "toInches", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public class StepsToDistanceAlgorithmAdapter implements FitnessAlgorithm {
    private final boolean isHybridDistanceEnabled;
    private ActivityType lastActivityType;
    private Long lastKnownSampleTime;
    private final ILog log;
    private ObserverToken sampleObserverToken;
    private SampleStore sampleStore;
    private SessionConfiguration sessionConfiguration;
    private final Lazy<StepToDistanceAlgorithm> stepsToDistanceAlgorithm;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UserProfile.Gender.values().length];

        static {
            $EnumSwitchMapping$0[UserProfile.Gender.MALE.ordinal()] = 1;
            $EnumSwitchMapping$0[UserProfile.Gender.FEMALE.ordinal()] = 2;
        }
    }

    @Inject
    public StepsToDistanceAlgorithmAdapter(@NotNull Lazy<StepToDistanceAlgorithm> stepsToDistanceAlgorithm, @NotNull FeatureService featureService, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(stepsToDistanceAlgorithm, "stepsToDistanceAlgorithm");
        Intrinsics.checkParameterIsNotNull(featureService, "featureService");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.stepsToDistanceAlgorithm = stepsToDistanceAlgorithm;
        this.log = log;
        this.isHybridDistanceEnabled = featureService.isHybridDistanceEnabled();
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("initializing algorithm (isHybridDistanceEnabled=");
        outline107.append(this.isHybridDistanceEnabled);
        outline107.append(')');
        ILog.DefaultImpls.debug$default(iLog, "StepsToDistanceAlgorithmAdapter", outline107.toString(), null, 4, null);
    }

    private final void addEvent(ActivityEvent activityEvent, UUID uuid) {
        try {
            this.stepsToDistanceAlgorithm.mo358get().addEvents(new ActivityEvent[]{activityEvent});
            generateDerivedSamples(uuid);
        } catch (Exception e) {
            this.log.error("StepsToDistanceAlgorithmAdapter", "Failed to add activity Event", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void generateDerivedSamples(UUID uuid) {
        StepToDistanceAlgorithm mo358get = this.stepsToDistanceAlgorithm.mo358get();
        Intrinsics.checkExpressionValueIsNotNull(mo358get, "stepsToDistanceAlgorithm.get()");
        ActivityMetrics[] activityMetrics = mo358get.getMetrics();
        ArrayList<Sample> arrayList = new ArrayList<>();
        Intrinsics.checkExpressionValueIsNotNull(activityMetrics, "activityMetrics");
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        int i = 0;
        for (ActivityMetrics activityMetrics2 : activityMetrics) {
            d += activityMetrics2.distance;
            i += activityMetrics2.steps;
            ILog iLog = this.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("adding samples with values - ");
            outline107.append(activityMetrics2.activityType);
            outline107.append(", ");
            outline107.append(activityMetrics2.distance);
            outline107.append(", ");
            outline107.append(activityMetrics2.steps);
            ILog.DefaultImpls.debug$default(iLog, "StepsToDistanceAlgorithmAdapter", outline107.toString(), null, 4, null);
        }
        ILog.DefaultImpls.debug$default(this.log, "StepsToDistanceAlgorithmAdapter", "generating samples with values - " + d + ", " + i, null, 4, null);
        long currentTimeMillis = System.currentTimeMillis();
        arrayList.add(new Sample.MeasurementSample(uuid, StepsToDistanceAlgorithmAdapterKt.STDAlgorithmId, currentTimeMillis, this.isHybridDistanceEnabled ? SampleType.StepBasedDistance : SampleType.Distance, new Measurement.Discrete(d), Units.Feet, Aggregation.PointInTime));
        arrayList.add(new Sample.MeasurementSample(uuid, StepsToDistanceAlgorithmAdapterKt.STDAlgorithmId, currentTimeMillis, SampleType.StepCount, new Measurement.Discrete(i), Units.Count, Aggregation.PointInTime));
        SampleStore sampleStore = this.sampleStore;
        if (sampleStore != null) {
            sampleStore.store(arrayList);
        }
    }

    private final ActivityEvent handleStateChange(FitnessSessionState fitnessSessionState, FitnessSessionState fitnessSessionState2, Session session, long j) {
        ILog iLog = this.log;
        ILog.DefaultImpls.info$default(iLog, "StepsToDistanceAlgorithmAdapter", "handling state change from " + fitnessSessionState + " to " + fitnessSessionState2, null, 4, null);
        if (FitnessAlgorithmUtilKt.starting(fitnessSessionState, fitnessSessionState2)) {
            UserProfile userProfile = session.getConfiguration().getUserProfile();
            if (userProfile != null) {
                startSTDAlgorithm(userProfile);
            }
        } else if (!FitnessAlgorithmUtilKt.started(fitnessSessionState, fitnessSessionState2) && !FitnessAlgorithmUtilKt.recovered(fitnessSessionState, fitnessSessionState2)) {
            if (FitnessAlgorithmUtilKt.resumed(fitnessSessionState, fitnessSessionState2) && this.sampleObserverToken == null) {
                ILog.DefaultImpls.info$default(this.log, "StepsToDistanceAlgorithmAdapter", "resuming after recovering to paused state, setting sample subscription", null, 4, null);
                setupSampleSubscription();
            }
        } else {
            setupSampleSubscription();
        }
        return FitnessAlgorithmUtilKt.getEventForStateChange(fitnessSessionState, fitnessSessionState2, j, this.lastActivityType);
    }

    static /* synthetic */ ActivityEvent handleStateChange$default(StepsToDistanceAlgorithmAdapter stepsToDistanceAlgorithmAdapter, FitnessSessionState fitnessSessionState, FitnessSessionState fitnessSessionState2, Session session, long j, int i, Object obj) {
        if (obj == null) {
            if ((i & 8) != 0) {
                j = DateTime.Companion.now().getEpochMilli();
            }
            return stepsToDistanceAlgorithmAdapter.handleStateChange(fitnessSessionState, fitnessSessionState2, session, j);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleStateChange");
    }

    private final void setupSampleSubscription() {
        SessionConfiguration sessionConfiguration = this.sessionConfiguration;
        if (sessionConfiguration != null) {
            SampleStore sampleStore = this.sampleStore;
            this.sampleObserverToken = sampleStore != null ? sampleStore.queryEchoBudSamplesSubscription(sessionConfiguration.getSessionId(), new StepsToDistanceAlgorithmAdapter$setupSampleSubscription$$inlined$let$lambda$1(this)) : null;
        }
    }

    private final void startSTDAlgorithm(UserProfile userProfile) {
        ILog.DefaultImpls.info$default(this.log, "StepsToDistanceAlgorithmAdapter", "starting STD algorithm", null, 4, null);
        this.stepsToDistanceAlgorithm.mo358get().startActivity(toInches(userProfile.getHeightInCentimetres()), toAlgorithmSex(userProfile.getGender()), StepToDistanceAlgorithm.UnitSystem.IMPERIAL_SYSTEM);
    }

    private final BiometricProfile.Sex toAlgorithmSex(@NotNull UserProfile.Gender gender) {
        int i = WhenMappings.$EnumSwitchMapping$0[gender.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return BiometricProfile.Sex.FEMALE;
        }
        return BiometricProfile.Sex.MALE;
    }

    private final double toInches(double d) {
        return d / 2.54d;
    }

    public final ActivityMetrics[] getMetrics() {
        StepToDistanceAlgorithm mo358get = this.stepsToDistanceAlgorithm.mo358get();
        Intrinsics.checkExpressionValueIsNotNull(mo358get, "stepsToDistanceAlgorithm.get()");
        return mo358get.getMetrics();
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void sessionChangedState(@NotNull Session session) {
        Intrinsics.checkParameterIsNotNull(session, "session");
        if (this.sampleStore == null || this.sessionConfiguration == null) {
            return;
        }
        UUID sessionId = session.getConfiguration().getSessionId();
        ActivityEvent handleStateChange$default = handleStateChange$default(this, SessionDataModelsKt.previousState(session), SessionDataModelsKt.currentState(session), session, 0L, 8, null);
        if (handleStateChange$default == null) {
            return;
        }
        addEvent(handleStateChange$default, sessionId);
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void sessionEnded() {
        SampleStore sampleStore;
        ILog.DefaultImpls.info$default(this.log, "StepsToDistanceAlgorithmAdapter", "removing sample subscription and stopping algorithm", null, 4, null);
        ObserverToken observerToken = this.sampleObserverToken;
        if (observerToken != null && (sampleStore = this.sampleStore) != null) {
            sampleStore.removeObserver(observerToken);
        }
        this.sampleObserverToken = null;
        SessionConfiguration sessionConfiguration = this.sessionConfiguration;
        if (sessionConfiguration != null) {
            addEvent(FitnessAlgorithmUtilKt.getEventFor(ActivityEvent.EventType.EVENT_TYPE_STOP, DateTime.Companion.now().getEpochMilli(), this.lastActivityType), sessionConfiguration.getSessionId());
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void setup(@NotNull SampleStore sampleStore, @NotNull Session session) {
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(session, "session");
        this.sessionConfiguration = session.getConfiguration();
        this.sampleStore = sampleStore;
        List<ActivityEvent> activityEventsFromSession = FitnessAlgorithmUtilKt.getActivityEventsFromSession(this, session, sampleStore);
        UserProfile userProfile = session.getConfiguration().getUserProfile();
        if (!(!activityEventsFromSession.isEmpty()) || userProfile == null) {
            return;
        }
        this.lastKnownSampleTime = Long.valueOf(((ActivityEvent) CollectionsKt.last((List<? extends Object>) activityEventsFromSession)).timestamp);
        startSTDAlgorithm(userProfile);
        try {
            StepToDistanceAlgorithm mo358get = this.stepsToDistanceAlgorithm.mo358get();
            Object[] array = activityEventsFromSession.toArray(new ActivityEvent[0]);
            if (array != null) {
                mo358get.addEvents((ActivityEvent[]) array);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (Exception e) {
            this.log.error("StepsToDistanceAlgorithmAdapter", "Failed to add activity events", e);
        }
    }
}
