package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: $AutoValue_Coordinate.java */
/* loaded from: classes.dex */
public abstract class TKK extends hYy {
    public final double BIo;
    public final double zQM;
    public final double zZm;

    public TKK(double d, double d2, double d3) {
        this.zZm = d;
        this.BIo = d2;
        this.zQM = d3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof hYy)) {
            return false;
        }
        TKK tkk = (TKK) obj;
        return Double.doubleToLongBits(this.zZm) == Double.doubleToLongBits(tkk.zZm) && Double.doubleToLongBits(this.BIo) == Double.doubleToLongBits(tkk.BIo) && Double.doubleToLongBits(this.zQM) == Double.doubleToLongBits(tkk.zQM);
    }

    public int hashCode() {
        return ((((((int) ((Double.doubleToLongBits(this.zZm) >>> 32) ^ Double.doubleToLongBits(this.zZm))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.BIo) >>> 32) ^ Double.doubleToLongBits(this.BIo)))) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.zQM) >>> 32) ^ Double.doubleToLongBits(this.zQM)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Coordinate{latitudeInDegrees=");
        zZm.append(this.zZm);
        zZm.append(", longitudeInDegrees=");
        zZm.append(this.BIo);
        zZm.append(", accuracyInMeters=");
        return GeneratedOutlineSupport1.outline84(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
