package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.qgo;
/* compiled from: $AutoValue_PlayerInfoPayload_InfoText.java */
/* loaded from: classes.dex */
public abstract class Knu extends qgo.zZm {
    public final String BIo;
    public final String zQM;
    public final String zZm;

    public Knu(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.zZm = str;
        this.BIo = str2;
        this.zQM = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qgo.zZm)) {
            return false;
        }
        String str = this.zZm;
        if (str != null ? str.equals(((Knu) obj).zZm) : ((Knu) obj).zZm == null) {
            String str2 = this.BIo;
            if (str2 != null ? str2.equals(((Knu) obj).BIo) : ((Knu) obj).BIo == null) {
                String str3 = this.zQM;
                if (str3 == null) {
                    if (((Knu) obj).zQM == null) {
                        return true;
                    }
                } else if (str3.equals(((Knu) obj).zQM)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.zZm;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.BIo;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.zQM;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("InfoText{title=");
        zZm.append(this.zZm);
        zZm.append(", subText1=");
        zZm.append(this.BIo);
        zZm.append(", subText2=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
