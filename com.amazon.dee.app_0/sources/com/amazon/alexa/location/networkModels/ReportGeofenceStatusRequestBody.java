package com.amazon.alexa.location.networkModels;

import com.amazon.alexa.location.models.GeoFenceStatus;
import java.util.List;
/* loaded from: classes9.dex */
public class ReportGeofenceStatusRequestBody {
    private final List<GeoFenceStatus> geoFenceStatus;
    private final String personId;

    public ReportGeofenceStatusRequestBody(String str, List<GeoFenceStatus> list) {
        this.personId = str;
        this.geoFenceStatus = list;
    }

    public List<GeoFenceStatus> getGeoFenceStatus() {
        return this.geoFenceStatus;
    }

    public String getPersonId() {
        return this.personId;
    }
}
