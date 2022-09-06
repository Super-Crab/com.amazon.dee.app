package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Alc;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SetDestinationPayload_Destination.java */
/* loaded from: classes.dex */
public abstract class QqQ extends Alc.zZm {
    public final String BIo;
    public final String zQM;
    public final Alc.zZm.AbstractC0010zZm zZm;
    public final String zyO;

    public QqQ(Alc.zZm.AbstractC0010zZm abstractC0010zZm, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        if (abstractC0010zZm != null) {
            this.zZm = abstractC0010zZm;
            this.BIo = str;
            this.zQM = str2;
            this.zyO = str3;
            return;
        }
        throw new NullPointerException("Null coordinate");
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Alc.zZm)) {
            return false;
        }
        QqQ qqQ = (QqQ) obj;
        if (this.zZm.equals(qqQ.zZm) && ((str = this.BIo) != null ? str.equals(qqQ.BIo) : qqQ.BIo == null) && ((str2 = this.zQM) != null ? str2.equals(qqQ.zQM) : qqQ.zQM == null)) {
            String str3 = this.zyO;
            if (str3 == null) {
                if (qqQ.zyO == null) {
                    return true;
                }
            } else if (str3.equals(qqQ.zyO)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        String str = this.BIo;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zQM;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.zyO;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode3 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Destination{coordinate=");
        zZm.append(this.zZm);
        zZm.append(", singleLineDisplayAddress=");
        zZm.append(this.BIo);
        zZm.append(", multipleLineDisplayAddress=");
        zZm.append(this.zQM);
        zZm.append(", name=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
