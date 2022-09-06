package com.amazon.alexa.cantilever;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.cantilever.utils.HelpFeatureChecker;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Logger;
import com.amazon.alexa.logging.Tag;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.identity.auth.device.api.MAPAndroidWebViewClient;
import com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes6.dex */
public class HelpWebViewClient extends MAPAndroidWebViewClient {
    private static final int TIMEOUT_SECONDS = 30;
    private final LazyComponent<EnvironmentService> environmentService;
    private final HelpCookiesService helpCookiesService;
    private final HelpFeatureChecker helpFeatureChecker;
    private final HelpSessionService helpSessionService;
    private final HelpWebViewLoadListener helpWebViewLoadListener;
    @VisibleForTesting
    CountDownTimer timeoutTimer;
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpWebViewClient.class);
    private static final String CALLER = HelpWebViewClient.class.getSimpleName();
    private static final List<String> ERROR_URL_ALLOW_LIST = Arrays.asList("/uedata/uedata", "/1/batch/1/OE", "/1/batch/1/OP", "/1/batch/2/OE");

    /* JADX INFO: Access modifiers changed from: package-private */
    public HelpWebViewClient(Context context, HelpSessionService helpSessionService, @NonNull final HelpWebViewLoadListener helpWebViewLoadListener, ComponentRegistry componentRegistry, HelpCookiesService helpCookiesService, HelpFeatureChecker helpFeatureChecker) {
        super(new MAPAndroidWebViewHelper(context));
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.helpSessionService = helpSessionService;
        this.helpWebViewLoadListener = helpWebViewLoadListener;
        this.helpCookiesService = helpCookiesService;
        this.helpFeatureChecker = helpFeatureChecker;
        this.timeoutTimer = new CountDownTimer(30000L, 1000L) { // from class: com.amazon.alexa.cantilever.HelpWebViewClient.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                helpWebViewLoadListener.onTimeout();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }
        };
    }

    private boolean isErrorURLAllowed(String str) {
        if (str == null) {
            return false;
        }
        for (String str2 : ERROR_URL_ALLOW_LIST) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSupportedUrlForForum(String str) {
        if (!this.helpFeatureChecker.hasAmazonForumAccess()) {
            Lib.Log.i(TAG, "Customer does not have forum access");
            return false;
        } else if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            return this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AMAZON_FORUM_RETAIL, str) || this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AMAZON_FORUM, str) || this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AMAZON_FORUM_AUTH_SALESFORCE, str);
        }
    }

    private void startTimeoutTimer() {
        CountDownTimer countDownTimer = this.timeoutTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.timeoutTimer.start();
        }
    }

    private void stopTimeoutTimer() {
        CountDownTimer countDownTimer = this.timeoutTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Logger logger = Lib.Log;
        Tag tag = TAG;
        logger.i(tag, "Web view loaded page: " + str);
        super.onPageFinished(webView, str);
        stopTimeoutTimer();
        this.helpWebViewLoadListener.onWebViewLoaded(str);
        this.helpSessionService.onHelpLoadFinishLanding(str);
    }

    @Override // com.amazon.identity.auth.device.api.MAPAndroidWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Logger logger = Lib.Log;
        Tag tag = TAG;
        logger.i(tag, "Loading request for: " + str);
        super.onPageStarted(webView, str, bitmap);
        this.helpSessionService.resetUnableToLoadFlag();
        this.helpCookiesService.setAmznAppInfoCookie(str);
        startTimeoutTimer();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        Logger logger = Lib.Log;
        Tag tag = TAG;
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Couldn't load: ", str2, " : ", str, " code: ");
        outline116.append(i);
        logger.e(tag, outline116.toString());
        if (!isErrorURLAllowed(str2)) {
            this.helpSessionService.onHelpLoadOtherError(i, GeneratedOutlineSupport1.outline72(str2, str));
        }
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(23)
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        Logger logger = Lib.Log;
        Tag tag = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Couldn't load: ");
        outline107.append(webResourceRequest.getUrl());
        outline107.append(", error code: ");
        outline107.append(webResourceResponse.getStatusCode());
        logger.e(tag, outline107.toString());
        if (!isErrorURLAllowed(webResourceRequest.getUrl().toString())) {
            this.helpSessionService.onHelpLoadHttpError(webResourceResponse.getStatusCode(), webResourceRequest.getUrl().toString());
        }
    }

    @Override // com.amazon.identity.auth.device.api.MAPAndroidWebViewClient, android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Logger logger = Lib.Log;
        Tag tag = TAG;
        logger.e(tag, "Couldn't load due to SSL Error: " + sslError);
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        sslErrorHandler.cancel();
        if (!isErrorURLAllowed(sslError.getUrl().toString())) {
            this.helpSessionService.onHelpLoadOtherError(sslError.getPrimaryError(), sslError.toString());
        }
    }

    @JavascriptInterface
    public void postMessage(String str) {
        Logger logger = Lib.Log;
        Tag tag = TAG;
        logger.i(tag, "JS reported " + str);
        this.helpSessionService.onJsEventSent(str);
    }

    @Override // com.amazon.identity.auth.device.api.MAPAndroidWebViewClient, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.helpWebViewLoadListener.onUrlChanged(str)) {
            Lib.Log.i(TAG, "Overriding url loading for Amazon root redirect.");
            return true;
        } else if (super.shouldOverrideUrlLoading(webView, str)) {
            Logger logger = Lib.Log;
            Tag tag = TAG;
            logger.i(tag, "Overriding Url loading based on super. Url : " + str);
            this.helpSessionService.onMAPOverrideUrlLoading(str);
            webView.setVisibility(8);
            return true;
        } else if (!this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AUTH_PORTAL, str) && !this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.CANTILEVER, str) && !this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.MESSAGE_US, str) && !this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.HMD_SURVEY, str) && !isSupportedUrlForForum(str)) {
            if (this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AMAZON_RETAIL_NON_ROOT, str)) {
                Logger logger2 = Lib.Log;
                Tag tag2 = TAG;
                logger2.i(tag2, "Overriding Url loading for retail website page. Url : " + str);
                return true;
            }
            if (webView.getContext() instanceof Activity) {
                webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
            Logger logger3 = Lib.Log;
            Tag tag3 = TAG;
            logger3.i(tag3, "Overriding Url loading for non-cantilever and non-authportal. Url : " + str);
            return true;
        } else {
            Lib.Log.i(TAG, "Allow web view to continue navigation");
            return false;
        }
    }
}
