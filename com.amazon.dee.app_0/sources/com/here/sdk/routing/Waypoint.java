package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.GeoCoordinates;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class Waypoint {
    @NonNull
    public GeoCoordinates coordinates;
    public int durationInSeconds;
    @Nullable
    public Double headingInDegrees;
    public int transitRadiusInMeters;
    @NonNull
    public WaypointType type;

    public Waypoint(@NonNull GeoCoordinates geoCoordinates) {
        this.coordinates = geoCoordinates;
        this.type = WaypointType.STOPOVER;
        this.transitRadiusInMeters = 0;
        this.durationInSeconds = 0;
        this.headingInDegrees = null;
    }

    public Waypoint(@NonNull GeoCoordinates geoCoordinates, @NonNull WaypointType waypointType, int i, int i2, @Nullable Double d) {
        this.coordinates = geoCoordinates;
        this.type = waypointType;
        this.transitRadiusInMeters = i;
        this.durationInSeconds = i2;
        this.headingInDegrees = d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Waypoint)) {
            return false;
        }
        Waypoint waypoint = (Waypoint) obj;
        return Objects.equals(this.coordinates, waypoint.coordinates) && Objects.equals(this.type, waypoint.type) && this.transitRadiusInMeters == waypoint.transitRadiusInMeters && this.durationInSeconds == waypoint.durationInSeconds && Objects.equals(this.headingInDegrees, waypoint.headingInDegrees);
    }

    public int hashCode() {
        GeoCoordinates geoCoordinates = this.coordinates;
        int i = 0;
        int hashCode = ((geoCoordinates != null ? geoCoordinates.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        WaypointType waypointType = this.type;
        int hashCode2 = (((((hashCode + (waypointType != null ? waypointType.hashCode() : 0)) * 31) + this.transitRadiusInMeters) * 31) + this.durationInSeconds) * 31;
        Double d = this.headingInDegrees;
        if (d != null) {
            i = d.hashCode();
        }
        return hashCode2 + i;
    }
}
