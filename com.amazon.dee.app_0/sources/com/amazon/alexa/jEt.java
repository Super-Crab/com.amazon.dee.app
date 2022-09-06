package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AdjustVolumePayload.java */
/* loaded from: classes.dex */
public abstract class jEt extends sci {
    public final long zZm;

    public jEt(long j) {
        this.zZm = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof sci) && this.zZm == ((jEt) obj).zZm;
    }

    public int hashCode() {
        long j = this.zZm;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("AdjustVolumePayload{volume="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
