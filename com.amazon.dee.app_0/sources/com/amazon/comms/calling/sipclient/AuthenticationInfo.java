package com.amazon.comms.calling.sipclient;

import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes11.dex */
public final class AuthenticationInfo {
    @Nullable
    private final String authToken;
    @Nonnull
    private final String password;
    @Nonnull
    private final String realm;
    @Nonnull
    private final String user;

    /* loaded from: classes11.dex */
    public static class AuthenticationInfoBuilder {
        private String authToken;
        private String password;
        private String realm;
        private String user;

        AuthenticationInfoBuilder() {
        }

        public AuthenticationInfoBuilder authToken(String str) {
            this.authToken = str;
            return this;
        }

        public AuthenticationInfo build() {
            return new AuthenticationInfo(this.realm, this.user, this.password, this.authToken);
        }

        public AuthenticationInfoBuilder password(String str) {
            this.password = str;
            return this;
        }

        public AuthenticationInfoBuilder realm(String str) {
            this.realm = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AuthenticationInfo.AuthenticationInfoBuilder(realm=");
            outline107.append(this.realm);
            outline107.append(", user=");
            outline107.append(this.user);
            outline107.append(", password=");
            outline107.append(this.password);
            outline107.append(", authToken=");
            return GeneratedOutlineSupport1.outline91(outline107, this.authToken, ")");
        }

        public AuthenticationInfoBuilder user(String str) {
            this.user = str;
            return this;
        }
    }

    AuthenticationInfo(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nullable String str4) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("user is null");
            }
            if (str3 == null) {
                throw new IllegalArgumentException("password is null");
            }
            this.realm = str;
            this.user = str2;
            this.password = str3;
            this.authToken = str4;
            return;
        }
        throw new IllegalArgumentException("realm is null");
    }

    public static AuthenticationInfoBuilder builder() {
        return new AuthenticationInfoBuilder();
    }

    @Nullable
    public String getAuthToken() {
        return this.authToken;
    }

    @Nonnull
    public String getPassword() {
        return this.password;
    }

    @Nonnull
    public String getRealm() {
        return this.realm;
    }

    @Nonnull
    public String getUser() {
        return this.user;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AuthenticationInfo(realm=");
        outline107.append(getRealm());
        outline107.append(", user=");
        outline107.append(getUser());
        outline107.append(")");
        return outline107.toString();
    }
}
