package com.amazon.alexa.location.networkModels;

import com.amazon.alexa.location.models.ALSTriggerEvent;
/* loaded from: classes9.dex */
public class TriggerGeofenceRequestBody {
    private ALSTriggerEvent geoFenceEvent;
    private String personId;
    private long timestamp;

    public TriggerGeofenceRequestBody(String str, ALSTriggerEvent aLSTriggerEvent, long j) {
        this.personId = str;
        this.geoFenceEvent = aLSTriggerEvent;
        this.timestamp = j;
    }

    public ALSTriggerEvent getGeoFenceEvent() {
        return this.geoFenceEvent;
    }

    public String getPersonId() {
        return this.personId;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
