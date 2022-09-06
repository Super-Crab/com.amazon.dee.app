package com.amazon.alexa;

import com.amazon.alexa.UVo;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ClearQueuePayload.java */
/* loaded from: classes.dex */
public abstract class kXG extends UVo {
    public final UVo.zZm zZm;

    public kXG(UVo.zZm zzm) {
        if (zzm != null) {
            this.zZm = zzm;
            return;
        }
        throw new NullPointerException("Null clearBehavior");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UVo)) {
            return false;
        }
        return this.zZm.equals(((kXG) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("ClearQueuePayload{clearBehavior="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
