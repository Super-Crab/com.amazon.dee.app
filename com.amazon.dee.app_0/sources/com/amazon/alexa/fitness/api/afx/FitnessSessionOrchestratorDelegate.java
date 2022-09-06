package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionOrchestrator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u001e\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "", "onMetricsUpdated", "", "metrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "onSessionChanged", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "onSessionEnded", "endedSession", "finalMetrics", "sensorAvailabilityChanged", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface FitnessSessionOrchestratorDelegate {

    /* compiled from: FitnessSessionOrchestrator.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void onSessionChanged$default(FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate, Session session, FitnessSessionOrchestrator.CommandProcessingError commandProcessingError, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    commandProcessingError = null;
                }
                fitnessSessionOrchestratorDelegate.onSessionChanged(session, commandProcessingError);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onSessionChanged");
        }
    }

    void onMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics);

    void onSessionChanged(@Nullable Session session, @Nullable FitnessSessionOrchestrator.CommandProcessingError commandProcessingError);

    void onSessionEnded(@NotNull Session session, @NotNull FitnessMetrics fitnessMetrics);

    void sensorAvailabilityChanged(@NotNull SensorAvailability sensorAvailability);
}
