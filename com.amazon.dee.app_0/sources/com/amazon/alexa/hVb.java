package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DownchannelStateEvent.java */
/* loaded from: classes.dex */
public final class hVb extends mUQ {
    public final boolean BIo;
    public final tux zQM;

    public hVb(boolean z, tux tuxVar) {
        this.BIo = z;
        if (tuxVar != null) {
            this.zQM = tuxVar;
            return;
        }
        throw new NullPointerException("Null downchannelIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof mUQ)) {
            return false;
        }
        hVb hvb = (hVb) obj;
        return this.BIo == hvb.BIo && this.zQM.equals(hvb.zQM);
    }

    public int hashCode() {
        return (((this.BIo ? 1231 : 1237) ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DownchannelStateEvent{isEstablished=");
        zZm.append(this.BIo);
        zZm.append(", downchannelIdentifier=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
