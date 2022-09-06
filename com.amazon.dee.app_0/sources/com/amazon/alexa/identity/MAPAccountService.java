package com.amazon.alexa.identity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.AccountUpgradeAuthority;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.Metric;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.SigninOption;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.LatencyNamespace;
import com.amazon.latencyinfra.TimelineInputArgument;
import com.amazon.latencyinfra.TimelineLatencyAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import java.lang.ref.WeakReference;
import javax.inject.Provider;
import rx.AsyncEmitter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes9.dex */
public class MAPAccountService implements AccountService {
    public static final String APP_AUTHENTICATED_EVENT_TYPE = "app:authenticated";
    @VisibleForTesting
    static final String LATENCY_COLD_START_METRIC_FIRST_LOGIN = "firstLogin";
    public static final String METRIC_EXISTING_SIGN_IN_GET_IDENTITY = "EXISTING_SIGN_IN_GET_IDENTITY";
    public static final String METRIC_NEW_SIGN_IN_GET_IDENTITY = "NEW_SIGN_IN_GET_IDENTITY";
    public static final String METRIC_PRE_SIGN_IN_MISSING_ACTIVITY = "PRE_SIGN_IN_MISSING_ACTIVITY";
    public static final String METRIC_SIGN_IN_MISSING_ACTIVITY = "SIGN_IN_MISSING_ACTIVITY";
    static final String TAG = Utils.tag(AccountService.class);
    private final AccountUpgradeAuthority accountUpgradeAuthority;
    private WeakReference<Activity> activity = new WeakReference<>(null);
    private final CommsManager commsManager;
    private final Lazy<CookieManager> cookieManager;
    private final Provider<CrashMetadata> crashMetadata;
    private final String deviceNameTemplate;
    private final Provider<EnvironmentService> environmentService;
    private final Provider<EventBus> eventBus;
    private final Provider<IdentityService> identityService;
    private final Provider<LatencyInfra> latencyInfra;
    private final MAPAccountManager mapAccountManager;
    private final Provider<MetricsService> metricsService;
    private final Provider<RoutingService> routingService;

