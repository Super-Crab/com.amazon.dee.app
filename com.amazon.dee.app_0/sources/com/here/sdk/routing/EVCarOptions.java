package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class EVCarOptions {
    @NonNull
    public AvoidanceOptions avoidanceOptions;
    @NonNull
    public BatterySpecifications batterySpecifications;
    @NonNull
    public EVConsumptionModel consumptionModel;
    public boolean ensureReachability;
    @NonNull
    public RouteOptions routeOptions;
    @NonNull
    public RouteTextOptions textOptions;

    public EVCarOptions() {
        this.routeOptions = new RouteOptions();
        this.textOptions = new RouteTextOptions();
        this.avoidanceOptions = new AvoidanceOptions();
        this.ensureReachability = false;
        this.consumptionModel = new EVConsumptionModel();
        this.batterySpecifications = new BatterySpecifications();
    }

    public EVCarOptions(@NonNull RouteOptions routeOptions, @NonNull RouteTextOptions routeTextOptions, @NonNull AvoidanceOptions avoidanceOptions, boolean z, @NonNull EVConsumptionModel eVConsumptionModel, @NonNull BatterySpecifications batterySpecifications) {
        this.routeOptions = routeOptions;
        this.textOptions = routeTextOptions;
        this.avoidanceOptions = avoidanceOptions;
        this.ensureReachability = z;
        this.consumptionModel = eVConsumptionModel;
        this.batterySpecifications = batterySpecifications;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EVCarOptions)) {
            return false;
        }
        EVCarOptions eVCarOptions = (EVCarOptions) obj;
        return Objects.equals(this.routeOptions, eVCarOptions.routeOptions) && Objects.equals(this.textOptions, eVCarOptions.textOptions) && Objects.equals(this.avoidanceOptions, eVCarOptions.avoidanceOptions) && this.ensureReachability == eVCarOptions.ensureReachability && Objects.equals(this.consumptionModel, eVCarOptions.consumptionModel) && Objects.equals(this.batterySpecifications, eVCarOptions.batterySpecifications);
    }

    public int hashCode() {
        RouteOptions routeOptions = this.routeOptions;
        int i = 0;
        int hashCode = ((routeOptions != null ? routeOptions.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        RouteTextOptions routeTextOptions = this.textOptions;
        int hashCode2 = (hashCode + (routeTextOptions != null ? routeTextOptions.hashCode() : 0)) * 31;
        AvoidanceOptions avoidanceOptions = this.avoidanceOptions;
        int hashCode3 = (((hashCode2 + (avoidanceOptions != null ? avoidanceOptions.hashCode() : 0)) * 31) + (this.ensureReachability ? 79 : 97)) * 31;
        EVConsumptionModel eVConsumptionModel = this.consumptionModel;
        int hashCode4 = (hashCode3 + (eVConsumptionModel != null ? eVConsumptionModel.hashCode() : 0)) * 31;
        BatterySpecifications batterySpecifications = this.batterySpecifications;
        if (batterySpecifications != null) {
            i = batterySpecifications.hashCode();
        }
        return hashCode4 + i;
    }
}
