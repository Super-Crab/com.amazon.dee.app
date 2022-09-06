package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.core.errors.InstantiationErrorException;
import java.util.List;
/* loaded from: classes3.dex */
public final class RoutingEngine extends NativeBase implements RoutingInterface {
    public RoutingEngine() throws InstantiationErrorException {
        this(make(), null);
        cacheThisInstance();
    }

    protected RoutingEngine(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.routing.RoutingEngine.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                RoutingEngine.disposeNativeHandle(j2);
            }
        });
    }

    public RoutingEngine(@NonNull SDKNativeEngine sDKNativeEngine) throws InstantiationErrorException {
        this(make(sDKNativeEngine), null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make() throws InstantiationErrorException;

    private static native long make(@NonNull SDKNativeEngine sDKNativeEngine) throws InstantiationErrorException;

    public native void calculateIsoline(@NonNull Waypoint waypoint, @NonNull IsolineOptions isolineOptions, @NonNull CalculateIsolineCallback calculateIsolineCallback);

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
