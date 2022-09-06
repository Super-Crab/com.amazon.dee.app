package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_EventTarget.java */
/* loaded from: classes.dex */
public abstract class YUQ extends StC {
    public final String zZm;

    public YUQ(String str) {
        if (str != null) {
            this.zZm = str;
            return;
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StC)) {
            return false;
        }
        return this.zZm.equals(((YUQ) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("EventTarget{token="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
