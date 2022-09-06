package com.amazon.alexa;

import com.amazon.alexa.api.AlexaCapabilityAgentRegistration;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_ExternalCapabilityRegistration_DisableExternalCapabilityAgentEvent.java */
/* loaded from: classes.dex */
public final class dVL extends AbstractC0242ykQ {
    public final eOP BIo;
    public final AlexaCapabilityAgentRegistration jiA;
    public final ExtendedClient zQM;
    public final ApiCallback zyO;

    public dVL(eOP eop, ExtendedClient extendedClient, ApiCallback apiCallback, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
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
        if (!(obj instanceof AbstractC0242ykQ)) {
            return false;
        }
        dVL dvl = (dVL) obj;
        return this.BIo.equals(dvl.BIo) && this.zQM.equals(dvl.zQM) && this.zyO.equals(dvl.zyO) && this.jiA.equals(dvl.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DisableExternalCapabilityAgentEvent{apiCallMetadata=");
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
