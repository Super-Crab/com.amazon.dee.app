package com.amazon.alexa;

import com.amazon.alexa.api.AlexaDriveModeListener;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_DriveMode_RegisterDriveModeListenerEvent.java */
/* loaded from: classes.dex */
public final class EOT extends DqQ {
    public final eOP BIo;
    public final AlexaDriveModeListener jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public EOT(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaDriveModeListener alexaDriveModeListener) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (alexaDriveModeListener != null) {
                        this.jiA = alexaDriveModeListener;
                        return;
                    }
                    throw new NullPointerException("Null alexaDriveModeListener");
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
        if (!(obj instanceof DqQ)) {
            return false;
        }
        EOT eot = (EOT) obj;
        return this.BIo.equals(eot.BIo) && this.zQM.equals(eot.zQM) && this.zyO.equals(eot.zyO) && this.jiA.equals(eot.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("RegisterDriveModeListenerEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", alexaDriveModeListener=");
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
