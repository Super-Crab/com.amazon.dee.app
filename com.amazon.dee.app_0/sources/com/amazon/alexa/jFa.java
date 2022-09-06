package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
import java.util.Locale;
/* compiled from: AutoValue_AlexaLocale.java */
/* loaded from: classes.dex */
public final class jFa extends mAU {
    public final boolean BIo;
    public final List<Locale> zZm;

    public jFa(List<Locale> list, boolean z) {
        if (list != null) {
            this.zZm = list;
            this.BIo = z;
            return;
        }
        throw new NullPointerException("Null locales");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof mAU)) {
            return false;
        }
        jFa jfa = (jFa) obj;
        return this.zZm.equals(jfa.zZm) && this.BIo == jfa.BIo;
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AlexaLocale{locales=");
        zZm.append(this.zZm);
        zZm.append(", deviceDefault=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
