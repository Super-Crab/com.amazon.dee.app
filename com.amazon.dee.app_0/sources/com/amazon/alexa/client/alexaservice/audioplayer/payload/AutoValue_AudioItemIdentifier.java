package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.xNT;
/* loaded from: classes6.dex */
public final class AutoValue_AudioItemIdentifier extends xNT {
    public final String zQM;

    public AutoValue_AudioItemIdentifier(String str) {
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
        if (!(obj instanceof xNT)) {
            return false;
        }
        return this.zQM.equals(((xNT) obj).getValue());
    }

    @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
    public String getValue() {
        return this.zQM;
    }

    public int hashCode() {
        return this.zQM.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("AudioItemIdentifier{value="), this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
