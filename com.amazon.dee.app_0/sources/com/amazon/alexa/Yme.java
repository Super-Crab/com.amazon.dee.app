package com.amazon.alexa;

import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogEvent_RequestDialogEvent.java */
/* loaded from: classes.dex */
public final class Yme extends QYV.BIo {
    public final esV BIo;
    public final AlexaDialogRequest zQM;
    public final AlexaDialogExtras zyO;

    public Yme(esV esv, AlexaDialogRequest alexaDialogRequest, AlexaDialogExtras alexaDialogExtras) {
        if (esv != null) {
            this.BIo = esv;
            if (alexaDialogRequest != null) {
                this.zQM = alexaDialogRequest;
                if (alexaDialogExtras != null) {
                    this.zyO = alexaDialogExtras;
                    return;
                }
                throw new NullPointerException("Null alexaDialogExtras");
            }
            throw new NullPointerException("Null dialogRequest");
        }
        throw new NullPointerException("Null launchSource");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QYV.BIo)) {
            return false;
        }
        Yme yme = (Yme) obj;
        return this.BIo.equals(yme.BIo) && this.zQM.equals(yme.zQM) && this.zyO.equals(yme.zyO);
    }

    public int hashCode() {
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("RequestDialogEvent{launchSource=");
        zZm.append(this.BIo);
        zZm.append(", dialogRequest=");
        zZm.append(this.zQM);
        zZm.append(", alexaDialogExtras=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
