package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.jsd;
/* compiled from: AutoValue_MicrophoneMetricEvent_MicrophoneInitializationSuccessEvent.java */
/* loaded from: classes.dex */
public final class zjk extends jsd.BIo {
    public final long BIo;

    public zjk(long j) {
        this.BIo = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof jsd.BIo) && this.BIo == ((zjk) obj).BIo;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("MicrophoneInitializationSuccessEvent{initializationTime="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
