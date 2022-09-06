package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class CarOptions {
    @NonNull
    public AvoidanceOptions avoidanceOptions;
    @NonNull
    public RouteOptions routeOptions;
    @NonNull
    public RouteTextOptions textOptions;

    public CarOptions() {
        this.routeOptions = new RouteOptions();
        this.textOptions = new RouteTextOptions();
        this.avoidanceOptions = new AvoidanceOptions();
    }

    public CarOptions(@NonNull RouteOptions routeOptions, @NonNull RouteTextOptions routeTextOptions, @NonNull AvoidanceOptions avoidanceOptions) {
        this.routeOptions = routeOptions;
        this.textOptions = routeTextOptions;
        this.avoidanceOptions = avoidanceOptions;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CarOptions)) {
            return false;
        }
        CarOptions carOptions = (CarOptions) obj;
        return Objects.equals(this.routeOptions, carOptions.routeOptions) && Objects.equals(this.textOptions, carOptions.textOptions) && Objects.equals(this.avoidanceOptions, carOptions.avoidanceOptions);
    }

    public int hashCode() {
        RouteOptions routeOptions = this.routeOptions;
        int i = 0;
        int hashCode = ((routeOptions != null ? routeOptions.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        RouteTextOptions routeTextOptions = this.textOptions;
        int hashCode2 = (hashCode + (routeTextOptions != null ? routeTextOptions.hashCode() : 0)) * 31;
        AvoidanceOptions avoidanceOptions = this.avoidanceOptions;
        if (avoidanceOptions != null) {
            i = avoidanceOptions.hashCode();
        }
        return hashCode2 + i;
    }
}
