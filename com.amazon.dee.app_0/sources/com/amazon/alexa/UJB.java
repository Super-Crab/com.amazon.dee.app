package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.xik;
/* compiled from: $AutoValue_PlayPayload.java */
/* loaded from: classes.dex */
public abstract class UJB extends xik {
    public final BIn BIo;
    public final xik.zZm zZm;

    public UJB(xik.zZm zzm, BIn bIn) {
        if (zzm != null) {
            this.zZm = zzm;
            if (bIn != null) {
                this.BIo = bIn;
                return;
            }
            throw new NullPointerException("Null audioItem");
        }
        throw new NullPointerException("Null playBehavior");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof xik)) {
            return false;
        }
        UJB ujb = (UJB) obj;
        return this.zZm.equals(ujb.zZm) && this.BIo.equals(ujb.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PlayPayload{playBehavior=");
        zZm.append(this.zZm);
        zZm.append(", audioItem=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
