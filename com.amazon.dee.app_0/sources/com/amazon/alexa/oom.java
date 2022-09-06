package com.amazon.alexa;

import com.amazon.alexa.HdS;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioPlayerPlaybackEvent_ConnectivityFailureResumeEvent.java */
/* loaded from: classes.dex */
public final class oom extends HdS.zZm {
    public final boolean BIo;

    public oom(boolean z) {
        this.BIo = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof HdS.zZm) && this.BIo == ((oom) obj).BIo;
    }

    public int hashCode() {
        return (this.BIo ? 1231 : 1237) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("ConnectivityFailureResumeEvent{didTimeoutElapse="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
