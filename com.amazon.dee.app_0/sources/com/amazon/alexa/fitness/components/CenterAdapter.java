package com.amazon.alexa.fitness.components;

import android.location.Location;
import com.amazon.alexa.fitness.api.LocationService;
import com.amazon.alexa.fitness.api.LocationServiceListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapCamera;
import com.here.sdk.mapview.MapView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CenterAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\rH\u0016J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000bH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/fitness/components/CenterAdapter;", "Lcom/amazon/alexa/fitness/components/MapViewAdapter;", "Lcom/amazon/alexa/fitness/components/MapViewListener;", "Lcom/amazon/alexa/fitness/api/LocationServiceListener;", "()V", "locationService", "Lcom/amazon/alexa/fitness/api/LocationService;", "kotlin.jvm.PlatformType", "preventAutoCentering", "", "workoutMapView", "Lcom/amazon/alexa/fitness/components/WorkoutMapView;", "adapt", "", "center", "coordinate", "Lcom/here/sdk/core/GeoCoordinates;", "onDestroy", "onLocationUpdated", "location", "Landroid/location/Location;", "onMapOffCentered", "mapView", "Lcom/here/sdk/mapview/MapView;", "onPause", "onResume", "setMapView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CenterAdapter implements MapViewAdapter, MapViewListener, LocationServiceListener {
    private final LocationService locationService = (LocationService) GeneratedOutlineSupport1.outline20(LocationService.class);
    private boolean preventAutoCentering;
    private WorkoutMapView workoutMapView;

    /* JADX INFO: Access modifiers changed from: private */
    public final void center(GeoCoordinates geoCoordinates) {
        MapView mapView;
        MapCamera camera;
        WorkoutMapView workoutMapView = this.workoutMapView;
        if (workoutMapView == null || (mapView = workoutMapView.getMapView()) == null || (camera = mapView.getCamera()) == null) {
            return;
        }
        camera.lookAt(geoCoordinates);
        camera.zoomTo(18.0d);
    }

    @Override // com.amazon.alexa.fitness.components.MapViewAdapter
    public void adapt() {
        this.locationService.requestLocationOnce(new CenterAdapter$adapt$1(this));
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onDestroy() {
        this.locationService.removeListener(this);
    }

    @Override // com.amazon.alexa.fitness.api.LocationServiceListener
    public void onLocationStatusChanged(boolean z) {
        LocationServiceListener.DefaultImpls.onLocationStatusChanged(this, z);
    }

    @Override // com.amazon.alexa.fitness.api.LocationServiceListener
    public void onLocationUpdated(@NotNull Location location) {
        Intrinsics.checkParameterIsNotNull(location, "location");
        if (!this.preventAutoCentering) {
            center(new GeoCoordinates(location.getLatitude(), location.getLongitude()));
        }
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onMapOffCentered(@NotNull MapView mapView) {
        Intrinsics.checkParameterIsNotNull(mapView, "mapView");
        this.preventAutoCentering = true;
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onPause() {
        this.locationService.removeListener(this);
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onResume() {
        this.locationService.addListener(this);
    }

    @Override // com.amazon.alexa.fitness.components.MapViewAdapter
    public void setMapView(@NotNull WorkoutMapView mapView) {
        Intrinsics.checkParameterIsNotNull(mapView, "mapView");
        this.workoutMapView = mapView;
        mapView.addListener(this);
        this.locationService.addListener(this);
    }
}
