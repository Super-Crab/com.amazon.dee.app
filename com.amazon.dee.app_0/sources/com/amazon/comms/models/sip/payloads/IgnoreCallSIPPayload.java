package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.sip.UtteranceInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class IgnoreCallSIPPayload implements SIPPayload {
    private String callId;
    private UtteranceInfo utteranceInfo;

    /* loaded from: classes11.dex */
    public static class IgnoreCallSIPPayloadBuilder {
        private String callId;
        private UtteranceInfo utteranceInfo;

        IgnoreCallSIPPayloadBuilder() {
        }

        public IgnoreCallSIPPayload build() {
            return new IgnoreCallSIPPayload(this.callId, this.utteranceInfo);
        }

        public IgnoreCallSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IgnoreCallSIPPayload.IgnoreCallSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", utteranceInfo=");
            outline107.append(this.utteranceInfo);
            outline107.append(")");
            return outline107.toString();
        }

        public IgnoreCallSIPPayloadBuilder utteranceInfo(UtteranceInfo utteranceInfo) {
            this.utteranceInfo = utteranceInfo;
            return this;
        }
    }

    public IgnoreCallSIPPayload() {
    }

    public static IgnoreCallSIPPayloadBuilder builder() {
        return new IgnoreCallSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof IgnoreCallSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IgnoreCallSIPPayload)) {
            return false;
        }
        IgnoreCallSIPPayload ignoreCallSIPPayload = (IgnoreCallSIPPayload) obj;
        if (!ignoreCallSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = ignoreCallSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        UtteranceInfo utteranceInfo = getUtteranceInfo();
        UtteranceInfo utteranceInfo2 = ignoreCallSIPPayload.getUtteranceInfo();
        return utteranceInfo != null ? utteranceInfo.equals(utteranceInfo2) : utteranceInfo2 == null;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public UtteranceInfo getUtteranceInfo() {
        return this.utteranceInfo;
    }

    public int hashCode() {
        String callId = getCallId();
        int i = 43;
        int hashCode = callId == null ? 43 : callId.hashCode();
        UtteranceInfo utteranceInfo = getUtteranceInfo();
        int i2 = (hashCode + 59) * 59;
        if (utteranceInfo != null) {
            i = utteranceInfo.hashCode();
        }
        return i2 + i;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setUtteranceInfo(UtteranceInfo utteranceInfo) {
        this.utteranceInfo = utteranceInfo;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IgnoreCallSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", utteranceInfo=");
        outline107.append(getUtteranceInfo());
        outline107.append(")");
        return outline107.toString();
    }

    private IgnoreCallSIPPayload(String str, UtteranceInfo utteranceInfo) {
        this.callId = str;
        this.utteranceInfo = utteranceInfo;
    }
}
