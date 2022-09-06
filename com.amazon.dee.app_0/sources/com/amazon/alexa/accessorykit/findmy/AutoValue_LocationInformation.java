package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessorykit.findmy.LocationInformation;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
final class AutoValue_LocationInformation extends LocationInformation {
    private final float accuracyInMeters;
    private final double altitudeInMeters;
    private final float bearingInDegrees;
    private final double latitudeInDegrees;
    private final double longitudeInDegrees;
    private final float speedInMetersPerSecond;
    private final long time;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class Builder extends LocationInformation.Builder {
        private Float accuracyInMeters;
        private Double altitudeInMeters;
        private Float bearingInDegrees;
        private Double latitudeInDegrees;
        private Double longitudeInDegrees;
        private Float speedInMetersPerSecond;
        private Long time;

        @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation.Builder
        public LocationInformation build() {
            String str = "";
            if (this.latitudeInDegrees == null) {
                str = GeneratedOutlineSupport1.outline72(str, " latitudeInDegrees");
            }
            if (this.longitudeInDegrees == null) {
                str = GeneratedOutlineSupport1.outline72(str, " longitudeInDegrees");
            }
            if (this.accuracyInMeters == null) {
                str = GeneratedOutlineSupport1.outline72(str, " accuracyInMeters");
            }
            if (this.altitudeInMeters == null) {
                str = GeneratedOutlineSupport1.outline72(str, " altitudeInMeters");
            }
            if (this.speedInMetersPerSecond == null) {
                str = GeneratedOutlineSupport1.outline72(str, " speedInMetersPerSecond");
            }
            if (this.bearingInDegrees == null) {
                str = GeneratedOutlineSupport1.outline72(str, " bearingInDegrees");
            }
            if (this.time == null) {
                str = GeneratedOutlineSupport1.outline72(str, " time");
            }
            if (str.isEmpty()) {
                return new AutoValue_LocationInformation(this.latitudeInDegrees.doubleValue(), this.longitudeInDegrees.doubleValue(), this.accuracyInMeters.floatValue(), this.altitudeInMeters.doubleValue(), this.speedInMetersPerSecond.floatValue(), this.bearingInDegrees.floatValue(), this.time.longValue());
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation.Builder
        public LocationInformation.Builder setAccuracyInMeters(float f) {
            this.accuracyInMeters = Float.valueOf(f);
            return this;
        }

        @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation.Builder
        public LocationInformation.Builder setAltitudeInMeters(double d) {
            this.altitudeInMeters = Double.valueOf(d);
            return this;
        }

        @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation.Builder
        public LocationInformation.Builder setBearingInDegrees(float f) {
            this.bearingInDegrees = Float.valueOf(f);
            return this;
        }

        @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation.Builder
        public LocationInformation.Builder setLatitudeInDegrees(double d) {
            this.latitudeInDegrees = Double.valueOf(d);
            return this;
        }

        @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation.Builder
        public LocationInformation.Builder setLongitudeInDegrees(double d) {
            this.longitudeInDegrees = Double.valueOf(d);
            return this;
        }

        @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation.Builder
        public LocationInformation.Builder setSpeedInMetersPerSecond(float f) {
            this.speedInMetersPerSecond = Float.valueOf(f);
            return this;
        }

        @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation.Builder
        public LocationInformation.Builder setTime(long j) {
            this.time = Long.valueOf(j);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocationInformation)) {
            return false;
        }
        LocationInformation locationInformation = (LocationInformation) obj;
        return Double.doubleToLongBits(this.latitudeInDegrees) == Double.doubleToLongBits(locationInformation.getLatitudeInDegrees()) && Double.doubleToLongBits(this.longitudeInDegrees) == Double.doubleToLongBits(locationInformation.getLongitudeInDegrees()) && Float.floatToIntBits(this.accuracyInMeters) == Float.floatToIntBits(locationInformation.getAccuracyInMeters()) && Double.doubleToLongBits(this.altitudeInMeters) == Double.doubleToLongBits(locationInformation.getAltitudeInMeters()) && Float.floatToIntBits(this.speedInMetersPerSecond) == Float.floatToIntBits(locationInformation.getSpeedInMetersPerSecond()) && Float.floatToIntBits(this.bearingInDegrees) == Float.floatToIntBits(locationInformation.getBearingInDegrees()) && this.time == locationInformation.getTime();
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation
    public float getAccuracyInMeters() {
        return this.accuracyInMeters;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation
    public double getAltitudeInMeters() {
        return this.altitudeInMeters;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation
    public float getBearingInDegrees() {
        return this.bearingInDegrees;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation
    public double getLatitudeInDegrees() {
        return this.latitudeInDegrees;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation
    public double getLongitudeInDegrees() {
        return this.longitudeInDegrees;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation
    public float getSpeedInMetersPerSecond() {
        return this.speedInMetersPerSecond;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationInformation
    public long getTime() {
        return this.time;
    }

    public int hashCode() {
        long j = this.time;
        return ((((((((((((((int) ((Double.doubleToLongBits(this.latitudeInDegrees) >>> 32) ^ Double.doubleToLongBits(this.latitudeInDegrees))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.longitudeInDegrees) >>> 32) ^ Double.doubleToLongBits(this.longitudeInDegrees)))) * 1000003) ^ Float.floatToIntBits(this.accuracyInMeters)) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.altitudeInMeters) >>> 32) ^ Double.doubleToLongBits(this.altitudeInMeters)))) * 1000003) ^ Float.floatToIntBits(this.speedInMetersPerSecond)) * 1000003) ^ Float.floatToIntBits(this.bearingInDegrees)) * 1000003) ^ ((int) ((j >>> 32) ^ j));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocationInformation{latitudeInDegrees=");
        outline107.append(this.latitudeInDegrees);
        outline107.append(", longitudeInDegrees=");
        outline107.append(this.longitudeInDegrees);
        outline107.append(", accuracyInMeters=");
        outline107.append(this.accuracyInMeters);
        outline107.append(", altitudeInMeters=");
        outline107.append(this.altitudeInMeters);
        outline107.append(", speedInMetersPerSecond=");
        outline107.append(this.speedInMetersPerSecond);
        outline107.append(", bearingInDegrees=");
        outline107.append(this.bearingInDegrees);
        outline107.append(", time=");
        return GeneratedOutlineSupport1.outline87(outline107, this.time, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_LocationInformation(double d, double d2, float f, double d3, float f2, float f3, long j) {
        this.latitudeInDegrees = d;
        this.longitudeInDegrees = d2;
        this.accuracyInMeters = f;
        this.altitudeInMeters = d3;
        this.speedInMetersPerSecond = f2;
        this.bearingInDegrees = f3;
        this.time = j;
    }
}
