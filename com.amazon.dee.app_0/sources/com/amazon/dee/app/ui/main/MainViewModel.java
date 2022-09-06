package com.amazon.dee.app.ui.main;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.enrollment.route.EnrollmentRoutingAdapter;
import com.amazon.alexa.enrollment.route.KidsEnrollmentRoutingAdapter;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.presence.battery.BatteryOptimizationRoutingAdapter;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.RouteWatcher;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteApiUtils;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.api.RoutingObserver;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.viewmanagement.impl.ViewControllerFragment;
import com.amazon.alexa.viewmanagement.impl.ViewManagerRoutingAdapter;
import com.amazon.alexa.viewmanagement.impl.ViewPresenter;
import com.amazon.alexa.voice.handsfree.HandsFreeRoutingAdapter;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter;
import com.amazon.dee.app.R;
import com.amazon.dee.app.dependencies.DriveModeMainModule;
import com.amazon.dee.app.dependencies.HomeModule;
import com.amazon.dee.app.elements.ElementsRoutingAdapter;
import com.amazon.dee.app.elements.ElementsUtils;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import com.amazon.dee.app.framework.ViewModel;
import com.amazon.dee.app.services.appreviewrequest.AppReviewRequestService;
import com.amazon.dee.app.services.appreviewrequest.AppReviewRequestServiceConstants;
import com.amazon.dee.app.services.appreviewrequest.MainActivityVisibility;
import com.amazon.dee.app.services.header.HeaderCacheService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.messaging.MessagingHandler;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.services.toolbar.ToolbarService;
import com.amazon.dee.app.services.toolbar.ToolbarViewModel;
import com.amazon.dee.app.services.toolbar.ToolbarWatcher;
import com.amazon.dee.app.ui.external.ExternalUIActivity;
import com.amazon.dee.app.ui.main.StartupStateMachine;
import com.amazon.dee.app.ui.menu.FirstInteraction;
import com.amazon.dee.app.ui.nowplaying.NowPlayingHandler;
import com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel;
import com.amazon.dee.app.ui.preload.PreloadAttributionUIManager;
import com.amazon.dee.app.ui.util.CacheClearOperations;
import com.amazon.dee.app.ui.util.LocationPermissionMetricHelper;
import com.amazon.dee.app.ui.util.ObservableRouteAdapterId;
import com.amazon.dee.app.ui.util.SonarUrlHandler;
import com.amazon.dee.app.ui.web.AlexaWebView;
import com.amazon.dee.app.ui.web.ExternalUILauncherBridge;
import com.amazon.dee.app.ui.web.WebConstants;
import com.amazon.dee.app.ui.web.WebRoutingAdapter;
import com.amazon.dee.app.util.WebUtils;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.ui.main.ConversationRoutingAdapter;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.services.ConversationUIService;
import com.amazon.deecomms.ui.util.CommsRoutingHelper;
import com.amazon.latencyinfra.LatencyInfra;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.common.base.Supplier;
import dagger.Lazy;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import rx.Completable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
/* loaded from: classes12.dex */
public class MainViewModel implements ViewModel, RoutingObserver, AlexaWebView.OnPageFinishedListener, NowPlayingHandler, MainActivityVisibility {
    private static final String APP_WARM_START = "app:warm:start";
    private static final String IS_HOME_VIEW_BUNDLE_KEY = "IsHomeView";
    private static final String IS_REACT_NATIVE_VIEW_BUNDLE_KEY = "IsReactNativeView";
    private static final String QUICK_ACTIONS_WIDGET_CLICK_TIME_KEY = "QUICK_ACTIONS_WIDGET_CLICK_TIME";
    private static final String ROUTE_NAME_KEY = "routeName";
    @VisibleForTesting
    static final String ROUTING = "routing";
    public static final int TAB_CONVERSATIONS = 2;
    public static final int TAB_DEVICES = 4;
    public static final int TAB_HOME = 1;
    public static final int TAB_NAVIGATION = 5;
    public static final int TAB_NONE = 0;
    public static final int TAB_NOW_PLAYING = 3;
    private static final String TAG = Log.tag(MainViewModel.class);
    private static final String TOOLBAR = "toolbar";
    @Inject
    AccountService accountService;
    @Inject
    Activity activity;
    @Inject
    RoutingRegistryAdapter adapters;
    @Inject
    AppReviewRequestService appReviewRequestService;
    @Inject
    BatteryOptimizationRoutingAdapter batteryOptimizationRoutingAdapter;
    @Inject
    BridgeStatusService bridgeStatusService;
    @Inject
    CacheClearOperations cacheClearOperations;
    @VisibleForTesting
    FeatureServiceV2.FeatureUpdateListener careHubFeatureUpdateListener;
    @VisibleForTesting
    boolean coldStart;
    @Inject
    Lazy<CommsManager> commsManager;
    @Inject
    CommsRoutingHelper commsRoutingHelper;
    @Inject
    Lazy<CommsServiceV2> commsServiceV2;
    @Inject
    ConversationRoutingAdapter conversationRoutingAdapter;
    @Inject
    Lazy<ConversationService> conversationService;
    @Inject
    Lazy<ConversationUIService> conversationUIService;
    @Inject
    CrashMetadata crashMetadata;
    @VisibleForTesting
    FeatureServiceV2.FeatureUpdateListener devicesChannelFeatureUpdateListener;
    @Inject
    @Named(DriveModeMainModule.DRIVE_MODE_INGRESS_ROUTING_ADAPTER)
    RoutingAdapter driveModeRoutingAdapter;
    @Inject
    Lazy<DriveModeService> driveModeService;
    @Inject
    Lazy<DriveModeMainActivityCompanion.ViewModel> driveModeViewModel;
    @Inject
    ElementsRoutingAdapter elementsRoutingAdapter;
    @Inject
    EnrollmentRoutingAdapter enrollmentRoutingAdapter;
    @Inject
    EnvironmentService environmentService;
    @Inject
    EventBus eventBus;
    @Inject
    EventBusMessagingReceiver eventBusMessagingReceiver;
    @Inject
    AuthenticationExceptionHandler exceptionHandler;
    @Inject
    FeatureQuery featureQuery;
    @Inject
    Lazy<FeatureServiceV2> featureServiceV2Lazy;
    @VisibleForTesting
    FeatureServiceV2.FeatureUpdateListener footerTitlesUpdateFeatureUpdateListener;
    @Inject
    HandsFreeRoutingAdapter handsFreeRoutingAdapter;
    @Inject
    HandsFreeSetup handsFreeSetup;
    @Inject
    HeaderCacheService headerCacheService;
    @VisibleForTesting
    FeatureServiceV2.FeatureUpdateListener homeChannelFeatureUpdateListener;
    @Inject
    @Named(HomeModule.HOME_ROUTING_ADAPTER)
    RoutingAdapter homeRoutingAdapter;
    MultiFilterSubscriber identityChangedSubscriber;
    @Inject
    IdentityService identityService;
    @Inject
    IntentFactory intentFactory;
    @Inject
    KidsEnrollmentRoutingAdapter kidsEnrollmentRoutingAdapter;
    @Inject
    LatencyInfra latencyInfra;
    @Inject
    LocationPermissionMetricHelper locationPermissionMetricHelper;
    @Inject
    MainActivity mainActivity;
    @Inject
    MainRoutingAdapter mainRoutingAdapter;
    @Inject
    MetricsService metricsService;
    @Inject
    MetricsServiceV2 metricsServiceV2;
    @Inject
    Mobilytics mobilytics;
    @Inject
    Lazy<ModeService> modeService;
    @Inject
    NowPlayingViewModel nowPlayingViewModel;
    @Inject
    PersistentStorage.Factory persistentStorageFactory;
    @Inject
    Lazy<PreloadAttributionUIManager> preloadAttributionUIManager;
    @Inject
    ReactBridgeService reactBridgeService;
    @Inject
    ReactFeatureManager reactFeatureManager;
    RoutingService.RouteInterceptor redirectingRouteInterceptor;
    @Inject
    RouteFeatureGroupRegistry routeFeatureGroupRegistry;
    @Inject
    RouteWatcher routeWatcher;
    @Inject
    RoutingRegistry routes;
    @Inject
    RoutingService routingService;
    @Inject
    Lazy<SonarUrlHandler> sonarUrlHandler;
    private StartupStateMachine stateMachine;
    @Inject
    TabLayoutStatusService tabLayoutService;
    @Inject
    TaskManager taskManager;
    @Inject
    TestArgumentsService testArguments;
    Subscriber themePreferenceSubscriber;
    @Inject
    Lazy<ThemeRecorder> themeRecorder;
    public ToolbarViewModel toolbar;
    @Inject
    ToolbarService toolbarService;
    @Inject
    ToolbarWatcher toolbarWatcher;
    UserIdentity updatedUser;
    @Inject
    ViewManagerRoutingAdapter viewManagerRoutingAdapter;
    @Inject
    RoutingViewService viewService;
    @Inject
    VoiceRoutingAdapter voiceRoutingAdapter;
    @Inject
    VoiceService voiceService;
    @Inject
    WebRoutingAdapter webRoutingAdapter;
    public final ObservableField<RouteContext> currentRouteContext = new ObservableField<>();
    public final ObservableField<CharSequence> headerTitle = new ObservableField<>();
    public final ObservableField<Themes> currentTheme = new ObservableField<>();
    public final ObservableInt currentTab = new ObservableInt();
    public final ObservableInt headerTitleVisibility = new ObservableInt(8);
    public final ObservableInt footerTitleVisibility = new ObservableInt(8);
    public final ObservableBoolean isConversationVisible = new ObservableBoolean();
    public final ObservableBoolean isDisabled = new ObservableBoolean();
    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableBoolean isBackVisible = new ObservableBoolean();
    public final ObservableBoolean showHeader = new ObservableBoolean();
    public final ObservableBoolean disableInteraction = new ObservableBoolean();
    public final ObservableBoolean isSoftInputVisible = new ObservableBoolean();
    public final ObservableBoolean isFullScreenMode = new ObservableBoolean(true);
    public final ObservableBoolean pendingViewIsReactNative = new ObservableBoolean();
    public final ObservableBoolean isReactNativeView = new ObservableBoolean();
    public final ObservableBoolean isHomeView = new ObservableBoolean(false);
    public final ObservableBoolean isNowPlayingElementsEnabled = new ObservableBoolean(false);
    public final ObservableBoolean isBlockAlexaListsViewEnabled = new ObservableBoolean(false);
    public final ObservableBoolean isUserKnown = new ObservableBoolean(false);
    public final ObservableBoolean useChannelsTheme = new ObservableBoolean(false);
    public final ObservableBoolean channelsHomeEnabled = new ObservableBoolean(true);
    public final ObservableBoolean channelsDevicesEnabled = new ObservableBoolean(false);
    public final ObservableBoolean tabAnimationFinished = new ObservableBoolean(true);
    public final ObservableBoolean isDriveModeFooterVisible = new ObservableBoolean(false);
    public final ObservableBoolean isRemoteManagementEnabled = new ObservableBoolean(false);
    public final ObservableBoolean isViewManagerVisible = new ObservableRouteAdapterId(this.currentRouteContext, 18);
    public final ObservableBoolean isHomeTransition = new ObservableBoolean(this.currentRouteContext, this.isHomeView, this.isReactNativeView) { // from class: com.amazon.dee.app.ui.main.MainViewModel.1
        @Override // androidx.databinding.ObservableBoolean
        public boolean get() {
            return !MainViewModel.this.isHomeView.get() && MainViewModel.this.currentRouteContext.get() != null && MainViewModel.this.currentRouteContext.get().getRoute().getAdapterId() == 1 && !MainViewModel.this.isReactNativeView.get();
        }
    };
    public final ObservableBoolean isHomeVisible = new ObservableBoolean(this.isHomeView, this.isFullScreenMode, this.isReactNativeView) { // from class: com.amazon.dee.app.ui.main.MainViewModel.2
        @Override // androidx.databinding.ObservableBoolean
        public boolean get() {
            return MainViewModel.this.isHomeView.get() && !MainViewModel.this.isFullScreenMode.get() && !MainViewModel.this.isReactNativeView.get();
        }
    };
    public final ObservableBoolean isToolbarVisible = new ObservableBoolean(this.showHeader, this.isReactNativeView, this.isHomeView, this.isHomeTransition) { // from class: com.amazon.dee.app.ui.main.MainViewModel.3
        @Override // androidx.databinding.ObservableBoolean
        public boolean get() {
            return MainViewModel.this.showHeader.get() && !MainViewModel.this.isReactNativeView.get() && !MainViewModel.this.isHomeView.get() && !MainViewModel.this.isHomeTransition.get();
        }
    };
    public final ObservableBoolean isTabBarVisible = new ObservableBoolean(this.isFullScreenMode, this.isSoftInputVisible, this.isRemoteManagementEnabled) { // from class: com.amazon.dee.app.ui.main.MainViewModel.4
        @Override // androidx.databinding.ObservableBoolean
        public boolean get() {
            return !MainViewModel.this.isFullScreenMode.get() && !MainViewModel.this.isSoftInputVisible.get() && !MainViewModel.this.isRemoteManagementEnabled.get();
        }
    };
    public final ObservableBoolean hideWebView = new ObservableBoolean(this.isDisabled, this.isLoading, this.isReactNativeView, this.isHomeTransition, this.isHomeView, this.currentTab, this.pendingViewIsReactNative, this.disableInteraction, this.isViewManagerVisible) { // from class: com.amazon.dee.app.ui.main.MainViewModel.5
        @Override // androidx.databinding.ObservableBoolean
        public boolean get() {
            return MainViewModel.this.isDisabled.get() || MainViewModel.this.isLoading.get() || MainViewModel.this.isReactNativeView.get() || MainViewModel.this.isHomeTransition.get() || MainViewModel.this.isHomeView.get() || MainViewModel.this.currentTab.get() == 2 || MainViewModel.this.pendingViewIsReactNative.get() || MainViewModel.this.disableInteraction.get() || MainViewModel.this.isViewManagerVisible.get();
        }
    };
    public final ObservableInt backgroundColor = new ObservableInt(this.currentRouteContext) { // from class: com.amazon.dee.app.ui.main.MainViewModel.6
        @Override // androidx.databinding.ObservableInt
        public int get() {
            Route route;
            if (MainViewModel.this.identityService.getUser(MainViewModel.TAG) == null) {
                return new NoUserTheme(MainViewModel.this.activity).getBackgroundColor();
            }
            Route.Theme theme = null;
            RouteContext routeContext = MainViewModel.this.currentRouteContext.get();
            if (routeContext != null && (route = routeContext.getRoute()) != null) {
                if (ElementsUtils.isElementsRoute(route)) {
                    MainViewModel mainViewModel = MainViewModel.this;
                    theme = ElementsUtils.getLoggedOnUsersEffectiveTheme(mainViewModel.activity, mainViewModel.identityService.getUser(MainViewModel.TAG), route);
                } else {
                    theme = route.getTheme();
                }
            }
            if (theme == null) {
                theme = Route.Theme.DEFAULT;
            }
            return theme.getBackgroundColor(MainViewModel.this.activity);
        }
    };
    private boolean isMainActivityBackgrounded = false;
    private boolean wasSSOAuthenticated = false;
    private boolean isFirstMenuInteraction = true;

