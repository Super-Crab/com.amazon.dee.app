package com.amazon.alexa;

import com.amazon.alexa.Sas;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PhoneCallControllerDevice.java */
/* loaded from: classes.dex */
public abstract class shY extends Sas {
    public final Sas.zZm zZm;

    public shY(Sas.zZm zzm) {
        if (zzm != null) {
            this.zZm = zzm;
            return;
        }
        throw new NullPointerException("Null connectionState");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Sas)) {
            return false;
        }
        return this.zZm.equals(((shY) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("PhoneCallControllerDevice{connectionState="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
