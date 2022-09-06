package com.amazon.alexa;

import com.amazon.alexa.Ims;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: $AutoValue_AuthorizeDiscoveredPlayersPayload.java */
/* loaded from: classes.dex */
public abstract class AkY extends Ims {
    public final Set<Ims.zZm> zZm;

    public AkY(Set<Ims.zZm> set) {
        if (set != null) {
            this.zZm = set;
            return;
        }
        throw new NullPointerException("Null players");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ims)) {
            return false;
        }
        return this.zZm.equals(((AkY) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AuthorizeDiscoveredPlayersPayload{players="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
