package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PhoneCallControllerCall.java */
/* loaded from: classes.dex */
public abstract class csl extends HbJ {
    public final vJW zZm;

    public csl(vJW vjw) {
        if (vjw != null) {
            this.zZm = vjw;
            return;
        }
        throw new NullPointerException("Null callId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HbJ)) {
            return false;
        }
        return this.zZm.equals(((csl) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("PhoneCallControllerCall{callId="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
