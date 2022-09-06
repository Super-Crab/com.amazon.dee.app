package com.amazon.alexa;

import com.amazon.alexa.ICG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_RecoverableVoiceInteractionEvent_DownchannelAvailable.java */
/* loaded from: classes.dex */
public final class spO extends ICG.zZm {
    public final bij BIo;
    public final String zQM;
    public final long zyO;

    public spO(bij bijVar, String str, long j) {
        if (bijVar != null) {
            this.BIo = bijVar;
            if (str != null) {
                this.zQM = str;
                this.zyO = j;
                return;
            }
            throw new NullPointerException("Null invocationType");
        }
        throw new NullPointerException("Null failureReason");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ICG.zZm)) {
            return false;
        }
        spO spo = (spO) obj;
        return this.BIo.equals(spo.BIo) && this.zQM.equals(spo.zQM) && this.zyO == spo.zyO;
    }

    public int hashCode() {
        long j = this.zyO;
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DownchannelAvailable{failureReason=");
        zZm.append(this.BIo);
        zZm.append(", invocationType=");
        zZm.append(this.zQM);
        zZm.append(", latencyBetweenFailureAndAvailableMs=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
