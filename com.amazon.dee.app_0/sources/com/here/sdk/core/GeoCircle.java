package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class GeoCircle {
    @NonNull
    public final GeoCoordinates center;
    public final double radiusInMeters;

    public GeoCircle(@NonNull GeoCoordinates geoCoordinates, double d) {
        GeoCircle make = make(geoCoordinates, d);
        this.center = make.center;
        this.radiusInMeters = make.radiusInMeters;
    }

    private static native GeoCircle make(@NonNull GeoCoordinates geoCoordinates, double d);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GeoCircle)) {
            return false;
        }
        GeoCircle geoCircle = (GeoCircle) obj;
        return Objects.equals(this.center, geoCircle.center) && Double.compare(this.radiusInMeters, geoCircle.radiusInMeters) == 0;
    }

    public int hashCode() {
        GeoCoordinates geoCoordinates = this.center;
        return (((geoCoordinates != null ? geoCoordinates.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31) + ((int) (Double.doubleToLongBits(this.radiusInMeters) ^ (Double.doubleToLongBits(this.radiusInMeters) >>> 32)));
    }
}
