package com.amazon.alexa.presence.identity;

import com.amazon.alexa.presence.Presence;
/* loaded from: classes9.dex */
public class PresenceAccessTokenProvider implements AccessTokenProvider {
    private static final String TAG = Presence.tag();

    @Override // com.amazon.alexa.presence.identity.AccessTokenProvider
    public String getAccessToken() {
        return getAuthTokenFromMap();
    }

    String getAuthTokenFromMap() {
        return IdentityHelper.getAuthTokenDirectlyFromMap();
    }
}
