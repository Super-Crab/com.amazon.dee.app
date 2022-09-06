package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/Speed;", "", "speedInMetersPerSecond", "", "accuracyInMetersPerSecond", "(DD)V", "getAccuracyInMetersPerSecond", "()D", "getSpeedInMetersPerSecond", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Speed {
    private final double accuracyInMetersPerSecond;
    private final double speedInMetersPerSecond;

    public Speed(double d, double d2) {
        this.speedInMetersPerSecond = d;
        this.accuracyInMetersPerSecond = d2;
    }

    public static /* synthetic */ Speed copy$default(Speed speed, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = speed.speedInMetersPerSecond;
        }
        if ((i & 2) != 0) {
            d2 = speed.accuracyInMetersPerSecond;
        }
        return speed.copy(d, d2);
    }

    public final double component1() {
        return this.speedInMetersPerSecond;
    }

    public final double component2() {
        return this.accuracyInMetersPerSecond;
    }

    @NotNull
    public final Speed copy(double d, double d2) {
        return new Speed(d, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Speed)) {
                return false;
            }
            Speed speed = (Speed) obj;
            return Double.compare(this.speedInMetersPerSecond, speed.speedInMetersPerSecond) == 0 && Double.compare(this.accuracyInMetersPerSecond, speed.accuracyInMetersPerSecond) == 0;
        }
        return true;
    }

    public final double getAccuracyInMetersPerSecond() {
        return this.accuracyInMetersPerSecond;
    }

    public final double getSpeedInMetersPerSecond() {
        return this.speedInMetersPerSecond;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.speedInMetersPerSecond);
        long doubleToLongBits2 = Double.doubleToLongBits(this.accuracyInMetersPerSecond);
        return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Speed(speedInMetersPerSecond=");
        outline107.append(this.speedInMetersPerSecond);
        outline107.append(", accuracyInMetersPerSecond=");
        return GeneratedOutlineSupport1.outline84(outline107, this.accuracyInMetersPerSecond, ")");
    }
}
