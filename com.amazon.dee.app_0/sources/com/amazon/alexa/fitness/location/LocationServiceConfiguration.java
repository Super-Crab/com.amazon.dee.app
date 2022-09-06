package com.amazon.alexa.fitness.location;

import com.amazon.alexa.fitness.configuration.Configuration;
import kotlin.Metadata;
/* compiled from: LocationService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/location/LocationServiceConfiguration;", "Lcom/amazon/alexa/fitness/configuration/Configuration;", "()V", "distanceFilterInMeters", "", "getDistanceFilterInMeters", "()F", "setDistanceFilterInMeters", "(F)V", "locationUpdateIntervalInMillis", "", "getLocationUpdateIntervalInMillis", "()J", "setLocationUpdateIntervalInMillis", "(J)V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class LocationServiceConfiguration implements Configuration {
    private float distanceFilterInMeters = 15.0f;
    private long locationUpdateIntervalInMillis = 5000;

    public final float getDistanceFilterInMeters() {
        return this.distanceFilterInMeters;
    }

    public final long getLocationUpdateIntervalInMillis() {
        return this.locationUpdateIntervalInMillis;
    }

    public final void setDistanceFilterInMeters(float f) {
        this.distanceFilterInMeters = f;
    }

    public final void setLocationUpdateIntervalInMillis(long j) {
        this.locationUpdateIntervalInMillis = j;
    }
}
