package com.amazon.mobile.heremapsexplore;

import android.location.Location;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.sensor.api.location.LocationConfigurationRequest;
import com.amazon.alexa.sensor.api.location.LocationEventHandler;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.amazon.mobile.heremapsexplore.Constants.Constants;
import com.amazon.mobile.heremapsexplore.Factories.ImageFactory;
import com.amazon.mobile.heremapsexplore.MapObjects.MarkerWithAccuracyCircle;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import java.lang.ref.WeakReference;
/* loaded from: classes13.dex */
public class CLDBasedUserPositionManager implements UserPositionManager {
    private static final String FEATURE_ID = "feature_elements_map_view";
    private static final String TAG = "CLDUserPosition";
    private MarkerWithAccuracyCircle userLocationMarker;
    private final WeakReference<HereMapExploreView> weakMapView;
    private GeoCoordinates coordinates = null;
    private boolean displayingUserLocation = false;
    private final MapImage markerImage = MapImageFactory.fromBitmap(ImageFactory.getImage(ImageFactory.ImageId.BLUE_FILL_BLACK_STROKE_USER_LOCATION));
    private final LazyComponent<LocationSensorAccess> locationSensorAccess = ComponentRegistry.getInstance().getLazy(LocationSensorAccess.class);
    private final LocationConfigurationRequest locationTrackingRequest = new LocationConfigurationRequest();

    public CLDBasedUserPositionManager(HereMapExploreView hereMapExploreView) {
        this.weakMapView = new WeakReference<>(hereMapExploreView);
        LocationConfigurationRequest locationConfigurationRequest = this.locationTrackingRequest;
        locationConfigurationRequest.mode = 2;
        locationConfigurationRequest.locationAccuracy = 0;
        locationConfigurationRequest.minimumDeliveryTimeInterval = 5000L;
        locationConfigurationRequest.minimumDeliveryDistance = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMarker(@NonNull Location location) {
        this.coordinates = new GeoCoordinates(location.getLatitude(), location.getLongitude());
        MarkerWithAccuracyCircle markerWithAccuracyCircle = this.userLocationMarker;
        if (markerWithAccuracyCircle != null) {
            markerWithAccuracyCircle.setCoordinates(this.coordinates);
            if (location.hasAccuracy()) {
                this.userLocationMarker.setAccuracy(location.getAccuracy());
            } else {
                Log.e(TAG, "No accuracy data in location updates, using previous value instead");
            }
        }
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void displayUserLocationWithAccuracy(final boolean z) {
        this.displayingUserLocation = true;
        this.locationSensorAccess.mo10268get().locationForFeatureId(FEATURE_ID, new LocationEventHandler() { // from class: com.amazon.mobile.heremapsexplore.CLDBasedUserPositionManager.3
            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void didReceiveLocation(Location location) {
                if (location == null) {
                    Log.e(Constants.LOG_TAG, "Failed to get user location for display");
                    return;
                }
                CLDBasedUserPositionManager.this.coordinates = new GeoCoordinates(location.getLatitude(), location.getLongitude());
                CLDBasedUserPositionManager cLDBasedUserPositionManager = CLDBasedUserPositionManager.this;
                cLDBasedUserPositionManager.userLocationMarker = new MarkerWithAccuracyCircle(cLDBasedUserPositionManager.coordinates, ((HereMapExploreView) CLDBasedUserPositionManager.this.weakMapView.get()).getMapView());
                CLDBasedUserPositionManager.this.userLocationMarker.setAccuracyCircleVisibility(z);
                if (location.hasAccuracy()) {
                    CLDBasedUserPositionManager.this.userLocationMarker.setAccuracy(location.getAccuracy());
                }
                CLDBasedUserPositionManager.this.userLocationMarker.setIcon(CLDBasedUserPositionManager.this.markerImage);
                CLDBasedUserPositionManager.this.userLocationMarker.addToMap(((HereMapExploreView) CLDBasedUserPositionManager.this.weakMapView.get()).getMapView());
                String.format("Displaying user location at lat %f and long %f", Double.valueOf(CLDBasedUserPositionManager.this.coordinates.latitude), Double.valueOf(CLDBasedUserPositionManager.this.coordinates.longitude));
            }

            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void fineLocationUpdatesWillEnd() {
            }
        });
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public GeoCoordinates getCoordinates() {
        return this.coordinates;
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public boolean hasCoordinates() {
        return this.coordinates != null;
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void pollForUserLocation() {
        if (!this.displayingUserLocation) {
            return;
        }
        this.locationSensorAccess.mo10268get().subscribeForLocationUpdates(FEATURE_ID, this.locationTrackingRequest, new LocationEventHandler() { // from class: com.amazon.mobile.heremapsexplore.CLDBasedUserPositionManager.1
            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void didReceiveLocation(Location location) {
                if (location == null) {
                    Log.e(Constants.LOG_TAG, "Failed to get user location for updates");
                } else {
                    CLDBasedUserPositionManager.this.updateMarker(location);
                }
            }

            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void fineLocationUpdatesWillEnd() {
                CLDBasedUserPositionManager.this.pollForUserLocation();
            }
        });
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void setInitialUserLocation() {
        this.locationSensorAccess.mo10268get().locationForFeatureId(FEATURE_ID, new LocationEventHandler() { // from class: com.amazon.mobile.heremapsexplore.CLDBasedUserPositionManager.2
            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void didReceiveLocation(Location location) {
                if (location == null) {
                    Log.e(CLDBasedUserPositionManager.TAG, "Failed to set initial user location");
                    return;
                }
                CLDBasedUserPositionManager.this.coordinates = new GeoCoordinates(location.getLatitude(), location.getLongitude());
                String.format("Initial user location set to lat %f and long %f", Double.valueOf(CLDBasedUserPositionManager.this.coordinates.latitude), Double.valueOf(CLDBasedUserPositionManager.this.coordinates.longitude));
            }

            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void fineLocationUpdatesWillEnd() {
            }
        });
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void stopPollingForUserLocation() {
        this.locationSensorAccess.mo10268get().unsubscribeFromLocationUpdates(FEATURE_ID, 2);
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void updateUserLocationMarker() {
        this.locationSensorAccess.mo10268get().locationForFeatureId(FEATURE_ID, new LocationEventHandler() { // from class: com.amazon.mobile.heremapsexplore.CLDBasedUserPositionManager.4
            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void didReceiveLocation(Location location) {
                if (location == null) {
                    Log.e(Constants.LOG_TAG, "Failed to get user location for updates");
                } else {
                    CLDBasedUserPositionManager.this.updateMarker(location);
                }
            }

            @Override // com.amazon.alexa.sensor.api.location.LocationEventHandler
            public void fineLocationUpdatesWillEnd() {
            }
        });
    }
}
