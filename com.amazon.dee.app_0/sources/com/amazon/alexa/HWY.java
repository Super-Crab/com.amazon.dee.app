package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PhoneCallControllerCallInfo.java */
/* loaded from: classes.dex */
public abstract class HWY extends vUA {
    public final dZc BIo;
    public final vJW zZm;

    public HWY(vJW vjw, dZc dzc) {
        if (vjw != null) {
            this.zZm = vjw;
            if (dzc != null) {
                this.BIo = dzc;
                return;
            }
            throw new NullPointerException("Null callState");
        }
        throw new NullPointerException("Null callId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vUA)) {
            return false;
        }
        HWY hwy = (HWY) obj;
        return this.zZm.equals(hwy.zZm) && this.BIo.equals(hwy.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PhoneCallControllerCallInfo{callId=");
        zZm.append(this.zZm);
        zZm.append(", callState=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
