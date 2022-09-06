package com.amazon.alexa;

import com.amazon.alexa.VXG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_WakeWordMetricEvent_ValidationSpeechOffsetEvent.java */
/* loaded from: classes.dex */
public final class rIt extends VXG.JTe {
    public final long BIo;

    public rIt(long j) {
        this.BIo = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof VXG.JTe) && this.BIo == ((rIt) obj).BIo;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("ValidationSpeechOffsetEvent{speechOffset="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
