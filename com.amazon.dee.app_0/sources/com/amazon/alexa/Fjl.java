package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_WindowConfiguration.java */
/* loaded from: classes.dex */
public abstract class Fjl extends AbstractC0229sSp {
    public final String BIo;
    public final String zZm;

    public Fjl(String str, String str2) {
        if (str != null) {
            this.zZm = str;
            if (str2 != null) {
                this.BIo = str2;
                return;
            }
            throw new NullPointerException("Null sizeConfigurationId");
        }
        throw new NullPointerException("Null interactionMode");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0229sSp)) {
            return false;
        }
        Fjl fjl = (Fjl) obj;
        return this.zZm.equals(fjl.zZm) && this.BIo.equals(fjl.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("WindowConfiguration{interactionMode=");
        zZm.append(this.zZm);
        zZm.append(", sizeConfigurationId=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
