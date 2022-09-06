package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionTransition;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J*\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionManagerDelegate;", "", "sensorAvailabilityChanged", "", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "sensorId", "", "stateDidChange", "oldState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "transition", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;", "newState", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SessionManagerDelegate {
    void sensorAvailabilityChanged(@NotNull SensorAvailability sensorAvailability, @NotNull String str);

    void stateDidChange(@NotNull FitnessSessionState fitnessSessionState, @NotNull FitnessSessionTransition fitnessSessionTransition, @NotNull FitnessSessionState fitnessSessionState2, @Nullable SensorError sensorError);
}
