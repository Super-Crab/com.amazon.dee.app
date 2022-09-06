package com.drew.metadata.exif;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.GeoLocation;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import java.text.DecimalFormat;
/* loaded from: classes2.dex */
public class GpsDescriptor extends TagDescriptor<GpsDirectory> {
    public GpsDescriptor(@NotNull GpsDirectory gpsDirectory) {
        super(gpsDirectory);
    }

    @Nullable
    private String getGpsVersionIdDescription() {
        return getVersionBytesDescription(0, 1);
    }

    @Nullable
    public String getDegreesMinutesSecondsDescription() {
        GeoLocation geoLocation = ((GpsDirectory) this._directory).getGeoLocation();
        if (geoLocation == null) {
            return null;
        }
        return geoLocation.toDMSString();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0036  */
    @Override // com.drew.metadata.TagDescriptor
    @com.drew.lang.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getDescription(int r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L68
            r0 = 2
            if (r2 == r0) goto L63
            r0 = 12
            if (r2 == r0) goto L5e
            r0 = 30
            if (r2 == r0) goto L59
            r0 = 4
            if (r2 == r0) goto L54
            r0 = 5
            if (r2 == r0) goto L4f
            r0 = 6
            if (r2 == r0) goto L4a
            r0 = 7
            if (r2 == r0) goto L45
            r0 = 9
            if (r2 == r0) goto L40
            r0 = 10
            if (r2 == r0) goto L3b
            switch(r2) {
                case 14: goto L36;
                case 15: goto L31;
                case 16: goto L36;
                case 17: goto L31;
                default: goto L24;
            }
        L24:
            switch(r2) {
                case 23: goto L36;
                case 24: goto L31;
                case 25: goto L2c;
                default: goto L27;
            }
        L27:
            java.lang.String r2 = super.getDescription(r2)
            return r2
        L2c:
            java.lang.String r2 = r1.getGpsDestinationReferenceDescription()
            return r2
        L31:
            java.lang.String r2 = r1.getGpsDirectionDescription(r2)
            return r2
        L36:
            java.lang.String r2 = r1.getGpsDirectionReferenceDescription(r2)
            return r2
        L3b:
            java.lang.String r2 = r1.getGpsMeasureModeDescription()
            return r2
        L40:
            java.lang.String r2 = r1.getGpsStatusDescription()
            return r2
        L45:
            java.lang.String r2 = r1.getGpsTimeStampDescription()
            return r2
        L4a:
            java.lang.String r2 = r1.getGpsAltitudeDescription()
            return r2
        L4f:
            java.lang.String r2 = r1.getGpsAltitudeRefDescription()
            return r2
        L54:
            java.lang.String r2 = r1.getGpsLongitudeDescription()
            return r2
        L59:
            java.lang.String r2 = r1.getGpsDifferentialDescription()
            return r2
        L5e:
            java.lang.String r2 = r1.getGpsSpeedRefDescription()
            return r2
        L63:
            java.lang.String r2 = r1.getGpsLatitudeDescription()
            return r2
        L68:
            java.lang.String r2 = r1.getGpsVersionIdDescription()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.exif.GpsDescriptor.getDescription(int):java.lang.String");
    }

    @Nullable
    public String getGpsAltitudeDescription() {
        Rational rational = ((GpsDirectory) this._directory).getRational(6);
        if (rational == null) {
            return null;
        }
        return rational.intValue() + " metres";
    }

    @Nullable
    public String getGpsAltitudeRefDescription() {
        return getIndexedDescription(5, "Sea level", "Below sea level");
    }

    @Nullable
    public String getGpsDestinationReferenceDescription() {
        String string = ((GpsDirectory) this._directory).getString(25);
        if (string == null) {
            return null;
        }
        String trim = string.trim();
        return "K".equalsIgnoreCase(trim) ? "kilometers" : "M".equalsIgnoreCase(trim) ? "miles" : "N".equalsIgnoreCase(trim) ? "knots" : GeneratedOutlineSupport1.outline75("Unknown (", trim, ")");
    }

    @Nullable
    public String getGpsDifferentialDescription() {
        return getIndexedDescription(30, "No Correction", "Differential Corrected");
    }

    @Nullable
    public String getGpsDirectionDescription(int i) {
        Rational rational = ((GpsDirectory) this._directory).getRational(i);
        String format = rational != null ? new DecimalFormat("0.##").format(rational.doubleValue()) : ((GpsDirectory) this._directory).getString(i);
        if (format == null || format.trim().length() == 0) {
            return null;
        }
        return format.trim() + " degrees";
    }

    @Nullable
    public String getGpsDirectionReferenceDescription(int i) {
        String string = ((GpsDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        String trim = string.trim();
        return ExifInterface.GPS_DIRECTION_TRUE.equalsIgnoreCase(trim) ? "True direction" : "M".equalsIgnoreCase(trim) ? "Magnetic direction" : GeneratedOutlineSupport1.outline75("Unknown (", trim, ")");
    }

    @Nullable
    public String getGpsLatitudeDescription() {
        GeoLocation geoLocation = ((GpsDirectory) this._directory).getGeoLocation();
        if (geoLocation == null) {
            return null;
        }
        return GeoLocation.decimalToDegreesMinutesSecondsString(geoLocation.getLatitude());
    }

    @Nullable
    public String getGpsLongitudeDescription() {
        GeoLocation geoLocation = ((GpsDirectory) this._directory).getGeoLocation();
        if (geoLocation == null) {
            return null;
        }
        return GeoLocation.decimalToDegreesMinutesSecondsString(geoLocation.getLongitude());
    }

    @Nullable
    public String getGpsMeasureModeDescription() {
        String string = ((GpsDirectory) this._directory).getString(10);
        if (string == null) {
            return null;
        }
        String trim = string.trim();
        return "2".equalsIgnoreCase(trim) ? "2-dimensional measurement" : "3".equalsIgnoreCase(trim) ? "3-dimensional measurement" : GeneratedOutlineSupport1.outline75("Unknown (", trim, ")");
    }

    @Nullable
    public String getGpsSpeedRefDescription() {
        String string = ((GpsDirectory) this._directory).getString(12);
        if (string == null) {
            return null;
        }
        String trim = string.trim();
        return "K".equalsIgnoreCase(trim) ? "kph" : "M".equalsIgnoreCase(trim) ? "mph" : "N".equalsIgnoreCase(trim) ? "knots" : GeneratedOutlineSupport1.outline75("Unknown (", trim, ")");
    }

    @Nullable
    public String getGpsStatusDescription() {
        String string = ((GpsDirectory) this._directory).getString(9);
        if (string == null) {
            return null;
        }
        String trim = string.trim();
        return ExifInterface.GPS_MEASUREMENT_IN_PROGRESS.equalsIgnoreCase(trim) ? "Active (Measurement in progress)" : ExifInterface.GPS_MEASUREMENT_INTERRUPTED.equalsIgnoreCase(trim) ? "Void (Measurement Interoperability)" : GeneratedOutlineSupport1.outline75("Unknown (", trim, ")");
    }

    @Nullable
    public String getGpsTimeStampDescription() {
        Rational[] rationalArray = ((GpsDirectory) this._directory).getRationalArray(7);
        DecimalFormat decimalFormat = new DecimalFormat("00.000");
        if (rationalArray == null) {
            return null;
        }
        return String.format("%02d:%02d:%s UTC", Integer.valueOf(rationalArray[0].intValue()), Integer.valueOf(rationalArray[1].intValue()), decimalFormat.format(rationalArray[2].doubleValue()));
    }
}
