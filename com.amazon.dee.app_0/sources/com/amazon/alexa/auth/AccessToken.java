package com.amazon.alexa.auth;

import java.util.Objects;
/* loaded from: classes6.dex */
public class AccessToken {
    private final String token;

    private AccessToken(String str) {
        this.token = str;
    }

    public static AccessToken create(String str) {
        return new AccessToken(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AccessToken.class == obj.getClass()) {
            return Objects.equals(this.token, ((AccessToken) obj).token);
        }
        return false;
    }

    public String getValue() {
        return this.token;
    }

    public int hashCode() {
        return Objects.hash(this.token);
    }
}
