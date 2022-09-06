package com.amazon.comms.models.sip;

import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@RedactInLogs
/* loaded from: classes11.dex */
public class SIPAuthenticationInfo {
    private String authToken;
    private String password;
    private String realm;
    private String user;

    /* loaded from: classes11.dex */
    public static class SIPAuthenticationInfoBuilder {
        private String authToken;
        private String password;
        private String realm;
        private String user;

        SIPAuthenticationInfoBuilder() {
        }

        public SIPAuthenticationInfoBuilder authToken(String str) {
            this.authToken = str;
            return this;
        }

        public SIPAuthenticationInfo build() {
            return new SIPAuthenticationInfo(this.realm, this.user, this.password, this.authToken);
        }

        public SIPAuthenticationInfoBuilder password(String str) {
            this.password = str;
            return this;
        }

        public SIPAuthenticationInfoBuilder realm(String str) {
            this.realm = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SIPAuthenticationInfo.SIPAuthenticationInfoBuilder(realm=");
            outline107.append(this.realm);
            outline107.append(", user=");
            outline107.append(this.user);
            outline107.append(", password=");
            outline107.append(this.password);
            outline107.append(", authToken=");
            return GeneratedOutlineSupport1.outline91(outline107, this.authToken, ")");
        }

        public SIPAuthenticationInfoBuilder user(String str) {
            this.user = str;
            return this;
        }
    }

    public SIPAuthenticationInfo() {
    }

    public static SIPAuthenticationInfoBuilder builder() {
        return new SIPAuthenticationInfoBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SIPAuthenticationInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SIPAuthenticationInfo)) {
            return false;
        }
        SIPAuthenticationInfo sIPAuthenticationInfo = (SIPAuthenticationInfo) obj;
        if (!sIPAuthenticationInfo.canEqual(this)) {
            return false;
        }
        String realm = getRealm();
        String realm2 = sIPAuthenticationInfo.getRealm();
        if (realm != null ? !realm.equals(realm2) : realm2 != null) {
            return false;
        }
        String user = getUser();
        String user2 = sIPAuthenticationInfo.getUser();
        if (user != null ? !user.equals(user2) : user2 != null) {
            return false;
        }
        String password = getPassword();
        String password2 = sIPAuthenticationInfo.getPassword();
        if (password != null ? !password.equals(password2) : password2 != null) {
            return false;
        }
        String authToken = getAuthToken();
        String authToken2 = sIPAuthenticationInfo.getAuthToken();
        return authToken != null ? authToken.equals(authToken2) : authToken2 == null;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRealm() {
        return this.realm;
    }

    public String getUser() {
        return this.user;
    }

    public int hashCode() {
        String realm = getRealm();
        int i = 43;
        int hashCode = realm == null ? 43 : realm.hashCode();
        String user = getUser();
        int hashCode2 = ((hashCode + 59) * 59) + (user == null ? 43 : user.hashCode());
        String password = getPassword();
        int hashCode3 = (hashCode2 * 59) + (password == null ? 43 : password.hashCode());
        String authToken = getAuthToken();
        int i2 = hashCode3 * 59;
        if (authToken != null) {
            i = authToken.hashCode();
        }
        return i2 + i;
    }

    public void setAuthToken(String str) {
        this.authToken = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setRealm(String str) {
        this.realm = str;
    }

    public void setUser(String str) {
        this.user = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SIPAuthenticationInfo(realm=");
        outline107.append(getRealm());
        outline107.append(", user=");
        outline107.append(getUser());
        outline107.append(")");
        return outline107.toString();
    }

    private SIPAuthenticationInfo(String str, String str2, String str3, String str4) {
        this.realm = str;
        this.user = str2;
        this.password = str3;
        this.authToken = str4;
    }
}
