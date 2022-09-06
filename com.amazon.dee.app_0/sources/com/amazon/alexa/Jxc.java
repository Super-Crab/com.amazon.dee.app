package com.amazon.alexa;

import com.amazon.alexa.QeM;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SpeechStatePayload.java */
/* loaded from: classes.dex */
public abstract class Jxc extends tui {
    public final long BIo;
    public final QeM.zZm zQM;
    public final C0176Pce zZm;

    public Jxc(C0176Pce c0176Pce, long j, QeM.zZm zzm) {
        if (c0176Pce != null) {
            this.zZm = c0176Pce;
            this.BIo = j;
            if (zzm != null) {
                this.zQM = zzm;
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
        if (!(obj instanceof tui)) {
            return false;
        }
        Jxc jxc = (Jxc) obj;
        return this.zZm.equals(jxc.zZm) && this.BIo == jxc.BIo && this.zQM.equals(jxc.zQM);
    }

    public int hashCode() {
        long j = this.BIo;
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SpeechStatePayload{token=");
        zZm.append(this.zZm);
        zZm.append(", offsetInMilliseconds=");
        zZm.append(this.BIo);
        zZm.append(", playerActivity=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
