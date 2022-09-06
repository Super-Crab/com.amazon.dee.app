package com.amazon.alexa;

import com.amazon.alexa.VXG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_WakeWordMetricEvent_PryonInitializationSuccessEvent.java */
/* loaded from: classes.dex */
public final class ZBr extends VXG.zyO {
    public final long BIo;
    public final String zQM;

    public ZBr(long j, String str) {
        this.BIo = j;
        if (str != null) {
            this.zQM = str;
            return;
        }
        throw new NullPointerException("Null modelMD5");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VXG.zyO)) {
            return false;
        }
        ZBr zBr = (ZBr) obj;
        return this.BIo == zBr.BIo && this.zQM.equals(zBr.zQM);
    }

    public int hashCode() {
        long j = this.BIo;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PryonInitializationSuccessEvent{pryonInitializationTime=");
        zZm.append(this.BIo);
        zZm.append(", modelMD5=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
