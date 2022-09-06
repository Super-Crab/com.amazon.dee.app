package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_VisualChannelUpdatedEvent.java */
/* loaded from: classes.dex */
public final class ofP extends yzL {
    public final JiQ BIo;
    public final boolean zQM;
    public final dnp zyO;

    public ofP(JiQ jiQ, boolean z, dnp dnpVar) {
        if (jiQ != null) {
            this.BIo = jiQ;
            this.zQM = z;
            if (dnpVar != null) {
                this.zyO = dnpVar;
                return;
            }
            throw new NullPointerException("Null interactionInterfaceName");
        }
        throw new NullPointerException("Null channelType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof yzL)) {
            return false;
        }
        ofP ofp = (ofP) obj;
        return this.BIo.equals(ofp.BIo) && this.zQM == ofp.zQM && this.zyO.equals(ofp.zyO);
    }

    public int hashCode() {
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237)) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("VisualChannelUpdatedEvent{channelType=");
        zZm.append(this.BIo);
        zZm.append(", active=");
        zZm.append(this.zQM);
        zZm.append(", interactionInterfaceName=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
