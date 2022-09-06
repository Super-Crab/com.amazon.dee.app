package com.amazon.dee.app.ui.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.dee.app.BuildConfig;
import com.amazon.dee.app.dependencies.WebComponent;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.util.WebUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class AlexaWebView extends WebView {
    static final String TAG = AlexaWebView.class.getSimpleName();
    static boolean webpageIsLoading = false;
    @Inject
    Lazy<CertificateReaderService> certificateReaderService;
    @Inject
    MetricsService metricsService;
    ArrayList<OnPageFinishedListener> onPageFinishedListeners;
    LinkedList<Runnable> pendingTasks;
    ResourceInterceptor resourceInterceptor;
    @VisibleForTesting
    WebApp webApp;
    WebClient webClient;
    WebDelegate webDelegate;
    WebDelegateProxy webDelegateProxy;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class DopplerRequestInterceptor implements ResourceInterceptor {
        private static final String BODY_HEADER = "post-body";
        WebApp webApp;

        DopplerRequestInterceptor(WebApp webApp) {
            this.webApp = webApp;
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.ResourceInterceptor
        public WebResourceResponse interceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (!shouldInterceptRequest(webView, webResourceRequest)) {
                return null;
            }
            Map<String, String> requestHeaders = webResourceRequest.getRequestHeaders();
            Response makeRequestToDoppler = this.webApp.makeRequestToDoppler(webResourceRequest.getMethod(), webResourceRequest.getUrl(), requestHeaders, requestHeaders.get(BODY_HEADER));
            if ("OPTIONS".equals(webResourceRequest.getMethod())) {
                return WebUtils.okHttpToPreflightResponse(webResourceRequest, makeRequestToDoppler, this.webApp);
            }
            return WebUtils.okHttpToWebResponse(makeRequestToDoppler);
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.ResourceInterceptor
        public boolean shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            Uri url = webResourceRequest.getUrl();
            return WebUtils.isDopplerHost(url.getHost()) && url.getScheme().equals("https");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class HttpsSupportInjector implements OnPageFinishedListener {
        WebApp webApp;

        HttpsSupportInjector(WebApp webApp) {
            this.webApp = webApp;
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.OnPageFinishedListener
        public void onPageFinished(String str) {
            this.webApp.injectHttpsSupport();
        }
    }

    /* loaded from: classes12.dex */
    public interface OnPageFinishedListener {
        void onPageFinished(String str);
    }

    /* loaded from: classes12.dex */
    public interface ResourceInterceptor {
        WebResourceResponse interceptRequest(WebView webView, WebResourceRequest webResourceRequest);

        boolean shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class UserAgentBuilder {
        private final String value;

        UserAgentBuilder(WebSettings webSettings) {
            this.value = webSettings.getUserAgentString();
        }

        static String formatEntry(String str, String str2) {
            return GeneratedOutlineSupport1.outline77("[", str, Config.Compare.EQUAL_TO, str2, "]");
        }

        public String build() {
            return this.value + " PitanguiBridge/" + BuildConfig.VERSION_NAME + '-' + formatEntry("MANUFACTURER", Build.MANUFACTURER) + formatEntry("RELEASE", Build.VERSION.RELEASE) + formatEntry("BRAND", Build.BRAND) + formatEntry("SDK", String.valueOf(Build.VERSION.SDK_INT)) + formatEntry("MODEL", Build.MODEL);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class WebChromeClient extends android.webkit.WebChromeClient {
        private boolean triggersAlreadyInjected = false;
        WebApp webApp;

        WebChromeClient(WebApp webApp) {
            this.webApp = webApp;
        }

        private void injectBackboneTriggers() {
            if (this.triggersAlreadyInjected) {
                return;
            }
            this.webApp.injectHttpsSupport();
            this.triggersAlreadyInjected = true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (consoleMessage != null) {
                Log.i(GeneratedOutlineSupport1.outline91(new StringBuilder(), AlexaWebView.TAG, ".console"), "%s:%d %s", consoleMessage.sourceId(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.message());
            }
            return consoleMessage != null;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            this.webApp.execute(str2, jsPromptResult);
            injectBackboneTriggers();
            return true;
        }
    }

    /* loaded from: classes12.dex */
    public interface WebDelegate {
        void login();

        void onHandleIntent(@NonNull Intent intent);

        void setDevices(@Nullable List<DeviceInfo> list);

        void setFullScreen(boolean z);

        void setHeaderTitle(@Nullable CharSequence charSequence);

        void setHeaderVisible(boolean z);

        void setHouseholdLibraries(@Nullable List<HouseholdLibraryInfo> list);

        void setHouseholdVisible(boolean z);

        void setLoading(boolean z);

        void setNowPlaying(boolean z);

        void setSelectedDevice(@Nullable DeviceInfo deviceInfo);

        void setSelectedLibrary(@Nullable HouseholdLibraryInfo householdLibraryInfo);

        void setYourSkillsTitleAndDisplay(@Nullable CharSequence charSequence, boolean z);

        void setYourSkillsVisible(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class WebDelegateProxy implements WebDelegate {
        WebDelegateProxy() {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void login() {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.login();
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void onHandleIntent(@NonNull Intent intent) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.onHandleIntent(intent);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setDevices(List<DeviceInfo> list) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setDevices(list);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setFullScreen(boolean z) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setFullScreen(z);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setHeaderTitle(CharSequence charSequence) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setHeaderTitle(charSequence);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setHeaderVisible(boolean z) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setHeaderVisible(z);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setHouseholdLibraries(@Nullable List<HouseholdLibraryInfo> list) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setHouseholdLibraries(list);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setHouseholdVisible(boolean z) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setHouseholdVisible(z);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setLoading(boolean z) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setLoading(z);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setNowPlaying(boolean z) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setNowPlaying(z);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setSelectedDevice(@Nullable DeviceInfo deviceInfo) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setSelectedDevice(deviceInfo);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setSelectedLibrary(@Nullable HouseholdLibraryInfo householdLibraryInfo) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setSelectedLibrary(householdLibraryInfo);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setYourSkillsTitleAndDisplay(@Nullable CharSequence charSequence, boolean z) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setYourSkillsTitleAndDisplay(charSequence, z);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setYourSkillsVisible(boolean z) {
            WebDelegate webDelegate = AlexaWebView.this.webDelegate;
            if (webDelegate != null) {
                webDelegate.setYourSkillsVisible(z);
            }
        }
    }

    public AlexaWebView(Context context) {
        super(context);
        this.onPageFinishedListeners = new ArrayList<>();
        this.resourceInterceptor = null;
        initialize(context);
    }

    public static boolean getAlexaWebpageLoadingFlag() {
        return webpageIsLoading;
    }

    public void createWebApp() {
        this.webApp.create();
    }

    public void destroyWebApp() {
        this.webApp.destroy();
    }

    void flushPendingTasks() {
        while (true) {
            Runnable poll = this.pendingTasks.poll();
            if (poll != null) {
                poll.run();
            } else {
                return;
            }
        }
    }

    public WebNavigator getWebNavigator() {
        return this.webApp.getWebNavigator();
    }

    void initialize(Context context) {
        Log.i(TAG, "Initializing Webview");
        if (context instanceof Activity) {
            ((AlexaApplication) ((Activity) context).getApplication()).getComponent().inject(this);
        }
        WebView.setWebContentsDebuggingEnabled(BuildConfig.IS_PRODQA_ENVIRONMENT || BuildConfig.IS_BETAQA_ENVIRONMENT);
        setFocusable(true);
        setClickable(true);
        setScrollContainer(true);
        setSaveEnabled(true);
        setScrollBarStyle(33554432);
        setFocusableInTouchMode(true);
        setupSettings(context);
        this.webApp = new WebApp();
        this.pendingTasks = new LinkedList<>();
        this.webDelegateProxy = new WebDelegateProxy();
        this.webClient = new WebClient(this.webDelegateProxy, this.webApp, this.metricsService, this.certificateReaderService) { // from class: com.amazon.dee.app.ui.web.AlexaWebView.1
            @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.clearFocus();
                webView.requestFocus();
                if (!AlexaWebView.this.onPageFinishedListeners.isEmpty()) {
                    Iterator<OnPageFinishedListener> it2 = AlexaWebView.this.onPageFinishedListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onPageFinished(str);
                    }
                }
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(@NonNull WebView webView, @NonNull WebResourceRequest webResourceRequest) {
                ResourceInterceptor resourceInterceptor = AlexaWebView.this.resourceInterceptor;
                if (resourceInterceptor != null) {
                    return resourceInterceptor.interceptRequest(webView, webResourceRequest);
                }
                return null;
            }
        };
        addJavascriptInterface(this.webApp, "webAppFromNative");
        setWebViewClient(this.webClient);
        setWebChromeClient(new WebChromeClient(this.webApp));
        setOnPageFinishedListener(new HttpsSupportInjector(this.webApp));
        setURLInterceptor(new DopplerRequestInterceptor(this.webApp));
    }

    public /* synthetic */ void lambda$selectDevice$0$AlexaWebView(DeviceInfo deviceInfo) {
        this.webApp.selectDevice(deviceInfo);
    }

    public /* synthetic */ void lambda$selectLibrary$1$AlexaWebView(HouseholdLibraryInfo householdLibraryInfo) {
        this.webApp.selectLibrary(householdLibraryInfo);
    }

    public void notifyLoadingDialogDismissed() {
        this.webDelegate.setLoading(false);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.webApp.onActivityResult(i, i2, intent);
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        flushPendingTasks();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.webApp.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("super"));
            this.webClient.restoreState(bundle.getBundle("webClient"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.webkit.WebView
    public void onResume() {
        final WebApp webApp = this.webApp;
        webApp.getClass();
        postWithinLifecycle(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$b9AJGa8IMpvK9S_eSXWcuIusczQ
            @Override // java.lang.Runnable
            public final void run() {
                WebApp.this.notifyResumed();
            }
        });
        String url = getUrl();
        if (url == null || !url.startsWith("https://tunein.com") || !this.webApp.isOutsideWebAppUrl(url) || !canGoBack()) {
            return;
        }
        String str = "navigating back from " + url;
        goBack();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", super.onSaveInstanceState());
        bundle.putBundle("webClient", this.webClient.saveState());
        return bundle;
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if ((action == 0 || action == 1) && !hasFocus()) {
            requestFocus();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onWebAppReady() {
    }

    void postWithinLifecycle(Runnable runnable) {
        if (isAttachedToWindow()) {
            runnable.run();
        } else {
            this.pendingTasks.add(runnable);
        }
    }

    public void selectDevice(@NonNull final DeviceInfo deviceInfo) {
        postWithinLifecycle(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AlexaWebView$GN_8OZ_W8h2CSLG-FGqzY2oofw8
            @Override // java.lang.Runnable
            public final void run() {
                AlexaWebView.this.lambda$selectDevice$0$AlexaWebView(deviceInfo);
            }
        });
    }

    public void selectLibrary(@NonNull final HouseholdLibraryInfo householdLibraryInfo) {
        postWithinLifecycle(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AlexaWebView$ba0zY9tpbwFqD31vQHFz80IJO6o
            @Override // java.lang.Runnable
            public final void run() {
                AlexaWebView.this.lambda$selectLibrary$1$AlexaWebView(householdLibraryInfo);
            }
        });
    }

    public void setDevices(@Nullable List<DeviceInfo> list) {
        this.webDelegateProxy.setDevices(list);
    }

    public void setFullScreen(boolean z) {
        this.webDelegateProxy.setFullScreen(z);
    }

    public void setHeaderTitle(@Nullable CharSequence charSequence) {
        this.webDelegateProxy.setHeaderTitle(charSequence);
    }

    public void setHeaderVisible(boolean z) {
        this.webDelegateProxy.setHeaderVisible(z);
    }

    public void setHouseholdLibraries(@Nullable List<HouseholdLibraryInfo> list) {
        this.webDelegateProxy.setHouseholdLibraries(list);
    }

    public void setHouseholdVisible(boolean z) {
        this.webDelegateProxy.setHouseholdVisible(z);
    }

    public void setNowPlaying(boolean z) {
        this.webDelegateProxy.setNowPlaying(z);
    }

    public void setOnPageFinishedListener(@Nullable OnPageFinishedListener onPageFinishedListener) {
        if (onPageFinishedListener == null) {
            this.onPageFinishedListeners.clear();
        } else {
            this.onPageFinishedListeners.add(onPageFinishedListener);
        }
    }

    public void setSelectedDevice(@Nullable DeviceInfo deviceInfo) {
        this.webDelegateProxy.setSelectedDevice(deviceInfo);
    }

    public void setSelectedLibrary(@Nullable HouseholdLibraryInfo householdLibraryInfo) {
        this.webDelegateProxy.setSelectedLibrary(householdLibraryInfo);
    }

    public void setURLInterceptor(@Nullable ResourceInterceptor resourceInterceptor) {
        this.resourceInterceptor = resourceInterceptor;
    }

    public void setWebComponent(@NonNull WebComponent webComponent) {
        webComponent.inject(this.webApp);
    }

    public void setWebDelegate(WebDelegate webDelegate) {
        this.webDelegate = webDelegate;
    }

    public void setYourSkillsTitleAndDisplay(@Nullable CharSequence charSequence, boolean z) {
        this.webDelegateProxy.setYourSkillsTitleAndDisplay(charSequence, z);
    }

    public void setYourSkillsVisible(boolean z) {
        this.webDelegateProxy.setYourSkillsVisible(z);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    void setupSettings(Context context) {
        WebSettings settings = getSettings();
        String build = new UserAgentBuilder(settings).build();
        String str = "Setting user agent: " + build;
        settings.setUserAgentString(build);
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        String absolutePath = context.getCacheDir().getAbsolutePath();
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(-1);
        settings.setAppCachePath(absolutePath);
    }

    public AlexaWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onPageFinishedListeners = new ArrayList<>();
        this.resourceInterceptor = null;
        initialize(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class WebClient extends WebViewClient {
        private final Lazy<CertificateReaderService> certificateReaderService;
        private final MetricsService metricsService;
        boolean recordTimerFlag = false;
        boolean signingIn;
        boolean warmSeatAuthInProgress;
        private final WebApp webApp;
        private final WebDelegate webDelegate;

        WebClient(WebDelegate webDelegate, WebApp webApp, MetricsService metricsService, Lazy<CertificateReaderService> lazy) {
            this.webDelegate = webDelegate;
            this.webApp = webApp;
            this.metricsService = metricsService;
            this.certificateReaderService = lazy;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            String str2 = AlexaWebView.TAG;
            String str3 = "onPageFinished(" + str + ")";
            if (this.webApp.isWithinWebAppUrl(str)) {
                this.signingIn = false;
            }
            if (this.warmSeatAuthInProgress) {
                this.webDelegate.setLoading(false);
            }
            super.onPageFinished(webView, str);
            recordTimer();
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            String str2 = AlexaWebView.TAG;
            String str3 = "onPageStarted(" + str + ")";
            if (WebUtils.ABOUT_BLANK_PAGE.equals(str)) {
                String str4 = AlexaWebView.TAG;
                return;
            }
            boolean z = false;
            if (this.warmSeatAuthInProgress) {
                if (this.webApp.isWebAppUrl(str)) {
                    String str5 = AlexaWebView.TAG;
                    this.warmSeatAuthInProgress = false;
                }
                z = true;
            } else if (this.webApp.isWebWarmSeatUrl(str)) {
                String str6 = AlexaWebView.TAG;
                GeneratedOutlineSupport1.outline158("MYH warm seat URL detected. url: ", str);
                this.warmSeatAuthInProgress = true;
                z = true;
            }
            if (!z) {
                if (this.webApp.isOutsideWebAppUrl(str)) {
                    webView.stopLoading();
                    String str7 = AlexaWebView.TAG;
                    String str8 = "Outside URL, Opening (" + str + ").";
                    this.webDelegate.onHandleIntent(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } else if (this.webApp.isWebSigninUrl(str)) {
                    String str9 = AlexaWebView.TAG;
                    String str10 = "Treating (" + str + ") as a sign in URL";
                    if (!this.signingIn) {
                        String str11 = AlexaWebView.TAG;
                        this.signingIn = true;
                        this.webDelegate.login();
                    }
                    webView.stopLoading();
                } else {
                    z = true;
                }
            }
            if (z) {
                this.webDelegate.setLoading(true);
                super.onPageStarted(webView, str, bitmap);
            }
            AlexaWebView.webpageIsLoading = true;
            startTimer();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            String str3 = AlexaWebView.TAG;
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Couldn't load: ", str2, " : ", str, " Code=");
            outline116.append(i);
            Log.e(str3, outline116.toString());
            webView.loadUrl(WebUtils.ABOUT_BLANK_PAGE);
            if (this.webApp.isOutsideWebAppUrl(str2)) {
                Log.e(AlexaWebView.TAG, "failingUrl is outside app");
                return;
            }
            String str4 = AlexaWebView.TAG;
            Log.e(str4, "failingUrl is inside app " + str2);
            this.webApp.alertNoNetworkAvailable(i, str2);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            Uri url = webResourceRequest.getUrl();
            if (webResourceRequest.isForMainFrame()) {
                this.webApp.recordHttpError(url.toString(), webResourceResponse.getStatusCode());
                return;
            }
            String lastPathSegment = url.getLastPathSegment();
            if (lastPathSegment == null) {
                return;
            }
            if (!lastPathSegment.endsWith("js") && !WebUtils.isAnXHRRequest(lastPathSegment)) {
                return;
            }
            this.webApp.recordHttpError(lastPathSegment, webResourceResponse.getStatusCode());
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (!BuildConfig.IS_LOCAL_ENVIRONMENT && !BuildConfig.IS_GAMMA_ENVIRONMENT && !this.certificateReaderService.mo358get().isDebugSigned()) {
                sslErrorHandler.cancel();
                return;
            }
            Log.w(AlexaWebView.TAG, "App is on local environment, ignoring SSL error.");
            sslErrorHandler.proceed();
        }

        public void recordTimer() {
            MetricsService metricsService;
            if (this.recordTimerFlag || (metricsService = this.metricsService) == null) {
                return;
            }
            metricsService.recordTimer(AlexaMetricsConstants.MetricEvents.ALEXAWEBVIEW_START_TO_COMPLETE_TIMER);
            this.recordTimerFlag = true;
        }

        public void restoreState(Bundle bundle) {
            this.warmSeatAuthInProgress = bundle.getBoolean("warmSeatAuthInProgress");
            this.signingIn = bundle.getBoolean("signingIn");
        }

        public Bundle saveState() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("warmSeatAuthInProgress", this.warmSeatAuthInProgress);
            bundle.putBoolean("signingIn", this.signingIn);
            return bundle;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String str2 = AlexaWebView.TAG;
            String str3 = "shouldOverrideUrlLoading(" + str + ")";
            Uri parse = Uri.parse(str);
            if (!"geo".equals(parse.getScheme()) && !"tel".equals(parse.getScheme())) {
                return !this.webApp.isOutsideWebAppUrl(str) && super.shouldOverrideUrlLoading(webView, str);
            }
            this.webDelegate.onHandleIntent(new Intent("android.intent.action.VIEW", parse));
            return true;
        }

        public void startTimer() {
            MetricsService metricsService = this.metricsService;
            if (metricsService != null) {
                metricsService.startTimer(AlexaMetricsConstants.MetricEvents.ALEXAWEBVIEW_START_TO_COMPLETE_TIMER, AlexaMetricsConstants.MetricsComponents.PREFETCH, null);
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            Uri url = webResourceRequest.getUrl();
            if (webResourceRequest.isForMainFrame()) {
                onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), url.toString());
                this.webApp.recordResourceError(url.toString(), webResourceError.getErrorCode());
                return;
            }
            String lastPathSegment = url.getLastPathSegment();
            if (lastPathSegment == null) {
                return;
            }
            if (!lastPathSegment.endsWith("js") && !WebUtils.isAnXHRRequest(lastPathSegment)) {
                return;
            }
            this.webApp.recordResourceError(lastPathSegment, webResourceError.getErrorCode());
        }
    }

    public AlexaWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onPageFinishedListeners = new ArrayList<>();
        this.resourceInterceptor = null;
        initialize(context);
    }
}
