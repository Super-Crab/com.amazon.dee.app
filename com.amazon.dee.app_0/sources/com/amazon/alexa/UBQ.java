package com.amazon.alexa;

import com.amazon.alexa.VXG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_WakeWordMetricEvent_WakeWordModelDownloadInterruptedEvent.java */
/* loaded from: classes.dex */
public final class UBQ extends VXG.Mlj {
    public final long BIo;

    public UBQ(long j) {
        this.BIo = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof VXG.Mlj) && this.BIo == ((UBQ) obj).BIo;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("WakeWordModelDownloadInterruptedEvent{requestDuration="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
