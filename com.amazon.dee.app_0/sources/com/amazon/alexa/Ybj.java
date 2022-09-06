package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.pPw;
/* compiled from: AutoValue_CapabilityAgentInteractionEvent_DirectiveDelivery_Failure.java */
/* loaded from: classes.dex */
public final class Ybj extends pPw.zZm.AbstractC0034zZm {
    public final pPw.zQM BIo;
    public final pPw.BIo zQM;
    public final String zyO;

    public Ybj(pPw.zQM zqm, pPw.BIo bIo, String str) {
        if (zqm != null) {
            this.BIo = zqm;
            if (bIo != null) {
                this.zQM = bIo;
                if (str != null) {
                    this.zyO = str;
                    return;
                }
                throw new NullPointerException("Null directiveName");
            }
            throw new NullPointerException("Null failureReason");
        }
        throw new NullPointerException("Null stage");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof pPw.zZm.AbstractC0034zZm)) {
            return false;
        }
        Ybj ybj = (Ybj) obj;
        return this.BIo.equals(ybj.BIo) && this.zQM.equals(ybj.zQM) && this.zyO.equals(ybj.zyO);
    }

    public int hashCode() {
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Failure{stage=");
        zZm.append(this.BIo);
        zZm.append(", failureReason=");
        zZm.append(this.zQM);
        zZm.append(", directiveName=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
