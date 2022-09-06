package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.kbp;
/* compiled from: AutoValue_FinishDialogInteractionEvent_CancelEvent.java */
/* loaded from: classes.dex */
public final class sbP extends kbp.BIo {
    public final XWx BIo;
    public final NTw zQM;

    public sbP(XWx xWx, NTw nTw) {
        if (xWx != null) {
            this.BIo = xWx;
            if (nTw != null) {
                this.zQM = nTw;
                return;
            }
            throw new NullPointerException("Null cancelReason");
        }
        throw new NullPointerException("Null dialogTurnIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kbp.BIo)) {
            return false;
        }
        sbP sbp = (sbP) obj;
        return this.BIo.equals(sbp.BIo) && this.zQM.equals(sbp.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("CancelEvent{dialogTurnIdentifier=");
        zZm.append(this.BIo);
        zZm.append(", cancelReason=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
