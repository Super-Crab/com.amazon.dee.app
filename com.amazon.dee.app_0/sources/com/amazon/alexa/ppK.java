package com.amazon.alexa;

import com.amazon.alexa.UMd;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_CapabilityRefreshEvent_TimedOutEvent.java */
/* loaded from: classes.dex */
public final class ppK extends UMd.BIo {
    public final long BIo;

    public ppK(long j) {
        this.BIo = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UMd.BIo) && this.BIo == ((ppK) obj).BIo;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("TimedOutEvent{timeoutLengthMs="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
