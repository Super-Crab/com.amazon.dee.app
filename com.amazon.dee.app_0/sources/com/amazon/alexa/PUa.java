package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PlayerError.java */
/* loaded from: classes.dex */
public abstract class PUa extends pfe {
    public final long BIo;
    public final String jiA;
    public final boolean zQM;
    public final Iof zZm;
    public final boolean zyO;

    public PUa(Iof iof, long j, boolean z, boolean z2, @Nullable String str) {
        if (iof != null) {
            this.zZm = iof;
            this.BIo = j;
            this.zQM = z;
            this.zyO = z2;
            this.jiA = str;
            return;
        }
        throw new NullPointerException("Null errorName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof pfe)) {
            return false;
        }
        PUa pUa = (PUa) obj;
        if (this.zZm.equals(pUa.zZm) && this.BIo == pUa.BIo && this.zQM == pUa.zQM && this.zyO == pUa.zyO) {
            String str = this.jiA;
            if (str == null) {
                if (pUa.jiA == null) {
                    return true;
                }
            } else if (str.equals(pUa.jiA)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.BIo;
        int i = 1231;
        int hashCode = (((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ (this.zQM ? 1231 : 1237)) * 1000003;
        if (!this.zyO) {
            i = 1237;
        }
        int i2 = (hashCode ^ i) * 1000003;
        String str = this.jiA;
        return i2 ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PlayerError{errorName=");
        zZm.append(this.zZm);
        zZm.append(", code=");
        zZm.append(this.BIo);
        zZm.append(", fatal=");
        zZm.append(this.zQM);
        zZm.append(", shouldCleanupSession=");
        zZm.append(this.zyO);
        zZm.append(", description=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
