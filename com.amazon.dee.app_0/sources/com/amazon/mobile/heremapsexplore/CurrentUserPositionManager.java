package com.amazon.mobile.heremapsexplore;

import android.location.Location;
import android.os.Handler;
import android.util.Log;
import com.amazon.alexa.location.LocationProvider;
import com.amazon.mobile.heremapsexplore.Constants.Constants;
import com.amazon.mobile.heremapsexplore.Factories.ImageFactory;
import com.amazon.mobile.heremapsexplore.MapObjects.MarkerWithAccuracyCircle;
import com.facebook.react.uimanager.ThemedReactContext;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import io.reactivex.rxjava3.functions.Consumer;
import java.lang.ref.WeakReference;
/* loaded from: classes13.dex */
public class CurrentUserPositionManager implements UserPositionManager {
    private static final int POLLING_DELAY = 5000;
    private GeoCoordinates coordinates;
    private LocationProvider locationProvider;
    private Handler updatePositionHandler;
    private MarkerWithAccuracyCircle userLocationMarker;
    private WeakReference<HereMapExploreView> weakMapView;
    private boolean displayingUserLocation = false;
    private Runnable updatePositionRunnable = new Runnable() { // from class: com.amazon.mobile.heremapsexplore.CurrentUserPositionManager.1
        @Override // java.lang.Runnable
        public void run() {
            CurrentUserPositionManager.this.updateUserLocationMarker();
            CurrentUserPositionManager.this.updatePositionHandler.postDelayed(this, 5000L);
        }
    };
    private MapImage markerImage = MapImageFactory.fromBitmap(ImageFactory.getImage(ImageFactory.ImageId.BLUE_FILL_BLACK_STROKE_USER_LOCATION));

    public CurrentUserPositionManager(HereMapExploreView hereMapExploreView, ThemedReactContext themedReactContext) {
        this.weakMapView = new WeakReference<>(hereMapExploreView);
        this.locationProvider = new LocationProvider(themedReactContext);
        setInitialUserLocation();
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void displayUserLocationWithAccuracy(final boolean z) {
        this.displayingUserLocation = true;
        this.locationProvider.getMostRecentLocation().subscribe(new Consumer() { // from class: com.amazon.mobile.heremapsexplore.-$$Lambda$CurrentUserPositionManager$5FwBQ5BXEBhNJ65VCFN58KWGfSo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CurrentUserPositionManager.this.lambda$displayUserLocationWithAccuracy$2$CurrentUserPositionManager(z, (Location) obj);
            }
        }, $$Lambda$CurrentUserPositionManager$7vK0yGdqM4P5KSs6pV2sJg13iO0.INSTANCE);
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public GeoCoordinates getCoordinates() {
        return this.coordinates;
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public boolean hasCoordinates() {
        return this.coordinates != null;
    }

    public /* synthetic */ void lambda$displayUserLocationWithAccuracy$2$CurrentUserPositionManager(boolean z, Location location) throws Throwable {
        try {
            this.coordinates = new GeoCoordinates(location.getLatitude(), location.getLongitude());
            this.userLocationMarker = new MarkerWithAccuracyCircle(this.coordinates, this.weakMapView.get().getMapView());
            this.userLocationMarker.setAccuracyCircleVisibility(z);
            if (location.hasAccuracy()) {
                this.userLocationMarker.setAccuracy(location.getAccuracy());
            }
            this.userLocationMarker.setIcon(this.markerImage);
            this.userLocationMarker.addToMap(this.weakMapView.get().getMapView());
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG, "Failed to update most recent user location", e);
        }
    }

    public /* synthetic */ void lambda$setInitialUserLocation$0$CurrentUserPositionManager(Location location) throws Throwable {
        this.coordinates = new GeoCoordinates(location.getLatitude(), location.getLongitude());
    }

    public /* synthetic */ void lambda$updateUserLocationMarker$4$CurrentUserPositionManager(Location location) throws Throwable {
        this.coordinates = new GeoCoordinates(location.getLatitude(), location.getLongitude());
        MarkerWithAccuracyCircle markerWithAccuracyCircle = this.userLocationMarker;
        if (markerWithAccuracyCircle != null) {
            markerWithAccuracyCircle.setCoordinates(this.coordinates);
            if (location.hasAccuracy()) {
                this.userLocationMarker.setAccuracy(location.getAccuracy());
            } else {
                Log.e(Constants.LOG_TAG, "Could not get updated user location accuracy, using previous value instead");
            }
        }
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void pollForUserLocation() {
        if (!this.displayingUserLocation) {
            return;
        }
        if (this.updatePositionHandler == null) {
            this.updatePositionHandler = new Handler();
        }
        this.updatePositionHandler.removeCallbacks(this.updatePositionRunnable);
        this.updatePositionHandler.postDelayed(this.updatePositionRunnable, 5000L);
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void setInitialUserLocation() {
        this.locationProvider.getMostRecentLocation().subscribe(new Consumer() { // from class: com.amazon.mobile.heremapsexplore.-$$Lambda$CurrentUserPositionManager$yEG98JTacdDInoaRYqpDzWK-ZTM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CurrentUserPositionManager.this.lambda$setInitialUserLocation$0$CurrentUserPositionManager((Location) obj);
            }
        }, $$Lambda$CurrentUserPositionManager$0QI4ejsHnsPHcY3bMEBCFNnOwY4.INSTANCE);
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void stopPollingForUserLocation() {
        Handler handler = this.updatePositionHandler;
        if (handler != null) {
            handler.removeCallbacks(this.updatePositionRunnable);
            this.updatePositionHandler = null;
        }
    }

    @Override // com.amazon.mobile.heremapsexplore.UserPositionManager
    public void updateUserLocationMarker() {
        this.locationProvider.getMostRecentLocation().subscribe(new Consumer() { // from class: com.amazon.mobile.heremapsexplore.-$$Lambda$CurrentUserPositionManager$inM2-RxESLMi2fI3ald_IqGm6c4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CurrentUserPositionManager.this.lambda$updateUserLocationMarker$4$CurrentUserPositionManager((Location) obj);
            }
        }, $$Lambda$CurrentUserPositionManager$1lvlVNxzNFkNBPmDFAw4GukEdI.INSTANCE);
    }
}
