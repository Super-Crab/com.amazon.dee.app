package com.amazon.alexa;

import com.amazon.alexa.Ued;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ClientMetricEvent_RecordLatencyEvent.java */
/* loaded from: classes.dex */
public final class gAT extends Ued.BIo {
    public final String BIo;
    public final long zQM;

    public gAT(String str, long j) {
        if (str != null) {
            this.BIo = str;
            this.zQM = j;
            return;
        }
        throw new NullPointerException("Null name");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ued.BIo)) {
            return false;
        }
        gAT gat = (gAT) obj;
        return this.BIo.equals(gat.BIo) && this.zQM == gat.zQM;
    }

    public int hashCode() {
        long j = this.zQM;
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("RecordLatencyEvent{name=");
        zZm.append(this.BIo);
        zZm.append(", latency=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
