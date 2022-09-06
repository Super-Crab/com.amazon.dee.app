package com.amazon.alexa;

import com.amazon.alexa.api.AlexaVisualTask;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_VisualTask_UnscheduleVisualTaskEvent.java */
/* loaded from: classes.dex */
public final class Ixs extends ArZ {
    public final eOP BIo;
    public final AlexaVisualTask jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public Ixs(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaVisualTask alexaVisualTask) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (alexaVisualTask != null) {
                        this.jiA = alexaVisualTask;
                        return;
                    }
                    throw new NullPointerException("Null alexaVisualTask");
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
        if (!(obj instanceof ArZ)) {
            return false;
        }
        Ixs ixs = (Ixs) obj;
        return this.BIo.equals(ixs.BIo) && this.zQM.equals(ixs.zQM) && this.zyO.equals(ixs.zyO) && this.jiA.equals(ixs.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("UnscheduleVisualTaskEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", alexaVisualTask=");
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
