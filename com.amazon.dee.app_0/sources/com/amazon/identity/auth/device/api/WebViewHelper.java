package com.amazon.identity.auth.device.api;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import com.amazon.identity.auth.device.fh;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mz;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class WebViewHelper {
    private WebViewHelper() {
    }

    public static boolean enableAmazonAuthenticatorForWebView(WebView webView, Bundle bundle) {
        if (!mz.bo(webView.getContext())) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        if (!webView.getSettings().getJavaScriptEnabled()) {
            io.w("WebViewHelper", webView.getContext().getPackageName() + " disabled the JavaScript on WebView. MAP will enable the JavaScript.");
            StringBuilder sb = new StringBuilder("MAPWebViewJavaScriptOriginallyDisabled:");
            sb.append(webView.getContext().getPackageName());
            mq.incrementCounterAndRecord(sb.toString(), new String[0]);
            webView.getSettings().setJavaScriptEnabled(true);
        }
        webView.addJavascriptInterface(new fh(webView), "FidoAuthenticatorJSBridge");
        return true;
    }
}
