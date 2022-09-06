package com.amazon.alexa;

import com.amazon.alexa.HdS;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioPlayerPlaybackEvent_PlayFailedEvent.java */
/* loaded from: classes.dex */
public final class wDd extends HdS.zQM {
    public final kQf BIo;
    public final boolean zQM;

    public wDd(kQf kqf, boolean z) {
        if (kqf != null) {
            this.BIo = kqf;
            this.zQM = z;
            return;
        }
        throw new NullPointerException("Null playItem");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HdS.zQM)) {
            return false;
        }
        wDd wdd = (wDd) obj;
        return this.BIo.equals(wdd.BIo) && this.zQM == wdd.zQM;
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PlayFailedEvent{playItem=");
        zZm.append(this.BIo);
        zZm.append(", connectivityFailure=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
