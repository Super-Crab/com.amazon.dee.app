package com.amazon.alexa;

import com.amazon.alexa.QYV;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogEvent_TextDialogReceiveEvent.java */
/* loaded from: classes.dex */
public final class eZz extends QYV.zQM {
    public final DEe BIo;

    public eZz(DEe dEe) {
        if (dEe != null) {
            this.BIo = dEe;
            return;
        }
        throw new NullPointerException("Null speakPayload");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QYV.zQM)) {
            return false;
        }
        return this.BIo.equals(((eZz) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("TextDialogReceiveEvent{speakPayload="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
