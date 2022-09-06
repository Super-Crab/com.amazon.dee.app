package com.amazon.whisperjoin.deviceprovisioningservice.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
/* loaded from: classes13.dex */
public class LocationPermissionsHelper {
    private final Context mContext;

    public LocationPermissionsHelper(Context context) {
        this.mContext = context;
    }

    @TargetApi(23)
    public boolean isAppLocationPermissionGranted() {
        return this.mContext.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public boolean isLocationPermissionNeededForBLEScanning() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public boolean isSystemLocationServicesEnabled() {
        int i = Build.VERSION.SDK_INT;
        try {
            return Settings.Secure.getInt(this.mContext.getContentResolver(), "location_mode") != 0;
        } catch (Settings.SettingNotFoundException unused) {
            return true;
        }
    }

    public boolean requiresGrantingAppLocationPermissionBeforeBLEScanning() {
        int i = Build.VERSION.SDK_INT;
        return 1 != 0 && !isAppLocationPermissionGranted();
    }
}
