package com.amazon.alexa;

import com.amazon.alexa.AbstractC0173MQv;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AttachmentWriteFailureEvent.java */
/* loaded from: classes.dex */
public final class aOP extends AbstractC0173MQv {
    public final AbstractC0173MQv.zZm BIo;

    public aOP(AbstractC0173MQv.zZm zzm) {
        if (zzm != null) {
            this.BIo = zzm;
            return;
        }
        throw new NullPointerException("Null type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0173MQv)) {
            return false;
        }
        return this.BIo.equals(((aOP) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AttachmentWriteFailureEvent{type="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
