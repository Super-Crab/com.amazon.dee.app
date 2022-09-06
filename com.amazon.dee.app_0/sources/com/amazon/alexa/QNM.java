package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ServiceCreatedEvent.java */
/* loaded from: classes.dex */
public final class QNM extends tkb {
    public final long BIo;
    public final long zQM;

    public QNM(long j, long j2) {
        this.BIo = j;
        this.zQM = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof tkb)) {
            return false;
        }
        QNM qnm = (QNM) obj;
        return this.BIo == qnm.BIo && this.zQM == qnm.zQM;
    }

    public int hashCode() {
        long j = this.BIo;
        long j2 = this.zQM;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ServiceCreatedEvent{serviceCreationStartTimeMs=");
        zZm.append(this.BIo);
        zZm.append(", serviceCreationEndTimeMs=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
