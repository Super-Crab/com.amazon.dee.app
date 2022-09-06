package com.amazon.alexa;

import com.amazon.alexa.api.AlexaCapabilityAgentRegistration;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_ExternalCapabilityRegistration_EnableExternalCapabilityAgentEvent.java */
/* loaded from: classes.dex */
public final class tsJ extends rKi {
    public final eOP BIo;
    public final AlexaCapabilityAgentRegistration jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public tsJ(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
        if (eop != null) {
            this.BIo = eop;
            if (extendedClient != null) {
                this.zQM = extendedClient;
                if (apiCallback != null) {
                    this.zyO = apiCallback;
                    if (alexaCapabilityAgentRegistration != null) {
                        this.jiA = alexaCapabilityAgentRegistration;
                        return;
                    }
                    throw new NullPointerException("Null capabilityAgentsRegistration");
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
        if (!(obj instanceof rKi)) {
            return false;
        }
        tsJ tsj = (tsJ) obj;
        return this.BIo.equals(tsj.BIo) && this.zQM.equals(tsj.zQM) && this.zyO.equals(tsj.zyO) && this.jiA.equals(tsj.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("EnableExternalCapabilityAgentEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", client=");
        zZm.append(this.zQM);
        zZm.append(", apiCallback=");
        zZm.append(this.zyO);
        zZm.append(", capabilityAgentsRegistration=");
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
