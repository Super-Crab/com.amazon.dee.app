package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\""}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/AFITSActivitySummary;", "", "distanceInMeters", "", "speedInMetersPerSecond", "stepCount", "paceInMinutesPerKm", "cadenceInCountsPerMinute", "durationInMs", "caloriesInKcal", "(DDDDDDD)V", "getCadenceInCountsPerMinute", "()D", "getCaloriesInKcal", "getDistanceInMeters", "getDurationInMs", "getPaceInMinutesPerKm", "getSpeedInMetersPerSecond", "getStepCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AFITSActivitySummary {
    private final double cadenceInCountsPerMinute;
    private final double caloriesInKcal;
    private final double distanceInMeters;
    private final double durationInMs;
    private final double paceInMinutesPerKm;
    private final double speedInMetersPerSecond;
    private final double stepCount;

    public AFITSActivitySummary(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        this.distanceInMeters = d;
        this.speedInMetersPerSecond = d2;
        this.stepCount = d3;
        this.paceInMinutesPerKm = d4;
        this.cadenceInCountsPerMinute = d5;
        this.durationInMs = d6;
        this.caloriesInKcal = d7;
    }

    public final double component1() {
        return this.distanceInMeters;
    }

    public final double component2() {
        return this.speedInMetersPerSecond;
    }

    public final double component3() {
        return this.stepCount;
    }

    public final double component4() {
        return this.paceInMinutesPerKm;
    }

    public final double component5() {
        return this.cadenceInCountsPerMinute;
    }

    public final double component6() {
        return this.durationInMs;
    }

    public final double component7() {
        return this.caloriesInKcal;
    }

    @NotNull
    public final AFITSActivitySummary copy(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        return new AFITSActivitySummary(d, d2, d3, d4, d5, d6, d7);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AFITSActivitySummary)) {
                return false;
            }
            AFITSActivitySummary aFITSActivitySummary = (AFITSActivitySummary) obj;
            return Double.compare(this.distanceInMeters, aFITSActivitySummary.distanceInMeters) == 0 && Double.compare(this.speedInMetersPerSecond, aFITSActivitySummary.speedInMetersPerSecond) == 0 && Double.compare(this.stepCount, aFITSActivitySummary.stepCount) == 0 && Double.compare(this.paceInMinutesPerKm, aFITSActivitySummary.paceInMinutesPerKm) == 0 && Double.compare(this.cadenceInCountsPerMinute, aFITSActivitySummary.cadenceInCountsPerMinute) == 0 && Double.compare(this.durationInMs, aFITSActivitySummary.durationInMs) == 0 && Double.compare(this.caloriesInKcal, aFITSActivitySummary.caloriesInKcal) == 0;
        }
        return true;
    }

    public final double getCadenceInCountsPerMinute() {
        return this.cadenceInCountsPerMinute;
    }

    public final double getCaloriesInKcal() {
        return this.caloriesInKcal;
    }

    public final double getDistanceInMeters() {
        return this.distanceInMeters;
    }

    public final double getDurationInMs() {
        return this.durationInMs;
    }

    public final double getPaceInMinutesPerKm() {
        return this.paceInMinutesPerKm;
    }

    public final double getSpeedInMetersPerSecond() {
        return this.speedInMetersPerSecond;
    }

    public final double getStepCount() {
        return this.stepCount;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.distanceInMeters);
        long doubleToLongBits2 = Double.doubleToLongBits(this.speedInMetersPerSecond);
        long doubleToLongBits3 = Double.doubleToLongBits(this.stepCount);
        long doubleToLongBits4 = Double.doubleToLongBits(this.paceInMinutesPerKm);
        long doubleToLongBits5 = Double.doubleToLongBits(this.cadenceInCountsPerMinute);
        long doubleToLongBits6 = Double.doubleToLongBits(this.durationInMs);
        long doubleToLongBits7 = Double.doubleToLongBits(this.caloriesInKcal);
        return (((((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 31) + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)))) * 31) + ((int) ((doubleToLongBits7 >>> 32) ^ doubleToLongBits7));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AFITSActivitySummary(distanceInMeters=");
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
        return GeneratedOutlineSupport1.outline84(outline107, this.caloriesInKcal, ")");
    }
}
