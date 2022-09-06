package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.urO;
/* compiled from: $AutoValue_Connection.java */
/* loaded from: classes.dex */
public abstract class kJm extends urO {
    public final urO.zZm zZm;

    public kJm(urO.zZm zzm) {
        if (zzm != null) {
            this.zZm = zzm;
            return;
        }
        throw new NullPointerException("Null type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof urO)) {
            return false;
        }
        return this.zZm.equals(((kJm) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("Connection{type="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
