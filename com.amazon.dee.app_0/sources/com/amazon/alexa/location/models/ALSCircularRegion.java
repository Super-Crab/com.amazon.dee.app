package com.amazon.alexa.location.models;
/* loaded from: classes9.dex */
public class ALSCircularRegion {
    private double latitudeInDegrees;
    private double longitudeInDegrees;
    private double radiusInMeters;

    public ALSCircularRegion(double d, double d2, double d3) {
        this.latitudeInDegrees = d;
        this.longitudeInDegrees = d2;
        this.radiusInMeters = d3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ALSCircularRegion)) {
            return false;
        }
        ALSCircularRegion aLSCircularRegion = (ALSCircularRegion) obj;
        return Double.compare(this.latitudeInDegrees, aLSCircularRegion.latitudeInDegrees) == 0 && Double.compare(this.longitudeInDegrees, aLSCircularRegion.longitudeInDegrees) == 0 && Double.compare(this.radiusInMeters, aLSCircularRegion.radiusInMeters) == 0;
    }

    public double getLatitudeInDegrees() {
        return this.latitudeInDegrees;
    }

    public double getLongitudeInDegrees() {
        return this.longitudeInDegrees;
    }

    public double getRadiusInMeters() {
        return this.radiusInMeters;
    }

    public int hashCode() {
        int hashCode = Double.valueOf(this.longitudeInDegrees).hashCode();
        return Double.valueOf(this.radiusInMeters).hashCode() + ((hashCode + ((Double.valueOf(this.latitudeInDegrees).hashCode() + 31) * 31)) * 31);
    }
}
