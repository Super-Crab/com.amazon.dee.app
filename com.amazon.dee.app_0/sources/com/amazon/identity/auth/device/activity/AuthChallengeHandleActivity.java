package com.amazon.identity.auth.device.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.fj;
import com.amazon.identity.auth.device.hj;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ji;
import java.net.URI;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class AuthChallengeHandleActivity extends MAPWebviewActivityTemplate {
    private String eO;
    private String eP;
    private String eT;
    private String eU;
    private String eV;
    private String eW;
    private String eX;

    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.activity.AuthChallengeHandleActivity$3  reason: invalid class name */
    /* loaded from: classes12.dex */
    class AnonymousClass3 extends WebViewClient {
        AnonymousClass3() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            "MAP AuthChallenge onPageStarted: ".concat(String.valueOf(str));
            io.dm("AuthChallengeHandleActivity");
            if (de.a(de.bj(str))) {
                io.e("AuthChallengeHandleActivity", "Customer canceled the flow");
                AuthChallengeHandleActivity.this.n(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.OPERATION_CANCELLED, "Customer canceled the flow"));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            io.e("AuthChallengeHandleActivity", "Got an error from the webview. Aborting...");
            AuthChallengeHandleActivity.this.n(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.NETWORK_ERROR, "Got an error from the webview. Aborting..."));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            AuthChallengeHandleActivity.this.n(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.NETWORK_ERROR, String.format(Locale.ENGLISH, "SSL Failure. SSL Error code %d.", Integer.valueOf(sslError.getPrimaryError()))));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            AuthChallengeHandleActivity.this.av(str);
            if (AuthChallengeHandleActivity.a(AuthChallengeHandleActivity.this, de.bj(str))) {
                io.i("AuthChallengeHandleActivity", "Handling return to URL, calling get actor token without FC...");
                hj.ae(AuthChallengeHandleActivity.this.o).a(null, AuthChallengeHandleActivity.this.eO, AuthChallengeHandleActivity.this.eV, AuthChallengeHandleActivity.this.eW, null, null, new Callback() { // from class: com.amazon.identity.auth.device.activity.AuthChallengeHandleActivity.3.1
                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onError(Bundle bundle) {
                        AuthChallengeHandleActivity.this.n(bundle);
                    }

                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onSuccess(final Bundle bundle) {
                        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.activity.AuthChallengeHandleActivity.3.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                io.i("AuthChallengeHandleActivity", "Successfully get actor token with failure context!");
                                AuthChallengeHandleActivity.this.fe.onSuccess(bundle);
                                AuthChallengeHandleActivity.this.finish();
                            }
                        });
                    }
                }, AuthChallengeHandleActivity.this.bR);
                return true;
            }
            return false;
        }
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected RemoteCallbackWrapper aO() {
        return (RemoteCallbackWrapper) this.fc.getParcelable("callback");
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aP() {
        return "AuthChallengeHandleActivity";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aQ() {
        return "authchallengehandleactivitylayout";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aR() {
        return "authchallengehandleactivitywebview";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected void aS() {
        io.i("AuthChallengeHandleActivity", "Initializing params for Auth challenge UI Activity");
        this.eT = this.fc.getBundle("auth_portal_config").getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN);
        this.eU = this.fc.getString("challenge_url");
        this.eO = this.fc.getString("account_id");
        this.eV = this.fc.getString("actor_id");
        this.eW = this.fc.getString("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token");
        this.eX = this.fc.getBundle("auth_portal_config").getString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN);
        this.eP = OpenIdRequest.aX(this.eX);
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aT() {
        return this.eU;
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aU() {
        return this.eO;
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String[] aV() {
        return this.fc.getBundle("actor_cookies_for_request").getStringArray(CookieKeys.KEY_COOKIES);
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected void aW() {
        io.i("AuthChallengeHandleActivity", "Setting up webview client for Auth challenge activity.");
        this.el.setWebViewClient(new AnonymousClass3());
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aX() {
        return "AuthChallengeHandleActivity_";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected void n(final Bundle bundle) {
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.activity.AuthChallengeHandleActivity.2
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle2 = bundle;
                if (bundle2 != null) {
                    AuthChallengeHandleActivity.this.fe.onError(bundle2);
                } else {
                    AuthChallengeHandleActivity.this.fe.onError(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.OPERATION_CANCELLED, "Operation canceled by customer"));
                }
                AuthChallengeHandleActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate, android.app.Activity
    public void onCreate(Bundle bundle) {
        io.i("AuthChallengeHandleActivity", String.format("Auth challenge webview called in package %s by package %s", getPackageName(), getCallingPackage()));
        super.onCreate(bundle);
        WebView webView = this.el;
        webView.addJavascriptInterface(new fj(webView, this.er, this.es), "MAPAndroidJSBridge");
        runOnUiThread(new Runnable() { // from class: com.amazon.identity.auth.device.activity.AuthChallengeHandleActivity.1
            @Override // java.lang.Runnable
            public void run() {
                AuthChallengeHandleActivity authChallengeHandleActivity = AuthChallengeHandleActivity.this;
                authChallengeHandleActivity.el.loadUrl(authChallengeHandleActivity.eU);
            }
        });
    }

    static /* synthetic */ boolean a(AuthChallengeHandleActivity authChallengeHandleActivity, URI uri) {
        URI bj = de.bj(authChallengeHandleActivity.eP);
        return uri != null && bj != null && uri.getHost().equals(bj.getHost()) && "/ap/maplanding".equals(uri.getPath());
    }
}
