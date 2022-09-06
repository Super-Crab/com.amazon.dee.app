package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogRequestIdChangedEvent.java */
/* loaded from: classes.dex */
public final class rtX extends Obt {
    public final DialogRequestIdentifier BIo;

    public rtX(DialogRequestIdentifier dialogRequestIdentifier) {
        if (dialogRequestIdentifier != null) {
            this.BIo = dialogRequestIdentifier;
            return;
        }
        throw new NullPointerException("Null dialogRequestIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Obt)) {
            return false;
        }
        return this.BIo.equals(((rtX) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("DialogRequestIdChangedEvent{dialogRequestIdentifier="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