    /* renamed from: com.amazon.dee.app.ui.main.MainViewModel$13  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$api$navigation$CommsView = new int[CommsView.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ContactList.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ReactNativeConversationList.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Nullable
    private RouteContext createRouteFromDeepLinkUri(@NonNull final Uri uri) {
        if (LatencyService.isStartActive()) {
            LatencyService.invalidate();
        }
        this.eventBusMessagingReceiver.publishDeepLinkClick(uri);
        if (this.sonarUrlHandler.mo358get().isSonarUrl(uri)) {
            this.taskManager.getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainViewModel$iUCKGiZ92xQiuAJ02NULxaH7I3w
                @Override // java.lang.Runnable
                public final void run() {
                    MainViewModel.this.lambda$createRouteFromDeepLinkUri$5$MainViewModel(uri);
                }
            });
            uri = this.sonarUrlHandler.mo358get().getDecodedUrl(uri);
            if (!this.sonarUrlHandler.mo358get().handleUrl(this.activity, uri)) {
                String str = "Cannot handle this link from Sonar: " + uri + ". Launching the browser instead.";
                logDeeplinkMetric(uri, AlexaMetricsConstants.MetricEvents.Deeplink.SONAR_FALLBACK_DEEPLINK_URL);
                return this.routingService.route(RouteName.HOME).create();
            }
        }
        String route = WebUtils.getRoute(uri);
        if (TextUtils.isEmpty(route)) {
            route = uri.toString();
        }
        if (!TextUtils.isEmpty(route)) {
            RoutingService.RoutingBuilder match = this.routingService.match(route);
            if (match == null) {
                match = this.routingService.route("web").with(RouteParameter.ROUTE, route);
                String str2 = "Cannot handle this link: " + uri + ". Invalid destination.";
                logDeeplinkMetric(uri, AlexaMetricsConstants.MetricEvents.Deeplink.INVALID_DESTINATION_DEEPLINK_URL);
            }
            return match.create();
        }
        return null;
    }

    private boolean determineBackVisible(RouteContext routeContext) {
        return !routeContext.getRoute().isRoot() && !routeContext.isFromMainMenu();
    }

    private void dismissKeyBoard() {
        View currentFocus;
        InputMethodManager inputMethodManager = (InputMethodManager) this.activity.getSystemService("input_method");
        if (!inputMethodManager.isAcceptingText() || (currentFocus = this.activity.getCurrentFocus()) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    private AlexaApplication getApplication() {
        return (AlexaApplication) this.activity.getApplication();
    }

    private Map<String, Object> getCustomEntriesWithOwnerIdentifier(String str) {
        return GeneratedOutlineSupport1.outline133("ownerIdentifier", str);
    }

    private void killTheApp() {
        System.exit(0);
    }

    private void notifyUserLoggedOut() {
        this.eventBus.publish(new Message.Builder().setEventType(AppReviewRequestServiceConstants.APP_REVIEW_PREFS_USER_LOGGED_OUT).build());
    }

    private void recordFirstMenuInteraction(String str, String str2) {
        if (this.isFirstMenuInteraction) {
            this.isFirstMenuInteraction = false;
            recordDiagnosticEvent(FirstInteraction.METRICS.containsKey(str2) ? FirstInteraction.METRICS.get(str2) : FirstInteraction.UNKNOWN, str, TAG);
        }
    }

    private void restoreCachedHeaderAsNeeded() {
        RouteContext currentRoute = this.routingService.getCurrentRoute();
        if (currentRoute != null) {
            if (!RouteCategories.ROUTES_WITH_CACHED_HEADERS.contains(currentRoute.getRoute().getName())) {
                return;
            }
            this.headerCacheService.loadCachedRequest();
        }
    }

    private void routeWithMetrics(String str, String str2, String str3, String str4, boolean z) {
        this.routingService.stopLoadingTimeout();
        if (!this.routingService.popFromBackStack(str)) {
            this.routingService.navigate(str, z);
        } else {
            this.headerCacheService.loadCachedRequest();
        }
        recordFirstMenuInteraction(str2, str4);
        recordEngagement(str4, str2, str3);
    }

    private void startUserEngagementTimer() {
        this.eventBus.publish(new Message.Builder().setEventType(AppReviewRequestServiceConstants.APP_REVIEW_PREFS_USER_ENGAGEMENT_TIMER_START).build());
    }

    private void startWithRoute(@Nullable RouteContext routeContext) {
        this.stateMachine = createStartupStateMachine();
        if (routeContext != null) {
            this.stateMachine.setTargetRoute(routeContext);
        }
        LatencyService.statemachineStarted();
        this.stateMachine.start();
        LatencyService.stateMachineComplete();
        UserIdentity user = this.identityService.getUser(TAG);
        String str = this.coldStart ? "APP_COLD_SIGN_IN_SUCCESS" : "APP_WARM_SIGN_IN_SUCCESS";
        Mobilytics mobilytics = this.mobilytics;
        boolean z = user != null;
        String str2 = TAG;
        mobilytics.recordOccurrence(str, z, str2, str2, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        this.locationPermissionMetricHelper.recordPermission();
        updateUIForUser(user);
        this.cacheClearOperations.start();
    }

    private void stopUserEngagementTimer() {
        this.eventBus.publish(new Message.Builder().setEventType(AppReviewRequestServiceConstants.APP_REVIEW_PREFS_USER_ENGAGEMENT_TIMER_STOP).build());
    }

    @VisibleForTesting
    void addCallbacks() {
        this.isTabBarVisible.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.amazon.dee.app.ui.main.MainViewModel.11
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                MainViewModel mainViewModel = MainViewModel.this;
                mainViewModel.tabLayoutService.setIsTabLayoutShown(mainViewModel.isTabBarVisible.get() || MainViewModel.this.isDriveModeFooterVisible.get());
            }
        });
        this.isDriveModeFooterVisible.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.amazon.dee.app.ui.main.MainViewModel.12
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                MainViewModel mainViewModel = MainViewModel.this;
                mainViewModel.tabLayoutService.setIsTabLayoutShown(mainViewModel.isTabBarVisible.get() || MainViewModel.this.isDriveModeFooterVisible.get());
            }
        });
    }

    @VisibleForTesting
    boolean blueprintsEnabled(UserIdentity userIdentity) {
        return checkFSV2AccessToFeature("ALEXA_MOBILE_APP_BLUEPRINTS_MENU_ANDROID") && Marketplace.USA.equals(userIdentity.getMarketplace()) && this.environmentService.getDeviceInformation().getLanguage().equals(Locale.ENGLISH.getDisplayLanguage());
    }

    boolean checkFSV2AccessToFeature(String str) {
        return this.featureServiceV2Lazy.mo358get() != null && this.featureServiceV2Lazy.mo358get().hasAccess(str, false);
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void create(@Nullable Bundle bundle) {
        this.coldStart = getApplication().isColdStart();
        initializeFeatureUpdateListeners();
        RoutingViewService routingViewService = this.viewService;
        if (routingViewService != null) {
            routingViewService.start();
        }
        if (!this.metricsService.isOngoingEvent(AlexaMetricsConstants.MetricEvents.APP_TIME)) {
            this.metricsService.startTimer(AlexaMetricsConstants.MetricEvents.APP_TIME, "Application", getCustomEntriesWithOwnerIdentifier("cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        } else {
            this.metricsService.resumeTimer(AlexaMetricsConstants.MetricEvents.APP_TIME);
        }
        final Runnable runnable = new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainViewModel$74DZhrdiYrf360Zd8aFcoix5sMs
            @Override // java.lang.Runnable
            public final void run() {
                MainViewModel.this.lambda$create$0$MainViewModel();
            }
        };
        this.themePreferenceSubscriber = ThemeUtil.getThemePreferenceSubscriber(this.activity);
        this.eventBus.subscribe(this.themePreferenceSubscriber);
        this.identityChangedSubscriber = this.eventBus.getSubscriber();
        this.identityChangedSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainViewModel$fZhbGYbYnc4coNWua_ogoUFW81U
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MainViewModel.this.lambda$create$1$MainViewModel(runnable, message);
            }
        });
        this.redirectingRouteInterceptor = new RedirectingRouteInterceptor(this.activity.getApplicationContext(), this.routingService, new Supplier() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainViewModel$S-knsN6uAE3yZlYaB6Pt-4r2n2I
            @Override // com.google.common.base.Supplier
            /* renamed from: get */
            public final Object mo8291get() {
                return MainViewModel.this.lambda$create$2$MainViewModel();
            }
        }, this.environmentService);
        this.routingService.registerObserver(this);
        this.routingService.registerRouteInterceptor(this.redirectingRouteInterceptor);
        try {
            this.adapters.register(this.mainRoutingAdapter);
            this.adapters.register(this.conversationRoutingAdapter);
            this.bridgeStatusService.registerListener(new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainViewModel$PIYiCFfRnxu2deNs-ZK3FZlaEzw
                @Override // java.lang.Runnable
                public final void run() {
                    MainViewModel.this.lambda$create$3$MainViewModel();
                }
            });
            this.adapters.register(this.voiceRoutingAdapter);
            this.adapters.register(this.batteryOptimizationRoutingAdapter);
            this.adapters.register(this.enrollmentRoutingAdapter);
            this.adapters.register(this.kidsEnrollmentRoutingAdapter);
            this.adapters.register(this.homeRoutingAdapter);
            this.adapters.register(this.handsFreeRoutingAdapter);
            this.adapters.register(this.driveModeRoutingAdapter);
            this.adapters.register(this.viewManagerRoutingAdapter);
            this.adapters.register(this.webRoutingAdapter);
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Caught IllegalArgumentException, restarting app initialization");
            killTheApp();
        }
        this.routeWatcher.start();
        FragmentManager supportFragmentManager = ((FragmentActivity) this.activity).getSupportFragmentManager();
        this.conversationUIService.mo358get().start(supportFragmentManager, supportFragmentManager.findFragmentById(R.id.conversation_container));
        this.toolbarWatcher.start();
        this.toolbarService.start();
        this.toolbar = new ToolbarViewModel();
        addCallbacks();
        this.eventBus.publish(new Message.Builder().setEventType("ui:loading:started").build());
        shouldTerminateRemoteManagement();
    }

    @VisibleForTesting
    StartupStateMachine createStartupStateMachine() {
        return new StartupStateMachine(this, this.identityService, this.accountService, this.routingService, this.commsServiceV2, this.mobilytics, this.exceptionHandler, this.reactFeatureManager, this.bridgeStatusService, this.metricsService, this.handsFreeSetup, this.latencyInfra, this.eventBus, this.persistentStorageFactory, this.featureServiceV2Lazy, this.testArguments);
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void destroy() {
        EventBus eventBus = this.eventBus;
        if (eventBus != null) {
            MultiFilterSubscriber multiFilterSubscriber = this.identityChangedSubscriber;
            if (multiFilterSubscriber != null) {
                eventBus.unsubscribe(multiFilterSubscriber);
            }
            Subscriber subscriber = this.themePreferenceSubscriber;
            if (subscriber != null) {
                this.eventBus.unsubscribe(subscriber);
            }
        }
        this.conversationUIService.mo358get().stop();
        recordDiagnosticEvent(AlexaMetricsConstants.MetricEvents.APP_TERMINATE, "Application", TAG);
        this.metricsService.recordTimer(AlexaMetricsConstants.MetricEvents.APP_TIME);
        this.metricsService.endSession();
        this.routingService.unregisterObserver(this);
        this.routingService.unregisterRouteInterceptor(this.redirectingRouteInterceptor);
        if (!this.activity.isChangingConfigurations()) {
            this.routingService.clearCurrentRoute();
            this.routingService.clearBackStack();
        } else {
            this.mobilytics.recordOccurrence(AlexaMetricsConstants.MetricEvents.CONFIGURATION_CHANGE_RESTART, true, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        }
        this.viewService.stop();
        this.adapters.unregister(this.mainRoutingAdapter);
        this.adapters.unregister(this.conversationRoutingAdapter);
        this.adapters.unregister(this.elementsRoutingAdapter);
        this.adapters.unregister(this.kidsEnrollmentRoutingAdapter);
        this.adapters.unregister(this.voiceRoutingAdapter);
        this.adapters.unregister(this.batteryOptimizationRoutingAdapter);
        this.adapters.unregister(this.enrollmentRoutingAdapter);
        this.adapters.unregister(this.homeRoutingAdapter);
        this.adapters.unregister(this.handsFreeRoutingAdapter);
        this.adapters.unregister(this.driveModeRoutingAdapter);
        this.adapters.unregister(this.viewManagerRoutingAdapter);
        this.adapters.unregister(this.webRoutingAdapter);
        this.routingService.unregisterRouteInterceptor(this.stateMachine);
        this.routeWatcher.stop();
        this.toolbarWatcher.stop();
        this.toolbarService.destroy();
        this.cacheClearOperations.stop();
        this.voiceService.clearScrimSubscription();
        if (this.featureServiceV2Lazy.mo358get() != null) {
            this.featureServiceV2Lazy.mo358get().unsubscribe(this.homeChannelFeatureUpdateListener);
            this.featureServiceV2Lazy.mo358get().unsubscribe(this.devicesChannelFeatureUpdateListener);
            this.featureServiceV2Lazy.mo358get().unsubscribe(this.footerTitlesUpdateFeatureUpdateListener);
        }
    }

    public void handleAudioMessageRecordingInterruption() {
        this.conversationUIService.mo358get().handleAudioMessageRecordingInterruption();
    }

    public void handleCommsVoiceNavigation(CommsView commsView) {
        int ordinal = commsView.ordinal();
        if (ordinal == 2) {
            this.routingService.route(RouteName.CONVERSATIONS_CONTACT_LIST).addToBackStack().forceNavigate();
        } else if (ordinal != 15) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown action for Vox navigation: ");
            outline107.append(commsView.name());
            Log.w(str, outline107.toString());
        } else {
            this.routingService.route("conversations").with("arguments", GeneratedOutlineSupport1.outline13(Constants.ORIGINATED_FROM_VOICE, true)).addToBackStack().forceNavigate();
        }
    }

    public void handleDeepLinking(@NonNull Uri uri) {
        String str = "Deep linking: " + uri;
        logDeeplinkMetric(uri, AlexaMetricsConstants.MetricEvents.Deeplink.URL);
        this.routingService.navigate(createRouteFromDeepLinkUri(uri));
    }

    public void ingressClicked() {
        this.voiceService.ingressClicked();
    }

    @VisibleForTesting
    void initializeFeatureUpdateListeners() {
        this.homeChannelFeatureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.dee.app.ui.main.MainViewModel.7
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                MainViewModel.this.updateUIForHomeChannel();
            }
        };
        this.devicesChannelFeatureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.dee.app.ui.main.MainViewModel.8
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                MainViewModel.this.updateUIForDevicesChannel();
            }
        };
        this.footerTitlesUpdateFeatureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.dee.app.ui.main.MainViewModel.9
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                MainViewModel.this.updateUIForFooterTitles();
            }
        };
        this.careHubFeatureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.dee.app.ui.main.MainViewModel.10
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                MainViewModel.this.terminateRemoteManagement();
            }
        };
    }

    @Override // com.amazon.dee.app.ui.nowplaying.NowPlayingHandler
    public boolean isCurrentTabNowPlaying() {
        return 3 == this.currentTab.get();
    }

    @Override // com.amazon.dee.app.services.appreviewrequest.MainActivityVisibility
    public boolean isMainActivityBackgrounded() {
        return this.isMainActivityBackgrounded;
    }

    public ObservableBoolean isNativeNowPlayingCardVisible() {
        return this.nowPlayingViewModel.isPlayerVisible();
    }

    public ObservableBoolean isPlayerVisible() {
        return this.nowPlayingViewModel.isPlayerVisible();
    }

    public boolean isRouteActive(String... strArr) {
        RouteContext routeContext = this.currentRouteContext.get();
        if (routeContext != null) {
            for (String str : strArr) {
                if (RouteApiUtils.isDependentOn(routeContext.getRoute(), str, this.routes)) {
                    return true;
                }
            }
        }
        return false;
    }

    public /* synthetic */ void lambda$create$0$MainViewModel() {
        updateUIForUser(this.updatedUser);
        if (this.updatedUser == null) {
            notifyUserLoggedOut();
            this.toolbarService.cleanup();
            this.toolbarWatcher.stop();
            this.reactFeatureManager.onClearReactData();
            this.elementsRoutingAdapter.clearBackstack();
            StartupStateMachine startupStateMachine = this.stateMachine;
            if (startupStateMachine == null) {
                return;
            }
            startupStateMachine.setTargetRoute(null);
            this.stateMachine.start();
            return;
        }
        this.toolbarService.start();
        this.toolbarWatcher.start();
        this.themeRecorder.mo358get().recordTheme();
    }

    public /* synthetic */ void lambda$create$1$MainViewModel(Runnable runnable, Message message) {
        this.updatedUser = this.identityService.getUser(TAG);
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public /* synthetic */ ElementsFeatureEnablement lambda$create$2$MainViewModel() {
        return ElementsFeatureEnablement.fromIdentityService(this.identityService);
    }

    public /* synthetic */ void lambda$create$3$MainViewModel() {
        try {
            this.adapters.register(this.elementsRoutingAdapter);
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Caught IllegalArgumentException, restarting app initialization");
            killTheApp();
        }
    }

    public /* synthetic */ void lambda$createRouteFromDeepLinkUri$5$MainViewModel(Uri uri) {
        this.sonarUrlHandler.mo358get().backgroundHttpRequest(uri);
    }

    public /* synthetic */ void lambda$notifyChannelsHomeClicked$4$MainViewModel(UserIdentity userIdentity, MainActivityVisibility mainActivityVisibility) {
        if (userIdentity != null) {
            this.appReviewRequestService.showAlertDialogIfAppropriate(this.activity, mainActivityVisibility);
        }
    }

    @VisibleForTesting
    void logDeeplinkMetric(Uri uri, String str) {
        recordDiagnosticEvent(str, AlexaMetricsConstants.MetricsComponents.DEEPLINKING, TAG);
    }

    public void nativeStartBeginning() {
        this.disableInteraction.set(true);
    }

    public void nativeStartComplete() {
        this.disableInteraction.set(false);
    }

    public void navigateBack() {
        Fragment findFragmentByTag = this.mainActivity.getSupportFragmentManager().findFragmentByTag(ViewPresenter.class.getSimpleName());
        if (!((!(findFragmentByTag instanceof ViewControllerFragment) || !findFragmentByTag.isVisible()) ? false : ((ViewControllerFragment) findFragmentByTag).onBackPressed())) {
            this.routingService.navigateBackward();
            restoreCachedHeaderAsNeeded();
        }
    }

    public void notifyBlueprintsSelected() {
        Intent createIntent = this.intentFactory.createIntent(ExternalUIActivity.class);
        createIntent.putExtra("android.intent.extra.TEXT", WebConstants.ExternalURL.BLUEPRINTS_URL);
        createIntent.putExtra("URL_REGEX_KEY", WebConstants.URLRegex.BLUEPRINTS_REGEX);
        createIntent.putExtra("BRIDGE_ACTION_KEY", ExternalUILauncherBridge.BridgeAction.EXTERNAL.toString());
        try {
            this.activity.startActivityForResult(createIntent, 2);
        } catch (ActivityNotFoundException unused) {
            Log.e(TAG, String.format("Could not launch an activity with specified intent: %s", createIntent));
        }
        recordFirstMenuInteraction("RightMenu", "RIGHT_MENU_BLUEPRINTS");
        recordEngagement("RIGHT_MENU_BLUEPRINTS", "RightMenu", "RightMenuBlueprints");
    }

    public void notifyChannelsDevicesClicked() {
        routeWithMetrics(RouteName.CHANNELS_DEVICES, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU_CHANNELS_DEVICES_SUBCOMPONENT, AlexaMetricsConstants.MetricEvents.MENU_CHANNELS_DEVICES, false);
    }

    public void notifyChannelsEntertainmentClicked() {
        routeWithMetrics(RouteName.CHANNELS_ENTERTAINMENT, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU_CHANNELS_ENTERTAINMENT_SUBCOMPONENT, AlexaMetricsConstants.MetricEvents.MENU_CHANNELS_ENTERTAINMENT, false);
    }

    public void notifyChannelsHomeClicked() {
        routeWithMetrics(RouteName.HOME, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU_CHANNELS_HOME_SUBCOMPONENT, AlexaMetricsConstants.MetricEvents.MENU_CHANNELS_HOME, false);
        final UserIdentity user = this.identityService.getUser(TAG);
        Completable.timer(checkFSV2AccessToFeature("ALEXA_IN_APP_REVIEW_ANDROID_REDUCE_HOME_TIME") ? 3 : 5, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Action0() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainViewModel$3CUpZLWefhVLbWYJB_-_1nifmoY
            @Override // rx.functions.Action0
            public final void call() {
                MainViewModel.this.lambda$notifyChannelsHomeClicked$4$MainViewModel(user, this);
            }
        });
    }

    public void notifyClickedOnNotification(@NonNull Bundle bundle) {
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.mobilytics.createUserInteractionEvent(AlexaMetricsConstants.MetricEvents.NOTIFICATION_CLICK, "click", "PushNotifications", AlexaMetricsConstants.MetricsComponents.NOTIFICATIONS_LANDING_PAGE_SUBCOMPONENT, "1235005e-4e8f-4ef2-82bc-34157415015b");
        createUserInteractionEvent.setChannelName("coreapp");
        MessagingHandler.extractAndSetMetricsPayload(createUserInteractionEvent, bundle);
        this.mobilytics.recordUserInteractionEvent(createUserInteractionEvent);
    }

    public void notifyContactsSelected() {
        if (this.commsRoutingHelper.isCommsBlockingEnabled(this.activity)) {
            Log.i(TAG, "Customer has enabled comms blocking -- Ignore routing to Contacts");
        } else {
            this.commsRoutingHelper.resolveCommsContactsRoute(this.activity);
        }
    }

    public void notifyConversationsSelected() {
        if (this.commsRoutingHelper.isCommsBlockingEnabled(this.activity)) {
            Log.i(TAG, "Customer has enabled comms blocking -- Ignore routing to Comms");
            return;
        }
        recordFirstMenuInteraction(AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU, AlexaMetricsConstants.MetricEvents.MENU_CONVERSATIONS);
        recordEngagement(AlexaMetricsConstants.MetricEvents.MENU_CONVERSATIONS, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU_HOME_SUBCOMPONENT);
        this.commsRoutingHelper.resolveCommsConversationRoute(this.activity);
    }

    public void notifyHeaderClicked() {
        this.preloadAttributionUIManager.mo358get().onAppHeaderClicked();
    }

    public void notifyHelpAndFeedbackSelected() {
        notifyHelpAndFeedbackSelected(false);
    }

    public void notifyMoreLongPressed() {
        this.routingService.navigate("diagnostics/index");
    }

    public void notifyNavigationSelected() {
        routeWithMetrics(RouteName.NAV_MORE_MENU, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU_MORE_MENU_SUBCOMPONENT, AlexaMetricsConstants.MetricEvents.MENU_MORE_NAVIGATION_MENU, false);
    }

    public void notifyNowPlayingSelected() {
        String str;
        String str2;
        if (this.isNowPlayingElementsEnabled.get()) {
            str = AlexaMetricsConstants.MetricEvents.MENU_NOWPLAYING_ELEMENTS;
            str2 = RouteName.NOW_PLAYING_ELEMENTS;
        } else {
            str = AlexaMetricsConstants.MetricEvents.MENU_NOWPLAYING;
            str2 = RouteName.NOW_PLAYING;
        }
        this.routingService.stopLoadingTimeout();
        this.nowPlayingViewModel.updatePlayerVisibility();
        if (!this.routingService.popFromBackStack(RouteName.NOW_PLAYING_ELEMENTS) && !this.routingService.popFromBackStack(RouteName.NOW_PLAYING) && !this.routingService.popFromBackStack(RouteName.PLAYER_CURRENT) && !this.routingService.popFromBackStack(RouteName.PLAYER_HISTORY) && !this.routingService.popFromBackStack(RouteName.PLAYER_QUEUE)) {
            this.routingService.navigate(str2);
        } else {
            this.headerCacheService.loadCachedRequest();
        }
        recordEngagement(str, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU, AlexaMetricsConstants.MetricsComponents.BOTTOM_MENU_NOW_PLAYING_SUBCOMPONENT);
    }

    public void notifyOnCreateFinish(@NonNull String str) {
        recordDiagnosticEvent(str.toUpperCase() + AlexaMetricsConstants.MetricEvents.ON_CREATE_DURATION, "Application", TAG);
        this.eventBus.publish(new Message.Builder().setEventType(AppReviewRequestServiceConstants.APP_REVIEW_PREFS_RECORD_APP_OPENED).build());
    }

    public void notifyOnCreateStart(@NonNull String str) {
        Map<String, Object> customEntriesWithOwnerIdentifier = getCustomEntriesWithOwnerIdentifier("cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        if (this.coldStart) {
            recordDiagnosticEvent(AlexaMetricsConstants.MetricEvents.APP_OPEN, "Application", TAG);
            this.mobilytics.recordOccurrence("APP_CRASH", false, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        } else {
            recordDiagnosticEvent(AlexaMetricsConstants.MetricEvents.APP_RESTART, "Application", TAG);
        }
        MetricsService metricsService = this.metricsService;
        metricsService.startTimer(str.toUpperCase() + AlexaMetricsConstants.MetricEvents.ON_CREATE_DURATION, str, customEntriesWithOwnerIdentifier);
    }

    public void notifyOnPause() {
        this.isMainActivityBackgrounded = true;
        recordDiagnosticEvent(AlexaMetricsConstants.MetricEvents.APP_CLOSE, "Application", TAG);
        this.metricsService.pauseTimer(AlexaMetricsConstants.MetricEvents.APP_TIME);
        this.voiceService.clearScrimSubscription();
        if (getApplication().isSingleSignOnBuild()) {
            this.wasSSOAuthenticated = this.identityService.isRegistered(TAG);
        }
        stopUserEngagementTimer();
    }

    public void notifyOnRestart() {
        this.isMainActivityBackgrounded = false;
        if (!this.identityService.isRegistered(TAG) && this.stateMachine.getCurrentState().equals(StartupStateMachine.State.LoggingIn)) {
            this.stateMachine.transitionTo(StartupStateMachine.State.LoggingIn);
        }
        String str = TAG;
        recordDiagnosticEvent(APP_WARM_START, str, str);
        if (this.identityService.isRegistered(TAG)) {
            this.identityService.getUser(TAG);
        }
    }

    public void notifyOnResume() {
        this.isMainActivityBackgrounded = false;
        this.environmentService.getDeviceInformation().refreshDeviceInfoData();
        recordDiagnosticEvent(AlexaMetricsConstants.MetricEvents.APP_RESUME, "Application", TAG);
        this.locationPermissionMetricHelper.recordTransitionIfPermissionChanged();
        this.voiceService.onResumeMainActivity(this.activity);
        this.voiceService.registerScrimListenerIfSpeaking();
        this.toolbarWatcher.updateToolbars();
        startUserEngagementTimer();
        if (this.isHomeView.get()) {
            this.currentTab.set(1);
        }
        if (!getApplication().isSingleSignOnBuild()) {
            return;
        }
        if ((!this.wasSSOAuthenticated && this.stateMachine.getCurrentState() != StartupStateMachine.State.LoggingIn) || this.identityService.isRegistered(TAG)) {
            return;
        }
        this.wasSSOAuthenticated = false;
        this.routingService.navigateToExit();
        Log.w(TAG, "Exiting");
    }

    public void notifyOnStart() {
        this.voiceService.onStartMainActivity(this.activity);
    }

    public void notifyOnStop() {
        this.voiceService.onStopMainActivity(this.activity);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void notifyQuickActionsWidgetClicked(Intent intent) {
        char c;
        String stringExtra = intent.getStringExtra("routeName");
        long longExtra = intent.getLongExtra(QUICK_ACTIONS_WIDGET_CLICK_TIME_KEY, 0L);
        switch (stringExtra.hashCode()) {
            case -1927714238:
                if (stringExtra.equals("v2/lists")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 83239831:
                if (stringExtra.equals(RouteName.ELEMENTS_ALARM_CREATE_LANDING)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1604239143:
                if (stringExtra.equals(RouteName.ELEMENTS_REMINDERS_CREATE_LANDING)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1678144994:
                if (stringExtra.equals(RouteName.COMMS_ANNOUNCEMENT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        MobilyticsMetricsTimer createTimer = this.mobilytics.createTimer(c != 0 ? c != 1 ? c != 2 ? c != 3 ? AlexaMetricsConstants.MetricEvents.QUICK_ACTIONS_WIDGET_UNKNOWN_LATENCY : AlexaMetricsConstants.MetricEvents.QUICK_ACTIONS_WIDGET_ALARM_LATENCY : AlexaMetricsConstants.MetricEvents.QUICK_ACTIONS_WIDGET_REMINDER_LATENCY : AlexaMetricsConstants.MetricEvents.QUICK_ACTIONS_WIDGET_ANNOUNCE_LATENCY : AlexaMetricsConstants.MetricEvents.QUICK_ACTIONS_WIDGET_LISTS_LATENCY, LatencyService.isStartType(LatencyService.StartType.COLD) ? AlexaMetricsConstants.MetricsComponents.LAUNCHER_WIDGET_COLD_START : AlexaMetricsConstants.MetricsComponents.LAUNCHER_WIDGET_WARM_START, "QUICK_ACTIONS_WIDGET", TimeUnit.MILLISECONDS.convert(System.nanoTime() - longExtra, TimeUnit.NANOSECONDS), false, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        createTimer.finishTimer();
        createTimer.setChannelName("coreapp");
        this.mobilytics.recordTimer(createTimer);
    }

    void onHeaderTitleChangeForRoute(@NonNull Route route) {
        if (route.headerTitle() != null) {
            setHeaderTitle(this.activity.getString(route.headerTitle().intValue()));
        }
    }

    @Override // com.amazon.dee.app.ui.web.AlexaWebView.OnPageFinishedListener
    public void onPageFinished(String str) {
        this.routingService.stopLoadingTimeout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onRouteChangeForContentMode(int i) {
        if (i == 0) {
            return;
        }
        if (i == 7) {
            Log.w(TAG, "content mode OVERLAY has not been implemented");
            return;
        }
        if (this.driveModeViewModel.mo358get().applyContentMode(i)) {
            this.isDriveModeFooterVisible.set(i == 8 || i == 10);
            i = 2;
        } else {
            this.isDriveModeFooterVisible.set(false);
        }
        this.isFullScreenMode.set(!this.isUserKnown.get());
        setHeaderVisible(true);
        if (i == 2) {
            this.isFullScreenMode.set(true);
            setHeaderVisible(false);
        } else if (i == 5) {
            this.isFullScreenMode.set(true);
        } else if (i == 6) {
        } else {
            if (i == 3) {
                setHeaderVisible(false);
            } else if (i != 4) {
            } else {
                Log.w(TAG, "content mode TAB_BAR_ONLY_WITH_MENU_ACCESSIBLE has not been implemented");
            }
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingObserver
    public void onRouteChanged(@NonNull RouteContext routeContext) {
        updateCurrentRoute(routeContext);
        dismissKeyBoard();
    }

    @VisibleForTesting
    void onRouteChangedForTabs(@NonNull RouteContext routeContext) {
        Route route = routeContext.getRoute();
        if (RouteApiUtils.isDependentOn(route, RouteName.HOME, this.routes)) {
            this.isBackVisible.set(determineBackVisible(routeContext));
            this.currentTab.set(1);
        } else if (RouteApiUtils.isDependentOnAny(route, RouteCategories.NOW_PLAYING_ROUTES, this.routes)) {
            this.isBackVisible.set(determineBackVisible(routeContext));
            this.currentTab.set(3);
        } else if (RouteApiUtils.isDependentOn(route, RouteName.REACT_NATIVE_COMMS, this.routes)) {
            this.isBackVisible.set(this.routingService.canNavigateBackward());
            this.currentTab.set(2);
        } else if (RouteApiUtils.isDependentOn(route, "conversations", this.routes)) {
            this.isBackVisible.set(determineBackVisible(routeContext));
            this.currentTab.set(2);
        } else if (RouteApiUtils.isDependentOn(route, "rn", this.routes)) {
            this.isBackVisible.set(determineBackVisible(routeContext));
            this.currentTab.set(0);
        } else if (RouteApiUtils.isDependentOn(route, "settings", this.routes)) {
            this.isBackVisible.set(determineBackVisible(routeContext));
            this.currentTab.set(0);
        } else if (RouteApiUtils.isDependentOn(route, RouteName.CHANNELS_DEVICES, this.routes)) {
            this.isBackVisible.set(determineBackVisible(routeContext));
            this.currentTab.set(4);
        } else if (RouteApiUtils.isDependentOnAny(route, RouteCategories.NON_TAB_ROUTES, this.routes)) {
            if (RouteApiUtils.isDependentOn(route, RouteName.HELP, this.routes)) {
                this.isBackVisible.set(this.routingService.canNavigateBackward());
            } else {
                this.isBackVisible.set(determineBackVisible(routeContext));
            }
            this.currentTab.set(0);
        } else if (route.is("web")) {
            this.currentTab.set(0);
            this.isBackVisible.set(this.routingService.canNavigateBackward());
            if (TextUtils.isEmpty(this.headerTitle.get())) {
                this.headerCacheService.loadCachedRequest();
            }
        } else if (route.is(RouteName.NAV_MORE_MENU)) {
            this.currentTab.set(5);
        } else if (!route.is("voice-enrollment-oobe") && !route.isOverlay()) {
            this.isBackVisible.set(this.routingService.canNavigateBackward());
            this.currentTab.set(0);
        }
        this.nowPlayingViewModel.updatePlayerVisibility();
    }

    public void overrideHost(@NonNull String str) {
        this.environmentService.overrideHost(str);
    }

    @VisibleForTesting
    void recordDiagnosticEvent(String str, String str2, String str3) {
        Mobilytics mobilytics = this.mobilytics;
        if (mobilytics != null) {
            this.mobilytics.recordOperationalEvent(mobilytics.createOperationalEvent(str, OperationalEventType.DIAGNOSTIC, str2, str3, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        }
    }

    @VisibleForTesting
    void recordEngagement(String str, String str2, String str3) {
        if (str3 == null || str3.isEmpty()) {
            str3 = AlexaMetricsConstants.MetricsComponents.VIEW_MODEL_SUBCOMPONENT;
        }
        this.mobilytics.recordUserInteractionEvent(this.mobilytics.createUserInteractionEvent(str, "click", str2, str3, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
    }

    public void restoreState(Bundle bundle) {
        boolean z = true;
        if (this.routingService.getCurrentRoute() == null) {
            this.currentTab.set(1);
            this.routingService.navigate(RouteName.HOME);
        }
        this.toolbar.create(bundle.getBundle(TOOLBAR));
        if (this.currentTab.get() != 1) {
            z = bundle.getBoolean(IS_HOME_VIEW_BUNDLE_KEY, false);
        }
        setReactNativeActive(bundle.getBoolean(IS_REACT_NATIVE_VIEW_BUNDLE_KEY, false));
        setHomeActive(z);
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    @Nullable
    public Bundle saveState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_REACT_NATIVE_VIEW_BUNDLE_KEY, this.isReactNativeView.get());
        bundle.putBoolean(IS_HOME_VIEW_BUNDLE_KEY, this.isHomeView.get());
        ToolbarViewModel toolbarViewModel = this.toolbar;
        if (toolbarViewModel != null) {
            bundle.putBundle(TOOLBAR, toolbarViewModel.saveState());
        }
        if (shouldPreserveRoutingState()) {
            bundle.putBundle(ROUTING, this.routingService.saveState());
        }
        return bundle;
    }

    public void setDisabled(boolean z) {
        this.isDisabled.set(z);
        setLoading(z);
    }

    public void setHeaderTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.headerTitleVisibility.set(0);
            this.headerTitle.set(charSequence.toString().toUpperCase());
        }
    }

    public void setHeaderVisible(boolean z) {
        this.showHeader.set(z);
    }

    public void setHomeActive(boolean z) {
        this.isHomeView.set(z);
    }

    public void setLoading(boolean z) {
        this.isLoading.set(z);
    }

    public void setReactNativeActive(boolean z) {
        this.isReactNativeView.set(z);
        this.pendingViewIsReactNative.set(z);
    }

    @VisibleForTesting
    void setRemoteManagement(UserIdentity userIdentity) {
        this.isRemoteManagementEnabled.set(userIdentity.getDelegatedProfile() != null);
    }

    @VisibleForTesting
    boolean shouldPreserveRoutingState() {
        return this.driveModeService.mo358get().useAMAViewManagerForDriveModeRoutes() && ModeName.DRIVE_MODE.equals(this.modeService.mo358get().getMode());
    }

    @VisibleForTesting
    void shouldTerminateRemoteManagement() {
        this.featureServiceV2Lazy.mo358get().observeFeature("ALEXA_BILOBA_ANDROID_MENU_INGRESS", this.careHubFeatureUpdateListener);
    }

    public void start(@Nullable Uri uri, @Nullable Bundle bundle) {
        if (uri != null) {
            logDeeplinkMetric(uri, AlexaMetricsConstants.MetricEvents.Deeplink.URL);
            startWithRoute(createRouteFromDeepLinkUri(uri));
        } else if (bundle == null) {
            startWithRoute(null);
        } else {
            startWithRoute(this.routingService.restoreState(bundle.getBundle(ROUTING)));
        }
    }

    @VisibleForTesting
    void terminateRemoteManagement() {
        UserIdentity user = this.identityService.getUser(TAG);
        if (!checkFSV2AccessToFeature("ALEXA_BILOBA_ANDROID_MENU_INGRESS") || user == null || user.getDelegatedProfile() == null) {
            return;
        }
        try {
            this.identityService.terminateDelegation(TAG);
        } catch (UnsupportedOperationException unused) {
            Log.e(TAG, "Terminate Remote management session not supported");
        }
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void updateContentMode(int i) {
        this.routingService.saveContentModeToRouteContext(i);
    }

    void updateCurrentRoute(@NonNull RouteContext routeContext) {
        this.currentRouteContext.set(routeContext);
        onHeaderTitleChangeForRoute(routeContext.getRoute());
        onRouteChangeForContentMode(routeContext.getContentMode());
        onRouteChangedForTabs(routeContext);
    }

    @VisibleForTesting
    void updateUIForDevicesChannel() {
        this.channelsDevicesEnabled.set(checkFSV2AccessToFeature("ELEMENTS_DEVICES_CHANNEL_ANDROID"));
    }

    @VisibleForTesting
    void updateUIForFooterTitles() {
        this.footerTitleVisibility.set(checkFSV2AccessToFeature("ALEXA_MOBILE_APP_GENERIC_FEATURE_8") ? 0 : 8);
    }

    @VisibleForTesting
    void updateUIForHomeChannel() {
        this.channelsHomeEnabled.set(checkFSV2AccessToFeature("CH_HOME_ANDROID"));
    }

    @VisibleForTesting
    void updateUIForUser(@Nullable UserIdentity userIdentity) {
        if (userIdentity == null) {
            this.isUserKnown.set(false);
            this.isConversationVisible.set(false);
            this.headerTitleVisibility.set(8);
            this.footerTitleVisibility.set(8);
            this.isFullScreenMode.set(true ^ this.isUserKnown.get());
            this.useChannelsTheme.set(false);
            this.channelsDevicesEnabled.set(false);
            this.channelsHomeEnabled.set(false);
            setReactNativeActive(false);
            this.currentTheme.set(Themes.NO_USER);
            this.routingService.clearCurrentRoute();
            this.routingService.clearBackStack();
            this.currentRouteContext.set(null);
            this.crashMetadata.put("logged_in", false);
            return;
        }
        this.isUserKnown.set(userIdentity.hasAcceptedEula());
        this.isFullScreenMode.set(!this.isUserKnown.get());
        this.currentTheme.set(Themes.MOSAIC);
        RouteContext currentRoute = this.routingService.getCurrentRoute();
        if (currentRoute != null) {
            onRouteChangeForContentMode(currentRoute.getContentMode());
        }
        if (this.featureServiceV2Lazy.mo358get() != null) {
            this.featureServiceV2Lazy.mo358get().observeFeature("ALEXA_MOBILE_APP_GENERIC_FEATURE_8", this.footerTitlesUpdateFeatureUpdateListener);
            this.featureServiceV2Lazy.mo358get().observeFeature("CH_HOME_ANDROID", this.homeChannelFeatureUpdateListener);
            this.featureServiceV2Lazy.mo358get().observeFeature("ELEMENTS_DEVICES_CHANNEL_ANDROID", this.devicesChannelFeatureUpdateListener);
        }
        this.isConversationVisible.set(true);
        this.isNowPlayingElementsEnabled.set(checkFSV2AccessToFeature("ELEMENTS_NOW_PLAYING_ANDROID"));
        this.isBlockAlexaListsViewEnabled.set(checkFSV2AccessToFeature("ALEXA_LISTS_PREVIEW"));
        this.useChannelsTheme.set(checkFSV2AccessToFeature("ALEXA_MOBILE_APP_GENERIC_FEATURE_3"));
        if (!checkFSV2AccessToFeature("ALEXA_ANDROID_DELAY_COMMS_INIT")) {
            ExecutorService executor = this.taskManager.getExecutor(1);
            final ConversationService mo358get = this.conversationService.mo358get();
            mo358get.getClass();
            executor.submit(new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$5rzC5823qo0Z-XgXVCuux1D7kz4
                @Override // java.lang.Runnable
                public final void run() {
                    ConversationService.this.initialize();
                }
            });
        }
        setRemoteManagement(userIdentity);
        this.crashMetadata.put("logged_in", true);
    }

    public void notifyHelpAndFeedbackSelected(boolean z) {
        routeWithMetrics(RouteName.HELP, "RightMenu", "RightMenuHelp", "RIGHT_MENU_HELP", z);
    }
}
