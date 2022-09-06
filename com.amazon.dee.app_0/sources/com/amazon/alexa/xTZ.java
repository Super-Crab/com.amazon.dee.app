package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: AutoValue_ExternalComponentStateProviderChangedEvent.java */
/* loaded from: classes.dex */
public final class xTZ extends NUK {
    public final Set<Namespace> BIo;
    public final boolean zQM;

    public xTZ(Set<Namespace> set, boolean z) {
        if (set != null) {
            this.BIo = set;
            this.zQM = z;
            return;
        }
        throw new NullPointerException("Null namespaces");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NUK)) {
            return false;
        }
        xTZ xtz = (xTZ) obj;
        return this.BIo.equals(xtz.BIo) && this.zQM == xtz.zQM;
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalComponentStateProviderChangedEvent{namespaces=");
        zZm.append(this.BIo);
        zZm.append(", present=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
