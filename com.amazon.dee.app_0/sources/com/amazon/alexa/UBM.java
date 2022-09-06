package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.uWW;
/* compiled from: $AutoValue_PlayPayload.java */
/* loaded from: classes.dex */
public abstract class UBM extends uWW {
    public final long BIo;
    public final uWW.zZm JTe;
    public final boolean LPk;
    public final GWl Qle;
    public final Hir jiA;
    public final long zQM;
    public final pHD zZm;
    public final vQe zyO;

    public UBM(pHD phd, long j, long j2, vQe vqe, Hir hir, GWl gWl, uWW.zZm zzm, boolean z) {
        if (phd != null) {
            this.zZm = phd;
            this.BIo = j;
            this.zQM = j2;
            if (vqe != null) {
                this.zyO = vqe;
                if (hir != null) {
                    this.jiA = hir;
                    if (gWl != null) {
                        this.Qle = gWl;
                        if (zzm != null) {
                            this.JTe = zzm;
                            this.LPk = z;
                            return;
                        }
                        throw new NullPointerException("Null navigation");
                    }
                    throw new NullPointerException("Null playbackSessionId");
                }
                throw new NullPointerException("Null skillToken");
            }
            throw new NullPointerException("Null playerId");
        }
        throw new NullPointerException("Null playbackContextToken");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof uWW)) {
            return false;
        }
        UBM ubm = (UBM) obj;
        return this.zZm.equals(ubm.zZm) && this.BIo == ubm.BIo && this.zQM == ubm.zQM && this.zyO.equals(ubm.zyO) && this.jiA.equals(ubm.jiA) && this.Qle.equals(ubm.Qle) && this.JTe.equals(ubm.JTe) && this.LPk == ubm.LPk;
    }

    public int hashCode() {
        long j = this.BIo;
        long j2 = this.zQM;
        return ((((((((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode()) * 1000003) ^ this.JTe.hashCode()) * 1000003) ^ (this.LPk ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PlayPayload{playbackContextToken=");
        zZm.append(this.zZm);
        zZm.append(", index=");
        zZm.append(this.BIo);
        zZm.append(", offsetInMilliseconds=");
        zZm.append(this.zQM);
        zZm.append(", playerId=");
        zZm.append(this.zyO);
        zZm.append(", skillToken=");
        zZm.append(this.jiA);
        zZm.append(", playbackSessionId=");
        zZm.append(this.Qle);
        zZm.append(", navigation=");
        zZm.append(this.JTe);
        zZm.append(", preload=");
        return C0179Pya.zZm(zZm, this.LPk, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
