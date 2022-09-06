package com.amazon.alexa.sensor.api.location;
/* loaded from: classes10.dex */
public class LocationAuthorization {
    public final boolean isFeatureAuthorized;
    public final boolean isLocationAccessAuthorized;
    public final boolean isPreciseLocationAuthorized;

    public LocationAuthorization(boolean z, boolean z2, boolean z3) {
        this.isFeatureAuthorized = z;
        this.isLocationAccessAuthorized = z2;
        this.isPreciseLocationAuthorized = z3;
    }
}
