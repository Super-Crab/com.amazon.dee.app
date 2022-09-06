package com.amazon.identity.auth.device.api;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum RegistrationType {
    WITH_LOGIN_CREDENTIALS("LoginCredentials"),
    WITH_DEVICE_SECRET("DeviceSecret"),
    WITH_EXPLICIT_URL("ExplicitUrl"),
    FROM_ATMAIN("ATMain"),
    FROM_AUTH_TOKEN("AuthToken"),
    FROM_ACCESS_TOKEN("AccessToken"),
    FROM_ADP_TOKEN("AdpToken"),
    REGISTER_DELEGATED_ACCOUNT("RegisterDelegatedAccount"),
    WITH_DIRECTEDID_CREDENTIALS("DirectedIdCredentials"),
    WITH_PRIMARY_DIRECTEDID_CREDENTIALS("PrimaryDirectedIdCredentials"),
    WITH_LINK_CODE("LinkCode"),
    WITH_SSO_CODE("SSOCode"),
    ANONYMOUS("Anonymous"),
    FROM_AUTHORIZATION_CODE(DatabaseHelper.authorizationCodeTable);
    
    private final String mName;

    RegistrationType(String str) {
        this.mName = str;
    }

    public static RegistrationType fromName(String str) {
        RegistrationType[] values;
        for (RegistrationType registrationType : values()) {
            if (registrationType.getName().equals(str)) {
                return registrationType;
            }
        }
        return null;
    }

    public String getName() {
        return this.mName;
    }
}
