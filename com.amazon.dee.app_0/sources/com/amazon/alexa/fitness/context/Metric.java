package com.amazon.alexa.fitness.context;

import com.amazon.alexa.fitness.metrics.Metrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaFitnessContextProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003J\t\u0010 \u001a\u00020\rHÆ\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/amazon/alexa/fitness/context/Metric;", "", Metrics.STEPS, "Lcom/amazon/alexa/fitness/context/Steps;", "cadence", "Lcom/amazon/alexa/fitness/context/Cadence;", Metrics.DISTANCE_IN_FEET, "Lcom/amazon/alexa/fitness/context/Distance;", "speedInMph", "Lcom/amazon/alexa/fitness/context/Speed;", "activityType", "", "durationInSecs", "Lcom/amazon/alexa/fitness/context/Duration;", "(Lcom/amazon/alexa/fitness/context/Steps;Lcom/amazon/alexa/fitness/context/Cadence;Lcom/amazon/alexa/fitness/context/Distance;Lcom/amazon/alexa/fitness/context/Speed;Ljava/lang/String;Lcom/amazon/alexa/fitness/context/Duration;)V", "getActivityType", "()Ljava/lang/String;", "getCadence", "()Lcom/amazon/alexa/fitness/context/Cadence;", "getDistanceInFeet", "()Lcom/amazon/alexa/fitness/context/Distance;", "getDurationInSecs", "()Lcom/amazon/alexa/fitness/context/Duration;", "getSpeedInMph", "()Lcom/amazon/alexa/fitness/context/Speed;", "getSteps", "()Lcom/amazon/alexa/fitness/context/Steps;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Metric {
    @NotNull
    private final String activityType;
    @NotNull
    private final Cadence cadence;
    @NotNull
    private final Distance distanceInFeet;
    @NotNull
    private final Duration durationInSecs;
    @NotNull
    private final Speed speedInMph;
    @NotNull
    private final Steps steps;

    public Metric(@NotNull Steps steps, @NotNull Cadence cadence, @NotNull Distance distanceInFeet, @NotNull Speed speedInMph, @NotNull String activityType, @NotNull Duration durationInSecs) {
        Intrinsics.checkParameterIsNotNull(steps, "steps");
        Intrinsics.checkParameterIsNotNull(cadence, "cadence");
        Intrinsics.checkParameterIsNotNull(distanceInFeet, "distanceInFeet");
        Intrinsics.checkParameterIsNotNull(speedInMph, "speedInMph");
        Intrinsics.checkParameterIsNotNull(activityType, "activityType");
        Intrinsics.checkParameterIsNotNull(durationInSecs, "durationInSecs");
        this.steps = steps;
        this.cadence = cadence;
        this.distanceInFeet = distanceInFeet;
        this.speedInMph = speedInMph;
        this.activityType = activityType;
        this.durationInSecs = durationInSecs;
    }

    public static /* synthetic */ Metric copy$default(Metric metric, Steps steps, Cadence cadence, Distance distance, Speed speed, String str, Duration duration, int i, Object obj) {
        if ((i & 1) != 0) {
            steps = metric.steps;
        }
        if ((i & 2) != 0) {
            cadence = metric.cadence;
        }
        Cadence cadence2 = cadence;
        if ((i & 4) != 0) {
            distance = metric.distanceInFeet;
        }
        Distance distance2 = distance;
        if ((i & 8) != 0) {
            speed = metric.speedInMph;
        }
        Speed speed2 = speed;
        if ((i & 16) != 0) {
            str = metric.activityType;
        }
        String str2 = str;
        if ((i & 32) != 0) {
            duration = metric.durationInSecs;
        }
        return metric.copy(steps, cadence2, distance2, speed2, str2, duration);
    }

    @NotNull
    public final Steps component1() {
        return this.steps;
    }

    @NotNull
    public final Cadence component2() {
        return this.cadence;
    }

    @NotNull
    public final Distance component3() {
        return this.distanceInFeet;
    }

    @NotNull
    public final Speed component4() {
        return this.speedInMph;
    }

    @NotNull
    public final String component5() {
        return this.activityType;
    }

    @NotNull
    public final Duration component6() {
        return this.durationInSecs;
    }

    @NotNull
    public final Metric copy(@NotNull Steps steps, @NotNull Cadence cadence, @NotNull Distance distanceInFeet, @NotNull Speed speedInMph, @NotNull String activityType, @NotNull Duration durationInSecs) {
        Intrinsics.checkParameterIsNotNull(steps, "steps");
        Intrinsics.checkParameterIsNotNull(cadence, "cadence");
        Intrinsics.checkParameterIsNotNull(distanceInFeet, "distanceInFeet");
        Intrinsics.checkParameterIsNotNull(speedInMph, "speedInMph");
        Intrinsics.checkParameterIsNotNull(activityType, "activityType");
        Intrinsics.checkParameterIsNotNull(durationInSecs, "durationInSecs");
        return new Metric(steps, cadence, distanceInFeet, speedInMph, activityType, durationInSecs);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Metric)) {
                return false;
            }
            Metric metric = (Metric) obj;
            return Intrinsics.areEqual(this.steps, metric.steps) && Intrinsics.areEqual(this.cadence, metric.cadence) && Intrinsics.areEqual(this.distanceInFeet, metric.distanceInFeet) && Intrinsics.areEqual(this.speedInMph, metric.speedInMph) && Intrinsics.areEqual(this.activityType, metric.activityType) && Intrinsics.areEqual(this.durationInSecs, metric.durationInSecs);
        }
        return true;
    }

    @NotNull
    public final String getActivityType() {
        return this.activityType;
    }

    @NotNull
    public final Cadence getCadence() {
        return this.cadence;
    }

    @NotNull
    public final Distance getDistanceInFeet() {
        return this.distanceInFeet;
    }

    @NotNull
    public final Duration getDurationInSecs() {
        return this.durationInSecs;
    }

    @NotNull
    public final Speed getSpeedInMph() {
        return this.speedInMph;
    }

    @NotNull
    public final Steps getSteps() {
        return this.steps;
    }

    public int hashCode() {
        Steps steps = this.steps;
        int i = 0;
        int hashCode = (steps != null ? steps.hashCode() : 0) * 31;
        Cadence cadence = this.cadence;
        int hashCode2 = (hashCode + (cadence != null ? cadence.hashCode() : 0)) * 31;
        Distance distance = this.distanceInFeet;
        int hashCode3 = (hashCode2 + (distance != null ? distance.hashCode() : 0)) * 31;
        Speed speed = this.speedInMph;
        int hashCode4 = (hashCode3 + (speed != null ? speed.hashCode() : 0)) * 31;
        String str = this.activityType;
        int hashCode5 = (hashCode4 + (str != null ? str.hashCode() : 0)) * 31;
        Duration duration = this.durationInSecs;
        if (duration != null) {
            i = duration.hashCode();
        }
        return hashCode5 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Metric(steps=");
        outline107.append(this.steps);
        outline107.append(", cadence=");
        outline107.append(this.cadence);
        outline107.append(", distanceInFeet=");
        outline107.append(this.distanceInFeet);
        outline107.append(", speedInMph=");
        outline107.append(this.speedInMph);
        outline107.append(", activityType=");
        outline107.append(this.activityType);
        outline107.append(", durationInSecs=");
        outline107.append(this.durationInSecs);
        outline107.append(")");
        return outline107.toString();
    }
}
