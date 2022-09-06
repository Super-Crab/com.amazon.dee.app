package com.amazon.dee.app.ui.main;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewStubProxy;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.dialog.impl.Dialog;
import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.feature.provider.api.FeatureStore;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.permissions.DefaultPermissionsService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.ui.TabBarAnimator;
import com.amazon.alexa.redesign.HomeViewDelegate;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.viewmanagement.impl.ViewControllerFactoryProducer;
import com.amazon.alexa.viewmanagement.impl.ViewControllerFragment;
import com.amazon.alexa.viewmanagement.impl.ViewManagerDelegate;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.screen.ScreenLockManager;
import com.amazon.alexa.voice.ui.VoiceActivity;
import com.amazon.alexa.voice.ui.VoiceActivityLauncher;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.MainBinding;
import com.amazon.dee.app.dependencies.ApplicationComponent;
import com.amazon.dee.app.dependencies.ConversationModule;
import com.amazon.dee.app.dependencies.ElementsModule;
import com.amazon.dee.app.dependencies.MainComponent;
import com.amazon.dee.app.dependencies.MainModule;
import com.amazon.dee.app.dependencies.WebModule;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.elements.ReactNativeViewDelegate;
import com.amazon.dee.app.elements.ReactTransitionCompletionListener;
import com.amazon.dee.app.elements.bridges.DeviceInformationModule;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.amazon.dee.app.services.testing.TestConfigurationService;
import com.amazon.dee.app.services.toolbar.ToolbarDelegate;
import com.amazon.dee.app.ui.comms.CommsViewModel;
import com.amazon.dee.app.ui.fullscreentakeover.FullScreenTakeoverViewModel;
import com.amazon.dee.app.ui.main.ThemeRecorder;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.dee.app.ui.menu.MenuItem;
import com.amazon.dee.app.ui.menu.MenuItemHandler;
import com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel;
import com.amazon.dee.app.ui.util.ActivityRequestCode;
import com.amazon.dee.app.ui.web.AlexaWebView;
import com.amazon.dee.app.ui.web.DeviceInfo;
import com.amazon.dee.app.ui.web.HouseholdLibraryInfo;
import com.amazon.dee.app.util.DriveModeSettingsUtils;
import com.amazon.dee.app.util.PermissionsUtils;
import com.amazon.dee.app.util.WebUtils;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.WebIntentUtils;
import com.amazon.deecomms.services.conversation.ConversationUIDelegate;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.LatencyMarker;
import com.amazon.latencyinfra.LatencyNamespace;
import com.amazon.latencyinfra.SingleEventInputArgument;
import com.amazon.latencyinfra.SingleLatencyAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import dagger.Lazy;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class MainActivity extends AppCompatActivity implements MainHandler, MenuItemHandler, DefaultHardwareBackBtnHandler, ConversationUIDelegate, ReactNativeViewDelegate, VoiceActivityLauncher, PermissionAwareActivity, ToolbarDelegate, HomeViewDelegate, ViewManagerDelegate {
    private static final String ACTION_LAUNCH_PLAYER = "com.amazon.intent.action.LAUNCH_PLAYER";
    private static final String CHANGE_ENDPOINT_ACTION = "com.amazon.dee.ENDPOINT";
    private static final String COLOR_SCHEME = "colorScheme";
    private static final String COM_AMAZON_INTENT_ACTION_VKICK = "com.amazon.intent.action.VKICK";
    private static final String DRIVE_MODE_VIEW_MODEL = "driveMode";
    private static final String NOW_PLAYING_VIEW_MODEL = "nowPlaying";
    private static final String QUICK_ACTIONS_WIDGET_INTENT_ACTION = "com.amazon.alexa.action.QUICK_ACTIONS_WIDGET";
    private static final String ROUTINES_SHARED_PATH = "/routines/shared";
    private static final String TAG = Log.tag(MainActivity.class);
    private static final String TEST_ID_KEY = "PerfTestID";
    private static final int TIME_UNTIL_WEBVIEW_LOAD_FINISH = 500;
    private static final String VIEW_MODEL = "viewModel";
    @Inject
    AlexaMenu alexaMenu;
    Disposable alexaMenuSubscription;
    private ApplicationComponent applicationComponent;
    private View applicationRootView;
    MainBinding binding;
    @Inject
    Lazy<CertificateReaderService> certificateReaderService;
    @Inject
    CommsViewModel commsViewModel;
    MainComponent component;
    Context context;
    @Inject
    CrashMetadata crashMetadata;
    BaseDeviceAdapter currentAdapter;
    DeviceInformation deviceInformation;
    DefaultDeviceAdapter devicesAdapter;
    @Inject
    DriveModeMainActivityCompanion.ViewModel driveModeViewModel;
    @Inject
    FeatureQuery featureQuery;
    @Inject
    Lazy<FeatureServiceV2> featureServiceV2Lazy;
    @Inject
    Lazy<FeatureStore> featureStore;
    @Inject
    FullScreenTakeoverViewModel fullScreenTakeoverViewModel;
    HouseholdLibraryAdapter householdLibraryAdapter;
    Disposable idleSubscription;
    @Inject
    LatencyInfra latencyInfra;
    private MainActivityLifecycleService mainActivityLifecycleService;
    @Inject
    Lazy<MainBindingThemeSetter> mainBindingThemeSetter;
    @Inject
    Lazy<ModeService> modeService;
    MusicDeviceAdapter musicDeviceAdapter;
    @Inject
    NowPlayingViewModel nowPlayingViewModel;
    @Inject
    Lazy<DefaultPermissionsService> permissionsService;
    ReactFeatureManager reactFeatureManager;
    private PermissionListener reactNativePermissionListener;
    private Runnable reactNativePermissionsCallback;
    ScreenLockManager screenLockManager;
    MainTabBarAnimator tabBarAnimator;
    @Inject
    TabLayoutStatusService tabLayoutStatus;
    @Inject
    Lazy<TabSelectionAnimator> tabSelectionAnimator;
    @Inject
    Lazy<TestConfigurationService> testConfigurations;
    @Inject
    Lazy<ThemeRecorder> themeRecorder;
    @Inject
    Lazy<ViewControllerFactoryProducer> viewControllerFactoryProducer;
    MainViewModel viewModel;
    VoiceService voiceService;
    private AlexaWebView webView;
    private long tabClickThrottle = 100;
    private long lastTabClickTime = 0;
    List ignoredActivityCodes = Arrays.asList(2, 11, 18237, Integer.valueOf((int) ActivityRequestCode.IMAGE_PICKER_REQUEST), Integer.valueOf((int) ActivityRequestCode.CAMERA_PICKER_REQUEST));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class WebDelegate implements AlexaWebView.WebDelegate {
        WebDelegate() {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void login() {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void onHandleIntent(@NonNull Intent intent) {
            if ("android.intent.action.VIEW".equals(intent.getAction())) {
                MainActivity.this.startActivity(intent);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setDevices(@Nullable List<DeviceInfo> list) {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setFullScreen(boolean z) {
            MainActivity.this.viewModel.isFullScreenMode.set(z);
            MainActivity.this.viewModel.isFullScreenMode.notifyChange();
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setHeaderTitle(@Nullable CharSequence charSequence) {
            if (!MainActivity.this.viewModel.isRouteActive("conversations", RouteName.HELP)) {
                MainActivity.this.viewModel.setHeaderTitle(charSequence);
            }
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setHeaderVisible(boolean z) {
            MainActivity.this.setHeaderVisible(z);
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setHouseholdLibraries(@Nullable List<HouseholdLibraryInfo> list) {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setHouseholdVisible(boolean z) {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setLoading(boolean z) {
            MainActivity.this.switchLoadingState(z);
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setNowPlaying(boolean z) {
            MainActivity.this.nowPlayingViewModel.setRemoteAudioPlaying(z);
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setSelectedDevice(@Nullable DeviceInfo deviceInfo) {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setSelectedLibrary(@Nullable HouseholdLibraryInfo householdLibraryInfo) {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setYourSkillsTitleAndDisplay(@Nullable CharSequence charSequence, boolean z) {
        }

        @Override // com.amazon.dee.app.ui.web.AlexaWebView.WebDelegate
        public void setYourSkillsVisible(boolean z) {
        }
    }

    private void checkColdStartForceTokenExpirationTest() {
        if (!this.certificateReaderService.mo358get().isPerformanceProfilingBuild() || !getIntent().getBooleanExtra("expireTokens", false)) {
            return;
        }
        IdentityService identityService = this.applicationComponent.identityService();
        if (!(identityService instanceof MAPIdentityService)) {
            return;
        }
        ((MAPIdentityService) identityService).forceTokenRefreshOnStart();
    }

    private void clearAccessibilityFocus(View view) {
        view.performAccessibilityAction(128, null);
    }

    private static float convertDPToPixels(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    private boolean handleIntent(@NonNull Intent intent) {
        Uri extractDeepLinkUri = extractDeepLinkUri(intent);
        if (extractDeepLinkUri != null) {
            String.format("intent action: %s uri: %s", intent.getAction(), extractDeepLinkUri);
            IdentityService identityService = this.applicationComponent.identityService();
            UserIdentity user = identityService.getUser(TAG);
            if (this.featureServiceV2Lazy.mo358get() != null && this.featureServiceV2Lazy.mo358get().hasAccess("ALEXA_BILOBA_ANDROID_MENU_INGRESS", false) && user != null && user.getDelegatedProfile() != null && !WebUtils.doesUriSupportDelegatedProfiles(extractDeepLinkUri)) {
                try {
                    identityService.terminateDelegation(TAG);
                } catch (UnsupportedOperationException unused) {
                    Log.e(TAG, "Terminate remote management session not supported");
                }
            }
            if (extractDeepLinkUri.getPath() != null && extractDeepLinkUri.getPath().startsWith(ROUTINES_SHARED_PATH) && (this.featureServiceV2Lazy.mo358get() == null || !this.featureServiceV2Lazy.mo358get().hasAccess("ALEXA_ROUTINES_SHARE_ROUTE", false))) {
                Log.i(TAG, "User does not have access to ALEXA_ROUTINES_SHARE_ROUTE feature.");
                return false;
            }
            this.viewModel.handleDeepLinking(extractDeepLinkUri);
            return true;
        }
        return false;
    }

    private void initGlobalLayoutListener() {
        this.applicationRootView = findViewById(R.id.root_container);
        setupGlobalLayoutListener(this.applicationRootView);
    }

    private void initializeCoreService() {
        this.mainActivityLifecycleService = ((AlexaApplication) getApplication()).getComponent().mainActivityLifecycleService();
        this.mainActivityLifecycleService.notifyOnCreated();
    }

    private boolean isWebViewInitialized() {
        return this.webView != null;
    }

    private void registerMoreLongPress() {
    }

    private void setHeaderAccessibilityDescription() {
        ViewCompat.setAccessibilityDelegate(this.binding.title, new AccessibilityDelegateCompat() { // from class: com.amazon.dee.app.ui.main.MainActivity.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                accessibilityNodeInfoCompat.setClickable(false);
                accessibilityNodeInfoCompat.setRoleDescription(MainActivity.this.getString(R.string.header_title_accessibility_click_description));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeaderVisible(boolean z) {
        this.viewModel.setHeaderVisible(z);
    }

    private void setTestIDIfAvailable() {
        this.latencyInfra.setTestID(getIntent().getStringExtra(TEST_ID_KEY));
    }

    private void setupGlobalLayoutListener(final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainActivity$6F30I5-LlBvU_iDa8u_L9-73xdQ
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                MainActivity.this.lambda$setupGlobalLayoutListener$0$MainActivity(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchLoadingState(boolean z) {
        if (z) {
            this.viewModel.setDisabled(true);
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainActivity$sPME024jqIa4k5tCcPV_bmGuvFk
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.this.lambda$switchLoadingState$2$MainActivity();
                }
            }, 500L);
        }
    }

    private boolean tabClickAllowed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastTabClickTime > this.tabClickThrottle) {
            this.lastTabClickTime = currentTimeMillis;
            return true;
        }
        return false;
    }

    private void updateUIWhenKeyboardAbsent() {
        this.viewModel.isSoftInputVisible.set(false);
    }

    private void updateUIWhenKeyboardPresent() {
        this.viewModel.isSoftInputVisible.set(true);
    }

    @Nullable
    @VisibleForTesting
    protected Uri extractDeepLinkUri(@NonNull Intent intent) {
        if (intent.getBooleanExtra(MessagingReceiver.NOTIFICATION, false)) {
            this.viewModel.notifyClickedOnNotification(intent.getExtras());
        }
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
        if ("android.intent.action.VIEW".equals(action)) {
            if ((intent.getFlags() & 1048576) != 0) {
                return null;
            }
            return intent.getData();
        } else if (COM_AMAZON_INTENT_ACTION_VKICK.equals(action) && intent.getExtras() != null) {
            if (TextUtils.isEmpty(stringExtra)) {
                return null;
            }
            return Uri.parse(stringExtra);
        } else if (ACTION_LAUNCH_PLAYER.equals(action)) {
            this.viewModel.notifyNowPlayingSelected();
            return null;
        } else if ("android.intent.action.MAIN".equals(action)) {
            String str = Log.line() + " Startup";
            return null;
        } else if (Constants.COMMS_VOICE_NAV_ACTION.equals(action)) {
            this.viewModel.handleCommsVoiceNavigation(CommsView.valueOf(intent.getStringExtra(Constants.COMMS_VOICE_NAV_ACTION)));
            return null;
        } else if (DriveModeSettingsUtils.DRIVE_MODE_NOTIFICATION_INTENT.equals(action)) {
            DriveModeSettingsUtils.handleDriveModeNotification();
            return null;
        } else if (!"com.amazon.alexa.action.QUICK_ACTIONS_WIDGET".equals(action)) {
            return null;
        } else {
            Uri data = intent.getData();
            this.viewModel.notifyQuickActionsWidgetClicked(intent);
            return data;
        }
    }

    public MainComponent getComponent() {
        if (this.component == null) {
            this.component = ((AlexaApplication) getApplication()).getComponent().plus(new MainModule(this), new ConversationModule(this), new ElementsModule(this));
            this.reactFeatureManager = this.component.reactFeatureManager();
        }
        return this.component;
    }

    @NonNull
    public AlexaWebView getWebview() {
        if (isWebViewInitialized()) {
            return this.webView;
        }
        ViewStubProxy viewStubProxy = this.binding.webviewstub;
        if (viewStubProxy != null && !viewStubProxy.isInflated()) {
            this.webView = (AlexaWebView) viewStubProxy.getViewStub().inflate();
        } else {
            this.webView = (AlexaWebView) viewStubProxy.getRoot();
        }
        this.webView.setWebComponent(getComponent().plus(new WebModule(this.webView)));
        this.webView.setWebDelegate(new WebDelegate());
        this.webView.createWebApp();
        this.webView.setOnPageFinishedListener(this.viewModel);
        return this.webView;
    }

    @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
    public void invokeDefaultOnBackPressed() {
        onBackPressed();
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate
    public boolean isConversationUIActive() {
        return this.viewModel.isRouteActive("conversations");
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate
    public boolean isFullScreen() {
        return this.viewModel.isFullScreenMode.get();
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate
    public boolean isReactNativeCommsActive() {
        return this.viewModel.isRouteActive(RouteName.REACT_NATIVE_COMMS);
    }

    public /* synthetic */ void lambda$onStart$1$MainActivity(Boolean bool) throws Throwable {
        if (!bool.booleanValue() || this.modeService.mo358get().isDriveModeForeground()) {
            return;
        }
        Log.i(TAG, "is idle and not in drive mode so releasing screen lock");
        this.screenLockManager.releaseLock();
    }

    public /* synthetic */ void lambda$setupGlobalLayoutListener$0$MainActivity(View view) {
        if (view.getRootView().getHeight() - view.getHeight() > convertDPToPixels(getApplicationContext(), 200.0f)) {
            updateUIWhenKeyboardPresent();
        } else {
            updateUIWhenKeyboardAbsent();
        }
    }

    public /* synthetic */ void lambda$switchLoadingState$2$MainActivity() {
        this.viewModel.setDisabled(false);
    }

    @Override // com.amazon.alexa.voice.ui.VoiceActivityLauncher
    public void launchVoiceActivity(boolean z) {
        this.tabBarAnimator.hide(TabBarAnimator.AnimationSpeed.SHORT);
        Intent intent = new Intent(this, VoiceActivity.class);
        if (z) {
            intent.setAction(VoiceActivity.ACTION_LAUNCH_FROM_INGRESS);
        }
        startActivityForResult(intent, 6);
    }

    @Override // com.amazon.dee.app.services.toolbar.ToolbarDelegate
    public void mountToolbar(View view) {
        FrameLayout frameLayout;
        MainBinding mainBinding = this.binding;
        if (mainBinding != null && (frameLayout = mainBinding.transportBar) != null) {
            if (frameLayout.getChildCount() == 0 && view.getParent() == null) {
                this.binding.transportBar.addView(view);
            } else {
                Log.i(TAG, "Transport bar already mounted.");
            }
            this.viewModel.toolbar.show.set(true);
            return;
        }
        Log.i(TAG, "Transport did not mount.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        getWebview().onActivityResult(i, i2, intent);
        this.reactFeatureManager.onActivityResult(i, i2, intent);
        if (!this.ignoredActivityCodes.contains(Integer.valueOf(i))) {
            if (6 == i) {
                this.tabBarAnimator.reveal(TabBarAnimator.AnimationSpeed.SHORT);
            } else {
                this.tabBarAnimator.reveal(TabBarAnimator.AnimationSpeed.IMMEDIATE);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ViewControllerFragment) {
            getComponent().inject((ViewControllerFragment) fragment);
        }
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onBackClicked() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.viewModel.navigateBack();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.viewModel.navigateBack();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Intent intent = new Intent();
        intent.setAction(DeviceInformationModule.ON_CONFIG_CHANGE_EVENT);
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        setTheme(2132017722);
        super.onCreate(bundle);
        this.context = this;
        this.binding = (MainBinding) DataBindingUtil.setContentView(this, R.layout.main);
        this.binding.setHandler(this);
        this.applicationComponent = ((AlexaApplication) getApplication()).getComponent();
        this.applicationComponent.rnLogPrinter();
        getComponent().inject(this);
        this.latencyInfra.mark(LatencyMarker.MAIN_ACTIVITY_COMPONENT_INJECT_CSL, LatencyNamespace.PROFILE_COLDSTART);
        LatencyService.onCreateStarted();
        setTestIDIfAvailable();
        setTheme(2132017720);
        checkColdStartForceTokenExpirationTest();
        if (this.deviceInformation == null) {
            this.deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
        }
        if (this.deviceInformation.isPhoneFormFactor()) {
            setRequestedOrientation(1);
        }
        this.featureStore.mo358get().initialize();
        this.voiceService = this.applicationComponent.voiceService();
        LatencyService.voiceInitialized();
        this.applicationComponent.accountService().setActivity(this);
        Dialog.setMainActivity(this);
        this.screenLockManager = new ScreenLockManager(getWindow(), new Handler(), this) { // from class: com.amazon.dee.app.ui.main.MainActivity.1
            @Override // com.amazon.alexa.voice.screen.ScreenLockManager
            public boolean shouldDisableKeepScreenOn() {
                return !MainActivity.this.voiceService.isAlexaActive();
            }
        };
        int[] iArr = {R.id.main_content, R.id.webview};
        LayoutTransition layoutTransition = new LayoutTransition();
        ReactTransitionCompletionListener.configureLayoutTransition(layoutTransition);
        for (int i : iArr) {
            ReactTransitionCompletionListener.setLayoutTransition((ViewGroup) findViewById(i), layoutTransition);
        }
        this.viewModel = getComponent().inject(new MainViewModel());
        Bundle bundle2 = null;
        this.viewModel.create(bundle == null ? null : bundle.getBundle(VIEW_MODEL));
        this.viewModel.notifyOnCreateStart(MainActivity.class.getSimpleName());
        if (CHANGE_ENDPOINT_ACTION.equals(getIntent().getAction()) && this.certificateReaderService.mo358get().isDebugSigned()) {
            this.viewModel.overrideHost(getIntent().getStringExtra("android.intent.extra.TEXT"));
        }
        this.testConfigurations.mo358get().initialize();
        this.binding.setViewModel(this.viewModel);
        this.binding.setToolbarViewModel(this.viewModel.toolbar);
        this.tabBarAnimator = new MainTabBarAnimator(this.binding, getResources());
        this.nowPlayingViewModel.initialize(bundle, this.viewModel, this.binding.player);
        this.commsViewModel.initialize();
        this.fullScreenTakeoverViewModel.initialize(this.context, this.binding.fullscreenContainer);
        DriveModeMainActivityCompanion.ViewModel viewModel = this.driveModeViewModel;
        MainBinding mainBinding = this.binding;
        viewModel.initialize(bundle, mainBinding.driveModeHeader, mainBinding.driveModeFooter);
        this.devicesAdapter = new DefaultDeviceAdapter();
        this.musicDeviceAdapter = new MusicDeviceAdapter();
        this.householdLibraryAdapter = new HouseholdLibraryAdapter();
        this.currentAdapter = this.devicesAdapter;
        this.viewModel.notifyOnCreateFinish(MainActivity.class.getSimpleName());
        this.mainBindingThemeSetter.mo358get().register(this.binding, this.viewModel.currentTheme);
        LatencyService.onCreateFinished();
        this.latencyInfra.mark(LatencyMarker.MAIN_ACTIVITY_CREATE_CSL, LatencyNamespace.PROFILE_COLDSTART);
        Uri extractDeepLinkUri = extractDeepLinkUri(getIntent());
        if (extractDeepLinkUri != null) {
            this.crashMetadata.put("started_from_deeplink", true);
        }
        MainViewModel mainViewModel = this.viewModel;
        if (bundle != null) {
            bundle2 = bundle.getBundle(VIEW_MODEL);
        }
        mainViewModel.start(extractDeepLinkUri, bundle2);
        LatencyService.viewModelStarted();
        MainBinding mainBinding2 = this.binding;
        this.tabSelectionAnimator.mo358get().initialize(mainBinding2.tabLine, mainBinding2.tabLayout, this.viewModel);
        this.voiceService.setVoiceActivityLauncher(this);
        this.voiceService.setTabBarAnimator(this.tabBarAnimator);
        initializeCoreService();
        initGlobalLayoutListener();
        registerMoreLongPress();
        this.themeRecorder.mo358get().recordThemeOnSchemeChange(bundle == null ? ThemeRecorder.ColorScheme.UNKNOWN : (ThemeRecorder.ColorScheme) bundle.get(COLOR_SCHEME));
        setHeaderAccessibilityDescription();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.voiceService.setTabBarAnimator(null);
        this.mainBindingThemeSetter.mo358get().deregister();
        this.tabSelectionAnimator.mo358get().deregister();
        if (this.binding != null) {
            if (isWebViewInitialized()) {
                getWebview().destroyWebApp();
            }
            this.binding.unbind();
            this.binding = null;
        }
        Disposable disposable = this.alexaMenuSubscription;
        if (disposable != null && !disposable.isDisposed()) {
            this.alexaMenuSubscription.dispose();
        }
        Disposable disposable2 = this.idleSubscription;
        if (disposable2 != null) {
            if (!disposable2.isDisposed()) {
                this.idleSubscription.dispose();
            }
            this.idleSubscription = null;
        }
        this.screenLockManager.releaseLock();
        this.viewModel.destroy();
        this.viewControllerFactoryProducer.mo358get().clear();
        this.reactFeatureManager.onDestroy();
        this.mainActivityLifecycleService.notifyOnDestroy();
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onHeaderClicked() {
        this.viewModel.notifyHeaderClicked();
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onHelpAndFeedbackClicked() {
        this.viewModel.notifyHelpAndFeedbackSelected();
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onIngressClicked() {
        if (tabClickAllowed()) {
            this.viewModel.ingressClicked();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, @NonNull KeyEvent keyEvent) {
        return this.reactFeatureManager.onKeyUp(i, keyEvent) || super.onKeyUp(i, keyEvent);
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onMenuClicked() {
    }

    @Override // com.amazon.dee.app.ui.menu.MenuItemHandler
    public void onMenuItemSelected(MenuItem menuItem) {
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public boolean onMenuKeyEvent(int i, KeyEvent keyEvent) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        super.onNewIntent(intent);
        IdentityService identityService = this.applicationComponent.identityService();
        UserIdentity user = identityService.getUser(TAG);
        if (user != null && identityService.isRegistered(TAG) && !user.hasAcceptedEula()) {
            return;
        }
        handleIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.viewModel.notifyOnPause();
        this.reactFeatureManager.onPause();
        this.tabBarAnimator.reset();
        this.mainActivityLifecycleService.notifyOnPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        this.latencyInfra.record(SingleLatencyAction.RECORD_DURATION_FROM_APP_START, new SingleEventInputArgument.Builder().withEventName(AlexaMetricsConstants.MetricEvents.CS_NATIVE_INIT).withNamespace(LatencyNamespace.CORE_COLDSTART).withLogOption(true).withMetricsOption(true).build());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(final int i, @NonNull final String[] strArr, @NonNull final int[] iArr) {
        if (this.reactNativePermissionListener != null) {
            this.reactNativePermissionsCallback = new Runnable() { // from class: com.amazon.dee.app.ui.main.MainActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    if (MainActivity.this.reactNativePermissionListener == null || !MainActivity.this.reactNativePermissionListener.onRequestPermissionsResult(i, strArr, iArr)) {
                        return;
                    }
                    MainActivity.this.reactNativePermissionListener = null;
                }
            };
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (PermissionsUtils.isPermissionRequest(i)) {
            PermissionsUtils.onRequestPermissionsResult(i, strArr, iArr);
        } else if (this.permissionsService.mo358get().isRequested(i)) {
            this.permissionsService.mo358get().onResult(i, strArr, iArr);
        } else {
            getWebview().onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        this.viewModel.notifyOnRestart();
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.viewModel.restoreState((Bundle) bundle.get(VIEW_MODEL));
        this.nowPlayingViewModel.restoreState(bundle.getBundle("nowPlaying"));
        this.driveModeViewModel.restoreState(bundle.getBundle(DRIVE_MODE_VIEW_MODEL));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
        } catch (IllegalArgumentException e) {
            if (getTaskId() == -1) {
                Log.e(TAG, e, "Caught IllegalArgumentException on resuming into dead task.", new Object[0]);
                finishAndRemoveTask();
                System.exit(0);
            }
        }
        this.viewModel.notifyOnResume();
        if (this.voiceService.isAlexaActive()) {
            this.screenLockManager.requestLock();
        }
        this.reactFeatureManager.onResume();
        this.mainActivityLifecycleService.notifyOnResume();
        Runnable runnable = this.reactNativePermissionsCallback;
        if (runnable != null) {
            runnable.run();
            this.reactNativePermissionsCallback = null;
        }
        this.latencyInfra.mark(LatencyMarker.MAIN_ACTIVITY_RESUME_CSL, LatencyNamespace.PROFILE_COLDSTART);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBundle(VIEW_MODEL, this.viewModel.saveState());
        bundle.putBundle("nowPlaying", this.nowPlayingViewModel.saveState());
        bundle.putBundle(DRIVE_MODE_VIEW_MODEL, this.driveModeViewModel.saveState());
        bundle.putSerializable(COLOR_SCHEME, this.themeRecorder.mo358get().getColorScheme());
        if (isWebViewInitialized()) {
            bundle.putString(EventBusMessagingReceiver.NOTIFICATION_URL_TYPE_WEB, getWebview().getUrl());
        }
        bundle.remove("android:support:fragments");
        bundle.remove("android:fragments");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.viewModel.notifyOnStart();
        this.mainActivityLifecycleService.notifyOnStart();
        this.idleSubscription = this.voiceService.onIdle().subscribe(new Consumer() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$MainActivity$nCoROYG5dmV-HMLu0WJUU9vIi9k
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MainActivity.this.lambda$onStart$1$MainActivity((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.idleSubscription.dispose();
        if (!isChangingConfigurations()) {
            this.viewModel.notifyOnStop();
        }
        this.mainActivityLifecycleService.notifyOnStop();
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onTabChannelsDeviceClicked() {
        if (tabClickAllowed()) {
            clearAccessibilityFocus(this.binding.tabChannelsDevice);
            this.viewModel.notifyChannelsDevicesClicked();
        }
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onTabChannelsEntertainmentClicked() {
        if (tabClickAllowed()) {
            clearAccessibilityFocus(this.binding.tabChannelsEntertainment);
            this.viewModel.notifyChannelsEntertainmentClicked();
        }
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onTabChannelsHomeClicked() {
        if (tabClickAllowed()) {
            clearAccessibilityFocus(this.binding.tabChannelHome);
            this.viewModel.notifyChannelsHomeClicked();
        }
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onTabConversationsClicked() {
        if (tabClickAllowed()) {
            clearAccessibilityFocus(this.binding.tabConversationsLayout);
            this.viewModel.notifyConversationsSelected();
        }
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onTabNavMenuClicked() {
        if (tabClickAllowed()) {
            clearAccessibilityFocus(this.binding.tabMore);
            this.viewModel.notifyNavigationSelected();
        }
    }

    @Override // com.amazon.dee.app.ui.main.MainHandler
    public void onTabNowPlayingClicked() {
        if (tabClickAllowed()) {
            clearAccessibilityFocus(this.binding.tabNowPlayingIcon);
            this.viewModel.notifyNowPlayingSelected();
        }
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        this.reactNativePermissionListener = permissionListener;
        requestPermissions(strArr, i);
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate, com.amazon.alexa.viewmanagement.impl.ViewManagerDelegate
    public void resetHeaderMenu() {
        this.binding.toolbar.getMenu().clear();
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate, com.amazon.alexa.viewmanagement.impl.ViewManagerDelegate
    public Menu setHeaderMenu(@MenuRes int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        this.binding.toolbar.getMenu().clear();
        this.binding.toolbar.inflateMenu(i);
        this.binding.toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        return this.binding.toolbar.getMenu();
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate, com.amazon.alexa.viewmanagement.impl.ViewManagerDelegate
    public void setHeaderTitle(@Nullable CharSequence charSequence) {
        this.viewModel.setHeaderTitle(charSequence);
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate
    public void setHeadersAndTabVisible(boolean z, boolean z2) {
        int i = 1;
        if (z && !z2) {
            i = 5;
        }
        if (!z && z2) {
            i = 3;
        }
        if (!z && !z2) {
            i = 2;
        }
        this.viewModel.onRouteChangeForContentMode(i);
        this.viewModel.updateContentMode(i);
    }

    @Override // com.amazon.alexa.redesign.HomeViewDelegate
    public void setHomeActive(boolean z) {
        this.viewModel.setHomeActive(z);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate
    public void setLoadingState(boolean z) {
        this.viewModel.setLoading(z);
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate
    public void setMessageIndicatorVisible(boolean z) {
        if (z) {
            if (this.binding.tabConversationsIndicator.getVisibility() == 0) {
                return;
            }
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.tab_conversation_indicator_enter);
            this.binding.tabConversationsIndicator.setVisibility(0);
            this.binding.tabConversationsIndicator.startAnimation(loadAnimation);
        } else if (this.binding.tabConversationsIndicator.getVisibility() == 8) {
        } else {
            Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.tab_conversation_indicator_exit);
            loadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.amazon.dee.app.ui.main.MainActivity.5
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    MainActivity.this.binding.tabConversationsIndicator.setVisibility(8);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.binding.tabConversationsIndicator.startAnimation(loadAnimation2);
        }
    }

    @Override // com.amazon.dee.app.elements.ReactNativeViewDelegate
    public void setPendingViewIsReactNative(boolean z) {
        this.viewModel.pendingViewIsReactNative.set(z);
    }

    @Override // com.amazon.dee.app.elements.ReactNativeViewDelegate
    public void setReactNativeActive(boolean z) {
        this.viewModel.setReactNativeActive(z);
    }

    @Override // com.amazon.dee.app.services.toolbar.ToolbarDelegate
    public void setToolbarVisible(boolean z) {
        MainBinding mainBinding = this.binding;
        if (mainBinding != null && mainBinding.transportBar != null) {
            this.viewModel.toolbar.show.set(z);
        } else {
            Log.i(TAG, "Could not hide transport bar - no reference to it in binding.");
        }
    }

    @Override // com.amazon.deecomms.services.conversation.ConversationUIDelegate
    public void setUserName(@Nullable String str) {
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        super.startActivity(WebIntentUtils.ensurePopupIfApplicable(intent));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    @SuppressLint({"RestrictedApi"})
    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(WebIntentUtils.ensurePopupIfApplicable(intent), i);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void startActivityFromFragment(Fragment fragment, Intent intent, int i) {
        super.startActivityFromFragment(fragment, WebIntentUtils.ensurePopupIfApplicable(intent), i);
    }

    @Override // com.amazon.dee.app.services.toolbar.ToolbarDelegate
    public void unmountToolbar() {
        FrameLayout frameLayout;
        MainBinding mainBinding = this.binding;
        if (mainBinding != null && (frameLayout = mainBinding.transportBar) != null) {
            frameLayout.removeAllViews();
        } else {
            Log.i(TAG, "Could not unmount transport bar - no reference to it in binding.");
        }
    }

    @Override // com.amazon.dee.app.elements.ReactNativeViewDelegate
    public void updateIsReactNative() {
        MainViewModel mainViewModel = this.viewModel;
        mainViewModel.isReactNativeView.set(mainViewModel.pendingViewIsReactNative.get());
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, @Nullable Bundle bundle) {
        super.startActivity(WebIntentUtils.ensurePopupIfApplicable(intent), bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    @SuppressLint({"RestrictedApi"})
    public void startActivityForResult(Intent intent, int i, @Nullable Bundle bundle) {
        super.startActivityForResult(WebIntentUtils.ensurePopupIfApplicable(intent), i, bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, @Nullable Bundle bundle) {
        super.startActivityFromFragment(fragment, WebIntentUtils.ensurePopupIfApplicable(intent), i, bundle);
    }

    @Override // android.app.Activity
    public void startActivityFromFragment(@NonNull android.app.Fragment fragment, Intent intent, int i) {
        super.startActivityFromFragment(fragment, WebIntentUtils.ensurePopupIfApplicable(intent), i);
    }

    @Override // android.app.Activity
    public void startActivityFromFragment(@NonNull android.app.Fragment fragment, Intent intent, int i, @Nullable Bundle bundle) {
        super.startActivityFromFragment(fragment, WebIntentUtils.ensurePopupIfApplicable(intent), i, bundle);
    }
}
