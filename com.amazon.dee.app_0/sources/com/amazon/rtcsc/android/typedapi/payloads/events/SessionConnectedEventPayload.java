package com.amazon.rtcsc.android.typedapi.payloads.events;

import com.amazon.rtcsc.android.typedapi.payloads.Payload;
/* loaded from: classes13.dex */
public class SessionConnectedEventPayload extends Payload {
    public SessionConnectedEventPayload(String str, String str2) {
        this.sessionId = str;
        this.sessionDomain = str2;
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    protected boolean canEqual(Object obj) {
        return obj instanceof SessionConnectedEventPayload;
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SessionConnectedEventPayload) && ((SessionConnectedEventPayload) obj).canEqual(this) && super.equals(obj);
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    public int hashCode() {
        return 59 + super.hashCode();
    }
}
