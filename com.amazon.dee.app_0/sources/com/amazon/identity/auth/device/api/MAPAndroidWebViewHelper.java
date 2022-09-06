package com.amazon.identity.auth.device.api;

import android.app.Activity;
import android.content.Context;
import android.net.UrlQuerySanitizer;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.webkit.WebView;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.fj;
import com.amazon.identity.auth.device.hf;
import com.amazon.identity.auth.device.in;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ji;
import com.amazon.identity.auth.device.jl;
import com.amazon.identity.auth.device.jo;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPAndroidWebViewHelper {
    public static final String AUTHENTICATOR_JS_BRIDGE = "AuthenticatorJSBridge";
    public static final String JS_BRIDGE_TYPE = "JSBridgeType";
    @FireOsSdk
    public static final String ON_REGISTRATION_SUCCESS = "ON_REGISTRATION_SUCCESS";
    @FireOsSdk
    public static final String ON_UNABLE_TO_GET_COOKIES = "ON_UNABLE_TO_GET_COOKIES";
    private static final String TAG = "com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper";
    private final TokenManagement au;
    private final MAPAccountManager av;
    private Parameters gm = new Parameters((byte) 0);
    private final Activity mActivity;
    private final Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class Parameters {
        public Bundle additionalSignInParams;
        public String associationHandle;
        public String claimedId;
        public String clientContext;
        public String domain;
        public String identity;
        public boolean isFederatedAuthentication;
        public String pageId;
        public String prompt;
        public String requestType;
        public String returnToURL;
        public String userAgent;

        private Parameters() {
        }

        /* synthetic */ Parameters(byte b) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum URL_TYPE {
        REGULAR,
        SIGNIN,
        REGISTER,
        CONFIRM_CREDENTIAL
    }

    @FireOsSdk
    public MAPAndroidWebViewHelper(Context context) {
        in.a(context, "Context");
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
            this.mContext = this.mActivity.getBaseContext().getApplicationContext();
        } else {
            this.mActivity = null;
            this.mContext = context.getApplicationContext();
        }
        this.av = new MAPAccountManager(this.mContext);
        this.au = new TokenManagement(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String aG(String str) {
        return TextUtils.isEmpty(str) ? this.gm.returnToURL : str;
    }

    static /* synthetic */ void b(MAPAndroidWebViewHelper mAPAndroidWebViewHelper, WebView webView, Bundle bundle, String str, String str2, MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        if (bundle.getInt(MAPError.KEY_ERROR_CODE) == MAPError.AccountError.ACCOUNT_ALREADY_REGISTERED.getErrorCode()) {
            mAPAndroidWebViewHelper.a(webView, bundle, str, str2, mAPAndroidWebViewNavigator);
            return;
        }
        io.e(TAG, "Error in handleAuthActivityResultError");
        mAPAndroidWebViewHelper.onEvent(ON_UNABLE_TO_GET_COOKIES, bundle);
    }

    private String c(Bundle bundle, String str) {
        if (bundle == null) {
            return str;
        }
        jl.b gT = jl.gT();
        for (String str2 : bundle.keySet()) {
            gT.aw(str2, bundle.getString(str2));
        }
        String gU = gT.gU();
        try {
            URI uri = new URI(str);
            String query = uri.getQuery();
            if (!TextUtils.isEmpty(query)) {
                gU = query + WebConstants.UriConstants.AMPERSAND_KEY + gU;
            }
            return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), gU, uri.getFragment()).toString();
        } catch (URISyntaxException unused) {
            io.e(TAG, "Error in appending the signIn query parameters to returnTo url.");
            return str;
        }
    }

    private void e(Bundle bundle, Bundle bundle2) {
        Bundle bundle3;
        if (bundle2 == null) {
            return;
        }
        if (bundle.containsKey(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS)) {
            bundle3 = bundle.getBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        } else {
            Bundle bundle4 = new Bundle();
            bundle.putBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS, bundle4);
            bundle3 = bundle4;
        }
        String string = bundle2.getString("override.assoc_handle");
        if (!TextUtils.isEmpty(string)) {
            bundle3.putString("openid.assoc_handle", string);
        }
        String string2 = bundle2.getString("override.page_id");
        if (TextUtils.isEmpty(string2)) {
            return;
        }
        bundle3.putString("pageId", string2);
    }

    @FireOsSdk
    public static boolean enableMAPForWebView(WebView webView, MAPAndroidWebViewClient mAPAndroidWebViewClient, Bundle bundle) {
        webView.setWebViewClient(mAPAndroidWebViewClient);
        return enableMAPJSBridgeForWebView(webView, bundle);
    }

    @FireOsSdk
    public static boolean enableMAPJSBridgeForWebView(WebView webView, Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        if (!webView.getSettings().getJavaScriptEnabled()) {
            String str = TAG;
            io.w(str, webView.getContext().getPackageName() + " disabled the JavaScript on WebView. MAP will enable the JavaScript");
            StringBuilder sb = new StringBuilder("MAPWebViewJavaScriptOriginallyDisabled:");
            sb.append(webView.getContext().getPackageName());
            mq.incrementCounterAndRecord(sb.toString(), new String[0]);
            webView.getSettings().setJavaScriptEnabled(true);
        }
        webView.addJavascriptInterface(new fj(webView, null, null), "MAPAndroidJSBridge");
        return true;
    }

    private void f(Bundle bundle, Bundle bundle2) {
        if (bundle2 == null) {
            return;
        }
        String string = bundle2.getString("domain_hint");
        if (!TextUtils.isEmpty(string)) {
            bundle.putString("domain_hint", string);
        }
        String string2 = bundle2.getString("ab_federated_auth");
        if (!TextUtils.isEmpty(string2)) {
            bundle.putString("ab_federated_auth", string2);
        }
        String string3 = bundle2.getString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB);
        if (TextUtils.isEmpty(string3)) {
            return;
        }
        bundle.putString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB, string3);
    }

    @FireOsSdk
    public static final boolean isInterceptableUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.contains("/ap/signin") || str.contains("/ap/register");
        }
        return false;
    }

    @FireOsSdk
    public String getAccount() {
        return this.av.getAccount();
    }

    @FireOsSdk
    public String getActor() {
        return null;
    }

    @FireOsSdk
    public Bundle getOptions() {
        return new Bundle();
    }

    @FireOsSdk
    public final boolean handleAuthentication(WebView webView, String str, Activity activity) {
        return handleAuthentication(webView, str, activity, null);
    }

    protected void handleSSLError(SslError sslError) {
        String str = "SSL error: " + sslError.toString();
        onEvent(ON_UNABLE_TO_GET_COOKIES, hf.a(MAPError.CommonError.NETWORK_ERROR, str, MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), str));
    }

    public boolean isFederatedAuthentication() {
        return this.gm.isFederatedAuthentication;
    }

    @FireOsSdk
    public void onEvent(String str, Bundle bundle) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder("Event response received: ");
        sb.append(str);
        sb.append(" Result: ");
        sb.append(bundle.toString());
        io.dm(str2);
    }

    @FireOsSdk
    public boolean skipCookieRefreshPostRegistration() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00fd A[Catch: MalformedURLException -> 0x025e, InvalidParameterException -> 0x0293, TryCatch #2 {MalformedURLException -> 0x025e, InvalidParameterException -> 0x0293, blocks: (B:6:0x0021, B:8:0x002f, B:10:0x0035, B:16:0x0049, B:18:0x005d, B:20:0x00d1, B:22:0x00d9, B:25:0x00e4, B:27:0x00ee, B:32:0x00fd, B:40:0x0120, B:42:0x0134, B:44:0x0144, B:34:0x0109, B:38:0x0114, B:39:0x011e, B:62:0x0253, B:63:0x025d), top: B:70:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0134 A[Catch: MalformedURLException -> 0x025e, InvalidParameterException -> 0x0293, TryCatch #2 {MalformedURLException -> 0x025e, InvalidParameterException -> 0x0293, blocks: (B:6:0x0021, B:8:0x002f, B:10:0x0035, B:16:0x0049, B:18:0x005d, B:20:0x00d1, B:22:0x00d9, B:25:0x00e4, B:27:0x00ee, B:32:0x00fd, B:40:0x0120, B:42:0x0134, B:44:0x0144, B:34:0x0109, B:38:0x0114, B:39:0x011e, B:62:0x0253, B:63:0x025d), top: B:70:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0181  */
    @com.amazon.fireos.sdk.annotations.FireOsSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean handleAuthentication(final android.webkit.WebView r19, final java.lang.String r20, final android.app.Activity r21, final com.amazon.identity.auth.device.api.MAPAndroidWebViewNavigator r22) {
        /*
            Method dump skipped, instructions count: 694
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.handleAuthentication(android.webkit.WebView, java.lang.String, android.app.Activity, com.amazon.identity.auth.device.api.MAPAndroidWebViewNavigator):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, Callback callback, OpenIdRequest.REQUEST_TYPE request_type) {
        if (activity != null) {
            Bundle bundle = new Bundle();
            bundle.putString("com.amazon.identity.ap.assoc_handle", this.gm.associationHandle);
            bundle.putString("com.amazon.identity.ap.pageid", this.gm.pageId);
            bundle.putString("com.amazon.identity.ap.clientContext", this.gm.clientContext);
            bundle.putString("com.amazon.identity.ap.domain", this.gm.domain);
            bundle.putBundle(MAPAccountManager.AuthPortalOptions.KEY_ADDITIONAL_SIGN_IN_PARAMETERS, this.gm.additionalSignInParams);
            bundle.putString(NotificationConstants.REQUEST_TYPE, request_type.toString());
            bundle.putAll(getOptions());
            e(bundle, this.gm.additionalSignInParams);
            f(bundle, this.gm.additionalSignInParams);
            this.av.registerAccountWithUI(activity, request_type == OpenIdRequest.REQUEST_TYPE.REGISTER ? SigninOption.WebviewCreateAccount : SigninOption.WebviewSignin, bundle, callback);
            return;
        }
        throw new InvalidParameterException("Activity object must not be null");
    }

    @FireOsSdk
    public MAPAndroidWebViewHelper(Activity activity) {
        in.a(activity, "Activity");
        this.mActivity = activity;
        this.mContext = activity.getBaseContext().getApplicationContext();
        this.av = new MAPAccountManager(this.mContext);
        this.au = new TokenManagement(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WebView webView, Bundle bundle, String str, String str2, MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        onEvent(ON_REGISTRATION_SUCCESS, bundle);
        io.i(TAG, "Registration successful. Starting get cookies.");
        if (!skipCookieRefreshPostRegistration()) {
            a(webView, false, bundle.getString("com.amazon.dcp.sso.property.account.acctId"), str, this.gm.domain, str2, new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.2
                @Override // com.amazon.identity.auth.device.api.Callback
                public void onError(Bundle bundle2) {
                    MAPAndroidWebViewHelper.this.onEvent(MAPAndroidWebViewHelper.ON_UNABLE_TO_GET_COOKIES, bundle2);
                }

                @Override // com.amazon.identity.auth.device.api.Callback
                public void onSuccess(Bundle bundle2) {
                    io.i(MAPAndroidWebViewHelper.TAG, "Successfully registered account, set cookies in WebView, and loaded return_to url");
                }
            }, mAPAndroidWebViewNavigator);
            return;
        }
        io.i(TAG, "Skipped the cookie refresh, loading the returnToURL");
        a(webView, c(bundle.getBundle(MAPAccountManager.KEY_ADDITIONAL_RETURN_TO_URL_PARAMETERS), this.gm.returnToURL), mAPAndroidWebViewNavigator);
    }

    private void a(WebView webView, boolean z, String str, String str2, String str3, String str4, Callback callback, MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, z);
        bundle.putString("domain", str3);
        bundle.putString(AccountManagerConstants.GetCookiesParams.USER_AGENT, this.gm.userAgent);
        bundle.putString(CookieKeys.Options.KEY_SIGN_IN_URL, str4);
        Callback a = a(str, webView, callback, mAPAndroidWebViewNavigator);
        if (TextUtils.isEmpty(str2)) {
            io.dm(TAG);
            this.au.getCookies(str, str3, bundle, a);
            return;
        }
        io.dm(TAG);
        this.au.getCookiesForActor(str, str2, str3, bundle, a);
    }

    private Bundle a(UrlQuerySanitizer urlQuerySanitizer) {
        Bundle bundle = new Bundle();
        for (UrlQuerySanitizer.ParameterValuePair parameterValuePair : urlQuerySanitizer.getParameterList()) {
            bundle.putString(parameterValuePair.mParameter, parameterValuePair.mValue);
        }
        return bundle;
    }

    private static void a(final WebView webView, final String str, final MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = TAG;
            "Loading ReturnTo Url: ".concat(String.valueOf(str));
            io.dm(str2);
            ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.3
                @Override // java.lang.Runnable
                public void run() {
                    MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator2 = MAPAndroidWebViewNavigator.this;
                    if (mAPAndroidWebViewNavigator2 == null) {
                        webView.loadUrl(str);
                    } else {
                        mAPAndroidWebViewNavigator2.navigate(webView, str);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callback a(final WebView webView, final String str, final String str2, final MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        return new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.4
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                MAPAndroidWebViewHelper.b(MAPAndroidWebViewHelper.this, webView, bundle, str, str2, mAPAndroidWebViewNavigator);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                MAPAndroidWebViewHelper.this.a(webView, bundle, str, str2, mAPAndroidWebViewNavigator);
            }
        };
    }

    private Callback a(final String str, final WebView webView, final Callback callback, final MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        return new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.5
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                callback.onError(bundle);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                String[] stringArray = bundle.getStringArray(CookieKeys.KEY_COOKIES);
                String aG = MAPAndroidWebViewHelper.this.aG(bundle.getString(CookieKeys.KEY_RESPONSE_URL));
                if (stringArray.length != 0) {
                    if (!URLUtil.isHttpsUrl(aG)) {
                        mq.b("MAPWebviewWarning:ReturnToURLNotHTTPS", new String[0]);
                        io.w(MAPAndroidWebViewHelper.TAG, "The return_to url is not HTTPS protocol, which is not encouraged and will not be supported by Android in the future. Please make sure your return_to url is using HTTPS protocol.");
                    }
                    GeneratedOutlineSupport1.outline161(aG, "Set cookies url to ", MAPAndroidWebViewHelper.TAG);
                    MAPAndroidWebViewHelper.a(MAPAndroidWebViewHelper.this, stringArray, aG);
                }
                MAPAndroidWebViewHelper.a(MAPAndroidWebViewHelper.this, webView, aG, mAPAndroidWebViewNavigator);
                callback.onSuccess(bundle);
            }
        };
    }

    static /* synthetic */ void a(MAPAndroidWebViewHelper mAPAndroidWebViewHelper, String[] strArr, String str) {
        jo.a(mAPAndroidWebViewHelper.mContext, str, strArr);
    }

    static /* synthetic */ void a(MAPAndroidWebViewHelper mAPAndroidWebViewHelper, WebView webView, String str, MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        a(webView, mAPAndroidWebViewHelper.aG(str), mAPAndroidWebViewNavigator);
    }

    @FireOsSdk
    public final boolean handleAuthentication(WebView webView, String str) {
        return handleAuthentication(webView, str, this.mActivity);
    }
}
