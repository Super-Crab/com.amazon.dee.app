package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.pPw;
/* compiled from: AutoValue_CapabilityAgentInteractionEvent_DirectiveDelivery_Success.java */
/* loaded from: classes.dex */
public final class Rqk extends pPw.zZm.BIo {
    public final pPw.zQM BIo;
    public final String zQM;

    public Rqk(pPw.zQM zqm, String str) {
        if (zqm != null) {
            this.BIo = zqm;
            if (str != null) {
                this.zQM = str;
                return;
            }
            throw new NullPointerException("Null directiveName");
        }
        throw new NullPointerException("Null stage");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof pPw.zZm.BIo)) {
            return false;
        }
        Rqk rqk = (Rqk) obj;
        return this.BIo.equals(rqk.BIo) && this.zQM.equals(rqk.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Success{stage=");
        zZm.append(this.BIo);
        zZm.append(", directiveName=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
