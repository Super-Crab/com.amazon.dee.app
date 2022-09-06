package com.amazon.alexa;

import com.amazon.alexa.JGr;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: $AutoValue_AuthorizationCompletePayload.java */
/* loaded from: classes.dex */
public abstract class XgI extends JGr {
    public final Set<JGr.zQM> BIo;
    public final Set<JGr.zZm> zZm;

    /* compiled from: $AutoValue_AuthorizationCompletePayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends JGr.BIo {
        public Set<JGr.zQM> BIo;
        public Set<JGr.zZm> zZm;
    }

    public XgI(Set<JGr.zZm> set, Set<JGr.zQM> set2) {
        if (set != null) {
            this.zZm = set;
            if (set2 != null) {
                this.BIo = set2;
                return;
            }
            throw new NullPointerException("Null deauthorized");
        }
        throw new NullPointerException("Null authorized");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JGr)) {
            return false;
        }
        XgI xgI = (XgI) obj;
        return this.zZm.equals(xgI.zZm) && this.BIo.equals(xgI.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("AuthorizationCompletePayload{authorized=");
        zZm2.append(this.zZm);
        zZm2.append(", deauthorized=");
        return C0179Pya.BIo(zZm2, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
