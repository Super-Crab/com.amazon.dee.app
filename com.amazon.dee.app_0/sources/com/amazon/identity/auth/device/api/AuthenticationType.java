package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import io.ktor.http.auth.AuthScheme;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum AuthenticationType {
    None("None", false),
    DeviceAuthenticator("DeviceAuthenticator", false),
    ADPAuthenticator("ADPAuthenticator", false),
    OAuth(AuthScheme.OAuth, true);
    
    private final boolean mRequireHttps;
    private final String mValue;

    AuthenticationType(String str, boolean z) {
        this.mValue = str;
        this.mRequireHttps = z;
    }

    @FireOsSdk
    public static AuthenticationType parse(String str) {
        AuthenticationType[] values;
        if (str == null) {
            return null;
        }
        for (AuthenticationType authenticationType : values()) {
            if (str.equals(authenticationType.getValue())) {
                return authenticationType;
            }
        }
        return null;
    }

    @FireOsSdk
    public String getValue() {
        return this.mValue;
    }

    @Deprecated
    public boolean requireHttps() {
        return this.mRequireHttps;
    }
}
