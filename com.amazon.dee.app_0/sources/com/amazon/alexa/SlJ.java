package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ProgressReport.java */
/* loaded from: classes.dex */
public abstract class SlJ extends CiJ {
    public final long BIo;
    public final long zZm;

    public SlJ(long j, long j2) {
        this.zZm = j;
        this.BIo = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CiJ)) {
            return false;
        }
        SlJ slJ = (SlJ) obj;
        return this.zZm == slJ.zZm && this.BIo == slJ.BIo;
    }

    public int hashCode() {
        long j = this.zZm;
        long j2 = this.BIo;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ProgressReport{progressReportDelayInMilliseconds=");
        zZm.append(this.zZm);
        zZm.append(", progressReportIntervalInMilliseconds=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
