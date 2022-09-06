package com.amazon.alexa;

import com.amazon.alexa.Fwh;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ExternalCapabilityAgentMetricEvent_ECAScanSuccessEvent.java */
/* loaded from: classes.dex */
public final class XOn extends Fwh.zQM {
    public final String BIo;
    public final String zQM;

    public /* synthetic */ XOn(String str, String str2, C0171Iye c0171Iye) {
        this.BIo = str;
        this.zQM = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fwh.zQM)) {
            return false;
        }
        XOn xOn = (XOn) obj;
        return this.BIo.equals(xOn.BIo) && this.zQM.equals(xOn.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ECAScanSuccessEvent{serviceName=");
        zZm.append(this.BIo);
        zZm.append(", packageName=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
