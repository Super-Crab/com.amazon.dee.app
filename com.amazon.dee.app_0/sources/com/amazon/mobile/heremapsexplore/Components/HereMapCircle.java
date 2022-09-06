package com.amazon.mobile.heremapsexplore.Components;

import android.content.Context;
import com.amazon.mobile.heremapsexplore.MapObjects.CustomMapCircle;
import com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.views.view.ReactViewGroup;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapView;
/* loaded from: classes13.dex */
public class HereMapCircle extends ReactViewGroup implements CustomMapObject {
    private GeoCoordinates center;
    private CustomMapCircle circle;
    private final Context context;
    private double radius;

    public HereMapCircle(Context context) {
        super(context);
        this.context = context;
    }

    private CustomMapCircle createCircleFromProps() {
        return new CustomMapCircle(this.center, this.radius);
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void addToMap(MapView mapView) {
        this.circle = createCircleFromProps();
        this.circle.addToMap(mapView);
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public Object getObject() {
        return this.circle;
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void removeFromMap(MapView mapView) {
        CustomMapCircle customMapCircle = this.circle;
        if (customMapCircle == null) {
            return;
        }
        customMapCircle.removeFromMap(mapView);
        this.circle = null;
    }

    public void setCenter(ReadableMap readableMap) {
        this.center = new GeoCoordinates(readableMap.getDouble("latitude"), readableMap.getDouble("longitude"));
        CustomMapCircle customMapCircle = this.circle;
        if (customMapCircle != null) {
            customMapCircle.setCenter(this.center);
        }
    }

    public void setRadius(double d) {
        this.radius = d;
        CustomMapCircle customMapCircle = this.circle;
        if (customMapCircle != null) {
            customMapCircle.setRadius(this.radius);
        }
    }
}
