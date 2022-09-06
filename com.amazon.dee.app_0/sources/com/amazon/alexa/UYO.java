package com.amazon.alexa;

import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogEvent_TextRequestDialogEvent.java */
/* loaded from: classes.dex */
public final class UYO extends QYV.zyO {
    public final ExtendedClient BIo;
    public final eOP JTe;
    public final AlexaDialogExtras Qle;
    public final AlexaDialogRequest jiA;
    public final String zQM;
    public final esV zyO;

    public UYO(ExtendedClient extendedClient, String str, esV esv, AlexaDialogRequest alexaDialogRequest, AlexaDialogExtras alexaDialogExtras, eOP eop) {
        if (extendedClient != null) {
            this.BIo = extendedClient;
            if (str != null) {
                this.zQM = str;
                if (esv != null) {
                    this.zyO = esv;
                    if (alexaDialogRequest != null) {
                        this.jiA = alexaDialogRequest;
                        if (alexaDialogExtras != null) {
                            this.Qle = alexaDialogExtras;
                            if (eop != null) {
                                this.JTe = eop;
                                return;
                            }
                            throw new NullPointerException("Null apiCallMetadata");
                        }
                        throw new NullPointerException("Null alexaDialogExtras");
                    }
                    throw new NullPointerException("Null dialogRequest");
                }
                throw new NullPointerException("Null launchSource");
            }
            throw new NullPointerException("Null message");
        }
        throw new NullPointerException("Null client");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QYV.zyO)) {
            return false;
        }
        UYO uyo = (UYO) obj;
        return this.BIo.equals(uyo.BIo) && this.zQM.equals(uyo.zQM) && this.zyO.equals(uyo.zyO) && this.jiA.equals(uyo.jiA) && this.Qle.equals(uyo.Qle) && this.JTe.equals(uyo.JTe);
    }

    public int hashCode() {
        return ((((((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode()) * 1000003) ^ this.JTe.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("TextRequestDialogEvent{client=");
        zZm.append(this.BIo);
        zZm.append(", message=");
        zZm.append(this.zQM);
        zZm.append(", launchSource=");
        zZm.append(this.zyO);
        zZm.append(", dialogRequest=");
        zZm.append(this.jiA);
        zZm.append(", alexaDialogExtras=");
        zZm.append(this.Qle);
        zZm.append(", apiCallMetadata=");
        return C0179Pya.BIo(zZm, this.JTe, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
