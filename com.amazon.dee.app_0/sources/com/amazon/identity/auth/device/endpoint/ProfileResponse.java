package com.amazon.identity.auth.device.endpoint;

import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.InsufficientScopeAuthError;
import com.amazon.identity.auth.device.InvalidTokenAuthError;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class ProfileResponse extends AbstractJSONTokenResponse {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.ProfileResponse";
    private JSONObject profileResponse;

    public ProfileResponse(HttpResponse httpResponse) {
        super(httpResponse);
    }

    private boolean isInsufficientScope(String str, String str2) {
        return "insufficient_scope".equals(str);
    }

    private boolean isInvalidToken(String str, String str2) {
        return "invalid_token".equals(str) || ("invalid_request".equals(str) && !TextUtils.isEmpty(str2) && str2.contains(AbstractJSONTokenResponse.ACCESS_TOKEN));
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected void doParse(JSONObject jSONObject) throws IOException, JSONException, AuthError {
        this.profileResponse = jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    public JSONObject extractResponseJSONObject(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    public JSONObject getProfileResponse() {
        return this.profileResponse;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    public String getVersion() {
        return "3.0.2";
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected void handleJSONError(JSONObject jSONObject) throws AuthError, JSONException {
        try {
            String string = jSONObject.getString("error");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            String string2 = jSONObject.getString("error_description");
            if (!isInsufficientScope(string, string2)) {
                if (isInvalidToken(string, string2)) {
                    String str = LOG_TAG;
                    MAPLog.pii(str, "Invalid Token in exchange.", "info=" + jSONObject);
                    throw new InvalidTokenAuthError("Invalid Token in exchange. " + jSONObject);
                }
                String str2 = LOG_TAG;
                MAPLog.pii(str2, "Server error doing authorization exchange.", "info=" + jSONObject);
                throw new AuthError("Server error doing authorization exchange. " + jSONObject, AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
            }
            String str3 = LOG_TAG;
            MAPLog.pii(str3, "Insufficient scope in token in exchange.", "info=" + jSONObject);
            throw new InsufficientScopeAuthError("Profile request not valid for authorized scopes");
        } catch (JSONException unused) {
            if (!TextUtils.isEmpty(null)) {
                throw new AuthError(GeneratedOutlineSupport1.outline72("Server Error : ", null), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
            }
        }
    }
}
