package com.amazon.alexa.location.networking.als.networkModels;

import com.amazon.alexa.location.networking.als.models.ALSGeofence;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class GetGeofenceByDeviceResponseBody {
    private List<ALSGeofence> geoFences;

    public GetGeofenceByDeviceResponseBody(List<ALSGeofence> list) {
        this.geoFences = new ArrayList();
        this.geoFences = list;
    }

    public List<ALSGeofence> getGeofences() {
        return this.geoFences;
    }
}
