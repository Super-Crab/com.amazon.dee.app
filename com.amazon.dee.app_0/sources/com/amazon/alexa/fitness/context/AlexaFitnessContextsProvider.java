package com.amazon.alexa.fitness.context;

import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.model.biometric.ActivitySummary;
import com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImplKt;
import com.amazon.alexa.fitness.time.ISO8601;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaFitnessContextProvider.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0016J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010!\u001a\u00020\u001f2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0018\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\tH\u0016J\u0010\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/alexa/fitness/context/AlexaFitnessContextsProvider;", "Lcom/amazon/alexa/api/AlexaContextsProvider;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "fitnessSessionOrchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;Lcom/amazon/alexa/fitness/logs/ILog;)V", "fitnessMetrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "buildAlexaHeader", "Lcom/amazon/alexa/api/AlexaHeader;", "kotlin.jvm.PlatformType", "buildAlexaPayload", "Lcom/amazon/alexa/api/AlexaPayload;", "createContextPayloadJson", "", "createStepBasedMetricWithCalories", "Lcom/amazon/alexa/fitness/context/MetricWithCalories;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "activitySummary", "Lcom/amazon/alexa/fitness/model/biometric/ActivitySummary;", "calories", "", "getAlexaContexts", "", "Lcom/amazon/alexa/api/AlexaContext;", "onMetricsUpdated", "", "metrics", "onSessionChanged", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "onSessionEnded", "endedSession", "finalMetrics", "sensorAvailabilityChanged", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AlexaFitnessContextsProvider implements AlexaContextsProvider, FitnessSessionOrchestratorDelegate {
    private FitnessMetrics fitnessMetrics;
    private final FitnessSessionOrchestrator fitnessSessionOrchestrator;
    private final ILog log;
    private com.amazon.alexa.fitness.api.fitnessSdk.Session session;

    @Inject
    public AlexaFitnessContextsProvider(@NotNull FitnessSessionOrchestrator fitnessSessionOrchestrator, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(fitnessSessionOrchestrator, "fitnessSessionOrchestrator");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.fitnessSessionOrchestrator = fitnessSessionOrchestrator;
        this.log = log;
        this.fitnessSessionOrchestrator.registerDelegate(new WeakReference<>(this));
    }

    private final AlexaHeader buildAlexaHeader() {
        AlexaHeader.Builder builder = AlexaHeader.builder();
        builder.setNamespace("Alexa.Health.Fitness");
        builder.setName("FitnessState");
        return builder.build();
    }

    private final AlexaPayload buildAlexaPayload() {
        return new AlexaPayload(createContextPayloadJson());
    }

    private final String createContextPayloadJson() {
        Session session;
        List listOfNotNull;
        com.amazon.alexa.fitness.api.fitnessSdk.Session session2 = this.session;
        MetricWithCalories metricWithCalories = null;
        if (session2 != null) {
            UUID sessionId = session2.getConfiguration().getSessionId();
            String serializeDateTimeInLocalTimeZone$default = ISO8601.Companion.serializeDateTimeInLocalTimeZone$default(ISO8601.Companion, session2.getCreatedAt(), null, false, 6, null);
            DateTime endTime = session2.getEndTime();
            session = new Session(sessionId, serializeDateTimeInLocalTimeZone$default, endTime != null ? ISO8601.Companion.serializeDateTimeInLocalTimeZone$default(ISO8601.Companion, endTime, null, false, 6, null) : null);
        } else {
            session = null;
        }
        com.amazon.alexa.fitness.api.fitnessSdk.Session session3 = this.session;
        int processingDuration$default = session3 != null ? SessionDataModelsKt.processingDuration$default(session3, null, 1, null) : 0;
        FitnessMetrics fitnessMetrics = this.fitnessMetrics;
        if (fitnessMetrics != null && (fitnessMetrics instanceof FitnessMetrics.StepBased)) {
            ActivitySummary activitySummary = FitnessSessionOrchestratorImplKt.toActivitySummary((FitnessMetrics.StepBased) fitnessMetrics, processingDuration$default);
            metricWithCalories = createStepBasedMetricWithCalories(ActivityType.RUNNING, activitySummary, activitySummary.getCalories());
        }
        listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(metricWithCalories);
        return GsonUtilsKt.toJson(new ContextPayload(new FitnessData(session, listOfNotNull)));
    }

    private final MetricWithCalories createStepBasedMetricWithCalories(ActivityType activityType, ActivitySummary activitySummary, int i) {
        String stepBasedActivityType;
        Steps steps = new Steps(activitySummary.getTotalSteps());
        Cadence cadence = new Cadence(activitySummary.getCadence());
        Distance distance = new Distance(activitySummary.getDistanceInFeet());
        Speed speed = new Speed(activitySummary.getSpeedInMph());
        stepBasedActivityType = AlexaFitnessContextProviderKt.toStepBasedActivityType(activityType);
        return new MetricWithCalories(steps, cadence, distance, speed, stepBasedActivityType, new Duration(activitySummary.getTimeInSeconds()), new Calories(i));
    }

    @Override // com.amazon.alexa.api.AlexaContextsProvider
    @NotNull
    public Set<AlexaContext> getAlexaContexts() {
        Set<AlexaContext> mutableSetOf;
        AlexaContext alexaContext = new AlexaContext(buildAlexaHeader(), buildAlexaPayload());
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getAlexaContext() called with payload: ");
        AlexaPayload alexaPayload = alexaContext.getAlexaPayload();
        Intrinsics.checkExpressionValueIsNotNull(alexaPayload, "it.alexaPayload");
        outline107.append(alexaPayload.getPayload());
        ILog.DefaultImpls.debug$default(iLog, "AlexaFitnessContextProvider", outline107.toString(), null, 4, null);
        mutableSetOf = SetsKt__SetsKt.mutableSetOf(alexaContext);
        return mutableSetOf;
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics) {
        this.fitnessMetrics = fitnessMetrics;
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionChanged(@Nullable com.amazon.alexa.fitness.api.fitnessSdk.Session session, @Nullable FitnessSessionOrchestrator.CommandProcessingError commandProcessingError) {
        this.session = session;
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionEnded(@NotNull com.amazon.alexa.fitness.api.fitnessSdk.Session endedSession, @NotNull FitnessMetrics finalMetrics) {
        Intrinsics.checkParameterIsNotNull(endedSession, "endedSession");
        Intrinsics.checkParameterIsNotNull(finalMetrics, "finalMetrics");
        this.session = endedSession;
        this.fitnessMetrics = finalMetrics;
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void sensorAvailabilityChanged(@NotNull SensorAvailability availability) {
        Intrinsics.checkParameterIsNotNull(availability, "availability");
    }
}
