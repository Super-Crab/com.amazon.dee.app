package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_UpdateTextInteractionEvent.java */
/* loaded from: classes.dex */
public final class tai extends urz {
    public final XWx BIo;
    public final DialogRequestIdentifier zQM;
    public final String zyO;

    public tai(XWx xWx, @Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable String str) {
        if (xWx != null) {
            this.BIo = xWx;
            this.zQM = dialogRequestIdentifier;
            this.zyO = str;
            return;
        }
        throw new NullPointerException("Null dialogTurnId");
    }

    public boolean equals(Object obj) {
        DialogRequestIdentifier dialogRequestIdentifier;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof urz)) {
            return false;
        }
        tai taiVar = (tai) obj;
        if (this.BIo.equals(taiVar.BIo) && ((dialogRequestIdentifier = this.zQM) != null ? dialogRequestIdentifier.equals(taiVar.zQM) : taiVar.zQM == null)) {
            String str = this.zyO;
            if (str == null) {
                if (taiVar.zyO == null) {
                    return true;
                }
            } else if (str.equals(taiVar.zyO)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.BIo.hashCode() ^ 1000003) * 1000003;
        DialogRequestIdentifier dialogRequestIdentifier = this.zQM;
        int i = 0;
        int hashCode2 = (hashCode ^ (dialogRequestIdentifier == null ? 0 : dialogRequestIdentifier.hashCode())) * 1000003;
        String str = this.zyO;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("UpdateTextInteractionEvent{dialogTurnId=");
        zZm.append(this.BIo);
        zZm.append(", dialogRequestId=");
        zZm.append(this.zQM);
        zZm.append(", invocationType=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
