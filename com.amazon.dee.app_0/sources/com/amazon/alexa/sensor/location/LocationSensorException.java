package com.amazon.alexa.sensor.location;

import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class LocationSensorException extends Exception {
    public static final int ERROR_CODE_API_CALL_NO_LOCATION_DATA = 16386;
    public static final int ERROR_CODE_API_CALL_UNSUCCESSFUL = 16385;
    public static final int ERROR_CODE_CLIENT_NOT_AUTHORIZED = 4097;
    public static final int ERROR_CODE_LOCATION_NOT_AVAILABLE_LOCATION_DISABLED = 8194;
    public static final int ERROR_CODE_LOCATION_NOT_AVAILABLE_NO_GOOGLE_PLAY_SERVICES = 8193;
    public static final int ERROR_CODE_MISSING_DEVICE_PERMISSION_BACKGROUND_LOCATION = 12292;
    public static final int ERROR_CODE_MISSING_DEVICE_PERMISSION_FINE_LOCATION = 12290;
    public static final int ERROR_CODE_MISSING_DEVICE_PERMISSION_GENERAL_LOCATION = 12289;
    public static final int ERROR_CODE_UNKNOWN_ERROR = 36864;
    public final int errorCode;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface ErrorCodes {
    }

    public LocationSensorException(int i, @Nullable Throwable th) {
        super(generateErrorMessage(i), th);
        this.errorCode = i;
    }

    public static String generateErrorMessage(int i) {
        return i != 4097 ? i != 12292 ? i != 8193 ? i != 8194 ? i != 12289 ? i != 12290 ? i != 16385 ? i != 16386 ? "Unknown location sensor error" : "API called but didn't receive any location data" : "API call was marked as unsuccessful" : "App doesn't have permissions to access fine location" : "App doesn't have permissions to access location" : "Location features on this device are disabled" : "Google Play Services are not available on this device" : "App doesn't have permissions to access location from background" : "Client isn't authorized to query for location";
    }
}
