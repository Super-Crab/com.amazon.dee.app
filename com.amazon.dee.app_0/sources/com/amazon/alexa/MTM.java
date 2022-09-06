package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_DeviceInfo.java */
/* loaded from: classes.dex */
public abstract class MTM extends cVW {
    public final pGm BIo;
    public final cMY zQM;
    public final IKe zZm;

    public MTM(IKe iKe, pGm pgm, @Nullable cMY cmy) {
        if (iKe != null) {
            this.zZm = iKe;
            if (pgm != null) {
                this.BIo = pgm;
                this.zQM = cmy;
                return;
            }
            throw new NullPointerException("Null deviceSerialNumber");
        }
        throw new NullPointerException("Null deviceType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof cVW)) {
            return false;
        }
        MTM mtm = (MTM) obj;
        if (this.zZm.equals(mtm.zZm) && this.BIo.equals(mtm.BIo)) {
            cMY cmy = this.zQM;
            if (cmy == null) {
                if (mtm.zQM == null) {
                    return true;
                }
            } else if (cmy.equals(mtm.zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003;
        cMY cmy = this.zQM;
        return hashCode ^ (cmy == null ? 0 : cmy.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DeviceInfo{deviceType=");
        zZm.append(this.zZm);
        zZm.append(", deviceSerialNumber=");
        zZm.append(this.BIo);
        zZm.append(", firmwareVersion=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
