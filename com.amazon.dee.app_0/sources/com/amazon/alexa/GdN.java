package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_CapabilityAutoUpdate.java */
/* loaded from: classes.dex */
public final class GdN extends EPu {
    public final boolean zZm;

    public GdN(boolean z) {
        this.zZm = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof EPu) && this.zZm == ((GdN) obj).zZm;
    }

    public int hashCode() {
        return (this.zZm ? 1231 : 1237) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("CapabilityAutoUpdate{value="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
