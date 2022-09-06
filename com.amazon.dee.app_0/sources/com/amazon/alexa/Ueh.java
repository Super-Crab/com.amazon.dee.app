package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_RecognizerStatePayload.java */
/* loaded from: classes.dex */
public abstract class Ueh extends Bfv {
    public final eYr BIo;

    public Ueh(eYr eyr) {
        if (eyr != null) {
            this.BIo = eyr;
            return;
        }
        throw new NullPointerException("Null wakeword");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bfv)) {
            return false;
        }
        return this.BIo.equals(((Ueh) obj).BIo);
    }

    public int hashCode() {
        String str = this.BIo.BIo;
        return (str != null ? str.hashCode() : 0) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("RecognizerStatePayload{wakeword="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
