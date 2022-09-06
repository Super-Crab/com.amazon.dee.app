package com.reactnativecommunity.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.scroll.OnScrollDispatchHelper;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.reactnativecommunity.webview.RNCWebViewModule;
import com.reactnativecommunity.webview.events.TopHttpErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingFinishEvent;
import com.reactnativecommunity.webview.events.TopLoadingProgressEvent;
import com.reactnativecommunity.webview.events.TopLoadingStartEvent;
import com.reactnativecommunity.webview.events.TopMessageEvent;
import com.reactnativecommunity.webview.events.TopRenderProcessGoneEvent;
import com.reactnativecommunity.webview.events.TopShouldStartLoadWithRequestEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;
@ReactModule(name = "RNCWebView")
/* loaded from: classes3.dex */
public class RNCWebViewManager extends SimpleViewManager<WebView> {
    protected static final String BLANK_URL = "about:blank";
    public static final int COMMAND_CLEAR_CACHE = 1001;
    public static final int COMMAND_CLEAR_FORM_DATA = 1000;
    public static final int COMMAND_CLEAR_HISTORY = 1002;
    public static final int COMMAND_FOCUS = 8;
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_LOAD_URL = 7;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    protected static final String HTML_ENCODING = "UTF-8";
    protected static final String HTML_MIME_TYPE = "text/html";
    protected static final String HTTP_METHOD_POST = "POST";
    protected static final String JAVASCRIPT_INTERFACE = "ReactNativeWebView";
    protected static final String REACT_CLASS = "RNCWebView";
    protected static final int SHOULD_OVERRIDE_URL_LOADING_TIMEOUT = 250;
    private static final String TAG = "RNCWebViewManager";
    protected boolean mAllowsFullscreenVideo;
    @Nullable
    protected String mUserAgent;
    @Nullable
    protected String mUserAgentWithApplicationName;
    protected RNCWebChromeClient mWebChromeClient;
    protected WebViewConfig mWebViewConfig;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static class RNCWebChromeClient extends WebChromeClient implements LifecycleEventListener {
        protected static final FrameLayout.LayoutParams FULLSCREEN_LAYOUT_PARAMS = new FrameLayout.LayoutParams(-1, -1, 17);
        @RequiresApi(api = 19)
        protected static final int FULLSCREEN_SYSTEM_UI_VISIBILITY = 7942;
        protected WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected ReactContext mReactContext;
        protected View mVideoView;
        protected View mWebView;
        protected RNCWebView.ProgressChangedFilter progressChangedFilter = null;

        public RNCWebChromeClient(ReactContext reactContext, WebView webView) {
            this.mReactContext = reactContext;
            this.mWebView = webView;
        }

        protected ViewGroup getRootView() {
            return (ViewGroup) this.mReactContext.getCurrentActivity().findViewById(16908290);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            callback.invoke(str, true, false);
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            int i = Build.VERSION.SDK_INT;
            View view = this.mVideoView;
            if (view == null || view.getSystemUiVisibility() == FULLSCREEN_SYSTEM_UI_VISIBILITY) {
                return;
            }
            this.mVideoView.setSystemUiVisibility(FULLSCREEN_SYSTEM_UI_VISIBILITY);
        }

        @Override // android.webkit.WebChromeClient
        @TargetApi(21)
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            String[] resources = permissionRequest.getResources();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < resources.length; i++) {
                if (resources[i].equals("android.webkit.resource.AUDIO_CAPTURE")) {
                    arrayList.add("android.permission.RECORD_AUDIO");
                } else if (resources[i].equals("android.webkit.resource.VIDEO_CAPTURE")) {
                    arrayList.add("android.permission.CAMERA");
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (ContextCompat.checkSelfPermission(this.mReactContext, (String) arrayList.get(i2)) == 0) {
                    if (((String) arrayList.get(i2)).equals("android.permission.RECORD_AUDIO")) {
                        arrayList2.add("android.webkit.resource.AUDIO_CAPTURE");
                    } else if (((String) arrayList.get(i2)).equals("android.permission.CAMERA")) {
                        arrayList2.add("android.webkit.resource.VIDEO_CAPTURE");
                    }
                }
            }
            if (arrayList2.isEmpty()) {
                permissionRequest.deny();
            } else {
                permissionRequest.grant((String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            String url = webView.getUrl();
            if (this.progressChangedFilter.isWaitingForCommandLoadUrl()) {
                return;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("target", webView.getId());
            createMap.putString("title", webView.getTitle());
            createMap.putString("url", url);
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            createMap.putDouble("progress", i / 100.0f);
            RNCWebViewManager.dispatchEvent(webView, new TopLoadingProgressEvent(webView.getId(), createMap));
        }

        @Override // android.webkit.WebChromeClient
        @TargetApi(21)
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            String[] acceptTypes = fileChooserParams.getAcceptTypes();
            boolean z = true;
            if (fileChooserParams.getMode() != 1) {
                z = false;
            }
            return RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, acceptTypes, z);
        }

        protected void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
            RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, str);
        }

