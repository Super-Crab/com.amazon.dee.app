package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_TimeZoneReportPayload.java */
/* loaded from: classes.dex */
public abstract class jTr extends yaQ {
    public final String zZm;

    public jTr(String str) {
        if (str != null) {
            this.zZm = str;
            return;
        }
        throw new NullPointerException("Null timeZone");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof yaQ)) {
            return false;
        }
        return this.zZm.equals(((jTr) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("TimeZoneReportPayload{timeZone="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
