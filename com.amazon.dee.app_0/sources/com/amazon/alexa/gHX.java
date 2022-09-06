package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SetVolumePayload.java */
/* loaded from: classes.dex */
public abstract class gHX extends tRN {
    public final long zZm;

    public gHX(long j) {
        this.zZm = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof tRN) && this.zZm == ((gHX) obj).zZm;
    }

    public int hashCode() {
        long j = this.zZm;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("SetVolumePayload{volume="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
