package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_UpdateVoiceInteractionProgressEvent.java */
/* loaded from: classes.dex */
public final class jcN extends EOM {
    public final XWx BIo;
    public final DialogRequestIdentifier zQM;
    public final YOj zyO;

    public jcN(@Nullable XWx xWx, @Nullable DialogRequestIdentifier dialogRequestIdentifier, YOj yOj) {
        this.BIo = xWx;
        this.zQM = dialogRequestIdentifier;
        if (yOj != null) {
            this.zyO = yOj;
            return;
        }
        throw new NullPointerException("Null progress");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EOM)) {
            return false;
        }
        XWx xWx = this.BIo;
        if (xWx != null ? xWx.equals(((jcN) obj).BIo) : ((jcN) obj).BIo == null) {
            DialogRequestIdentifier dialogRequestIdentifier = this.zQM;
            if (dialogRequestIdentifier != null ? dialogRequestIdentifier.equals(((jcN) obj).zQM) : ((jcN) obj).zQM == null) {
                if (this.zyO.equals(((jcN) obj).zyO)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        XWx xWx = this.BIo;
        int i = 0;
        int hashCode = ((xWx == null ? 0 : xWx.hashCode()) ^ 1000003) * 1000003;
        DialogRequestIdentifier dialogRequestIdentifier = this.zQM;
        if (dialogRequestIdentifier != null) {
            i = dialogRequestIdentifier.hashCode();
        }
        return ((hashCode ^ i) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("UpdateVoiceInteractionProgressEvent{dialogTurnId=");
        zZm.append(this.BIo);
        zZm.append(", dialogRequestId=");
        zZm.append(this.zQM);
        zZm.append(", progress=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
