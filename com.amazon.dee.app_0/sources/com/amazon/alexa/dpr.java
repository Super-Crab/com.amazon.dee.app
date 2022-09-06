package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_WakeWordIndices.java */
/* loaded from: classes.dex */
public abstract class dpr extends DWt {
    public final long BIo;
    public final long zZm;

    public dpr(long j, long j2) {
        this.zZm = j;
        this.BIo = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DWt)) {
            return false;
        }
        dpr dprVar = (dpr) obj;
        return this.zZm == dprVar.zZm && this.BIo == dprVar.BIo;
    }

    public int hashCode() {
        long j = this.zZm;
        long j2 = this.BIo;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("WakeWordIndices{startIndexInSamples=");
        zZm.append(this.zZm);
        zZm.append(", endIndexInSamples=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
