package com.amazon.alexa.presence.service;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.presence.library.IdentityHelper;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
/* loaded from: classes9.dex */
public class MapAuthTokenProvider implements AuthTokenProvider {
    private static final String TAG = "com.amazon.alexa.presence.service.MapAuthTokenProvider";

    private String getMapToken() {
        try {
            IdentityService identityService = (IdentityService) ComponentRegistry.getInstance().get(IdentityService.class).orNull();
            UserIdentity user = identityService == null ? null : identityService.getUser(MapAuthTokenProvider.class.getName());
            String accessToken = user == null ? null : user.getAccessToken();
            if (!TextUtils.isEmpty(accessToken)) {
                return accessToken;
            }
            return null;
        } catch (Throwable th) {
            Log.w(TAG, "Error encountered getting map token", th);
            return null;
        }
    }

    private String tryGetAuthToken() {
        String mapToken = IdentityHelper.getMapToken();
        return TextUtils.isEmpty(mapToken) ? getMapToken() : mapToken;
    }

    @Override // com.amazon.alexa.presence.service.AuthTokenProvider
    public String getAuthToken() {
        String tryGetAuthToken = tryGetAuthToken();
        if (TextUtils.isEmpty(tryGetAuthToken)) {
            refreshAuthToken();
        }
        return TextUtils.isEmpty(tryGetAuthToken) ? tryGetAuthToken() : tryGetAuthToken;
    }

    @Override // com.amazon.alexa.presence.service.AuthTokenProvider
    public void refreshAuthToken() {
        IdentityHelper.requestRefreshAuthToken();
    }
}
