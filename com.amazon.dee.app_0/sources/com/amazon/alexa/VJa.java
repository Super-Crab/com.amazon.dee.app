package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.fuM;
/* compiled from: AutoValue_RecordingEvent_StopEvent.java */
/* loaded from: classes.dex */
public final class VJa extends fuM.zQM {
    public final fuM.zyO BIo;

    public VJa(fuM.zyO zyo) {
        if (zyo != null) {
            this.BIo = zyo;
            return;
        }
        throw new NullPointerException("Null stopRecordingSource");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fuM.zQM)) {
            return false;
        }
        return this.BIo.equals(((VJa) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("StopEvent{stopRecordingSource="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
