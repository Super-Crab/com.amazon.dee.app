package com.here.sdk.mapview;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class GeoConverter implements Function<GeoCoordinates, Point2D> {
    private WeakReference<MapView> mWeakMapView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeoConverter(MapView mapView) {
        this.mWeakMapView = new WeakReference<>(mapView);
    }

    @Override // com.here.sdk.mapview.Function
    public Point2D apply(GeoCoordinates geoCoordinates) {
        MapView mapView = this.mWeakMapView.get();
        if (mapView == null) {
            return null;
        }
        return mapView.geoToViewCoordinates(geoCoordinates);
    }
}
