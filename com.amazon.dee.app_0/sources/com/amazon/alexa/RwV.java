package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: AutoValue_ScoMetricsCounterEvent.java */
/* loaded from: classes.dex */
public final class RwV extends GSJ {
    public final String BIo;
    public final int zQM;

    public RwV(String str, int i) {
        if (str != null) {
            this.BIo = str;
            this.zQM = i;
            return;
        }
        throw new NullPointerException("Null name");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GSJ)) {
            return false;
        }
        RwV rwV = (RwV) obj;
        return this.BIo.equals(rwV.BIo) && this.zQM == rwV.zQM;
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ScoMetricsCounterEvent{name=");
        zZm.append(this.BIo);
        zZm.append(", count=");
        return GeneratedOutlineSupport1.outline86(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
