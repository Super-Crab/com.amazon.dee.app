package com.amazon.alexa;

import com.amazon.alexa.Ued;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ClientMetricEvent_RecordEvent.java */
/* loaded from: classes.dex */
public final class ELT extends Ued.zZm {
    public final String BIo;
    public final MNR zQM;

    public ELT(String str, MNR mnr) {
        if (str != null) {
            this.BIo = str;
            if (mnr != null) {
                this.zQM = mnr;
                return;
            }
            throw new NullPointerException("Null apiCallIdentifier");
        }
        throw new NullPointerException("Null name");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ued.zZm)) {
            return false;
        }
        ELT elt = (ELT) obj;
        return this.BIo.equals(elt.BIo) && this.zQM.equals(elt.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("RecordEvent{name=");
        zZm.append(this.BIo);
        zZm.append(", apiCallIdentifier=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
