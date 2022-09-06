package com.amazon.alexa;

import com.amazon.alexa.PJz;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioMetadata.java */
/* loaded from: classes.dex */
public final class PNy extends PJz {
    public final PJz.BIo BIo;
    public final boolean jiA;
    public final PJz.zyO zQM;
    public final PJz.zQM zZm;
    public final PJz.zZm zyO;

    public PNy(PJz.zQM zqm, PJz.BIo bIo, PJz.zyO zyo, PJz.zZm zzm, boolean z) {
        if (zqm != null) {
            this.zZm = zqm;
            if (bIo != null) {
                this.BIo = bIo;
                if (zyo != null) {
                    this.zQM = zyo;
                    if (zzm != null) {
                        this.zyO = zzm;
                        this.jiA = z;
                        return;
                    }
                    throw new NullPointerException("Null audioContentType");
                }
                throw new NullPointerException("Null audioUsage");
            }
            throw new NullPointerException("Null duration");
        }
        throw new NullPointerException("Null stream");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PJz)) {
            return false;
        }
        PNy pNy = (PNy) obj;
        return this.zZm.equals(pNy.zZm) && this.BIo.equals(pNy.BIo) && this.zQM.equals(pNy.zQM) && this.zyO.equals(pNy.zyO) && this.jiA == pNy.jiA;
    }

    public int hashCode() {
        return ((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ (this.jiA ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AudioMetadata{stream=");
        zZm.append(this.zZm);
        zZm.append(", duration=");
        zZm.append(this.BIo);
        zZm.append(", audioUsage=");
        zZm.append(this.zQM);
        zZm.append(", audioContentType=");
        zZm.append(this.zyO);
        zZm.append(", alexaAudio=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
