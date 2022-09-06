package com.amazon.alexa;

import com.amazon.alexa.QYV;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogEvent_TextTurnAvailableEvent.java */
/* loaded from: classes.dex */
public final class Arb extends QYV.jiA {
    public final NEe BIo;
    public final eOP zQM;

    public Arb(NEe nEe, eOP eop) {
        if (nEe != null) {
            this.BIo = nEe;
            if (eop != null) {
                this.zQM = eop;
                return;
            }
            throw new NullPointerException("Null apiCallMetadata");
        }
        throw new NullPointerException("Null multiTurnDialog");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QYV.jiA)) {
            return false;
        }
        Arb arb = (Arb) obj;
        return this.BIo.equals(arb.BIo) && this.zQM.equals(arb.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("TextTurnAvailableEvent{multiTurnDialog=");
        zZm.append(this.BIo);
        zZm.append(", apiCallMetadata=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
