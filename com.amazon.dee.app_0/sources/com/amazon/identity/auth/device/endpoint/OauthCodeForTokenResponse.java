package com.amazon.identity.auth.device.endpoint;

import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.token.RefreshAtzToken;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
/* loaded from: classes12.dex */
class OauthCodeForTokenResponse extends OauthTokenResponse {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.OauthCodeForTokenResponse";

    /* JADX INFO: Access modifiers changed from: package-private */
    public OauthCodeForTokenResponse(HttpResponse httpResponse, String str, String str2) {
        super(httpResponse, str, str2);
        String str3 = LOG_TAG;
        MAPLog.i(str3, "Creating OauthCodeForTokenResponse appId=" + str);
    }

    @Override // com.amazon.identity.auth.device.endpoint.OauthTokenResponse
    public RefreshAtzToken extractRefreshAtzToken(JSONObject jSONObject) throws AuthError {
        RefreshAtzToken extractRefreshAtzToken = super.extractRefreshAtzToken(jSONObject);
        if (extractRefreshAtzToken != null) {
            return extractRefreshAtzToken;
        }
        throw new AuthError("JSON response did not contain an AccessAtzToken", AuthError.ERROR_TYPE.ERROR_JSON);
    }

    @Override // com.amazon.identity.auth.device.endpoint.OauthTokenResponse
    boolean isInvalidToken(String str, String str2) {
        return false;
    }
}
