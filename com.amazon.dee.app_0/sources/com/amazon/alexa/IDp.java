package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_FinishInteractionEvent.java */
/* loaded from: classes.dex */
public final class IDp extends LBB {
    public final IYE BIo;

    public IDp(IYE iye) {
        if (iye != null) {
            this.BIo = iye;
            return;
        }
        throw new NullPointerException("Null interactionIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LBB)) {
            return false;
        }
        return this.BIo.equals(((IDp) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("FinishInteractionEvent{interactionIdentifier="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
