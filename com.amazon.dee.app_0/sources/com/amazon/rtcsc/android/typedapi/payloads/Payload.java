package com.amazon.rtcsc.android.typedapi.payloads;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/* loaded from: classes13.dex */
public abstract class Payload {
    protected String sessionDomain;
    protected String sessionId;
    private transient GsonBuilder gsonBuilder = new GsonBuilder();
    private transient Gson gson = this.gsonBuilder.create();

    protected boolean canEqual(Object obj) {
        return obj instanceof Payload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Payload)) {
            return false;
        }
        Payload payload = (Payload) obj;
        if (!payload.canEqual(this)) {
            return false;
        }
        String str = this.sessionId;
        String str2 = payload.sessionId;
        if (str != null ? !str.equals(str2) : str2 != null) {
            return false;
        }
        String str3 = this.sessionDomain;
        String str4 = payload.sessionDomain;
        return str3 != null ? str3.equals(str4) : str4 == null;
    }

    public String getSerializedPayload() {
        return this.gson.toJson(this);
    }

    public int hashCode() {
        String str = this.sessionId;
        int i = 43;
        int hashCode = str == null ? 43 : str.hashCode();
        String str2 = this.sessionDomain;
        int i2 = (hashCode + 59) * 59;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return i2 + i;
    }
}
