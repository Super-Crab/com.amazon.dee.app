package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PhoneCallControllerCallingFeature.java */
/* loaded from: classes.dex */
public abstract class rXH extends HYG {
    public final boolean BIo;
    public final String zZm;

    public rXH(String str, boolean z) {
        if (str != null) {
            this.zZm = str;
            this.BIo = z;
            return;
        }
        throw new NullPointerException("Null name");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HYG)) {
            return false;
        }
        rXH rxh = (rXH) obj;
        return this.zZm.equals(rxh.zZm) && this.BIo == rxh.BIo;
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PhoneCallControllerCallingFeature{name=");
        zZm.append(this.zZm);
        zZm.append(", value=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
