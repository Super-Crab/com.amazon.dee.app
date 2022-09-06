package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PlaybackStutterFinishedEventPayload.java */
/* loaded from: classes.dex */
public abstract class lcl extends pLw {
    public final long BIo;
    public final long zQM;
    public final Puy zZm;

    public lcl(Puy puy, long j, long j2) {
        if (puy != null) {
            this.zZm = puy;
            this.BIo = j;
            this.zQM = j2;
            return;
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof pLw)) {
            return false;
        }
        lcl lclVar = (lcl) obj;
        return this.zZm.equals(lclVar.zZm) && this.BIo == lclVar.BIo && this.zQM == lclVar.zQM;
    }

    public int hashCode() {
        long j = this.BIo;
        long j2 = this.zQM;
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PlaybackStutterFinishedEventPayload{token=");
        zZm.append(this.zZm);
        zZm.append(", offsetInMilliseconds=");
        zZm.append(this.BIo);
        zZm.append(", stutterDurationInMilliseconds=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
