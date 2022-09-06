package com.amazon.alexa;

import com.amazon.alexa.LWv;
import com.amazon.alexa.TTH;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ExceptionEncounteredPayload_Error.java */
/* loaded from: classes.dex */
public abstract class axq extends LWv.BIo {
    public final String BIo;
    public final TTH.zZm zZm;

    public axq(TTH.zZm zzm, String str) {
        if (zzm != null) {
            this.zZm = zzm;
            if (str != null) {
                this.BIo = str;
                return;
            }
            throw new NullPointerException("Null message");
        }
        throw new NullPointerException("Null type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LWv.BIo)) {
            return false;
        }
        axq axqVar = (axq) obj;
        return this.zZm.equals(axqVar.zZm) && this.BIo.equals(axqVar.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Error{type=");
        zZm.append(this.zZm);
        zZm.append(", message=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
