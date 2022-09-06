package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioPlayerPlaybackResumedMetricEvent.java */
/* loaded from: classes.dex */
public final class UfY extends glM {
    public final nLZ BIo;

    public UfY(nLZ nlz) {
        if (nlz != null) {
            this.BIo = nlz;
            return;
        }
        throw new NullPointerException("Null metricPlayItem");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof glM)) {
            return false;
        }
        return this.BIo.equals(((UfY) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AudioPlayerPlaybackResumedMetricEvent{metricPlayItem="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
