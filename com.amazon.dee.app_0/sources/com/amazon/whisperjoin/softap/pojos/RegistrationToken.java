package com.amazon.whisperjoin.softap.pojos;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes13.dex */
public class RegistrationToken {
    @SerializedName("token")
    private String token;

    /* loaded from: classes13.dex */
    public static class RegistrationTokenBuilder {
        private String token;

        RegistrationTokenBuilder() {
        }

        public RegistrationToken build() {
            return new RegistrationToken(this.token);
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("RegistrationToken.RegistrationTokenBuilder(token="), this.token, ")");
        }

        public RegistrationTokenBuilder token(String str) {
            this.token = str;
            return this;
        }
    }

    RegistrationToken(String str) {
        this.token = str;
    }

    public static RegistrationTokenBuilder builder() {
        return new RegistrationTokenBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RegistrationToken;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RegistrationToken)) {
            return false;
        }
        RegistrationToken registrationToken = (RegistrationToken) obj;
        if (!registrationToken.canEqual(this)) {
            return false;
        }
        String token = getToken();
        String token2 = registrationToken.getToken();
        return token != null ? token.equals(token2) : token2 == null;
    }

    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        String token = getToken();
        return 59 + (token == null ? 43 : token.hashCode());
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RegistrationToken(token=");
        outline107.append(getToken());
        outline107.append(")");
        return outline107.toString();
    }
}
