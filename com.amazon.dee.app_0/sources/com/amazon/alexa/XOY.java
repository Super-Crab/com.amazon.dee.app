package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SpeechEventPayload.java */
/* loaded from: classes.dex */
public abstract class XOY extends yXU {
    public final C0176Pce zZm;

    public XOY(C0176Pce c0176Pce) {
        if (c0176Pce != null) {
            this.zZm = c0176Pce;
            return;
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof yXU)) {
            return false;
        }
        return this.zZm.equals(((XOY) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("SpeechEventPayload{token="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
