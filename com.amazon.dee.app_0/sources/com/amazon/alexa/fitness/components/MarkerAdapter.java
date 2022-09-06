package com.amazon.alexa.fitness.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.fitness.api.LocationService;
import com.amazon.alexa.fitness.api.LocationServiceListener;
import com.amazon.alexa.fitness.components.MapViewListener;
import com.amazon.alexa.fitness.ui.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MarkerAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0004J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0013H\u0004J\b\u0010\u001a\u001a\u00020\u0015H\u0016J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0015H\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0016J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0013H\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/alexa/fitness/components/MarkerAdapter;", "Lcom/amazon/alexa/fitness/components/MapViewAdapter;", "Lcom/amazon/alexa/fitness/components/MapViewListener;", "Lcom/amazon/alexa/fitness/api/LocationServiceListener;", "()V", "locationService", "Lcom/amazon/alexa/fitness/api/LocationService;", "kotlin.jvm.PlatformType", "mapImage", "Lcom/here/sdk/mapview/MapImage;", "mapMarker", "Lcom/here/sdk/mapview/MapMarker;", "markerResourceId", "", "getMarkerResourceId", "()I", "setMarkerResourceId", "(I)V", "workoutMapView", "Lcom/amazon/alexa/fitness/components/WorkoutMapView;", "adapt", "", "addMarker", "coordinate", "Lcom/here/sdk/core/GeoCoordinates;", "getMapView", "onDestroy", "onLocationUpdated", "location", "Landroid/location/Location;", "onPause", "onResume", "setMapView", "mapView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class MarkerAdapter implements MapViewAdapter, MapViewListener, LocationServiceListener {
    private MapImage mapImage;
    private MapMarker mapMarker;
    private WorkoutMapView workoutMapView;
    private final LocationService locationService = (LocationService) GeneratedOutlineSupport1.outline20(LocationService.class);
    private int markerResourceId = R.drawable.current_location_marker;

    @Override // com.amazon.alexa.fitness.components.MapViewAdapter
    public void adapt() {
        this.locationService.requestLocationOnce(new MarkerAdapter$adapt$1(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addMarker(@NotNull GeoCoordinates coordinate) {
        MapView mapView;
        Intrinsics.checkParameterIsNotNull(coordinate, "coordinate");
        WorkoutMapView workoutMapView = this.workoutMapView;
        if (workoutMapView == null || (mapView = workoutMapView.getMapView()) == null) {
            return;
        }
        MapMarker mapMarker = this.mapMarker;
        if (mapMarker != null) {
            mapView.getMapScene().removeMapMarker(mapMarker);
        }
        MapImage mapImage = this.mapImage;
        if (mapImage == null) {
            return;
        }
        MapMarker mapMarker2 = new MapMarker(coordinate, mapImage);
        MapScene mapScene = mapView.getMapScene();
        if (mapScene != null) {
            mapScene.addMapMarker(mapMarker2);
            Unit unit = Unit.INSTANCE;
        }
        this.mapMarker = mapMarker2;
    }

    @Nullable
    protected final WorkoutMapView getMapView() {
        return this.workoutMapView;
    }

    protected int getMarkerResourceId() {
        return this.markerResourceId;
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
        MapMarker mapMarker = this.mapMarker;
        if (mapMarker != null) {
            mapMarker.setCoordinates(new GeoCoordinates(location.getLatitude(), location.getLongitude()));
        }
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onMapOffCentered(@NotNull MapView mapView) {
        Intrinsics.checkParameterIsNotNull(mapView, "mapView");
        MapViewListener.DefaultImpls.onMapOffCentered(this, mapView);
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
        Drawable drawable;
        Intrinsics.checkParameterIsNotNull(mapView, "mapView");
        this.workoutMapView = mapView;
        this.locationService.addListener(this);
        mapView.addListener(this);
        if (this.mapImage == null && (drawable = ContextCompat.getDrawable(mapView.getContext(), getMarkerResourceId())) != null) {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            this.mapImage = MapImageFactory.fromBitmap(createBitmap);
            Unit unit = Unit.INSTANCE;
        }
    }

    protected void setMarkerResourceId(int i) {
        this.markerResourceId = i;
    }
}
