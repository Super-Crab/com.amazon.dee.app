package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_MediaSessionPlaybackStartedEvent.java */
/* loaded from: classes.dex */
public final class cqx extends MwZ {
    public final vQe BIo;

    public cqx(vQe vqe) {
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
        if (!(obj instanceof MwZ)) {
            return false;
        }
        return this.BIo.equals(((cqx) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("MediaSessionPlaybackStartedEvent{playerId="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
