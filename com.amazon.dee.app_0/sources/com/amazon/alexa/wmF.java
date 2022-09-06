package com.amazon.alexa;

import com.amazon.alexa.QYV;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogEvent_TurnAvailableEvent.java */
/* loaded from: classes.dex */
public final class wmF extends QYV.Qle {
    public final NEe BIo;

    public wmF(NEe nEe) {
        if (nEe != null) {
            this.BIo = nEe;
            return;
        }
        throw new NullPointerException("Null multiTurnDialog");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QYV.Qle)) {
            return false;
        }
        return this.BIo.equals(((wmF) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("TurnAvailableEvent{multiTurnDialog="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
