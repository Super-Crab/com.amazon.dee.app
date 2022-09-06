package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_MediaSessionPlaybackPausedEvent.java */
/* loaded from: classes.dex */
public final class yag extends gad {
    public final vQe BIo;

    public yag(vQe vqe) {
        if (vqe != null) {
            this.BIo = vqe;
            return;
        }
        throw new NullPointerException("Null playerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof gad)) {
            return false;
        }
        return this.BIo.equals(((yag) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("MediaSessionPlaybackPausedEvent{playerId="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