    public MAPAccountService(ComponentRegistry componentRegistry, MAPAccountManager mAPAccountManager, AccountUpgradeAuthority accountUpgradeAuthority, Lazy<CookieManager> lazy, CommsManager commsManager, String str) {
        this.mapAccountManager = mAPAccountManager;
        this.accountUpgradeAuthority = accountUpgradeAuthority;
        this.cookieManager = lazy;
        this.commsManager = commsManager;
        this.deviceNameTemplate = str;
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.metricsService = componentRegistry.getLazy(MetricsService.class);
        this.crashMetadata = componentRegistry.getLazy(CrashMetadata.class);
        this.identityService = componentRegistry.getLazy(IdentityService.class);
        this.latencyInfra = componentRegistry.getLazy(LatencyInfra.class);
        this.eventBus = componentRegistry.getLazy(EventBus.class);
        this.routingService = componentRegistry.getLazy(RoutingService.class);
        this.eventBus.mo10268get().getSubscriber().subscribeFilter(new EventTypeMessageFilter("app:authenticated"), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$-4fy7S8qGrD4t_kpqDftDIbJ1C8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPAccountService.this.lambda$new$0$MAPAccountService(message);
            }
        });
    }

    private Bundle defaultMAPOptions() {
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.identity.ap.assoc_handle", this.environmentService.mo10268get().getAuthWebAssociationHandle());
        bundle.putString("com.amazon.identity.ap.domain", this.environmentService.mo10268get().getAuthWebHost());
        bundle.putSerializable("progressbar_state", MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.SPINNER_MEDIUM);
        bundle.putString("device_name", this.deviceNameTemplate);
        return bundle;
    }

    @SuppressLint({"SwitchIntDef"})
    public static String getMetricFromException(Throwable th) {
        if (th instanceof AccountException) {
            switch (((AccountException) th).error) {
                case 2:
                    return Metric.Event.NETWORK_FAILURE;
                case 3:
                default:
                    return Metric.Event.UNKNOWN_MAP_FAILURE;
                case 4:
                    return Metric.Event.NETWORK_SSL_ERROR;
                case 5:
                    return Metric.Event.LOGIN_USER_CANCEL;
                case 6:
                    return Metric.Event.ACCOUNT_ALREADY_EXISTS;
                case 7:
                    return Metric.Event.CUSTOMER_NOT_FOUND;
                case 8:
                    return Metric.Event.NO_ACCOUNT;
                case 9:
                    return Metric.Event.AUTHENTICATION_CHALLENGED;
                case 10:
                    return Metric.Event.REQUIRED_3P_AUTHENTICATION;
                case 11:
                    return Metric.Event.BAD_SECRET;
                case 12:
                    return Metric.Event.DEVICE_ALREADY_REGISTERED;
                case 13:
                    return Metric.Event.DUPLICATE_DEVICE_NAME;
                case 14:
                    return Metric.Event.DEREGISTER_FAILED;
                case 15:
                    return Metric.Event.DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED;
                case 16:
                    return Metric.Event.ALREADY_DEREGISTERED;
            }
        }
        return Metric.Event.UNKNOWN_MAP_FAILURE;
    }

    private static boolean isIgnorable(Throwable th) {
        return (th instanceof AccountException) && ((AccountException) th).error == 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void lambda$reAuthenticate$13(String[] strArr) {
        return null;
    }

    private void recordReauthenticationMetric(String str) {
        this.metricsService.mo10268get().recordEvent(String.format("native.%s.count", str), "Reauthentication", Utils.CUSTOM_ENTRIES);
    }

    private void recordSignInCompletedToLatencyInfra() {
        this.latencyInfra.mo10268get().recordTimeline(TimelineLatencyAction.END_TIMELINE, new TimelineInputArgument.Builder().withNamespace(LatencyNamespace.CORE_COLDSTART).withTimelineName(LATENCY_COLD_START_METRIC_FIRST_LOGIN).withEndTimestamp(Long.valueOf(System.currentTimeMillis())).withLogOption(true).build());
    }

    private void recordSignInStartedToLatencyInfra() {
        this.latencyInfra.mo10268get().recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, new TimelineInputArgument.Builder().withNamespace(LatencyNamespace.CORE_COLDSTART).withTimelineName(LATENCY_COLD_START_METRIC_FIRST_LOGIN).withLogOption(true).build());
    }

    @NonNull
    private Observable<UserIdentity> signInInternal(@Nullable final Bundle bundle) {
        if (this.identityService.mo10268get().isRegistered(TAG)) {
            this.metricsService.mo10268get().recordEvent(METRIC_EXISTING_SIGN_IN_GET_IDENTITY, TAG, Utils.CUSTOM_ENTRIES);
            Utils.publishIdentityEvent(TAG, this.eventBus.mo10268get(), IdentityEvent.IDENTITY_SIGN_IN_SUCCESS, this.identityService.mo10268get().getUser(TAG));
            return this.identityService.mo10268get().refresh(TAG).subscribeOn(AndroidSchedulers.mainThread());
        } else if (this.activity.get() == null) {
            Log.e(TAG, "IdentityV2 : Attempted signin but failed due to not having a reference to an activity");
            Utils.publishIdentityEvent(TAG, this.eventBus.mo10268get(), IdentityEvent.IDENTITY_SIGN_IN_FAILURE, (UserIdentity) null);
            this.metricsService.mo10268get().recordEvent(METRIC_PRE_SIGN_IN_MISSING_ACTIVITY, TAG, Utils.CUSTOM_ENTRIES);
            return Observable.error(new AccountServiceInitializationException("Missing activity"));
        } else {
            this.metricsService.mo10268get().recordEvent(Metric.Event.SIGN_IN_ATTEMPT, "Reauthentication", Utils.CUSTOM_ENTRIES);
            Log.i(TAG, "IdentityV2 : signIn.register");
            return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$geHi3llMpuNWC7OzEv4_VEvKz_0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MAPAccountService.this.lambda$signInInternal$4$MAPAccountService(bundle, (Subscriber) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$-ROHF0R7hm7MT699s0IcysyVjTM
                @Override // rx.functions.Action0
                public final void call() {
                    MAPAccountService.this.lambda$signInInternal$5$MAPAccountService();
                }
            }).doOnTerminate(new Action0() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$6O2WkHZrMfIzAT-2A8wpGBfDg4E
                @Override // rx.functions.Action0
                public final void call() {
                    MAPAccountService.this.lambda$signInInternal$6$MAPAccountService();
                }
            }).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$KsRfeP3-RNPqBPVEhFwjlS6odq4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MAPAccountService.this.lambda$signInInternal$7$MAPAccountService((String[]) obj);
                }
            }).doOnError(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$KZ4YHaWu8Zxq8I4Qf47fjyYib0U
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MAPAccountService.this.lambda$signInInternal$8$MAPAccountService((Throwable) obj);
                }
            }).switchMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$LaE-gvGBuEvC-89qnLZHfa9C1xQ
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return MAPAccountService.this.lambda$signInInternal$9$MAPAccountService((String[]) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$0$MAPAccountService(Message message) {
        onAccountAuthenticated();
    }

    public /* synthetic */ void lambda$onAccountAuthenticated$1$MAPAccountService() {
        this.routingService.mo10268get().route(RouteName.HOME).clearBackStack();
        this.routingService.mo10268get().clearCurrentRoute();
    }

    public /* synthetic */ void lambda$onAccountAuthenticated$2$MAPAccountService(Void r5) {
        this.metricsService.mo10268get().recordOccurrence(Metric.Event.PROFILE_UNAUTHENTICATED_LOGOUT_SUCCESS, TAG, true, Utils.CUSTOM_ENTRIES);
    }

    public /* synthetic */ void lambda$onAccountAuthenticated$3$MAPAccountService(Throwable th) {
        this.metricsService.mo10268get().recordOccurrence(Metric.Event.PROFILE_UNAUTHENTICATED_LOGOUT_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
        Log.e(TAG, "IdentityV2 : Error occurred when logging out unauthenticated profile.", th);
    }

    public /* synthetic */ void lambda$reAuthenticate$10$MAPAccountService(Subscriber subscriber) {
        Bundle defaultMAPOptions = defaultMAPOptions();
        defaultMAPOptions.putString("com.amazon.dcp.sso.property.account.acctId", this.identityService.mo10268get().getDirectedAccountId(TAG));
        MAPCookiesCallback mAPCookiesCallback = new MAPCookiesCallback(subscriber, this.crashMetadata.mo10268get());
        mAPCookiesCallback.includeAccessTokenCookieWithDomain(this.environmentService.mo10268get().getCoralHost());
        if (this.activity.get() != null) {
            this.mapAccountManager.authenticateAccountWithUI(this.activity.get(), SigninOption.WebviewConfirmCredentials, defaultMAPOptions, mAPCookiesCallback);
            return;
        }
        Log.e(TAG, "IdentityV2 : reAuthenticate attempt failed due to not having a reference to an activity");
        subscriber.onError(new AccountServiceInitializationException("Missing activity"));
    }

    public /* synthetic */ void lambda$reAuthenticate$11$MAPAccountService(String[] strArr) {
        Log.i(TAG, "IdentityV2 : ReAuthenticate success");
        if (strArr != null) {
            for (String str : strArr) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("https://");
                outline107.append(this.environmentService.mo10268get().getCoralHost());
                this.cookieManager.mo358get().setCookie(outline107.toString(), str);
            }
        }
        this.identityService.mo10268get().refresh(TAG);
        recordReauthenticationMetric(Metric.Event.REAUTHENTICATION_SUCCESS);
    }

    public /* synthetic */ void lambda$reAuthenticate$12$MAPAccountService(Throwable th) {
        if (isIgnorable(th)) {
            Log.w(TAG, "IdentityV2 : ReAuthenticate cancelled");
            recordReauthenticationMetric(Metric.Event.REAUTHENTICATION_CANCELLED);
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(Utils.safeFormat("ReAuthenticate error: %s", th.getMessage()));
        Log.w(str, outline107.toString());
        recordReauthenticationMetric(Metric.Event.REAUTHENTICATION_ERROR);
        this.metricsService.mo10268get().recordEvent(getMetricFromException(th), "Reauthentication", Utils.CUSTOM_ENTRIES);
    }

    public /* synthetic */ void lambda$signInInternal$4$MAPAccountService(Bundle bundle, Subscriber subscriber) {
        Bundle defaultMAPOptions = defaultMAPOptions();
        Activity activity = this.activity.get();
        if (activity == null) {
            this.metricsService.mo10268get().recordEvent(METRIC_SIGN_IN_MISSING_ACTIVITY, TAG, Utils.CUSTOM_ENTRIES);
            Log.e(TAG, "IdentityV2 : Sign in attempt failed due to not having a reference to an activity");
            subscriber.onError(new AccountServiceInitializationException("Missing activity"));
        } else if (bundle == null) {
            this.mapAccountManager.registerAccountWithUI(activity, SigninOption.WebviewSignin, defaultMAPOptions, new MAPCookiesCallback(subscriber, this.crashMetadata.mo10268get()));
        } else {
            this.mapAccountManager.registerAccount(activity, bundle, new Bundle(), new MAPCookiesCallback(subscriber, this.crashMetadata.mo10268get()));
        }
    }

    public /* synthetic */ void lambda$signInInternal$5$MAPAccountService() {
        this.metricsService.mo10268get().startTimer(Metric.Event.SIGN_IN_DURATION, MAPAccountService.class.getSimpleName(), Utils.CUSTOM_ENTRIES);
        recordSignInStartedToLatencyInfra();
    }

    public /* synthetic */ void lambda$signInInternal$6$MAPAccountService() {
        this.metricsService.mo10268get().recordTimer(Metric.Event.SIGN_IN_DURATION, Utils.CUSTOM_ENTRIES);
        this.metricsService.mo10268get().recordEvent(METRIC_NEW_SIGN_IN_GET_IDENTITY, TAG, Utils.CUSTOM_ENTRIES);
        recordSignInCompletedToLatencyInfra();
    }

    public /* synthetic */ void lambda$signInInternal$7$MAPAccountService(String[] strArr) {
        Log.i(TAG, "IdentityV2 : Sign in success");
        this.metricsService.mo10268get().recordOccurrence(Metric.Event.SIGN_IN_SUCCESS, TAG, true, Utils.CUSTOM_ENTRIES);
        this.accountUpgradeAuthority.notifyAccountUpgraded();
        Utils.publishIdentityEvent(TAG, this.eventBus.mo10268get(), IdentityEvent.IDENTITY_SIGN_IN_SUCCESS, this.identityService.mo10268get().getUser(TAG));
    }

    public /* synthetic */ void lambda$signInInternal$8$MAPAccountService(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IdentityV2 : Sign in error: ");
        outline107.append(th.getMessage());
        Log.i(str, outline107.toString());
        String metricFromException = getMetricFromException(th);
        GeneratedOutlineSupport1.outline158("IdentityV2 : Sign in error metric ", metricFromException);
        this.metricsService.mo10268get().recordEvent(metricFromException, TAG, Utils.CUSTOM_ENTRIES);
        if (!isIgnorable(th)) {
            this.metricsService.mo10268get().recordOccurrence(Metric.Event.SIGN_IN_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
        }
        Utils.publishIdentityEvent(TAG, this.eventBus.mo10268get(), IdentityEvent.IDENTITY_SIGN_IN_FAILURE, (UserIdentity) null);
    }

    public /* synthetic */ Observable lambda$signInInternal$9$MAPAccountService(String[] strArr) {
        Log.i(TAG, "IdentityV2 : signInInternal at last step. issuing identityService.refresh");
        return this.identityService.mo10268get().refresh(TAG + ".signInInternal");
    }

    public /* synthetic */ void lambda$signOut$14$MAPAccountService(final AsyncEmitter asyncEmitter) {
        MAPAccountManager mAPAccountManager = this.mapAccountManager;
        mAPAccountManager.deregisterAccount(mAPAccountManager.getAccount(), new Callback() { // from class: com.amazon.alexa.identity.MAPAccountService.1
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                ((MetricsService) MAPAccountService.this.metricsService.mo10268get()).recordOccurrence(Metric.Event.LOGOUT_SUCCESS, MAPAccountService.TAG, false, Utils.CUSTOM_ENTRIES);
                AsyncEmitter asyncEmitter2 = asyncEmitter;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to log out:  ");
                outline107.append(bundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                outline107.append(" - ");
                outline107.append(bundle.getString("com.amazon.dcp.sso.ErrorCode"));
                asyncEmitter2.onError(new Exception(outline107.toString()));
                Utils.publishIdentityEvent(MAPAccountService.TAG, (EventBus) MAPAccountService.this.eventBus.mo10268get(), IdentityEvent.IDENTITY_SIGN_OUT_FAILURE, (UserIdentity) null);
                ((MetricsService) MAPAccountService.this.metricsService.mo10268get()).recordEvent(Metric.Event.LOGOUT_FAILED_ERROR, MAPAccountService.TAG, Utils.CUSTOM_ENTRIES);
                MAPAccountService.this.commsManager.registerForPush();
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                String str = MAPAccountService.TAG;
                ((MetricsService) MAPAccountService.this.metricsService.mo10268get()).recordOccurrence(Metric.Event.LOGOUT_SUCCESS, MAPAccountService.TAG, true, Utils.CUSTOM_ENTRIES);
                asyncEmitter.onNext(null);
                asyncEmitter.onCompleted();
            }
        });
    }

    public /* synthetic */ void lambda$signOut$15$MAPAccountService(Void r4) {
        if (this.identityService.mo10268get() instanceof MAPIdentityService) {
            MAPIdentityService mAPIdentityService = (MAPIdentityService) this.identityService.mo10268get();
            mAPIdentityService.removeCookies();
            mAPIdentityService.updateUserIdentity(null);
            Utils.publishIdentityEvent(TAG, this.eventBus.mo10268get(), IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS, (UserIdentity) null);
        }
        this.environmentService.mo10268get().resetValues();
    }

    @VisibleForTesting
    void onAccountAuthenticated() {
        if (this.identityService.mo10268get().isProfileAuthenticated(TAG)) {
            return;
        }
        signOut().subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).doOnTerminate(new Action0() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$qiTtonXvlFT1kZ6Bpwmvcp_nS4w
            @Override // rx.functions.Action0
            public final void call() {
                MAPAccountService.this.lambda$onAccountAuthenticated$1$MAPAccountService();
            }
        }).subscribe(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$wVyfcx1lx2xYxWoBKVSqTV4Pk48
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountService.this.lambda$onAccountAuthenticated$2$MAPAccountService((Void) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$MAk9dpLtiLvglmz38Z8fWu097kg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountService.this.lambda$onAccountAuthenticated$3$MAPAccountService((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.identity.api.AccountService
    @NonNull
    public Observable<Void> reAuthenticate() {
        recordReauthenticationMetric(Metric.Event.REAUTHENTICATION_ATTEMPT);
        Log.i(TAG, "IdentityV2 : ReAuthentication starting");
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$Vckke4fYOGvJ_T2Wprqqg4QnTx4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountService.this.lambda$reAuthenticate$10$MAPAccountService((Subscriber) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$nUVe1syG4IE2t7KwmdRbtHYoOzY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountService.this.lambda$reAuthenticate$11$MAPAccountService((String[]) obj);
            }
        }).doOnError(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$ICw60KxzTi8eyC7wdajhSo1gBjc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountService.this.lambda$reAuthenticate$12$MAPAccountService((Throwable) obj);
            }
        }).map($$Lambda$MAPAccountService$uBig2SFFb8huv_JpzZiYbrW6rI.INSTANCE);
    }

    @Override // com.amazon.alexa.identity.api.AccountService
    public void setActivity(Activity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override // com.amazon.alexa.identity.api.AccountService
    @NonNull
    public Observable<UserIdentity> signIn() {
        return signInInternal(null);
    }

    @Override // com.amazon.alexa.identity.api.AccountService
    @NonNull
    public Observable<UserIdentity> signInForTesting(String str, String str2) {
        Bundle outline12 = GeneratedOutlineSupport1.outline12(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME, str, MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, str2);
        outline12.putString("device_name", this.deviceNameTemplate);
        return signInInternal(outline12);
    }

    @Override // com.amazon.alexa.identity.api.AccountService
    @NonNull
    public Observable<Void> signOut() {
        this.crashMetadata.mo10268get().put("sign_out_called", true);
        this.commsManager.deregisterForPush(true);
        return Observable.fromAsync(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$kcJ0XVj70Yb65Y_l2mL00-v82Ps
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountService.this.lambda$signOut$14$MAPAccountService((AsyncEmitter) obj);
            }
        }, AsyncEmitter.BackpressureMode.LATEST).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPAccountService$-ww7zR2-ifzBBYqA7qRg6Dy_hgA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPAccountService.this.lambda$signOut$15$MAPAccountService((Void) obj);
            }
        });
    }
}
