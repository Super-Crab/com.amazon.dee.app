package com.amazon.alexa.cantilever;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Tag;
/* loaded from: classes6.dex */
public class HelpWebChromeClient extends WebChromeClient {
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpWebChromeClient.class);
    private final FrameLayout.LayoutParams fullscreenLayoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
    private Activity mActivity;
    @VisibleForTesting
    View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    protected FrameLayout mFullscreenContainer;
    private int mOriginalOrientation;
    private int mOriginalSystemUiVisibility;
    private View mWebView;

    public HelpWebChromeClient(Activity activity, View view) {
        this.mActivity = activity;
        this.mWebView = view;
    }

    private ViewGroup getRootView() {
        return (ViewGroup) this.mActivity.findViewById(16908290);
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        if (this.mCustomView == null) {
            return;
        }
        Lib.Log.i(TAG, "Closing fullscreen client, returning to previous webview");
        getRootView().setVisibility(this.mOriginalSystemUiVisibility);
        this.mActivity.setRequestedOrientation(this.mOriginalOrientation);
        this.mWebView.setVisibility(0);
        this.mCustomView.setVisibility(8);
        getRootView().removeView(this.mCustomView);
        getRootView().setVisibility(0);
        this.mCustomView = null;
        this.mCustomViewCallback.onCustomViewHidden();
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.mCustomView != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        Lib.Log.i(TAG, "Opening fullscreen client");
        this.mCustomView = view;
        this.mCustomViewCallback = customViewCallback;
        this.mOriginalOrientation = this.mActivity.getRequestedOrientation();
        this.mOriginalSystemUiVisibility = getRootView().getSystemUiVisibility();
        if (this.mCustomView.getParent() != null) {
            Lib.Log.i(TAG, "Removing view from parent");
            ((ViewGroup) this.mCustomView.getParent()).removeView(this.mCustomView);
        }
        this.mWebView.setVisibility(8);
        getRootView().setVisibility(0);
        this.mActivity.setRequestedOrientation(0);
        getRootView().addView(this.mCustomView, this.fullscreenLayoutParams);
    }
}
