package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_MediaBrowserSessionDestroyedEvent.java */
/* loaded from: classes.dex */
public final class DNr extends AbstractC0201dvl {
    public final vQe BIo;

    public DNr(vQe vqe) {
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
        if (!(obj instanceof AbstractC0201dvl)) {
            return false;
        }
        return this.BIo.equals(((DNr) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("MediaBrowserSessionDestroyedEvent{playerId="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
