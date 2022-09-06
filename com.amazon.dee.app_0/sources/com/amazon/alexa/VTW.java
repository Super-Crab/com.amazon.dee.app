package com.amazon.alexa;

import com.amazon.alexa.Ims;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AuthorizeDiscoveredPlayersPayload_Player_Metadata.java */
/* loaded from: classes.dex */
public abstract class VTW extends Ims.zZm.AbstractC0011zZm {
    public final Hir BIo;
    public final vQe zZm;

    public VTW(vQe vqe, Hir hir) {
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
        if (!(obj instanceof Ims.zZm.AbstractC0011zZm)) {
            return false;
        }
        VTW vtw = (VTW) obj;
        return this.zZm.equals(vtw.zZm) && this.BIo.equals(vtw.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Metadata{playerId=");
        zZm.append(this.zZm);
        zZm.append(", skillToken=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
