package com.amazon.alexa;

import android.app.PendingIntent;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_MediaPlayback_SetMediaNotificationContentIntentEvent.java */
/* loaded from: classes.dex */
public final class DxN extends kAu {
    public final eOP BIo;
    public final PendingIntent jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public DxN(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, PendingIntent pendingIntent) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (pendingIntent != null) {
                        this.jiA = pendingIntent;
                        return;
                    }
                    throw new NullPointerException("Null pendingIntent");
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
        if (!(obj instanceof kAu)) {
            return false;
        }
        DxN dxN = (DxN) obj;
        return this.BIo.equals(dxN.BIo) && this.zQM.equals(dxN.zQM) && this.zyO.equals(dxN.zyO) && this.jiA.equals(dxN.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SetMediaNotificationContentIntentEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", pendingIntent=");
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
