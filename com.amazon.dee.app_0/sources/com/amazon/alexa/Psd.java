package com.amazon.alexa;

import com.amazon.alexa.HdS;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioPlayerPlaybackEvent_PlayAttemptedEvent.java */
/* loaded from: classes.dex */
public final class Psd extends HdS.BIo {
    public final kQf BIo;

    public Psd(kQf kqf) {
        if (kqf != null) {
            this.BIo = kqf;
            return;
        }
        throw new NullPointerException("Null playItem");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HdS.BIo)) {
            return false;
        }
        return this.BIo.equals(((Psd) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("PlayAttemptedEvent{playItem="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
