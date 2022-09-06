package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.WorkoutType;
import com.amazon.device.messaging.ADMRegistrationConstants;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAlgorithm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001e\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH&¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmsManager;", "", "deregisterAllAlgorithms", "", "workoutType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/WorkoutType;", ADMRegistrationConstants.METHOD_REGISTER, "algorithmProviders", "", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmProvider;", "sessionEnded", "start", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "stateDidChange", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface FitnessAlgorithmsManager {
    void deregisterAllAlgorithms(@NotNull WorkoutType workoutType);

    void register(@NotNull List<FitnessAlgorithmProvider> list, @NotNull WorkoutType workoutType);

    void sessionEnded();

    void start(@NotNull Session session);

    void stateDidChange(@Nullable Session session);
}
