package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class RegistrationDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.RegistrationDetails");
    private long expiresAt;
    private String registrationToken;

    public boolean equals(Object obj) {
        if (!(obj instanceof RegistrationDetails)) {
            return false;
        }
        RegistrationDetails registrationDetails = (RegistrationDetails) obj;
        return Helper.equals(this.registrationToken, registrationDetails.registrationToken) && Helper.equals(Long.valueOf(this.expiresAt), Long.valueOf(registrationDetails.expiresAt));
    }

    public long getExpiresAt() {
        return this.expiresAt;
    }

    public String getRegistrationToken() {
        return this.registrationToken;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.registrationToken, Long.valueOf(this.expiresAt));
    }

    public void setExpiresAt(long j) {
        this.expiresAt = j;
    }

    public void setRegistrationToken(String str) {
        this.registrationToken = str;
    }
}
