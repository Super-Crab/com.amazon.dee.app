package com.amazon.identity.auth.device.api;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.in;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jl;
import com.amazon.identity.auth.device.mq;
import java.net.URL;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPAndroidWebViewClient extends WebViewClient {
    private final MAPAndroidWebViewHelper gl;

    @FireOsSdk
    public MAPAndroidWebViewClient(MAPAndroidWebViewHelper mAPAndroidWebViewHelper) {
        in.a(mAPAndroidWebViewHelper, "MAPAndroidWebViewHelper");
        this.gl = mAPAndroidWebViewHelper;
    }

    @Override // android.webkit.WebViewClient
    @FireOsSdk
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        io.dm("MAPAndroidWebViewClient");
        if (this.gl.handleAuthentication(webView, str)) {
            return;
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    @FireOsSdk
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        URL dJ;
        new StringBuilder("Got an SSL error:").append(sslError.toString());
        io.dm("MAPAndroidWebViewClient");
        int i = Build.VERSION.SDK_INT;
        if (jl.dJ(sslError.getUrl()) != null) {
            String str = dJ.getHost() + dJ.getPath();
            io.i("MAPAndroidWebViewClient", "SSL error when hitting ".concat(String.valueOf(str)));
            mq.incrementCounterAndRecord("MAPWebViewSSLError_".concat(String.valueOf(str)), new String[0]);
        }
        int i2 = Build.VERSION.SDK_INT;
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    @Override // android.webkit.WebViewClient
    @FireOsSdk
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!webView.getSettings().getJavaScriptEnabled()) {
            io.e("MAPAndroidWebViewClient", "App disabled the JavaScript on WebView: " + webView.getContext().getPackageName());
            mq.incrementCounterAndRecord("MAPWebViewJavaScriptDisabled:" + webView.getContext().getPackageName(), new String[0]);
        }
        io.dm("MAPAndroidWebViewClient");
        return this.gl.handleAuthentication(webView, str);
    }

    @FireOsSdk
    public MAPAndroidWebViewClient(Activity activity) {
        in.a(activity, "Activity");
        this.gl = new MAPAndroidWebViewHelper(activity);
    }
}
