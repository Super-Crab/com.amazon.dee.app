package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DownchannelAvailabilityChangedEvent.java */
/* loaded from: classes.dex */
public final class kjy extends Bob {
    public final uuj BIo;

    public kjy(uuj uujVar) {
        if (uujVar != null) {
            this.BIo = uujVar;
            return;
        }
        throw new NullPointerException("Null status");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bob)) {
            return false;
        }
        return this.BIo.equals(((kjy) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("DownchannelAvailabilityChangedEvent{status="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
