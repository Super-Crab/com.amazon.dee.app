package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AudioPlayerStatePayload.java */
/* loaded from: classes.dex */
public abstract class BkS extends Vma {
    public final long BIo;
    public final AUQ zQM;
    public final Puy zZm;

    public BkS(Puy puy, long j, AUQ auq) {
        if (puy != null) {
            this.zZm = puy;
            this.BIo = j;
            if (auq != null) {
                this.zQM = auq;
                return;
            }
            throw new NullPointerException("Null playerActivity");
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vma)) {
            return false;
        }
        BkS bkS = (BkS) obj;
        return this.zZm.equals(bkS.zZm) && this.BIo == bkS.BIo && this.zQM.equals(bkS.zQM);
    }

    public int hashCode() {
        long j = this.BIo;
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AudioPlayerStatePayload{token=");
        zZm.append(this.zZm);
        zZm.append(", offsetInMilliseconds=");
        zZm.append(this.BIo);
        zZm.append(", playerActivity=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
