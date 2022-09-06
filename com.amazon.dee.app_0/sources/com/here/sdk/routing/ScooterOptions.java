package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class ScooterOptions {
    public boolean allowHighway;
    @NonNull
    public AvoidanceOptions avoidanceOptions;
    @NonNull
    public RouteOptions routeOptions;
    @NonNull
    public RouteTextOptions textOptions;

    public ScooterOptions() {
        this.routeOptions = new RouteOptions();
        this.textOptions = new RouteTextOptions();
        this.avoidanceOptions = new AvoidanceOptions();
        this.allowHighway = false;
    }

    public ScooterOptions(@NonNull RouteOptions routeOptions, @NonNull RouteTextOptions routeTextOptions, @NonNull AvoidanceOptions avoidanceOptions, boolean z) {
        this.routeOptions = routeOptions;
        this.textOptions = routeTextOptions;
        this.avoidanceOptions = avoidanceOptions;
        this.allowHighway = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScooterOptions)) {
            return false;
        }
        ScooterOptions scooterOptions = (ScooterOptions) obj;
        return Objects.equals(this.routeOptions, scooterOptions.routeOptions) && Objects.equals(this.textOptions, scooterOptions.textOptions) && Objects.equals(this.avoidanceOptions, scooterOptions.avoidanceOptions) && this.allowHighway == scooterOptions.allowHighway;
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
        return ((hashCode2 + i) * 31) + (this.allowHighway ? 79 : 97);
    }
}
