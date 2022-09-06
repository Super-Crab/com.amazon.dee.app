package com.amazon.alexa.location.phase3.sensor.alexageofence;

import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.phase3.LocationSignal;
import com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationInput;
/* loaded from: classes9.dex */
public class AlexaGeofenceState extends LocationSignal {
    public static final String INPUT_ID_INITIAL = "INITIAL_STATE";
    public static final String STATE_IN_FENCE = "STATE_IN_FENCE";
    public static final String STATE_OUT_OF_FENCE = "STATE_OUT_OF_FENCE";
    public static final String STATE_UNDETERMINED = "STATE_UNDETERMINED";
    private static final String TYPE = "ESTIMATED_STATE_ALEXA_GEOFENCE";
    public final int estimatedScore;
    public final String geofenceId;
    public final String locationInputId;
    public final String state;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaGeofenceState(@NonNull ALSGeofence aLSGeofence, @NonNull NativeLocationInput nativeLocationInput) {
        super(TYPE, -1, nativeLocationInput.timestamp);
        this.geofenceId = aLSGeofence.getId();
        this.estimatedScore = estimateScore(aLSGeofence, nativeLocationInput);
        this.state = determineState(this.estimatedScore);
        this.locationInputId = nativeLocationInput.id;
    }

    @VisibleForTesting
    static String determineState(int i) {
        return i == 0 ? STATE_OUT_OF_FENCE : i == 100 ? STATE_IN_FENCE : STATE_UNDETERMINED;
    }

    @VisibleForTesting
    static float distanceBetweenCenters(@NonNull ALSGeofence aLSGeofence, @NonNull NativeLocationInput nativeLocationInput) {
        Location location = new Location("CurrentLocation");
        location.setLatitude(nativeLocationInput.latitude);
        location.setLongitude(nativeLocationInput.longitude);
        Location location2 = new Location("Geofence");
        location2.setLatitude(aLSGeofence.getCircularRegion().getLatitudeInDegrees());
        location2.setLongitude(aLSGeofence.getCircularRegion().getLongitudeInDegrees());
        return location.distanceTo(location2);
    }

    @VisibleForTesting
    static int estimateScore(@NonNull ALSGeofence aLSGeofence, @NonNull NativeLocationInput nativeLocationInput) {
        return ((double) distanceBetweenCenters(aLSGeofence, nativeLocationInput)) < aLSGeofence.getCircularRegion().getRadiusInMeters() ? 100 : 0;
    }

    @VisibleForTesting
    AlexaGeofenceState(@NonNull ALSGeofence aLSGeofence, long j) {
        super(TYPE, -1, j);
        this.geofenceId = aLSGeofence.getId();
        this.estimatedScore = -1;
        this.state = STATE_UNDETERMINED;
        this.locationInputId = INPUT_ID_INITIAL;
    }
}
