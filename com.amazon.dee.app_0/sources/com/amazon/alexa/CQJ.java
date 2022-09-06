package com.amazon.alexa;

import com.amazon.alexa.VXG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: AutoValue_WakeWordMetricEvent_PryonErrorEvent.java */
/* loaded from: classes.dex */
public final class CQJ extends VXG.BIo {
    public final int BIo;

    public CQJ(int i) {
        this.BIo = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof VXG.BIo) && this.BIo == ((CQJ) obj).BIo;
    }

    public int hashCode() {
        return this.BIo ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(C0179Pya.zZm("PryonErrorEvent{errorCode="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
