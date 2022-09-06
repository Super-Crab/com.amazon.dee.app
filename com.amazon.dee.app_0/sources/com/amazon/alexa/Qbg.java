package com.amazon.alexa;

import com.amazon.alexa.BaP;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_VoiceInteractionEvent_AttemptEvent.java */
/* loaded from: classes.dex */
public final class Qbg extends BaP.zZm {
    public final String BIo;
    public final String jiA;
    public final String zQM;
    public final DialogRequestIdentifier zyO;

    public Qbg(String str, String str2, DialogRequestIdentifier dialogRequestIdentifier, String str3) {
        if (str != null) {
            this.BIo = str;
            if (str2 != null) {
                this.zQM = str2;
                if (dialogRequestIdentifier != null) {
                    this.zyO = dialogRequestIdentifier;
                    if (str3 != null) {
                        this.jiA = str3;
                        return;
                    }
                    throw new NullPointerException("Null softwareVersion");
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
        if (!(obj instanceof BaP.zZm)) {
            return false;
        }
        Qbg qbg = (Qbg) obj;
        return this.BIo.equals(qbg.BIo) && this.zQM.equals(qbg.zQM) && this.zyO.equals(qbg.zyO) && this.jiA.equals(qbg.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AttemptEvent{name=");
        zZm.append(this.BIo);
        zZm.append(", invocationType=");
        zZm.append(this.zQM);
        zZm.append(", dialogRequestId=");
        zZm.append(this.zyO);
        zZm.append(", softwareVersion=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
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
