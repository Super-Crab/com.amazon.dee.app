package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ClusterDevice.java */
/* loaded from: classes.dex */
public abstract class ruk extends vwO {
    public final pGm BIo;
    public final IKe zZm;

    public ruk(IKe iKe, pGm pgm) {
        if (iKe != null) {
            this.zZm = iKe;
            if (pgm != null) {
                this.BIo = pgm;
                return;
            }
            throw new NullPointerException("Null clusterDeviceSerialNumber");
        }
        throw new NullPointerException("Null clusterDeviceType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vwO)) {
            return false;
        }
        ruk rukVar = (ruk) obj;
        return this.zZm.equals(rukVar.zZm) && this.BIo.equals(rukVar.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ClusterDevice{clusterDeviceType=");
        zZm.append(this.zZm);
        zZm.append(", clusterDeviceSerialNumber=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
