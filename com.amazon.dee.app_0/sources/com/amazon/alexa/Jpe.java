package com.amazon.alexa;

import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_Text_SendTextMessageEvent.java */
/* loaded from: classes.dex */
public final class Jpe extends uxJ {
    public final eOP BIo;
    public final AlexaDialogExtras Qle;
    public final String jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public Jpe(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, String str, AlexaDialogExtras alexaDialogExtras) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (str != null) {
                        this.jiA = str;
                        if (alexaDialogExtras != null) {
                            this.Qle = alexaDialogExtras;
                            return;
                        }
                        throw new NullPointerException("Null alexaDialogExtras");
                    }
                    throw new NullPointerException("Null message");
                }
                throw new NullPointerException("Null apiCallback");
            }
            throw new NullPointerException("Null client");
        }
        throw new NullPointerException("Null apiCallMetadata");
    }

    @Override // com.amazon.alexa.NEv
    public eOP BIo() {
        return this.BIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof uxJ)) {
            return false;
        }
        Jpe jpe = (Jpe) obj;
        return this.BIo.equals(jpe.BIo) && this.zQM.equals(jpe.zQM) && this.zyO.equals(jpe.zyO) && this.jiA.equals(jpe.jiA) && this.Qle.equals(jpe.Qle);
    }

    public int hashCode() {
        return ((((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SendTextMessageEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", message=");
        zZm.append(this.jiA);
        zZm.append(", alexaDialogExtras=");
        return C0179Pya.BIo(zZm, this.Qle, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.NEv.BIo
    public ApiCallback zQM() {
        return this.zyO;
    }

    @Override // com.amazon.alexa.NEv.BIo
    public ExtendedClient zyO() {
        return this.zQM;
    }
}
