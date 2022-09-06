package com.amazon.alexa;

import com.amazon.alexa.JGr;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AuthorizationCompletePayload_Authorized.java */
/* loaded from: classes.dex */
public abstract class HHo extends JGr.zZm {
    public final Hir BIo;
    public final vQe zZm;

    public HHo(vQe vqe, Hir hir) {
        if (vqe != null) {
            this.zZm = vqe;
            if (hir != null) {
                this.BIo = hir;
                return;
            }
            throw new NullPointerException("Null skillToken");
        }
        throw new NullPointerException("Null playerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JGr.zZm)) {
            return false;
        }
        HHo hHo = (HHo) obj;
        return this.zZm.equals(hHo.zZm) && this.BIo.equals(hHo.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Authorized{playerId=");
        zZm.append(this.zZm);
        zZm.append(", skillToken=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
