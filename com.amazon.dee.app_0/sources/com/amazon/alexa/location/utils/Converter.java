package com.amazon.alexa.location.utils;

import com.amazon.alexa.location.models.ALSGeofence;
import com.google.android.gms.location.Geofence;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public final class Converter {
    private Converter() {
    }

    public static List<Geofence> convertToGeofences(List<ALSGeofence> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (ALSGeofence aLSGeofence : list) {
                arrayList.add(aLSGeofence.toGeofence());
            }
        }
        return arrayList;
    }

    public static List<String> extractGeofenceIds(List<ALSGeofence> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (ALSGeofence aLSGeofence : list) {
                arrayList.add(aLSGeofence.getId());
            }
        }
        return arrayList;
    }
}
