package com.amazon.alexa;

import android.net.Uri;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SetEndpointPayload.java */
/* loaded from: classes.dex */
public abstract class IGY extends mPf {
    public final Uri zZm;

    public IGY(Uri uri) {
        if (uri != null) {
            this.zZm = uri;
            return;
        }
        throw new NullPointerException("Null endpoint");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof mPf)) {
            return false;
        }
        return this.zZm.equals(((IGY) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("SetEndpointPayload{endpoint="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
