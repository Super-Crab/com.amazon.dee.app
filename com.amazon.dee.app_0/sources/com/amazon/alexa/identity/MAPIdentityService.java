package com.amazon.alexa.identity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceConstants;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.api.AuthenticationProvider;
import com.amazon.alexa.identity.api.IdentityConstant;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.Metric;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserIdentityRepository;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.identity.api.UserProfileManager;
import com.amazon.alexa.marketplace.api.MarketplaceEndpoints;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.protocols.datastore.DataStoreService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
/* loaded from: classes9.dex */
public class MAPIdentityService implements IdentityService, AuthenticationProvider {
    private static final String ALEXA_CORE_DATA_REGION_ENDPOINTS = "ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID";
    private static final String ALEXA_PREFIX = "alexa";
    private static final String CALLING_CLASS = "CallingClass";
    private static final String HTTPS_PREFIX = "https://";
    @VisibleForTesting
    static final String IDENTITY_REFRESH_KEY_V2 = "LAST_REFRESHED_MILLIS_V2";
    private static final String IDENTITY_V2_METRIC_SUFFIX = "_V2";
    private static final int MAP_ERROR_NETWORK_ERROR = 401;
    public static final String REFRESH_COOKIES_DATA_REGION_FAILURE = "REFRESH_COOKIES_DATA_REGION_FAILURE";
    public static final String REFRESH_COOKIES_DATA_REGION_SUCCESS_RATE = "REFRESH_COOKIES_DATA_REGION_SUCCESS_RATE";
    public static final String REFRESH_COOKIES_ENDPOINT_SERVICE_MISMATCH = "REFRESH_COOKIES_ENDPOINT_SERVICE_MISMATCH";
    public static final String REFRESH_COOKIES_FAILURE = "REFRESH_COOKIES_FAILURE";
    public static final String REFRESH_COOKIES_NETWORK_FAILURE = "REFRESH_INTERNAL_NETWORK_FAILURE";
    public static final String REFRESH_COOKIES_UNACCOUNTED_FAILURE = "REFRESH_COOKIES_UNACCOUNTED_FAILURE";
    private static final String UNKNOWN_CALLER = "unknown";
    public static final int USER_IDENTITY_REFRESH_HOURS = 1;
    private static final String VERSION_NAME = "AppVersionName";
    private final Lazy<MAPAccountRegistrationService> accountRegistrationService;
    private final ApesCallerInterface apesCaller;
    private final Context context;
    private final Lazy<CookieManager> cookieManager;
    private final Provider<CrashMetadata> crashMetadata;
    private final Provider<CrashReporter> crashReporter;
    private final DataStoreService dataStoreService;
    private final DelegatedTokenManagement delegatedTokenManagement;
    private final EnvironmentService environmentService;
    private final EventBus eventBus;
    private final Provider<FeatureQuery> featureQuery;
    private boolean forceTokenRefreshOnStart;
    private boolean isDebugBuild;
    private final MAPAccountManager mapAccountManager;
    private final Provider<MetricsService> metricsService;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final Provider<NetworkService> networkService;
    @VisibleForTesting
    Scheduler onUserChangedScheduler;
    private final PersistentStorage persistentStorage;
    private final UserProfileManager profileManager;
    @VisibleForTesting
    final AtomicBoolean refreshInProgress;
    private final TokenManagement tokenManagement;
    private volatile UserIdentity userIdentity;
    private final UserIdentityRepository userRepository;
    @VisibleForTesting
    final UserUpdateTimestamp userUpdateTimestamp;
    static final String TAG = Utils.tag(IdentityService.class);
    private static final Random random = new Random();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class DomainCookie {
        private final String[] cookies;
        private final String domain;

        DomainCookie(String str, String[] strArr) {
            this.domain = str;
            this.cookies = strArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes9.dex */
    public class UserUpdateTimestamp {
        private DateTime userLastRefreshed = null;

        UserUpdateTimestamp() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isExpired() {
            DateTime now = DateTime.now(DateTimeZone.UTC);
            if (this.userLastRefreshed == null) {
                this.userLastRefreshed = new DateTime(MAPIdentityService.this.persistentStorage.getLong(MAPIdentityService.IDENTITY_REFRESH_KEY_V2, 0L));
            }
            return now.isAfter(this.userLastRefreshed.plusHours(1));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void update() {
            this.userLastRefreshed = DateTime.now(DateTimeZone.UTC);
            MAPIdentityService.this.persistentStorage.edit().set(MAPIdentityService.IDENTITY_REFRESH_KEY_V2, this.userLastRefreshed.getMillis()).commit();
        }

        @VisibleForTesting
        void clear() {
            this.userLastRefreshed = new DateTime(0L);
            MAPIdentityService.this.persistentStorage.edit().set(MAPIdentityService.IDENTITY_REFRESH_KEY_V2, this.userLastRefreshed.getMillis()).commit();
        }
    }

    public MAPIdentityService(ComponentRegistry componentRegistry, MAPAccountManager mAPAccountManager, Lazy<CookieManager> lazy, TokenManagement tokenManagement, DataStoreService dataStoreService, UserIdentityRepository userIdentityRepository, UserProfileManager userProfileManager, Lazy<MAPAccountRegistrationService> lazy2, DelegatedTokenManagement delegatedTokenManagement, Context context, ApesCallerInterface apesCallerInterface) {
        this.onUserChangedScheduler = AndroidSchedulers.mainThread();
        this.forceTokenRefreshOnStart = false;
        this.isDebugBuild = false;
        this.refreshInProgress = new AtomicBoolean();
        this.userUpdateTimestamp = new UserUpdateTimestamp();
        this.cookieManager = lazy;
        this.mapAccountManager = mAPAccountManager;
        this.tokenManagement = tokenManagement;
        this.userRepository = userIdentityRepository;
        this.profileManager = userProfileManager;
        this.delegatedTokenManagement = delegatedTokenManagement;
        this.context = context;
        this.dataStoreService = dataStoreService;
        this.environmentService = (EnvironmentService) componentRegistry.getLazy(EnvironmentService.class).mo10268get();
        this.eventBus = (EventBus) componentRegistry.getLazy(EventBus.class).mo10268get();
        this.persistentStorage = ((PersistentStorage.Factory) componentRegistry.getLazy(PersistentStorage.Factory.class).mo10268get()).create(IdentityConstant.IDENTITY_STORAGE_NAME);
        this.networkService = componentRegistry.getLazy(NetworkService.class);
        this.crashMetadata = componentRegistry.getLazy(CrashMetadata.class);
        this.metricsService = componentRegistry.getLazy(MetricsService.class);
        this.featureQuery = componentRegistry.getLazy(FeatureQuery.class);
        this.crashReporter = componentRegistry.getLazy(CrashReporter.class);
        this.accountRegistrationService = lazy2;
        this.userIdentity = userIdentityRepository.getCachedIdentity();
        this.apesCaller = apesCallerInterface;
        this.mobilyticsProvider = componentRegistry.getLazy(Mobilytics.class);
        MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_TOU_ACCEPTED), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$N-uh5vDk7ohwT1dvUQxzhCC5Nb4
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPIdentityService.this.lambda$new$0$MAPIdentityService(message);
            }
        });
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_COR_PFM_UPDATED), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$lpltxA6lVb2ahf0Nnfz1VQaAX3s
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPIdentityService.this.lambda$new$1$MAPIdentityService(message);
            }
        });
        subscriber.subscribeFilter(new EventTypeMessageFilter(GetAssetURLServiceConstants.APP_UPDATED_EVENT), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$CGhdLlnCNZgwpWR8RzPuzXuI0ms
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPIdentityService.this.lambda$new$2$MAPIdentityService(message);
            }
        });
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_SIGN_IN_SUCCESS), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$hUb657LchWA1ry2jHpFJNW0Ya4w
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPIdentityService.this.lambda$new$3$MAPIdentityService(message);
            }
        });
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_OOBE_PROFILE_SELECTED), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$fqCCnzhMnkdTCE8ekJiaXwrNAME
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPIdentityService.this.lambda$new$4$MAPIdentityService(message);
            }
        });
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_PROFILE_SELECTION_ATTEMPT), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$stXRoKLxl6L80oyTn_W9fqWaQx0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPIdentityService.this.lambda$new$5$MAPIdentityService(message);
            }
        });
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_LINK_CODE_REQUEST), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$Ol3_AtKseXHPr6hn6GtMBE26_Uw
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPIdentityService.this.lambda$new$6$MAPIdentityService(message);
            }
        });
        subscriber.subscribeFilter(new EventTypeMessageFilter("ENDPOINTS_CHANGED"), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$wNiGjhHFs1u7hh2Fd3F0Gpr2N4g
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPIdentityService.this.lambda$new$7$MAPIdentityService(message);
            }
        });
        if (apesCallerInterface != null) {
            subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_PROFILE_OOBE_COMPLETED), new MessageHandler() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$otFTX5nALUs28CSt6rHXdApuDHg
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    MAPIdentityService.this.lambda$new$8$MAPIdentityService(message);
                }
            });
        }
        this.isDebugBuild = this.environmentService.isDebugBuild();
    }

    private void clearDelegationToken(String str) {
        try {
            this.delegatedTokenManagement.terminateDelegation(getDelegatedToken(str));
        } catch (Exception unused) {
            Log.e(TAG, "IdentityV2 : Exception occurred while terminating delegation. Ignoring the exception and moving forward.");
        }
    }

    private String getPersonId(UserProfile userProfile, String str, String str2) {
        if (userProfile == null) {
            Log.e(TAG, "IdentityV2 : Failed to getPersonId. Provided userProfile is null");
            this.metricsService.mo10268get().recordEvent(metricName(str), TAG, Utils.CUSTOM_ENTRIES);
            return "";
        }
        String personId = userProfile.getPersonId();
        if (personId != null) {
            return personId;
        }
        Log.e(TAG, "IdentityV2 : Failed to getPersonId. Null personId in provided userProfile");
        this.metricsService.mo10268get().recordEvent(metricName(str2), TAG, Utils.CUSTOM_ENTRIES);
        return "";
    }

    private String getValueFromCookie(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        for (String str3 : str.split("; ")) {
            String[] split = str3.split(Config.Compare.EQUAL_TO, 2);
            if (split[0].equals(str2)) {
                return split[1].trim();
            }
        }
        return "";
    }

    private boolean isPersonIdAuthenticated(String str) {
        boolean z = false;
        try {
            try {
                try {
                    this.metricsService.mo10268get().startTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, TAG, Utils.CUSTOM_ENTRIES);
                    this.tokenManagement.getTokenForActor(this.context, this.mapAccountManager.getAccount(), str, getActorTokenKey(), null, null, null).get();
                    this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, true, Utils.CUSTOM_ENTRIES);
                    this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, true, Utils.CUSTOM_ENTRIES);
                } catch (MAPCallbackErrorException e) {
                    Log.e(TAG, "IdentityV2 : MAPCallbackErrorException during retrieveActorAccessTokenFromMap from MAP", e);
                    Bundle errorBundle = e.getErrorBundle();
                    int i = errorBundle != null ? errorBundle.getInt(MAPError.KEY_ERROR_CODE) : 0;
                    int i2 = errorBundle != null ? errorBundle.getInt("com.amazon.dcp.sso.ErrorCode") : 0;
                    if (i != MAPAccountManager.RegistrationError.ACTOR_NOT_ASSOCIATED.value()) {
                        if (i2 == MAPAccountManager.RegistrationError.ACTOR_NOT_ASSOCIATED.value()) {
                        }
                        this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
                        this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                        return true;
                    }
                    String str2 = "IdentityV2 : Current profile: " + str + " is not associated with the logged in Account.";
                    this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, true, Utils.CUSTOM_ENTRIES);
                    this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
                    this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                    return false;
                }
            } catch (Exception e2) {
                Log.e(TAG, "IdentityV2 : Exception during retrieveActorAccessTokenFromMap from MAP", e2);
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
                this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                return true;
            } catch (Throwable th) {
                th = th;
                z = true;
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, z, Utils.CUSTOM_ENTRIES);
                this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                throw th;
            }
            this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
            return true;
        } catch (Throwable th2) {
            th = th2;
            this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, z, Utils.CUSTOM_ENTRIES);
            this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$13(String str, Throwable th) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(String.format("serverFetch failed for %s", str));
        Log.e(str2, outline107.toString(), th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DomainCookie lambda$null$21(String str, String[] strArr) {
        return new DomainCookie(str, strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ HashSet lambda$refreshCookies$25() {
        return new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$refreshCookies$26(HashSet hashSet, String[] strArr) {
        Log.i(TAG, "IdentityV2 : refreshCookies (dataRegion) collecting cookies");
        Collections.addAll(hashSet, strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$refreshCookies$27(HashSet hashSet) {
        return (String[]) hashSet.toArray(new String[0]);
    }

    private void logMAPException(Throwable th) {
        String metricName = metricName(MAPAccountService.getMetricFromException(th));
        String str = TAG;
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115(Utils.V2_LOG_PREFIX, metricName, RealTimeTextConstants.COLON_SPACE);
        outline115.append(th.getMessage());
        Log.e(str, outline115.toString());
        recordMobilyticsCriticalEvent(metricName, metricName, Metric.Component.IDENTITY_SERVICE, th);
        this.metricsService.mo10268get().recordError(metricName, metricName, Metric.Component.IDENTITY_SERVICE, Utils.CUSTOM_ENTRIES);
    }

    private void logRefreshInternalError(Throwable th) {
        if (th instanceof AccountException) {
            logMAPException(th);
        } else if (th instanceof UnknownHostException) {
            recordMobilyticsCriticalEvent(th.getMessage(), metricName(REFRESH_COOKIES_NETWORK_FAILURE), "refreshInternal", th);
        } else {
            recordMobilyticsCriticalEvent(th.getMessage(), metricName(REFRESH_COOKIES_UNACCOUNTED_FAILURE), "refreshInternal", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logUserError(Throwable th, String str) {
        String str2 = TAG;
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115(Utils.V2_LOG_PREFIX, str, RealTimeTextConstants.COLON_SPACE);
        outline115.append(th.getMessage());
        Log.e(str2, outline115.toString());
        this.metricsService.mo10268get().recordError(str, th.getMessage(), Metric.Component.IDENTITY_SERVICE, Utils.CUSTOM_ENTRIES);
    }

    private void logWhereCalledFrom() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String metricName(String str) {
        return GeneratedOutlineSupport1.outline72(str, IDENTITY_V2_METRIC_SUFFIX);
    }

    private void notifyUserChanged(UserIdentity userIdentity) {
        if (Objects.equals(userIdentity, this.userIdentity)) {
            return;
        }
        logFeatureChanges(this.userIdentity, userIdentity);
        this.userIdentity = userIdentity;
        if (this.userIdentity != null) {
            persistUser(TAG);
        }
        Utils.publishIdentityEvent(TAG, this.eventBus, IdentityEvent.IDENTITY_CHANGED, userIdentity);
    }

    private void onOobeProfileSelected() {
        Log.i(TAG, "got IDENTITY_OOBE_PROFILE_SELECTED.");
        if (this.userIdentity != null) {
            notifyUserChanged(UserIdentity.from(this.userIdentity).withUserProfile(this.profileManager.getCurrentProfile()).build());
            this.metricsService.mo10268get().recordOccurrence(Metric.Event.COMMS_OOBE_PROFILE_SELECTION_SUCCESS, TAG, true, Utils.CUSTOM_ENTRIES);
            return;
        }
        this.metricsService.mo10268get().recordOccurrence(Metric.Event.COMMS_OOBE_PROFILE_SELECTION_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
    }

    private void onProfileOobeCompleted() {
        if (this.userIdentity == null || this.userIdentity.getUserProfile() == null || TextUtils.isEmpty(this.userIdentity.getUserProfile().getDirectedId()) || !TextUtils.isEmpty(this.userIdentity.getUserProfile().getPersonId())) {
            return;
        }
        updatePersonIdV2();
    }

    private void onTermsOfUseAccepted() {
        if (this.userIdentity == null) {
            Log.w(TAG, "IdentityV2 : Terms of use accepted, but user was null.");
            return;
        }
        Log.i(TAG, "IdentityV2 : Terms of use accepted, proceeding to update user.");
        notifyUserChanged(UserIdentity.from(this.userIdentity).hasAcceptedEula(true).build());
    }

    private synchronized void persistUser(String str) {
        String str2 = Utils.V2_LOG_PREFIX + String.format("%s: persistUser", str);
        this.userRepository.save(this.userIdentity);
    }

    private void queueUserRefreshOnEvent(String str) {
        GeneratedOutlineSupport1.outline163("forced refresh for ", str, TAG);
        this.userUpdateTimestamp.clear();
        getUser(str);
    }

    private void recordMobilyticsCriticalEvent(String str, String str2, String str3) {
        recordMobilyticsCriticalEvent(str, str2, str3, null);
    }

    private void reportNonFatalWithSampling(Throwable th, double d) {
        if (random.nextDouble() < d) {
            this.crashReporter.mo10268get().reportNonFatal(th);
        }
    }

    private String retrieveActorTokenFromMap(String str) {
        this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_ACTOR_TOKEN_START), TAG, Utils.CUSTOM_ENTRIES);
        String str2 = "";
        if (str2.equals(str)) {
            return str2;
        }
        boolean z = false;
        try {
            try {
                try {
                    try {
                        this.metricsService.mo10268get().startTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, TAG, Utils.CUSTOM_ENTRIES);
                        str2 = this.tokenManagement.getTokenForActor(this.context, this.mapAccountManager.getAccount(), str, getActorTokenKey(), null, null, null).get().getString("value_key", str2);
                        this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_ACTOR_TOKEN_COMPLETE), TAG, Utils.CUSTOM_ENTRIES);
                        this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                        this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, true, Utils.CUSTOM_ENTRIES);
                    } catch (MAPCallbackErrorException e) {
                        Log.e(TAG, "IdentityV2 : MAPCallbackErrorException during retrieveActorAccessTokenFromMap from MAP", e);
                        Bundle errorBundle = e.getErrorBundle();
                        int i = errorBundle != null ? errorBundle.getInt(MAPError.KEY_ERROR_CODE) : 0;
                        if (i == 401) {
                            this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_ACTOR_TOKEN_CONNECTIVITY_FAIL), TAG, Utils.CUSTOM_ENTRIES);
                        } else {
                            HashMap hashMap = new HashMap();
                            hashMap.put("statusCode", String.valueOf(i));
                            hashMap.put("ownerIdentifier", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
                            this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_ACTOR_TOKEN_MAP_GET_FAIL), TAG, hashMap);
                            reportNonFatalWithSampling(e, 5.0E-6d);
                        }
                        this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                        this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
                        return str2;
                    }
                } catch (InterruptedException e2) {
                    Log.e(TAG, "IdentityV2 : InterruptedException during retrieveActorAccessTokenFromMap from MAP", e2);
                    this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_ACTOR_TOKEN_INTERRUPT_GET_FAIL), TAG, Utils.CUSTOM_ENTRIES);
                    this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                    this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
                    return str2;
                } catch (Exception e3) {
                    Log.e(TAG, "IdentityV2 : Exception during retrieveActorAccessTokenFromMap from MAP", e3);
                    this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_ACTOR_TOKEN_GET_FAIL), TAG, Utils.CUSTOM_ENTRIES);
                    this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                    this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
                    return str2;
                }
            } catch (ExecutionException e4) {
                Log.e(TAG, "IdentityV2 : ExecutionException during retrieveActorAccessTokenFromMap from MAP", e4);
                this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_ACTOR_TOKEN_EXECUTE_GET_FAIL), TAG, Utils.CUSTOM_ENTRIES);
                this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
                return str2;
            } catch (Throwable th) {
                th = th;
                z = true;
                this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, z, Utils.CUSTOM_ENTRIES);
                throw th;
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            this.metricsService.mo10268get().recordTimer(Metric.Event.GET_ACTOR_TOKEN_DURATION, Utils.CUSTOM_ENTRIES);
            this.metricsService.mo10268get().recordOccurrence(Metric.Event.GET_ACTOR_TOKEN_SUCCESS, TAG, z, Utils.CUSTOM_ENTRIES);
            throw th;
        }
    }

    private String retrieveDelegatedToken(String str, String str2) {
        this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_DELEGATED_TOKEN_START), TAG, Utils.CUSTOM_ENTRIES);
        String str3 = "";
        if (str3.equals(str2)) {
            Log.e(TAG, "IdentityV2 : Exception during retrieveDelegatedToken. Empty PersonId for delegatedProfile. No Remote Management Session is active.");
            this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_DELEGATED_TOKEN_FAIL_NO_RM_SESSION), TAG, Utils.CUSTOM_ENTRIES);
            return str3;
        }
        String accessTokenForPerson = getAccessTokenForPerson(str);
        if (str3.equals(accessTokenForPerson)) {
            Log.e(TAG, "IdentityV2 : Exception during retrieveDelegatedToken. No actor token returned from MAP.");
            this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_DELEGATED_TOKEN_FAIL_NO_RM_ACTOR_TOKEN), TAG, Utils.CUSTOM_ENTRIES);
            return str3;
        }
        try {
            str3 = this.delegatedTokenManagement.getDelegatedToken(str2, accessTokenForPerson, this.forceTokenRefreshOnStart);
            this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_DELEGATED_TOKEN_COMPLETE), TAG, Utils.CUSTOM_ENTRIES);
            return str3;
        } catch (UnsupportedOperationException e) {
            Log.e(TAG, "IdentityV2 : UnsupportedOperationException during retrieveDelegatedToken. Returning empty token", e);
            return str3;
        } catch (Exception e2) {
            Log.e(TAG, "IdentityV2 : Exception during retrieveDelegatedToken.", e2);
            this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_DELEGATED_TOKEN_GET_FAIL), TAG, Utils.CUSTOM_ENTRIES);
            return str3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0186  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String retrieveUserAccessToken() {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.identity.MAPIdentityService.retrieveUserAccessToken():java.lang.String");
    }

    private Observable<UserIdentity> updateAccessToken() {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$jsVOjSYYdJq0W72Jqg8Ch2aHrr0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                MAPIdentityService.this.lambda$updateAccessToken$43$MAPIdentityService(observableEmitter);
            }
        });
    }

    private void updatePersonIdV2() {
        String personId = this.apesCaller.getPersonId(this.userIdentity.getUserProfile().getDirectedId());
        if (personId == null) {
            return;
        }
        notifyUserChanged(UserIdentity.from(this.userIdentity).withUserProfile(UserProfile.from(this.userIdentity.getUserProfile()).withPersonId(personId).build()).build());
    }

    protected void authorizeLinkCode(String str, String str2) {
        String account = this.mapAccountManager.getAccount();
        if (TextUtils.isEmpty(account)) {
            Log.e(TAG, "IdentityV2 : map account is null or empty");
            Utils.publishIdentityEvent(TAG, this.eventBus, IdentityEvent.IDENTITY_LINK_CODE_NOT_AUTHORIZED, (UserIdentity) null);
            return;
        }
        this.mapAccountManager.authorizeLinkCode(GeneratedOutlineSupport1.outline12("com.amazon.dcp.sso.property.account.acctId", account, MAPAccountManager.KEY_LINK_CODE, str2), new Callback() { // from class: com.amazon.alexa.identity.MAPIdentityService.1
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                Utils.publishIdentityEvent(MAPIdentityService.TAG, MAPIdentityService.this.eventBus, IdentityEvent.IDENTITY_LINK_CODE_NOT_AUTHORIZED, (UserIdentity) null);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                Utils.publishIdentityEvent(MAPIdentityService.TAG, MAPIdentityService.this.eventBus, IdentityEvent.IDENTITY_LINK_CODE_AUTHORIZED, (UserIdentity) null);
            }
        });
    }

    @VisibleForTesting
    String convertHost2TopLevelDomain(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(".")) {
            return null;
        }
        return str.substring(str.indexOf("."));
    }

    @NonNull
    @VisibleForTesting
    rx.Observable<String[]> createGetCookiesObservable(final String str, final String str2, final boolean z) {
        return rx.Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$jLIyPaEbanouN9PAod2Kq0JEIG0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$createGetCookiesObservable$20$MAPIdentityService(str, str2, z, (Subscriber) obj);
            }
        });
    }

    Single<String> createSingle(SingleOnSubscribe<String> singleOnSubscribe) {
        return Single.create(singleOnSubscribe).subscribeOn(Schedulers.io());
    }

    public void forceTokenRefreshOnStart() {
        this.forceTokenRefreshOnStart = true;
    }

    @Override // com.amazon.alexa.identity.api.AuthenticationProvider
    public Single<String> generateCBLRegistrationToken() {
        return createSingle(new SingleOnSubscribe() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$ASd-pfGUYaGmdjCHVMCn6yXEEDo
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                MAPIdentityService.this.lambda$generateCBLRegistrationToken$44$MAPIdentityService(singleEmitter);
            }
        });
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public String getAccessToken(String str) {
        if (this.userIdentity == null) {
            String format = String.format("%s getAccessToken early return. User is null. caller = %s", Utils.V2_LOG_PREFIX, str);
            Log.w(TAG, format);
            recordMobilyticsCriticalEvent(format, Metric.Event.ACCESS_TOKEN_REQUEST_NULL_IDENTITY, "getAccessToken");
            return "";
        }
        Log.i(TAG, String.format("%s getAccessToken for %s", Utils.V2_LOG_PREFIX, str));
        String retrieveUserAccessToken = retrieveUserAccessToken();
        if ("".equals(retrieveUserAccessToken)) {
            boolean isConnected = this.networkService.mo10268get().isConnected();
            Object[] objArr = new Object[3];
            objArr[0] = Utils.V2_LOG_PREFIX;
            objArr[1] = str;
            objArr[2] = isConnected ? "yes" : "no";
            String format2 = String.format("%s getAccessToken Failed to retrieveUserAccessToken. caller = %s. network connected? %s", objArr);
            Log.w(TAG, format2);
            recordMobilyticsCriticalEvent(format2, metricName(Metric.Event.ACCESS_TOKEN_REQUEST_EMPTY_TOKEN_FROM_MAP), "getAccessToken");
            this.mobilyticsProvider.mo10268get().recordOccurrence(metricName(Metric.Event.ACCESS_TOKEN_REQUEST_EMPTY_TOKEN_WHEN_NOT_CONNECTED), !isConnected, TAG, "getAccessToken", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
        }
        return retrieveUserAccessToken;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public String getAccessTokenForPerson(String str) {
        String str2 = "IdentityV2 : Getting access token for person using caller: " + str;
        if (!isAuthenticated(str)) {
            Log.w(TAG, "IdentityV2 : Failed to getAccessTokenForPerson. User is null or not registered with map");
            this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_ACTOR_TOKEN_REGISTER_FAIL), TAG, Utils.CUSTOM_ENTRIES);
            return "";
        }
        return retrieveActorTokenFromMap(getPersonId(this.userIdentity.getUserProfile(), Metric.Event.RETRIEVE_ACTOR_TOKEN_FAIL_NO_USERPROFILE_SELECTED, Metric.Event.RETRIEVE_ACTOR_TOKEN_FAIL_NULL_PERSONID));
    }

    @VisibleForTesting
    String getActorTokenKey() {
        return TokenKeys.getActorAccessTokenKeyForPackage(this.context.getPackageName());
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public rx.Observable<String[]> getCookiesFromDirectedId(final String str, final String str2) {
        if (str2 == null) {
            str2 = this.environmentService.getAuthWebHost();
        }
        String.format("%s getCookiesFromDirectedId getting cookies from %s for domain %s", Utils.V2_LOG_PREFIX, str, str2);
        return createGetCookiesObservable(str, str2, false).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$ClbHjHDufX0613TLsleNIgUMTos
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$getCookiesFromDirectedId$33$MAPIdentityService(str, str2, (String[]) obj);
            }
        }).doOnError(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$BG4kX1-_i0lQ1VYBounuvmmJg1k
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$getCookiesFromDirectedId$34$MAPIdentityService(str, str2, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public String getDelegatedToken(String str) {
        GeneratedOutlineSupport1.outline158("Getting delegated token using caller: ", str);
        if (this.userIdentity != null && this.userIdentity.getDelegatedProfile() != null) {
            if (!isAuthenticated(str)) {
                Log.w(TAG, "IdentityV2 : Failed to getAccessTokenForPerson. User is null or not registered with map");
                this.metricsService.mo10268get().recordEvent(metricName(Metric.Event.RETRIEVE_DELEGATED_TOKEN_REGISTER_FAIL), TAG, Utils.CUSTOM_ENTRIES);
                return "";
            }
            return retrieveDelegatedToken(str, getPersonId(this.userIdentity.getDelegatedProfile(), Metric.Event.RETRIEVE_DELEGATED_TOKEN_FAIL_NO_DELEGATEDPROFILE, Metric.Event.RETRIEVE_DELEGATED_TOKEN_FAIL_NULL_DELAGATED_PERSONID));
        }
        Log.e(TAG, "IdentityV2 : No active delegation found. So cannot retrieve token!");
        this.metricsService.mo10268get().recordEvent(Metric.Event.GET_DELEGATION_TOKEN_WITHOUT_ACTIVE_DELEGATION, TAG, Utils.CUSTOM_ENTRIES);
        return "";
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    @Nullable
    public String getDirectedAccountId(String str) {
        GeneratedOutlineSupport1.outline158("IdentityV2 : getDirectedAccountId caller:", str);
        return this.mapAccountManager.getAccount();
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public rx.Single<String> getSessionId(String str) {
        final String account = this.mapAccountManager.getAccount();
        final String authWebHost = this.environmentService.getAuthWebHost();
        return rx.Single.create(new Single.OnSubscribe() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$KVWDqGzyVays3XdEhgUt2oQVjko
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$getSessionId$35$MAPIdentityService(account, authWebHost, (SingleSubscriber) obj);
            }
        }).map(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$qyIhdvG1kHNzvGQZeBZJk_cVe5o
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return MAPIdentityService.this.lambda$getSessionId$36$MAPIdentityService((String[]) obj);
            }
        }).doOnSuccess(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$6H_8xnvHZS5gAvK_9Jrud0lgWy4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$getSessionId$37$MAPIdentityService(account, (String) obj);
            }
        }).doOnError(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$5zoWq9z9K9RoAMZ_oHsEuEXWCWs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$getSessionId$38$MAPIdentityService(account, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    String getTokenKey() {
        return TokenKeys.getAccessTokenKeyForPackage(this.context.getPackageName());
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public UserIdentity getUser(final String str) {
        if (isAuthenticated(str)) {
            if (this.userUpdateTimestamp.isExpired()) {
                if (!this.networkService.mo10268get().isConnected()) {
                    return this.userIdentity;
                }
                if (this.refreshInProgress.compareAndSet(false, true)) {
                    GeneratedOutlineSupport1.outline163("IdentityV2 : getUser calling refresh for ", str, TAG);
                    refreshInternal(str, true, true).first().doAfterTerminate(new Action0() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$vP0Bc4X3_VClXhkP2IaGX6xr_8c
                        @Override // rx.functions.Action0
                        public final void call() {
                            MAPIdentityService.this.lambda$getUser$9$MAPIdentityService();
                        }
                    }).subscribe(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$y78lORyZjTN0mADqKDInIMGg0B0
                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            MAPIdentityService.this.lambda$getUser$10$MAPIdentityService(str, (UserIdentity) obj);
                        }
                    }, new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$odZ3Bya3bVODv3mP8L4GRzJpM3s
                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            MAPIdentityService.this.lambda$getUser$11$MAPIdentityService(str, (Throwable) obj);
                        }
                    });
                }
            }
            return this.userIdentity;
        }
        String.format("%s: user was not authenticated, check cache", str);
        UserIdentity cachedIdentity = this.userRepository.getCachedIdentity();
        if (cachedIdentity != null && isRegistered(str, cachedIdentity.getDirectedId())) {
            String.format("%s: cached identity is registered with map, using that", str);
            return cachedIdentity;
        }
        String.format("%s: no authenticated user identity found", str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: handleGetCookies */
    public void lambda$createGetCookiesObservable$20$MAPIdentityService(String str, String str2, boolean z, Subscriber<? super String[]> subscriber) {
        this.tokenManagement.getCookies(str, str2, GeneratedOutlineSupport1.outline13(CookieKeys.Options.KEY_FORCE_REFRESH, z), new MAPCookiesCallback(subscriber, this.crashMetadata.mo10268get()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: handleGetSessionId */
    public void lambda$getSessionId$35$MAPIdentityService(String str, String str2, SingleSubscriber<? super String[]> singleSubscriber) {
        this.tokenManagement.getCookies(str, str2, GeneratedOutlineSupport1.outline13(CookieKeys.Options.KEY_FORCE_REFRESH, false), new MAPCookiesCallback(singleSubscriber, this.crashMetadata.mo10268get()));
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public void initiateDelegation(@NonNull UserProfile userProfile, @NonNull String str) {
        try {
            try {
                this.metricsService.mo10268get().startTimer(Metric.Event.INITIATE_DELEGATION_DURATION, TAG, Utils.CUSTOM_ENTRIES);
                Log.i(TAG, "IdentityV2 : Initiating the delegation for profile: " + userProfile.getPersonId() + "and caller: " + str);
                Preconditions.checkArgument(!TextUtils.isEmpty(userProfile.getFirstName()), "First Name is Empty.");
                Preconditions.checkArgument(!TextUtils.isEmpty(userProfile.getPersonId()), "PersonId is Empty");
                Preconditions.checkArgument(this.userIdentity.getUserProfile() != null, "No profile found for current user.");
                UserProfile delegatedProfile = this.userIdentity.getDelegatedProfile();
                if (delegatedProfile != null && !userProfile.getPersonId().equals(delegatedProfile.getPersonId())) {
                    String str2 = "Delegation already in progress for: " + delegatedProfile.getPersonId();
                    Log.e(TAG, Utils.V2_LOG_PREFIX + str2);
                    throw new IllegalStateException(str2);
                }
                if (TextUtils.isEmpty(this.userIdentity.getUserProfile().getPersonId())) {
                    updatePersonIdV2();
                }
                this.delegatedTokenManagement.initiateDelegation(userProfile.getPersonId(), getAccessTokenForPerson(str));
                notifyUserChanged(UserIdentity.from(this.userIdentity).withDelegatedProfile(userProfile).build());
                Utils.publishDelegationUpdateEvent(TAG, this.eventBus, IdentityEvent.IDENTITY_PROFILE_DELEGATION_CHANGED, "ACTIVE");
                Log.i(TAG, "IdentityV2 : Initiated the delegation for profile: " + userProfile.getPersonId());
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.INITIATE_DELEGATION_SUCCESS_RATE, TAG, true, Utils.CUSTOM_ENTRIES);
            } catch (Exception e) {
                Log.e(TAG, "IdentityV2 : Exception occurred while initiating delegation for personId: " + userProfile.getPersonId());
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.INITIATE_DELEGATION_SUCCESS_RATE, TAG, false, Utils.CUSTOM_ENTRIES);
                throw e;
            }
        } finally {
            this.metricsService.mo10268get().recordTimer(Metric.Event.INITIATE_DELEGATION_DURATION, Utils.CUSTOM_ENTRIES);
        }
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public boolean isAuthenticated(String str) {
        return this.userIdentity != null && isRegistered(str, this.userIdentity.getDirectedId());
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public boolean isProfileAuthenticated(String str) {
        if (this.userIdentity.getUserProfile() == null || TextUtils.isEmpty(this.userIdentity.getUserProfile().getPersonId())) {
            return true;
        }
        this.metricsService.mo10268get().startTimer(Metric.Event.IS_AUTHENTICATED_V2_DURATION, TAG, Utils.CUSTOM_ENTRIES);
        String personId = this.userIdentity.getUserProfile().getPersonId();
        boolean isPersonIdAuthenticated = isPersonIdAuthenticated(personId);
        this.metricsService.mo10268get().recordTimer(Metric.Event.IS_AUTHENTICATED_V2_DURATION, Utils.CUSTOM_ENTRIES);
        String str2 = "IdentityV2 : Authentication result for profile: " + personId + " is: " + isPersonIdAuthenticated;
        return isPersonIdAuthenticated;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public boolean isRegistered(String str) {
        return isRegistered(str, this.mapAccountManager.getAccount());
    }

    public /* synthetic */ void lambda$generateCBLRegistrationToken$44$MAPIdentityService(final SingleEmitter singleEmitter) throws Throwable {
        this.mapAccountManager.generatePreAuthorizedLinkCode(GeneratedOutlineSupport1.outline11("com.amazon.dcp.sso.property.account.acctId", this.mapAccountManager.getAccount()), new Callback() { // from class: com.amazon.alexa.identity.MAPIdentityService.3
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                singleEmitter.onError(new MAPCallbackErrorException(bundle));
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                singleEmitter.onSuccess(bundle.getString(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE));
            }
        });
    }

    public /* synthetic */ void lambda$getCookiesFromDirectedId$33$MAPIdentityService(String str, String str2, String[] strArr) {
        Log.i(TAG, String.format("%sSucceeded getting cookies from %s for domain %s", Utils.V2_LOG_PREFIX, str, str2));
        this.mobilyticsProvider.mo10268get().recordOccurrence(metricName(Metric.Event.REFRESH_COOKIES_SUCCESS_RATE), true, TAG, "getCookiesFromDirectedId", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public /* synthetic */ void lambda$getCookiesFromDirectedId$34$MAPIdentityService(String str, String str2, Throwable th) {
        boolean isConnected = this.networkService.mo10268get().isConnected();
        String str3 = TAG;
        Object[] objArr = new Object[4];
        objArr[0] = Utils.V2_LOG_PREFIX;
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = isConnected ? "yes" : "no";
        Log.i(str3, String.format("%sFailed getting cookies from %s for domain %s. network connected? %s", objArr), th);
        this.mobilyticsProvider.mo10268get().recordOccurrence(metricName(Metric.Event.REFRESH_COOKIES_SUCCESS_RATE), false, TAG, "getCookiesFromDirectedId", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
        this.mobilyticsProvider.mo10268get().recordOccurrence(metricName(Metric.Event.REFRESH_COOKIE_FAILURE_WHEN_NOT_CONNECTED), !isConnected, TAG, "getCookiesFromDirectedId", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public /* synthetic */ String lambda$getSessionId$36$MAPIdentityService(String[] strArr) {
        setCookies(new String[]{this.environmentService.getCoralEndpoint(), this.environmentService.getWebEndpoint()}, strArr);
        String cookie = this.cookieManager.mo358get().getCookie(this.environmentService.getWebEndpoint());
        return (cookie == null || cookie.isEmpty() || !cookie.contains("session-id=")) ? "" : getValueFromCookie(cookie, "session-id");
    }

    public /* synthetic */ void lambda$getSessionId$37$MAPIdentityService(String str, String str2) {
        GeneratedOutlineSupport1.outline158("IdentityV2 : Succeeded getSessionId from ", str);
        this.metricsService.mo10268get().recordOccurrence(metricName(Metric.Event.GET_SESSION_ID_SUCCESS_RATE), TAG, true, Utils.CUSTOM_ENTRIES);
    }

    public /* synthetic */ void lambda$getSessionId$38$MAPIdentityService(String str, Throwable th) {
        GeneratedOutlineSupport1.outline162("IdentityV2 : Failed getSessionId from ", str, TAG);
        this.metricsService.mo10268get().recordOccurrence(metricName(Metric.Event.GET_SESSION_ID_SUCCESS_RATE), TAG, false, Utils.CUSTOM_ENTRIES);
    }

    public /* synthetic */ void lambda$getUser$10$MAPIdentityService(String str, UserIdentity userIdentity) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(String.format("getUser refresh succeeded for %s", str));
        Log.i(str2, outline107.toString());
        this.userUpdateTimestamp.update();
    }

    public /* synthetic */ void lambda$getUser$11$MAPIdentityService(String str, Throwable th) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(String.format("getUser refresh failed for %s", str));
        Log.e(str2, outline107.toString());
        this.userUpdateTimestamp.update();
    }

    public /* synthetic */ void lambda$getUser$9$MAPIdentityService() {
        this.refreshInProgress.set(false);
    }

    public /* synthetic */ void lambda$new$0$MAPIdentityService(Message message) {
        onTermsOfUseAccepted();
    }

    public /* synthetic */ void lambda$new$1$MAPIdentityService(Message message) {
        queueUserRefreshOnEvent("event:COR_PFM_UPDATED");
    }

    public /* synthetic */ void lambda$new$2$MAPIdentityService(Message message) {
        queueUserRefreshOnEvent("event:APP_UPDATE");
    }

    public /* synthetic */ void lambda$new$3$MAPIdentityService(Message message) {
        queueUserRefreshOnEvent("event:IDENTITY_SIGN_IN_SUCCESS");
    }

    public /* synthetic */ void lambda$new$4$MAPIdentityService(Message message) {
        onOobeProfileSelected();
    }

    public /* synthetic */ void lambda$new$5$MAPIdentityService(Message message) {
        onUserProfileSelection(message.getPayloadAsString());
    }

    public /* synthetic */ void lambda$new$6$MAPIdentityService(Message message) {
        submitLinkCode(TAG, message.getPayloadAsString());
    }

    public /* synthetic */ void lambda$new$7$MAPIdentityService(Message message) {
        onEndpointsChanged(message.getPayloadAsString());
    }

    public /* synthetic */ void lambda$new$8$MAPIdentityService(Message message) {
        onProfileOobeCompleted();
    }

    public /* synthetic */ void lambda$null$12$MAPIdentityService(String str, UserIdentity userIdentity, UserIdentity userIdentity2) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(String.format("serverFetch succeeded for %s", str));
        Log.i(str2, outline107.toString());
        HashMap hashMap = new HashMap();
        hashMap.putAll(userIdentity2.getTokens());
        String str3 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityV2 : number of tokens fetched from the server are ");
        outline1072.append(hashMap.size());
        Log.i(str3, outline1072.toString());
        hashMap.putAll(userIdentity.getTokens());
        UserIdentity build = UserIdentity.from(userIdentity2).withTokens(hashMap).build();
        String str4 = TAG;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("serverFetch saving user with eula ");
        outline1073.append(build.hasAcceptedEula());
        Log.i(str4, outline1073.toString());
        this.userRepository.save(build);
        updateUserIdentity(build);
    }

    public /* synthetic */ rx.Observable lambda$null$14$MAPIdentityService(final String str, boolean z, final UserIdentity userIdentity) {
        rx.Observable<UserIdentity> share = this.userRepository.get(UserIdentityRepository.FetchOptions.FromServer).first().share();
        if (userIdentity == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
            outline107.append(String.format("%s: No cached copy of the user found; going to pull from the server.", str));
            outline107.toString();
            return share;
        }
        share.subscribe(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$X7sFRv-yTxlwgz1x_s2Gu1Lu_SM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$null$12$MAPIdentityService(str, userIdentity, (UserIdentity) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$oCE0WvMqEir_dtzUCJmNixxqVkA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.lambda$null$13(str, (Throwable) obj);
            }
        });
        return z ? share : rx.Observable.just(userIdentity);
    }

    public /* synthetic */ rx.Observable lambda$null$16$MAPIdentityService(String[] strArr) {
        return this.userRepository.get(UserIdentityRepository.FetchOptions.FromServer);
    }

    public /* synthetic */ rx.Observable lambda$refreshCookies$22$MAPIdentityService(String str, boolean z, final String str2) {
        return createGetCookiesObservable(str, str2, z).map(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$ePhSLWBKHq6Ow1YXbreMQdEr_Co
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return MAPIdentityService.lambda$null$21(str2, (String[]) obj);
            }
        });
    }

    public /* synthetic */ void lambda$refreshCookies$23$MAPIdentityService(DomainCookie domainCookie) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(String.format("refreshCookies (dataRegion) doOnNext called for %s", domainCookie.domain));
        Log.i(str, outline107.toString());
        setCookies(new String[]{"https://alexa" + domainCookie.domain}, domainCookie.cookies);
    }

    public /* synthetic */ void lambda$refreshCookies$28$MAPIdentityService(String str, boolean z, String[] strArr) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(String.format("refreshCookies (dataRegion) %s: Succeeded refreshing cookies with force: %b", str, Boolean.valueOf(z)));
        outline107.toString();
        this.mobilyticsProvider.mo10268get().recordOccurrence(metricName(REFRESH_COOKIES_DATA_REGION_SUCCESS_RATE), true, TAG, "refreshCookies", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public /* synthetic */ void lambda$refreshCookies$29$MAPIdentityService(String str, boolean z, Throwable th) {
        String format = String.format("refreshCookies (dataRegion) %s: Failed refreshing cookies with force: %b", str, Boolean.valueOf(z));
        String str2 = TAG;
        Log.e(str2, Utils.V2_LOG_PREFIX + format, th);
        this.mobilyticsProvider.mo10268get().recordCriticalEvent(format, metricName(REFRESH_COOKIES_DATA_REGION_FAILURE), TAG, "refreshCookies", th, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
        this.mobilyticsProvider.mo10268get().recordOccurrence(metricName(REFRESH_COOKIES_DATA_REGION_SUCCESS_RATE), false, TAG, "refreshCookies", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public /* synthetic */ String[] lambda$refreshCookies$30$MAPIdentityService(String[][] strArr, String[] strArr2) {
        setCookies(new String[]{this.environmentService.getCoralEndpoint(), this.environmentService.getWebEndpoint()}, strArr2);
        strArr[0] = strArr2;
        return strArr2;
    }

    public /* synthetic */ void lambda$refreshCookies$31$MAPIdentityService(String str, boolean z, String[] strArr) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(String.format("%s: Succeeded refreshing cookies with force: %b", str, Boolean.valueOf(z)));
        outline107.toString();
        this.mobilyticsProvider.mo10268get().recordOccurrence(metricName(Metric.Event.REFRESH_COOKIES_SUCCESS_RATE), true, TAG, "refreshCookies", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public /* synthetic */ void lambda$refreshCookies$32$MAPIdentityService(String str, boolean z, Throwable th) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(String.format("%s: Failed refreshing cookies with force: %b", str, Boolean.valueOf(z)));
        Log.e(str2, outline107.toString(), th);
        this.mobilyticsProvider.mo10268get().recordCriticalEvent(metricName(REFRESH_COOKIES_FAILURE), metricName(REFRESH_COOKIES_FAILURE), TAG, "refreshCookies", th, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
        this.mobilyticsProvider.mo10268get().recordOccurrence(metricName(Metric.Event.REFRESH_COOKIES_SUCCESS_RATE), false, TAG, "refreshCookies", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public /* synthetic */ rx.Observable lambda$refreshInternal$15$MAPIdentityService(final String str, final boolean z, String[] strArr) {
        return this.userRepository.get(UserIdentityRepository.FetchOptions.FromCache).switchMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$0GaAaIAnwu6i6IEMHmce5CoW8fU
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return MAPIdentityService.this.lambda$null$14$MAPIdentityService(str, z, (UserIdentity) obj);
            }
        });
    }

    public /* synthetic */ rx.Observable lambda$refreshInternal$17$MAPIdentityService(UserIdentity userIdentity) {
        MarketplaceEndpoints forMarketplace = this.environmentService.forMarketplace(userIdentity.getEffectiveMarketplace());
        boolean z = !Objects.equals(forMarketplace.getCoralHost(), this.environmentService.getCoralHost());
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(Utils.safeFormat("Change Required: [%b] envService: %s pfmConfig: %s", Boolean.valueOf(z), this.environmentService.getCoralHost(), forMarketplace.getCoralHost()));
        Log.i(str, outline107.toString());
        if (!z) {
            this.mobilyticsProvider.mo10268get().recordOccurrence(REFRESH_COOKIES_ENDPOINT_SERVICE_MISMATCH, false, TAG, "refreshInternal", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
            return rx.Observable.just(userIdentity);
        }
        this.mobilyticsProvider.mo10268get().recordOccurrence(REFRESH_COOKIES_ENDPOINT_SERVICE_MISMATCH, true, TAG, "refreshInternal", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
        Log.i(TAG, String.format("%s Setting marketplace on envService to %s", Utils.V2_LOG_PREFIX, userIdentity.getEffectiveMarketplace()));
        this.environmentService.setMarketplace(userIdentity.getEffectiveMarketplace());
        return refreshCookies(this.mapAccountManager.getAccount(), forMarketplace.getAuthWebHost(), false).switchMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$qH-72QdWbs7g6YF3RLEAaFMO3Q8
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return MAPIdentityService.this.lambda$null$16$MAPIdentityService((String[]) obj);
            }
        });
    }

    public /* synthetic */ void lambda$refreshInternal$18$MAPIdentityService(UserIdentity userIdentity) {
        Log.i(TAG, "IdentityV2 : refreshInternal: succeeded");
        this.mobilyticsProvider.mo10268get().recordOccurrence(Metric.Event.SERVER_IDENTITY_FETCH_SUCCEEDED, true, TAG, "refreshInternal", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
        Log.i(TAG, "IdentityV2 : refreshInternal calling updateUserIdentity");
        updateUserIdentity(userIdentity);
    }

    public /* synthetic */ void lambda$refreshInternal$19$MAPIdentityService(Throwable th) {
        Log.e(TAG, "IdentityV2 : refreshInternal: failed", th);
        this.mobilyticsProvider.mo10268get().recordOccurrence(Metric.Event.SERVER_IDENTITY_FETCH_SUCCEEDED, false, TAG, "refreshInternal", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
        logRefreshInternalError(th);
    }

    public /* synthetic */ void lambda$updateAccessToken$43$MAPIdentityService(final ObservableEmitter observableEmitter) throws Throwable {
        String tokenKey = getTokenKey();
        String account = this.mapAccountManager.getAccount();
        final UserIdentity userIdentity = this.userIdentity;
        if (account != null && userIdentity != null) {
            this.tokenManagement.getToken(account, tokenKey, null, new Callback() { // from class: com.amazon.alexa.identity.MAPIdentityService.2
                @Override // com.amazon.identity.auth.device.api.Callback
                public void onError(Bundle bundle) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to obtain token with a message: ");
                    outline107.append(bundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                    Exception exc = new Exception(outline107.toString());
                    MAPIdentityService mAPIdentityService = MAPIdentityService.this;
                    mAPIdentityService.logUserError(exc, mAPIdentityService.metricName(Metric.Event.LOGIN_TOKEN_ERROR));
                    observableEmitter.onError(exc);
                }

                @Override // com.amazon.identity.auth.device.api.Callback
                public void onSuccess(Bundle bundle) {
                    UserIdentity userIdentity2 = MAPIdentityService.this.userIdentity;
                    if (userIdentity2 != null && userIdentity.getDirectedId().equals(userIdentity2.getDirectedId())) {
                        String string = bundle.getString("value_key");
                        GeneratedOutlineSupport1.outline173("IdentityV2 : updateAccessToken: Received access token from MAP is empty? ", Strings.isNullOrEmpty(string), MAPIdentityService.TAG);
                        observableEmitter.onNext(UserIdentity.from(userIdentity2).withAccessToken(string).build());
                    } else {
                        observableEmitter.onNext(userIdentity2);
                    }
                    observableEmitter.onComplete();
                }
            });
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("User is not authenticated");
        logUserError(illegalStateException, metricName(Metric.Event.LOGIN_AUTH_ERROR));
        observableEmitter.onError(illegalStateException);
    }

    public /* synthetic */ void lambda$updateUserIdentity$39$MAPIdentityService(UserIdentity userIdentity) throws Throwable {
        notifyUserChanged(userIdentity);
        this.metricsService.mo10268get().recordOccurrence(metricName(Metric.Event.REFRESH_AUTH_TOKEN_SUCCESS_RATE), TAG, true, Utils.CUSTOM_ENTRIES);
    }

    public /* synthetic */ void lambda$updateUserIdentity$40$MAPIdentityService(Throwable th) throws Throwable {
        notifyUserChanged(this.userIdentity);
        this.metricsService.mo10268get().recordOccurrence(metricName(Metric.Event.REFRESH_AUTH_TOKEN_SUCCESS_RATE), TAG, false, Utils.CUSTOM_ENTRIES);
    }

    @VisibleForTesting
    String logFeatureChanges(UserIdentity userIdentity, UserIdentity userIdentity2) {
        if (userIdentity == null || userIdentity2 == null || !Objects.equals(userIdentity.getDirectedId(), userIdentity2.getDirectedId()) || Objects.equals(userIdentity.getFeatures(), userIdentity2.getFeatures())) {
            return "";
        }
        StringBuilder sb = new StringBuilder("Received feature changes");
        for (String str : Sets.difference(userIdentity.getFeatures(), userIdentity2.getFeatures())) {
            sb.append("\n\t- ");
            sb.append(str);
        }
        for (String str2 : Sets.difference(userIdentity2.getFeatures(), userIdentity.getFeatures())) {
            sb.append("\n\t+ ");
            sb.append(str2);
        }
        String sb2 = sb.toString();
        Log.i(TAG, sb2);
        return sb2;
    }

    @VisibleForTesting
    void onEndpointsChanged(String str) {
        Log.i(TAG, "IdentityV2 : onEndpointsChanged now refreshing cookies");
        refreshCookies("onEndpointsChanged", this.mapAccountManager.getAccount(), null, true);
    }

    void onUserProfileSelection(String str) {
        Log.i(TAG, "IdentityV2 : got IDENTITY_PROFILE_SELECTION_ATTEMPT.");
        if (this.userIdentity != null) {
            UserProfile currentProfile = this.profileManager.getCurrentProfile(str);
            if (!currentProfile.getDirectedId().equals(this.userIdentity.getDirectedId()) && !this.accountRegistrationService.mo358get().registerSecondaryAccount(currentProfile.getDirectedId(), this.userIdentity.getFeatures())) {
                Log.e(TAG, "IdentityV2 : Failed to register secondary account with MAP!");
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.USER_PROFILE_SELECTION_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
                this.mobilyticsProvider.mo10268get().recordOccurrence(Metric.Event.USER_PROFILE_SELECTION_SUCCESS, false, TAG, "onUserProfileSelection", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
                return;
            }
            UserIdentity build = UserIdentity.from(this.userIdentity).withUserProfile(currentProfile).build();
            Utils.publishIdentityEvent(TAG, this.eventBus, IdentityEvent.IDENTITY_PROFILE_CHANGED, str);
            notifyUserChanged(build);
            Log.i(TAG, "IdentityV2 : successfully processed IDENTITY_PROFILE_SELECTION_ATTEMPT event.");
            this.metricsService.mo10268get().recordOccurrence(Metric.Event.USER_PROFILE_SELECTION_SUCCESS, TAG, true, Utils.CUSTOM_ENTRIES);
            this.mobilyticsProvider.mo10268get().recordOccurrence(Metric.Event.USER_PROFILE_SELECTION_SUCCESS, true, TAG, "onUserProfileSelection", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
            return;
        }
        this.metricsService.mo10268get().recordOccurrence(Metric.Event.USER_PROFILE_SELECTION_SUCCESS, TAG, false, Utils.CUSTOM_ENTRIES);
        this.mobilyticsProvider.mo10268get().recordOccurrence(Metric.Event.USER_PROFILE_SELECTION_SUCCESS, false, TAG, "onUserProfileSelection", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public synchronized void putUserToken(String str, String str2) {
        boolean isNullOrEmpty = Strings.isNullOrEmpty(str2);
        String str3 = TAG;
        Log.i(str3, Utils.V2_LOG_PREFIX + String.format("putUserToken called for key %s. token empty? ", str, Boolean.valueOf(isNullOrEmpty)));
        HashMap hashMap = new HashMap(this.userIdentity.getTokens());
        hashMap.put(str, str2);
        updateUserIdentity(UserIdentity.from(this.userIdentity).withTokens(hashMap).build());
        persistUser(TAG);
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    @NonNull
    public rx.Observable<UserIdentity> refresh(String str) {
        UserIdentity cachedIdentity = this.userRepository.getCachedIdentity();
        boolean isConnected = this.networkService.mo10268get().isConnected();
        String str2 = TAG;
        Object[] objArr = new Object[3];
        objArr[0] = Utils.V2_LOG_PREFIX;
        objArr[1] = str;
        objArr[2] = isConnected ? "yes" : "no";
        Log.i(str2, String.format("%s refresh called by %s. network connected? %s", objArr));
        if (!isConnected && cachedIdentity != null && isRegistered(str, cachedIdentity.getDirectedId())) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
            outline107.append(String.format("%s: Using cached identity, since it's also registered with map", str));
            outline107.toString();
            updateUserIdentity(cachedIdentity);
            return rx.Observable.just(cachedIdentity);
        }
        boolean z = this.forceTokenRefreshOnStart;
        this.forceTokenRefreshOnStart = false;
        return refreshInternal(str, z, false);
    }

    @VisibleForTesting
    rx.Observable<String[]> refreshCookies(final String str, final String str2, String str3, final boolean z) {
        if (this.userIdentity != null && this.userIdentity.hasFeature("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID")) {
            String convertHost2TopLevelDomain = convertHost2TopLevelDomain(this.environmentService.getCoralHost());
            String convertHost2TopLevelDomain2 = convertHost2TopLevelDomain(this.environmentService.getWebHost());
            String convertHost2TopLevelDomain3 = convertHost2TopLevelDomain(this.environmentService.getRetailEndpoint());
            HashSet hashSet = new HashSet(3);
            hashSet.add(convertHost2TopLevelDomain);
            hashSet.add(convertHost2TopLevelDomain2);
            hashSet.add(convertHost2TopLevelDomain3);
            String str4 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IdentityV2 : refreshCookies (dataRegion). unique domain count ");
            outline107.append(hashSet.size());
            Log.i(str4, outline107.toString());
            return rx.Observable.from(hashSet.toArray(new String[0])).flatMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$wi4VdlIk99rp3xZLbWjd7s3cxrc
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return MAPIdentityService.this.lambda$refreshCookies$22$MAPIdentityService(str2, z, (String) obj);
                }
            }).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$W99Ci3b-ba2gRph_bbdxYgIfYh8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MAPIdentityService.this.lambda$refreshCookies$23$MAPIdentityService((MAPIdentityService.DomainCookie) obj);
                }
            }).map($$Lambda$MAPIdentityService$TplG8dtESPIIjeJO04bsKfvFa4I.INSTANCE).collect($$Lambda$MAPIdentityService$Nk8aoU8XJUXujEfo2xXr203ZbME.INSTANCE, $$Lambda$MAPIdentityService$JG6V6bJHkEDSuJavLtmxVV1aVio.INSTANCE).map($$Lambda$MAPIdentityService$BQoXzVdjfrr2nwNapgVZx9SMzM.INSTANCE).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$gDJE7mtkJQE-3fkqzrSdTaqU12g
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MAPIdentityService.this.lambda$refreshCookies$28$MAPIdentityService(str, z, (String[]) obj);
                }
            }).doOnError(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$WgYYnYyp87agAg3bkbTcJ7sSKi8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MAPIdentityService.this.lambda$refreshCookies$29$MAPIdentityService(str, z, (Throwable) obj);
                }
            });
        }
        Log.i(TAG, "IdentityV2 : refreshCookies not in (dataRegion) feature");
        final String[][] strArr = (String[][]) Array.newInstance(String.class, 1, 1);
        return createGetCookiesObservable(str2, str3, z).map(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$Jv5YgTFm9COR43prvoA4Bg941vg
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return MAPIdentityService.this.lambda$refreshCookies$30$MAPIdentityService(strArr, (String[]) obj);
            }
        }).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$4LHf21cBxqc4Sc0gYCsX9Apewqc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$refreshCookies$31$MAPIdentityService(str, z, (String[]) obj);
            }
        }).doOnError(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$PM5_DOdXGGqNCOB2PRerj_Mo8PM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$refreshCookies$32$MAPIdentityService(str, z, (Throwable) obj);
            }
        });
    }

    @NonNull
    @VisibleForTesting
    rx.Observable<UserIdentity> refreshInternal(final String str, boolean z, final boolean z2) {
        String.format("refreshCookies for %s with forceCookieRefresh %s and authWebHost %s", str, Boolean.valueOf(z), this.environmentService.getAuthWebHost());
        return refreshCookies(str, this.mapAccountManager.getAccount(), this.environmentService.getAuthWebHost(), z).switchMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$KFegdx98nG7qull1RNG6AvESJwE
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return MAPIdentityService.this.lambda$refreshInternal$15$MAPIdentityService(str, z2, (String[]) obj);
            }
        }).switchMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$HHFMoHR2yTGjUvdJxMKTsm76c40
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return MAPIdentityService.this.lambda$refreshInternal$17$MAPIdentityService((UserIdentity) obj);
            }
        }).doOnNext(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$KP3SOS3yHMx2nfzqfHUPQXGfrAE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$refreshInternal$18$MAPIdentityService((UserIdentity) obj);
            }
        }).doOnError(new Action1() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$OZxR8YuW2v-Bny8ZRPAAizxS-U0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MAPIdentityService.this.lambda$refreshInternal$19$MAPIdentityService((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeCookies() {
        this.cookieManager.mo358get().removeAllCookie();
    }

    void setCookies(@NonNull String[] strArr, @NonNull String[] strArr2) {
        this.cookieManager.mo358get().setAcceptCookie(true);
        for (String str : strArr) {
            GeneratedOutlineSupport1.outline163("IdentityV2 : Setting cookies for ", str, TAG);
            for (String str2 : strArr2) {
                this.cookieManager.mo358get().setCookie(str, str2);
            }
        }
        Utils.publishCookieEvent(TAG, this.eventBus, IdentityEvent.IDENTITY_COOKIES_UPDATED);
    }

    public void submitLinkCode(String str, String str2) {
        authorizeLinkCode(str, str2);
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public void terminateDelegation(String str) {
        GeneratedOutlineSupport1.outline163("IdentityV2 : Terminating the delegation for caller: ", str, TAG);
        this.metricsService.mo10268get().startTimer(Metric.Event.TERMINATE_DELEGATION_DURATION, TAG, Utils.CUSTOM_ENTRIES);
        try {
            try {
                if (this.userIdentity != null && this.userIdentity.getDelegatedProfile() != null) {
                    String str2 = TAG;
                    Log.i(str2, "IdentityV2 : Terminating the delegation for profile: " + this.userIdentity.getDelegatedProfile().getPersonId());
                    clearDelegationToken(str);
                    notifyUserChanged(UserIdentity.from(this.userIdentity).withDelegatedProfile(null).build());
                    Utils.publishDelegationUpdateEvent(TAG, this.eventBus, IdentityEvent.IDENTITY_PROFILE_DELEGATION_CHANGED, "INACTIVE");
                } else {
                    clearDelegationToken(str);
                }
                Log.i(TAG, "IdentityV2 : Terminated the active delegation.");
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.TERMINATE_DELEGATION_SUCCESS_RATE, TAG, true, Utils.CUSTOM_ENTRIES);
            } catch (Exception e) {
                Log.e(TAG, "IdentityV2 : Exception occurred while terminating delegation.", e);
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.TERMINATE_DELEGATION_SUCCESS_RATE, TAG, false, Utils.CUSTOM_ENTRIES);
                throw e;
            }
        } finally {
            this.metricsService.mo10268get().recordTimer(Metric.Event.TERMINATE_DELEGATION_DURATION, Utils.CUSTOM_ENTRIES);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateUserIdentity(UserIdentity userIdentity) {
        updateUserIdentity(userIdentity, false);
    }

    private void recordMobilyticsCriticalEvent(String str, String str2, String str3, Throwable th) {
        this.mobilyticsProvider.mo10268get().recordCriticalEvent(str, str2, TAG, str3, th, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    private void updateUserIdentity(UserIdentity userIdentity, boolean z) {
        if (!z && Objects.equals(this.userIdentity, userIdentity)) {
            Log.i(TAG, "IdentityV2 : updateUserIdentity no change detected.");
            return;
        }
        notifyUserChanged(userIdentity);
        if (userIdentity == null) {
            this.dataStoreService.clear();
            this.userRepository.clear();
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
            outline107.append(Utils.safeFormat("updateUserIdentity: %s", userIdentity.getId()));
            outline107.toString();
        }
        updateAccessToken().doOnNext(new Consumer() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$Cbyj7qfCjRt5fINsMWO-CKQLLHU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MAPIdentityService.this.lambda$updateUserIdentity$39$MAPIdentityService((UserIdentity) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$j6oAJLSPewyAwvYZDBXaAVFJdqg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MAPIdentityService.this.lambda$updateUserIdentity$40$MAPIdentityService((Throwable) obj);
            }
        }).subscribe($$Lambda$MAPIdentityService$GJyFXEFo_MCvosrDZWkFIKcvZvA.INSTANCE, $$Lambda$MAPIdentityService$obo_DBCh4Nt78Csv02th6a0DMuQ.INSTANCE);
    }

    @VisibleForTesting
    boolean isRegistered(String str, String str2) {
        return str2 != null && this.mapAccountManager.isAccountRegistered(str2);
    }

    @VisibleForTesting
    rx.Observable<String[]> refreshCookies(String str, String str2, boolean z) {
        logWhereCalledFrom();
        return refreshCookies("unknown", str, str2, z);
    }

    @Deprecated
    public MAPIdentityService(ComponentRegistry componentRegistry, MAPAccountManager mAPAccountManager, Lazy<CookieManager> lazy, TokenManagement tokenManagement, DataStoreService dataStoreService, UserIdentityRepository userIdentityRepository, UserProfileManager userProfileManager, Lazy<MAPAccountRegistrationService> lazy2, DelegatedTokenManagement delegatedTokenManagement, Context context) {
        this(componentRegistry, mAPAccountManager, lazy, tokenManagement, dataStoreService, userIdentityRepository, userProfileManager, lazy2, delegatedTokenManagement, context, null);
    }

    @Deprecated
    public MAPIdentityService(ComponentRegistry componentRegistry, MAPAccountManager mAPAccountManager, Lazy<CookieManager> lazy, TokenManagement tokenManagement, DataStoreService dataStoreService, UserIdentityRepository userIdentityRepository, UserProfileManager userProfileManager, Lazy<MAPAccountRegistrationService> lazy2, Context context) {
        this(componentRegistry, mAPAccountManager, lazy, tokenManagement, dataStoreService, userIdentityRepository, userProfileManager, lazy2, null, context);
    }
}
