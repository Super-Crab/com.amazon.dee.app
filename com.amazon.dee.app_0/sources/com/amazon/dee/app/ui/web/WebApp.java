package com.amazon.dee.app.ui.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.Response;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes12.dex */
public class WebApp {
    static final String TAG = "WebApp";
    @Inject
    AccessibilityInfoBridge accessibilityInfoBridge;
    @Inject
    AccountManagementBridge accountManagementBridge;
    @Inject
    AlexaDeviceBackgroundImageBridge alexaDeviceBackgroundImageBridge;
    @Inject
    AppCacheBridge appCacheBridge;
    @Inject
    AppInfoBridge appInfoBridge;
    @Inject
    AppLauncherBridge appLauncherBridge;
    @Inject
    AppLayoutBridge appLayoutBridge;
    @Inject
    AppReloadBridge appReloadBridge;
    @Inject
    AudioBridge audioBridge;
    @Inject
    CommsDeviceSupport commsDeviceSupport;
    CompositeSubscription compositeSubscription;
    @Inject
    Context context;
    @Inject
    EnvironmentService environmentService;
    @Inject
    WebNavigator environmentWebNavigator;
    @Inject
    EventBusWebViewBridge eventBusWebViewBridge;
    @Inject
    ExternalUILauncherBridge externalUILauncherBridge;
    @Inject
    FeedbackBridge feedbackBridge;
    @Inject
    HeaderInfoBridge headerInfoBridge;
    @Inject
    IdentityService identityService;
    @Inject
    JavaScriptBridgeOrchestrator javaScriptBridgeOrchestrator;
    @Inject
    JavaScriptInjector javaScriptInjector;
    @Inject
    JavaScriptPlayer javaScriptPlayer;
    @Inject
    MenuSettingsBridge menuSettingsBridge;
    @Inject
    MessagingService messagingService;
    @Inject
    MetricsService metricsService;
    @Inject
    MetricsServiceBridge metricsServiceBridge;
    @Inject
    NativeHostBridge nativeHostBridge;
    @Inject
    NativeLocalStorageBridge nativeLocalStorageBridge;
    @Inject
    NavBarToggleBridge navBarToggleBridge;
    @Inject
    NavigationBridge navigationBridge;
    @Inject
    NetworkEventBridge networkEventBridge;
    @Inject
    NetworkService networkService;
    @Inject
    NotificationServiceBridge notificationServiceBridge;
    @Inject
    OOBEBridge oobeBridge;
    @Inject
    OrientationBridge orientationBridge;
    @Inject
    PermissionsBridge permissionsBridge;
    @Inject
    RoutingService routingService;
    @Inject
    TachyonIdentityBridge tachyonIdentityBridge;
    @Inject
    TachyonSettingsBridge tachyonSettingsBridge;
    @Inject
    UserMessageService userMessageService;
    @Inject
    VideoPlaybackBridge videoPlaybackBridge;
    @Inject
    WebAppMessagingReceiver webAppMessagingReceiver;
    @Inject
    WebViewDelegate webViewDelegate;
    @Inject
    WifiBridge wifiBridge;
    @Inject
    WifiService wifiService;

    @NonNull
    private Map<String, Object> createErrorWithEvent(String str) {
        return GeneratedOutlineSupport1.outline133("EventValue", str);
    }

    public void alertNoNetworkAvailable(int i, String str) {
        CharSequence text;
        boolean isConnected = this.networkService.isConnected();
        boolean isConnectedToDoppler = this.wifiService.isConnectedToDoppler();
        if (i == -8) {
            text = this.context.getText(R.string.main_connection_timed_out_message);
        } else if (i == -5) {
            text = this.context.getText(R.string.main_bad_proxy_message);
        } else if (i != -4) {
            text = this.context.getText(R.string.main_no_network_message);
        } else {
            text = this.context.getText(R.string.main_bad_authenticate_message);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("EventNumericValue", Integer.valueOf(i));
        hashMap.put(AlexaMetricsConstants.EventConstants.NETWORK, Integer.valueOf(isConnected ? 1 : 0));
        hashMap.put(AlexaMetricsConstants.EventConstants.SOURCE, Integer.valueOf(isConnectedToDoppler ? 1 : 0));
        hashMap.put("EventValue", str);
        this.metricsService.recordCustom("Error", AlexaMetricsConstants.MetricEvents.NO_NETWORK_ERROR, AlexaMetricsConstants.MetricsComponents.WEBVIEW, hashMap);
        this.userMessageService.message(text).withDismiss(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$WebApp$eaxbd0juY6mIbNXoIRb8SVNfeaI
            @Override // java.lang.Runnable
            public final void run() {
                WebApp.this.lambda$alertNoNetworkAvailable$1$WebApp();
            }
        }).show();
    }

