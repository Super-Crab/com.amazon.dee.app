package com.amazon.alexa;

import com.amazon.alexa.VXG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_WakeWordMetricEvent_WakeWordModelDownloadSuccessEvent.java */
/* loaded from: classes.dex */
public final class NUX extends VXG.zzR {
    public final long BIo;
    public final String zQM;

    public NUX(long j, String str) {
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
        if (!(obj instanceof VXG.zzR)) {
            return false;
        }
        NUX nux = (NUX) obj;
        return this.BIo == nux.BIo && this.zQM.equals(nux.zQM);
    }

    public int hashCode() {
        long j = this.BIo;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("WakeWordModelDownloadSuccessEvent{requestDuration=");
        zZm.append(this.BIo);
        zZm.append(", modelMD5=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
