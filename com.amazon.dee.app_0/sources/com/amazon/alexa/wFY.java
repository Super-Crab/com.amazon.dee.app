package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_CardDisplayedEvent.java */
/* loaded from: classes.dex */
public final class wFY extends HBD {
    public final DialogRequestIdentifier BIo;
    public final String zQM;

    public wFY(@Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable String str) {
        this.BIo = dialogRequestIdentifier;
        this.zQM = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HBD)) {
            return false;
        }
        DialogRequestIdentifier dialogRequestIdentifier = this.BIo;
        if (dialogRequestIdentifier != null ? dialogRequestIdentifier.equals(((wFY) obj).BIo) : ((wFY) obj).BIo == null) {
            String str = this.zQM;
            if (str == null) {
                if (((wFY) obj).zQM == null) {
                    return true;
                }
            } else if (str.equals(((wFY) obj).zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        DialogRequestIdentifier dialogRequestIdentifier = this.BIo;
        int i = 0;
        int hashCode = ((dialogRequestIdentifier == null ? 0 : dialogRequestIdentifier.hashCode()) ^ 1000003) * 1000003;
        String str = this.zQM;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("CardDisplayedEvent{dialogRequestId=");
        zZm.append(this.BIo);
        zZm.append(", cardType=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
