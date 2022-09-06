package com.amazon.alexa.location.phase3.sensor.alexageofence;

import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.phase3.LocationSignal;
import com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationInput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
/* loaded from: classes9.dex */
public final class LocationOfInterest extends LocationSignal {
    private static final int PRECISION = 3;
    @VisibleForTesting
    static final String SIGNAL_ID_PAST_SIGNALS = "past_signals";
    private static final String TYPE = "ESTIMATED_LOCATION_OF_INTEREST";
    @VisibleForTesting
    final Set<String> associatedFences;
    public final double centerLatitude;
    public final double centerLongitude;
    public final String key;
    @VisibleForTesting
    final Map<String, Long> supportingSignals;
    private static final String KEY_FORMAT = String.format(Locale.US, "locationKey_%%.%df%%c_%%.%df%%c", 3, 3);
    private static final double SCALE = Math.pow(10.0d, 3.0d);

    private LocationOfInterest(double d, double d2, long j) {
        super(TYPE, 1, j);
        this.key = generateKey(d, d2);
        this.centerLatitude = d;
        this.centerLongitude = d2;
        this.associatedFences = new HashSet();
        this.supportingSignals = new HashMap();
        this.supportingSignals.put(SIGNAL_ID_PAST_SIGNALS, 0L);
    }

    public static LocationOfInterest fromLocationInput(@NonNull NativeLocationInput nativeLocationInput) {
        return new LocationOfInterest(round(nativeLocationInput.latitude), round(nativeLocationInput.longitude), nativeLocationInput.timestamp);
    }

    public static String generateKey(@NonNull NativeLocationInput nativeLocationInput) {
        return generateKey(round(nativeLocationInput.latitude), round(nativeLocationInput.longitude));
    }

    private static double round(double d) {
        return Math.round(d * SCALE) / SCALE;
    }

    public boolean associateWithFence(@NonNull ALSGeofence aLSGeofence) {
        boolean z = true;
        if (this.associatedFences.contains(aLSGeofence.getId())) {
            return true;
        }
        Location location = new Location("Location");
        location.setLatitude(this.centerLatitude);
        location.setLongitude(this.centerLongitude);
        Location location2 = new Location("Fence");
        location2.setLatitude(aLSGeofence.getCircularRegion().getLatitudeInDegrees());
        location2.setLongitude(aLSGeofence.getCircularRegion().getLongitudeInDegrees());
        if (location.distanceTo(location2) >= aLSGeofence.getCircularRegion().getRadiusInMeters()) {
            z = false;
        }
        if (z) {
            this.associatedFences.add(aLSGeofence.getId());
        }
        return z;
    }

    public long getScore() {
        long j = 0;
        for (Long l : this.supportingSignals.values()) {
            j += l.longValue();
        }
        return j;
    }

    public void removeSupportingSignal(@NonNull LocationSignal locationSignal, boolean z) {
        Long l = this.supportingSignals.get(locationSignal.id);
        if (z && l != null) {
            Map<String, Long> map = this.supportingSignals;
            map.put(SIGNAL_ID_PAST_SIGNALS, Long.valueOf(l.longValue() + map.get(SIGNAL_ID_PAST_SIGNALS).longValue()));
        }
        this.supportingSignals.remove(locationSignal.id);
    }

    public void updateSupportingSignal(@NonNull LocationSignal locationSignal, long j) {
        this.supportingSignals.put(locationSignal.id, Long.valueOf(j));
    }

    @VisibleForTesting
    static String generateKey(double d, double d2) {
        Locale locale = Locale.US;
        String str = KEY_FORMAT;
        Object[] objArr = new Object[4];
        objArr[0] = Double.valueOf(Math.abs(d));
        objArr[1] = Character.valueOf(d >= 0.0d ? 'N' : 'S');
        objArr[2] = Double.valueOf(Math.abs(d2));
        objArr[3] = Character.valueOf(d2 >= 0.0d ? 'E' : 'W');
        return String.format(locale, str, objArr);
    }
}
