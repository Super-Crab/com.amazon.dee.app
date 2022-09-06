package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.TTH;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_SystemErrorEvent.java */
/* loaded from: classes.dex */
public final class nAN extends TTH {
    public final TTH.zZm BIo;
    public final DialogRequestIdentifier Qle;
    public final boolean jiA;
    public final String zQM;
    public final String zyO;

    public nAN(TTH.zZm zzm, @Nullable String str, @Nullable String str2, boolean z, @Nullable DialogRequestIdentifier dialogRequestIdentifier) {
        if (zzm != null) {
            this.BIo = zzm;
            this.zQM = str;
            this.zyO = str2;
            this.jiA = z;
            this.Qle = dialogRequestIdentifier;
            return;
        }
        throw new NullPointerException("Null errorType");
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TTH)) {
            return false;
        }
        nAN nan = (nAN) obj;
        if (this.BIo.equals(nan.BIo) && ((str = this.zQM) != null ? str.equals(nan.zQM) : nan.zQM == null) && ((str2 = this.zyO) != null ? str2.equals(nan.zyO) : nan.zyO == null) && this.jiA == nan.jiA) {
            DialogRequestIdentifier dialogRequestIdentifier = this.Qle;
            if (dialogRequestIdentifier == null) {
                if (nan.Qle == null) {
                    return true;
                }
            } else if (dialogRequestIdentifier.equals(nan.Qle)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.BIo.hashCode() ^ 1000003) * 1000003;
        String str = this.zQM;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zyO;
        int hashCode3 = (((hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003) ^ (this.jiA ? 1231 : 1237)) * 1000003;
        DialogRequestIdentifier dialogRequestIdentifier = this.Qle;
        if (dialogRequestIdentifier != null) {
            i = dialogRequestIdentifier.hashCode();
        }
        return hashCode3 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SystemErrorEvent{errorType=");
        zZm.append(this.BIo);
        zZm.append(", errorMessage=");
        zZm.append(this.zQM);
        zZm.append(", unparsedDirective=");
        zZm.append(this.zyO);
        zZm.append(", userFacing=");
        zZm.append(this.jiA);
        zZm.append(", dialogRequestIdentifier=");
        return C0179Pya.BIo(zZm, this.Qle, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
