package com.amazon.mobile.heremapsexplore.MapObjects;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.here.sdk.core.Color;
import com.here.sdk.core.GeoCircle;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.GeoPolygon;
import com.here.sdk.mapview.MapPolygon;
import com.here.sdk.mapview.MapView;
/* loaded from: classes13.dex */
public class CustomMapCircle implements CustomMapObject {
    private GeoCoordinates center;
    private MapPolygon circle;
    private double radiusInMeters;
    private boolean isVisible = false;
    private Color accuracyCircleColor = new Color(212, 19, 19, 50);

    public CustomMapCircle(GeoCoordinates geoCoordinates, double d) {
        this.radiusInMeters = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.center = geoCoordinates;
        this.radiusInMeters = d;
        this.circle = new MapPolygon(new GeoPolygon(new GeoCircle(this.center, this.radiusInMeters)), this.accuracyCircleColor);
    }

    private void updateGeometry(GeoCoordinates geoCoordinates, double d) {
        this.circle.updateGeometry(new GeoPolygon(new GeoCircle(geoCoordinates, d)));
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void addToMap(MapView mapView) {
        mapView.getMapScene().addMapPolygon(this.circle);
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public Object getObject() {
        return this.circle;
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void removeFromMap(MapView mapView) {
        if (this.circle == null || mapView.getMapScene() == null) {
            return;
        }
        mapView.getMapScene().removeMapPolygon(this.circle);
    }

    public void setCenter(GeoCoordinates geoCoordinates) {
        this.center = geoCoordinates;
        if (this.isVisible) {
            updateGeometry(this.center, this.radiusInMeters);
        }
    }

    public void setRadius(double d) {
        this.radiusInMeters = d;
        if (this.isVisible) {
            updateGeometry(this.center, this.radiusInMeters);
        }
    }

    public void setVisible(boolean z) {
        this.isVisible = z;
        if (z) {
            updateGeometry(this.center, this.radiusInMeters);
        } else {
            updateGeometry(this.center, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        }
    }
}
