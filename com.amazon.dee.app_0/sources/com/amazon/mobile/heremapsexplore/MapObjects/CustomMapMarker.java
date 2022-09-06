package com.amazon.mobile.heremapsexplore.MapObjects;

import androidx.annotation.NonNull;
import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.CustomMetadataValue;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Metadata;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapView;
import java.lang.ref.WeakReference;
/* loaded from: classes13.dex */
public class CustomMapMarker implements CustomMapObject, CustomMetadataValue {
    private Anchor2D anchor = new Anchor2D(0.5d, 0.5d);
    private GeoCoordinates coordinates;
    private MapImage icon;
    private MapMarker mapMarker;
    private WeakReference<MapView> weakMapView;

    public CustomMapMarker(GeoCoordinates geoCoordinates, MapView mapView) {
        this.coordinates = geoCoordinates;
        this.weakMapView = new WeakReference<>(mapView);
    }

    private void setMetadata() {
        Metadata metadata = new Metadata();
        metadata.setCustomValue("WRAPPER", this);
        MapMarker mapMarker = this.mapMarker;
        if (mapMarker != null) {
            mapMarker.setMetadata(metadata);
        }
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void addToMap(MapView mapView) {
        if (getMapMarker() != null) {
            mapView.getMapScene().addMapMarker(getMapMarker());
        }
    }

    public MapMarker getMapMarker() {
        return this.mapMarker;
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public Object getObject() {
        return this.mapMarker;
    }

    @Override // com.here.sdk.core.CustomMetadataValue
    @NonNull
    public String getTag() {
        return null;
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void removeFromMap(MapView mapView) {
        if (getMapMarker() == null || mapView.getMapScene() == null) {
            return;
        }
        mapView.getMapScene().removeMapMarker(getMapMarker());
    }

    public void setAnchor(Anchor2D anchor2D) {
        if (this.weakMapView.get() != null) {
            removeFromMap(this.weakMapView.get());
        }
        this.anchor = anchor2D;
        MapImage mapImage = this.icon;
        if (mapImage != null) {
            this.mapMarker = new MapMarker(this.coordinates, mapImage, this.anchor);
            setMetadata();
        }
        if (this.weakMapView.get() != null) {
            addToMap(this.weakMapView.get());
        }
    }

    public void setCoordinates(GeoCoordinates geoCoordinates) {
        this.coordinates = geoCoordinates;
        MapMarker mapMarker = this.mapMarker;
        if (mapMarker != null) {
            mapMarker.setCoordinates(geoCoordinates);
        }
    }

    public void setIcon(MapImage mapImage) {
        if (this.weakMapView.get() != null) {
            removeFromMap(this.weakMapView.get());
        }
        this.icon = mapImage;
        this.mapMarker = new MapMarker(this.coordinates, this.icon, this.anchor);
        setMetadata();
        if (this.weakMapView.get() != null) {
            addToMap(this.weakMapView.get());
        }
    }
}
