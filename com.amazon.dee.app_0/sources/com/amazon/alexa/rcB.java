package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PlaybackEventPayload.java */
/* loaded from: classes.dex */
public abstract class rcB extends lCm {
    public final long BIo;
    public final Puy zZm;

    public rcB(Puy puy, long j) {
        if (puy != null) {
            this.zZm = puy;
            this.BIo = j;
            return;
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof lCm)) {
            return false;
        }
        rcB rcb = (rcB) obj;
        return this.zZm.equals(rcb.zZm) && this.BIo == rcb.BIo;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PlaybackEventPayload{token=");
        zZm.append(this.zZm);
        zZm.append(", offsetInMilliseconds=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
