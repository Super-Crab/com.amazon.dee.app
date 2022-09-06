package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_FinishVisualInteractionEvent.java */
/* loaded from: classes.dex */
public final class kwP extends dOG {
    public final IYE BIo;

    public kwP(IYE iye) {
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
        if (!(obj instanceof dOG)) {
            return false;
        }
        return this.BIo.equals(((kwP) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("FinishVisualInteractionEvent{interactionIdentifier="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
