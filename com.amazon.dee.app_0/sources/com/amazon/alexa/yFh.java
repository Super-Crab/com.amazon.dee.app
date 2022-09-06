package com.amazon.alexa;

import com.amazon.alexa.BjL;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_OfflinePromptsMetricEvent_OfflinePromptDownloadFailureEvent.java */
/* loaded from: classes.dex */
public final class yFh extends BjL.zZm {
    public final long BIo;
    public final String zQM;
    public final String zyO;

    public yFh(long j, String str, String str2) {
        this.BIo = j;
        if (str != null) {
            this.zQM = str;
            if (str2 != null) {
                this.zyO = str2;
                return;
            }
            throw new NullPointerException("Null failureReason");
        }
        throw new NullPointerException("Null modelMD5");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BjL.zZm)) {
            return false;
        }
        yFh yfh = (yFh) obj;
        return this.BIo == yfh.BIo && this.zQM.equals(yfh.zQM) && this.zyO.equals(yfh.zyO);
    }

    public int hashCode() {
        long j = this.BIo;
        return ((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("OfflinePromptDownloadFailureEvent{requestDuration=");
        zZm.append(this.BIo);
        zZm.append(", modelMD5=");
        zZm.append(this.zQM);
        zZm.append(", failureReason=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
