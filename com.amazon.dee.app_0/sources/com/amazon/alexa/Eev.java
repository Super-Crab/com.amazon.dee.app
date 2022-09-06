package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_TimeZoneChangedPayload.java */
/* loaded from: classes.dex */
public abstract class Eev extends Ejh {
    public final SOo zZm;

    public Eev(SOo sOo) {
        if (sOo != null) {
            this.zZm = sOo;
            return;
        }
        throw new NullPointerException("Null timeZone");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ejh)) {
            return false;
        }
        return this.zZm.equals(((Eev) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("TimeZoneChangedPayload{timeZone="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
