package com.amazon.alexa.accessorykit.findmy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public class AccessoryAndroidLocationProvider implements LocationProvider {
    private static final String TAG = "AccessoryAndroidLocationProvider";
    private final Context context;
    private final LocationManager locationManager;

    public AccessoryAndroidLocationProvider(Context context, LocationManager locationManager) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(locationManager, "locationManager");
        this.context = context;
        this.locationManager = locationManager;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationProvider
    @SuppressLint({"MissingPermission"})
    public Single<Location> getLatestLocation() {
        if (LocationPermissionUtils.hasNoAccessFineLocationPermission(this.context)) {
            return Single.error(new RuntimeException("Location permission is not granted."));
        }
        Location location = null;
        for (String str : this.locationManager.getProviders(true)) {
            if (this.locationManager.getLastKnownLocation(str) != null) {
                Location lastKnownLocation = this.locationManager.getLastKnownLocation(str);
                if (location == null || location.getAccuracy() < lastKnownLocation.getAccuracy()) {
                    location = lastKnownLocation;
                }
            }
        }
        if (location == null) {
            return Single.error(new RuntimeException("Fail to retrieve last location from OS using LocationManager."));
        }
        Logger.d("%s: Last location is retrieved from OS: Latitude= %f, Longitude=%f", TAG, Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()));
        return Single.just(location);
    }
}
