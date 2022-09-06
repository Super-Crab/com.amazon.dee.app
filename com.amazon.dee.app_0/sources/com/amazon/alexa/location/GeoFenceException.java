package com.amazon.alexa.location;

import com.amazon.alexa.location.models.ALSGeofence;
import java.util.List;
/* loaded from: classes9.dex */
public class GeoFenceException extends LocationException {
    private List<ALSGeofence> listOfGeofences;

    public GeoFenceException(LocationErrorCode locationErrorCode, String str, List<ALSGeofence> list) {
        this(locationErrorCode, str, list, null);
    }

    public List<ALSGeofence> getListOfGeofences() {
        return this.listOfGeofences;
    }

    public GeoFenceException(LocationErrorCode locationErrorCode, String str, List<ALSGeofence> list, Throwable th) {
        super(locationErrorCode, str, th);
        this.listOfGeofences = list;
    }
}
