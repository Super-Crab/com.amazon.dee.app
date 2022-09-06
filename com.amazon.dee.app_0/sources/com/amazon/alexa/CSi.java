package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_StartDialogInteractionEvent.java */
/* loaded from: classes.dex */
public final class CSi extends GSR {
    public final XWx BIo;
    public final boolean jiA;
    public final String zQM;
    public final String zyO;

    public CSi(XWx xWx, @Nullable String str, @Nullable String str2, boolean z) {
        if (xWx != null) {
            this.BIo = xWx;
            this.zQM = str;
            this.zyO = str2;
            this.jiA = z;
            return;
        }
        throw new NullPointerException("Null dialogTurnId");
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GSR)) {
            return false;
        }
        CSi cSi = (CSi) obj;
        return this.BIo.equals(cSi.BIo) && ((str = this.zQM) != null ? str.equals(cSi.zQM) : cSi.zQM == null) && ((str2 = this.zyO) != null ? str2.equals(cSi.zyO) : cSi.zyO == null) && this.jiA == cSi.jiA;
    }

    public int hashCode() {
        int hashCode = (this.BIo.hashCode() ^ 1000003) * 1000003;
        String str = this.zQM;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zyO;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode2 ^ i) * 1000003) ^ (this.jiA ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("StartDialogInteractionEvent{dialogTurnId=");
        zZm.append(this.BIo);
        zZm.append(", invocationType=");
        zZm.append(this.zQM);
        zZm.append(", softwareVersion=");
        zZm.append(this.zyO);
        zZm.append(", textDialog=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
