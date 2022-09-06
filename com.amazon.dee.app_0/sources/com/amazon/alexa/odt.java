package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_PreciseDialogResponseEvent.java */
/* loaded from: classes.dex */
public final class odt extends aJD {
    public final DialogRequestIdentifier BIo;
    public final XWx zQM;
    public final long zyO;

    public odt(DialogRequestIdentifier dialogRequestIdentifier, XWx xWx, long j) {
        if (dialogRequestIdentifier != null) {
            this.BIo = dialogRequestIdentifier;
            if (xWx != null) {
                this.zQM = xWx;
                this.zyO = j;
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
        if (!(obj instanceof aJD)) {
            return false;
        }
        odt odtVar = (odt) obj;
        return this.BIo.equals(odtVar.BIo) && this.zQM.equals(odtVar.zQM) && this.zyO == odtVar.zyO;
    }

    public int hashCode() {
        long j = this.zyO;
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PreciseDialogResponseEvent{dialogRequestId=");
        zZm.append(this.BIo);
        zZm.append(", dialogTurnId=");
        zZm.append(this.zQM);
        zZm.append(", elapsedTimeOfResponse=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
