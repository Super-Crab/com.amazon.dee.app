package com.amazon.comms.models.sip.payloads;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class WarmupSIPPayload implements SIPPayload {
    private String callId;
    private int maxDurationMs;

    /* loaded from: classes11.dex */
    public static class WarmupSIPPayloadBuilder {
        private String callId;
        private int maxDurationMs;

        WarmupSIPPayloadBuilder() {
        }

        public WarmupSIPPayload build() {
            return new WarmupSIPPayload(this.callId, this.maxDurationMs);
        }

        public WarmupSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public WarmupSIPPayloadBuilder maxDurationMs(int i) {
            this.maxDurationMs = i;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WarmupSIPPayload.WarmupSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", maxDurationMs=");
            return GeneratedOutlineSupport1.outline86(outline107, this.maxDurationMs, ")");
        }
    }

    public WarmupSIPPayload() {
    }

    public static WarmupSIPPayloadBuilder builder() {
        return new WarmupSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WarmupSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WarmupSIPPayload)) {
            return false;
        }
        WarmupSIPPayload warmupSIPPayload = (WarmupSIPPayload) obj;
        if (!warmupSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = warmupSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        return getMaxDurationMs() == warmupSIPPayload.getMaxDurationMs();
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public int getMaxDurationMs() {
        return this.maxDurationMs;
    }

    public int hashCode() {
        String callId = getCallId();
        return getMaxDurationMs() + (((callId == null ? 43 : callId.hashCode()) + 59) * 59);
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setMaxDurationMs(int i) {
        this.maxDurationMs = i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WarmupSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", maxDurationMs=");
        outline107.append(getMaxDurationMs());
        outline107.append(")");
        return outline107.toString();
    }

    private WarmupSIPPayload(String str, int i) {
        this.callId = str;
        this.maxDurationMs = i;
    }
}
