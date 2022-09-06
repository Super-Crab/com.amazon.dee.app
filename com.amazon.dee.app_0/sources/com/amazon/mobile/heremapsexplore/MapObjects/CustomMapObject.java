package com.amazon.mobile.heremapsexplore.MapObjects;

import com.here.sdk.mapview.MapView;
/* loaded from: classes13.dex */
public interface CustomMapObject {
    void addToMap(MapView mapView);

    Object getObject();

    void removeFromMap(MapView mapView);
}
