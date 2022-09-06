package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class de extends WebViewClient {
    private final ej bR;
    private final a iZ;
    private final String ja;
    private final Set<String> jb;
    private final boolean jc;
    private final OpenIdRequest.TOKEN_SCOPE jd;
    private final OpenIdRequest.REQUEST_TYPE je;
    private mv jf;
    private mv jg;
    private final MAPSmsReceiver ji;
    private final Context mContext;
    public boolean iY = false;
    private mv jh = null;
    private volatile boolean jj = false;
    private volatile boolean jk = false;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        void aM();

        void aN();

        void ax(String str);

        void ay(String str);

        void b(cm cmVar);

        void o(Bundle bundle);
    }

    public de(Context context, MAPSmsReceiver mAPSmsReceiver, OpenIdRequest.REQUEST_TYPE request_type, String str, OpenIdRequest.TOKEN_SCOPE token_scope, Set<String> set, boolean z, a aVar, ej ejVar) {
        in.a(aVar, "callback");
        this.mContext = context;
        this.ji = mAPSmsReceiver;
        this.ja = str;
        this.iZ = aVar;
        this.jd = token_scope;
        this.je = request_type;
        this.jb = set;
        this.jc = z;
        this.bR = ejVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e8, code lost:
        if (r5 == false) goto L54;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(android.webkit.WebView r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.de.a(android.webkit.WebView, java.lang.String):boolean");
    }

    public static boolean b(String str, Set<String> set) {
        for (String str2 : set) {
            if (str.endsWith(str2)) {
                return true;
            }
            int indexOf = str2.indexOf(".");
            if (indexOf == 0 && str.equals(str2.substring(indexOf + 1, str2.length()))) {
                return true;
            }
        }
        return false;
    }

    private boolean bh(String str) {
        return (str.contains("ap/signin") || str.contains("ap/register")) && !str.contains("openid.assoc_handle");
    }

    private void bi(String str) {
        this.iY = true;
        io.dm("AuthenticationWebViewClient");
        cm cmVar = new cm(str);
        io.dm("AuthenticationWebViewClient");
        String bX = cmVar.bX();
        if (!"device_auth_access".equalsIgnoreCase(cmVar.bZ()) && TextUtils.isEmpty(bX)) {
            mq.incrementCounterAndRecord("WebViewFailure:InvalidScope:" + this.je.name() + ":" + mp.eQ(str), new String[0]);
            String format = String.format("Received token with invalid scope %s and no authorization code", cmVar.bZ());
            this.iZ.o(m.a(MAPError.CommonError.PARSE_ERROR, format, MAPAccountManager.RegistrationError.PARSE_ERROR.value(), format));
        } else if (TextUtils.isEmpty(cmVar.getAccessToken()) && TextUtils.isEmpty(bX)) {
            mq.incrementCounterAndRecord("WebViewFailure:NoAccessTokenAndAuthorizationCode:" + this.je.name() + ":" + mp.eQ(str), new String[0]);
            mq.incrementCounterAndRecord("MAPError:AuthenticationFailed", new String[0]);
            this.iZ.o(m.a(MAPError.AccountError.REGISTER_FAILED, "Sign in failed because the access token is not set in the return_to_url. Please contact the AuthPortal team to understand the reason.", MAPAccountManager.RegistrationError.REGISTER_FAILED.value(), "Received empty access token and authorization code from AP response"));
        } else if (TextUtils.isEmpty(cmVar.getDirectedId())) {
            mq.b("WebViewFailure:NoDirectedID:" + this.je.name() + ":" + mp.eQ(str), new String[0]);
            mq.b("MAPError:AuthenticationFailed", new String[0]);
            this.iZ.o(m.a(MAPError.AccountError.REGISTER_FAILED, "Sign in failed because the directedId is not set in the return_to_url. Please contact the AuthPortal team to understand the reason.", MAPAccountManager.RegistrationError.REGISTER_FAILED.value(), "Registration response received invalid because it did not contain a directed id"));
        } else {
            this.iZ.b(cmVar);
        }
    }

    public static URI bj(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            io.e("AuthenticationWebViewClient", "Exception while trying to parse url in onPageStarted. Continue with page load.", e);
            mq.incrementCounterAndRecord("MAP_URISyntaxException", new String[0]);
            return null;
        }
    }

    public boolean cJ() {
        return this.jj;
    }

    public boolean cK() {
        return this.jk;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        mv mvVar;
        mv mvVar2;
        io.dm("AuthenticationWebViewClient");
        super.onPageFinished(webView, str);
        jo.aJ(this.mContext);
        if (this.jj && (mvVar2 = this.jf) != null) {
            mvVar2.stop();
        }
        if (this.jk && (mvVar = this.jg) != null) {
            mvVar.stop();
        }
        if (!str.startsWith(this.ja) && !this.iY) {
            this.iZ.aM();
            return;
        }
        mv mvVar3 = this.jh;
        if (mvVar3 == null) {
            return;
        }
        mvVar3.stop();
        this.jh = null;
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        boolean z;
        "onPageStarted: ".concat(String.valueOf(str));
        io.dm("AuthenticationWebViewClient");
        new StringBuilder("Before Page Started with scope =").append(this.jd);
        io.dm("AuthenticationWebViewClient");
        if (!this.ji.a(str, this.mContext)) {
            this.ji.L(this.mContext);
        }
        this.iZ.ax(str);
        URI bj = bj(str);
        if (bj == null) {
            z = false;
        } else {
            z = bj.getQuery() == null && (TextUtils.equals("/gp/yourstore/home", bj.getPath()) || TextUtils.equals("/gp/yourstore/home/", bj.getPath()));
            "isCancelEvent : ".concat(String.valueOf(z));
            io.dm("AuthenticationWebViewClient");
        }
        if (z) {
            this.iZ.o(m.b(MAPError.CommonError.OPERATION_CANCELLED, "Registration canceled", 4, "Registration canceled"));
            if (this.bR == null) {
                return;
            }
            if (this.jk) {
                this.bR.bA("DCQCanceled");
            }
            if (!this.jj) {
                return;
            }
            this.bR.bA("MFACanceled");
        } else if (!b(bj)) {
            if (bh(str) && (this.jj || this.jk)) {
                this.iZ.aN();
            }
            if (str.contains("ap/dcq")) {
                ej ejVar = this.bR;
                if (ejVar != null) {
                    ejVar.bA("WebView:ContactedDCQ:" + this.je.name());
                    this.jg = this.bR.bz("DCQ:PageRender");
                }
                this.jk = true;
                this.jj = false;
            } else if (str.contains("ap/mfa")) {
                ej ejVar2 = this.bR;
                if (ejVar2 != null) {
                    ejVar2.bA("WebView:ContactedMFA:" + this.je.name());
                    this.jf = this.bR.bz("MFA:PageRender");
                }
                this.jj = true;
                this.jk = false;
            } else {
                this.jj = false;
                this.jk = false;
            }
        } else {
            bi(str);
            io.w("AuthenticationWebViewClient", "ReturnToUrl is loaded by webview! This shouldn't happen");
            mq.incrementCounterAndRecord("ReturnToUrl_OnPageStarted", new String[0]);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        io.e("AuthenticationWebViewClient", "Got an error from the webview. Returning false for SignIn (" + i + ") " + str);
        SSOMetrics.c(str2, i);
        mq.incrementCounterAndRecord("NetworkError3:AuthenticationWebViewClient", new String[0]);
        this.iZ.o(m.a(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", str), MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), String.format(Locale.ENGLISH, "Received error code %d and description: %s", Integer.valueOf(i), str)));
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        new StringBuilder("Got an SSL error:").append(sslError.toString());
        io.dm("AuthenticationWebViewClient");
        mq.incrementCounterAndRecord("NetworkError5:AuthenticationWebViewClient", new String[0]);
        String format = String.format(Locale.ENGLISH, "SSL Failure. SSL Error code %d.", Integer.valueOf(sslError.getPrimaryError()));
        Bundle a2 = m.a(MAPError.CommonError.NETWORK_ERROR, format, MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), format);
        int i = Build.VERSION.SDK_INT;
        String url = sslError.getUrl();
        URL dJ = jl.dJ(url);
        SSOMetrics.d(url, sslError.getPrimaryError());
        if (dJ != null) {
            String str = dJ.getHost() + dJ.getPath();
            io.e("AuthenticationWebViewClient", "SSL error for: ".concat(String.valueOf(str)));
            mq.incrementCounterAndRecord("MAPWebViewSSLError_".concat(String.valueOf(str)), new String[0]);
            a2.putString(MAPAccountManager.KEY_ERROR_DOMAIN_PATH_WEBVIEW_SSL_ERROR, str);
        }
        a2.putInt(MAPAccountManager.KEY_ERROR_CODE_WEBVIEW_SSL_ERROR, sslError.getPrimaryError());
        this.iZ.o(a2);
    }

    @Override // android.webkit.WebViewClient
    @SuppressLint({"NewApi"})
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        ej ejVar;
        if (bh(str) && this.jh == null && (ejVar = this.bR) != null) {
            this.jh = ejVar.bz("AuthenticationWebViewClient_SignInRegisterPost:" + this.je.name());
        }
        return super.shouldInterceptRequest(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        GeneratedOutlineSupport1.outline161(str, "shouldOverrideUrlLoading: ", "AuthenticationWebViewClient");
        if (a(webView, str)) {
            return true;
        }
        if (!b(bj(str))) {
            return false;
        }
        this.iZ.ax(str);
        bi(str);
        return true;
    }

    public static boolean b(URI uri) {
        return uri != null && TextUtils.equals(uri.getPath(), "/ap/maplanding");
    }

    public static boolean a(URI uri) {
        boolean z = false;
        if (uri == null) {
            return false;
        }
        if (TextUtils.equals("/ap/mapcancel", uri.getPath()) || TextUtils.equals("/ap/mapcancel/", uri.getPath())) {
            z = true;
        }
        "isCancelForActor : ".concat(String.valueOf(z));
        io.dm("AuthenticationWebViewClient");
        return z;
    }
}
