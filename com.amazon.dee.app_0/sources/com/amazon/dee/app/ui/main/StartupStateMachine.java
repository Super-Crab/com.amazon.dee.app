package com.amazon.dee.app.ui.main;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceConstants;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventing.EventArgs;
import com.amazon.alexa.eventing.EventHandler;
import com.amazon.alexa.eventing.EventSubscription;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.dee.app.BuildConfig;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.metrics.MetricsHelper;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.ui.main.StartupStateMachine;
import com.amazon.dee.app.ui.voiceenrollment.VoiceEnrollmentLauncher;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.LatencyMarker;
import com.amazon.latencyinfra.LatencyNamespace;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import java.util.Timer;
import java.util.TimerTask;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class StartupStateMachine implements RoutingService.RouteInterceptor {
    private static final int BRIDGE_WAIT_DURATION = 10;
    @VisibleForTesting
    static final String IS_NEW_ACCOUNT = "isNewAccount";
    private static final String STARTUP_STATE = "startupState";
    private static final String VERSION_NAME = "AppVersionName";
    private final AccountService accountService;
    private final BridgeStatusService bridgeStatusService;
    private final Lazy<CommsServiceV2> commsServiceV2;
    private State currentState;
    private final EventBus eventBus;
    private final AuthenticationExceptionHandler exceptionHandler;
    private Lazy<FeatureServiceV2> featureServiceV2Lazy;
    private final HandsFreeSetup handsFreeSetup;
    private final IdentityService identityService;
    private final LatencyInfra latencyInfra;
    private final MetricsService metricsService;
    private final Mobilytics mobilytics;
    PersistentStorage.Factory persistentStorageFactory;
    private final RoutingService routingService;
    @VisibleForTesting
    RouteContext targetRoute;
    private final TestArgumentsService testArguments;
    private MainViewModel viewModel;
    VoiceEnrollmentLauncher voiceEnrollmentLauncher;
    private static final String TAG = Log.tag(StartupStateMachine.class);
    private static Handler handler = new Handler();
    private EventSubscription bridgeStatusSubscription = null;
    private TimerTask bridgeStatusTimer = null;
    private FeatureServiceV2.FeatureUpdateListener oobeDecouplingFeatureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.dee.app.ui.main.StartupStateMachine.1
        @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
        public void onFeatureUpdate(String str) {
            StartupStateMachine.this.shouldShowProfilePicker();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.dee.app.ui.main.StartupStateMachine$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass2 extends TimerTask {
        final /* synthetic */ State val$state;

        AnonymousClass2(State state) {
            this.val$state = state;
        }

        public /* synthetic */ void lambda$run$0$StartupStateMachine$2(State state) {
            String unused = StartupStateMachine.TAG;
            String.format("Transitioning from %S to %S due to timeout waiting for bridge", StartupStateMachine.this.currentState, state);
            StartupStateMachine.this.unregisterListeners();
            StartupStateMachine.this.mobilytics.recordOperationalEvent(StartupStateMachine.this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.RN_BRIDGE_READY_AFTER_TIMEOUT, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.ELEMENTS, StartupStateMachine.TAG));
            StartupStateMachine.this.transitionTo(state);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Handler handler = StartupStateMachine.handler;
            final State state = this.val$state;
            handler.post(new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$2$jEBG97rsNYrq_T8AsWzZusck9Ls
                @Override // java.lang.Runnable
                public final void run() {
                    StartupStateMachine.AnonymousClass2.this.lambda$run$0$StartupStateMachine$2(state);
                }
            });
        }
    }

    /* renamed from: com.amazon.dee.app.ui.main.StartupStateMachine$4  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$ui$main$LoginErrorAction = new int[LoginErrorAction.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State;

        static {
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$LoginErrorAction[LoginErrorAction.EXIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$LoginErrorAction[LoginErrorAction.RETRY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State = new int[State.values().length];
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.Initial.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.NotAuthenticated.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.Authenticated.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.LoggingIn.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.RouteToTarget.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.HomeScreen.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.CommunicationsTab.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.ProfileOobe.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.ElementsFtue.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$StartupStateMachine$State[State.Complete.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum State {
        Initial,
        NotAuthenticated,
        Authenticated,
        LoggingIn,
        ErrorLoggingIn,
        ElementsFtue,
        HomeScreen,
        RouteToTarget,
        CommunicationsTab,
        ProfileOobe,
        Complete
    }

    public StartupStateMachine(MainViewModel mainViewModel, IdentityService identityService, AccountService accountService, RoutingService routingService, Lazy<CommsServiceV2> lazy, Mobilytics mobilytics, AuthenticationExceptionHandler authenticationExceptionHandler, ReactFeatureManager reactFeatureManager, BridgeStatusService bridgeStatusService, MetricsService metricsService, HandsFreeSetup handsFreeSetup, LatencyInfra latencyInfra, EventBus eventBus, PersistentStorage.Factory factory, Lazy<FeatureServiceV2> lazy2, TestArgumentsService testArgumentsService) {
        this.identityService = identityService;
        this.accountService = accountService;
        this.routingService = routingService;
        this.commsServiceV2 = lazy;
        this.mobilytics = mobilytics;
        this.exceptionHandler = authenticationExceptionHandler;
        this.viewModel = mainViewModel;
        this.bridgeStatusService = bridgeStatusService;
        this.metricsService = metricsService;
        this.handsFreeSetup = handsFreeSetup;
        this.latencyInfra = latencyInfra;
        this.eventBus = eventBus;
        this.voiceEnrollmentLauncher = new VoiceEnrollmentLauncher(eventBus, metricsService, routingService, identityService, handsFreeSetup);
        this.persistentStorageFactory = factory;
        this.featureServiceV2Lazy = lazy2;
        this.testArguments = testArgumentsService;
    }

    private void blockCsl() {
        this.latencyInfra.blockNamespace(LatencyNamespace.HOME_COLDSTART);
        this.latencyInfra.blockNamespace(LatencyNamespace.JASPERHOME_COLDSTART);
        this.latencyInfra.blockNamespace(LatencyNamespace.PROFILE_COLDSTART);
    }

    private Observable<UserIdentity> getSignInObservable() {
        if (this.testArguments.isSet(TestArgumentsService.Argument.LOGIN_NAME, TestArgumentsService.Argument.LOGIN_PASSWORD)) {
            return this.accountService.signInForTesting(this.testArguments.getValue(TestArgumentsService.Argument.LOGIN_NAME), this.testArguments.getValue(TestArgumentsService.Argument.LOGIN_PASSWORD));
        }
        return this.accountService.signIn();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$5(LoginErrorAction loginErrorAction) {
    }

    private void navigateToCommunications() {
        Log.i(TAG, "Routing to Native-Comms OOBE.");
        this.routingService.route("conversations").with("arguments", new Bundle()).addToBackStack().navigate();
        this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.COMMS_LAUNCH_EXISTING_USER_OOBE, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.OOBE, TAG));
        transitionTo(State.Complete);
        LatencyService.stateMachineComplete();
        this.voiceEnrollmentLauncher.launch();
    }

    private void navigateToElementsFtue() {
        routeTo("v2/alexa-oobe/ftue-startup");
    }

    private void navigateToProfileOobe() {
        Log.i(TAG, "Routing to RN-Profile OOBE.");
        this.routingService.route("v2/profile-oobe/profile-oobe-start").navigate();
        this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.PROFILE_LAUNCH_EXISTING_USER_OOBE, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.OOBE, TAG));
        transitionTo(State.Complete);
        LatencyService.stateMachineComplete();
    }

    private void navigateToTarget() {
        if (shouldAddRouteOnDeeplink(this.targetRoute)) {
            RoutingService routingService = this.routingService;
            routingService.saveRouteContextInBackStack(routingService.route("v2/comms/conversation-list").create());
        }
        routeTo(this.targetRoute);
        LatencyService.stateMachineComplete();
    }

    private boolean shouldAddRouteOnDeeplink(RouteContext routeContext) {
        if (checkFSV2AccessToFeature("ALEXA_MOBILE_APP_GENERIC_FEATURE_47")) {
            return false;
        }
        String route = routeContext.getRoute().toString();
        return route.startsWith(RouteName.REACT_NATIVE_COMMS) || route.startsWith("comms") || route.startsWith(RouteName.CONVERSATIONS_PATH);
    }

    private boolean shouldShowProfileOobe() {
        return !this.commsServiceV2.mo358get().oobeService().hasSelectedProfile();
    }

    void authenticate() {
        Mobilytics mobilytics = this.mobilytics;
        String str = TAG;
        this.mobilytics.recordOperationalEvent(mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.AUTH_START, OperationalEventType.DIAGNOSTIC, str, str, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        if (!this.viewModel.isMainActivityBackgrounded()) {
            this.eventBus.publish(new Message.Builder().setEventType(Constants.DEVICE_LOGIN_EVENT).build());
            getSignInObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$-Sr_bi8aQRZGREmcenT11YtbzPQ
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StartupStateMachine.this.lambda$authenticate$2$StartupStateMachine((UserIdentity) obj);
                }
            }, new Action1() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$YKOXSButK8FsfK8ZLg3qQLV83fQ
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StartupStateMachine.this.lambda$authenticate$7$StartupStateMachine((Throwable) obj);
                }
            });
        }
    }

    boolean checkFSV2AccessToFeature(String str) {
        return this.featureServiceV2Lazy.mo358get() != null && this.featureServiceV2Lazy.mo358get().hasAccess(str, false);
    }

    void complete() {
        this.featureServiceV2Lazy.mo358get().unsubscribe(this.oobeDecouplingFeatureUpdateListener);
        this.viewModel.nativeStartComplete();
    }

    public State getCurrentState() {
        return this.currentState;
    }

    void handleAuthenticated() {
        LatencyService.activate();
        Log.i(TAG, "App is authenticated.");
        Mobilytics mobilytics = this.mobilytics;
        String str = TAG;
        this.mobilytics.recordOperationalEvent(mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.AUTH_SUCCESS, OperationalEventType.DIAGNOSTIC, str, str, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        this.eventBus.publish(new Message.Builder().setEventType("app:authenticated").build());
        this.routingService.unregisterRouteInterceptor(this);
        if (this.targetRoute == null) {
            this.targetRoute = this.routingService.getCurrentRoute();
        }
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null) {
            Log.w(TAG, "User is NULL!");
            transitionTo(State.NotAuthenticated);
            return;
        }
        PersistentStorage create = this.persistentStorageFactory.create(STARTUP_STATE);
        if (!user.hasAcceptedEula()) {
            Log.w(TAG, "User has not accepted EULA. Transition to Elements FTUE.");
            transitionWhenReady(State.ElementsFtue);
            create.edit().set(IS_NEW_ACCOUNT, true).commit();
            return;
        }
        create.edit().set(IS_NEW_ACCOUNT, false).commit();
        if (this.targetRoute != null) {
            transitionWhenReady(State.RouteToTarget);
        } else if (shouldShowProfileOobe()) {
            setupFSV2CacheUpdateListener("ALEXA_PROFILE_OOBE_DECOUPLING_ANDROID", this.oobeDecouplingFeatureUpdateListener);
            blockCsl();
        } else {
            if (checkFSV2AccessToFeature("ALEXA_PROFILE_PERSONID_BACKFILL_ANDROID")) {
                this.eventBus.publish(new Message.Builder().setEventType(IdentityEvent.IDENTITY_PROFILE_OOBE_COMPLETED).build());
            }
            this.handsFreeSetup.launchHandsFreeSetupIfSupported(new HandsFreeSetup.AfterSetupTransitionCallback() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$8PsV3E4HSfoOsOUjlEJMsji8Tro
                @Override // com.amazon.alexa.voice.handsfree.HandsFreeSetup.AfterSetupTransitionCallback
                public final void onTransitionOutOfSetup() {
                    StartupStateMachine.this.lambda$handleAuthenticated$8$StartupStateMachine();
                }
            });
        }
    }

    void handleInitialState() {
        this.viewModel.nativeStartBeginning();
        setupHandsFreeState();
        this.routingService.registerRouteInterceptor(this);
        if (this.identityService.isAuthenticated(TAG)) {
            PersistentStorage create = this.persistentStorageFactory.create(STARTUP_STATE);
            if (!BuildConfig.VERSION_NAME.equals(create.getString("AppVersionName", "2.2.0.0"))) {
                create.edit().set("AppVersionName", BuildConfig.VERSION_NAME).commit();
                this.eventBus.publish(new Message.Builder().setEventType(GetAssetURLServiceConstants.APP_UPDATED_EVENT).build());
                Mobilytics mobilytics = this.mobilytics;
                String str = TAG;
                this.mobilytics.recordOperationalEvent(mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.APP_UPDATE, OperationalEventType.DIAGNOSTIC, str, str, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
            }
            transitionTo(State.Authenticated);
            return;
        }
        transitionTo(State.NotAuthenticated);
    }

    void handleNotAuthorized() {
        LatencyService.suspend();
        if (this.identityService.isAuthenticated(TAG)) {
            String str = TAG;
            this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.SIGN_OUT_ATTEMPT, OperationalEventType.DIAGNOSTIC, str, str, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
            this.accountService.signOut().subscribe(new Action1() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$tqXyocCCeFU-JguFb9S8rVmQu7I
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StartupStateMachine.this.lambda$handleNotAuthorized$1$StartupStateMachine((Void) obj);
                }
            });
            return;
        }
        transitionTo(State.LoggingIn);
    }

    public /* synthetic */ void lambda$authenticate$2$StartupStateMachine(UserIdentity userIdentity) {
        transitionTo(State.Authenticated);
    }

    public /* synthetic */ void lambda$authenticate$7$StartupStateMachine(Throwable th) {
        if (this.identityService.isAuthenticated(TAG)) {
            transitionTo(State.Authenticated);
            return;
        }
        transitionTo(State.ErrorLoggingIn);
        this.exceptionHandler.handle(th).observeOn(AndroidSchedulers.mainThread()).subscribeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$b9RDTVU9YIAJ6fppe7XhG8epYOw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StartupStateMachine.this.lambda$null$3$StartupStateMachine((LoginErrorAction) obj);
            }
        }).doOnError(new Action1() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$sWh_v_zvYbDAe14HRSe4pkWOTW0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StartupStateMachine.this.lambda$null$4$StartupStateMachine((Throwable) obj);
            }
        }).subscribe($$Lambda$StartupStateMachine$3Vz7wUYcbvh5j4PLfXeOK46D4VM.INSTANCE, $$Lambda$StartupStateMachine$pcwxOlciGUT8DqlJcSc8gdJIk8E.INSTANCE);
    }

    public /* synthetic */ void lambda$handleAuthenticated$8$StartupStateMachine() {
        transitionTo(State.HomeScreen);
    }

    public /* synthetic */ void lambda$handleNotAuthorized$1$StartupStateMachine(Void r1) {
        transitionTo(State.LoggingIn);
    }

    public /* synthetic */ void lambda$null$3$StartupStateMachine(LoginErrorAction loginErrorAction) {
        int ordinal = loginErrorAction.ordinal();
        if (ordinal == 0) {
            this.currentState = State.LoggingIn;
            this.routingService.navigateToExit();
        } else if (ordinal != 1) {
        } else {
            start();
        }
    }

    public /* synthetic */ void lambda$null$4$StartupStateMachine(Throwable th) {
        this.currentState = State.LoggingIn;
        this.routingService.navigateToExit();
    }

    public /* synthetic */ void lambda$transitionWhenReady$0$StartupStateMachine(State state, EventArgs eventArgs) {
        String.format("Bridge ready: Transitioning from %S to %S", this.currentState, state);
        unregisterListeners();
        refreshTargetRoute();
        if (state == State.ProfileOobe) {
            this.latencyInfra.mark(LatencyMarker.PROFILE_OOBE_END_CSL, LatencyNamespace.PROFILE_OOBE_COLDSTART);
        }
        transitionTo(state);
    }

    void navigateToHome() {
        routeTo(RouteName.HOME);
        LatencyService.stateMachineComplete();
    }

    @Override // com.amazon.alexa.routing.api.RoutingService.RouteInterceptor
    public boolean onRouteChanging(RouteContext routeContext) {
        this.targetRoute = routeContext;
        return false;
    }

    void refreshTargetRoute() {
        RouteContext routeContext = this.targetRoute;
        String uri = routeContext == null ? "" : routeContext.toUri();
        RoutingService.RoutingBuilder match = this.routingService.match(uri);
        if (match != null) {
            setTargetRoute(match.create());
        } else {
            String.format("Couldn't find route for %s", uri);
        }
    }

    void routeTo(String str) {
        this.routingService.navigate(str);
        transitionTo(State.Complete);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTargetRoute(RouteContext routeContext) {
        this.targetRoute = routeContext;
    }

    @VisibleForTesting
    void setupFSV2CacheUpdateListener(String str, FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.featureServiceV2Lazy.mo358get().observeFeature(str, featureUpdateListener);
    }

    void setupHandsFreeState() {
        if (this.handsFreeSetup.isHandsFreeDevice()) {
            this.handsFreeSetup.launch(new HandsFreeSetup.HandsFreeSetupCallback() { // from class: com.amazon.dee.app.ui.main.StartupStateMachine.3
                @Override // com.amazon.alexa.voice.handsfree.HandsFreeSetup.HandsFreeSetupCallback
                public void onError() {
                    String unused = StartupStateMachine.TAG;
                }

                @Override // com.amazon.alexa.voice.handsfree.HandsFreeSetup.HandsFreeSetupCallback
                public void onSuccess() {
                    String unused = StartupStateMachine.TAG;
                }
            });
        }
    }

    @VisibleForTesting
    void shouldShowProfilePicker() {
        this.handsFreeSetup.launchHandsFreeSetupIfSupported(new HandsFreeSetup.AfterSetupTransitionCallback() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$s7GizYO2mrO97lrDyWHdBOFxMbA
            @Override // com.amazon.alexa.voice.handsfree.HandsFreeSetup.AfterSetupTransitionCallback
            public final void onTransitionOutOfSetup() {
                StartupStateMachine.this.lambda$shouldShowProfilePicker$9$StartupStateMachine();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: showProfilePicker */
    public void lambda$shouldShowProfilePicker$9$StartupStateMachine() {
        if (checkFSV2AccessToFeature("ALEXA_PROFILE_OOBE_DECOUPLING_ANDROID") && !MetricsHelper.isFireOS()) {
            transitionWhenReady(State.ProfileOobe);
        } else {
            transitionTo(State.CommunicationsTab);
        }
    }

    public void start() {
        transitionTo(State.Initial);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transitionTo(State state) {
        String.format("%S to %S", this.currentState, state);
        this.currentState = state;
        switch (state.ordinal()) {
            case 0:
                handleInitialState();
                return;
            case 1:
                handleNotAuthorized();
                return;
            case 2:
                handleAuthenticated();
                return;
            case 3:
                authenticate();
                return;
            case 4:
            default:
                return;
            case 5:
                navigateToElementsFtue();
                return;
            case 6:
                navigateToHome();
                return;
            case 7:
                navigateToTarget();
                return;
            case 8:
                navigateToCommunications();
                return;
            case 9:
                navigateToProfileOobe();
                return;
            case 10:
                complete();
                return;
        }
    }

    void transitionWhenReady(final State state) {
        RouteContext routeContext;
        if (state == State.RouteToTarget && (routeContext = this.targetRoute) != null && routeContext.getRoute().getAdapterId() != 1) {
            transitionTo(State.RouteToTarget);
            return;
        }
        String.format("Transitioning from %S to %S when bridge ready", this.currentState, state);
        unregisterListeners();
        if (this.bridgeStatusService.getIsReady()) {
            String.format("Bridge already ready: Transitioning.", this.currentState, state);
            this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.RN_BRIDGE_ALREADY_READY, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.ELEMENTS, TAG));
            transitionTo(state);
            return;
        }
        if (state == State.ProfileOobe) {
            this.latencyInfra.mark(LatencyMarker.PROFILE_OOBE_START_CSL, LatencyNamespace.PROFILE_OOBE_COLDSTART);
        }
        LatencyService.startWhenReady();
        this.bridgeStatusTimer = new AnonymousClass2(state);
        this.bridgeStatusSubscription = this.bridgeStatusService.onReadyChange().subscribe(new EventHandler() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$jtPQq_B3eR6vXP--XxEcZONYJCs
            @Override // com.amazon.alexa.eventing.EventHandler
            public final void onEvent(EventArgs eventArgs) {
                StartupStateMachine.this.lambda$transitionWhenReady$0$StartupStateMachine(state, eventArgs);
            }
        });
        new Timer(true).schedule(this.bridgeStatusTimer, 10000L);
    }

    public void unregisterListeners() {
        EventSubscription eventSubscription = this.bridgeStatusSubscription;
        if (eventSubscription != null) {
            eventSubscription.unsubscribe();
            this.bridgeStatusSubscription = null;
        }
        TimerTask timerTask = this.bridgeStatusTimer;
        if (timerTask != null) {
            timerTask.cancel();
            this.bridgeStatusTimer = null;
        }
    }

    void routeTo(Route route) {
        this.routingService.route(route.getName()).navigate();
        transitionTo(State.Complete);
    }

    void routeTo(RouteContext routeContext) {
        this.routingService.navigate(routeContext);
        transitionTo(State.Complete);
    }
}
