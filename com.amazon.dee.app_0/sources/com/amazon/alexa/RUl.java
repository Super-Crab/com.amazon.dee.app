package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AlexaStateChangedEvent.java */
/* loaded from: classes.dex */
public final class RUl extends HDT {
    public final wSq BIo;

    public RUl(wSq wsq) {
        if (wsq != null) {
            this.BIo = wsq;
            return;
        }
        throw new NullPointerException("Null alexaState");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HDT)) {
            return false;
        }
        return this.BIo.equals(((RUl) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AlexaStateChangedEvent{alexaState="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
