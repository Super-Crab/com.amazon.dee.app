package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_Window.java */
/* loaded from: classes.dex */
public abstract class qWU extends uEF {
    public final String BIo;
    public final String zQM;
    public final String zZm;
    public final AbstractC0229sSp zyO;

    public qWU(String str, String str2, @Nullable String str3, AbstractC0229sSp abstractC0229sSp) {
        if (str != null) {
            this.zZm = str;
            if (str2 != null) {
                this.BIo = str2;
                this.zQM = str3;
                if (abstractC0229sSp != null) {
                    this.zyO = abstractC0229sSp;
                    return;
                }
                throw new NullPointerException("Null configuration");
            }
            throw new NullPointerException("Null templateId");
        }
        throw new NullPointerException("Null id");
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof uEF)) {
            return false;
        }
        qWU qwu = (qWU) obj;
        return this.zZm.equals(qwu.zZm) && this.BIo.equals(qwu.BIo) && ((str = this.zQM) != null ? str.equals(qwu.zQM) : qwu.zQM == null) && this.zyO.equals(qwu.zyO);
    }

    public int hashCode() {
        int hashCode = (((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003;
        String str = this.zQM;
        return ((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Window{id=");
        zZm.append(this.zZm);
        zZm.append(", templateId=");
        zZm.append(this.BIo);
        zZm.append(", token=");
        zZm.append(this.zQM);
        zZm.append(", configuration=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
