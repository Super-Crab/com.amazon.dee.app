package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_Setting.java */
/* loaded from: classes.dex */
public abstract class ONB extends qWv {
    public final String BIo;
    public final String zZm;

    public ONB(String str, String str2) {
        if (str != null) {
            this.zZm = str;
            if (str2 != null) {
                this.BIo = str2;
                return;
            }
            throw new NullPointerException("Null value");
        }
        throw new NullPointerException("Null key");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qWv)) {
            return false;
        }
        ONB onb = (ONB) obj;
        return this.zZm.equals(onb.zZm) && this.BIo.equals(onb.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Setting{key=");
        zZm.append(this.zZm);
        zZm.append(", value=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
