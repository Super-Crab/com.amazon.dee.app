package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AttentionSystemLatencyEvent.java */
/* loaded from: classes.dex */
public final class AWj extends TNw {
    public final String BIo;
    public final long zQM;

    public AWj(String str, long j) {
        if (str != null) {
            this.BIo = str;
            this.zQM = j;
            return;
        }
        throw new NullPointerException("Null invocationType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TNw)) {
            return false;
        }
        AWj aWj = (AWj) obj;
        return this.BIo.equals(aWj.BIo) && this.zQM == aWj.zQM;
    }

    public int hashCode() {
        long j = this.zQM;
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AttentionSystemLatencyEvent{invocationType=");
        zZm.append(this.BIo);
        zZm.append(", latencyInMilliseconds=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
