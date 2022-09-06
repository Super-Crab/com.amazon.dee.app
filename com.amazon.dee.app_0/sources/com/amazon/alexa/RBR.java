package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.vHh;
/* compiled from: AutoValue_PublishCapabilitiesFailedEvent.java */
/* loaded from: classes.dex */
public final class RBR extends DLc {
    public final vHh.zZm BIo;
    public final boolean zQM;

    public RBR(vHh.zZm zzm, boolean z) {
        if (zzm != null) {
            this.BIo = zzm;
            this.zQM = z;
            return;
        }
        throw new NullPointerException("Null requestFailureReason");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DLc)) {
            return false;
        }
        RBR rbr = (RBR) obj;
        return this.BIo.equals(rbr.BIo) && this.zQM == rbr.zQM;
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PublishCapabilitiesFailedEvent{requestFailureReason=");
        zZm.append(this.BIo);
        zZm.append(", final=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
