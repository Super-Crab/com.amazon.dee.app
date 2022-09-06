package com.amazon.identity.auth.device.authorization;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.appid.AppIdentifier;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.RequestedScope;
import com.amazon.identity.auth.device.endpoint.TokenVendor;
import com.amazon.identity.auth.device.thread.ThreadUtils;
import com.amazon.identity.auth.device.utils.HashAlgorithm;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.device.utils.MAPConstants;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.device.utils.PackageSignatureUtil;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AuthorizationHelper {
    public static final String AUTHZ_QUERY_PARAMS = "authzParams";
    private static final String HTTPS = "https";
    private static final String LANGUAGE_PARAMETER = "&language=";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.authorization.AuthorizationHelper";
    private static final String LWA_ANDROID_SESSION_EXPIRED_BODY = "lwa-android-session-expired-body";
    private static final String LWA_ANDROID_SESSION_EXPIRED_ERROR_CODE = "400";
    private static final String LWA_ANDROID_SESSION_EXPIRED_TITLE = "lwa-android-session-expired-title";

    private static String getAppIdentifierBlob(Context context, String str) {
        HashAlgorithm[] values;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("package", str);
            for (HashAlgorithm hashAlgorithm : HashAlgorithm.values()) {
                jSONObject.put(hashAlgorithm.getAlgorithmName(), new JSONArray((Collection) PackageSignatureUtil.getAllSignaturesFor(str, hashAlgorithm, context)));
            }
            return Base64.encodeToString(jSONObject.toString().getBytes(), 0);
        } catch (JSONException e) {
            MAPLog.e(LOG_TAG, "Encountered exception while generating app identifier blob", e);
            return null;
        }
    }

    public static String[] getCommonScopesForAuthorization(Context context, String[] strArr, List<RequestedScope> list) {
        List asList = Arrays.asList(strArr);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(asList);
        if (list != null) {
            for (RequestedScope requestedScope : list) {
                String scopeValue = requestedScope.getScopeValue();
                if (!arrayList.contains(scopeValue)) {
                    arrayList.add(scopeValue);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String getCustomQueryParams(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(AUTHZ_QUERY_PARAMS);
        StringBuffer stringBuffer = new StringBuffer("");
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                stringBuffer.append(Typography.amp);
                stringBuffer.append(getUrlEncodedQuery(str, bundle2.getString(str)));
            }
        }
        return stringBuffer.toString();
    }

    private static String getEndPoint() {
        return "/ap/oa";
    }

    private static String getErrorEndPoint() {
        return "/ap/oacerror";
    }

    private static String getErrorQueryParamsString() {
        return WebConstants.UriConstants.QUESTIONMARK_KEY + getUrlEncodedQuery("errorCode", "400") + WebConstants.UriConstants.AMPERSAND_KEY + getUrlEncodedQuery("title", LWA_ANDROID_SESSION_EXPIRED_TITLE) + WebConstants.UriConstants.AMPERSAND_KEY + getUrlEncodedQuery("message", LWA_ANDROID_SESSION_EXPIRED_BODY) + WebConstants.UriConstants.AMPERSAND_KEY + getUrlEncodedQuery("applicationName", "");
    }

    private static String getFilteredParams(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it2 = bundle.keySet().iterator();
        while (true) {
            boolean z = true;
            if (!it2.hasNext()) {
                break;
            }
            String next = it2.next();
            String string = bundle.getString(next);
            AuthzConstants.BUNDLE_KEY[] values = AuthzConstants.BUNDLE_KEY.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                } else if (values[i].val.equalsIgnoreCase(next)) {
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                sb.append(getUrlEncodedQuery(next, string));
                sb.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static String getHost(Context context, String str) {
        return MAPUtils.getHostType(context, str) + ".amazon.com";
    }

    private static String getLanguageParameter() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(LANGUAGE_PARAMETER);
        outline107.append(Locale.getDefault().toString());
        return outline107.toString();
    }

    public static String getOAuth2ErrorUrl(Context context) {
        URL url;
        String host = getHost(context, context.getPackageName());
        try {
            url = new URL("https", host, getErrorEndPoint() + getErrorQueryParamsString() + getLanguageParameter());
        } catch (MalformedURLException unused) {
            MAPLog.w(LOG_TAG, "Unable to generate OAuth2Error URL");
            url = null;
        }
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("url=");
        outline107.append(url.toString());
        MAPLog.pii(str, "Generating OAuth2Error URL", outline107.toString());
        return url.toString();
    }

    public static String getOAuth2Url(Context context, String str, String str2, String[] strArr, String str3, boolean z, boolean z2, Bundle bundle, AppInfo appInfo) throws AuthError {
        try {
            EndpointDomainBuilder forService = new EndpointDomainBuilder(context, appInfo).forService(Service.AUTHORIZATION);
            if (bundle.containsKey(LWAConstants.AUTHORIZE_BUNDLE_KEY.REGION.val)) {
                forService.forRegion(RegionUtil.regionForString(bundle.getString(LWAConstants.AUTHORIZE_BUNDLE_KEY.REGION.val)));
            }
            String queryString = getQueryString(context, str, str2, strArr, str3, z, z2, bundle);
            String url = new URL(forService.getDomain() + getEndPoint() + queryString + getLanguageParameter() + getCustomQueryParams(bundle)).toString();
            String str4 = LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("url=");
            sb.append(url);
            MAPLog.pii(str4, "Generating OAUTH2 URL", sb.toString());
            return url;
        } catch (MalformedURLException e) {
            throw new AuthError("MalformedURLException", e, AuthError.ERROR_TYPE.ERROR_BAD_PARAM);
        }
    }

    private static String getQueryString(Context context, String str, String str2, String[] strArr, String str3, boolean z, boolean z2, Bundle bundle) {
        StringBuffer stringBuffer = new StringBuffer(WebConstants.UriConstants.QUESTIONMARK_KEY);
        String redirectUri = getRedirectUri(str);
        stringBuffer.append(getUrlEncodedQuery("response_type", "code"));
        stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
        stringBuffer.append(getUrlEncodedQuery("redirect_uri", redirectUri));
        if (str2 != null) {
            stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
            stringBuffer.append(getUrlEncodedQuery("client_id", str2));
        }
        stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
        if (z) {
            stringBuffer.append(getUrlEncodedQuery("amzn_respectRmrMeAuthState", "1"));
            stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
            stringBuffer.append(getUrlEncodedQuery("amzn_showRmrMe", "1"));
            stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
            stringBuffer.append(getUrlEncodedQuery("amzn_rmrMeDefaultSelected", "1"));
            stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
        }
        if (z2) {
            stringBuffer.append(getUrlEncodedQuery("skipSignIn", "1"));
            stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
        }
        if (bundle.getBoolean(AuthzConstants.BUNDLE_KEY.SANDBOX.val, false)) {
            stringBuffer.append(getUrlEncodedQuery(MAPConstants.SANDBOX_MODE_QUERY_PARAM, "true"));
            stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
        }
        if (str2 == null) {
            str2 = str3;
        }
        boolean z3 = bundle.getBoolean(AuthzConstants.BUNDLE_KEY.GET_AUTH_CODE.val, false);
        StringBuilder sb = new StringBuilder();
        sb.append("clientId=" + str2 + WebConstants.UriConstants.AMPERSAND_KEY);
        sb.append("redirectUri=" + redirectUri + WebConstants.UriConstants.AMPERSAND_KEY);
        sb.append("clientRequestId=" + str3.toString() + WebConstants.UriConstants.AMPERSAND_KEY);
        if (bundle.containsKey(LWAConstants.INTERACTIVE_REQUEST_TYPE_KEY)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InteractiveRequestType=");
            outline107.append(bundle.getString(LWAConstants.INTERACTIVE_REQUEST_TYPE_KEY));
            outline107.append(WebConstants.UriConstants.AMPERSAND_KEY);
            sb.append(outline107.toString());
        }
        sb.append(AuthzConstants.BUNDLE_KEY.GET_AUTH_CODE.val + Config.Compare.EQUAL_TO + String.valueOf(z3));
        stringBuffer.append(getUrlEncodedQuery("state", sb.toString()));
        stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
        stringBuffer.append(getUrlEncodedQuery(AuthorizationResponseParser.SCOPE, ScopesHelper.getScopesString(strArr)));
        stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
        stringBuffer.append(getUrlEncodedQuery("appIdentifier", getAppIdentifierBlob(context, str)));
        if (bundle.containsKey(AuthzConstants.BUNDLE_KEY.SDK_VERSION.val) || bundle.containsKey(AuthzConstants.BUNDLE_KEY.SSO_VERSION.val)) {
            stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
            stringBuffer.append(getUrlEncodedQuery("sw_ver", getSoftwareVersion(bundle)));
        }
        stringBuffer.append(WebConstants.UriConstants.AMPERSAND_KEY);
        stringBuffer.append(getFilteredParams(bundle.getBundle(AuthzConstants.BUNDLE_KEY.EXTRA_URL_PARAMS.val)));
        return stringBuffer.toString();
    }

    private static String getRedirectUri(String str) {
        String outline72 = GeneratedOutlineSupport1.outline72("amzn://", str);
        String str2 = LOG_TAG;
        MAPLog.pii(str2, "Generating Redirect URI", "rediectUri=" + outline72);
        return outline72;
    }

    private static String getSoftwareVersion(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        if (bundle.containsKey(AuthzConstants.BUNDLE_KEY.SDK_VERSION.val)) {
            sb.append(bundle.getString(AuthzConstants.BUNDLE_KEY.SDK_VERSION.val));
            if (bundle.containsKey(AuthzConstants.BUNDLE_KEY.SSO_VERSION.val)) {
                sb.append(ProcessIdUtil.DEFAULT_PROCESSID);
            }
        }
        if (bundle.containsKey(AuthzConstants.BUNDLE_KEY.SSO_VERSION.val)) {
            sb.append(bundle.getString(AuthzConstants.BUNDLE_KEY.SSO_VERSION.val));
        }
        return sb.toString();
    }

    private static String getUrlEncodedQuery(String str, String str2) {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112(URLEncoder.encode(str), Config.Compare.EQUAL_TO);
        if (str2 != null) {
            outline112.append(URLEncoder.encode(str2));
        }
        return outline112.toString();
    }

    public static void sendAuthorizationCodeAsResponse(String str, String str2, String str3, AuthorizationListener authorizationListener) {
        try {
            if (!TextUtils.isEmpty(str)) {
                Bundle bundle = new Bundle();
                bundle.putString(AuthzConstants.BUNDLE_KEY.AUTHORIZATION_CODE.val, str);
                bundle.putString(AuthzConstants.BUNDLE_KEY.CLIENT_ID.val, str2);
                bundle.putString(AuthzConstants.BUNDLE_KEY.REDIRECT_URI.val, str3);
                MAPLog.i(LOG_TAG, "Return auth code success");
                if (authorizationListener == null) {
                    return;
                }
                authorizationListener.onSuccess(bundle);
                return;
            }
            throw new AuthError("Response bundle from Authorization does not contain authorization code", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        } catch (AuthError e) {
            String str4 = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Return auth code error. ");
            outline107.append(e.getMessage());
            MAPLog.e(str4, outline107.toString());
            if (authorizationListener == null) {
                return;
            }
            authorizationListener.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCodeForTokenExchange(Context context, String str, String str2, String str3, TokenVendor tokenVendor, AppIdentifier appIdentifier, Bundle bundle, boolean z, Bundle bundle2, AuthorizationListener authorizationListener) {
        if (!ThreadUtils.isRunningOnMainThread()) {
            String string = bundle.getString("code");
            if (!TextUtils.isEmpty(string)) {
                String string2 = bundle.getString(AuthorizationResponseParser.CLIENT_ID_STATE);
                String string3 = bundle.getString(AuthorizationResponseParser.REDIRECT_URI_STATE);
                String[] stringArray = bundle.getStringArray(AuthorizationResponseParser.SCOPE);
                String string4 = bundle.getString(AuthorizationResponseParser.RESPONSE_URL);
                String str4 = LOG_TAG;
                StringBuilder outline116 = GeneratedOutlineSupport1.outline116("code=", string, "clientId=", string2, " redirectUri=");
                GeneratedOutlineSupport1.outline181(outline116, string3, " directedId=", str3, " scopes=");
                outline116.append(Arrays.toString(stringArray));
                MAPLog.pii(str4, "Params extracted from OAuth2 response", outline116.toString());
                AppInfo appInfo = appIdentifier.getAppInfo(str, context);
                if (appInfo != null) {
                    try {
                        Bundle vendNewTokensFromCode = tokenVendor.vendNewTokensFromCode(string, str2, string3, stringArray, str3, context, appInfo, bundle2);
                        if (z) {
                            vendNewTokensFromCode.putString(AuthorizationResponseParser.RESPONSE_URL, string4);
                        }
                        authorizationListener.onSuccess(vendNewTokensFromCode);
                        return;
                    } catch (AuthError e) {
                        String str5 = LOG_TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed doing code for token exchange ");
                        outline107.append(e.getMessage());
                        MAPLog.e(str5, outline107.toString());
                        authorizationListener.onError(e);
                        return;
                    } catch (IOException e2) {
                        authorizationListener.onError(new AuthError("Failed to exchange code for token", e2, AuthError.ERROR_TYPE.ERROR_IO));
                        return;
                    }
                }
                String str6 = LOG_TAG;
                MAPLog.e(str6, "Unable to extract AppInfo for " + str);
                authorizationListener.onError(new AuthError("Unable to extract AppInfo", AuthError.ERROR_TYPE.ERROR_UNKNOWN));
                return;
            }
            authorizationListener.onError(new AuthError("Response bundle from Authorization was empty", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE));
            return;
        }
        MAPLog.e(LOG_TAG, "code for token exchange started on main thread");
        throw new IllegalStateException("authorize started on main thread");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doCodeForTokenExchange(final Context context, final String str, final String str2, final Bundle bundle, final boolean z, final String str3, final TokenVendor tokenVendor, final AppIdentifier appIdentifier, final Bundle bundle2, final AuthorizationListener authorizationListener) {
        ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.authorization.AuthorizationHelper.1
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle3 = bundle;
                if (bundle3 != null) {
                    AuthorizationHelper.this.startCodeForTokenExchange(context, str, str2, str3, tokenVendor, appIdentifier, bundle3, z, bundle2, authorizationListener);
                } else {
                    authorizationListener.onError(new AuthError("Response bundle from Authorization was null", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE));
                }
            }
        });
    }

    void doCodeForTokenExchange(Context context, String str, Bundle bundle, boolean z, String str2, TokenVendor tokenVendor, AppIdentifier appIdentifier, AuthorizationListener authorizationListener) {
        doCodeForTokenExchange(context, str, null, bundle, z, str2, tokenVendor, appIdentifier, Bundle.EMPTY, authorizationListener);
    }
}
