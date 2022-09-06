package com.here.sdk.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.Date;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class Location {
    @Nullable
    public Double bearingInDegrees;
    @NonNull
    public GeoCoordinates coordinates;
    @Nullable
    public Double horizontalAccuracyInMeters;
    @Nullable
    public Double speedInMetersPerSecond;
    @NonNull
    public Date timestamp;
    @Nullable
    public Double verticalAccuracyInMeters;

    @Deprecated
    /* loaded from: classes3.dex */
    public static class Builder {
        private GeoCoordinates coordinates = null;
        private Double bearingInDegrees = null;
        private Double speedInMetersPerSecond = null;
        private Date timestamp = null;
        private Double horizontalAccuracyInMeters = null;
        private Double verticalAccuracyInMeters = null;

        /* loaded from: classes3.dex */
        public class FinalBuilder {
            FinalBuilder() {
            }

            public Location build() {
                return new Location(Builder.this.coordinates, Builder.this.bearingInDegrees, Builder.this.speedInMetersPerSecond, Builder.this.timestamp, Builder.this.horizontalAccuracyInMeters, Builder.this.verticalAccuracyInMeters);
            }

            public FinalBuilder setBearingInDegrees(Double d) {
                Builder.this.bearingInDegrees = d;
                return this;
            }

            public FinalBuilder setCoordinates(GeoCoordinates geoCoordinates) {
                Builder.this.coordinates = geoCoordinates;
                return this;
            }

            public FinalBuilder setHorizontalAccuracyInMeters(Double d) {
                Builder.this.horizontalAccuracyInMeters = d;
                return this;
            }

            public FinalBuilder setSpeedInMetersPerSecond(Double d) {
                Builder.this.speedInMetersPerSecond = d;
                return this;
            }

            public FinalBuilder setTimestamp(Date date) {
                Builder.this.timestamp = date;
                return this;
            }

            public FinalBuilder setVerticalAccuracyInMeters(Double d) {
                Builder.this.verticalAccuracyInMeters = d;
                return this;
            }
        }

        /* loaded from: classes3.dex */
        public class PartialBuilder1 {
            PartialBuilder1() {
            }

            public FinalBuilder setTimestamp(Date date) {
                Builder.this.timestamp = date;
                return new FinalBuilder();
            }
        }

        public PartialBuilder1 setCoordinates(GeoCoordinates geoCoordinates) {
            this.coordinates = geoCoordinates;
            return new PartialBuilder1();
        }
    }

    public Location(@NonNull GeoCoordinates geoCoordinates, @Nullable Double d, @Nullable Double d2, @NonNull Date date, @Nullable Double d3, @Nullable Double d4) {
        this.coordinates = geoCoordinates;
        this.bearingInDegrees = d;
        this.speedInMetersPerSecond = d2;
        this.timestamp = date;
        this.horizontalAccuracyInMeters = d3;
        this.verticalAccuracyInMeters = d4;
    }

    public Location(@NonNull GeoCoordinates geoCoordinates, @NonNull Date date) {
        this.coordinates = geoCoordinates;
        this.bearingInDegrees = null;
        this.speedInMetersPerSecond = null;
        this.timestamp = date;
        this.horizontalAccuracyInMeters = null;
        this.verticalAccuracyInMeters = null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        Location location = (Location) obj;
        return Objects.equals(this.coordinates, location.coordinates) && Objects.equals(this.bearingInDegrees, location.bearingInDegrees) && Objects.equals(this.speedInMetersPerSecond, location.speedInMetersPerSecond) && Objects.equals(this.timestamp, location.timestamp) && Objects.equals(this.horizontalAccuracyInMeters, location.horizontalAccuracyInMeters) && Objects.equals(this.verticalAccuracyInMeters, location.verticalAccuracyInMeters);
    }

    public int hashCode() {
        GeoCoordinates geoCoordinates = this.coordinates;
        int i = 0;
        int hashCode = ((geoCoordinates != null ? geoCoordinates.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        Double d = this.bearingInDegrees;
        int hashCode2 = (hashCode + (d != null ? d.hashCode() : 0)) * 31;
        Double d2 = this.speedInMetersPerSecond;
        int hashCode3 = (hashCode2 + (d2 != null ? d2.hashCode() : 0)) * 31;
        Date date = this.timestamp;
        int hashCode4 = (hashCode3 + (date != null ? date.hashCode() : 0)) * 31;
        Double d3 = this.horizontalAccuracyInMeters;
        int hashCode5 = (hashCode4 + (d3 != null ? d3.hashCode() : 0)) * 31;
        Double d4 = this.verticalAccuracyInMeters;
        if (d4 != null) {
            i = d4.hashCode();
        }
        return hashCode5 + i;
    }
}
