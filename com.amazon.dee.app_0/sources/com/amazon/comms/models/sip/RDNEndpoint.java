package com.amazon.comms.models.sip;

import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = RDNEndpointBuilder.class)
@RedactInLogs
/* loaded from: classes11.dex */
public final class RDNEndpoint {
    private final ICEConfiguration calleeEndpoint;
    private final ICEConfiguration callerEndpoint;

    @JsonPOJOBuilder(withPrefix = "")
    /* loaded from: classes11.dex */
    public static class RDNEndpointBuilder {
        private ICEConfiguration calleeEndpoint;
        private ICEConfiguration callerEndpoint;

        RDNEndpointBuilder() {
        }

        public RDNEndpoint build() {
            return new RDNEndpoint(this.calleeEndpoint, this.callerEndpoint);
        }

        public RDNEndpointBuilder calleeEndpoint(ICEConfiguration iCEConfiguration) {
            this.calleeEndpoint = iCEConfiguration;
            return this;
        }

        public RDNEndpointBuilder callerEndpoint(ICEConfiguration iCEConfiguration) {
            this.callerEndpoint = iCEConfiguration;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RDNEndpoint.RDNEndpointBuilder(calleeEndpoint=");
            outline107.append(this.calleeEndpoint);
            outline107.append(", callerEndpoint=");
            outline107.append(this.callerEndpoint);
            outline107.append(")");
            return outline107.toString();
        }
    }

    RDNEndpoint(ICEConfiguration iCEConfiguration, ICEConfiguration iCEConfiguration2) {
        this.calleeEndpoint = iCEConfiguration;
        this.callerEndpoint = iCEConfiguration2;
    }

    public static RDNEndpointBuilder builder() {
        return new RDNEndpointBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RDNEndpoint)) {
            return false;
        }
        RDNEndpoint rDNEndpoint = (RDNEndpoint) obj;
        ICEConfiguration calleeEndpoint = getCalleeEndpoint();
        ICEConfiguration calleeEndpoint2 = rDNEndpoint.getCalleeEndpoint();
        if (calleeEndpoint != null ? !calleeEndpoint.equals(calleeEndpoint2) : calleeEndpoint2 != null) {
            return false;
        }
        ICEConfiguration callerEndpoint = getCallerEndpoint();
        ICEConfiguration callerEndpoint2 = rDNEndpoint.getCallerEndpoint();
        return callerEndpoint != null ? callerEndpoint.equals(callerEndpoint2) : callerEndpoint2 == null;
    }

    public ICEConfiguration getCalleeEndpoint() {
        return this.calleeEndpoint;
    }

    public ICEConfiguration getCallerEndpoint() {
        return this.callerEndpoint;
    }

    public int hashCode() {
        ICEConfiguration calleeEndpoint = getCalleeEndpoint();
        int i = 43;
        int hashCode = calleeEndpoint == null ? 43 : calleeEndpoint.hashCode();
        ICEConfiguration callerEndpoint = getCallerEndpoint();
        int i2 = (hashCode + 59) * 59;
        if (callerEndpoint != null) {
            i = callerEndpoint.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RDNEndpoint(calleeEndpoint=");
        outline107.append(getCalleeEndpoint());
        outline107.append(", callerEndpoint=");
        outline107.append(getCallerEndpoint());
        outline107.append(")");
        return outline107.toString();
    }
}
