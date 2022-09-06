package com.amazon.alexa.fitness.components;

import android.util.Log;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.fitness.ui.R;
import com.here.sdk.core.Color;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.GeoPolyline;
import com.here.sdk.mapview.LineCap;
import com.here.sdk.mapview.MapPolyline;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RouteAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0004J\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bJ\u0014\u0010\u0014\u001a\u00020\u00112\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u000bH\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/components/RouteAdapter;", "Lcom/amazon/alexa/fitness/components/MapViewAdapter;", "()V", "coordinates", "", "Lcom/here/sdk/core/GeoCoordinates;", "getCoordinates", "()Ljava/util/List;", "polyline", "Lcom/here/sdk/mapview/MapPolyline;", "workoutMapView", "Lcom/amazon/alexa/fitness/components/WorkoutMapView;", "getWorkoutMapView", "()Lcom/amazon/alexa/fitness/components/WorkoutMapView;", "setWorkoutMapView", "(Lcom/amazon/alexa/fitness/components/WorkoutMapView;)V", "adapt", "", "addRoute", "getMapView", "setCoordinates", "", "setMapView", "mapView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class RouteAdapter implements MapViewAdapter {
    @NotNull
    private final List<GeoCoordinates> coordinates = new ArrayList();
    private MapPolyline polyline;
    @Nullable
    private WorkoutMapView workoutMapView;

    @Override // com.amazon.alexa.fitness.components.MapViewAdapter
    public void adapt() {
        addRoute();
    }

    protected final void addRoute() {
        MapView map;
        List list;
        MapView mapView;
        MapScene mapScene;
        try {
            String str = "drawing path for coordinates: " + this.coordinates;
            WorkoutMapView workoutMapView = this.workoutMapView;
            MapPolyline mapPolyline = this.polyline;
            if (mapPolyline != null && workoutMapView != null && (mapView = workoutMapView.getMapView()) != null && (mapScene = mapView.getMapScene()) != null) {
                mapScene.removeMapPolyline(mapPolyline);
            }
            if (this.coordinates.size() < 2 || workoutMapView == null || (map = workoutMapView.getMap()) == null) {
                return;
            }
            int color = ContextCompat.getColor(workoutMapView.getContext(), R.color.action10);
            list = CollectionsKt___CollectionsKt.toList(this.coordinates);
            MapPolyline mapPolyline2 = new MapPolyline(new GeoPolyline(list), 20.0d, Color.valueOf(android.graphics.Color.rgb(android.graphics.Color.red(color), android.graphics.Color.green(color), android.graphics.Color.blue(color))));
            mapPolyline2.setLineCap(LineCap.ROUND);
            map.getMapScene().addMapPolyline(mapPolyline2);
            this.polyline = mapPolyline2;
        } catch (Error e) {
            Log.e("AFX-RouteAdapter", e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final List<GeoCoordinates> getCoordinates() {
        return this.coordinates;
    }

    @Nullable
    public final WorkoutMapView getMapView() {
        return this.workoutMapView;
    }

    @Nullable
    protected final WorkoutMapView getWorkoutMapView() {
        return this.workoutMapView;
    }

    public final void setCoordinates(@NotNull List<GeoCoordinates> coordinates) {
        Intrinsics.checkParameterIsNotNull(coordinates, "coordinates");
        this.coordinates.clear();
        this.coordinates.addAll(coordinates);
    }

    @Override // com.amazon.alexa.fitness.components.MapViewAdapter
    public void setMapView(@NotNull WorkoutMapView mapView) {
        Intrinsics.checkParameterIsNotNull(mapView, "mapView");
        this.workoutMapView = mapView;
    }

    protected final void setWorkoutMapView(@Nullable WorkoutMapView workoutMapView) {
        this.workoutMapView = workoutMapView;
    }
}
