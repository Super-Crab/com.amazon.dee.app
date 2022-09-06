package com.amazon.identity.auth.device.endpoint;

import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.InvalidTokenAuthError;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.amazon.identity.auth.map.device.utils.MAPVersionInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public abstract class AbstractJSONTokenResponse implements PandaResponse {
    public static final String ACCESS_TOKEN = "access_token";
    protected static final String COOKIE = "cookie";
    private static final String DEFAULT_CHAR_SET = "UTF-8";
    public static final String EXPIRES_IN = "expires_in";
    protected static final String FORCE_UPDATE = "force_update";
    private static final String FORCE_UPDATE_REQUESTED = "1";
    private static final String INVALID_SOURCE_TOKEN = "InvalidSourceToken";
    private static final String INVALID_TOKEN = "INVALID_TOKEN";
    private static final String INVALID_TOKEN_CODE = "InvalidToken";
    private static final String JSON_CODE_FIELD = "code";
    protected static final String JSON_ERROR_FIELD = "error";
    private static final String JSON_MESSAGE_FIELD = "message";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String REQUEST_ID = "request_id";
    protected static final String RESPONSE = "response";
    private static final String SERVER_ERROR = "ServerError";
    private static final String SERVER_ERROR_PAGE_IDENTIFIER = "!DOCTYPE html";
    protected static final String TOKEN = "token";
    public static final String TOKEN_EXPIRES_IN = "token_expires_in";
    public static final String TOKEN_TYPE = "token_type";
    public static final String VER_UNKOWN = "Unkown";
    private final HttpResponse _response;
    private String _sEntity;

    public AbstractJSONTokenResponse(HttpResponse httpResponse) {
        this._response = httpResponse;
    }

    private String getEntityString(HttpEntity httpEntity) throws IOException {
        InputStream inputStream;
        try {
            inputStream = httpEntity.getContent();
            try {
                if (isGZIPEncoded(httpEntity)) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                if (httpEntity.getContentLength() <= 2147483647L) {
                    String contentCharSet = EntityUtils.getContentCharSet(httpEntity);
                    if (contentCharSet == null) {
                        contentCharSet = "UTF-8";
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            String str = new String(byteArrayOutputStream.toByteArray(), contentCharSet);
                            inputStream.close();
                            return str;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
                }
            } catch (Throwable th) {
                th = th;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    public static boolean hasReceived500Error(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        return statusCode >= 500 && statusCode <= 599;
    }

    private boolean isGZIPEncoded(HttpEntity httpEntity) {
        Header contentEncoding = httpEntity.getContentEncoding();
        if (contentEncoding != null) {
            for (HeaderElement headerElement : contentEncoding.getElements()) {
                if (headerElement.getName().equalsIgnoreCase("gzip")) {
                    return true;
                }
            }
        }
        return false;
    }

    protected abstract void doParse(JSONObject jSONObject) throws IOException, JSONException, AuthError;

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject extractResponseJSONObject(JSONObject jSONObject) throws JSONException {
        return jSONObject.getJSONObject("response");
    }

    protected String getEntity() {
        return this._sEntity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getExpiresIn(JSONObject jSONObject) {
        long j;
        try {
            if (jSONObject.has(TOKEN_EXPIRES_IN)) {
                j = jSONObject.getLong(TOKEN_EXPIRES_IN);
            } else if (jSONObject.has("expires_in")) {
                j = jSONObject.getLong("expires_in");
            } else {
                MAPLog.w(LOG_TAG, "Unable to find expiration time in JSON response, AccessToken will not expire locally");
                return 0L;
            }
            return j;
        } catch (JSONException unused) {
            MAPLog.e(LOG_TAG, "Unable to parse expiration time in JSON response, AccessToken will not expire locally");
            return 0L;
        }
    }

    protected JSONObject getJSONResponse() throws IOException, JSONException {
        this._sEntity = getEntityString(this._response.getEntity()).trim();
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("entity=");
        outline107.append(this._sEntity);
        MAPLog.pii(str, "Entity Extracted", outline107.toString());
        JSONObject jSONObject = new JSONObject(this._sEntity);
        JSONObject extractResponseJSONObject = extractResponseJSONObject(jSONObject);
        logRequestId(jSONObject);
        return extractResponseJSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpResponse getResponse() {
        return this._response;
    }

    @Override // com.amazon.identity.auth.device.endpoint.PandaResponse
    public int getStatusCode() throws AuthError {
        try {
            return this._response.getStatusLine().getStatusCode();
        } catch (NullPointerException e) {
            throw new AuthError("StatusLine is null", e, AuthError.ERROR_TYPE.ERROR_COM);
        }
    }

    public String getVersion() {
        return MAPVersionInfo.VERSION;
    }

    protected void handleForceUpdate(JSONObject jSONObject) throws AuthError {
        ParseException e;
        String str;
        JSONException e2;
        try {
            str = jSONObject.getString(FORCE_UPDATE);
            if (str == null) {
                return;
            }
            try {
                if (!str.equals("1")) {
                    return;
                }
                String version = getVersion();
                MAPLog.e(LOG_TAG, "Force update requested ver:" + version);
                throw new AuthError("Server denied request, requested Force Update ver:" + version, null, AuthError.ERROR_TYPE.ERROR_FORCE_UPDATE);
            } catch (JSONException e3) {
                e2 = e3;
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                String str2 = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JSON exception parsing force update response:");
                outline107.append(e2.toString());
                MAPLog.e(str2, outline107.toString());
                throw new AuthError(e2.getMessage(), e2, AuthError.ERROR_TYPE.ERROR_JSON);
            } catch (ParseException e4) {
                e = e4;
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                String str3 = LOG_TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("JSON parsing exception force update parsing response:");
                outline1072.append(e.toString());
                MAPLog.e(str3, outline1072.toString());
                throw new AuthError(e.getMessage(), e, AuthError.ERROR_TYPE.ERROR_PARSE);
            }
        } catch (JSONException e5) {
            e2 = e5;
            str = null;
        } catch (ParseException e6) {
            e = e6;
            str = null;
        }
    }

    protected void handleJSONError(JSONObject jSONObject) throws AuthError, JSONException {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("error");
            String string = jSONObject2.getString("code");
            if (SERVER_ERROR.equalsIgnoreCase(string)) {
                if (!jSONObject2.getString("message").startsWith(INVALID_TOKEN)) {
                    throwUnknownAuthError(string);
                    return;
                }
                throw new InvalidTokenAuthError("Invalid Exchange parameter - SERVER_ERROR.");
            } else if (!INVALID_SOURCE_TOKEN.equalsIgnoreCase(string)) {
                if (!INVALID_TOKEN_CODE.equals(string)) {
                    if (hasReceived500Error(this._response)) {
                        throwUnknownAuthError("500 error (status=" + getStatusCode() + ")" + string);
                        return;
                    }
                    throwUnknownAuthError(string);
                    return;
                }
                throw new InvalidTokenAuthError("Token used is invalid.");
            } else {
                throw new InvalidTokenAuthError("Invalid Source Token in exchange parameter");
            }
        } catch (JSONException e) {
            if (0 != 0) {
                throw new AuthError("JSON exception parsing json error response:", e, AuthError.ERROR_TYPE.ERROR_JSON);
            }
        } catch (ParseException e2) {
            if (0 != 0) {
                throw new AuthError("Exception parsing response", e2, AuthError.ERROR_TYPE.ERROR_PARSE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logRequestId(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString(REQUEST_ID);
            String str = LOG_TAG;
            MAPLog.pii(str, "ExchangeRepsonse", "requestId=" + string);
        } catch (JSONException unused) {
            MAPLog.w(LOG_TAG, "No RequestId in JSON response");
        }
    }

    @Override // com.amazon.identity.auth.device.endpoint.PandaResponse
    public void parse() throws AuthError {
        String str = "";
        try {
            try {
                try {
                    if (hasReceived500Error(this._response)) {
                        str = "500 error (status=" + getStatusCode() + ")";
                    }
                    JSONObject jSONResponse = getJSONResponse();
                    handleJSONError(jSONResponse);
                    doParse(jSONResponse);
                    handleForceUpdate(jSONResponse);
                    try {
                        this._response.getEntity().getContent().close();
                    } catch (IOException e) {
                        String str2 = LOG_TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IOException closing response ");
                        outline107.append(e.toString());
                        MAPLog.e(str2, outline107.toString());
                    } catch (IllegalStateException e2) {
                        String str3 = LOG_TAG;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IllegalStateException closing response ");
                        outline1072.append(e2.toString());
                        MAPLog.i(str3, outline1072.toString());
                    }
                } catch (JSONException e3) {
                    if (this._sEntity != null && this._sEntity.contains(SERVER_ERROR_PAGE_IDENTIFIER)) {
                        MAPLog.e(LOG_TAG, "Server sending back default error page - BAD request");
                        throw new AuthError("Server sending back default error page - BAD request", e3, AuthError.ERROR_TYPE.ERROR_JSON);
                    }
                    MAPLog.w(LOG_TAG, "JSON exception parsing " + str + " response:" + e3.toString());
                    String str4 = LOG_TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("JSON exception html = ");
                    sb.append(this._sEntity);
                    MAPLog.w(str4, sb.toString());
                    throw new AuthError(e3.getMessage(), e3, AuthError.ERROR_TYPE.ERROR_JSON);
                }
            } catch (ParseException e4) {
                MAPLog.e(LOG_TAG, "Exception parsing " + str + " response:" + e4.toString());
                throw new AuthError(e4.getMessage(), e4, AuthError.ERROR_TYPE.ERROR_PARSE);
            } catch (IOException e5) {
                MAPLog.e(LOG_TAG, "Exception accessing " + str + " response:" + e5.toString());
                throw new AuthError(e5.getMessage(), e5, AuthError.ERROR_TYPE.ERROR_COM);
            }
        } catch (Throwable th) {
            try {
                this._response.getEntity().getContent().close();
            } catch (IOException e6) {
                String str5 = LOG_TAG;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("IOException closing response ");
                outline1073.append(e6.toString());
                MAPLog.e(str5, outline1073.toString());
            } catch (IllegalStateException e7) {
                String str6 = LOG_TAG;
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("IllegalStateException closing response ");
                outline1074.append(e7.toString());
                MAPLog.i(str6, outline1074.toString());
            }
            throw th;
        }
    }

    public void throwUnknownAuthError(String str) throws AuthError {
        throw new AuthError(GeneratedOutlineSupport1.outline72("Server Error : ", String.format("Error code: %s Server response: %s", str, this._sEntity)), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
    }
}
