package com.amazon.alexa.sensor.api.location;
/* loaded from: classes10.dex */
public class LocationConfiguration {
    public final boolean isCoarseLocationOn;
    public final boolean isFineLocationOn;
    public final int locationAccuracy;

    public LocationConfiguration(boolean z, boolean z2, int i) {
        this.isCoarseLocationOn = z;
        this.isFineLocationOn = z2;
        this.locationAccuracy = i;
    }
}
