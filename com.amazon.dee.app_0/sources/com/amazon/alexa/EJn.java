package com.amazon.alexa;

import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: AutoValue_ApiCallEvent_WakeWord_SetWakeWordsEvent.java */
/* loaded from: classes.dex */
public final class EJn extends RCa {
    public final eOP BIo;
    public final List<String> jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public EJn(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, List<String> list) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (list != null) {
                        this.jiA = list;
                        return;
                    }
                    throw new NullPointerException("Null wakeWords");
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
        if (!(obj instanceof RCa)) {
            return false;
        }
        EJn eJn = (EJn) obj;
        return this.BIo.equals(eJn.BIo) && this.zQM.equals(eJn.zQM) && this.zyO.equals(eJn.zyO) && this.jiA.equals(eJn.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SetWakeWordsEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", wakeWords=");
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
