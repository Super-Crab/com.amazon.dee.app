package com.amazon.alexa;

import com.amazon.alexa.api.AlexaMediaPlaybackListener;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_MediaPlayback_RegisterMediaPlayerListenerEvent.java */
/* loaded from: classes.dex */
public final class UaM extends vhe {
    public final eOP BIo;
    public final AlexaMediaPlaybackListener jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public UaM(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (alexaMediaPlaybackListener != null) {
                        this.jiA = alexaMediaPlaybackListener;
                        return;
                    }
                    throw new NullPointerException("Null alexaMediaPlaybackListener");
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
        if (!(obj instanceof vhe)) {
            return false;
        }
        UaM uaM = (UaM) obj;
        return this.BIo.equals(uaM.BIo) && this.zQM.equals(uaM.zQM) && this.zyO.equals(uaM.zyO) && this.jiA.equals(uaM.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("RegisterMediaPlayerListenerEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", alexaMediaPlaybackListener=");
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
