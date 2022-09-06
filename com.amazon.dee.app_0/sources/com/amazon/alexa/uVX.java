package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_UserSpeechProviderIdentifier.java */
/* loaded from: classes.dex */
public final class uVX extends piE {
    public final String zQM;

    public uVX(String str) {
        if (str != null) {
            this.zQM = str;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof piE)) {
            return false;
        }
        return this.zQM.equals(((piE) obj).getValue());
    }

    @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
    public String getValue() {
        return this.zQM;
    }

    public int hashCode() {
        return this.zQM.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("UserSpeechProviderIdentifier{value="), this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
