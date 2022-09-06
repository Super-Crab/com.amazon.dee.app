package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessorykit.findmy.AutoValue_LocationInformation;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class LocationInformation {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract LocationInformation build();

        public abstract Builder setAccuracyInMeters(float f);

        public abstract Builder setAltitudeInMeters(double d);

        public abstract Builder setBearingInDegrees(float f);

        public abstract Builder setLatitudeInDegrees(double d);

        public abstract Builder setLongitudeInDegrees(double d);

        public abstract Builder setSpeedInMetersPerSecond(float f);

        public abstract Builder setTime(long j);
    }

    public static Builder builder() {
        return new AutoValue_LocationInformation.Builder();
    }

    public abstract float getAccuracyInMeters();

    public abstract double getAltitudeInMeters();

    public abstract float getBearingInDegrees();

    public abstract double getLatitudeInDegrees();

    public abstract double getLongitudeInDegrees();

    public abstract float getSpeedInMetersPerSecond();

    public abstract long getTime();
}
