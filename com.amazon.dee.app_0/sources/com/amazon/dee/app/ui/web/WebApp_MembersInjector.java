package com.amazon.dee.app.ui.web;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.dee.app.metrics.MetricsService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class WebApp_MembersInjector implements MembersInjector<WebApp> {
    private final Provider<AccessibilityInfoBridge> accessibilityInfoBridgeProvider;
    private final Provider<AccountManagementBridge> accountManagementBridgeProvider;
    private final Provider<AlexaDeviceBackgroundImageBridge> alexaDeviceBackgroundImageBridgeProvider;
    private final Provider<AppCacheBridge> appCacheBridgeProvider;
    private final Provider<AppInfoBridge> appInfoBridgeProvider;
    private final Provider<AppLauncherBridge> appLauncherBridgeProvider;
    private final Provider<AppLayoutBridge> appLayoutBridgeProvider;
    private final Provider<AppReloadBridge> appReloadBridgeProvider;
    private final Provider<AudioBridge> audioBridgeProvider;
    private final Provider<CommsDeviceSupport> commsDeviceSupportProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<WebNavigator> environmentWebNavigatorProvider;
    private final Provider<EventBusWebViewBridge> eventBusWebViewBridgeProvider;
    private final Provider<ExternalUILauncherBridge> externalUILauncherBridgeProvider;
    private final Provider<FeedbackBridge> feedbackBridgeProvider;
    private final Provider<HeaderInfoBridge> headerInfoBridgeProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<JavaScriptBridgeOrchestrator> javaScriptBridgeOrchestratorProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptPlayer> javaScriptPlayerProvider;
    private final Provider<MenuSettingsBridge> menuSettingsBridgeProvider;
    private final Provider<MessagingService> messagingServiceProvider;
    private final Provider<MetricsServiceBridge> metricsServiceBridgeProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<NativeHostBridge> nativeHostBridgeProvider;
    private final Provider<NativeLocalStorageBridge> nativeLocalStorageBridgeProvider;
    private final Provider<NavBarToggleBridge> navBarToggleBridgeProvider;
    private final Provider<NavigationBridge> navigationBridgeProvider;
    private final Provider<NetworkEventBridge> networkEventBridgeProvider;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<NotificationServiceBridge> notificationServiceBridgeProvider;
    private final Provider<OOBEBridge> oobeBridgeProvider;
    private final Provider<OrientationBridge> orientationBridgeProvider;
    private final Provider<PermissionsBridge> permissionsBridgeProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<TachyonIdentityBridge> tachyonIdentityBridgeProvider;
    private final Provider<TachyonSettingsBridge> tachyonSettingsBridgeProvider;
    private final Provider<UserMessageService> userMessageServiceProvider;
    private final Provider<VideoPlaybackBridge> videoPlaybackBridgeProvider;
    private final Provider<WebAppMessagingReceiver> webAppMessagingReceiverProvider;
    private final Provider<WebViewDelegate> webViewDelegateProvider;
    private final Provider<WifiBridge> wifiBridgeProvider;
    private final Provider<WifiService> wifiServiceProvider;

    public WebApp_MembersInjector(Provider<JavaScriptBridgeOrchestrator> provider, Provider<JavaScriptPlayer> provider2, Provider<WebViewDelegate> provider3, Provider<JavaScriptInjector> provider4, Provider<PermissionsBridge> provider5, Provider<NavigationBridge> provider6, Provider<NetworkEventBridge> provider7, Provider<AccountManagementBridge> provider8, Provider<AppInfoBridge> provider9, Provider<AccessibilityInfoBridge> provider10, Provider<AppCacheBridge> provider11, Provider<AudioBridge> provider12, Provider<AppReloadBridge> provider13, Provider<AppLauncherBridge> provider14, Provider<ExternalUILauncherBridge> provider15, Provider<HeaderInfoBridge> provider16, Provider<NativeLocalStorageBridge> provider17, Provider<NavBarToggleBridge> provider18, Provider<OrientationBridge> provider19, Provider<NotificationServiceBridge> provider20, Provider<WifiBridge> provider21, Provider<VideoPlaybackBridge> provider22, Provider<NativeHostBridge> provider23, Provider<MetricsServiceBridge> provider24, Provider<AppLayoutBridge> provider25, Provider<EnvironmentService> provider26, Provider<NetworkService> provider27, Provider<WifiService> provider28, Provider<MessagingService> provider29, Provider<WebAppMessagingReceiver> provider30, Provider<IdentityService> provider31, Provider<AlexaDeviceBackgroundImageBridge> provider32, Provider<RoutingService> provider33, Provider<WebNavigator> provider34, Provider<UserMessageService> provider35, Provider<OOBEBridge> provider36, Provider<CommsDeviceSupport> provider37, Provider<TachyonSettingsBridge> provider38, Provider<TachyonIdentityBridge> provider39, Provider<FeedbackBridge> provider40, Provider<Context> provider41, Provider<MetricsService> provider42, Provider<EventBusWebViewBridge> provider43, Provider<MenuSettingsBridge> provider44) {
        this.javaScriptBridgeOrchestratorProvider = provider;
        this.javaScriptPlayerProvider = provider2;
        this.webViewDelegateProvider = provider3;
        this.javaScriptInjectorProvider = provider4;
        this.permissionsBridgeProvider = provider5;
        this.navigationBridgeProvider = provider6;
        this.networkEventBridgeProvider = provider7;
        this.accountManagementBridgeProvider = provider8;
        this.appInfoBridgeProvider = provider9;
        this.accessibilityInfoBridgeProvider = provider10;
        this.appCacheBridgeProvider = provider11;
        this.audioBridgeProvider = provider12;
        this.appReloadBridgeProvider = provider13;
        this.appLauncherBridgeProvider = provider14;
        this.externalUILauncherBridgeProvider = provider15;
        this.headerInfoBridgeProvider = provider16;
        this.nativeLocalStorageBridgeProvider = provider17;
        this.navBarToggleBridgeProvider = provider18;
        this.orientationBridgeProvider = provider19;
        this.notificationServiceBridgeProvider = provider20;
        this.wifiBridgeProvider = provider21;
        this.videoPlaybackBridgeProvider = provider22;
        this.nativeHostBridgeProvider = provider23;
        this.metricsServiceBridgeProvider = provider24;
        this.appLayoutBridgeProvider = provider25;
        this.environmentServiceProvider = provider26;
        this.networkServiceProvider = provider27;
        this.wifiServiceProvider = provider28;
        this.messagingServiceProvider = provider29;
        this.webAppMessagingReceiverProvider = provider30;
        this.identityServiceProvider = provider31;
        this.alexaDeviceBackgroundImageBridgeProvider = provider32;
        this.routingServiceProvider = provider33;
        this.environmentWebNavigatorProvider = provider34;
        this.userMessageServiceProvider = provider35;
        this.oobeBridgeProvider = provider36;
        this.commsDeviceSupportProvider = provider37;
        this.tachyonSettingsBridgeProvider = provider38;
        this.tachyonIdentityBridgeProvider = provider39;
        this.feedbackBridgeProvider = provider40;
        this.contextProvider = provider41;
        this.metricsServiceProvider = provider42;
        this.eventBusWebViewBridgeProvider = provider43;
        this.menuSettingsBridgeProvider = provider44;
    }

    public static MembersInjector<WebApp> create(Provider<JavaScriptBridgeOrchestrator> provider, Provider<JavaScriptPlayer> provider2, Provider<WebViewDelegate> provider3, Provider<JavaScriptInjector> provider4, Provider<PermissionsBridge> provider5, Provider<NavigationBridge> provider6, Provider<NetworkEventBridge> provider7, Provider<AccountManagementBridge> provider8, Provider<AppInfoBridge> provider9, Provider<AccessibilityInfoBridge> provider10, Provider<AppCacheBridge> provider11, Provider<AudioBridge> provider12, Provider<AppReloadBridge> provider13, Provider<AppLauncherBridge> provider14, Provider<ExternalUILauncherBridge> provider15, Provider<HeaderInfoBridge> provider16, Provider<NativeLocalStorageBridge> provider17, Provider<NavBarToggleBridge> provider18, Provider<OrientationBridge> provider19, Provider<NotificationServiceBridge> provider20, Provider<WifiBridge> provider21, Provider<VideoPlaybackBridge> provider22, Provider<NativeHostBridge> provider23, Provider<MetricsServiceBridge> provider24, Provider<AppLayoutBridge> provider25, Provider<EnvironmentService> provider26, Provider<NetworkService> provider27, Provider<WifiService> provider28, Provider<MessagingService> provider29, Provider<WebAppMessagingReceiver> provider30, Provider<IdentityService> provider31, Provider<AlexaDeviceBackgroundImageBridge> provider32, Provider<RoutingService> provider33, Provider<WebNavigator> provider34, Provider<UserMessageService> provider35, Provider<OOBEBridge> provider36, Provider<CommsDeviceSupport> provider37, Provider<TachyonSettingsBridge> provider38, Provider<TachyonIdentityBridge> provider39, Provider<FeedbackBridge> provider40, Provider<Context> provider41, Provider<MetricsService> provider42, Provider<EventBusWebViewBridge> provider43, Provider<MenuSettingsBridge> provider44) {
        return new WebApp_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39, provider40, provider41, provider42, provider43, provider44);
    }

    public static void injectAccessibilityInfoBridge(WebApp webApp, AccessibilityInfoBridge accessibilityInfoBridge) {
        webApp.accessibilityInfoBridge = accessibilityInfoBridge;
    }

    public static void injectAccountManagementBridge(WebApp webApp, AccountManagementBridge accountManagementBridge) {
        webApp.accountManagementBridge = accountManagementBridge;
    }

    public static void injectAlexaDeviceBackgroundImageBridge(WebApp webApp, AlexaDeviceBackgroundImageBridge alexaDeviceBackgroundImageBridge) {
        webApp.alexaDeviceBackgroundImageBridge = alexaDeviceBackgroundImageBridge;
    }

    public static void injectAppCacheBridge(WebApp webApp, AppCacheBridge appCacheBridge) {
        webApp.appCacheBridge = appCacheBridge;
    }

    public static void injectAppInfoBridge(WebApp webApp, AppInfoBridge appInfoBridge) {
        webApp.appInfoBridge = appInfoBridge;
    }

    public static void injectAppLauncherBridge(WebApp webApp, AppLauncherBridge appLauncherBridge) {
        webApp.appLauncherBridge = appLauncherBridge;
    }

    public static void injectAppLayoutBridge(WebApp webApp, AppLayoutBridge appLayoutBridge) {
        webApp.appLayoutBridge = appLayoutBridge;
    }

    public static void injectAppReloadBridge(WebApp webApp, AppReloadBridge appReloadBridge) {
        webApp.appReloadBridge = appReloadBridge;
    }

    public static void injectAudioBridge(WebApp webApp, AudioBridge audioBridge) {
        webApp.audioBridge = audioBridge;
    }

    public static void injectCommsDeviceSupport(WebApp webApp, CommsDeviceSupport commsDeviceSupport) {
        webApp.commsDeviceSupport = commsDeviceSupport;
    }

    public static void injectContext(WebApp webApp, Context context) {
        webApp.context = context;
    }

    public static void injectEnvironmentService(WebApp webApp, EnvironmentService environmentService) {
        webApp.environmentService = environmentService;
    }

    public static void injectEnvironmentWebNavigator(WebApp webApp, WebNavigator webNavigator) {
        webApp.environmentWebNavigator = webNavigator;
    }

    public static void injectEventBusWebViewBridge(WebApp webApp, EventBusWebViewBridge eventBusWebViewBridge) {
        webApp.eventBusWebViewBridge = eventBusWebViewBridge;
    }

    public static void injectExternalUILauncherBridge(WebApp webApp, ExternalUILauncherBridge externalUILauncherBridge) {
        webApp.externalUILauncherBridge = externalUILauncherBridge;
    }

    public static void injectFeedbackBridge(WebApp webApp, FeedbackBridge feedbackBridge) {
        webApp.feedbackBridge = feedbackBridge;
    }

    public static void injectHeaderInfoBridge(WebApp webApp, HeaderInfoBridge headerInfoBridge) {
        webApp.headerInfoBridge = headerInfoBridge;
    }

    public static void injectIdentityService(WebApp webApp, IdentityService identityService) {
        webApp.identityService = identityService;
    }

    public static void injectJavaScriptBridgeOrchestrator(WebApp webApp, JavaScriptBridgeOrchestrator javaScriptBridgeOrchestrator) {
        webApp.javaScriptBridgeOrchestrator = javaScriptBridgeOrchestrator;
    }

    public static void injectJavaScriptInjector(WebApp webApp, JavaScriptInjector javaScriptInjector) {
        webApp.javaScriptInjector = javaScriptInjector;
    }

    public static void injectJavaScriptPlayer(WebApp webApp, JavaScriptPlayer javaScriptPlayer) {
        webApp.javaScriptPlayer = javaScriptPlayer;
    }

    public static void injectMenuSettingsBridge(WebApp webApp, MenuSettingsBridge menuSettingsBridge) {
        webApp.menuSettingsBridge = menuSettingsBridge;
    }

    public static void injectMessagingService(WebApp webApp, MessagingService messagingService) {
        webApp.messagingService = messagingService;
    }

    public static void injectMetricsService(WebApp webApp, MetricsService metricsService) {
        webApp.metricsService = metricsService;
    }

    public static void injectMetricsServiceBridge(WebApp webApp, MetricsServiceBridge metricsServiceBridge) {
        webApp.metricsServiceBridge = metricsServiceBridge;
    }

    public static void injectNativeHostBridge(WebApp webApp, NativeHostBridge nativeHostBridge) {
        webApp.nativeHostBridge = nativeHostBridge;
    }

    public static void injectNativeLocalStorageBridge(WebApp webApp, NativeLocalStorageBridge nativeLocalStorageBridge) {
        webApp.nativeLocalStorageBridge = nativeLocalStorageBridge;
    }

    public static void injectNavBarToggleBridge(WebApp webApp, NavBarToggleBridge navBarToggleBridge) {
        webApp.navBarToggleBridge = navBarToggleBridge;
    }

    public static void injectNavigationBridge(WebApp webApp, NavigationBridge navigationBridge) {
        webApp.navigationBridge = navigationBridge;
    }

    public static void injectNetworkEventBridge(WebApp webApp, NetworkEventBridge networkEventBridge) {
        webApp.networkEventBridge = networkEventBridge;
    }

    public static void injectNetworkService(WebApp webApp, NetworkService networkService) {
        webApp.networkService = networkService;
    }

    public static void injectNotificationServiceBridge(WebApp webApp, NotificationServiceBridge notificationServiceBridge) {
        webApp.notificationServiceBridge = notificationServiceBridge;
    }

    public static void injectOobeBridge(WebApp webApp, OOBEBridge oOBEBridge) {
        webApp.oobeBridge = oOBEBridge;
    }

    public static void injectOrientationBridge(WebApp webApp, OrientationBridge orientationBridge) {
        webApp.orientationBridge = orientationBridge;
    }

    public static void injectPermissionsBridge(WebApp webApp, PermissionsBridge permissionsBridge) {
        webApp.permissionsBridge = permissionsBridge;
    }

    public static void injectRoutingService(WebApp webApp, RoutingService routingService) {
        webApp.routingService = routingService;
    }

    public static void injectTachyonIdentityBridge(WebApp webApp, TachyonIdentityBridge tachyonIdentityBridge) {
        webApp.tachyonIdentityBridge = tachyonIdentityBridge;
    }

    public static void injectTachyonSettingsBridge(WebApp webApp, TachyonSettingsBridge tachyonSettingsBridge) {
        webApp.tachyonSettingsBridge = tachyonSettingsBridge;
    }

    public static void injectUserMessageService(WebApp webApp, UserMessageService userMessageService) {
        webApp.userMessageService = userMessageService;
    }

    public static void injectVideoPlaybackBridge(WebApp webApp, VideoPlaybackBridge videoPlaybackBridge) {
        webApp.videoPlaybackBridge = videoPlaybackBridge;
    }

    public static void injectWebAppMessagingReceiver(WebApp webApp, WebAppMessagingReceiver webAppMessagingReceiver) {
        webApp.webAppMessagingReceiver = webAppMessagingReceiver;
    }

    public static void injectWebViewDelegate(WebApp webApp, WebViewDelegate webViewDelegate) {
        webApp.webViewDelegate = webViewDelegate;
    }

    public static void injectWifiBridge(WebApp webApp, WifiBridge wifiBridge) {
        webApp.wifiBridge = wifiBridge;
    }

    public static void injectWifiService(WebApp webApp, WifiService wifiService) {
        webApp.wifiService = wifiService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(WebApp webApp) {
        injectJavaScriptBridgeOrchestrator(webApp, this.javaScriptBridgeOrchestratorProvider.mo10268get());
        injectJavaScriptPlayer(webApp, this.javaScriptPlayerProvider.mo10268get());
        injectWebViewDelegate(webApp, this.webViewDelegateProvider.mo10268get());
        injectJavaScriptInjector(webApp, this.javaScriptInjectorProvider.mo10268get());
        injectPermissionsBridge(webApp, this.permissionsBridgeProvider.mo10268get());
        injectNavigationBridge(webApp, this.navigationBridgeProvider.mo10268get());
        injectNetworkEventBridge(webApp, this.networkEventBridgeProvider.mo10268get());
        injectAccountManagementBridge(webApp, this.accountManagementBridgeProvider.mo10268get());
        injectAppInfoBridge(webApp, this.appInfoBridgeProvider.mo10268get());
        injectAccessibilityInfoBridge(webApp, this.accessibilityInfoBridgeProvider.mo10268get());
        injectAppCacheBridge(webApp, this.appCacheBridgeProvider.mo10268get());
        injectAudioBridge(webApp, this.audioBridgeProvider.mo10268get());
        injectAppReloadBridge(webApp, this.appReloadBridgeProvider.mo10268get());
        injectAppLauncherBridge(webApp, this.appLauncherBridgeProvider.mo10268get());
        injectExternalUILauncherBridge(webApp, this.externalUILauncherBridgeProvider.mo10268get());
        injectHeaderInfoBridge(webApp, this.headerInfoBridgeProvider.mo10268get());
        injectNativeLocalStorageBridge(webApp, this.nativeLocalStorageBridgeProvider.mo10268get());
        injectNavBarToggleBridge(webApp, this.navBarToggleBridgeProvider.mo10268get());
        injectOrientationBridge(webApp, this.orientationBridgeProvider.mo10268get());
        injectNotificationServiceBridge(webApp, this.notificationServiceBridgeProvider.mo10268get());
        injectWifiBridge(webApp, this.wifiBridgeProvider.mo10268get());
        injectVideoPlaybackBridge(webApp, this.videoPlaybackBridgeProvider.mo10268get());
        injectNativeHostBridge(webApp, this.nativeHostBridgeProvider.mo10268get());
        injectMetricsServiceBridge(webApp, this.metricsServiceBridgeProvider.mo10268get());
        injectAppLayoutBridge(webApp, this.appLayoutBridgeProvider.mo10268get());
        injectEnvironmentService(webApp, this.environmentServiceProvider.mo10268get());
        injectNetworkService(webApp, this.networkServiceProvider.mo10268get());
        injectWifiService(webApp, this.wifiServiceProvider.mo10268get());
        injectMessagingService(webApp, this.messagingServiceProvider.mo10268get());
        injectWebAppMessagingReceiver(webApp, this.webAppMessagingReceiverProvider.mo10268get());
        injectIdentityService(webApp, this.identityServiceProvider.mo10268get());
        injectAlexaDeviceBackgroundImageBridge(webApp, this.alexaDeviceBackgroundImageBridgeProvider.mo10268get());
        injectRoutingService(webApp, this.routingServiceProvider.mo10268get());
        injectEnvironmentWebNavigator(webApp, this.environmentWebNavigatorProvider.mo10268get());
        injectUserMessageService(webApp, this.userMessageServiceProvider.mo10268get());
        injectOobeBridge(webApp, this.oobeBridgeProvider.mo10268get());
        injectCommsDeviceSupport(webApp, this.commsDeviceSupportProvider.mo10268get());
        injectTachyonSettingsBridge(webApp, this.tachyonSettingsBridgeProvider.mo10268get());
        injectTachyonIdentityBridge(webApp, this.tachyonIdentityBridgeProvider.mo10268get());
        injectFeedbackBridge(webApp, this.feedbackBridgeProvider.mo10268get());
        injectContext(webApp, this.contextProvider.mo10268get());
        injectMetricsService(webApp, this.metricsServiceProvider.mo10268get());
        injectEventBusWebViewBridge(webApp, this.eventBusWebViewBridgeProvider.mo10268get());
        injectMenuSettingsBridge(webApp, this.menuSettingsBridgeProvider.mo10268get());
    }
}
