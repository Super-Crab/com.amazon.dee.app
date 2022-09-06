package com.amazon.alexa;

import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.DriveModeState;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_DriveMode_SetDriveModeStateEvent.java */
/* loaded from: classes.dex */
public final class BPW extends AbstractC0230smc {
    public final eOP BIo;
    public final DriveModeState jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public BPW(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, DriveModeState driveModeState) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (driveModeState != null) {
                        this.jiA = driveModeState;
                        return;
                    }
                    throw new NullPointerException("Null driveModeState");
                }
                throw new NullPointerException("Null getApiCallback");
            }
            throw new NullPointerException("Null getClient");
        }
        throw new NullPointerException("Null getApiCallMetadata");
    }

    @Override // com.amazon.alexa.NEv
    public eOP BIo() {
        return this.BIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0230smc)) {
            return false;
        }
        BPW bpw = (BPW) obj;
        return this.BIo.equals(bpw.BIo) && this.zQM.equals(bpw.zQM) && this.zyO.equals(bpw.zyO) && this.jiA.equals(bpw.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SetDriveModeStateEvent{getApiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", getClient=");
        zZm.append(this.zQM);
        zZm.append(", getApiCallback=");
        zZm.append(this.zyO);
        zZm.append(", driveModeState=");
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
