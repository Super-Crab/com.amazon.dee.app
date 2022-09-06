package com.amazon.alexa;

import com.amazon.alexa.XnZ;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ReadinessChangedEvent_NotReadyToReadyEvent.java */
/* loaded from: classes.dex */
public final class QEq extends XnZ.BIo {
    public final long BIo;

    public QEq(long j) {
        this.BIo = j;
    }

    @Override // com.amazon.alexa.XnZ
    public long BIo() {
        return this.BIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof XnZ.BIo) && this.BIo == ((XnZ.BIo) obj).BIo();
    }

    public int hashCode() {
        long j = this.BIo;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("NotReadyToReadyEvent{timeElapsedMs="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
