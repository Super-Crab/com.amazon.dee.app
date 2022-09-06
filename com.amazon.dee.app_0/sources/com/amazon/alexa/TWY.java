package com.amazon.alexa;

import com.amazon.alexa.AbstractC0238xdr;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_LocalStopEvent.java */
/* loaded from: classes.dex */
public final class TWY extends AbstractC0238xdr {
    public final AbstractC0238xdr.zZm BIo;

    public TWY(AbstractC0238xdr.zZm zzm) {
        if (zzm != null) {
            this.BIo = zzm;
            return;
        }
        throw new NullPointerException("Null source");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0238xdr)) {
            return false;
        }
        return this.BIo.equals(((TWY) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("LocalStopEvent{source="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