        public void setProgressChangedFilter(RNCWebView.ProgressChangedFilter progressChangedFilter) {
            this.progressChangedFilter = progressChangedFilter;
        }

        protected void openFileChooser(ValueCallback<Uri> valueCallback) {
            RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, "");
        }

        protected void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static class RNCWebView extends WebView implements LifecycleEventListener {
        protected boolean hasScrollEvent;
        @Nullable
        protected String injectedJS;
        @Nullable
        protected String injectedJSBeforeContentLoaded;
        protected boolean injectedJavaScriptBeforeContentLoadedForMainFrameOnly;
        protected boolean injectedJavaScriptForMainFrameOnly;
        @Nullable
        protected CatalystInstance mCatalystInstance;
        private OnScrollDispatchHelper mOnScrollDispatchHelper;
        @Nullable
        protected RNCWebViewClient mRNCWebViewClient;
        WebChromeClient mWebChromeClient;
        protected boolean messagingEnabled;
        @Nullable
        protected String messagingModuleName;
        protected ProgressChangedFilter progressChangedFilter;
        protected boolean sendContentSizeChangeEvents;

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: classes3.dex */
        public static class ProgressChangedFilter {
            private boolean waitingForCommandLoadUrl = false;

            protected ProgressChangedFilter() {
            }

            public boolean isWaitingForCommandLoadUrl() {
                return this.waitingForCommandLoadUrl;
            }

            public void setWaitingForCommandLoadUrl(boolean z) {
                this.waitingForCommandLoadUrl = z;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: classes3.dex */
        public class RNCWebViewBridge {
            RNCWebView mContext;

            RNCWebViewBridge(RNCWebView rNCWebView) {
                this.mContext = rNCWebView;
            }

            @JavascriptInterface
            public void postMessage(String str) {
                this.mContext.onMessage(str);
            }
        }

        public RNCWebView(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.injectedJavaScriptForMainFrameOnly = true;
            this.injectedJavaScriptBeforeContentLoadedForMainFrameOnly = true;
            this.messagingEnabled = false;
            this.sendContentSizeChangeEvents = false;
            this.hasScrollEvent = false;
            createCatalystInstance();
            this.progressChangedFilter = new ProgressChangedFilter();
        }

        public void callInjectedJavaScript() {
            String str;
            if (!getSettings().getJavaScriptEnabled() || (str = this.injectedJS) == null || TextUtils.isEmpty(str)) {
                return;
            }
            evaluateJavascriptWithFallback(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("(function() {\n"), this.injectedJS, ";\n})();"));
        }

        public void callInjectedJavaScriptBeforeContentLoaded() {
            String str;
            if (!getSettings().getJavaScriptEnabled() || (str = this.injectedJSBeforeContentLoaded) == null || TextUtils.isEmpty(str)) {
                return;
            }
            evaluateJavascriptWithFallback(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("(function() {\n"), this.injectedJSBeforeContentLoaded, ";\n})();"));
        }

        protected void cleanupCallbacksAndDestroy() {
            setWebViewClient(null);
            destroy();
        }

        protected void createCatalystInstance() {
            ReactContext reactContext = (ReactContext) getContext();
            if (reactContext != null) {
                this.mCatalystInstance = reactContext.getCatalystInstance();
            }
        }

        protected RNCWebViewBridge createRNCWebViewBridge(RNCWebView rNCWebView) {
            return new RNCWebViewBridge(rNCWebView);
        }

        @Override // android.webkit.WebView
        public void destroy() {
            WebChromeClient webChromeClient = this.mWebChromeClient;
            if (webChromeClient != null) {
                webChromeClient.onHideCustomView();
            }
            super.destroy();
        }

        protected void evaluateJavascriptWithFallback(String str) {
            int i = Build.VERSION.SDK_INT;
            evaluateJavascript(str, null);
        }

        @Nullable
        public RNCWebViewClient getRNCWebViewClient() {
            return this.mRNCWebViewClient;
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            cleanupCallbacksAndDestroy();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
        }

        public void onMessage(final String str) {
            ReactContext reactContext = (ReactContext) getContext();
            if (this.mRNCWebViewClient != null) {
                post(new Runnable() { // from class: com.reactnativecommunity.webview.RNCWebViewManager.RNCWebView.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RNCWebViewClient rNCWebViewClient = RNCWebView.this.mRNCWebViewClient;
                        if (rNCWebViewClient == null) {
                            return;
                        }
                        WebView webView = this;
                        WritableMap createWebViewEvent = rNCWebViewClient.createWebViewEvent(webView, webView.getUrl());
                        createWebViewEvent.putString("data", str);
                        if (RNCWebView.this.mCatalystInstance != null) {
                            this.sendDirectMessage("onMessage", createWebViewEvent);
                            return;
                        }
                        WebView webView2 = this;
                        RNCWebViewManager.dispatchEvent(webView2, new TopMessageEvent(webView2.getId(), createWebViewEvent));
                    }
                });
                return;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putString("data", str);
            if (this.mCatalystInstance != null) {
                sendDirectMessage("onMessage", createMap);
            } else {
                RNCWebViewManager.dispatchEvent(this, new TopMessageEvent(getId(), createMap));
            }
        }

        @Override // android.webkit.WebView, android.view.View
        protected void onScrollChanged(int i, int i2, int i3, int i4) {
            super.onScrollChanged(i, i2, i3, i4);
            if (!this.hasScrollEvent) {
                return;
            }
            if (this.mOnScrollDispatchHelper == null) {
                this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
            }
            if (!this.mOnScrollDispatchHelper.onScrollChanged(i, i2)) {
                return;
            }
            RNCWebViewManager.dispatchEvent(this, ScrollEvent.obtain(getId(), ScrollEventType.SCROLL, i, i2, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity(), computeHorizontalScrollRange(), computeVerticalScrollRange(), getWidth(), getHeight()));
        }

        @Override // android.webkit.WebView, android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            if (this.sendContentSizeChangeEvents) {
                RNCWebViewManager.dispatchEvent(this, new ContentSizeChangeEvent(getId(), i, i2));
            }
        }

        protected void sendDirectMessage(String str, WritableMap writableMap) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putMap("nativeEvent", writableMap);
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            writableNativeArray.pushMap(writableNativeMap);
            this.mCatalystInstance.callFunction(this.messagingModuleName, str, writableNativeArray);
        }

        public void setHasScrollEvent(boolean z) {
            this.hasScrollEvent = z;
        }

        public void setIgnoreErrFailedForThisURL(String str) {
            this.mRNCWebViewClient.setIgnoreErrFailedForThisURL(str);
        }

        public void setInjectedJavaScript(@Nullable String str) {
            this.injectedJS = str;
        }

        public void setInjectedJavaScriptBeforeContentLoaded(@Nullable String str) {
            this.injectedJSBeforeContentLoaded = str;
        }

        public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(boolean z) {
            this.injectedJavaScriptBeforeContentLoadedForMainFrameOnly = z;
        }

        public void setInjectedJavaScriptForMainFrameOnly(boolean z) {
            this.injectedJavaScriptForMainFrameOnly = z;
        }

        @SuppressLint({"AddJavascriptInterface"})
        public void setMessagingEnabled(boolean z) {
            if (this.messagingEnabled == z) {
                return;
            }
            this.messagingEnabled = z;
            if (z) {
                addJavascriptInterface(createRNCWebViewBridge(this), RNCWebViewManager.JAVASCRIPT_INTERFACE);
            } else {
                removeJavascriptInterface(RNCWebViewManager.JAVASCRIPT_INTERFACE);
            }
        }

        public void setMessagingModuleName(String str) {
            this.messagingModuleName = str;
        }

        public void setSendContentSizeChangeEvents(boolean z) {
            this.sendContentSizeChangeEvents = z;
        }

        @Override // android.webkit.WebView
        public void setWebChromeClient(WebChromeClient webChromeClient) {
            this.mWebChromeClient = webChromeClient;
            super.setWebChromeClient(webChromeClient);
            if (webChromeClient instanceof RNCWebChromeClient) {
                ((RNCWebChromeClient) webChromeClient).setProgressChangedFilter(this.progressChangedFilter);
            }
        }

        @Override // android.webkit.WebView
        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            if (webViewClient instanceof RNCWebViewClient) {
                this.mRNCWebViewClient = (RNCWebViewClient) webViewClient;
                this.mRNCWebViewClient.setProgressChangedFilter(this.progressChangedFilter);
            }
        }
    }

    public RNCWebViewManager() {
        this.mWebChromeClient = null;
        this.mAllowsFullscreenVideo = false;
        this.mUserAgent = null;
        this.mUserAgentWithApplicationName = null;
        this.mWebViewConfig = new WebViewConfig() { // from class: com.reactnativecommunity.webview.RNCWebViewManager.1
            @Override // com.reactnativecommunity.webview.WebViewConfig
            public void configWebView(WebView webView) {
            }
        };
    }

    protected static void dispatchEvent(WebView webView, Event event) {
        ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher().dispatchEvent(event);
    }

    public static RNCWebViewModule getModule(ReactContext reactContext) {
        return (RNCWebViewModule) reactContext.getNativeModule(RNCWebViewModule.class);
    }

    protected RNCWebView createRNCWebViewInstance(ThemedReactContext themedReactContext) {
        return new RNCWebView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.builder().put("goBack", 1).put("goForward", 2).put("reload", 3).put("stopLoading", 4).put("postMessage", 5).put("injectJavaScript", 6).put("loadUrl", 7).put("requestFocus", 8).put("clearFormData", 1000).put("clearCache", 1001).put("clearHistory", 1002).build();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = MapBuilder.newHashMap();
        }
        exportedCustomDirectEventTypeConstants.put(TopLoadingProgressEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingProgress"));
        exportedCustomDirectEventTypeConstants.put(TopShouldStartLoadWithRequestEvent.EVENT_NAME, MapBuilder.of("registrationName", "onShouldStartLoadWithRequest"));
        exportedCustomDirectEventTypeConstants.put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.of("registrationName", "onScroll"));
        exportedCustomDirectEventTypeConstants.put(TopHttpErrorEvent.EVENT_NAME, MapBuilder.of("registrationName", "onHttpError"));
        exportedCustomDirectEventTypeConstants.put(TopRenderProcessGoneEvent.EVENT_NAME, MapBuilder.of("registrationName", "onRenderProcessGone"));
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCWebView";
    }

    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(WebView webView, @Nullable Boolean bool) {
        webView.getSettings().setAllowFileAccess(bool != null && bool.booleanValue());
    }

    @ReactProp(name = "allowFileAccessFromFileURLs")
    public void setAllowFileAccessFromFileURLs(WebView webView, boolean z) {
        webView.getSettings().setAllowFileAccessFromFileURLs(z);
    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView webView, boolean z) {
        webView.getSettings().setAllowUniversalAccessFromFileURLs(z);
    }

    @ReactProp(name = "allowsFullscreenVideo")
    public void setAllowsFullscreenVideo(WebView webView, @Nullable Boolean bool) {
        this.mAllowsFullscreenVideo = bool != null && bool.booleanValue();
        setupWebChromeClient((ReactContext) webView.getContext(), webView);
    }

    @ReactProp(name = "applicationNameForUserAgent")
    public void setApplicationNameForUserAgent(WebView webView, @Nullable String str) {
        if (str != null) {
            int i = Build.VERSION.SDK_INT;
            this.mUserAgentWithApplicationName = GeneratedOutlineSupport1.outline75(WebSettings.getDefaultUserAgent(webView.getContext()), " ", str);
        } else {
            this.mUserAgentWithApplicationName = null;
        }
        setUserAgentString(webView);
    }

    @ReactProp(name = "cacheEnabled")
    public void setCacheEnabled(WebView webView, boolean z) {
        if (z) {
            Context context = webView.getContext();
            if (context == null) {
                return;
            }
            webView.getSettings().setAppCachePath(context.getCacheDir().getAbsolutePath());
            webView.getSettings().setCacheMode(-1);
            webView.getSettings().setAppCacheEnabled(true);
            return;
        }
        webView.getSettings().setCacheMode(2);
        webView.getSettings().setAppCacheEnabled(false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @ReactProp(name = "cacheMode")
    public void setCacheMode(WebView webView, String str) {
        boolean z;
        Integer num;
        switch (str.hashCode()) {
            case -2059164003:
                if (str.equals("LOAD_NO_CACHE")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case -1215135800:
                if (str.equals("LOAD_DEFAULT")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case -873877826:
                if (str.equals("LOAD_CACHE_ELSE_NETWORK")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1548620642:
                if (str.equals("LOAD_CACHE_ONLY")) {
                    z = false;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        if (!z) {
            num = 3;
        } else if (z) {
            num = 1;
        } else if (!z) {
            num = -1;
        } else {
            num = 2;
        }
        webView.getSettings().setCacheMode(num.intValue());
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(WebView webView, boolean z) {
        webView.getSettings().setDomStorageEnabled(z);
    }

    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(WebView webView, @Nullable Boolean bool) {
        webView.getSettings().setGeolocationEnabled(bool != null && bool.booleanValue());
    }

    @ReactProp(name = "androidHardwareAccelerationDisabled")
    public void setHardwareAccelerationDisabled(WebView webView, boolean z) {
        if (z) {
            webView.setLayerType(1, null);
        }
    }

    @ReactProp(name = "incognito")
    public void setIncognito(WebView webView, boolean z) {
        if (!z) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        CookieManager.getInstance().removeAllCookies(null);
        webView.getSettings().setCacheMode(2);
        webView.getSettings().setAppCacheEnabled(false);
        webView.clearHistory();
        webView.clearCache(true);
        webView.clearFormData();
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setSaveFormData(false);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(WebView webView, @Nullable String str) {
        ((RNCWebView) webView).setInjectedJavaScript(str);
    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoaded")
    public void setInjectedJavaScriptBeforeContentLoaded(WebView webView, @Nullable String str) {
        ((RNCWebView) webView).setInjectedJavaScriptBeforeContentLoaded(str);
    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoadedForMainFrameOnly")
    public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(WebView webView, boolean z) {
        ((RNCWebView) webView).setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(z);
    }

    @ReactProp(name = "injectedJavaScriptForMainFrameOnly")
    public void setInjectedJavaScriptForMainFrameOnly(WebView webView, boolean z) {
        ((RNCWebView) webView).setInjectedJavaScriptForMainFrameOnly(z);
    }

    @ReactProp(name = "javaScriptCanOpenWindowsAutomatically")
    public void setJavaScriptCanOpenWindowsAutomatically(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(z);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptEnabled(z);
    }

    @ReactProp(name = "androidLayerType")
    public void setLayerType(WebView webView, String str) {
        char c;
        int hashCode = str.hashCode();
        int i = 0;
        if (hashCode != 116909544) {
            if (hashCode == 1319330215 && str.equals("software")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(MetricsConfiguration.HARDWARE)) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            i = 2;
        } else if (c == 1) {
            i = 1;
        }
        webView.setLayerType(i, null);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    @TargetApi(17)
    public void setMediaPlaybackRequiresUserAction(WebView webView, boolean z) {
        webView.getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(WebView webView, boolean z) {
        ((RNCWebView) webView).setMessagingEnabled(z);
    }

    @ReactProp(name = "messagingModuleName")
    public void setMessagingModuleName(WebView webView, String str) {
        ((RNCWebView) webView).setMessagingModuleName(str);
    }

    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(WebView webView, @Nullable String str) {
        int i = Build.VERSION.SDK_INT;
        if (str != null && !ReactScrollViewHelper.OVER_SCROLL_NEVER.equals(str)) {
            if (ReactScrollViewHelper.OVER_SCROLL_ALWAYS.equals(str)) {
                webView.getSettings().setMixedContentMode(0);
                return;
            } else if (!"compatibility".equals(str)) {
                return;
            } else {
                webView.getSettings().setMixedContentMode(2);
                return;
            }
        }
        webView.getSettings().setMixedContentMode(1);
    }

    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(WebView webView, boolean z) {
        ((RNCWebView) webView).setSendContentSizeChangeEvents(z);
    }

    @ReactProp(name = "onScroll")
    public void setOnScroll(WebView webView, boolean z) {
        ((RNCWebView) webView).setHasScrollEvent(z);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(WebView webView, String str) {
        char c;
        Integer num;
        int hashCode = str.hashCode();
        if (hashCode == -1414557169) {
            if (str.equals(ReactScrollViewHelper.OVER_SCROLL_ALWAYS)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 104712844) {
            if (hashCode == 951530617 && str.equals("content")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(ReactScrollViewHelper.OVER_SCROLL_NEVER)) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            num = 2;
        } else if (c != 1) {
            num = 0;
        } else {
            num = 1;
        }
        webView.setOverScrollMode(num.intValue());
    }

    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(WebView webView, boolean z) {
        webView.getSettings().setSaveFormData(!z);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(WebView webView, boolean z) {
        webView.getSettings().setLoadWithOverviewMode(z);
        webView.getSettings().setUseWideViewPort(z);
    }

    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(WebView webView, boolean z) {
        webView.setHorizontalScrollBarEnabled(z);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(WebView webView, boolean z) {
        webView.setVerticalScrollBarEnabled(z);
    }

    @ReactProp(name = "source")
    public void setSource(WebView webView, @Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                webView.loadDataWithBaseURL(readableMap.hasKey("baseUrl") ? readableMap.getString("baseUrl") : "", readableMap.getString("html"), "text/html", "UTF-8", null);
                return;
            } else if (readableMap.hasKey("uri")) {
                String string = readableMap.getString("uri");
                String url = webView.getUrl();
                if (url != null && url.equals(string)) {
                    return;
                }
                if (readableMap.hasKey(MetricsConstants.NativeFetch.METHOD) && readableMap.getString(MetricsConstants.NativeFetch.METHOD).equalsIgnoreCase("POST")) {
                    byte[] bArr = null;
                    if (readableMap.hasKey("body")) {
                        String string2 = readableMap.getString("body");
                        try {
                            bArr = string2.getBytes("UTF-8");
                        } catch (UnsupportedEncodingException unused) {
                            bArr = string2.getBytes();
                        }
                    }
                    if (bArr == null) {
                        bArr = new byte[0];
                    }
                    webView.postUrl(string, bArr);
                    return;
                }
                HashMap hashMap = new HashMap();
                if (readableMap.hasKey(HttpClientModule.ElementsRequestKey.HEADERS)) {
                    ReadableMap mo6945getMap = readableMap.mo6945getMap(HttpClientModule.ElementsRequestKey.HEADERS);
                    ReadableMapKeySetIterator keySetIterator = mo6945getMap.keySetIterator();
                    while (keySetIterator.hasNextKey()) {
                        String nextKey = keySetIterator.nextKey();
                        if ("user-agent".equals(nextKey.toLowerCase(Locale.ENGLISH))) {
                            if (webView.getSettings() != null) {
                                webView.getSettings().setUserAgentString(mo6945getMap.getString(nextKey));
                            }
                        } else {
                            hashMap.put(nextKey, mo6945getMap.getString(nextKey));
                        }
                    }
                }
                webView.loadUrl(string, hashMap);
                return;
            }
        }
        webView.loadUrl("about:blank");
    }

    @ReactProp(name = "textZoom")
    public void setTextZoom(WebView webView, int i) {
        webView.getSettings().setTextZoom(i);
    }

    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(WebView webView, boolean z) {
        int i = Build.VERSION.SDK_INT;
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, z);
    }

    @ReactProp(name = "urlPrefixesForDefaultIntent")
    public void setUrlPrefixesForDefaultIntent(WebView webView, @Nullable ReadableArray readableArray) {
        RNCWebViewClient rNCWebViewClient = ((RNCWebView) webView).getRNCWebViewClient();
        if (rNCWebViewClient == null || readableArray == null) {
            return;
        }
        rNCWebViewClient.setUrlPrefixesForDefaultIntent(readableArray);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(WebView webView, @Nullable String str) {
        if (str != null) {
            this.mUserAgent = str;
        } else {
            this.mUserAgent = null;
        }
        setUserAgentString(webView);
    }

    protected void setUserAgentString(WebView webView) {
        if (this.mUserAgent != null) {
            webView.getSettings().setUserAgentString(this.mUserAgent);
        } else if (this.mUserAgentWithApplicationName != null) {
            webView.getSettings().setUserAgentString(this.mUserAgentWithApplicationName);
        } else {
            int i = Build.VERSION.SDK_INT;
            webView.getSettings().setUserAgentString(WebSettings.getDefaultUserAgent(webView.getContext()));
        }
    }

    protected void setupWebChromeClient(ReactContext reactContext, WebView webView) {
        if (this.mAllowsFullscreenVideo) {
            final int requestedOrientation = reactContext.getCurrentActivity().getRequestedOrientation();
            this.mWebChromeClient = new RNCWebChromeClient(reactContext, webView) { // from class: com.reactnativecommunity.webview.RNCWebViewManager.3
                @Override // android.webkit.WebChromeClient
                public Bitmap getDefaultVideoPoster() {
                    return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
                }

                @Override // android.webkit.WebChromeClient
                public void onHideCustomView() {
                    View view = this.mVideoView;
                    if (view == null) {
                        return;
                    }
                    view.setVisibility(8);
                    getRootView().removeView(this.mVideoView);
                    this.mCustomViewCallback.onCustomViewHidden();
                    this.mVideoView = null;
                    this.mCustomViewCallback = null;
                    this.mWebView.setVisibility(0);
                    int i = Build.VERSION.SDK_INT;
                    this.mReactContext.getCurrentActivity().getWindow().clearFlags(512);
                    this.mReactContext.getCurrentActivity().setRequestedOrientation(requestedOrientation);
                    this.mReactContext.removeLifecycleEventListener(this);
                }

                @Override // android.webkit.WebChromeClient
                public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                    if (this.mVideoView != null) {
                        customViewCallback.onCustomViewHidden();
                        return;
                    }
                    this.mVideoView = view;
                    this.mCustomViewCallback = customViewCallback;
                    this.mReactContext.getCurrentActivity().setRequestedOrientation(-1);
                    int i = Build.VERSION.SDK_INT;
                    this.mVideoView.setSystemUiVisibility(7942);
                    this.mReactContext.getCurrentActivity().getWindow().setFlags(512, 512);
                    this.mVideoView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    getRootView().addView(this.mVideoView, RNCWebChromeClient.FULLSCREEN_LAYOUT_PARAMS);
                    this.mWebView.setVisibility(8);
                    this.mReactContext.addLifecycleEventListener(this);
                }
            };
            webView.setWebChromeClient(this.mWebChromeClient);
            return;
        }
        RNCWebChromeClient rNCWebChromeClient = this.mWebChromeClient;
        if (rNCWebChromeClient != null) {
            rNCWebChromeClient.onHideCustomView();
        }
        this.mWebChromeClient = new RNCWebChromeClient(reactContext, webView) { // from class: com.reactnativecommunity.webview.RNCWebViewManager.4
            @Override // android.webkit.WebChromeClient
            public Bitmap getDefaultVideoPoster() {
                return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            }
        };
        webView.setWebChromeClient(this.mWebChromeClient);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, WebView webView) {
        webView.setWebViewClient(new RNCWebViewClient());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @TargetApi(21)
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public WebView mo12880createViewInstance(final ThemedReactContext themedReactContext) {
        final RNCWebView createRNCWebViewInstance = createRNCWebViewInstance(themedReactContext);
        setupWebChromeClient(themedReactContext, createRNCWebViewInstance);
        themedReactContext.addLifecycleEventListener(createRNCWebViewInstance);
        this.mWebViewConfig.configWebView(createRNCWebViewInstance);
        WebSettings settings = createRNCWebViewInstance.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        int i = Build.VERSION.SDK_INT;
        settings.setAllowFileAccessFromFileURLs(false);
        setAllowUniversalAccessFromFileURLs(createRNCWebViewInstance, false);
        setMixedContentMode(createRNCWebViewInstance, ReactScrollViewHelper.OVER_SCROLL_NEVER);
        createRNCWebViewInstance.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        createRNCWebViewInstance.setDownloadListener(new DownloadListener() { // from class: com.reactnativecommunity.webview.RNCWebViewManager.2
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                createRNCWebViewInstance.setIgnoreErrFailedForThisURL(str);
                RNCWebViewModule module = RNCWebViewManager.getModule(themedReactContext);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
                String guessFileName = URLUtil.guessFileName(str, str3, str4);
                String outline72 = GeneratedOutlineSupport1.outline72("Downloading ", guessFileName);
                try {
                    URL url = new URL(str);
                    request.addRequestHeader("Cookie", CookieManager.getInstance().getCookie(url.getProtocol() + "://" + url.getHost()));
                } catch (MalformedURLException e) {
                    PrintStream printStream = System.out;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error getting cookie for DownloadManager: ");
                    outline107.append(e.toString());
                    printStream.println(outline107.toString());
                    e.printStackTrace();
                }
                request.addRequestHeader("User-Agent", str2);
                request.setTitle(guessFileName);
                request.setDescription(outline72);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(1);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, guessFileName);
                module.setDownloadRequest(request);
                if (module.grantFileDownloaderPermissions()) {
                    module.downloadFile();
                }
            }
        });
        return createRNCWebViewInstance;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(WebView webView) {
        super.onDropViewInstance((RNCWebViewManager) webView);
        RNCWebView rNCWebView = (RNCWebView) webView;
        ((ThemedReactContext) webView.getContext()).removeLifecycleEventListener(rNCWebView);
        rNCWebView.cleanupCallbacksAndDestroy();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(WebView webView, int i, @Nullable ReadableArray readableArray) {
        boolean z = false;
        switch (i) {
            case 1:
                webView.goBack();
                return;
            case 2:
                webView.goForward();
                return;
            case 3:
                webView.reload();
                return;
            case 4:
                webView.stopLoading();
                return;
            case 5:
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("data", readableArray.getString(0));
                    ((RNCWebView) webView).evaluateJavascriptWithFallback("(function () {var event;var data = " + jSONObject.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            case 6:
                ((RNCWebView) webView).evaluateJavascriptWithFallback(readableArray.getString(0));
                return;
            case 7:
                if (readableArray != null) {
                    ((RNCWebView) webView).progressChangedFilter.setWaitingForCommandLoadUrl(false);
                    webView.loadUrl(readableArray.getString(0));
                    return;
                }
                throw new RuntimeException("Arguments for loading an url are null!");
            case 8:
                webView.requestFocus();
                return;
            default:
                switch (i) {
                    case 1000:
                        webView.clearFormData();
                        return;
                    case 1001:
                        if (readableArray != null && readableArray.getBoolean(0)) {
                            z = true;
                        }
                        webView.clearCache(z);
                        return;
                    case 1002:
                        webView.clearHistory();
                        return;
                    default:
                        return;
                }
        }
    }

    public RNCWebViewManager(WebViewConfig webViewConfig) {
        this.mWebChromeClient = null;
        this.mAllowsFullscreenVideo = false;
        this.mUserAgent = null;
        this.mUserAgentWithApplicationName = null;
        this.mWebViewConfig = webViewConfig;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static class RNCWebViewClient extends WebViewClient {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        @Nullable
        protected ReadableArray mUrlPrefixesForDefaultIntent;
        protected boolean mLastLoadFailed = false;
        protected RNCWebView.ProgressChangedFilter progressChangedFilter = null;
        @Nullable
        protected String ignoreErrFailedForThisURL = null;

        protected RNCWebViewClient() {
        }

        protected WritableMap createWebViewEvent(WebView webView, String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("target", webView.getId());
            createMap.putString("url", str);
            createMap.putBoolean("loading", !this.mLastLoadFailed && webView.getProgress() != 100);
            createMap.putString("title", webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            return createMap;
        }

        protected void emitFinishEvent(WebView webView, String str) {
            RNCWebViewManager.dispatchEvent(webView, new TopLoadingFinishEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!this.mLastLoadFailed) {
                ((RNCWebView) webView).callInjectedJavaScript();
                emitFinishEvent(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.mLastLoadFailed = false;
            ((RNCWebView) webView).callInjectedJavaScriptBeforeContentLoaded();
            RNCWebViewManager.dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            String str3 = this.ignoreErrFailedForThisURL;
            if (str3 != null && str2.equals(str3) && i == -1 && str.equals("net::ERR_FAILED")) {
                setIgnoreErrFailedForThisURL(null);
                return;
            }
            super.onReceivedError(webView, i, str, str2);
            this.mLastLoadFailed = true;
            emitFinishEvent(webView, str2);
            WritableMap createWebViewEvent = createWebViewEvent(webView, str2);
            createWebViewEvent.putDouble("code", i);
            createWebViewEvent.putString("description", str);
            RNCWebViewManager.dispatchEvent(webView, new TopLoadingErrorEvent(webView.getId(), createWebViewEvent));
        }

        @Override // android.webkit.WebViewClient
        @RequiresApi(api = 23)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            if (webResourceRequest.isForMainFrame()) {
                WritableMap createWebViewEvent = createWebViewEvent(webView, webResourceRequest.getUrl().toString());
                createWebViewEvent.putInt("statusCode", webResourceResponse.getStatusCode());
                createWebViewEvent.putString("description", webResourceResponse.getReasonPhrase());
                RNCWebViewManager.dispatchEvent(webView, new TopHttpErrorEvent(webView.getId(), createWebViewEvent));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
            int primaryError = sslError.getPrimaryError();
            onReceivedError(webView, primaryError, GeneratedOutlineSupport1.outline72("SSL error: ", primaryError != 0 ? primaryError != 1 ? primaryError != 2 ? primaryError != 3 ? primaryError != 4 ? primaryError != 5 ? "Unknown SSL Error" : "A generic error occurred" : "The date of the certificate is invalid" : "The certificate authority is not trusted" : "Hostname mismatch" : "The certificate has expired" : "The certificate is not yet valid"), sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(26)
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            int i = Build.VERSION.SDK_INT;
            super.onRenderProcessGone(webView, renderProcessGoneDetail);
            if (renderProcessGoneDetail.didCrash()) {
                Log.e(RNCWebViewManager.TAG, "The WebView rendering process crashed.");
            } else {
                Log.w(RNCWebViewManager.TAG, "The WebView rendering process was killed by the system.");
            }
            if (webView == null) {
                return true;
            }
            WritableMap createWebViewEvent = createWebViewEvent(webView, webView.getUrl());
            createWebViewEvent.putBoolean("didCrash", renderProcessGoneDetail.didCrash());
            RNCWebViewManager.dispatchEvent(webView, new TopRenderProcessGoneEvent(webView.getId(), createWebViewEvent));
            return true;
        }

        public void setIgnoreErrFailedForThisURL(@Nullable String str) {
            this.ignoreErrFailedForThisURL = str;
        }

        public void setProgressChangedFilter(RNCWebView.ProgressChangedFilter progressChangedFilter) {
            this.progressChangedFilter = progressChangedFilter;
        }

        public void setUrlPrefixesForDefaultIntent(ReadableArray readableArray) {
            this.mUrlPrefixesForDefaultIntent = readableArray;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            RNCWebView rNCWebView = (RNCWebView) webView;
            boolean z = true;
            if (!(((ReactContext) webView.getContext()).getJavaScriptContextHolder().get() == 0) && rNCWebView.mCatalystInstance != null) {
                Pair<Integer, AtomicReference<RNCWebViewModule.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState>> newLock = RNCWebViewModule.shouldOverrideUrlLoadingLock.getNewLock();
                int intValue = newLock.first.intValue();
                AtomicReference<RNCWebViewModule.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState> atomicReference = newLock.second;
                WritableMap createWebViewEvent = createWebViewEvent(webView, str);
                createWebViewEvent.putInt("lockIdentifier", intValue);
                rNCWebView.sendDirectMessage("onShouldStartLoadWithRequest", createWebViewEvent);
                try {
                    synchronized (atomicReference) {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        while (atomicReference.get() == RNCWebViewModule.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.UNDECIDED) {
                            if (SystemClock.elapsedRealtime() - elapsedRealtime > 250) {
                                FLog.w(RNCWebViewManager.TAG, "Did not receive response to shouldOverrideUrlLoading in time, defaulting to allow loading.");
                                RNCWebViewModule.shouldOverrideUrlLoadingLock.removeLock(Integer.valueOf(intValue));
                                return false;
                            }
                            atomicReference.wait(250L);
                        }
                        if (atomicReference.get() != RNCWebViewModule.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.SHOULD_OVERRIDE) {
                            z = false;
                        }
                        RNCWebViewModule.shouldOverrideUrlLoadingLock.removeLock(Integer.valueOf(intValue));
                        return z;
                    }
                } catch (InterruptedException e) {
                    FLog.e(RNCWebViewManager.TAG, "shouldOverrideUrlLoading was interrupted while waiting for result.", e);
                    RNCWebViewModule.shouldOverrideUrlLoadingLock.removeLock(Integer.valueOf(intValue));
                    return false;
                }
            }
            FLog.w(RNCWebViewManager.TAG, "Couldn't use blocking synchronous call for onShouldStartLoadWithRequest due to debugging or missing Catalyst instance, falling back to old event-and-load.");
            this.progressChangedFilter.setWaitingForCommandLoadUrl(true);
            RNCWebViewManager.dispatchEvent(webView, new TopShouldStartLoadWithRequestEvent(webView.getId(), createWebViewEvent(webView, str)));
            return true;
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
        }
    }
}
