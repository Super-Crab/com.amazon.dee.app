package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ExternalMediaPlayerMetadata.java */
/* loaded from: classes.dex */
public abstract class vKJ extends MOI {
    public final zYH BIo;
    public final ZYY zQM;
    public final AbstractC0188bKf zZm;

    public vKJ(AbstractC0188bKf abstractC0188bKf, @Nullable zYH zyh, @Nullable ZYY zyy) {
        if (abstractC0188bKf != null) {
            this.zZm = abstractC0188bKf;
            this.BIo = zyh;
            this.zQM = zyy;
            return;
        }
        throw new NullPointerException("Null spiVersion");
    }

    public boolean equals(Object obj) {
        zYH zyh;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MOI)) {
            return false;
        }
        vKJ vkj = (vKJ) obj;
        if (this.zZm.equals(vkj.zZm) && ((zyh = this.BIo) != null ? zyh.equals(vkj.BIo) : vkj.BIo == null)) {
            ZYY zyy = this.zQM;
            if (zyy == null) {
                if (vkj.zQM == null) {
                    return true;
                }
            } else if (zyy.equals(vkj.zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        zYH zyh = this.BIo;
        int i = 0;
        int hashCode2 = (hashCode ^ (zyh == null ? 0 : zyh.hashCode())) * 1000003;
        ZYY zyy = this.zQM;
        if (zyy != null) {
            i = zyy.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalMediaPlayerMetadata{spiVersion=");
        zZm.append(this.zZm);
        zZm.append(", playerCookie=");
        zZm.append(this.BIo);
        zZm.append(", playerVersion=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
