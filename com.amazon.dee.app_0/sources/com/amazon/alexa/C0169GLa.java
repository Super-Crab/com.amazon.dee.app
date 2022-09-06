package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ChannelUpdatedEvent.java */
/* renamed from: com.amazon.alexa.GLa  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0169GLa extends TSb {
    public final aVo BIo;
    public final boolean zQM;
    public final dnp zyO;

    public C0169GLa(aVo avo, boolean z, dnp dnpVar) {
        if (avo != null) {
            this.BIo = avo;
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
        if (!(obj instanceof TSb)) {
            return false;
        }
        C0169GLa c0169GLa = (C0169GLa) obj;
        return this.BIo.equals(c0169GLa.BIo) && this.zQM == c0169GLa.zQM && this.zyO.equals(c0169GLa.zyO);
    }

    public int hashCode() {
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237)) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ChannelUpdatedEvent{channelType=");
        zZm.append(this.BIo);
        zZm.append(", active=");
        zZm.append(this.zQM);
        zZm.append(", interactionInterfaceName=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
