package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.sip.RDNEndpoint;
import com.amazon.comms.models.sip.SIPAuthenticationInfo;
import com.amazon.comms.models.sip.SIPContact;
import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
@RedactInLogs
/* loaded from: classes11.dex */
public class ReconnectCallSIPPayload implements SIPPayload {
    private SIPAuthenticationInfo authenticationInfo;
    private String callId;
    private SIPContact callee;
    private SIPContact caller;
    private RDNEndpoint mediaRelayInfo;

    /* loaded from: classes11.dex */
    public static class ReconnectCallSIPPayloadBuilder {
        private SIPAuthenticationInfo authenticationInfo;
        private String callId;
        private SIPContact callee;
        private SIPContact caller;
        private RDNEndpoint mediaRelayInfo;

        ReconnectCallSIPPayloadBuilder() {
        }

        public ReconnectCallSIPPayloadBuilder authenticationInfo(SIPAuthenticationInfo sIPAuthenticationInfo) {
            this.authenticationInfo = sIPAuthenticationInfo;
            return this;
        }

        public ReconnectCallSIPPayload build() {
            return new ReconnectCallSIPPayload(this.callId, this.caller, this.callee, this.authenticationInfo, this.mediaRelayInfo);
        }

        public ReconnectCallSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public ReconnectCallSIPPayloadBuilder callee(SIPContact sIPContact) {
            this.callee = sIPContact;
            return this;
        }

        public ReconnectCallSIPPayloadBuilder caller(SIPContact sIPContact) {
            this.caller = sIPContact;
            return this;
        }

        public ReconnectCallSIPPayloadBuilder mediaRelayInfo(RDNEndpoint rDNEndpoint) {
            this.mediaRelayInfo = rDNEndpoint;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReconnectCallSIPPayload.ReconnectCallSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", caller=");
            outline107.append(this.caller);
            outline107.append(", callee=");
            outline107.append(this.callee);
            outline107.append(", authenticationInfo=");
            outline107.append(this.authenticationInfo);
            outline107.append(", mediaRelayInfo=");
            outline107.append(this.mediaRelayInfo);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public ReconnectCallSIPPayload() {
    }

    public static ReconnectCallSIPPayloadBuilder builder() {
        return new ReconnectCallSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ReconnectCallSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReconnectCallSIPPayload)) {
            return false;
        }
        ReconnectCallSIPPayload reconnectCallSIPPayload = (ReconnectCallSIPPayload) obj;
        if (!reconnectCallSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = reconnectCallSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        SIPContact caller = getCaller();
        SIPContact caller2 = reconnectCallSIPPayload.getCaller();
        if (caller != null ? !caller.equals(caller2) : caller2 != null) {
            return false;
        }
        SIPContact callee = getCallee();
        SIPContact callee2 = reconnectCallSIPPayload.getCallee();
        if (callee != null ? !callee.equals(callee2) : callee2 != null) {
            return false;
        }
        SIPAuthenticationInfo authenticationInfo = getAuthenticationInfo();
        SIPAuthenticationInfo authenticationInfo2 = reconnectCallSIPPayload.getAuthenticationInfo();
        if (authenticationInfo != null ? !authenticationInfo.equals(authenticationInfo2) : authenticationInfo2 != null) {
            return false;
        }
        RDNEndpoint mediaRelayInfo = getMediaRelayInfo();
        RDNEndpoint mediaRelayInfo2 = reconnectCallSIPPayload.getMediaRelayInfo();
        return mediaRelayInfo != null ? mediaRelayInfo.equals(mediaRelayInfo2) : mediaRelayInfo2 == null;
    }

    public SIPAuthenticationInfo getAuthenticationInfo() {
        return this.authenticationInfo;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public SIPContact getCallee() {
        return this.callee;
    }

    public SIPContact getCaller() {
        return this.caller;
    }

    public RDNEndpoint getMediaRelayInfo() {
        return this.mediaRelayInfo;
    }

    public int hashCode() {
        String callId = getCallId();
        int i = 43;
        int hashCode = callId == null ? 43 : callId.hashCode();
        SIPContact caller = getCaller();
        int hashCode2 = ((hashCode + 59) * 59) + (caller == null ? 43 : caller.hashCode());
        SIPContact callee = getCallee();
        int hashCode3 = (hashCode2 * 59) + (callee == null ? 43 : callee.hashCode());
        SIPAuthenticationInfo authenticationInfo = getAuthenticationInfo();
        int hashCode4 = (hashCode3 * 59) + (authenticationInfo == null ? 43 : authenticationInfo.hashCode());
        RDNEndpoint mediaRelayInfo = getMediaRelayInfo();
        int i2 = hashCode4 * 59;
        if (mediaRelayInfo != null) {
            i = mediaRelayInfo.hashCode();
        }
        return i2 + i;
    }

    public void setAuthenticationInfo(SIPAuthenticationInfo sIPAuthenticationInfo) {
        this.authenticationInfo = sIPAuthenticationInfo;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setCallee(SIPContact sIPContact) {
        this.callee = sIPContact;
    }

    public void setCaller(SIPContact sIPContact) {
        this.caller = sIPContact;
    }

    public void setMediaRelayInfo(RDNEndpoint rDNEndpoint) {
        this.mediaRelayInfo = rDNEndpoint;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReconnectCallSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", caller=");
        outline107.append(getCaller());
        outline107.append(", callee=");
        outline107.append(getCallee());
        outline107.append(", authenticationInfo=");
        outline107.append(getAuthenticationInfo());
        outline107.append(", mediaRelayInfo=");
        outline107.append(getMediaRelayInfo());
        outline107.append(")");
        return outline107.toString();
    }

    private ReconnectCallSIPPayload(String str, SIPContact sIPContact, SIPContact sIPContact2, SIPAuthenticationInfo sIPAuthenticationInfo, RDNEndpoint rDNEndpoint) {
        this.callId = str;
        this.caller = sIPContact;
        this.callee = sIPContact2;
        this.authenticationInfo = sIPAuthenticationInfo;
        this.mediaRelayInfo = rDNEndpoint;
    }
}
