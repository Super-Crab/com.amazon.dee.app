package com.amazon.alexa.location.phase3.sensor.osgeofence;

import androidx.annotation.NonNull;
import com.amazon.alexa.location.phase3.evaluator.GeofenceTriggerEvent;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class OsGeofenceTriggerEvent extends GeofenceTriggerEvent {
    public OsGeofenceTriggerEvent(long j, int i, @NonNull String str) {
        super("OsGeofenceTriggerEvent", j, i, str);
    }

    @NonNull
    public static List<OsGeofenceTriggerEvent> fromGeofencingEvent(@NonNull GeofencingEvent geofencingEvent, long j) {
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        int i = 2;
        if (geofenceTransition == 1) {
            i = 0;
        } else if (geofenceTransition == 2) {
            i = 1;
        }
        if (geofencingEvent.getTriggeringLocation() != null) {
            j = geofencingEvent.getTriggeringLocation().getTime();
        }
        ArrayList arrayList = new ArrayList(geofencingEvent.getTriggeringGeofences().size());
        for (Geofence geofence : geofencingEvent.getTriggeringGeofences()) {
            arrayList.add(new OsGeofenceTriggerEvent(j, i, geofence.getRequestId()));
        }
        return arrayList;
    }
}
