package com.amazon.alexa;

import com.amazon.alexa.api.AlexaTextResponseListener;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_Text_DeregisterTextResponseListenerEvent.java */
/* loaded from: classes.dex */
public final class KLb extends KMu {
    public final eOP BIo;
    public final AlexaTextResponseListener jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public KLb(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaTextResponseListener alexaTextResponseListener) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (alexaTextResponseListener != null) {
                        this.jiA = alexaTextResponseListener;
                        return;
                    }
                    throw new NullPointerException("Null alexaTextResponseListener");
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
        if (!(obj instanceof KMu)) {
            return false;
        }
        KLb kLb = (KLb) obj;
        return this.BIo.equals(kLb.BIo) && this.zQM.equals(kLb.zQM) && this.zyO.equals(kLb.zyO) && this.jiA.equals(kLb.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DeregisterTextResponseListenerEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", alexaTextResponseListener=");
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
