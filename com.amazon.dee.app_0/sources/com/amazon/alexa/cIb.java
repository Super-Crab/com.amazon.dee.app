package com.amazon.alexa;

import com.amazon.alexa.Fwh;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ExternalCapabilityAgentMetricEvent_ECAScanFailureEvent.java */
/* loaded from: classes.dex */
public final class cIb extends Fwh.BIo {
    public final String BIo;
    public final String zQM;

    public /* synthetic */ cIb(String str, String str2, C0170IGy c0170IGy) {
        this.BIo = str;
        this.zQM = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fwh.BIo)) {
            return false;
        }
        cIb cib = (cIb) obj;
        return this.BIo.equals(cib.BIo) && this.zQM.equals(cib.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ECAScanFailureEvent{serviceName=");
        zZm.append(this.BIo);
        zZm.append(", packageName=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
