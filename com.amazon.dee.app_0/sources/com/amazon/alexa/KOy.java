package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: $AutoValue_DisplayWindowStatePayload.java */
/* loaded from: classes.dex */
public abstract class KOy extends Bsa {
    public final Set<uEF> BIo;
    public final String zZm;

    public KOy(String str, Set<uEF> set) {
        if (str != null) {
            this.zZm = str;
            if (set != null) {
                this.BIo = set;
                return;
            }
            throw new NullPointerException("Null instances");
        }
        throw new NullPointerException("Null defaultWindowId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bsa)) {
            return false;
        }
        KOy kOy = (KOy) obj;
        return this.zZm.equals(kOy.zZm) && this.BIo.equals(kOy.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DisplayWindowStatePayload{defaultWindowId=");
        zZm.append(this.zZm);
        zZm.append(", instances=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
