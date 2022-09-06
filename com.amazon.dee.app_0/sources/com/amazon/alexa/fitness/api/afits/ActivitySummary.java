package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJV\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0006HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013¨\u0006&"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/ActivitySummary;", "", "distanceInMeters", "", "speedInMetersPerSecond", "stepCount", "", "paceInMinutesPerKm", "cadenceInCountsPerMinute", "durationInMs", "caloriesInKcal", "(DDIDDILjava/lang/Double;)V", "getCadenceInCountsPerMinute", "()D", "getCaloriesInKcal", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getDistanceInMeters", "getDurationInMs", "()I", "getPaceInMinutesPerKm", "getSpeedInMetersPerSecond", "getStepCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(DDIDDILjava/lang/Double;)Lcom/amazon/alexa/fitness/api/afits/ActivitySummary;", "equals", "", "other", "hashCode", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ActivitySummary {
    private final double cadenceInCountsPerMinute;
    @Nullable
    private final Double caloriesInKcal;
    private final double distanceInMeters;
    private final int durationInMs;
    private final double paceInMinutesPerKm;
    private final double speedInMetersPerSecond;
    private final int stepCount;

    public ActivitySummary(double d, double d2, int i, double d3, double d4, int i2, @Nullable Double d5) {
        this.distanceInMeters = d;
        this.speedInMetersPerSecond = d2;
        this.stepCount = i;
        this.paceInMinutesPerKm = d3;
        this.cadenceInCountsPerMinute = d4;
        this.durationInMs = i2;
        this.caloriesInKcal = d5;
    }

    public final double component1() {
        return this.distanceInMeters;
    }

    public final double component2() {
        return this.speedInMetersPerSecond;
    }

    public final int component3() {
        return this.stepCount;
    }

    public final double component4() {
        return this.paceInMinutesPerKm;
    }

    public final double component5() {
        return this.cadenceInCountsPerMinute;
    }

    public final int component6() {
        return this.durationInMs;
    }

    @Nullable
    public final Double component7() {
        return this.caloriesInKcal;
    }

    @NotNull
    public final ActivitySummary copy(double d, double d2, int i, double d3, double d4, int i2, @Nullable Double d5) {
        return new ActivitySummary(d, d2, i, d3, d4, i2, d5);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ActivitySummary)) {
                return false;
            }
            ActivitySummary activitySummary = (ActivitySummary) obj;
            return Double.compare(this.distanceInMeters, activitySummary.distanceInMeters) == 0 && Double.compare(this.speedInMetersPerSecond, activitySummary.speedInMetersPerSecond) == 0 && this.stepCount == activitySummary.stepCount && Double.compare(this.paceInMinutesPerKm, activitySummary.paceInMinutesPerKm) == 0 && Double.compare(this.cadenceInCountsPerMinute, activitySummary.cadenceInCountsPerMinute) == 0 && this.durationInMs == activitySummary.durationInMs && Intrinsics.areEqual((Object) this.caloriesInKcal, (Object) activitySummary.caloriesInKcal);
        }
        return true;
    }

    public final double getCadenceInCountsPerMinute() {
        return this.cadenceInCountsPerMinute;
    }

    @Nullable
    public final Double getCaloriesInKcal() {
        return this.caloriesInKcal;
    }

    public final double getDistanceInMeters() {
        return this.distanceInMeters;
    }

    public final int getDurationInMs() {
        return this.durationInMs;
    }

    public final double getPaceInMinutesPerKm() {
        return this.paceInMinutesPerKm;
    }

    public final double getSpeedInMetersPerSecond() {
        return this.speedInMetersPerSecond;
    }

    public final int getStepCount() {
        return this.stepCount;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.distanceInMeters);
        long doubleToLongBits2 = Double.doubleToLongBits(this.speedInMetersPerSecond);
        long doubleToLongBits3 = Double.doubleToLongBits(this.paceInMinutesPerKm);
        long doubleToLongBits4 = Double.doubleToLongBits(this.cadenceInCountsPerMinute);
        int i = ((((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + this.stepCount) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) ((doubleToLongBits4 >>> 32) ^ doubleToLongBits4))) * 31) + this.durationInMs) * 31;
        Double d = this.caloriesInKcal;
        return i + (d != null ? d.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ActivitySummary(distanceInMeters=");
        outline107.append(this.distanceInMeters);
        outline107.append(", speedInMetersPerSecond=");
        outline107.append(this.speedInMetersPerSecond);
        outline107.append(", stepCount=");
        outline107.append(this.stepCount);
        outline107.append(", paceInMinutesPerKm=");
        outline107.append(this.paceInMinutesPerKm);
        outline107.append(", cadenceInCountsPerMinute=");
        outline107.append(this.cadenceInCountsPerMinute);
        outline107.append(", durationInMs=");
        outline107.append(this.durationInMs);
        outline107.append(", caloriesInKcal=");
        outline107.append(this.caloriesInKcal);
        outline107.append(")");
        return outline107.toString();
    }
}
