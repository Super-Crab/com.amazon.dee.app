package com.amazon.alexa.drive.navigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.drivemode.api.DriveModeCardsProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public class CurrentLocationManager implements LocationListener {
    static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 100;
    static final long MIN_TIME_BW_UPDATES = 60000;
    private static final String TAG = CurrentLocationManager.class.getSimpleName();
    private DriveModeCardsProvider cardsProvider;
    private final Context mContext;
    private Location mCurrentLocation = null;
    private final List<LocationChangeListener> mLocationChangeListeners = new ArrayList();
    private boolean mLocationTrackingEnabled;

    /* loaded from: classes7.dex */
    public interface LocationChangeListener {
        void onLocationChanges(Location location);
    }

    public CurrentLocationManager(Context context) {
        this.mContext = context;
        startLocationTracking();
    }

    @TargetApi(23)
    private boolean checkPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    public Location getCurrentLocation() {
        if (this.mCurrentLocation == null) {
            Log.w(TAG, "Current Location is unavailable.");
        }
        return this.mCurrentLocation;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (location == null) {
            return;
        }
        this.mCurrentLocation = location;
        for (LocationChangeListener locationChangeListener : this.mLocationChangeListeners) {
            locationChangeListener.onLocationChanges(location);
        }
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void registerForLocationUpdates(@NonNull LocationChangeListener locationChangeListener) {
        if (this.mLocationChangeListeners.contains(locationChangeListener)) {
            Log.e(TAG, "Trying to add listener that is already added");
        } else {
            this.mLocationChangeListeners.add(locationChangeListener);
        }
    }

    public void setCardProvider(DriveModeCardsProvider driveModeCardsProvider) {
        this.cardsProvider = driveModeCardsProvider;
    }

    public void startLocationTracking() {
        String str;
        if (this.mLocationTrackingEnabled) {
            return;
        }
        Log.i(TAG, "startTrackingLocation");
        if (!checkPermission(this.mContext)) {
            Log.w(TAG, "Location Permission unavailable!");
            return;
        }
        try {
            LocationManager locationManager = (LocationManager) this.mContext.getSystemService("location");
            if (locationManager.isProviderEnabled("gps")) {
                str = "gps";
            } else {
                str = locationManager.isProviderEnabled("network") ? "network" : null;
            }
            if (str != null) {
                this.mCurrentLocation = locationManager.getLastKnownLocation(str);
                locationManager.requestLocationUpdates(str, 60000L, 100.0f, this);
                this.mLocationTrackingEnabled = true;
                return;
            }
            Log.w(TAG, "Location providers not available");
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Exception to getLocation: "), TAG);
        }
    }

    public void stopLocationTracking() {
        if (!this.mLocationTrackingEnabled) {
            return;
        }
        Log.i(TAG, "stopLocationTracking");
        if (!checkPermission(this.mContext)) {
            Log.w(TAG, "Location Permission unavailable!");
            return;
        }
        try {
            ((LocationManager) this.mContext.getSystemService("location")).removeUpdates(this);
            this.mLocationTrackingEnabled = false;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Exception to getLocation: "), TAG);
        }
    }

    public void unregisterForLocationUpdates(@NonNull LocationChangeListener locationChangeListener) {
        if (!this.mLocationChangeListeners.contains(locationChangeListener)) {
            Log.e(TAG, "Trying to remove listener that is not added");
        } else {
            this.mLocationChangeListeners.remove(locationChangeListener);
        }
    }
}
