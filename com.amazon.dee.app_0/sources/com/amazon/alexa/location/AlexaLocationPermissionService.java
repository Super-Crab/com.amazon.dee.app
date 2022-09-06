package com.amazon.alexa.location;

import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
/* loaded from: classes9.dex */
public class AlexaLocationPermissionService implements LocationPermissionService {
    private static final String ACCESS_BACKGROUND_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    private Context context;

    public AlexaLocationPermissionService(Context context) {
        this.context = context;
    }

    @Override // com.amazon.alexa.location.LocationPermissionService
    public boolean hasAccessBackgroundLocationPermission() {
        return Build.VERSION.SDK_INT < 29 || ContextCompat.checkSelfPermission(this.context, ACCESS_BACKGROUND_LOCATION_PERMISSION) == 0;
    }

    @Override // com.amazon.alexa.location.LocationPermissionService
    public boolean hasAccessFineLocationPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    @Override // com.amazon.alexa.location.LocationPermissionService
    public boolean hasFullLocationPermission() {
        return hasAccessFineLocationPermission() && hasAccessBackgroundLocationPermission();
    }
}
