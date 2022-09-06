package com.amazon.alexa;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.fcj;
import java.util.Date;
/* compiled from: $AutoValue_Stream.java */
/* loaded from: classes.dex */
public abstract class oFL extends fcj {
    public final long BIo;
    public final CiJ JTe;
    public final fcj.zZm LPk;
    public final Puy Qle;
    public final fcj.BIo jiA;
    public final Puy zQM;
    public final Uri zZm;
    public final Date zyO;

    public oFL(Uri uri, long j, Puy puy, @Nullable Date date, @Nullable fcj.BIo bIo, @Nullable Puy puy2, @Nullable CiJ ciJ, @Nullable fcj.zZm zzm) {
        if (uri != null) {
            this.zZm = uri;
            this.BIo = j;
            if (puy != null) {
                this.zQM = puy;
                this.zyO = date;
                this.jiA = bIo;
                this.Qle = puy2;
                this.JTe = ciJ;
                this.LPk = zzm;
                return;
            }
            throw new NullPointerException("Null token");
        }
        throw new NullPointerException("Null url");
    }

    public boolean equals(Object obj) {
        Date date;
        fcj.BIo bIo;
        Puy puy;
        CiJ ciJ;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fcj)) {
            return false;
        }
        oFL ofl = (oFL) obj;
        if (this.zZm.equals(ofl.zZm) && this.BIo == ofl.BIo && this.zQM.equals(ofl.zQM) && ((date = this.zyO) != null ? date.equals(ofl.zyO) : ofl.zyO == null) && ((bIo = this.jiA) != null ? bIo.equals(ofl.jiA) : ofl.jiA == null) && ((puy = this.Qle) != null ? puy.equals(ofl.Qle) : ofl.Qle == null) && ((ciJ = this.JTe) != null ? ciJ.equals(ofl.JTe) : ofl.JTe == null)) {
            fcj.zZm zzm = this.LPk;
            if (zzm == null) {
                if (ofl.LPk == null) {
                    return true;
                }
            } else if (zzm.equals(ofl.LPk)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.BIo;
        int hashCode = (((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.zQM.hashCode()) * 1000003;
        Date date = this.zyO;
        int i = 0;
        int hashCode2 = (hashCode ^ (date == null ? 0 : date.hashCode())) * 1000003;
        fcj.BIo bIo = this.jiA;
        int hashCode3 = (hashCode2 ^ (bIo == null ? 0 : bIo.hashCode())) * 1000003;
        Puy puy = this.Qle;
        int hashCode4 = (hashCode3 ^ (puy == null ? 0 : puy.hashCode())) * 1000003;
        CiJ ciJ = this.JTe;
        int hashCode5 = (hashCode4 ^ (ciJ == null ? 0 : ciJ.hashCode())) * 1000003;
        fcj.zZm zzm = this.LPk;
        if (zzm != null) {
            i = zzm.hashCode();
        }
        return hashCode5 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Stream{url=");
        zZm.append(this.zZm);
        zZm.append(", offsetInMilliseconds=");
        zZm.append(this.BIo);
        zZm.append(", token=");
        zZm.append(this.zQM);
        zZm.append(", expiryTime=");
        zZm.append(this.zyO);
        zZm.append(", streamFormat=");
        zZm.append(this.jiA);
        zZm.append(", expectedPreviousToken=");
        zZm.append(this.Qle);
        zZm.append(", progressReport=");
        zZm.append(this.JTe);
        zZm.append(", interruptedBehavior=");
        return C0179Pya.BIo(zZm, this.LPk, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
