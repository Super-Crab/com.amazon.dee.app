package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.sip.UtteranceInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class EarlyAcceptCallSIPPayload implements SIPPayload {
    private String callId;
    private UtteranceInfo utteranceInfo;

    /* loaded from: classes11.dex */
    public static class EarlyAcceptCallSIPPayloadBuilder {
        private String callId;
        private UtteranceInfo utteranceInfo;

        EarlyAcceptCallSIPPayloadBuilder() {
        }

        public EarlyAcceptCallSIPPayload build() {
            return new EarlyAcceptCallSIPPayload(this.callId, this.utteranceInfo);
        }

        public EarlyAcceptCallSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EarlyAcceptCallSIPPayload.EarlyAcceptCallSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", utteranceInfo=");
            outline107.append(this.utteranceInfo);
            outline107.append(")");
            return outline107.toString();
        }

        public EarlyAcceptCallSIPPayloadBuilder utteranceInfo(UtteranceInfo utteranceInfo) {
            this.utteranceInfo = utteranceInfo;
            return this;
        }
    }

    public EarlyAcceptCallSIPPayload() {
    }

    public static EarlyAcceptCallSIPPayloadBuilder builder() {
        return new EarlyAcceptCallSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof EarlyAcceptCallSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EarlyAcceptCallSIPPayload)) {
            return false;
        }
        EarlyAcceptCallSIPPayload earlyAcceptCallSIPPayload = (EarlyAcceptCallSIPPayload) obj;
        if (!earlyAcceptCallSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = earlyAcceptCallSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        UtteranceInfo utteranceInfo = getUtteranceInfo();
        UtteranceInfo utteranceInfo2 = earlyAcceptCallSIPPayload.getUtteranceInfo();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EarlyAcceptCallSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", utteranceInfo=");
        outline107.append(getUtteranceInfo());
        outline107.append(")");
        return outline107.toString();
    }

    private EarlyAcceptCallSIPPayload(String str, UtteranceInfo utteranceInfo) {
        this.callId = str;
        this.utteranceInfo = utteranceInfo;
    }
}
