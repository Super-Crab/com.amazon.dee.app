package com.amazon.alexa;

import com.amazon.alexa.NEv;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_TimeoutEvent.java */
/* loaded from: classes.dex */
public final class zLp extends NEv.jiA {
    public final eOP BIo;

    public zLp(eOP eop) {
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
        if (!(obj instanceof NEv.jiA)) {
            return false;
        }
        return this.BIo.equals(((zLp) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("TimeoutEvent{apiCallMetadata="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
