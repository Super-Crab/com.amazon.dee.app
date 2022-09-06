package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/LocationSample;", "", "timestamp", "", "geolocation", "Lcom/amazon/alexa/fitness/api/afits/Geolocation;", "(Ljava/lang/String;Lcom/amazon/alexa/fitness/api/afits/Geolocation;)V", "getGeolocation", "()Lcom/amazon/alexa/fitness/api/afits/Geolocation;", "getTimestamp", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class LocationSample {
    @NotNull
    private final Geolocation geolocation;
    @NotNull
    private final String timestamp;

    public LocationSample(@NotNull String timestamp, @NotNull Geolocation geolocation) {
        Intrinsics.checkParameterIsNotNull(timestamp, "timestamp");
        Intrinsics.checkParameterIsNotNull(geolocation, "geolocation");
        this.timestamp = timestamp;
        this.geolocation = geolocation;
    }

    public static /* synthetic */ LocationSample copy$default(LocationSample locationSample, String str, Geolocation geolocation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = locationSample.timestamp;
        }
        if ((i & 2) != 0) {
            geolocation = locationSample.geolocation;
        }
        return locationSample.copy(str, geolocation);
    }

    @NotNull
    public final String component1() {
        return this.timestamp;
    }

    @NotNull
    public final Geolocation component2() {
        return this.geolocation;
    }

    @NotNull
    public final LocationSample copy(@NotNull String timestamp, @NotNull Geolocation geolocation) {
        Intrinsics.checkParameterIsNotNull(timestamp, "timestamp");
        Intrinsics.checkParameterIsNotNull(geolocation, "geolocation");
        return new LocationSample(timestamp, geolocation);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof LocationSample)) {
                return false;
            }
            LocationSample locationSample = (LocationSample) obj;
            return Intrinsics.areEqual(this.timestamp, locationSample.timestamp) && Intrinsics.areEqual(this.geolocation, locationSample.geolocation);
        }
        return true;
    }

    @NotNull
    public final Geolocation getGeolocation() {
        return this.geolocation;
    }

    @NotNull
    public final String getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        String str = this.timestamp;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Geolocation geolocation = this.geolocation;
        if (geolocation != null) {
            i = geolocation.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocationSample(timestamp=");
        outline107.append(this.timestamp);
        outline107.append(", geolocation=");
        outline107.append(this.geolocation);
        outline107.append(")");
        return outline107.toString();
    }
}
