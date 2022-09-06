package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogResponseEvent.java */
/* loaded from: classes.dex */
public final class sVH extends Car {
    public final DialogRequestIdentifier BIo;
    public final XWx zQM;

    public sVH(DialogRequestIdentifier dialogRequestIdentifier, XWx xWx) {
        if (dialogRequestIdentifier != null) {
            this.BIo = dialogRequestIdentifier;
            if (xWx != null) {
                this.zQM = xWx;
                return;
            }
            throw new NullPointerException("Null dialogTurnId");
        }
        throw new NullPointerException("Null dialogRequestId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Car)) {
            return false;
        }
        sVH svh = (sVH) obj;
        return this.BIo.equals(svh.BIo) && this.zQM.equals(svh.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DialogResponseEvent{dialogRequestId=");
        zZm.append(this.BIo);
        zZm.append(", dialogTurnId=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
