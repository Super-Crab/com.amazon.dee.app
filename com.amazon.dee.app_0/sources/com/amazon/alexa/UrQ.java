package com.amazon.alexa;

import com.amazon.alexa.Alc;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: $AutoValue_SetDestinationPayload_Destination_Coordinate.java */
/* loaded from: classes.dex */
public abstract class UrQ extends Alc.zZm.AbstractC0010zZm {
    public final double BIo;
    public final double zZm;

    public UrQ(double d, double d2) {
        this.zZm = d;
        this.BIo = d2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Alc.zZm.AbstractC0010zZm)) {
            return false;
        }
        UrQ urQ = (UrQ) obj;
        return Double.doubleToLongBits(this.zZm) == Double.doubleToLongBits(urQ.zZm) && Double.doubleToLongBits(this.BIo) == Double.doubleToLongBits(urQ.BIo);
    }

    public int hashCode() {
        return ((((int) ((Double.doubleToLongBits(this.zZm) >>> 32) ^ Double.doubleToLongBits(this.zZm))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.BIo) >>> 32) ^ Double.doubleToLongBits(this.BIo)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Coordinate{latitudeInDegrees=");
        zZm.append(this.zZm);
        zZm.append(", longitudeInDegrees=");
        return GeneratedOutlineSupport1.outline84(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
