package com.amazon.alexa.location.models;

import java.util.Objects;
/* loaded from: classes9.dex */
public class GeoFenceStatus {
    private String fenceId;
    private StatusCode statusCode;

    public GeoFenceStatus(String str, StatusCode statusCode) {
        this.fenceId = str;
        this.statusCode = statusCode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ALSGeofence)) {
            return false;
        }
        GeoFenceStatus geoFenceStatus = (GeoFenceStatus) obj;
        String str = this.fenceId;
        String str2 = geoFenceStatus.fenceId;
        return (str == str2 || str.equals(str2)) && Objects.equals(this.statusCode, geoFenceStatus.statusCode);
    }

    public String getFenceId() {
        return this.fenceId;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        String str = this.fenceId;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 31) * 31;
        StatusCode statusCode = this.statusCode;
        if (statusCode != null) {
            i = statusCode.hashCode();
        }
        return hashCode + i;
    }
}
