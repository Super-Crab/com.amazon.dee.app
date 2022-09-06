package com.amazon.comms.models.sip.payloads;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class SetRingToneSIPPayload implements SIPPayload {
    private String callId;
    private String ringToneUrl;

    /* loaded from: classes11.dex */
    public static class SetRingToneSIPPayloadBuilder {
        private String callId;
        private String ringToneUrl;

        SetRingToneSIPPayloadBuilder() {
        }

        public SetRingToneSIPPayload build() {
            return new SetRingToneSIPPayload(this.callId, this.ringToneUrl);
        }

        public SetRingToneSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public SetRingToneSIPPayloadBuilder ringToneUrl(String str) {
            this.ringToneUrl = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetRingToneSIPPayload.SetRingToneSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", ringToneUrl=");
            return GeneratedOutlineSupport1.outline91(outline107, this.ringToneUrl, ")");
        }
    }

    public SetRingToneSIPPayload() {
    }

    public static SetRingToneSIPPayloadBuilder builder() {
        return new SetRingToneSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SetRingToneSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetRingToneSIPPayload)) {
            return false;
        }
        SetRingToneSIPPayload setRingToneSIPPayload = (SetRingToneSIPPayload) obj;
        if (!setRingToneSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = setRingToneSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        String ringToneUrl = getRingToneUrl();
        String ringToneUrl2 = setRingToneSIPPayload.getRingToneUrl();
        return ringToneUrl != null ? ringToneUrl.equals(ringToneUrl2) : ringToneUrl2 == null;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public String getRingToneUrl() {
        return this.ringToneUrl;
    }

    public int hashCode() {
        String callId = getCallId();
        int i = 43;
        int hashCode = callId == null ? 43 : callId.hashCode();
        String ringToneUrl = getRingToneUrl();
        int i2 = (hashCode + 59) * 59;
        if (ringToneUrl != null) {
            i = ringToneUrl.hashCode();
        }
        return i2 + i;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setRingToneUrl(String str) {
        this.ringToneUrl = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetRingToneSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", ringToneUrl=");
        outline107.append(getRingToneUrl());
        outline107.append(")");
        return outline107.toString();
    }

    private SetRingToneSIPPayload(String str, String str2) {
        this.callId = str;
        this.ringToneUrl = str2;
    }
}
