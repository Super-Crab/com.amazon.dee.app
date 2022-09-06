package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.hFk;
/* compiled from: $AutoValue_PlaybackFailedEventPayload_Error.java */
/* loaded from: classes.dex */
public abstract class aZz extends hFk.BIo {
    public final String BIo;
    public final hFk.zQM zZm;

    public aZz(hFk.zQM zqm, String str) {
        if (zqm != null) {
            this.zZm = zqm;
            if (str != null) {
                this.BIo = str;
                return;
            }
            throw new NullPointerException("Null message");
        }
        throw new NullPointerException("Null type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof hFk.BIo)) {
            return false;
        }
        aZz azz = (aZz) obj;
        return this.zZm.equals(azz.zZm) && this.BIo.equals(azz.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Error{type=");
        zZm.append(this.zZm);
        zZm.append(", message=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
