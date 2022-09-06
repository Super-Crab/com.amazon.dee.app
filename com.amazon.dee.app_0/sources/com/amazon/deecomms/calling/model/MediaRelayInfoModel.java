package com.amazon.deecomms.calling.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class MediaRelayInfoModel {
    @JsonProperty("authToken")
    private String authToken;
    @JsonProperty("calleeSTUN")
    private EndpointConfigurationInfo calleeSTUN;
    @JsonProperty("calleeTURNTCP")
    private EndpointConfigurationInfo calleeTURNTCP;
    @JsonProperty("calleeTURNUDP")
    private EndpointConfigurationInfo calleeTURNUDP;
    @JsonProperty("callerSTUN")
    private EndpointConfigurationInfo callerSTUN;
    @JsonProperty("callerTURNTCP")
    private EndpointConfigurationInfo callerTURNTCP;
    @JsonProperty("callerTURNUDP")
    private EndpointConfigurationInfo callerTURNUDP;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaRelayInfoModel)) {
            return false;
        }
        MediaRelayInfoModel mediaRelayInfoModel = (MediaRelayInfoModel) obj;
        return Objects.equal(this.calleeTURNUDP, mediaRelayInfoModel.calleeTURNUDP) && Objects.equal(this.callerTURNUDP, mediaRelayInfoModel.callerTURNUDP) && Objects.equal(this.calleeTURNTCP, mediaRelayInfoModel.calleeTURNTCP) && Objects.equal(this.callerTURNTCP, mediaRelayInfoModel.callerTURNTCP) && Objects.equal(this.calleeSTUN, mediaRelayInfoModel.calleeSTUN) && Objects.equal(this.callerSTUN, mediaRelayInfoModel.callerSTUN) && Objects.equal(this.authToken, mediaRelayInfoModel.authToken);
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public EndpointConfigurationInfo getCalleeSTUN() {
        return this.calleeSTUN;
    }

    public EndpointConfigurationInfo getCalleeTURNTCP() {
        return this.calleeTURNTCP;
    }

    public EndpointConfigurationInfo getCalleeTURNUDP() {
        return this.calleeTURNUDP;
    }

    public EndpointConfigurationInfo getCallerSTUN() {
        return this.callerSTUN;
    }

    public EndpointConfigurationInfo getCallerTURNTCP() {
        return this.callerTURNTCP;
    }

    public EndpointConfigurationInfo getCallerTURNUDP() {
        return this.callerTURNUDP;
    }

    public int hashCode() {
        return Objects.hashCode(this.calleeTURNUDP, this.callerTURNUDP, this.calleeTURNTCP, this.callerTURNTCP, this.calleeSTUN, this.callerSTUN, this.authToken);
    }

    public void setCalleeSTUN(EndpointConfigurationInfo endpointConfigurationInfo) {
        this.calleeSTUN = endpointConfigurationInfo;
    }

    public void setCalleeTURNTCP(EndpointConfigurationInfo endpointConfigurationInfo) {
        this.calleeTURNTCP = endpointConfigurationInfo;
    }

    public void setCalleeTURNUDP(EndpointConfigurationInfo endpointConfigurationInfo) {
        this.calleeTURNUDP = endpointConfigurationInfo;
    }

    public void setCallerSTUN(EndpointConfigurationInfo endpointConfigurationInfo) {
        this.callerSTUN = endpointConfigurationInfo;
    }

    public void setCallerTURNTCP(EndpointConfigurationInfo endpointConfigurationInfo) {
        this.callerTURNTCP = endpointConfigurationInfo;
    }

    public void setCallerTURNUDP(EndpointConfigurationInfo endpointConfigurationInfo) {
        this.callerTURNUDP = endpointConfigurationInfo;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("calleeTURNUDP", this.calleeTURNUDP).add("callerTURNUDP", this.callerTURNUDP).add("calleeTURNTCP", this.calleeTURNTCP).add("callerTURNTCP", this.callerTURNTCP).add("calleeSTUN", this.calleeSTUN).add("callerSTUN", this.callerSTUN).add("authToken", this.authToken).toString();
    }
}
