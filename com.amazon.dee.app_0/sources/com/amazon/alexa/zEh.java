package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_NavigationPayload.java */
/* loaded from: classes.dex */
public abstract class zEh extends zAH {
    public final SFx BIo;
    public final Csx zZm;

    public zEh(Csx csx, SFx sFx) {
        if (csx != null) {
            this.zZm = csx;
            if (sFx != null) {
                this.BIo = sFx;
                return;
            }
            throw new NullPointerException("Null identifier");
        }
        throw new NullPointerException("Null identifierType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zAH)) {
            return false;
        }
        zEh zeh = (zEh) obj;
        return this.zZm.equals(zeh.zZm) && this.BIo.equals(zeh.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("NavigationPayload{identifierType=");
        zZm.append(this.zZm);
        zZm.append(", identifier=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
