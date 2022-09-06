package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class TruckOptions {
    @NonNull
    public AvoidanceOptions avoidanceOptions;
    @NonNull
    public List<HazardousGood> hazardousGoods;
    @NonNull
    public RouteOptions routeOptions;
    @NonNull
    public TruckSpecifications specifications;
    @NonNull
    public RouteTextOptions textOptions;
    @Nullable
    public TunnelCategory tunnelCategory;

    public TruckOptions() {
        this.routeOptions = new RouteOptions();
        this.textOptions = new RouteTextOptions();
        this.avoidanceOptions = new AvoidanceOptions();
        this.specifications = new TruckSpecifications();
        this.tunnelCategory = null;
        this.hazardousGoods = new ArrayList();
    }

    public TruckOptions(@NonNull RouteOptions routeOptions, @NonNull RouteTextOptions routeTextOptions, @NonNull AvoidanceOptions avoidanceOptions, @NonNull TruckSpecifications truckSpecifications, @Nullable TunnelCategory tunnelCategory, @NonNull List<HazardousGood> list) {
        this.routeOptions = routeOptions;
        this.textOptions = routeTextOptions;
        this.avoidanceOptions = avoidanceOptions;
        this.specifications = truckSpecifications;
        this.tunnelCategory = tunnelCategory;
        this.hazardousGoods = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TruckOptions)) {
            return false;
        }
        TruckOptions truckOptions = (TruckOptions) obj;
        return Objects.equals(this.routeOptions, truckOptions.routeOptions) && Objects.equals(this.textOptions, truckOptions.textOptions) && Objects.equals(this.avoidanceOptions, truckOptions.avoidanceOptions) && Objects.equals(this.specifications, truckOptions.specifications) && Objects.equals(this.tunnelCategory, truckOptions.tunnelCategory) && Objects.equals(this.hazardousGoods, truckOptions.hazardousGoods);
    }

    public int hashCode() {
        RouteOptions routeOptions = this.routeOptions;
        int i = 0;
        int hashCode = ((routeOptions != null ? routeOptions.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        RouteTextOptions routeTextOptions = this.textOptions;
        int hashCode2 = (hashCode + (routeTextOptions != null ? routeTextOptions.hashCode() : 0)) * 31;
        AvoidanceOptions avoidanceOptions = this.avoidanceOptions;
        int hashCode3 = (hashCode2 + (avoidanceOptions != null ? avoidanceOptions.hashCode() : 0)) * 31;
        TruckSpecifications truckSpecifications = this.specifications;
        int hashCode4 = (hashCode3 + (truckSpecifications != null ? truckSpecifications.hashCode() : 0)) * 31;
        TunnelCategory tunnelCategory = this.tunnelCategory;
        int hashCode5 = (hashCode4 + (tunnelCategory != null ? tunnelCategory.hashCode() : 0)) * 31;
        List<HazardousGood> list = this.hazardousGoods;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode5 + i;
    }
}
