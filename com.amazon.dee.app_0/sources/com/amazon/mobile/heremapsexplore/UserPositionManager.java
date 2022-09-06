package com.amazon.mobile.heremapsexplore;

import com.here.sdk.core.GeoCoordinates;
/* loaded from: classes13.dex */
public interface UserPositionManager {
    void displayUserLocationWithAccuracy(boolean z);

    GeoCoordinates getCoordinates();

    boolean hasCoordinates();

    void pollForUserLocation();

    void setInitialUserLocation();

    void stopPollingForUserLocation();

    void updateUserLocationMarker();
}
