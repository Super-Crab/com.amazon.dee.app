package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: $AutoValue_SettingsPayload.java */
/* loaded from: classes.dex */
public abstract class rNq extends xsd {
    public final List<qWv> zZm;

    public rNq(List<qWv> list) {
        if (list != null) {
            this.zZm = list;
            return;
        }
        throw new NullPointerException("Null settings");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof xsd)) {
            return false;
        }
        return this.zZm.equals(((rNq) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("SettingsPayload{settings="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
