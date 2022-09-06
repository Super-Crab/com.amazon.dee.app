package com.amazon.alexa;

import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogEvent_NewDialogRequestedEvent.java */
/* loaded from: classes.dex */
public final class lDN extends QYV.zZm {
    public final esV BIo;
    public final String jiA;
    public final mqw zQM;
    public final AlexaDialogRequest zyO;

    public lDN(esV esv, mqw mqwVar, AlexaDialogRequest alexaDialogRequest, String str) {
        if (esv != null) {
            this.BIo = esv;
            if (mqwVar != null) {
                this.zQM = mqwVar;
                if (alexaDialogRequest != null) {
                    this.zyO = alexaDialogRequest;
                    if (str != null) {
                        this.jiA = str;
                        return;
                    }
                    throw new NullPointerException("Null wakeword");
                }
                throw new NullPointerException("Null alexaDialogRequest");
            }
            throw new NullPointerException("Null userSpeechProvider");
        }
        throw new NullPointerException("Null launchSource");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QYV.zZm)) {
            return false;
        }
        lDN ldn = (lDN) obj;
        return this.BIo.equals(ldn.BIo) && this.zQM.equals(ldn.zQM) && this.zyO.equals(ldn.zyO) && this.jiA.equals(ldn.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("NewDialogRequestedEvent{launchSource=");
        zZm.append(this.BIo);
        zZm.append(", userSpeechProvider=");
        zZm.append(this.zQM);
        zZm.append(", alexaDialogRequest=");
        zZm.append(this.zyO);
        zZm.append(", wakeword=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
