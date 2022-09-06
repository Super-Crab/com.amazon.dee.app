package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: $AutoValue_IOComponentsStatePayload.java */
/* loaded from: classes.dex */
public abstract class JdP extends Agi {
    public final Set<tWv> BIo;
    public final Set<tWv> zZm;

    public JdP(Set<tWv> set, Set<tWv> set2) {
        if (set != null) {
            this.zZm = set;
            if (set2 != null) {
                this.BIo = set2;
                return;
            }
            throw new NullPointerException("Null allIOComponents");
        }
        throw new NullPointerException("Null activeIOComponents");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Agi)) {
            return false;
        }
        JdP jdP = (JdP) obj;
        return this.zZm.equals(jdP.zZm) && this.BIo.equals(jdP.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("IOComponentsStatePayload{activeIOComponents=");
        zZm.append(this.zZm);
        zZm.append(", allIOComponents=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
