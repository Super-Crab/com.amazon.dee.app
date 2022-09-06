package com.amazon.dee.app.ui.external;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.dee.app.util.WebUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public class ThirdPartyUIWebViewManager extends ExternalUIWebViewManager {
    protected static final Set<Integer> NON_FATAL_WEBVIEW_ERRORS = new HashSet<Integer>() { // from class: com.amazon.dee.app.ui.external.ThirdPartyUIWebViewManager.1
        {
            add(-1);
            add(-9);
        }
    };
    private String[] hostAllowlist;

    /* renamed from: com.amazon.dee.app.ui.external.ThirdPartyUIWebViewManager$3  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$ui$external$ThirdPartyUIWebViewManager$UrlClassification = new int[UrlClassification.values().length];

        static {
            try {
                $SwitchMap$com$amazon$dee$app$ui$external$ThirdPartyUIWebViewManager$UrlClassification[UrlClassification.MAIN_APP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$external$ThirdPartyUIWebViewManager$UrlClassification[UrlClassification.ALLOWED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$external$ThirdPartyUIWebViewManager$UrlClassification[UrlClassification.BLOCKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum UrlClassification {
        MAIN_APP,
        ALLOWED,
        BLOCKED
    }

    public ThirdPartyUIWebViewManager(ExternalUIActivity externalUIActivity, WebView webView, ArrayList<String> arrayList, ExternalUIViewModel externalUIViewModel) {
        super(externalUIActivity, webView, externalUIViewModel);
        if (arrayList == null) {
            this.hostAllowlist = new String[0];
        } else {
            this.hostAllowlist = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        initialize();
    }

    @Override // com.amazon.dee.app.ui.external.ExternalUIWebViewManager
    protected WebViewClient buildWebViewClient() {
        return new WebViewClient() { // from class: com.amazon.dee.app.ui.external.ThirdPartyUIWebViewManager.2
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                int ordinal = ThirdPartyUIWebViewManager.this.validateUrl(str).ordinal();
                if (ordinal == 0) {
                    webView.stopLoading();
                    ThirdPartyUIWebViewManager.this.getActivity().exitActivity(str);
                } else if (ordinal != 1) {
                    webView.stopLoading();
                    try {
                        ThirdPartyUIWebViewManager.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    } catch (ActivityNotFoundException unused) {
                        ThirdPartyUIWebViewManager.this.getActivity().exitActivity(null);
                    }
                } else {
                    super.onPageStarted(webView, str, bitmap);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                String str3 = ExternalUIWebViewManager.TAG;
                StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Couldn't load: ", str2, " : ", str, " Code=");
                outline116.append(i);
                Log.e(str3, outline116.toString());
                if (!ThirdPartyUIWebViewManager.this.isSpotifyNonFatalErrors(webView.getOriginalUrl(), i)) {
                    webView.loadUrl(WebUtils.ABOUT_BLANK_PAGE);
                    Bundle bundle = new Bundle();
                    bundle.putInt(ExternalUIActivity.EXTERNAL_URL_LOAD_ERROR_CODE, i);
                    bundle.putString(ExternalUIActivity.EXTERNAL_URL_LOAD_RESULT_DESCRIPTION, str);
                    ThirdPartyUIWebViewManager.this.getActivity().setActivityResultData(bundle);
                    ThirdPartyUIWebViewManager.this.getActivity().tellUserToConnectToNetwork();
                }
            }

            @Override // android.webkit.WebViewClient
            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if (webResourceRequest.isForMainFrame() && ThirdPartyUIWebViewManager.this.validateUrl(webResourceRequest.getUrl().toString()) == UrlClassification.BLOCKED) {
                    ThirdPartyUIWebViewManager.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", webResourceRequest.getUrl()));
                    return true;
                }
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }

            @Override // android.webkit.WebViewClient
            @TargetApi(23)
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
            }
        };
    }

    protected boolean isSpotifyNonFatalErrors(String str, int i) {
        return str != null && str.toLowerCase().contains("spotify.com") && NON_FATAL_WEBVIEW_ERRORS.contains(Integer.valueOf(i));
    }

    public UrlClassification validateUrl(String str) {
        if (getViewModel().isWithinWebAppUrl(str)) {
            return UrlClassification.MAIN_APP;
        }
        if (!getViewModel().isWithinAlexaWebViewNonIndex(str) && !getViewModel().isWithinHostList(str, this.hostAllowlist)) {
            return UrlClassification.BLOCKED;
        }
        return UrlClassification.ALLOWED;
    }
}
