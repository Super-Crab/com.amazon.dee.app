package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ResponsePendingEvent.java */
/* loaded from: classes.dex */
public final class aeu extends YQk {
    public final RrI BIo;

    public aeu(RrI rrI) {
        if (rrI != null) {
            this.BIo = rrI;
            return;
        }
        throw new NullPointerException("Null requestIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof YQk)) {
            return false;
        }
        return this.BIo.equals(((aeu) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("ResponsePendingEvent{requestIdentifier="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
