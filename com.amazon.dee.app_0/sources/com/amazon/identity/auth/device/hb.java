package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.az;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class hb {
    private final ea at;
    private final iy hO;
    private final ed o;
    private final ip pD;

    public hb(ed edVar, iy iyVar) {
        this.o = edVar;
        this.hO = iyVar;
        this.at = (ea) this.o.getSystemService("dcp_device_info");
        this.pD = new ip(edVar);
    }

    private JSONObject G(Bundle bundle) {
        if (TextUtils.isEmpty(bundle.getString("program"))) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("program", bundle.getString("program")).put("app_platform", "android").put("app_identifier", bundle.getString("app_identifier"));
            return jSONObject;
        } catch (JSONException unused) {
            io.e("PandaOAuthExchangeRequestHelper", "Cannot build switchContextJSon, return null.");
            return null;
        }
    }

    private URL cK(String str) {
        try {
            return EnvironmentUtils.cd().n(hr.c(this.o, str), "/auth/token");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Should never occur, hardcoded constant.", e);
        }
    }

    private void p(JSONObject jSONObject) throws JSONException {
        jSONObject.put("app_name", this.o.getPackageName());
        jSONObject.put("app_version", hv.gt());
    }

    private JSONObject u(String str, String str2, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str3)) {
            io.dm("PandaOAuthExchangeRequestHelper");
            jSONObject.put("device_type", ie.au(this.o));
        } else {
            io.dm("PandaOAuthExchangeRequestHelper");
            jSONObject.put("device_type", ie.s(this.o, str3));
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("token", str);
        jSONObject.put("account_refresh_token", jSONObject2);
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("actor_refresh_token", new JSONObject().put("token", str2));
        }
        new StringBuilder("JSON: ").append(jSONObject.toString());
        io.dm("PandaOAuthExchangeRequestHelper");
        return jSONObject;
    }

    public HttpURLConnection a(String str, String str2, String str3, String str4, String str5, String str6, String str7, @NonNull Bundle bundle, ej ejVar) throws IOException, JSONException {
        URL cK = cK(str4);
        io.i("PandaOAuthExchangeRequestHelper", "Refreshing Actor OAuth token with exchange token endpoint " + cK.toString() + " due to " + ejVar.O(this.o));
        return this.hO.a(this.o, cK, a(str, str2, str3, str5, str6, str7, bundle), TextUtils.isEmpty(str7) ? null : ia.e(bundle.getBundle("actor_cookies_for_request"), str4), str4, str6, ejVar);
    }

    public HttpURLConnection b(String str, String str2, String str3, ej ejVar) throws IOException, JSONException {
        URL cK = cK(str2);
        io.i("PandaOAuthExchangeRequestHelper", "Refreshing Normal OAuth token with exchange token endpoint " + cK.toString() + " due to " + ejVar.O(this.o));
        return this.hO.a(this.o, cK, f(str, ejVar), (List<MAPCookie>) null, str2, str3, ejVar);
    }

    public JSONObject c(String str, String str2, String str3, ej ejVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        p(jSONObject);
        jSONObject.put("source_token_type", str);
        jSONObject.put("source_token", str2);
        jSONObject.put("requested_token_type", str3);
        if (ejVar != null && mz.aY(this.o)) {
            jSONObject.put("calling_package_name", ejVar.O(this.o));
        }
        ed edVar = this.o;
        jSONObject.put("device_metadata", fm.b(ie.s(edVar, edVar.getPackageName()), this.at.cu(), ejVar));
        jSONObject.putOpt("map_version", this.pD.gG());
        return jSONObject;
    }

    JSONObject f(String str, ej ejVar) throws JSONException {
        return c(AbstractJSONTokenResponse.REFRESH_TOKEN, str, AbstractJSONTokenResponse.ACCESS_TOKEN, ejVar);
    }

    public JSONObject i(String str, String str2, ej ejVar) throws JSONException {
        JSONObject c = c(AbstractJSONTokenResponse.REFRESH_TOKEN, str, "delegated_access_token", ejVar);
        c.put("directed_id", str2);
        new StringBuilder("Delegated token exchange body: ").append(c.toString());
        io.dm("PandaOAuthExchangeRequestHelper");
        return c;
    }

    public OAuthTokenManager.a l(JSONObject jSONObject) throws JSONException, ParseException {
        if (jSONObject != null) {
            int i = jSONObject.getInt("expires_in");
            String string = jSONObject.getString("token_type");
            String string2 = jSONObject.getString(AbstractJSONTokenResponse.ACCESS_TOKEN);
            String optString = jSONObject.optString(AbstractJSONTokenResponse.REFRESH_TOKEN, null);
            if (!HttpClientModule.ElementsRequestKey.BEARER.equals(string)) {
                throw new ParseException("Unexpected token type.", 0);
            }
            if (string2 != null) {
                return new OAuthTokenManager.a(string2, i, optString);
            }
            throw new ParseException("Incomplete response.", 0);
        }
        throw new ParseException("Null response", 0);
    }

    public OAuthTokenManager.a[] m(JSONObject jSONObject) throws ParseException, OAuthTokenManager.OAuthTokenManagerException {
        if (jSONObject != null) {
            String str = null;
            if ("OpenInWebView".equals(ik.a(ik.b(jSONObject, "ui_action"), "next_step", null))) {
                str = ik.a(ik.b(jSONObject, "ui_action"), "url", null);
            }
            try {
                if (HttpClientModule.ElementsRequestKey.BEARER.equals(jSONObject.getString("token_type"))) {
                    JSONArray jSONArray = jSONObject.getJSONArray("device_tokens");
                    if (jSONArray != null && jSONArray.length() != 0) {
                        return b(jSONArray);
                    }
                    throw new ParseException("Incomplete response, device tokens is null.", 0);
                }
                throw new ParseException("Unexpected token type.", 0);
            } catch (Exception e) {
                if (TextUtils.isEmpty(str)) {
                    throw new ParseException(e.getMessage(), 0);
                }
                throw new OAuthTokenManager.OAuthTokenManagerException(MAPError.AccountError.AUTHENTICATION_CHALLENGED, str, MAPAccountManager.RegistrationError.AUTHENTICATION_CHALLENGED.value(), str);
            }
        }
        throw new ParseException("Null response", 0);
    }

    public AuthEndpointErrorParser.a n(JSONObject jSONObject) {
        if (jSONObject == null) {
            io.dm("PandaOAuthExchangeRequestHelper");
            return null;
        }
        try {
            String string = jSONObject.getString("error_description");
            String string2 = jSONObject.getString("error");
            return new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.getAuthErrorTypeFromCode(string2), string, null, ik.a(jSONObject, "error_index", null), null);
        } catch (JSONException unused) {
            io.dm("PandaOAuthExchangeRequestHelper");
            return new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.ParseError, "Unable to parse response JSON.", null, null, null);
        }
    }

    public az.a o(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("actor_info");
            return new az.a(jSONObject2.getString("actor_sub_type"), jSONObject2.getString("actor_entity_type"), jSONObject2.getString("actor_converted_type"));
        } catch (Exception e) {
            io.e("PandaOAuthExchangeRequestHelper", "Failed to parse actor info from the response. Just return null.", e);
            return null;
        }
    }

    OAuthTokenManager.a[] b(JSONArray jSONArray) throws JSONException {
        int length = jSONArray.length();
        OAuthTokenManager.a[] aVarArr = new OAuthTokenManager.a[length];
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.getString("device_type");
            String string2 = jSONObject.getJSONObject("actor_access_token").getString("token");
            String a = a(jSONObject, "actor_refresh_token", "token", "Actor refresh token");
            aVarArr[i] = new OAuthTokenManager.a(string2, jSONObject.getJSONObject("actor_access_token").getInt("expires_in"), a(jSONObject, "account_refresh_token", "token", "Account refresh token"), a, string);
        }
        return aVarArr;
    }

    public boolean a(Integer num) {
        return this.hO.a(num);
    }

    public JSONObject a(String str, String str2, String str3, String str4, String str5) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        p(jSONObject);
        jSONObject.put("source_token_type", AccountConstants.KEY_AUTHORIZATION_CODE);
        jSONObject.put("source_token", str);
        jSONObject.put("requested_token_type", AbstractJSONTokenResponse.ACCESS_TOKEN);
        jSONObject.put(AccountConstants.KEY_CODE_VERIFIER, str2);
        jSONObject.put("code_algorithm", str3);
        jSONObject.put("client_id", str4);
        jSONObject.put(AccountConstants.KEY_CLIENT_DOMAIN, str5);
        jSONObject.put("app_name", this.o.getPackageName());
        jSONObject.putOpt("map_version", this.pD.gG());
        return jSONObject;
    }

    JSONObject a(String str, String str2, String str3, String str4, String str5, String str6, @NonNull Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        p(jSONObject);
        jSONObject.put("source_token_type", AbstractJSONTokenResponse.REFRESH_TOKEN);
        jSONObject.put("requested_token_type", "actor_access_token");
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(u(str, str3, null));
        if (str2 != null && !str2.equals(str) && !TextUtils.isEmpty(str5)) {
            jSONArray.put(u(str2, null, str5));
        }
        jSONObject.put("source_device_tokens", jSONArray);
        jSONObject.put("actor_id", str4);
        jSONObject.putOpt("actor_switch_context", G(bundle));
        if (!TextUtils.isEmpty(str6)) {
            jSONObject.put("token_validation_failure_context", str6);
            Bundle bundle2 = bundle.getBundle("auth_portal_config");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("client_config", bundle2.getString(TokenKeys.Options.KEY_CHALLENGE_URL_ASSOC_HANDLE));
            jSONObject2.put("ui_config", bundle2.getString(TokenKeys.Options.KEY_CHALLENGE_URL_PAGE_ID));
            jSONObject2.put("domain", bundle2.getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN));
            jSONObject2.put("redirect_uri", OpenIdRequest.aX(bundle2.getString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN)));
            jSONObject2.put("cancel_uri", OpenIdRequest.aY(bundle2.getString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN)));
            jSONObject.put("auth_portal_config", jSONObject2);
            jSONObject.put("ui_capacity", MAPWebviewActivityTemplate.ba());
        }
        new StringBuilder("RefreshActorTokenBodyJSON").append(jSONObject.toString());
        io.dm("PandaOAuthExchangeRequestHelper");
        return jSONObject;
    }

    private static String a(JSONObject jSONObject, String str, String str2, String str3) {
        try {
            return jSONObject.getJSONObject(str).getString(str2);
        } catch (Exception unused) {
            io.w("PandaOAuthExchangeRequestHelper", String.format("%s is not set in the response, ignoring...", str3));
            return null;
        }
    }
}
