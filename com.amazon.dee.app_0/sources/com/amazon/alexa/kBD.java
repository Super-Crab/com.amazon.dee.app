package com.amazon.alexa;

import com.amazon.alexa.VXG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_WakeWordMetricEvent_PryonInitializationFailureEvent.java */
/* loaded from: classes.dex */
public final class kBD extends VXG.zQM {
    public final int BIo;
    public final String zQM;

    public kBD(int i, String str) {
        this.BIo = i;
        if (str != null) {
            this.zQM = str;
            return;
        }
        throw new NullPointerException("Null modelMD5");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VXG.zQM)) {
            return false;
        }
        kBD kbd = (kBD) obj;
        return this.BIo == kbd.BIo && this.zQM.equals(kbd.zQM);
    }

    public int hashCode() {
        return ((this.BIo ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PryonInitializationFailureEvent{errorCode=");
        zZm.append(this.BIo);
        zZm.append(", modelMD5=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