    public void create() {
        this.javaScriptBridgeOrchestrator.registerBridge(this.navigationBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.networkEventBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.nativeHostBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.accountManagementBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.appInfoBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.accessibilityInfoBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.appCacheBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.audioBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.nativeLocalStorageBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.headerInfoBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.appReloadBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.appLauncherBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.externalUILauncherBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.orientationBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.notificationServiceBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.videoPlaybackBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.navBarToggleBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.wifiBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.metricsServiceBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.alexaDeviceBackgroundImageBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.appLayoutBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.oobeBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.eventBusWebViewBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.menuSettingsBridge);
        this.javaScriptBridgeOrchestrator.registerBridge(this.permissionsBridge);
        this.eventBusWebViewBridge.startListening();
        if (this.commsDeviceSupport.check()) {
            this.javaScriptBridgeOrchestrator.registerBridge(this.tachyonIdentityBridge);
            this.javaScriptBridgeOrchestrator.registerBridge(this.tachyonSettingsBridge);
        }
        this.javaScriptBridgeOrchestrator.registerBridge(this.feedbackBridge);
        this.nativeHostBridge.registerNativeTargets(this.javaScriptBridgeOrchestrator.getRegisteredBridges());
        this.compositeSubscription = new CompositeSubscription();
        this.compositeSubscription.add(this.networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$WebApp$KqmU4NH_DSSifme2hrOeh6c8Sa4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                WebApp.this.lambda$create$0$WebApp((Boolean) obj);
            }
        }));
        if (this.networkService.isConnected()) {
            this.networkEventBridge.notifyConnected();
        } else {
            this.networkEventBridge.notifyDisconnected();
        }
        this.messagingService.registerReceiverWithHigherPriority(this.webAppMessagingReceiver);
    }

    public void destroy() {
        this.messagingService.unregisterReceiver(this.webAppMessagingReceiver);
        this.compositeSubscription.unsubscribe();
        this.javaScriptPlayer.release();
        this.eventBusWebViewBridge.stopListening();
    }

    public void execute(String str, JsPromptResult jsPromptResult) {
        this.javaScriptBridgeOrchestrator.execute(str, jsPromptResult);
    }

    public WebNavigator getWebNavigator() {
        return this.environmentWebNavigator;
    }

    public void injectHttpsSupport() {
        this.javaScriptInjector.inject("Backbone.trigger('setOOBEBodyHeaders');");
    }

    public boolean isOutsideWebAppUrl(String str) {
        return this.environmentService.isOutsideWebAppUrl(str);
    }

    public boolean isWebAppUrl(String str) {
        return this.environmentService.isWebAppUrl(str);
    }

    public boolean isWebSigninUrl(String str) {
        return this.environmentService.isWebSigninUrl(str);
    }

    public boolean isWebWarmSeatUrl(String str) {
        return this.environmentService.isWebWarmSeatUrl(str);
    }

    public boolean isWithinWebAppUrl(String str) {
        return this.environmentService.isWithinWebAppUrl(str);
    }

    public /* synthetic */ void lambda$alertNoNetworkAvailable$1$WebApp() {
        this.routingService.navigateToExit();
    }

    public /* synthetic */ void lambda$create$0$WebApp(Boolean bool) {
        if (bool.booleanValue()) {
            this.networkEventBridge.notifyConnected();
        } else {
            this.networkEventBridge.notifyDisconnected();
        }
    }

    public Response makeRequestToDoppler(String str, Uri uri, Map<String, String> map, String str2) {
        return this.wifiService.makeRequestToDoppler(str, uri, map, str2);
    }

    public void notifyResumed() {
        this.javaScriptInjector.inject("Backbone.trigger('onMobileAppResume');");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.externalUILauncherBridge.onActivityResult(i, i2, intent);
        this.videoPlaybackBridge.onActivityResult(i, i2, intent);
        this.alexaDeviceBackgroundImageBridge.onActivityResult(i, i2, intent);
        this.oobeBridge.onActivityResult(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.wifiBridge.onRequestPermissionsResult(i, strArr, iArr);
        this.feedbackBridge.onRequestPermissionsResult(i, strArr, iArr);
    }

    @JavascriptInterface
    public String provideCountryCode() {
        return this.environmentService.getCountryCode();
    }

    public void recordHttpError(String str, int i) {
        Map<String, Object> createErrorWithEvent = createErrorWithEvent(str);
        createErrorWithEvent.put("statusCode", String.valueOf(i));
        this.metricsService.recordCustom("Error", AlexaMetricsConstants.MetricEvents.WEBVIEW_HTTP_ERROR, AlexaMetricsConstants.MetricsComponents.WEBVIEW, createErrorWithEvent);
    }

    public void recordMissingCORSHeader() {
        this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.OOBE_CORS_ERROR, AlexaMetricsConstants.MetricsComponents.OOBE, null);
    }

    public void recordResourceError(String str, int i) {
        Map<String, Object> createErrorWithEvent = createErrorWithEvent(str);
        createErrorWithEvent.put("EventNumericValue", String.valueOf(i));
        this.metricsService.recordCustom("Error", AlexaMetricsConstants.MetricEvents.WEBVIEW_RESOURCE_ERROR, AlexaMetricsConstants.MetricsComponents.WEBVIEW, createErrorWithEvent);
    }

    public void selectDevice(@NonNull DeviceInfo deviceInfo) {
        JavaScriptInjector javaScriptInjector = this.javaScriptInjector;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Backbone.trigger('native:selectedDevice', '");
        outline107.append(deviceInfo.serialNumber);
        outline107.append("');");
        javaScriptInjector.inject(outline107.toString());
    }

    public void selectLibrary(@NonNull HouseholdLibraryInfo householdLibraryInfo) {
        JavaScriptInjector javaScriptInjector = this.javaScriptInjector;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Backbone.trigger('native:mediaOwnerChanged', '");
        outline107.append(householdLibraryInfo.customerId);
        outline107.append("');");
        javaScriptInjector.inject(outline107.toString());
    }

    @JavascriptInterface
    public void sendJsonToNative(String str) {
        try {
            String str2 = "Received JSON from DWJS: " + str;
            execute(str, null);
        } catch (Exception e) {
            String str3 = "Caught exception with sendJsonToNative: " + e;
        }
    }
}
