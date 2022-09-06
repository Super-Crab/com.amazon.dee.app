package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioPlayerPlaybackStartedMetricEvent.java */
/* renamed from: com.amazon.alexa.yDN  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0240yDN extends paE {
    public final nLZ BIo;

    public C0240yDN(nLZ nlz) {
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
        if (!(obj instanceof paE)) {
            return false;
        }
        return this.BIo.equals(((C0240yDN) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AudioPlayerPlaybackStartedMetricEvent{metricPlayItem="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
