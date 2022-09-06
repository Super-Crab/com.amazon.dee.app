package com.amazon.alexa;

import com.amazon.alexa.NEv;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_SuccessEvent.java */
/* loaded from: classes.dex */
public final class ptH extends NEv.zQM {
    public final eOP BIo;

    public ptH(eOP eop) {
        if (eop != null) {
            this.BIo = eop;
            return;
        }
        throw new NullPointerException("Null apiCallMetadata");
    }

    @Override // com.amazon.alexa.NEv
    public eOP BIo() {
        return this.BIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NEv.zQM)) {
            return false;
        }
        return this.BIo.equals(((ptH) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("SuccessEvent{apiCallMetadata="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
