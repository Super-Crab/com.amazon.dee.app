package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.magiear.handsfree.util.Common;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DistanceDeltaCalculator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/DistanceDelta;", "", "value", "", "unit", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", Common.EXTRA_CONFIDENCE, "(DLcom/amazon/alexa/fitness/api/fitnessSdk/Units;D)V", "getConfidence", "()D", "getUnit", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DistanceDelta {
    private final double confidence;
    @NotNull
    private final Units unit;
    private final double value;

    public DistanceDelta(double d, @NotNull Units unit, double d2) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        this.value = d;
        this.unit = unit;
        this.confidence = d2;
    }

    public static /* synthetic */ DistanceDelta copy$default(DistanceDelta distanceDelta, double d, Units units, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = distanceDelta.value;
        }
        double d3 = d;
        if ((i & 2) != 0) {
            units = distanceDelta.unit;
        }
        Units units2 = units;
        if ((i & 4) != 0) {
            d2 = distanceDelta.confidence;
        }
        return distanceDelta.copy(d3, units2, d2);
    }

    public final double component1() {
        return this.value;
    }

    @NotNull
    public final Units component2() {
        return this.unit;
    }

    public final double component3() {
        return this.confidence;
    }

    @NotNull
    public final DistanceDelta copy(double d, @NotNull Units unit, double d2) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return new DistanceDelta(d, unit, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DistanceDelta)) {
                return false;
            }
            DistanceDelta distanceDelta = (DistanceDelta) obj;
            return Double.compare(this.value, distanceDelta.value) == 0 && Intrinsics.areEqual(this.unit, distanceDelta.unit) && Double.compare(this.confidence, distanceDelta.confidence) == 0;
        }
        return true;
    }

    public final double getConfidence() {
        return this.confidence;
    }

    @NotNull
    public final Units getUnit() {
        return this.unit;
    }

    public final double getValue() {
        return this.value;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.value);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31;
        Units units = this.unit;
        int hashCode = units != null ? units.hashCode() : 0;
        long doubleToLongBits2 = Double.doubleToLongBits(this.confidence);
        return ((i + hashCode) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DistanceDelta(value=");
        outline107.append(this.value);
        outline107.append(", unit=");
        outline107.append(this.unit);
        outline107.append(", confidence=");
        return GeneratedOutlineSupport1.outline84(outline107, this.confidence, ")");
    }
}
