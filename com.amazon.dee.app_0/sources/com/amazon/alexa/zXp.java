package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.qgo;
/* compiled from: $AutoValue_PlayerInfoPayload.java */
/* loaded from: classes.dex */
public abstract class zXp extends qgo {
    public final qgo.zZm BIo;
    public final qgo.zQM zQM;
    public final xNT zZm;
    public final qgo.BIo zyO;

    public zXp(xNT xnt, @Nullable qgo.zZm zzm, @Nullable qgo.zQM zqm, @Nullable qgo.BIo bIo) {
        if (xnt != null) {
            this.zZm = xnt;
            this.BIo = zzm;
            this.zQM = zqm;
            this.zyO = bIo;
            return;
        }
        throw new NullPointerException("Null mediaId");
    }

    public boolean equals(Object obj) {
        qgo.zZm zzm;
        qgo.zQM zqm;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qgo)) {
            return false;
        }
        zXp zxp = (zXp) obj;
        if (this.zZm.equals(zxp.zZm) && ((zzm = this.BIo) != null ? zzm.equals(zxp.BIo) : zxp.BIo == null) && ((zqm = this.zQM) != null ? zqm.equals(zxp.zQM) : zxp.zQM == null)) {
            qgo.BIo bIo = this.zyO;
            if (bIo == null) {
                if (zxp.zyO == null) {
                    return true;
                }
            } else if (bIo.equals(zxp.zyO)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        qgo.zZm zzm = this.BIo;
        int i = 0;
        int hashCode2 = (hashCode ^ (zzm == null ? 0 : zzm.hashCode())) * 1000003;
        qgo.zQM zqm = this.zQM;
        int hashCode3 = (hashCode2 ^ (zqm == null ? 0 : zqm.hashCode())) * 1000003;
        qgo.BIo bIo = this.zyO;
        if (bIo != null) {
            i = bIo.hashCode();
        }
        return hashCode3 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PlayerInfoPayload{mediaId=");
        zZm.append(this.zZm);
        zZm.append(", infoText=");
        zZm.append(this.BIo);
        zZm.append(", template=");
        zZm.append(this.zQM);
        zZm.append(", progress=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
