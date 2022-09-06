package com.amazon.alexa.location.phase3.sensor.nativelocation;

import android.location.Location;
import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.alexa.location.phase3.LocationSignal;
/* loaded from: classes9.dex */
public class NativeLocationInput extends LocationSignal {
    public final double altitude;
    public final float bearing;
    public final float bearingAccuracy;
    public final float horizontalAccuracy;
    public final double latitude;
    public final double longitude;
    public final float speed;
    public final float speedAccuracy;

    public NativeLocationInput(@NonNull Location location) {
        super("SENSOR_INPUT_NATIVE_LOCATION", 1, location.getTime());
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.altitude = location.getAltitude();
        this.speed = location.getSpeed();
        this.bearing = location.getBearing();
        this.horizontalAccuracy = location.getAccuracy();
        int i = Build.VERSION.SDK_INT;
        this.speedAccuracy = location.getSpeedAccuracyMetersPerSecond();
        this.bearingAccuracy = location.getBearingAccuracyDegrees();
    }
}
