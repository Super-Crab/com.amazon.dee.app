package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.fitness.api.util.DateTime;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003JC\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/LocationSample;", "", "dateTime", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "coordinate", "Lcom/amazon/alexa/fitness/api/afx/Coordinate;", "altitude", "Lcom/amazon/alexa/fitness/api/afx/GPSValue;", "speed", "heading", "(Lcom/amazon/alexa/fitness/api/util/DateTime;Lcom/amazon/alexa/fitness/api/afx/Coordinate;Lcom/amazon/alexa/fitness/api/afx/GPSValue;Lcom/amazon/alexa/fitness/api/afx/GPSValue;Lcom/amazon/alexa/fitness/api/afx/GPSValue;)V", "getAltitude", "()Lcom/amazon/alexa/fitness/api/afx/GPSValue;", "getCoordinate", "()Lcom/amazon/alexa/fitness/api/afx/Coordinate;", "getDateTime", "()Lcom/amazon/alexa/fitness/api/util/DateTime;", "getHeading", "getSpeed", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class LocationSample {
    @Nullable
    private final GPSValue altitude;
    @Nullable
    private final Coordinate coordinate;
    @NotNull
    private final DateTime dateTime;
    @Nullable
    private final GPSValue heading;
    @Nullable
    private final GPSValue speed;

    public LocationSample(@NotNull DateTime dateTime, @Nullable Coordinate coordinate, @Nullable GPSValue gPSValue, @Nullable GPSValue gPSValue2, @Nullable GPSValue gPSValue3) {
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        this.dateTime = dateTime;
        this.coordinate = coordinate;
        this.altitude = gPSValue;
        this.speed = gPSValue2;
        this.heading = gPSValue3;
    }

    public static /* synthetic */ LocationSample copy$default(LocationSample locationSample, DateTime dateTime, Coordinate coordinate, GPSValue gPSValue, GPSValue gPSValue2, GPSValue gPSValue3, int i, Object obj) {
        if ((i & 1) != 0) {
            dateTime = locationSample.dateTime;
        }
        if ((i & 2) != 0) {
            coordinate = locationSample.coordinate;
        }
        Coordinate coordinate2 = coordinate;
        if ((i & 4) != 0) {
            gPSValue = locationSample.altitude;
        }
        GPSValue gPSValue4 = gPSValue;
        if ((i & 8) != 0) {
            gPSValue2 = locationSample.speed;
        }
        GPSValue gPSValue5 = gPSValue2;
        if ((i & 16) != 0) {
            gPSValue3 = locationSample.heading;
        }
        return locationSample.copy(dateTime, coordinate2, gPSValue4, gPSValue5, gPSValue3);
    }

    @NotNull
    public final DateTime component1() {
        return this.dateTime;
    }

    @Nullable
    public final Coordinate component2() {
        return this.coordinate;
    }

    @Nullable
    public final GPSValue component3() {
        return this.altitude;
    }

    @Nullable
    public final GPSValue component4() {
        return this.speed;
    }

    @Nullable
    public final GPSValue component5() {
        return this.heading;
    }

    @NotNull
    public final LocationSample copy(@NotNull DateTime dateTime, @Nullable Coordinate coordinate, @Nullable GPSValue gPSValue, @Nullable GPSValue gPSValue2, @Nullable GPSValue gPSValue3) {
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        return new LocationSample(dateTime, coordinate, gPSValue, gPSValue2, gPSValue3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof LocationSample)) {
                return false;
            }
            LocationSample locationSample = (LocationSample) obj;
            return Intrinsics.areEqual(this.dateTime, locationSample.dateTime) && Intrinsics.areEqual(this.coordinate, locationSample.coordinate) && Intrinsics.areEqual(this.altitude, locationSample.altitude) && Intrinsics.areEqual(this.speed, locationSample.speed) && Intrinsics.areEqual(this.heading, locationSample.heading);
        }
        return true;
    }

    @Nullable
    public final GPSValue getAltitude() {
        return this.altitude;
    }

    @Nullable
    public final Coordinate getCoordinate() {
        return this.coordinate;
    }

    @NotNull
    public final DateTime getDateTime() {
        return this.dateTime;
    }

    @Nullable
    public final GPSValue getHeading() {
        return this.heading;
    }

    @Nullable
    public final GPSValue getSpeed() {
        return this.speed;
    }

    public int hashCode() {
        DateTime dateTime = this.dateTime;
        int i = 0;
        int hashCode = (dateTime != null ? dateTime.hashCode() : 0) * 31;
        Coordinate coordinate = this.coordinate;
        int hashCode2 = (hashCode + (coordinate != null ? coordinate.hashCode() : 0)) * 31;
        GPSValue gPSValue = this.altitude;
        int hashCode3 = (hashCode2 + (gPSValue != null ? gPSValue.hashCode() : 0)) * 31;
        GPSValue gPSValue2 = this.speed;
        int hashCode4 = (hashCode3 + (gPSValue2 != null ? gPSValue2.hashCode() : 0)) * 31;
        GPSValue gPSValue3 = this.heading;
        if (gPSValue3 != null) {
            i = gPSValue3.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocationSample(dateTime=");
        outline107.append(this.dateTime);
        outline107.append(", coordinate=");
        outline107.append(this.coordinate);
        outline107.append(", altitude=");
        outline107.append(this.altitude);
        outline107.append(", speed=");
        outline107.append(this.speed);
        outline107.append(", heading=");
        outline107.append(this.heading);
        outline107.append(")");
        return outline107.toString();
    }
}
