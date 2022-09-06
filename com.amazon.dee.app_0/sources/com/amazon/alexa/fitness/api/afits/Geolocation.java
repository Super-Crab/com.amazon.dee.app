package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/Geolocation;", "", "coordinate", "Lcom/amazon/alexa/fitness/api/afits/Coordinate;", "altitude", "Lcom/amazon/alexa/fitness/api/afits/Altitude;", "heading", "Lcom/amazon/alexa/fitness/api/afits/Heading;", "speed", "Lcom/amazon/alexa/fitness/api/afits/Speed;", "(Lcom/amazon/alexa/fitness/api/afits/Coordinate;Lcom/amazon/alexa/fitness/api/afits/Altitude;Lcom/amazon/alexa/fitness/api/afits/Heading;Lcom/amazon/alexa/fitness/api/afits/Speed;)V", "getAltitude", "()Lcom/amazon/alexa/fitness/api/afits/Altitude;", "getCoordinate", "()Lcom/amazon/alexa/fitness/api/afits/Coordinate;", "getHeading", "()Lcom/amazon/alexa/fitness/api/afits/Heading;", "getSpeed", "()Lcom/amazon/alexa/fitness/api/afits/Speed;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Geolocation {
    @Nullable
    private final Altitude altitude;
    @NotNull
    private final Coordinate coordinate;
    @Nullable
    private final Heading heading;
    @Nullable
    private final Speed speed;

    public Geolocation(@NotNull Coordinate coordinate, @Nullable Altitude altitude, @Nullable Heading heading, @Nullable Speed speed) {
        Intrinsics.checkParameterIsNotNull(coordinate, "coordinate");
        this.coordinate = coordinate;
        this.altitude = altitude;
        this.heading = heading;
        this.speed = speed;
    }

    public static /* synthetic */ Geolocation copy$default(Geolocation geolocation, Coordinate coordinate, Altitude altitude, Heading heading, Speed speed, int i, Object obj) {
        if ((i & 1) != 0) {
            coordinate = geolocation.coordinate;
        }
        if ((i & 2) != 0) {
            altitude = geolocation.altitude;
        }
        if ((i & 4) != 0) {
            heading = geolocation.heading;
        }
        if ((i & 8) != 0) {
            speed = geolocation.speed;
        }
        return geolocation.copy(coordinate, altitude, heading, speed);
    }

    @NotNull
    public final Coordinate component1() {
        return this.coordinate;
    }

    @Nullable
    public final Altitude component2() {
        return this.altitude;
    }

    @Nullable
    public final Heading component3() {
        return this.heading;
    }

    @Nullable
    public final Speed component4() {
        return this.speed;
    }

    @NotNull
    public final Geolocation copy(@NotNull Coordinate coordinate, @Nullable Altitude altitude, @Nullable Heading heading, @Nullable Speed speed) {
        Intrinsics.checkParameterIsNotNull(coordinate, "coordinate");
        return new Geolocation(coordinate, altitude, heading, speed);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Geolocation)) {
                return false;
            }
            Geolocation geolocation = (Geolocation) obj;
            return Intrinsics.areEqual(this.coordinate, geolocation.coordinate) && Intrinsics.areEqual(this.altitude, geolocation.altitude) && Intrinsics.areEqual(this.heading, geolocation.heading) && Intrinsics.areEqual(this.speed, geolocation.speed);
        }
        return true;
    }

    @Nullable
    public final Altitude getAltitude() {
        return this.altitude;
    }

    @NotNull
    public final Coordinate getCoordinate() {
        return this.coordinate;
    }

    @Nullable
    public final Heading getHeading() {
        return this.heading;
    }

    @Nullable
    public final Speed getSpeed() {
        return this.speed;
    }

    public int hashCode() {
        Coordinate coordinate = this.coordinate;
        int i = 0;
        int hashCode = (coordinate != null ? coordinate.hashCode() : 0) * 31;
        Altitude altitude = this.altitude;
        int hashCode2 = (hashCode + (altitude != null ? altitude.hashCode() : 0)) * 31;
        Heading heading = this.heading;
        int hashCode3 = (hashCode2 + (heading != null ? heading.hashCode() : 0)) * 31;
        Speed speed = this.speed;
        if (speed != null) {
            i = speed.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Geolocation(coordinate=");
        outline107.append(this.coordinate);
        outline107.append(", altitude=");
        outline107.append(this.altitude);
        outline107.append(", heading=");
        outline107.append(this.heading);
        outline107.append(", speed=");
        outline107.append(this.speed);
        outline107.append(")");
        return outline107.toString();
    }
}
