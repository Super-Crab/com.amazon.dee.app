package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class PedestrianOptions {
    @NonNull
    public RouteOptions routeOptions;
    @NonNull
    public RouteTextOptions textOptions;
    public double walkSpeedInMetersPerSecond;

    public PedestrianOptions() {
        this.routeOptions = new RouteOptions();
        this.textOptions = new RouteTextOptions();
        this.walkSpeedInMetersPerSecond = 1.0d;
    }

    public PedestrianOptions(@NonNull RouteOptions routeOptions, @NonNull RouteTextOptions routeTextOptions, double d) {
        this.routeOptions = routeOptions;
        this.textOptions = routeTextOptions;
        this.walkSpeedInMetersPerSecond = d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PedestrianOptions)) {
            return false;
        }
        PedestrianOptions pedestrianOptions = (PedestrianOptions) obj;
        return Objects.equals(this.routeOptions, pedestrianOptions.routeOptions) && Objects.equals(this.textOptions, pedestrianOptions.textOptions) && Double.compare(this.walkSpeedInMetersPerSecond, pedestrianOptions.walkSpeedInMetersPerSecond) == 0;
    }

    public int hashCode() {
        RouteOptions routeOptions = this.routeOptions;
        int i = 0;
        int hashCode = ((routeOptions != null ? routeOptions.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        RouteTextOptions routeTextOptions = this.textOptions;
        if (routeTextOptions != null) {
            i = routeTextOptions.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) (Double.doubleToLongBits(this.walkSpeedInMetersPerSecond) ^ (Double.doubleToLongBits(this.walkSpeedInMetersPerSecond) >>> 32)));
    }
}
