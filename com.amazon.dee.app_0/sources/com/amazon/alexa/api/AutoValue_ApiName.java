package com.amazon.alexa.api;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* loaded from: classes6.dex */
public final class AutoValue_ApiName extends ApiName {
    public final String CGv;

    public AutoValue_ApiName(String str) {
        if (str != null) {
            this.CGv = str;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiName)) {
            return false;
        }
        return this.CGv.equals(((AutoValue_ApiName) obj).CGv);
    }

    public int hashCode() {
        return this.CGv.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("ApiName{value="), this.CGv, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
