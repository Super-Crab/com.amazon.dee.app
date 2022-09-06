package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AdjustSeekPositionPayload.java */
/* loaded from: classes.dex */
public abstract class jmO extends STS {
    public final vQe BIo;
    public final long zZm;

    public jmO(long j, vQe vqe) {
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
        if (!(obj instanceof STS)) {
            return false;
        }
        jmO jmo = (jmO) obj;
        return this.zZm == jmo.zZm && this.BIo.equals(jmo.BIo);
    }

    public int hashCode() {
        long j = this.zZm;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AdjustSeekPositionPayload{deltaPositionMilliseconds=");
        zZm.append(this.zZm);
        zZm.append(", playerId=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
