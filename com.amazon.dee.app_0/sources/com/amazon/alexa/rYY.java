package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SetSeekPositionPayload.java */
/* loaded from: classes.dex */
public abstract class rYY extends ErD {
    public final vQe BIo;
    public final long zZm;

    public rYY(long j, vQe vqe) {
        this.zZm = j;
        if (vqe != null) {
            this.BIo = vqe;
            return;
        }
        throw new NullPointerException("Null playerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ErD)) {
            return false;
        }
        rYY ryy = (rYY) obj;
        return this.zZm == ryy.zZm && this.BIo.equals(ryy.BIo);
    }

    public int hashCode() {
        long j = this.zZm;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SetSeekPositionPayload{positionMilliseconds=");
        zZm.append(this.zZm);
        zZm.append(", playerId=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
