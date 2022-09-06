package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/Coordinate;", "", "latitudeInDegrees", "", "longitudeInDegrees", "accuracyInMeters", "(DDD)V", "getAccuracyInMeters", "()D", "getLatitudeInDegrees", "getLongitudeInDegrees", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Coordinate {
    private final double accuracyInMeters;
    private final double latitudeInDegrees;
    private final double longitudeInDegrees;

    public Coordinate(double d, double d2, double d3) {
        this.latitudeInDegrees = d;
        this.longitudeInDegrees = d2;
        this.accuracyInMeters = d3;
    }

    public static /* synthetic */ Coordinate copy$default(Coordinate coordinate, double d, double d2, double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = coordinate.latitudeInDegrees;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = coordinate.longitudeInDegrees;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = coordinate.accuracyInMeters;
        }
        return coordinate.copy(d4, d5, d3);
    }

    public final double component1() {
        return this.latitudeInDegrees;
    }

    public final double component2() {
        return this.longitudeInDegrees;
    }

    public final double component3() {
        return this.accuracyInMeters;
    }

    @NotNull
    public final Coordinate copy(double d, double d2, double d3) {
        return new Coordinate(d, d2, d3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Coordinate)) {
                return false;
            }
            Coordinate coordinate = (Coordinate) obj;
            return Double.compare(this.latitudeInDegrees, coordinate.latitudeInDegrees) == 0 && Double.compare(this.longitudeInDegrees, coordinate.longitudeInDegrees) == 0 && Double.compare(this.accuracyInMeters, coordinate.accuracyInMeters) == 0;
        }
        return true;
    }

    public final double getAccuracyInMeters() {
        return this.accuracyInMeters;
    }

    public final double getLatitudeInDegrees() {
        return this.latitudeInDegrees;
    }

    public final double getLongitudeInDegrees() {
        return this.longitudeInDegrees;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitudeInDegrees);
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitudeInDegrees);
        long doubleToLongBits3 = Double.doubleToLongBits(this.accuracyInMeters);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Coordinate(latitudeInDegrees=");
        outline107.append(this.latitudeInDegrees);
        outline107.append(", longitudeInDegrees=");
        outline107.append(this.longitudeInDegrees);
        outline107.append(", accuracyInMeters=");
        return GeneratedOutlineSupport1.outline84(outline107, this.accuracyInMeters, ")");
    }
}
