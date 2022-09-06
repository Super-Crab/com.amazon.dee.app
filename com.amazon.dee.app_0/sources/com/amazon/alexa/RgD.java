package com.amazon.alexa;

import com.amazon.alexa.BjL;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_OfflinePromptsMetricEvent_OfflinePromptDownloadInterruptedEvent.java */
/* loaded from: classes.dex */
public final class RgD extends BjL.BIo {
    public final long BIo;

    public RgD(long j) {
        this.BIo = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BjL.BIo) && this.BIo == ((RgD) obj).BIo;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("OfflinePromptDownloadInterruptedEvent{requestDuration="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
