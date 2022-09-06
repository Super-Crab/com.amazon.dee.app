package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.sip.UtteranceInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class SendDtmfSIPPayload implements SIPPayload {
    private String callId;
    private Integer durationInMillis;
    private String signal;
    private UtteranceInfo utteranceInfo;

    /* loaded from: classes11.dex */
    public static class SendDtmfSIPPayloadBuilder {
        private String callId;
        private Integer durationInMillis;
        private String signal;
        private UtteranceInfo utteranceInfo;

        SendDtmfSIPPayloadBuilder() {
        }

        public SendDtmfSIPPayload build() {
            return new SendDtmfSIPPayload(this.callId, this.signal, this.durationInMillis, this.utteranceInfo);
        }

        public SendDtmfSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public SendDtmfSIPPayloadBuilder durationInMillis(Integer num) {
            this.durationInMillis = num;
            return this;
        }

        public SendDtmfSIPPayloadBuilder signal(String str) {
            this.signal = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SendDtmfSIPPayload.SendDtmfSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", signal=");
            outline107.append(this.signal);
            outline107.append(", durationInMillis=");
            outline107.append(this.durationInMillis);
            outline107.append(", utteranceInfo=");
            outline107.append(this.utteranceInfo);
            outline107.append(")");
            return outline107.toString();
        }

        public SendDtmfSIPPayloadBuilder utteranceInfo(UtteranceInfo utteranceInfo) {
            this.utteranceInfo = utteranceInfo;
            return this;
        }
    }

    public SendDtmfSIPPayload() {
    }

    public static SendDtmfSIPPayloadBuilder builder() {
        return new SendDtmfSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SendDtmfSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SendDtmfSIPPayload)) {
            return false;
        }
        SendDtmfSIPPayload sendDtmfSIPPayload = (SendDtmfSIPPayload) obj;
        if (!sendDtmfSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = sendDtmfSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        String signal = getSignal();
        String signal2 = sendDtmfSIPPayload.getSignal();
        if (signal != null ? !signal.equals(signal2) : signal2 != null) {
            return false;
        }
        Integer durationInMillis = getDurationInMillis();
        Integer durationInMillis2 = sendDtmfSIPPayload.getDurationInMillis();
        if (durationInMillis != null ? !durationInMillis.equals(durationInMillis2) : durationInMillis2 != null) {
            return false;
        }
        UtteranceInfo utteranceInfo = getUtteranceInfo();
        UtteranceInfo utteranceInfo2 = sendDtmfSIPPayload.getUtteranceInfo();
        return utteranceInfo != null ? utteranceInfo.equals(utteranceInfo2) : utteranceInfo2 == null;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public Integer getDurationInMillis() {
        return this.durationInMillis;
    }

    public String getSignal() {
        return this.signal;
    }

    public UtteranceInfo getUtteranceInfo() {
        return this.utteranceInfo;
    }

    public int hashCode() {
        String callId = getCallId();
        int i = 43;
        int hashCode = callId == null ? 43 : callId.hashCode();
        String signal = getSignal();
        int hashCode2 = ((hashCode + 59) * 59) + (signal == null ? 43 : signal.hashCode());
        Integer durationInMillis = getDurationInMillis();
        int hashCode3 = (hashCode2 * 59) + (durationInMillis == null ? 43 : durationInMillis.hashCode());
        UtteranceInfo utteranceInfo = getUtteranceInfo();
        int i2 = hashCode3 * 59;
        if (utteranceInfo != null) {
            i = utteranceInfo.hashCode();
        }
        return i2 + i;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setDurationInMillis(Integer num) {
        this.durationInMillis = num;
    }

    public void setSignal(String str) {
        this.signal = str;
    }

    public void setUtteranceInfo(UtteranceInfo utteranceInfo) {
        this.utteranceInfo = utteranceInfo;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SendDtmfSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", signal=");
        outline107.append(getSignal());
        outline107.append(", durationInMillis=");
        outline107.append(getDurationInMillis());
        outline107.append(", utteranceInfo=");
        outline107.append(getUtteranceInfo());
        outline107.append(")");
        return outline107.toString();
    }

    private SendDtmfSIPPayload(String str, String str2, Integer num, UtteranceInfo utteranceInfo) {
        this.callId = str;
        this.signal = str2;
        this.durationInMillis = num;
        this.utteranceInfo = utteranceInfo;
    }
}
