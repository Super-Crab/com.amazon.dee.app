package com.amazon.dee.app.ui.web;

import android.text.TextUtils;
import com.amazon.dee.app.services.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class JavaScriptResponse {
    public static final String KEY_ERROR = "error";
    public static final String KEY_ERROR_REASON = "errorReason";
    public static final String KEY_REQUEST_ID = "requestId";
    public static final String KEY_RESPONSE = "response";
    private static final String TAG = "com.amazon.dee.app.ui.web.JavaScriptResponse";
    private boolean mError;
    private String mErrorReason;
    private String mRequestId;
    private JSONObject mResponse;

    public final String getErrorReason() {
        return this.mErrorReason;
    }

    public final String getRequestId() {
        return this.mRequestId;
    }

    public final JSONObject getResponse() {
        return this.mResponse;
    }

    public final boolean isError() {
        return this.mError;
    }

    public final JavaScriptResponse setError(boolean z) {
        this.mError = z;
        return this;
    }

    public final JavaScriptResponse setErrorReason(String str) {
        this.mErrorReason = str;
        if (!TextUtils.isEmpty(str)) {
            this.mError = true;
        }
        return this;
    }

    public final JavaScriptResponse setRequestId(String str) {
        this.mRequestId = str;
        return this;
    }

    public final JavaScriptResponse setResponse(JSONObject jSONObject) {
        this.mResponse = jSONObject;
        return this;
    }

    public final JSONObject toJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestId", getRequestId());
        jSONObject.put("error", isError());
        if (isError()) {
            jSONObject.put(KEY_ERROR_REASON, getErrorReason());
        } else {
            jSONObject.put("response", getResponse());
        }
        return jSONObject;
    }

    public final String toString() {
        try {
            return toJSON().toString();
        } catch (JSONException e) {
            Log.w(TAG, "JSONException happens while parsing JSONValue to String", e);
            return "";
        }
    }
}
