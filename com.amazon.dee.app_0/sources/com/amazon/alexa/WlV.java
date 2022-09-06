package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.vRe;
/* compiled from: $AutoValue_VolumeStatePayload.java */
/* loaded from: classes.dex */
public abstract class WlV extends vRe {
    public final boolean BIo;
    public final long zZm;

    /* compiled from: $AutoValue_VolumeStatePayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends vRe.zZm {
        public Boolean BIo;
        public Long zZm;
    }

    public WlV(long j, boolean z) {
        this.zZm = j;
        this.BIo = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vRe)) {
            return false;
        }
        WlV wlV = (WlV) obj;
        return this.zZm == wlV.zZm && this.BIo == wlV.BIo;
    }

    public int hashCode() {
        long j = this.zZm;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("VolumeStatePayload{volume=");
        zZm2.append(this.zZm);
        zZm2.append(", muted=");
        return C0179Pya.zZm(zZm2, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
