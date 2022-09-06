package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.sip.CallMediaState;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class SetCallMediaStateSIPPayload implements SIPPayload {
    private String callId;
    private CallMediaState callMediaState;

    /* loaded from: classes11.dex */
    public static class SetCallMediaStateSIPPayloadBuilder {
        private String callId;
        private CallMediaState callMediaState;

        SetCallMediaStateSIPPayloadBuilder() {
        }

        public SetCallMediaStateSIPPayload build() {
            return new SetCallMediaStateSIPPayload(this.callId, this.callMediaState);
        }

        public SetCallMediaStateSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public SetCallMediaStateSIPPayloadBuilder callMediaState(CallMediaState callMediaState) {
            this.callMediaState = callMediaState;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetCallMediaStateSIPPayload.SetCallMediaStateSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", callMediaState=");
            outline107.append(this.callMediaState);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public SetCallMediaStateSIPPayload() {
    }

    public static SetCallMediaStateSIPPayloadBuilder builder() {
        return new SetCallMediaStateSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SetCallMediaStateSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetCallMediaStateSIPPayload)) {
            return false;
        }
        SetCallMediaStateSIPPayload setCallMediaStateSIPPayload = (SetCallMediaStateSIPPayload) obj;
        if (!setCallMediaStateSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = setCallMediaStateSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        CallMediaState callMediaState = getCallMediaState();
        CallMediaState callMediaState2 = setCallMediaStateSIPPayload.getCallMediaState();
        return callMediaState != null ? callMediaState.equals(callMediaState2) : callMediaState2 == null;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public CallMediaState getCallMediaState() {
        return this.callMediaState;
    }

    public int hashCode() {
        String callId = getCallId();
        int i = 43;
        int hashCode = callId == null ? 43 : callId.hashCode();
        CallMediaState callMediaState = getCallMediaState();
        int i2 = (hashCode + 59) * 59;
        if (callMediaState != null) {
            i = callMediaState.hashCode();
        }
        return i2 + i;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setCallMediaState(CallMediaState callMediaState) {
        this.callMediaState = callMediaState;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetCallMediaStateSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", callMediaState=");
        outline107.append(getCallMediaState());
        outline107.append(")");
        return outline107.toString();
    }

    private SetCallMediaStateSIPPayload(String str, CallMediaState callMediaState) {
        this.callId = str;
        this.callMediaState = callMediaState;
    }
}
