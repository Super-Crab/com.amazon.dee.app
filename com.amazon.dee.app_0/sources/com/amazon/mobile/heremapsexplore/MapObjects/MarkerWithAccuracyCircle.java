package com.amazon.mobile.heremapsexplore.MapObjects;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapView;
/* loaded from: classes13.dex */
public class MarkerWithAccuracyCircle implements CustomMapObject {
    private CustomMapCircle accuracyCircle;
    private CustomMapMarker marker;

    public MarkerWithAccuracyCircle(GeoCoordinates geoCoordinates, MapView mapView) {
        this(geoCoordinates, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, mapView);
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void addToMap(MapView mapView) {
        this.marker.addToMap(mapView);
        this.accuracyCircle.addToMap(mapView);
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public Object getObject() {
        return this.marker;
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void removeFromMap(MapView mapView) {
        this.marker.removeFromMap(mapView);
        this.accuracyCircle.removeFromMap(mapView);
    }

    public void setAccuracy(double d) {
        this.accuracyCircle.setRadius(d);
    }

    public void setAccuracyCircleVisibility(boolean z) {
        this.accuracyCircle.setVisible(z);
    }

    public void setCoordinates(GeoCoordinates geoCoordinates) {
        this.marker.setCoordinates(geoCoordinates);
        this.accuracyCircle.setCenter(geoCoordinates);
    }

    public void setIcon(MapImage mapImage) {
        this.marker.setIcon(mapImage);
    }

    public MarkerWithAccuracyCircle(GeoCoordinates geoCoordinates, double d, MapView mapView) {
        this.marker = new CustomMapMarker(geoCoordinates, mapView);
        this.accuracyCircle = new CustomMapCircle(geoCoordinates, d);
    }
}
