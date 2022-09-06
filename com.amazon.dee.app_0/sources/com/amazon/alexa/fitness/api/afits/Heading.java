package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/Heading;", "", "directionInDegrees", "", "accuracyInDegrees", "(DD)V", "getAccuracyInDegrees", "()D", "getDirectionInDegrees", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Heading {
    private final double accuracyInDegrees;
    private final double directionInDegrees;

    public Heading(double d, double d2) {
        this.directionInDegrees = d;
        this.accuracyInDegrees = d2;
    }

    public static /* synthetic */ Heading copy$default(Heading heading, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = heading.directionInDegrees;
        }
        if ((i & 2) != 0) {
            d2 = heading.accuracyInDegrees;
        }
        return heading.copy(d, d2);
    }

    public final double component1() {
        return this.directionInDegrees;
    }

    public final double component2() {
        return this.accuracyInDegrees;
    }

    @NotNull
    public final Heading copy(double d, double d2) {
        return new Heading(d, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Heading)) {
                return false;
            }
            Heading heading = (Heading) obj;
            return Double.compare(this.directionInDegrees, heading.directionInDegrees) == 0 && Double.compare(this.accuracyInDegrees, heading.accuracyInDegrees) == 0;
        }
        return true;
    }

    public final double getAccuracyInDegrees() {
        return this.accuracyInDegrees;
    }

    public final double getDirectionInDegrees() {
        return this.directionInDegrees;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.directionInDegrees);
        long doubleToLongBits2 = Double.doubleToLongBits(this.accuracyInDegrees);
        return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Heading(directionInDegrees=");
        outline107.append(this.directionInDegrees);
        outline107.append(", accuracyInDegrees=");
        return GeneratedOutlineSupport1.outline84(outline107, this.accuracyInDegrees, ")");
    }
}
