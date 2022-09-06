package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ExternalMediaPlayerEvent.java */
/* loaded from: classes.dex */
public final class SLO extends ddC {
    public final vQe BIo;
    public final vat zQM;
    public final GWl zyO;

    public SLO(vQe vqe, vat vatVar, GWl gWl) {
        if (vqe != null) {
            this.BIo = vqe;
            if (vatVar != null) {
                this.zQM = vatVar;
                if (gWl != null) {
                    this.zyO = gWl;
                    return;
                }
                throw new NullPointerException("Null playbackSessionId");
            }
            throw new NullPointerException("Null playerEvent");
        }
        throw new NullPointerException("Null playerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ddC)) {
            return false;
        }
        SLO slo = (SLO) obj;
        return this.BIo.equals(slo.BIo) && this.zQM.equals(slo.zQM) && this.zyO.equals(slo.zyO);
    }

    public int hashCode() {
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalMediaPlayerEvent{playerId=");
        zZm.append(this.BIo);
        zZm.append(", playerEvent=");
        zZm.append(this.zQM);
        zZm.append(", playbackSessionId=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
