package com.amazon.alexa.fitness.model.biometric;

import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.voice.tta.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WorkoutSummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00100\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010#J\u0010\u00101\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0084\u0001\u00103\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u00104J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00108\u001a\u00020\u0005HÖ\u0001J\t\u00109\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0015\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010!\u001a\u0004\b%\u0010\u001eR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b&\u0010\u0014R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0012\"\u0004\b(\u0010\u001c¨\u0006:"}, d2 = {"Lcom/amazon/alexa/fitness/model/biometric/WorkoutSummary;", "", "activityType", "", "calories", "", Metrics.DISTANCE_IN_FEET, "durationInSecs", "error", "processWorkoutRequestTimestamp", "", "speedInMph", "", Constants.IntentParameters.START_TIMESTAMP, Metrics.STEPS, "workoutState", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;)V", "getActivityType", "()Ljava/lang/String;", "getCalories", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDistanceInFeet", "getDurationInSecs", "setDurationInSecs", "(Ljava/lang/Integer;)V", "getError", "setError", "(Ljava/lang/String;)V", "getProcessWorkoutRequestTimestamp", "()Ljava/lang/Long;", "setProcessWorkoutRequestTimestamp", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getSpeedInMph", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getStartTimestamp", "getSteps", "getWorkoutState", "setWorkoutState", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;)Lcom/amazon/alexa/fitness/model/biometric/WorkoutSummary;", "equals", "", "other", "hashCode", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutSummary {
    @Nullable
    private final String activityType;
    @Nullable
    private final Integer calories;
    @Nullable
    private final Integer distanceInFeet;
    @Nullable
    private Integer durationInSecs;
    @Nullable
    private String error;
    @Nullable
    private Long processWorkoutRequestTimestamp;
    @Nullable
    private final Double speedInMph;
    @Nullable
    private final Long startTimestamp;
    @Nullable
    private final Integer steps;
    @NotNull
    private String workoutState;

    public WorkoutSummary(@Nullable String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str2, @Nullable Long l, @Nullable Double d, @Nullable Long l2, @Nullable Integer num4, @NotNull String workoutState) {
        Intrinsics.checkParameterIsNotNull(workoutState, "workoutState");
        this.activityType = str;
        this.calories = num;
        this.distanceInFeet = num2;
        this.durationInSecs = num3;
        this.error = str2;
        this.processWorkoutRequestTimestamp = l;
        this.speedInMph = d;
        this.startTimestamp = l2;
        this.steps = num4;
        this.workoutState = workoutState;
    }

    @Nullable
    public final String component1() {
        return this.activityType;
    }

    @NotNull
    public final String component10() {
        return this.workoutState;
    }

    @Nullable
    public final Integer component2() {
        return this.calories;
    }

    @Nullable
    public final Integer component3() {
        return this.distanceInFeet;
    }

    @Nullable
    public final Integer component4() {
        return this.durationInSecs;
    }

    @Nullable
    public final String component5() {
        return this.error;
    }

    @Nullable
    public final Long component6() {
        return this.processWorkoutRequestTimestamp;
    }

    @Nullable
    public final Double component7() {
        return this.speedInMph;
    }

    @Nullable
    public final Long component8() {
        return this.startTimestamp;
    }

    @Nullable
    public final Integer component9() {
        return this.steps;
    }

    @NotNull
    public final WorkoutSummary copy(@Nullable String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str2, @Nullable Long l, @Nullable Double d, @Nullable Long l2, @Nullable Integer num4, @NotNull String workoutState) {
        Intrinsics.checkParameterIsNotNull(workoutState, "workoutState");
        return new WorkoutSummary(str, num, num2, num3, str2, l, d, l2, num4, workoutState);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof WorkoutSummary)) {
                return false;
            }
            WorkoutSummary workoutSummary = (WorkoutSummary) obj;
            return Intrinsics.areEqual(this.activityType, workoutSummary.activityType) && Intrinsics.areEqual(this.calories, workoutSummary.calories) && Intrinsics.areEqual(this.distanceInFeet, workoutSummary.distanceInFeet) && Intrinsics.areEqual(this.durationInSecs, workoutSummary.durationInSecs) && Intrinsics.areEqual(this.error, workoutSummary.error) && Intrinsics.areEqual(this.processWorkoutRequestTimestamp, workoutSummary.processWorkoutRequestTimestamp) && Intrinsics.areEqual((Object) this.speedInMph, (Object) workoutSummary.speedInMph) && Intrinsics.areEqual(this.startTimestamp, workoutSummary.startTimestamp) && Intrinsics.areEqual(this.steps, workoutSummary.steps) && Intrinsics.areEqual(this.workoutState, workoutSummary.workoutState);
        }
        return true;
    }

    @Nullable
    public final String getActivityType() {
        return this.activityType;
    }

    @Nullable
    public final Integer getCalories() {
        return this.calories;
    }

    @Nullable
    public final Integer getDistanceInFeet() {
        return this.distanceInFeet;
    }

    @Nullable
    public final Integer getDurationInSecs() {
        return this.durationInSecs;
    }

    @Nullable
    public final String getError() {
        return this.error;
    }

    @Nullable
    public final Long getProcessWorkoutRequestTimestamp() {
        return this.processWorkoutRequestTimestamp;
    }

    @Nullable
    public final Double getSpeedInMph() {
        return this.speedInMph;
    }

    @Nullable
    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    @Nullable
    public final Integer getSteps() {
        return this.steps;
    }

    @NotNull
    public final String getWorkoutState() {
        return this.workoutState;
    }

    public int hashCode() {
        String str = this.activityType;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.calories;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.distanceInFeet;
        int hashCode3 = (hashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.durationInSecs;
        int hashCode4 = (hashCode3 + (num3 != null ? num3.hashCode() : 0)) * 31;
        String str2 = this.error;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Long l = this.processWorkoutRequestTimestamp;
        int hashCode6 = (hashCode5 + (l != null ? l.hashCode() : 0)) * 31;
        Double d = this.speedInMph;
        int hashCode7 = (hashCode6 + (d != null ? d.hashCode() : 0)) * 31;
        Long l2 = this.startTimestamp;
        int hashCode8 = (hashCode7 + (l2 != null ? l2.hashCode() : 0)) * 31;
        Integer num4 = this.steps;
        int hashCode9 = (hashCode8 + (num4 != null ? num4.hashCode() : 0)) * 31;
        String str3 = this.workoutState;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode9 + i;
    }

    public final void setDurationInSecs(@Nullable Integer num) {
        this.durationInSecs = num;
    }

    public final void setError(@Nullable String str) {
        this.error = str;
    }

    public final void setProcessWorkoutRequestTimestamp(@Nullable Long l) {
        this.processWorkoutRequestTimestamp = l;
    }

    public final void setWorkoutState(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.workoutState = str;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WorkoutSummary(activityType=");
        outline107.append(this.activityType);
        outline107.append(", calories=");
        outline107.append(this.calories);
        outline107.append(", distanceInFeet=");
        outline107.append(this.distanceInFeet);
        outline107.append(", durationInSecs=");
        outline107.append(this.durationInSecs);
        outline107.append(", error=");
        outline107.append(this.error);
        outline107.append(", processWorkoutRequestTimestamp=");
        outline107.append(this.processWorkoutRequestTimestamp);
        outline107.append(", speedInMph=");
        outline107.append(this.speedInMph);
        outline107.append(", startTimestamp=");
        outline107.append(this.startTimestamp);
        outline107.append(", steps=");
        outline107.append(this.steps);
        outline107.append(", workoutState=");
        return GeneratedOutlineSupport1.outline91(outline107, this.workoutState, ")");
    }

    public /* synthetic */ WorkoutSummary(String str, Integer num, Integer num2, Integer num3, String str2, Long l, Double d, Long l2, Integer num4, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : l, (i & 64) != 0 ? null : d, (i & 128) != 0 ? null : l2, (i & 256) != 0 ? null : num4, str3);
    }
}
