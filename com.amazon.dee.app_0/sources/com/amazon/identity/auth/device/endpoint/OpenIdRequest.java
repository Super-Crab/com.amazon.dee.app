package com.amazon.identity.auth.device.endpoint;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.dee.app.BuildConfig;
import com.amazon.identity.auth.device.api.FederatedAuthConfig;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class OpenIdRequest {
    private static final String gM = "com.amazon.identity.auth.device.endpoint.OpenIdRequest";
    private final Bundle ec;
    private final Map<String, String> iq = new HashMap();
    private final REQUEST_TYPE ir;
    private String is;

    /* renamed from: it  reason: collision with root package name */
    private final Uri.Builder f10it;
    private Map<String, String> iu;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum REQUEST_TYPE {
        SIGN_IN,
        REGISTER,
        CONFIRM_CREDENTIAL,
        AUTHENTICATE,
        FORGOT_PASSWORD,
        CALLBACK_FOR_3P_LOGIN,
        CNEP
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum TOKEN_SCOPE {
        ACCESS,
        REFRESH
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public OpenIdRequest(java.lang.String r7, com.amazon.identity.auth.device.endpoint.OpenIdRequest.REQUEST_TYPE r8, android.os.Bundle r9) throws java.lang.IllegalArgumentException, java.lang.NullPointerException {
        /*
            Method dump skipped, instructions count: 664
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.endpoint.OpenIdRequest.<init>(java.lang.String, com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE, android.os.Bundle):void");
    }

    public static String aX(@NonNull String str) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        String ba = EnvironmentUtils.cd().ba(str);
        EnvironmentUtils.cd();
        EnvironmentUtils.cf();
        builder.authority(ba);
        builder.path("/ap/maplanding");
        return builder.build().toString();
    }

    public static String aY(@NonNull String str) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        String ba = EnvironmentUtils.cd().ba(str);
        EnvironmentUtils.cd();
        EnvironmentUtils.cf();
        builder.authority(ba);
        builder.path("/ap/mapcancel");
        return builder.build().toString();
    }

    public static String m(String str, String str2) {
        return String.format("%x", new BigInteger(String.format("%s#%s", str, str2).getBytes()));
    }

    private void v(Bundle bundle) throws IllegalArgumentException {
        String string;
        if (bundle == null) {
            return;
        }
        String string2 = bundle.getString("com.amazon.identity.ap.pageid");
        if (string2 != null) {
            aU(string2);
        }
        if (bundle.getString("com.amazon.identity.ap.assoc_handle") == null) {
            String host = getHost();
            if (host != null) {
                if (host.endsWith(BuildConfig.RETAIL_HOST_JP)) {
                    string = "amzn_device_jp";
                } else if (host.endsWith(BuildConfig.RETAIL_HOST_CN)) {
                    string = "amzn_device_cn";
                }
            }
            string = null;
        } else {
            string = bundle.getString("com.amazon.identity.ap.assoc_handle");
        }
        if (string != null) {
            aT(string);
        }
        String string3 = bundle.getString("com.amazon.identity.ap.clientContext");
        if (string3 != null) {
            aV(string3);
        }
        Bundle bundle2 = bundle.getBundle(MAPAccountManager.AuthPortalOptions.KEY_ADDITIONAL_SIGN_IN_PARAMETERS);
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                this.iq.put(str, (String) bundle2.get(str));
            }
        }
        Bundle bundle3 = bundle.getBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        if (bundle3 != null) {
            for (String str2 : bundle3.keySet()) {
                Object obj = bundle3.get(str2);
                if (obj instanceof String) {
                    this.iq.put(str2, (String) obj);
                } else {
                    String format = String.format("Invalid value type passed in for AuthPortalOptions.KEY_REQUEST_PARAMETERS, only strings are allowed, please us Bundle.putString. Object in violation key: %s object type: %s", str2, obj.getClass().getName());
                    io.e(gM, format);
                    throw new IllegalArgumentException(String.format(format, new Object[0]));
                }
            }
        }
        FederatedAuthConfig federatedAuthConfig = (FederatedAuthConfig) bundle.getParcelable(MAPAccountManager.KEY_FEDERATED_AUTH_CONFIG);
        if (federatedAuthConfig == null) {
            return;
        }
        this.iq.put("relyingParty", federatedAuthConfig.getRelyingParty());
        this.iq.put("identityProvider", federatedAuthConfig.getIdentityProvider().getValue());
        this.iq.put("request", "signin");
    }

    public void aQ(String str) {
        this.iq.put("openid.pape.max_auth_age", str);
    }

    public void aR(String str) {
        this.iq.put("openid.claimed_id", str);
        this.iq.put("openid.identity", str);
    }

    public void aS(String str) {
        this.is = str;
        this.iq.put("openid.return_to", str);
    }

    public void aT(String str) {
        this.iq.put("openid.assoc_handle", str);
    }

    public void aU(String str) {
        this.iq.put("pageId", str);
    }

    public void aV(String str) {
        this.iq.put("clientContext", str);
    }

    public void aW(String str) {
        this.iq.put("openid.oa2.client_id", AccountManagerConstants.CLIENT_ID_PREFIX.concat(String.valueOf(str)));
    }

    public void b(Map<String, String> map) {
        this.iu = map;
    }

    public void bR() {
        this.iq.put("authCookies", "0");
    }

    public String bS() {
        Uri.Builder buildUpon = this.f10it.build().buildUpon();
        if (this.ec.getBoolean(MAPAccountManager.KEY_IS_CALLBACK_FROM_3P_PARAM)) {
            buildUpon.encodedQuery(this.ec.getString(MAPAccountManager.KEY_3P_CALLBACK_QUERY_PARAM));
        } else {
            Map<String, String> map = this.iu;
            if (map != null && map.size() > 0) {
                this.iq.putAll(this.iu);
                this.iu.clear();
            }
            for (Map.Entry<String, String> entry : this.iq.entrySet()) {
                buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        Uri build = buildUpon.build();
        String queryParameter = build.getQueryParameter("openid.oa2.code_challenge");
        String queryParameter2 = build.getQueryParameter("openid.oa2.code_challenge_method");
        if (!TextUtils.equals(queryParameter, this.iq.get("openid.oa2.code_challenge")) || !TextUtils.equals(queryParameter2, this.iq.get("openid.oa2.code_challenge_method"))) {
            Set<String> queryParameterNames = build.getQueryParameterNames();
            Uri.Builder clearQuery = build.buildUpon().clearQuery();
            for (String str : queryParameterNames) {
                if (TextUtils.equals(str, "openid.oa2.code_challenge")) {
                    if (!TextUtils.isEmpty(this.iq.get("openid.oa2.code_challenge"))) {
                        clearQuery.appendQueryParameter(str, this.iq.get("openid.oa2.code_challenge"));
                    } else {
                        io.dm(gM);
                    }
                } else if (TextUtils.equals(str, "openid.oa2.code_challenge_method")) {
                    if (!TextUtils.isEmpty(this.iq.get("openid.oa2.code_challenge_method"))) {
                        clearQuery.appendQueryParameter(str, this.iq.get("openid.oa2.code_challenge_method"));
                    } else {
                        io.dm(gM);
                    }
                } else {
                    clearQuery.appendQueryParameter(str, build.getQueryParameter(str));
                }
            }
            build = clearQuery.build();
        }
        return build.toString();
    }

    public String bT() {
        Uri build = this.f10it.build();
        return String.format(Locale.US, "%s://%s", build.getScheme(), build.getAuthority());
    }

    public String bU() {
        return this.is;
    }

    public String bV() {
        String host;
        String string = this.ec.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN);
        if (!TextUtils.isEmpty(string)) {
            host = EnvironmentUtils.cd().ba(string);
        } else {
            host = getHost();
        }
        Uri.Builder builder = new Uri.Builder();
        if (this.ec.containsKey("ab_federated_auth") && this.ec.containsKey("domain_hint") && this.ec.containsKey("app_name")) {
            builder.scheme(this.ec.getString("app_name"));
        } else {
            builder.scheme("https");
        }
        EnvironmentUtils.cd();
        EnvironmentUtils.cf();
        builder.authority(host);
        builder.path("/ap/maplanding");
        return builder.build().toString();
    }

    public REQUEST_TYPE bW() {
        return this.ir;
    }

    public void e(boolean z) {
        this.iq.put("disableLoginPrepopulate", z ? "1" : "0");
    }

    public String getHost() {
        return this.f10it.build().getAuthority();
    }

    public Map<String, String> getRequestHeaders() {
        HashMap hashMap = new HashMap();
        FederatedAuthConfig federatedAuthConfig = (FederatedAuthConfig) this.ec.getParcelable(MAPAccountManager.KEY_FEDERATED_AUTH_CONFIG);
        if (federatedAuthConfig != null) {
            hashMap.put("3p_access_token", federatedAuthConfig.getAccessToken());
        }
        return hashMap;
    }
}
