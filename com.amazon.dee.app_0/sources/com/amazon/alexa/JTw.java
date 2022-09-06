package com.amazon.alexa;

import com.amazon.alexa.VXG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_WakeWordMetricEvent_ValidationLatencyEvent.java */
/* loaded from: classes.dex */
public final class JTw extends VXG.Qle {
    public final long BIo;
    public final long zQM;

    public JTw(long j, long j2) {
        this.BIo = j;
        this.zQM = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VXG.Qle)) {
            return false;
        }
        JTw jTw = (JTw) obj;
        return this.BIo == jTw.BIo && this.zQM == jTw.zQM;
    }

    public int hashCode() {
        long j = this.BIo;
        long j2 = this.zQM;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ValidationLatencyEvent{latency=");
        zZm.append(this.BIo);
        zZm.append(", startTimestamp=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
