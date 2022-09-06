package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: AutoValue_MediaBrowserConnectionStatusEvent.java */
/* loaded from: classes.dex */
public final class FeU extends bdJ {
    public final vQe BIo;
    public final boolean zQM;
    public final int zyO;

    public FeU(vQe vqe, boolean z, int i) {
        if (vqe != null) {
            this.BIo = vqe;
            this.zQM = z;
            this.zyO = i;
            return;
        }
        throw new NullPointerException("Null playerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof bdJ)) {
            return false;
        }
        FeU feU = (FeU) obj;
        return this.BIo.equals(feU.BIo) && this.zQM == feU.zQM && this.zyO == feU.zyO;
    }

    public int hashCode() {
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237)) * 1000003) ^ this.zyO;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("MediaBrowserConnectionStatusEvent{playerId=");
        zZm.append(this.BIo);
        zZm.append(", isConnected=");
        zZm.append(this.zQM);
        zZm.append(", numberOfAttempts=");
        return GeneratedOutlineSupport1.outline86(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
