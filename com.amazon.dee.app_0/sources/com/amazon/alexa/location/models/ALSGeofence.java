package com.amazon.alexa.location.models;

import com.google.android.gms.location.Geofence;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class ALSGeofence {
    private ALSCircularRegion circularRegion;
    private String id;
    private List<ALSTriggerEvent> monitorEvents = new ArrayList();

    public ALSGeofence(String str, double d, double d2, double d3) {
        this.id = str;
        this.circularRegion = new ALSCircularRegion(d, d2, d3);
        this.monitorEvents.add(ALSTriggerEvent.ENTER);
        this.monitorEvents.add(ALSTriggerEvent.EXIT);
    }

    public boolean equals(Object obj) {
        ALSCircularRegion aLSCircularRegion;
        ALSCircularRegion aLSCircularRegion2;
        List<ALSTriggerEvent> list;
        List<ALSTriggerEvent> list2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ALSGeofence)) {
            return false;
        }
        ALSGeofence aLSGeofence = (ALSGeofence) obj;
        String str = this.id;
        String str2 = aLSGeofence.id;
        return (str == str2 || str.equals(str2)) && ((aLSCircularRegion = this.circularRegion) == (aLSCircularRegion2 = aLSGeofence.circularRegion) || aLSCircularRegion.equals(aLSCircularRegion2)) && ((list = this.monitorEvents) == (list2 = aLSGeofence.monitorEvents) || list.equals(list2));
    }

    public ALSCircularRegion getCircularRegion() {
        return this.circularRegion;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 31) * 31;
        ALSCircularRegion aLSCircularRegion = this.circularRegion;
        int hashCode2 = (hashCode + (aLSCircularRegion != null ? aLSCircularRegion.hashCode() : 0)) * 31;
        List<ALSTriggerEvent> list = this.monitorEvents;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }

    public Geofence toGeofence() {
        return new Geofence.Builder().setRequestId(getId()).setCircularRegion(getCircularRegion().getLatitudeInDegrees(), getCircularRegion().getLongitudeInDegrees(), (float) getCircularRegion().getRadiusInMeters()).setTransitionTypes(3).setExpirationDuration(-1L).build();
    }
}
