package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SetEndOfSpeechOffsetPayload.java */
/* loaded from: classes.dex */
public abstract class vOm extends NYz {
    public final long zZm;

    public vOm(long j) {
        this.zZm = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof NYz) && this.zZm == ((vOm) obj).zZm;
    }

    public int hashCode() {
        long j = this.zZm;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("SetEndOfSpeechOffsetPayload{endOfSpeechOffsetInMilliseconds="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
