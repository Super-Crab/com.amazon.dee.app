package com.amazon.alexa;

import com.amazon.alexa.api.AlexaWakeWordListener;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_WakeWord_DeregisterWakeWordListenerEvent.java */
/* loaded from: classes.dex */
public final class mQM extends WGo {
    public final eOP BIo;
    public final AlexaWakeWordListener jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public mQM(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaWakeWordListener alexaWakeWordListener) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (alexaWakeWordListener != null) {
                        this.jiA = alexaWakeWordListener;
                        return;
                    }
                    throw new NullPointerException("Null alexaWakeWordListener");
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
        if (!(obj instanceof WGo)) {
            return false;
        }
        mQM mqm = (mQM) obj;
        return this.BIo.equals(mqm.BIo) && this.zQM.equals(mqm.zQM) && this.zyO.equals(mqm.zyO) && this.jiA.equals(mqm.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DeregisterWakeWordListenerEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", alexaWakeWordListener=");
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
