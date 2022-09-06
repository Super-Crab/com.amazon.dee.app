package com.amazon.alexa;

import com.amazon.alexa.XnZ;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ReadinessChangedEvent_NotReadyToConnectedEvent.java */
/* loaded from: classes.dex */
public final class VLd extends XnZ.zZm {
    public final long BIo;

    public VLd(long j) {
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
        return (obj instanceof XnZ.zZm) && this.BIo == ((XnZ.zZm) obj).BIo();
    }

    public int hashCode() {
        long j = this.BIo;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("NotReadyToConnectedEvent{timeElapsedMs="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
