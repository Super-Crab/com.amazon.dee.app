package com.amazon.alexa;

import com.amazon.alexa.PJz;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioFocusGainedEvent.java */
/* loaded from: classes.dex */
public final class ApR extends Zbv {
    public final PJz.BIo BIo;
    public final boolean zQM;
    public final boolean zyO;

    public ApR(PJz.BIo bIo, boolean z, boolean z2) {
        if (bIo != null) {
            this.BIo = bIo;
            this.zQM = z;
            this.zyO = z2;
            return;
        }
        throw new NullPointerException("Null getDuration");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Zbv)) {
            return false;
        }
        ApR apR = (ApR) obj;
        return this.BIo.equals(apR.BIo) && this.zQM == apR.zQM && this.zyO == apR.zyO;
    }

    public int hashCode() {
        int i = 1231;
        int hashCode = (((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237)) * 1000003;
        if (!this.zyO) {
            i = 1237;
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AudioFocusGainedEvent{getDuration=");
        zZm.append(this.BIo);
        zZm.append(", wasAnotherAppPlayingMusic=");
        zZm.append(this.zQM);
        zZm.append(", wasEmpPlayingMusic=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
