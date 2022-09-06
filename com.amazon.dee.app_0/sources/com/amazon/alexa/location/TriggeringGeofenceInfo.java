package com.amazon.alexa.location;

import com.amazon.alexa.location.models.ALSTriggerEvent;
/* loaded from: classes9.dex */
public class TriggeringGeofenceInfo {
    private String fenceId;
    private int geofenceTransition;
    private long time;

    public TriggeringGeofenceInfo(String str, int i, long j) {
        this.fenceId = str;
        this.geofenceTransition = i;
        this.time = j;
    }

    public ALSTriggerEvent getALSTrigerEvent() {
        int i = this.geofenceTransition;
        if (i != 1) {
            if (i != 2) {
                return ALSTriggerEvent.EXIT;
            }
            return ALSTriggerEvent.EXIT;
        }
        return ALSTriggerEvent.ENTER;
    }

    public String getFenceId() {
        return this.fenceId;
    }

    public long getTime() {
        return this.time;
    }
}
