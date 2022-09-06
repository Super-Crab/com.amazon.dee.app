package com.amazon.alexa;

import com.amazon.alexa.BaP;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_VoiceInteractionEvent_ProgressEvent.java */
/* loaded from: classes.dex */
public final class hZA extends BaP.BIo {
    public final String BIo;
    public final String zQM;
    public final DialogRequestIdentifier zyO;

    public hZA(String str, String str2, DialogRequestIdentifier dialogRequestIdentifier) {
        if (str != null) {
            this.BIo = str;
            if (str2 != null) {
                this.zQM = str2;
                if (dialogRequestIdentifier != null) {
                    this.zyO = dialogRequestIdentifier;
                    return;
                }
                throw new NullPointerException("Null dialogRequestId");
            }
            throw new NullPointerException("Null invocationType");
        }
        throw new NullPointerException("Null name");
    }

    @Override // com.amazon.alexa.BaP
    public DialogRequestIdentifier BIo() {
        return this.zyO;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaP.BIo)) {
            return false;
        }
        BaP.BIo bIo = (BaP.BIo) obj;
        return this.BIo.equals(bIo.zyO()) && this.zQM.equals(bIo.zQM()) && this.zyO.equals(bIo.BIo());
    }

    public int hashCode() {
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ProgressEvent{name=");
        zZm.append(this.BIo);
        zZm.append(", invocationType=");
        zZm.append(this.zQM);
        zZm.append(", dialogRequestId=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.BaP
    public String zQM() {
        return this.zQM;
    }

    @Override // com.amazon.alexa.BaP
    public String zyO() {
        return this.BIo;
    }
}
