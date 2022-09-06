package com.amazon.rtcsc.android.typedapi.payloads.events;

import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import com.amazon.rtcsc.android.typedapi.types.Error;
/* loaded from: classes13.dex */
public class SessionDisconnectedEventPayload extends Payload {
    private Error error;

    public SessionDisconnectedEventPayload(String str, String str2, Error error) {
        this.sessionId = str;
        this.sessionDomain = str2;
        this.error = error;
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    protected boolean canEqual(Object obj) {
        return obj instanceof SessionDisconnectedEventPayload;
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SessionDisconnectedEventPayload)) {
            return false;
        }
        SessionDisconnectedEventPayload sessionDisconnectedEventPayload = (SessionDisconnectedEventPayload) obj;
        if (!sessionDisconnectedEventPayload.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Error error = getError();
        Error error2 = sessionDisconnectedEventPayload.getError();
        return error != null ? error.equals(error2) : error2 == null;
    }

    public Error getError() {
        return this.error;
    }

    @Override // com.amazon.rtcsc.android.typedapi.payloads.Payload
    public int hashCode() {
        Error error = getError();
        return ((super.hashCode() + 59) * 59) + (error == null ? 43 : error.hashCode());
    }
}
