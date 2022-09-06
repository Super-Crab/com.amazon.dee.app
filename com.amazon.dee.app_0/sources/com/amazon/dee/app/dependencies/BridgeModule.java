package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.logupload.LogRetriever;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.dee.app.services.datastore.DataStoreService;
import com.amazon.dee.app.services.header.HeaderCacheService;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.dee.app.ui.web.AccessibilityInfoBridge;
import com.amazon.dee.app.ui.web.AccountManagementBridge;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.dee.app.ui.web.AppCacheBridge;
import com.amazon.dee.app.ui.web.AppInfoBridge;
import com.amazon.dee.app.ui.web.AppLauncherBridge;
import com.amazon.dee.app.ui.web.AppLayoutBridge;
import com.amazon.dee.app.ui.web.AppReloadBridge;
import com.amazon.dee.app.ui.web.AudioBridge;
import com.amazon.dee.app.ui.web.EventBusWebViewBridge;
import com.amazon.dee.app.ui.web.ExternalUILauncherBridge;
import com.amazon.dee.app.ui.web.FeedbackBridge;
import com.amazon.dee.app.ui.web.HeaderInfoBridge;
import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptPlayer;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.MenuSettingsBridge;
import com.amazon.dee.app.ui.web.MetricsServiceBridge;
import com.amazon.dee.app.ui.web.NativeHostBridge;
import com.amazon.dee.app.ui.web.NativeLocalStorageBridge;
import com.amazon.dee.app.ui.web.NavBarToggleBridge;
import com.amazon.dee.app.ui.web.NavigationBridge;
import com.amazon.dee.app.ui.web.NetworkEventBridge;
import com.amazon.dee.app.ui.web.NotificationServiceBridge;
import com.amazon.dee.app.ui.web.OOBEBridge;
import com.amazon.dee.app.ui.web.OrientationBridge;
import com.amazon.dee.app.ui.web.PermissionsBridge;
import com.amazon.dee.app.ui.web.TachyonIdentityBridge;
import com.amazon.dee.app.ui.web.TachyonSettingsBridge;
import com.amazon.dee.app.ui.web.VideoPlaybackBridge;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import com.amazon.dee.app.ui.web.WifiBridge;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsService;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class BridgeModule {
    @Provides
    public AccessibilityInfoBridge provideAccessibilityInfoBridge(Context context, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new AccessibilityInfoBridge(context, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public AccountManagementBridge provideAccountManagementBridge(MAPAccountManager mAPAccountManager, AccountService accountService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, WebViewDelegate webViewDelegate, RoutingService routingService, IdentityService identityService, JavaScriptDelegate javaScriptDelegate, MetricsService metricsService) {
        return new AccountManagementBridge(mAPAccountManager, accountService, javaScriptInjector, javaScriptResponseQueue, routingService, identityService, javaScriptDelegate, webViewDelegate, metricsService);
    }

    @Provides
    public AppCacheBridge provideAppCacheBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new AppCacheBridge(javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public AppInfoBridge provideAppInfoBridge(Context context, Activity activity, DeviceInformation deviceInformation, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new AppInfoBridge(context, activity, javaScriptInjector, javaScriptResponseQueue, deviceInformation);
    }

    @Provides
    public AppLauncherBridge provideAppLauncherBridge(Context context, Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new AppLauncherBridge(context, activity, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public AppLayoutBridge provideAppLayoutBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new AppLayoutBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public AppReloadBridge provideAppReloadBridge(WebViewDelegate webViewDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new AppReloadBridge(webViewDelegate, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public AudioBridge provideAudioBridge(Context context, JavaScriptPlayer javaScriptPlayer, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new AudioBridge(context, javaScriptPlayer, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public AlexaDeviceBackgroundImageBridge provideBackgroundImageBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, Gson gson) {
        return new AlexaDeviceBackgroundImageBridge(activity, javaScriptInjector, javaScriptResponseQueue, gson);
    }

    @Provides
    public EventBusWebViewBridge provideEventBusWebViewBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, EventBus eventBus) {
        return new EventBusWebViewBridge(javaScriptInjector, javaScriptResponseQueue, eventBus);
    }

    @Provides
    public ExternalUILauncherBridge provideExternalUILauncherBridge(Activity activity, WebViewDelegate webViewDelegate, EnvironmentService environmentService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new ExternalUILauncherBridge(activity, webViewDelegate, environmentService, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public FeedbackBridge provideFeedbackBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CoralService coralService, LogRetriever logRetriever) {
        return new FeedbackBridge(activity, javaScriptInjector, javaScriptResponseQueue, coralService, logRetriever);
    }

    @Provides
    public HeaderInfoBridge provideHeaderInfoBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, HeaderCacheService headerCacheService) {
        return new HeaderInfoBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue, headerCacheService);
    }

    @Provides
    public LogRetriever provideLogRetriever() {
        return new LogRetriever();
    }

    @Provides
    public MenuSettingsBridge provideMenuSettingsBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, AlexaMenu alexaMenu) {
        return new MenuSettingsBridge(javaScriptInjector, javaScriptResponseQueue, alexaMenu);
    }

    @Provides
    public MetricsServiceBridge provideMetricsServiceBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, MetricsService metricsService, Gson gson) {
        return new MetricsServiceBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue, metricsService, gson);
    }

    @Provides
    public NativeHostBridge provideNativeHostBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new NativeHostBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public NativeLocalStorageBridge provideNativeLocalStorageBridge(DataStoreService dataStoreService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new NativeLocalStorageBridge(dataStoreService, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public NavBarToggleBridge provideNavBarToggleBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new NavBarToggleBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public NavigationBridge provideNavigationBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, RoutingService routingService, WebViewDelegate webViewDelegate, IdentityService identityService, Lazy<CommsServiceV2> lazy) {
        return new NavigationBridge(javaScriptInjector, javaScriptResponseQueue, routingService, lazy, webViewDelegate, identityService);
    }

    @Provides
    public NetworkEventBridge provideNetworkEventBridge(Context context, NetworkService networkService, WifiService wifiService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new NetworkEventBridge(context, networkService, wifiService, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public NotificationServiceBridge provideNotificationServiceBridge(Context context, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new NotificationServiceBridge(context, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public OOBEBridge provideOOBEBridge(Activity activity, Lazy<ConversationService> lazy, IdentityService identityService, EnvironmentService environmentService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, WebViewDelegate webViewDelegate, AccountService accountService, MetricsService metricsService, RoutingService routingService, EventBus eventBus, HandsFreeSetup handsFreeSetup, Lazy<CommsServiceV2> lazy2) {
        return new OOBEBridge(activity, lazy, identityService, environmentService, javaScriptInjector, javaScriptResponseQueue, webViewDelegate, accountService, metricsService, routingService, eventBus, handsFreeSetup, lazy2);
    }

    @Provides
    public OrientationBridge provideOrientationBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new OrientationBridge(activity, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public PermissionsBridge providePermissionsBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, Gson gson) {
        return new PermissionsBridge(activity, javaScriptInjector, javaScriptResponseQueue, gson);
    }

    @Provides
    public TachyonIdentityBridge provideTachyonIdentityBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CommsManager commsManager, Lazy<CommsServiceV2> lazy) {
        return new TachyonIdentityBridge(javaScriptInjector, javaScriptResponseQueue, commsManager, lazy);
    }

    @Provides
    public TachyonSettingsBridge provideTachyonSettingsBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CommsManager commsManager, IdentityPreferencesProvider identityPreferencesProvider) {
        return new TachyonSettingsBridge(javaScriptInjector, javaScriptResponseQueue, commsManager, identityPreferencesProvider);
    }

    @Provides
    public VideoPlaybackBridge provideVideoPlaybackBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new VideoPlaybackBridge(activity, javaScriptInjector, javaScriptResponseQueue);
    }

    @Provides
    public WifiBridge provideWifiBridge(Activity activity, WifiService wifiService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return new WifiBridge(activity, wifiService, javaScriptInjector, javaScriptResponseQueue);
    }
}
