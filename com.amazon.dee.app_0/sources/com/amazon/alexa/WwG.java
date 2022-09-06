package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.kbp;
/* compiled from: AutoValue_FinishDialogInteractionEvent_ImmediateResultEvent.java */
/* loaded from: classes.dex */
public final class WwG extends kbp.zyO {
    public final String BIo;
    public final boolean jiA;
    public final String zQM;
    public final pRk zyO;

    public WwG(@Nullable String str, String str2, pRk prk, boolean z) {
        this.BIo = str;
        if (str2 != null) {
            this.zQM = str2;
            if (prk != null) {
                this.zyO = prk;
                this.jiA = z;
                return;
            }
            throw new NullPointerException("Null reason");
        }
        throw new NullPointerException("Null softwareVersion");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kbp.zyO)) {
            return false;
        }
        String str = this.BIo;
        if (str != null ? str.equals(((WwG) obj).BIo) : ((WwG) obj).BIo == null) {
            WwG wwG = (WwG) obj;
            if (this.zQM.equals(wwG.zQM) && this.zyO.equals(wwG.zyO) && this.jiA == wwG.jiA) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.BIo;
        return (((((((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ (this.jiA ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ImmediateResultEvent{invocationType=");
        zZm.append(this.BIo);
        zZm.append(", softwareVersion=");
        zZm.append(this.zQM);
        zZm.append(", reason=");
        zZm.append(this.zyO);
        zZm.append(", textDialog=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
