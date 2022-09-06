package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AttachmentTimeoutsConfiguration.java */
/* loaded from: classes.dex */
public final class RzL extends ojb {
    public final long BIo;
    public final long zQM;
    public final long zZm;

    public RzL(long j, long j2, long j3) {
        this.zZm = j;
        this.BIo = j2;
        this.zQM = j3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ojb)) {
            return false;
        }
        RzL rzL = (RzL) obj;
        return this.zZm == rzL.zZm && this.BIo == rzL.BIo && this.zQM == rzL.zQM;
    }

    public int hashCode() {
        long j = this.zZm;
        long j2 = this.BIo;
        long j3 = this.zQM;
        return ((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AttachmentTimeoutsConfiguration{totalWriteTimeout=");
        zZm.append(this.zZm);
        zZm.append(", writeBytesTimeout=");
        zZm.append(this.BIo);
        zZm.append(", durationLimit=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.ojb
    public long zZm() {
        return this.zQM;
    }
}
