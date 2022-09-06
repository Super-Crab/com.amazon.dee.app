package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioPlayerBufferingTimeoutEvent.java */
/* loaded from: classes.dex */
public final class Ycg extends qqU {
    public final nLZ BIo;

    public Ycg(nLZ nlz) {
        if (nlz != null) {
            this.BIo = nlz;
            return;
        }
        throw new NullPointerException("Null playItem");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qqU)) {
            return false;
        }
        return this.BIo.equals(((Ycg) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AudioPlayerBufferingTimeoutEvent{playItem="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
