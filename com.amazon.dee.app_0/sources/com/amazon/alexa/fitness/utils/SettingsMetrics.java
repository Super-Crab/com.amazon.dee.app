package com.amazon.alexa.fitness.utils;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/utils/SettingsMetrics;", "", "()V", "CancelDeleteAllWorkouts", "Lcom/amazon/alexa/fitness/utils/SettingsMetricsComponent;", "getCancelDeleteAllWorkouts", "()Lcom/amazon/alexa/fitness/utils/SettingsMetricsComponent;", "DeleteAllWorkouts", "getDeleteAllWorkouts", "WorkoutProfile", "getWorkoutProfile", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SettingsMetrics {
    public static final SettingsMetrics INSTANCE = new SettingsMetrics();
    @NotNull
    private static final SettingsMetricsComponent CancelDeleteAllWorkouts = new SettingsMetricsComponent("cancelDeleteAllWorkouts");
    @NotNull
    private static final SettingsMetricsComponent DeleteAllWorkouts = new SettingsMetricsComponent("deleteAllWorkouts");
    @NotNull
    private static final SettingsMetricsComponent WorkoutProfile = new SettingsMetricsComponent("workoutProfile");

    private SettingsMetrics() {
    }

    @NotNull
    public final SettingsMetricsComponent getCancelDeleteAllWorkouts() {
        return CancelDeleteAllWorkouts;
    }

    @NotNull
    public final SettingsMetricsComponent getDeleteAllWorkouts() {
        return DeleteAllWorkouts;
    }

    @NotNull
    public final SettingsMetricsComponent getWorkoutProfile() {
        return WorkoutProfile;
    }
}
