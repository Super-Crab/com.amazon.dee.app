package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_LaunchTargetPayload.java */
/* loaded from: classes.dex */
public abstract class sew extends mLq {
    public final pUc BIo;
    public final IUU zZm;

    public sew(IUU iuu, pUc puc) {
        if (iuu != null) {
            this.zZm = iuu;
            if (puc != null) {
                this.BIo = puc;
                return;
            }
            throw new NullPointerException("Null target");
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof mLq)) {
            return false;
        }
        sew sewVar = (sew) obj;
        return this.zZm.equals(sewVar.zZm) && this.BIo.equals(sewVar.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("LaunchTargetPayload{token=");
        zZm.append(this.zZm);
        zZm.append(", target=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
