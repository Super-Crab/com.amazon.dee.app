package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.sip.UtteranceInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class EndCallSIPPayload implements SIPPayload {
    private String callId;
    private UtteranceInfo utteranceInfo;

    /* loaded from: classes11.dex */
    public static class EndCallSIPPayloadBuilder {
        private String callId;
        private UtteranceInfo utteranceInfo;

        EndCallSIPPayloadBuilder() {
        }

        public EndCallSIPPayload build() {
            return new EndCallSIPPayload(this.callId, this.utteranceInfo);
        }

        public EndCallSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EndCallSIPPayload.EndCallSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", utteranceInfo=");
            outline107.append(this.utteranceInfo);
            outline107.append(")");
            return outline107.toString();
        }

        public EndCallSIPPayloadBuilder utteranceInfo(UtteranceInfo utteranceInfo) {
            this.utteranceInfo = utteranceInfo;
            return this;
        }
    }

    public EndCallSIPPayload() {
    }

    public static EndCallSIPPayloadBuilder builder() {
        return new EndCallSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof EndCallSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EndCallSIPPayload)) {
            return false;
        }
        EndCallSIPPayload endCallSIPPayload = (EndCallSIPPayload) obj;
        if (!endCallSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = endCallSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        UtteranceInfo utteranceInfo = getUtteranceInfo();
        UtteranceInfo utteranceInfo2 = endCallSIPPayload.getUtteranceInfo();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EndCallSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", utteranceInfo=");
        outline107.append(getUtteranceInfo());
        outline107.append(")");
        return outline107.toString();
    }

    private EndCallSIPPayload(String str, UtteranceInfo utteranceInfo) {
        this.callId = str;
        this.utteranceInfo = utteranceInfo;
    }
}
