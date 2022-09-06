package com.amazon.dee.app.elements.bridges;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.elements.api.InitializationPhase;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.services.useragent.UserAgentService;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.HashMap;
import java.util.Map;
@ReactModule(name = AlexaEnvironmentModule.MODULE)
/* loaded from: classes12.dex */
public class AlexaEnvironmentModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String API_GATEWAY_ENDPOINT = "apiGatewayEndpoint";
    private static final String BASE_URI = "baseUri";
    private static final String CORAL_ENDPOINT = "coralEndpoint";
    private static final String COUNTRY_CODE = "countryCode";
    private static final String DEVICE_COUNTRY = "DEVICE_COUNTRY";
    private static final String DOMAIN = "domain";
    private static final String ENDPOINT_CHANGED = "endpointChanged";
    private static final String ID = "id";
    private static final String IS_FONT_V2_ENABLED = "isFontV2Enabled";
    private static final String MARKETPLACE = "marketplace";
    private static final String MODULE = "AlexaEnvironment";
    private static final String RETAIL_ENDPOINT = "retailEndpoint";
    private static final String RETAIL_HOST = "retailHost";
    private static final String SKILLS_STORE_ENDPOINT = "skillsStoreEndpoint";
    private static final String STAGE = "stage";
    static final String TAG = Log.tag(AlexaEnvironmentModule.class);
    private static final String TEST_ARGUMENTS = "testArguments";
    private static final String USER_AGENT = "user_agent";
    private static final String WEB_ENDPOINT = "webEndpoint";
    private final BridgeStatusService bridgeStatusService;
    private final CollectionsFactory collectionsFactory;
    @Nullable
    private MultiFilterSubscriber.FilterUuid endpointsChangedSubscription;
    private final EnvironmentService environmentService;
    private final EventBus eventBus;
    private MultiFilterSubscriber eventBusSubscriber;
    private final Object getEndpointLock;
    private Map<Object, MultiFilterSubscriber.FilterUuid> getEndpointSubscriptions;
    private final IdentityService identityService;
    @Nullable
    private WritableMap previousEndpoint;
    @VisibleForTesting
    final PersistentStorage startupStorage;
    private final TestArgumentsService testArguments;
    private final UserAgentService userAgentService;
    @Nullable
    private MultiFilterSubscriber.FilterUuid userChangedSubscription;

    public AlexaEnvironmentModule(ReactApplicationContext reactApplicationContext, CollectionsFactory collectionsFactory, EnvironmentService environmentService, IdentityService identityService, UserAgentService userAgentService, BridgeStatusService bridgeStatusService, EventBus eventBus, TestArgumentsService testArgumentsService, PersistentStorage persistentStorage) {
        super(reactApplicationContext);
        this.getEndpointLock = new Object();
        this.collectionsFactory = collectionsFactory;
        this.environmentService = environmentService;
        this.identityService = identityService;
        this.userAgentService = userAgentService;
        this.previousEndpoint = null;
        this.bridgeStatusService = bridgeStatusService;
        this.eventBus = eventBus;
        this.getEndpointSubscriptions = new HashMap();
        this.eventBusSubscriber = eventBus.getNewSubscriber();
        this.testArguments = testArgumentsService;
        this.startupStorage = persistentStorage;
        reactApplicationContext.addLifecycleEventListener(this);
    }

    private WritableMap gatherEndpoint(UserIdentity userIdentity) {
        Marketplace marketplace;
        WritableMap createMap = this.collectionsFactory.createMap();
        if (userIdentity != null && userIdentity.getMarketplace() != null) {
            marketplace = userIdentity.getMarketplace();
        } else {
            marketplace = Marketplace.USA;
        }
        createMap.putString("id", marketplace.getObfuscatedId().toString());
        createMap.putString(COUNTRY_CODE, this.environmentService.getDeviceInformation().getStaticDeviceProfileData().get("DEVICE_COUNTRY").toString());
        WritableMap createMap2 = this.collectionsFactory.createMap();
        createMap2.putString(BASE_URI, this.environmentService.getCoralEndpoint());
        createMap2.putString(CORAL_ENDPOINT, this.environmentService.getCoralEndpoint());
        createMap2.putString(WEB_ENDPOINT, this.environmentService.getWebEndpoint());
        createMap2.putString(API_GATEWAY_ENDPOINT, this.environmentService.getApiGatewayEndpoint());
        createMap2.putString(RETAIL_ENDPOINT, this.environmentService.getRetailEndpoint());
        createMap2.putString(RETAIL_HOST, this.environmentService.getRetailHost());
        createMap2.putString(SKILLS_STORE_ENDPOINT, this.environmentService.getSkillsStoreEndpoint());
        createMap2.putString("domain", this.environmentService.getCoralHost());
        createMap2.putMap("marketplace", createMap);
        createMap2.putString("user_agent", this.userAgentService.build());
        return createMap2;
    }

    private boolean isFontV2Enabled() {
        return getReactApplicationContext().hasActiveCatalystInstance() && FontResolver.isLocaleArabic(getReactApplicationContext());
    }

    private void sendEvent(String str, WritableMap writableMap) {
        if (getReactApplicationContext().hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    private void userChanged(UserIdentity userIdentity) {
        WritableMap gatherEndpoint = gatherEndpoint(userIdentity);
        WritableMap writableMap = this.previousEndpoint;
        if (writableMap == null || !writableMap.equals(gatherEndpoint)) {
            this.previousEndpoint = gatherEndpoint;
            sendEvent(ENDPOINT_CHANGED, gatherEndpoint);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(STAGE, this.environmentService.getBuildStage());
        hashMap.put(TEST_ARGUMENTS, this.testArguments.getArgs());
        hashMap.put(IS_FONT_V2_ENABLED, Boolean.valueOf(isFontV2Enabled()));
        return hashMap;
    }

    @ReactMethod
    public void getEndpoint(final Promise promise) {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user != null) {
            promise.resolve(gatherEndpoint(user));
            return;
        }
        synchronized (this.getEndpointLock) {
            final Object obj = new Object();
            this.getEndpointSubscriptions.put(obj, this.eventBusSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaEnvironmentModule$qubaSsFHmUcQHM9Cd0vYMGukSk0
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    AlexaEnvironmentModule.this.lambda$getEndpoint$2$AlexaEnvironmentModule(promise, obj, message);
                }
            }));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE;
    }

    @ReactMethod
    public void getStartupStorageString(String str, Promise promise) {
        PersistentStorage persistentStorage = this.startupStorage;
        if (persistentStorage == null) {
            promise.reject(TAG, "Startup storage is null");
        } else {
            promise.resolve(persistentStorage.getString(str));
        }
    }

    @ReactMethod
    public void initializationComplete() {
        this.bridgeStatusService.setIsReady(true);
    }

    public /* synthetic */ void lambda$getEndpoint$2$AlexaEnvironmentModule(Promise promise, Object obj, Message message) {
        synchronized (this.getEndpointLock) {
            UserIdentity user = this.identityService.getUser(TAG);
            if (user != null) {
                promise.resolve(gatherEndpoint(user));
            } else {
                promise.reject("Failed to get user identity", "no user");
            }
            if (this.getEndpointSubscriptions.containsKey(obj)) {
                this.eventBusSubscriber.unsubscribeFilter(this.getEndpointSubscriptions.remove(obj));
            }
        }
    }

    public /* synthetic */ void lambda$onHostResume$0$AlexaEnvironmentModule(Message message) {
        userChanged(this.identityService.getUser(TAG));
    }

    public /* synthetic */ void lambda$onHostResume$1$AlexaEnvironmentModule(Message message) {
        userChanged(this.identityService.getUser(TAG));
    }

    @ReactMethod
    public void markInitializationPhase(String str) {
        this.bridgeStatusService.setPhaseReady(InitializationPhase.fromString(str));
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.userChangedSubscription;
        if (filterUuid != null) {
            this.eventBusSubscriber.unsubscribeFilter(filterUuid);
            this.userChangedSubscription = null;
        }
        MultiFilterSubscriber.FilterUuid filterUuid2 = this.endpointsChangedSubscription;
        if (filterUuid2 != null) {
            this.eventBusSubscriber.unsubscribeFilter(filterUuid2);
            this.endpointsChangedSubscription = null;
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        if (this.userChangedSubscription == null) {
            this.userChangedSubscription = this.eventBusSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaEnvironmentModule$HvX9z6M7HYb7muCf7J4HaddhCcs
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    AlexaEnvironmentModule.this.lambda$onHostResume$0$AlexaEnvironmentModule(message);
                }
            });
        }
        if (this.endpointsChangedSubscription == null) {
            this.endpointsChangedSubscription = this.eventBusSubscriber.subscribeFilter(new EventTypeMessageFilter("ENDPOINTS_CHANGED"), new MessageHandler() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaEnvironmentModule$U5xLg2QBt7CUUzlP_peX5mgC7y0
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    AlexaEnvironmentModule.this.lambda$onHostResume$1$AlexaEnvironmentModule(message);
                }
            });
        }
    }
}
