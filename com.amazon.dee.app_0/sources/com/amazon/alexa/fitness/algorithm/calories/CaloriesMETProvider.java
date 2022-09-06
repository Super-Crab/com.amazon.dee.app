package com.amazon.alexa.fitness.algorithm.calories;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CaloriesMETProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesMETProvider;", "", "computeMETValue", "", "workoutStatistics", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesWorkoutStatistics;", "(Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesWorkoutStatistics;)Ljava/lang/Double;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface CaloriesMETProvider {
    @Nullable
    Double computeMETValue(@NotNull CaloriesWorkoutStatistics caloriesWorkoutStatistics);
}
