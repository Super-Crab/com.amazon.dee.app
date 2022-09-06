package com.amazon.dee.app.ui.web;

import android.annotation.SuppressLint;
import android.webkit.WebView;
/* loaded from: classes12.dex */
public class WebViewJavaScriptLoader {
    static final String TAG = "com.amazon.dee.app.ui.web.WebViewJavaScriptLoader";
    final WebView webView;

    public WebViewJavaScriptLoader(WebView webView) {
        this.webView = webView;
    }

    @SuppressLint({"NewApi"})
    public final void loadJavascript(String str) {
        this.webView.evaluateJavascript(str, null);
    }
}
