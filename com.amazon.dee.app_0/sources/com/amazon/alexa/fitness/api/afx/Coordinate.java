package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ2\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/Coordinate;", "", "longitude", "", "latitude", "accuracy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)V", "getAccuracy", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLatitude", "getLongitude", "component1", "component2", "component3", "copy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)Lcom/amazon/alexa/fitness/api/afx/Coordinate;", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Coordinate {
    @Nullable
    private final Double accuracy;
    @Nullable
    private final Double latitude;
    @Nullable
    private final Double longitude;

    public Coordinate(@Nullable Double d, @Nullable Double d2, @Nullable Double d3) {
        this.longitude = d;
        this.latitude = d2;
        this.accuracy = d3;
    }

    public static /* synthetic */ Coordinate copy$default(Coordinate coordinate, Double d, Double d2, Double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = coordinate.longitude;
        }
        if ((i & 2) != 0) {
            d2 = coordinate.latitude;
        }
        if ((i & 4) != 0) {
            d3 = coordinate.accuracy;
        }
        return coordinate.copy(d, d2, d3);
    }

    @Nullable
    public final Double component1() {
        return this.longitude;
    }

    @Nullable
    public final Double component2() {
        return this.latitude;
    }

    @Nullable
    public final Double component3() {
        return this.accuracy;
    }

    @NotNull
    public final Coordinate copy(@Nullable Double d, @Nullable Double d2, @Nullable Double d3) {
        return new Coordinate(d, d2, d3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Coordinate)) {
                return false;
            }
            Coordinate coordinate = (Coordinate) obj;
            return Intrinsics.areEqual((Object) this.longitude, (Object) coordinate.longitude) && Intrinsics.areEqual((Object) this.latitude, (Object) coordinate.latitude) && Intrinsics.areEqual((Object) this.accuracy, (Object) coordinate.accuracy);
        }
        return true;
    }

    @Nullable
    public final Double getAccuracy() {
        return this.accuracy;
    }

    @Nullable
    public final Double getLatitude() {
        return this.latitude;
    }

    @Nullable
    public final Double getLongitude() {
        return this.longitude;
    }

    public int hashCode() {
        Double d = this.longitude;
        int i = 0;
        int hashCode = (d != null ? d.hashCode() : 0) * 31;
        Double d2 = this.latitude;
        int hashCode2 = (hashCode + (d2 != null ? d2.hashCode() : 0)) * 31;
        Double d3 = this.accuracy;
        if (d3 != null) {
            i = d3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Coordinate(longitude=");
        outline107.append(this.longitude);
        outline107.append(", latitude=");
        outline107.append(this.latitude);
        outline107.append(", accuracy=");
        outline107.append(this.accuracy);
        outline107.append(")");
        return outline107.toString();
    }
}
