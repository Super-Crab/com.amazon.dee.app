package com.amazon.mobile.heremapsexplore.Utilities;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.here.sdk.core.GeoCoordinates;
/* loaded from: classes13.dex */
public class ReactResponses {
    private ReactResponses() {
    }

    public static WritableMap forCoordinate(GeoCoordinates geoCoordinates) {
        if (geoCoordinates != null) {
            WritableMap createMap = Arguments.createMap();
            WritableMap createMap2 = Arguments.createMap();
            createMap.putDouble("latitude", geoCoordinates.latitude);
            createMap.putDouble("longitude", geoCoordinates.longitude);
            createMap2.putMap("coordinate", createMap);
            return createMap2;
        }
        throw new NullPointerException("Coordinates for callback response was null");
    }

    public static WritableMap forCoordinateAndRadius(GeoCoordinates geoCoordinates, double d) {
        WritableMap forCoordinate = forCoordinate(geoCoordinates);
        forCoordinate.putDouble("radius", d);
        return forCoordinate;
    }
}
