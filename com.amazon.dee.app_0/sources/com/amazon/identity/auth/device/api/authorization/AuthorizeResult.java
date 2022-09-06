package com.amazon.identity.auth.device.api.authorization;

import android.os.Bundle;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
/* loaded from: classes12.dex */
public final class AuthorizeResult {
    private String accessToken;
    private String authorizationCode;
    private String clientId;
    private String redirectURI;
    private User user;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthorizeResult(Bundle bundle) {
        this(bundle, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthorizeResult.class != obj.getClass()) {
            return false;
        }
        AuthorizeResult authorizeResult = (AuthorizeResult) obj;
        String str = this.accessToken;
        if (str == null) {
            if (authorizeResult.accessToken != null) {
                return false;
            }
        } else if (!str.equals(authorizeResult.accessToken)) {
            return false;
        }
        String str2 = this.authorizationCode;
        if (str2 == null) {
            if (authorizeResult.authorizationCode != null) {
                return false;
            }
        } else if (!str2.equals(authorizeResult.authorizationCode)) {
            return false;
        }
        User user = this.user;
        if (user == null) {
            if (authorizeResult.user != null) {
                return false;
            }
        } else if (!user.equals(authorizeResult.user)) {
            return false;
        }
        String str3 = this.clientId;
        if (str3 == null) {
            if (authorizeResult.clientId != null) {
                return false;
            }
        } else if (!str3.equals(authorizeResult.clientId)) {
            return false;
        }
        String str4 = this.redirectURI;
        if (str4 == null) {
            if (authorizeResult.redirectURI != null) {
                return false;
            }
        } else if (!str4.equals(authorizeResult.redirectURI)) {
            return false;
        }
        return true;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAuthorizationCode() {
        return this.authorizationCode;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getRedirectURI() {
        return this.redirectURI;
    }

    public User getUser() {
        return this.user;
    }

    public int hashCode() {
        String str = this.accessToken;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.authorizationCode;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        User user = this.user;
        int hashCode3 = (hashCode2 + (user == null ? 0 : user.hashCode())) * 31;
        String str3 = this.clientId;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.redirectURI;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode4 + i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthorizeResult(Bundle bundle, User user) {
        this.accessToken = bundle.getString(AuthzConstants.BUNDLE_KEY.TOKEN.val);
        this.authorizationCode = bundle.getString(AuthzConstants.BUNDLE_KEY.AUTHORIZATION_CODE.val);
        this.clientId = bundle.getString(AuthzConstants.BUNDLE_KEY.CLIENT_ID.val);
        this.redirectURI = bundle.getString(AuthzConstants.BUNDLE_KEY.REDIRECT_URI.val);
        this.user = user;
    }
}
