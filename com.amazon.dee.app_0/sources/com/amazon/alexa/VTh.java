package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_RequestProcessingStartedEvent.java */
/* loaded from: classes.dex */
public final class VTh extends AbstractC0218lro {
    public final DialogRequestIdentifier BIo;

    public VTh(DialogRequestIdentifier dialogRequestIdentifier) {
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
        if (!(obj instanceof AbstractC0218lro)) {
            return false;
        }
        return this.BIo.equals(((VTh) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("RequestProcessingStartedEvent{dialogRequestIdentifier="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
