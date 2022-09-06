package com.amazon.alexa;

import com.amazon.alexa.api.AlexaMediaPlaybackListener;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_MediaPlayback_DeregisterMediaPlayerListenerEvent.java */
/* renamed from: com.amazon.alexa.dlc  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0199dlc extends eXo {
    public final eOP BIo;
    public final AlexaMediaPlaybackListener jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public C0199dlc(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
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
        if (!(obj instanceof eXo)) {
            return false;
        }
        C0199dlc c0199dlc = (C0199dlc) obj;
        return this.BIo.equals(c0199dlc.BIo) && this.zQM.equals(c0199dlc.zQM) && this.zyO.equals(c0199dlc.zyO) && this.jiA.equals(c0199dlc.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DeregisterMediaPlayerListenerEvent{apiCallMetadata=");
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
