package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithm;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmUtilKt;
import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2;
import com.amazon.alexa.fitness.algorithms.ActivityEvent;
import com.amazon.alexa.fitness.algorithms.ActivityType;
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
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CaloriesAlgorithmAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\"\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\"\u001a\u00020#2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010%\u001a\u00020\u0013H\u0016J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010'\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmAdapter;", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithm;", "caloriesAlgorithm", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmV2;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmV2;Lcom/amazon/alexa/fitness/logs/ILog;)V", "lastActivityType", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "lastKnownSampleTime", "", "Ljava/lang/Long;", "sampleObserverToken", "Lcom/amazon/alexa/fitness/sdk/sample/ObserverToken;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "sessionConfiguration", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "addEvent", "", "event", "Lcom/amazon/alexa/fitness/algorithms/ActivityEvent;", "attemptRecovery", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "generateDerivedSamples", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "calories", "", "handleStateChange", "previousState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "currentState", "isValidConfiguration", "", "sessionChangedState", "sessionEnded", "setup", "setupSampleSubscription", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public class CaloriesAlgorithmAdapter implements FitnessAlgorithm {
    private final CaloriesAlgorithmV2 caloriesAlgorithm;
    private ActivityType lastActivityType;
    private Long lastKnownSampleTime;
    private final ILog log;
    private ObserverToken sampleObserverToken;
    private SampleStore sampleStore;
    private SessionConfiguration sessionConfiguration;

    @Inject
    public CaloriesAlgorithmAdapter(@NotNull CaloriesAlgorithmV2 caloriesAlgorithm, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(caloriesAlgorithm, "caloriesAlgorithm");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.caloriesAlgorithm = caloriesAlgorithm;
        this.log = log;
        this.caloriesAlgorithm.setOnTotalCaloriesChangedListener(new CaloriesAlgorithmV2.OnTotalCaloriesChangedListener() { // from class: com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmAdapter.1
            @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2.OnTotalCaloriesChangedListener
            public void onTotalCaloriesChanged(@NotNull CaloriesAlgorithmV2 algorithm) {
                Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
                SessionConfiguration sessionConfiguration = CaloriesAlgorithmAdapter.this.sessionConfiguration;
                if (sessionConfiguration != null) {
                    CaloriesAlgorithmAdapter.this.generateDerivedSamples(sessionConfiguration.getSessionId(), algorithm.getTotalCaloriesBurned());
                }
            }
        });
    }

    private final void addEvent(ActivityEvent activityEvent) {
        try {
            this.caloriesAlgorithm.addActivityEvent(activityEvent);
        } catch (Exception e) {
            this.log.error("CaloriesAlgorithmAdapter", "Failed to add activity Event", e);
        }
    }

    private final void attemptRecovery(Session session, SampleStore sampleStore) {
        List<ActivityEvent> activityEventsFromSession = FitnessAlgorithmUtilKt.getActivityEventsFromSession(this, session, sampleStore);
        UserProfile userProfile = session.getConfiguration().getUserProfile();
        if (!(!activityEventsFromSession.isEmpty()) || userProfile == null) {
            return;
        }
        this.lastKnownSampleTime = Long.valueOf(((ActivityEvent) CollectionsKt.last((List<? extends Object>) activityEventsFromSession)).timestamp);
        this.caloriesAlgorithm.start(userProfile);
        try {
            this.caloriesAlgorithm.addActivityEvents(activityEventsFromSession);
        } catch (Exception e) {
            this.log.error("CaloriesAlgorithmAdapter", "Failed to add activity events", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void generateDerivedSamples(UUID uuid, double d) {
        SampleStore sampleStore = this.sampleStore;
        if (sampleStore != null) {
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "CaloriesAlgorithmAdapter", "updating calories measurement: calories -> " + d, null, 4, null);
            sampleStore.store(new Sample.MeasurementSample(uuid, CaloriesAlgorithmAdapterKt.CaloriesAlgorithmId, DateTime.Companion.now().getEpochMilli(), SampleType.ActiveCaloriesBurned, new Measurement.Discrete(d), Units.KiloCalories, Aggregation.PointInTime));
        }
    }

    private final void handleStateChange(FitnessSessionState fitnessSessionState, FitnessSessionState fitnessSessionState2, Session session) {
        ILog iLog = this.log;
        ILog.DefaultImpls.info$default(iLog, "CaloriesAlgorithmAdapter", "handling state change from " + fitnessSessionState + " to " + fitnessSessionState2, null, 4, null);
        if (FitnessAlgorithmUtilKt.starting(fitnessSessionState, fitnessSessionState2)) {
            UserProfile userProfile = session.getConfiguration().getUserProfile();
            if (userProfile == null) {
                return;
            }
            this.caloriesAlgorithm.start(userProfile);
        } else if (!FitnessAlgorithmUtilKt.started(fitnessSessionState, fitnessSessionState2) && !FitnessAlgorithmUtilKt.recovered(fitnessSessionState, fitnessSessionState2)) {
            if (!FitnessAlgorithmUtilKt.resumed(fitnessSessionState, fitnessSessionState2) || this.sampleObserverToken != null) {
                return;
            }
            setupSampleSubscription();
        } else {
            setupSampleSubscription();
        }
    }

    private final boolean isValidConfiguration(SessionConfiguration sessionConfiguration) {
        UserProfile userProfile;
        Double weightInKilograms;
        return (sessionConfiguration == null || (userProfile = sessionConfiguration.getUserProfile()) == null || (weightInKilograms = userProfile.getWeightInKilograms()) == null || weightInKilograms.doubleValue() == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) ? false : true;
    }

    private final void setupSampleSubscription() {
        SessionConfiguration sessionConfiguration = this.sessionConfiguration;
        if (sessionConfiguration != null) {
            SampleStore sampleStore = this.sampleStore;
            this.sampleObserverToken = sampleStore != null ? sampleStore.queryEchoBudSamplesSubscription(sessionConfiguration.getSessionId(), new CaloriesAlgorithmAdapter$setupSampleSubscription$$inlined$let$lambda$1(this)) : null;
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void sessionChangedState(@NotNull Session session) {
        SessionConfiguration sessionConfiguration;
        Intrinsics.checkParameterIsNotNull(session, "session");
        if (this.sampleStore == null || (sessionConfiguration = this.sessionConfiguration) == null || !isValidConfiguration(sessionConfiguration)) {
            return;
        }
        FitnessSessionState currentState = SessionDataModelsKt.currentState(session);
        FitnessSessionState previousState = SessionDataModelsKt.previousState(session);
        handleStateChange(previousState, currentState, session);
        ActivityEvent eventForStateChange = FitnessAlgorithmUtilKt.getEventForStateChange(previousState, currentState, DateTime.Companion.now().getEpochMilli(), this.lastActivityType);
        if (eventForStateChange == null) {
            return;
        }
        addEvent(eventForStateChange);
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void sessionEnded() {
        SampleStore sampleStore;
        ILog.DefaultImpls.info$default(this.log, "CaloriesAlgorithmAdapter", "removing sample subscription and stopping algorithm", null, 4, null);
        ObserverToken observerToken = this.sampleObserverToken;
        if (observerToken != null && (sampleStore = this.sampleStore) != null) {
            sampleStore.removeObserver(observerToken);
        }
        this.sampleObserverToken = null;
        if (this.sessionConfiguration != null) {
            addEvent(FitnessAlgorithmUtilKt.getEventFor$default(ActivityEvent.EventType.EVENT_TYPE_STOP, DateTime.Companion.now().getEpochMilli(), null, 4, null));
        }
        this.caloriesAlgorithm.stop();
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void setup(@NotNull SampleStore sampleStore, @NotNull Session session) {
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(session, "session");
        if (!isValidConfiguration(session.getConfiguration())) {
            ILog.DefaultImpls.info$default(this.log, "CaloriesAlgorithmAdapter", "weight not setup, not initializing", null, 4, null);
            return;
        }
        this.sessionConfiguration = session.getConfiguration();
        this.sampleStore = sampleStore;
        attemptRecovery(session, sampleStore);
    }
}
