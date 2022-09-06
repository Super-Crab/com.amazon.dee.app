package com.here.sdk.routing;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes3.dex */
public interface RoutingInterface {
    void calculateRoute(@NonNull List<Waypoint> list, @NonNull CarOptions carOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    void calculateRoute(@NonNull List<Waypoint> list, @NonNull EVCarOptions eVCarOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    void calculateRoute(@NonNull List<Waypoint> list, @NonNull EVTruckOptions eVTruckOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    void calculateRoute(@NonNull List<Waypoint> list, @NonNull PedestrianOptions pedestrianOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    void calculateRoute(@NonNull List<Waypoint> list, @NonNull ScooterOptions scooterOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    void calculateRoute(@NonNull List<Waypoint> list, @NonNull TruckOptions truckOptions, @NonNull CalculateRouteCallback calculateRouteCallback);
}
