package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SupportsMobileDownchannelSetting.java */
/* loaded from: classes.dex */
public abstract class oBs extends yqu {
    public final boolean BIo;
    public final String zZm;

    public oBs(String str, boolean z) {
        if (str != null) {
            this.zZm = str;
            this.BIo = z;
            return;
        }
        throw new NullPointerException("Null key");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof yqu)) {
            return false;
        }
        oBs obs = (oBs) obj;
        return this.zZm.equals(obs.zZm) && this.BIo == obs.BIo;
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SupportsMobileDownchannelSetting{key=");
        zZm.append(this.zZm);
        zZm.append(", value=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
