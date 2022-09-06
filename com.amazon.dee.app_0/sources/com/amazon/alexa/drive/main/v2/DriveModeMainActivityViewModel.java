package com.amazon.alexa.drive.main.v2;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.main.DriveModeMainContract;
import com.amazon.alexa.drive.main.routing.DriveModeRoutes;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.LandingPageMetrics;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.orientation.LandscapeErrorProvider;
import com.amazon.alexa.drive.refactor.CommsRoutes;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.drive.util.DriveModeAnimationUtils;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.common.base.Preconditions;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class DriveModeMainActivityViewModel implements DriveModeMainActivityCompanion.ViewModel, DriveModeMainContract.View, MainActivityLifecycleObserver {
    private static final String ALEXA_APP_PACKAGE_NAME = "com.amazon.dee.app";
    private static final String ALEXA_VOICE_ACTIVITY_CLASS_NAME = "com.amazon.alexa.voice.ui.VoiceActivity";
    private static final String TAG = "DriveModeMainActivityViewModel";
    int driveModeDependenciesGenerationId;
    private DriveModeService driveModeService = (DriveModeService) GeneratedOutlineSupport1.outline21(DriveModeService.class);
    private ViewGroup footerContainer;
    private ViewGroup headerContainer;
    private boolean isWaitingForAccessoryStatusUpdate;
    @Inject
    AMPDInformationProvider mAMPDInformationProvider;
    private Disposable mAccessoryConnectionStatusDisposable;
    private BehaviorSubject<Boolean> mAccessoryConnectionStatusObservable;
    @Inject
    DriveModeMetrics mDriveModeMetrics;
    @Inject
    DriveModeMetricsHelper mDriveModeMetricsHelper;
    @Inject
    DriveModeThemeManager mDriveModeThemeManager;
    @Inject
    EventBus mEventBus;
    private boolean mIsAMPDDevice;
    @Inject
    LandscapeErrorProvider mLandscapeErrorProvider;
    private AnimatorSet mLottieTitleAnimation;
    @Inject
    NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;
    private int mStatusBarColor;
    private int mSystemNavBarColor;
    private int mSystemVisibilityUIFlag;
    private AnimatorSet mTabSelectedAnimation;
    private SimpleMultiFilterSubscriber mThemeChangeEventSubscriber;
    @Inject
    WeblabManager mWeblabManager;
    @Inject
    ModeService modeService;
    private TextView navBarTitle;
    private Toolbar navigationBar;
    @Inject
    DriveModeMainContract.Presenter presenter;
    private RoutingRegistry routingRegistry;
    private BottomNavigationView tabBar;
    private Handler uiThreadHandler;

    public DriveModeMainActivityViewModel() {
        Preconditions.checkNotNull(this.driveModeService);
        this.routingRegistry = (RoutingRegistry) ComponentRegistry.getInstance().get(RoutingRegistry.class).orNull();
        Preconditions.checkNotNull(this.routingRegistry);
    }

    private void destroyThemeChangeListener() {
        Log.i(TAG, "destroyThemeChangeListener");
        this.mDriveModeThemeManager.destroy();
        EventBus eventBus = this.mEventBus;
        if (eventBus != null) {
            eventBus.unsubscribe(this.mThemeChangeEventSubscriber);
        }
    }

    public void egressDriveMode() {
        String str = TAG;
        Log.i(str, this + " Uninitializing Drive Mode View Model");
        Preconditions.checkState((this.headerContainer == null || this.footerContainer == null) ? false : true, "onCreate must be called before uninitializeDriveMode");
        this.uiThreadHandler.removeCallbacksAndMessages(null);
        destroyThemeChangeListener();
        resetSystemBarsThemeColor();
        toggleKeepScreenOnFlag(false, this.headerContainer.getContext());
        stopMainActivityLifecycleMonitor();
        if (this.mWeblabManager.isHeroFeaturesWeblabEnabled()) {
            this.mLandscapeErrorProvider.destroy();
        }
        this.headerContainer.removeAllViews();
        this.footerContainer.removeAllViews();
        this.headerContainer.setVisibility(8);
        this.footerContainer.setVisibility(8);
        this.navBarTitle = null;
        this.navigationBar = null;
        this.tabBar = null;
        if (this.mAccessoryConnectionStatusDisposable != null) {
            Log.i(TAG, "Disposing accessory connection observable");
            this.mAccessoryConnectionStatusDisposable.dispose();
        }
        if (!this.mNetworkStatusSubscription.isUnsubscribed()) {
            this.mNetworkStatusSubscription.unsubscribe();
        }
        boolean doesDriveModeDaggerBelongsToTheGeneration = DriveModeDependencies.doesDriveModeDaggerBelongsToTheGeneration(this.driveModeDependenciesGenerationId);
        DriveModeMainContract.Presenter presenter = this.presenter;
        if (presenter != null) {
            presenter.uninitialize(doesDriveModeDaggerBelongsToTheGeneration);
            this.presenter = null;
        }
        DriveModeDependencies.driveModeUninitialize(this.driveModeDependenciesGenerationId);
        if (doesDriveModeDaggerBelongsToTheGeneration) {
            ensureDriveModeDependenciesInitialized();
        }
    }

    private void ensureDriveModeDependenciesInitialized() {
        if (DriveModeDependencies.doesDriveModeDaggerBelongsToTheGeneration(this.driveModeDependenciesGenerationId)) {
            return;
        }
        this.driveModeDependenciesGenerationId = DriveModeDependencies.driveModeInitialize(this.headerContainer.getContext(), null);
        String str = this + " dependencies initialized";
    }

    private void ensureDriveModeIngress() {
        if (this.presenter != null) {
            return;
        }
        ensureDriveModeDependenciesInitialized();
        String str = TAG;
        Log.i(str, this + " Initializing Drive Mode");
        Preconditions.checkState((this.headerContainer == null || this.footerContainer == null) ? false : true, "onCreate must be called before ensureDriveModeInitialized");
        Context context = this.headerContainer.getContext();
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
        this.presenter.initialize(this);
        startMainActivityLifecycleMonitor();
        this.mDriveModeThemeManager.init(context);
        this.mDriveModeMetrics.logSessionStartedWithTheme(this.mDriveModeThemeManager.isCurrentThemeDark() ? DriveModeMetrics.Theme.DARK : DriveModeMetrics.Theme.LIGHT);
        this.isWaitingForAccessoryStatusUpdate = true;
        initNetworkConnectionManager();
        this.mIsAMPDDevice = this.mAMPDInformationProvider.isAMPDDevice();
        this.uiThreadHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$WccMv5tFAE3rqFufj2P3FmGkczw
            @Override // java.lang.Runnable
            public final void run() {
                DriveModeMainActivityViewModel.this.initAccessoryConnectionObserver();
            }
        }, 5000L);
        if (this.mWeblabManager.isAutoMode_2_0_WeblabEnabled()) {
            setupTabBarV2();
            setupNavigationBarV2();
        } else {
            setupTabBar();
            setupNavigationBar();
        }
        updateDriveModeRoutesTheme();
        initThemeChangeListener();
        toggleKeepScreenOnFlag(true, context);
        saveSystemBarsThemeColor();
        setSystemBarsThemeColor();
        if (!this.mWeblabManager.isHeroFeaturesWeblabEnabled()) {
            return;
        }
        this.mLandscapeErrorProvider.init();
    }

    public void initAccessoryConnectionObserver() {
        Log.i(TAG, "initAccessoryConnectionObserver");
        this.isWaitingForAccessoryStatusUpdate = false;
        ModeService modeService = this.modeService;
        if (modeService != null) {
            this.mAccessoryConnectionStatusObservable = modeService.isDriveModeAccessoryDeviceConnected();
            updateTTTButtonVisibility(this.mAccessoryConnectionStatusObservable.getValue().booleanValue());
            this.mAccessoryConnectionStatusDisposable = this.mAccessoryConnectionStatusObservable.subscribe(new Consumer() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$BptnxZvmYk6-Fq9NDAfMIvoT6TI
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DriveModeMainActivityViewModel.this.lambda$initAccessoryConnectionObserver$2$DriveModeMainActivityViewModel((Boolean) obj);
                }
            });
        }
    }

    private void initThemeChangeListener() {
        Log.i(TAG, "initThemeChangeListener");
        this.mThemeChangeEventSubscriber = new SimpleMultiFilterSubscriber();
        this.mThemeChangeEventSubscriber.subscribeFilter(new EventTypeMessageFilter(DriveModeThemeManager.DRIVE_MODE_THEME_CHANGE_EVENT), new MessageHandler() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$HIESeNHSo80w_mztP3CIltadOJw
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DriveModeMainActivityViewModel.this.lambda$initThemeChangeListener$8$DriveModeMainActivityViewModel(message);
            }
        });
        EventBus eventBus = this.mEventBus;
        if (eventBus != null) {
            eventBus.subscribe(this.mThemeChangeEventSubscriber);
        }
    }

    private boolean isDriveModeContentMode(int i) {
        return i == 9 || i == 10 || i == 8 || i == 11;
    }

    static /* synthetic */ void lambda$onActivityResume$0() {
    }

    public static /* synthetic */ void lambda$toggleKeepScreenOnFlag$6(Context context, boolean z) {
        if (context instanceof Activity) {
            GeneratedOutlineSupport1.outline172("Setting screen on window flag to ", z);
            Activity activity = (Activity) context;
            if (z) {
                activity.getWindow().addFlags(128);
            } else {
                activity.getWindow().clearFlags(128);
            }
        }
    }

    private void launchAlexa(Context context) {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        RouteContext currentRoute = routingService.getCurrentRoute();
        if (currentRoute != null) {
            String channel = this.mDriveModeMetricsHelper.getChannel(currentRoute.getRoute().getName());
            GeneratedOutlineSupport1.outline163("launchAlexa", channel, TAG);
            if (channel != null) {
                this.mDriveModeMetrics.logTTTButtonPressed(channel);
            }
        }
        if (context instanceof Activity) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.amazon.dee.app", ALEXA_VOICE_ACTIVITY_CLASS_NAME));
            intent.setFlags(67108864);
            ((Activity) context).startActivity(intent);
        }
    }

    public boolean onTabSelection(MenuItem menuItem) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onTabSelection ");
        outline107.append(menuItem.getItemId());
        Log.i(str, outline107.toString());
        int itemId = menuItem.getItemId();
        if (itemId == R.id.dm_menu_home) {
            this.presenter.navigateToHome();
            DriverDistractionLog.logTouch(LogConstants.BOTTOM_BAR, "Home");
            return true;
        } else if (itemId == R.id.dm_menu_comm) {
            this.presenter.navigateToCommunications();
            DriverDistractionLog.logTouch(LogConstants.BOTTOM_BAR, "Comms");
            return true;
        } else if (itemId == R.id.dm_menu_entertainment) {
            this.presenter.navigateToEntertainment();
            DriverDistractionLog.logTouch(LogConstants.BOTTOM_BAR, "Entertainment");
            return true;
        } else if (itemId == R.id.dm_menu_navigation) {
            this.presenter.navigateToNavigation();
            DriverDistractionLog.logTouch(LogConstants.BOTTOM_BAR, "Navigation");
            return true;
        } else if (itemId != R.id.dm_menu_devices) {
            return false;
        } else {
            this.presenter.navigateToHome();
            DriverDistractionLog.logTouch(LogConstants.BOTTOM_BAR, LandingPageMetrics.Channel.DEVICES);
            return true;
        }
    }

    private void reopenCurrentRoute() {
        Log.i(TAG, "reopenCurrentRoute");
        RoutingService routingService = (RoutingService) ComponentRegistry.getInstance().get(RoutingService.class).orNull();
        Preconditions.checkNotNull(routingService);
        RouteContext currentRoute = routingService.getCurrentRoute();
        String str = TAG;
        Log.i(str, "currentRouteContext " + currentRoute);
        if (currentRoute != null && CommsRoutes.isCommsRoute(currentRoute.getRoute())) {
            Log.i(TAG, "Don't refresh comms route when theme changes");
            Route route = currentRoute.getRoute();
            if (this.mDriveModeThemeManager.isCurrentThemeDark()) {
                route.setTheme(Route.Theme.DARK);
            } else {
                route.setTheme(Route.Theme.LIGHT);
            }
            channelSwitched(DriveModeMainContract.View.ChannelName.COMMS);
        } else if (currentRoute != null) {
            if (routingService.canNavigateBackward() && routingService.getBackstack().length > 1) {
                Log.i(TAG, "canNavigateBackward");
                Route route2 = currentRoute.getRoute();
                String str2 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("currentRoute ");
                outline107.append(route2.getName());
                Log.i(str2, outline107.toString());
                if (this.mDriveModeThemeManager.isCurrentThemeDark()) {
                    route2.setTheme(Route.Theme.DARK);
                } else {
                    route2.setTheme(Route.Theme.LIGHT);
                }
                routingService.navigateBackward();
                routingService.route(route2.getName()).with(DriveModeThemeManager.DRIVE_MODE_THEME_CHANGE_EVENT, true).addToBackStack().navigate();
                return;
            }
            String str3 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("refresh route ");
            outline1072.append(hashCode());
            Log.i(str3, outline1072.toString());
            routingService.refresh();
        } else {
            Log.e(TAG, "Current route is null, unable to re-open route");
        }
    }

    public void resetSystemBarsThemeColor() {
        Log.i(TAG, "resetSystemBarsThemeColor");
        Context context = this.headerContainer.getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.getWindow().setStatusBarColor(this.mStatusBarColor);
            activity.getWindow().setNavigationBarColor(this.mSystemNavBarColor);
            activity.getWindow().getDecorView().setSystemUiVisibility(this.mSystemVisibilityUIFlag);
        }
    }

    public void saveSystemBarsThemeColor() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("saveSystemBarsThemeColor ");
        outline107.append(this.driveModeDependenciesGenerationId);
        Log.i(str, outline107.toString());
        if (!DriveModeDependencies.doesDriveModeDaggerBelongsToTheGeneration(this.driveModeDependenciesGenerationId)) {
            return;
        }
        Context context = this.headerContainer.getContext();
        if (!(context instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) context;
        this.mStatusBarColor = activity.getWindow().getStatusBarColor();
        this.mSystemNavBarColor = activity.getWindow().getNavigationBarColor();
        this.mSystemVisibilityUIFlag = activity.getWindow().getDecorView().getSystemUiVisibility();
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("mStatusBarColor ");
        outline1072.append(this.mStatusBarColor);
        Log.i(str2, outline1072.toString());
        String str3 = TAG;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("mSystemNavBarColor ");
        outline1073.append(this.mSystemNavBarColor);
        Log.i(str3, outline1073.toString());
        String str4 = TAG;
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("mSystemVisibilityUIFlag ");
        outline1074.append(this.mSystemVisibilityUIFlag);
        Log.i(str4, outline1074.toString());
    }

    private void setChannelTitle() {
        int selectedItemId = this.tabBar.getSelectedItemId();
        DriveModeAnimationUtils.titleFadeInAnimation(this.navBarTitle);
        if (selectedItemId == R.id.dm_menu_home) {
            this.navBarTitle.setText(R.string.dm_title_landing_page);
        } else if (selectedItemId == R.id.dm_menu_comm) {
            this.navBarTitle.setText(R.string.dm_title_communication);
        } else if (selectedItemId == R.id.dm_menu_entertainment) {
            this.navBarTitle.setText(R.string.dm_title_entertainment);
        } else if (selectedItemId == R.id.dm_menu_navigation) {
            this.navBarTitle.setText(R.string.dm_title_navigate);
        } else if (selectedItemId != R.id.dm_menu_devices) {
        } else {
            this.navBarTitle.setText(R.string.dm_title_devices);
        }
    }

    public void setSystemBarsThemeColor() {
        Log.i(TAG, "setSystemBarsThemeColor");
        ContextThemeWrapper contextThemeWrapper = getContextThemeWrapper(this.headerContainer.getContext());
        this.headerContainer.setBackgroundColor(this.mDriveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicBackground));
        this.footerContainer.setBackgroundColor(this.mDriveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicBackground));
        this.mDriveModeThemeManager.setSystemBarTheme(this.headerContainer.getContext());
    }

    private void setupNavigationBar() {
        LottieAnimationView lottieAnimationView;
        Log.i(TAG, "setupNavigationBar");
        View inflate = LayoutInflater.from(getContextThemeWrapper(this.headerContainer.getContext())).inflate(R.layout.dm_navigation_bar, this.headerContainer);
        this.navigationBar = (Toolbar) inflate.findViewById(R.id.drive_mode_toolbar);
        this.navBarTitle = (TextView) inflate.findViewById(R.id.drive_mode_channel_title);
        this.navigationBar.findViewById(R.id.dm_voice_ingress_image).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$6htYoYiyhCntDCX8icbqs30VjeA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeMainActivityViewModel.this.lambda$setupNavigationBar$3$DriveModeMainActivityViewModel(view);
            }
        });
        this.navigationBar.findViewById(R.id.drive_mode_egress).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$Ev6ruLQ4FPqridtcMlTSe5buot0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeMainActivityViewModel.this.lambda$setupNavigationBar$4$DriveModeMainActivityViewModel(view);
            }
        });
        if (this.mDriveModeThemeManager.isCurrentThemeDark()) {
            lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.car_lottie_animation_dark);
        } else {
            lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.car_lottie_animation_light);
        }
        this.mLottieTitleAnimation = DriveModeAnimationUtils.loadLottieAndTitle(lottieAnimationView, this.navBarTitle);
    }

    private void setupNavigationBarV2() {
        LottieAnimationView lottieAnimationView;
        Log.i(TAG, "setupNavigationBarV2");
        View inflate = LayoutInflater.from(getContextThemeWrapper(this.headerContainer.getContext())).inflate(R.layout.dm_navigation_bar, this.headerContainer);
        this.navigationBar = (Toolbar) inflate.findViewById(R.id.drive_mode_toolbar);
        this.navBarTitle = (TextView) inflate.findViewById(R.id.drive_mode_channel_title);
        this.navigationBar.findViewById(R.id.drive_mode_egress).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$gySuRAZeJhkXqAM-JbRb_W_TlDE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeMainActivityViewModel.this.lambda$setupNavigationBarV2$5$DriveModeMainActivityViewModel(view);
            }
        });
        if (this.mDriveModeThemeManager.isCurrentThemeDark()) {
            lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.car_lottie_animation_dark);
        } else {
            lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.car_lottie_animation_light);
        }
        this.mLottieTitleAnimation = DriveModeAnimationUtils.loadLottieAndTitle(lottieAnimationView, this.navBarTitle);
    }

    private void setupTabBar() {
        Log.i(TAG, "setupTabBar");
        ContextThemeWrapper contextThemeWrapper = getContextThemeWrapper(this.footerContainer.getContext());
        this.tabBar = (BottomNavigationView) LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_tab_bar, this.footerContainer).findViewById(R.id.dm_bottom_nav);
        DriveModeAnimationUtils.attachFooterIndicator(this.tabBar, contextThemeWrapper);
        this.tabBar.setOnNavigationItemSelectedListener(new $$Lambda$DriveModeMainActivityViewModel$ASJIwGg7Ph1bFuG2qoMijCNbpA(this));
    }

    private void setupTabBarV2() {
        Log.i(TAG, "setupTabBarV2");
        ContextThemeWrapper contextThemeWrapper = getContextThemeWrapper(this.footerContainer.getContext());
        this.tabBar = (BottomNavigationView) LayoutInflater.from(contextThemeWrapper).inflate(R.layout.dm_tab_bar_v2, this.footerContainer).findViewById(R.id.dm_bottom_nav);
        DriveModeAnimationUtils.attachFooterIndicator(this.tabBar, contextThemeWrapper);
        this.tabBar.setOnNavigationItemSelectedListener(new $$Lambda$DriveModeMainActivityViewModel$ASJIwGg7Ph1bFuG2qoMijCNbpA(this));
    }

    private void startMainActivityLifecycleMonitor() {
        MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar = (MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline21(MainActivityLifecycleObserverRegistrar.class);
        Preconditions.checkNotNull(mainActivityLifecycleObserverRegistrar);
        mainActivityLifecycleObserverRegistrar.addObserver(this);
    }

    private void stopMainActivityLifecycleMonitor() {
        MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar = (MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline21(MainActivityLifecycleObserverRegistrar.class);
        Preconditions.checkNotNull(mainActivityLifecycleObserverRegistrar);
        mainActivityLifecycleObserverRegistrar.removeObserver(this);
    }

    private void toggleKeepScreenOnFlag(final boolean z, final Context context) {
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$ZlKtLUycJOSp5tWIxKaAErW5x7c
            @Override // java.lang.Runnable
            public final void run() {
                DriveModeMainActivityViewModel.lambda$toggleKeepScreenOnFlag$6(context, z);
            }
        });
    }

    private void updateDriveModeRoutesTheme() {
        Log.i(TAG, "updateDriveModeRoutesTheme");
        for (Route route : this.routingRegistry) {
            if (DriveModeRoutes.isDriveModeRoute(route)) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Updating Route Theme ");
                outline107.append(route.getName());
                Log.i(str, outline107.toString());
                route.setTheme(this.mDriveModeThemeManager.isCurrentThemeDark() ? Route.Theme.DARK : Route.Theme.LIGHT);
            }
        }
    }

    private void updateNavigationBarTheme() {
        LottieAnimationView lottieAnimationView;
        BehaviorSubject<Boolean> behaviorSubject;
        Log.i(TAG, "updateNavigationBarTheme");
        this.headerContainer.removeAllViews();
        View inflate = LayoutInflater.from(getContextThemeWrapper(this.headerContainer.getContext())).inflate(R.layout.dm_navigation_bar, this.headerContainer);
        this.navigationBar = (Toolbar) inflate.findViewById(R.id.drive_mode_toolbar);
        this.navBarTitle = (TextView) inflate.findViewById(R.id.drive_mode_channel_title);
        View findViewById = this.navigationBar.findViewById(R.id.dm_voice_ingress_image);
        if (!this.isWaitingForAccessoryStatusUpdate && (behaviorSubject = this.mAccessoryConnectionStatusObservable) != null) {
            updateTTTButtonVisibility(behaviorSubject.getValue().booleanValue());
        }
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$f2JgOM-7SVeFVBCREH7YAPSXorc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeMainActivityViewModel.this.lambda$updateNavigationBarTheme$9$DriveModeMainActivityViewModel(view);
            }
        });
        this.navigationBar.findViewById(R.id.drive_mode_egress).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$bvJNmztl_ngbYk4e3Cn86ya8MZQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeMainActivityViewModel.this.lambda$updateNavigationBarTheme$10$DriveModeMainActivityViewModel(view);
            }
        });
        AnimatorSet animatorSet = this.mLottieTitleAnimation;
        if (animatorSet != null && animatorSet.isRunning()) {
            Log.i(TAG, "Title animation was not complete, showing animation");
            if (this.mDriveModeThemeManager.isCurrentThemeDark()) {
                lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.car_lottie_animation_dark);
            } else {
                lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.car_lottie_animation_light);
            }
            this.mLottieTitleAnimation = DriveModeAnimationUtils.loadLottieAndTitle(lottieAnimationView, this.navBarTitle);
            return;
        }
        Log.i(TAG, "Title animation was complete, showing new title");
        this.navBarTitle.setVisibility(0);
    }

    private void updateNavigationBarThemeV2() {
        LottieAnimationView lottieAnimationView;
        Log.i(TAG, "updateNavigationBarThemeV2");
        this.headerContainer.removeAllViews();
        View inflate = LayoutInflater.from(getContextThemeWrapper(this.headerContainer.getContext())).inflate(R.layout.dm_navigation_bar, this.headerContainer);
        this.navigationBar = (Toolbar) inflate.findViewById(R.id.drive_mode_toolbar);
        this.navBarTitle = (TextView) inflate.findViewById(R.id.drive_mode_channel_title);
        this.navigationBar.findViewById(R.id.drive_mode_egress).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$ypY9m4LZH33wGQAVmcStIpiBHro
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeMainActivityViewModel.this.lambda$updateNavigationBarThemeV2$11$DriveModeMainActivityViewModel(view);
            }
        });
        AnimatorSet animatorSet = this.mLottieTitleAnimation;
        if (animatorSet != null && animatorSet.isRunning()) {
            Log.i(TAG, "Title animation was not complete, showing animation");
            if (this.mDriveModeThemeManager.isCurrentThemeDark()) {
                lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.car_lottie_animation_dark);
            } else {
                lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.car_lottie_animation_light);
            }
            this.mLottieTitleAnimation = DriveModeAnimationUtils.loadLottieAndTitle(lottieAnimationView, this.navBarTitle);
            return;
        }
        Log.i(TAG, "Title animation was complete, showing new title");
        this.navBarTitle.setVisibility(0);
    }

    private void updateTTTButtonVisibility(boolean z) {
        GeneratedOutlineSupport1.outline173("updateTTTButtonVisibility ", z, TAG);
        Toolbar toolbar = this.navigationBar;
        if (toolbar != null) {
            View findViewById = toolbar.findViewById(R.id.dm_voice_ingress_image);
            if (!z && this.mNetworkConnectivityManager.isNetworkConnected() && !this.mIsAMPDDevice) {
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(4);
            }
        }
    }

    private void updateTabBarTheme() {
        Log.i(TAG, "updateTabBarTheme");
        this.footerContainer.removeAllViews();
        if (this.mWeblabManager.isAutoMode_2_0_WeblabEnabled()) {
            setupTabBarV2();
        } else {
            setupTabBar();
        }
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion.ViewModel
    public boolean applyContentMode(int i) {
        if (this.headerContainer != null && this.footerContainer != null) {
            boolean isDriveModeContentMode = isDriveModeContentMode(i);
            if (isDriveModeContentMode) {
                ensureDriveModeIngress();
            }
            if (i == 8) {
                this.headerContainer.setVisibility(0);
                this.footerContainer.setVisibility(0);
            } else if (i == 10) {
                this.headerContainer.setVisibility(8);
                this.footerContainer.setVisibility(0);
            } else if (i != 11) {
                this.headerContainer.setVisibility(8);
                this.footerContainer.setVisibility(8);
            } else {
                this.headerContainer.setVisibility(0);
                this.footerContainer.setVisibility(8);
            }
            return isDriveModeContentMode;
        }
        Log.w(TAG, "Header and footer for Drive Mode not initialized");
        return false;
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.View
    public void channelSwitched(DriveModeMainContract.View.ChannelName channelName) {
        int i;
        String str = TAG;
        Log.i(str, "channelSwitched " + channelName);
        if (channelName == DriveModeMainContract.View.ChannelName.HOME) {
            i = R.id.dm_menu_home;
        } else if (channelName == DriveModeMainContract.View.ChannelName.NAVIGATION) {
            i = R.id.dm_menu_navigation;
        } else if (channelName == DriveModeMainContract.View.ChannelName.COMMS) {
            i = R.id.dm_menu_comm;
        } else if (channelName == DriveModeMainContract.View.ChannelName.ENTERTAINMENT) {
            i = R.id.dm_menu_entertainment;
        } else {
            i = channelName == DriveModeMainContract.View.ChannelName.DEVICES ? R.id.dm_menu_devices : -1;
        }
        MenuItem findItem = this.tabBar.getMenu().findItem(i);
        if (findItem == null) {
            String str2 = TAG;
            Log.w(str2, "Menu item not found for channel " + channelName);
            return;
        }
        ContextThemeWrapper contextThemeWrapper = getContextThemeWrapper(this.tabBar.getContext());
        this.tabBar.setBackgroundColor(this.mDriveModeThemeManager.getColorFromAttribute(contextThemeWrapper, R.attr.mosaicBackground));
        this.mTabSelectedAnimation = DriveModeAnimationUtils.performTabSelectedAnimation(contextThemeWrapper, this.tabBar, findItem, this.mTabSelectedAnimation);
        setChannelTitle();
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.View
    public void exitingDriveModeRoutes() {
        String str = this + " Exiting drive mode on navigation of non drive mode route";
        this.uiThreadHandler.post(new $$Lambda$DriveModeMainActivityViewModel$PArD1fwU1c5nKMjUSyrdbzHnY(this));
    }

    ContextThemeWrapper getContextThemeWrapper(Context context) {
        return new ContextThemeWrapper(context, this.mDriveModeThemeManager.getTheme());
    }

    public void handleNetworkConnectionChange(boolean z) {
        GeneratedOutlineSupport1.outline173("handleNetworkConnectionChange ", z, TAG);
        BehaviorSubject<Boolean> behaviorSubject = this.mAccessoryConnectionStatusObservable;
        if (behaviorSubject != null) {
            updateTTTButtonVisibility(behaviorSubject.getValue().booleanValue());
        }
    }

    @VisibleForTesting
    /* renamed from: handleThemeChangeEvent */
    public void lambda$null$7$DriveModeMainActivityViewModel(String str) {
        GeneratedOutlineSupport1.outline163("handleThemeChangeEvent ", str, TAG);
        if (this.mWeblabManager.isAutoMode_2_0_WeblabEnabled()) {
            updateNavigationBarThemeV2();
        } else {
            updateNavigationBarTheme();
        }
        updateTabBarTheme();
        setSystemBarsThemeColor();
        reopenCurrentRoute();
        updateDriveModeRoutesTheme();
    }

    void initNetworkConnectionManager() {
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$ukSrPudiB7LKac4j8zO6E8wxoEg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DriveModeMainActivityViewModel.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion.ViewModel
    @Deprecated
    public void initialize(@Nullable Bundle bundle, @NonNull ViewGroup viewGroup, @NonNull ViewGroup viewGroup2, @NonNull ViewGroup viewGroup3) {
        initialize(bundle, viewGroup, viewGroup3);
    }

    public /* synthetic */ void lambda$initAccessoryConnectionObserver$2$DriveModeMainActivityViewModel(Boolean bool) throws Throwable {
        String str = "Accessory connection status changed " + bool;
        updateTTTButtonVisibility(bool.booleanValue());
    }

    public /* synthetic */ void lambda$initThemeChangeListener$8$DriveModeMainActivityViewModel(Message message) {
        final String payloadAsString = message.getPayloadAsString();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("handle theme change ");
        outline107.append(message.getSource().name());
        Log.i(str, outline107.toString());
        if (message.getSource() != Message.Source.TComm) {
            Log.i(TAG, "Refreshing drive mode UI based on theme");
            this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$ku2bbunzgnead42i6KgS7JlchJI
                @Override // java.lang.Runnable
                public final void run() {
                    DriveModeMainActivityViewModel.this.lambda$null$7$DriveModeMainActivityViewModel(payloadAsString);
                }
            });
        }
    }

    public /* synthetic */ void lambda$navigateToDefaultRoute$1$DriveModeMainActivityViewModel() {
        DriveModeMainContract.Presenter presenter = this.presenter;
        if (presenter != null) {
            presenter.navigateToHome();
        } else {
            Log.e(TAG, "Drive Mode presenter could not be found.");
        }
    }

    public /* synthetic */ void lambda$setupNavigationBar$3$DriveModeMainActivityViewModel(View view) {
        launchAlexa(this.headerContainer.getContext());
    }

    public /* synthetic */ void lambda$setupNavigationBar$4$DriveModeMainActivityViewModel(View view) {
        this.presenter.egress();
    }

    public /* synthetic */ void lambda$setupNavigationBarV2$5$DriveModeMainActivityViewModel(View view) {
        this.presenter.egress();
    }

    public /* synthetic */ void lambda$updateNavigationBarTheme$10$DriveModeMainActivityViewModel(View view) {
        this.presenter.egress();
    }

    public /* synthetic */ void lambda$updateNavigationBarTheme$9$DriveModeMainActivityViewModel(View view) {
        launchAlexa(this.headerContainer.getContext());
    }

    public /* synthetic */ void lambda$updateNavigationBarThemeV2$11$DriveModeMainActivityViewModel(View view) {
        this.presenter.egress();
    }

    public void navigateToDefaultRoute() {
        ensureDriveModeIngress();
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$Rgpes__HNi4iUmVeQZmn2s21APE
            @Override // java.lang.Runnable
            public final void run() {
                DriveModeMainActivityViewModel.this.lambda$navigateToDefaultRoute$1$DriveModeMainActivityViewModel();
            }
        });
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityCreated() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityDestroy() {
        String str = this + " Activity destroyed. Egressing from Drive Mode";
        ModeService modeService = this.modeService;
        if (modeService != null) {
            modeService.exitDriveMode(2);
        }
        this.uiThreadHandler.post(new $$Lambda$DriveModeMainActivityViewModel$PArD1fwU1c5nKMjUSyrdbzHnY(this));
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityPause() {
        String str = this + " onActivityPause";
        ViewGroup viewGroup = this.headerContainer;
        if (viewGroup != null) {
            toggleKeepScreenOnFlag(false, viewGroup.getContext());
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityResume() {
        String str = this + " onActivityResume";
        ViewGroup viewGroup = this.headerContainer;
        if (viewGroup != null) {
            toggleKeepScreenOnFlag(true, viewGroup.getContext());
            this.uiThreadHandler.post($$Lambda$DriveModeMainActivityViewModel$vr2TDeTHvMkSxZzxHWMo9HFs7r4.INSTANCE);
        }
        DriverDistractionLog.logEvent(LogConstants.ACTION_RETURN_TO_APP, "");
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStart() {
        String str = this + " onActivityStart";
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$rt2FK3AlcyobuZLvup25hJCgTKQ
            @Override // java.lang.Runnable
            public final void run() {
                DriveModeMainActivityViewModel.this.saveSystemBarsThemeColor();
            }
        });
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$THD2Zqed3VUJXFGJP-E7v5rql5k
            @Override // java.lang.Runnable
            public final void run() {
                DriveModeMainActivityViewModel.this.setSystemBarsThemeColor();
            }
        });
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStop() {
        String str = this + " onActivityStop";
        DriverDistractionLog.logEvent(LogConstants.ACTION_SWITCH_TO_EXTERNAL_APP, "");
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$KIAauG4EEWm0ptzm5G9cALUbCEw
            @Override // java.lang.Runnable
            public final void run() {
                DriveModeMainActivityViewModel.this.resetSystemBarsThemeColor();
            }
        });
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion.ViewModel
    public void restoreState(@NonNull Bundle bundle) {
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.View
    public void routeNavigationFailed() {
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion.ViewModel
    @Nullable
    public Bundle saveState() {
        return null;
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.View
    public void setNavigationBarVisibility(boolean z) {
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.View
    public void setTabBarVisibility(boolean z) {
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("DMViewModel(generation:"), this.driveModeDependenciesGenerationId, ")");
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion.ViewModel
    public void initialize(@Nullable Bundle bundle, @NonNull ViewGroup viewGroup, @NonNull ViewGroup viewGroup2) {
        this.headerContainer = viewGroup;
        this.footerContainer = viewGroup2;
        this.uiThreadHandler = new Handler();
        ensureDriveModeDependenciesInitialized();
    }
}
