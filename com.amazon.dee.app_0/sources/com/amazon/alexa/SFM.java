package com.amazon.alexa;

import com.amazon.alexa.XFF;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AttachmentWriteFinishedEvent.java */
/* loaded from: classes.dex */
public final class SFM extends XFF {
    public final int BIo;
    public final XFF.zZm zQM;

    public SFM(int i, XFF.zZm zzm) {
        this.BIo = i;
        if (zzm != null) {
            this.zQM = zzm;
            return;
        }
        throw new NullPointerException("Null encodingType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XFF)) {
            return false;
        }
        SFM sfm = (SFM) obj;
        return this.BIo == sfm.BIo && this.zQM.equals(sfm.zQM);
    }

    public int hashCode() {
        return ((this.BIo ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AttachmentWriteFinishedEvent{getBytesWritten=");
        zZm.append(this.BIo);
        zZm.append(", encodingType=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
