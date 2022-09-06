package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_WakewordDetectionEvent_InAppWakeWordDetectedEvent.java */
/* loaded from: classes.dex */
public final class ocm extends BWd {
    public final long BIo;

    public ocm(long j) {
        this.BIo = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BWd) && this.BIo == ((ocm) obj).BIo;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("InAppWakeWordDetectedEvent{wakewordDetectionTimeMs="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
