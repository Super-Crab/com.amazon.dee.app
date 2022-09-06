package com.amazon.dee.app.elements.bridges;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.CommsProfile;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.elements.ElementsUtils;
import com.amazon.dee.app.services.coral.CookiesDomainUtil;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.dee.app.metrics.MetricsService;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.common.Scopes;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
@ReactModule(name = IdentityModule.MODULE_NAME)
/* loaded from: classes12.dex */
public class IdentityModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String COOKIE_CHANGED_EVENT = "AMTS::COOKIES_DID_CHANGE";
    private static final String MODULE_NAME = "Identity";
    public static final String TAG = Log.tag(IdentityModule.class);
    private static final String USER_CHANGED_EVENT = "userChanged";
    private final AccountService accountService;
    private final CollectionsFactory collectionsFactory;
    @Nullable
    private MultiFilterSubscriber cookieChangeSubscription;
    @Nullable
    private volatile UserIdentity currentUser;
    private final EventBus eventBus;
    private FeatureServiceV2 featureServiceV2;
    private final IdentityService identityService;
    private final MetricsService metricsService;
    private final ReactApplicationContext reactApplicationContext;
    private final RoutingService routingService;
    @Nullable
    private MultiFilterSubscriber userChangedSubscription;

    public IdentityModule(ReactApplicationContext reactApplicationContext, CollectionsFactory collectionsFactory, IdentityService identityService, AccountService accountService, RoutingService routingService, EventBus eventBus, MetricsService metricsService, FeatureServiceV2 featureServiceV2) {
        super(reactApplicationContext);
        this.collectionsFactory = collectionsFactory;
        this.identityService = identityService;
        this.accountService = accountService;
        this.reactApplicationContext = reactApplicationContext;
        this.routingService = routingService;
        this.eventBus = eventBus;
        this.metricsService = metricsService;
        this.featureServiceV2 = featureServiceV2;
        this.userChangedSubscription = listenForIdentityChange();
        this.cookieChangeSubscription = listenForCookieChange();
        reactApplicationContext.addLifecycleEventListener(this);
    }

    private WritableMap createProfileMap(UserProfile userProfile) {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putString("directedId", userProfile.getDirectedId());
        createMap.putString("firstName", userProfile.getFirstName());
        createMap.putString("lastName", userProfile.getLastName());
        createMap.putString(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID, userProfile.getPersonId());
        WritableMap createMap2 = this.collectionsFactory.createMap();
        CommsProfile commsProfile = userProfile.getCommsProfile();
        if (commsProfile != null) {
            WritableMap createMap3 = this.collectionsFactory.createMap();
            createMap3.putString("id", commsProfile.getCommsId());
            createMap3.putString("phoneNumber", commsProfile.getPhoneNumber());
            createMap3.putString(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR, commsProfile.getAor());
            createMap3.putString(MetricKeys.META_HASHED_COMMS_ID, commsProfile.getHashedCommsId());
            createMap3.putString("householdId", commsProfile.getHouseholdId());
            createMap2.putMap("comms", createMap3);
        }
        createMap.putMap("metadata", createMap2);
        return createMap;
    }

    @NonNull
    private String getAction(UserIdentity userIdentity, UserIdentity userIdentity2) {
        return (userIdentity != null || userIdentity2 == null) ? ((userIdentity == null || userIdentity2 != null) && userIdentity != null) ? !userIdentity.getId().equals(userIdentity2.getId()) ? USER_CHANGED_EVENT : "update" : "logout" : "login";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$signOut$3(Void r0) {
    }

    private MultiFilterSubscriber listenForCookieChange() {
        MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
        subscriber.subscribe(new EventTypeMessageFilter(IdentityEvent.IDENTITY_COOKIES_UPDATED), new MessageHandler() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$czyMktUvbqlq9hfu62SnUye_cNc
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                IdentityModule.this.lambda$listenForCookieChange$1$IdentityModule(message);
            }
        });
        return subscriber;
    }

    private MultiFilterSubscriber listenForIdentityChange() {
        MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
        subscriber.subscribe(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$cBfSJkDwOEZrznikK3ZzToOoxs0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                IdentityModule.this.lambda$listenForIdentityChange$0$IdentityModule(message);
            }
        });
        return subscriber;
    }

    private void onCookiesChanged() {
        if (this.reactApplicationContext.hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(COOKIE_CHANGED_EVENT, null);
        } else {
            Log.e(TAG, "Error: Attempting to send cookiesChange event before React initialized.");
        }
    }

    private void onUserChangedOrNull(UserIdentity userIdentity) {
        new Object[1][0] = userIdentity;
        WritableMap createMap = this.collectionsFactory.createMap();
        UserIdentity userIdentity2 = this.currentUser;
        createMap.putString("action", getAction(userIdentity2, userIdentity));
        if (userIdentity2 != null) {
            createMap.putMap("previous", serializeUserIdentity(userIdentity2));
        }
        if (userIdentity != null) {
            createMap.putMap("current", serializeUserIdentity(userIdentity));
        }
        this.currentUser = userIdentity;
        if (this.reactApplicationContext.hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(USER_CHANGED_EVENT, createMap);
        } else {
            Log.e(TAG, "Error: Attempting to send onUserChange event before React initialized.");
        }
    }

    @NonNull
    private WritableMap serializeUserIdentity(@NonNull UserIdentity userIdentity) {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putString("id", userIdentity.getId());
        createMap.putString("directedId", userIdentity.getDirectedId());
        createMap.putString("name", userIdentity.getName());
        createMap.putString("email", userIdentity.getEmail());
        createMap.putString(WebConstants.WebviewConstants.MARKETPLACE_ID, userIdentity.getOriginalMarketplace() == null ? null : userIdentity.getOriginalMarketplace().getObfuscatedId().toString());
        createMap.putArray(NetworkConstants.FEATURES_KEY, toWritableArray(userIdentity.getFeatures()));
        createMap.putString(MetricsConfiguration.COUNTRY_OF_RESIDENCE, userIdentity.getCountryOfResidence());
        createMap.putMap("tokens", toWritableMap(userIdentity.getTokens()));
        UserProfile userProfile = userIdentity.getUserProfile();
        if (userProfile != null) {
            createMap.putMap(Scopes.PROFILE, createProfileMap(userProfile));
        }
        UserProfile delegatedProfile = userIdentity.getDelegatedProfile();
        if (delegatedProfile != null) {
            createMap.putMap("delegatedProfile", createProfileMap(delegatedProfile));
        }
        return createMap;
    }

    private WritableArray toWritableArray(Set<String> set) {
        WritableArray createArray = this.collectionsFactory.createArray();
        if (set == null) {
            return createArray;
        }
        for (String str : set) {
            createArray.pushString(str);
        }
        return createArray;
    }

    private WritableMap toWritableMap(Map<String, String> map) {
        WritableMap createMap = this.collectionsFactory.createMap();
        if (map == null) {
            return createMap;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            createMap.putString(entry.getKey(), entry.getValue());
        }
        return createMap;
    }

    @ReactMethod
    public void getAccessToken(String str, Promise promise) {
        promise.resolve(this.identityService.getAccessToken(str));
    }

    @ReactMethod
    public void getCookies(String str, String str2, final Promise promise) {
        if (str == null) {
            promise.reject(new Exception("Directed Id can not be null."));
            return;
        }
        String str3 = null;
        FeatureServiceV2 featureServiceV2 = this.featureServiceV2;
        if (featureServiceV2 != null && featureServiceV2.hasAccess("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID", false)) {
            str3 = CookiesDomainUtil.getCookiesDomain(str2);
        }
        this.identityService.getCookiesFromDirectedId(str, str3).subscribe(new Action1() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$pDre5ybLhjZ7xRgv4OvF-RV4X0c
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                IdentityModule.this.lambda$getCookies$9$IdentityModule(promise, (String[]) obj);
            }
        }, new Action1() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$EQogPkmi6A8CWrY1bTJZqZ47eXY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                Promise.this.reject((Throwable) obj);
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getSessionId(final Promise promise) {
        this.identityService.getSessionId(TAG).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).subscribe(new Action1() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$wPrzqQM9YI-rQwqrPZrHiXbV3Zg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                Promise.this.resolve((String) obj);
            }
        }, new Action1() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$u1726NtwCJHErb2C3Q37nyEan64
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                Promise.this.reject("Failed to get the session id", (Throwable) obj);
            }
        });
    }

    @ReactMethod
    public void getUser(Promise promise) {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null) {
            promise.resolve(null);
        } else {
            promise.resolve(serializeUserIdentity(user));
        }
    }

    @ReactMethod
    public void initiateDelegation(ReadableMap readableMap, Promise promise) {
        Log.i(TAG, "Initiating delegation.");
        try {
            Map<String, Object> deepReadableMapToMap = ElementsUtils.deepReadableMapToMap(readableMap);
            if (deepReadableMapToMap != null && deepReadableMapToMap.containsKey(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID) && deepReadableMapToMap.containsKey("firstName")) {
                this.identityService.initiateDelegation(UserProfile.builder().withPersonId((String) deepReadableMapToMap.get(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID)).withFirstName((String) deepReadableMapToMap.get("firstName")).withLastName((String) deepReadableMapToMap.get("lastName")).withDirectedId((String) deepReadableMapToMap.get("directedId")).build(), TAG);
                promise.resolve(null);
            }
            promise.reject("[IdentityService]: Failed to initiate delegation.", "Invalid input provided to initiate delegation.");
        } catch (Exception e) {
            promise.reject("[IdentityService]: Failed to initiate delegation.", e.getMessage());
        }
    }

    public /* synthetic */ void lambda$getCookies$9$IdentityModule(Promise promise, String[] strArr) {
        WritableArray createArray = this.collectionsFactory.createArray();
        for (String str : strArr) {
            createArray.pushString(str);
        }
        promise.resolve(createArray);
    }

    public /* synthetic */ void lambda$listenForCookieChange$1$IdentityModule(Message message) {
        onCookiesChanged();
    }

    public /* synthetic */ void lambda$listenForIdentityChange$0$IdentityModule(Message message) {
        onUserChangedOrNull(this.identityService.getUser(TAG));
    }

    public /* synthetic */ void lambda$signOut$2$IdentityModule(Promise promise) {
        this.routingService.route(RouteName.HOME).clearBackStack();
        this.routingService.clearCurrentRoute();
        promise.resolve(true);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        MultiFilterSubscriber multiFilterSubscriber = this.userChangedSubscription;
        if (multiFilterSubscriber != null) {
            this.eventBus.unsubscribe(multiFilterSubscriber);
            this.userChangedSubscription = null;
        }
        MultiFilterSubscriber multiFilterSubscriber2 = this.cookieChangeSubscription;
        if (multiFilterSubscriber2 != null) {
            this.eventBus.unsubscribe(multiFilterSubscriber2);
            this.cookieChangeSubscription = null;
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        if (this.userChangedSubscription == null) {
            UserIdentity user = this.identityService.getUser(TAG);
            if (!Objects.equals(this.currentUser, user)) {
                onUserChangedOrNull(user);
            }
            this.userChangedSubscription = listenForIdentityChange();
            this.cookieChangeSubscription = listenForCookieChange();
        }
    }

    @ReactMethod
    public void requestReAuthentication(final Promise promise) {
        this.accountService.reAuthenticate().subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).subscribe(new Action1() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$yC4VPJMfTUbi4am4a8lQnGdka70
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                Void r2 = (Void) obj;
                Promise.this.resolve(null);
            }
        }, new Action1() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$MDhtY38DMVIK6DSlIrNBdWzBHcQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                Promise.this.reject("Failed to reAuthenticate user", (Throwable) obj);
            }
        });
    }

    @ReactMethod
    public void signOut(final Promise promise) {
        if (this.identityService.isAuthenticated(TAG)) {
            this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.SIGN_OUT_ATTEMPT, TAG, null);
            this.accountService.signOut().subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).doOnTerminate(new Action0() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$gUWOoxlf4eRyuoc0adwL6FUyKHY
                @Override // rx.functions.Action0
                public final void call() {
                    IdentityModule.this.lambda$signOut$2$IdentityModule(promise);
                }
            }).subscribe($$Lambda$IdentityModule$_37RKf4E3jo3UOb75XG6bHaKlw.INSTANCE, $$Lambda$IdentityModule$GehtPS6vpllpEkR77lpY3Udnq08.INSTANCE);
        }
    }

    @ReactMethod
    public void terminateDelegation(Promise promise) {
        Log.i(TAG, "Terminating delegation.");
        try {
            this.identityService.terminateDelegation(TAG);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("[IdentityService]: Failed to terminate delegation.", e.getMessage());
        }
    }
}
