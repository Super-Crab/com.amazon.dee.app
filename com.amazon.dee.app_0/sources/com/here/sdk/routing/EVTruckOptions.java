package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class EVTruckOptions {
    @NonNull
    public AvoidanceOptions avoidanceOptions;
    @NonNull
    public EVConsumptionModel consumptionModel;
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

    public EVTruckOptions() {
        this.routeOptions = new RouteOptions();
        this.textOptions = new RouteTextOptions();
        this.avoidanceOptions = new AvoidanceOptions();
        this.specifications = new TruckSpecifications();
        this.tunnelCategory = null;
        this.hazardousGoods = new ArrayList();
        this.consumptionModel = new EVConsumptionModel();
    }

    public EVTruckOptions(@NonNull RouteOptions routeOptions, @NonNull RouteTextOptions routeTextOptions, @NonNull AvoidanceOptions avoidanceOptions, @NonNull TruckSpecifications truckSpecifications, @Nullable TunnelCategory tunnelCategory, @NonNull List<HazardousGood> list, @NonNull EVConsumptionModel eVConsumptionModel) {
        this.routeOptions = routeOptions;
        this.textOptions = routeTextOptions;
        this.avoidanceOptions = avoidanceOptions;
        this.specifications = truckSpecifications;
        this.tunnelCategory = tunnelCategory;
        this.hazardousGoods = list;
        this.consumptionModel = eVConsumptionModel;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EVTruckOptions)) {
            return false;
        }
        EVTruckOptions eVTruckOptions = (EVTruckOptions) obj;
        return Objects.equals(this.routeOptions, eVTruckOptions.routeOptions) && Objects.equals(this.textOptions, eVTruckOptions.textOptions) && Objects.equals(this.avoidanceOptions, eVTruckOptions.avoidanceOptions) && Objects.equals(this.specifications, eVTruckOptions.specifications) && Objects.equals(this.tunnelCategory, eVTruckOptions.tunnelCategory) && Objects.equals(this.hazardousGoods, eVTruckOptions.hazardousGoods) && Objects.equals(this.consumptionModel, eVTruckOptions.consumptionModel);
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
        int hashCode6 = (hashCode5 + (list != null ? list.hashCode() : 0)) * 31;
        EVConsumptionModel eVConsumptionModel = this.consumptionModel;
        if (eVConsumptionModel != null) {
            i = eVConsumptionModel.hashCode();
        }
        return hashCode6 + i;
    }
}
