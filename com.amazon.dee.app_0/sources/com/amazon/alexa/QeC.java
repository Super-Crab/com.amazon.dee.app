package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: $AutoValue_Altitude.java */
/* loaded from: classes.dex */
public abstract class QeC extends Gkq {
    public final double BIo;
    public final double zZm;

    public QeC(double d, double d2) {
        this.zZm = d;
        this.BIo = d2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Gkq)) {
            return false;
        }
        QeC qeC = (QeC) obj;
        return Double.doubleToLongBits(this.zZm) == Double.doubleToLongBits(qeC.zZm) && Double.doubleToLongBits(this.BIo) == Double.doubleToLongBits(qeC.BIo);
    }

    public int hashCode() {
        return ((((int) ((Double.doubleToLongBits(this.zZm) >>> 32) ^ Double.doubleToLongBits(this.zZm))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.BIo) >>> 32) ^ Double.doubleToLongBits(this.BIo)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Altitude{altitudeInMeters=");
        zZm.append(this.zZm);
        zZm.append(", accuracyInMeters=");
        return GeneratedOutlineSupport1.outline84(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
