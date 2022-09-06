package com.amazon.alexa;

import com.amazon.alexa.api.AlexaExpectTextListener;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_Text_RegisterExpectTextListenerEvent.java */
/* loaded from: classes.dex */
public final class pwz extends Ezv {
    public final eOP BIo;
    public final AlexaExpectTextListener jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public pwz(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaExpectTextListener alexaExpectTextListener) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (alexaExpectTextListener != null) {
                        this.jiA = alexaExpectTextListener;
                        return;
                    }
                    throw new NullPointerException("Null alexaExpectTextListener");
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
        if (!(obj instanceof Ezv)) {
            return false;
        }
        pwz pwzVar = (pwz) obj;
        return this.BIo.equals(pwzVar.BIo) && this.zQM.equals(pwzVar.zQM) && this.zyO.equals(pwzVar.zyO) && this.jiA.equals(pwzVar.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("RegisterExpectTextListenerEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", alexaExpectTextListener=");
        return C0179Pya.BIo(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
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
