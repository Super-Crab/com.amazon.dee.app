package com.amazon.alexa;

import com.amazon.alexa.HdS;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioPlayerPlaybackEvent_SlowPlaybackEvent.java */
/* loaded from: classes.dex */
public final class RWT extends HdS.zyO {
    public final kQf BIo;

    public RWT(kQf kqf) {
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
        if (!(obj instanceof HdS.zyO)) {
            return false;
        }
        return this.BIo.equals(((RWT) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("SlowPlaybackEvent{playItem="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
