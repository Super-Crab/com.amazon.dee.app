package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class JavaScriptRequest {
    public static final String KEY_REQUEST_ID = "requestId";
    private static final String TAG = "com.amazon.dee.app.ui.web.JavaScriptRequest";
    private final String mRawRequestString;
    private String mRequestId;
    private JSONObject mRequestObj;

    /* loaded from: classes12.dex */
    public static class InvalidJavaScriptRequestException extends Exception {
    }

    public JavaScriptRequest(String str) {
        this.mRawRequestString = str;
    }

    public String getParameterString(String str) {
        JSONObject jSONObject = this.mRequestObj;
        if (jSONObject != null) {
            return jSONObject.optString(str, null);
        }
        return null;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public JavaScriptResponse prepareResponse() {
        JavaScriptResponse javaScriptResponse = new JavaScriptResponse();
        javaScriptResponse.setResponse(new JSONObject());
        javaScriptResponse.setRequestId(this.mRequestId);
        return javaScriptResponse;
    }

    public void tryParse() throws InvalidJavaScriptRequestException {
        try {
            this.mRequestObj = new JSONObject(this.mRawRequestString);
            this.mRequestId = this.mRequestObj.getString("requestId");
        } catch (JSONException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received invalid JavaScriptRequest: ");
            outline107.append(this.mRawRequestString);
            Log.e(str, e, outline107.toString(), new Object[0]);
            throw new InvalidJavaScriptRequestException();
        }
    }
}
