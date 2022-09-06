package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ScoMetricsTimerEvent.java */
/* loaded from: classes.dex */
public final class wCw extends HtI {
    public final String BIo;
    public final long zQM;

    public wCw(String str, long j) {
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
        if (!(obj instanceof HtI)) {
            return false;
        }
        wCw wcw = (wCw) obj;
        return this.BIo.equals(wcw.BIo) && this.zQM == wcw.zQM;
    }

    public int hashCode() {
        long j = this.zQM;
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ScoMetricsTimerEvent{name=");
        zZm.append(this.BIo);
        zZm.append(", time=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
