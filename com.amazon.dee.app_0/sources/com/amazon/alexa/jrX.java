package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SetMutePayload.java */
/* loaded from: classes.dex */
public abstract class jrX extends RhW {
    public final boolean zZm;

    public jrX(boolean z) {
        this.zZm = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RhW) && this.zZm == ((jrX) obj).zZm;
    }

    public int hashCode() {
        return (this.zZm ? 1231 : 1237) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("SetMutePayload{mute="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
