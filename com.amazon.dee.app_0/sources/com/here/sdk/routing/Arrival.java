package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.GeoCoordinates;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class Arrival {
    @Nullable
    public Double chargeInKilowattHours;
    @Nullable
    public ChargingStation chargingStation;
    @NonNull
    public GeoCoordinates mapMatchedCoordinates;
    @Nullable
    public GeoCoordinates originalCoordinates;
    @NonNull
    public RoutePlaceType type;
    @Nullable
    public Integer waypointIndex;

    public Arrival(@NonNull RoutePlaceType routePlaceType, @Nullable Integer num, @Nullable GeoCoordinates geoCoordinates, @NonNull GeoCoordinates geoCoordinates2, @Nullable Double d, @Nullable ChargingStation chargingStation) {
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
        if (!(obj instanceof Arrival)) {
            return false;
        }
        Arrival arrival = (Arrival) obj;
        return Objects.equals(this.type, arrival.type) && Objects.equals(this.waypointIndex, arrival.waypointIndex) && Objects.equals(this.originalCoordinates, arrival.originalCoordinates) && Objects.equals(this.mapMatchedCoordinates, arrival.mapMatchedCoordinates) && Objects.equals(this.chargeInKilowattHours, arrival.chargeInKilowattHours) && Objects.equals(this.chargingStation, arrival.chargingStation);
    }

    public int hashCode() {
        RoutePlaceType routePlaceType = this.type;
        int i = 0;
        int hashCode = ((routePlaceType != null ? routePlaceType.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        Integer num = this.waypointIndex;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        GeoCoordinates geoCoordinates = this.originalCoordinates;
        int hashCode3 = (hashCode2 + (geoCoordinates != null ? geoCoordinates.hashCode() : 0)) * 31;
        GeoCoordinates geoCoordinates2 = this.mapMatchedCoordinates;
        int hashCode4 = (hashCode3 + (geoCoordinates2 != null ? geoCoordinates2.hashCode() : 0)) * 31;
        Double d = this.chargeInKilowattHours;
        int hashCode5 = (hashCode4 + (d != null ? d.hashCode() : 0)) * 31;
        ChargingStation chargingStation = this.chargingStation;
        if (chargingStation != null) {
            i = chargingStation.hashCode();
        }
        return hashCode5 + i;
    }
}
