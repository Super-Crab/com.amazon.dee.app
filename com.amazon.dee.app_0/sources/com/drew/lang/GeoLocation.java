package com.drew.lang;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.text.DecimalFormat;
/* loaded from: classes2.dex */
public final class GeoLocation {
    private final double _latitude;
    private final double _longitude;

    public GeoLocation(double d, double d2) {
        this._latitude = d;
        this._longitude = d2;
    }

    @NotNull
    public static double[] decimalToDegreesMinutesSeconds(double d) {
        int i = (int) d;
        double abs = Math.abs((d % 1.0d) * 60.0d);
        return new double[]{i, (int) abs, (abs % 1.0d) * 60.0d};
    }

    @NotNull
    public static String decimalToDegreesMinutesSecondsString(double d) {
        double[] decimalToDegreesMinutesSeconds = decimalToDegreesMinutesSeconds(d);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return String.format("%s° %s' %s\"", decimalFormat.format(decimalToDegreesMinutesSeconds[0]), decimalFormat.format(decimalToDegreesMinutesSeconds[1]), decimalFormat.format(decimalToDegreesMinutesSeconds[2]));
    }

    @Nullable
    public static Double degreesMinutesSecondsToDecimal(@NotNull Rational rational, @NotNull Rational rational2, @NotNull Rational rational3, boolean z) {
        double doubleValue = (rational3.doubleValue() / 3600.0d) + (rational2.doubleValue() / 60.0d) + Math.abs(rational.doubleValue());
        if (Double.isNaN(doubleValue)) {
            return null;
        }
        if (z) {
            doubleValue *= -1.0d;
        }
        return Double.valueOf(doubleValue);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GeoLocation.class != obj.getClass()) {
            return false;
        }
        GeoLocation geoLocation = (GeoLocation) obj;
        return Double.compare(geoLocation._latitude, this._latitude) == 0 && Double.compare(geoLocation._longitude, this._longitude) == 0;
    }

    public double getLatitude() {
        return this._latitude;
    }

    public double getLongitude() {
        return this._longitude;
    }

    public int hashCode() {
        double d = this._latitude;
        long j = 0;
        long doubleToLongBits = d != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? Double.doubleToLongBits(d) : 0L;
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        double d2 = this._longitude;
        if (d2 != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            j = Double.doubleToLongBits(d2);
        }
        return (i * 31) + ((int) ((j >>> 32) ^ j));
    }

    public boolean isZero() {
        return this._latitude == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && this._longitude == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @NotNull
    public String toDMSString() {
        return decimalToDegreesMinutesSecondsString(this._latitude) + ", " + decimalToDegreesMinutesSecondsString(this._longitude);
    }

    @NotNull
    public String toString() {
        return this._latitude + ", " + this._longitude;
    }
}
