package com.amazon.dee.app.ui.external;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.BuildConfig;
import com.amazon.dee.app.ui.util.MissingBrowserDialog;
import com.amazon.dee.app.util.WebUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public class ExternalUIWebViewManager {
    static final String[] EXTERNAL_ACTIVITY_LAUNCHING_SCHEMES = {"mailto", "sms"};
    private static final Set<Integer> NON_FATAL_WEBVIEW_ERRORS = new HashSet<Integer>() { // from class: com.amazon.dee.app.ui.external.ExternalUIWebViewManager.1
        {
            add(-1);
        }
    };
    protected static final String TAG = "com.amazon.dee.app.ui.external.ExternalUIWebViewManager";
    private boolean continueOnNonFatalErrors;
    final ExternalUIActivity mActivity;
    String mUrlRegex;
    final WebView mWebView;
    final ExternalUIViewModel viewModel;

    /* JADX INFO: Access modifiers changed from: protected */
    public ExternalUIWebViewManager(ExternalUIActivity externalUIActivity, WebView webView, ExternalUIViewModel externalUIViewModel) {
        this.continueOnNonFatalErrors = false;
        this.mActivity = externalUIActivity;
        this.mWebView = webView;
        this.viewModel = externalUIViewModel;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void setupWebSettings(WebSettings webSettings) {
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " " + this.viewModel.getUserAgentString());
        webSettings.setAllowFileAccess(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSaveFormData(false);
        webSettings.setDomStorageEnabled(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    protected WebViewClient buildWebViewClient() {
        return new WebViewClient() { // from class: com.amazon.dee.app.ui.external.ExternalUIWebViewManager.2
            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                String str3 = ExternalUIWebViewManager.TAG;
                StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Couldn't load: ", str2, " : ", str, " Code=");
                outline116.append(i);
                Log.e(str3, outline116.toString());
                if (!ExternalUIWebViewManager.this.shouldContinueOnNonFatalError(webView.getOriginalUrl(), i)) {
                    webView.loadUrl(WebUtils.ABOUT_BLANK_PAGE);
                    Bundle bundle = new Bundle();
                    bundle.putInt(ExternalUIActivity.EXTERNAL_URL_LOAD_ERROR_CODE, i);
                    bundle.putString(ExternalUIActivity.EXTERNAL_URL_LOAD_RESULT_DESCRIPTION, str);
                    ExternalUIWebViewManager.this.getActivity().setActivityResultData(bundle);
                    ExternalUIWebViewManager.this.getActivity().tellUserToConnectToNetwork();
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                if (BuildConfig.IS_PROD_ENVIRONMENT) {
                    super.onReceivedSslError(webView, sslErrorHandler, sslError);
                    sslErrorHandler.cancel();
                    return;
                }
                Log.w(ExternalUIWebViewManager.TAG, "App stage is non-prod, ignoring SSL error.");
                sslErrorHandler.proceed();
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                ExternalUIWebViewManager externalUIWebViewManager = ExternalUIWebViewManager.this;
                if (externalUIWebViewManager.viewModel.isWithinExternalUIWebView(str, externalUIWebViewManager.mUrlRegex)) {
                    String str2 = ExternalUIWebViewManager.TAG;
                    String str3 = "url isWithinExternalWebView " + str;
                    Uri parse = Uri.parse(str);
                    if (!ExternalUIWebViewManager.this.isExternalActivityLaunchingUri(parse)) {
                        return false;
                    }
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(parse);
                    try {
                        ExternalUIWebViewManager.this.mActivity.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        Log.e(ExternalUIWebViewManager.TAG, "Caught ActivityNotFoundException while opening external app");
                    }
                    return true;
                }
                if (ExternalUIWebViewManager.this.viewModel.isWithinWebAppUrl(str)) {
                    String str4 = ExternalUIWebViewManager.TAG;
                    GeneratedOutlineSupport1.outline158("url isWithinAlexaWebView ", str);
                    ExternalUIWebViewManager.this.mActivity.exitActivity(str);
                } else {
                    String str5 = ExternalUIWebViewManager.TAG;
                    try {
                        ExternalUIWebViewManager.this.mActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    } catch (ActivityNotFoundException unused2) {
                        Log.e(ExternalUIWebViewManager.TAG, "Caught ActivityNotFoundException while opening external url");
                        MissingBrowserDialog.show(ExternalUIWebViewManager.this.mActivity);
                    }
                }
                return true;
            }

            @Override // android.webkit.WebViewClient
            @TargetApi(23)
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
            }
        };
    }

    public ExternalUIActivity getActivity() {
        return this.mActivity;
    }

    public boolean getContinueOnNonFatalErrors() {
        return this.continueOnNonFatalErrors;
    }

    public ExternalUIViewModel getViewModel() {
        return this.viewModel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initialize() {
        this.mWebView.setSaveEnabled(true);
        this.mWebView.setScrollBarStyle(33554432);
        setupWebSettings(this.mWebView.getSettings());
        this.mWebView.setWebViewClient(buildWebViewClient());
        this.mWebView.setWebChromeClient(new WebChromeClient());
    }

    @VisibleForTesting
    boolean isExternalActivityLaunchingUri(Uri uri) {
        String scheme = uri.getScheme();
        if (scheme != null) {
            for (String str : EXTERNAL_ACTIVITY_LAUNCHING_SCHEMES) {
                if (scheme.matches(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void loadUrl(String str) {
        this.mWebView.loadUrl(str);
    }

    public void reload() {
        this.mWebView.reload();
    }

    public void setContinueOnNonFatalErrors(boolean z) {
        this.continueOnNonFatalErrors = z;
    }

    public void setDOMStorageEnabled(boolean z) {
        if (this.mWebView.getSettings() != null) {
            this.mWebView.getSettings().setDomStorageEnabled(z);
        }
    }

    @VisibleForTesting
    boolean shouldContinueOnNonFatalError(String str, int i) {
        return str != null && getContinueOnNonFatalErrors() && NON_FATAL_WEBVIEW_ERRORS.contains(Integer.valueOf(i));
    }

    public ExternalUIWebViewManager(ExternalUIActivity externalUIActivity, WebView webView, String str, ExternalUIViewModel externalUIViewModel) {
        this(externalUIActivity, webView, externalUIViewModel);
        this.mUrlRegex = str;
        initialize();
    }
}
