package com.amazon.alexa.location.models;

import android.location.Location;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes9.dex */
public final class PersonalizationLocationItem {
    private String id;
    private Double latitudeInDegrees;
    private Double longitudeInDegrees;
    private Double radiusInMeters;
    private Integer state;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private String id;
        private double latitudeInDegrees;
        private Location location;
        private double longitudeInDegrees;
        private double radiusInMeters;
        private int state;

        public Builder(Location location) {
            this.location = location;
            this.latitudeInDegrees = location.getLatitude();
            this.longitudeInDegrees = location.getLongitude();
        }

        private int calculateState() {
            Location location = new Location("geofence enter");
            location.setLatitude(this.latitudeInDegrees);
            location.setLongitude(this.longitudeInDegrees);
            return ((double) location.distanceTo(this.location)) - this.radiusInMeters >= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? 0 : 1;
        }

        public PersonalizationLocationItem build() {
            return new PersonalizationLocationItem(this);
        }

        public Builder withALSGeofence(ALSGeofence aLSGeofence) {
            this.id = aLSGeofence.getId();
            this.latitudeInDegrees = aLSGeofence.getCircularRegion().getLatitudeInDegrees();
            this.longitudeInDegrees = aLSGeofence.getCircularRegion().getLongitudeInDegrees();
            this.radiusInMeters = aLSGeofence.getCircularRegion().getRadiusInMeters();
            this.state = calculateState();
            return this;
        }
    }

    private PersonalizationLocationItem(Builder builder) {
        this.latitudeInDegrees = Double.valueOf(builder.latitudeInDegrees);
        this.longitudeInDegrees = Double.valueOf(builder.longitudeInDegrees);
        this.radiusInMeters = Double.valueOf(builder.radiusInMeters);
        this.id = builder.id;
        this.state = Integer.valueOf(builder.state);
    }
}
