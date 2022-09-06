package com.amazon.alexa.fitness.sdk;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.location.FusedLocationProviderClient;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SampleDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013¨\u0006)"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/LocationSampleData;", "", "latitude", "", "longitude", "altitude", "horizontalAccuracy", "", FusedLocationProviderClient.KEY_VERTICAL_ACCURACY, "heading", "speed", "collectedAt", "", "(DDDFFFFJ)V", "getAltitude", "()D", "getCollectedAt", "()J", "getHeading", "()F", "getHorizontalAccuracy", "getLatitude", "getLongitude", "getSpeed", "getVerticalAccuracy", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class LocationSampleData {
    private final double altitude;
    private final long collectedAt;
    private final float heading;
    private final float horizontalAccuracy;
    private final double latitude;
    private final double longitude;
    private final float speed;
    private final float verticalAccuracy;

    public LocationSampleData(double d, double d2, double d3, float f, float f2, float f3, float f4, long j) {
        this.latitude = d;
        this.longitude = d2;
        this.altitude = d3;
        this.horizontalAccuracy = f;
        this.verticalAccuracy = f2;
        this.heading = f3;
        this.speed = f4;
        this.collectedAt = j;
    }

    public final double component1() {
        return this.latitude;
    }

    public final double component2() {
        return this.longitude;
    }

    public final double component3() {
        return this.altitude;
    }

    public final float component4() {
        return this.horizontalAccuracy;
    }

    public final float component5() {
        return this.verticalAccuracy;
    }

    public final float component6() {
        return this.heading;
    }

    public final float component7() {
        return this.speed;
    }

    public final long component8() {
        return this.collectedAt;
    }

    @NotNull
    public final LocationSampleData copy(double d, double d2, double d3, float f, float f2, float f3, float f4, long j) {
        return new LocationSampleData(d, d2, d3, f, f2, f3, f4, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof LocationSampleData)) {
                return false;
            }
            LocationSampleData locationSampleData = (LocationSampleData) obj;
            return Double.compare(this.latitude, locationSampleData.latitude) == 0 && Double.compare(this.longitude, locationSampleData.longitude) == 0 && Double.compare(this.altitude, locationSampleData.altitude) == 0 && Float.compare(this.horizontalAccuracy, locationSampleData.horizontalAccuracy) == 0 && Float.compare(this.verticalAccuracy, locationSampleData.verticalAccuracy) == 0 && Float.compare(this.heading, locationSampleData.heading) == 0 && Float.compare(this.speed, locationSampleData.speed) == 0 && this.collectedAt == locationSampleData.collectedAt;
        }
        return true;
    }

    public final double getAltitude() {
        return this.altitude;
    }

    public final long getCollectedAt() {
        return this.collectedAt;
    }

    public final float getHeading() {
        return this.heading;
    }

    public final float getHorizontalAccuracy() {
        return this.horizontalAccuracy;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final float getVerticalAccuracy() {
        return this.verticalAccuracy;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        long doubleToLongBits3 = Double.doubleToLongBits(this.altitude);
        int floatToIntBits = Float.floatToIntBits(this.horizontalAccuracy);
        int floatToIntBits2 = Float.floatToIntBits(this.verticalAccuracy);
        int floatToIntBits3 = Float.floatToIntBits(this.heading);
        int floatToIntBits4 = Float.floatToIntBits(this.speed);
        long j = this.collectedAt;
        return ((floatToIntBits4 + ((floatToIntBits3 + ((floatToIntBits2 + ((floatToIntBits + (((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31)) * 31)) * 31)) * 31)) * 31) + ((int) ((j >>> 32) ^ j));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocationSampleData(latitude=");
        outline107.append(this.latitude);
        outline107.append(", longitude=");
        outline107.append(this.longitude);
        outline107.append(", altitude=");
        outline107.append(this.altitude);
        outline107.append(", horizontalAccuracy=");
        outline107.append(this.horizontalAccuracy);
        outline107.append(", verticalAccuracy=");
        outline107.append(this.verticalAccuracy);
        outline107.append(", heading=");
        outline107.append(this.heading);
        outline107.append(", speed=");
        outline107.append(this.speed);
        outline107.append(", collectedAt=");
        return GeneratedOutlineSupport1.outline87(outline107, this.collectedAt, ")");
    }
}
