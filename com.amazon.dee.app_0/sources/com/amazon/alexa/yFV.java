package com.amazon.alexa;

import com.amazon.alexa.BjL;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_OfflinePromptsMetricEvent_OfflinePromptDownloadSuccessEvent.java */
/* loaded from: classes.dex */
public final class yFV extends BjL.zQM {
    public final long BIo;
    public final String zQM;

    public yFV(long j, String str) {
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
        if (!(obj instanceof BjL.zQM)) {
            return false;
        }
        yFV yfv = (yFV) obj;
        return this.BIo == yfv.BIo && this.zQM.equals(yfv.zQM);
    }

    public int hashCode() {
        long j = this.BIo;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("OfflinePromptDownloadSuccessEvent{requestDuration=");
        zZm.append(this.BIo);
        zZm.append(", modelMD5=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
