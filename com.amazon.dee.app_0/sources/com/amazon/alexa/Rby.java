package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AlbumArtAcquiredEvent.java */
/* loaded from: classes.dex */
public final class Rby extends WgM {
    public final xNT BIo;

    public Rby(xNT xnt) {
        if (xnt != null) {
            this.BIo = xnt;
            return;
        }
        throw new NullPointerException("Null audioItemIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WgM)) {
            return false;
        }
        return this.BIo.equals(((Rby) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AlbumArtAcquiredEvent{audioItemIdentifier="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
