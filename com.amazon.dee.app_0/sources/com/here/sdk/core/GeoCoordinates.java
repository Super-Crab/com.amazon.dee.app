package com.here.sdk.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class GeoCoordinates {
    @Nullable
    public final Double altitude;
    public final double latitude;
    public final double longitude;

    public GeoCoordinates(double d, double d2) {
        GeoCoordinates make = make(d, d2);
        this.latitude = make.latitude;
        this.longitude = make.longitude;
        this.altitude = make.altitude;
    }

    public GeoCoordinates(double d, double d2, double d3) {
        GeoCoordinates make = make(d, d2, d3);
        this.latitude = make.latitude;
        this.longitude = make.longitude;
        this.altitude = make.altitude;
    }

    private static native GeoCoordinates make(double d, double d2);

    private static native GeoCoordinates make(double d, double d2, double d3);

    public native double distanceTo(@NonNull GeoCoordinates geoCoordinates);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GeoCoordinates)) {
            return false;
        }
        GeoCoordinates geoCoordinates = (GeoCoordinates) obj;
        return Double.compare(this.latitude, geoCoordinates.latitude) == 0 && Double.compare(this.longitude, geoCoordinates.longitude) == 0 && Objects.equals(this.altitude, geoCoordinates.altitude);
    }

    public int hashCode() {
        int doubleToLongBits = (((((int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32))) + JfifUtil.MARKER_EOI) * 31) + ((int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32)))) * 31;
        Double d = this.altitude;
        return doubleToLongBits + (d != null ? d.hashCode() : 0);
    }

    native double internalDistanceTo(@NonNull GeoCoordinates geoCoordinates);
}
