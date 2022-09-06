package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.uxs;
/* compiled from: AutoValue_PromptPlayerEvent.java */
/* loaded from: classes.dex */
public final class SHw extends uxs {
    public final uxs.zZm BIo;

    public SHw(uxs.zZm zzm) {
        if (zzm != null) {
            this.BIo = zzm;
            return;
        }
        throw new NullPointerException("Null promptType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof uxs)) {
            return false;
        }
        return this.BIo.equals(((SHw) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("PromptPlayerEvent{promptType="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
