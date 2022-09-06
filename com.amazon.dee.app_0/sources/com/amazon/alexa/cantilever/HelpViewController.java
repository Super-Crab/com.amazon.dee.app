package com.amazon.alexa.cantilever;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.cantilever.utils.HelpFeatureChecker;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Tag;
import com.amazon.alexa.logupload.LogUploader;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.amazon.comms.config.util.DeviceConfigConstants;
import java.util.Objects;
import rx.Subscription;
import rx.functions.Action1;
/* loaded from: classes6.dex */
public class HelpViewController implements HelpWebViewLoadListener, View.OnClickListener, ViewController {
    private static final String CANTILEVER_INTENT_KEY = "cantileverIntent";
    private static final String CHAT_REDIRECT_KEY = "isChatRedirect";
    private static final String JS_INTERFACE = "alexa";
    private static final String LOG_UPLOAD_METRIC_PREFIX = "help";
    private static final String NODE_ID_KEY = "nodeId";
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpViewController.class);
    private static final String USER_AGENT_SUFFIX = "CLVR";
    private static final String WEBVIEW_COMPLETE_PATH = "/webViewComplete";
    private static final int WEBVIEW_DISPLAY_DELAY_SECONDS = 3;
    private HelpCookiesService helpCookiesService;
    private HelpErrorViewController helpErrorViewController;
    private HelpFeatureChecker helpFeatureChecker;
    private HelpSessionService helpSessionService;
    private HelpURLService helpUrlService;
    private boolean isAttached;
    private ViewManagerLoadingDelegate loadingDelegate;
    private LogUploader logUploader;
    private Subscription networkSubscription;
    protected RouteContext routeContextToBeLoaded;
    private HelpWebChromeClient webChromeClient;
    private WebView webView;
    private HelpWebViewClient webViewClient;
    private final LazyComponent<EnvironmentService> environmentService = ComponentRegistry.getInstance().getLazy(EnvironmentService.class);
    private final LazyComponent<NetworkService> networkService = ComponentRegistry.getInstance().getLazy(NetworkService.class);
    private final LazyComponent<RoutingService> routingService = ComponentRegistry.getInstance().getLazy(RoutingService.class);

    private void delayWebViewDisplay(final WebView webView) {
        new CountDownTimer(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS, 1000L) { // from class: com.amazon.alexa.cantilever.HelpViewController.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                webView.setVisibility(0);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }
        }.start();
    }

    private Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initializeWebView(View view) {
        Lib.Log.d(TAG, "Creating WebView");
        this.webView = (WebView) view.findViewById(R.id.cantilever_webview);
        this.webViewClient = new HelpWebViewClient(view.getContext(), this.helpSessionService, this, ComponentRegistry.getInstance(), this.helpCookiesService, this.helpFeatureChecker);
        Activity activity = getActivity(view.getContext());
        if (activity == null) {
            return;
        }
        this.webChromeClient = new HelpWebChromeClient(activity, view);
        this.webView.setWebViewClient(this.webViewClient);
        this.webView.setWebChromeClient(this.webChromeClient);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setOverScrollMode(2);
        this.webView.addJavascriptInterface(this.webViewClient, "alexa");
        this.webView.getSettings().setUserAgentString(this.webView.getSettings().getUserAgentString() + " " + USER_AGENT_SUFFIX);
        this.webView.getSettings().setDomStorageEnabled(true);
    }

    private boolean isOnMessageUsUrl() {
        return this.webView.getUrl() != null && (this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.MESSAGE_US, this.webView.getUrl()) || this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.HMD_SURVEY, this.webView.getUrl()));
    }

    private void loadUrl(RouteContext routeContext) {
        String pageURL;
        if (routeContext.getBoolean(CHAT_REDIRECT_KEY, false)) {
            pageURL = this.helpUrlService.getChatPageURL();
        } else if (routeContext.getString(CANTILEVER_INTENT_KEY) != null) {
            pageURL = this.helpUrlService.getCantileverIntentUrl(String.valueOf(routeContext.getString(CANTILEVER_INTENT_KEY)));
        } else if (routeContext.get("nodeId") instanceof Integer) {
            pageURL = this.helpUrlService.getPageURL(String.valueOf(routeContext.getInt("nodeId")));
        } else {
            pageURL = this.helpUrlService.getPageURL(routeContext.getString("nodeId", null));
        }
        setCantileverCookies(pageURL);
        this.webView.loadUrl(pageURL);
        this.helpSessionService.onLoadUrlCalled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNetworkChanged(boolean z) {
        this.helpErrorViewController.setOffline(!z);
    }

    private void onRetryClicked() {
        this.webView.reload();
    }

    private void setCantileverCookies(String str) {
        CookieManager cookieManager = CookieManager.getInstance();
        for (String str2 : this.helpCookiesService.getCookies()) {
            cookieManager.setCookie(str, str2);
        }
    }

    private void submitLogs(String str) {
        this.logUploader.uploadLogsToDET(Uri.parse(str).getQueryParameter(LogUploader.DTS_SESSION_ID));
    }

    private void updateCompletion(String str) {
        this.helpSessionService.onHelpFlowCompleted(str);
        if (this.helpSessionService.isHelpFlowCompleted()) {
            submitLogs(str);
        }
    }

    private void updateCompletionView(WebView webView, String str) {
        if (this.networkService.mo10268get().isConnected() && !this.helpSessionService.isUnableToLoad()) {
            this.helpErrorViewController.setVisible(false);
            if (this.helpSessionService.isMAPNotSignedIn()) {
                webView.setVisibility(0);
            } else if (this.helpSessionService.isMAPSignedInLastTime()) {
                delayWebViewDisplay(webView);
            }
            updateRoute(webView, str);
            return;
        }
        if (!this.networkService.mo10268get().isConnected()) {
            this.helpErrorViewController.setupNoNetworkText();
        } else if (this.helpSessionService.isUnableToLoad()) {
            this.helpErrorViewController.setupUnableToLoadText();
        }
        this.helpErrorViewController.setVisible(true);
        webView.setVisibility(8);
    }

    private void updateRoute(WebView webView, String str) {
        if (!webView.canGoBack()) {
            return;
        }
        RouteContext create = this.routingService.mo10268get().route("help").with(RouteParameter.ROUTE, str).addToBackStack().create();
        create.getRoute().setRoot(false);
        onRouteUpdated(create);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        Objects.requireNonNull(layoutInflater, "inflater argument must be non-null.");
        Objects.requireNonNull(viewGroup, "container argument must be non-null.");
        View inflate = layoutInflater.inflate(R.layout.cantilever_container, viewGroup, false);
        initializeWebView(inflate);
        this.helpErrorViewController = new HelpErrorViewController(inflate.getContext(), inflate, this);
        this.helpSessionService.onHelpLoadAttempt();
        onRouteUpdated(this.routeContextToBeLoaded);
        return inflate;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public boolean onBackPressed() {
        if (this.webView.canGoBack()) {
            onRouteUpdated(this.routeContextToBeLoaded);
            return true;
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.cantilever_retry) {
            onRetryClicked();
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        Lib.Log.d(TAG, "Creating Help Controller");
        this.helpFeatureChecker = new HelpFeatureChecker();
        this.helpCookiesService = new HelpCookiesService(context, this.helpFeatureChecker);
        this.helpSessionService = new HelpSessionService(new HelpMetricsService());
        this.helpUrlService = new HelpURLService(context);
        this.logUploader = new LogUploader(context, "help");
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        Subscription subscription = this.networkSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.networkSubscription = null;
        }
        this.helpSessionService.onHelpLoadDetached();
        WebView webView = this.webView;
        if (webView != null) {
            webView.stopLoading();
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onPause(Context context) {
        this.isAttached = false;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onResume(Context context) {
        this.isAttached = true;
        this.networkSubscription = this.networkService.mo10268get().onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.alexa.cantilever.-$$Lambda$HelpViewController$v0qw98TuT7S5y7TLHioDu68yJqU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HelpViewController.this.onNetworkChanged(((Boolean) obj).booleanValue());
            }
        });
        if (!this.networkService.mo10268get().isConnected()) {
            Lib.Log.d(TAG, "No network connection on initialization.");
            onNetworkChanged(false);
        }
    }

    public void onRouteUpdated(RouteContext routeContext) {
        if (this.webView == null || routeContext == null) {
            if (routeContext == null) {
                return;
            }
            this.routeContextToBeLoaded = routeContext;
        } else if (routeContext.getBoolean(CHAT_REDIRECT_KEY, false) && isOnMessageUsUrl()) {
        } else {
            if (!this.helpSessionService.isLoadUrlCalled() || routeContext.getBoolean(CHAT_REDIRECT_KEY, false)) {
                loadUrl(routeContext);
                if (routeContext.getBoolean(CHAT_REDIRECT_KEY, false)) {
                    return;
                }
            }
            if (!this.webView.canGoBack()) {
                return;
            }
            if (TextUtils.equals(this.webView.getUrl(), routeContext.getString(RouteParameter.ROUTE))) {
                return;
            }
            if (this.helpSessionService.isHelpFlowCompleted()) {
                this.routingService.mo10268get().route(RouteName.HOME).clearBackStack().navigate();
            } else {
                this.webView.goBack();
            }
        }
    }

    @Override // com.amazon.alexa.cantilever.HelpWebViewLoadListener
    public void onTimeout() {
        this.webView.stopLoading();
        HelpWebViewClient helpWebViewClient = this.webViewClient;
        WebView webView = this.webView;
        helpWebViewClient.onReceivedError(webView, -8, MetricsConstants.KeyExchange.STATE_TRANSITION_TIMEOUT, webView.getUrl());
    }

    @Override // com.amazon.alexa.cantilever.HelpWebViewLoadListener
    public boolean onUrlChanged(String str) {
        if (this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AMAZON_ROOT, str) || str.contains(WEBVIEW_COMPLETE_PATH)) {
            this.routingService.mo10268get().route(RouteName.HOME).clearBackStack().navigate();
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.cantilever.HelpWebViewLoadListener
    public void onWebViewLoaded(String str) {
        this.loadingDelegate.setLoadingState(false);
        updateCompletion(str);
        if (this.isAttached) {
            updateCompletionView(this.webView, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHelpViewDelegate(@NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate) {
        this.loadingDelegate = viewManagerLoadingDelegate;
    }
}
