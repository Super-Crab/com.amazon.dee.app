package com.amazon.identity.auth.device.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.fj;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ji;
import java.net.URI;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ActorEnrollActivity extends MAPWebviewActivityTemplate {
    private String eN;
    private String eO;
    private String eP;
    private fj eQ;

    static /* synthetic */ Bundle b(ActorEnrollActivity actorEnrollActivity) {
        Bundle bundle = new Bundle();
        bundle.putInt("result_code", 1);
        bundle.putString("actor_id", actorEnrollActivity.eQ.ew());
        return bundle;
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected RemoteCallbackWrapper aO() {
        return (RemoteCallbackWrapper) this.fc.getParcelable("callback");
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aP() {
        return "EnrollActivity";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aQ() {
        return "enrollwebviewlayout";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aR() {
        return "enrollwebview";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected void aS() {
        io.i("ActorEnrollActivity", "Initializing params for actor enroll UI Activity");
        this.eN = this.fc.getString("load_url");
        this.eO = this.fc.getString("account_id");
        this.eP = this.fc.getString("return_to_url");
        this.eQ = new fj(this.el, this.er, this.es);
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aT() {
        return this.eN;
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
        io.i("ActorEnrollActivity", "Setting up webview client for actor enroll activity.");
        this.el.setWebViewClient(new WebViewClient() { // from class: com.amazon.identity.auth.device.activity.ActorEnrollActivity.3
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                "Actor enroll onPageStarted: ".concat(String.valueOf(str));
                io.dm("ActorEnrollActivity");
                if (de.a(de.bj(str))) {
                    io.e("ActorEnrollActivity", "Customer canceled the actor enroll flow");
                    ActorEnrollActivity.this.n(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.OPERATION_CANCELLED, "Customer canceled the actor enroll flow", true));
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                io.e("ActorEnrollActivity", "Got an error from the actor enroll webview. Aborting...");
                ActorEnrollActivity.this.n(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, "Got an error from the actor enroll webview. Aborting...", true));
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                ActorEnrollActivity.this.n(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, String.format(Locale.ENGLISH, "SSL Failure in actor enroll. SSL Error code %d.", Integer.valueOf(sslError.getPrimaryError())), false));
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                ActorEnrollActivity.this.av(str);
                if (ActorEnrollActivity.a(ActorEnrollActivity.this, de.bj(str))) {
                    ActorEnrollActivity actorEnrollActivity = ActorEnrollActivity.this;
                    actorEnrollActivity.fe.onSuccess(ActorEnrollActivity.b(actorEnrollActivity));
                    ActorEnrollActivity.this.finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected String aX() {
        return "ActorEnrollActivity_";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    protected void n(final Bundle bundle) {
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.activity.ActorEnrollActivity.2
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle2 = bundle;
                if (bundle2 != null) {
                    ActorEnrollActivity.this.fe.onError(bundle2);
                } else {
                    ActorEnrollActivity.this.fe.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.OPERATION_CANCELLED, "Operation canceled by customer", true));
                }
                ActorEnrollActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate, android.app.Activity
    public void onCreate(Bundle bundle) {
        io.i("ActorEnrollActivity", String.format("Actor enroll webview called in package %s by package %s", getPackageName(), getCallingPackage()));
        super.onCreate(bundle);
        WebView webView = this.el;
        fj fjVar = this.eQ;
        int i = Build.VERSION.SDK_INT;
        webView.addJavascriptInterface(fjVar, "MAPAndroidJSBridge");
        runOnUiThread(new Runnable() { // from class: com.amazon.identity.auth.device.activity.ActorEnrollActivity.1
            @Override // java.lang.Runnable
            public void run() {
                ActorEnrollActivity actorEnrollActivity = ActorEnrollActivity.this;
                actorEnrollActivity.el.loadUrl(actorEnrollActivity.eN);
            }
        });
    }

    static /* synthetic */ boolean a(ActorEnrollActivity actorEnrollActivity, URI uri) {
        URI bj = de.bj(actorEnrollActivity.eP);
        return uri != null && bj != null && uri.getHost().equals(bj.getHost()) && "/ap/maplanding".equals(uri.getPath());
    }
}
