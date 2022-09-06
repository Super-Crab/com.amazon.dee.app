package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.Date;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class RouteOptions {
    public int alternatives;
    @Nullable
    public Date departureTime;
    @NonNull
    public OptimizationMode optimizationMode;

    @Deprecated
    /* loaded from: classes3.dex */
    public static class Builder {
        private OptimizationMode optimizationMode = OptimizationMode.FASTEST;
        private int alternatives = 0;
        private Date departureTime = null;

        public RouteOptions build() {
            return new RouteOptions(this.optimizationMode, this.alternatives, this.departureTime);
        }

        public Builder setAlternatives(int i) {
            this.alternatives = i;
            return this;
        }

        public Builder setDepartureTime(Date date) {
            this.departureTime = date;
            return this;
        }

        public Builder setOptimizationMode(OptimizationMode optimizationMode) {
            this.optimizationMode = optimizationMode;
            return this;
        }
    }

    public RouteOptions() {
        this.optimizationMode = OptimizationMode.FASTEST;
        this.alternatives = 0;
        this.departureTime = null;
    }

    public RouteOptions(@NonNull OptimizationMode optimizationMode, int i, @Nullable Date date) {
        this.optimizationMode = optimizationMode;
        this.alternatives = i;
        this.departureTime = date;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RouteOptions)) {
            return false;
        }
        RouteOptions routeOptions = (RouteOptions) obj;
        return Objects.equals(this.optimizationMode, routeOptions.optimizationMode) && this.alternatives == routeOptions.alternatives && Objects.equals(this.departureTime, routeOptions.departureTime);
    }

    public int hashCode() {
        OptimizationMode optimizationMode = this.optimizationMode;
        int i = 0;
        int hashCode = ((((optimizationMode != null ? optimizationMode.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31) + this.alternatives) * 31;
        Date date = this.departureTime;
        if (date != null) {
            i = date.hashCode();
        }
        return hashCode + i;
    }
}
