package com.amazon.alexa;

import com.amazon.alexa.JGr;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AuthorizationCompletePayload_Deauthorized.java */
/* loaded from: classes.dex */
public abstract class Peh extends JGr.zQM {
    public final FHI zZm;

    public Peh(FHI fhi) {
        if (fhi != null) {
            this.zZm = fhi;
            return;
        }
        throw new NullPointerException("Null localPlayerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JGr.zQM)) {
            return false;
        }
        return this.zZm.equals(((Peh) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("Deauthorized{localPlayerId="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
