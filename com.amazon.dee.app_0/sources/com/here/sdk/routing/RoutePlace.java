package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.GeoCoordinates;
import java.util.Objects;
/* loaded from: classes3.dex */
final class RoutePlace {
    @Nullable
    Double chargeInKilowattHours;
    @Nullable
    ChargingStation chargingStation;
    @NonNull
    RoutePlaceDirection direction;
    @NonNull
    GeoCoordinates mapMatchedCoordinates;
    @Nullable
    GeoCoordinates originalCoordinates;
    @NonNull
    RoutePlaceType type;
    @Nullable
    Integer waypointIndex;

    RoutePlace(@NonNull RoutePlaceDirection routePlaceDirection, @NonNull RoutePlaceType routePlaceType, @Nullable Integer num, @Nullable GeoCoordinates geoCoordinates, @NonNull GeoCoordinates geoCoordinates2, @Nullable Double d, @Nullable ChargingStation chargingStation) {
        this.direction = routePlaceDirection;
        this.type = routePlaceType;
        this.waypointIndex = num;
        this.originalCoordinates = geoCoordinates;
        this.mapMatchedCoordinates = geoCoordinates2;
        this.chargeInKilowattHours = d;
        this.chargingStation = chargingStation;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RoutePlace)) {
            return false;
        }
        RoutePlace routePlace = (RoutePlace) obj;
        return Objects.equals(this.direction, routePlace.direction) && Objects.equals(this.type, routePlace.type) && Objects.equals(this.waypointIndex, routePlace.waypointIndex) && Objects.equals(this.originalCoordinates, routePlace.originalCoordinates) && Objects.equals(this.mapMatchedCoordinates, routePlace.mapMatchedCoordinates) && Objects.equals(this.chargeInKilowattHours, routePlace.chargeInKilowattHours) && Objects.equals(this.chargingStation, routePlace.chargingStation);
    }

    public int hashCode() {
        RoutePlaceDirection routePlaceDirection = this.direction;
        int i = 0;
        int hashCode = ((routePlaceDirection != null ? routePlaceDirection.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        RoutePlaceType routePlaceType = this.type;
        int hashCode2 = (hashCode + (routePlaceType != null ? routePlaceType.hashCode() : 0)) * 31;
        Integer num = this.waypointIndex;
        int hashCode3 = (hashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        GeoCoordinates geoCoordinates = this.originalCoordinates;
        int hashCode4 = (hashCode3 + (geoCoordinates != null ? geoCoordinates.hashCode() : 0)) * 31;
        GeoCoordinates geoCoordinates2 = this.mapMatchedCoordinates;
        int hashCode5 = (hashCode4 + (geoCoordinates2 != null ? geoCoordinates2.hashCode() : 0)) * 31;
        Double d = this.chargeInKilowattHours;
        int hashCode6 = (hashCode5 + (d != null ? d.hashCode() : 0)) * 31;
        ChargingStation chargingStation = this.chargingStation;
        if (chargingStation != null) {
            i = chargingStation.hashCode();
        }
        return hashCode6 + i;
    }
}
