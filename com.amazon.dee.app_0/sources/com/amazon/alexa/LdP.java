package com.amazon.alexa;

import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.tWv;
import java.util.List;
/* compiled from: $AutoValue_IOComponent.java */
/* loaded from: classes.dex */
public abstract class LdP extends tWv {
    public final urO BIo;
    public final List<URU> jiA;
    public final cVW zQM;
    public final tWv.zZm zZm;
    public final vwO zyO;

    public LdP(tWv.zZm zzm, urO uro, cVW cvw, @Nullable vwO vwo, @Nullable List<URU> list) {
        if (zzm != null) {
            this.zZm = zzm;
            if (uro != null) {
                this.BIo = uro;
                if (cvw != null) {
                    this.zQM = cvw;
                    this.zyO = vwo;
                    this.jiA = list;
                    return;
                }
                throw new NullPointerException("Null deviceInfo");
            }
            throw new NullPointerException("Null connection");
        }
        throw new NullPointerException("Null type");
    }

    public boolean equals(Object obj) {
        vwO vwo;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof tWv)) {
            return false;
        }
        LdP ldP = (LdP) obj;
        if (this.zZm.equals(ldP.zZm) && this.BIo.equals(ldP.BIo) && this.zQM.equals(ldP.zQM) && ((vwo = this.zyO) != null ? vwo.equals(ldP.zyO) : ldP.zyO == null)) {
            List<URU> list = this.jiA;
            if (list == null) {
                if (ldP.jiA == null) {
                    return true;
                }
            } else if (list.equals(ldP.jiA)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003;
        vwO vwo = this.zyO;
        int i = 0;
        int hashCode2 = (hashCode ^ (vwo == null ? 0 : vwo.hashCode())) * 1000003;
        List<URU> list = this.jiA;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("IOComponent{type=");
        zZm.append(this.zZm);
        zZm.append(", connection=");
        zZm.append(this.BIo);
        zZm.append(", deviceInfo=");
        zZm.append(this.zQM);
        zZm.append(", clusterDevice=");
        zZm.append(this.zyO);
        zZm.append(", context=");
        return C0179Pya.BIo(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
