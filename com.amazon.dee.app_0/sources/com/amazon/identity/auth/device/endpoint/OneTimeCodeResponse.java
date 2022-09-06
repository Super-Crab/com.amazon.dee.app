package com.amazon.identity.auth.device.endpoint;

import com.amazon.identity.auth.device.AuthError;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class OneTimeCodeResponse extends AbstractJSONTokenResponse {
    private static final String ONE_TIME_CODE_PARAMETER = "oneTimeCode";
    private String oneTimeCode;

    public OneTimeCodeResponse(HttpResponse httpResponse) {
        super(httpResponse);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected void doParse(JSONObject jSONObject) throws IOException, JSONException, AuthError {
        setOneTimeCode(jSONObject.getString(ONE_TIME_CODE_PARAMETER));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    public JSONObject extractResponseJSONObject(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    public String getOneTimeCode() {
        return this.oneTimeCode;
    }

    public void setOneTimeCode(String str) {
        this.oneTimeCode = str;
    }
}
