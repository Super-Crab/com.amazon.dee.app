package com.amazon.comms.models.sip.payloads;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class ConfigureCommsStopSIPPayload implements SIPPayload {
    private String callId;
    private String reason;

    /* loaded from: classes11.dex */
    public static class ConfigureCommsStopSIPPayloadBuilder {
        private String callId;
        private String reason;

        ConfigureCommsStopSIPPayloadBuilder() {
        }

        public ConfigureCommsStopSIPPayload build() {
            return new ConfigureCommsStopSIPPayload(this.callId, this.reason);
        }

        public ConfigureCommsStopSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public ConfigureCommsStopSIPPayloadBuilder reason(String str) {
            this.reason = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ConfigureCommsStopSIPPayload.ConfigureCommsStopSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", reason=");
            return GeneratedOutlineSupport1.outline91(outline107, this.reason, ")");
        }
    }

    public ConfigureCommsStopSIPPayload() {
    }

    public static ConfigureCommsStopSIPPayloadBuilder builder() {
        return new ConfigureCommsStopSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ConfigureCommsStopSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConfigureCommsStopSIPPayload)) {
            return false;
        }
        ConfigureCommsStopSIPPayload configureCommsStopSIPPayload = (ConfigureCommsStopSIPPayload) obj;
        if (!configureCommsStopSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = configureCommsStopSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        String reason = getReason();
        String reason2 = configureCommsStopSIPPayload.getReason();
        return reason != null ? reason.equals(reason2) : reason2 == null;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public String getReason() {
        return this.reason;
    }

    public int hashCode() {
        String callId = getCallId();
        int i = 43;
        int hashCode = callId == null ? 43 : callId.hashCode();
        String reason = getReason();
        int i2 = (hashCode + 59) * 59;
        if (reason != null) {
            i = reason.hashCode();
        }
        return i2 + i;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ConfigureCommsStopSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", reason=");
        outline107.append(getReason());
        outline107.append(")");
        return outline107.toString();
    }

    private ConfigureCommsStopSIPPayload(String str, String str2) {
        this.callId = str;
        this.reason = str2;
    }
}
