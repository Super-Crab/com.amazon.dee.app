package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.fitness.metrics.Metrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ActivitySummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012¨\u0006)"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/ActivitySummary;", "Ljava/io/Serializable;", "timeInSeconds", "", "totalSteps", Metrics.DISTANCE_IN_FEET, "", "cadence", "speedInMph", "calories", "(IIDDDI)V", "getCadence", "()D", "setCadence", "(D)V", "getCalories", "()I", "setCalories", "(I)V", "getDistanceInFeet", "setDistanceInFeet", "getSpeedInMph", "setSpeedInMph", "getTimeInSeconds", "setTimeInSeconds", "getTotalSteps", "setTotalSteps", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ActivitySummary implements Serializable {
    private double cadence;
    private int calories;
    private double distanceInFeet;
    private double speedInMph;
    private int timeInSeconds;
    private int totalSteps;

    public ActivitySummary(int i, int i2, double d, double d2, double d3, int i3) {
        this.timeInSeconds = i;
        this.totalSteps = i2;
        this.distanceInFeet = d;
        this.cadence = d2;
        this.speedInMph = d3;
        this.calories = i3;
    }

    public final int component1() {
        return this.timeInSeconds;
    }

    public final int component2() {
        return this.totalSteps;
    }

    public final double component3() {
        return this.distanceInFeet;
    }

    public final double component4() {
        return this.cadence;
    }

    public final double component5() {
        return this.speedInMph;
    }

    public final int component6() {
        return this.calories;
    }

    @NotNull
    public final ActivitySummary copy(int i, int i2, double d, double d2, double d3, int i3) {
        return new ActivitySummary(i, i2, d, d2, d3, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ActivitySummary)) {
                return false;
            }
            ActivitySummary activitySummary = (ActivitySummary) obj;
            return this.timeInSeconds == activitySummary.timeInSeconds && this.totalSteps == activitySummary.totalSteps && Double.compare(this.distanceInFeet, activitySummary.distanceInFeet) == 0 && Double.compare(this.cadence, activitySummary.cadence) == 0 && Double.compare(this.speedInMph, activitySummary.speedInMph) == 0 && this.calories == activitySummary.calories;
        }
        return true;
    }

    public final double getCadence() {
        return this.cadence;
    }

    public final int getCalories() {
        return this.calories;
    }

    public final double getDistanceInFeet() {
        return this.distanceInFeet;
    }

    public final double getSpeedInMph() {
        return this.speedInMph;
    }

    public final int getTimeInSeconds() {
        return this.timeInSeconds;
    }

    public final int getTotalSteps() {
        return this.totalSteps;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.distanceInFeet);
        long doubleToLongBits2 = Double.doubleToLongBits(this.cadence);
        long doubleToLongBits3 = Double.doubleToLongBits(this.speedInMph);
        return (((((((((this.timeInSeconds * 31) + this.totalSteps) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + this.calories;
    }

    public final void setCadence(double d) {
        this.cadence = d;
    }

    public final void setCalories(int i) {
        this.calories = i;
    }

    public final void setDistanceInFeet(double d) {
        this.distanceInFeet = d;
    }

    public final void setSpeedInMph(double d) {
        this.speedInMph = d;
    }

    public final void setTimeInSeconds(int i) {
        this.timeInSeconds = i;
    }

    public final void setTotalSteps(int i) {
        this.totalSteps = i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ActivitySummary(timeInSeconds=");
        outline107.append(this.timeInSeconds);
        outline107.append(", totalSteps=");
        outline107.append(this.totalSteps);
        outline107.append(", distanceInFeet=");
        outline107.append(this.distanceInFeet);
        outline107.append(", cadence=");
        outline107.append(this.cadence);
        outline107.append(", speedInMph=");
        outline107.append(this.speedInMph);
        outline107.append(", calories=");
        return GeneratedOutlineSupport1.outline86(outline107, this.calories, ")");
    }

    public /* synthetic */ ActivitySummary(int i, int i2, double d, double d2, double d3, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0.0d : d, (i4 & 8) != 0 ? 0.0d : d2, (i4 & 16) != 0 ? 0.0d : d3, i3);
    }
}
