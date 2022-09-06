package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.vST;
import java.util.Set;
/* compiled from: $AutoValue_ReportDiscoveredPlayersPayload.java */
/* loaded from: classes.dex */
public abstract class LTt extends vST {
    public final Set<vST.BIo> BIo;
    public final String zZm;

    /* compiled from: $AutoValue_ReportDiscoveredPlayersPayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends vST.zZm {
        public Set<vST.BIo> BIo;
        public String zZm;
    }

    public LTt(String str, Set<vST.BIo> set) {
        if (str != null) {
            this.zZm = str;
            if (set != null) {
                this.BIo = set;
                return;
            }
            throw new NullPointerException("Null players");
        }
        throw new NullPointerException("Null agent");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vST)) {
            return false;
        }
        LTt lTt = (LTt) obj;
        return this.zZm.equals(lTt.zZm) && this.BIo.equals(lTt.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("ReportDiscoveredPlayersPayload{agent=");
        zZm2.append(this.zZm);
        zZm2.append(", players=");
        return C0179Pya.BIo(zZm2, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
