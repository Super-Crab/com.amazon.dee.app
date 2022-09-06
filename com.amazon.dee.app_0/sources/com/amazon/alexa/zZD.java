package com.amazon.alexa;

import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_DriveMode_SetDriveModeEnabledEvent.java */
/* loaded from: classes.dex */
public final class zZD extends ShT {
    public final eOP BIo;
    public final boolean jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public zZD(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, boolean z) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    this.jiA = z;
                    return;
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
        if (!(obj instanceof ShT)) {
            return false;
        }
        zZD zzd = (zZD) obj;
        return this.BIo.equals(zzd.BIo) && this.zQM.equals(zzd.zQM) && this.zyO.equals(zzd.zyO) && this.jiA == zzd.jiA;
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ (this.jiA ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SetDriveModeEnabledEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", driveModeEnabled=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
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
