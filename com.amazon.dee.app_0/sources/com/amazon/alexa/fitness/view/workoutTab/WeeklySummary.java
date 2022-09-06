package com.amazon.alexa.fitness.view.workoutTab;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u001d\u0018\u00002\u00020\u0001BS\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007¢\u0006\u0002\u0010\fR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0012\"\u0004\b!\u0010\u0014R\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014¨\u0006$"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/WeeklySummary;", "Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutItem;", "activeDays", "", "", "numOfWorkouts", "totalActiveWorkoutTimeInSeconds", "", "totalDistanceInMeters", "totalCalories", "averagePace", "totalSteps", "(Ljava/util/Set;IDDLjava/lang/Double;DD)V", "getActiveDays", "()Ljava/util/Set;", "setActiveDays", "(Ljava/util/Set;)V", "getAveragePace", "()D", "setAveragePace", "(D)V", "getNumOfWorkouts", "()I", "setNumOfWorkouts", "(I)V", "getTotalActiveWorkoutTimeInSeconds", "setTotalActiveWorkoutTimeInSeconds", "getTotalCalories", "()Ljava/lang/Double;", "setTotalCalories", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getTotalDistanceInMeters", "setTotalDistanceInMeters", "getTotalSteps", "setTotalSteps", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WeeklySummary extends WorkoutItem {
    @NotNull
    private Set<Integer> activeDays;
    private double averagePace;
    private int numOfWorkouts;
    private double totalActiveWorkoutTimeInSeconds;
    @Nullable
    private Double totalCalories;
    private double totalDistanceInMeters;
    private double totalSteps;

    public WeeklySummary() {
        this(null, 0, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, null, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 127, null);
    }

    public /* synthetic */ WeeklySummary(Set set, int i, double d, double d2, Double d3, double d4, double d5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new LinkedHashSet() : set, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? 0.0d : d, (i2 & 8) != 0 ? 0.0d : d2, (i2 & 16) != 0 ? null : d3, (i2 & 32) != 0 ? 0.0d : d4, (i2 & 64) == 0 ? d5 : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    @NotNull
    public final Set<Integer> getActiveDays() {
        return this.activeDays;
    }

    public final double getAveragePace() {
        return this.averagePace;
    }

    public final int getNumOfWorkouts() {
        return this.numOfWorkouts;
    }

    public final double getTotalActiveWorkoutTimeInSeconds() {
        return this.totalActiveWorkoutTimeInSeconds;
    }

    @Nullable
    public final Double getTotalCalories() {
        return this.totalCalories;
    }

    public final double getTotalDistanceInMeters() {
        return this.totalDistanceInMeters;
    }

    public final double getTotalSteps() {
        return this.totalSteps;
    }

    public final void setActiveDays(@NotNull Set<Integer> set) {
        Intrinsics.checkParameterIsNotNull(set, "<set-?>");
        this.activeDays = set;
    }

    public final void setAveragePace(double d) {
        this.averagePace = d;
    }

    public final void setNumOfWorkouts(int i) {
        this.numOfWorkouts = i;
    }

    public final void setTotalActiveWorkoutTimeInSeconds(double d) {
        this.totalActiveWorkoutTimeInSeconds = d;
    }

    public final void setTotalCalories(@Nullable Double d) {
        this.totalCalories = d;
    }

    public final void setTotalDistanceInMeters(double d) {
        this.totalDistanceInMeters = d;
    }

    public final void setTotalSteps(double d) {
        this.totalSteps = d;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WeeklySummary(@NotNull Set<Integer> activeDays, int i, double d, double d2, @Nullable Double d3, double d4, double d5) {
        super(FitnessListType.WEEKLY_VIEW);
        Intrinsics.checkParameterIsNotNull(activeDays, "activeDays");
        this.activeDays = activeDays;
        this.numOfWorkouts = i;
        this.totalActiveWorkoutTimeInSeconds = d;
        this.totalDistanceInMeters = d2;
        this.totalCalories = d3;
        this.averagePace = d4;
        this.totalSteps = d5;
    }
}
