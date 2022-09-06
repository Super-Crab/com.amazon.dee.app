package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import java.util.List;
/* loaded from: classes3.dex */
class RoutingInterfaceImpl extends NativeBase implements RoutingInterface {
    protected RoutingInterfaceImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.routing.RoutingInterfaceImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                RoutingInterfaceImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.routing.RoutingInterface
    public native void calculateRoute(@NonNull List<Waypoint> list, @NonNull CarOptions carOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    @Override // com.here.sdk.routing.RoutingInterface
    public native void calculateRoute(@NonNull List<Waypoint> list, @NonNull EVCarOptions eVCarOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    @Override // com.here.sdk.routing.RoutingInterface
    public native void calculateRoute(@NonNull List<Waypoint> list, @NonNull EVTruckOptions eVTruckOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    @Override // com.here.sdk.routing.RoutingInterface
    public native void calculateRoute(@NonNull List<Waypoint> list, @NonNull PedestrianOptions pedestrianOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    @Override // com.here.sdk.routing.RoutingInterface
    public native void calculateRoute(@NonNull List<Waypoint> list, @NonNull ScooterOptions scooterOptions, @NonNull CalculateRouteCallback calculateRouteCallback);

    @Override // com.here.sdk.routing.RoutingInterface
    public native void calculateRoute(@NonNull List<Waypoint> list, @NonNull TruckOptions truckOptions, @NonNull CalculateRouteCallback calculateRouteCallback);
}
